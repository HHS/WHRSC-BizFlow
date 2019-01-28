package gov.hhs.batch.bits;



import javax.sql.DataSource;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gov.hhs.batch.Properties;

@Component
public class TruncateTaskletStep implements Tasklet{

	@Autowired
	private DataSource targetDataSource;
	
	@Autowired
	Properties properties;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		targetDataSource.getConnection().createStatement().execute(properties.getQueryBitsDeleteTarget());
		
		return RepeatStatus.FINISHED;
	}
	
}
