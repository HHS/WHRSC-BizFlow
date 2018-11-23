package gov.hhs.usas.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.springframework.data.annotation.Transient;

@Entity(name="VW_R_CERT_RESULT")
@Immutable
public class RCertificate
{
	
	@Id
	@Column(name = "REQ_VAC")
	private String recVacID; 
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
	@Transient
	private transient List<String> seriesList;
	@Column(name="SERIES")
	private String series;
	@Transient
	private transient List<String> gradeList;
	@Transient
	private transient List<String> dutyLocationList;
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
		this.seriesList = new ArrayList<String>();
		this.series = "";
		this.gradeList = new ArrayList<String>();
		this.dutyLocationList = new ArrayList<String>();
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
		this.seriesList = seriesList;
		this.series = series;
		this.gradeList = gradeList;
		this.dutyLocationList = dutyLocationList;
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

	public String getVacancyNumber() {
		return vacancyNumber;
	}

	public void setVacancyNumber(String vacancyNumber) {
		this.vacancyNumber = vacancyNumber;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setSeries(String series) {
		this.series = series;
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
		if(this.seriesList.size() > 0){
			StringBuffer seriesStr = new StringBuffer();
			for (int i = 0; i < this.seriesList.size(); i++) {
				if (i > 0) {
					seriesStr.append("," + (String)this.seriesList.get(i));
				} else {
					seriesStr.append((String)this.seriesList.get(i));
				}
			}
			this.series = seriesStr.toString();
		}
		return this.series;
	}

	public void setSeries(List<String> series)
	{
		this.seriesList = series;
		this.series = getSeries();
	}

	public void addSeries(String series)
	{
		if ((!this.seriesList.contains(series)) && (series.trim().length() > 0)) {
			this.seriesList.add(series);
		}
		this.series = getSeries();
	}


	public String getGrades()
	{
		if(this.gradeList.size() > 0){
			StringBuffer gradesStr = new StringBuffer();
			for (int i = 0; i < this.gradeList.size(); i++) {
				if (i > 0) {
					gradesStr.append("," + (String)this.gradeList.get(i));
				} else {
					gradesStr.append((String)this.gradeList.get(i));
				}
			}
			this.grade = gradesStr.toString();
		}
		return this.grade;
	}

	public void setGrades(List<String> grades)
	{
		this.gradeList = grades;
		this.grade = getGrades();
	}

	public void addGrade(String grade)
	{
		if ((!this.gradeList.contains(grade)) && (grade.trim().length() > 0)) {
			this.gradeList.add(grade);
		}
		this.grade = getGrades();
	}


	public String getDutyLocation()
	{
		if(this.dutyLocationList.size() > 0){
			StringBuffer dutyLocationStr = new StringBuffer();
			for (int i = 0; i < this.dutyLocationList.size(); i++) {
				if (i > 0) {
					dutyLocationStr.append(";" + (String)this.dutyLocationList.get(i));
				} else {
					dutyLocationStr.append((String)this.dutyLocationList.get(i));
				}
			}
			this.dutyLocation = dutyLocationStr.toString();
		}
		return this.dutyLocation;
	}

	public void setDutyLocation(List<String> dutyLocation)
	{
		this.dutyLocationList = dutyLocation;
		this.dutyLocation = getDutyLocation();
	}

	public void addDutyLocation(String dutyLocation)
	{
		if ((!this.dutyLocationList.contains(dutyLocation)) && (dutyLocation.trim().length() > 0)) {
			this.dutyLocationList.add(dutyLocation);
		}
		this.dutyLocation = getDutyLocation();
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
		return "RCertificate [announcementNumber: " + getAnnouncementNumber() + "| certificateType: " + getCertificateType() 
		+ "| certificateNumber: " + getCertificateNumber() + "| positionTitle: " + getPositionTitle() + "| series: " + getSeries() 
		+ "| gradeList: " + getGrades() + "| dutyLocationList: " + getDutyLocation() + "| dateCertificateIssued: " + getDateCertificateIssued() 
		+ "| dateCertificateSentToSO: " + getDateCertificateSentToSO() + "| selectionMade: " + getSelectionMade() + "| actionTaken: " + getActionTaken()
		+ "| dateHiringDecisionRecievedInHR: " + getDateHiringDecisionRecievedInHR() + "| dateFinalApplcantStatusesSet: " + getDateFinalApplcantStatusesSet() 
		+ "| dateAuditCompleted: " + getDateAuditCompleted() + "]";
	}
}
