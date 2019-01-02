package gov.hhs.induction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author prabhjyot.virdi
 * This class as main controller class for the
 * Smart Card Management System SOAP Web Service Client SOAP Client application. 
 *
 */
@Controller
@RequestMapping({"/induction"})
public class SOAPController
{
	private static final Log LOG = LogFactory.getLog(SOAPController.class);

	@Autowired
	SOAPService soapService;	
	

	/**
	 * This method receives the request to induct a new applicant 
	 * and returns the response received from Induction web service. 
	 * 
	 * @param inductionRequest - Object with all required parameters for Induction service
	 * @return response object with result code, etc.
	 */
	@RequestMapping(value = "/inductPerson", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public InductionResponse getResponse(@RequestBody InductionRequest inductionRequest)
	{
		InductionResponse inductionResponse = new InductionResponse();
		if(inductionRequest.getFirstName().isEmpty() || inductionRequest.getLastName().isEmpty()){
			LOG.info("SCMS Induction request has invalid FirstName and/or LastName.");
			inductionResponse.setResultCode("Failed");
			inductionResponse.setFailureDetailMessage("Invalid FirstName and/or LastName.");
			return inductionResponse;
		}else{
			LOG.info("SCMS Induction request received for [" + inductionRequest.getFirstName() + " " + inductionRequest.getLastName() +"]");
			inductionResponse = soapService.getInductionResponse(inductionRequest);
			LOG.info("SCMS Induction response sent for [" + inductionRequest.getFirstName() + " " + inductionRequest.getLastName()+"]");
			return inductionResponse;
		}
	}	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String testStartup(){
		LOG.info("RESOURCE REQUEST RECEIVED /test");
		return "SCMS Induction Client Deployment Test Successful";
	}

}

