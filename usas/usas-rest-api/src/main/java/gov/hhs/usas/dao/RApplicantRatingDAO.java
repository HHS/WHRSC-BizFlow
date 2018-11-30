package gov.hhs.usas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.hhs.usas.entity.RApplicantRating;

@Repository
public interface RApplicantRatingDAO  extends JpaRepository<RApplicantRating, String> {

	@Query(value = "SELECT * FROM VW_R_APPLICANTS_RESULT WHERE VACANCY_NUMBER = ?1", nativeQuery = true)	    
	public List<RApplicantRating> findByVacancyNumber(String vacancyNumberParam);
	
	@Query(value = "SELECT * FROM VW_R_APPLICANTS_RESULT WHERE VACANCY_NUMBER = ?1 AND REQUEST_NUMBER = ?2", nativeQuery = true)	    
	public RApplicantRating findByVacancyAndRequestNumbers(String vacancyNumberParam, String requestNumberParam);
	
	@Query(value = "SELECT * FROM VW_R_APPLICANTS_RESULT WHERE REQUEST_NUMBER = ?1 AND VACANCY_NUMBER = ?2 AND ANNOUNCEMENT_NUMBER = ?3", nativeQuery = true)	    
	public RApplicantRating findByRequestVacancyAndAnnouncementNumber(String requestNumberParam, String vacancyNumberParam, String announcementNumberParam);
}

