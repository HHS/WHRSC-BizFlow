package gov.hhs.induction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Component
public class SOAPConnector extends WebServiceGatewaySupport {

	private static final Log LOG = LogFactory.getLog(SOAPConnector.class);

	@Autowired
	private WebServiceTemplate webServiceTemplate;

	@Autowired
	private WebServiceTemplate webServiceTestTemplate;

	public Object callWebService(Object request){

		LOG.info("Sending Web Service Request using WebServiceTemplate...");
		return webServiceTemplate.marshalSendAndReceive(request);

	}

	public Object callWebServiceTest(Object request){

		LOG.info("Testing Web Service Request using WebServiceTestTemplate...");
		return webServiceTestTemplate.marshalSendAndReceive(request);
	}

}