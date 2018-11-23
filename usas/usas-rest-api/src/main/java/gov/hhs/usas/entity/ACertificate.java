package gov.hhs.usas.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.springframework.data.annotation.Transient;

@Entity(name="VW_A_CERT_RESULT_TWO")
@Immutable
public class ACertificate {
	
	@Id
	@Column(name = "REQ_VAC_CERT")
	private String reqVacCertID;
	@Column(name = "REQUEST_NUMBER")
	private String requestNumber;
	@Column(name = "VACANCY_NUMBER")
	private String vacancyIdentificationNumber;
	@Column(name = "CERTIFICATE_NUMBER")
	private String certificateNumber;
	@Column(name = "CERTIFICATE_TYPE")
	private String certificateType;
	@Column(name = "POSITION_TITLE")
	private String positionTitle;
	@Column(name = "PAY_PLAN")
	private String payPlan;
	@Column(name = "SERIES")
	private String series;
	@Column(name = "GRADE")
	private String grade;
	@Column(name = "FULL_PERFORMANCE_LEVEL")
	private String fullPerformanceLevel;
	@Transient
	private transient List<ADutyStation> dutyStationList;
	@Column(name = "DATE_CERTIFICATE_SENT_TO_SO")
	private String dateCertificateSentToSO;
	@Column(name = "DATE_HIRING_DECISN_RECD_IN_HR")
	private String dateHiringDecisionReceivedInHR;
	@Column(name = "DATE_OF_TENTATV_OFFER")
	private String dateOfTentativeJobOffer;
	@Column(name = "DATE_OF_OFFCL_OFFER")
	private String dateOfOfficialJobOffer;
	@Column(name = "DATE_OFFCL_OFFER_SENT")
	private String dateOfficialOfferSent;
	
	public ACertificate() {
		this.reqVacCertID = "";
		this.requestNumber = "";
		this.vacancyIdentificationNumber = "";
		this.certificateNumber = "";
		this.certificateType = "";
		this.positionTitle = "";
		this.payPlan = "";
		this.series = "";
		this.grade = "";
		this.fullPerformanceLevel = "";
		this.dutyStationList = new ArrayList<ADutyStation>();
		this.dateCertificateSentToSO = "";
		this.dateHiringDecisionReceivedInHR = "";
		this.dateOfTentativeJobOffer = "";
		this.dateOfOfficialJobOffer = "";
		this.dateOfficialOfferSent = "";
	}





	public ACertificate(String reqVacID, String requestNumber, String vacancyIdentificationNumber,
			String certificateNumber, String certificateType, String positionTitle, String payPlan, String series,
			String grade, String fullPerformanceLevel, List<ADutyStation> dutyStationList,
			String dateCertificateSentToSO, String dateHiringDecisionReceivedInHR, String dateOfTentativeJobOffer,
			String dateOfOfficialJobOffer, String dateOfficialOfferSent) {
		super();
		this.reqVacCertID = reqVacID;
		this.requestNumber = requestNumber;
		this.vacancyIdentificationNumber = vacancyIdentificationNumber;
		this.certificateNumber = certificateNumber;
		this.certificateType = certificateType;
		this.positionTitle = positionTitle;
		this.payPlan = payPlan;
		this.series = series;
		this.grade = grade;
		this.fullPerformanceLevel = fullPerformanceLevel;
		this.dutyStationList = dutyStationList;
		this.dateCertificateSentToSO = dateCertificateSentToSO;
		this.dateHiringDecisionReceivedInHR = dateHiringDecisionReceivedInHR;
		this.dateOfTentativeJobOffer = dateOfTentativeJobOffer;
		this.dateOfOfficialJobOffer = dateOfOfficialJobOffer;
		this.dateOfficialOfferSent = dateOfficialOfferSent;
	}





	public String getReqVacCertID() {
		return reqVacCertID;
	}





	public void setReqVacCertID(String reqVacCertID) {
		this.reqVacCertID = reqVacCertID;
	}





	public String getRequestNumber() {
		return requestNumber;
	}





	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}





	public String getVacancyIdentificationNumber() {
		return vacancyIdentificationNumber;
	}





	public void setVacancyIdentificationNumber(String vacancyIdentificationNumber) {
		this.vacancyIdentificationNumber = vacancyIdentificationNumber;
	}





	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getPositionTitle() {
		return positionTitle;
	}

	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}

	public String getPayPlan() {
		return payPlan;
	}

	public void setPayPlan(String payPlan) {
		this.payPlan = payPlan;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getFullPerformanceLevel() {
		return fullPerformanceLevel;
	}

	public void setFullPerformanceLevel(String fullPerformanceLevel) {
		this.fullPerformanceLevel = fullPerformanceLevel;
	}

	public List<ADutyStation> getDutyStationList() {
		return dutyStationList;
	}

	public void setDutyStationList(List<ADutyStation> dutyStationList) {
		this.dutyStationList = dutyStationList;
	}

	public String getDateCertificateSentToSO() {
		return dateCertificateSentToSO;
	}

	public void setDateCertificateSentToSO(String dateCertificateSentToSO) {
		this.dateCertificateSentToSO = dateCertificateSentToSO;
	}

	public String getDateHiringDecisionReceivedInHR() {
		return dateHiringDecisionReceivedInHR;
	}

	public void setDateHiringDecisionReceivedInHR(String dateHiringDecisionReceivedInHR) {
		this.dateHiringDecisionReceivedInHR = dateHiringDecisionReceivedInHR;
	}

	public String getDateOfTentativeJobOffer() {
		return dateOfTentativeJobOffer;
	}

	public void setDateOfTentativeJobOffer(String dateOfTentativeJobOffer) {
		this.dateOfTentativeJobOffer = dateOfTentativeJobOffer;
	}

	public String getDateOfOfficialJobOffer() {
		return dateOfOfficialJobOffer;
	}

	public void setDateOfOfficialJobOffer(String dateOfOfficialJobOffer) {
		this.dateOfOfficialJobOffer = dateOfOfficialJobOffer;
	}

	public String getDateOfficialOfferSent() {
		return dateOfficialOfferSent;
	}

	public void setDateOfficialOfferSent(String dateOfficialOfferSent) {
		this.dateOfficialOfferSent = dateOfficialOfferSent;
	}
	
	@Override
	public String toString(){
		return "ACertificate [certificateNumber: " + this.getCertificateNumber() + "| certificateType: " + this.getCertificateType() + "| positionTitle: " + this.getPositionTitle()
				+ "| payPlan: " + this.getPayPlan() + "| series: " + this.getSeries() + "| grade: " + this.getGrade() + "| fullPerformanceLevel: " + this.getFullPerformanceLevel()
				+ "| dutyStations: " + this.getDutyStationList()
				+ "| dateCertificateSentToSO: " + this.getDateCertificateSentToSO() + "| dateHiringDecisionReceivedInHR: " + this.getDateHiringDecisionReceivedInHR()
				+ "| dateOfTentativeJobOffer: " + this.getDateOfTentativeJobOffer() + "| dateOfOfficialJobOffer: " + this.getDateOfOfficialJobOffer()
				+ "| dateOfficialOfferSent: " + this.getDateOfficialOfferSent() + "]";
	}
	

}
