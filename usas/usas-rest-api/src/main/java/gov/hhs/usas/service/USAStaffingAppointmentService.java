package gov.hhs.usas.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.hhs.usas.dto.AVacancyAnnouncementDTO;
import gov.hhs.usas.dto.USAStaffingAppointmentDTO;

@Service
public class USAStaffingAppointmentService {
	
	private static final Log LOG = LogFactory.getLog(USAStaffingAppointmentService.class);
	
	@Autowired
	AVacancyAnnouncementService vacancyAnnouncementService;

	public USAStaffingAppointmentDTO getAppointmentDataXMLByRequestNumber(String requestNumberParam) {

		USAStaffingAppointmentDTO appointmentDTO = new USAStaffingAppointmentDTO();
		appointmentDTO.setRequestNumber(requestNumberParam);
		
		try {
			List<AVacancyAnnouncementDTO> vacancyAnnouncementList = this.vacancyAnnouncementService.getVacancyAnnouncementDTOByRequestNumber(requestNumberParam);
			appointmentDTO.setVacancyAnnouncementList(vacancyAnnouncementList);
		} catch(JDBCConnectionException jdbcException){
			LOG.error("Exception Stack Trace :: ", jdbcException);
			
			appointmentDTO = new USAStaffingAppointmentDTO(requestNumberParam);
			appointmentDTO.setResultCode("Connection Error");
			appointmentDTO.setFailureMessage("An exception occurred while trying to connect to database.");
			
		} catch (Exception e) {
			LOG.error("Exception Stack Trace :: ", e);
			appointmentDTO = new USAStaffingAppointmentDTO(requestNumberParam);
			appointmentDTO.setResultCode("No Content");
			appointmentDTO.setFailureMessage("An exception occurred. Please check the logs.");
		}
		
		if(appointmentDTO.getVacancyCount() > 0){
			appointmentDTO.setResultCode("Success");
		}

		//Case: If there are no vacancies for a given request number, send error message
		if(appointmentDTO.getVacancyCount() == 0){
			appointmentDTO = new USAStaffingAppointmentDTO(requestNumberParam);
			appointmentDTO.setResultCode("No Content");
			appointmentDTO.setFailureMessage("No data available for this request number.");
		}		
		
		return appointmentDTO;
	}
	
}
