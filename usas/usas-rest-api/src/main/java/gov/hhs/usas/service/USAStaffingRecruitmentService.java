package gov.hhs.usas.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.hhs.usas.dto.RVacancyAnnouncementDTO;
import gov.hhs.usas.dto.USAStaffingRecruitmentDTO;

@Service
public class USAStaffingRecruitmentService {
	
	private static final Log LOG = LogFactory.getLog(USAStaffingRecruitmentService.class);

	@Autowired
	RVacancyAnnouncementService vacancyAnnouncementService;
	@Autowired
	RPositionsService positionsService;

	public USAStaffingRecruitmentDTO getRecruitmentDataXMLByRequestNumber(String requestNumberParam) {

		USAStaffingRecruitmentDTO recruitmentDTO = new USAStaffingRecruitmentDTO(requestNumberParam);

		try {

			List<RVacancyAnnouncementDTO> vacancyAnnouncementList = this.vacancyAnnouncementService.getVacancyAnnouncementDTOByRequestNumber(requestNumberParam);

			recruitmentDTO.setVacancyAnnouncementList(vacancyAnnouncementList);
			recruitmentDTO.setPositions(this.positionsService.getPositionsDTOByRequestNumber(requestNumberParam));
			recruitmentDTO.setPositionCount(recruitmentDTO.getPositions().getPositionList().size());
		} catch(JDBCConnectionException jdbcException){
			LOG.error("Exception Stack Trace :: ", jdbcException);
			
			recruitmentDTO = new USAStaffingRecruitmentDTO(requestNumberParam);
			recruitmentDTO.setResultCode("Connection Error");
			recruitmentDTO.setFailureMessage("An exception occurred while trying to connect to database.");
			
		} catch (Exception e) {		

			LOG.error("Exception Stack Trace :: ", e);

			recruitmentDTO = new USAStaffingRecruitmentDTO(requestNumberParam);
			recruitmentDTO.setResultCode("No Content");
			recruitmentDTO.setFailureMessage("An exception occurred. Please check the logs.");
		}

		if(recruitmentDTO.getVacancyCount() > 0 && recruitmentDTO.getPositionCount() > 0){
			recruitmentDTO.setResultCode("Success");
		}

		//Case: If there are no vacancies for a given request number, send error message
		if(recruitmentDTO.getVacancyCount() == 0){
			recruitmentDTO = new USAStaffingRecruitmentDTO(requestNumberParam);
			recruitmentDTO.setResultCode("No Content");
			recruitmentDTO.setFailureMessage("No data available for this request number.");
		}
		
		return recruitmentDTO;

	}

}
