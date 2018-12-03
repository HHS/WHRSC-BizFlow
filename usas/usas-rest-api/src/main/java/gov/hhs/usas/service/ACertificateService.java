package gov.hhs.usas.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.hhs.usas.dao.ACertificateDAO;
import gov.hhs.usas.dto.ACertificateDTO;
import gov.hhs.usas.dto.ADutyStationDTO;
import gov.hhs.usas.entity.ACertificate;
import gov.hhs.usas.entity.ADutyStation;

@Service
public class ACertificateService {
	
	private static final Log LOG = LogFactory.getLog(ACertificateService.class);
	
	@Autowired
	ACertificateDAO certificateDao;
	
	@Autowired
	ADutyStationService dutyStationService;

	public ACertificateDTO getCertificateDTOByRequestNumber(String requestNumber, String vacancyNumber) {
		
		LOG.info("Query Vacancy Certificate data for Request Number ["+requestNumber +"]");
		
		ACertificate certificate = this.certificateDao.findByRequestNumber(requestNumber);
		
		List<ADutyStation> dutyStationList = this.dutyStationService.getDutyStationByRequestVacancyAndcertificateNumber(requestNumber, vacancyNumber, certificate.getCertificateNumber());
		List<ADutyStationDTO> dutyStationDTOList = new ArrayList<ADutyStationDTO>();
		for(ADutyStation dutyStation : dutyStationList){
			ADutyStationDTO dutyStationDTO = new ADutyStationDTO(dutyStation.getReqVacCertDLID(), dutyStation.getRequestNumber(), dutyStation.getVacancyIdentificationNumber(),
					dutyStation.getCertificateNumber(), dutyStation.getDutyStationName(), dutyStation.getDutyStationCode());
			dutyStationDTOList.add(dutyStationDTO);
		}
		
		if(certificate != null){
			LOG.info("Vacancy Certificate data retrieved for Request Number ["+requestNumber +"]");
		}
				
		ACertificateDTO certificateDTO = new ACertificateDTO(certificate.getReqVacCertID(), certificate.getRequestNumber(), certificate.getVacancyIdentificationNumber(),
				certificate.getCertificateNumber(), certificate.getCertificateType(), certificate.getPositionTitle(), certificate.getPayPlan(), certificate.getSeries(),
				certificate.getGrade(), certificate.getFullPerformanceLevel(), dutyStationDTOList,
				certificate.getDateCertificateSentToSO(), certificate.getDateHiringDecisionReceivedInHR(), certificate.getDateOfTentativeJobOffer(),
				certificate.getDateOfOfficialJobOffer(), certificate.getDateOfficialOfferSent());
		
        return certificateDTO;
    }
}
