package gov.hhs.usas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.hhs.usas.entity.ADutyStation;

@Repository
public interface ADutyStationDAO extends JpaRepository<ADutyStation, String> {

	@Query(value = "SELECT DISTINCT * FROM VW_A_DUTY_LOC_RESULT WHERE REQUEST_NUMBER = ?1 AND VACANCY_NUMBER = ?2 AND CERTIFICATE_NUMBER = ?3", nativeQuery = true)	    
	public List<ADutyStation> findByRequestVacancyAndCertificateNumber(String requestNumberParam, String vacancyNumberParam, String certificateNumberParam);

}
