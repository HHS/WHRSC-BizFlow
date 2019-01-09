package gov.hhs.batch.bits;

import java.util.Date;

public class ViewNEOStatusSource {
	
	private String HHSID;
	private Date DateRequested;
	private String SensitivityLevel;
	private String SensitivityLevelDesc;
	private String SensitivityLevelFormalDesc;
	private Date ClearedForNEO;
	private Date InactiveDate;

	public ViewNEOStatusSource() {
		
		HHSID = "";
		DateRequested = null;
		SensitivityLevel = "";
		SensitivityLevelDesc = "";
		SensitivityLevelFormalDesc = "";
		ClearedForNEO = null;
		InactiveDate = null;
	}
	
	public ViewNEOStatusSource(String hHSID, Date dateRequested, String sensitivityLevel, String sensitivityLevelDesc,
			String sensitivityLevelFormalDesc, Date clearedForNEO, Date inactiveDate) {
		
		HHSID = hHSID;
		DateRequested = dateRequested;
		SensitivityLevel = sensitivityLevel;
		SensitivityLevelDesc = sensitivityLevelDesc;
		SensitivityLevelFormalDesc = sensitivityLevelFormalDesc;
		ClearedForNEO = clearedForNEO;
		InactiveDate = inactiveDate;
	}


	
	public String getHHSID() {
		return HHSID;
	}



	public void setHHSID(String hHSID) {
		HHSID = hHSID;
	}



	public Date getDateRequested() {
		return DateRequested;
	}



	public void setDateRequested(Date dateRequested) {
		DateRequested = dateRequested;
	}



	public String getSensitivityLevel() {
		return SensitivityLevel;
	}



	public void setSensitivityLevel(String sensitivityLevel) {
		SensitivityLevel = sensitivityLevel;
	}



	public String getSensitivityLevelDesc() {
		return SensitivityLevelDesc;
	}



	public void setSensitivityLevelDesc(String sensitivityLevelDesc) {
		SensitivityLevelDesc = sensitivityLevelDesc;
	}



	public String getSensitivityLevelFormalDesc() {
		return SensitivityLevelFormalDesc;
	}



	public void setSensitivityLevelFormalDesc(String sensitivityLevelFormalDesc) {
		SensitivityLevelFormalDesc = sensitivityLevelFormalDesc;
	}



	public Date getClearedForNEO() {
		return ClearedForNEO;
	}



	public void setClearedForNEO(Date clearedForNEO) {
		ClearedForNEO = clearedForNEO;
	}



	public Date getInactiveDate() {
		return InactiveDate;
	}



	public void setInactiveDate(Date inactiveDate) {
		InactiveDate = inactiveDate;
	}

	@Override
    public String toString() {
        return "[ HHSID: "+ HHSID +" | "+ "DateRequested: " + " | " + DateRequested + " | " 
        		+ "SensitivityLevel: " + SensitivityLevel + " | " + "SensitivityLevelDesc: " 
        		+ SensitivityLevelDesc +" | "+ "SensitivityLevelFormalDesc: " + SensitivityLevelFormalDesc
        		+" | "+ "ClearedForNEO: " + ClearedForNEO + " | " + "InactiveDate: " +  InactiveDate +"]";
    }

	
	
}
