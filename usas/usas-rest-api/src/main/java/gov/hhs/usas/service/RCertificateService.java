package gov.hhs.usas.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.hhs.usas.dao.RCertificateDAO;
import gov.hhs.usas.dto.RCertificateDTO;
import gov.hhs.usas.entity.RCertificate;

@Service
public class RCertificateService {

	private static final Log LOG = LogFactory.getLog(RCertificateService.class);

	@Autowired
	RCertificateDAO certificateDao;

	public List<RCertificate> getCertificateByAnnouncementNumber(String announcementNumber) {
		return this.certificateDao.findByRequestNumber(announcementNumber);
	}

	public List<RCertificateDTO> getCertificateDTOByRequestNumber(String requestNumber) {

		LOG.info("Query Vacancy Certificate data for Request Number ["+requestNumber +"]");		

		List<RCertificateDTO> certificateDTOList = new ArrayList<RCertificateDTO>();
		List<RCertificate> certificateList = this.certificateDao.findByRequestNumber(requestNumber);

		if(certificateList != null){
			LOG.info("Vacancy Certificate data retrieved for Request Number ["+requestNumber +"]");		
			LOG.info("Vacancy Certificate Count = "+certificateList.size());
		}

		for(RCertificate certificate : certificateList){
			RCertificateDTO certificateDTO = new RCertificateDTO(certificate.getRecVacID(), certificate.getAnnouncementNumber(), certificate.getCertificateType(), certificate.getCertificateNumber(),
					certificate.getPositionTitle(), certificate.getSeries(), certificate.getGrade(), certificate.getDutyLocation(), certificate.getDateCertificateIssued(),
					certificate.getDateCertificateSentToSO(), certificate.getSelectionMade(), certificate.getActionTaken(),
					certificate.getDateHiringDecisionRecievedInHR(), certificate.getDateFinalApplcantStatusesSet(), certificate.getDateAuditCompleted());

			certificateDTOList.add(certificateDTO);			
		}

		return certificateDTOList;
	}
}
