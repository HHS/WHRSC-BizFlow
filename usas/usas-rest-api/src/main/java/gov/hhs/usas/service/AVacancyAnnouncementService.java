package gov.hhs.usas.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.hhs.usas.dao.AVacancyAnnouncementDAO;
import gov.hhs.usas.dto.ACertificateDTO;
import gov.hhs.usas.dto.AVacancyAnnouncementDTO;
import gov.hhs.usas.entity.AVacancyAnnouncement;

@Service
public class AVacancyAnnouncementService {

	private static final Log LOG = LogFactory.getLog(AVacancyAnnouncementService.class);

	@Autowired
	AVacancyAnnouncementDAO vacancyAnnouncementDao;	

	@Autowired
	ACertificateService certificateService;

	public List<AVacancyAnnouncementDTO> getVacancyAnnouncementDTOByRequestNumber(String requestNumberParam) {

		LOG.info("Query Vacancy Announcement data for Request Number ["+requestNumberParam +"]");

		List<AVacancyAnnouncementDTO> vacancyAnnouncementDTOList = new ArrayList<AVacancyAnnouncementDTO>();
		List<AVacancyAnnouncement> vacancyAnnouncementList = this.vacancyAnnouncementDao.findByRequestNumber(requestNumberParam);

		if(vacancyAnnouncementList != null){
			LOG.info("Vacancy Announcement data retrieved for Request Number ["+requestNumberParam +"]");
			LOG.info("Vacancy Count = "+vacancyAnnouncementList.size());
		}

		for(AVacancyAnnouncement vacancyAnnouncement : vacancyAnnouncementList){
			ACertificateDTO certificateDTO = this.certificateService.getCertificateDTOByRequestNumber(requestNumberParam, vacancyAnnouncement.getVacancyIdentificationNumber());

			AVacancyAnnouncementDTO vacancyAnnouncementDTO = new AVacancyAnnouncementDTO(vacancyAnnouncement.getReqVacID(), vacancyAnnouncement.getRequestNumber(), vacancyAnnouncement.getVacancyIdentificationNumber(),
					vacancyAnnouncement.getVacancyAnnouncementNumber(), vacancyAnnouncement.getSupervisoryStatus(), vacancyAnnouncement.getOf306AssignedInOnboardingManager(),
					vacancyAnnouncement.getRelationshipToRecruitmentAction(), vacancyAnnouncement.getJobCode(), vacancyAnnouncement.getClearanceLevelRequiredForPosition(),
					vacancyAnnouncement.getTypeOfSelection(), vacancyAnnouncement.getEod(), certificateDTO);
			vacancyAnnouncementDTOList.add(vacancyAnnouncementDTO);
		}
		return vacancyAnnouncementDTOList;

	}

}
