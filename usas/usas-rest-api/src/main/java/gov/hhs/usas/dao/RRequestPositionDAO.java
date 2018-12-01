package gov.hhs.usas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.hhs.usas.entity.RRequestPosition;

@Repository
public interface RRequestPositionDAO  extends JpaRepository<RRequestPosition, String> {

	@Query(value = "SELECT * FROM VW_R_REQ_POS_RESULT WHERE REQUEST_NUMBER = ?1", nativeQuery = true)	    
	public List<RRequestPosition> findByRequestNumbers(String requestNumberParam);
	
	@Query(value = "SELECT * FROM VW_R_REQ_POS_RESULT WHERE VACANCY_NUMBER = ?1 AND REQUEST_NUMBER = ?2", nativeQuery = true)	    
	public RRequestPosition findByVacancyAndRequestNumbers(String vacancyNumberParam, String requestNumberParam);
	
}

