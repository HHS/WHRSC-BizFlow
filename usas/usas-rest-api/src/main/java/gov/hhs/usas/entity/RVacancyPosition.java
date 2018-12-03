package gov.hhs.usas.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import gov.hhs.usas.Util;

@Entity(name="VW_R_VAC_POS_RESULT")
public class RVacancyPosition
{	
	@Id
	@Column(name = "REQ_VAC")
	private String reqVacID;
	@Column(name = "REQUEST_NUMBER")
	private String requestNumber;
	@Column(name = "VACANCY_NUMBER")
	private String vacancyNumber;
	@Column(name = "POSITION_TITLE")
	private String title;
	@Column(name = "PAY_PLAN")
	private String payPlan;
	@Column(name = "SERIES")
	private String series;
	@Column(name = "GRADE")
	private String grades;
	@Column(name = "FULL_PERFORMANCE_LEVEL")
	private String fullPerformanceLevel;
	@Column(name = "DUTY_LOCATION")
	private String dutyLocations;

	public RVacancyPosition()
	{
		this.title = "";
		this.payPlan = "";
		this.series = "";
		this.grades = "";
		this.fullPerformanceLevel = "";
		this.dutyLocations = "";
	}


	public RVacancyPosition(String title, List<String> payPlanList, String payPlan, List<String> seriesList, String series,
			List<Integer> gradeList, String grades, String fullPerformanceLevel, List<String> dutyLocationList,
			String dutyLocations) {
		this.title = title;
		this.payPlan = payPlan;
		this.series = series;
		this.grades = grades;
		this.fullPerformanceLevel = fullPerformanceLevel;
		this.dutyLocations = dutyLocations;
	}



	public String getReqVacID() {
		return Util.checkForNull(reqVacID);
	}


	public void setReqVacID(String reqVacID) {
		this.reqVacID = reqVacID;
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


	public String getTitle()
	{
		return Util.checkForNull(title);
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getPayPlan()
	{
		return Util.checkForNull(payPlan);
	}

	public void setPayPlan(String payPlan)
	{
		this.payPlan = payPlan;
	}

	public String getSeries()
	{
		return Util.checkForNull(series);
	}

	public void setSeries(String series)
	{
		this.series = series;
	}

	public String getGrades()
	{
		return Util.checkForNull(grades);
	}

	public void setGrades(String grades)
	{
		this.grades = grades;
	}

	public String getDutyLocations()
	{
		String dutyLocationStr = Util.checkForNull(this.dutyLocations).replace(";", "; ");
		return dutyLocationStr;
	}

	public void setDutyLocations(String dutyLocations)
	{
		this.dutyLocations = dutyLocations;
	}


	public String getFullPerformanceLevel()
	{
		return Util.checkForNull(fullPerformanceLevel);
	}

	public void setFullPerformanceLevel(String fullPerformanceLevel)
	{
		this.fullPerformanceLevel = fullPerformanceLevel;
	}

	public String getDutyLocation()
	{
		return Util.checkForNull(dutyLocations);
	}

	public void setDutyLocation(String dutyLocation)
	{
		this.dutyLocations = dutyLocation;
	}

	@Override
	public String toString()
	{
		return "RVacancyPosition [positionTitle: " + getTitle() + "| payPlan: " + getPayPlan() + "| series: " + getSeries() 
				+ "| grade: " + getGrades() + "| fullPerformanceLevel: " + getFullPerformanceLevel() + "| dutyLocation: " + getDutyLocation() +"]";
	}
}

