package gov.hhs.usas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import gov.hhs.usas.Util;

@Entity(name="VW_R_REQ_POS_RESULT")
public class RRequestPosition {

	@Id
	@Column(name = "REQUEST_NUMBER")
	private String requestNumber;
	@Column(name = "POSITION_DESCRIPTION_TITLE")
	private String title;
	@Column(name = "DUTY_LOCATION")
	private String dutyLocations;
	@Column(name = "DESCRIPTION")
	private String description;
	private transient String payPlan;
	private transient String series;
	private transient String grades;

	public String getRequestNumber() {
		return Util.checkForNull(requestNumber);
	}
	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}
	public String getTitle() {
		return Util.checkForNull(title);
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDutyLocations() {
		String dutyLocationStr = Util.checkForNull(this.dutyLocations).replace(";", "; ");
		return dutyLocationStr;
	}
	public void setDutyLocations(String dutyLocations) {
		this.dutyLocations = dutyLocations;
	}
	public String getDescription() {
		return Util.checkForNull(description);
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPayPlan() {
		if(getDescription().length()>0){
			String[] arr = getDescription().split("-", 3);
			payPlan = arr[0];
		}

		return payPlan;
	}
	public void setPayPlan(String payPlan) {
		this.payPlan = payPlan;
	}
	public String getSeries() {
		if(getDescription().length()>0){
			String[] arr = getDescription().split("-", 3);
			series = arr[1];
		}
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getGrades() {
		if(getDescription().length()>0){
			String[] arr = getDescription().split("-", 3);
			String gradeStr = arr[2];
			grades = gradeStr.replace("/", ",");
		}
		return grades;
	}
	public void setGrades(String grades) {
		this.grades = grades;
	}



}
