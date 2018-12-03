package gov.hhs.usas.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class RApplicantRatingDTO
{
	@XmlTransient
	private String recVacID;
	@XmlTransient
	private String requestNumber;
	@XmlElement(name="Vacancy_Identification_Number")
	private String vacancyIdentificationNumber;
	@XmlElement(name="Announcement_Number")
	private String announcementNumber;
	@XmlElement(name="Total_Number_Of_Applicants")
	private String totalNumberOfApplicants;
	@XmlElement(name="Total_Number_Of_Eligible_Applicants")
	private String totalNumberOfEligibleApplicants;
	@XmlElement(name="Total_Number_Of_Unique_Referred_Applicants")
	private String totalNumberOfUniqueReferredApplicants;
	@XmlElement(name="Date_Applicants_Notified_Eligibility_Status")
	private String dateApplicantsNotifiedEligibilityStatus;
	@XmlElement(name="Date_Applicants_Notified_Referral_Status")
	private String dateApplicantsNotifiedReferralStatus;

	public RApplicantRatingDTO() {}

	public RApplicantRatingDTO(String recVacID, String requestNumber, String vacancyIdentificationNumber,
			String announcementNumber, String totalNumberOfApplicants, String totalNumberOfEligibleApplicants,
			String totalNumberOfUniqueReferredApplicants, String dateApplicantsNotifiedEligibilityStatus,
			String dateApplicantsNotifiedReferralStatus) {
		super();
		this.recVacID = recVacID;
		this.requestNumber = requestNumber;
		this.vacancyIdentificationNumber = vacancyIdentificationNumber;
		this.announcementNumber = announcementNumber;
		this.totalNumberOfApplicants = totalNumberOfApplicants;
		this.totalNumberOfEligibleApplicants = totalNumberOfEligibleApplicants;
		this.totalNumberOfUniqueReferredApplicants = totalNumberOfUniqueReferredApplicants;
		this.dateApplicantsNotifiedEligibilityStatus = dateApplicantsNotifiedEligibilityStatus;
		this.dateApplicantsNotifiedReferralStatus = dateApplicantsNotifiedReferralStatus;
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
		return "RApplicantRatingDTO [announcementNumber: " + getAnnouncementNumber() + "| totalNumberOfApplicants: " + getTotalNumberOfApplicants() 
		+ "| totalNumberOfEligibleApplicants: " + getTotalNumberOfEligibleApplicants() + "| totalNumberOfUniqueReferredApplicants: " + getTotalNumberOfUniqueReferredApplicants() 
		+ "|  dateApplicantsNotifiedEligibilityStatus: " + getDateApplicantsNotifiedEligibilityStatus() + "| dateApplicantsNotifiedReferralStatus: " + getDateApplicantsNotifiedReferralStatus() + "]";
	}
}

