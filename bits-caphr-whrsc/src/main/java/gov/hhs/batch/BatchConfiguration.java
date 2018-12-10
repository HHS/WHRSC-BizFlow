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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

import gov.hhs.batch.bits.NEOStatusProcessor;
import gov.hhs.batch.bits.RecordCountListener;
import gov.hhs.batch.bits.TableNEOStatusTarget;
import gov.hhs.batch.bits.TruncateTaskletStep;
import gov.hhs.batch.bits.ViewNEOStatusSource;
import gov.hhs.batch.caphr.CapHRTasklet;

@Configuration
@EnableBatchProcessing
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
	private BitsJobListener jobCompletionlistener;
	
	@Autowired
	private RecordCountListener recordCountlistener;
	
	@Autowired
	private TruncateTaskletStep truncateTaskletStep;
	
	@Autowired
	private CapHRTasklet capHRDataTasklet;
	
	@Autowired
	private StepListener stepsListener;
	
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
	 * Job - BitsInterfaceJob
	 */
		@Bean
		public Job BitsInterfaceJob() {

			log.info("Inside BitsInterfaceJob()");
			return jobBuilderFactory.get("BitsInterfaceJob")
					.incrementer(new RunIdIncrementer())
					.listener(jobCompletionlistener)
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
			log.info("Inside importNEOStatusStep()");
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

		log.info("Source Database: "+sourceDataSource);
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
		log.info("Target Database: "+targetDataSource);
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
		log.info("Inside BitsInterfaceJob()");
		return jobBuilderFactory.get("CapHRInterfaceJob")
				.incrementer(new RunIdIncrementer())
				.listener(jobCompletionlistener)
				.preventRestart()
				.start(importCapHRDataStep())//.on("*")
				.build();
	}
	
	
	/*
	 * Step - importCapHRDataStep - Import CapHR Data
	 */

	@Bean
	public Step importCapHRDataStep() {
		log.info("Inside importCapHRDataStep()");
		return stepBuilderFactory.get("importCapHRDataStep")
				.tasklet(capHRDataTasklet)
				.listener(stepsListener)
				.build();
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

