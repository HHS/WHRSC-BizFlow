package gov.hhs.usas.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.hhs.usas.dao.RVacancyPositionDAO;
import gov.hhs.usas.dto.RVacancyPositionDTO;
import gov.hhs.usas.entity.RVacancyPosition;

@Service
public class RVacancyPositionService {
	
	private static final Log LOG = LogFactory.getLog(RVacancyPositionService.class);

	@Autowired
	RVacancyPositionDAO positionDao;
	
	public List<RVacancyPosition> getPositionByVacancyNumber(String vacancyNumber) {
        return this.positionDao.findByVacancyNumber(vacancyNumber);
    }
	
	public List<RVacancyPosition> getPositionByVacancyAndRequestNumber(String vacancyNumber, String requestNumber) {
        return this.positionDao.findByVacancyAndRequestNumbers(vacancyNumber, requestNumber);
    }
	
	public List<RVacancyPositionDTO> getPositionDTOByVacancyAndRequestNumber(String vacancyNumber, String requestNumber) {
		LOG.info("Query Vacancy Position data for Request Number ["+requestNumber +"], Vacancy Number ["+vacancyNumber +"]");
		List<RVacancyPositionDTO> vacancyPositionDTOList = new ArrayList<RVacancyPositionDTO>();
		List<RVacancyPosition> vacancyPositionList = this.positionDao.findByVacancyAndRequestNumbers(vacancyNumber, requestNumber);
		if(vacancyPositionList != null){
			LOG.info("Vacancy Position data retrieved for Request Number ["+requestNumber +"], Vacancy Number ["+vacancyNumber +"]");
			LOG.info("Vacancy Position Count = "+vacancyPositionList.size());
		}
				
		for(RVacancyPosition vacancyPosition : vacancyPositionList){
			RVacancyPositionDTO vacancyPositionDTO = new RVacancyPositionDTO(vacancyPosition.getReqVacID(), vacancyPosition.getRequestNumber(), vacancyPosition.getVacancyNumber(), 
					vacancyPosition.getTitle(), vacancyPosition.getPayPlan(),
					vacancyPosition.getSeries(), vacancyPosition.getGrades(), vacancyPosition.getFullPerformanceLevel(), vacancyPosition.getDutyLocation());
			
			vacancyPositionDTOList.add(vacancyPositionDTO);			
		}
		
		return vacancyPositionDTOList;
    }
}
