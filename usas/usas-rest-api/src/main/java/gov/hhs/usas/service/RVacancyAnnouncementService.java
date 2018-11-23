package gov.hhs.usas.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.hhs.usas.dao.RVacancyAnnouncementDAO;
import gov.hhs.usas.dto.RVacancyAnnouncementDTO;
import gov.hhs.usas.entity.RVacancyAnnouncement;

@Service
public class RVacancyAnnouncementService {

	private static final Log LOG = LogFactory.getLog(RVacancyAnnouncementService.class);

	@Autowired
	RVacancyAnnouncementDAO vacancyAnnouncementDao;

	@Autowired
	RVacancyPositionService positionService;

	@Autowired
	RApplicantRatingService applicantRatingService;

	@Autowired
	RCertificateService certificateService;


	public List<RVacancyAnnouncement> getVacancyAnnouncementByRequestNumber(String requestNumberParam) {

		List<RVacancyAnnouncement> vacancyAnnouncementList = new ArrayList<RVacancyAnnouncement>();
		vacancyAnnouncementList = this.vacancyAnnouncementDao.findByRequestNumber(requestNumberParam);
		//traverse vacancyAnnouncementList
		//for each vacancyAnnouncement element, find positionList, applicants, certificateList
		for(RVacancyAnnouncement vacancyAnnouncement : vacancyAnnouncementList){
			String requestNumber = vacancyAnnouncement.getRequestNumber();
			String vacancyNumber = vacancyAnnouncement.getVacancyIdentificationNumber();
			String announcementNumber = vacancyAnnouncement.getVacancyAnnouncementNumber();

			vacancyAnnouncement.setPositionList(this.positionService.getPositionByVacancyAndRequestNumber(vacancyNumber, requestNumber));

			vacancyAnnouncement.setApplicants(this.applicantRatingService.getApplicantRatingByVacancyAndRequestNumber(vacancyNumber, requestNumber));

			vacancyAnnouncement.setCertificateList(this.certificateService.getCertificateByAnnouncementNumber(announcementNumber));

		}


		return vacancyAnnouncementList;
	}

	public List<RVacancyAnnouncementDTO> getVacancyAnnouncementDTOByRequestNumber(String requestNumberParam) {
		LOG.info("Query Vacancy Announcement data for Request Number ["+requestNumberParam +"]");

		List<RVacancyAnnouncementDTO> vacancyAnnouncementDTOList = new ArrayList<RVacancyAnnouncementDTO>();		

		List<RVacancyAnnouncement> vacancyAnnouncementList = new ArrayList<RVacancyAnnouncement>();
		vacancyAnnouncementList = this.vacancyAnnouncementDao.findByRequestNumber(requestNumberParam);
		if(vacancyAnnouncementList != null){
			LOG.info("Vacancy Announcement data retrieved for Request Number ["+requestNumberParam +"]");
			LOG.info("Vacancy Count = "+vacancyAnnouncementList.size());
		}

		for(RVacancyAnnouncement vacancyAnnouncement : vacancyAnnouncementList){

			RVacancyAnnouncementDTO vacancyAnnouncementDTO = new RVacancyAnnouncementDTO();

			String requestNumber = vacancyAnnouncement.getRequestNumber();
			String vacancyNumber = vacancyAnnouncement.getVacancyIdentificationNumber();
			String announcementNumber = vacancyAnnouncement.getVacancyAnnouncementNumber();

			vacancyAnnouncementDTO.setRequestNumber(requestNumber);
			vacancyAnnouncementDTO.setVacancyIdentificationNumber(vacancyNumber);
			vacancyAnnouncementDTO.setVacancyAnnouncementNumber(announcementNumber);

			vacancyAnnouncementDTO.setVacancyPositionCount(vacancyAnnouncement.getVacancyPositionCount());
			vacancyAnnouncementDTO.setVacancyCertificateCount(vacancyAnnouncement.getVacancyCertificateCount());
			vacancyAnnouncementDTO.setNumberOfPositionsAdvertised(vacancyAnnouncement.getNumberOfPositionsAdvertised());
			vacancyAnnouncementDTO.setAreaOfConsideration(vacancyAnnouncement.getAreaOfConsideration());
			vacancyAnnouncementDTO.setInterdisciplinaryPosition(vacancyAnnouncement.getInterdisciplinaryPosition());
			vacancyAnnouncementDTO.setVacancyIdentificationNumber(vacancyAnnouncement.getVacancyIdentificationNumber());
			vacancyAnnouncementDTO.setVacancyAnnouncementNumber(vacancyAnnouncement.getVacancyAnnouncementNumber());
			vacancyAnnouncementDTO.setAnnouncementType(vacancyAnnouncement.getAnnouncementType());
			vacancyAnnouncementDTO.setDateAnnouncementPosted(vacancyAnnouncement.getDateAnnouncementPosted());
			vacancyAnnouncementDTO.setDateAnnouncementOpened(vacancyAnnouncement.getDateAnnouncementOpened());
			vacancyAnnouncementDTO.setDateAnnouncementClosed(vacancyAnnouncement.getDateAnnouncementClosed());
			vacancyAnnouncementDTO.setDateAnnouncementCancelled(vacancyAnnouncement.getDateAnnouncementCancelled());

			vacancyAnnouncementDTO.setPositionList(this.positionService.getPositionDTOByVacancyAndRequestNumber(vacancyNumber, requestNumber));

			vacancyAnnouncementDTO.setApplicants(this.applicantRatingService.getApplicantRatingDTOByVacancyAndRequestNumber(vacancyNumber, requestNumber));

			vacancyAnnouncementDTO.setCertificateList(this.certificateService.getCertificateDTOByRequestNumber(requestNumber));

			vacancyAnnouncementDTOList.add(vacancyAnnouncementDTO);
		}


		return vacancyAnnouncementDTOList;
	}

}
