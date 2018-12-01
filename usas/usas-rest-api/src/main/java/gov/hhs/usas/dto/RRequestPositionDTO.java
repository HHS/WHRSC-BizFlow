package gov.hhs.usas.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="Position", propOrder={})
@XmlRootElement(name="Position")
public class RRequestPositionDTO {

	@XmlTransient
	private String requestNumber;
	@XmlElement(name="Position_Title")
	private String title;
	@XmlElement(name="Duty_Location")
	private String dutyLocations;
	@XmlElement(name="Pay_Plan")
	private String payPlan;
	@XmlElement(name="Series")
	private String series;
	@XmlElement(name="Grade")
	private String grades;
	
	public RRequestPositionDTO() {
		this.requestNumber = "";
		this.title = "";
		this.dutyLocations = "";
		this.payPlan = "";
		this.series = "";
		this.grades = "";
	}

	public RRequestPositionDTO(String requestNumber, String title, String dutyLocations, String payPlan, String series,
			String grades) {
		super();
		this.requestNumber = requestNumber;
		this.title = title;
		this.dutyLocations = dutyLocations;
		this.payPlan = payPlan;
		this.series = series;
		this.grades = grades;
	}
	public String getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDutyLocations() {
		return dutyLocations;
	}
	public void setDutyLocations(String dutyLocations) {
		this.dutyLocations = dutyLocations;
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
	public String getGrades() {
		return grades;
	}
	public void setGrades(String grades) {
		this.grades = grades;
	}



}
