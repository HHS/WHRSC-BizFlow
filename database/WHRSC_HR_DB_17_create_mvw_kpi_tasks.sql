CREATE MATERIALIZED VIEW HHS_WHRSC_HR.MVW_KPI_TASKS
REFRESH COMPLETE ON DEMAND START WITH sysdate+0 NEXT (SYSDATE+15/1440)
AS SELECT date_range_input.*,
                             r.*,
                             TASK_DUE_DATE - TASK_START_DATE + 1 AS TASK_NUMBER_OF_DAYS,
                             ROUND(TASK_HOURS_TO_COMPLETE / (TASK_DUE_DATE - TASK_START_DATE + 1), 2) AS TASK_DAILY_LOAD,
                             TASK || ' for ' || NVL(ANN_NUMBER_OR_VIN,'req ' || JOB_OPENING_ID) || ' (' || ROUND(TASK_HOURS_TO_COMPLETE / (TASK_DUE_DATE - TASK_START_DATE + 1), 2) || ' hours)' AS ACTION_DESC
              FROM 
                             (SELECT (to_date('04-12-2032','DD-MM-YYYY') - level + 1) AS DATES
                                           FROM
                                             dual
                                           CONNECT BY LEVEL <= (to_date('04-12-2032','DD-MM-YYYY') - to_date('04-12-2017','DD-MM-YYYY') + 1)) date_range_input
              INNER JOIN 
                             (SELECT TRANSACTION_ID,
                                           ACTION_TYPE,
                                           JOB_OPENING_ID,
                                           RECRUITMENT_TYPE,
                                           ANN_NUMBER_OR_VIN,
                                           TASK,
                                           CASE WHEN TASK = 'Draft Package' THEN HR_SPECIALIST
                                                          WHEN TASK = 'Review Package' THEN ANN_QUAL_REVIEWER
                                                          WHEN TASK = 'Build Announcement' THEN HR_SPECIALIST
                                                          WHEN TASK = 'QR Announcement' THEN ANN_QUAL_REVIEWER
                                                          WHEN TASK = 'Review Quals' THEN HR_SPECIALIST
                                                          WHEN TASK = 'QR Quals/Issue Certs' THEN QUALITY_REVIEWER
                                                          END KPI_TASK_ASSIGNEE,
                                           CASE WHEN TASK = 'Draft Package' THEN DATE_RECRUITMENT_AUTHORIZED + 1
                                                          WHEN TASK = 'Review Package' AND RECRUITMENT_TYPE LIKE '%Complex%' THEN PACKAGE_DRAFT_DUE_DATE_DUPE + 1
                                                          WHEN TASK = 'Build Announcement' AND RECRUITMENT_TYPE = 'Standard' THEN DATE_RECRUITMENT_AUTHORIZED + 1
                                                          WHEN TASK = 'Build Announcement' AND RECRUITMENT_TYPE LIKE '%Complex%' THEN PACKAGE_QR_DUE_DATE_DUPE + 1
                                                          WHEN TASK = 'QR Announcement' THEN ANN_BUILT_DUE_DATE_DUPE + 1
                                                          WHEN TASK = 'Review Quals' THEN ANN_CLOSED_DUE_DATE_DUPE + 1
                                                          WHEN TASK = 'QR Quals/Issue Certs' THEN INITIAL_QUALS_DUE_DATE_DUPE + 1
                                                          END TASK_START_DATE,
                                           TASK_DUE_DATE,
                                           CASE WHEN TASK = 'Draft Package' AND RECRUITMENT_TYPE LIKE '%Complex%' THEN 12
                                                          WHEN TASK = 'Review Package' THEN 4.5
                                                          WHEN TASK = 'Build Announcement' AND RECRUITMENT_TYPE = 'Standard' THEN 3
                                                          WHEN TASK = 'Build Announcement' AND OTHER_RECRUIT_CONSIDERATIONS = 'Direct Hire' THEN 2
                                                          WHEN TASK = 'Build Announcement' AND RECRUITMENT_TYPE LIKE '%Complex%' THEN 5
                                                          WHEN TASK = 'QR Announcement' THEN 1
                                                          WHEN TASK = 'Review Quals' AND RECRUITMENT_TYPE = 'Standard' THEN 7.6
                                                          WHEN TASK = 'Review Quals' AND OTHER_RECRUIT_CONSIDERATIONS = 'Direct Hire' THEN 31.36
                                                          WHEN TASK = 'Review Quals' AND RECRUITMENT_TYPE LIKE '%Complex%' THEN 18
                                                          WHEN TASK = 'QR Quals/Issue Certs' AND RECRUITMENT_TYPE = 'Standard' THEN 3.04
                                                          WHEN TASK = 'QR Quals/Issue Certs' AND OTHER_RECRUIT_CONSIDERATIONS = 'Direct Hire' THEN 12.544
                                                          WHEN TASK = 'QR Quals/Issue Certs' AND RECRUITMENT_TYPE LIKE '%Complex%' THEN 7.2
                                                          END TASK_HOURS_TO_COMPLETE
                             FROM    (
                                           SELECT * FROM
                                                          (SELECT p.*,
                                                                        CASE WHEN p.ACTION_TYPE = 'Appointment' AND NH_PROJECTED_START_DATE IS NOT NULL THEN 'All KPIs Complete'
                                                                                      WHEN p.ACTION_TYPE = 'Appointment' AND SEND_OFFICL_OFFER_COMPLT_DATE IS NOT NULL THEN 'Set EOD'
                                                                                      /*WHEN p.ACTION_TYPE = 'Appointment' AND DATE_DPSAC_NOTIFICATION_RCVD IS NOT NULL THEN 'Send Final Offer (if Cleared)'*/
                                                                                      WHEN p.ACTION_TYPE = 'Appointment' AND SEND_TENTATV_OFFER_COMPLT_DATE IS NOT NULL THEN 'Pending Security'
                                                                                      WHEN p.ACTION_TYPE = 'Appointment' AND SEND_TENTATV_OFFER_COMPLT_DATE IS NULL THEN 'Send TJO'
                                                                                      WHEN p.ACTION_TYPE = 'Recruitment' AND FIRST_CERT_ISSUE_DATE IS NOT NULL THEN 'All KPIs Complete'
                                                                                      WHEN p.ACTION_TYPE = 'Recruitment' AND DATE_QUAL_SENT_DEU IS NOT NULL THEN 'QR Quals/Issue Certs'
                                                                                      WHEN p.ACTION_TYPE = 'Recruitment' AND DATE_ANNOUNCEMENT_CLOSED IS NOT NULL AND DATE_ANNOUNCEMENT_CLOSED < CURRENT_DATE THEN 'Review Quals'
                                                                                      WHEN p.ACTION_TYPE = 'Recruitment' AND DATE_ANNOUNCEMENT_POSTED IS NOT NULL THEN 'Wait for Announcement to Close'
                                                                                      WHEN p.ACTION_TYPE = 'Recruitment' AND DATE_ANN_APPROVED_BY_DEU IS NOT NULL THEN 'Post Announcement'
                                                                                      WHEN p.ACTION_TYPE = 'Recruitment' AND DATE_SENT_TO_DEU IS NOT NULL THEN 'QR Announcement'
                                                                                      WHEN p.ACTION_TYPE = 'Recruitment' AND DATE_REC_PCKG_SENT_APPROVED IS NOT NULL AND RECRUITMENT_TYPE LIKE '%Complex%' THEN 'Build Announcement'
                                                                                      WHEN p.ACTION_TYPE = 'Recruitment' AND DATE_REC_PCKG_SENT_DEU IS NOT NULL AND RECRUITMENT_TYPE LIKE '%Complex%' THEN 'Review Package'
                                                                                      WHEN p.ACTION_TYPE = 'Recruitment' AND DATE_REC_PCKG_SENT_DEU IS NULL AND RECRUITMENT_TYPE LIKE '%Complex%' THEN 'Draft Package'
                                                                                      WHEN p.ACTION_TYPE = 'Recruitment' AND DATE_ANNOUNCEMENT_POSTED IS NULL AND RECRUITMENT_TYPE = 'Standard' THEN 'Build Announcement'
                                                                                      END NEXT_KPI_STEP,
                                                                        CASE WHEN p.ACTION_TYPE = 'Appointment' AND NH_PROJECTED_START_DATE IS NOT NULL THEN NULL
                                                                                      WHEN p.ACTION_TYPE = 'Appointment' AND SEND_OFFICL_OFFER_COMPLT_DATE IS NOT NULL THEN SEND_OFFICL_OFFER_COMPLT_DATE
                                                                                      /*WHEN p.ACTION_TYPE = 'Appointment' AND DATE_DPSAC_NOTIFICATION_RCVD IS NOT NULL THEN DATE_DPSAC_NOTIFICATION_RCVD + 3 /* Replace with FINAL_OFFER_DUE_DATE after BITS integration */
                                                                                      WHEN p.ACTION_TYPE = 'Appointment' AND SEND_TENTATV_OFFER_COMPLT_DATE IS NOT NULL THEN NULL
                                                                                      WHEN p.ACTION_TYPE = 'Appointment' AND SEND_TENTATV_OFFER_COMPLT_DATE IS NULL THEN TENTATIVE_OFFER_DUE_DATE
                                                                                      WHEN p.ACTION_TYPE = 'Recruitment' AND FIRST_CERT_ISSUE_DATE IS NOT NULL THEN NULL
                                                                                      WHEN p.ACTION_TYPE = 'Recruitment' AND DATE_QUAL_SENT_DEU IS NOT NULL THEN QUALS_QR_DUE_DATE
                                                                                      WHEN p.ACTION_TYPE = 'Recruitment' AND DATE_ANNOUNCEMENT_CLOSED IS NOT NULL AND DATE_ANNOUNCEMENT_CLOSED < CURRENT_DATE THEN INITIAL_QUALS_DUE_DATE
                                                                                      WHEN p.ACTION_TYPE = 'Recruitment' AND DATE_ANNOUNCEMENT_POSTED IS NOT NULL THEN DATE_ANNOUNCEMENT_CLOSED
                                                                                      WHEN p.ACTION_TYPE = 'Recruitment' AND DATE_ANN_APPROVED_BY_DEU IS NOT NULL THEN ANN_POSTED_DUE_DATE
                                                                                      WHEN p.ACTION_TYPE = 'Recruitment' AND DATE_SENT_TO_DEU IS NOT NULL THEN ANN_POSTED_DUE_DATE
                                                                                      WHEN p.ACTION_TYPE = 'Recruitment' AND DATE_REC_PCKG_SENT_APPROVED IS NOT NULL AND RECRUITMENT_TYPE LIKE '%Complex%' THEN ANN_BUILT_DUE_DATE
                                                                                      WHEN p.ACTION_TYPE = 'Recruitment' AND DATE_REC_PCKG_SENT_DEU IS NOT NULL AND RECRUITMENT_TYPE LIKE '%Complex%' THEN PACKAGE_QR_DUE_DATE
                                                                                      WHEN p.ACTION_TYPE = 'Recruitment' AND DATE_REC_PCKG_SENT_DEU IS NULL AND RECRUITMENT_TYPE LIKE '%Complex%' THEN PACKAGE_DRAFT_DUE_DATE
                                                                                      WHEN p.ACTION_TYPE = 'Recruitment' AND DATE_ANNOUNCEMENT_POSTED IS NULL AND RECRUITMENT_TYPE = 'Standard' THEN ANN_BUILT_DUE_DATE
                                                                                      END NEXT_KPI_DUE_DATE,
                                                                        PACKAGE_DRAFT_DUE_DATE AS PACKAGE_DRAFT_DUE_DATE_DUPE,
                                                                        PACKAGE_QR_DUE_DATE AS PACKAGE_QR_DUE_DATE_DUPE,
                                                                        ANN_BUILT_DUE_DATE AS ANN_BUILT_DUE_DATE_DUPE,
                                                                        ANN_POSTED_DUE_DATE AS ANN_POSTED_DUE_DATE_DUPE,
                                                                        NVL(DATE_ANNOUNCEMENT_CLOSED, ANN_CLOSED_DUE_DATE) AS ANN_CLOSED_DUE_DATE_DUPE,
                                                                        INITIAL_QUALS_DUE_DATE AS INITIAL_QUALS_DUE_DATE_DUPE,
                                                                        QUALS_QR_DUE_DATE AS QUALS_QR_DUE_DATE_DUPE
                                                          FROM
                                                                        (SELECT  o.*,  
                                                                        "HHS_WHRSC_HR"."MAIN"."COMMENTS_STATUS",
                                                                        CASE WHEN RECRUITMENT_TYPE = '-' THEN null
                                                                                      WHEN RECRUITMENT_TYPE = 'Standard' THEN null
                                                                                      WHEN OTHER_RECRUIT_CONSIDERATIONS = 'Direct Hire' THEN DATE_RECRUITMENT_AUTHORIZED + 15
                                                                                      WHEN RECRUITMENT_TYPE LIKE '%Complex%' THEN DATE_RECRUITMENT_AUTHORIZED + 35
                                                                                      END PACKAGE_DRAFT_DUE_DATE,
                                                                        CASE WHEN RECRUITMENT_TYPE = '-' THEN null
                                                                                      WHEN RECRUITMENT_TYPE = 'Standard' THEN null
                                                                                      WHEN OTHER_RECRUIT_CONSIDERATIONS = 'Direct Hire' THEN DATE_RECRUITMENT_AUTHORIZED + 25
                                                                                      WHEN RECRUITMENT_TYPE LIKE '%Complex%' THEN DATE_RECRUITMENT_AUTHORIZED + 45
                                                                                      END PACKAGE_QR_DUE_DATE,
                                                                        CASE WHEN RECRUITMENT_TYPE = '-' THEN null
                                                                                      WHEN RECRUITMENT_TYPE = 'Standard' THEN DATE_RECRUITMENT_AUTHORIZED + 3
                                                                                      WHEN OTHER_RECRUIT_CONSIDERATIONS = 'Direct Hire' THEN DATE_RECRUITMENT_AUTHORIZED + 28
                                                                                      WHEN RECRUITMENT_TYPE LIKE '%Complex%' THEN DATE_RECRUITMENT_AUTHORIZED + 54
                                                                                      END ANN_BUILT_DUE_DATE,
                                                                        CASE WHEN RECRUITMENT_TYPE = '-' THEN null
                                                                                      WHEN RECRUITMENT_TYPE = 'Standard' THEN DATE_RECRUITMENT_AUTHORIZED + 4
                                                                                      WHEN OTHER_RECRUIT_CONSIDERATIONS = 'Direct Hire' THEN DATE_RECRUITMENT_AUTHORIZED + 29
                                                                                      WHEN RECRUITMENT_TYPE LIKE '%Complex%' THEN DATE_RECRUITMENT_AUTHORIZED + 55
                                                                                      END ANN_POSTED_DUE_DATE,
                                                                        CASE WHEN RECRUITMENT_TYPE = '-' THEN null
                                                                                      WHEN RECRUITMENT_TYPE = 'Standard' THEN DATE_RECRUITMENT_AUTHORIZED + 9
                                                                                      WHEN OTHER_RECRUIT_CONSIDERATIONS = 'Direct Hire' THEN DATE_RECRUITMENT_AUTHORIZED + 36
                                                                                      WHEN RECRUITMENT_TYPE LIKE '%Complex%' THEN DATE_RECRUITMENT_AUTHORIZED + 60
                                                                                      END ANN_CLOSED_DUE_DATE,
                                                                        CASE WHEN RECRUITMENT_TYPE = '-' THEN null
                                                                                      WHEN RECRUITMENT_TYPE = 'Standard' AND DATE_ANNOUNCEMENT_CLOSED IS NULL THEN DATE_RECRUITMENT_AUTHORIZED + 13
                                                                                      WHEN RECRUITMENT_TYPE = 'Standard' AND DATE_ANNOUNCEMENT_CLOSED IS NOT NULL AND DATE_RECRUITMENT_AUTHORIZED IS NOT NULL THEN DATE_ANNOUNCEMENT_CLOSED + 4
                                                                                      WHEN OTHER_RECRUIT_CONSIDERATIONS = 'Direct Hire' AND DATE_ANNOUNCEMENT_CLOSED IS NULL THEN DATE_RECRUITMENT_AUTHORIZED + 56
                                                                                      WHEN OTHER_RECRUIT_CONSIDERATIONS = 'Direct Hire' AND DATE_ANNOUNCEMENT_CLOSED IS NOT NULL AND DATE_RECRUITMENT_AUTHORIZED IS NOT NULL THEN DATE_ANNOUNCEMENT_CLOSED + 20
                                                                                      WHEN RECRUITMENT_TYPE LIKE '%Complex%' AND DATE_ANNOUNCEMENT_CLOSED IS NULL THEN DATE_RECRUITMENT_AUTHORIZED + 74
                                                                                      WHEN RECRUITMENT_TYPE LIKE '%Complex%' AND DATE_ANNOUNCEMENT_CLOSED IS NOT NULL AND DATE_RECRUITMENT_AUTHORIZED IS NOT NULL THEN DATE_ANNOUNCEMENT_CLOSED + 14
                                                                                      END INITIAL_QUALS_DUE_DATE,
                                                                        CASE WHEN RECRUITMENT_TYPE = '-' THEN null
                                                                                      WHEN RECRUITMENT_TYPE = 'Standard' AND DATE_ANNOUNCEMENT_CLOSED IS NULL THEN DATE_RECRUITMENT_AUTHORIZED + 16
                                                                                      WHEN RECRUITMENT_TYPE = 'Standard' AND DATE_ANNOUNCEMENT_CLOSED IS NOT NULL AND DATE_RECRUITMENT_AUTHORIZED IS NOT NULL THEN DATE_ANNOUNCEMENT_CLOSED + 7
                                                                                      WHEN OTHER_RECRUIT_CONSIDERATIONS = 'Direct Hire' AND DATE_ANNOUNCEMENT_CLOSED IS NULL THEN DATE_RECRUITMENT_AUTHORIZED + 66
                                                                                      WHEN OTHER_RECRUIT_CONSIDERATIONS = 'Direct Hire' AND DATE_ANNOUNCEMENT_CLOSED IS NOT NULL AND DATE_RECRUITMENT_AUTHORIZED IS NOT NULL THEN DATE_ANNOUNCEMENT_CLOSED + 30
                                                                                      WHEN RECRUITMENT_TYPE LIKE '%Complex%' AND DATE_ANNOUNCEMENT_CLOSED IS NULL THEN DATE_RECRUITMENT_AUTHORIZED + 81
                                                                                      WHEN RECRUITMENT_TYPE LIKE '%Complex%' AND DATE_ANNOUNCEMENT_CLOSED IS NOT NULL AND DATE_RECRUITMENT_AUTHORIZED IS NOT NULL THEN DATE_ANNOUNCEMENT_CLOSED + 21
                                                                                      END QUALS_QR_DUE_DATE,
                                                                        CASE WHEN o.ACTION_TYPE = 'Appointment' THEN NH_CREATION_DATE + 3
                                                                                      END TENTATIVE_OFFER_DUE_DATE/*,
                                                                        CASE WHEN o.ACTION_TYPE = 'Appointment' THEN 'Pending BITS Integration'  /* Fix NEXT_KPI_DUE_DATE when this is fixed 
                                                                                      END FINAL_OFFER_DUE_DATE*/
                                                                                      FROM
                                                                                                     (SELECT DISTINCT
                                                                                                                                                main."TRANSACTION_ID",
                                                                                                                                                  main."ACTION_TYPE",
                                                                                                                                                  req_combined."REQUEST_TYPE",
                                                                                                                                                  main."JOB_OPENING_ID",
                                                                                                                                                  main."HR_SPECIALIST",
                                                                                                                                                  "BIZFLOW"."MEMBER"."EMAIL",
                                                                                                                                                  main."INSTITUTE" AS CUSTOMER,
                                                                                                                                                   vac_cert_combined."POSITION_TITLE",
                                                                                                                                                   vac_cert_combined."Series" AS SERIES,
                                                                                                                                                   vac_cert_combined."Grade" AS GRADE,
                                                                                                                                                   vac_cert_combined."Locations" AS LOCATIONS,
                                                                                                                                                   CASE WHEN vac_cert_combined."ANNOUNCEMENT_TYPE" = 'DE' THEN 'DE'
                                                                                                                                                                             WHEN vac_cert_combined."ANNOUNCEMENT_TYPE" = 'ST' THEN 'MP'
                                                                                                                                                                             WHEN vac_cert_combined."ANNOUNCEMENT_TYPE" = 'IMP' THEN 'MP (Internal)'
                                                                                                                                                                             WHEN vac_cert_combined."ANNOUNCEMENT_TYPE" LIKE '%DE%' AND vac_cert_combined."ANNOUNCEMENT_TYPE" LIKE '%ST%' THEN 'MP/DE'
                                                                                                                                                                            WHEN vac_cert_combined."ANNOUNCEMENT_TYPE" IS NULL THEN NULL
                                                                                                                                                                             ELSE 'Multiple Areas of Consideration'
                                                                                                                                                                             END ANNOUNCEMENT_TYPE,
                                                                                                                                                   NVL(vac_cert_combined."VACANCY_ANNOUNCEMENT_NUMBER", vac_cert_combined."VACANCY_IDENTIFICATION_NUMBER") AS "ANN_NUMBER_OR_VIN",
                                                                                                                                                   vac_cert_combined."Number of Certs" AS NUMBER_OF_CERTS,
                                                                                                                                                   main."DATE_RECEIVED",
                                                                                                                                                   main."MISSING_DOCS_RECEIPT_DATE",
                                                                                                                                                   "HHS_WHRSC_HR"."RECRUITMENT"."DATE_REC_PCKG_SENT_DEU",
                                                                                                                                                   "HHS_WHRSC_HR"."RECRUITMENT"."DATE_REC_PCKG_SENT_APPROVED",
                                                                                                                                                   "HHS_WHRSC_HR"."ANNOUNCEMENT"."DATE_SENT_TO_DEU",
                                                                                                                                                   vac_cert_combined."DATE_ANN_APPROVED_BY_DEU",
                                                                                                                                                   vac_cert_combined."DATE_ANNOUNCEMENT_OPENED",
                                                                                                                                                   vac_cert_combined."DATE_ANNOUNCEMENT_CLOSED",
                                                                                                                                                   vac_cert_combined."First Cert Issue Date" AS FIRST_CERT_ISSUE_DATE,
                                                                                                                                                   vac_cert_combined."First Cert Return Date" AS FIRST_CERT_RETURN_DATE,
                                                                                                                                                   vac_cert_combined.LAST_CERT_EXPIRE_DATE,
                                                                                                                                                   nh_combined."NH_CREATION_DATE",
                                                                                                                                                   nh_combined."NH_NAME",
                                                                                                                                                   nh_combined."NH_PAY_PLAN",
                                                                                                                                                   nh_combined."NH_SERIES",
                                                                                                                                                   nh_combined."NH_GRADE",
                                                                                                                                                   nh_combined."NH_POSITION_TITLE",
                                                                                                                                                   nh_combined."NH_DUTY_LOCATION",
                                                                                                                                                   nh_combined."SEND_TENTATV_OFFER_COMPLT_DATE",
                                                                                                                                                   nh_combined."BCKGRND_INVSTGTN_COMPLT_DATE",
                                                                                                                                                   nh_combined."SEND_OFFICL_OFFER_COMPLT_DATE",
                                                                                                                                                   nh_combined."NH_PROJECTED_START_DATE",
                                                                                                                                                   vac_cert_combined."DATE_ANNOUNCEMENT_POSTED",
                                                                                                                                                   vac_cert_combined."TOTAL_REFERRED_APPLICANTS",
                                                                                                                                                   main."STATUS",
                                                                                                                                                   CASE WHEN nh_combined."NH_NAME" IS NOT NULL THEN 'Onboarding'
                                                                                                                                                                             WHEN vac_cert_combined."First Cert Issue Date" IS NOT NULL THEN 'Certs Issued'
                                                                                                                                                                             WHEN vac_cert_combined."DATE_ANNOUNCEMENT_CLOSED" IS NOT NULL AND vac_cert_combined."DATE_ANNOUNCEMENT_CLOSED" < CURRENT_DATE THEN 'Reviewing Apps'
                                                                                                                                                                             WHEN vac_cert_combined."DATE_ANNOUNCEMENT_OPENED" IS NOT NULL AND vac_cert_combined."DATE_ANNOUNCEMENT_OPENED" <= CURRENT_DATE THEN 'Announcement Open'
                                                                                                                                                                            WHEN vac_cert_combined."DATE_ANNOUNCEMENT_POSTED" IS NOT NULL AND vac_cert_combined."DATE_ANNOUNCEMENT_POSTED" <= CURRENT_DATE THEN 'Announcement Released'
                                                                                                                                                                             WHEN ras.ACTION_STATUS = 'Program' THEN main."INSTITUTE" || ' Review'
                                                                                                                                                                             ELSE ras.ACTION_STATUS 
                                                                                                                                                                             END ACTION_STATUS,
                                                                                                                                                              CASE WHEN nh_combined."NH_NAME" IS NOT NULL THEN nh_combined."NH_CREATION_DATE"
                                                                                                                                                                             WHEN vac_cert_combined."First Cert Issue Date" IS NOT NULL THEN vac_cert_combined."First Cert Issue Date"
                                                                                                                                                                             WHEN vac_cert_combined."DATE_ANNOUNCEMENT_CLOSED" IS NOT NULL AND vac_cert_combined."DATE_ANNOUNCEMENT_CLOSED" < CURRENT_DATE THEN vac_cert_combined."DATE_ANNOUNCEMENT_CLOSED"
                                                                                                                                                                             WHEN vac_cert_combined."DATE_ANNOUNCEMENT_OPENED" IS NOT NULL AND vac_cert_combined."DATE_ANNOUNCEMENT_OPENED" <= CURRENT_DATE THEN vac_cert_combined."DATE_ANNOUNCEMENT_OPENED"
                                                                                                                                                                             WHEN vac_cert_combined."DATE_ANNOUNCEMENT_POSTED" IS NOT NULL AND vac_cert_combined."DATE_ANNOUNCEMENT_POSTED" <= CURRENT_DATE THEN vac_cert_combined."DATE_ANNOUNCEMENT_POSTED"
                                                                                                                                                                             ELSE ras.MOST_RECENT_ACTION_DATE 
                                                                                                                                                                             END MOST_RECENT_ACTION_DATE,
                                                                                                                                                              CASE WHEN main."ACTION_TYPE" NOT IN ('Recruitment') THEN '-'
                                                                                                                                                                             WHEN "HHS_WHRSC_HR"."RECRUITMENT"."OTHER_RECRUIT_CONSIDERATIONS" = 'Direct Hire' THEN 'DH Complex'
                                                                                                                                                                             WHEN vac_cert_combined."ANNOUNCEMENT_APP_COUNT" > 50 THEN 'Complex (Applicant Limit)'
                                                                                                                                                                             WHEN "HHS_WHRSC_HR"."RECRUITMENT"."IS_NEW_POSITION" = 'Yes' THEN 'Complex (New Position)'
                                                                                                                                                                             WHEN vac_cert_combined."Series" LIKE '%,%' THEN 'Complex (Interdisciplinary)'
                                                                                                                                                                             WHEN vac_cert_combined."Grade" LIKE '%,%' THEN 'Complex (Multi-Grade)'
                                                                                                                                                                             WHEN vac_cert_combined."Locations" LIKE '%,%' THEN 'Complex (Multi-Location)'
                                                                                                                                                                             WHEN "ALL_AOCS" LIKE '%DE%' AND "ALL_AOCS" LIKE '%ST%' THEN 'Complex (Multi-AOC)'
                                                                                                                                                                             ELSE 'Standard'
                                                                                                                                                                             END RECRUITMENT_TYPE,
                                                                                                                                                               "HHS_WHRSC_HR"."RECRUITMENT"."OTHER_RECRUIT_CONSIDERATIONS",
                                                                                                                                                               main."HR_SENIOR_ADVISOR" AS RECRUITMENT_PROGRAM_MANAGER,
                                                                                                                                                               "HHS_WHRSC_HR"."RECRUITMENT"."ANN_QUAL_REVIEWER",
                                                                                                                                                               "HHS_WHRSC_HR"."ANNOUNCEMENT"."QUALITY_REVIEWER",
                                                                                                                                                               main."DATE_JOB_OPENING_APPROVED" AS DATE_RECRUITMENT_AUTHORIZED,
                                                                                                                                                               "HHS_WHRSC_HR"."APPLICANT_RATING"."DATE_QUAL_SENT_DEU",
                                                                                                                                                               "HHS_WHRSC_HR"."APPOINTMENT"."DATE_DPSAC_NOTIFICATION_RCVD" 
                                                                                                                                  FROM (SELECT *
                                                                                                                   FROM "HHS_WHRSC_HR"."MAIN"
                                                                                                                                  LEFT JOIN "HHS_WHRSC_HR"."DSS_NEW_HIRE_VACANCY_REQUEST" 
                                                                                                                                  ON "HHS_WHRSC_HR"."MAIN"."JOB_OPENING_ID" = "HHS_WHRSC_HR"."DSS_NEW_HIRE_VACANCY_REQUEST"."NH_REQUEST_NUMBER" ) main 
                                                                                                                                                left join
                                                                                                                                                               (SELECT *
                                                                                                                                                FROM  "HHS_WHRSC_HR"."DSS_VACANCY" 
                                                                                                                                                 left join 
                                                                                                                                                               (select  "HHS_WHRSC_HR"."DSS_VACANCY_LOCATIONS"."VACANCY_IDENTIFICATION_NUMBER", 
                                                                                                                                                                                            LISTAGG( "HHS_WHRSC_HR"."DSS_VACANCY_LOCATIONS"."LOCATION_CITY" , ', ')
                                                                                                                                                                                            within group (order by "HHS_WHRSC_HR"."DSS_VACANCY_LOCATIONS"."VACANCY_IDENTIFICATION_NUMBER" ) "Locations"
                                                                                                                                                                                            from  "HHS_WHRSC_HR"."DSS_VACANCY_LOCATIONS" 
                                                                                                                                                                                            group by   "HHS_WHRSC_HR"."DSS_VACANCY_LOCATIONS"."VACANCY_IDENTIFICATION_NUMBER" 
                                                                                                                                                                                            order by   "HHS_WHRSC_HR"."DSS_VACANCY_LOCATIONS"."VACANCY_IDENTIFICATION_NUMBER" 
                                                                                                                                                                             ) loc
                                                                                                                                                                             on "HHS_WHRSC_HR"."DSS_VACANCY"."VACANCY_IDENTIFICATION_NUMBER" = loc."VACANCY_IDENTIFICATION_NUMBER"  
                                                                                                                                                               left join 
                                                                                                                                                                             (select  a."VACANCY_IDENTIFICATION_NUMBER", 
                                                                                                                                                                                            LISTAGG(a."SERIES" , ', ')
                                                                                                                                                                                            within group (order by a."VACANCY_IDENTIFICATION_NUMBER" ) "Series"
                                                                                                                                                                                            from  
                                                                                                                                                                                            (select distinct "HHS_WHRSC_HR"."DSS_VACANCY_RATING_COMBINATION"."VACANCY_IDENTIFICATION_NUMBER", 
                                                                                                                                                                                           "HHS_WHRSC_HR"."DSS_VACANCY_RATING_COMBINATION"."SERIES" from "HHS_WHRSC_HR"."DSS_VACANCY_RATING_COMBINATION") a
                                                                                                                                                                                            group by   a."VACANCY_IDENTIFICATION_NUMBER" 
                                                                                                                                                                                            order by   a."VACANCY_IDENTIFICATION_NUMBER"                                            
                                                                                                                                                                             ) ser
                                                                                                                                                                             on "HHS_WHRSC_HR"."DSS_VACANCY"."VACANCY_IDENTIFICATION_NUMBER" = ser."VACANCY_IDENTIFICATION_NUMBER"
                                                                                                                                                               left join
                                                                                                                                                                             (select  b."VACANCY_IDENTIFICATION_NUMBER", 
                                                                                                                                                                                            LISTAGG(b."GRADE" , ',')
                                                                                                                                                                                            within group (order by b."VACANCY_IDENTIFICATION_NUMBER" ) "Grade"
                                                                                                                                                                                            from  
                                                                                                                                                                                            (select distinct "HHS_WHRSC_HR"."DSS_VACANCY_RATING_COMBINATION"."VACANCY_IDENTIFICATION_NUMBER",                                            
                                                                                                                                                                                           to_char("HHS_WHRSC_HR"."DSS_VACANCY_RATING_COMBINATION"."GRADE", '99') as "GRADE"
                                                                                                                                                                                                          from "HHS_WHRSC_HR"."DSS_VACANCY_RATING_COMBINATION") b
                                                                                                                                                                                            group by   b."VACANCY_IDENTIFICATION_NUMBER" 
                                                                                                                                                                                            order by   b."VACANCY_IDENTIFICATION_NUMBER"                                           
                                                                                                                                                                             ) gra
                                                                                                                                                                             on "HHS_WHRSC_HR"."DSS_VACANCY"."VACANCY_IDENTIFICATION_NUMBER" = gra."VACANCY_IDENTIFICATION_NUMBER"
                                                                                                                                                               left join
                                                                                                                                                                             (select  c."VACANCY_NUMBER", 
                                                                                                                                                                                            LISTAGG(c."VCNCY_MISSION_CRITICAL_OCCUPTN" , ', ')
                                                                                                                                                                                            within group (order by c."VACANCY_NUMBER" ) "Special Tracking"
                                                                                                                                                                                            from  
                                                                                                                                                                                            (select distinct  "HHS_WHRSC_HR"."DSS_VAC_MISSION_CRITCL_OCCUPTN"."VACANCY_NUMBER", 
                                                                                                                                                                                            "HHS_WHRSC_HR"."DSS_VAC_MISSION_CRITCL_OCCUPTN"."VCNCY_MISSION_CRITICAL_OCCUPTN"
                                                                                                                                                                                                          from  "HHS_WHRSC_HR"."DSS_VAC_MISSION_CRITCL_OCCUPTN" ) c
                                                                                                                                                                                            group by   c."VACANCY_NUMBER" 
                                                                                                                                                                                            order by   c."VACANCY_NUMBER"                               
                                                                                                                                                                             ) mco
                                                                                                                                                                             on "HHS_WHRSC_HR"."DSS_VACANCY"."VACANCY_IDENTIFICATION_NUMBER" = mco."VACANCY_NUMBER"
                                                                                                                                                               left join  "HHS_WHRSC_HR"."ANNOUNCEMENT" 
                                                                                                                                                                             on "HHS_WHRSC_HR"."DSS_VACANCY"."VACANCY_IDENTIFICATION_NUMBER" =  "HHS_WHRSC_HR"."ANNOUNCEMENT"."VIN" 
                                                                                                                                                               left join  "HHS_WHRSC_HR"."DSS_REQUEST_VACNCY_COMBINATION" 
                                                                                                                                                                             on "HHS_WHRSC_HR"."DSS_VACANCY"."VACANCY_IDENTIFICATION_NUMBER" = "HHS_WHRSC_HR"."DSS_REQUEST_VACNCY_COMBINATION"."VACANCY_NUMBER" 
                                                                                                                                                               left join
                                                                                                                                                                             (SELECT  "HHS_WHRSC_HR"."DSS_CERTIFICATES"."VACANCY_IDENTIFICATION_NUMBER", 
                                                                                                                                                                                                          count( "HHS_WHRSC_HR"."DSS_CERTIFICATES"."CERTIFICATE_NUMBER") as "Number of Certs", 
                                                                                                                                                                                                          min("HHS_WHRSC_HR"."DSS_CERTIFICATES"."DATE_CERTIFICATE_ISSUED") as "First Cert Issue Date",
                                                                                                                                                                                                     min("HHS_WHRSC_HR"."DSS_CERTIFICATES"."DATE_HIRING_DECISN_RECD_IN_HR") as "First Cert Return Date",
                                                                                                                                                                                                          CASE WHEN MAX("HHS_WHRSC_HR"."DSS_CERTIFICATES"."DATE_CERTIFICATE_ISSUED") < '27-JUL-18'  THEN MAX("HHS_WHRSC_HR"."DSS_CERTIFICATES"."DATE_CERTIFICATE_ISSUED") +120 ELSE MAX("HHS_WHRSC_HR"."DSS_CERTIFICATES"."DATE_CERTIFICATE_ISSUED") +240 END LAST_CERT_EXPIRE_DATE
                                                                                                                                                                                            FROM  "HHS_WHRSC_HR"."DSS_CERTIFICATES" 
                                                                                                                                                                                                          left join  "HHS_WHRSC_HR"."CERTIFICATE" 
                                                                                                                                                                                                                        on  "HHS_WHRSC_HR"."DSS_CERTIFICATES"."CERTIFICATE_NUMBER" =  "HHS_WHRSC_HR"."CERTIFICATE"."CERT_NUMBER" 
                                                                                                                                                                                            group by  "HHS_WHRSC_HR"."DSS_CERTIFICATES"."VACANCY_IDENTIFICATION_NUMBER" 
                                                                                                                                                                                            order by "HHS_WHRSC_HR"."DSS_CERTIFICATES"."VACANCY_IDENTIFICATION_NUMBER"                                
                                                                                                                                                                             ) cert_combined
                                                                                                                                                                             on "HHS_WHRSC_HR"."DSS_VACANCY"."VACANCY_IDENTIFICATION_NUMBER" = cert_combined."VACANCY_IDENTIFICATION_NUMBER"                            
                                                                                                                                                               ) vac_cert_combined
                                                                                                                                                               on  main."JOB_OPENING_ID" = vac_cert_combined."REQUEST_NUMBER" 
                                                                                                                                                left join (SELECT *
                                                                                                                                                               FROM "HHS_WHRSC_HR"."DSS_NEW_HIRES"
                                                                                                                                                               left join "HHS_WHRSC_HR"."DSS_NEW_HIRE_TASKS" 
                                                                                                                                                                             on "HHS_WHRSC_HR"."DSS_NEW_HIRES"."NEW_HIRE_NUMBER" = "HHS_WHRSC_HR"."DSS_NEW_HIRE_TASKS"."NEW_HIRE_NUMBER"
                                                                                                                                                               left join 
                                                                                                                                                                             (select distinct "HHS_WHRSC_HR"."DSS_NEW_HIRE_VACANCY_REQUEST"."NEW_HIRE_NUMBER", "HHS_WHRSC_HR"."DSS_NEW_HIRE_VACANCY_REQUEST"."NH_REQUEST_NUMBER" , "HHS_WHRSC_HR"."DSS_NEW_HIRE_VACANCY_REQUEST"."NH_VACANCY_NUMBER" 
                                                                                                                                                                                            from  "HHS_WHRSC_HR"."DSS_NEW_HIRE_VACANCY_REQUEST" 
                                                                                                                                                                             ) nhvr 
                                                                                                                                                                             on "HHS_WHRSC_HR"."DSS_NEW_HIRES"."NEW_HIRE_NUMBER" = nhvr."NEW_HIRE_NUMBER"                            
                                                                                                                                                               ) nh_combined
                                                                                                                                                               on main."JOB_OPENING_ID" = nh_combined."NH_REQUEST_NUMBER" AND main.NH_VACANCY_NUMBER = nh_combined.NH_VACANCY_NUMBER
                                                                                                                                                left join (SELECT *
                                                                                                                                                               FROM "HHS_WHRSC_HR"."DSS_REQUESTS"
                                                                                                                                                               ) req_combined
                                                                                                                                                               on main."JOB_OPENING_ID" = req_combined."REQUEST_NUMBER" 
                                                                                                                                                left join (SELECT rah1.TRANSACTION_ID, 
                                                                                                                                                               CASE WHEN ACTION_STATUS = 'Active with Program' THEN 'Program' ELSE 'With HR' END ACTION_STATUS, 
                                                                                                                                                               MAXDATE as MOST_RECENT_ACTION_DATE
                                                                                                                                                               FROM "HHS_WHRSC_HR"."RECRUITMENT_ACTION_HISTORY" rah1 
                                                                                                                                                                             INNER JOIN
                                                                                                                                                                             (SELECT  TRANSACTION_ID, MAX( "HHS_WHRSC_HR"."RECRUITMENT_ACTION_HISTORY"."DATE_CERTIFIED" ) MAXDATE
                                                                                                                                                                             FROM "HHS_WHRSC_HR"."RECRUITMENT_ACTION_HISTORY" 
                                                                                                                                                                             GROUP BY TRANSACTION_ID) rah2
                                                                                                                                                                             ON rah1.TRANSACTION_ID = rah2.TRANSACTION_ID 
                                                                                                                                                                                            AND rah1.DATE_CERTIFIED = rah2.MAXDATE) ras
                                                                                                                                                               on main."TRANSACTION_ID" = ras.TRANSACTION_ID
                                                                                                                                                left join  "HHS_WHRSC_HR"."RECRUITMENT"
                                                                                                                                                               on main."TRANSACTION_ID" = "HHS_WHRSC_HR"."RECRUITMENT"."TRANSACTION_ID"
                                                                                                                                                left join  "HHS_WHRSC_HR"."ANNOUNCEMENT" 
                                                                                                                                                               on main."TRANSACTION_ID" = "HHS_WHRSC_HR"."ANNOUNCEMENT"."TRANSACTION_ID" 
                                                                                                                                                left join  "HHS_WHRSC_HR"."APPLICANT_RATING"
                                                                                                                                                               on main."TRANSACTION_ID" = "HHS_WHRSC_HR"."APPLICANT_RATING"."TRANSACTION_ID" 
                                                                                                                                                left join  "HHS_WHRSC_HR"."APPOINTMENT" 
                                                                                                                                                               on main."TRANSACTION_ID" = "HHS_WHRSC_HR"."APPOINTMENT"."TRANSACTION_ID" 
                                                                                                                                                left join  "BIZFLOW"."MEMBER"
                                                                                                                                                               on  main."HR_SPECIALIST_ID" = "BIZFLOW"."MEMBER"."MEMBERID" 
                                                                                                                                                left join (SELECT aocagg.REQUEST_NUMBER,
                                                                                                                                                                             LISTAGG(aocagg.ANNOUNCEMENT_TYPE, ', ')
                                                                                                                                                                             WITHIN GROUP (ORDER BY aocagg.REQUEST_NUMBER) "ALL_AOCS"
                                                                                                                                                                             FROM
                                                                                                                                                                             (SELECT REQUEST_NUMBER, VACANCY_NUMBER, ANNOUNCEMENT_TYPE
                                                                                                                                                                                            FROM "HHS_WHRSC_HR"."DSS_REQUEST_VACNCY_COMBINATION" 
                                                                                                                                                                                                          INNER JOIN "HHS_WHRSC_HR"."DSS_VACANCY"
                                                                                                                                                                                                                        ON VACANCY_NUMBER =  VACANCY_IDENTIFICATION_NUMBER) aocagg
                                                                                                                                                                             GROUP BY aocagg.REQUEST_NUMBER
                                                                                                                                                                             ORDER BY aocagg.REQUEST_NUMBER
                                                                                                                                                               ) aocs
                                                                                                                                                               on main."JOB_OPENING_ID" = aocs.REQUEST_NUMBER
                                                                                                                                                /*where STATUS = 'ACTIVE'*/
                        WHERE ((ACTION_TYPE = 'Appointment' AND
                                (vac_cert_combined.VACANCY_IDENTIFICATION_NUMBER = main.NH_VACANCY_NUMBER
                                OR main.NH_VACANCY_NUMBER IS NULL))
                            OR ACTION_TYPE <> 'Appointment')
                                                                                                     ORDER BY JOB_OPENING_ID, ANN_NUMBER_OR_VIN, ACTION_TYPE DESC) o
                                                                                      LEFT JOIN  "HHS_WHRSC_HR"."MAIN"
                                                                                                     ON o.TRANSACTION_ID = "HHS_WHRSC_HR"."MAIN"."TRANSACTION_ID") p ) kpidata
                                           UNPIVOT (TASK_DUE_DATE FOR TASK IN (
                                                          PACKAGE_DRAFT_DUE_DATE AS 'Draft Package',
                                                          PACKAGE_QR_DUE_DATE AS 'Review Package',
                                                          ANN_BUILT_DUE_DATE AS 'Build Announcement',
                                                          ANN_POSTED_DUE_DATE AS 'QR Announcement',
                                                          INITIAL_QUALS_DUE_DATE AS 'Review Quals',
                                                          QUALS_QR_DUE_DATE AS 'QR Quals/Issue Certs'
                                                          ))
                                           ORDER BY TRANSACTION_ID) q
                             ) r
                             ON date_range_input.DATES BETWEEN TASK_START_DATE AND TASK_DUE_DATE;


