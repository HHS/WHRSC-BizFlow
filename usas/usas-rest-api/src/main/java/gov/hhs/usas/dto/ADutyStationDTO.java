package gov.hhs.usas.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="DutyStation", propOrder={})
public class ADutyStationDTO {
	
	@XmlTransient
	private String reqVacCertDLID;
	@XmlTransient
	private String requestNumber;
	@XmlTransient
	private String vacancyIdentificationNumber;
	@XmlTransient
	private String certificateNumber;
	@XmlElement(name="Name")
	private String dutyStationName;
	@XmlElement(name="Code")
	private String dutyStationCode;
	
	public ADutyStationDTO() {
		this.reqVacCertDLID = "";
		this.requestNumber = "";
		this.vacancyIdentificationNumber = "";
		this.certificateNumber = "";
		this.dutyStationName = "";
		this.dutyStationCode = "";
	}

	public ADutyStationDTO(String reqVacCertDLID, String requestNumber, String vacancyIdentificationNumber,
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
		return reqVacCertDLID;
	}
	public void setReqVacCertDLID(String reqVacCertDLID) {
		this.reqVacCertDLID = reqVacCertDLID;
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
	public String getDutyStationName() {
		return dutyStationName;
	}
	public void setDutyStationName(String dutyStationName) {
		this.dutyStationName = dutyStationName;
	}
	public String getDutyStationCode() {
		return dutyStationCode;
	}
	public void setDutyStationCode(String dutyStationCode) {
		this.dutyStationCode = dutyStationCode;
	}
	
	@Override
	public String toString(){
		return "ADutyStationDTO [dutyStationName: " + this.getDutyStationName() 
				+ "| dutyStationCode: " + this.getDutyStationCode() + "]";
	}

}
