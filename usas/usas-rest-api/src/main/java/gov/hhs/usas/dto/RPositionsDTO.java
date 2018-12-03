package gov.hhs.usas.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class RPositionsDTO
{
	@XmlTransient
	private String requestNumber;
	@XmlAttribute(name="PositionCount")
	private int positionCount;
	@XmlElement(name="Clearance_Level_Required_For_Position")
	private String clearanceLevelRequiredForPosition;
	@XmlElement(name="record")
	private List<RRequestPositionDTO> requestPositionList;

	public RPositionsDTO()
	{
		this.positionCount = 0;
		this.clearanceLevelRequiredForPosition = "";
		this.requestPositionList = new ArrayList<RRequestPositionDTO>();
	}



/*	public RPositionsDTO(String reqVacID, String requestNumber, String vacancyNumber, int positionCount,
			String numberOfPositionsToBeAdvertised, String clearanceLevelRequiredForPosition,
			List<RRequestPositionDTO> requestPositionList) {
		super();
		this.reqVacID = reqVacID;
		this.requestNumber = requestNumber;
		this.vacancyNumber = vacancyNumber;
		this.positionCount = positionCount;
		this.numberOfPositionsToBeAdvertised = numberOfPositionsToBeAdvertised;
		this.clearanceLevelRequiredForPosition = clearanceLevelRequiredForPosition;
		this.positionList = requestPositionList;
	}*/
	
	public RPositionsDTO(String requestNumber, int positionCount, String clearanceLevelRequiredForPosition,
			List<RRequestPositionDTO> positionList) {
		this.requestNumber = requestNumber;
		this.positionCount = positionList.size();
		this.clearanceLevelRequiredForPosition = clearanceLevelRequiredForPosition;
		this.requestPositionList = positionList;
	}

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public int getPositionCount()
	{
		return this.positionCount;
	}

	public void setPositionCount(int positionCount)
	{
		this.positionCount = positionCount;
	}

	public String getClearanceLevelRequiredForPosition()
	{
		return this.clearanceLevelRequiredForPosition;
	}

	public void setClearanceLevelRequiredForPosition(String clearanceLevelRequiredForPosition)
	{
		this.clearanceLevelRequiredForPosition = clearanceLevelRequiredForPosition;
	}

	public List<RRequestPositionDTO> getPositionList()
	{
		return this.requestPositionList;
	}

	public void setPositionList(List<RRequestPositionDTO> positionList)
	{
		this.requestPositionList = positionList;
	}

	public void addPosition(RRequestPositionDTO newPosition)
	{
		if (!this.requestPositionList.contains(newPosition))
		{
			this.requestPositionList.add(newPosition);
			this.positionCount += 1;
		}
	}

	@Override
	public String toString()
	{
		return "RPositionsDTO [positionCount: " + getPositionCount() + "| clearanceLevelRequiredForPosition: " + getClearanceLevelRequiredForPosition() + "| positions: " + getPositionList() + "]";
	}
}

