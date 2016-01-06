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
	aaaaaa:'Log Out',//EwayLocale.aaaaaa
	msg:{
		perviewFailForText:'Preview failed:the charater and rolling advertisement is supported to preview',//EwayLocale.msg.perviewFailForText
		perviewFailNoResource:'Preview failed:no resource found in this advertisement.',//EwayLocale.msg.perviewFailNoResource
		choseResToPerview:'Please select the advertisement which you want to preview.',//EwayLocale.msg.choseResToPerview
		noAdvertResAtTheResolution:'No allocation of advertising resources under Resolution.',//EwayLocale.msg.noAdvertResAtTheResolution
		chooseAdvert:'Please select an advertisement.',//EwayLocale.msg.chooseAdvert
		chooseOneDevice:'Please select a machine.',//EwayLocale.msg.chooseOneDevice
		downLoadedAdvertCantDelete:'Delete failed:can not delete the advertisement which status is "issued" or "wait for issue".',//EwayLocale.msg.downLoadedAdvertCantDelete
		chooseAdvertToDelete:'Please select the advertisement which you want to delete.',//EwayLocale.msg.chooseAdvertToDelete
		chooseAdvertToDownload:'Please select the advertisement which you want to issue.',//EwayLocale.msg.chooseAdvertToDownload
		generalVersionFailForDownloaded:'Generate  the version-file failed:the status "issued" advertisement can not generate version-information any more',//EwayLocale.msg.generalVersionFailForDownloaded
		generalVersionSuccess:"Generate the version file successful",//EwayLocale.msg.generalVersionSuccess
		createSuccess:"Create Successfully.",//EwayLocale.msg.createSuccess
		mustHaveOneResource:'At least has one resource of advertisement!',//EwayLocale.msg.mustHaveOneResource
		saveFail:'Save failed',//EwayLocale.msg.saveFail
		saveFailPleaseRefresh:'Save failed, please refresh retry',//EwayLocale.msg.saveFailPleaseRefresh
		saveFileSizeMaxFail:'Save failed:the max size of each single file is 30M',//EwayLocale.msg.saveFileSizeMaxFail
		saveFileCommunicationFail:'Save failed:connection refused',//EwayLocale.msg.saveFileCommunicationFail
		chooseDevice:"Please select a machine",//EwayLocale.msg.chooseDevice
		downloadFailForNoVersion:"Version file issue failed :no generated version-file found or version-file lost,please generate version file first.",//EwayLocale.msg.downloadFailForNoVersion
		saveSuccess:'Save successful',//EwayLocale.msg.saveSuccess

		removeSuccess:'Relieve success',//EwayLocale.msg.removeSuccess
		removeFail:'Relieve failure',//EwayLocale.msg.removeFail
		someStripRemoveFailePleaseRefresh:'Article lift failed. Please refresh retry！',//EwayLocale.msg.someStripRemoveFailePleaseRefresh
		versionDownloaded:'Can not delete the version which status is "issued" or "wait for issue"',//EwayLocale.msg.versionDownloaded
		selectVersionToDelete:'Please choose the version which you want to delete',//EwayLocale.msg.selectVersionToDelete
		communicationFail:'Add failed : connction refused.',//EwayLocale.msg.communicationFail
		sameVersionNoFail:'Add failed: same version no exists.',//EwayLocale.msg.sameVersionNoFail
		fileSizeMaxFail:'Add failed:the max size of the file is 200M',//EwayLocale.msg.fileSizeMaxFail
		fileUnzipFail:'Add failed: zip-file can not be unziped',//EwayLocale.msg.fileUnzipFail
		addFileFail:'Add failed :',//EwayLocale.msg.addFileFail
		mustSelectDevice:'please choose at least one machine.',//EwayLocale.msg.mustSelectDevice
		selectVersionRecord:'Please choose the version which you want to issue',//EwayLocale.msg.selectVersionRecord
		missVersionFile:"Version files lost,can not execute",//EwayLocale.msg.missVersionFile
		mustSelectPerson:"Please select personnel must"//EwayLocale.msg.mustSelectPerson
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
		
		deepQuery:'Deep Search', //EwayLocale.button.deepQuery
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
		move:'Move', //EwayLocale.button.move
		exportXLS:'Export as XLS', //EwayLocale.button.exportXLS
		exportPDF:'Export as PDF', //EwayLocale.button.exportPDF

		massExport:'Batch Import', //EwayLocale.button.massExport
		download:'Deploy', //EwayLocale.button.download
		downloadToolTip:'Deploy Settings', //EwayLocale.button.downloadToolTip
		save:'Save',//EwayLocale.button.save
		openPlan:'Open Plan',//EwayLocale.button.openPlan
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
			warn:'Error input for query.',//EwayLocale.search.warn
			record:'Please choose one record'//EwayLocale.search.record
		},
		update:{
			one:'Only choose one record',//EwayLocale.update.one
			two:'This record can not be modified'//EwayLocale.update.two
		},
		remove :{
			none:'Please select the record which you want to delete',//EwayLocale.remove.none
			one:'Only one record can be choose to delete', //EwayLocale.remove.one
			confirm:{
				title:'Please confirm',//EwayLocale.confirm.title
				info:'Delete this record'//EwayLocale.confirm.info
			},
			error:'Delete failed:'//EwayLocale.confirm.error
		},
		own:{
			have:'yes',//EwayLocale.own.have
			nothing:'no'//EwayLocale.own.nothing
		},
		right:{
			yes:'yes', //EwayLocale.right.yes
			no:'no' //EwayLocale.right.no
		},
		add:{
			error:'Add failed'//EwayLocale.add.error
		},
		success:'Successfully.',//EwayLocale.add.success
		fail:'Failed:',//EwayLocale.add.fail
		phone:'Please enter the right telephone number', //EwayLocale.add.phone
		remind:'Tip',//EwayLocale.add.remind
		formatPageBfMsg: 'Per page',//EwayLocale.add.formatPageBfMsg
		formatPageAfMsg: 'items',//EwayLocale.add.formatPageAfMsg

		unCertain:'Unknown',//EwayLocale.add.unCertain
		searchOfNoLegal:'There are some illegal parameters in the query condition,can not commit ',//EwayLocale.add.searchOfNoLegal
		choseExportDevInfo:'Please select the machine which you want to export for detail ',//EwayLocale.add.choseExportDevInfo
		nowLink:'Connecting...',//EwayLocale.add.nowLink
		linkFailure:'Connecting failed.',//EwayLocale.add.linkFailure
		inputError:'Illegal input',//EwayLocale.add.inputError
		numberExist:'Exist ID, please enter again',//EwayLocale.add.numberExist
		isConfirmRemove:'The relationship of the group will be deleted with this delete operation , make sure you want to delete?',//EwayLocale.add.isConfirmRemove
		noGroupInfo:'No data of the group ,can not execute query',//EwayLocale.add.noGroupInfo
		selectAdd:'Please select the record which you want to add',//EwayLocale.add.selectAdd
		continueAdd:'Add successful,continue add machine to this group?',//EwayLocale.add.continueAdd
		addFail:'Add failed.',//EwayLocale.add.addFail
		isRemoveDev:'Remove this machine from this group?',//EwayLocale.add.isRemoveDev
		removeFail:'Remove failed',//EwayLocale.add.removeFail
		selectRemoveGroup:'Please select the group which the machine you want to remove belongs.',//EwayLocale.add.selectRemoveGroup
		selectRemoveDev:'Please select the machine which you want to remove.',//EwayLocale.add.selectRemoveDev
		selectMoveDev:'Please select the machine which you want to move.',//EwayLocale.add.selectMoveDev
		moveSuc:'Move the machine successful',//EwayLocale.add.moveSuc
		dateReSelect:'The begin date can not be later than end date,please select again',//EwayLocale.add.dateReSelect
		selectPlan:'Please select the plan which you want to execute',//EwayLocale.add.selectPlan
		removeFail:'Relieve failed',//EwayLocale.add.removeFail
		selectRemoveDev:'Please select the machine which you want to relieve.',//EwayLocale.add.selectRemoveDev
		relatedFail:'Binding machine failed.',//EwayLocale.add.relatedFail
		selectRelatedDev:'Please choose machines which you want to bind.',//EwayLocale.add.selectRelatedDev
		planNoUpdate:'This plan has been executed ,can not be modified ',//EwayLocale.add.planNoUpdate
		planNoRemove:'This plan has been executed ,can not be deleted ',//EwayLocale.add.planNoRemove
		exportFiles: 'Please choose a file to import,only support .xls or .xlsx files ',//EwayLocale.add.exportFiles
		noChange:'Did not change the data, change and then click OK!',//EwayLocale.add.noChange
		operateSuc:'Successful operation',//EwayLocale.add.operateSuc
		operateWrong:'Successful operation',//EwayLocale.add.operateWrong
		deleteOne:'You can only delete a record.',//EwayLocale.add.deleteOne
		chooseRecord:'Please select the records that you want to associate.',//EwayLocale.add.chooseRecord
		choosePlan:'Please select the plan you want to view',//EwayLocale.add.choosePlan
		planDetail:'Plan details',//EwayLocale.add.planDetail
		planDate:'Plan details (date)',//EwayLocale.add.planDate
		planWeek:'Plan details (Week)',//EwayLocale.add.planWeek
		planNoConf:'The plan no detailed settings！',//EwayLocale.add.planNoConf
		chooseRelatedDev:'Please select the device you want to associate！',//EwayLocale.add.chooseRelatedDev
		devRelatedPlan:'Device has been bound with the service plan!',//EwayLocale.add.devRelatedPlan
		
		tips:'Tips',//EwayLocale.add.tips
		input:'Please enter the right item',//EwayLocale.add.input
		roleName:'Just for character ‘a-z’ or ‘A-Z’ or numbers ‘0-9’ ,max length is 40',//EwayLocale.add.roleName
		notNull:'Can not be null',//EwayLocale.add.notNull
		cardNo:'Just for 13-19 numbers ‘0-9’',//EwayLocale.add.cardNo
		blankBegin:'Can not start with blank',//EwayLocale.add.blankBegin
		passwd:{
			confirmPasswd:'Reset the password ,confirm?',//EwayLocale.passwd.confirmPasswd
			resetPasswding:'Reseting the password.....',//EwayLocale.passwd.resetPasswding
			resetPasswdFail:'Reset the password failed'//EwayLocale.passwd.resetPasswdFail
		},
		operateDate:{
			operateDateBegin:'Operate date start with ',//EwayLocale.operateDate.operateDateBegin
			operateDateEnd:'Operate date end  with'//EwayLocale.operateDate.operateDateEnd
		},
		bankOrg:{
			manager:{
				set:{
					chooseOrg:'Please choose the organization which you want to set',//EwayLocale.set.chooseOrg
					managerSuccess:'Set manager successfully.',//EwayLocale.set.managerSuccess
					managerFail:'Set manager failed.',//EwayLocale.set.managerFail
					chooseOneManager:'Please choose at least one record'//EwayLocale.set.chooseOneManager
				},
				remove:{
					confirm:'Delete the manager of this organization?',//EwayLocale.remove.confirm
					reChoose:'You have not choose the organization or there is no manager of this organization, please choose again',//EwayLocale.remove.reChoose
					delSuccess:'Delete the manager successful',//EwayLocale.remove.delSuccess
					delFail:'Delete the manager failed'//EwayLocale.remove.delFail
				}
			},
			orgEligible:'Organization which accordwith the conditions',//EwayLocale.remove.orgEligible
			downGradeOrg:'direct lower organization',//EwayLocale.remove.downGradeOrg
			move:{
				chooseOrg:'Please choose organization which you want to move.',//EwayLocale.move.chooseOrg
				moveSuccess:'Move the organization successfully.'//EwayLocale.move.moveSuccess
			}
		},
		bankPer:{
			allPersonInfo:'ALL info of bank employeers',//EwayLocale.bankPer.allPersonInfo
			link:{
				linkPerson:'Please choose a maintenaner which you want to bind with.',//EwayLocale.link.linkPerson
				linkBankPerson:'Please choose a banker which you want to bind with.',//EwayLocale.link.linkBankPerson
				unLinkPersonFail:'Link failed.',//EwayLocale.link.unLinkPersonFail
				unlinkDev:'Please choose a machine which you want to lift.',//EwayLocale.link.unlinkDev
				linkDev:'Please choose a machine which you want to link.',//EwayLocale.link.linkDev
				unLinkDevFail:'Lift failed.'//EwayLocale.link.unLinkDevFail
			},
			personEligible:'People which accordwith the conditions',//EwayLocale.link.personEligible
			downGradePer:'and its lower organizastion personnel',//EwayLocale.link.downGradePer
			personBelongs:'personnel information belongs to'//EwayLocale.link.personBelongs
		},
		serviceOrg:{
			chooseOrg:'Please choose the service which you want to set.',//EwayLocale.serviceOrg.chooseOrg
			remove:{
				reChoose:'You hava not choose a service or no manager found in the service,please choose again.'//EwayLocale.remove.reChoose
			}
		},
		servicePer:{
			allSerPer:'All info of service people'//EwayLocale.servicePer.allSerPer
		},
		user:{
			add:{
				createAcc:'Please choose the man which you want to create account',//EwayLocale.add.createAcc
				createSuccess:'Create successful,newcreate account',//EwayLocale.add.createSuccess
				initPasswd:'The init password is :888888'//EwayLocale.add.initPasswd
			},
			remove:{
				failRoot:'Delete failed,system manager can not be deleted.',//EwayLocale.remove.failRoot
				confirm:'Delete this record? User log will delete with this operation.',//EwayLocale.remove.confirm
				fail:'Delete failed: can not delete the role,please try again'//EwayLocale.remove.fail
			},
			update:{
				fail:'Update failed:the record dose not exist,please refresh.'//EwayLocale.update.fail
			},
			move:{
				choose:'Please choose the record which you want to move.'//EwayLocale.move.choose

			}
		},
		business:{
			transaction:{
				transactionMonitor:{
					beginMonitor:'Please stop monitoring before input item,click start-monitor button to monitor!',//EwayLocale.transactionMonitor.beginMonitor
					input:'At least one of terminal no,creditAccount,debitAccount is necessary.',//EwayLocale.transactionMonitor.input
					left:'leave'//EwayLocale.transactionMonitor.left
				},
				historyTransaction:{
					input:'Terminal ID is necessary for the query.'//EwayLocale.historyTransaction.input


				}
			},
			blackList:{
				importing:'Importing file',//EwayLocale.blackList.importing
				importSuccess:'Blackcard file importing successful'//EwayLocale.blackList.importSuccess
			},
			card:{
				returnFail:'Turnover failed:system error.',//EwayLocale.card.returnFail
				choose:'Please choose the card which you want to turnover.',//EwayLocale.card.choose
				returnSucess:'Turnover successfully.',//EwayLocale.card.returnSucess
				chooseBack:'Please choose the card which you want to draw.',//EwayLocale.card.chooseBack
				getSuccess:'Draw successfully.',//EwayLocale.card.getSuccess
				destroyConfirm:'Destroy this card?',//EwayLocale.card.destroyConfirm
				destroySuccess:'Destroy successful',//EwayLocale.card.destroySuccess
				chooseDestroy:'Please choose the card which you want to destroy.',//EwayLocale.card.chooseDestroy
				idCardRegex:'Please input the correct ID number,15 or 18 numbers',//EwayLocale.card.idCardRegex
				accountRegex:'Please input the correct ID number of the householdRegister,15 or 18 numbers',//EwayLocale.card.accountRegex
				driveCardRegex:'Please input the correct ID number of the driver-license,15 or 18 numbers',//EwayLocale.card.driveCardRegex
				passportRegex:'Please input the correct ID number of the passport,15 or 18 numbers',//EwayLocale.card.passportRegex
				soldierRegex:'Please input the correct number of officer-license ,1-5 Chinesecharacter or ‘1-10’ numbers',//EwayLocale.card.soldierRegex
				soldierCard:'Please input the correct number of soldier-license 7-8 numbers ',//EwayLocale.card.soldierCard
				busnessPaper:'Please input the correct number of businesslicence,12-15 numbers',//EwayLocale.card.busnessPaper
				busnessCode:'Please input the correct number of juridicalperson,15 numbers',//EwayLocale.card.busnessCode
				taxPaper:'Please input the correct number of taxregistration,15 numbers'//EwayLocale.card.taxPaper

			},
			device:{
				getCashInfoFail:'Get cash boxes info failed',//EwayLocale.device.getCashInfoFail
				operating:'Executing',//EwayLocale.device.operating
				reviewFail:'Failed to review.',//EwayLocale.device.reviewFail
				logLoadConfirm:'Get the atmc logs successful,download it?',//EwayLocale.device.logLoadConfirm
				logPullFail:'Get atmc logs failed',//EwayLocale.device.logPullFail
				logFail:'Log process failed.',//EwayLocale.device.logFail
				linkServerFail:'Connection refused.',//EwayLocale.device.linkServerFail
				logicOpen:'Confirm execute start-service command.',//EwayLocale.device.logicOpen
				openSuccess:'Execute start-service command successfully.',//EwayLocale.device.openSuccess
				openFail:'Execute start-service command failed.',//EwayLocale.device.openFail
				closeConfirm:'Confirm execute stop-service command?',//EwayLocale.device.closeConfirm
				closeServiceSuccess:'Execute stop-service command successfully.',//EwayLocale.device.closeServiceSuccess
				closeServiceFail:'Execute stop-service command failed.',//EwayLocale.device.closeServiceFail
				closeNormal:'Shut down normally.',//EwayLocale.device.closeNormal
				closeComfirm:'Confirm execute normal shut-down command,it may brings some risk?',//EwayLocale.device.closeComfirm
				closing:'Shutting down normally.',//EwayLocale.device.closing
				closeSucess:'Shut down normally successfully.',//EwayLocale.device.closeSucess
				closeFail:'Shut down normally failed.',//EwayLocale.device.closeFail
				closeSentFail:'Send the normal shut-down command failed ',//EwayLocale.device.closeSentFail
				forceClose:'Force shut down',//EwayLocale.device.forceClose
				forceCloseComfirm:'Confirm execute force shut-down command,it may brings some risk?',//EwayLocale.device.forceCloseComfirm
				forceClosing:'Shutting down force.',//EwayLocale.device.forceClosing
				forceCloseSucess:'Force-shut-down successfully.',//EwayLocale.device.forceCloseSucess
				forceCloseFail:'Force-shut-down failed.',//EwayLocale.device.forceCloseFail
				ForceCloseSentFail:'Send the force-shut-down command failed ',//EwayLocale.device.ForceCloseSentFail
				reboot:'Reboot normally',//EwayLocale.device.reboot
				rebootConfirm:'Confirm execute normal-reboot command,it may brings some risk?',//EwayLocale.device.rebootConfirm
				rebooting:'Normally rebooting',//EwayLocale.device.rebooting
				rebootSucess:'Normal-reboot successfully.',//EwayLocale.device.rebootSucess
				rebootFail:'Normal-reboot failed.',//EwayLocale.device.rebootFail
				rebootSendFail:'Send the  normal-reboot command failed ',//EwayLocale.device.rebootSendFail
				forceReboot:'Force reboot',//EwayLocale.device.forceReboot
				forceRebootConfirm:'Confirm execute force-reboot command,it may brings some risk?',//EwayLocale.device.forceRebootConfirm
				forceRebooting:'Executing force-reboot command',//EwayLocale.device.forceRebooting
				forceRebootSuccess:'Force-reboot successfully.',//EwayLocale.device.forceRebootSuccess
				forceRebootFail:'Force-reboot failed.',//EwayLocale.device.forceRebootFail
				forceRebootSendFail:'Send the normal force-reboot command failed ',//EwayLocale.device.forceRebootSendFail
				resetConfirm:'Confirm execute force-reset command?',//EwayLocale.device.resetConfirm
				resetSuccess:'Force-reset successful',//EwayLocale.device.resetSuccess
				resetFail:'Force-reset failed',//EwayLocale.device.resetFail
				resetSendFail:'Send the force-reset command failed.',//EwayLocale.device.resetSendFail
				term:'Terminal ID',//EwayLocale.device.term
				detail:'Detail',//EwayLocale.device.detail
				refresh:'Refreshing......',//EwayLocale.device.refresh
				chooseOrg:'Screen organization',//EwayLocale.device.chooseOrg
				stateSet:'Status-monitor Setting',//EwayLocale.device.stateSet
				filterSet:'Filter setting',//EwayLocale.device.filterSet
				connFirst:'Monitor connection has been paused at the present,please connect the server first as ‘start monitor’"',//EwayLocale.device.connFirst
				matrixPattern:'Matrix Pattern',//EwayLocale.device.matrixPattern
				listPattern:'List Pattern',//EwayLocale.device.listPattern
				remoteCommandMsg:'Remote Command send Successful'//EwayLocale.device.remoteCommandMsg
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
		numberRulesOne: 'Just for character ‘a-z’ or ‘A-Z’、number‘0-9’、minussign‘-’、underline‘_’ or point‘.’,only start with Chinesecharacter,character or number,max length is 100',//EwayLocale.vtype.numberRulesOne
		numberRulesFour	: 'Just for character ‘a-z’ or ‘A-Z’、number‘0-9’,max length is 40',//EwayLocale.vtype.numberRulesFour
		numberRules: 'Just for character ‘a-z’ or ‘A-Z’、number‘0-9’、minussign‘-’、underline‘_’ or point‘.’,only start with Chinesecharacter,character or number,max length is 200',//EwayLocale.vtype.numberRules


		mobileRules:'Input error,mobile number can only be 8-11 numbers',//EwayLocale.vtype.mobileRules
		choseDev:'Please choose the machine which you want to stop.',//EwayLocale.vtype.choseDev
		dataLoad:'Data loading......',//EwayLocale.vtype.dataLoad
		devLinkNormal:'Please check the connection between server and machine.',//EwayLocale.vtype.devLinkNormal
		hardwayInitialize:'Hardware module initializing......',//EwayLocale.vtype.hardwayInitialize
		inputCorrect:'Please input correct data.',//EwayLocale.vtype.inputCorrect
		exportRepError:'Export report error,please try again',//EwayLocale.vtype.exportRepError
		planOutdate:'(This plan is out of date,can not be applied)',//EwayLocale.vtype.planOutdate

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
		versionChart:'Chart of version-issued histroy status',//EwayLocale.vtype.versionChart
		openRefresh:'Start the automatic refresh',//EwayLocale.vtype.openRefresh
		choseTask:'Please choose a Task',//EwayLocale.vtype.choseTask
		cancelTask:'Can not cancel the job which status is ‘finish’.',//EwayLocale.vtype.cancelTask
		cancelParticularTask:'Cancel the job?(running job can only cancel the task which is not running)',//EwayLocale.vtype.cancelParticularTask
		nowDelete:'Deleting......', //EwayLocale.vtype.nowDelete
		endDateGtBenginDate:' Operate date start with can not be later than Operate date end with'//EwayLocale.vtype.endDateGtBenginDate
	},

	commen:{
		jobNum:'Job Number',//EwayLocale.commen.jobNum
		name:'Name',//EwayLocale.commen.name
		personJobName:'Quarters',//EwayLocale.commen.personJobName
		state:'Status',//EwayLocale.commen.state
		birthday:'Birthday',//EwayLocale.commen.birthday
		comboxStatus:{
			onJob:'Incumbency',//EwayLocale.comboxStatus.onJob
			onAdjust:'Leaving',//EwayLocale.comboxStatus.onAdjust
			onVacation:'Vacation',//EwayLocale.comboxStatus.onVacation
			other:'other',//EwayLocale.comboxStatus.other
			dredge:'Dredge',//EwayLocale.comboxStatus.dredge
			open:'Open',//EwayLocale.comboxStatus.open
			close:'Close',//EwayLocale.comboxStatus.close
			pastDue:'Expired',//EwayLocale.comboxStatus.pastDue
			pastDueSoon:'About to expire'//EwayLocale.comboxStatus.pastDueSoon
		},
		type:'Type',//EwayLocale.comboxStatus.type
		comboxType:{
			machineManager:'Device Manager',//EwayLocale.comboxType.machineManager
			machineRepairer:'Maintenance Engineers'//EwayLocale.comboxType.machineRepairer
		},
		mobile:'Mobile',//EwayLocale.comboxType.mobile
		email:'E-Mail',//EwayLocale.comboxType.email
		phone:'Phone',//EwayLocale.comboxType.phone
		gender:'Gender',//EwayLocale.comboxType.gender
		all:'All',//EwayLocale.comboxType.all
		comboxGender:{
			male:'Male',//EwayLocale.comboxGender.male
			female:'Female',//EwayLocale.comboxGender.female
			unknow:'Unknow'//EwayLocale.comboxGender.unknow
		},
		remark:'Description',//EwayLocale.comboxGender.remark
		terminalId:'Terminal ID',//EwayLocale.comboxGender.terminalId
		ip:'IP',//EwayLocale.comboxGender.ip
		orgNameBelongs:'Bank',//EwayLocale.comboxGender.orgNameBelongs
		devTypeName:'Device Type',//EwayLocale.comboxGender.devTypeName
		devVendorName:'Device Brand',//EwayLocale.comboxGender.devVendorName
		devCatalogName:'Device Catalog',//EwayLocale.comboxGender.devCatalogName
		devStatus:'Status',//EwayLocale.comboxGender.devStatus
		comboxDevStatus:{
			upOpen:'upOpen',//EwayLocale.comboxDevStatus.upOpen
			open:'Open',//EwayLocale.comboxDevStatus.open
			stop:'Stop',//EwayLocale.comboxDevStatus.stop
			scrapped:'Scrapped'//EwayLocale.comboxDevStatus.scrapped
		},
		setManager:'Settings',//EwayLocale.comboxDevStatus.setManager
		devServiceName:'Maintenance Provider',//EwayLocale.comboxDevStatus.devServiceName
		cashboxLimit:'Alarm of money in cashbox',//EwayLocale.comboxDevStatus.cashboxLimit
		installDate:'Installation Time',//EwayLocale.comboxDevStatus.installDate
		address:'Address',//EwayLocale.comboxDevStatus.address
		areaCode:'Area code',//EwayLocale.comboxDevStatus.areaCode
		areaName:'Area name',//EwayLocale.comboxDevStatus.areaName
		toolbar:'Total：{2},display{0}-{1}',//EwayLocale.comboxDevStatus.toolbar
		bindMachine :'Devices bound',//EwayLocale.comboxDevStatus.bindMachine
		lift:'Relieve',//EwayLocale.comboxDevStatus.lift
		canBindMachine:'Bindable devices',//EwayLocale.comboxDevStatus.canBindMachine
		bind:'Bind',//EwayLocale.comboxDevStatus.bind
		filter:'Filter',//EwayLocale.comboxDevStatus.filter
		stateDict:{
			newCreate:'New',//EwayLocale.stateDict.newCreate
			normal:'Normal',//EwayLocale.stateDict.normal
			locked:'Locked',//EwayLocale.stateDict.locked
			disable:'Disable',//EwayLocale.stateDict.disable
			frozen:'Frozen',//EwayLocale.stateDict.frozen
			deleted:'Deleted'//EwayLocale.stateDict.deleted
		},
		yes:'yes',//EwayLocale.stateDict.yes
		no:'no',//EwayLocale.stateDict.no
		selectAll:'Select All',//EwayLocale.stateDict.selectAll
		selectNon:'Select None',//EwayLocale.stateDict.selectNon
		content:'Content',//EwayLocale.stateDict.content
		upgrade:'Upgrade',//EwayLocale.stateDict.upgrade
		port:'port',//EwayLocale.stateDict.port
		seviceMode:'Service mode',//EwayLocale.stateDict.seviceMode
		insideOutside:'In bank flag',//EwayLocale.stateDict.insideOutside
		appVersion:'Atmc Version',//EwayLocale.stateDict.appVersion
		devInfo:'Device Basic Info',//EwayLocale.stateDict.devInfo
		
		personnel:'Contacts',//EwayLocale.stateDict.personnel
		warn:'Warning',//EwayLocale.stateDict.warn
		fatal:'Fatal',//EwayLocale.stateDict.fatal
		unStable:'UnStable',//EwayLocale.stateDict.unStable
		unknow:'Unknown',//EwayLocale.stateDict.unknow
		noDevice:'NoDevice',//EwayLocale.stateDict.noDevice
		description:'Description',//EwayLocale.stateDict.description
		info:'Detail',//EwayLocale.stateDict.info
		startDataTime:'Start time',//EwayLocale.stateDict.startDataTime
		endDataTime:'End time',//EwayLocale.stateDict.endDataTime
		year:'Year',//EwayLocale.stateDict.year
		month:'Month',//EwayLocale.stateDict.month
		day:'Day',//EwayLocale.stateDict.day
		yearTime:'Year',//EwayLocale.stateDict.yearTime
		monthTime:'Month',//EwayLocale.stateDict.monthTime
		dayTime:'Day',//EwayLocale.stateDict.dayTime
		orgFramework:'Organization',//EwayLocale.stateDict.orgFramework
		matchOrg:'Organization matched',//EwayLocale.stateDict.matchOrg
		orgID:'Organization ID',//EwayLocale.stateDict.orgID
		endValidty:'Valid date until',//EwayLocale.stateDict.endValidty
		publishDate:'Issue date',//EwayLocale.stateDict.publishDate
		announceTheme:'Announce theme',//EwayLocale.stateDict.announceTheme
		filterDelete: 'deleted'//EwayLocale.stateDict.filterDelete


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
		registSuc:'Regist successful',//EwayLocale.system.registSuc
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
