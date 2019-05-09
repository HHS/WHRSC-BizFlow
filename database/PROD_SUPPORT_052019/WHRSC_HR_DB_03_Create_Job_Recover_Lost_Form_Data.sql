----------------------------------------------------------------------------
--  Oracle Scheduler to Create Jobs to execute SP_RECOVER_LOST_FORM_DATA Procedures
----------------------------------------------------------------------------
----------------------------------------------------------------------------

--------------------------------------------------------
--  DDL for Job SP_RECOVER_LOST_FORM_DATA
--------------------------------------------------------

BEGIN
    DBMS_SCHEDULER.CREATE_JOB (
            JOB_NAME => 'RECOVER_LOST_FORM_DATA',
            JOB_TYPE => 'STORED_PROCEDURE',
            JOB_ACTION => 'SP_RECOVER_LOST_FORM_DATA',
            START_DATE => SYSTIMESTAMP,
            REPEAT_INTERVAL => 'FREQ=DAILY;BYDAY=MON,TUE,WED,THU,FRI;BYHOUR=6;BYMINUTE=0;BYSECOND=0;',
            ENABLED => TRUE,
            COMMENTS => 'Oracle Job: Recover the lost form data');
END;
/