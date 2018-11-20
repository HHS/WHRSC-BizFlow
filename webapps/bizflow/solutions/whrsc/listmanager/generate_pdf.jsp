<%@ page import="com.hs.bf.web.beans.HWFilter" %>
<%@ page import="com.hs.bf.web.beans.HWSession" %>
<%@ page import="com.hs.bf.web.beans.HWSessionInfo" %>
<%@ page import="com.hs.bf.web.xmlrs.XMLResultSet" %>
<%@ page import="com.hs.bf.web.xmlrs.XMLResultSetImpl" %>
<%@ page import="com.hs.bf.wf.jo.HWAttachment" %>
<%@ page import="com.hs.bf.wf.jo.HWAttachments" %>
<%@ page import="com.hs.bf.wf.jo.HWAttachmentsImpl" %>
<%@ page import="com.hs.frmwk.json.JSONObject" %>
<%@ page import="com.hs.ja.web.servlet.ServletUtil" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.Properties" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/bizflow.tld" prefix="bf" %>

<jsp:useBean id="bizflowProps" class="com.hs.bf.web.props.Properties" scope="application"/>
<jsp:useBean id="hwSessionFactory" class="com.hs.bf.web.beans.HWSessionFactory" scope="application"/>
<jsp:useBean id="hwSessionInfo" class="com.hs.bf.web.beans.HWSessionInfo" scope="session"/>
<jsp:useBean id="hwiniSystem" class="com.hs.frmwk.common.ini.IniFile" scope="application"/>

<bf:parameter id="loginid" name="loginid" value="" valuePattern="NoRiskyValue"/><%--madatory--%>
<bf:parameter id="processid" name="pid" value="" valuePattern="NoRiskyValue"/><%--madatory--%>
<bf:parameter id="activityid" name="aseq" value="" valuePattern="NoRiskyValue"/><%--madatory--%>
<bf:parameter id="workitemseq" name="wseq" value="" valuePattern="NoRiskyValue"/><%--madatory--%>
<bf:parameter id="param1" name="p1" value="" valuePattern="NoRiskyValue"/><%--madatory--%>
<bf:parameter id="overwrite" name="ow" value="true" valuePattern="NoRiskyValue"/><%--madatory--%>

<%@ include file="./sslinit.jsp" %>

<%!
    static final String DEFAULT_FILE_NAME = "List Manager Search Result.pdf";
    static Properties properties = null;
	
	static final String REPORT_URL = "{REPORTSERVERURL}/rest_v2/reports{PATH}.{FILEFORMAT}?j_memberid={J_MEMBERID}&j_username={J_USERNAME}&SEARCH_ID={PARAM1}";

    static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("JSP");

    void loadProperties(ServletContext application) {
        try {
            if (null == properties) {
                properties = new Properties();
                properties.load(new FileInputStream(ServletUtil.getRealPath(application, "/solutions/whrsc/listmanager/generatepdf.properties")));
            }
        } catch (Exception e) {
        }
    }

    File downloadWorksheet(HttpServletRequest request, String reportServerURL, String path, String fileFormat, String jMemberId, String jUserName, String param1) {
        File fp = null;
        try {
            initSSLEx(request, reportServerURL);

            String url = REPORT_URL;
            url = StringUtils.replace(url, "{REPORTSERVERURL}", reportServerURL);
            url = StringUtils.replace(url, "{PATH}", path);
            url = StringUtils.replace(url, "{FILEFORMAT}", fileFormat);
            url = StringUtils.replace(url, "{J_MEMBERID}", jMemberId);
            url = StringUtils.replace(url, "{J_USERNAME}", jUserName);
            url = StringUtils.replace(url, "{PARAM1}", param1);
            java.net.URL agent = new java.net.URL(url);

            InputStream inputStream = null;
            FileOutputStream fos = null;
            fp = File.createTempFile("whrsc_", ".pdf");

            try {
                inputStream = new BufferedInputStream(agent.openStream());
                fos = new FileOutputStream(fp);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
            } catch (IOException e) {
                log.error("Error during the downloading the  report file. (url=" + url + ")", e);
                fp = null;
            } finally {
                if (inputStream != null) try {
                    inputStream.close();
                } catch (Exception be) {
                }
                ;
                if (fos != null) try {
                    fos.close();
                } catch (Exception we) {
                }
                ;
            }
        } catch (Exception e) {
            log.error(e);
        }

        return fp;
    }


    String getReportServerUrl(ServletRequest request) {
        StringBuilder sb = new StringBuilder(50);
        sb.append(request.getScheme()).append("://");
        sb.append(request.getServerName());
        int port = request.getServerPort();
        if (80 != port) {
            sb.append(":").append(port);
        }

        sb.append("/bizflowadvreport");

        return sb.toString();
    }
%>
<%
   	
	String errorMsg = null;
    JSONObject ret = new JSONObject();
    HWSession hwSession = hwSessionFactory.newInstance();
    XMLResultSet loginUser = null;
    String fileName = null;
    String reportPath = null;
    String fileFormat = "pdf";
    String reportServerURL = getReportServerUrl(request);
    boolean isOverwrite = !"false".equalsIgnoreCase(overwrite);
    try {
        loadProperties(application);

        // Validation
        loginUser = (XMLResultSet) session.getAttribute("LoginUser");
        reportServerURL = properties.getProperty("report.server.url", reportServerURL);
        fileName = properties.getProperty("report.ListManagerSearchResult.fileName", DEFAULT_FILE_NAME);
 	
        reportPath = properties.getProperty("report.ListManagerSearchResult.path");

    } catch (Exception e) {
        log.error(e);
        errorMsg = getOriginalExceptionMessage(e);
    }

    if (null == errorMsg) {
        
		try {
			
            // download Worksheet report file
            String jMemberID = loginUser.getFieldValueAt(0, "ID");
            String jUserName = loginUser.getFieldValueAt(0, "LOGINID");
			String param = request.getParameter("param1");
			
			File worksheetFile = downloadWorksheet(request, reportServerURL, reportPath, fileFormat, jMemberID, jUserName, param);
			
			response.setContentType ("application/pdf");    
			response.setHeader ("Content-Disposition", "attachment;filename="+fileName);
			
			InputStream inputStream = new FileInputStream(worksheetFile);
			ServletOutputStream servletOutputStream = response.getOutputStream();
			
			int bit = 256;
			int i = 0;
			
			try 
			{

				while ((bit) >= 0) 
				{
					bit = inputStream.read();
					servletOutputStream.write(bit);
				}
			}
			catch (Exception ioe) 
			{
				errorMsg = getOriginalExceptionMessage(ioe);
			}
			servletOutputStream.flush();

			inputStream.close();
				
		} catch (Exception e) {
			log.error(e);
			errorMsg = getOriginalExceptionMessage(e);
		}
		
    }

    if (errorMsg != null) {
        ret.put("success", false);
        ret.put("message", errorMsg);
    } else {
        ret.put("success", true);
        ret.put("fileName", fileName);
    }
    //out.clear();
    //response.setContentType("application/json; charset=UTF-8");
    //out.write(ret.toString());
	
	
	
%>