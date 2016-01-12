Ext.apply(EwayLocale,{
	addSuccess : 'Add successfully.',//EwayLocale.addSuccess
	updateSuccess : 'Update successfully.',//EwayLocale.updateSuccess
	deleteSuccess : 'Delete successfully.',//EwayLocale.deleteSuccess
	choiceUpdateMsg :'Please select the record which you want to update.',//EwayLocale.choiceUpdateMsg
	choiceDeleteMsg :'Please select the record which you want to delete.',//EwayLocale.choiceDeleteMsg

	myTable:'Workbench',//EwayLocale.myTable
	ATMV:'ATM View',//EwayLocale.ATMV
	welcome:'Welcome,',//EwayLocale.welcome
	personalConf:'Personal Settings',//EwayLocale.personalConf
	systemHelp:'Help',//EwayLocale.systemHelp
	exitSystem:'Log Out',//EwayLocale.exitSystem
	msg:{
		perviewFailForText:'Preview failed:the charater and rolling advertisement is supported to preview',//EwayLocale.msg.perviewFailForText
		choseResToPerview:'Please select the advertisement which you want to preview.',//EwayLocale.msg.choseResToPerview
		noAdvertResAtTheResolution:'No allocation of advertising resources under Resolution.',//EwayLocale.msg.noAdvertResAtTheResolution
		chooseAdvert:'Please select an advertisement.',//EwayLocale.msg.chooseAdvert
		chooseOneDevice:'Please select a machine.',//EwayLocale.msg.chooseOneDevice
		downLoadedAdvertCantDelete:'Delete failed:can not delete the advertisement which status is "issued" or "wait for issue".',//EwayLocale.msg.downLoadedAdvertCantDelete
		chooseAdvertToDelete:'Please select the advertisement which you want to delete.',//EwayLocale.msg.chooseAdvertToDelete
		generalVersionFailForDownloaded:'Generate  the version-file failed:the status "issued" advertisement can not generate version-information any more',//EwayLocale.msg.generalVersionFailForDownloaded
		generalVersionSuccess:"Generate the version file successful",//EwayLocale.msg.generalVersionSuccess
		createSuccess:"Create Successfully.",//EwayLocale.msg.createSuccess
		mustHaveOneResource:'At least has one resource of advertisement!',//EwayLocale.msg.mustHaveOneResource
		saveFileCommunicationFail:'Save failed:connection refused',//EwayLocale.msg.saveFileCommunicationFail
		downloadFailForNoVersion:"Version file issue failed :no generated version-file found or version-file lost,please generate version file first.",//EwayLocale.msg.downloadFailForNoVersion
		saveSuccess:'Save successfully',//EwayLocale.msg.saveSuccess

		removeSuccess:'Relieve success',//EwayLocale.msg.removeSuccess
		removeFail:'Relieve failure',//EwayLocale.msg.removeFail
		versionDownloaded:'Can not delete the version which status is "issued" or "wait for issue"',//EwayLocale.msg.versionDownloaded
		selectVersionToDelete:'Please choose the version which you want to delete',//EwayLocale.msg.selectVersionToDelete
		communicationFail:'Add failed : connction refused.',//EwayLocale.msg.communicationFail
		sameVersionNoFail:'Add failed：the same version already exists',//EwayLocale.msg.sameVersionNoFail
		fileSizeMaxFail:'Add failed:the max size of the file is 200M',//EwayLocale.msg.fileSizeMaxFail
		fileUnzipFail:'Add failed: zip-file can not be unziped',//EwayLocale.msg.fileUnzipFail
		addFileFail:'Add failed :',//EwayLocale.msg.addFileFail
		mustSelectDevice:'please choose at least one machine.',//EwayLocale.msg.mustSelectDevice
		selectVersionRecord:'Please choose the version which you want to issue',//EwayLocale.msg.selectVersionRecord
		missVersionFile:"Version files lost,can not execute",//EwayLocale.msg.missVersionFile
	},
	confirm:{
		titleSure:'Confirm',//EwayLocale.confirm.titleSure
		todoDelete:'Delete this record?',//EwayLocale.confirm.todoDelete
		title:'Tip',//EwayLocale.confirm.title
		withoutNumTaskConfirmInfo:'Job save successful,skip to the "Deployment Monitor" page?',//EwayLocale.confirm.withoutNumTaskConfirmInfo
		timeout:'Session timeout, 3 seconds after the automatic jump to the system login page',//EwayLocale.confirm.timeout
		taskConfirmInfo0:' Save No.',//EwayLocale.confirm.taskConfirmInfo0
		taskConfirmInfo1:' job sucessfully,skip to `Deployment Monitor` page?'//EwayLocale.confirm.taskConfirmInfo1
	},
	button:{
		search:'Search',//EwayLocale.button.search
		add : 'Add', //EwayLocale.button.add
		update:'Update',//EwayLocale.button.update
		remove:'Delete',//EwayLocale.button.remove
		refresh:'Refresh',//EwayLocale.button.refresh
		reset:'Reset',//EwayLocale.button.reset
		back:'Back',//EwayLocale.button.back
		apply:'Apply',//EwayLocale.button.apply
		link:'Binding',//EwayLocale.button.link
		unlink:'Delete',//EwayLocale.button.unlink
		
		bankOrgMove:'Migrate',  //EwayLocale.button.bankOrgMove
		bankOrgAdmin:'Manager', //EwayLocale.button.bankOrgAdmin
		
		bankPerlink:'Binding Devices', //EwayLocale.button.bankPerlink
		sure:'Confirm',//EwayLocale.button.sure
		confirm:'Confirm',//EwayLocale.button.confirm
		cancle:'Cancel',//EwayLocale.button.cancle
		choose:'Select',//EwayLocale.button.choose
		pause:'Pause',//EwayLocale.button.pause

		exported:'Export',//EwayLocale.button.exported
		select:'Select', //EwayLocale.button.select
		info :'Detail', //EwayLocale.button.info
		exportXLS:'Export as XLS', //EwayLocale.button.exportXLS
		exportPDF:'Export as PDF', //EwayLocale.button.exportPDF

		massExport:'Batch Import', //EwayLocale.button.massExport
		download:'Deploy', //EwayLocale.button.download
		downloadToolTip:'Deploy Settings', //EwayLocale.button.downloadToolTip
		save:'Save',//EwayLocale.button.save
		adminBtn:'Manager',//EwayLocale.button.adminBtn
		personM:'Maintenancer',//EwayLocale.button.personM
		personTM:'Banker',//EwayLocale.button.personTM
		orgAdmin:'Bank Administrator'//EwayLocale.button.orgAdmin
	},
	
	refs:{
		selectAll:'All',//EwayLocale.refs.selectAll
		orgName:'Bank',//EwayLocale.refs.orgName
		terminalId:'Terminal ID',//EwayLocale.refs.terminalId
		ip:'IP',//EwayLocale.refs.ip
		devType:"Device Type"//EwayLocale.refs.devType
	},
	
	tip:{
		search :{
			warn:'Error input for query.',//EwayLocale.tip.search.warn
			record:'Please choose one record'//EwayLocale.tip.search.record
		},
		update:{
			one:'Only choose one record',//EwayLocale.tip.update.one
			two:'This record can not be modified'//EwayLocale.tip.update.two
		},
		remove :{
			none:'Please select the record which you want to delete',//EwayLocale.tip.remove.none
			one:'Only one record can be choose to delete', //EwayLocale.tip.remove.one
			confirm:{
				title:'Please confirm',//EwayLocale.tip.remove.confirm.title
				info:'Delete this record'//EwayLocale.tip.remove.confirm.info
			},
			error:'Delete failed:'//EwayLocale.tip.remove.error
		},
		own:{
			have:'yes',//EwayLocale.tip.own.have
			nothing:'no'//EwayLocale.tip.own.nothing
		},
		right:{
			yes:'yes', //EwayLocale.tip.right.yes
			no:'no' //EwayLocale.tip.right.no
		},
		add:{
			error:'Add failed'//EwayLocale.tip.add.error
		},
		success:'Successfully.',//EwayLocale.tip.success
		fail:'Failed:',//EwayLocale.tip.fail
		phone:'Please enter the right telephone number', //EwayLocale.tip.phone
		remind:'Tip',//EwayLocale.tip.remind
		formatPageBfMsg: 'Per page',//EwayLocale.tip.formatPageBfMsg
		formatPageAfMsg: 'items',//EwayLocale.tip.formatPageAfMsg

		unCertain:'Unknown',//EwayLocale.tip.unCertain
		searchOfNoLegal:'There are some illegal parameters in the query condition,can not commit ',//EwayLocale.tip.searchOfNoLegal
		choseExportDevInfo:'Please select the machine which you want to export for detail ',//EwayLocale.tip.choseExportDevInfo
		nowLink:'Connecting...',//EwayLocale.tip.nowLink
		linkFailure:'Connecting failed.',//EwayLocale.tip.linkFailure
		inputError:'Illegal input',//EwayLocale.tip.inputError
		numberExist:'Exist ID, please enter again',//EwayLocale.tip.numberExist
		isConfirmRemove:'The relationship of the group will be deleted with this delete operation , make sure you want to delete?',//EwayLocale.tip.isConfirmRemove
		noGroupInfo:'No data of the group ,can not execute query',//EwayLocale.tip.noGroupInfo
		selectAdd:'Please select the record which you want to add',//EwayLocale.tip.selectAdd
		continueAdd:'Add successful,continue add machine to this group?',//EwayLocale.tip.continueAdd
		addFail:'Add failed.',//EwayLocale.tip.addFail
		isRemoveDev:'Remove this machine from this group?',//EwayLocale.tip.isRemoveDev
		removeFail:'Remove failed',//EwayLocale.tip.removeFail
		selectRemoveGroup:'Please select the group which the machine you want to remove belongs.',//EwayLocale.tip.selectRemoveGroup
		selectRemoveDev:'Please select the machine which you want to remove.',//EwayLocale.tip.selectRemoveDev
		selectMoveDev:'Please select the machine which you want to move.',//EwayLocale.tip.selectMoveDev
		moveSuc:'Move the machine successfully',//EwayLocale.tip.moveSuc
		dateReSelect:'The begin date can not be later than end date,please select again',//EwayLocale.tip.dateReSelect
		selectPlan:'Please select the plan which you want to execute',//EwayLocale.tip.selectPlan
		removeFail:'Relieve failed',//EwayLocale.tip.removeFail
		selectRemoveDev:'Please select the machine which you want to relieve.',//EwayLocale.tip.selectRemoveDev
		relatedFail:'Binding machine failed.',//EwayLocale.tip.relatedFail
		selectRelatedDev:'Please choose machines which you want to bind.',//EwayLocale.tip.selectRelatedDev
		exportFiles: 'Please choose a file to import,only support .xls or .xlsx files ',//EwayLocale.tip.exportFiles
		operateSuc:'Successful operation',//EwayLocale.tip.operateSuc
		operateWrong:'Successful operation',//EwayLocale.tip.operateWrong
		choosePlan:'Please select the plan you want to view',//EwayLocale.tip.choosePlan
		planDetail:'Plan details',//EwayLocale.tip.planDetail
		planDate:'Plan details (date)',//EwayLocale.tip.planDate
		planWeek:'Plan details (Week)',//EwayLocale.tip.planWeek
		planNoConf:'The plan no detailed settings！',//EwayLocale.tip.planNoConf
		devRelatedPlan:'Device has been bound with the service plan!',//EwayLocale.tip.devRelatedPlan
		
		tips:'Tips',//EwayLocale.tip.tips
		input:'Please enter the right item',//EwayLocale.tip.input
		roleName:'Just for character ‘a-z’ or ‘A-Z’ or numbers ‘0-9’ ,max length is 40',//EwayLocale.tip.roleName
		notNull:'Can not be null',//EwayLocale.tip.notNull
		cardNo:'Just for 13-19 numbers ‘0-9’',//EwayLocale.tip.cardNo
		blankBegin:'Can not start with blank',//EwayLocale.tip.blankBegin
		passwd:{
			confirmPasswd:'Reset the password ,confirm?',//EwayLocale.tip.passwd.confirmPasswd
			resetPasswding:'Reseting the password.....',//EwayLocale.tip.passwd.resetPasswding
			resetPasswdFail:'Reset the password failed'//EwayLocale.tip.passwd.resetPasswdFail
		},
		operateDate:{
			operateDateBegin:'Operate date start with ',//EwayLocale.tip.operateDate.operateDateBegin
			operateDateEnd:'Operate date end  with'//EwayLocale.tip.operateDate.operateDateEnd
		},
		bankOrg:{
			manager:{
				set:{
					chooseOrg:'Please choose the organization which you want to set',//EwayLocale.tip.bankOrg.manager.set.chooseOrg
					managerSuccess:'Set manager successfully.',//EwayLocale.tip.bankOrg.manager.set.managerSuccess
					managerFail:'Set manager failed.',//EwayLocale.tip.bankOrg.manager.set.managerFail
					chooseOneManager:'Please choose at least one record'//EwayLocale.tip.bankOrg.manager.set.chooseOneManager
				},
				remove:{
					confirm:'Delete the manager of this organization?',//EwayLocale.tip.bankOrg.manager.remove.confirm
					reChoose:'You have not choose the organization or there is no manager of this organization, please choose again',//EwayLocale.tip.bankOrg.manager.remove.reChoose
					delSuccess:'Delete the manager successfully',//EwayLocale.tip.bankOrg.manager.remove.delSuccess
					delFail:'Delete the manager failed'//EwayLocale.tip.bankOrg.manager.remove.delFail
				}
			},
			move:{
				chooseOrg:'Please choose organization which you want to move.',//EwayLocale.tip.bankOrg.move.chooseOrg
				moveSuccess:'Move the organization successfully.'//EwayLocale.tip.bankOrg.move.moveSuccess
			}
		},
		bankPer:{
			allPersonInfo:'ALL info of bank employeers',//EwayLocale.tip.bankPer.allPersonInfo
			link:{
				linkPerson:'Please choose a maintenaner which you want to bind with.',//EwayLocale.tip.bankPer.link.linkPerson
				linkBankPerson:'Please choose a banker which you want to bind with.',//EwayLocale.tip.bankPer.link.linkBankPerson
				unLinkPersonFail:'Link failed.',//EwayLocale.tip.bankPer.link.unLinkPersonFail
				unlinkDev:'Please choose a machine which you want to lift.',//EwayLocale.tip.bankPer.link.unlinkDev
				linkDev:'Please choose a machine which you want to link.',//EwayLocale.tip.bankPer.link.linkDev
				unLinkDevFail:'Lift failed.'//EwayLocale.tip.bankPer.link.unLinkDevFail
			},
			personEligible:'People which accordwith the conditions',//EwayLocale.tip.bankPer.personEligible
			downGradePer:'and its lower organizastion personnel',//EwayLocale.tip.bankPer.downGradePer
		},
		serviceOrg:{
			chooseOrg:'Please choose the service which you want to set.',//EwayLocale.tip.serviceOrg.chooseOrg
			remove:{
				reChoose:'You hava not choose a service or no manager found in the service,please choose again.'//EwayLocale.tip.serviceOrg.remove.reChoose
			}
		},
		user:{
			add:{
				createAcc:'Please choose the man which you want to create account',//EwayLocale.tip.user.add.createAcc
				createSuccess:'Create successfully,newcreate account `',//EwayLocale.tip.user.add.createSuccess
				initPasswd:'` The init password is :888888'//EwayLocale.tip.user.add.initPasswd
			},
			remove:{
				failRoot:'Delete failed,system manager can not be deleted.',//EwayLocale.tip.user.remove.failRoot
				confirm:'Delete this record? User log will delete with this operation.',//EwayLocale.tip.user.remove.confirm
				fail:'Delete failed: can not delete the role,please try again'//EwayLocale.tip.user.remove.fail
			},
			update:{
				fail:'Update failed:the record dose not exist,please refresh.'//EwayLocale.tip.user.update.fail
			}
		},
		business:{
			transaction:{
				historyTransaction:{
					input:'Terminal ID is necessary for the query.'//EwayLocale.tip.business.transaction.historyTransaction.input


				}
			},
			blackList:{
				importing:'Importing file',//EwayLocale.tip.business.blackList.importing
				importSuccess:'Blackcard file importing successfully'//EwayLocale.tip.business.blackList.importSuccess
			},
			card:{
				returnFail:'Turnover failed:system error.',//EwayLocale.tip.business.card.returnFail
				choose:'Please choose the card which you want to turnover.',//EwayLocale.tip.business.card.choose
				returnSucess:'Turnover successfully.',//EwayLocale.tip.business.card.returnSucess
				chooseBack:'Please choose the card which you want to draw.',//EwayLocale.tip.business.card.chooseBack
				getSuccess:'Draw successfully.',//EwayLocale.tip.business.card.getSuccess
				destroyConfirm:'Destroy this card?',//EwayLocale.tip.business.card.destroyConfirm
				destroySuccess:'Destroy successfully',//EwayLocale.tip.business.card.destroySuccess
				chooseDestroy:'Please choose the card which you want to destroy.',//EwayLocale.tip.business.card.chooseDestroy
				idCardRegex:'Please input the correct ID number,15 or 18 numbers',//EwayLocale.tip.business.card.idCardRegex
				accountRegex:'Please input the correct ID number of the householdRegister,15 or 18 numbers',//EwayLocale.tip.business.card.accountRegex
				driveCardRegex:'Please input the correct ID number of the driver-license,15 or 18 numbers',//EwayLocale.tip.business.card.driveCardRegex
				passportRegex:'Please input the correct ID number of the passport,15 or 18 numbers',//EwayLocale.tip.business.card.passportRegex
				soldierRegex:'Please input the correct number of officer-license ,1-5 Chinesecharacter or ‘1-10’ numbers',//EwayLocale.tip.business.card.soldierRegex
				soldierCard:'Please input the correct number of soldier-license 7-8 numbers ',//EwayLocale.tip.business.card.soldierCard
				busnessPaper:'Please input the correct number of businesslicence,12-15 numbers',//EwayLocale.tip.business.card.busnessPaper
				busnessCode:'Please input the correct number of juridicalperson,15 numbers',//EwayLocale.tip.business.card.busnessCode
				taxPaper:'Please input the correct number of taxregistration,15 numbers'//EwayLocale.tip.business.card.taxPaper

			},
			device:{
				getCashInfoFail:'Get cash boxes info failed',//EwayLocale.tip.business.device.getCashInfoFail
				operating:'Executing',//EwayLocale.tip.business.device.operating
				reviewFail:'Failed to review.',//EwayLocale.tip.business.device.reviewFail
				logLoadConfirm:'Get the atmc logs successful,download it?',//EwayLocale.tip.business.device.logLoadConfirm
				logPullFail:'Get atmc logs failed',//EwayLocale.tip.business.device.logPullFail
				logFail:'Log process failed.',//EwayLocale.tip.business.device.logFail
				linkServerFail:'Connection refused.',//EwayLocale.tip.business.device.linkServerFail
				logicOpen:'Confirm execute start-service command.',//EwayLocale.tip.business.device.logicOpen
				openSuccess:'Execute start-service command successfully.',//EwayLocale.tip.business.device.openSuccess
				openFail:'Execute start-service command failed.',//EwayLocale.tip.business.device.openFail
				closeConfirm:'Confirm execute stop-service command?',//EwayLocale.tip.business.device.closeConfirm
				closeServiceSuccess:'Execute stop-service command successfully.',//EwayLocale.tip.business.device.closeServiceSuccess
				closeServiceFail:'Execute stop-service command failed.',//EwayLocale.tip.business.device.closeServiceFail
				closeNormal:'Shut down normally.',//EwayLocale.tip.business.device.closeNormal
				closing:'Shutting down normally.',//EwayLocale.tip.business.device.closing
				closeSucess:'Shut down normally successfully.',//EwayLocale.tip.business.device.closeSucess
				closeFail:'Shut down normally failed.',//EwayLocale.tip.business.device.closeFail
				closeSentFail:'Send the normal shut-down command failed ',//EwayLocale.tip.business.device.closeSentFail
				forceClose:'Force shut down',//EwayLocale.tip.business.device.forceClose
				forceCloseComfirm:'Confirm execute force shut-down command,it may brings some risk?',//EwayLocale.tip.business.device.forceCloseComfirm
				forceClosing:'Shutting down force.',//EwayLocale.tip.business.device.forceClosing
				forceCloseSucess:'Force-shut-down successfully.',//EwayLocale.tip.business.device.forceCloseSucess
				forceCloseFail:'Force-shut-down failed.',//EwayLocale.tip.business.device.forceCloseFail
				ForceCloseSentFail:'Send the force-shut-down command failed ',//EwayLocale.tip.business.device.ForceCloseSentFail
				reboot:'Reboot normally',//EwayLocale.tip.business.device.reboot
				rebootConfirm:'Confirm execute normal-reboot command,it may brings some risk?',//EwayLocale.tip.business.device.rebootConfirm
				rebooting:'Normally rebooting',//EwayLocale.tip.business.device.rebooting
				rebootSucess:'Normal-reboot successfully.',//EwayLocale.tip.business.device.rebootSucess
				rebootFail:'Normal-reboot failed.',//EwayLocale.tip.business.device.rebootFail
				rebootSendFail:'Send the  normal-reboot command failed ',//EwayLocale.tip.business.device.rebootSendFail
				forceReboot:'Force reboot',//EwayLocale.tip.business.device.forceReboot
				forceRebootConfirm:'Confirm execute force-reboot command,it may brings some risk?',//EwayLocale.tip.business.device.forceRebootConfirm
				forceRebooting:'Executing force-reboot command',//EwayLocale.tip.business.device.forceRebooting
				forceRebootSuccess:'Force-reboot successfully.',//EwayLocale.tip.business.device.forceRebootSuccess
				forceRebootFail:'Force-reboot failed.',//EwayLocale.tip.business.device.forceRebootFail
				forceRebootSendFail:'Send the normal force-reboot command failed ',//EwayLocale.tip.business.device.forceRebootSendFail
				resetConfirm:'Confirm execute force-reset command?',//EwayLocale.tip.business.device.resetConfirm
				resetSuccess:'Force-reset successfully',//EwayLocale.tip.business.device.resetSuccess
				resetFail:'Force-reset failed',//EwayLocale.tip.business.device.resetFail
				resetSendFail:'Send the force-reset command failed.',//EwayLocale.tip.business.device.resetSendFail
				term:'Terminal ID',//EwayLocale.tip.business.device.term
				detail:'Detail',//EwayLocale.tip.business.device.detail
				refresh:'Refreshing......',//EwayLocale.tip.business.device.refresh
				remoteCommandMsg:'Remote Command send Successfully'//EwayLocale.tip.business.device.remoteCommandMsg
			}
		}

	},

	

	combox:{
		select:'Please select',//EwayLocale.combox.select
		explorer: 'Browse...'//EwayLocale.combox.explorer
	},



	vtype:{
		ip:'Please enter the correct IP address',//EwayLocale.vtype.ip
		zip:'Please enter the correct Z.C. ,6 numbers', //EwayLocale.vtype.zip
		versionNo:'Incorrect Version No,format explain：1.Version No make up with 4 part:A.B.C.D ;2.Only part A is necessary ;3. A、B、C、D must be an integer which greater than 0,max length of each part is 8;4.Part ABCD must split with `.`',//EwayLocale.vtype.versionNo
		terminalId:'Just for 1-20 characters ‘a-z’ or ‘A-Z’ or numbers ‘0-9’ or‘-’ or‘_’ or ‘.’,and must be start with character or number.',//EwayLocale.vtype.terminalId
		mobile:'Input error,mobile number can only be 8-11 numbers',//EwayLocale.vtype.mobile
		cardNo:'Input error,bankcard number can only be 16-19 numbers',//EwayLocale.vtype.cardNo
		telephone:'Input error,phone number can only be 8-12 numbers',//EwayLocale.vtype.telephone
		daterange:'Range of time is not correct.',//EwayLocale.vtype.daterange
		numberrange:'Range of money is not correct.',//EwayLocale.vtype.numberrange

		bankOrgCode:'Just for 1-20 characters ‘a-z’ or ‘A-Z’ or number ‘0-9’ or‘-’ or‘_’ or ‘.’,and must be start with character or number',//EwayLocale.vtype.bankOrgCode
		zip:'Just for 6 ‘0-9’ numbers',//EwayLocale.vtype.zip
		maxLength20:'The maximum length for this field is 20.',//EwayLocale.vtype.maxLength20
		numberRule: 'Just for character ‘a-z’ or ‘A-Z’、number‘0-9’、minussign‘-’、underline‘_’ or point‘.’,only start with character or number',//EwayLocale.vtype.numberRule


		choseDev:'Please choose the machine which you want to stop.',//EwayLocale.vtype.choseDev
		dataLoad:'Data loading......',//EwayLocale.vtype.dataLoad
		devLinkNormal:'Please check the connection between server and machine.',//EwayLocale.vtype.devLinkNormal
		hardwayInitialize:'Hardware module initializing......',//EwayLocale.vtype.hardwayInitialize
		inputCorrect:'Please input correct data.',//EwayLocale.vtype.inputCorrect
		exportRepError:'Export report error,please try again',//EwayLocale.vtype.exportRepError

		emailRules: 'email must accord with the rule of *@*.* ',//EwayLocale.vtype.emailRules
		notifyTimesRules: 'Times of notice must be numbers,minimum 0,maximum 100.',//EwayLocale.vtype.notifyTimesRules
		sendTimesRules: 'Times of sending must be numbers,minimum 0,maximum 100.',//EwayLocale.vtype.sendTimesRules

		launchTranscribe:'Starting record......', //EwayLocale.vtype.launchTranscribe
		stopTranscribe:'Stoping record......',//EwayLocale.vtype.stopTranscribe
		inexistenceScreen:'Screen not found',//EwayLocale.vtype.inexistenceScreen
		devEmploy:'This machine is being used by ', //EwayLocale.vtype.devEmploy
		userEmploy:'!',//EwayLocale.vtype.userEmploy
		loadTranscribe:'Downloading the recorded video ......',//EwayLocale.vtype.loadTranscribe
		remoteFailure:'Remote browse failed',//EwayLocale.vtype.remoteFailure
		choseTask:'Please choose a Task',//EwayLocale.vtype.choseTask
	},

	commen:{
		jobNum:'Job Number',//EwayLocale.commen.jobNum
		name:'Name',//EwayLocale.commen.name
		personJobName:'Quarters',//EwayLocale.commen.personJobName
		state:'Status',//EwayLocale.commen.state
		birthday:'Birthday',//EwayLocale.commen.birthday
		comboxStatus:{
			onJob:'Incumbency',//EwayLocale.commen.comboxStatus.onJob
			onAdjust:'Leaving',//EwayLocale.commen.comboxStatus.onAdjust
			onVacation:'Vacation',//EwayLocale.commen.comboxStatus.onVacation
			other:'other',//EwayLocale.commen.comboxStatus.other
			dredge:'Dredge',//EwayLocale.commen.comboxStatus.dredge
			close:'Close',//EwayLocale.commen.comboxStatus.close
			pastDue:'Expired',//EwayLocale.commen.comboxStatus.pastDue
			pastDueSoon:'About to expire'//EwayLocale.commen.comboxStatus.pastDueSoon
		},
		type:'Type',//EwayLocale.commen.type
		comboxType:{
			machineManager:'Device Manager',//EwayLocale.commen.comboxType.machineManager
			machineRepairer:'Maintenance Engineers'//EwayLocale.commen.comboxType.machineRepairer
		},
		mobile:'Mobile',//EwayLocale.commen.mobile
		email:'E-Mail',//EwayLocale.commen.email
		phone:'Phone',//EwayLocale.commen.phone
		gender:'Gender',//EwayLocale.commen.gender
		all:'All',//EwayLocale.commen.all
		comboxGender:{
			male:'Male',//EwayLocale.commen.comboxGender.male
			female:'Female',//EwayLocale.commen.comboxGender.female
			unknow:'Unknow'//EwayLocale.commen.comboxGender.unknow
		},
		remark:'Description',//EwayLocale.commen.remark
		terminalId:'Terminal ID',//EwayLocale.commen.terminalId
		ip:'IP',//EwayLocale.commen.ip
		orgNameBelongs:'Bank',//EwayLocale.commen.orgNameBelongs
		devTypeName:'Device Type',//EwayLocale.commen.devTypeName
		devVendorName:'Device Brand',//EwayLocale.commen.devVendorName
		devCatalogName:'Device Catalog',//EwayLocale.commen.devCatalogName
		devStatus:'Status',//EwayLocale.commen.devStatus
		comboxDevStatus:{
			upOpen:'upOpen',//EwayLocale.commen.comboxDevStatus.upOpen
			open:'Open',//EwayLocale.commen.comboxDevStatus.open
			stop:'Stop',//EwayLocale.commen.comboxDevStatus.stop
			scrapped:'Scrapped'//EwayLocale.commen.comboxDevStatus.scrapped
		},
		setManager:'Settings',//EwayLocale.commen.setManager
		devServiceName:'Maintenance Provider',//EwayLocale.commen.devServiceName
		cashboxLimit:'Alarm of money in cashbox',//EwayLocale.commen.cashboxLimit
		installDate:'Installation Time',//EwayLocale.commen.installDate
		address:'Address',//EwayLocale.commen.address
		areaCode:'Area code',//EwayLocale.commen.areaCode
		areaName:'Area name',//EwayLocale.commen.areaName
		toolbar:'Total：{2},display{0}-{1}',//EwayLocale.commen.toolbar
		bindMachine :'Devices bound',//EwayLocale.commen.bindMachine
		lift:'Relieve',//EwayLocale.commen.lift
		canBindMachine:'Bindable devices',//EwayLocale.commen.canBindMachine
		bind:'Bind',//EwayLocale.commen.bind
		filter:'Filter',//EwayLocale.commen.filter
		stateDict:{
			newCreate:'New',//EwayLocale.commen.stateDict.newCreate
			normal:'Normal',//EwayLocale.commen.stateDict.normal
			locked:'Locked',//EwayLocale.commen.stateDict.locked
			disable:'Disable',//EwayLocale.commen.stateDict.disable
			frozen:'Frozen',//EwayLocale.commen.stateDict.frozen
			deleted:'Deleted'//EwayLocale.commen.stateDict.deleted
		},
		yes:'yes',//EwayLocale.commen.yes
		no:'no',//EwayLocale.commen.no
		selectAll:'Select All',//EwayLocale.commen.selectAll
		selectNon:'Select None',//EwayLocale.commen.selectNon
		content:'Content',//EwayLocale.commen.content
		upgrade:'Upgrade',//EwayLocale.commen.upgrade
		port:'port',//EwayLocale.commen.port
		seviceMode:'Service mode',//EwayLocale.commen.seviceMode
		insideOutside:'In bank flag',//EwayLocale.commen.insideOutside
		appVersion:'Atmc Version',//EwayLocale.commen.appVersion
		devInfo:'Device Basic Info',//EwayLocale.commen.devInfo
		
		personnel:'Contacts',//EwayLocale.commen.personnel
		warn:'Warning',//EwayLocale.commen.warn
		fatal:'Fatal',//EwayLocale.commen.fatal
		unStable:'UnStable',//EwayLocale.commen.unStable
		unknow:'Unknown',//EwayLocale.commen.unknow
		noDevice:'NoDevice',//EwayLocale.commen.noDevice
		description:'Description',//EwayLocale.commen.description
		info:'Detail',//EwayLocale.commen.info
		startDataTime:'Start time',//EwayLocale.commen.startDataTime
		endDataTime:'End time',//EwayLocale.commen.endDataTime
		year:'Year',//EwayLocale.commen.year
		month:'Month',//EwayLocale.commen.month
		day:'Day',//EwayLocale.commen.day
		yearTime:'Year',//EwayLocale.commen.yearTime
		monthTime:'Month',//EwayLocale.commen.monthTime
		dayTime:'Day',//EwayLocale.commen.dayTime
		orgFramework:'Organization',//EwayLocale.commen.orgFramework
		matchOrg:'Organization matched',//EwayLocale.commen.matchOrg
		orgID:'Organization ID',//EwayLocale.commen.orgID
		endValidty:'Valid date until',//EwayLocale.commen.endValidty
		publishDate:'Issue date',//EwayLocale.commen.publishDate
		announceTheme:'Announce theme',//EwayLocale.commen.announceTheme
		filterDelete: 'deleted'//EwayLocale.commen.filterDelete


	},
	

	index:{
		indexPage:'Home',//EwayLocale.index.indexPage
		dailyFaultPic:'Faults Trend Chart',//EwayLocale.index.dailyFaultPic
		faultAmount:' Amount of Faults: ',//EwayLocale.index.faultAmount
		devStatusDisPic:'Device Status Pie',//EwayLocale.index.devStatusDisPic
		normalDev:'Healthy',//EwayLocale.index.normalDev
		unknownDev:'Unknow',//EwayLocale.index.unknownDev
		exceptionDev:'Fatal',//EwayLocale.index.exceptionDev
		amount:'',//EwayLocale.index.amount
		versionDistributePie:'Patch Distribution',//EwayLocale.index.versionDistributePie
		retainCardTrendTitle:'Retained Card Trend Chart'//EwayLocale.index.retainCardTrendTitle
	},

	
	personal:{
		baseInfo:'Basic info',//EwayLocale.personal.baseInfo
		accountNum:'User Code',//EwayLocale.personal.accountNum
		personalInfo:'Profile',//EwayLocale.personal.personalInfo
		changePwd:'Udpate Password',//EwayLocale.personal.changePwd
		nowLogin:'Current User',//EwayLocale.personal.nowLogin
		inputOldPwd:'Input old password',//EwayLocale.personal.inputOldPwd
		inputNewPwd:'Input new password',//EwayLocale.personal.inputNewPwd
		inputVali:"Just for 1-20 characters 'a-z'or 'A-Z'or number '0-9' or '-' or '_' or special characters",//EwayLocale.personal.inputVali
		inputValiPwd:"Just for 8-20 characters 'a-z'or 'A-Z'or number '0-9' or '-' or '_' or special characters",//EwayLocale.personal.inputValiPwd
		inputAgain:'Input new password again',//EwayLocale.personal.inputAgain
		pwdNotSame:'New passwords is not match.',//EwayLocale.personal.pwdNotSame
		rememberPwd:'Click confirm to update password ,please remember it!',//EwayLocale.personal.rememberPwd
		pwdSameNoChange:'New password is the same with the old ,can not update.',//EwayLocale.personal.pwdSameNoChange
		reOperate:'Can not update password,please try again.'//EwayLocale.personal.reOperate
	},

	
	system:{
		sysRegist:'SystemRegist',//EwayLocale.system.sysRegist
		registCode:'Regist code',//EwayLocale.system.registCode
		startDate:'Start date',//EwayLocale.system.startDate
		endDate:'End date',//EwayLocale.system.endDate
		registType:'Regist type',//EwayLocale.system.registType
		serialNum:'SerialNo',//EwayLocale.system.serialNum
		getSerialNum:'Getting SerialNo......',//EwayLocale.system.getSerialNum
		checkCode:'Check code',//EwayLocale.system.checkCode
		tryOut:'Tryout',//EwayLocale.system.tryOut
		noLimit:'Limitless',//EwayLocale.system.noLimit
		getSerialNumFail:'Get SerialNo failed',//EwayLocale.system.getSerialNumFail
		registSuc:'Regist successfully',//EwayLocale.system.registSuc
		registFail:'Regist failed',//EwayLocale.system.registFail
		appearInnerFalse:'System error',//EwayLocale.system.appearInnerFalse
		regist:'Regist',//EwayLocale.system.regist
		aboutSystem:'About',//EwayLocale.system.aboutSystem
		softwareName:'Name',//EwayLocale.system.softwareName
		ATMV:'Self-service machine monitor system(ATMV)',//EwayLocale.system.ATMV
		softwareVersion:'Software-Version',//EwayLocale.system.softwareVersion
		innerVersion:'InnerVersion',//EwayLocale.system.innerVersion
		copyRight:'CopyRight：&copy;YIHUA ',//EwayLocale.system.copyRight
		introduction:'introduction:',//EwayLocale.system.introduction
		introductionA:'This system is used for monitor ATM-basic-info,',//EwayLocale.system.introductionA
		introductionB:'自动化版本分发管理、 ATM设备监控等功能。通过这些功能，各大银行可以集中管理ATM设备信息  监视 远程的ATM，对远程ATM机器上的软件升',//EwayLocale.system.introductionB
		introductionC:'级和软件维护，方便了各大银 行对自助设备进行高效的管理和维护。',//EwayLocale.system.introductionC

		guideUsers:'This handbook help user to use this system',//EwayLocale.system.guideUsers
		systemHelp:'Help',//EwayLocale.system.systemHelp
		helpName:'Name',//EwayLocale.system.helpName
		helpExpain:'Instructions',//EwayLocale.system.helpExpain
		helpDownload:'Download',//EwayLocale.system.helpDownload
		clickDownload:'Click to download'//EwayLocale.system.clickDownload
	}
});
