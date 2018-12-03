package gov.hhs.usas.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.hhs.usas.dao.ADutyStationDAO;
import gov.hhs.usas.dto.ADutyStationDTO;
import gov.hhs.usas.entity.ADutyStation;

@Service
public class ADutyStationService {

	private static final Log LOG = LogFactory.getLog(ADutyStationService.class);
	
	@Autowired
	ADutyStationDAO dutyStationDAO;

	public List<ADutyStation> getDutyStationByRequestVacancyAndcertificateNumber(String requestNumber, String vacancyNumber, String certificateNumber) {
		LOG.info("Query Certificate Duty Station data for Certificate Number ["+certificateNumber +"]");
		List<ADutyStation> dutyStationList =  this.dutyStationDAO.findByRequestVacancyAndCertificateNumber(requestNumber, vacancyNumber, certificateNumber);

		if(dutyStationList != null){
			LOG.info("Certificate Duty Station data retrieved for Certificate Number ["+certificateNumber +"]");
			LOG.info("Duty Station Count = "+dutyStationList.size());//for null query output, returns value 1
		}
		return dutyStationList;
	}

	public List<ADutyStationDTO> getDutyStationDTOByRequestVacancyAndcertificateNumber(String requestNumber, String vacancyNumber, String certificateNumber) {
		List<ADutyStationDTO> dutyStationDTOList = new ArrayList<ADutyStationDTO>();
		List<ADutyStation> dutyStationList =  this.dutyStationDAO.findByRequestVacancyAndCertificateNumber(requestNumber, vacancyNumber, certificateNumber);

		for(ADutyStation dutyStation: dutyStationList){
			ADutyStationDTO dutyStationDTO = new ADutyStationDTO(dutyStation.getReqVacCertDLID(), dutyStation.getRequestNumber(), dutyStation.getVacancyIdentificationNumber(),
					dutyStation.getCertificateNumber(), dutyStation.getDutyStationName(), dutyStation.getDutyStationCode());
		
			dutyStationDTOList.add(dutyStationDTO);
		}
	
		return dutyStationDTOList;

	}
}
