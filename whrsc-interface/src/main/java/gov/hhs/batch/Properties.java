package gov.hhs.batch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Component
@PropertySources({ @PropertySource("classpath:application.properties"),
		@PropertySource("classpath:whrsc-report.properties") })
public class Properties {

	// Interface Name
	@Value("${bits.interface.name}")
	private String bitsInterfacename;
	@Value("${caphr.interface.name}")
	private String caphrInterfaceName;
	@Value("${usas.interface.name}")
	private String usasInterfaceName;

	// Log Files Name
	@Value("${bits.log.name}")
	private String bitsLogfile;
	@Value("${caphr.log.name}")
	private String caphrLogfile;
	@Value("${usas.log.name}")
	private String usasLogfile;

	// BITS Queries
	@Value("${query.bits.list.source}")
	private String queryBitsListSource;
	@Value("${query.bits.insert.target}")
	private String queryBitsInsertTarget;
	@Value("${query.bits.delete.target}")
	private String queryBitsDeleteTarget;

	// CapHR PL/SQL Stored Function properties
	@Value("${plsql.caphr.catalog}")
	private String plsqlCaphrCatalog;
	@Value("${plsql.caphr.schema}")
	private String plsqlCaphrSchema;
	@Value("${plsql.caphr.function}")
	private String plsqlCaphrFunction;

	// Cron Schedule of Jobs
	@Value("${bits.cron.sched}")
	private String bitsCronSchedule;
	@Value("${caphr.cron.sched}")
	private String caphrCronSchedule;
	@Value("${usas.cron.sched}")
	private String usasCronSchedule;

	// Run Batch Jobs-True or False
	@Value("${run.bits.job}")
	private String runBitsJob;
	@Value("${run.caphr.job}")
	private String runCaphrJob;
	@Value("${run.usas.job}")
	private String runUsasJob;

	// Email Properties
	@Value("${send.email.notification}")
	private boolean sendEmailNotification;

	@Value("${email.subject}")
	private String emailSubjectTemplate;
	@Value("${email.caphr.message}")
	private String emailCaphrMessageTemplate;
	@Value("${email.bits.message}")
	private String emailBitsMessageTemplate;
	@Value("${email.usas.message}")
	private String emailUsasMessageTemplate;
	@Value("${emailid.from}")
	private String from;
	@Value("${emailid.to}")
	private String[] to;

	//////////////////////////////////////////////////////////////////////////////////////////
	//// CONFIGURATION FOR WHRSC DATASTORE REPORTS
	//////////////////////////////////////////////////////////////////////////////////////////

	// Save a file of the report to the directory for debugging purposes- true
	// or false value
	@Value("${save.report.file}")
	private boolean saveReport;
	//////////////////////////////////////////////////////////////////////
	//// REPORT STATUSES
	//////////////////////////////////////////////////////////////////////
	@Value("${report.off}")
	private String reportOffMessage;
	@Value("${report.success}")
	private String reportSuccessMessage;
	@Value("${report.error}")
	private String reportErrorMessage;
	@Value("${report.fail}")
	private String reportFailMessage;

	//////////////////////////////////////////////////////////////////////
	//// REPORT PROPERTIES
	//////////////////////////////////////////////////////////////////////

	// Applicant Notifications (an) Report
	@Value("${an.search.path}")
	private String anSearchPath;
	@Value("${an.intg.type}")
	private String anIntgType;
	@Value("${an.file.name}")
	private String anFileName;
	@Value("${an.report.name}")
	private String anReportName;
	@Value("${an.step.name}")
	private String anStepName;
	@Value("${an.truncate}")
	private String anTruncateProcName;
	@Value("${an.run.report}")
	private boolean runANReport;

	// Certificate Series Locations (cl) Report
	@Value("${cl.search.path}")
	private String clSearchPath;
	@Value("${cl.intg.type}")
	private String clIntgType;
	@Value("${cl.file.name}")
	private String clFileName;
	@Value("${cl.report.name}")
	private String clReportName;
	@Value("${cl.step.name}")
	private String clStepName;
	@Value("${cl.truncate}")
	private String clTruncateProcName;
	@Value("${cl.run.report}")
	private boolean runCLReport;

	// Certificates (c) Report
	@Value("${c.search.path}")
	private String cSearchPath;
	@Value("${c.intg.type}")
	private String cIntgType;
	@Value("${c.file.name}")
	private String cFileName;
	@Value("${c.report.name}")
	private String cReportName;
	@Value("${c.step.name}")
	private String cStepName;
	@Value("${c.truncate}")
	private String cTruncateProcName;
	@Value("${c.run.report}")
	private boolean runCReport;

	// Permission Profiles (pp) Report
	@Value("${pp.search.path}")
	private String ppSearchPath;
	@Value("${pp.intg.type}")
	private String ppIntgType;
	@Value("${pp.file.name}")
	private String ppFileName;
	@Value("${pp.report.name}")
	private String ppReportName;
	@Value("${pp.step.name}")
	private String ppStepName;
	@Value("${pp.truncate}")
	private String ppTruncateProcName;
	@Value("${pp.run.report}")
	private boolean runPPReport;

	// New Hire Forms (nhf) Report
	@Value("${nhf.search.path}")
	private String nhfSearchPath;
	@Value("${nhf.intg.type}")
	private String nhfIntgType;
	@Value("${nhf.file.name}")
	private String nhfFileName;
	@Value("${nhf.report.name}")
	private String nhfReportName;
	@Value("${nhf.step.name}")
	private String nhfStepName;
	@Value("${nhf.truncate}")
	private String nhfTruncateProcName;
	@Value("${nhf.run.report}")
	private boolean runNHFReport;

	// New Hire Onboarding Documents (nhod) Report
	@Value("${nhod.search.path}")
	private String nhodSearchPath;
	@Value("${nhod.intg.type}")
	private String nhodIntgType;
	@Value("${nhod.file.name}")
	private String nhodFileName;
	@Value("${nhod.report.name}")
	private String nhodReportName;
	@Value("${nhod.step.name}")
	private String nhodStepName;
	@Value("${nhod.truncate}")
	private String nhodTruncateProcName;
	@Value("${nhod.run.report}")
	private boolean runNHODReport;

	// New Hire Task (nht) Report
	@Value("${nht.search.path}")
	private String nhtSearchPath;
	@Value("${nht.intg.type}")
	private String nhtIntgType;
	@Value("${nht.file.name}")
	private String nhtFileName;
	@Value("${nht.report.name}")
	private String nhtReportName;
	@Value("${nht.step.name}")
	private String nhtStepName;
	@Value("${nht.truncate}")
	private String nhtTruncateProcName;
	@Value("${nht.run.report}")
	private boolean runNHTReport;

	// New Hire Vacancy Request (nhvr) Report
	@Value("${nhvr.search.path}")
	private String nhvrSearchPath;
	@Value("${nhvr.intg.type}")
	private String nhvrIntgType;
	@Value("${nhvr.file.name}")
	private String nhvrFileName;
	@Value("${nhvr.report.name}")
	private String nhvrReportName;
	@Value("${nhvr.step.name}")
	private String nhvrStepName;
	@Value("${nhvr.truncate}")
	private String nhvrTruncateProcName;
	@Value("${nhvr.run.report}")
	private boolean runNHVRPath;

	// New Hires (nh) Report
	@Value("${nh.search.path}")
	private String nhSearchPath;
	@Value("${nh.intg.type}")
	private String nhIntgType;
	@Value("${nh.file.name}")
	private String nhFileName;
	@Value("${nh.report.name}")
	private String nhReportName;
	@Value("${nh.step.name}")
	private String nhStepName;
	@Value("${nh.truncate}")
	private String nhTruncateProcName;
	@Value("${nh.run.report}")
	private boolean runNHReport;

	// Request Locations (rl) Report
	@Value("${rl.search.path}")
	private String rlSearchPath;
	@Value("${rl.intg.type}")
	private String rlIntgType;
	@Value("${rl.file.name}")
	private String rlFileName;
	@Value("${rl.report.name}")
	private String rlReportName;
	@Value("${rl.step.name}")
	private String rlStepName;
	@Value("${rl.truncate}")
	private String rlTruncateProcName;
	@Value("${rl.run.report}")
	private boolean runRLReport;

	// Request Rating Combinations (rrc) Report
	@Value("${rrc.search.path}")
	private String rrcSearchPath;
	@Value("${rrc.intg.type}")
	private String rrcIntgType;
	@Value("${rrc.file.name}")
	private String rrcFileName;
	@Value("${rrc.report.name}")
	private String rrcReportName;
	@Value("${rrc.step.name}")
	private String rrcStepName;
	@Value("${rrc.truncate}")
	private String rrcTruncateProcName;
	@Value("${rrc.run.report}")
	private boolean runRRCReport;

	// Request Vacancy Combinations (rvc) Report
	@Value("${rvc.search.path}")
	private String rvcSearchPath;
	@Value("${rvc.intg.type}")
	private String rvcIntgType;
	@Value("${rvc.file.name}")
	private String rvcFileName;
	@Value("${rvc.report.name}")
	private String rvcReportName;
	@Value("${rvc.step.name}")
	private String rvcStepName;
	@Value("${rvc.truncate}")
	private String rvcTruncateProcName;
	@Value("${rvc.run.report}")
	private boolean runRVCReport;

	// Requests (req) Report
	@Value("${req.search.path}")
	private String reqSearchPath;
	@Value("${req.intg.type}")
	private String reqIntgType;
	@Value("${req.file.name}")
	private String reqFileName;
	@Value("${req.report.name}")
	private String reqReportName;
	@Value("${req.step.name}")
	private String reqStepname;
	@Value("${req.truncate}")
	private String reqTruncateProcName;
	@Value("${req.run.report}")
	private boolean runREQReport;

	// Vacancy (vac) Report
	@Value("${vac.search.path}")
	private String vacSearchPath;
	@Value("${vac.intg.type}")
	private String vacIntgType;
	@Value("${vac.file.name}")
	private String vacFileName;
	@Value("${vac.report.name}")
	private String vacReportName;
	@Value("${vac.step.name}")
	private String vacStepName;
	@Value("${vac.truncate}")
	private String vacTruncateProcName;
	@Value("${vac.run.report}")
	private boolean runVACReport;

	// Vacancy Eligibilities (ve) Report
	@Value("${ve.search.path}")
	private String veSearchPath;
	@Value("${ve.intg.type}")
	private String veIntgType;
	@Value("${ve.file.name}")
	private String veFileName;
	@Value("${ve.report.name}")
	private String veReportName;
	@Value("${ve.step.name}")
	private String veStepName;
	@Value("${ve.truncate}")
	private String veTruncateProcName;
	@Value("${ve.run.report}")
	private boolean runVEReport;

	// Vacancy Locations (vl) Report
	@Value("${vl.search.path}")
	private String vlSearchPath;
	@Value("${vl.intg.type}")
	private String vlIntgType;
	@Value("${vl.file.name}")
	private String vlFileName;
	@Value("${vl.report.name}")
	private String vlReportName;
	@Value("${vl.step.name}")
	private String vlStepName;
	@Value("${vl.truncate}")
	private String vlTruncateProcName;
	@Value("${vl.run.report}")
	private boolean runVLReport;

	// Vacancy Mission Critical Occupations (vmco) Report
	@Value("${vmco.search.path}")
	private String vmcoSearchPath;
	@Value("${vmco.intg.type}")
	private String vmcoIntgType;
	@Value("${vmco.file.name}")
	private String vmcoFileName;
	@Value("${vmco.report.name}")
	private String vmcoReportName;
	@Value("${vmco.step.name}")
	private String vmcoStepName;
	@Value("${vmco.truncate}")
	private String vmcoTruncateProcName;
	@Value("${vmco.run.report}")
	private boolean runVMCOReport;

	// Vacancy Rating Combinations (vrc) Report
	@Value("${vrc.search.path}")
	private String vrcSearchPath;
	@Value("${vrc.intg.type}")
	private String vrcIntgType;
	@Value("${vrc.file.name}")
	private String vrcFileName;
	@Value("${vrc.report.name}")
	private String vrcReportName;
	@Value("${vrc.step.name}")
	private String vrcStepName;
	@Value("${vrc.truncate}")
	private String vrcTruncateProcName;
	@Value("${vrc.run.report}")
	private boolean runVRCReport;

	public String getBitsInterfacename() {
		return bitsInterfacename;
	}

	public void setBitsInterfacename(String bitsInterfacename) {
		this.bitsInterfacename = bitsInterfacename;
	}

	public String getCaphrInterfaceName() {
		return caphrInterfaceName;
	}

	public void setCaphrInterfaceName(String caphrInterfaceName) {
		this.caphrInterfaceName = caphrInterfaceName;
	}

	public String getUsasInterfaceName() {
		return usasInterfaceName;
	}

	public void setUsasInterfaceName(String usasInterfaceName) {
		this.usasInterfaceName = usasInterfaceName;
	}

	public String getBitsLogfile() {
		return bitsLogfile;
	}

	public void setBitsLogfile(String bitsLogfile) {
		this.bitsLogfile = bitsLogfile;
	}

	public String getCaphrLogfile() {
		return caphrLogfile;
	}

	public void setCaphrLogfile(String caphrLogfile) {
		this.caphrLogfile = caphrLogfile;
	}

	public String getUsasLogfile() {
		return usasLogfile;
	}

	public void setUsasLogfile(String usasLogfile) {
		this.usasLogfile = usasLogfile;
	}

	public String getQueryBitsListSource() {
		return queryBitsListSource;
	}

	public void setQueryBitsListSource(String queryBitsListSource) {
		this.queryBitsListSource = queryBitsListSource;
	}

	public String getQueryBitsInsertTarget() {
		return queryBitsInsertTarget;
	}

	public void setQueryBitsInsertTarget(String queryBitsInsertTarget) {
		this.queryBitsInsertTarget = queryBitsInsertTarget;
	}

	public String getQueryBitsDeleteTarget() {
		return queryBitsDeleteTarget;
	}

	public void setQueryBitsDeleteTarget(String queryBitsDeleteTarget) {
		this.queryBitsDeleteTarget = queryBitsDeleteTarget;
	}

	public String getPlsqlCaphrCatalog() {
		return plsqlCaphrCatalog;
	}

	public void setPlsqlCaphrCatalog(String plsqlCaphrCatalog) {
		this.plsqlCaphrCatalog = plsqlCaphrCatalog;
	}

	public String getPlsqlCaphrSchema() {
		return plsqlCaphrSchema;
	}

	public void setPlsqlCaphrSchema(String plsqlCaphrSchema) {
		this.plsqlCaphrSchema = plsqlCaphrSchema;
	}

	public String getPlsqlCaphrFunction() {
		return plsqlCaphrFunction;
	}

	public void setPlsqlCaphrFunction(String plsqlCaphrFunction) {
		this.plsqlCaphrFunction = plsqlCaphrFunction;
	}

	public String getBitsCronSchedule() {
		return bitsCronSchedule;
	}

	public void setBitsCronSchedule(String bitsCronSchedule) {
		this.bitsCronSchedule = bitsCronSchedule;
	}

	public String getCaphrCronSchedule() {
		return caphrCronSchedule;
	}

	public void setCaphrCronSchedule(String caphrCronSchedule) {
		this.caphrCronSchedule = caphrCronSchedule;
	}

	public String getUsasCronSchedule() {
		return usasCronSchedule;
	}

	public void setUsasCronSchedule(String usasCronSchedule) {
		this.usasCronSchedule = usasCronSchedule;
	}

	public String getRunBitsJob() {
		return runBitsJob;
	}

	public void setRunBitsJob(String runBitsJob) {
		this.runBitsJob = runBitsJob;
	}

	public String getRunCaphrJob() {
		return runCaphrJob;
	}

	public void setRunCaphrJob(String runCaphrJob) {
		this.runCaphrJob = runCaphrJob;
	}

	public String getRunUsasJob() {
		return runUsasJob;
	}

	public void setRunUsasJob(String runUsasJob) {
		this.runUsasJob = runUsasJob;
	}


	public boolean sendEmailNotification() {
		return sendEmailNotification;
	}

	public void setSendEmailNotification(boolean sendEmailNotification) {
		this.sendEmailNotification = sendEmailNotification;
	}

	public String getEmailSubjectTemplate() {
		return emailSubjectTemplate;
	}

	public void setEmailSubjectTemplate(String emailSubjectTemplate) {
		this.emailSubjectTemplate = emailSubjectTemplate;
	}

	public String getEmailCaphrMessageTemplate() {
		return emailCaphrMessageTemplate;
	}

	public void setEmailCaphrMessageTemplate(String emailMessageTemplate) {
		this.emailCaphrMessageTemplate = emailMessageTemplate;
	}

	public String getEmailBitsMessageTemplate() {
		return emailBitsMessageTemplate;
	}

	public void setEmailBitsMessageTemplate(String emailBitsMessageTemplate) {
		this.emailBitsMessageTemplate = emailBitsMessageTemplate;
	}

	public String getEmailUsasMessageTemplate() {
		return emailUsasMessageTemplate;
	}

	public void setEmailUsasMessageTemplate(String emailHTMLMessageTemplate) {
		this.emailUsasMessageTemplate = emailHTMLMessageTemplate;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String[] getTo() {
		return to;
	}

	public boolean saveReport() {
		return saveReport;
	}

	public void setSaveReport(boolean saveReport) {
		this.saveReport = saveReport;
	}

	public String getReportOffMessage() {
		return reportOffMessage;
	}

	public void setReportOffMessage(String reportOffMessage) {
		this.reportOffMessage = reportOffMessage;
	}

	public String getReportSuccessMessage() {
		return reportSuccessMessage;
	}

	public void setReportSuccessMessage(String reportSuccessMessage) {
		this.reportSuccessMessage = reportSuccessMessage;
	}

	public String getReportErrorMessage() {
		return reportErrorMessage;
	}

	public void setReportErrorMessage(String reportErrorMessage) {
		this.reportErrorMessage = reportErrorMessage;
	}

	public String getReportFailMessage() {
		return reportFailMessage;
	}

	public void setReportFailMessage(String reportFailMessage) {
		this.reportFailMessage = reportFailMessage;
	}

	public String getAnSearchPath() {
		return anSearchPath;
	}

	public void setAnSearchPath(String anSearchPath) {
		this.anSearchPath = anSearchPath;
	}

	public String getAnIntgType() {
		return anIntgType;
	}

	public void setAnIntgType(String anIntgType) {
		this.anIntgType = anIntgType;
	}

	public String getAnFileName() {
		return anFileName;
	}

	public void setAnFileName(String anFileName) {
		this.anFileName = anFileName;
	}

	public String getAnReportName() {
		return anReportName;
	}

	public void setAnReportName(String anReportName) {
		this.anReportName = anReportName;
	}

	public String getAnStepName() {
		return anStepName;
	}

	public void setAnStepName(String anStepName) {
		this.anStepName = anStepName;
	}

	public String getAnTruncateProcName() {
		return anTruncateProcName;
	}

	public void setAnTruncateProcName(String anTruncateProcName) {
		this.anTruncateProcName = anTruncateProcName;
	}

	public boolean isRunANReport() {
		return runANReport;
	}

	public void setRunANReport(boolean runANReport) {
		this.runANReport = runANReport;
	}

	public String getClSearchPath() {
		return clSearchPath;
	}

	public void setClSearchPath(String clSearchPath) {
		this.clSearchPath = clSearchPath;
	}

	public String getClIntgType() {
		return clIntgType;
	}

	public void setClIntgType(String clIntgType) {
		this.clIntgType = clIntgType;
	}

	public String getClFileName() {
		return clFileName;
	}

	public void setClFileName(String clFileName) {
		this.clFileName = clFileName;
	}

	public String getClReportName() {
		return clReportName;
	}

	public void setClReportName(String clReportName) {
		this.clReportName = clReportName;
	}

	public String getClStepName() {
		return clStepName;
	}

	public void setClStepName(String clStepName) {
		this.clStepName = clStepName;
	}

	public String getClTruncateProcName() {
		return clTruncateProcName;
	}

	public void setClTruncateProcName(String clTruncateProcName) {
		this.clTruncateProcName = clTruncateProcName;
	}

	public boolean isRunCLReport() {
		return runCLReport;
	}

	public void setRunCLReport(boolean runCLReport) {
		this.runCLReport = runCLReport;
	}

	public String getcSearchPath() {
		return cSearchPath;
	}

	public void setcSearchPath(String cSearchPath) {
		this.cSearchPath = cSearchPath;
	}

	public String getcIntgType() {
		return cIntgType;
	}

	public void setcIntgType(String cIntgType) {
		this.cIntgType = cIntgType;
	}

	public String getcFileName() {
		return cFileName;
	}

	public void setcFileName(String cFileName) {
		this.cFileName = cFileName;
	}

	public String getcReportName() {
		return cReportName;
	}

	public void setcReportName(String cReportName) {
		this.cReportName = cReportName;
	}

	public String getcStepName() {
		return cStepName;
	}

	public void setcStepName(String cStepName) {
		this.cStepName = cStepName;
	}

	public String getcTruncateProcName() {
		return cTruncateProcName;
	}

	public void setcTruncateProcName(String cTruncateProcName) {
		this.cTruncateProcName = cTruncateProcName;
	}

	public boolean isRunCReport() {
		return runCReport;
	}

	public void setRunCReport(boolean runCReport) {
		this.runCReport = runCReport;
	}

	public String getPpSearchPath() {
		return ppSearchPath;
	}

	public void setPpSearchPath(String ppSearchPath) {
		this.ppSearchPath = ppSearchPath;
	}

	public String getPpIntgType() {
		return ppIntgType;
	}

	public void setPpIntgType(String ppIntgType) {
		this.ppIntgType = ppIntgType;
	}

	public String getPpFileName() {
		return ppFileName;
	}

	public void setPpFileName(String ppFileName) {
		this.ppFileName = ppFileName;
	}

	public String getPpReportName() {
		return ppReportName;
	}

	public void setPpReportName(String ppReportName) {
		this.ppReportName = ppReportName;
	}

	public String getPpStepName() {
		return ppStepName;
	}

	public void setPpStepName(String ppStepName) {
		this.ppStepName = ppStepName;
	}

	public String getPpTruncateProcName() {
		return ppTruncateProcName;
	}

	public void setPpTruncateProcName(String ppTruncateProcName) {
		this.ppTruncateProcName = ppTruncateProcName;
	}

	public boolean isRunPPReport() {
		return runPPReport;
	}

	public void setRunPPReport(boolean runPPReport) {
		this.runPPReport = runPPReport;
	}

	public String getNhfSearchPath() {
		return nhfSearchPath;
	}

	public void setNhfSearchPath(String nhfSearchPath) {
		this.nhfSearchPath = nhfSearchPath;
	}

	public String getNhfIntgType() {
		return nhfIntgType;
	}

	public void setNhfIntgType(String nhfIntgType) {
		this.nhfIntgType = nhfIntgType;
	}

	public String getNhfFileName() {
		return nhfFileName;
	}

	public void setNhfFileName(String nhfFileName) {
		this.nhfFileName = nhfFileName;
	}

	public String getNhfReportName() {
		return nhfReportName;
	}

	public void setNhfReportName(String nhfReportName) {
		this.nhfReportName = nhfReportName;
	}

	public String getNhfStepName() {
		return nhfStepName;
	}

	public void setNhfStepName(String nhfStepName) {
		this.nhfStepName = nhfStepName;
	}

	public String getNhfTruncateProcName() {
		return nhfTruncateProcName;
	}

	public void setNhfTruncateProcName(String nhfTruncateProcName) {
		this.nhfTruncateProcName = nhfTruncateProcName;
	}

	public boolean isRunNHFReport() {
		return runNHFReport;
	}

	public void setRunNHFReport(boolean runNHFReport) {
		this.runNHFReport = runNHFReport;
	}

	public String getNhodSearchPath() {
		return nhodSearchPath;
	}

	public void setNhodSearchPath(String nhodSearchPath) {
		this.nhodSearchPath = nhodSearchPath;
	}

	public String getNhodIntgType() {
		return nhodIntgType;
	}

	public void setNhodIntgType(String nhodIntgType) {
		this.nhodIntgType = nhodIntgType;
	}

	public String getNhodFileName() {
		return nhodFileName;
	}

	public void setNhodFileName(String nhodFileName) {
		this.nhodFileName = nhodFileName;
	}

	public String getNhodReportName() {
		return nhodReportName;
	}

	public void setNhodReportName(String nhodReportName) {
		this.nhodReportName = nhodReportName;
	}

	public String getNhodStepName() {
		return nhodStepName;
	}

	public void setNhodStepName(String nhodStepName) {
		this.nhodStepName = nhodStepName;
	}

	public String getNhodTruncateProcName() {
		return nhodTruncateProcName;
	}

	public void setNhodTruncateProcName(String nhodTruncateProcName) {
		this.nhodTruncateProcName = nhodTruncateProcName;
	}

	public boolean isRunNHODReport() {
		return runNHODReport;
	}

	public void setRunNHODReport(boolean runNHODReport) {
		this.runNHODReport = runNHODReport;
	}

	public String getNhtSearchPath() {
		return nhtSearchPath;
	}

	public void setNhtSearchPath(String nhtSearchPath) {
		this.nhtSearchPath = nhtSearchPath;
	}

	public String getNhtIntgType() {
		return nhtIntgType;
	}

	public void setNhtIntgType(String nhtIntgType) {
		this.nhtIntgType = nhtIntgType;
	}

	public String getNhtFileName() {
		return nhtFileName;
	}

	public void setNhtFileName(String nhtFileName) {
		this.nhtFileName = nhtFileName;
	}

	public String getNhtReportName() {
		return nhtReportName;
	}

	public void setNhtReportName(String nhtReportName) {
		this.nhtReportName = nhtReportName;
	}

	public String getNhtStepName() {
		return nhtStepName;
	}

	public void setNhtStepName(String nhtStepName) {
		this.nhtStepName = nhtStepName;
	}

	public String getNhtTruncateProcName() {
		return nhtTruncateProcName;
	}

	public void setNhtTruncateProcName(String nhtTruncateProcName) {
		this.nhtTruncateProcName = nhtTruncateProcName;
	}

	public boolean isRunNHTReport() {
		return runNHTReport;
	}

	public void setRunNHTReport(boolean runNHTReport) {
		this.runNHTReport = runNHTReport;
	}

	public String getNhvrSearchPath() {
		return nhvrSearchPath;
	}

	public void setNhvrSearchPath(String nhvrSearchPath) {
		this.nhvrSearchPath = nhvrSearchPath;
	}

	public String getNhvrIntgType() {
		return nhvrIntgType;
	}

	public void setNhvrIntgType(String nhvrIntgType) {
		this.nhvrIntgType = nhvrIntgType;
	}

	public String getNhvrFileName() {
		return nhvrFileName;
	}

	public void setNhvrFileName(String nhvrFileName) {
		this.nhvrFileName = nhvrFileName;
	}

	public String getNhvrReportName() {
		return nhvrReportName;
	}

	public void setNhvrReportName(String nhvrReportName) {
		this.nhvrReportName = nhvrReportName;
	}

	public String getNhvrStepName() {
		return nhvrStepName;
	}

	public void setNhvrStepName(String nhvrStepName) {
		this.nhvrStepName = nhvrStepName;
	}

	public String getNhvrTruncateProcName() {
		return nhvrTruncateProcName;
	}

	public void setNhvrTruncateProcName(String nhvrTruncateProcName) {
		this.nhvrTruncateProcName = nhvrTruncateProcName;
	}

	public boolean isRunNHVRPath() {
		return runNHVRPath;
	}

	public void setRunNHVRPath(boolean runNHVRPath) {
		this.runNHVRPath = runNHVRPath;
	}

	public String getNhSearchPath() {
		return nhSearchPath;
	}

	public void setNhSearchPath(String nhSearchPath) {
		this.nhSearchPath = nhSearchPath;
	}

	public String getNhIntgType() {
		return nhIntgType;
	}

	public void setNhIntgType(String nhIntgType) {
		this.nhIntgType = nhIntgType;
	}

	public String getNhFileName() {
		return nhFileName;
	}

	public void setNhFileName(String nhFileName) {
		this.nhFileName = nhFileName;
	}

	public String getNhReportName() {
		return nhReportName;
	}

	public void setNhReportName(String nhReportName) {
		this.nhReportName = nhReportName;
	}

	public String getNhStepName() {
		return nhStepName;
	}

	public void setNhStepName(String nhStepName) {
		this.nhStepName = nhStepName;
	}

	public String getNhTruncateProcName() {
		return nhTruncateProcName;
	}

	public void setNhTruncateProcName(String nhTruncateProcName) {
		this.nhTruncateProcName = nhTruncateProcName;
	}

	public boolean isRunNHReport() {
		return runNHReport;
	}

	public void setRunNHReport(boolean runNHReport) {
		this.runNHReport = runNHReport;
	}

	public String getRlSearchPath() {
		return rlSearchPath;
	}

	public void setRlSearchPath(String rlSearchPath) {
		this.rlSearchPath = rlSearchPath;
	}

	public String getRlIntgType() {
		return rlIntgType;
	}

	public void setRlIntgType(String rlIntgType) {
		this.rlIntgType = rlIntgType;
	}

	public String getRlFileName() {
		return rlFileName;
	}

	public void setRlFileName(String rlFileName) {
		this.rlFileName = rlFileName;
	}

	public String getRlReportName() {
		return rlReportName;
	}

	public void setRlReportName(String rlReportName) {
		this.rlReportName = rlReportName;
	}

	public String getRlStepName() {
		return rlStepName;
	}

	public void setRlStepName(String rlStepName) {
		this.rlStepName = rlStepName;
	}

	public String getRlTruncateProcName() {
		return rlTruncateProcName;
	}

	public void setRlTruncateProcName(String rlTruncateProcName) {
		this.rlTruncateProcName = rlTruncateProcName;
	}

	public boolean isRunRLReport() {
		return runRLReport;
	}

	public void setRunRLReport(boolean runRLReport) {
		this.runRLReport = runRLReport;
	}

	public String getRrcSearchPath() {
		return rrcSearchPath;
	}

	public void setRrcSearchPath(String rrcSearchPath) {
		this.rrcSearchPath = rrcSearchPath;
	}

	public String getRrcIntgType() {
		return rrcIntgType;
	}

	public void setRrcIntgType(String rrcIntgType) {
		this.rrcIntgType = rrcIntgType;
	}

	public String getRrcFileName() {
		return rrcFileName;
	}

	public void setRrcFileName(String rrcFileName) {
		this.rrcFileName = rrcFileName;
	}

	public String getRrcReportName() {
		return rrcReportName;
	}

	public void setRrcReportName(String rrcReportName) {
		this.rrcReportName = rrcReportName;
	}

	public String getRrcStepName() {
		return rrcStepName;
	}

	public void setRrcStepName(String rrcStepName) {
		this.rrcStepName = rrcStepName;
	}

	public String getRrcTruncateProcName() {
		return rrcTruncateProcName;
	}

	public void setRrcTruncateProcName(String rrcTruncateProcName) {
		this.rrcTruncateProcName = rrcTruncateProcName;
	}

	public boolean isRunRRCReport() {
		return runRRCReport;
	}

	public void setRunRRCReport(boolean runRRCReport) {
		this.runRRCReport = runRRCReport;
	}

	public String getRvcSearchPath() {
		return rvcSearchPath;
	}

	public void setRvcSearchPath(String rvcSearchPath) {
		this.rvcSearchPath = rvcSearchPath;
	}

	public String getRvcIntgType() {
		return rvcIntgType;
	}

	public void setRvcIntgType(String rvcIntgType) {
		this.rvcIntgType = rvcIntgType;
	}

	public String getRvcFileName() {
		return rvcFileName;
	}

	public void setRvcFileName(String rvcFileName) {
		this.rvcFileName = rvcFileName;
	}

	public String getRvcReportName() {
		return rvcReportName;
	}

	public void setRvcReportName(String rvcReportName) {
		this.rvcReportName = rvcReportName;
	}

	public String getRvcStepName() {
		return rvcStepName;
	}

	public void setRvcStepName(String rvcStepName) {
		this.rvcStepName = rvcStepName;
	}

	public String getRvcTruncateProcName() {
		return rvcTruncateProcName;
	}

	public void setRvcTruncateProcName(String rvcTruncateProcName) {
		this.rvcTruncateProcName = rvcTruncateProcName;
	}

	public boolean isRunRVCReport() {
		return runRVCReport;
	}

	public void setRunRVCReport(boolean runRVCReport) {
		this.runRVCReport = runRVCReport;
	}

	public String getReqSearchPath() {
		return reqSearchPath;
	}

	public void setReqSearchPath(String reqSearchPath) {
		this.reqSearchPath = reqSearchPath;
	}

	public String getReqIntgType() {
		return reqIntgType;
	}

	public void setReqIntgType(String reqIntgType) {
		this.reqIntgType = reqIntgType;
	}

	public String getReqFileName() {
		return reqFileName;
	}

	public void setReqFileName(String reqFileName) {
		this.reqFileName = reqFileName;
	}

	public String getReqReportName() {
		return reqReportName;
	}

	public void setReqReportName(String reqReportName) {
		this.reqReportName = reqReportName;
	}

	public String getReqStepname() {
		return reqStepname;
	}

	public void setReqStepname(String reqStepname) {
		this.reqStepname = reqStepname;
	}

	public String getReqTruncateProcName() {
		return reqTruncateProcName;
	}

	public void setReqTruncateProcName(String reqTruncateProcName) {
		this.reqTruncateProcName = reqTruncateProcName;
	}

	public boolean isRunREQReport() {
		return runREQReport;
	}

	public void setRunREQReport(boolean runREQReport) {
		this.runREQReport = runREQReport;
	}

	public String getVacSearchPath() {
		return vacSearchPath;
	}

	public void setVacSearchPath(String vacSearchPath) {
		this.vacSearchPath = vacSearchPath;
	}

	public String getVacIntgType() {
		return vacIntgType;
	}

	public void setVacIntgType(String vacIntgType) {
		this.vacIntgType = vacIntgType;
	}

	public String getVacFileName() {
		return vacFileName;
	}

	public void setVacFileName(String vacFileName) {
		this.vacFileName = vacFileName;
	}

	public String getVacReportName() {
		return vacReportName;
	}

	public void setVacReportName(String vacReportName) {
		this.vacReportName = vacReportName;
	}

	public String getVacStepName() {
		return vacStepName;
	}

	public void setVacStepName(String vacStepName) {
		this.vacStepName = vacStepName;
	}

	public String getVacTruncateProcName() {
		return vacTruncateProcName;
	}

	public void setVacTruncateProcName(String vacTruncateProcName) {
		this.vacTruncateProcName = vacTruncateProcName;
	}

	public boolean isRunVACReport() {
		return runVACReport;
	}

	public void setRunVACReport(boolean runVACReport) {
		this.runVACReport = runVACReport;
	}

	public String getVeSearchPath() {
		return veSearchPath;
	}

	public void setVeSearchPath(String veSearchPath) {
		this.veSearchPath = veSearchPath;
	}

	public String getVeIntgType() {
		return veIntgType;
	}

	public void setVeIntgType(String veIntgType) {
		this.veIntgType = veIntgType;
	}

	public String getVeFileName() {
		return veFileName;
	}

	public void setVeFileName(String veFileName) {
		this.veFileName = veFileName;
	}

	public String getVeReportName() {
		return veReportName;
	}

	public void setVeReportName(String veReportName) {
		this.veReportName = veReportName;
	}

	public String getVeStepName() {
		return veStepName;
	}

	public void setVeStepName(String veStepName) {
		this.veStepName = veStepName;
	}

	public String getVeTruncateProcName() {
		return veTruncateProcName;
	}

	public void setVeTruncateProcName(String veTruncateProcName) {
		this.veTruncateProcName = veTruncateProcName;
	}

	public boolean isRunVEReport() {
		return runVEReport;
	}

	public void setRunVEReport(boolean runVEReport) {
		this.runVEReport = runVEReport;
	}

	public String getVlSearchPath() {
		return vlSearchPath;
	}

	public void setVlSearchPath(String vlSearchPath) {
		this.vlSearchPath = vlSearchPath;
	}

	public String getVlIntgType() {
		return vlIntgType;
	}

	public void setVlIntgType(String vlIntgType) {
		this.vlIntgType = vlIntgType;
	}

	public String getVlFileName() {
		return vlFileName;
	}

	public void setVlFileName(String vlFileName) {
		this.vlFileName = vlFileName;
	}

	public String getVlReportName() {
		return vlReportName;
	}

	public void setVlReportName(String vlReportName) {
		this.vlReportName = vlReportName;
	}

	public String getVlStepName() {
		return vlStepName;
	}

	public void setVlStepName(String vlStepName) {
		this.vlStepName = vlStepName;
	}

	public String getVlTruncateProcName() {
		return vlTruncateProcName;
	}

	public void setVlTruncateProcName(String vlTruncateProcName) {
		this.vlTruncateProcName = vlTruncateProcName;
	}

	public boolean isRunVLReport() {
		return runVLReport;
	}

	public void setRunVLReport(boolean runVLReport) {
		this.runVLReport = runVLReport;
	}

	public String getVmcoSearchPath() {
		return vmcoSearchPath;
	}

	public void setVmcoSearchPath(String vmcoSearchPath) {
		this.vmcoSearchPath = vmcoSearchPath;
	}

	public String getVmcoIntgType() {
		return vmcoIntgType;
	}

	public void setVmcoIntgType(String vmcoIntgType) {
		this.vmcoIntgType = vmcoIntgType;
	}

	public String getVmcoFileName() {
		return vmcoFileName;
	}

	public void setVmcoFileName(String vmcoFileName) {
		this.vmcoFileName = vmcoFileName;
	}

	public String getVmcoReportName() {
		return vmcoReportName;
	}

	public void setVmcoReportName(String vmcoReportName) {
		this.vmcoReportName = vmcoReportName;
	}

	public String getVmcoStepName() {
		return vmcoStepName;
	}

	public void setVmcoStepName(String vmcoStepName) {
		this.vmcoStepName = vmcoStepName;
	}

	public String getVmcoTruncateProcName() {
		return vmcoTruncateProcName;
	}

	public void setVmcoTruncateProcName(String vmcoTruncateProcName) {
		this.vmcoTruncateProcName = vmcoTruncateProcName;
	}

	public boolean isRunVMCOReport() {
		return runVMCOReport;
	}

	public void setRunVMCOReport(boolean runVMCOReport) {
		this.runVMCOReport = runVMCOReport;
	}

	public String getVrcSearchPath() {
		return vrcSearchPath;
	}

	public void setVrcSearchPath(String vrcSearchPath) {
		this.vrcSearchPath = vrcSearchPath;
	}

	public String getVrcIntgType() {
		return vrcIntgType;
	}

	public void setVrcIntgType(String vrcIntgType) {
		this.vrcIntgType = vrcIntgType;
	}

	public String getVrcFileName() {
		return vrcFileName;
	}

	public void setVrcFileName(String vrcFileName) {
		this.vrcFileName = vrcFileName;
	}

	public String getVrcReportName() {
		return vrcReportName;
	}

	public void setVrcReportName(String vrcReportName) {
		this.vrcReportName = vrcReportName;
	}

	public String getVrcStepName() {
		return vrcStepName;
	}

	public void setVrcStepName(String vrcStepName) {
		this.vrcStepName = vrcStepName;
	}

	public String getVrcTruncateProcName() {
		return vrcTruncateProcName;
	}

	public void setVrcTruncateProcName(String vrcTruncateProcName) {
		this.vrcTruncateProcName = vrcTruncateProcName;
	}

	public boolean isRunVRCReport() {
		return runVRCReport;
	}

	public void setRunVRCReport(boolean runVRCReport) {
		this.runVRCReport = runVRCReport;
	}

}
