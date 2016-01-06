Ext.apply(EwayLocale,{
	

	cases:{
		confirm:'Confirm',//EwayLocale.cases.confirm
		cancel:'Cancel',//EwayLocale.cases.cancel
		concern:'Attention please',//EwayLocale.cases.concern
		SRCBView:',Send by monitor system',//EwayLocale.cases.SRCBView
		nowExportFile:'Importing File',//EwayLocale.cases.nowExportFile
		exportFaultInfo:'Import fault code successfully',//EwayLocale.cases.exportFaultInfo
		caseFault:{
			faultRelevantInfo:'SMS about fault',//EwayLocale.caseFault.faultRelevantInfo
			faultModule:'Fault Module',//EwayLocale.caseFault.faultModule
			cardReaderModule:'Card Reader',//EwayLocale.caseFault.cardReaderModule
			depoistModule:'Deposit Module',//EwayLocale.caseFault.depoistModule
			drawModule:'Dispenser Module',//EwayLocale.caseFault.drawModule
			rprModule:'Receipt Printer',//EwayLocale.caseFault.rprModule
			jprModule:'Journal Printer',//EwayLocale.caseFault.jprModule
			pinModule:'PIN',//EwayLocale.caseFault.pinModule
			textTerminalUnit:'TTU',//EwayLocale.caseFault.textTerminalUnit
			sensoModule:'Sensors',//EwayLocale.caseFault.sensoModule
			faultClassify: 'Fault Catalog',//EwayLocale.caseFault.faultClassify
			faultCode : 'Fault Code',//EwayLocale.caseFault.faultCode
			providerFaultCode: 'Fault Code',//EwayLocale.caseFault.providerFaultCode
			faultStartTime : 'Start Time',//EwayLocale.caseFault.faultStartTime
			faultCloseTime : 'Close Time',//EwayLocale.caseFault.faultCloseTime
			faultContinueTime : 'Duration',//EwayLocale.caseFault.faultContinueTime
		    faultState : 'Status',//EwayLocale.caseFault.faultState
		    status:{
		    	open:'Open',//EwayLocale.status.open
		    	close:'Closed'//EwayLocale.status.close
		    },
		    closeType:{
		    	force : 'Close Manually',//EwayLocale.closeType.force
		    	normal : 'Close Automaticlly'//EwayLocale.closeType.normal
		    },
		    closeByForce : 'Click to close fault by hand',//EwayLocale.closeType.closeByForce
		    faultCloseType : 'Close Type',//EwayLocale.closeType.faultCloseType
		    none : 'none',//EwayLocale.closeType.none

		    upgradeTimes: 'Upgrade Times',//EwayLocale.closeType.upgradeTimes
		    message: 'SMS',//EwayLocale.closeType.message
		    checkDetails: 'Detail',//EwayLocale.closeType.checkDetails
		    bankPer: 'Bank Contacts',//EwayLocale.closeType.bankPer
		    serPer: 'Maintenance Engineers',//EwayLocale.closeType.serPer
		    createTime: 'Create Time',//EwayLocale.closeType.createTime
		    informContent: 'Notify Content',//EwayLocale.closeType.informContent
		    messageContentDetail: 'SMS Content',//EwayLocale.closeType.messageContentDetail
		    informWay: 'Notify Way',//EwayLocale.closeType.informWay
		    mail:'Email',//EwayLocale.closeType.mail
		    messageAndMail:'SMS And Email',//EwayLocale.closeType.messageAndMail
		    informMobile: 'Notify Mobile',//EwayLocale.closeType.informMobile
		    notifyTimes: 'Notify Times',//EwayLocale.closeType.notifyTimes
		    notifyRepeatTimes: 'Notify Times',//EwayLocale.closeType.notifyRepeatTimes
		    sendTimes: 'Send Times',//EwayLocale.closeType.sendTimes
		    sendInterval: 'Intervals Between Each Send',//EwayLocale.closeType.sendInterval
		    sendTime: 'Send Time',//EwayLocale.closeType.sendTime
		    faultSearch:'Fault Search',//EwayLocale.closeType.faultSearch
		    none : 'Do Not Send'//EwayLocale.closeType.none

		},
		caseNotify:{
			fault:'Fault',//EwayLocale.caseNotify.fault
			faultDetails:'Detail',//EwayLocale.caseNotify.faultDetails
			faultlastTime: 'Fault last(unit:hour)',//EwayLocale.caseNotify.faultlastTime
			checkFailure:'View failed!',//EwayLocale.caseNotify.checkFailure
			innerFault:'System error',//EwayLocale.caseNotify.innerFault
			messageCheck:'SMS Info'//EwayLocale.caseNotify.messageCheck
		},
		faultClassify:{
			faultClassifyName: 'Name',//EwayLocale.faultClassify.faultClassifyName
			faultresponsorType: 'Handlers Type', //EwayLocale.faultClassify.faultresponsorType
			maintain:'Maintenance Engineers',//EwayLocale.faultClassify.maintain
			manageAndMaintain:'All the above',//EwayLocale.faultClassify.manageAndMaintain
			upGradeTimes: 'Max upgrade times',//EwayLocale.faultClassify.upGradeTimes
			faultInformWay:'Notify way',//EwayLocale.faultClassify.faultInformWay
			faultCloseInterval:'Intervals between close(unit:hour)',//EwayLocale.faultClassify.faultCloseInterval
			faultTypeConfiguration: 'Fault type setting',//EwayLocale.faultClassify.faultTypeConfiguration
			updateFaultTypeConfiguration: 'Update Fault Type Info',//EwayLocale.faultClassify.updateFaultTypeConfiguration
			number:'Just for numbers ‘0-9’',//EwayLocale.faultClassify.number
			informNumber:'Can not be 0,1-5 numbers'//EwayLocale.faultClassify.informNumber
		},
		notifyMould:{
			noticeType:'Type',//EwayLocale.notifyMould.noticeType
			createNotice:'Create',//EwayLocale.notifyMould.createNotice
			upgradeNotice:'Upgrade',//EwayLocale.notifyMould.upgradeNotice
			closeNotice:'Close',//EwayLocale.notifyMould.closeNotice
			noticeValue: 'Parameters',//EwayLocale.notifyMould.noticeValue
			messageContentConfiguration:'SMS content',//EwayLocale.notifyMould.messageContentConfiguration
			updateMessageContentConfiguration: 'Modify SMS content',//EwayLocale.notifyMould.updateMessageContentConfiguration
			necessaryOption: 'This option is necessary',//EwayLocale.notifyMould.necessaryOption
			faultType:'Fault type',//EwayLocale.notifyMould.faultType
			applyStatus:'App status'//EwayLocale.notifyMould.applyStatus
		},
		vendorCode:{
			exportProviderInfo: 'Import manufacturer fault code',//EwayLocale.vendorCode.exportProviderInfo
			provider:'Manufacturer',//EwayLocale.vendorCode.provider
			exportFile: 'Import file',//EwayLocale.vendorCode.exportFile
			deleteFaultInfo:'Delete manufacturer fault code',//EwayLocale.vendorCode.deleteFaultInfo
			templateLoad:'Example download',//EwayLocale.vendorCode.templateLoad
			massRemove: 'Batch delete',//EwayLocale.vendorCode.massRemove
			providerDescription:'Description',//EwayLocale.vendorCode.providerDescription
			solveProject: 'Solution',//EwayLocale.vendorCode.solveProject
			providerFaultInfo:'Fault Code',//EwayLocale.vendorCode.providerFaultInfo
			formwork:'fault_message_en.xls'//EwayLocale.vendorCode.formwork
		}
	}
});
