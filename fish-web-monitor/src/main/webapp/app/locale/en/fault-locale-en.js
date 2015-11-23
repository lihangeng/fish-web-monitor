Ext.apply(EwayLocale,{
	//**********************************************************/

	cases:{
		confirm:'Confirm',//确认
		cancel:'Cancel',//取消
		concern:'Attention please',//请关注
		SRCBView:'',//上海农商行新监控发送
		nowExportFile:'File Importing',//正在导入文件
		exportFaultInfo:'Import fault code successful',//导入厂商故障信息成功.
		caseFault:{
			faultRelevantInfo:'SMS about fault',//故障相关短信
			faultModule:'Fault Module ',
			cardReaderModule:'Card reader',//读卡器模块
			depoistModule:'Deposit module',//存款模块
			drawModule:'Draw module',//取款模块
			rprModule:'Voucher printer',//凭条打印模块
			jprModule:'Log printer',//日志打印模块
			pinModule:'Pin keyboard',//密码键盘模块
			textTerminalUnit:'Text terminal',//文本终端单元
			sensoModule:'Sensor',//传感器模块
			faultClassify: 'Fault Catalog',//故障分类
			faultCode : 'Fault Code',//故障码
			providerFaultCode: 'Fault Code Manufacturer',//厂商故障码
			faultStartTime : 'Open Time',//故障开始时间
			faultCloseTime : 'Close Time',//故障关闭时间
			faultContinueTime : 'Time Last',//持续时长
		    faultState : 'Status',//故障状态
		    status:{
		    	open:'Opening',//未关闭
		    	close:'Closed'//已关闭
		    },
		    closeType:{
		    	force : 'closeByForce',
		    	normal : 'closeNormal'
		    },
		    closeByForce : 'click to close fault by hand',
		    faultCloseType : 'Fault Close Type',
		    none : 'none',

		    upgradeTimes: 'Upgrade Times',//升级次数
		    message: 'SMS',//短信
		    checkDetails: 'Detail',//查看详情
		    bankPer: 'Bank Contact',//银行联系人
		    serPer: 'Suppliers Contact',//供应商联系人
		    createTime: 'Create Time',//创建时间
		    informContent: 'Notify Content',//通知内容
		    messageContentDetail: 'SMS Content',//短信内容详情
		    informWay: 'Notify Way',//通知方式
		    mail:'Email',//邮件
		    messageAndMail:'SMS And Email',//短信和邮件
		    informMobile: 'Notify Mobile',//通知手机号
		    notifyTimes: 'Notify Times',//通知次数
		    notifyRepeatTimes: 'Repeat Notify Times',//重复通知次数
		    sendTimes: 'Send Times',//发送次数
		    sendInterval: 'Intervals Between Each Send',//发送时间间隔
		    sendTime: 'Send Time',//发送时间
		    faultSearch:'Fault Search',//故障查询
		    none : 'Do Not Send'//不发送

		},
		caseNotify:{
			fault:'Fault',//故障
			faultDetails:'Detail',//故障详情
			faultlastTime: 'Fault last(unit:hour)',//故障持续时长(单位:小时)
			checkFailure:'View failed!',//查看失败！
			innerFault:'System error',//EwayLocale.cases.caseNotify.innerFault 内部错误
			messageCheck:'Query SMS'//短信查询
		},
		faultClassify:{
			faultClassifyName: 'Fault type name',//故障分类名称
			faultresponsorType: 'Fault personliable type', //故障责任人类型
			maintain:'Maintainer',//维护员
			manageAndMaintain:'Manager&Maintainer',//管机员和维护员
			upGradeTimes: 'Max upgrade times',//最高升级次数
			faultInformWay:'Notify way',//故障通知方式
			faultCloseInterval:'Intervals between close(unit:hour)',//故障规定关闭时间间隔（单位:小时）
			faultTypeConfiguration: 'Fault type setting',//故障类型配置
			updateFaultTypeConfiguration: 'Modify fault type',//更改故障类型配置
			number:'Just for numbers ‘0-9’',//由数字‘0-9’,‘.’组成
			informNumber:'Can not be 0,1-5 numbers'//通知次数不能为0,由数字‘0-9’组成,1-5位
		},
		notifyMould:{
			noticeType:'Type',//通知类型
			createNotice:'Create',//创建通知
			upgradeNotice:'Upgrade',//升级通知
			closeNotice:'Close',//关闭通知
			noticeValue: 'Parameters',//通知参数
			messageContentConfiguration:'SMS content',//短信内容配置
			updateMessageContentConfiguration: 'Modify SMS content',//更改短信内容配置
			necessaryOption: 'This option is necessary',//此项为必选项
			faultType:'Fault type',//故障类型
			applyStatus:'App status'//应用状态
		},
		vendorCode:{
			exportProviderInfo: 'Import manufacturer faultCode',//导入厂商故障信息
			provider:'Manufacturer',
			exportFile: 'Import file',//导入文件
			deleteFaultInfo:'Delete manufacturer faultCode',//删除厂商故障信息
			templateLoad:'Example download',//模板下载
			massRemove: 'Batch delete',//批量删除
			providerDescription:'DaultCode describe',//厂商故障描述
			solveProject: 'Solution',//解决方案
			providerFaultInfo:'Manufacturer faultCode',//厂商故障信息管理
			formwork:'fault_message_en.xls'//模板下载
		}
	}
});
