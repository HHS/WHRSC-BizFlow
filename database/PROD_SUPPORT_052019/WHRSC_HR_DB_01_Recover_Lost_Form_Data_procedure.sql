---------------------------------------------------------------------------------------------------------------------------------------------------------
--THIS PROCEDURE WILL RELOAD THE LAST SUCCESSFULLY SAVED FORM DATA FOR PROCESS ID THAT LOST THE DATA DUE TO USER ACTIONS IN HHW_WHRSC_HR SCHEMA
---------------------------------------------------------------------------------------------------------------------------------------------------------
 

create or replace PROCEDURE SP_RECOVER_LOST_FORM_DATA AS

V_PROCID	NUMBER(10,0);
V_FORM_TYPE	VARCHAR2(50 BYTE);
V_LAST_USER	VARCHAR2(50 BYTE);
V_TRANSACTIONID	NUMBER(10,0);
V_FIELD_DATA	XMLTYPE;

V_SENDER VARCHAR2(100);
V_TORECIPIENT VARCHAR2(100);
V_RECIPIENTNAME VARCHAR2(100);
V_CCRECIPIENT VARCHAR2(100);
V_SUBJECT VARCHAR2(2000);
V_CONTENTTOP VARCHAR2(8000);
V_BODY CLOB;


CURSOR CUR_DATA_LOSS_PROCIDS IS	 
---------Query----------------
	SELECT PROCID, FORM_TYPE, NVL(MOD_USR,CRT_USR) AS LAST_USER, TRANSACTIONID FROM HHS_WHRSC_HR.TBL_FORM_DTL
  WHERE EXTRACTVALUE(FIELD_DATA,'/DOCUMENT/TRANSACTION/ACTION_TYPE') IS NULL AND CRT_DT >= '01-JAN-19';
	
----------Query End----------------

BEGIN
    dbms_output.put_line('DEBUG ' || SYSTIMESTAMP || ' -- SP_RECOVER_LOST_FORM_DATA started');
    OPEN CUR_DATA_LOSS_PROCIDS;  
    FETCH CUR_DATA_LOSS_PROCIDS INTO
		V_PROCID,
    V_FORM_TYPE,
    V_LAST_USER,
    V_TRANSACTIONID;
	
    IF V_PROCID IS NOT NULL THEN	
        LOOP
            
            dbms_output.put_line('DEBUG ' || SYSTIMESTAMP || ' -- PROCID: ' || V_PROCID || ' -- TRANSACTIONID: ' || V_TRANSACTIONID || ' FORM_TYPE: ' || V_FORM_TYPE || ' LAST_USER: ' || V_LAST_USER);
            
            -- Read the last successfully saved form data for given PROCID
            
            SELECT FIELD_DATA INTO V_FIELD_DATA FROM HHS_WHRSC_HR.TBL_FORM_DTL_AUDIT WHERE PROCID = V_PROCID 
            AND EXTRACTVALUE(FIELD_DATA,'/DOCUMENT/TRANSACTION/ACTION_TYPE') IS NOT NULL
            AND AUDITID = (SELECT MAX(AUDITID) FROM HHS_WHRSC_HR.TBL_FORM_DTL_AUDIT WHERE PROCID = V_PROCID 
            AND EXTRACTVALUE(FIELD_DATA,'/DOCUMENT/TRANSACTION/ACTION_TYPE') IS NOT NULL);
            
            --dbms_output.put_line('DEBUG ' || SYSTIMESTAMP || ' -- FIELD_DATA: ' || V_FIELD_DATA);
            
            IF V_FIELD_DATA IS NOT NULL THEN
            -- Update the Field Data from Audit Table
            UPDATE HHS_WHRSC_HR.TBL_FORM_DTL
            SET FIELD_DATA = V_FIELD_DATA
            WHERE PROCID = V_PROCID;
            
            COMMIT;

            -- Retrieving V_TORECIPIENTS email address from MEMBER table, using the HRS id from query above
            SELECT EMAIL, 
            CASE 
              WHEN NAME LIKE '%,%' THEN SUBSTR(NAME, INSTR(NAME, ',') + 1) || ' ' ||  SUBSTR(NAME, 1, INSTR(NAME, ',') - 1)
              ELSE NAME 
            END AS NAME
            INTO V_TORECIPIENT, V_RECIPIENTNAME
            FROM BIZFLOW.MEMBER WHERE LOGINID = V_LAST_USER;

            dbms_output.put_line('DEBUG ' || SYSTIMESTAMP || ' -- PROCID: ' || V_PROCID || ' -- EMAIL RECIPIENT: ' || V_RECIPIENTNAME || ' [' || V_TORECIPIENT || ']');
                
                V_SENDER := 'DoNotReply@hhs.gov' ;
                V_CCRECIPIENT := '';
                
                IF V_TORECIPIENT IS NOT NULL THEN
                
                    IF V_TRANSACTIONID IS NOT NULL  THEN
                            
                        V_SUBJECT := 'Form Data Recovered for Transaction ID: ' || V_TRANSACTIONID;
                        
                        V_CONTENTTOP := 
                            '<html>
                                <head>
                                    <title>Form Data Recovery Email</title>
                                </head>
                                <body>   
                                    ' || V_RECIPIENTNAME || ', <br><br>
                                    <font face=" Calibri" size="3">
                                    
                                    This email is being sent to you to inform that ' || V_FORM_TYPE || ' form data for Transaction ID: ' || V_TRANSACTIONID || ' has been recovered and set to the last successful saved data in the EWITS2.0 system.<br><br>
                                                                        
                                    Thank you.
                                    <br><br>*** Please do not reply to this email. 
                                </body>
                            </html>';
        
                    END IF;
                
                    V_BODY := V_CONTENTTOP;
        
                    ------------------------------------
                    -------EMAILSEND---------------
                    SP_SEND_MAIL(V_TORECIPIENT,V_CCRECIPIENT,'',V_SENDER,V_SUBJECT,'',V_BODY);
                    ------------------------------------
                    
                    dbms_output.put_line('DEBUG ' || SYSTIMESTAMP || ' -- PROCID: ' || V_PROCID || ' -- Email sent to ' || V_TORECIPIENT || ', ' || V_CCRECIPIENT);
        
                    INSERT INTO AUTO_EMAIL_LOG
                    (EMAIL_SENT_DATE, FROM_EMAIL_ADDRESS,TO_EMAIL_ADDRESS,SUBJECT,BODY,OFFICE, SLA, TRANSACTION_ID, ANN_NUMBER)     
                    VALUES
                    (SYSDATE, V_SENDER, V_TORECIPIENT || ';' || V_CCRECIPIENT || ';', V_SUBJECT, 'From: ' || V_SENDER || '<br>To: ' || V_TORECIPIENT || '<br><br>Subject: ' ||  V_SUBJECT || '<br><br>' || V_BODY,'','', V_TRANSACTIONID,'');
                                    
                ELSE   
                    dbms_output.put_line('DEBUG ' || SYSTIMESTAMP || ' -- PROCID: ' || V_PROCID || ' -- Form Data Recovery Email Notification not sent');
                END IF;   
             
            ELSE   
                    dbms_output.put_line('DEBUG ' || SYSTIMESTAMP || ' -- PROCID: ' || V_PROCID || ' -- No saved data available for this process ID.');  
            END IF;
        
        FETCH CUR_DATA_LOSS_PROCIDS INTO
          V_PROCID,
          V_FORM_TYPE,
          V_LAST_USER,
          V_TRANSACTIONID;
            
        EXIT WHEN CUR_DATA_LOSS_PROCIDS%NOTFOUND;            

        NULL;
        END LOOP;
    ELSE
        dbms_output.put_line('DEBUG ' || SYSTIMESTAMP || ' -- No Process ID was found');
    END IF;
	CLOSE CUR_DATA_LOSS_PROCIDS;
  NULL;
  dbms_output.put_line('DEBUG ' || SYSTIMESTAMP || ' -- SP_RECOVER_LOST_FORM_DATA ended');
END SP_RECOVER_LOST_FORM_DATA;