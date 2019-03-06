--------------------------------------------------------
--  DDL for Procedure SP_UPDATE_VACANCY
--------------------------------------------------------
/**
 * Parses WHRSC Vacancy XML data and 
 * stores it into DSS_VACANCY table.
 *
 * @param I_ID - Record ID
 */
 CREATE OR REPLACE PROCEDURE SP_UPDATE_VACANCY							
(							
	I_ID                IN      NUMBER						
)							
IS							
	V_REC_CNT                   NUMBER(10);						
	V_XMLDOC                    XMLTYPE;						
	V_XMLVALUE                  XMLTYPE;						
	V_ERRCODE                   NUMBER(10);						
	V_ERRMSG                    VARCHAR2(512);						
	E_INVALID_REC_ID            EXCEPTION;						
	PRAGMA EXCEPTION_INIT(E_INVALID_REC_ID, -20920);						
	E_INVALID_DATA     EXCEPTION;						
	PRAGMA EXCEPTION_INIT(E_INVALID_DATA, -20921);						
BEGIN							
	--DBMS_OUTPUT.PUT_LINE('SP_UPDATE_VACANCY - BEGIN ============================');						
	--DBMS_OUTPUT.PUT_LINE('PARAMETERS ----------------');						
	--DBMS_OUTPUT.PUT_LINE('    I_ID IS NULL?  = ' || (CASE WHEN I_ID IS NULL THEN 'YES' ELSE 'NO' END));						
	--DBMS_OUTPUT.PUT_LINE('    I_ID           = ' || TO_CHAR(I_ID));						
	--DBMS_OUTPUT.PUT_LINE(' ----------------');						
							
	--DBMS_OUTPUT.PUT_LINE('Starting xml data retrieval and table update ----------');						
							
	IF I_ID IS NULL THEN						
		RAISE_APPLICATION_ERROR(-20920, 'SP_UPDATE_VACANCY: Input Record ID is invalid.  I_ID = '	|| TO_CHAR(I_ID) );				
	END IF;						
							
	BEGIN						
							
		--DBMS_OUTPUT.PUT_LINE('    DSS_VACANCY table');					
		INSERT INTO DSS_VACANCY					
			(		POSITION_TITLE		
					, VACANCY_IDENTIFICATION_NUMBER						
					, VACANCY_ANNOUNCEMENT_NUMBER						
					, VACANCY_STATUS						
					, ANNOUNCEMENT_TYPE						
					, LAST_UPDATE_DATE						
					, FULL_PERFORMANCE_LEVEL						
					, DATE_ANNOUNCEMENT_POSTED						
					, DATE_ANNOUNCEMENT_OPENED						
					, DATE_ANNOUNCEMENT_CLOSED						
					, NUMBER_OF_POSITIONS_ADVERTISED						
					, TOTAL_APPLICANTS						
					, TOTAL_ELIGIBLE_APPLICANTS						
					, TOTAL_REFERRED_APPLICANTS						
					, TOTAL_SELECTED_APPLICANTS
					, ANNOUNCEMENT_CONTROL_NUMBER
					, ANNOUNCEMENT_APP_COUNT)						
		SELECT					
				X.POSITION_TITLE			
				, X.VACANCY_IDENTIFICATION_NUMBER							
				, X.VACANCY_ANNOUNCEMENT_NUMBER							
				, X.VACANCY_STATUS							
				, X.ANNOUNCEMENT_TYPE							
				, TO_DATE(SUBSTR(X.LAST_UPDATE_DATE, 1, 19), 'YYYY-MM-DD"T"HH24:MI:SS') AS LAST_UPDATE_DATE							
				, X.FULL_PERFORMANCE_LEVEL							
				, TO_DATE(SUBSTR(X.DATE_ANNOUNCEMENT_POSTED, 1, 19), 'YYYY-MM-DD"T"HH24:MI:SS') AS DATE_ANNOUNCEMENT_POSTED							
				, TO_DATE(SUBSTR(X.DATE_ANNOUNCEMENT_OPENED, 1, 19), 'YYYY-MM-DD"T"HH24:MI:SS') AS DATE_ANNOUNCEMENT_OPENED							
				, TO_DATE(SUBSTR(X.DATE_ANNOUNCEMENT_CLOSED, 1, 19), 'YYYY-MM-DD"T"HH24:MI:SS') AS DATE_ANNOUNCEMENT_CLOSED							
				, X.NUMBER_OF_POSITIONS_ADVERTISED							
				, X.TOTAL_APPLICANTS							
				, X.TOTAL_ELIGIBLE_APPLICANTS							
				, X.TOTAL_REFERRED_APPLICANTS							
				, X.TOTAL_SELECTED_APPLICANTS
				, X.ANNOUNCEMENT_CONTROL_NUMBER
				, X.ANNOUNCEMENT_APP_COUNT
							
		FROM INTG_DATA_DTL IDX					
			, XMLTABLE(XMLNAMESPACES(DEFAULT 'http://www.ibm.com/xmlns/prod/cognos/dataSet/201006'), '/dataSet/dataTable/row[../id/text() = "List1"]'				
				PASSING IDX.FIELD_DATA			
				COLUMNS			
					POSITION_TITLE					VARCHAR2(202)	Path 'Position__Title'
					,VACANCY_IDENTIFICATION_NUMBER	NUMBER(10)		Path 'Vacancy__Identification__Number'					
					,VACANCY_ANNOUNCEMENT_NUMBER	VARCHAR2(56)	Path 'Vacancy__Announcement__Number'					
					,VACANCY_STATUS					VARCHAR2(1002)	Path 'Vacancy__Status'					
					,ANNOUNCEMENT_TYPE				VARCHAR2(24)	Path 'Announcement__Type'					
					,LAST_UPDATE_DATE				VARCHAR2(50)	Path 'Vacancy__Last__Update__Date_x002fTime'					
					,FULL_PERFORMANCE_LEVEL			VARCHAR2(6)		Path 'Full__Performance__Level'					
					,DATE_ANNOUNCEMENT_POSTED		VARCHAR2(50)	Path 'Date__Announcement__Posted'					
					,DATE_ANNOUNCEMENT_OPENED		VARCHAR2(50)	Path 'Date__Announcement__Opened'					
					,DATE_ANNOUNCEMENT_CLOSED		VARCHAR2(50)	Path 'Date__Announcement__Closed'					
					,NUMBER_OF_POSITIONS_ADVERTISED	VARCHAR2(12)	Path 'Number__of__Positions__Advertised'					
					,TOTAL_APPLICANTS				NUMBER(10)		Path 'Vacancy__Total__Applicants'					
					,TOTAL_ELIGIBLE_APPLICANTS		NUMBER(10)		Path 'Vacancy__Total__Eligible__Applicants'					
					,TOTAL_REFERRED_APPLICANTS		NUMBER(10)		Path 'Vacancy__Total__Referred__Applicants'					
					,TOTAL_SELECTED_APPLICANTS		NUMBER(10)		Path 'Vacancy__Total__Selected__Applicants'					
					,ANNOUNCEMENT_CONTROL_NUMBER	NUMBER(10)		Path 'Announcement__Control__Number'
					,ANNOUNCEMENT_APP_COUNT         NUMBER(10)      Path 'Announcement__Application__Count'
					) X							
		WHERE IDX.ID = I_ID;					
							
	EXCEPTION						
		WHEN OTHERS THEN					
			RAISE_APPLICATION_ERROR(-20921, 'SP_UPDATE_VACANCY: Invalid VACANCY data.  I_ID = ' || TO_CHAR(I_ID) );				
	END;						
							
	--DBMS_OUTPUT.PUT_LINE('SP_UPDATE_VACANCY - END ==========================');						
							
							
EXCEPTION							
	WHEN E_INVALID_REC_ID THEN						
		SP_ERROR_LOG();					
		--DBMS_OUTPUT.PUT_LINE('ERROR occurred while executing SP_UPDATE_VACANCY -------------------');					
		--DBMS_OUTPUT.PUT_LINE('ERROR message = ' || 'Record ID is not valid');					
	WHEN E_INVALID_DATA THEN						
		SP_ERROR_LOG();					
		--DBMS_OUTPUT.PUT_LINE('ERROR occurred while executing SP_UPDATE_VACANCY -------------------');					
		--DBMS_OUTPUT.PUT_LINE('ERROR message = ' || 'Invalid data');					
	WHEN OTHERS THEN						
		SP_ERROR_LOG();					
		V_ERRCODE := SQLCODE;					
		V_ERRMSG := SQLERRM;					
		--DBMS_OUTPUT.PUT_LINE('ERROR occurred while executing SP_UPDATE_VACANCY -------------------');					
		--DBMS_OUTPUT.PUT_LINE('Error code    = ' || V_ERRCODE);					
		--DBMS_OUTPUT.PUT_LINE('Error message = ' || V_ERRMSG);					
END;
/

--------------------------------------------------------
--  DDL for Procedure SP_UPDATE_INTG_DATA
--------------------------------------------------------

 CREATE OR REPLACE PROCEDURE SP_UPDATE_INTG_DATA
(
	IO_ID               IN OUT  NUMBER
	, I_INTG_TYPE       IN      VARCHAR2
	, I_FIELD_DATA      IN      CLOB
	, I_USER            IN      VARCHAR2
)
IS
	V_ID                        NUMBER(20);
	V_INTG_TYPE                 VARCHAR2(50);
	V_USER                      VARCHAR2(50);
	V_REC_CNT                   NUMBER(10);
	V_MAX_ID                    NUMBER(20);
	V_XMLDOC                    XMLTYPE;
BEGIN
--	DBMS_OUTPUT.PUT_LINE('PARAMETERS ----------------');
--	DBMS_OUTPUT.PUT_LINE('    ID IS NULL?  = ' || (CASE WHEN IO_ID IS NULL THEN 'YES' ELSE 'NO' END));
--	DBMS_OUTPUT.PUT_LINE('    ID           = ' || TO_CHAR(IO_ID));
--	DBMS_OUTPUT.PUT_LINE('    I_INTG_TYPE  = ' || I_INTG_TYPE);
--	DBMS_OUTPUT.PUT_LINE('    I_FIELD_DATA = ' || I_FIELD_DATA);
--	DBMS_OUTPUT.PUT_LINE('    I_USER       = ' || I_USER);
--	DBMS_OUTPUT.PUT_LINE(' ----------------');


	V_ID := IO_ID;

	DBMS_OUTPUT.PUT_LINE('ID to be used is determined: ' || TO_CHAR(V_ID));


	BEGIN
		SELECT COUNT(*) INTO V_REC_CNT FROM INTG_DATA_DTL WHERE ID = V_ID;
	EXCEPTION
		WHEN NO_DATA_FOUND THEN
			V_REC_CNT := -1;
	END;

	V_INTG_TYPE := I_INTG_TYPE;
	V_USER := I_USER;

--	DBMS_OUTPUT.PUT_LINE('Inspected existence of same record.');
--	DBMS_OUTPUT.PUT_LINE('    V_ID       = ' || TO_CHAR(V_ID));
--	DBMS_OUTPUT.PUT_LINE('    V_REC_CNT  = ' || TO_CHAR(V_REC_CNT));

	V_XMLDOC := XMLTYPE(I_FIELD_DATA);

	IF V_REC_CNT > 0 THEN
		DBMS_OUTPUT.PUT_LINE('Record found so that field data will be updated on the same record.');

		UPDATE INTG_DATA_DTL
		SET
			INTG_TYPE = V_INTG_TYPE
			, FIELD_DATA = V_XMLDOC
			, MOD_DT = SYSDATE
			, MOD_USR = V_USER
		WHERE ID = V_ID
		;

	ELSE
		DBMS_OUTPUT.PUT_LINE('No record found so that new record will be inserted.');

		INSERT INTO INTG_DATA_DTL
		(
			INTG_TYPE
			, FIELD_DATA
			, CRT_DT
			, CRT_USR
		)
		VALUES
		(
			V_INTG_TYPE
			, V_XMLDOC
			, SYSDATE
			, V_USER
		)
		RETURNING ID INTO V_ID
		;
	END IF;

	--------------------------------------------
	-- Parse XML data into respective tables
	--------------------------------------------
	IF V_INTG_TYPE = 'APPNOTIF'	THEN	
		SP_UPDATE_APPLICANT_NOTIFICTNS(V_ID);
	ELSIF V_INTG_TYPE = 'CERTLOC' THEN	
		SP_UPDATE_CERTIFICATE_LOCATION(V_ID);
	ELSIF V_INTG_TYPE = 'CERTS' THEN	
		SP_UPDATE_CERTIFICATES(V_ID);
	ELSIF V_INTG_TYPE = 'NEWHIREFORMS' THEN	
		SP_UPDATE_NEW_HIRE_FORMS(V_ID);
	ELSIF V_INTG_TYPE = 'NEWHIREONDOCS' THEN	
		SP_UPDATE_NEW_HIRE_ONBRDNG_DOC(V_ID);
	ELSIF V_INTG_TYPE = 'NEWHIRETASK' THEN	
		SP_UPDATE_NEW_HIRE_TASKS(V_ID);
	ELSIF V_INTG_TYPE = 'NEWHIREVACREQ' THEN	
		SP_UPDATE_NEW_HIRE_VACNCY_REQ(V_ID);
	ELSIF V_INTG_TYPE = 'NEWHIRES' THEN	
		SP_UPDATE_NEW_HIRES(V_ID);
	ELSIF V_INTG_TYPE = 'PERMPROFILE' THEN	
		SP_UPDATE_PERMISSION_PROFILES(V_ID);
	ELSIF V_INTG_TYPE = 'REQLOC' THEN	
		SP_UPDATE_REQUEST_LOCATIONS(V_ID);
	ELSIF V_INTG_TYPE = 'REQRATNGCOMB' THEN	
		SP_UPDATE_REQUEST_RATING_COMB(V_ID);
	ELSIF V_INTG_TYPE = 'REQVACNCYCOMB' THEN	
		SP_UPDATE_REQUEST_VACNCY_COMB(V_ID);
	ELSIF V_INTG_TYPE = 'REQUESTS' THEN	
		SP_UPDATE_REQUESTS(V_ID);
	ELSIF V_INTG_TYPE = 'VACNCY' THEN	
		SP_UPDATE_VACANCY(V_ID);
	ELSIF V_INTG_TYPE = 'VACNCYELIG' THEN	
		SP_UPDATE_VACANCY_ELIGIBILITIS(V_ID);
	ELSIF V_INTG_TYPE = 'VACNCYLOC' THEN	
		SP_UPDATE_VACANCY_LOCATIONS(V_ID);
	ELSIF V_INTG_TYPE = 'VACNCYMISSNCRITCL' THEN	
		SP_UPDATE_VAC_MISSN_CRITCL_OCC(V_ID);
	ELSIF V_INTG_TYPE = 'VACNCYRATNGCOMB' THEN	
		SP_UPDATE_VACANCY_RATING_COMB(V_ID);
	END IF;
	

	COMMIT;

EXCEPTION
	WHEN OTHERS THEN
		SP_ERROR_LOG();
--		DBMS_OUTPUT.PUT_LINE('Error occurred while executing UPDATE_INTG_DATA -------------------');
END;
/