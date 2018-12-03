package gov.hhs.usas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import gov.hhs.usas.Util;

@Entity(name="VW_A_VAC_ANN_RESULT")
public class AVacancyAnnouncement {
	
	@Id
	@Column(name = "REQ_VAC")
	private String reqVacID;
	@Column(name = "REQUEST_NUMBER")
	private String requestNumber;
	@Column(name = "VACANCY_NUMBER")
	private String vacancyIdentificationNumber;
	@Column(name = "VACANCY_ANNOUNCEMENT_NUMBER")
	private String vacancyAnnouncementNumber;			
	@Column(name = "SUPERVISORY_POSITION")
	private String supervisoryStatus;
	@Column(name = "OF_306_ASSGND_IN_ONBRDG_MNGR")
	private String of306AssignedInOnboardingManager;
	@Column(name = "RLTSHP_TO_RCRMT_ACTN")
	private String relationshipToRecruitmentAction;
	@Column(name = "JOB_CODE")
	private String jobCode;
	@Column(name = "CLEARANCE_LEVEL_REQUIRED")
	private String clearanceLevelRequiredForPosition;
	@Column(name = "TYPE_OF_SELECTION")
	private String typeOfSelection;			
	@Column(name = "EOD")
	private String eod;
	private transient ACertificate certificate;
	
	public AVacancyAnnouncement() {
		this.reqVacID = "";
		this.requestNumber = "";
		this.vacancyIdentificationNumber = "";
		this.vacancyAnnouncementNumber = "";
		this.supervisoryStatus = "";
		this.of306AssignedInOnboardingManager = "";
		this.relationshipToRecruitmentAction = "";
		this.jobCode = "";
		this.clearanceLevelRequiredForPosition = "";
		this.typeOfSelection = "";
		this.eod = "";
		this.certificate = new ACertificate();
	}
	
	public AVacancyAnnouncement(String reqVacID, String requestNumber, String vacancyIdentificationNumber,
			String vacancyAnnouncementNumber, String supervisoryStatus, String of306AssignedInOnboardingManager,
			String relationshipToRecruitmentAction, String jobCode, String clearanceLevelRequiredForPosition,
			String typeOfSelection, String eod, ACertificate certificate) {
		super();
		this.reqVacID = reqVacID;
		this.requestNumber = requestNumber;
		this.vacancyIdentificationNumber = vacancyIdentificationNumber;
		this.vacancyAnnouncementNumber = vacancyAnnouncementNumber;
		this.supervisoryStatus = supervisoryStatus;
		this.of306AssignedInOnboardingManager = of306AssignedInOnboardingManager;
		this.relationshipToRecruitmentAction = relationshipToRecruitmentAction;
		this.jobCode = jobCode;
		this.clearanceLevelRequiredForPosition = clearanceLevelRequiredForPosition;
		this.typeOfSelection = typeOfSelection;
		this.eod = eod;
		this.certificate = certificate;
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

	public String getVacancyIdentificationNumber() {
		return Util.checkForNull(vacancyIdentificationNumber);
	}

	public void setVacancyIdentificationNumber(String vacancyIdentificationNumber) {
		this.vacancyIdentificationNumber = vacancyIdentificationNumber;
	}

	public String getVacancyAnnouncementNumber() {
		return Util.checkForNull(vacancyAnnouncementNumber);
	}

	public void setVacancyAnnouncementNumber(String vacancyAnnouncementNumber) {
		this.vacancyAnnouncementNumber = vacancyAnnouncementNumber;
	}

	public String getSupervisoryStatus() {
		return Util.checkForNull(supervisoryStatus);
	}

	public void setSupervisoryStatus(String supervisoryStatus) {
		this.supervisoryStatus = supervisoryStatus;
	}

	public String getOf306AssignedInOnboardingManager() {
		return Util.checkForNull(of306AssignedInOnboardingManager);
	}

	public void setOf306AssignedInOnboardingManager(String of306AssignedInOnboardingManager) {
		this.of306AssignedInOnboardingManager = of306AssignedInOnboardingManager;
	}

	public String getRelationshipToRecruitmentAction() {
		return Util.checkForNull(relationshipToRecruitmentAction);
	}

	public void setRelationshipToRecruitmentAction(String relationshipToRecruitmentAction) {
		this.relationshipToRecruitmentAction = relationshipToRecruitmentAction;
	}

	public String getJobCode() {
		return Util.checkForNull(jobCode);
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getClearanceLevelRequiredForPosition() {
		return Util.checkForNull(clearanceLevelRequiredForPosition);
	}

	public void setClearanceLevelRequiredForPosition(String clearanceLevelRequiredForPosition) {
		this.clearanceLevelRequiredForPosition = clearanceLevelRequiredForPosition;
	}

	public String getTypeOfSelection() {
		return Util.checkForNull(typeOfSelection);
	}

	public void setTypeOfSelection(String typeOfSelection) {
		this.typeOfSelection = typeOfSelection;
	}

	public String getEod() {
		return Util.checkForNull(eod);
	}

	public void setEod(String eod) {
		this.eod = eod;
	}

	public ACertificate getCertificate() {
		return certificate;
	}

	public void setCertificate(ACertificate certificate) {
		this.certificate = certificate;
	}
	
	@Override
	public String toString(){
		return "AVacancyAnnouncement [vacancyIdentificationNumber: " + this.getVacancyIdentificationNumber() + "| vacancyAnnouncementNumber: " + this.getVacancyAnnouncementNumber()
		+ "| supervisoryStatus: " + this.getSupervisoryStatus() + "| of306AssignedInOnboardingManager: " + this.getOf306AssignedInOnboardingManager() + "| relationshipToRecruitmentAction: " + this.getRelationshipToRecruitmentAction()
		+ "| jobCode: " + this.getJobCode() + "| clearanceLevelRequiredForPosition: " + this.getClearanceLevelRequiredForPosition() 
		+ "| typeOfSelection: " + this.getTypeOfSelection() + "| eod: " + this.getEod() + "| certificate: " + this.getCertificate() + "]";
	}
	
	
	
}
