Ext.apply(EwayLocale,{


	cases:{
		confirm:'确认',//EwayLocale.cases.confirm
		cancel:'取消',//EwayLocale.cases.cancel
		concern:'请关注',//EwayLocale.cases.concern
		SRCBView:'新监控发送',//EwayLocale.cases.SRCBView
		nowExportFile:'正在导入文件',//EwayLocale.cases.nowExportFile
		exportFaultInfo:'导入厂商故障信息成功.',//EwayLocale.cases.exportFaultInfo
		caseFault:{
			faultRelevantInfo:'故障相关短信',//EwayLocale.cases.caseFault.faultRelevantInfo
			faultModule:'故障模块',//EwayLocale.cases.caseFault.faultModule
			cardReaderModule:'读卡器模块',//EwayLocale.cases.caseFault.cardReaderModule
			depoistModule:'存款模块',//EwayLocale.cases.caseFault.depoistModule
			drawModule:'取款模块',//EwayLocale.cases.caseFault.drawModule
			rprModule:'凭条打印模块',//EwayLocale.cases.caseFault.rprModule
			jprModule:'日志打印模块',//EwayLocale.cases.caseFault.jprModule
			pinModule:'密码键盘模块',//EwayLocale.cases.caseFault.pinModule
			textTerminalUnit:'文本终端单元',//EwayLocale.cases.caseFault.textTerminalUnit
			sensoModule:'传感器模块',//EwayLocale.cases.caseFault.sensoModule
			iscModule:'身份证扫描仪模块',//EwayLocale.cases.caseFault.iscModule
			iccModule:'发卡器模块',//EwayLocale.cases.caseFault.iccModule
			fgpModule:'指纹仪模块',//EwayLocale.cases.caseFault.fgpModule
			camModule:'摄像头故障模块',//EwayLocale.cases.caseFault.camModule
			bcrModule:'二维码故障模块',//EwayLocale.cases.caseFault.bcrModule
			faultClassify: '故障分类',//EwayLocale.cases.caseFault.faultClassify
			faultCode : '故障码',//EwayLocale.cases.caseFault.faultCode
			providerFaultCode: '厂商故障码',//EwayLocale.cases.caseFault.providerFaultCode
			faultStartTime : '故障开始时间',//EwayLocale.cases.caseFault.faultStartTime
			faultCloseTime : '故障关闭时间',//EwayLocale.cases.caseFault.faultCloseTime
			faultContinueTime : '持续时长',//EwayLocale.cases.caseFault.faultContinueTime
		    faultState : '故障状态',//EwayLocale.cases.caseFault.faultState
		    status:{
		    	open:'未关闭',//EwayLocale.cases.caseFault.status.open
		    	close:'已关闭'//EwayLocale.cases.caseFault.status.close
		    },
		    closeType:{
		    	force : '手工关闭',//EwayLocale.cases.caseFault.closeType.force
		    	normal : '正常关闭'//EwayLocale.cases.caseFault.closeType.normal
		    },
		    closeByForce : '点击手工关闭故障',//EwayLocale.cases.caseFault.closeByForce
		    faultCloseType : '故障关闭方式',//EwayLocale.cases.caseFault.faultCloseType
		    none : '没有',//EwayLocale.cases.caseFault.none
		    upgradeTimes: '升级次数',//EwayLocale.cases.caseFault.upgradeTimes
		    message: '短信',//EwayLocale.cases.caseFault.message
		    checkDetails: '查看详情',//EwayLocale.cases.caseFault.checkDetails
		    bankPer: '银行联系人',//EwayLocale.cases.caseFault.bankPer
		    serPer: '供应商联系人',//EwayLocale.cases.caseFault.serPer
		    createTime: '创建时间',//EwayLocale.cases.caseFault.createTime
		    informContent: '通知内容',//EwayLocale.cases.caseFault.informContent
		    messageContentDetail: '短信内容详情',//EwayLocale.cases.caseFault.messageContentDetail
		    informWay: '通知方式',//EwayLocale.cases.caseFault.informWay
		    mail:'邮件',//EwayLocale.cases.caseFault.mail
		    messageAndMail:'短信和邮件',//EwayLocale.cases.caseFault.messageAndMail
		    informMobile: '通知手机号',//EwayLocale.cases.caseFault.informMobile
		    notifyTimes: '通知次数',//EwayLocale.cases.caseFault.notifyTimes
		    notifyRepeatTimes: '重复通知次数',//EwayLocale.cases.caseFault.notifyRepeatTimes
		    sendTimes: '发送次数',//EwayLocale.cases.caseFault.sendTimes
		    sendInterval: '发送时间间隔',//EwayLocale.cases.caseFault.sendInterval
		    sendTime: '发送时间',//EwayLocale.cases.caseFault.sendTime
		    faultSearch:'故障查询',//EwayLocale.cases.caseFault.faultSearch
		    none : '不发送'//EwayLocale.cases.caseFault.none

		},
		caseNotify:{
			fault:'故障',//EwayLocale.cases.caseNotify.fault
			faultDetails:'故障详情',//EwayLocale.cases.caseNotify.faultDetails
			faultlastTime: '故障持续时长(单位:小时)',//EwayLocale.cases.caseNotify.faultlastTime
			checkFailure:'查看失败！',//EwayLocale.cases.caseNotify.checkFailure
			innerFault:'内部错误',//EwayLocale.cases.caseNotify.innerFault
			messageCheck:'短信查询'//EwayLocale.cases.caseNotify.messageCheck
		},
		faultClassify:{
			faultClassifyName: '故障分类名称',//EwayLocale.cases.faultClassify.faultClassifyName
			faultresponsorType: '故障责任人类型',//EwayLocale.cases.faultClassify.faultresponsorType
			maintain:'维护员',//EwayLocale.cases.faultClassify.maintain
			manageAndMaintain:'管机员和维护员 ',//EwayLocale.cases.faultClassify.manageAndMaintain
			upGradeTimes: '最高升级次数',//EwayLocale.cases.faultClassify.upGradeTimes
			faultInformWay:'故障通知方式',//EwayLocale.cases.faultClassify.faultInformWay
			faultCloseInterval:'故障规定关闭时间间隔（单位:小时）',//EwayLocale.cases.faultClassify.faultCloseInterval
			faultTypeConfiguration: '故障类型配置',//EwayLocale.cases.faultClassify.faultTypeConfiguration
			updateFaultTypeConfiguration: '更改故障类型配置',//EwayLocale.cases.faultClassify.updateFaultTypeConfiguration
			number:'由数字‘0-9’,‘.’组成',//EwayLocale.cases.faultClassify.number
			informNumber:'通知次数不能为0,由数字‘0-9’组成,1-5位'//EwayLocale.cases.faultClassify.informNumber
		},
		notifyMould:{
			noticeType:'通知类型',//EwayLocale.cases.notifyMould.noticeType
			createNotice:'创建通知',//EwayLocale.cases.notifyMould.createNotice
			upgradeNotice:'升级通知',//EwayLocale.cases.notifyMould.upgradeNotice
			closeNotice:'关闭通知',//EwayLocale.cases.notifyMould.closeNotice
			noticeValue: '通知参数',//EwayLocale.cases.notifyMould.noticeValue
			messageContentConfiguration:'短信内容配置',//EwayLocale.cases.notifyMould.messageContentConfiguration
			updateMessageContentConfiguration: '更改短信内容配置',//EwayLocale.cases.notifyMould.updateMessageContentConfiguration
			necessaryOption: '此项为必选项',//EwayLocale.cases.notifyMould.necessaryOption
			faultType:'故障类型',//EwayLocale.cases.notifyMould.faultType
			applyStatus:'应用状态'//EwayLocale.cases.notifyMould.applyStatus
		},
		vendorCode:{
			exportProviderInfo: '导入厂商故障信息',//EwayLocale.cases.vendorCode.exportProviderInfo
			provider:'厂商',//EwayLocale.cases.vendorCode.provider
			exportFile: '导入文件',//EwayLocale.cases.vendorCode.exportFile
			deleteFaultInfo:'删除厂商故障信息',//EwayLocale.cases.vendorCode.deleteFaultInfo
			templateLoad:'模板下载',//EwayLocale.cases.vendorCode.templateLoad
			massRemove: '批量删除',//EwayLocale.cases.vendorCode.massRemove
			providerDescription:'厂商故障描述',//EwayLocale.cases.vendorCode.providerDescription
			solveProject: '解决方案',//EwayLocale.cases.vendorCode.solveProject
			providerFaultInfo:'厂商故障信息管理',//EwayLocale.cases.vendorCode.providerFaultInfo
			formwork:'fault_message_ch.xls'//EwayLocale.cases.vendorCode.formwork
		}
	}

});
