package gov.hhs.usas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.hhs.usas.entity.RVacancyPosition;

@Repository
public interface RVacancyPositionDAO  extends JpaRepository<RVacancyPosition, String> {

	@Query(value = "SELECT * FROM VW_R_VAC_POS_RESULT WHERE VACANCY_NUMBER = ?1", nativeQuery = true)	    
	public List<RVacancyPosition> findByVacancyNumber(String vacancyNumberParam);
	
	@Query(value = "SELECT * FROM VW_R_VAC_POS_RESULT WHERE VACANCY_NUMBER = ?1 AND REQUEST_NUMBER = ?2", nativeQuery = true)	    
	public List<RVacancyPosition> findByVacancyAndRequestNumbers(String vacancyNumberParam, String requestNumberParam);
	
}
