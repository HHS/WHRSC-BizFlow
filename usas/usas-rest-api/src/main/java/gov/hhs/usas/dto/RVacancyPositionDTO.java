package gov.hhs.usas.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="VacancyPosition", propOrder={})
@XmlRootElement(name="VacancyPosition")
public class RVacancyPositionDTO
{	
	@XmlTransient
	private String reqVacID;
	@XmlTransient
	private String requestNumber;
	@XmlTransient
	private String vacancyNumber;
	@XmlElement(name="Position_Title")
	private String title;
	@XmlElement(name="Pay_Plan")
	private String payPlan;
	@XmlElement(name="Series")
	private String series;
	@XmlElement(name="Grade")
	private String grades;
	@XmlElement(name="Full_Performance_Level")
	private String fullPerformanceLevel;
	@XmlElement(name="Duty_Location")
	private String dutyLocations;

	public RVacancyPositionDTO()
	{
		this.title = "";
		this.payPlan = "";
		this.series = "";
		this.grades = "";
		this.fullPerformanceLevel = "";
		this.dutyLocations = "";
	}

	public RVacancyPositionDTO(String reqVacID, String requestNumber, String vacancyNumber, String title, String payPlan,
			String series, String grades, String fullPerformanceLevel, String dutyLocations) {
		super();
		this.reqVacID = reqVacID;
		this.requestNumber = requestNumber;
		this.vacancyNumber = vacancyNumber;
		this.title = title;
		this.payPlan = payPlan;
		this.series = series;
		this.grades = grades;
		this.fullPerformanceLevel = fullPerformanceLevel;
		this.dutyLocations = dutyLocations;
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

	public String getDutyLocations()
	{
		return this.dutyLocations;
	}

	public void setDutyLocations(String dutyLocations)
	{
		this.dutyLocations = dutyLocations;
	}

	public void setGrades(String grades)
	{
		this.grades = grades;
	}

	public String getFullPerformanceLevel()
	{
		return this.fullPerformanceLevel;
	}

	public void setFullPerformanceLevel(String fullPerformanceLevel)
	{
		this.fullPerformanceLevel = fullPerformanceLevel;
	}


	@Override
	public String toString()
	{
		return "RVacancyPositionDTO [positionTitle: " + getTitle() + "| payPlan: " + getPayPlan() + "| series: " + getSeries() 
				+ "| grade: " + getGrades() + "| fullPerformanceLevel: " + getFullPerformanceLevel() + "| dutyLocation: " + getDutyLocations() +"]";
	}
}

