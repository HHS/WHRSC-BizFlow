package gov.hhs.batch;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

import gov.hhs.batch.bits.BITSJobListener;
import gov.hhs.batch.bits.NEOStatusProcessor;
import gov.hhs.batch.bits.RecordCountListener;
import gov.hhs.batch.bits.TableNEOStatusTarget;
import gov.hhs.batch.bits.TruncateTaskletStep;
import gov.hhs.batch.bits.ViewNEOStatusSource;
import gov.hhs.batch.caphr.CapHRJobListener;
import gov.hhs.batch.caphr.CapHRTasklet;
import gov.hhs.batch.usas.USASJobListener;
import gov.hhs.batch.usas.USASStepsListener;
import gov.hhs.batch.usas.WHRSCReportTasklet;
import gov.hhs.batch.usas.model.ApplicantNotifications;
import gov.hhs.batch.usas.model.CertificateLocations;
import gov.hhs.batch.usas.model.Certificates;
import gov.hhs.batch.usas.model.NewHireForms;
import gov.hhs.batch.usas.model.NewHireOnboardingDocuments;
import gov.hhs.batch.usas.model.NewHireTask;
import gov.hhs.batch.usas.model.NewHireVacancyRequest;
import gov.hhs.batch.usas.model.NewHires;
import gov.hhs.batch.usas.model.PermissionProfiles;
import gov.hhs.batch.usas.model.RequestLocations;
import gov.hhs.batch.usas.model.RequestRatingCombinations;
import gov.hhs.batch.usas.model.RequestVacancyCombinations;
import gov.hhs.batch.usas.model.Requests;
import gov.hhs.batch.usas.model.Vacancies;
import gov.hhs.batch.usas.model.VacancyEligibilities;
import gov.hhs.batch.usas.model.VacancyLocations;
import gov.hhs.batch.usas.model.VacancyMissionCriticalOccupations;
import gov.hhs.batch.usas.model.VacancyRatingCombinations;

@Configuration
@EnableBatchProcessing
@PropertySources({
	@PropertySource("classpath:application.properties"),
	@PropertySource("classpath:whrsc-report.properties")
})
public class BatchConfiguration {

	private static final Logger log = LoggerFactory.getLogger(BatchConfiguration.class);
	
	private static int recordCount;	
	
	@Autowired
	private Properties properties;

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("sourceDb")
	private DataSource sourceDataSource;

	@Autowired
	@Qualifier("targetDb")
	private DataSource targetDataSource;

	@Autowired
	private BITSJobListener bitsJobListener;
	
	@Autowired
	private RecordCountListener recordCountlistener;
	
	@Autowired
	private CapHRJobListener caphrJobListener;
	
	@Autowired
	private USASJobListener usasJobListener;	
	
	@Autowired
	private USASStepsListener usasStepsListener;

	
	@Autowired
	private TruncateTaskletStep truncateTaskletStep;
	
	@Autowired
	private CapHRTasklet capHRDataTasklet;
	
	@Autowired
	private StepListener stepsListener;
	
	@Autowired
	private ApplicantNotifications applicantNotifications;
	
	@Autowired
	private CertificateLocations certificateLocations;
	
	@Autowired
	private Certificates certificates;
	
	@Autowired
	private PermissionProfiles permissionProfiles;
	
	@Autowired
	private NewHireForms newHireForms;	
	
	@Autowired
	private NewHireOnboardingDocuments newHireOnboardingDocuments;	
	
	@Autowired
	private NewHireTask newHireTask;	
	
	@Autowired
	private NewHireVacancyRequest newHireVacancyRequest;	
	
	@Autowired
	private NewHires newHires;	
	
	@Autowired
	private RequestLocations requestLocations;

	@Autowired
	private RequestRatingCombinations requestRatingCombinations;

	@Autowired
	private RequestVacancyCombinations requestVacancyCombinations;

	@Autowired
	private Requests requests;

	@Autowired
	private Vacancies vacancies;

	@Autowired
	private VacancyEligibilities vacancyEligibilities;

	@Autowired
	private VacancyLocations vacancyLocations;

	@Autowired
	private VacancyMissionCriticalOccupations vacancyMissionCriticalOccupations;

	@Autowired
	private VacancyRatingCombinations vacancyRatingCombinations;
	
	public static void setRecordCount(int count){
		recordCount = count;
	}
	
	public static int getRecordCount(){
		return recordCount;
	}
	
	
	/*
	 * Schedule the BITS Interface Job 
	 */
	@Scheduled(cron = "${bits.cron.sched}")
	public void runBITSJob() {
		try {
			if(Boolean.valueOf(properties.getRunBitsJob())) {
				JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
				getJobLauncher().run(BitsInterfaceJob(), jobParameters);	
			} else {
				log.info(properties.getBitsInterfacename() + " is turned off.");
			}
		} catch (Exception e) {
			log.info(e.getMessage() + "::" + e.getCause());
		}
	}
	
	/*
	 * Schedule the CapHR Interface Job 
	 */
	@Scheduled(cron = "${caphr.cron.sched}")
	public void runCapHRJob() {
		try {
			if(Boolean.valueOf(properties.getRunCaphrJob())) {
				JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
				getJobLauncher().run(CapHRInterfaceJob(), jobParameters);
			} else {
				log.info(properties.getCaphrInterfaceName() + " is turned off.");
			}
		} catch (Exception e) {
			log.info(e.getMessage() + "::" + e.getCause());
		}
	}
	
	/*
	 * Schedule the USAS Interface Job 
	 */
	@Scheduled(cron = "${usas.cron.sched}")
	public void runUSASJob() {
		try {
			if(Boolean.valueOf(properties.getRunUsasJob())) {
				JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
				getJobLauncher().run(USASInterfaceJob(), jobParameters);
			} else {
				log.info(properties.getUsasInterfaceName() + " is turned off.");
			}
		} catch (Exception e) {
			log.info(e.getMessage() + "::" + e.getCause());
		}
	}

	
	
	/*
	 * Job - BitsInterfaceJob
	 */
		@Bean
		public Job BitsInterfaceJob() {
			return jobBuilderFactory.get("BitsInterfaceJob")
					.incrementer(new RunIdIncrementer())
					.listener(bitsJobListener)
					.start(truncateBITSTargetTableStep())
					.next(importNEOStatusStep())
					.build();
		}
		
		/*
		 * Step - truncateTargetTableStep - Truncate target table
		 * before importing data
		 */
		@Bean
	    public Step truncateBITSTargetTableStep() {
	        return stepBuilderFactory.get("truncateBITSTargetTableStep")
	        		.tasklet(truncateTaskletStep)
	        		.listener(stepsListener)
	                .build();
	    }

		/*
		 * Step - importNEOStatusStep - Import NEO Status
		 */
		@Bean
		public Step importNEOStatusStep() {
			return stepBuilderFactory.get("importNEOStatusStep")
					.<ViewNEOStatusSource, TableNEOStatusTarget>chunk(10)
					.reader(importReader())
					.processor(importProcessor())
					.writer(importWriter())
					.listener(recordCountlistener)
					.build();
		}
		


	// Reader, Processor, Writer for importPersonDirectoryJob

	@Bean
	public ItemReader<ViewNEOStatusSource> importReader() {
		JdbcCursorItemReader<ViewNEOStatusSource> databaseReader = new JdbcCursorItemReader<ViewNEOStatusSource>();
		databaseReader.setDataSource(sourceDataSource);
		databaseReader.setSql(properties.getQueryBitsListSource());
		databaseReader.setRowMapper(new BeanPropertyRowMapper<ViewNEOStatusSource>(ViewNEOStatusSource.class));

		return databaseReader;

	}

	@Bean
	public ItemProcessor<ViewNEOStatusSource, TableNEOStatusTarget> importProcessor() {
		return new NEOStatusProcessor();
	}

	@Bean
	public ItemWriter<TableNEOStatusTarget> importWriter() {
		JdbcBatchItemWriter<TableNEOStatusTarget> databaseWriter = new JdbcBatchItemWriter<TableNEOStatusTarget>();
		databaseWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<TableNEOStatusTarget>());
		databaseWriter.setSql(properties.getQueryBitsInsertTarget());
		databaseWriter.setDataSource(targetDataSource);
		return databaseWriter;
	}
	

	/*
	 * Job - capHRInterfaceJob
	 */
	@Bean
	public Job CapHRInterfaceJob() {
		return jobBuilderFactory.get("CapHRInterfaceJob")
				.incrementer(new RunIdIncrementer())
				.listener(caphrJobListener)
				.preventRestart()
				.start(importCapHRDataStep())//.on("*")
				.build();
	}
	
	
	/*
	 * Step - importCapHRDataStep - Import CapHR Data
	 */

	@Bean
	public Step importCapHRDataStep() {
		return stepBuilderFactory.get("importCapHRDataStep")
				.tasklet(capHRDataTasklet)
				.listener(stepsListener)
				.build();
	}


	/*
	 * Job - USASInterfaceJob
	 */
	@Bean
	public Job USASInterfaceJob() throws Exception {
		return jobBuilderFactory.get("USASInterfaceJob")
				.incrementer(new RunIdIncrementer())
				.listener(usasJobListener)
				.start(executeAppNotifReportStep())
				.next(executeCertLocReportStep())
				.next(executeCertsReportStep())
				.next(executePermReportStep())
				.next(executeNewHireFormsReportStep())
				.next(executeNewHireOnDocsReportStep())
				.next(executeNewHireTaskReportStep())
				.next(executeNewHireVacReqReportStep())
				.next(executeNewHiresReportStep())
				.next(executeReqLocReportStep())
				.next(executeReqRatngCombReportStep())
				.next(executeReqVacncyCombReportStep())
				.next(executeRequestsReportStep())
				.next(executeVacncyReportStep())
				.next(executeVacncyEligReportStep())
				.next(executeVacncyLocReportStep())
				.next(executeVacncyMissnCritclReportStep())
				.next(executeVacncyRatngCombReportStep())
				.build();

	}
	
	//Applicant Notifications Report Step & Tasklet
		@Bean
		public Step executeAppNotifReportStep() throws Exception {
			return stepBuilderFactory.get("executeAppNotifReportStep")
					.listener(usasStepsListener)
					.tasklet(anTasklet())
					.build();
		}

		@Bean
		public WHRSCReportTasklet anTasklet() {
			WHRSCReportTasklet anTasklet = new WHRSCReportTasklet();
			anTasklet.setReport(applicantNotifications);
			return anTasklet;
		}

		//Certificate Locations Report Step & Tasklet
		@Bean
		public Step executeCertLocReportStep() throws Exception {
			return stepBuilderFactory.get("executeCertLocReportStep")
					.listener(usasStepsListener)
					.tasklet(clTasklet())
					.build();
		}

		@Bean
		public WHRSCReportTasklet clTasklet() {
			WHRSCReportTasklet clTasklet = new WHRSCReportTasklet();
			clTasklet.setReport(certificateLocations);
			return clTasklet;
		}
		
		//Certificates Report Step & Tasklet
		@Bean
		public Step executeCertsReportStep() throws Exception {
			return stepBuilderFactory.get("executeCertsReportStep")
					.listener(usasStepsListener)
					.tasklet(cTasklet())
					.build();
		}

		@Bean
		public WHRSCReportTasklet cTasklet() {
			WHRSCReportTasklet cTasklet = new WHRSCReportTasklet();
			cTasklet.setReport(certificates);
			return cTasklet;
		}
		
		//Permission Profiles Report Step & Tasklet
		@Bean
		public Step executePermReportStep() throws Exception {
			return stepBuilderFactory.get("executePermReportStep")
					.listener(usasStepsListener)
					.tasklet(ppTasklet())
					.build();
		}

		@Bean
		public WHRSCReportTasklet ppTasklet() {
			WHRSCReportTasklet ppTasklet = new WHRSCReportTasklet();
			ppTasklet.setReport(permissionProfiles);
			return ppTasklet;
		}

		//New Hire Forms Report Step & Tasklet
		@Bean
		public Step executeNewHireFormsReportStep() throws Exception {
			return stepBuilderFactory.get("executeNewHireFormsReportStep")
					.listener(usasStepsListener)
					.tasklet(nhfTasklet())
					.build();
		}

		@Bean
		public WHRSCReportTasklet nhfTasklet() {
			WHRSCReportTasklet nhfTasklet = new WHRSCReportTasklet();
			nhfTasklet.setReport(newHireForms);
			return nhfTasklet;
		}

		//New Hire Onboarding Documents Report Step & Tasklet
		@Bean
		public Step executeNewHireOnDocsReportStep() throws Exception {
			return stepBuilderFactory.get("executeNewHireOnDocsReportStep")
					.listener(usasStepsListener)
					.tasklet(nhodTasklet())
					.build();
		}

		@Bean
		public WHRSCReportTasklet nhodTasklet() {
			WHRSCReportTasklet nhodTasklet = new WHRSCReportTasklet();
			nhodTasklet.setReport(newHireOnboardingDocuments);
			return nhodTasklet;
		}

		//New Hire Task Report Step & Tasklet
		@Bean
		public Step executeNewHireTaskReportStep() throws Exception {
			return stepBuilderFactory.get("executeNewHireTaskReportStep")
					.listener(usasStepsListener)
					.tasklet(nhtTasklet())
					.build();
		}

		@Bean
		public WHRSCReportTasklet nhtTasklet() {
			WHRSCReportTasklet nhtTasklet = new WHRSCReportTasklet();
			nhtTasklet.setReport(newHireTask);
			return nhtTasklet;
		}

		//New Hire Vacancy Request Report Step & Tasklet
		@Bean
		public Step executeNewHireVacReqReportStep() throws Exception {
			return stepBuilderFactory.get("executeNewHireVacReqReportStep")
					.listener(usasStepsListener)
					.tasklet(nhvrTasklet())
					.build();
		}

		@Bean
		public WHRSCReportTasklet nhvrTasklet() {
			WHRSCReportTasklet nhvrTasklet = new WHRSCReportTasklet();
			nhvrTasklet.setReport(newHireVacancyRequest);
			return nhvrTasklet;
		}

		//New Hires Report Step & Tasklet
		@Bean
		public Step executeNewHiresReportStep() throws Exception {
			return stepBuilderFactory.get("executeNewHiresReportStep")
					.listener(usasStepsListener)
					.tasklet(nhTasklet())
					.build();
		}

		@Bean
		public WHRSCReportTasklet nhTasklet() {
			WHRSCReportTasklet nhTasklet = new WHRSCReportTasklet();
			nhTasklet.setReport(newHires);
			return nhTasklet;
		}
		
		//Request Locations Report Step & Tasklet
		@Bean
		public Step executeReqLocReportStep() throws Exception {
			return stepBuilderFactory.get("executeReqLocReportStep")
					.listener(usasStepsListener)
					.tasklet(rlTasklet())
					.build();
		}

		@Bean
		public WHRSCReportTasklet rlTasklet() {
			WHRSCReportTasklet rlTasklet = new WHRSCReportTasklet();
			rlTasklet.setReport(requestLocations);
			return rlTasklet;
		}

		//Request Rating Combinations Report Step & Tasklet
		@Bean
		public Step executeReqRatngCombReportStep() throws Exception {
			return stepBuilderFactory.get("executeReqRatngCombReportStep")
					.listener(usasStepsListener)
					.tasklet(rrcTasklet())
					.build();
		}

		@Bean
		public WHRSCReportTasklet rrcTasklet() {
			WHRSCReportTasklet rrcTasklet = new WHRSCReportTasklet();
			rrcTasklet.setReport(requestRatingCombinations);
			return rrcTasklet;
		}
		
		//Request Vacancy Combinations Report Step & Tasklet
		@Bean
		public Step executeReqVacncyCombReportStep() throws Exception {
			return stepBuilderFactory.get("executeReqVacncyCombReportStep")
					.listener(usasStepsListener)
					.tasklet(rvcTasklet())
					.build();
		}

		@Bean
		public WHRSCReportTasklet rvcTasklet() {
			WHRSCReportTasklet rvcTasklet = new WHRSCReportTasklet();
			rvcTasklet.setReport(requestVacancyCombinations);
			return rvcTasklet;
		}
		
		//Requests Report Step & Tasklet
		@Bean
		public Step executeRequestsReportStep() throws Exception {
			return stepBuilderFactory.get("executeRequestsReportStep")
					.listener(usasStepsListener)
					.tasklet(reqTasklet())
					.build();
		}

		@Bean
		public WHRSCReportTasklet reqTasklet() {
			WHRSCReportTasklet reqTasklet = new WHRSCReportTasklet();
			reqTasklet.setReport(requests);
			return reqTasklet;
		}

		//Vacancy Report Step & Tasklet
		@Bean
		public Step executeVacncyReportStep() throws Exception {
			return stepBuilderFactory.get("executeVacncyReportStep")
					.listener(usasStepsListener)
					.tasklet(vacTasklet())
					.build();
		}

		@Bean
		public WHRSCReportTasklet vacTasklet() {
			WHRSCReportTasklet vacTasklet = new WHRSCReportTasklet();
			vacTasklet.setReport(vacancies);
			return vacTasklet;
		}

		//Vacancy Eligibilities Report Step & Tasklet
		@Bean
		public Step executeVacncyEligReportStep() throws Exception {
			return stepBuilderFactory.get("executeVacncyEligReportStep")
					.listener(usasStepsListener)
					.tasklet(veTasklet())
					.build();
		}

		@Bean
		public WHRSCReportTasklet veTasklet() {
			WHRSCReportTasklet veTasklet = new WHRSCReportTasklet();
			veTasklet.setReport(vacancyEligibilities);
			return veTasklet;
		}

		//Vacancy Locations Report Step & Tasklet
		@Bean
		public Step executeVacncyLocReportStep() throws Exception {
			return stepBuilderFactory.get("executeVacncyLocReportStep")
					.listener(usasStepsListener)
					.tasklet(vlTasklet())
					.build();
		}

		@Bean
		public WHRSCReportTasklet vlTasklet() {
			WHRSCReportTasklet vlTasklet = new WHRSCReportTasklet();
			vlTasklet.setReport(vacancyLocations);
			return vlTasklet;
		}

		//Vacancy Mission Critical Occupations Report Step & Tasklet
		@Bean
		public Step executeVacncyMissnCritclReportStep() throws Exception {
			return stepBuilderFactory.get("executeVacncyMissnCritclReportStep")
					.listener(usasStepsListener)
					.tasklet(vmcoTasklet())
					.build();
		}

		@Bean
		public WHRSCReportTasklet vmcoTasklet() {
			WHRSCReportTasklet vmcoTasklet = new WHRSCReportTasklet();
			vmcoTasklet.setReport(vacancyMissionCriticalOccupations);
			return vmcoTasklet;
		}

		//Vacancy Rating Combinations Report Step & Tasklet
		@Bean
		public Step executeVacncyRatngCombReportStep() throws Exception {
			return stepBuilderFactory.get("executeVacncyRatngCombReportStep")
					.listener(usasStepsListener)
					.tasklet(vrcTasklet())
					.build();
		}

		@Bean
		public WHRSCReportTasklet vrcTasklet() {
			WHRSCReportTasklet vrcTasklet = new WHRSCReportTasklet();
			vrcTasklet.setReport(vacancyRatingCombinations);
			return vrcTasklet;
		}
	
	//added new batch configurer for using multiple datasource
	@Bean
	BatchConfigurer configurer(DataSource targetDb){
		return new DefaultBatchConfigurer(targetDb);
	}


	@Bean
	public JdbcTemplate sourceJdbcTemplate(DataSource sourceDb) {
		return new JdbcTemplate(sourceDb);
	}

	@Bean
	@Primary
	public JdbcTemplate targetJdbcTemplate(DataSource targetDb) {
		return new JdbcTemplate(targetDb);
	}	
	
	//***To resolve error: Can't serialize access for this transaction***
	private JobRepository getJobRepository() throws Exception {
		JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
		factory.setDataSource(targetDataSource);
		factory.setTransactionManager(getTransactionManager());
		factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");//Added to get rid of ->Caused by: java.sql.SQLException: ORA-08177: can't serialize access for this transaction
		factory.afterPropertiesSet();
		return (JobRepository) factory.getObject();
	}

	private PlatformTransactionManager getTransactionManager() {
		return new ResourcelessTransactionManager();
	}

	public JobLauncher getJobLauncher() throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(getJobRepository());
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
	}


}

