Ext.apply(EwayLocale,{
	//**********************************************************/
	report:{
		baseReport:{
			date:'加钞日期',//EwayLocale.report.baseReport.date
			amt:'加钞金额',//EwayLocale.report.baseReport.amt
			boxId:'钞箱ID',//EwayLocale.report.baseReport.boxId
			boxCurrency:'币种',//EwayLocale.report.baseReport.boxCurrency
			boxInitAmt:'初始金额',//EwayLocale.report.baseReport.boxInitAmt
			lastAmt:'剩余金额',//EwayLocale.report.baseReport.lastAmt
			cashAddRep:'加钞情况报表',//EwayLocale.report.baseReport.cashAddRep
			boxBalanceRep:'钞箱余额报表',//EwayLocale.report.baseReport.boxBalanceRep
			sysConfRep:'系统硬件配置报表',//EwayLocale.report.baseReport.sysConfRep
			devDetailRep:'设备明细报表',//EwayLocale.report.baseReport.devDetailRep
			devBrandRep:'设备品牌统计报表',//EwayLocale.report.baseReport.devBrandRep
			devRunInfoRep:'设备运行情况报表',//EwayLocale.report.baseReport.devRunInfoRep
			eatCardRep:'吞卡统计报表',//EwayLocale.report.baseReport.eatCardRep
			eatCardDetailRep:'吞卡明细报表',//EwayLocale.report.baseReport.eatCardDetailRep
			clearDate:'清机日期',//EwayLocale.report.baseReport.clearDate
			clearTable:'清机情况报表',//EwayLocale.report.baseReport.clearTable
			dependDev:'按设备',//EwayLocale.report.baseReport.dependDev
			tradeRep:'交易统计报表',//EwayLocale.report.baseReport.tradeRep
			tradeResultRep:'交易结果统计报表',//EwayLocale.report.baseReport.tradeResultRep
			tradeDaysCountRep:'日均交易笔数趋势图',//EwayLocale.report.baseReport.tradeDaysCountRep
			tradeHoursCountRep:'一日内时均交易笔数趋势图',//EwayLocale.report.baseReport.tradeHoursCountRep
			tradeCount:'交易笔数',//EwayLocale.report.baseReport.tradeCount
		},
		openrate:{
			device:{
				statisticsMethod:'统计方式',//EwayLocale.report.openrate.device.statisticsMethod
				statistics:'统计',//EwayLocale.report.openrate.device.statistics
				importStat:'导出',//EwayLocale.report.openrate.device.importStat
				statDate:'统计日期',//EwayLocale.report.openrate.device.statDate
				openTimes:'设备应工作时长',//EwayLocale.report.openrate.device.openTimes
				healthyTimeReal:'正常状态时长',//EwayLocale.report.openrate.device.healthyTimeReal
				maintainTimeReal:'管机员维护时长',//EwayLocale.report.openrate.device.maintainTimeReal
				unknownTimeReal:'离线未知时长',//EwayLocale.report.openrate.device.unknownTimeReal
				faultTimeReal:'硬件故障停机时长',//EwayLocale.report.openrate.device.faultTimeReal
				atmpTimeReal:'ATMP故障时长',//EwayLocale.report.openrate.device.atmpTimeReal
				stopTimeReal:'其它暂停服务状态时长',//EwayLocale.report.openrate.device.stopTimeReal
				openRate:'实际工作开机率',//EwayLocale.report.openrate.device.openRate
				devOpenRate:'设备开机率',//EwayLocale.report.openrate.device.devOpenRate
				organizationName:'机构',//EwayLocale.report.openrate.device.organizationName
			},
			org:{
				orgOpenRate:'机构开机率',//EwayLocale.report.openrate.org.orgOpenRate
			},
			type:{
				terminalId:'型号',//EwayLocale.report.openrate.type.terminalId
				typeOpenRate:'型号开机率',//EwayLocale.report.openrate.type.typeOpenRate
			},
		},
		plan:{
			addPlan:'增加开机方案',//EwayLocale.report.plan.addPlan
			name:'方案名称',//EwayLocale.report.plan.name
			type:'方案类型',//EwayLocale.report.plan.type
			startDate:'有效开始时间',//EwayLocale.report.plan.startDate
			endDate:'有效结束时间',//EwayLocale.report.plan.endDate
			terminalId:'编号',//EwayLocale.report.plan.terminalId
			cashboxLimit:'钞箱报警金额(单位：张数)',//EwayLocale.report.plan.cashboxLimit
			perToDev:'人员<-->设备',//EwayLocale.report.plan.perToDev
			changePlan:'更改方案',//EwayLocale.report.plan.changePlan
			servicePlan:'开机方案',//EwayLocale.report.plan.servicePlan
		},

		serviceplan:{
			name:'方案名称',//EwayLocale.report.serviceplan.name
			machineQuantity:'设备数量',//EwayLocale.report.serviceplan.machineQuantity
			state:'状态',//EwayLocale.report.serviceplan.state
			openDate:'有效开机时间',//EwayLocale.report.serviceplan.openDate
			closeDate:'有效关机时间',//EwayLocale.report.serviceplan.closeDate
			createDateTime:'创建时间',//EwayLocale.report.serviceplan.createDateTime
			date:'日期',//EwayLocale.report.serviceplan.date
			week:'星期',//EwayLocale.report.serviceplan.week
			inportLinkedMachine:'导入关联设备',//EwayLocale.report.serviceplan.inportLinkedMachine
			selectFile:'选择文件',//EwayLocale.report.serviceplan.selectFile
			placeUploadingResource:'请上传资源',//EwayLocale.report.serviceplan.placeUploadingResource
			fileNotSupport:'导入的文件格式不支持,请按模板导入设备信息',//EwayLocale.report.serviceplan.fileNotSupport
			exportExplain:'导入说明',//EwayLocale.report.serviceplan.exportExplain
			thisIsTooLong:'请在设备导入模板中连续添加要下发的设备号,最多一次性导入2000条数据(约耗时5分钟),最少导入1条数据',//EwayLocale.report.serviceplan.thisIsTooLong
			thisHardToTranslate:'点击下载导入设备号模板',//EwayLocale.report.serviceplan.thisHardToTranslate
			placeSelect:'---请选择---',//EwayLocale.report.serviceplan.placeSelect
			planDevice:'方案<-->设备',//EwayLocale.report.serviceplan.planDevice
			timeEare:'输入时间段有误，请重新输入！',//EwayLocale.report.serviceplan.timeEare
			planOlonOne:'同方案只能设置开机或关机的一种',//EwayLocale.report.serviceplan.planOlonOne
			addSuccess:'添加成功',//EwayLocale.report.serviceplan.addSuccess
			addFail:'添加失败',//EwayLocale.report.serviceplan.addFail
			setTime:'请设置详细时间',//EwayLocale.report.serviceplan.setTime
			thisPlanStop:'(此方案已停用，不可应用！)',//EwayLocale.report.serviceplan.thisPlanStop
			placeRefresh:'条解除失败，请刷新后查看！',//EwayLocale.report.serviceplan.placeRefresh
			linking:'正在关联设备....',//EwayLocale.report.serviceplan.linking
			testingPlaceWaiting:'正在判断设备号是否符合要求，请耐心等待...',//EwayLocale.report.serviceplan.testingPlaceWaiting
			leastOne:'最少一次导入1条设备信息，请重新选择导入文件!',//EwayLocale.report.serviceplan.leastOne
			notMore:'最多一次导入2000条设备信息，请重新选择导入文件!',//EwayLocale.report.serviceplan.notMore
			checkFile:'请检查导入文件',//EwayLocale.report.serviceplan.checkFile
			fileNotAllowed:'文件不符合要求！',//EwayLocale.report.serviceplan.fileNotAllowed
			tipExportSuccess:'条数据,成功导入',//EwayLocale.report.serviceplan.tipExportSuccess
			tipLookUp:'条,点击查看导入详情!',//EwayLocale.report.serviceplan.tipLookUp
			tochenkDervice:'请选择您要更改的设备',//EwayLocale.report.serviceplan.tochenkDervice
			tochenckPeople:'请选择人员',//EwayLocale.report.serviceplan.tochenckPeople
			tipAddError:'条添加失败,请刷新后查看',//EwayLocale.report.serviceplan.tipAddError
			planIsHaved:'',
			linkSuccess:'关联成功',//EwayLocale.report.serviceplan.linkSuccess
			Mon:'一',//EwayLocale.report.serviceplan.Mon
			Tues:'二',//EwayLocale.report.serviceplan.Tues
			Wed:'三',//EwayLocale.report.serviceplan.Wed
			Thur:'四',//EwayLocale.report.serviceplan.Thur
			Fri:'五',//EwayLocale.report.serviceplan.Fri
			Sat:'六',//EwayLocale.report.serviceplan.Sat
			Sun:'日',//EwayLocale.report.serviceplan.Sun
			useSuccess:'正常启用',//EwayLocale.report.serviceplan.useSuccess
			notSuccess:'未启用',//EwayLocale.report.serviceplan.notSuccess
			lastOneGroup:'请在组内至少选中一项',//EwayLocale.report.serviceplan.lastOneGroup
			lanDetailWeek:'星期方案详细列表',//EwayLocale.report.serviceplan.lanDetailWeek
			planDetailDay:'日期方案详细列表',//EwayLocale.report.serviceplan.planDetailDay
			selectPlan:'选择开机方案',//EwayLocale.report.serviceplan.selectPlan
			weekSelect:'通知方式'//EwayLocale.report.serviceplan.weekSelect
		}
	}
});
