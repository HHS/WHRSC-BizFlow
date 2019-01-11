--------------------------------------------------------
--  DDL for Procedure SP_APPOINTEE_SECURITY_EMAIL
--------------------------------------------------------

create or replace PROCEDURE SP_APPOINTEE_SECURITY_EMAIL AS

V_SENDER VARCHAR2(100);
V_TORECIPIENTS VARCHAR2(500);
V_SUBJECT VARCHAR2(2000);
V_CONTENTTOP VARCHAR2(8000);
V_BODY CLOB;
V_HHSID VARCHAR2(10);
V_REQNUMBER NUMBER(15);
V_CLEARED_FOR_NEO DATE;
V_HR_SPECIALIST_ID VARCHAR2(10);

CURSOR CUR_HHS_IDS IS	 
---------Query----------------
	SELECT HHSID, CLEARED_FOR_NEO 
	
	FROM hhs_whrsc_hr.bits_neostatus 
	
	WHERE date_requested > SYSDATE - 1;
	
----------Query End----------------

BEGIN
    OPEN CUR_HHS_IDS;  
    FETCH CUR_HHS_IDS INTO
		V_HHSID ,
		V_CLEARED_FOR_NEO;
		
    LOOP
    
        -- Retrieving req# and HRSid from MAIN, looping through all active HHSID
    	SELECT JOB_OPENING_ID, HR_SPECIALIST_ID INTO V_REQNUMBER, V_HR_SPECIALIST_ID
		FROM hhs_whrsc_hr.main
		WHERE transaction_id IN (
            SELECT MAX(transaction_id) FROM hhs_whrsc_hr.person_info WHERE hhsid = V_HHSID
        )
        AND LOWER(action_type) = 'appointment'
        AND LOWER(status) = 'active';
        
        -- Retrieving V_TORECIPIENTS email address from MEMBER table, using the HRS id from query above
        SELECT EMAIL INTO V_TORECIPIENTS
        FROM BIZFLOW.MEMBER
        WHERE MEMBERID = V_HR_SPECIALIST_ID;
        --V_TORECIPIENTS := 'JMIRANDA@BIZFLOW.COM'; -- hardcoded for testing only
        
        V_SENDER := 'donotreply@hhs.gov' ;
        
        IF V_REQNUMBER IS NOT NULL AND V_TORECIPIENTS IS NOT NULL THEN
        
            IF V_CLEARED_FOR_NEO IS NOT NULL  THEN
                    
                V_SUBJECT := 'Appointment action for Request Number #' || V_REQNUMBER || ' - Cleared Security';
                
                V_CONTENTTOP := 
                    '<html>
                        <head>
                            <title>Cleared by Security Email</title>
                        </head>
                        <body>                                                            
                            <font face=" Calibri" size="3">
                            An appointment action for Request Number #' || V_REQNUMBER || ' has been routed to you by security. The individual has cleared suitability.<br><br>
                        </body>
                    </html>';

            ELSE
                    
                V_SUBJECT := 'Appointment action for Request Number #' || V_REQNUMBER || ' - Not Cleared Security';
                
                V_CONTENTTOP := 
                    '<html>
                        <head>
                            <title>Not Cleared by Security Email</title>
                        </head>
                        <body>
                            <font face=" Calibri" size="3">
                            An appointment action for Request Number #' || V_REQNUMBER || ' has been routed to you by security. The individual has not cleared suitability.<br><br>
                        </body>
                    </html>';

            END IF;
        
            V_BODY := V_CONTENTTOP;

            ------------------------------------
            -------EMAILSEND---------------
            SP_SEND_MAIL(V_TORECIPIENTS,'','',V_SENDER,V_SUBJECT,'',V_BODY);
            ------------------------------------
        

            INSERT INTO AUTO_EMAIL_LOG
            (EMAIL_SENT_DATE, FROM_EMAIL_ADDRESS,TO_EMAIL_ADDRESS,SUBJECT,BODY,OFFICE, SLA, TRANSACTION_ID, ANN_NUMBER)     
            VALUES
            (SYSDATE, V_SENDER, V_TORECIPIENTS || ';', V_SUBJECT, 'From: ' || V_SENDER || '<br>To: ' || V_TORECIPIENTS || '<br><br>Subject: ' ||  V_SUBJECT || '<br><br>' || V_BODY,'','', '','');

		END IF;
        FETCH CUR_HHS_IDS INTO
            V_HHSID ,
            V_CLEARED_FOR_NEO;
		
        EXIT WHEN CUR_HHS_IDS%NOTFOUND;
	
    END LOOP;
	CLOSE CUR_HHS_IDS;
  NULL;
END SP_APPOINTEE_SECURITY_EMAIL;

/