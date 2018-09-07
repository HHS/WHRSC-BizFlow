CREATE OR REPLACE VIEW VW_ACTIVE_RECRUITMENTS AS
--===========================================================
SELECT 
 MAIN_COMBINED.*,
 RECRUITMENT.DATE_REC_PCKG_SENT_APPROVED,
 NULL AS INVESTIGATION_COMPLETE
FROM  RECRUITMENT 
 LEFT JOIN 
  (SELECT  
    MAIN.TRANSACTION_ID,
     MAIN.HR_SPECIALIST,
     MAIN.ADMIN_CODE,
     MAIN.JOB_OPENING_ID,
      VAC_CERT_COMBINED.POSITION_TITLE,
      VAC_CERT_COMBINED.SERIES,
      VAC_CERT_COMBINED.GRADE,
      VAC_CERT_COMBINED.LOCATIONS,
      VAC_CERT_COMBINED.SPECIAL_TRACKING,
      VAC_CERT_COMBINED.ANNOUNCEMENT_TYPE,
      NVL(VAC_CERT_COMBINED.VACANCY_ANNOUNCEMENT_NUMBER, VAC_CERT_COMBINED.VACANCY_IDENTIFICATION_NUMBER) AS ANNOUNCEMENT_NUMBER_OR_VIN,
      VAC_CERT_COMBINED.NUMBER_OF_CERTS,
      MAIN.DATE_RECEIVED,
      MAIN.MISSING_DOCS_RECEIPT_DATE,
      VAC_CERT_COMBINED.DATE_ANN_APPROVED_BY_DEU,
      VAC_CERT_COMBINED.DATE_ANNOUNCEMENT_OPENED,
      VAC_CERT_COMBINED.DATE_ANNOUNCEMENT_CLOSED,
      VAC_CERT_COMBINED.FIRST_CERT_ISSUE_DATE,
      VAC_CERT_COMBINED.FIRST_CERT_RETURN_DATE,
      VAC_CERT_COMBINED.FIRST_CERT_RETURN_DATE+120 AS CERT_EXPIRE_DATE,
      NH_COMBINED.NH_CREATION_DATE,
      NH_COMBINED.SEND_TENTATV_OFFER_COMPLT_DATE,
      NH_COMBINED.BCKGRND_INVSTGTN_COMPLT_DATE,
      NH_COMBINED.SEND_OFFICL_OFFER_COMPLT_DATE,
      NH_COMBINED.NH_ACTUAL_START_DATE,
      VAC_CERT_COMBINED.DATE_ANNOUNCEMENT_POSTED,
      VAC_CERT_COMBINED.TOTAL_REFERRED_APPLICANTS,
      MAIN.COMMENTS_STATUS,
      MAIN.STATUS 
  FROM MAIN 
   LEFT JOIN
    (SELECT *
   FROM  DSS_VACANCY 
    LEFT JOIN 
    (SELECT  DSS_VACANCY_LOCATIONS.VACANCY_IDENTIFICATION_NUMBER, 
      LISTAGG( DSS_VACANCY_LOCATIONS.LOCATION_CITY , '; ')
      WITHIN GROUP (ORDER BY DSS_VACANCY_LOCATIONS.VACANCY_IDENTIFICATION_NUMBER ) LOCATIONS
      FROM  DSS_VACANCY_LOCATIONS 
      GROUP BY   DSS_VACANCY_LOCATIONS.VACANCY_IDENTIFICATION_NUMBER 
      ORDER BY   DSS_VACANCY_LOCATIONS.VACANCY_IDENTIFICATION_NUMBER 
     ) LOC
      ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = LOC.VACANCY_IDENTIFICATION_NUMBER  
    LEFT JOIN 
     (SELECT  A.VACANCY_IDENTIFICATION_NUMBER, 
      LISTAGG(A.SERIES , '; ')
      WITHIN GROUP (ORDER BY A.VACANCY_IDENTIFICATION_NUMBER ) SERIES
      FROM  
      (SELECT DISTINCT DSS_VACANCY_RATING_COMBINATION.VACANCY_IDENTIFICATION_NUMBER, 
      DSS_VACANCY_RATING_COMBINATION.SERIES FROM DSS_VACANCY_RATING_COMBINATION) A
      GROUP BY   A.VACANCY_IDENTIFICATION_NUMBER 
      ORDER BY   A.VACANCY_IDENTIFICATION_NUMBER    
     ) SER
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = SER.VACANCY_IDENTIFICATION_NUMBER
    LEFT JOIN
     (SELECT  B.VACANCY_IDENTIFICATION_NUMBER, 
      LISTAGG(B.GRADE , ';')
      WITHIN GROUP (ORDER BY B.VACANCY_IDENTIFICATION_NUMBER ) GRADE
      FROM  
      (SELECT DISTINCT DSS_VACANCY_RATING_COMBINATION.VACANCY_IDENTIFICATION_NUMBER,    
      TO_CHAR(DSS_VACANCY_RATING_COMBINATION.GRADE, '99') AS GRADE
       FROM DSS_VACANCY_RATING_COMBINATION) B
      GROUP BY   B.VACANCY_IDENTIFICATION_NUMBER 
      ORDER BY   B.VACANCY_IDENTIFICATION_NUMBER   
     ) GRA
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = GRA.VACANCY_IDENTIFICATION_NUMBER
    LEFT JOIN
     (SELECT  C.VACANCY_NUMBER, 
      LISTAGG(C.VCNCY_MISSION_CRITICAL_OCCUPTN , '; ')
      WITHIN GROUP (ORDER BY C.VACANCY_NUMBER ) SPECIAL_TRACKING
      FROM  
      (SELECT DISTINCT  DSS_VAC_MISSION_CRITCL_OCCUPTN.VACANCY_NUMBER, 
       DSS_VAC_MISSION_CRITCL_OCCUPTN.VCNCY_MISSION_CRITICAL_OCCUPTN
       FROM  DSS_VAC_MISSION_CRITCL_OCCUPTN ) C
      GROUP BY   C.VACANCY_NUMBER 
      ORDER BY   C.VACANCY_NUMBER   
     ) MCO
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = MCO.VACANCY_NUMBER
    LEFT JOIN  ANNOUNCEMENT 
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER =  ANNOUNCEMENT.VIN 
    LEFT JOIN  DSS_REQUEST_VACNCY_COMBINATION 
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = DSS_REQUEST_VACNCY_COMBINATION.VACANCY_NUMBER 
    LEFT JOIN
     (SELECT  DSS_CERTIFICATES.VACANCY_IDENTIFICATION_NUMBER, 
       COUNT( DSS_CERTIFICATES.CERTIFICATE_NUMBER) AS NUMBER_OF_CERTS, 
       MIN(DSS_CERTIFICATES.DATE_CERTIFICATE_ISSUED) AS FIRST_CERT_ISSUE_DATE,
       MIN(DSS_CERTIFICATES.DATE_HIRING_DECISN_RECD_IN_HR) AS FIRST_CERT_RETURN_DATE 
      FROM  DSS_CERTIFICATES 
       LEFT JOIN  CERTIFICATE 
        ON  DSS_CERTIFICATES.CERTIFICATE_NUMBER =  CERTIFICATE.CERT_NUMBER 
      GROUP BY  DSS_CERTIFICATES.VACANCY_IDENTIFICATION_NUMBER 
      ORDER BY DSS_CERTIFICATES.VACANCY_IDENTIFICATION_NUMBER    
     ) CERT_COMBINED
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = CERT_COMBINED.VACANCY_IDENTIFICATION_NUMBER  
    ) VAC_CERT_COMBINED
    ON  MAIN.JOB_OPENING_ID = VAC_CERT_COMBINED.REQUEST_NUMBER 
   LEFT JOIN (SELECT *
    FROM DSS_NEW_HIRES
    LEFT JOIN DSS_NEW_HIRE_TASKS 
     ON DSS_NEW_HIRES.NEW_HIRE_NUMBER = DSS_NEW_HIRE_TASKS.NEW_HIRE_NUMBER
    LEFT JOIN 
     (SELECT DISTINCT DSS_NEW_HIRE_VACANCY_REQUEST.NEW_HIRE_NUMBER, DSS_NEW_HIRE_VACANCY_REQUEST.NH_REQUEST_NUMBER
      FROM  DSS_NEW_HIRE_VACANCY_REQUEST 
     ) NHVR 
     ON DSS_NEW_HIRES.NEW_HIRE_NUMBER = NHVR.NEW_HIRE_NUMBER  
    ) NH_COMBINED
    ON MAIN.JOB_OPENING_ID =NH_COMBINED.NH_REQUEST_NUMBER
  ) MAIN_COMBINED
  ON  RECRUITMENT.TRANSACTION_ID = MAIN_COMBINED.TRANSACTION_ID
  WHERE STATUS = 'ACTIVE';
--===========================================================
CREATE OR REPLACE VIEW VW_ALL_RECRUITMENTS AS
--===========================================================
SELECT 
 MAIN_COMBINED.*,
 RECRUITMENT.DATE_REC_PCKG_SENT_APPROVED,
 NULL AS INVESTIGATION_COMPLETE
FROM  RECRUITMENT 
 LEFT JOIN 
  (SELECT  
    MAIN.TRANSACTION_ID,
     MAIN.HR_SPECIALIST,
     MAIN.ADMIN_CODE,
     MAIN.JOB_OPENING_ID,
      VAC_CERT_COMBINED.POSITION_TITLE,
      VAC_CERT_COMBINED.SERIES,
      VAC_CERT_COMBINED.GRADE,
      VAC_CERT_COMBINED.LOCATIONS,
      VAC_CERT_COMBINED.SPECIAL_TRACKING,
      VAC_CERT_COMBINED.ANNOUNCEMENT_TYPE,
      NVL(VAC_CERT_COMBINED.VACANCY_ANNOUNCEMENT_NUMBER, VAC_CERT_COMBINED.VACANCY_IDENTIFICATION_NUMBER) AS ANNOUNCEMENT_NUMBER_OR_VIN,
      VAC_CERT_COMBINED.NUMBER_OF_CERTS,
      MAIN.DATE_RECEIVED,
      MAIN.MISSING_DOCS_RECEIPT_DATE,
      VAC_CERT_COMBINED.DATE_ANN_APPROVED_BY_DEU,
      VAC_CERT_COMBINED.DATE_ANNOUNCEMENT_OPENED,
      VAC_CERT_COMBINED.DATE_ANNOUNCEMENT_CLOSED,
      VAC_CERT_COMBINED.FIRST_CERT_ISSUE_DATE,
      VAC_CERT_COMBINED.FIRST_CERT_RETURN_DATE,
      VAC_CERT_COMBINED.FIRST_CERT_RETURN_DATE+120 AS CERT_EXPIRE_DATE,
      NH_COMBINED.NH_CREATION_DATE,
      NH_COMBINED.SEND_TENTATV_OFFER_COMPLT_DATE,
      NH_COMBINED.BCKGRND_INVSTGTN_COMPLT_DATE,
      NH_COMBINED.SEND_OFFICL_OFFER_COMPLT_DATE,
      NH_COMBINED.NH_ACTUAL_START_DATE,
      VAC_CERT_COMBINED.DATE_ANNOUNCEMENT_POSTED,
      VAC_CERT_COMBINED.TOTAL_REFERRED_APPLICANTS,
      MAIN.COMMENTS_STATUS,
      MAIN.STATUS 
  FROM MAIN 
   LEFT JOIN
    (SELECT *
   FROM  DSS_VACANCY 
    LEFT JOIN 
    (SELECT  DSS_VACANCY_LOCATIONS.VACANCY_IDENTIFICATION_NUMBER, 
      LISTAGG( DSS_VACANCY_LOCATIONS.LOCATION_CITY , '; ')
      WITHIN GROUP (ORDER BY DSS_VACANCY_LOCATIONS.VACANCY_IDENTIFICATION_NUMBER ) LOCATIONS
      FROM  DSS_VACANCY_LOCATIONS 
      GROUP BY   DSS_VACANCY_LOCATIONS.VACANCY_IDENTIFICATION_NUMBER 
      ORDER BY   DSS_VACANCY_LOCATIONS.VACANCY_IDENTIFICATION_NUMBER 
     ) LOC
      ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = LOC.VACANCY_IDENTIFICATION_NUMBER  
    LEFT JOIN 
     (SELECT  A.VACANCY_IDENTIFICATION_NUMBER, 
      LISTAGG(A.SERIES , '; ')
      WITHIN GROUP (ORDER BY A.VACANCY_IDENTIFICATION_NUMBER ) SERIES
      FROM  
      (SELECT DISTINCT DSS_VACANCY_RATING_COMBINATION.VACANCY_IDENTIFICATION_NUMBER, 
      DSS_VACANCY_RATING_COMBINATION.SERIES FROM DSS_VACANCY_RATING_COMBINATION) A
      GROUP BY   A.VACANCY_IDENTIFICATION_NUMBER 
      ORDER BY   A.VACANCY_IDENTIFICATION_NUMBER    
     ) SER
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = SER.VACANCY_IDENTIFICATION_NUMBER
    LEFT JOIN
     (SELECT  B.VACANCY_IDENTIFICATION_NUMBER, 
      LISTAGG(B.GRADE , ';')
      WITHIN GROUP (ORDER BY B.VACANCY_IDENTIFICATION_NUMBER ) GRADE
      FROM  
      (SELECT DISTINCT DSS_VACANCY_RATING_COMBINATION.VACANCY_IDENTIFICATION_NUMBER,    
      TO_CHAR(DSS_VACANCY_RATING_COMBINATION.GRADE, '99') AS GRADE
       FROM DSS_VACANCY_RATING_COMBINATION) B
      GROUP BY   B.VACANCY_IDENTIFICATION_NUMBER 
      ORDER BY   B.VACANCY_IDENTIFICATION_NUMBER   
     ) GRA
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = GRA.VACANCY_IDENTIFICATION_NUMBER
    LEFT JOIN
     (SELECT  C.VACANCY_NUMBER, 
      LISTAGG(C.VCNCY_MISSION_CRITICAL_OCCUPTN , '; ')
      WITHIN GROUP (ORDER BY C.VACANCY_NUMBER ) SPECIAL_TRACKING
      FROM  
      (SELECT DISTINCT  DSS_VAC_MISSION_CRITCL_OCCUPTN.VACANCY_NUMBER, 
       DSS_VAC_MISSION_CRITCL_OCCUPTN.VCNCY_MISSION_CRITICAL_OCCUPTN
       FROM  DSS_VAC_MISSION_CRITCL_OCCUPTN ) C
      GROUP BY   C.VACANCY_NUMBER 
      ORDER BY   C.VACANCY_NUMBER   
     ) MCO
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = MCO.VACANCY_NUMBER
    LEFT JOIN  ANNOUNCEMENT 
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER =  ANNOUNCEMENT.VIN 
    LEFT JOIN  DSS_REQUEST_VACNCY_COMBINATION 
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = DSS_REQUEST_VACNCY_COMBINATION.VACANCY_NUMBER 
    LEFT JOIN
     (SELECT  DSS_CERTIFICATES.VACANCY_IDENTIFICATION_NUMBER, 
       COUNT( DSS_CERTIFICATES.CERTIFICATE_NUMBER) AS NUMBER_OF_CERTS, 
       MIN(DSS_CERTIFICATES.DATE_CERTIFICATE_ISSUED) AS FIRST_CERT_ISSUE_DATE,
       MIN(DSS_CERTIFICATES.DATE_HIRING_DECISN_RECD_IN_HR) AS FIRST_CERT_RETURN_DATE 
      FROM  DSS_CERTIFICATES 
       LEFT JOIN  CERTIFICATE 
        ON  DSS_CERTIFICATES.CERTIFICATE_NUMBER =  CERTIFICATE.CERT_NUMBER 
      GROUP BY  DSS_CERTIFICATES.VACANCY_IDENTIFICATION_NUMBER 
      ORDER BY DSS_CERTIFICATES.VACANCY_IDENTIFICATION_NUMBER    
     ) CERT_COMBINED
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = CERT_COMBINED.VACANCY_IDENTIFICATION_NUMBER  
    ) VAC_CERT_COMBINED
    ON  MAIN.JOB_OPENING_ID = VAC_CERT_COMBINED.REQUEST_NUMBER 
   LEFT JOIN (SELECT *
    FROM DSS_NEW_HIRES
    LEFT JOIN DSS_NEW_HIRE_TASKS 
     ON DSS_NEW_HIRES.NEW_HIRE_NUMBER = DSS_NEW_HIRE_TASKS.NEW_HIRE_NUMBER
    LEFT JOIN 
     (SELECT DISTINCT DSS_NEW_HIRE_VACANCY_REQUEST.NEW_HIRE_NUMBER, DSS_NEW_HIRE_VACANCY_REQUEST.NH_REQUEST_NUMBER
      FROM  DSS_NEW_HIRE_VACANCY_REQUEST 
     ) NHVR 
     ON DSS_NEW_HIRES.NEW_HIRE_NUMBER = NHVR.NEW_HIRE_NUMBER  
    ) NH_COMBINED
    ON MAIN.JOB_OPENING_ID =NH_COMBINED.NH_REQUEST_NUMBER
  ) MAIN_COMBINED
  ON  RECRUITMENT.TRANSACTION_ID = MAIN_COMBINED.TRANSACTION_ID;
--===================================================================
CREATE OR REPLACE VIEW VW_ACTIVE_RECRUITMENTS_EXPAND AS
--===================================================================
SELECT 
 MAIN_COMBINED.*,
  RECRUITMENT.RECRUITMENT_TYPE,
  RECRUITMENT.LEGISLATIVE_INIT_SUPPORTED, 
 RECRUITMENT.DATE_REC_PCKG_SENT_APPROVED,
 NULL AS INVESTIGATION_COMPLETE
FROM  RECRUITMENT 
 LEFT JOIN 
  (SELECT  
   MAIN.TRANSACTION_ID,
   MAIN.JOB_OPENING_ID,
   BRANCH.BRANCH, 
   MAIN.INSTITUTE,
   MAIN.ADMIN_CODE,
   MAIN.ORG_INITS,
   MAIN.HR_SPECIALIST,
   MAIN.GLOBAL_RECRUITMENT, 
    VAC_CERT_COMBINED.ANNOUNCEMENT_TYPE,
    VAC_CERT_COMBINED.OPEN_CONTINUOUS_ANN, 
    VAC_CERT_COMBINED.SPECIAL_TRACKING,
    VAC_CERT_COMBINED.LOCATIONS,
    VAC_CERT_COMBINED.POSITION_TITLE,
    VAC_CERT_COMBINED.PAY_PLAN,
    VAC_CERT_COMBINED.SERIES,
    VAC_CERT_COMBINED.GRADE,
    VAC_CERT_COMBINED.FULL_PERFORMANCE_LEVEL, 
    MAIN.DATE_RECEIVED,
    MAIN.MISSING_DOCS_RECEIPT_DATE,
    MAIN.COMMENTS_STATUS,
    MAIN.RYB_STATUS,
     MAIN.STATUS 
  FROM MAIN 
   LEFT JOIN
    (SELECT *
   FROM  DSS_VACANCY 
    LEFT JOIN 
    (SELECT  DSS_VACANCY_LOCATIONS.VACANCY_IDENTIFICATION_NUMBER, 
      LISTAGG( DSS_VACANCY_LOCATIONS.LOCATION_CITY , '; ')
      WITHIN GROUP (ORDER BY DSS_VACANCY_LOCATIONS.VACANCY_IDENTIFICATION_NUMBER ) LOCATIONS
      FROM  DSS_VACANCY_LOCATIONS 
      GROUP BY   DSS_VACANCY_LOCATIONS.VACANCY_IDENTIFICATION_NUMBER 
      ORDER BY   DSS_VACANCY_LOCATIONS.VACANCY_IDENTIFICATION_NUMBER 
     ) LOC
      ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = LOC.VACANCY_IDENTIFICATION_NUMBER  
    LEFT JOIN 
     (SELECT  A.VACANCY_IDENTIFICATION_NUMBER, 
      LISTAGG(A.SERIES , ',')
      WITHIN GROUP (ORDER BY A.VACANCY_IDENTIFICATION_NUMBER ) SERIES
      FROM  
      (SELECT DISTINCT DSS_VACANCY_RATING_COMBINATION.VACANCY_IDENTIFICATION_NUMBER, 
      DSS_VACANCY_RATING_COMBINATION.SERIES FROM DSS_VACANCY_RATING_COMBINATION) A
      GROUP BY   A.VACANCY_IDENTIFICATION_NUMBER 
      ORDER BY   A.VACANCY_IDENTIFICATION_NUMBER    
     ) SER
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = SER.VACANCY_IDENTIFICATION_NUMBER
    LEFT JOIN
     (SELECT  B.VACANCY_IDENTIFICATION_NUMBER, 
      LISTAGG(B.GRADE , ',')
      WITHIN GROUP (ORDER BY B.VACANCY_IDENTIFICATION_NUMBER ) GRADE
      FROM  
      (SELECT DISTINCT DSS_VACANCY_RATING_COMBINATION.VACANCY_IDENTIFICATION_NUMBER,    
      TO_CHAR(DSS_VACANCY_RATING_COMBINATION.GRADE, '99') AS GRADE
       FROM DSS_VACANCY_RATING_COMBINATION) B
      GROUP BY   B.VACANCY_IDENTIFICATION_NUMBER 
      ORDER BY   B.VACANCY_IDENTIFICATION_NUMBER   
     ) GRA
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = GRA.VACANCY_IDENTIFICATION_NUMBER
    LEFT JOIN
     (SELECT  D.VACANCY_IDENTIFICATION_NUMBER, 
      LISTAGG(D.PAY_PLAN , ',')
      WITHIN GROUP (ORDER BY D.VACANCY_IDENTIFICATION_NUMBER ) PAY_PLAN
      FROM  
      (SELECT DISTINCT DSS_VACANCY_RATING_COMBINATION.VACANCY_IDENTIFICATION_NUMBER,    
      (DSS_VACANCY_RATING_COMBINATION.PAY_PLAN) AS PAY_PLAN
       FROM DSS_VACANCY_RATING_COMBINATION) D
      GROUP BY   D.VACANCY_IDENTIFICATION_NUMBER 
      ORDER BY   D.VACANCY_IDENTIFICATION_NUMBER   
     ) PP
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = PP.VACANCY_IDENTIFICATION_NUMBER
    
    LEFT JOIN
     (SELECT  C.VACANCY_NUMBER, 
      LISTAGG(C.VCNCY_MISSION_CRITICAL_OCCUPTN , ';')
      WITHIN GROUP (ORDER BY C.VACANCY_NUMBER ) SPECIAL_TRACKING
      FROM  
      (SELECT DISTINCT  DSS_VAC_MISSION_CRITCL_OCCUPTN.VACANCY_NUMBER, 
       DSS_VAC_MISSION_CRITCL_OCCUPTN.VCNCY_MISSION_CRITICAL_OCCUPTN
       FROM  DSS_VAC_MISSION_CRITCL_OCCUPTN ) C
      GROUP BY   C.VACANCY_NUMBER 
      ORDER BY   C.VACANCY_NUMBER   
     ) MCO
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = MCO.VACANCY_NUMBER
    LEFT JOIN  ANNOUNCEMENT 
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER =  ANNOUNCEMENT.VIN 
    LEFT JOIN  DSS_REQUEST_VACNCY_COMBINATION 
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = DSS_REQUEST_VACNCY_COMBINATION.VACANCY_NUMBER 
    LEFT JOIN
     (SELECT  DSS_CERTIFICATES.VACANCY_IDENTIFICATION_NUMBER, 
       COUNT( DSS_CERTIFICATES.CERTIFICATE_NUMBER) AS NUMBER_OF_CERTS, 
       MIN(DSS_CERTIFICATES.DATE_CERTIFICATE_ISSUED) AS FIRST_CERT_ISSUE_DATE,
       MIN(DSS_CERTIFICATES.DATE_HIRING_DECISN_RECD_IN_HR) AS FIRST_CERT_RETURN_DATE 
      FROM  DSS_CERTIFICATES 
       LEFT JOIN  CERTIFICATE 
        ON  DSS_CERTIFICATES.CERTIFICATE_NUMBER =  CERTIFICATE.CERT_NUMBER 
      GROUP BY  DSS_CERTIFICATES.VACANCY_IDENTIFICATION_NUMBER 
      ORDER BY DSS_CERTIFICATES.VACANCY_IDENTIFICATION_NUMBER    
     ) CERT_COMBINED
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = CERT_COMBINED.VACANCY_IDENTIFICATION_NUMBER  
    ) VAC_CERT_COMBINED
    ON  MAIN.JOB_OPENING_ID = VAC_CERT_COMBINED.REQUEST_NUMBER 
   LEFT JOIN (SELECT *
    FROM DSS_NEW_HIRES
    LEFT JOIN DSS_NEW_HIRE_TASKS 
     ON DSS_NEW_HIRES.NEW_HIRE_NUMBER = DSS_NEW_HIRE_TASKS.NEW_HIRE_NUMBER
    LEFT JOIN 
     (SELECT DISTINCT DSS_NEW_HIRE_VACANCY_REQUEST.NEW_HIRE_NUMBER, DSS_NEW_HIRE_VACANCY_REQUEST.NH_REQUEST_NUMBER
      FROM  DSS_NEW_HIRE_VACANCY_REQUEST 
     ) NHVR 
     ON DSS_NEW_HIRES.NEW_HIRE_NUMBER = NHVR.NEW_HIRE_NUMBER  
    ) NH_COMBINED
    ON MAIN.JOB_OPENING_ID =NH_COMBINED.NH_REQUEST_NUMBER
   LEFT JOIN   
    (SELECT DISTINCT BRANCHES.IC , BRANCHES.BRANCH
    FROM  BRANCHES )BRANCH
    ON   MAIN.INSTITUTE = BRANCH.IC 
  ) MAIN_COMBINED
  ON  RECRUITMENT.TRANSACTION_ID = MAIN_COMBINED.TRANSACTION_ID
 
  WHERE STATUS = 'ACTIVE';
--===================================================================
CREATE OR REPLACE VIEW VW_ALL_RECRUITMENTS_EXPAND AS
--===================================================================
SELECT 
 MAIN_COMBINED.*,
  RECRUITMENT.RECRUITMENT_TYPE,
  RECRUITMENT.LEGISLATIVE_INIT_SUPPORTED, 
 RECRUITMENT.DATE_REC_PCKG_SENT_APPROVED,
 NULL AS INVESTIGATION_COMPLETE
FROM  RECRUITMENT 
 LEFT JOIN 
  (SELECT  
   MAIN.TRANSACTION_ID,
   MAIN.JOB_OPENING_ID,
   BRANCH.BRANCH, 
   MAIN.INSTITUTE,
   MAIN.ADMIN_CODE,
   MAIN.ORG_INITS,
   MAIN.HR_SPECIALIST,
   MAIN.GLOBAL_RECRUITMENT, 
    VAC_CERT_COMBINED.ANNOUNCEMENT_TYPE,
    VAC_CERT_COMBINED.OPEN_CONTINUOUS_ANN, 
    VAC_CERT_COMBINED.SPECIAL_TRACKING,
    VAC_CERT_COMBINED.LOCATIONS,
    VAC_CERT_COMBINED.POSITION_TITLE,
    VAC_CERT_COMBINED.PAY_PLAN,
    VAC_CERT_COMBINED.SERIES,
    VAC_CERT_COMBINED.GRADE,
    VAC_CERT_COMBINED.FULL_PERFORMANCE_LEVEL, 
    MAIN.DATE_RECEIVED,
    MAIN.MISSING_DOCS_RECEIPT_DATE,
    MAIN.COMMENTS_STATUS,
    MAIN.RYB_STATUS,
     MAIN.STATUS 
  FROM MAIN 
   LEFT JOIN
    (SELECT *
   FROM  DSS_VACANCY 
    LEFT JOIN 
    (SELECT  DSS_VACANCY_LOCATIONS.VACANCY_IDENTIFICATION_NUMBER, 
      LISTAGG( DSS_VACANCY_LOCATIONS.LOCATION_CITY , '; ')
      WITHIN GROUP (ORDER BY DSS_VACANCY_LOCATIONS.VACANCY_IDENTIFICATION_NUMBER ) LOCATIONS
      FROM  DSS_VACANCY_LOCATIONS 
      GROUP BY   DSS_VACANCY_LOCATIONS.VACANCY_IDENTIFICATION_NUMBER 
      ORDER BY   DSS_VACANCY_LOCATIONS.VACANCY_IDENTIFICATION_NUMBER 
     ) LOC
      ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = LOC.VACANCY_IDENTIFICATION_NUMBER  
    LEFT JOIN 
     (SELECT  A.VACANCY_IDENTIFICATION_NUMBER, 
      LISTAGG(A.SERIES , ',')
      WITHIN GROUP (ORDER BY A.VACANCY_IDENTIFICATION_NUMBER ) SERIES
      FROM  
      (SELECT DISTINCT DSS_VACANCY_RATING_COMBINATION.VACANCY_IDENTIFICATION_NUMBER, 
      DSS_VACANCY_RATING_COMBINATION.SERIES FROM DSS_VACANCY_RATING_COMBINATION) A
      GROUP BY   A.VACANCY_IDENTIFICATION_NUMBER 
      ORDER BY   A.VACANCY_IDENTIFICATION_NUMBER    
     ) SER
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = SER.VACANCY_IDENTIFICATION_NUMBER
    LEFT JOIN
     (SELECT  B.VACANCY_IDENTIFICATION_NUMBER, 
      LISTAGG(B.GRADE , ',')
      WITHIN GROUP (ORDER BY B.VACANCY_IDENTIFICATION_NUMBER ) GRADE
      FROM  
      (SELECT DISTINCT DSS_VACANCY_RATING_COMBINATION.VACANCY_IDENTIFICATION_NUMBER,    
      TO_CHAR(DSS_VACANCY_RATING_COMBINATION.GRADE, '99') AS GRADE
       FROM DSS_VACANCY_RATING_COMBINATION) B
      GROUP BY   B.VACANCY_IDENTIFICATION_NUMBER 
      ORDER BY   B.VACANCY_IDENTIFICATION_NUMBER   
     ) GRA
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = GRA.VACANCY_IDENTIFICATION_NUMBER
    LEFT JOIN
     (SELECT  D.VACANCY_IDENTIFICATION_NUMBER, 
      LISTAGG(D.PAY_PLAN , ',')
      WITHIN GROUP (ORDER BY D.VACANCY_IDENTIFICATION_NUMBER ) PAY_PLAN
      FROM  
      (SELECT DISTINCT DSS_VACANCY_RATING_COMBINATION.VACANCY_IDENTIFICATION_NUMBER,    
      (DSS_VACANCY_RATING_COMBINATION.PAY_PLAN) AS PAY_PLAN
       FROM DSS_VACANCY_RATING_COMBINATION) D
      GROUP BY   D.VACANCY_IDENTIFICATION_NUMBER 
      ORDER BY   D.VACANCY_IDENTIFICATION_NUMBER   
     ) PP
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = PP.VACANCY_IDENTIFICATION_NUMBER
    
    LEFT JOIN
     (SELECT  C.VACANCY_NUMBER, 
      LISTAGG(C.VCNCY_MISSION_CRITICAL_OCCUPTN , ';')
      WITHIN GROUP (ORDER BY C.VACANCY_NUMBER ) SPECIAL_TRACKING
      FROM  
      (SELECT DISTINCT  DSS_VAC_MISSION_CRITCL_OCCUPTN.VACANCY_NUMBER, 
       DSS_VAC_MISSION_CRITCL_OCCUPTN.VCNCY_MISSION_CRITICAL_OCCUPTN
       FROM  DSS_VAC_MISSION_CRITCL_OCCUPTN ) C
      GROUP BY   C.VACANCY_NUMBER 
      ORDER BY   C.VACANCY_NUMBER   
     ) MCO
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = MCO.VACANCY_NUMBER
    LEFT JOIN  ANNOUNCEMENT 
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER =  ANNOUNCEMENT.VIN 
    LEFT JOIN  DSS_REQUEST_VACNCY_COMBINATION 
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = DSS_REQUEST_VACNCY_COMBINATION.VACANCY_NUMBER 
    LEFT JOIN
     (SELECT  DSS_CERTIFICATES.VACANCY_IDENTIFICATION_NUMBER, 
       COUNT( DSS_CERTIFICATES.CERTIFICATE_NUMBER) AS NUMBER_OF_CERTS, 
       MIN(DSS_CERTIFICATES.DATE_CERTIFICATE_ISSUED) AS FIRST_CERT_ISSUE_DATE,
       MIN(DSS_CERTIFICATES.DATE_HIRING_DECISN_RECD_IN_HR) AS FIRST_CERT_RETURN_DATE 
      FROM  DSS_CERTIFICATES 
       LEFT JOIN  CERTIFICATE 
        ON  DSS_CERTIFICATES.CERTIFICATE_NUMBER =  CERTIFICATE.CERT_NUMBER 
      GROUP BY  DSS_CERTIFICATES.VACANCY_IDENTIFICATION_NUMBER 
      ORDER BY DSS_CERTIFICATES.VACANCY_IDENTIFICATION_NUMBER    
     ) CERT_COMBINED
     ON DSS_VACANCY.VACANCY_IDENTIFICATION_NUMBER = CERT_COMBINED.VACANCY_IDENTIFICATION_NUMBER  
    ) VAC_CERT_COMBINED
    ON  MAIN.JOB_OPENING_ID = VAC_CERT_COMBINED.REQUEST_NUMBER 
   LEFT JOIN (SELECT *
    FROM DSS_NEW_HIRES
    LEFT JOIN DSS_NEW_HIRE_TASKS 
     ON DSS_NEW_HIRES.NEW_HIRE_NUMBER = DSS_NEW_HIRE_TASKS.NEW_HIRE_NUMBER
    LEFT JOIN 
     (SELECT DISTINCT DSS_NEW_HIRE_VACANCY_REQUEST.NEW_HIRE_NUMBER, DSS_NEW_HIRE_VACANCY_REQUEST.NH_REQUEST_NUMBER
      FROM  DSS_NEW_HIRE_VACANCY_REQUEST 
     ) NHVR 
     ON DSS_NEW_HIRES.NEW_HIRE_NUMBER = NHVR.NEW_HIRE_NUMBER  
    ) NH_COMBINED
    ON MAIN.JOB_OPENING_ID =NH_COMBINED.NH_REQUEST_NUMBER
   LEFT JOIN   
    (SELECT DISTINCT BRANCHES.IC , BRANCHES.BRANCH
    FROM  BRANCHES )BRANCH
    ON   MAIN.INSTITUTE = BRANCH.IC 
  ) MAIN_COMBINED
  ON  RECRUITMENT.TRANSACTION_ID = MAIN_COMBINED.TRANSACTION_ID;
--====================================================