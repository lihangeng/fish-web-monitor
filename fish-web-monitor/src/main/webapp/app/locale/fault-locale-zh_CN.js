Ext.apply(EwayLocale,{
	//**********************************************************/

	cases:{
		confirm:'确认',//EwayLocale.cases.confirm
		cancel:'取消',//EwayLocale.cases.cancel
		concern:'请关注',
		SRCBView:'新监控发送',
		nowExportFile:'正在导入文件',
		exportFaultInfo:'导入厂商故障信息成功.',
		caseFault:{
			faultRelevantInfo:'故障相关短信',
			faultModule:'故障模块',
			cardReaderModule:'读卡器模块',
			depoistModule:'存款模块',
			drawModule:'取款模块',
			rprModule:'凭条打印模块',
			jprModule:'日志打印模块',
			pinModule:'密码键盘模块',
			textTerminalUnit:'文本终端单元',
			sensoModule:'传感器模块',
			faultClassify: '故障分类',
			faultCode : '故障码',
			providerFaultCode: '厂商故障码',
			faultStartTime : '故障开始时间',
			faultCloseTime : '故障关闭时间',
			faultContinueTime : '持续时长',
		    faultState : '故障状态',
		    status:{
		    	open:'未关闭',
		    	close:'已关闭'
		    },
		    closeType:{
		    	force : '手工关闭',
		    	normal : '正常关闭'
		    },
		    closeByForce : '点击手工关闭故障',
		    faultCloseType : '故障关闭方式',
		    none : '没有',
		    upgradeTimes: '升级次数',
		    message: '短信',
		    checkDetails: '查看详情',
		    bankPer: '银行联系人',
		    serPer: '供应商联系人',
		    createTime: '创建时间',
		    informContent: '通知内容',
		    messageContentDetail: '短信内容详情',
		    informWay: '通知方式',//EwayLocale.cases.informWay
		    mail:'邮件',
		    messageAndMail:'短信和邮件',
		    informMobile: '通知手机号',
		    notifyTimes: '通知次数',
		    notifyRepeatTimes: '重复通知次数',
		    sendTimes: '发送次数',
		    sendInterval: '发送时间间隔',
		    sendTime: '发送时间',
		    faultSearch:'故障查询',
		    none : '不发送'

		},
		caseNotify:{
			fault:'故障',
			faultDetails:'故障详情',
			faultlastTime: '故障持续时长(单位:小时)',
			checkFailure:'查看失败！',
			innerFault:'内部错误',//EwayLocale.cases.caseNotify.innerFault
			messageCheck:'短信查询'
		},
		faultClassify:{
			faultClassifyName: '故障分类名称',
			faultresponsorType: '故障责任人类型',
			maintain:'维护员',
			manageAndMaintain:'管机员和维护员 ',
			upGradeTimes: '最高升级次数',
			faultInformWay:'故障通知方式',
			faultCloseInterval:'故障规定关闭时间间隔（单位:小时）',
			faultTypeConfiguration: '故障类型配置',
			updateFaultTypeConfiguration: '更改故障类型配置',
			number:'由数字‘0-9’,‘.’组成',
			informNumber:'通知次数不能为0,由数字‘0-9’组成,1-5位'
		},
		notifyMould:{
			noticeType:'通知类型',
			createNotice:'创建通知',
			upgradeNotice:'升级通知',
			closeNotice:'关闭通知',
			noticeValue: '通知参数',
			messageContentConfiguration:'短信内容配置',
			updateMessageContentConfiguration: '更改短信内容配置',
			necessaryOption: '此项为必选项',
			faultType:'故障类型',
			applyStatus:'应用状态'
		},
		vendorCode:{
			exportProviderInfo: '导入厂商故障信息',
			provider:'厂商',
			exportFile: '导入文件',
			deleteFaultInfo:'删除厂商故障信息',
			templateLoad:'模板下载',
			massRemove: '批量删除',
			providerDescription:'厂商故障描述',
			solveProject: '解决方案',
			providerFaultInfo:'厂商故障信息管理'
		}
	}

});
