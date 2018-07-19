--------------------------------------------
-- Backout statement
--------------------------------------------
/*
DROP TABLE HHS_WHRSC_HR.DSS_APPLICANT_NOTIFICATIONS;
DROP TABLE HHS_WHRSC_HR.DSS_CERTIFICATE_LOCATIONS;
DROP TABLE HHS_WHRSC_HR.DSS_CERTIFICATES;
DROP TABLE HHS_WHRSC_HR.DSS_NEW_HIRE_FORMS;
DROP TABLE HHS_WHRSC_HR.DSS_NEW_HIRE_ONBOARDING_DOCS;
DROP TABLE HHS_WHRSC_HR.DSS_NEW_HIRE_TASKS;
DROP TABLE HHS_WHRSC_HR.DSS_NEW_HIRE_VACANCY_REQUEST;
DROP TABLE HHS_WHRSC_HR.DSS_NEW_HIRES;
DROP TABLE HHS_WHRSC_HR.DSS_PERMISSION_PROFILES;
DROP TABLE HHS_WHRSC_HR.DSS_REQUEST_LOCATIONS;
DROP TABLE HHS_WHRSC_HR.DSS_REQUEST_RATING_COMBINATION;
DROP TABLE HHS_WHRSC_HR.DSS_REQUEST_VACNCY_COMBINATION;
DROP TABLE HHS_WHRSC_HR.DSS_REQUESTS;
DROP TABLE HHS_WHRSC_HR.DSS_VACANCY;
DROP TABLE HHS_WHRSC_HR.DSS_VACANCY_ELIGIBILITIES;
DROP TABLE HHS_WHRSC_HR.DSS_VACANCY_LOCATIONS;
DROP TABLE HHS_WHRSC_HR.DSS_VAC_MISSION_CRITCL_OCCUPTN;
DROP TABLE HHS_WHRSC_HR.DSS_VACANCY_RATING_COMBINATION;
*/



SET DEFINE OFF;


--==================================
--APPLICANT TABLES
--==================================

-------------------------------------------
--DDL for table DSS_APPLICANT_NOTIFICATIONS
-------------------------------------------
CREATE TABLE HHS_WHRSC_HR.DSS_APPLICANT_NOTIFICATIONS
(
    VACANCY_NUMBER					NUMBER(10),
	APPLICATN_NOTIFICATN_TEMPLATE	VARCHAR2(102),
	NOTIFICATIONS_SENT				NUMBER(10),
	INITIAL_NOTIFICATION_SEND_DATE	DATE
);


--==================================
--CERTIFICATE TABLES
--==================================

-----------------------------------------
--DDL for table DSS_CERTIFICATE_LOCATIONS
-----------------------------------------
CREATE TABLE HHS_WHRSC_HR.DSS_CERTIFICATE_LOCATIONS
(
    CERTIFICATE_NUMBER				VARCHAR2(102),
	CERT_FILTER_SERIES				VARCHAR2(1028),
	CERT_FILTER_LOCATION_CODE		VARCHAR2(34),
	CERT_FILTER_LOCATION_CITY		VARCHAR2(122),
	CERT_FILTER_LOCATION_STATE		VARCHAR2(8),
	CERT_FILTER_LOCATION_COUNTRY	VARCHAR2(202)
);


--------------------------------
--DDL for table DSS_CERTIFICATES
--------------------------------
CREATE TABLE HHS_WHRSC_HR.DSS_CERTIFICATES
(
	CERTIFICATE_NUMBER				VARCHAR2(102),
	VACANCY_IDENTIFICATION_NUMBER	NUMBER(10),
	POSITION_TITLE					VARCHAR2(202),
	CERTIFICATE_TYPE				VARCHAR2(82),
	ANNOUNCEMENT_NUMBER				VARCHAR2(56),
	DATE_AUDIT_COMPLETED			DATE,
	DATE_CERTIFICATE_ISSUED			DATE,
	DATE_CERTIFICATE_SENT_TO_SO		DATE,
	DATE_HIRING_DECISN_RECD_IN_HR	DATE,
	TOTAL_REFERRED_APPLICANTS		NUMBER(10),
	SELECTIONS_MADE					NUMBER(10),
	TOTAL_VETERANS_REFERRED			NUMBER(10),
	TOTAL_VETERANS_SELECTED			NUMBER(10),
	CERT_FILTER_LOCATIONS			VARCHAR2(2050),
	CERT_FILTER_GRADE				VARCHAR2(502)
);


--==================================
--NEW HIRE TABLES
--==================================

-----------------------------------------
--DDL for table DSS_NEW_HIRE_FORMS
-----------------------------------------
CREATE TABLE HHS_WHRSC_HR.DSS_NEW_HIRE_FORMS
(
	NEW_HIRE_NUMBER					VARCHAR2(22),
	NH_FORM_NAME					VARCHAR2(514),
	NH_FORM_NEXT_AGENCY_ACTION		VARCHAR2(1002),
	NH_FORM_NEXT_NEW_HIRE_ACTION	VARCHAR2(1002),
	NH_FORM_NUMBER					VARCHAR2(130),
	NH_FORM_STATUS					VARCHAR2(1002)
);

--------------------------------------------
--DDL for table DSS_NEW_HIRE_ONBOARDING_DOCS
--------------------------------------------
CREATE TABLE HHS_WHRSC_HR.DSS_NEW_HIRE_ONBOARDING_DOCS
(
	NEW_HIRE_NUMBER					VARCHAR2(22),
	NH_ONBOARDING_DOC_FILE_NAME		VARCHAR2(202),
	NH_ONBOARDING_DOC_NAME			VARCHAR2(514),
	NH_ONBOARDING_DOC_NUMBER		VARCHAR2(102),
	NH_ONBOARDING_DOC_SOURCE		VARCHAR2(1002),
	NH_ONBOARDING_DOC_UPLOAD_DATE	DATE
);

-----------------------------------------
--DDL for table DSS_NEW_HIRE_TASKS
-----------------------------------------
CREATE TABLE HHS_WHRSC_HR.DSS_NEW_HIRE_TASKS
(
	NEW_HIRE_NUMBER					VARCHAR2(22),
	BCKGRND_INVSTGTN_ACTIVE_DATE	DATE,
	BCKGRND_INVSTGTN_COMPLT_DATE	DATE,
	ARRIVAL_VERIFIED_COMPLT_DATE	DATE,
	SEND_OFFICL_OFFER_ACTIVE_DATE	DATE,
	SEND_OFFICL_OFFER_COMPLT_DATE	DATE,
	SEND_TENTATV_OFFER_ACTIVE_DATE	DATE,
	SEND_TENTATV_OFFER_COMPLT_DATE	DATE	
);


--------------------------------------------
--DDL for table DSS_NEW_HIRE_VACANCY_REQUEST
--------------------------------------------
CREATE TABLE HHS_WHRSC_HR.DSS_NEW_HIRE_VACANCY_REQUEST
(
	NEW_HIRE_NUMBER		VARCHAR2(22),
	NH_REQUEST_NUMBER	VARCHAR2(202),
	NH_VACANCY_NUMBER	NUMBER(10)	
);

-----------------------------------------
--DDL for table DSS_NEW_HIRES
-----------------------------------------
CREATE TABLE HHS_WHRSC_HR.DSS_NEW_HIRES
(
	NH_ACTUAL_START_DATE			DATE,
	NH_APPLICANT_ID					NUMBER(20),
	NH_APPLICANT_NAME				VARCHAR2(620),
	NH_APPLICATION_NUMBER			VARCHAR2(22),
	NH_CREATION_DATE				DATE,
	NH_DUTY_LOCATION				VARCHAR2(2050),
	NH_DUTY_LOCATION_CODE			VARCHAR2(2050),
	NH_EMAIL						VARCHAR2(2050),
	NH_FIRST_NAME					VARCHAR2(2050),
	NH_GRADE						VARCHAR2(2050),
	NH_LAST_NAME					VARCHAR2(2050),
	NH_LAST_UPDATE_DATE				DATE,
	NH_MAIDEN_NAME					VARCHAR2(2050),
	NH_MIDDLE_NAME					VARCHAR2(2050),
	NH_NAME							VARCHAR2(2050),
	NEW_HIRE_NUMBER					VARCHAR2(22),
	NH_ONBOARDING_PROCESS_OWNER		VARCHAR2(204),
	NH_PAY_PLAN						VARCHAR2(2050),
	NH_POSITION_DESCRIPTION_NUMBER	VARCHAR2(2050),
	NH_POSITION_TITLE				VARCHAR2(2050),
	NH_PROJECTED_START_DATE			DATE,
	NH_PROLONGED_START_DATE_REASON	VARCHAR2(2050),
	NH_SERIES						VARCHAR2(2050),
	NH_STAFFING_CUSTOMER			VARCHAR2(202),
	NH_STATUS						VARCHAR2(1002),
	NH_VETERANS_PREFERENCE_STATUS	VARCHAR2(2050)		
);

--==================================
--PERMISSION TABLES
--==================================

-----------------------------------------
--DDL for table DSS_PERMISSION_PROFILES
-----------------------------------------
CREATE TABLE HHS_WHRSC_HR.DSS_PERMISSION_PROFILES
(
	ACCOUNT_USER_EMAIL			VARCHAR2(242),
	STAFFING_CUSTOMER_NAME		VARCHAR2(202),
	STAFFING_OFFICE_CODE		VARCHAR2(10),
	STAFFING_ORGANIZATION_CODE	VARCHAR2(10),
	PERMISSION_PROFILE_NAME		VARCHAR2(82),
	CURRENT_INDICATOR			VARCHAR2(8)	
);

--==================================
--REQUEST TABLES
--==================================

-----------------------------------------
--DDL for table DSS_REQUEST_LOCATIONS
-----------------------------------------
CREATE TABLE HHS_WHRSC_HR.DSS_REQUEST_LOCATIONS
(
	REQUEST_NUMBER		VARCHAR2(202),
	LOCATION_OPENINGS	VARCHAR2(12),
	LOCATION_CODE		VARCHAR2(34),
	LOCATION_CITY		VARCHAR2(122),
	LOCATION_STATE_ABBR	VARCHAR2(8),
	LOCATION_COUNTRY	VARCHAR2(202)		
);

----------------------------------------------
--DDL for table DSS_REQUEST_RATING_COMBINATION
----------------------------------------------
CREATE TABLE HHS_WHRSC_HR.DSS_REQUEST_RATING_COMBINATION
(
	REQUEST_NUMBER				VARCHAR2(202),
	POSITION_DESCRIPTION_TITLE	VARCHAR2(202),
	PAY_PLAN					VARCHAR2(102),
	SERIES						VARCHAR2(22),
	GRADE						VARCHAR2(6)			
);

----------------------------------------------
--DDL for table DSS_REQUEST_VACNCY_COMBINATION
----------------------------------------------
CREATE TABLE HHS_WHRSC_HR.DSS_REQUEST_VACNCY_COMBINATION
(
	REQUEST_NUMBER					VARCHAR2(202),
	VACANCY_NUMBER					NUMBER(10),
	VCNCY_STAFFG_ORGANIZATN_CODE	VARCHAR2(10)			
);

-----------------------------------------
--DDL for table DSS_REQUESTS
-----------------------------------------
CREATE TABLE HHS_WHRSC_HR.DSS_REQUESTS
(
	REQUEST_NUMBER				VARCHAR2(202),
	CLEARANCE_LEVEL_REQUIRED	VARCHAR2(1002),
	REQUEST_TYPE				VARCHAR2(1002),
	CUSTOMER_NAME				VARCHAR2(202),
	REQUESTER_NAME				VARCHAR2(206),
	SUPERVISORY_POSITION		VARCHAR2(8),
	TRAVEL_PREFERENCE			VARCHAR2(1002),
	DESCRIPTION					CLOB				
);


--==================================
--VACANCY TABLES
--==================================

-----------------------------------------
--DDL for table DSS_VACANCY
-----------------------------------------
CREATE TABLE HHS_WHRSC_HR.DSS_VACANCY
(
	POSITION_TITLE					VARCHAR2(202),
	VACANCY_IDENTIFICATION_NUMBER	NUMBER(10),
	VACANCY_ANNOUNCEMENT_NUMBER		VARCHAR2(56),
	VACANCY_STATUS					VARCHAR2(1002),
	ANNOUNCEMENT_TYPE				VARCHAR2(24),
	LAST_UPDATE_DATE				DATE,
	FULL_PERFORMANCE_LEVEL			VARCHAR2(6),
	DATE_ANNOUNCEMENT_POSTED		DATE,
	DATE_ANNOUNCEMENT_OPENED		DATE,
	DATE_ANNOUNCEMENT_CLOSED		DATE,
	NUMBER_OF_POSITIONS_ADVERTISED	VARCHAR2(12),
	TOTAL_APPLICANTS				NUMBER(10),
	TOTAL_ELIGIBLE_APPLICANTS		NUMBER(10),
	TOTAL_REFERRED_APPLICANTS		NUMBER(10),
	TOTAL_SELECTED_APPLICANTS		NUMBER(10)			
);

-----------------------------------------
--DDL for table DSS_VACANCY_ELIGIBILITIES
-----------------------------------------
CREATE TABLE HHS_WHRSC_HR.DSS_VACANCY_ELIGIBILITIES
(
	VACANCY_IDENTIFICATION_NUMBER	NUMBER(10),
	VACANCY_ELIGIBILITY				VARCHAR2(202)			
);

-------------------------------------
--DDL for table DSS_VACANCY_LOCATIONS
-------------------------------------
CREATE TABLE HHS_WHRSC_HR.DSS_VACANCY_LOCATIONS
(
	VACANCY_IDENTIFICATION_NUMBER	NUMBER(10),
	LOCATION_OPENINGS				VARCHAR2(12),
	LOCATION_CODE					VARCHAR2(34),
	LOCATION_CITY					VARCHAR2(122),
	LOCATION_STATE_ABBR				VARCHAR2(8),
	LOCATION_COUNTRY				VARCHAR2(202)			
);

----------------------------------------------
--DDL for table DSS_VAC_MISSION_CRITCL_OCCUPTN
----------------------------------------------
CREATE TABLE HHS_WHRSC_HR.DSS_VAC_MISSION_CRITCL_OCCUPTN
(
	VACANCY_NUMBER					NUMBER(10),
	VCNCY_MISSION_CRITICAL_OCCUPTN	VARCHAR2(202),
	TAG_LEVEL						VARCHAR2(14)			
);

----------------------------------------------
--DDL for table DSS_VACANCY_RATING_COMBINATION
----------------------------------------------
CREATE TABLE HHS_WHRSC_HR.DSS_VACANCY_RATING_COMBINATION
(	
	VACANCY_IDENTIFICATION_NUMBER	NUMBER(10),
	SERIES							VARCHAR2(22),
	GRADE							VARCHAR2(6),
	PAY_PLAN						VARCHAR2(102)				
);