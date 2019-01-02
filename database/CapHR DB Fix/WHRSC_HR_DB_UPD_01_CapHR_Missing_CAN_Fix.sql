--SCRIPT FOR UPDATING MISSING CAN_NUMBER IN HHS_WHRSC_HR.CHR_EMPLOYEE_INFO TABLE FOR OLD RECORDS
  ----------------------------------------------------------------------------------------------
  ----------------------------------------------------------------------------------------------
  
--STEP 1: CREATE TEMP TABLE---------------------
  CREATE TABLE HHS_WHRSC_HR.TEMP_UPDATE
  AS 
  SELECT  JO.HRS_JOB_OPENING_ID AS ID, RE.ACCT_CD AS CAN
      FROM    HHS_HR.PS_HRS_JOB_OPENING JO
        LEFT JOIN HHS_HR.PS_HE_RECRUIT_EWIT RE
          ON JO.HRS_JOB_OPENING_ID = RE.HRS_JOB_OPENING_ID
      WHERE 
      TO_CHAR(JO.HRS_JOB_OPENING_ID) IN (SELECT ID FROM HHS_WHRSC_HR.CHR_EMPLOYEE_INFO WHERE CAN_NUMBER IS NULL);
      
--STEP 2: UPDATE CAN_NUMBER IN TARGET TABLE------
  UPDATE CHR_EMPLOYEE_INFO t1
    SET t1.CAN_NUMBER = (SELECT t2.CAN
                           FROM TEMP_UPDATE t2
                          WHERE t1.ID = t2.ID)
    WHERE EXISTS (
      SELECT 1
        FROM TEMP_UPDATE t2
       WHERE t1.ID = t2.ID );
      
--STEP 3: DROP TEMP TABLE------------------------
  DROP TABLE HHS_WHRSC_HR.TEMP_UPDATE;
      
  COMMIT; 
-------------------------------------------------  


MERGE INTO hhs_whrsc_hr.main m
USING ( select id, can_number
          from hhs_whrsc_hr.chr_employee_info  ) c
ON ( m.job_opening_id = c.id  
and c.can_number is not null)
WHEN MATCHED THEN 
UPDATE SET  m.can_no = c.can_number;
commit;
