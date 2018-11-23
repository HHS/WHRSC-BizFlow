package gov.hhs.usas.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;
import org.springframework.data.annotation.Transient;

@Entity(name="VW_R_VAC_POS_RESULT")
@Immutable
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
	@Transient
	private transient List<String> payPlanList;
	@Column(name = "PAY_PLAN")
	private String payPlan;
	@Transient
	private transient List<String> seriesList;
	@Column(name = "SERIES")
	private String series;
	@Transient
	private transient List<Integer> gradeList;//Type Integer to enable sorting
	@Column(name = "GRADE")
	private String grades;
	@Column(name = "FULL_PERFORMANCE_LEVEL")
	private String fullPerformanceLevel;
	@Transient
	private transient List<String> dutyLocationList;
	@Column(name = "DUTY_LOCATION")
	private String dutyLocations;

	public RVacancyPosition()
	{
		this.title = "";
		this.payPlanList = new ArrayList<String>();
		this.payPlan = "";
		this.seriesList = new ArrayList<String>();
		this.series = "";
		this.gradeList = new ArrayList<Integer>();
		this.grades = "";
		this.fullPerformanceLevel = "";
		this.dutyLocationList = new ArrayList<String>();
		this.dutyLocations = "";
	}


	public RVacancyPosition(String title, List<String> payPlanList, String payPlan, List<String> seriesList, String series,
			List<Integer> gradeList, String grades, String fullPerformanceLevel, List<String> dutyLocationList,
			String dutyLocations) {
		this.title = title;
		this.payPlanList = payPlanList;
		this.payPlan = payPlan;
		this.seriesList = seriesList;
		this.series = series;
		this.gradeList = gradeList;
		this.grades = grades;
		this.fullPerformanceLevel = fullPerformanceLevel;
		this.dutyLocationList = dutyLocationList;
		this.dutyLocations = dutyLocations;
	}



	public String getReqVacID() {
		return reqVacID;
	}


	public void setReqVacID(String reqVacID) {
		this.reqVacID = reqVacID;
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


	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getPayPlan()
	{
		return this.payPlan;
	}

	public void setPayPlan(String payPlan)
	{
		this.payPlan = payPlan;
	}

	public String getSeries()
	{
		return this.series;
	}

	public void setSeries(String series)
	{
		this.series = series;
	}

	public String getGrades()
	{
		return this.grades;
	}

	public void setGrades(String grades)
	{
		this.grades = grades;
	}

	public String getDutyLocations()
	{
		String dutyLocationStr = this.dutyLocations.replace(";", "; ");
		//return this.dutyLocations;
		return dutyLocationStr;
	}

	public void setDutyLocations(String dutyLocations)
	{
		this.dutyLocations = dutyLocations;
	}


	public String getFullPerformanceLevel()
	{
		return this.fullPerformanceLevel;
	}

	public void setFullPerformanceLevel(String fullPerformanceLevel)
	{
		this.fullPerformanceLevel = fullPerformanceLevel;
	}

	public List<String> getDutyLocationList()
	{
		return this.dutyLocationList;
	}

	public String getDutyLocation()
	{
		return this.dutyLocations;
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

