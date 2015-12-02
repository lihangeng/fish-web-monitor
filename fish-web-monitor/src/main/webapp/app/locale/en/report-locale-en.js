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
		},
		plan:{
			addPlan:'Add Plan',//EwayLocale.report.plan.addPlan 增加方案
			name:'Name',//EwayLocale.report.plan.name 名称
			startDate:'Start time',//EwayLocale.report.plan.startDate 有效开始时间
			endDate:'End time',//EwayLocale.report.plan.endDate 有效结束时间
			terminalId:'Code',//EwayLocale.report.plan.terminalId 编号
			cashboxLimit:'Alarm cashbox(unit:piece)',//EwayLocale.report.plan.cashboxLimit 钞箱报警金额(单位：张数)
			perToDev:'Person<-->Device',//EwayLocale.report.plan.perToDev 人员<-->设备
			changePlan:'Modify plan',//EwayLocale.report.plan.changePlan 更改方案
			servicePlan:'Start up plan'//EwayLocale.report.plan.servicePlan 开机方案
		},
		serviceplan:{
			name:'Programme Name',//EwayLocale.report.serviceplan.name方案名称
			machineQuantity:'Number of devices',//EwayLocale.report.serviceplan.machineQuantity设备数量
			state:'Status',//EwayLocale.report.serviceplan.state状态
			openDate:'Effective boot time',//EwayLocale.report.serviceplan.openDate有效开机时间
			closeDate:'Effective shutdown time',//EwayLocale.report.serviceplan.closeDate有效关机时间
			createDateTime:'Created',//EwayLocale.report.serviceplan.createDateTime创建时间
			date:'Date',//EwayLocale.report.serviceplan.date日期
			week:'Week',//EwayLocale.report.serviceplan.week星期
			inportLinkedMachine:'Import related equipment',//EwayLocale.report.serviceplan.inportLinkedMachine导入关联设备
			selectFile:'Select the file',//EwayLocale.report.serviceplan.selectFile选择文件
			placeUploadingResource:'Please upload resources',//EwayLocale.report.serviceplan.placeUploadingResource请上传资源
			fileNotSupport:'Import file format is not supported, according to the template import device information',//EwayLocale.report.serviceplan.fileNotSupport导入的文件格式不支持,请按模板导入设备信息
			exportExplain:'Import instructions',//EwayLocale.report.serviceplan.exportExplain导入说明
			thisIsTooLong:'Please add the device continuously import template device number to be issued, up to a one-time import 2000 data (takes about 5 minutes), a minimum import data',//EwayLocale.report.serviceplan.thisIsTooLong请在设备导入模板中连续添加要下发的设备号,最多一次性导入2000条数据(约耗时5分钟),最少导入1条数据
			thisHardToTranslate:'Click to download introducing device ID template',//EwayLocale.report.serviceplan.thisHardToTranslate点击下载导入设备号模板
			placeSelect:'---Please select---',//EwayLocale.report.serviceplan.placeSelect请选择
			planDevice:'Program <--> Device',//EwayLocale.report.serviceplan.planDevice方案<-->设备
			timeEare:'Enter the time is incorrect, please re-enter！',//EwayLocale.report.serviceplan.timeEare输入时间段有误，请重新输入
			planOlonOne:'The same program can only set a startup or shutdown',//EwayLocale.report.serviceplan.planOlonOne同方案只能设置开机或关机的一种
			addSuccess:'Added successfully',//EwayLocale.report.serviceplan.addSuccess添加成功
			addFail:'Add Failed',//EwayLocale.report.serviceplan.addFail添加失败
			setTime:'Please set the detailed time',//EwayLocale.report.serviceplan.setTime请设置详细时间
			thisPlanStop:'(This program is disabled, can not apply!)',//EwayLocale.report.serviceplan.thisPlanStop(此方案已停用，不可应用！)
			placeRefresh:'Article lift failed. Please refresh view！',//EwayLocale.report.serviceplan.placeRefresh条解除失败，请刷新后查看
			linking:'Being associated equipment....',//EwayLocale.report.serviceplan.linking正在关联设备
			testingPlaceWaiting:'Device number is judged to meet the requirements, please wait...',//EwayLocale.report.serviceplan.testingPlaceWaiting正在判断设备号是否符合要求，请耐心等待
			leastOne:'Importing a device at least once information, please re-select the import file!',//EwayLocale.report.serviceplan.leastOne最少一次导入1条设备信息，请重新选择导入文件
			notMore:'Up to 2000 the first import device information, please re-select the import file!',//EwayLocale.report.serviceplan.notMore最多一次导入2000条设备信息，请重新选择导入文件
			checkFile:'Please check the import file',//EwayLocale.report.serviceplan.checkFile请检查导入文件
			fileNotAllowed:'Documents do not meet requirements！',//EwayLocale.report.serviceplan.fileNotAllowed文件不符合要求
			tipExportSuccess:'Article data successfully imported',//EwayLocale.report.serviceplan.tipExportSuccess条数据,成功导入
			tipLookUp:'Bar, click View import details!',//EwayLocale.report.serviceplan.tipLookUp条,点击查看导入详情!
			tochenkDervice:'Please select the device you want to change',//EwayLocale.report.serviceplan.tochenkDervice请选择您要更改的设备
			tochenckPeople:'Please select personnel',//EwayLocale.report.serviceplan.tochenckPeople请选择人员
			tipAddError:'Article Adding failed. Please refresh view',//EwayLocale.report.serviceplan.tipAddError条添加失败,请刷新后查看
			planIsHaved:'',
			linkSuccess:'Associate success',//EwayLocale.report.serviceplan.linkSuccess关联成功
			Mon:'Mon',//EwayLocale.report.serviceplan.Mon一
			Tues:'Tues',//EwayLocale.report.serviceplan.Tues二
			Wed:'Wed',//EwayLocale.report.serviceplan.Wed三
			Thur:'Thur',//EwayLocale.report.serviceplan.Thur四
			Fri:'Fri',//EwayLocale.report.serviceplan.Fri五
			Sat:'Sat',//EwayLocale.report.serviceplan.Sat六
			Sun:'Sun',//EwayLocale.report.serviceplan.Sun日
			useSuccess:'Normal start',//EwayLocale.report.serviceplan.useSuccess正常启用
			notSuccess:'Not Enabled',//EwayLocale.report.serviceplan.notSuccess未启用
			lastOneGroup:'Please select at least one in the group',//EwayLocale.report.serviceplan.lastOneGroup请在组内至少选中一项
			lanDetailWeek:'Week program detailed list',//EwayLocale.report.serviceplan.lanDetailWeek星期方案详细列表
			planDetailDay:'Date program detailed list',//EwayLocale.report.serviceplan.planDetailDay日期方案详细列表
			selectPlan:'To select a startup program',//EwayLocale.report.serviceplan.selectPlan选择开机方案
			weekSelect:'Notify way'//EwayLocale.report.serviceplan.weekSelect通知方式
		}
	}
});
