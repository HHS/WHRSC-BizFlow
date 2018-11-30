package gov.hhs.usas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.hhs.usas.entity.ACertificate;

@Repository
public interface ACertificateDAO extends JpaRepository<ACertificate, String> {
	
	@Query(value = "SELECT * FROM VW_A_CERT_RESULT WHERE REQUEST_NUMBER = ?1", nativeQuery = true)
	public ACertificate findByRequestNumber(String requestNumberParam);
	
	@Query(value = "SELECT DISTINCT * FROM VW_A_CERT_RESULT WHERE REQUEST_NUMBER = ?1 AND VACANCY_NUMBER = ?2", nativeQuery = true)	    
	public ACertificate findByRequestAndVacancyNumber(String requestNumberParam, String vacancyNumberParam);

}
