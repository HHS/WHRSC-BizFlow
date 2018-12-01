package gov.hhs.usas.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="USAStaffing_Recruitment")
public class USAStaffingRecruitmentDTO
{
	@XmlAttribute(name="RequestNumber")
	@XmlID
	private String requestNumber;
	@XmlAttribute(name="VacancyCount")
	private int vacancyCount;
	@XmlAttribute(name="PositionCount")
	private int positionCount;
	@XmlElementWrapper(name="Vacancies")
	@XmlElement(name="record")
	private List<RVacancyAnnouncementDTO> vacancyAnnouncementList;
	@XmlElement(name="Positions")
	private RPositionsDTO positions;
	@XmlElement(name="Result_Code")
	private String resultCode;
	@XmlElement(name="Failure_Message")
	private String failureMessage;

	public USAStaffingRecruitmentDTO()
	{
		this.requestNumber = "";
		this.vacancyCount = 0;
		this.positionCount = 0;
		this.vacancyAnnouncementList = new ArrayList<RVacancyAnnouncementDTO>();
		this.positions = new RPositionsDTO();
		this.resultCode = "";
		this.failureMessage = "";
	}
	
	public USAStaffingRecruitmentDTO(String requestNumber)
	{
		this.requestNumber = requestNumber;
		this.vacancyCount = 0;
		this.positionCount = 0;
		this.vacancyAnnouncementList = new ArrayList<RVacancyAnnouncementDTO>();
		this.positions = new RPositionsDTO();
		this.resultCode = "";
		this.failureMessage = "";
	}

	public USAStaffingRecruitmentDTO(String requestNumber, int vacancyCount, int positionCount, List<RVacancyAnnouncementDTO> vacancyAnnouncementList, RPositionsDTO positions)
	{
		this.requestNumber = requestNumber;
		this.vacancyCount = vacancyCount;
		this.positionCount = positionCount;
		this.vacancyAnnouncementList = vacancyAnnouncementList;
		this.positions = positions;
		this.resultCode = "Success";
		this.failureMessage = "";
	}

	public USAStaffingRecruitmentDTO(String requestNumber, int vacancyCount, int positionCount,
			List<RVacancyAnnouncementDTO> vacancyAnnouncementList, RPositionsDTO positions, String resultCode,
			String failureMessage) {
		this.requestNumber = requestNumber;
		this.vacancyCount = vacancyCount;
		this.positionCount = positionCount;
		this.vacancyAnnouncementList = vacancyAnnouncementList;
		this.positions = positions;
		this.resultCode = resultCode;
		this.failureMessage = failureMessage;
	}

	public USAStaffingRecruitmentDTO(String resultCode, String failureMessage) {
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

	public List<RVacancyAnnouncementDTO> getVacancyAnnouncementList()
	{
		return this.vacancyAnnouncementList;
	}

	public void setVacancyAnnouncementList(List<RVacancyAnnouncementDTO> vacancyAnnouncementList)
	{
		this.vacancyAnnouncementList = vacancyAnnouncementList;
		setVacancyCount(vacancyAnnouncementList.size());
	}

	public RPositionsDTO getPositions()
	{
		return this.positions;
	}

	/*public void setPositions(RPositionsDTO positions)
	{
		this.positions = positions;
		setPositionCount(positions.getPositionList().size());
	}*/
	public void setPositions(RPositionsDTO positions)
	{
		/*this.positions = new RPositionsDTO(positions.getReqVacID(), positions.getRequestNumber(), positions.getVacancyNumber(), 
				positions.getPositionCount(), positions.getNumberOfPositionsToBeAdvertised(), positions.getClearanceLevelRequiredForPosition(),
				positions.getPositionList());*/
		
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
		return "USAStaffingRecruitmentDTO [requestNumber: " + getRequestNumber() + "| vacancyCount: " + getVacancyCount() + "| positionCount: " + getPositionCount() 
		+ "| vacancyAnnouncementList: " + getVacancyAnnouncementList() + "| positions: " + getPositions()
		+ "| resultCode: " + getResultCode() + "| failureMessage: " + getFailureMessage() + "]";
	}
}

