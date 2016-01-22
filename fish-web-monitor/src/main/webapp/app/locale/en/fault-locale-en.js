Ext.apply(EwayLocale,{
	

	cases:{
		confirm:'Confirm',//EwayLocale.cases.confirm
		cancel:'Cancel',//EwayLocale.cases.cancel
		concern:'Attention Please',//EwayLocale.cases.concern
		SRCBView:',Send By Monitor System',//EwayLocale.cases.SRCBView
		nowExportFile:'Importing File',//EwayLocale.cases.nowExportFile
		exportFaultInfo:'Import Fault Code Successfully',//EwayLocale.cases.exportFaultInfo
		caseFault:{
			faultRelevantInfo:'SMS About Fault',//EwayLocale.cases.caseFault.faultRelevantInfo
			faultModule:'Fault Module',//EwayLocale.cases.caseFault.faultModule
			cardReaderModule:'Card Reader',//EwayLocale.cases.caseFault.cardReaderModule
			depoistModule:'Deposit Module',//EwayLocale.cases.caseFault.depoistModule
			drawModule:'Dispenser Module',//EwayLocale.cases.caseFault.drawModule
			rprModule:'Receipt Printer',//EwayLocale.cases.caseFault.rprModule
			jprModule:'Journal Printer',//EwayLocale.cases.caseFault.jprModule
			pinModule:'PIN',//EwayLocale.cases.caseFault.pinModule
			textTerminalUnit:'TTU',//EwayLocale.cases.caseFault.textTerminalUnit
			iscModule:'ID Card Reader',//EwayLocale.cases.caseFault.iscModule
			iccModule:'Issue Card Module',//EwayLocale.cases.caseFault.iccModule
			fgpModule:'Fingerprint Recognition Module',//EwayLocale.cases.caseFault.fgpModule
			sensoModule:'Sensors',//EwayLocale.cases.caseFault.sensoModule
			faultClassify: 'Fault Catalog',//EwayLocale.cases.caseFault.faultClassify
			faultCode : 'Fault Code',//EwayLocale.cases.caseFault.faultCode
			providerFaultCode: 'Fault Code',//EwayLocale.cases.caseFault.providerFaultCode
			faultStartTime : 'Start Time',//EwayLocale.cases.caseFault.faultStartTime
			faultCloseTime : 'Close Time',//EwayLocale.cases.caseFault.faultCloseTime
			faultContinueTime : 'Duration',//EwayLocale.cases.caseFault.faultContinueTime
		    faultState : 'Status',//EwayLocale.cases.caseFault.faultState
		    status:{
		    	open:'Open',//EwayLocale.cases.caseFault.status.open
		    	close:'Closed'//EwayLocale.cases.caseFault.status.close
		    },
		    closeType:{
		    	force : 'Close Manually',//EwayLocale.cases.caseFault.closeType.force
		    	normal : 'Close Automaticlly'//EwayLocale.cases.caseFault.closeType.normal
		    },
		    closeByForce : 'Click To Close Fault By Hand',//EwayLocale.cases.caseFault.closeByForce
		    faultCloseType : 'Close Type',//EwayLocale.cases.caseFault.faultCloseType
		    none : 'None',//EwayLocale.cases.caseFault.none

		    upgradeTimes: 'Upgrade Times',//EwayLocale.cases.caseFault.upgradeTimes
		    message: 'SMS',//EwayLocale.cases.caseFault.message
		    checkDetails: 'Detail',//EwayLocale.cases.caseFault.checkDetails
		    bankPer: 'Bank Contacts',//EwayLocale.cases.caseFault.bankPer
		    serPer: 'Maintenance Engineers',//EwayLocale.cases.caseFault.serPer
		    createTime: 'Create Time',//EwayLocale.cases.caseFault.createTime
		    informContent: 'Notify Content',//EwayLocale.cases.caseFault.informContent
		    messageContentDetail: 'SMS Content',//EwayLocale.cases.caseFault.messageContentDetail
		    informWay: 'Notify Way',//EwayLocale.cases.caseFault.informWay
		    mail:'Email',//EwayLocale.cases.caseFault.mail
		    messageAndMail:'SMS And Email',//EwayLocale.cases.caseFault.messageAndMail
		    informMobile: 'Notify Mobile',//EwayLocale.cases.caseFault.informMobile
		    notifyTimes: 'Notify Times',//EwayLocale.cases.caseFault.notifyTimes
		    notifyRepeatTimes: 'Notify Times',//EwayLocale.cases.caseFault.notifyRepeatTimes
		    sendTimes: 'Send Times',//EwayLocale.cases.caseFault.sendTimes
		    sendInterval: 'Intervals Between Each Send',//EwayLocale.cases.caseFault.sendInterval
		    sendTime: 'Send Time',//EwayLocale.cases.caseFault.sendTime
		    faultSearch:'Fault Info',//EwayLocale.cases.caseFault.faultSearch
		    none : 'Do Not Send'//EwayLocale.cases.caseFault.none

		},
		caseNotify:{
			fault:'Fault',//EwayLocale.cases.caseNotify.fault
			faultDetails:'Detail',//EwayLocale.cases.caseNotify.faultDetails
			faultlastTime: 'Fault Last(unit:Hour)',//EwayLocale.cases.caseNotify.faultlastTime
			checkFailure:'View Failed!',//EwayLocale.cases.caseNotify.checkFailure
			innerFault:'System Error',//EwayLocale.cases.caseNotify.innerFault
			messageCheck:'SMS Info'//EwayLocale.cases.caseNotify.messageCheck
		},
		faultClassify:{
			faultClassifyName: 'Name',//EwayLocale.cases.faultClassify.faultClassifyName
			faultresponsorType: 'Handlers Type', //EwayLocale.cases.faultClassify.faultresponsorType
			maintain:'Maintenance Engineers',//EwayLocale.cases.faultClassify.maintain
			manageAndMaintain:'All The Above',//EwayLocale.cases.faultClassify.manageAndMaintain
			upGradeTimes: 'Max Upgrade Times',//EwayLocale.cases.faultClassify.upGradeTimes
			faultInformWay:'Notify Way',//EwayLocale.cases.faultClassify.faultInformWay
			faultCloseInterval:'Intervals Between Close(unit:Hour)',//EwayLocale.cases.faultClassify.faultCloseInterval
			faultTypeConfiguration: 'Fault Type',//EwayLocale.cases.faultClassify.faultTypeConfiguration
			updateFaultTypeConfiguration: 'Update Fault Type Info',//EwayLocale.cases.faultClassify.updateFaultTypeConfiguration
			number:'Just For Numbers ‘0-9’',//EwayLocale.cases.faultClassify.number
			informNumber:'Can Not Be 0,1-5 Numbers'//EwayLocale.cases.faultClassify.informNumber
		},
		notifyMould:{
			noticeType:'Type',//EwayLocale.cases.notifyMould.noticeType
			createNotice:'Create',//EwayLocale.cases.notifyMould.createNotice
			upgradeNotice:'Upgrade',//EwayLocale.cases.notifyMould.upgradeNotice
			closeNotice:'Close',//EwayLocale.cases.notifyMould.closeNotice
			noticeValue: 'Parameters',//EwayLocale.cases.notifyMould.noticeValue
			messageContentConfiguration:'SMS Config',//EwayLocale.cases.notifyMould.messageContentConfiguration
			updateMessageContentConfiguration: 'Modify SMS Config',//EwayLocale.cases.notifyMould.updateMessageContentConfiguration
			necessaryOption: 'This Option Is Necessary',//EwayLocale.cases.notifyMould.necessaryOption
			faultType:'Fault Type',//EwayLocale.cases.notifyMould.faultType
			applyStatus:'App Status'//EwayLocale.cases.notifyMould.applyStatus
		},
		vendorCode:{
			exportProviderInfo: 'Import Manufacturer Fault Code',//EwayLocale.cases.vendorCode.exportProviderInfo
			provider:'Manufacturer',//EwayLocale.cases.vendorCode.provider
			exportFile: 'Import File',//EwayLocale.cases.vendorCode.exportFile
			deleteFaultInfo:'Delete Manufacturer Fault Code',//EwayLocale.cases.vendorCode.deleteFaultInfo
			templateLoad:'Example Download',//EwayLocale.cases.vendorCode.templateLoad
			massRemove: 'Batch Delete',//EwayLocale.cases.vendorCode.massRemove
			providerDescription:'Description',//EwayLocale.cases.vendorCode.providerDescription
			solveProject: 'Solution',//EwayLocale.cases.vendorCode.solveProject
			providerFaultInfo:'Fault Code',//EwayLocale.cases.vendorCode.providerFaultInfo
			formwork:'fault_message_en.xls'//EwayLocale.cases.vendorCode.formwork
		}
	}
});
