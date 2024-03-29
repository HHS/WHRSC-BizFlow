package gov.hhs.usas.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="VacancyAnnouncementResult", propOrder={})
public class AVacancyAnnouncementDTO {

	@XmlTransient
	private String reqVacID;
	@XmlTransient
	private String requestNumber;
	@XmlElement(name="Vacancy_Identification_Number")
	private String vacancyIdentificationNumber;
	@XmlElement(name="Vacancy_Announcement_Number")
	private String vacancyAnnouncementNumber;			
	@XmlElement(name="Supervisory_Status")
	private String supervisoryStatus;
	@XmlElement(name="OF-306_Assigned_In_Onboarding_Manager")
	private String of306AssignedInOnboardingManager;
	@XmlElement(name="Relationship_To_Recruitment_Action")
	private String relationshipToRecruitmentAction;
	@XmlElement(name="Job_Code")
	private String jobCode;
	@XmlElement(name="Clearance_Level_Required_For_Position")
	private String clearanceLevelRequiredForPosition;
	@XmlElement(name="Type_Of_Selection")
	private String typeOfSelection;			
	@XmlElement(name="EOD")
	private String eod;
	@XmlElement(name="Certificate")
	private ACertificateDTO certificate;
	
	public AVacancyAnnouncementDTO() {
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
		this.certificate = new ACertificateDTO();
	}
	
	public AVacancyAnnouncementDTO(String reqVacID, String requestNumber, String vacancyIdentificationNumber,
			String vacancyAnnouncementNumber, String supervisoryStatus, String of306AssignedInOnboardingManager,
			String relationshipToRecruitmentAction, String jobCode, String clearanceLevelRequiredForPosition,
			String typeOfSelection, String eod, ACertificateDTO certificate) {
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

	public String getVacancyIdentificationNumber() {
		return vacancyIdentificationNumber;
	}

	public void setVacancyIdentificationNumber(String vacancyIdentificationNumber) {
		this.vacancyIdentificationNumber = vacancyIdentificationNumber;
	}

	public String getVacancyAnnouncementNumber() {
		return vacancyAnnouncementNumber;
	}

	public void setVacancyAnnouncementNumber(String vacancyAnnouncementNumber) {
		this.vacancyAnnouncementNumber = vacancyAnnouncementNumber;
	}

	public String getSupervisoryStatus() {
		return supervisoryStatus;
	}

	public void setSupervisoryStatus(String supervisoryStatus) {
		this.supervisoryStatus = supervisoryStatus;
	}

	public String getOf306AssignedInOnboardingManager() {
		return of306AssignedInOnboardingManager;
	}

	public void setOf306AssignedInOnboardingManager(String of306AssignedInOnboardingManager) {
		this.of306AssignedInOnboardingManager = of306AssignedInOnboardingManager;
	}

	public String getRelationshipToRecruitmentAction() {
		return relationshipToRecruitmentAction;
	}

	public void setRelationshipToRecruitmentAction(String relationshipToRecruitmentAction) {
		this.relationshipToRecruitmentAction = relationshipToRecruitmentAction;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getClearanceLevelRequiredForPosition() {
		return clearanceLevelRequiredForPosition;
	}

	public void setClearanceLevelRequiredForPosition(String clearanceLevelRequiredForPosition) {
		this.clearanceLevelRequiredForPosition = clearanceLevelRequiredForPosition;
	}

	public String getTypeOfSelection() {
		return typeOfSelection;
	}

	public void setTypeOfSelection(String typeOfSelection) {
		this.typeOfSelection = typeOfSelection;
	}

	public String getEod() {
		return eod;
	}

	public void setEod(String eod) {
		this.eod = eod;
	}

	public ACertificateDTO getCertificate() {
		return certificate;
	}

	public void setCertificate(ACertificateDTO certificate) {
		this.certificate = certificate;
	}
	
	@Override
	public String toString(){
		return "AVacancyAnnouncementDTO [vacancyIdentificationNumber: " + this.getVacancyIdentificationNumber() + "| vacancyAnnouncementNumber: " + this.getVacancyAnnouncementNumber()
		+ "| supervisoryStatus: " + this.getSupervisoryStatus() + "| of306AssignedInOnboardingManager: " + this.getOf306AssignedInOnboardingManager() + "| relationshipToRecruitmentAction: " + this.getRelationshipToRecruitmentAction()
		+ "| jobCode: " + this.getJobCode() + "| clearanceLevelRequiredForPosition: " + this.getClearanceLevelRequiredForPosition() 
		+ "| typeOfSelection: " + this.getTypeOfSelection() + "| eod: " + this.getEod() + "| certificate: " + this.getCertificate() + "]";
	}
	
	
	
}
