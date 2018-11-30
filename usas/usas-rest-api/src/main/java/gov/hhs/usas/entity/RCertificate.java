package gov.hhs.usas.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import gov.hhs.usas.Util;

@Entity(name="VW_R_CERT_RESULT")
public class RCertificate
{
	
	@Id
	@Column(name = "REQ_VAC_CERT")
	private String recVacCertID; 
	@Column(name = "REQUEST_NUMBER")
	private String requestNumber;
	@Column(name = "VACANCY_NUMBER")
	private transient String vacancyNumber;
	@Column(name = "ANNOUNCEMENT_NUMBER")
	private String announcementNumber;
	@Column(name="CERTIFICATE_TYPE")
	private String certificateType;
	@Column(name="CERTIFICATE_NUMBER")
	private String certificateNumber;
	@Column(name="POSITION_TITLE")
	private String positionTitle;
	@Column(name="SERIES")
	private String series;
	@Column(name="GRADE")
	private String grade;
	@Column(name="DUTY_LOCATION")
	private String dutyLocation;
	@Column(name="DATE_CERTIFICATE_ISSUED")
	private String dateCertificateIssued;
	@Column(name="DATE_CERTIFICATE_SENT_TO_SO")
	private String dateCertificateSentToSO;
	@Column(name="SELECTIONS_MADE")
	private String selectionMade;
	@Column(name="ACTION_TAKEN")
	private String actionTaken;
	@Column(name="DATE_HIRING_DECISN_RECD_IN_HR")
	private String dateHiringDecisionRecievedInHR;
	@Column(name="INITIAL_NOTIFICATION_SEND_DATE")//change column name in the view?
	private String dateFinalApplcantStatusesSet;
	@Column(name="DATE_AUDIT_COMPLETED")
	private String dateAuditCompleted;

	public RCertificate()
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

	public RCertificate(String announcementNumber, String certificateType, String certificateNumber, String positionTitle,
			List<String> seriesList, String series, List<String> gradeList, List<String> dutyLocationList, String grade,
			String dutyLocation, String dateCertificateIssued, String dateCertificateSentToSO, String selectionMade,
			String actionTaken, String dateHiringDecisionRecievedInHR, String dateFinalApplcantStatusesSet,
			String dateAuditCompleted) {
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

	public String getRecVacCertID() {
		return Util.checkForNull(recVacCertID);
	}

	public void setRecVacCertID(String recVacCertID) {
		this.recVacCertID = recVacCertID;
	}

	public String getRequestNumber() {
		return Util.checkForNull(requestNumber);
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public String getVacancyNumber() {
		return Util.checkForNull(vacancyNumber);
	}

	public void setVacancyNumber(String vacancyNumber) {
		this.vacancyNumber = vacancyNumber;
	}

	public String getGrade() {
		return Util.checkForNull(grade);
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getAnnouncementNumber()
	{
		return Util.checkForNull(announcementNumber);
	}

	public void setAnnouncementNumber(String announcementNumber)
	{
		this.announcementNumber = announcementNumber;
	}

	public String getCertificateType()
	{
		return Util.checkForNull(certificateType);
	}

	public void setCertificateType(String certificateType)
	{
		this.certificateType = certificateType;
	}

	public String getCertificateNumber()
	{
		return Util.checkForNull(certificateNumber);
	}

	public void setCertificateNumber(String certificateNumber)
	{
		this.certificateNumber = certificateNumber;
	}

	public String getPositionTitle()
	{
		return Util.checkForNull(positionTitle);
	}

	public void setPositionTitle(String positionTitle)
	{
		this.positionTitle = positionTitle;
	}


	public String getSeries()
	{
		return Util.checkForNull(series);
	}

	public String getDutyLocation()
	{
		return Util.checkForNull(dutyLocation);
	}

	public void setDutyLocation(String dutyLocation)
	{
		this.dutyLocation = dutyLocation;
	}

	public String getDateCertificateIssued()
	{
		return Util.checkForNull(dateCertificateIssued);
	}

	public void setDateCertificateIssued(String dateCertificateIssued)
	{
		this.dateCertificateIssued = dateCertificateIssued;
	}

	public String getDateCertificateSentToSO()
	{
		return Util.checkForNull(dateCertificateSentToSO);
	}

	public void setDateCertificateSentToSO(String dateCertificateSentToSO)
	{
		this.dateCertificateSentToSO = dateCertificateSentToSO;
	}

	public String getSelectionMade()
	{
		return Util.checkForNull(selectionMade);
	}

	public void setSelectionMade(String selectionMade)
	{
		this.selectionMade = selectionMade;
	}

	public String getActionTaken()
	{
		return Util.checkForNull(actionTaken);
	}

	public void setActionTaken(String actionTaken)
	{
		this.actionTaken = actionTaken;
	}

	public String getDateHiringDecisionRecievedInHR()
	{
		return Util.checkForNull(dateHiringDecisionRecievedInHR);
	}

	public void setDateHiringDecisionRecievedInHR(String dateHiringDecisionRecievedInHR)
	{
		this.dateHiringDecisionRecievedInHR = dateHiringDecisionRecievedInHR;
	}

	public String getDateFinalApplcantStatusesSet()
	{
		return Util.checkForNull(dateFinalApplcantStatusesSet);
	}

	public void setDateFinalApplcantStatusesSet(String dateFinalApplcantStatusesSet)
	{
		this.dateFinalApplcantStatusesSet = dateFinalApplcantStatusesSet;
	}

	public String getDateAuditCompleted()
	{
		return Util.checkForNull(dateAuditCompleted);
	}

	public void setDateAuditCompleted(String dateAuditCompleted)
	{
		this.dateAuditCompleted = dateAuditCompleted;
	}

	@Override
	public String toString()
	{
		return "RCertificate [announcementNumber: " + getAnnouncementNumber() + "| certificateType: " + getCertificateType() 
		+ "| certificateNumber: " + getCertificateNumber() + "| positionTitle: " + getPositionTitle() + "| series: " + getSeries() 
		+ "| gradeList: " + getGrade() + "| dutyLocationList: " + getDutyLocation() + "| dateCertificateIssued: " + getDateCertificateIssued() 
		+ "| dateCertificateSentToSO: " + getDateCertificateSentToSO() + "| selectionMade: " + getSelectionMade() + "| actionTaken: " + getActionTaken()
		+ "| dateHiringDecisionRecievedInHR: " + getDateHiringDecisionRecievedInHR() + "| dateFinalApplcantStatusesSet: " + getDateFinalApplcantStatusesSet() 
		+ "| dateAuditCompleted: " + getDateAuditCompleted() + "]";
	}
}
