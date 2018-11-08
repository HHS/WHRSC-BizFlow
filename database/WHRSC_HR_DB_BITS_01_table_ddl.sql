-------------------------------
--DDL for BITS_NEOSTATUS table
-------------------------------
CREATE TABLE BITS_NEOStatus
(
HHSID VARCHAR2(10),
Date_Requested TIMESTAMP,	
Sensitivity_Level VARCHAR2(8),
Sensitivity_Level_Desc VARCHAR2(30),
Sensitivity_Level_Formal_Desc VARCHAR2(250),
Cleared_For_NEO TIMESTAMP,	
Inactive_Date TIMESTAMP	
);