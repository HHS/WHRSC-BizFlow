package gov.hhs.usas.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="CertificateResult", propOrder={})
public class RCertificateDTO
{
	@XmlTransient
	private String recVacID;
	@XmlElement(name="Announcement_Number")
	private String announcementNumber;
	@XmlElement(name="Certificate_Type")
	private String certificateType;
	@XmlElement(name="Certificate_Number")
	private String certificateNumber;
	@XmlElement(name="Position_Title")
	private String positionTitle;
	@XmlElement(name="Series")
	private String series;
	@XmlElement(name="Grade")
	private String grade;
	@XmlElement(name="Duty_Location")
	private String dutyLocation;
	@XmlElement(name="Date_Certificate_Issued")
	private String dateCertificateIssued;
	@XmlElement(name="Date_Certificate_Sent_To_SO")
	private String dateCertificateSentToSO;
	@XmlElement(name="Selection_Made")
	private String selectionMade;
	@XmlElement(name="Action_Taken")
	private String actionTaken;
	@XmlElement(name="Date_Hiring_Decision_Received_In_HR")
	private String dateHiringDecisionRecievedInHR;
	@XmlElement(name="Date_Final_Applicant_Statuses_Set")
	private String dateFinalApplcantStatusesSet;
	@XmlElement(name="Date_Audit_Completed")
	private String dateAuditCompleted;

	public RCertificateDTO()
	{
		this.announcementNumber = "";
		this.certificateType = "";
		this.certificateNumber = "";
		this.positionTitle = "";
		this.series = "";
		this.dateCertificateIssued = "";
		this.dateCertificateSentToSO = "";
		this.selectionMade = "";
		this.actionTaken = "";
		this.dateHiringDecisionRecievedInHR = "";
		this.dateFinalApplcantStatusesSet = "";
		this.dateAuditCompleted = "";
	}

	public RCertificateDTO(String recVacID, String announcementNumber, String certificateType, String certificateNumber,
			String positionTitle, String series, String grade, String dutyLocation, String dateCertificateIssued,
			String dateCertificateSentToSO, String selectionMade, String actionTaken,
			String dateHiringDecisionRecievedInHR, String dateFinalApplcantStatusesSet, String dateAuditCompleted) {
		super();
		this.recVacID = recVacID;
		this.announcementNumber = announcementNumber;
		this.certificateType = certificateType;
		this.certificateNumber = certificateNumber;
		this.positionTitle = positionTitle;
		this.series = series;
		this.grade = grade;
		this.dutyLocation = dutyLocation;
		this.dateCertificateIssued = dateCertificateIssued;
		this.dateCertificateSentToSO = dateCertificateSentToSO;
		this.selectionMade = selectionMade;
		this.actionTaken = actionTaken;
		this.dateHiringDecisionRecievedInHR = dateHiringDecisionRecievedInHR;
		this.dateFinalApplcantStatusesSet = dateFinalApplcantStatusesSet;
		this.dateAuditCompleted = dateAuditCompleted;
	}

	public String getAnnouncementNumber()
	{
		return this.announcementNumber;
	}

	public void setAnnouncementNumber(String announcementNumber)
	{
		this.announcementNumber = announcementNumber;
	}

	public String getCertificateType()
	{
		return this.certificateType;
	}

	public void setCertificateType(String certificateType)
	{
		this.certificateType = certificateType;
	}

	public String getCertificateNumber()
	{
		return this.certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber)
	{
		this.certificateNumber = certificateNumber;
	}

	public String getPositionTitle()
	{
		return this.positionTitle;
	}

	public void setPositionTitle(String positionTitle)
	{
		this.positionTitle = positionTitle;
	}


	public String getSeries()
	{
		return this.series;
	}

	public void setSeries(String series)
	{
		this.series = series;
	}

	public String getGrades()
	{
		return this.grade;
	}

	public void setGrades(String grades)
	{
		this.grade = grades;
	}



	public String getDutyLocation()
	{
		return this.dutyLocation;
	}

	public void setDutyLocation(String dutyLocation)
	{
		this.dutyLocation = dutyLocation;
	}

	public String getDateCertificateIssued()
	{
		return this.dateCertificateIssued;
	}

	public void setDateCertificateIssued(String dateCertificateIssued)
	{
		this.dateCertificateIssued = dateCertificateIssued;
	}

	public String getDateCertificateSentToSO()
	{
		return this.dateCertificateSentToSO;
	}

	public void setDateCertificateSentToSO(String dateCertificateSentToSO)
	{
		this.dateCertificateSentToSO = dateCertificateSentToSO;
	}

	public String getSelectionMade()
	{
		return this.selectionMade;
	}

	public void setSelectionMade(String selectionMade)
	{
		this.selectionMade = selectionMade;
	}

	public String getActionTaken()
	{
		return this.actionTaken;
	}

	public void setActionTaken(String actionTaken)
	{
		this.actionTaken = actionTaken;
	}

	public String getDateHiringDecisionRecievedInHR()
	{
		return this.dateHiringDecisionRecievedInHR;
	}

	public void setDateHiringDecisionRecievedInHR(String dateHiringDecisionRecievedInHR)
	{
		this.dateHiringDecisionRecievedInHR = dateHiringDecisionRecievedInHR;
	}

	public String getDateFinalApplcantStatusesSet()
	{
		return this.dateFinalApplcantStatusesSet;
	}

	public void setDateFinalApplcantStatusesSet(String dateFinalApplcantStatusesSet)
	{
		this.dateFinalApplcantStatusesSet = dateFinalApplcantStatusesSet;
	}

	public String getDateAuditCompleted()
	{
		return this.dateAuditCompleted;
	}

	public void setDateAuditCompleted(String dateAuditCompleted)
	{
		this.dateAuditCompleted = dateAuditCompleted;
	}

	@Override
	public String toString()
	{
		return "RCertificateDTO [announcementNumber: " + getAnnouncementNumber() + "| certificateType: " + getCertificateType() 
		+ "| certificateNumber: " + getCertificateNumber() + "| positionTitle: " + getPositionTitle() + "| series: " + getSeries() 
		+ "| gradeList: " + getGrades() + "| dutyLocationList: " + getDutyLocation() + "| dateCertificateIssued: " + getDateCertificateIssued() 
		+ "| dateCertificateSentToSO: " + getDateCertificateSentToSO() + "| selectionMade: " + getSelectionMade() + "| actionTaken: " + getActionTaken()
		+ "| dateHiringDecisionRecievedInHR: " + getDateHiringDecisionRecievedInHR() + "| dateFinalApplcantStatusesSet: " + getDateFinalApplcantStatusesSet() 
		+ "| dateAuditCompleted: " + getDateAuditCompleted() + "]";
	}
}
