package gov.hhs.induction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.WebServiceIOException;
import org.springframework.ws.soap.client.SoapFaultClientException;

import gov.hhs.induction.schema.ForeignNationalIdInfoType;
import gov.hhs.induction.schema.HHSInductPersonRequiredDataType;
import gov.hhs.induction.schema.IdentificationInformationType;
import gov.hhs.induction.schema.InductPersonApplicantDataType;
import gov.hhs.induction.schema.InductPersonRequest;
import gov.hhs.induction.schema.InductPersonResponse;
import gov.hhs.induction.schema.ReadInductionDataRequest;
import gov.hhs.induction.schema.ReadInductionDataResponse;
import gov.hhs.induction.schema.TransactionHeaderType;


@Service
public class SOAPService {

	private static final Log LOG = LogFactory.getLog(SOAPService.class);
	private static final AtomicInteger count = new AtomicInteger(0); 
	private int transactionOfTheDay;

	@Value("${mode.secure}")
	private boolean secureMode;

	@Autowired
	private SOAPConnector soapConnector;
	@Value("${request.transactionID.appender}")
	private String transactionIDAppender;
	@Value("${request.transactionOrg}")
	private String transactionOrgIdentifier;
	@Value("${request.systemID}")
	private String systemID;
	@Value("${induction.url}")
	private String inductionURL;
	@Value("${message.duplicate}")
	private String duplicateMessage;
	@Value("${message.success}")
	private String successMessage;
	@Value("${error.soapfault}")
	private String soapFaultErrorMessage;
	@Value("${error.webserviceIO}")
	private String webServiceIOErrorMessage;
	@Value("${error.illegalArgument}")
	private String illegalArgumentErrorMessage;
	@Value("${error.other}")
	private String otherErrorMessage;
	@Value("${code.error}")
	private String errorCode;


	/**
	 * This method prepares Induct Person request using Induction request 
	 * received from end user and calls the Induction web service.
	 * The web service response received maps to Induct Person Response object, using 
	 * which Induction Response is prepared for the end user. The method
	 * also translates the exceptions/errors to be easily understood by the user.
	 * The actual message gets logged along with Transaction ID. 
	 * @param inductionRequest
	 * @return
	 */
	@SuppressWarnings("finally")
	public InductionResponse getInductionResponse(InductionRequest inductionRequest){

		InductionResponse inductionResponse = new InductionResponse();
		InductPersonResponse inductPersonResponse = new InductPersonResponse();

		try {
			//prepare InductPerson request using WSDL generated classes and request parameters received
			InductPersonRequest inductPersonRequest = createInductPersonRequest(inductionRequest);
			LOG.info("InductPerson Request [" + getInductPersonRequestAsString(inductPersonRequest) + "]");

			if(secureMode)//web app connects to target web service using mutual authentication over HTTPS
				inductPersonResponse = (InductPersonResponse) soapConnector.callWebService(inductPersonRequest);
			else
				inductPersonResponse = (InductPersonResponse) soapConnector.callWebServiceTest(inductPersonRequest);

			LOG.info("InductPerson Response [" + getInductPersonResponseAsString(inductPersonResponse)+"]");

			if(inductPersonResponse.getInductionResult().get(0).getResultCode() != null)
				inductionResponse.setResultCode(inductPersonResponse.getInductionResult().get(0).getResultCode());
			if(inductPersonResponse.getInductionResult().get(0).getAssignedPI() != null)
				inductionResponse.setHhsid(inductPersonResponse.getInductionResult().get(0).getAssignedPI());
			if(inductPersonResponse.getInductionResult().get(0).getResultMessage() != null)
				inductionResponse.setResultMessage(inductPersonResponse.getInductionResult().get(0).getResultMessage());
			if(inductPersonResponse.getInductionResult().get(0).getFailureDetailMessage() != null)
				inductionResponse.setFailureDetailMessage(inductPersonResponse.getInductionResult().get(0).getFailureDetailMessage());

			if(inductionResponse.getResultCode().contains("Success"))
			{
				inductionResponse.setResultMessage(successMessage.replace("FIRST_NAME", inductionRequest.getFirstName()).replace("LAST_NAME", inductionRequest.getLastName()));
			}
			//If response received has message for duplicate SSN, call ReadInductionData to retrieve original record info
			if(inductionResponse.getResultCode().contains("Reject-Duplicate"))
			{
				ReadInductionDataRequest inductionDataRequest = new ReadInductionDataRequest();
				ReadInductionDataResponse inductionDataResponse = new ReadInductionDataResponse();
				inductionDataRequest.setTransactionHeader(createTransactionHeader());
				inductionDataRequest.setSearchPI(inductionResponse.getHhsid());

				inductionDataResponse = (ReadInductionDataResponse) soapConnector.callWebService(inductionDataRequest);

				//set result message, original first name, original last name
				if(inductionDataResponse.getInductionData().getPersonID().equalsIgnoreCase(inductionResponse.getHhsid())){
//					if(inductionDataResponse.getInductionData().getFirstName().equalsIgnoreCase(inductionRequest.getFirstName()) && (inductionDataResponse.getInductionData().getLastName().equalsIgnoreCase(inductionRequest.getLastName()))){
//						inductionResponse.setResultMessage(samePersonDuplicateMessage.replace("FIRST_NAME", inductionDataResponse.getInductionData().getFirstName()).replace("LAST_NAME", inductionDataResponse.getInductionData().getLastName()));
//					}else
						inductionResponse.setResultMessage(duplicateMessage.replace("FIRST_NAME", inductionDataResponse.getInductionData().getFirstName()).replace("LAST_NAME", inductionDataResponse.getInductionData().getLastName()));
				}

			}

		} catch(SoapFaultClientException e){
			LOG.error("Exception Stack Trace :: ", e);
			inductionResponse.setResultCode(errorCode);
			inductionResponse.setFailureDetailMessage(soapFaultErrorMessage);
		} catch(WebServiceIOException e){
			LOG.error("Exception Stack Trace :: ", e);
			inductionResponse.setResultCode(errorCode);
			inductionResponse.setFailureDetailMessage(webServiceIOErrorMessage);
		} catch(IllegalArgumentException e){
			LOG.error("Exception Stack Trace :: ", e);
			inductionResponse.setResultCode(errorCode);
			inductionResponse.setFailureDetailMessage(illegalArgumentErrorMessage+" "+e.getMessage());
		} catch (Exception e) {
			LOG.error("Exception Stack Trace :: ", e);
			inductionResponse.setResultCode(errorCode);
			inductionResponse.setFailureDetailMessage(otherErrorMessage);
		}finally{
			LOG.info("SCMS Induction response [" + getInductionResponseAsString(inductionResponse) +"]");
			return inductionResponse;
		}
	}

	/**
	 * This method creates the Induct Person request for calling the Induction service.
	 * @param inductionRequest
	 * @return
	 * @throws DatatypeConfigurationException
	 */
	private InductPersonRequest createInductPersonRequest(InductionRequest inductionRequest) throws DatatypeConfigurationException{

		InductPersonRequest newInductPersonRequest = new InductPersonRequest();

		TransactionHeaderType newTransactionHeader = createTransactionHeader();

		InductPersonApplicantDataType newInductPersonData = createInductPersonApplicantData(inductionRequest);

		//add elements to create newInductPersonRequest in the end
		newInductPersonRequest.setTransactionHeader(newTransactionHeader);
		newInductPersonRequest.getInductPersonData().add(newInductPersonData);

		return newInductPersonRequest;
	}

	/**
	 * This method creates the transaction header for Induct Person request.
	 * @return
	 */
	private TransactionHeaderType createTransactionHeader(){
		TransactionHeaderType newTransactionHeader = new TransactionHeaderType();
		String newTransactionID = generateTransactionID();		

		newTransactionHeader.setTransactionID(newTransactionID);
		newTransactionHeader.setTransactionOrgIdentifier(transactionOrgIdentifier);
		newTransactionHeader.setSystemID(systemID);

		return newTransactionHeader;
	}

	/**
	 * This method creates a transaction ID using random number
	 * for transaction header element.
	 * This method is modified to create a transaction ID based 
	 * on the current date and the transaction number of the day.
	 * @return
	 */
	private String generateTransactionID(){

		Date currentDate = new Date();
		String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);
		transactionOfTheDay = count.incrementAndGet(); 
		String newTransactionID = transactionIDAppender+"-"+dateFormat+"-"+transactionOfTheDay;

		return newTransactionID;
	}

	/**
	 * This method creates the applicant data for Induct Person request.
	 * @param inductionRequest
	 * @return
	 * @throws DatatypeConfigurationException
	 */
	private InductPersonApplicantDataType createInductPersonApplicantData(InductionRequest inductionRequest) throws DatatypeConfigurationException{
		InductPersonApplicantDataType newInductPersonApplicantData = new InductPersonApplicantDataType();
		HHSInductPersonRequiredDataType baseData = new HHSInductPersonRequiredDataType();

		baseData.setPersonSponsored(inductionRequest.isPersonSponsored());
		baseData.setFirstName(inductionRequest.getFirstName());
		baseData.setLastName(inductionRequest.getLastName());

		IdentificationInformationType identificationInformation = new IdentificationInformationType();

		if(!inductionRequest.getSsn().isEmpty())
			identificationInformation.setSSN(inductionRequest.getSsn());
		else if(!inductionRequest.getArn().isEmpty())
			identificationInformation.setARN(inductionRequest.getArn());
		else if(!inductionRequest.getVisaNumber().isEmpty())
			identificationInformation.setVisaNumber(inductionRequest.getVisaNumber());
		else if(!inductionRequest.getForeignIDNumber().isEmpty()){
			ForeignNationalIdInfoType foreignNationalIdInfo = new ForeignNationalIdInfoType();
			foreignNationalIdInfo.setIdNumber(inductionRequest.getForeignIDNumber());

			if(!inductionRequest.getForeignIDIssuingCountryAsString().isEmpty()){
				foreignNationalIdInfo.setIssuingCountry(inductionRequest.getForeignIDIssuingCountry());
				identificationInformation.setForeignId(foreignNationalIdInfo);
			}
		}		

		baseData.setIdentificationInfo(identificationInformation);
		baseData.setDOB(DatatypeFactory.newInstance().newXMLGregorianCalendar(inductionRequest.getDateOfBirth()));
		baseData.setEmergencyResponder(inductionRequest.getEmergencyResponder());
		baseData.setOrganization(inductionRequest.getOrganization());
		baseData.setOPDIV(inductionRequest.getOpdiv());
		baseData.setAffiliationCode(inductionRequest.getAffiliationCode());
		baseData.setCredentialCategory(inductionRequest.getCredentialCategory());

		newInductPersonApplicantData.setBaseInductPersonData(baseData);
		return newInductPersonApplicantData;
	}


	/**
	 * This method converts the InductPersonRequest object into a string format.
	 * @param inductPersonRequest
	 * @return
	 */
	private String getInductPersonRequestAsString(InductPersonRequest inductPersonRequest){
		return "TransactionID: " + inductPersonRequest.getTransactionHeader().getTransactionID() 
				+ " | " + " TransactionOrg: " + inductPersonRequest.getTransactionHeader().getTransactionOrgIdentifier()
				+ " | " + " SystemID: " + inductPersonRequest.getTransactionHeader().getSystemID()
				+ " | " + " FirstName: " + inductPersonRequest.getInductPersonData().get(0).getBaseInductPersonData().getFirstName()
				+ " | " + " LastName: " + inductPersonRequest.getInductPersonData().get(0).getBaseInductPersonData().getLastName();

	}

	/**
	 * This method converts the InductPersonResponse object into a string format.
	 * @param inductPersonResponse
	 * @return
	 */
	private String getInductPersonResponseAsString(InductPersonResponse inductPersonResponse){
		return "Result Code: " + inductPersonResponse.getInductionResult().get(0).getResultCode()
				+ " | " + " Assigned PI (HHSID): " + inductPersonResponse.getInductionResult().get(0).getAssignedPI()
				+ " | " + " Result Message: " + inductPersonResponse.getInductionResult().get(0).getResultMessage()
				+ " | " + " Failure Detail: " + inductPersonResponse.getInductionResult().get(0).getFailureDetailMessage();
	}

	/**
	 * This method converts the InductionResponse object into a string format.
	 * @param inductionResponse
	 * @return
	 */
	private String getInductionResponseAsString(InductionResponse inductionResponse){
		return "Result Code: " + inductionResponse.getResultCode()
		+ " | " + " Assigned PI (HHSID): " + inductionResponse.getHhsid()
		+ " | " + " Result Message: " + inductionResponse.getResultMessage()
		+ " | " + " Failure Detail: " + inductionResponse.getFailureDetailMessage();
	}

}
