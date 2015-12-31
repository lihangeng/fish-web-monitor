Ext.apply(EwayLocale,{
	//**********************************************************/
	report:{
		baseReport:{
			date:'Cash date',//EwayLocale.report.baseReport.date 加钞日期
			amt:'Cash amount',//EwayLocale.report.baseReport.amt 加钞金额
			boxId:'Cashbox ID',//EwayLocale.report.baseReport.boxId 钞箱ID
			boxCurrency:'Currency',//EwayLocale.report.baseReport.boxCurrency 币种
			boxInitAmt:'InitAmt',//EwayLocale.report.baseReport.boxInitAmt 初始金额
			lastAmt:'LeftAmt',//EwayLocale.report.baseReport.lastAmt
			cashAddRep:'CashAdd detail report',//EwayLocale.report.baseReport.cashAddRep 加钞情况报表
			boxBalanceRep:'Cashbox left report',//EwayLocale.report.baseReport.boxBalanceRep 钞箱余额报表
			sysConfRep:'Hardware report',//EwayLocale.report.baseReport.sysConfRep 系统硬件配置报表
			devDetailRep:'Device detail report',//EwayLocale.report.baseReport.devDetailRep 设备明细报表
			devBrandRep:'Device brand report',//EwayLocale.report.baseReport.devBrandRep 设备品牌统计报表
			devRunInfoRep:'Device run report',//EwayLocale.report.baseReport.devRunInfoRep 设备运行情况报表
			eatCardRep:'Retain card report',//EwayLocale.report.baseReport.eatCardRep 吞卡统计报表
			eatCardDetailRep:'Retain card detail report',//EwayLocale.report.baseReport.eatCardDetailRep 吞卡明细报表
			clearDate:'Clear date',//EwayLocale.report.baseReport.clearDate 清机日期
			clearTable:'Clear report',//EwayLocale.report.baseReport.clearTable 清机情况报表
			dependDev:'Depend on Dev',//EwayLocale.report.baseReport.dependDev 按设备
			tradeRep:'Transation report',//EwayLocale.report.baseReport.tradeRep 交易统计报表
			tradeResultRep:'Transation result report',//EwayLocale.report.baseReport.tradeResultRep 交易结果统计报表
			tradeDaysCountRep:'Transaction day count report',//EwayLocale.report.baseReport.tradeDaysCountRep
			tradeHoursCountRep:'Transaction hour count report',//EwayLocale.report.baseReport.tradeHoursCountRep
			tradeCount:'Transaction count',//EwayLocale.report.baseReport.tradeCount
			angle : 'Statistical dimension',//EwayLocale.report.baseReport.angle
			device : 'Device',//EwayLocale.report.baseReport.device
			vendor : 'Device Brand',//EwayLocale.report.baseReport.vendor
			devType : 'Device Type',//EwayLocale.report.baseReport.devType
			mod : 'Device Modules',//EwayLocale.report.baseReport.mod
			rank : 'Top',//EwayLocale.report.baseReport.rank
			caseStatisticsRep:'Fault Statistic Chart',//EwayLocale.report.baseReport.caseStatisticsRep
			statisticsDays : 'Time Scope',//EwayLocale.report.baseReport.statisticsDays
			caseTrendReportTitle: 'Fault Trend Chart'//EwayLocale.report.baseReport.caseTrendReportTitle
		},
		openrate:{
			device:{
				statisticsMethod:'Statistics way',//EwayLocale.report.openrate.device.statisticsMethod 统计方式
				statistics:'Statistics',//EwayLocale.report.openrate.device.statistics 统计
				importStat:'Export',//EwayLocale.report.openrate.device.importStat 导出
				statDate:'Statistics date',//EwayLocale.report.openrate.device.statDate 统计日期
				openTimes:'Time device should run',//EwayLocale.report.openrate.device.openTimes 设备应工作时长
				healthyTimeReal:'Time device run normally',//EwayLocale.report.openrate.device.healthyTimeReal 正常状态时长
				maintainTimeReal:'Time device manage-mode',//EwayLocale.report.openrate.device.maintainTimeReal 管机员维护时长
				unknownTimeReal:'Time device off-line',//EwayLocale.report.openrate.device.unknownTimeReal 离线未知时长
				faultTimeReal:'Time device hardware-error',//EwayLocale.report.openrate.device.faultTimeReal 硬件故障停机时长
				atmpTimeReal:'Time ATMP error',//EwayLocale.report.openrate.device.atmpTimeReal ATMP故障时长
				stopTimeReal:'Time other reason stop-service',//EwayLocale.report.openrate.device.stopTimeReal 其它暂停服务状态时长
				openRate:'OpenRate',//EwayLocale.report.openrate.device.openRate 实际工作开机率
				devOpenRate:'OpenRate',//EwayLocale.report.openrate.device.devOpenRate 设备开机率
				organizationName:'Org'//EwayLocale.report.openrate.device.organizationName 机构
			},
			org:{
				orgOpenRate:'OpenRate-org'//EwayLocale.report.openrate.org.orgOpenRate 机构开机率
			},
			type:{
				terminalId:'Type',//EwayLocale.report.openrate.type.terminalId 型号
				typeOpenRate:'OpenRate of device type'//EwayLocale.report.openrate.type.typeOpenRate 型号开机率
			}
		}
	}
});
