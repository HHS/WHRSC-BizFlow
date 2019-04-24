SET DEFINE OFF;

update  hhs_whrsc_hr.tbl_form_dtl  set field_data = (select field_data from 
(select field_data from hhs_whrsc_hr.tbl_form_dtl_audit where procid = '19575' 
and extractvalue(field_data,'/DOCUMENT/TRANSACTION/ACTION_TYPE') is not null order by auditid desc)
where rownum =1)
where procid = 19575;

update  hhs_whrsc_hr.tbl_form_dtl  set field_data = (select field_data from 
(select field_data from hhs_whrsc_hr.tbl_form_dtl_audit where procid = '16888' 
and extractvalue(field_data,'/DOCUMENT/TRANSACTION/ACTION_TYPE') is not null order by auditid desc)
where rownum =1)
where procid = 16888;

update  hhs_whrsc_hr.tbl_form_dtl  set field_data = (select field_data from 
(select field_data from hhs_whrsc_hr.tbl_form_dtl_audit where procid = '16383' 
and extractvalue(field_data,'/DOCUMENT/TRANSACTION/ACTION_TYPE') is not null order by auditid desc)
where rownum =1)
where procid = 16383;

update  hhs_whrsc_hr.tbl_form_dtl  set field_data = (select field_data from 
(select field_data from hhs_whrsc_hr.tbl_form_dtl_audit where procid = '18239' 
and extractvalue(field_data,'/DOCUMENT/TRANSACTION/ACTION_TYPE') is not null order by auditid desc)
where rownum =1)
where procid = 18239;

update  hhs_whrsc_hr.tbl_form_dtl  set field_data = (select field_data from 
(select field_data from hhs_whrsc_hr.tbl_form_dtl_audit where procid = '18983' 
and extractvalue(field_data,'/DOCUMENT/TRANSACTION/ACTION_TYPE') is not null order by auditid desc)
where rownum =1)
where procid = 18983;

update  hhs_whrsc_hr.tbl_form_dtl  set field_data = (select field_data from 
(select field_data from hhs_whrsc_hr.tbl_form_dtl_audit where procid = '19695' 
and extractvalue(field_data,'/DOCUMENT/TRANSACTION/ACTION_TYPE') is not null order by auditid desc)
where rownum =1)
where procid = 19695;

update  hhs_whrsc_hr.tbl_form_dtl  set field_data = (select field_data from 
(select field_data from hhs_whrsc_hr.tbl_form_dtl_audit where procid = '19137' 
and extractvalue(field_data,'/DOCUMENT/TRANSACTION/ACTION_TYPE') is not null order by auditid desc)
where rownum =1)
where procid = 19137;
 
 
commit
 