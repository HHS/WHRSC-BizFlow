package gov.hhs.induction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;


@Configuration
public class Config {

	private static final Log LOG = LogFactory.getLog(Config.class);

	@Value("${induction.url}")
	private String inductionURL;

	@Value("${ssl.key-store-type}")
	private String keyStoreType;
	
	@Value("${ssl.key-store}")
	private String keyStore;

	@Value("${ssl.key-store-password}")
	private String keyStorePassword;

	@Value("${ssl.trust-store}")
	private String trustStore;

	@Value("${ssl.trust-store-password}")
	private String trustStorePassword;

	@Value("${ssl.cert.path}")
	private String certPath;

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("gov.hhs.induction.schemas");
		return marshaller;
	}

	@Bean
	public WebServiceTemplate webServiceTestTemplate() throws Exception {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setMarshaller(marshaller());
		webServiceTemplate.setUnmarshaller(marshaller());
		webServiceTemplate.setDefaultUri(inductionURL);

		return webServiceTemplate;
	}

	@Bean
	public WebServiceTemplate webServiceTemplate() {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setMarshaller(marshaller());
		webServiceTemplate.setUnmarshaller(marshaller());
		webServiceTemplate.setDefaultUri(inductionURL);
		webServiceTemplate.setMessageSender(httpComponentsMessageSender());
		return webServiceTemplate;
	}

	@Bean
	public HttpComponentsMessageSender httpComponentsMessageSender() {
		HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();


		KeyStore identityKeyStore;
		try {
			identityKeyStore = KeyStore.getInstance(keyStoreType);
			FileInputStream identityKeyStoreFile = new FileInputStream(new File(certPath + File.separator + keyStore));
			identityKeyStore.load(identityKeyStoreFile, keyStorePassword.toCharArray());

			KeyStore trustKeyStore = KeyStore.getInstance(keyStoreType);
			FileInputStream trustKeyStoreFile = new FileInputStream(new File(certPath + File.separator + trustStore));
			trustKeyStore.load(trustKeyStoreFile, trustStorePassword.toCharArray());

			SSLContextBuilder sslContextBuilder = SSLContexts.custom();			
			sslContextBuilder = SSLContexts.custom().loadKeyMaterial(identityKeyStore, keyStorePassword.toCharArray());			
			sslContextBuilder.loadTrustMaterial(trustKeyStore, new TrustSelfSignedStrategy());

			SSLContext sslContext =  sslContextBuilder.build();

			SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
					new String[]{"TLSv1.2", "TLSv1.1"},
					null,
					NoopHostnameVerifier.INSTANCE);
			CloseableHttpClient client = HttpClients.custom()
					.setSSLSocketFactory(sslConnectionSocketFactory)
					.addInterceptorFirst(new ContentLengthHeaderRemover())
					.build();
			httpComponentsMessageSender.setHttpClient(client);

		} catch (FileNotFoundException e) {
			LOG.error("Cannot connect to SCMS Server due to " + e.getClass().getSimpleName());
			LOG.error("Exception Stack Trace :: ", e);
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | UnrecoverableKeyException | KeyManagementException e) {
			LOG.error("Cannot connect to SCMS Server due to " + e.getClass().getSimpleName());
			LOG.error("Exception Stack Trace :: ", e);
		} catch (Exception e) {
			LOG.error("Cannot connect to SCMS Server due to " + e.getClass().getSimpleName());
			LOG.error("Exception Stack Trace :: ", e);
		}
		return httpComponentsMessageSender;
	}

	private static class ContentLengthHeaderRemover implements HttpRequestInterceptor {
		@Override
		public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
			request.removeHeaders(HTTP.CONTENT_LEN);
		}

	}

}
