package gov.hhs.usas.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Immutable;
import org.springframework.data.annotation.Transient;


@Entity(name="VW_R_VAC_ANN_RESULT")
@Immutable
public class RVacancyAnnouncement
{
	
	@Id
	@Column(name = "REQ_VAC")
	private String reqVacID;
	@Column(name = "REQUEST_NUMBER")
	private String requestNumber;
	@Transient
	private transient int vacancyPositionCount;
	@Transient
	private transient int vacancyCertificateCount;
	@Column(name = "NUMBER_OF_POSITIONS_ADVERTISED")
	private String numberOfPositionsAdvertised;
	@XmlTransient
	private transient List<String> areaOfConsiderationList;
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
	@Transient
	private transient List<RVacancyPosition> positionList;
	@Transient
	private transient RApplicantRating applicants;
	@Transient
	private transient List<RCertificate> certificateList;



	public RVacancyAnnouncement()
	{
		this.vacancyPositionCount = 0;
		this.vacancyCertificateCount = 0;
		this.numberOfPositionsAdvertised = "";
		this.areaOfConsiderationList = new ArrayList<String>();
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

	public RVacancyAnnouncement(int vacancyPositionCount, int vacancyCertificateCount, String numberOfPositionsAdvertised,
			List<String> areaOfConsiderationList, String areaOfConsideration, String interdisciplinaryPosition,
			String vacancyIdentificationNumber, String vacancyAnnouncementNumber, String announcementType,
			String dateAnnouncementPosted, String dateAnnouncementOpened, String dateAnnouncementClosed,
			String dateAnnouncementCancelled, List<RVacancyPosition> positionList, RApplicantRating applicants,
			List<RCertificate> certificateList) {
		this.vacancyPositionCount = vacancyPositionCount;
		this.vacancyCertificateCount = vacancyCertificateCount;
		this.numberOfPositionsAdvertised = numberOfPositionsAdvertised;
		this.areaOfConsiderationList = areaOfConsiderationList;
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
		return requestNumber;
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
		StringBuffer areaOfConsiderationStr = new StringBuffer();
		for (int i = 0; i < this.areaOfConsiderationList.size(); i++) {
			if (i > 0) {
				areaOfConsiderationStr.append(";" + (String)this.areaOfConsiderationList.get(i));
			} else {
				areaOfConsiderationStr.append((String)this.areaOfConsiderationList.get(i));
			}
		}
		this.areaOfConsideration = areaOfConsiderationStr.toString();
		return this.areaOfConsideration;
	}

	public void setAreaOfConsideration(List<String> areaOfConsideration)
	{
		this.areaOfConsiderationList = areaOfConsideration;
		this.areaOfConsideration = getAreaOfConsideration();
	}

	public void addAreaOfConsideration(String areaOfConsideration)
	{
		if ((!this.areaOfConsiderationList.contains(areaOfConsideration)) && (areaOfConsideration.trim().length() > 0)) {
			this.areaOfConsiderationList.add(areaOfConsideration);
		}
		this.areaOfConsideration = getAreaOfConsideration();
	}

	public String getInterdisciplinaryPosition()
	{
		return this.interdisciplinaryPosition;
	}

	public void setInterdisciplinaryPosition(String interdisciplinaryPosition)
	{
		this.interdisciplinaryPosition = interdisciplinaryPosition;
	}

	public String getVacancyIdentificationNumber()
	{
		return this.vacancyIdentificationNumber;
	}

	public void setVacancyIdentificationNumber(String vacancyIdentificationNumber)
	{
		this.vacancyIdentificationNumber = vacancyIdentificationNumber;
	}

	public String getVacancyAnnouncementNumber()
	{
		return this.vacancyAnnouncementNumber;
	}

	public void setVacancyAnnouncementNumber(String vacancyAnnouncementNumber)
	{
		this.vacancyAnnouncementNumber = vacancyAnnouncementNumber;
	}

	public String getAnnouncementType()
	{
		return this.announcementType;
	}

	public void setAnnouncementType(String announcementType)
	{
		this.announcementType = announcementType;
	}

	public String getDateAnnouncementPosted()
	{
		if(this.dateAnnouncementPosted == null)
			return "";
		return this.dateAnnouncementPosted;
	}

	public void setDateAnnouncementPosted(String dateAnnouncementPosted)
	{
		this.dateAnnouncementPosted = dateAnnouncementPosted;
	}

	public String getDateAnnouncementOpened()
	{
		if(this.dateAnnouncementOpened == null)
			return "";
		return this.dateAnnouncementOpened;
	}

	public void setDateAnnouncementOpened(String dateAnnouncementOpened)
	{
		this.dateAnnouncementOpened = dateAnnouncementOpened;
	}

	public String getDateAnnouncementClosed()
	{
		if(this.dateAnnouncementClosed == null)
			return "";
		return this.dateAnnouncementClosed;
	}

	public void setDateAnnouncementClosed(String dateAnnouncementClosed)
	{
		this.dateAnnouncementClosed = dateAnnouncementClosed;
	}

	public String getDateAnnouncementCancelled()
	{
		if(this.dateAnnouncementCancelled == null)
			return "";
		return this.dateAnnouncementCancelled;
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

	public void addPosition(RVacancyPosition newPosition)
	{
		if (!this.positionList.contains(newPosition))
		{
			this.positionList.add(newPosition);
			this.vacancyPositionCount += 1;
		}
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

	public void addCertificate(RCertificate newCertificate)
	{
		if (!this.certificateList.contains(newCertificate))
		{
			this.certificateList.add(newCertificate);
			this.vacancyCertificateCount += 1;
		}
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

