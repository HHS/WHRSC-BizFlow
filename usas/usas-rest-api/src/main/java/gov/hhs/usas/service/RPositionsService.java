package gov.hhs.usas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.hhs.usas.dao.RPositionsDAO;
import gov.hhs.usas.dto.RPositionsDTO;
import gov.hhs.usas.entity.RPositions;

@Service
public class RPositionsService {

	@Autowired
	RPositionsDAO positionsDAO;
	@Autowired
	RRequestPositionService positionService;

/*	public RPositions getPositionsByVacancyAndRequestNumber(String vacancyNumberParam, String requestNumberParam) {

		RPositions positions = this.positionsDAO.findByVacancyAndRequestNumbers(vacancyNumberParam, requestNumberParam);
		positions.setPositionList(this.positionService.getRequestPositionByRequestNumber(requestNumberParam));		

		return positions;
	}*/

/*	public RPositions getPositionsByRequestNumber(String requestNumberParam) {

		RPositions positions = this.positionsDAO.findByRequestNumbers(requestNumberParam);
		positions.setPositionList(this.positionService.getRequestPositionByRequestNumber(requestNumberParam));		

		return positions;
	}*/

	public RPositionsDTO getPositionsDTOByRequestNumber(String requestNumberParam) {

		RPositions positions = this.positionsDAO.findByRequestNumbers(requestNumberParam);

		RPositionsDTO positionsDTO;
		if(positions != null && positions.getRequestNumber() != null && positions.getClearanceLevelRequiredForPosition() != null){
			positionsDTO = new RPositionsDTO(positions.getRequestNumber(), 
					positions.getPositionCount(), positions.getClearanceLevelRequiredForPosition(),
					this.positionService.getRequestPositionDTOByRequestNumber(requestNumberParam));
		}else{
			positionsDTO = new RPositionsDTO();
		}
		return positionsDTO;
	}


/*	public RPositionsDTO getPositionsDTOByVacancyAndRequestNumber(List<String> vacancyNumberParamList, String requestNumberParam) {

		RPositions positions = this.positionsDAO.findByRequestNumbers(requestNumberParam);//find Clearance Level Required field only

		RPositionsDTO positionsDTO = new RPositionsDTO(positions.getRequestNumber(), positions.getPositionCount(),
				positions.getClearanceLevelRequiredForPosition(),
				this.positionService.getRequestPositionDTOByRequestNumber(requestNumberParam));

		return positionsDTO;
	}*/

}
