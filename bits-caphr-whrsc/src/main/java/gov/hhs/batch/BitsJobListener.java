package gov.hhs.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BitsJobListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(BitsJobListener.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	private Properties properties;
	@Autowired
	private EmailService emailService;

	private String jobName;
	private String exitStatus;	
	private String exitDescription;

	@Value("${send.email.notification}")
	private boolean sendEmailNotification;


	@Autowired
	public BitsJobListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	//Callback before a job executes.
	@Override
	public void beforeJob(JobExecution jobExecution) {
		this.jobName = jobExecution.getJobInstance().getJobName();
		log.info("JOB " + this.jobName +" STARTED. Execution Status: "+ jobExecution.getStatus());
	}

	//Callback after completion of a job. Called after both both successful and failed executions.
	@Override
	public void afterJob(JobExecution jobExecution) {
		String interfaceName = properties.getBitsInterfacename();
		this.jobName = jobExecution.getJobInstance().getJobName();
		this.exitStatus = jobExecution.getExitStatus().getExitCode().toString().replace("exitCode=", " "); 	
		this.exitDescription = jobExecution.getExitStatus().getExitDescription().toString().replace("exitDescription=", " ");

		int recordCount = BatchConfiguration.getRecordCount();

		log.info(interfaceName + ": " + jobName + ": " + exitStatus + ": " + exitDescription);
		if (Boolean.valueOf(properties.getSendEmailNotification())) {
			emailService.sendBitsEmail(interfaceName, jobName, exitStatus, exitDescription, recordCount);
		}
	}




}
