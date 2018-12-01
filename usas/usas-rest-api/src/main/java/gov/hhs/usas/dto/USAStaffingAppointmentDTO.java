package gov.hhs.usas.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="USAStaffing_Appointment")
public class USAStaffingAppointmentDTO {

	@XmlAttribute(name="RequestNumber")
	@XmlID
	private String requestNumber;
	@XmlAttribute(name="VacancyCount")
	private int vacancyCount;
	@XmlElement(name="Vacancy")
	private List<AVacancyAnnouncementDTO> vacancyAnnouncementList;
	@XmlElement(name="Result_Code")
	private String resultCode;
	@XmlElement(name="Failure_Message")
	private String failureMessage;
	
	public USAStaffingAppointmentDTO() {
		this.requestNumber = "";
		this.vacancyCount = 0;
		this.vacancyAnnouncementList = new ArrayList<AVacancyAnnouncementDTO>();
		this.resultCode = "";
		this.failureMessage = "";
	}
	
	public USAStaffingAppointmentDTO(String requestNumber)
	{
		this.requestNumber = requestNumber;
		this.vacancyCount = 0;
		this.vacancyAnnouncementList = new ArrayList<AVacancyAnnouncementDTO>();
		this.resultCode = "";
		this.failureMessage = "";
	}
	
	public USAStaffingAppointmentDTO(String requestNumber, int vacancyCount,
			List<AVacancyAnnouncementDTO> vacancyAnnouncementList) {
		this.requestNumber = requestNumber;
		this.vacancyCount = vacancyCount;
		this.vacancyAnnouncementList = vacancyAnnouncementList;
		this.resultCode = "Success";
		this.failureMessage = "";
	}
	

	public USAStaffingAppointmentDTO(String requestNumber, int vacancyCount,
			List<AVacancyAnnouncementDTO> vacancyAnnouncementList, String resultCode, String failureMessage) {
		this.requestNumber = requestNumber;
		this.vacancyCount = vacancyCount;
		this.vacancyAnnouncementList = vacancyAnnouncementList;
		this.resultCode = resultCode;
		this.failureMessage = failureMessage;
	}
	
	public USAStaffingAppointmentDTO(String resultCode, String failureMessage) {
		this();
		this.resultCode = resultCode;
		this.failureMessage = failureMessage;
	}

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public int getVacancyCount() {
		return vacancyCount;
	}

	public void setVacancyCount(int vacancyCount) {
		this.vacancyCount = vacancyCount;
	}

	public List<AVacancyAnnouncementDTO> getVacancyAnnouncementList() {
		return vacancyAnnouncementList;
	}

	public void setVacancyAnnouncementList(List<AVacancyAnnouncementDTO> vacancyAnnouncementList) {
		this.vacancyAnnouncementList = vacancyAnnouncementList;
		this.setVacancyCount(this.vacancyAnnouncementList.size());
	}
	
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getFailureMessage() {
		return failureMessage;
	}

	public void setFailureMessage(String failureMessage) {
		this.failureMessage = failureMessage;
	}

	@Override
	public String toString(){
		return "USAStaffingAppointmentDTO [requestNumber: " + this.getRequestNumber() + "| vacancyCount: " + this.getVacancyCount()
		+ "| vacancyAnnouncementList: " + this.getVacancyAnnouncementList()
		+ "| resultCode: " + this.getResultCode() + "| failureMessage: " + this.getFailureMessage() + "]";
	}
	
	
}
