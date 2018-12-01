package gov.hhs.usas.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import gov.hhs.usas.Util;


@Entity(name="VW_R_VAC_ANN_RESULT")
public class RVacancyAnnouncement
{
	
	@Id
	@Column(name = "REQ_VAC")
	private String reqVacID;
	@Column(name = "REQUEST_NUMBER")
	private String requestNumber;
	private transient int vacancyPositionCount;
	private transient int vacancyCertificateCount;
	@Column(name = "NUMBER_OF_POSITIONS_ADVERTISED")
	private String numberOfPositionsAdvertised;
	@Column(name = "AREA_OF_CONSIDERATION")
	private String areaOfConsideration;
	@Column(name = "INTERDISCIPLINARY_POSITION")
	private String interdisciplinaryPosition;
	@Column(name = "VACANCY_NUMBER")
	private String vacancyIdentificationNumber;
	@Column(name = "VACANCY_ANNOUNCEMENT_NUMBER")
	private String vacancyAnnouncementNumber;
	@Column(name = "ANNOUNCEMENT_TYPE")
	private String announcementType;
	@Column(name = "DATE_ANNOUNCEMENT_POSTED")
	private String dateAnnouncementPosted;
	@Column(name = "DATE_ANNOUNCEMENT_OPENED")
	private String dateAnnouncementOpened;
	@Column(name = "DATE_ANNOUNCEMENT_CLOSED")
	private String dateAnnouncementClosed;
	@Column(name = "DATE_ANNOUNCEMENT_CANCELLED")
	private String dateAnnouncementCancelled;
	private transient List<RVacancyPosition> positionList;
	private transient RApplicantRating applicants;
	private transient List<RCertificate> certificateList;



	public RVacancyAnnouncement()
	{
		this.vacancyPositionCount = 0;
		this.vacancyCertificateCount = 0;
		this.numberOfPositionsAdvertised = "";
		this.areaOfConsideration = "";
		this.interdisciplinaryPosition = "";
		this.vacancyIdentificationNumber = "";
		this.vacancyAnnouncementNumber = "";
		this.announcementType = "";
		this.dateAnnouncementPosted = "";
		this.dateAnnouncementOpened = "";
		this.dateAnnouncementClosed = "";
		this.dateAnnouncementCancelled = "";
		this.positionList = new ArrayList<RVacancyPosition>();
		this.applicants = new RApplicantRating();
		this.certificateList = new ArrayList<RCertificate>();
	}

	public RVacancyAnnouncement(int vacancyPositionCount, int vacancyCertificateCount, String numberOfPositionsAdvertised, String areaOfConsideration, String interdisciplinaryPosition,
			String vacancyIdentificationNumber, String vacancyAnnouncementNumber, String announcementType,
			String dateAnnouncementPosted, String dateAnnouncementOpened, String dateAnnouncementClosed,
			String dateAnnouncementCancelled, List<RVacancyPosition> positionList, RApplicantRating applicants,
			List<RCertificate> certificateList) {
		this.vacancyPositionCount = vacancyPositionCount;
		this.vacancyCertificateCount = vacancyCertificateCount;
		this.numberOfPositionsAdvertised = numberOfPositionsAdvertised;
		this.areaOfConsideration = areaOfConsideration;
		this.interdisciplinaryPosition = interdisciplinaryPosition;
		this.vacancyIdentificationNumber = vacancyIdentificationNumber;
		this.vacancyAnnouncementNumber = vacancyAnnouncementNumber;
		this.announcementType = announcementType;
		this.dateAnnouncementPosted = dateAnnouncementPosted;
		this.dateAnnouncementOpened = dateAnnouncementOpened;
		this.dateAnnouncementClosed = dateAnnouncementClosed;
		this.dateAnnouncementCancelled = dateAnnouncementCancelled;
		this.positionList = positionList;
		this.applicants = applicants;
		this.certificateList = certificateList;
	}

	public String getRequestNumber() {
		return Util.checkForNull(requestNumber);
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public int getVacancyPositionCount()
	{
		return this.vacancyPositionCount;
	}

	public void setVacancyPositionCount(int vacancyPositionCount)
	{
		this.vacancyPositionCount = vacancyPositionCount;
	}

	public int getVacancyCertificateCount()
	{
		return this.vacancyCertificateCount;
	}

	public void setVacancyCertificateCount(int vacancyCertificateCount)
	{
		this.vacancyCertificateCount = vacancyCertificateCount;
	}

	public String getNumberOfPositionsAdvertised()
	{
		return this.numberOfPositionsAdvertised;
	}

	public void setNumberOfPositionsAdvertised(String numberOfPositionsAdvertised)
	{
		this.numberOfPositionsAdvertised = numberOfPositionsAdvertised;
	}

	public String getAreaOfConsideration()
	{
		return Util.checkForNull(areaOfConsideration);
	}

	public void setAreaOfConsideration(String areaOfConsideration)
	{
		this.areaOfConsideration = areaOfConsideration;
	}

	public String getInterdisciplinaryPosition()
	{
		return Util.checkForNull(interdisciplinaryPosition);
	}

	public void setInterdisciplinaryPosition(String interdisciplinaryPosition)
	{
		this.interdisciplinaryPosition = interdisciplinaryPosition;
	}

	public String getVacancyIdentificationNumber()
	{
		return Util.checkForNull(vacancyIdentificationNumber);
	}

	public void setVacancyIdentificationNumber(String vacancyIdentificationNumber)
	{
		this.vacancyIdentificationNumber = vacancyIdentificationNumber;
	}

	public String getVacancyAnnouncementNumber()
	{
		return Util.checkForNull(vacancyAnnouncementNumber);
	}

	public void setVacancyAnnouncementNumber(String vacancyAnnouncementNumber)
	{
		this.vacancyAnnouncementNumber = vacancyAnnouncementNumber;
	}

	public String getAnnouncementType()
	{
		return Util.checkForNull(announcementType);
	}

	public void setAnnouncementType(String announcementType)
	{
		this.announcementType = announcementType;
	}

	public String getDateAnnouncementPosted()
	{
		return Util.checkForNull(dateAnnouncementPosted);
	}

	public void setDateAnnouncementPosted(String dateAnnouncementPosted)
	{
		this.dateAnnouncementPosted = dateAnnouncementPosted;
	}

	public String getDateAnnouncementOpened()
	{
		return Util.checkForNull(dateAnnouncementOpened);
	}

	public void setDateAnnouncementOpened(String dateAnnouncementOpened)
	{
		this.dateAnnouncementOpened = dateAnnouncementOpened;
	}

	public String getDateAnnouncementClosed()
	{
		return Util.checkForNull(dateAnnouncementClosed);
	}

	public void setDateAnnouncementClosed(String dateAnnouncementClosed)
	{
		this.dateAnnouncementClosed = dateAnnouncementClosed;
	}

	public String getDateAnnouncementCancelled()
	{
		return Util.checkForNull(dateAnnouncementCancelled);
	}

	public void setDateAnnouncementCancelled(String dateAnnouncementCancelled)
	{
		this.dateAnnouncementCancelled = dateAnnouncementCancelled;
	}

	public List<RVacancyPosition> getPositionList()
	{
		return this.positionList;
	}

	public void setPositionList(List<RVacancyPosition> positionList)
	{
		this.positionList = positionList;
		this.setVacancyPositionCount(this.positionList.size());
	}

	public RApplicantRating getApplicants()
	{
		return this.applicants;
	}

	public void setApplicants(RApplicantRating applicants)
	{
		this.applicants = applicants;
	}

	public List<RCertificate> getCertificateList()
	{
		return this.certificateList;
	}

	public void setCertificateList(List<RCertificate> certificate)
	{
		this.certificateList = certificate;
		this.setVacancyCertificateCount(this.certificateList.size());
	}

	@Override
	public String toString()
	{
		return "RVacancyAnnouncement [vacancyPositionCount: " + getVacancyPositionCount() + "| vacancyCertificateCount: " 
				+ getVacancyCertificateCount() + "| numberOfPositionsAdvertised: " + getNumberOfPositionsAdvertised() + "|  areaOfConsideration: " 
				+ getAreaOfConsideration() + "|  interdisciplinaryPosition: " + getInterdisciplinaryPosition() + "|  vacancyIdentificationNumber: " 
				+ getVacancyIdentificationNumber() + "|  vacancyAnnouncementNumber: " + getVacancyAnnouncementNumber() + "|  announcementType: " 
				+ getAnnouncementType() + "|  dateAnnouncementPosted: " + getDateAnnouncementPosted() + "|  dateAnnouncementOpened: " 
				+ getDateAnnouncementOpened() + "|  dateAnnouncementClosed: " + getDateAnnouncementClosed() + "|  dateAnnouncementCancelled: " 
				+ getDateAnnouncementCancelled() + "| positionList: " + getPositionList() + "| applicants: " + getApplicants() + "| certificate: " + getCertificateList() + "]";
	}
}

