

--=============================================================================
-- Grant privileges on objects under WHRSC schema to roles
-------------------------------------------------------------------------------


-- privilege for HHS_WHRSC_HR_RW_ROLE;

GRANT SELECT, INSERT, UPDATE, DELETE ON HHS_WHRSC_HR.OPDIV_AFFILIATION TO HHS_WHRSC_HR_RW_ROLE;

