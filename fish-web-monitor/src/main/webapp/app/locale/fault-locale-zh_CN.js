Ext.apply(EwayLocale,{
	

	cases:{
		confirm:'确认',//EwayLocale.cases.confirm
		cancel:'取消',//EwayLocale.cases.cancel
		concern:'请关注',//EwayLocale.cases.concern
		SRCBView:'新监控发送',//EwayLocale.cases.SRCBView
		nowExportFile:'正在导入文件',//EwayLocale.cases.nowExportFile
		exportFaultInfo:'导入厂商故障信息成功.',//EwayLocale.cases.exportFaultInfo
		caseFault:{
			faultRelevantInfo:'故障相关短信',//EwayLocale.caseFault.faultRelevantInfo
			faultModule:'故障模块',//EwayLocale.caseFault.faultModule
			cardReaderModule:'读卡器模块',//EwayLocale.caseFault.cardReaderModule
			depoistModule:'存款模块',//EwayLocale.caseFault.depoistModule
			drawModule:'取款模块',//EwayLocale.caseFault.drawModule
			rprModule:'凭条打印模块',//EwayLocale.caseFault.rprModule
			jprModule:'日志打印模块',//EwayLocale.caseFault.jprModule
			pinModule:'密码键盘模块',//EwayLocale.caseFault.pinModule
			textTerminalUnit:'文本终端单元',//EwayLocale.caseFault.textTerminalUnit
			sensoModule:'传感器模块',//EwayLocale.caseFault.sensoModule
			iscModule:'身份证扫描仪模块',//EwayLocale.caseFault.iscModule
			iccModule:'发卡器模块',//EwayLocale.caseFault.iccModule
			fgpModule:'指纹仪模块',//EwayLocale.caseFault.fgpModule
			faultClassify: '故障分类',//EwayLocale.caseFault.faultClassify
			faultCode : '故障码',//EwayLocale.caseFault.faultCode
			providerFaultCode: '厂商故障码',//EwayLocale.caseFault.providerFaultCode
			faultStartTime : '故障开始时间',//EwayLocale.caseFault.faultStartTime
			faultCloseTime : '故障关闭时间',//EwayLocale.caseFault.faultCloseTime
			faultContinueTime : '持续时长',//EwayLocale.caseFault.faultContinueTime
		    faultState : '故障状态',//EwayLocale.caseFault.faultState
		    status:{
		    	open:'未关闭',//EwayLocale.status.open
		    	close:'已关闭'//EwayLocale.status.close
		    },
		    closeType:{
		    	force : '手工关闭',//EwayLocale.closeType.force
		    	normal : '正常关闭'//EwayLocale.closeType.normal
		    },
		    closeByForce : '点击手工关闭故障',//EwayLocale.closeType.closeByForce
		    faultCloseType : '故障关闭方式',//EwayLocale.closeType.faultCloseType
		    none : '没有',//EwayLocale.closeType.none
		    upgradeTimes: '升级次数',//EwayLocale.closeType.upgradeTimes
		    message: '短信',//EwayLocale.closeType.message
		    checkDetails: '查看详情',//EwayLocale.closeType.checkDetails
		    bankPer: '银行联系人',//EwayLocale.closeType.bankPer
		    serPer: '供应商联系人',//EwayLocale.closeType.serPer
		    createTime: '创建时间',//EwayLocale.closeType.createTime
		    informContent: '通知内容',//EwayLocale.closeType.informContent
		    messageContentDetail: '短信内容详情',//EwayLocale.closeType.messageContentDetail
		    informWay: '通知方式',//EwayLocale.closeType.informWay
		    mail:'邮件',//EwayLocale.closeType.mail
		    messageAndMail:'短信和邮件',//EwayLocale.closeType.messageAndMail
		    informMobile: '通知手机号',//EwayLocale.closeType.informMobile
		    notifyTimes: '通知次数',//EwayLocale.closeType.notifyTimes
		    notifyRepeatTimes: '重复通知次数',//EwayLocale.closeType.notifyRepeatTimes
		    sendTimes: '发送次数',//EwayLocale.closeType.sendTimes
		    sendInterval: '发送时间间隔',//EwayLocale.closeType.sendInterval
		    sendTime: '发送时间',//EwayLocale.closeType.sendTime
		    faultSearch:'故障查询',//EwayLocale.closeType.faultSearch
		    none : '不发送'//EwayLocale.closeType.none

		},
		caseNotify:{
			fault:'故障',//EwayLocale.caseNotify.fault
			faultDetails:'故障详情',//EwayLocale.caseNotify.faultDetails
			faultlastTime: '故障持续时长(单位:小时)',//EwayLocale.caseNotify.faultlastTime
			checkFailure:'查看失败！',//EwayLocale.caseNotify.checkFailure
			innerFault:'内部错误',//EwayLocale.caseNotify.innerFault
			messageCheck:'短信查询'//EwayLocale.caseNotify.messageCheck
		},
		faultClassify:{
			faultClassifyName: '故障分类名称',//EwayLocale.faultClassify.faultClassifyName
			faultresponsorType: '故障责任人类型',//EwayLocale.faultClassify.faultresponsorType
			maintain:'维护员',//EwayLocale.faultClassify.maintain
			manageAndMaintain:'管机员和维护员 ',//EwayLocale.faultClassify.manageAndMaintain
			upGradeTimes: '最高升级次数',//EwayLocale.faultClassify.upGradeTimes
			faultInformWay:'故障通知方式',//EwayLocale.faultClassify.faultInformWay
			faultCloseInterval:'故障规定关闭时间间隔（单位:小时）',//EwayLocale.faultClassify.faultCloseInterval
			faultTypeConfiguration: '故障类型配置',//EwayLocale.faultClassify.faultTypeConfiguration
			updateFaultTypeConfiguration: '更改故障类型配置',//EwayLocale.faultClassify.updateFaultTypeConfiguration
			number:'由数字‘0-9’,‘.’组成',//EwayLocale.faultClassify.number
			informNumber:'通知次数不能为0,由数字‘0-9’组成,1-5位'//EwayLocale.faultClassify.informNumber
		},
		notifyMould:{
			noticeType:'通知类型',//EwayLocale.notifyMould.noticeType
			createNotice:'创建通知',//EwayLocale.notifyMould.createNotice
			upgradeNotice:'升级通知',//EwayLocale.notifyMould.upgradeNotice
			closeNotice:'关闭通知',//EwayLocale.notifyMould.closeNotice
			noticeValue: '通知参数',//EwayLocale.notifyMould.noticeValue
			messageContentConfiguration:'短信内容配置',//EwayLocale.notifyMould.messageContentConfiguration
			updateMessageContentConfiguration: '更改短信内容配置',//EwayLocale.notifyMould.updateMessageContentConfiguration
			necessaryOption: '此项为必选项',//EwayLocale.notifyMould.necessaryOption
			faultType:'故障类型',//EwayLocale.notifyMould.faultType
			applyStatus:'应用状态'//EwayLocale.notifyMould.applyStatus
		},
		vendorCode:{
			exportProviderInfo: '导入厂商故障信息',//EwayLocale.vendorCode.exportProviderInfo
			provider:'厂商',//EwayLocale.vendorCode.provider
			exportFile: '导入文件',//EwayLocale.vendorCode.exportFile
			deleteFaultInfo:'删除厂商故障信息',//EwayLocale.vendorCode.deleteFaultInfo
			templateLoad:'模板下载',//EwayLocale.vendorCode.templateLoad
			massRemove: '批量删除',//EwayLocale.vendorCode.massRemove
			providerDescription:'厂商故障描述',//EwayLocale.vendorCode.providerDescription
			solveProject: '解决方案',//EwayLocale.vendorCode.solveProject
			providerFaultInfo:'厂商故障信息管理',//EwayLocale.vendorCode.providerFaultInfo
			formwork:'fault_message_ch.xls'//EwayLocale.vendorCode.formwork
		}
	}

});
