package gov.hhs.usas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import gov.hhs.usas.Util;

@Entity(name="VW_A_DUTY_LOC_RESULT")
public class ADutyStation {
	
	@Id
	@Column(name = "REQ_VAC_CERT_DL")
	private String reqVacCertDLID;
	@Column(name = "REQUEST_NUMBER")
	private String requestNumber;
	@Column(name = "VACANCY_NUMBER")
	private String vacancyIdentificationNumber;
	@Column(name = "CERTIFICATE_NUMBER")
	private String certificateNumber;
	@Column(name="DUTY_LOCATION")
	private String dutyStationName;
	@Column(name="DUTY_LOCATION_CODE")
	private String dutyStationCode;
	
	public ADutyStation() {
		this.reqVacCertDLID = "";
		this.requestNumber = "";
		this.vacancyIdentificationNumber = "";
		this.certificateNumber = "";
		this.dutyStationName = "";
		this.dutyStationCode = "";
	}

	public ADutyStation(String reqVacCertDLID, String requestNumber, String vacancyIdentificationNumber,
			String certificateNumber, String dutyStationName, String dutyStationCode) {
		super();
		this.reqVacCertDLID = reqVacCertDLID;
		this.requestNumber = requestNumber;
		this.vacancyIdentificationNumber = vacancyIdentificationNumber;
		this.certificateNumber = certificateNumber;
		this.dutyStationName = dutyStationName;
		this.dutyStationCode = dutyStationCode;
	}

	public String getReqVacCertDLID() {
		return Util.checkForNull(reqVacCertDLID);
	}

	public void setReqVacCertDLID(String reqVacCertDLID) {
		this.reqVacCertDLID = reqVacCertDLID;
	}

	public String getRequestNumber() {
		return Util.checkForNull(requestNumber);
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public String getVacancyIdentificationNumber() {
		return Util.checkForNull(vacancyIdentificationNumber);
	}

	public void setVacancyIdentificationNumber(String vacancyIdentificationNumber) {
		this.vacancyIdentificationNumber = vacancyIdentificationNumber;
	}

	public String getCertificateNumber() {
		return Util.checkForNull(certificateNumber);
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public String getDutyStationName() {
		return Util.checkForNull(dutyStationName);
	}
	public void setDutyStationName(String dutyStationName) {
		this.dutyStationName = dutyStationName;
	}
	public String getDutyStationCode() {
		return Util.checkForNull(dutyStationCode);
	}
	public void setDutyStationCode(String dutyStationCode) {
		this.dutyStationCode = dutyStationCode;
	}
	
	@Override
	public String toString(){
		return "ADutyStation [dutyStationName: " + this.getDutyStationName() 
				+ "| dutyStationCode: " + this.getDutyStationCode() + "]";
	}

}
