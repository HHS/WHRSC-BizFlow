package gov.hhs.usas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.hhs.usas.entity.RCertificate;

@Repository
public interface RCertificateDAO extends JpaRepository<RCertificate, String> {
	
	@Query(value = "SELECT * FROM VW_R_CERT_RESULT_TWO WHERE REQUEST_NUMBER = ?1", nativeQuery = true)
	public List<RCertificate> findByRequestNumber(String requestNumberParam);
	
	@Query(value = "SELECT DISTINCT * FROM VW_R_CERT_RESULT WHERE REQUEST_NUMBER = ?1 AND VACANCY_NUMBER = ?2 AND ANNOUNCEMENT_NUMBER = ?3", nativeQuery = true)	    
	public List<RCertificate> findByRequestVacancyAndAnnouncementNumber(String requestNumberParam, String vacancyNumberParam, String announcementNumberParam);
}
