--------------------------------------------------------
--  DDL for Job EMAIL_APPOINTEE_SECURITY_EMAIL
--------------------------------------------------------
BEGIN
    DBMS_SCHEDULER.CREATE_JOB (
            JOB_NAME => 'EMAIL_APPOINTEE_SECURITY_EMAIL',
            JOB_TYPE => 'STORED_PROCEDURE',
            JOB_ACTION => 'SP_APPOINTEE_SECURITY_EMAIL',
            START_DATE => SYSTIMESTAMP,
            REPEAT_INTERVAL => 'FREQ=DAILY;BYDAY=MON,TUE,WED,THU,FRI,SAT,SUN;BYHOUR=6;BYMINUTE=0;BYSECOND=0;',
            ENABLED => TRUE,
            COMMENTS => 'Oracle Job: Email - Appointment action for Request - (Not) Cleared Security');
END;
/