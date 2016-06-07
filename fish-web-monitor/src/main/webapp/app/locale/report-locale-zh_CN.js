Ext.apply(EwayLocale,{
	
	report:{
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
			viewTitle : '故障率報表',//EwayLocale.report.faultRateReport.viewTitle
			dateMonth : '月份',//EwayLocale.report.faultRateReport.dateMonth
			devType : '型号',//EwayLocale.report.faultRateReport.devType
			vendorName : '品牌',//EwayLocale.report.faultRateReport.vendorName
			moduleName : '模块',//EwayLocale.report.faultRateReport.moduleName
			tradeCount : '交易数',//EwayLocale.report.faultRateReport.tradeCount
			faultCount : '故障数',//EwayLocale.report.faultRateReport.faultCount
			rate　: '故障率(百分比)',//EwayLocale.report.faultRateReport.rate
			vendorRate : '不同品牌交易故障率',//EwayLocale.report.faultRateReport.vendorRate
			typeRate : '不同型号交易故障率',//EwayLocale.report.faultRateReport.typeRate
            moduleRate : '不同模块交易故障率',//EwayLocale.report.faultRateReport.moduleRate
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
				organizationName:'机构'//EwayLocale.report.openrate.device.organizationName
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
