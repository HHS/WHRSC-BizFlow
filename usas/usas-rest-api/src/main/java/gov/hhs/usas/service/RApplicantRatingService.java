package gov.hhs.usas.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.hhs.usas.dao.RApplicantRatingDAO;
import gov.hhs.usas.dto.RApplicantRatingDTO;
import gov.hhs.usas.entity.RApplicantRating;


@Service
public class RApplicantRatingService {
	
	private static final Log LOG = LogFactory.getLog(RApplicantRatingService.class);

	@Autowired
	RApplicantRatingDAO applicantRatingDao;
	
	public List<RApplicantRating> getApplicantRatingByVacancyNumber(String vacancyNumber) {
        return this.applicantRatingDao.findByVacancyNumber(vacancyNumber);
    }
	
	public RApplicantRating getApplicantRatingByVacancyAndRequestNumber(String vacancyNumber, String requestNumber) {
        return this.applicantRatingDao.findByVacancyAndRequestNumbers(vacancyNumber, requestNumber);
    }
	
	public RApplicantRatingDTO getApplicantRatingDTOByVacancyAndRequestNumber(String vacancyNumber, String requestNumber) {
		
		LOG.info("Query Vacancy Applicant Rating data for Request Number ["+requestNumber +"], Vacancy Number ["+vacancyNumber +"]");		
		
		RApplicantRating applicantRating =  this.applicantRatingDao.findByVacancyAndRequestNumbers(vacancyNumber, requestNumber);
		if(applicantRating != null){
			LOG.info("Vacancy Applicant Rating data retrieved for Request Number ["+requestNumber +"], Vacancy Number ["+vacancyNumber +"]");
			LOG.info("Vacancy Total Applicant Count = "+applicantRating.getTotalNumberOfApplicants());
		}		
		
		RApplicantRatingDTO applicantRatingDTO = new RApplicantRatingDTO(applicantRating.getRecVacID(), applicantRating.getRequestNumber(), applicantRating.getVacancyIdentificationNumber(),
				applicantRating.getAnnouncementNumber(), applicantRating.getTotalNumberOfApplicants(), applicantRating.getTotalNumberOfEligibleApplicants(),
				applicantRating.getTotalNumberOfUniqueReferredApplicants(), applicantRating.getDateApplicantsNotifiedEligibilityStatus(),
				applicantRating.getDateApplicantsNotifiedReferralStatus());
		
		return applicantRatingDTO;
		
    }
}

