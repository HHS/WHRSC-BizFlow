--------------------------------------------------------
--  DDL for Procedure SP_UPDATE_REQUESTS
--------------------------------------------------------
/**
 * Parses WHRSC Requests with Additional Date Fields XML data and 
 * stores it into DSS_REQUESTS table.
 *
 * @param I_ID - Record ID
 */
 CREATE OR REPLACE PROCEDURE SP_UPDATE_REQUESTS
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
	--DBMS_OUTPUT.PUT_LINE('SP_UPDATE_REQUESTS - BEGIN ============================');						
	--DBMS_OUTPUT.PUT_LINE('PARAMETERS ----------------');						
	--DBMS_OUTPUT.PUT_LINE('    I_ID IS NULL?  = ' || (CASE WHEN I_ID IS NULL THEN 'YES' ELSE 'NO' END));						
	--DBMS_OUTPUT.PUT_LINE('    I_ID           = ' || TO_CHAR(I_ID));						
	--DBMS_OUTPUT.PUT_LINE(' ----------------');						
							
	--DBMS_OUTPUT.PUT_LINE('Starting xml data retrieval and table update ----------');						
							
	IF I_ID IS NULL THEN						
		RAISE_APPLICATION_ERROR(-20920, 'SP_UPDATE_REQUESTS: Input Record ID is invalid.  I_ID = '	|| TO_CHAR(I_ID) );				
	END IF;						
							
	BEGIN						
							
		--DBMS_OUTPUT.PUT_LINE('    DSS_REQUESTS table');					
		INSERT INTO DSS_REQUESTS					
			(	REQUEST_NUMBER			
				,CLEARANCE_LEVEL_REQUIRED							
				,REQUEST_TYPE							
				,CUSTOMER_NAME							
				,REQUESTER_NAME							
				,SUPERVISORY_POSITION							
				,TRAVEL_PREFERENCE							
				,DESCRIPTION
        ,REQ_APPROVAL_DATE
        ,REQ_CANCELLATION_DATE
        ,REQ_CREATION_DATE)							
		SELECT					
				X.REQUEST_NUMBER			
				, X.CLEARANCE_LEVEL_REQUIRED							
				, X.REQUEST_TYPE							
				, X.CUSTOMER_NAME							
				, X.REQUESTER_NAME							
				, X.SUPERVISORY_POSITION							
				, X.TRAVEL_PREFERENCE							
				, X.DESCRIPTION	
        , TO_DATE(SUBSTR(X.REQ_APPROVAL_DATE, 1, 19), 'YYYY-MM-DD"T"HH24:MI:SS') AS REQ_APPROVAL_DATE
        , TO_DATE(SUBSTR(X.REQ_CANCELLATION_DATE, 1, 19), 'YYYY-MM-DD"T"HH24:MI:SS') AS REQ_CANCELLATION_DATE
        , TO_DATE(SUBSTR(X.REQ_CREATION_DATE, 1, 19), 'YYYY-MM-DD"T"HH24:MI:SS') AS REQ_CREATION_DATE
		FROM INTG_DATA_DTL IDX					
			, XMLTABLE(XMLNAMESPACES(DEFAULT 'http://www.ibm.com/xmlns/prod/cognos/dataSet/201006'), '/dataSet/dataTable/row[../id/text() = "List1"]'				
				PASSING IDX.FIELD_DATA			
				COLUMNS			
					REQUEST_NUMBER				VARCHAR2(202)	Path 'Request__Number'
					,CLEARANCE_LEVEL_REQUIRED	VARCHAR2(1002)	Path 'Clearance__Level__Required__for__Position'					
					,REQUEST_TYPE				VARCHAR2(1002)	Path 'Request__Type'					
					,CUSTOMER_NAME				VARCHAR2(202)	Path 'Request__Customer__Name'					
					,REQUESTER_NAME				VARCHAR2(206)	Path 'Requester__Name'					
					,SUPERVISORY_POSITION		VARCHAR2(8)		Path 'Request__Supervisory__Position'					
					,TRAVEL_PREFERENCE			VARCHAR2(1002)	Path 'Request__Travel__Preference'					
					,DESCRIPTION				VARCHAR2(4000)	Path 'Request__Description'	
          ,REQ_APPROVAL_DATE      VARCHAR2(50)      	PATH 'Request__Approval__Date'
          ,REQ_CANCELLATION_DATE    VARCHAR2(50)      	PATH 'Request__Cancellation__Date'
          ,REQ_CREATION_DATE      VARCHAR2(50)      	PATH 'Request__Creation__Date'
					) X							
		WHERE IDX.ID = I_ID;					
							
	EXCEPTION						
		WHEN OTHERS THEN					
			RAISE_APPLICATION_ERROR(-20921, 'SP_UPDATE_REQUESTS: Invalid REQUESTS  data.  I_ID = ' || TO_CHAR(I_ID) );				
	END;						
							
	--DBMS_OUTPUT.PUT_LINE('SP_UPDATE_REQUESTS - END ==========================');						
							
							
EXCEPTION							
	WHEN E_INVALID_REC_ID THEN						
		SP_ERROR_LOG();					
		--DBMS_OUTPUT.PUT_LINE('ERROR occurred while executing SP_UPDATE_REQUESTS -------------------');					
		--DBMS_OUTPUT.PUT_LINE('ERROR message = ' || 'Record ID is not valid');					
	WHEN E_INVALID_DATA THEN						
		SP_ERROR_LOG();					
		--DBMS_OUTPUT.PUT_LINE('ERROR occurred while executing SP_UPDATE_REQUESTS -------------------');					
		--DBMS_OUTPUT.PUT_LINE('ERROR message = ' || 'Invalid data');					
	WHEN OTHERS THEN						
		SP_ERROR_LOG();					
		V_ERRCODE := SQLCODE;					
		V_ERRMSG := SQLERRM;					
		--DBMS_OUTPUT.PUT_LINE('ERROR occurred while executing SP_UPDATE_REQUESTS -------------------');					
		--DBMS_OUTPUT.PUT_LINE('Error code    = ' || V_ERRCODE);					
		--DBMS_OUTPUT.PUT_LINE('Error message = ' || V_ERRMSG);					
END;
/