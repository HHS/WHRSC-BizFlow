package gov.hhs.batch.bits;

import java.util.Date;

public class TableNEOStatusTarget {
	
	private String HHSID;
	private Date DATE_REQUESTED;
	private String SENSITIVITY_LEVEL;
	private String SENSITIVITY_LEVEL_DESC;
	private String SENSITIVITY_LEVEL_FORMAL_DESC;
	private Date CLEARED_FOR_NEO;
	private Date INACTIVE_DATE;
	
	public TableNEOStatusTarget() {
		HHSID = "";
		DATE_REQUESTED = null;
		SENSITIVITY_LEVEL = "";
		SENSITIVITY_LEVEL_DESC = "";
		SENSITIVITY_LEVEL_FORMAL_DESC = "";
		CLEARED_FOR_NEO = null;
		INACTIVE_DATE = null;
	}
	
	public TableNEOStatusTarget(String hHSID, Date dATE_REQUESTED, String sENSITIVITY_LEVEL,
			String sENSITIVITY_LEVEL_DESC, String sENSITIVITY_LEVEL_FORMAL_DESC, Date cLEARED_FOR_NEO,
			Date iNACTIVE_DATE) {
		super();
		HHSID = hHSID;
		DATE_REQUESTED = dATE_REQUESTED;
		SENSITIVITY_LEVEL = sENSITIVITY_LEVEL;
		SENSITIVITY_LEVEL_DESC = sENSITIVITY_LEVEL_DESC;
		SENSITIVITY_LEVEL_FORMAL_DESC = sENSITIVITY_LEVEL_FORMAL_DESC;
		CLEARED_FOR_NEO = cLEARED_FOR_NEO;
		INACTIVE_DATE = iNACTIVE_DATE;
	}



	public String getHHSID() {
		return HHSID;
	}

	public void setHHSID(String hHSID) {
		HHSID = hHSID;
	}

	public Date getDATE_REQUESTED() {
		return DATE_REQUESTED;
	}

	public void setDATE_REQUESTED(Date dATE_REQUESTED) {
		DATE_REQUESTED = dATE_REQUESTED;
	}

	public String getSENSITIVITY_LEVEL() {
		return SENSITIVITY_LEVEL;
	}

	public void setSENSITIVITY_LEVEL(String sENSITIVITY_LEVEL) {
		SENSITIVITY_LEVEL = sENSITIVITY_LEVEL;
	}

	public String getSENSITIVITY_LEVEL_DESC() {
		return SENSITIVITY_LEVEL_DESC;
	}

	public void setSENSITIVITY_LEVEL_DESC(String sENSITIVITY_LEVEL_DESC) {
		SENSITIVITY_LEVEL_DESC = sENSITIVITY_LEVEL_DESC;
	}

	public String getSENSITIVITY_LEVEL_FORMAL_DESC() {
		return SENSITIVITY_LEVEL_FORMAL_DESC;
	}

	public void setSENSITIVITY_LEVEL_FORMAL_DESC(String sENSITIVITY_LEVEL_FORMAL_DESC) {
		SENSITIVITY_LEVEL_FORMAL_DESC = sENSITIVITY_LEVEL_FORMAL_DESC;
	}

	public Date getCLEARED_FOR_NEO() {
		return CLEARED_FOR_NEO;
	}

	public void setCLEARED_FOR_NEO(Date cLEARED_FOR_NEO) {
		CLEARED_FOR_NEO = cLEARED_FOR_NEO;
	}

	public Date getINACTIVE_DATE() {
		return INACTIVE_DATE;
	}

	public void setINACTIVE_DATE(Date iNACTIVE_DATE) {
		INACTIVE_DATE = iNACTIVE_DATE;
	}

	@Override
    public String toString() {
        return "[ HHSID: "+ HHSID +" | "+ "DateRequested: " + " | " + DATE_REQUESTED + " | " 
        		+ "SensitivityLevel: " + SENSITIVITY_LEVEL + " | " + "SensitivityLevelDesc: " 
        		+ SENSITIVITY_LEVEL_DESC +" | "+ "SensitivityLevelFormalDesc: " + SENSITIVITY_LEVEL_FORMAL_DESC
        		+" | "+ "ClearedForNEO: " + CLEARED_FOR_NEO + " | " + "InactiveDate: " +  INACTIVE_DATE +"]";
    }

}
