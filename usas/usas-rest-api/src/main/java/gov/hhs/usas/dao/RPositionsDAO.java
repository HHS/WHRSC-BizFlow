package gov.hhs.usas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.hhs.usas.entity.RPositions;

@Repository
public interface RPositionsDAO  extends JpaRepository<RPositions, String> {

	@Query(value = "SELECT * FROM VW_R_POS_RESULT WHERE VACANCY_NUMBER = ?1 AND REQUEST_NUMBER = ?2", nativeQuery = true)	    
	public RPositions findByVacancyAndRequestNumbers(String vacancyNumberParam, String requestNumberParam);
	
	@Query(value = "SELECT * FROM VW_R_POS_RESULT WHERE REQUEST_NUMBER = ?1", nativeQuery = true)	    
	public RPositions findByRequestNumbers(String requestNumberParam);
	
	
}
