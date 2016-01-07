Ext.apply(EwayLocale,{
	

	cases:{
		confirm:'Confirm',//EwayLocale.cases.confirm
		cancel:'Cancel',//EwayLocale.cases.cancel
		concern:'Attention please',//EwayLocale.cases.concern
		SRCBView:',Send by monitor system',//EwayLocale.cases.SRCBView
		nowExportFile:'Importing File',//EwayLocale.cases.nowExportFile
		exportFaultInfo:'Import fault code successfully',//EwayLocale.cases.exportFaultInfo
		caseFault:{
			faultRelevantInfo:'SMS about fault',//EwayLocale.cases.caseFault.faultRelevantInfo
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
		    closeByForce : 'Click to close fault by hand',//EwayLocale.cases.caseFault.closeByForce
		    faultCloseType : 'Close Type',//EwayLocale.cases.caseFault.faultCloseType
		    none : 'none',//EwayLocale.cases.caseFault.none

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
		    faultSearch:'Fault Search',//EwayLocale.cases.caseFault.faultSearch
		    none : 'Do Not Send'//EwayLocale.cases.caseFault.none

		},
		caseNotify:{
			fault:'Fault',//EwayLocale.cases.caseNotify.fault
			faultDetails:'Detail',//EwayLocale.cases.caseNotify.faultDetails
			faultlastTime: 'Fault last(unit:hour)',//EwayLocale.cases.caseNotify.faultlastTime
			checkFailure:'View failed!',//EwayLocale.cases.caseNotify.checkFailure
			innerFault:'System error',//EwayLocale.cases.caseNotify.innerFault
			messageCheck:'SMS Info'//EwayLocale.cases.caseNotify.messageCheck
		},
		faultClassify:{
			faultClassifyName: 'Name',//EwayLocale.cases.faultClassify.faultClassifyName
			faultresponsorType: 'Handlers Type', //EwayLocale.cases.faultClassify.faultresponsorType
			maintain:'Maintenance Engineers',//EwayLocale.cases.faultClassify.maintain
			manageAndMaintain:'All the above',//EwayLocale.cases.faultClassify.manageAndMaintain
			upGradeTimes: 'Max upgrade times',//EwayLocale.cases.faultClassify.upGradeTimes
			faultInformWay:'Notify way',//EwayLocale.cases.faultClassify.faultInformWay
			faultCloseInterval:'Intervals between close(unit:hour)',//EwayLocale.cases.faultClassify.faultCloseInterval
			faultTypeConfiguration: 'Fault type setting',//EwayLocale.cases.faultClassify.faultTypeConfiguration
			updateFaultTypeConfiguration: 'Update Fault Type Info',//EwayLocale.cases.faultClassify.updateFaultTypeConfiguration
			number:'Just for numbers ‘0-9’',//EwayLocale.cases.faultClassify.number
			informNumber:'Can not be 0,1-5 numbers'//EwayLocale.cases.faultClassify.informNumber
		},
		notifyMould:{
			noticeType:'Type',//EwayLocale.cases.notifyMould.noticeType
			createNotice:'Create',//EwayLocale.cases.notifyMould.createNotice
			upgradeNotice:'Upgrade',//EwayLocale.cases.notifyMould.upgradeNotice
			closeNotice:'Close',//EwayLocale.cases.notifyMould.closeNotice
			noticeValue: 'Parameters',//EwayLocale.cases.notifyMould.noticeValue
			messageContentConfiguration:'SMS content',//EwayLocale.cases.notifyMould.messageContentConfiguration
			updateMessageContentConfiguration: 'Modify SMS content',//EwayLocale.cases.notifyMould.updateMessageContentConfiguration
			necessaryOption: 'This option is necessary',//EwayLocale.cases.notifyMould.necessaryOption
			faultType:'Fault type',//EwayLocale.cases.notifyMould.faultType
			applyStatus:'App status'//EwayLocale.cases.notifyMould.applyStatus
		},
		vendorCode:{
			exportProviderInfo: 'Import manufacturer fault code',//EwayLocale.cases.vendorCode.exportProviderInfo
			provider:'Manufacturer',//EwayLocale.cases.vendorCode.provider
			exportFile: 'Import file',//EwayLocale.cases.vendorCode.exportFile
			deleteFaultInfo:'Delete manufacturer fault code',//EwayLocale.cases.vendorCode.deleteFaultInfo
			templateLoad:'Example download',//EwayLocale.cases.vendorCode.templateLoad
			massRemove: 'Batch delete',//EwayLocale.cases.vendorCode.massRemove
			providerDescription:'Description',//EwayLocale.cases.vendorCode.providerDescription
			solveProject: 'Solution',//EwayLocale.cases.vendorCode.solveProject
			providerFaultInfo:'Fault Code',//EwayLocale.cases.vendorCode.providerFaultInfo
			formwork:'fault_message_en.xls'//EwayLocale.cases.vendorCode.formwork
		}
	}
});
