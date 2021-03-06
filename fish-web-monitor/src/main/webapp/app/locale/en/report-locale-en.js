Ext.apply(EwayLocale,{
	
	report:{
		pivot:{
			common:{
				totalSum:'Grand Total',//EwayLocale.report.pivot.common.totalSum
				sumSub:'(Total)'//EwayLocale.report.pivot.common.sumSub
			}
		},
		baseReport:{
			date:'Cash Date',//EwayLocale.report.baseReport.date
			amt:'Cash Amount',//EwayLocale.report.baseReport.amt
			cashAddRep:'CashAdd Detail Report',//EwayLocale.report.baseReport.cashAddRep
			boxBalanceRep:'Cashbox Left Report',//EwayLocale.report.baseReport.boxBalanceRep
			sysConfRep:'Hardware Report',//EwayLocale.report.baseReport.sysConfRep
			devDetailRep:'Device Detail Report',//EwayLocale.report.baseReport.devDetailRep
			devBrandRep:'Device Brand Statistics',//EwayLocale.report.baseReport.devBrandRep
			devRunInfoRep:'Device Run Report',//EwayLocale.report.baseReport.devRunInfoRep
			eatCardRep:'Retained Card Statistics',//EwayLocale.report.baseReport.eatCardRep
			eatCardDetailRep:'Retain Card Detail Report',//EwayLocale.report.baseReport.eatCardDetailRep
			clearDate:'Clear Date',//EwayLocale.report.baseReport.clearDate
			clearTable:'Clear Report',//EwayLocale.report.baseReport.clearTable
			dependDev:'Depend On Dev',//EwayLocale.report.baseReport.dependDev
			tradeRep:'Transaction Statistics',//EwayLocale.report.baseReport.tradeRep
			tradeResultRep:'Transaction Result Statistics',//EwayLocale.report.baseReport.tradeResultRep
			tradeDaysCountRep:'Daily Transaction Statistics',//EwayLocale.report.baseReport.tradeDaysCountRep
			tradeHoursCountRep:'Hourly Transaction Statistics',//EwayLocale.report.baseReport.tradeHoursCountRep
			tradeCount:'Transaction Count',//EwayLocale.report.baseReport.tradeCount
			angle : 'Statistical Dimension',//EwayLocale.report.baseReport.angle
			device : 'Device',//EwayLocale.report.baseReport.device
			vendor : 'Device Brand',//EwayLocale.report.baseReport.vendor
			devType : 'Device Type',//EwayLocale.report.baseReport.devType
			mod : 'Device Modules',//EwayLocale.report.baseReport.mod
			rank : 'Top',//EwayLocale.report.baseReport.rank
			caseStatisticsRep:'Fault Statistics Chart',//EwayLocale.report.baseReport.caseStatisticsRep
			statisticsDays : 'Time Scope',//EwayLocale.report.baseReport.statisticsDays
			caseTrendReportTitle: 'Fault Trend Chart'//EwayLocale.report.baseReport.caseTrendReportTitle
		},
		faultRateReport:{
			viewTitle : 'Fault Rate Report',//EwayLocale.report.faultRateReport.viewTitle
			dateMonth : 'Month',//EwayLocale.report.faultRateReport.dateMonth
			devType : 'Device Type',//EwayLocale.report.faultRateReport.devType
			vendorName : 'Device Vendor',//EwayLocale.report.faultRateReport.vendorName
			moduleName : 'Device Module',//EwayLocale.report.faultRateReport.moduleName
			tradeCount : 'Transaction Quantity',//EwayLocale.report.faultRateReport.tradeCount
			faultCount : ' Fault Quantity',//EwayLocale.report.faultRateReport.faultCount
			rate: 'Fault Rate(Qercentage)',//EwayLocale.report.faultRateReport.rate
			details : 'Details',//EwayLocale.report.faultRateReport.details
			back : 'Return Back',//EwayLocale.report.faultRateReport.back
			preVendor : 'The Previous Brand',//EwayLocale.report.faultRateReport.preVendor
			nextVendor : 'The Next Brand',//EwayLocale.report.faultRateReport.nextVendor
			preType : 'The Previous Type',//EwayLocale.report.faultRateReport.preType
			nextType : 'The Next Type',//EwayLocale.report.faultRateReport.nextType
			vendorDetail : 'Brand Under All the Type Failure Rate Situation ',//EwayLocale.report.faultRateReport.vendorDetail
			typeDetail : 'Type Under All the Module Failure Rate Situation',//EwayLocale.report.faultRateReport.typeDetail
		},
		openrate:{
			device:{
				statisticsMethod:'Statistics Way',//EwayLocale.report.openrate.device.statisticsMethod
				statistics:'Statistics',//EwayLocale.report.openrate.device.statistics
				importStat:'Export',//EwayLocale.report.openrate.device.importStat
				statDate:'Statistics Date',//EwayLocale.report.openrate.device.statDate
				openTimes:'Planning Time',//EwayLocale.report.openrate.device.openTimes
				healthyTimeReal:'Healthy',//EwayLocale.report.openrate.device.healthyTimeReal
				maintainTimeReal:'Maintenance',//EwayLocale.report.openrate.device.maintainTimeReal
				unknownTimeReal:'Off-line',//EwayLocale.report.openrate.device.unknownTimeReal
				faultTimeReal:'Hardware-error',//EwayLocale.report.openrate.device.faultTimeReal
				atmpTimeReal:'ATMP Error',//EwayLocale.report.openrate.device.atmpTimeReal
				stopTimeReal:'Out Of Service',//EwayLocale.report.openrate.device.stopTimeReal
				openRate:'OpenRate',//EwayLocale.report.openrate.device.openRate
				devOpenRate:'Device Open Rate',//EwayLocale.report.openrate.device.devOpenRate
				organizationName:'Bank',//EwayLocale.report.openrate.device.organizationName
				inBank:'In Bank',//EwayLocale.report.openrate.device.inBank
				outBank:'Out Of Bank',//EwayLocale.report.openrate.device.outBank
				allBank:'Both In Or Out Of Bank',//EwayLocale.report.openrate.device.allBank
				lt:'<',//EwayLocale.report.openrate.device.lt
				gt:'>',//EwayLocale.report.openrate.device.gt
				openRate:'OpenRate',//EwayLocale.report.openrate.device.openRate
				regex:'please input 0-100.00',//EwayLocale.report.openrate.device.regex
				openRateAvg:'AvgOpenRate',//EwayLocale.report.openrate.device.openRateAvg
				orgAvg:'AvgOpnRateByOrg',//EwayLocale.report.openrate.device.orgAvg
			},
			moduleFaultRateReport:{
				Title:'Module Fault Rate Report',//EwayLocale.report.moduleFaultRateReport.Title
			},
			org:{
				orgOpenRate:'Banks Open Rate'//EwayLocale.report.openrate.org.orgOpenRate
			},
			type:{
				terminalId:'Type',//EwayLocale.report.openrate.type.terminalId
				typeOpenRate:'Device Type Open Rate'//EwayLocale.report.openrate.type.typeOpenRate
			}
		}
	}
});
