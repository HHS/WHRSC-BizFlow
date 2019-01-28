package gov.hhs.batch.bits;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

import gov.hhs.batch.BatchConfiguration;

@Component
public class RecordCountListener  implements ChunkListener{

	private static int recordCount;

	public static int getRecordCount() {
		return recordCount;
	}
	
	@Override
	public void beforeChunk(ChunkContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterChunk(ChunkContext context) {
		recordCount = context.getStepContext().getStepExecution().getReadCount();
		BatchConfiguration.setRecordCount(recordCount);
		//jobCompletionlistener.setRecordCount(count);
	}

	@Override
	public void afterChunkError(ChunkContext context) {
		// TODO Auto-generated method stub

	}

}
