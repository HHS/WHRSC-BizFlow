package gov.hhs.usas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.hhs.usas.entity.RVacancyAnnouncement;

@Repository
public interface RVacancyAnnouncementDAO  extends JpaRepository<RVacancyAnnouncement, String> {

	@Query(value = "SELECT * FROM VW_R_VAC_ANN_RESULT WHERE REQUEST_NUMBER = ?1", nativeQuery = true)	    
	public List<RVacancyAnnouncement> findByRequestNumber(String requestNumberParam);
	
}