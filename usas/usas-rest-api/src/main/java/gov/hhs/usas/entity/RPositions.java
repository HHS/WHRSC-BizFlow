package gov.hhs.usas.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import gov.hhs.usas.Util;

@Entity(name="VW_R_POS_RESULT")
public class RPositions
{
	@Id
	@Column(name = "REQUEST_NUMBER")
	private String requestNumber;
	private transient int positionCount;
	@Column(name = "CLEARANCE_LEVEL_REQUIRED")
	private String clearanceLevelRequiredForPosition;
	private transient List<RRequestPosition> positionList;

	public RPositions()
	{
		this.positionCount = 0;
		this.clearanceLevelRequiredForPosition = "";
		this.positionList = new ArrayList<RRequestPosition>();
	}

	public RPositions(int positionCount, String clearanceLevelRequiredForPosition, List<RRequestPosition> positionList)
	{
		this.positionCount = positionCount;
		this.clearanceLevelRequiredForPosition = clearanceLevelRequiredForPosition;
		this.positionList = positionList;
	}

	public String getRequestNumber() {
		return Util.checkForNull(requestNumber);
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public int getPositionCount()
	{
		return positionCount;
	}

	public void setPositionCount(int positionCount)
	{
		this.positionCount = positionCount;
	}

	public String getClearanceLevelRequiredForPosition()
	{
		return Util.checkForNull(clearanceLevelRequiredForPosition);
	}

	public void setClearanceLevelRequiredForPosition(String clearanceLevelRequiredForPosition)
	{
		this.clearanceLevelRequiredForPosition = clearanceLevelRequiredForPosition;
	}

	public List<RRequestPosition> getPositionList()
	{
		return this.positionList;
	}

	public void setPositionList(List<RRequestPosition> positionList)
	{
		this.positionList = positionList;
	}


	@Override
	public String toString()
	{
		return "RPositions [positionCount: " + getPositionCount() + "| clearanceLevelRequiredForPosition: " + getClearanceLevelRequiredForPosition() + "| positions: " + getPositionList() + "]";
	}
}

