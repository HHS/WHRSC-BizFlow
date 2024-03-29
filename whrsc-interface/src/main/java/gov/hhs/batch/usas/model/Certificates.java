package gov.hhs.batch.usas.model;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:whrsc-report.properties")
public class Certificates extends WHRSCDatastoreReport{
	
	@Value("${c.search.path}") private String searchPath;
	@Value("${c.intg.type}") private String intgType;
	@Value("${c.file.name}") private String fileName;
	@Value("${c.report.name}") private String reportName;
	@Value("${c.truncate}") private String spTruncate;
	@Value("${c.run.report}") private boolean runReport;

	public Certificates() {
	}
	
	@PostConstruct
	public void construct() {
		super.setSearchPath(searchPath);
		super.setIntgType(intgType);
		super.setFileName(fileName);
		super.setReportName(reportName);		
		super.setSpTruncate(spTruncate);
		super.setRunReport(runReport);
	}
}
