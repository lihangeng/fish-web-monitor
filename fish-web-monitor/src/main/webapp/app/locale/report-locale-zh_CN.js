Ext.apply(EwayLocale,{
	
	report:{
		run:{
			viewTitle:'运行分析报告',//EwayLocale.report.run.viewTitle
			createDate:'创建时间',//EwayLocale.report.run.createDate
			fileName:'文件名称',//EwayLocale.report.run.fileName
			fileSize:'文件大小',//EwayLocale.report.run.fileSize
			reportType:{
				title:'报告类型',//EwayLocale.report.run.reportType.title
				weekReport:'周报',//EwayLocale.report.run.reportType.weekReport
				monthReport:'月报'//EwayLocale.report.run.reportType.monthReport
			}
		},
		pivot:{
			common:{
				totalSum:'共计',//EwayLocale.report.pivot.common.totalSum
				sumSub:'(小计)'//EwayLocale.report.pivot.common.sumSub
			}
		},
		baseReport:{
			date:'加钞日期',//EwayLocale.report.baseReport.date
			amt:'加钞金额',//EwayLocale.report.baseReport.amt
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
			tradeDaysCountRep:'日均交易笔数统计',//EwayLocale.report.baseReport.tradeDaysCountRep
			tradeHoursCountRep:'时均交易笔数统计',//EwayLocale.report.baseReport.tradeHoursCountRep
			tradeCount:'交易笔数',//EwayLocale.report.baseReport.tradeCount
			caseStatisticsRep:'故障统计图',//EwayLocale.report.baseReport.caseStatisticsRep
			angle : '统计维度',//EwayLocale.report.baseReport.angle
			device : '设备',//EwayLocale.report.baseReport.device
			vendor : '设备品牌',//EwayLocale.report.baseReport.vendor
			devType : '设备型号',//EwayLocale.report.baseReport.devType
			mod : '设备模块',//EwayLocale.report.baseReport.mod
			rank : 'Top',//EwayLocale.report.baseReport.rank
			statisticsDays : '统计时间段',//EwayLocale.report.baseReport.statisticsDays
			caseTrendReportTitle: '故障趋势图'//EwayLocale.report.baseReport.caseTrendReportTitle
		},
		faultRateReport:{
			viewTitle : '故障率报表',//EwayLocale.report.faultRateReport.viewTitle
			dateMonth : '月份',//EwayLocale.report.faultRateReport.dateMonth
			devType : '型号',//EwayLocale.report.faultRateReport.devType
			vendorName : '品牌',//EwayLocale.report.faultRateReport.vendorName
			moduleName : '模块',//EwayLocale.report.faultRateReport.moduleName
			tradeCount : '交易数',//EwayLocale.report.faultRateReport.tradeCount
			faultCount : '故障数',//EwayLocale.report.faultRateReport.faultCount
			rate: '故障率(百分比)',//EwayLocale.report.faultRateReport.rate
			details : '详情',//EwayLocale.report.faultRateReport.details
			back : '返回',//EwayLocale.report.faultRateReport.back
			preVendor : '上一个品牌',//EwayLocale.report.faultRateReport.preVendor
			nextVendor : '下一个品牌',//EwayLocale.report.faultRateReport.nextVendor
			preType : '上一个型号',//EwayLocale.report.faultRateReport.preType
			nextType : '下一个型号',//EwayLocale.report.faultRateReport.nextType
			vendorDetail : '品牌下的所有型号故障率情况',//EwayLocale.report.faultRateReport.vendorDetail
			typeDetail : '型号下所有模块故障率情况',//EwayLocale.report.faultRateReport.typeDetail
		},
		moduleFaultRateReport:{
			Title:'模块故障率报表'//EwayLocale.report.moduleFaultRateReport.Title
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
				inBank:'在行',//EwayLocale.report.openrate.device.inBank
				outBank:'离行',//EwayLocale.report.openrate.device.outBank
				allBank:'所有',//EwayLocale.report.openrate.device.allBank
				lt:'低于',//EwayLocale.report.openrate.device.lt
				gt:'高于',//EwayLocale.report.openrate.device.gt
				openRate:'开机率',//EwayLocale.report.openrate.device.openRate
				regex:'请输入0-100.00的数字',//EwayLocale.report.openrate.device.regex
				openRateAvg:'平均值',//EwayLocale.report.openrate.device.openRateAvg
				orgAvg:'机构平均值',//EwayLocale.report.openrate.device.orgAvg
			},
			org:{
				orgOpenRate:'机构开机率'//EwayLocale.report.openrate.org.orgOpenRate
			},
			type:{
				terminalId:'型号',//EwayLocale.report.openrate.type.terminalId
				typeOpenRate:'型号开机率'//EwayLocale.report.openrate.type.typeOpenRate
			}
		}
	}
});
