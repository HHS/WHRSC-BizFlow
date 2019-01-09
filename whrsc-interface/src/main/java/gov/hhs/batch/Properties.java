package gov.hhs.batch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Properties {

	//Interface Name
	@Value("${bits.interface.name}") 
	private String bitsInterfacename;
	@Value("${caphr.interface.name}")
	private String caphrInterfaceName;

	//Log Files Name
	@Value("${bits.log.name}")
	private String bitsLogfile;
	@Value("${caphr.log.name}")
	private String caphrLogfile;

	//BITS Queries
	@Value("${query.bits.list.source}")
	private String queryBitsListSource;
	@Value("${query.bits.insert.target}")
	private String queryBitsInsertTarget;
	@Value("${query.bits.delete.target}")
	private String queryBitsDeleteTarget;

	//CapHR PL/SQL Stored Function properties
	@Value("${plsql.caphr.catalog}")
	private String plsqlCaphrCatalog;
	@Value("${plsql.caphr.schema}")
	private String plsqlCaphrSchema;
	@Value("${plsql.caphr.function}")
	private String plsqlCaphrFunction;

	//Cron Schedule of Jobs
	@Value("${bits.cron.sched}")
	private String bitsCronSchedule;
	@Value("${caphr.cron.sched}")
	private String caphrCronSchedule;

	//Run Batch Jobs-True or False
	@Value("${run.bits.job}")
	private String runBitsJob;
	@Value("${run.caphr.job}")
	private String runCaphrJob;

	//Email Properties
	@Value("${send.email.notification}")
	private String sendEmailNotification;			

	@Value("${email.subject}")
	private String emailSubjectTemplate;
	@Value("${email.message}")
	private String emailMessageTemplate;
	@Value("${email.bits.message}")
	private String emailBitsMessageTemplate;
	@Value("${email.html.message}")
	private String emailHTMLMessageTemplate;
	@Value("${emailid.from}")
	private String from;
	@Value("${emailid.to}")
	private String[] to;
	
	public String getBitsInterfacename() {
		return bitsInterfacename;
	}
	public String getCaphrInterfaceName() {
		return caphrInterfaceName;
	}
	public String getBitsLogfile() {
		return bitsLogfile;
	}
	public String getCaphrLogfile() {
		return caphrLogfile;
	}
	public String getQueryBitsListSource() {
		return queryBitsListSource;
	}
	public String getQueryBitsInsertTarget() {
		return queryBitsInsertTarget;
	}
	public String getQueryBitsDeleteTarget() {
		return queryBitsDeleteTarget;
	}
	public String getPlsqlCaphrCatalog() {
		return plsqlCaphrCatalog;
	}
	public String getPlsqlCaphrSchema() {
		return plsqlCaphrSchema;
	}
	public String getPlsqlCaphrFunction() {
		return plsqlCaphrFunction;
	}
	public String getBitsCronSchedule() {
		return bitsCronSchedule;
	}
	public String getCaphrCronSchedule() {
		return caphrCronSchedule;
	}
	public String getRunBitsJob() {
		return runBitsJob;
	}
	public String getRunCaphrJob() {
		return runCaphrJob;
	}
	public String getSendEmailNotification() {
		return sendEmailNotification;
	}
	public String getEmailSubjectTemplate() {
		return emailSubjectTemplate;
	}
	public String getEmailMessageTemplate() {
		return emailMessageTemplate;
	}
	public String getEmailBitsMessageTemplate() {
		return emailBitsMessageTemplate;
	}
	public String getEmailHTMLMessageTemplate() {
		return emailHTMLMessageTemplate;
	}
	public String getFrom() {
		return from;
	}
	public String[] getTo() {
		return to;
	}


}
