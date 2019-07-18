var WHRSCMain = {

	_USAS_DATA: null,

	_CMS_allUserGroups: null,

	_CMS_myUserGroups: null,

	actList: [{
		name: "HRS Reviews Action",
		usergroup: [],
		tabs: ['tab1','tab2','tab3','tab4','tab5','tab6'],
		readonly: []
	},
	{
		name: "HRA Processes Action",
		usergroup: [],
		tabs: ['tab1','tab2','tab3','tab4','tab5','tab6'],
		readonly: []
	},
	{
		name: "Final Authorizer Authorizes Action",
		usergroup: [],
		tabs: ['tab1','tab2','tab3','tab4','tab5','tab6'],
		readonly: []
	},
	{
		name: "DEU Reviews",
		usergroup: [],
		tabs: ['tab1','tab2','tab3','tab4','tab5','tab6'],
		readonly: []
	}
		],

	tabList: [{
		id: 'tab1',
		targetUrl: '/whrsc_appointment/appointment_transaction.do',
		targetGroup: 'partial_tab1',
		name: 'Transaction Information',
		loaded: false,
		completed: false,
		disabledHeader: false,
		onInit: function() {
			$('#RYB_STATUS').on("change", function() {
                WHRSCMain.resetActionStatus();
            });
            WHRSCMain.resetActionStatus();
			$('#DATE_SF52_RECEIVED').on("change", function() {
                WHRSCMain.resetDateReceived();
            });
            WHRSCMain.resetDateReceived();
        },
		enableHandler: null
	}
	, {
		id: 'tab2',
		targetUrl: '/whrsc_appointment/appointment_appointment.do',
		targetGroup: 'partial_tab2',
		name: 'Appointment Information',
		loaded: false,
		completed: false,
		disabledHeader: false,
		onInit: function() {
			$('#A_CAP_HR_JOB_REQ').on("change", function() {
                WHRSCMain.resetRequisitionNumber();
            });
			WHRSCMain.resetRequisitionNumber();
		},

		enableHandler: null
	}

	, {
		id: 'tab3',
		targetUrl: '/whrsc_appointment/appointment_approval.do',
		targetGroup: 'partial_tab3',
		name: 'Approvals Information',
		loaded: false,
		completed: false,
		disabledHeader: false,
		onInit: function() {

		},

		enableHandler: null
	}
	, {
		id: 'tab4',
		targetUrl: '/whrsc_appointment/appointment_payinformation.do',
		targetGroup: 'partial_tab4',
		name: 'Pay Information',
		loaded: false,
		completed: false,
		disabledHeader: false,
		onInit: function() {

		},

		enableHandler: null
	}
	, {
		id: 'tab5',
		targetUrl: '/whrsc_appointment/appointment_orientation.do',
		targetGroup: 'partial_tab5',
		name: 'Orientation Information',
		loaded: false,
		completed: false,
		disabledHeader: false,
		onInit: function() {

		},

		enableHandler: null
	}
	, {
		id: 'tab6',
		targetUrl: '/whrsc_appointment/appointment_finalprocessing.do',
		targetGroup: 'partial_tab6',
		name: 'Final Processing/Authorization Information',
		loaded: false,
		completed: false,
		disabledHeader: false,
		onInit: function() {

		},

		enableHandler: null
	}
		],

	activityOption: null,

	tabManager: null,

	removeSizeLabel: function(element) {
		var target = null;
		var nodeName = $(element).get(0).tagName;
		if (nodeName == 'input') {
			if ($(element).hasClass('dijitInputInner')) {
				target = $(element).parent().parent().parent();
			} else {
				target = $(element).parent().parent();
			}
		} else if (nodeName == 'textarea') {
			target = $(element).parent();
		}

		if (target != null && target.length > 0) {
			$(target).find('p.sizeLabel').remove();
		}
	},
	// The element with alwaysDisabled/alwaysReadonly attribute will be submitted to the webserver.
	// But, when the form is enabled, element will not be enabled.
	setAlwaysDisabled: function(elementID) {
		var element = $('#' + elementID);
		var selectorKey = '#' + elementID;
		$(element).attr('disabled','disabled');
		$(element).attr('alwaysDisabled','true');

		WHRSCMain.removeSizeLabel(element);
	},
	setAlwaysReadonly: function(elementID) {
		var element = $('#' + elementID);
		$(element).prop('readonly','true');
		$(element).attr('alwaysReadonly','true');

		WHRSCMain.removeSizeLabel(element);
	},

	getUserGroupMemberID: function(userGroupName) {
		var userGroupMemberID = '';
		if (userGroupName == null || userGroupName.length == 0) {
			utility.debugLog('WHRSCMain - getUserGroupMemberID() - userGroupName is null or empty');
		}
		if (WHRSCMain._CMS_allUserGroups == null) {
			var rawAllUserGroupData = $('#h_cms_usergroupString').val();
			if (rawAllUserGroupData != null && rawAllUserGroupData.length > 0) {
				$('#h_cms_usergroupString').val('');
				var x2js = new X2JS();
				WHRSCMain._CMS_allUserGroups = x2js.xml_str2json(rawAllUserGroupData);
				x2js = null;
			} else {
				WHRSCMain._CMS_allUserGroups = {UserGroups: {}};
			}
			utility.debugLog('WHRSCMain - getUserGroupMemberID() - Usergroup information is initialized.');
		}

		if (WHRSCMain._CMS_allUserGroups != null
				&& WHRSCMain._CMS_allUserGroups.cms_usergroup != null
				&& WHRSCMain._CMS_allUserGroups.cms_usergroup.record != null) {
			var foundGroups = WHRSCMain._CMS_allUserGroups.cms_usergroup.record.filter(function(node, index) {
				return node.NAME == userGroupName
			});
			if (foundGroups.length > 0) {
				userGroupMemberID = foundGroups[0].MEMBERID;
			}
		}
		return userGroupMemberID;
	},


	// Action Status on Transaction Tab
	resetActionStatus: function() {

		var actionStatusLabel = $('#RYB_STATUS option:selected').text();
		var actionStatusValue = $('#RYB_STATUS option:selected').val();
		if (actionStatusValue != '') {
			$('#actionStatus').text(actionStatusLabel);
		} else {
			$('#actionStatus').text('');
		}
		this.tabManager.resetTabs();
	},
		// Action Status on Transaction Tab
	resetDateReceived: function() {
		var dateReceived = $('#DATE_SF52_RECEIVED').val();
		if (dateReceived != '') {
			$('#dateReceived').text(dateReceived);
		} else {
			$('#dateReceived').text('');
		}
		this.tabManager.resetTabs();
	},

	resetRequisitionNumber: function() {
		var reqNumber = $('#A_CAP_HR_JOB_REQ').val();
		if (reqNumber != '') {
			$('#requsitionNumber').text(reqNumber);
		} else {
			$('#requsitionNumber').text('');
		}
		//this.tabManager.resetTabs();

	},

	_currentUserGroupData: null,

	getCurrentUserGroupData: function() {
		if (WHRSCMain._currentUserGroupData == null) {
			WHRSCMain._currentUserGroupData = $('#h_userGroups').val();
			if (WHRSCMain._currentUserGroupData == null || WHRSCMain._currentUserGroupData.length == 0) {
				utility.debugLog('WHRSCMain - getCurrentUserGroupData() - ActivityName is null or empty.');
			} else {
				$('#h_userGroups').val('');
			}
		}
		return WHRSCMain._currentUserGroupData;
	},
	_initUserGroupData: function() {
		var rawMyUserGroups = WHRSCMain.getCurrentUserGroupData();
		if (rawMyUserGroups != null && rawMyUserGroups.length > 0) {
			var x2js = new X2JS();
			WHRSCMain._CMS_myUserGroups = x2js.xml_str2json(rawMyUserGroups);
			x2js = null;
		} else {
			WHRSCMain._CMS_myUserGroups = {UserGroups: {}};
		}
	},
	isCurrentUserMemberOf: function(userGroupName) {
		if (userGroupName == null || userGroupName.length == 0) {
			utility.debugLog('WHRSCMain - isCurrentUserMemberOf() - userGroupName is null or empty');
		}
		if (WHRSCMain._CMS_myUserGroups == null) {
			WHRSCMain._initUserGroupData();
		}

		if (WHRSCMain._CMS_myUserGroups != null
				&& WHRSCMain._CMS_myUserGroups.UserGroups != null
				&& WHRSCMain._CMS_myUserGroups.UserGroups.UserGroup != null) {
			var foundGroups = WHRSCMain._CMS_myUserGroups.UserGroups.UserGroup.filter(function(node, index) {
				return node.Name == userGroupName
			});
			return foundGroups.length > 0;
		} else {
			return false;
		}
	},

	enableForModification: function() {
		var tabs = ['tab1','tab2','tab3','tab4','tab5','tab6'];
		tabs.forEach(function(tab) {
			this.tabManager.enableTab(tab);
			WHRSCMain.initMaxSizePerTab(tab);
		})

		var activityName = this.activityOption.getActivityName();
		var option = this.activityOption.getCurrentActivityOption(activityName);
		option.readonly = [];

		//TODO: adjust ELIGQUAL form specific elements
		hyf.util.enableComponent('button_apscm_return_2');
		utility.disableComponents(['IS_SO_ACK','IS_HR_CLS_SPC_APR','IS_HR_STF_SPC_APR','btnCancelWorkitem'])
	},
	popupModifyRequest: function() {
		bootbox.dialog({
			message: 'Are you sure you want to modify worksheet?',
			onEscape: true,
			buttons: [{
				label: 'Yes',
				className: 'btn-success',
				callback: WHRSCMain.enableForModification
			}, {
				label: 'No',
				className: 'btn-danger'
			}]
		});
	},
	popupCancellation: function() {
		var isUserSO = WHRSCMain.isCurrentUserMemberOf('Selecting Officials');
		var isUserLiaison = WHRSCMain.isCurrentUserMemberOf('HR Liaison');
		var isXO = WHRSCMain.isCurrentUserMemberOf('Executive Officers');
		var isClassificationSpecialist = WHRSCMain.isCurrentUserMemberOf('HR Classification Specialists');
		var isStaffingSpecialist = WHRSCMain.isCurrentUserMemberOf('HR Staffing Specialists');

		var reasons = [];

		if (isUserSO == true) {
			reasons = LookupManager.findByLTYPE('UserGroup[Selecting Officials]/CancellationReason');
		} else if (isUserLiaison == true) {
			reasons = LookupManager.findByLTYPE('UserGroup[HR Liaison]/CancellationReason');
		} else if (isXO == true) {
			reasons = LookupManager.findByLTYPE('UserGroup[Executive Officers]/CancellationReason');
		} else if (isClassificationSpecialist == true) {
			reasons = LookupManager.findByLTYPE('UserGroup[HR Classification Specialists]/CancellationReason');
		} else if (isStaffingSpecialist == true) {
			reasons = LookupManager.findByLTYPE('UserGroup[HR Staffing Specialists]/CancellationReason');
		} else {
			reasons = LookupManager.findByLTYPE('UserGroup[Default]/CancellationReason');
		}

		var options = '<option value>Select one</option>';
		reasons.forEach(function(reason) {
			options = options + '<option value=' + reason.LABEL + '>' + reason.LABEL + '</option>';
		});

		var dialog = bootbox.dialog({
			title: 'Reason for Cancellation',
			message: '<span>Cancellation Reason</span><span class="mandatory" style="" title="Mandatory field"> * </span><span>:&nbsp;</span><select name="CancellationReason">' + options + '</select>',
			onEscape: true,
			buttons: {
				confirm: {
					label: 'OK',
					className: 'btn-success',
					callback: function() {
						var message = $('div.bootbox select option:selected').text();
						if (message == null || message.length == 0) {
							return false;
						}

						setTimeout(function() {
							utility.greyOutScreen(true);
							this.tabManager.enableTabsForSubmission();
							$('#WIH_complete_requested').val('true');
							$('#pv_requestStatus').val('Request Cancelled');
							$('#pv_requestStatusDate').val(utility.getNowUTCString());
							$('#pv_CancelReason').val(message);
							utility.submitFormPage('btnCancelWorkitem', 'saveNewForm');
						}, 0);
					}
				},
				cancel: {
					label: 'Cancel',
					className: 'btn-danger'
				}
			}
		});

		$('div.bootbox button.btn-success').prop('disabled', true);

		$('div.bootbox select').on('change keyup', function() {
			var message = $('div.bootbox select option:selected').val();
			if (message == '') {
				$('div.bootbox button.btn-success').prop('disabled', true);
			} else {
				$('div.bootbox button.btn-success').prop('disabled', false);
			}
		});
	},
	showAlertMessage: function(message) {
		var dialog = bootbox.dialog({
			title: 'Requested Changes',
			message: '<textarea disabled rows="5" class="bootbox-input bootbox-input-text form-control">' + message + '</textarea>',
			onEscape: true,
			buttons: {
				confirm: {
					label: 'OK',
					className: 'btn-success',
				}
			}
		});
	},
	/*
	validateTabCustomCallback: function(tabId){
		var result = true;
		if (result == true && tabId == 'tab2'){
			result = validateEligQualPosCustom();
		}
		return result;
	},
	*/
	//
	//
	// Form Initialization function
	// This function will be called when the mainform is loaded.
	init: function() {
		var ui = basicWIHActionClient.getUI();
		ui.getPane(ui.PANE_MENU).show();

		var requestNumber = $('#pvGetRequestNumber').val();
		if(requestNumber=='') {
			requestNumber = $('#pv_requistionNumber').val();
		}

		//var requestNumber = $('#pv_requistionNumber').val();
		var usasPath = $('#pv_usasPath').val();
		var usasUrl = usasPath + '/appointment/' + requestNumber;
		$('#husasResult').val("true");
		if (requestNumber != '') {
			$.ajax({
				//url: '/usasrwsc/usas/reportXML/appointment/'+ requestNumber,   //+ requestNumber, //070707',
				url: usasUrl,
				dataType: 'text',
				cache: false,
				async: false,
				success: function (resultObj){
					if(resultObj=='' || resultObj==null) {
						alert("An exception occurred. Please check the logs.");
						WHRSCMain._USAS_DATA = null;
					}
					else {
						var indx = resultObj.indexOf('>');
						resultObj = resultObj.substring(indx+1);
						var x2js = new X2JS();
						var getRestDataXML =  x2js.xml_str2json(resultObj);

						var resultCode = getRestDataXML.USAStaffing_Appointment.Result_Code;
						if(resultCode!='Success') {
							//alert(getRestDataXML.USAStaffing_Appointment.Failure_Message);
							alert('No USA Staffing data could be retrieved for this request number.');
							$('#husasResult').val("false");
							WHRSCMain._USAS_DATA = null;
						}
						else {
							if(getRestDataXML.USAStaffing_Appointment.Vacancy==undefined)
							{
								alert("VIN number(s) not found. Please review the USAStaffing for the Vacancy Information and update as necessary. You can then return to EWITS 2.0 to continue processing the action.");
								WHRSCMain._USAS_DATA = null;
							}
							else if (getRestDataXML.USAStaffing_Appointment.Vacancy.Certificate==undefined)
							{
								alert("Missing Certificate Information. Please review the USAStaffing for the Certificate information and update as required. You can then return to EWITS 2.0 to continue processing the action.");
									WHRSCMain._USAS_DATA = null;
							}
							else
									WHRSCMain._USAS_DATA = resultObj;

						}
					}
				}
			});

		} else {
			WHRSCMain._USAS_DATA = null;
		}


		// instantiate dependent objects
		this.activityOption = new BFActivityOption(this.actList, $('#h_activityName').val());
		this.tabManager = new TabManager(this.tabList, this.activityOption, 'tab6', 'WHRSCMain', this.validateTabCustomCallback);

		// Following should be called to reduce redundant network traffic.
		WHRSCMain.getCurrentUserGroupData();

		if (isReadOnly() == true) {
			var activityName = this.activityOption.getActivityName();
			var curActivityOption = this.activityOption.getCurrentActivityOption(activityName);
			curActivityOption.readonly = ['tab1','tab2','tab3','tab4','tab5','tab6'];
		}

		LookupManager.init();
		this.tabManager.installCustomTabChanger();

		this.tabManager.initTab(WHRSCMain.tabList, WHRSCMain._USAS_DATA);



		// set focus on the current tab
		$('a.selectedTab').focus();

	}
}

var _readOnly = null;
function isReadOnly() {
	if (_readOnly == null) {
		_readOnly = $('#h_readOnly').val();
		if (_readOnly == 'y') {
			_readOnly = true;
		} else {
			_readOnly = false;
		}
	}
	return _readOnly;
}

function openUserGuide() {
	window.open("custom/doc/WHRSC Appointment User Guide.docx");
}

function checkMandatory() {
	var alertMassage = "You must specify a value for the following for the field(s)";
	var ret = true;
	var responseName = getSelectedResponse();

	if(responseName=="Send to HRA") {
        if($('#A_CAP_HR_JOB_REQ').val()==''){
			alertMassage = alertMassage + "\n Requisition Number";
			ret = false;
		}
		if($('#HR_ASSISTANT_ID').val()==''){
			alertMassage = alertMassage + "\n HR Assistant";
			ret = false;
		}
		if($('#A_POSITION_TITL').val()==''){
			alertMassage = alertMassage + "\n Position Title";
			ret = false;
		}
		if($('#A_PAY_PLAN').val()==''){
			alertMassage = alertMassage + "\n Pay Plan";
			ret = false;
		}
		if($('#A_SERIES').val()==''){
			alertMassage = alertMassage + "\n Series";
			ret = false;
		}
		if($('#A_GRADE').val()==''){
			alertMassage = alertMassage + "\n Grade";
			ret = false;
		}
		if($('#INSTITUTE').val()==''){
			alertMassage = alertMassage + "\n Customer";
			ret = false;
		}
		if($('#ORG_INITS').val()==''){
			alertMassage = alertMassage + "\n Organization Initials";
			ret = false;
		}
		if($('#ADMIN_CODE').val()==''){
			alertMassage = alertMassage + "\n Administrative Code";
			ret = false;
		}
		if(ret ==false){alert(alertMassage); return false;}
		else {
			$('#h_updateRyb').val('false');
			return ret;
		}
     }
	else if(responseName=="Reassign to New HRS") {
		if($('#HR_SPECIALIST_ID').val()==''){
			alertMassage = alertMassage + "\n HR Specialist";
			ret = false;
		}
		if(ret ==false){alert(alertMassage); return false;}
		else {
			$('#h_updateRyb').val('false');
			return ret;
		}
	}
    else if(responseName=="Send Package Received Email") {
        if($('#A_CAP_HR_JOB_REQ').val()==''){
			alertMassage = alertMassage + "\n Requisition Number";
			ret = false;
		}
		if($('#h_email').val()==''){
			alertMassage = alertMassage + "\n HR liaison Last Name";
			ret = false;
		}
		if($('#h_a_emp_lastname').val()==''){
			alertMassage = alertMassage + "\n Employee's Last Name";
			ret = false;
		}
		if($('#h_a_emp_firstname').val()==''){
			alertMassage = alertMassage + "\n Employee's First Name";
			ret = false;
		}
		if($('#A_POSITION_TITL').val()==''){
			alertMassage = alertMassage + "\n Position Title";
			ret = false;
		}
		if($('#INSTITUTE').val()==''){
			alertMassage = alertMassage + "\n Customer";
			ret = false;
		}
		if($('#ADMIN_CODE').val()==''){
			alertMassage = alertMassage + "\n Administrative Code";
			ret = false;
		}
		if(ret ==false){alert(alertMassage); return false;}
		else {
			$('#h_updateRyb').val('false');
			return ret;
		}

    }
    else if(responseName=="Send Package Incomplete Email") {
		if($('#A_CAP_HR_JOB_REQ').val()==''){
			alertMassage = alertMassage + "\n Requisition Number";
			ret = false;
		}
		if($('#HR_SPECIALIST_ID').val()==''){
			alertMassage = alertMassage + "\n HR Specialist";
			ret = false;
		}
		if($('#HR_ASSISTANT_ID').val()==''){
			alertMassage = alertMassage + "\n HR Assistant";
			ret = false;
		}
		//if($('#h_lastname').val()==''){
		//	alertMassage = alertMassage + "\n HR liaison Last Name";
		//	ret = false;
		//}
		if($('#HR_SENIOR_ADVISOR_ID').val()==''){
			alertMassage = alertMassage + "\n HR Senior Advisor";
			ret = false;
		}
		if($('#h_a_emp_lastname').val()==''){
			alertMassage = alertMassage + "\n Employee's Last Name";
			ret = false;
		}
		if($('#h_a_emp_firstname').val()==''){
			alertMassage = alertMassage + "\n Employee's First Name";
			ret = false;
		}
		if($('#A_POSITION_TITL').val()==''){
			alertMassage = alertMassage + "\n Position Title";
			ret = false;
		}
		if($('#ADMIN_CODE').val()==''){
			alertMassage = alertMassage + "\n Administrative Code";
			ret = false;
		}
		if ($('#MISSING_DOCS').val()=='') 	{
			alertMassage = alertMassage + "\n List of Missing Docs/Info";
			ret = false;
		}
		if($('#PCKG_COMPLETE option:selected').val() =='Yes') {
			alertMassage = alertMassage + "\n The package complete must be 'NO'";
			ret = false;
		}

		if(ret ==false){alert(alertMassage); return false;}
		else {
			if($('#MISSING_DOCS_EMAIL_SENT_DATE').val()=='') {
				var now = new Date();
				var sysDate = utility.getDateString({isUTC: false, dateFormat: 'mm/dd/yyyy'}, now);
				$('#MISSING_DOCS_EMAIL_SENT_DATE').val(sysDate);
			}
			$('#h_updateRyb').val('false');
			return ret;
		}

    }

    else if(responseName=="Send to DEU (Selection Approval)") {
		if($('#A_CAP_HR_JOB_REQ').val()==''){
			alertMassage = alertMassage + "\n Requisition Number";
			ret = false;
		}
		if($('#HR_SPECIALIST_ID').val()==''){
			alertMassage = alertMassage + "\n HR Specialist";
			ret = false;
		}
		/*
		if($('#AP_DEU_SEL_APPROVER').val()==''){
			alertMassage = alertMassage + "\n DEU Selection Approver";
			ret = false;
		}
		*/
		if($('#TEAM_LEADER_ID').val()==''){
			alertMassage = alertMassage + "\n Team Leader";
			ret = false;
		}
		if($('#h_a_emp_lastname').val()==''){
			alertMassage = alertMassage + "\n Employee's Last Name";
			ret = false;
		}
		if($('#h_a_emp_firstname').val()==''){
			alertMassage = alertMassage + "\n Employee's First Name";
			ret = false;
		}
		if($('#A_POSITION_TITL').val()==''){
			alertMassage = alertMassage + "\n Position Title";
			ret = false;
		}
		/*
		if($('#A_RELATE_RECR_REQ').val()==''){
			alertMassage = alertMassage + "\n Vacancy Identification Number";
			ret = false;
		}
		*/
		if($('#ADMIN_CODE').val()==''){
			alertMassage = alertMassage + "\n Administrative Code";
			ret = false;
		}
		if(ret ==false){alert(alertMassage); return false;}
		else {
			if($('#AP_DATE_DEU_DECISION').val()=='') {
				var now = new Date();
				var sysDate = utility.getDateString({isUTC: false, dateFormat: 'mm/dd/yyyy'}, now);
				$('#AP_DATE_DEU_DECISION').val(sysDate);
			}
			var now2 = new Date();
			var sysDate2= utility.getDateString({isUTC: true, dateFormat: 'yyyy/mm/dd hh:MM:ss'}, now2);
			$('#pv_dateSentDeu').val(sysDate2);
			$('#pv_deStatus').val('DEU Active');

			$('#h_rybCode').val('Red');
			$('#h_rybStatus').val('Active in HR');
			$('#h_rybDesc').val('Selection Under Review - DE');
			$('#h_updateRyb').val('true');
			$('#h_reqAdminEditable').val('no');

			return ret;
		}
   }
   else if(responseName=="Send to Final Authorizer") {
		if($('#A_CAP_HR_JOB_REQ').val()==''){
			alertMassage = alertMassage + "\n Requisition Number";
			ret = false;
		}
		if($('#HR_SPA_ID').val()==''){
			alertMassage = alertMassage + "\n Final Authorizer";
			ret = false;
		}
		if($('#A_POSITION_TITL').val()==''){
			alertMassage = alertMassage + "\n Position Title";
			ret = false;
		}
		if($('#A_PAY_PLAN').val()==''){
			alertMassage = alertMassage + "\n Pay Plan";
			ret = false;
		}
		if($('#A_SERIES').val()==''){
			alertMassage = alertMassage + "\n Series";
			ret = false;
		}
		if($('#A_GRADE').val()==''){
			alertMassage = alertMassage + "\n Grade";
			ret = false;
		}
		if($('#INSTITUTE').val()==''){
			alertMassage = alertMassage + "\n Customer";
			ret = false;
		}
		if($('#ORG_INITS').val()==''){
			alertMassage = alertMassage + "\n Organization Initials";
			ret = false;
		}
		if($('#ADMIN_CODE').val()==''){
			alertMassage = alertMassage + "\n Administrative Code";
			ret = false;
		}
		if(ret ==false){alert(alertMassage); return false;}
		else {

			//var now = new Date();
			//var sysDate = utility.getDateString({isUTC: false, dateFormat: 'yyyy/mm/dd hh:MM:ss'}, now);
			//$('#h_sentSPADate').val(sysDate);
			$('#h_updatesentSPA').val('true');
			$('#h_updateRyb').val('false');
			$('#h_reqAdminEditable').val('no');
			return ret;
		}

    }
   else if(responseName=="Cancel Action") {
	   	var conf = confirm("Are you sure you wish to proceed? ");
		if (conf==false) {return false};
		$('#h_status').val('CANCELLED');
		var now = new Date();
		var sysDate = utility.getDateString({isUTC: false, dateFormat: 'yyyy/mm/dd hh:MM:ss'}, now);
		$('#h_statusDate').val(sysDate);
		$('#h_statusUserID').val($('#h_currentUserID').val());
		$('#h_updateRyb').val('false');
        return ret;

    }
    else if(responseName=="Send to HRS") {
        if($('#A_CAP_HR_JOB_REQ').val()==''){
			alertMassage = alertMassage + "\n Requisition Number";
			ret = false;
		}
		if($('#HR_SPECIALIST_ID').val()==''){
			alertMassage = alertMassage + "\n HR Specialist";
			ret = false;
		}
		if($('#A_POSITION_TITL').val()==''){
			alertMassage = alertMassage + "\n Position Title";
			ret = false;
		}
		if($('#A_PAY_PLAN').val()==''){
			alertMassage = alertMassage + "\n Pay Plan";
			ret = false;
		}
		if($('#A_SERIES').val()==''){
			alertMassage = alertMassage + "\n Series";
			ret = false;
		}
		if($('#A_GRADE').val()==''){
			alertMassage = alertMassage + "\n Grade";
			ret = false;
		}
		if($('#INSTITUTE').val()==''){
			alertMassage = alertMassage + "\n Customer";
			ret = false;
		}
		if($('#ORG_INITS').val()==''){
			alertMassage = alertMassage + "\n Organization Initials";
			ret = false;
		}
		if($('#ADMIN_CODE').val()==''){
			alertMassage = alertMassage + "\n Administrative Code";
			ret = false;
		}
		if(ret ==false){alert(alertMassage); return false;}
		else {
			$('#h_updateRyb').val('false');
			return ret;
		}
    }
   else if(responseName=="Action Complete") {
	   var conf = confirm("Are you sure you wish to proceed? ");
		if (conf==false) {return false};

		if($('#A_CAP_HR_JOB_REQ').val()==''){
			alertMassage = alertMassage + "\n Requisition Number";
			ret = false;
		}
		if($('#h_a_emp_lastname').val()==''){
			alertMassage = alertMassage + "\n Employee's Last Name";
			ret = false;
		}
		if($('#h_a_emp_firstname').val()==''){
			alertMassage = alertMassage + "\n Employee's First Name";
			ret = false;
		}
		if($('#A_POSITION_TITL').val()==''){
			alertMassage = alertMassage + "\n Position Title";
			ret = false;
		}
		//transaction Info
		if($('#h_lastname').val()==''){
			alertMassage = alertMassage + "\n HR liaison Last Name";
			ret = false;
		}
		if($('#GLOBAL_RECRUITMENT').val()==''){
			alertMassage = alertMassage + "\n SSB Recruitment";
			ret = false;
		}
		if($('#DATE_SF52_RECEIVED').val()==''){
			alertMassage = alertMassage + "\n Date Recv'd in HR";
			ret = false;
		}
		if($('#ADMIN_CODE').val()==''){
			alertMassage = alertMassage + "\n Administrative Code";
			ret = false;
		}
		if($('#INSTITUTE').val()==''){
			alertMassage = alertMassage + "\n Customer";
			ret = false;
		}
		/*
		if($('#PROPOSED_EFF_DATE').val()==''){
			alertMassage = alertMassage + "\n Proposed Effective Date";
			ret = false;
		}
		*/
		if($('#BRANCH_CHIEF_ID').val()==''){
			alertMassage = alertMassage + "\n Branch Chief";
			ret = false;
		}
		if($('#TEAM_LEADER_ID').val()==''){
			alertMassage = alertMassage + "\n Team Leader";
			ret = false;
		}
		if($('#HR_SENIOR_ADVISOR_ID').val()==''){
			alertMassage = alertMassage + "\n HR Senior Advisor";
			ret = false;
		}
		if($('#HR_SPECIALIST_ID').val()==''){
			alertMassage = alertMassage + "\n HR Specialist";
			ret = false;
		}
		if($('#HR_ASSISTANT_ID').val()==''){
			alertMassage = alertMassage + "\n HR Assistant";
			ret = false;
		}
		if($('#HR_SPA_ID').val()==''){
			alertMassage = alertMassage + "\n Final Authorizer";
			ret = false;
		}
		if($('#PCKG_COMPLETE').val()==''){
			alertMassage = alertMassage + "\n Is the package complete";
			ret = false;
		}
		if($('#PRIORITY').val()==''){
			alertMassage = alertMassage + "\n Priority";
			ret = false;
		}
		/*
		if($('#RYB_CODE').val()==''){
			alertMassage = alertMassage + "\n Action Status Code";
			ret = false;
		}
		*/
		if($('#RYB_STATUS').val()==''){
			alertMassage = alertMassage + "\n Action Status";
			ret = false;
		}
		/*
		if($('#RYB_DESCRIPTION').val()==''){
			alertMassage = alertMassage + "\n Action Status Description";
			ret = false;
		}
		*/
		//Appointment Information
		/*
		if($('#A_RELATE_RECR_REQ').val()==''){
			alertMassage = alertMassage + "\n Vacancy Identification Number";
			ret = false;
		}
		*/
		if($('#A_HR_CARDS_PD_USED').val()==''){
			alertMassage = alertMassage + "\n Recruitment Document Repository Used";
			ret = false;
		}
		if($('#A_LEGISLATIVE_INITIATIVE_USED').val()==''){
			alertMassage = alertMassage + "\n Legislative Initiative Supported";
			ret = false;
		}
		if($('#A_ADDITIONAL_APPROVAL_REQ').val()==''){
			alertMassage = alertMassage + "\n Additional Approval Required";
			ret = false;
		}
		if($('#A_APPOINT_TYPE').val()==''){
			alertMassage = alertMassage + "\n Appointment Type";
			ret = false;
		}
		if($('#A_FILER278').val()==''){
			alertMassage = alertMassage + "\n OGE - 278 Filer";
			ret = false;
		}
		if($('#A_NAT_ACT_CD').val()==''){
			alertMassage = alertMassage + "\n Nature of Action Code";
			ret = false;
		}
		if ($('#F_EFFECTIVE_DATE').val() == '') {
			alertMassage = alertMassage + "\n Effective Date";
			ret = false;
		}
		var hiringFlexType = $('#A_HIRING_FLEX_TYPE').val();
		if(hiringFlexType!='') {
			hiringFlexType = hiringFlexType.substring(0,10);
			if(hiringFlexType == 'Schedule D') {
				if($('#A_IS_PATHWAY_AGREE_COMP').val()==''){
					alertMassage = alertMassage + "\n Pathways Agreement Completed";
					ret = false;
				}
				if($('#A_EXP_DATE_PRG_COM').val()==''){
					alertMassage = alertMassage + "\n Expected Date of Program Completion";
					ret = false;
				}
			}
		}

		if($('#A_BASIC_PAY_MIN').val()==''){
			alertMassage = alertMassage + "\n Basic Pay";
			ret = false;
		}
		if($('#A_BASIC_PAY_MAX').val()==''){
			alertMassage = alertMassage + "\n Adjusted Basic Pay";
			ret = false;
		}
		if($('#A_ELIGIBLE_FOR_BENEFITS').val()==''){
			alertMassage = alertMassage + "\n Eligible for Benefits";
			ret = false;
		}
		if($('#A_IS_REEMPLOYED_ANNUITANT').val()==''){
			alertMassage = alertMassage + "\n Reemployed Annuitant";
			ret = false;
		}
		//Orientation Information
		if($('#O_AO').val()==''){
			alertMassage = alertMassage + "\n Attending Orientation";
			ret = false;
		}
		if($('#O_DOO').val()==''){
			alertMassage = alertMassage + "\n Date Official Offer Letter Sent";
			ret = false;
		}
		if(ret ==false){alert(alertMassage); return false;}
		else {
			$('#h_status').val('COMPLETED');
			var now = new Date();
			var sysDate = utility.getDateString({isUTC: false, dateFormat: 'yyyy/mm/dd hh:MM:ss'}, now);
			$('#h_statusDate').val(sysDate);
			$('#h_statusUserID').val($('#h_currentUserID').val());

			$('#h_rybCode').val('Blue');
			$('#h_rybStatus').val('Completed');
			$('#h_rybDesc').val(' ');
			$('#h_updateRyb').val('true');

			return ret;
		}

    }
   else if(responseName=="Return to HRS") {
	    if($('#A_CAP_HR_JOB_REQ').val()==''){
			alertMassage = alertMassage + "\n Requisition Number";
			ret = false;
		}
		if($('#HR_SPECIALIST_ID').val()==''){
			alertMassage = alertMassage + "\n HR Specialist";
			ret = false;
		}
		if($('#A_POSITION_TITL').val()==''){
			alertMassage = alertMassage + "\n Position Title";
			ret = false;
		}
		if($('#A_PAY_PLAN').val()==''){
			alertMassage = alertMassage + "\n Pay Plan";
			ret = false;
		}
		if($('#A_SERIES').val()==''){
			alertMassage = alertMassage + "\n Series";
			ret = false;
		}
		if($('#A_GRADE').val()==''){
			alertMassage = alertMassage + "\n Grade";
			ret = false;
		}
		if($('#INSTITUTE').val()==''){
			alertMassage = alertMassage + "\n Customer";
			ret = false;
		}
		if($('#ORG_INITS').val()==''){
			alertMassage = alertMassage + "\n Organization Initials";
			ret = false;
		}
		if($('#ADMIN_CODE').val()==''){
			alertMassage = alertMassage + "\n Administrative Code";
			ret = false;
		}
		if(ret ==false){alert(alertMassage); return false;}
		else {
			$('#h_updateRyb').val('false');
			return ret;
		}

    }
   else if(responseName=="Return to HRA") {
		if($('#A_CAP_HR_JOB_REQ').val()==''){
			alertMassage = alertMassage + "\n Requisition Number";
			ret = false;
		}
		if($('#HR_ASSISTANT_ID').val()==''){
			alertMassage = alertMassage + "\n HR Assistant";
			ret = false;
		}
		if($('#A_POSITION_TITL').val()==''){
			alertMassage = alertMassage + "\n Position Title";
			ret = false;
		}
		if($('#A_PAY_PLAN').val()==''){
			alertMassage = alertMassage + "\n Pay Plan";
			ret = false;
		}
		if($('#A_SERIES').val()==''){
			alertMassage = alertMassage + "\n Series";
			ret = false;
		}
		if($('#A_GRADE').val()==''){
			alertMassage = alertMassage + "\n Grade";
			ret = false;
		}
		if($('#INSTITUTE').val()==''){
			alertMassage = alertMassage + "\n Customer";
			ret = false;
		}
		if($('#ORG_INITS').val()==''){
			alertMassage = alertMassage + "\n Organization Initials";
			ret = false;
		}
		if($('#ADMIN_CODE').val()==''){
			alertMassage = alertMassage + "\n Administrative Code";
			ret = false;
		}
		if(ret ==false){alert(alertMassage); return false;}
		else {
			$('#h_updateRyb').val('false');
			return ret;
		}

    }
   else if(responseName=="Selection Approved-Return to Branch") {
		if($('#AP_DEU_SEL_DECISION option:selected').val()!='Approved') {
			alertMassage = alertMassage + "\n DEU Selection Decision must  be 'Approved'";
			ret = false;
		}
		if($('#A_CAP_HR_JOB_REQ').val()==''){
			alertMassage = alertMassage + "\n Requisition Number";
			ret = false;
		}
		if($('#HR_SPECIALIST_ID').val()==''){
			alertMassage = alertMassage + "\n HR Specialist";
			ret = false;
		}
		if($('#AP_DEU_SEL_APPROVER').val()==''){
			alertMassage = alertMassage + "\n DEU Selection Approver";
			ret = false;
		}
		if($('#TEAM_LEADER_ID').val()==''){
			alertMassage = alertMassage + "\n Team Leader";
			ret = false;
		}
		if($('#A_POSITION_TITL').val()==''){
			alertMassage = alertMassage + "\n Position Title";
			ret = false;
		}
		if($('#h_a_emp_lastname').val()==''){
			alertMassage = alertMassage + "\n Employee's Last Name";
			ret = false;
		}
		if($('#h_a_emp_firstname').val()==''){
			alertMassage = alertMassage + "\n Employee's First Name";
			ret = false;
		}
		/*
		if($('#A_RELATE_RECR_REQ').val()==''){
			alertMassage = alertMassage + "\n Vacancy Identification Number";
			ret = false;
		}
		*/
		if(ret ==false){alert(alertMassage); return false;}
		else {
			$('#h_updateRyb').val('false');
			return ret;
		}

    }
   else if(responseName=="Selection Disapproved-Return to Branch") {
		if($('#AP_DEU_SEL_DECISION option:selected').val()!='Disapproved') {
			alertMassage = alertMassage + "\n DEU Selection Decision must  be 'Disapproved'";
			ret = false;
		}
		if($('#AP_DEU_SEL_DECISION_COM').val()==''){
			alertMassage = alertMassage + "\n DEU Selection Decision Comments";
			ret = false;
		}
		if($('#A_CAP_HR_JOB_REQ').val()==''){
			alertMassage = alertMassage + "\n Requisition Number";
			ret = false;
		}
		if($('#HR_SPECIALIST_ID').val()==''){
			alertMassage = alertMassage + "\n HR Specialist";
			ret = false;
		}
		if($('#AP_DEU_SEL_APPROVER').val()==''){
			alertMassage = alertMassage + "\n DEU Selection Approver";
			ret = false;
		}
		if($('#TEAM_LEADER_ID').val()==''){
			alertMassage = alertMassage + "\n Team Leader";
			ret = false;
		}
		if($('#A_POSITION_TITL').val()==''){
			alertMassage = alertMassage + "\n Position Title";
			ret = false;
		}
		if($('#h_a_emp_lastname').val()==''){
			alertMassage = alertMassage + "\n Employee's Last Name";
			ret = false;
		}
		if($('#h_a_emp_firstname').val()==''){
			alertMassage = alertMassage + "\n Employee's First Name";
			ret = false;
		}
		/*
		if($('#A_RELATE_RECR_REQ').val()==''){
			alertMassage = alertMassage + "\n Vacancy Identification Number";
			ret = false;
		}
		*/
		if(ret ==false){alert(alertMassage); return false;}
		else {
			$('#h_updateRyb').val('false');
			return ret;
		}

    }
   else if(responseName=="More Info Needed-Return to Branch") {
	   	if($('#AP_DEU_SEL_DECISION option:selected').val()!='Return for Additional Action') {
			alertMassage = alertMassage + "\n DEU Selection Decision must  be 'Return for Additional Action'";
			ret = false;
		}
		if($('#AP_DEU_SEL_DECISION option:selected').val()=='Return for Additional Action') {
			if($('#AP_ADD_DEU_SEL_INFO').val()=='') {
				alertMassage = alertMassage + "\n Additional DEU Selection Info Needed";
				ret = false;
			}
		}
		if($('#A_CAP_HR_JOB_REQ').val()==''){
			alertMassage = alertMassage + "\n Requisition Number";
			ret = false;
		}
		if($('#HR_SPECIALIST_ID').val()==''){
			alertMassage = alertMassage + "\n HR Specialist";
			ret = false;
		}
		if($('#AP_DEU_SEL_APPROVER').val()==''){
			alertMassage = alertMassage + "\n DEU Selection Approver";
			ret = false;
		}
		if($('#TEAM_LEADER_ID').val()==''){
			alertMassage = alertMassage + "\n Team Leader";
			ret = false;
		}
		if($('#A_POSITION_TITL').val()==''){
			alertMassage = alertMassage + "\n Position Title";
			ret = false;
		}
		if($('#h_a_emp_lastname').val()==''){
			alertMassage = alertMassage + "\n Employee's Last Name";
			ret = false;
		}
		if($('#h_a_emp_firstname').val()==''){
			alertMassage = alertMassage + "\n Employee's First Name";
			ret = false;
		}
		/*
		if($('#A_RELATE_RECR_REQ').val()==''){
			alertMassage = alertMassage + "\n Vacancy Identification Number";
			ret = false;
		}
		*/
		if(ret ==false){alert(alertMassage); return false;}
		else {
			$('#h_updateRyb').val('false');
			return ret;
		}

    }

   else
   {

     alert("Cannot find Response");

     return false;

    }

}

function getSelectedResponse()
{
    var response = "";

    try
    {
        var obj = basicWIHActionClient.getSelectedResponse();
        if(obj)
        {
            return obj.NAME;
        }
    }
    catch (e)
    {
        response = "";
    }

    return response;
}
