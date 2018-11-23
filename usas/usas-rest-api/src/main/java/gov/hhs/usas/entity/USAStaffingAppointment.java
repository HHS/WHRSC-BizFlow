package gov.hhs.usas.entity;

import java.util.ArrayList;
import java.util.List;

public class USAStaffingAppointment {

	private String requestNumber;
	private int vacancyCount;
	private List<AVacancyAnnouncement> vacancyAnnouncementList;
	private String resultCode;
	private String failureMessage;
	
	public USAStaffingAppointment() {
		this.requestNumber = "";
		this.vacancyCount = 0;
		this.vacancyAnnouncementList = new ArrayList<AVacancyAnnouncement>();
		this.resultCode = "";
		this.failureMessage = "";
	}
	
	public USAStaffingAppointment(String requestNumber, int vacancyCount,
			List<AVacancyAnnouncement> vacancyAnnouncementList) {
		this.requestNumber = requestNumber;
		this.vacancyCount = vacancyCount;
		this.vacancyAnnouncementList = vacancyAnnouncementList;
		this.resultCode = "Success";
		this.failureMessage = "";
	}
	

	public USAStaffingAppointment(String requestNumber, int vacancyCount,
			List<AVacancyAnnouncement> vacancyAnnouncementList, String resultCode, String failureMessage) {
		this.requestNumber = requestNumber;
		this.vacancyCount = vacancyCount;
		this.vacancyAnnouncementList = vacancyAnnouncementList;
		this.resultCode = resultCode;
		this.failureMessage = failureMessage;
	}
	
	public USAStaffingAppointment(String resultCode, String failureMessage) {
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

	public List<AVacancyAnnouncement> getVacancyAnnouncementList() {
		return vacancyAnnouncementList;
	}

	public void setVacancyAnnouncementList(List<AVacancyAnnouncement> vacancyAnnouncementList) {
		this.vacancyAnnouncementList = vacancyAnnouncementList;
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
		return "USAStaffingAppointment [requestNumber: " + this.getRequestNumber() + "| vacancyCount: " + this.getVacancyCount()
		+ "| vacancyAnnouncementList: " + this.getVacancyAnnouncementList()
		+ "| resultCode: " + this.getResultCode() + "| failureMessage: " + this.getFailureMessage() + "]";
	}
	
	
}
