package gov.hhs.usas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;

@Entity(name="VW_R_APPLICANTS_RESULT")
@Immutable
public class RApplicantRating
{
	@Id
	@Column(name = "REQ_VAC")
	private String recVacID;
	@Column(name = "REQUEST_NUMBER")
	private String requestNumber;
	@Column(name = "VACANCY_NUMBER")
	private String vacancyIdentificationNumber;
	@Column(name = "VACANCY_ANNOUNCEMENT_NUMBER")
	private String announcementNumber;
	@Column(name = "TOTAL_APPLICANTS")
	private String totalNumberOfApplicants;
	@Column(name = "TOTAL_ELIGIBLE_APPLICANTS")
	private String totalNumberOfEligibleApplicants;
	@Column(name = "TOTAL_REFERRED_APPLICANTS")
	private String totalNumberOfUniqueReferredApplicants;
	@Column(name = "DATE_APP_NOTFD_ELG_STATUS")
	private String dateApplicantsNotifiedEligibilityStatus;
	@Column(name = "DATE_APP_NOTFD_RFRL_STATUS")
	private String dateApplicantsNotifiedReferralStatus;

	public RApplicantRating() {}

	public RApplicantRating(String vacancyAnnouncementNumber, String announcementNumber, String totalNumberOfApplicants, String totalNumberOfEligibleApplicants, String totalNumberOfUniqueReferredApplicants, String dateApplicantsNotifiedEligibilityStatus, String dateApplicantsNotifiedReferralStatus)
	{
		this.vacancyIdentificationNumber = vacancyAnnouncementNumber;
		this.announcementNumber = announcementNumber;
		this.totalNumberOfApplicants = totalNumberOfApplicants;
		this.totalNumberOfEligibleApplicants = totalNumberOfEligibleApplicants;
		this.totalNumberOfUniqueReferredApplicants = totalNumberOfUniqueReferredApplicants;
		this.dateApplicantsNotifiedEligibilityStatus = dateApplicantsNotifiedEligibilityStatus;
		this.dateApplicantsNotifiedReferralStatus = dateApplicantsNotifiedReferralStatus;
	}

	public String getRecVacID() {
		return recVacID;
	}

	public void setRecVacID(String recVacID) {
		this.recVacID = recVacID;
	}

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public String getVacancyIdentificationNumber()
	{
		return this.vacancyIdentificationNumber;
	}

	public void setVacancyIdentificationNumber(String vacancyIdentificationNumber)
	{
		this.vacancyIdentificationNumber = vacancyIdentificationNumber;
	}

	public String getAnnouncementNumber()
	{
		return this.announcementNumber;
	}

	public void setAnnouncementNumber(String announcementNumber)
	{
		this.announcementNumber = announcementNumber;
	}

	public String getTotalNumberOfApplicants()
	{
		return this.totalNumberOfApplicants;
	}

	public void setTotalNumberOfApplicants(String totalNumberOfApplicants)
	{
		this.totalNumberOfApplicants = totalNumberOfApplicants;
	}

	public String getTotalNumberOfEligibleApplicants()
	{
		return this.totalNumberOfEligibleApplicants;
	}

	public void setTotalNumberOfEligibleApplicants(String totalNumberOfEligibleApplicants)
	{
		this.totalNumberOfEligibleApplicants = totalNumberOfEligibleApplicants;
	}

	public String getTotalNumberOfUniqueReferredApplicants()
	{
		return this.totalNumberOfUniqueReferredApplicants;
	}

	public void setTotalNumberOfUniqueReferredApplicants(String totalNumberOfUniqueReferredApplicants)
	{
		this.totalNumberOfUniqueReferredApplicants = totalNumberOfUniqueReferredApplicants;
	}

	public String getDateApplicantsNotifiedEligibilityStatus()
	{
		return this.dateApplicantsNotifiedEligibilityStatus;
	}

	public void setDateApplicantsNotifiedEligibilityStatus(String dateApplicantsNotifiedEligibilityStatus)
	{
		this.dateApplicantsNotifiedEligibilityStatus = dateApplicantsNotifiedEligibilityStatus;
	}

	public String getDateApplicantsNotifiedReferralStatus()
	{
		return this.dateApplicantsNotifiedReferralStatus;
	}

	public void setDateApplicantsNotifiedReferralStatus(String dateApplicantsNotifiedReferralStatus)
	{
		this.dateApplicantsNotifiedReferralStatus = dateApplicantsNotifiedReferralStatus;
	}

	@Override
	public String toString()
	{
		return "RApplicantRating [announcementNumber: " + getAnnouncementNumber() + "| totalNumberOfApplicants: " + getTotalNumberOfApplicants() 
		+ "| totalNumberOfEligibleApplicants: " + getTotalNumberOfEligibleApplicants() + "| totalNumberOfUniqueReferredApplicants: " + getTotalNumberOfUniqueReferredApplicants() 
		+ "|  dateApplicantsNotifiedEligibilityStatus: " + getDateApplicantsNotifiedEligibilityStatus() + "| dateApplicantsNotifiedReferralStatus: " + getDateApplicantsNotifiedReferralStatus() + "]";
	}
}

