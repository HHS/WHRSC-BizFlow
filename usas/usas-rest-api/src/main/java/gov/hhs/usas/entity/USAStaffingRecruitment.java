package gov.hhs.usas.entity;

import java.util.ArrayList;
import java.util.List;

public class USAStaffingRecruitment
{
	private String requestNumber;
	private int vacancyCount;
	private int positionCount;
	private List<RVacancyAnnouncement> vacancyAnnouncementList;
	private RPositions positions;
	private String resultCode;
	private String failureMessage;

	public USAStaffingRecruitment()
	{
		this.requestNumber = "";
		this.vacancyCount = 0;
		this.positionCount = 0;
		this.vacancyAnnouncementList = new ArrayList<RVacancyAnnouncement>();
		this.positions = new RPositions();
		this.resultCode = "";
		this.failureMessage = "";
	}

	public USAStaffingRecruitment(String requestNumber, int vacancyCount, int positionCount, List<RVacancyAnnouncement> vacancyAnnouncementList, RPositions positions)
	{
		this.requestNumber = requestNumber;
		this.vacancyCount = vacancyCount;
		this.positionCount = positionCount;
		this.vacancyAnnouncementList = vacancyAnnouncementList;
		this.positions = positions;
		this.resultCode = "Success";
		this.failureMessage = "";
	}

	public USAStaffingRecruitment(String requestNumber, int vacancyCount, int positionCount,
			List<RVacancyAnnouncement> vacancyAnnouncementList, RPositions positions, String resultCode,
			String failureMessage) {
		this.requestNumber = requestNumber;
		this.vacancyCount = vacancyCount;
		this.positionCount = positionCount;
		this.vacancyAnnouncementList = vacancyAnnouncementList;
		this.positions = positions;
		this.resultCode = resultCode;
		this.failureMessage = failureMessage;
	}

	public USAStaffingRecruitment(String resultCode, String failureMessage) {
		this();
		this.resultCode = resultCode;
		this.failureMessage = failureMessage;
	}

	public String getRequestNumber()
	{
		return this.requestNumber;
	}

	public void setRequestNumber(String requestNumber)
	{
		this.requestNumber = requestNumber;
	}

	public int getVacancyCount()
	{
		return this.vacancyCount;
	}

	public void setVacancyCount(int vacancyCount)
	{
		this.vacancyCount = vacancyCount;
	}

	public int getPositionCount()
	{
		return this.positionCount;
	}

	public void setPositionCount(int positionCount)
	{
		this.positionCount = positionCount;
	}

	public List<RVacancyAnnouncement> getVacancyAnnouncementList()
	{
		return this.vacancyAnnouncementList;
	}

	public void setVacancyAnnouncementList(List<RVacancyAnnouncement> vacancyAnnouncementList)
	{
		this.vacancyAnnouncementList = vacancyAnnouncementList;
		setVacancyCount(vacancyAnnouncementList.size());
	}

	public RPositions getPositions()
	{
		return this.positions;
	}

	public void setPositions(RPositions positions)
	{
		this.positions = positions;
		setPositionCount(positions.getPositionList().size());
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
	public String toString()
	{
		return "USAStaffingRecruitment [requestNumber: " + getRequestNumber() + "| vacancyCount: " + getVacancyCount() + "| positionCount: " + getPositionCount() 
		+ "| vacancyAnnouncementList: " + getVacancyAnnouncementList() + "| positions: " + getPositions()
		+ "| resultCode: " + getResultCode() + "| failureMessage: " + getFailureMessage() + "]";
	}
}

