package gov.hhs.usas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.hhs.usas.dao.RRequestPositionDAO;
import gov.hhs.usas.dto.RRequestPositionDTO;
import gov.hhs.usas.entity.RRequestPosition;

@Service
public class RRequestPositionService {

	@Autowired
	RRequestPositionDAO requestPositionDao;
	
/*	public List<RRequestPosition> getRequestPositionByRequestNumber(String requestNumber) {
        return this.requestPositionDao.findByRequestNumbers(requestNumber);
    }*/
	
	public List<RRequestPositionDTO> getRequestPositionDTOByRequestNumber(String requestNumber) {
		List<RRequestPositionDTO> requestPositionDTOList = new ArrayList<RRequestPositionDTO>();
		List<RRequestPosition> requestPositionList = this.requestPositionDao.findByRequestNumbers(requestNumber);
		
		for(int i=0; i<requestPositionList.size();i++){
			RRequestPosition requestPosition = requestPositionList.get(i);
			RRequestPositionDTO requestPositionDTO = new RRequestPositionDTO(requestPosition.getRequestNumber(), 
					requestPosition.getTitle(), requestPosition.getDutyLocations(), 
					requestPosition.getPayPlan(), requestPosition.getSeries(),
					requestPosition.getGrades());
			
			requestPositionDTOList.add(i, requestPositionDTO);
		}
		
		
        return requestPositionDTOList;
    }
	
/*	public RRequestPositionDTO getPositionDTOByVacancyAndRequestNumber(String vacancyNumber, String requestNumber) {
		List<RRequestPositionDTO> requestPositionDTOList = new ArrayList<RRequestPositionDTO>();
		List<RRequestPosition> requestPositionList = this.positionDao.findByRequestNumbers(requestNumber);
		
		for(int i=0; i<requestPositionList.size();i++){
			RRequestPositionDTO requestPositionDTO = new RRequestPositionDTO(requestPositionList.get(i).getRequestNumber(), 
					requestPositionList.get(i).getTitle(), requestPositionList.get(i).getDutyLocations(), 
					requestPositionList.get(i).getPayPlan(), requestPositionList.get(i).getSeries(),
					requestPositionList.get(i).getGrades());
			
			requestPositionDTOList.add(i, requestPositionDTO);
		}
		
		
        return requestPositionDTOList;
    }*/
}
