package gov.hhs.usas.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.hhs.usas.dto.USAStaffingAppointmentDTO;
import gov.hhs.usas.dto.USAStaffingRecruitmentDTO;
import gov.hhs.usas.service.USAStaffingAppointmentService;
import gov.hhs.usas.service.USAStaffingRecruitmentService;

/**
 * @author prabhjyot.virdi
 *This class acts as the main controller class for the
 *USA Staffing REST API application.
 *
 */
@RestController
@Controller
@RequestMapping("/usas")
public class UsasRestAPIController {

	private static final Log LOG = LogFactory.getLog(UsasRestAPIController.class);


	@Autowired
	USAStaffingRecruitmentService recruitmentService;

	@Autowired
	USAStaffingAppointmentService appointmentService;

	/**
	 * This method helps with testing the deployment of the web application. 
	 * @return String - Message to the tester about web application status on the server
	 */
	@GetMapping(path = "/test", produces = MediaType.TEXT_PLAIN_VALUE)
	public String getStatus() {

		return "Test Success. Application is up and running.";
	}
	
	/**
	 * This method pulls the Recruitment Report data for specific Job Request Number and 
	 * prepares the XML response for EWITS2.0  consumable format.
	 * @param requestNumber - Job Request Number
	 * @return USAStaffingRecruitmentDTO - EWITS2.0 (BizFlow) consumable XML format
	 */
	@GetMapping(path = "/reportXML/recruitment/{requestNumber}", produces = MediaType.APPLICATION_XML_VALUE)
	public USAStaffingRecruitmentDTO getRecruitmentData(@PathVariable String requestNumber) {
		LOG.info("USA Staffing Recruitment Form Data Request received for Request Number ["+requestNumber+"].");
		USAStaffingRecruitmentDTO recruitmentResponse = this.recruitmentService.getRecruitmentDataXMLByRequestNumber(requestNumber);
		LOG.info("USA Staffing Recruitment Form Data Response sent for Request Number ["+requestNumber+"].");
		return recruitmentResponse;
	}

	/**
	 * This method pulls the Appointment Report data for specific Job Request Number and 
	 * prepares the XML response for EWITS2.0  consumable format.
	 * @param requestNumber - Job Request Number
	 * @return USAStaffingAppointmentDTO - EWITS2.0 (BizFlow) consumable XML format
	 */
	@GetMapping(path = "/reportXML/appointment/{requestNumber}", produces = MediaType.APPLICATION_XML_VALUE)
	public USAStaffingAppointmentDTO getAppointmentData(@PathVariable String requestNumber) {
		LOG.info("USA Staffing Appointment Form Data Request received for Request Number ["+requestNumber+"].");
		USAStaffingAppointmentDTO appointmentResponse = this.appointmentService.getAppointmentDataXMLByRequestNumber(requestNumber);
		LOG.info("USA Staffing Appointment Form Data Response sent for Request Number ["+requestNumber+"].");
		return appointmentResponse;
	}


}
