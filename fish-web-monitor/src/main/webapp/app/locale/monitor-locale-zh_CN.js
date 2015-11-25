Ext.apply(EwayLocale,{
	//**********************************************************/
	monitor:{
		summary:{
			title:'监控总览',//EwayLocale.monitor.summary.title
			allSummary:'整体状态概况',//EwayLocale.monitor.summary.allSummary
			appSummary:'应用状态概况',//EwayLocale.monitor.summary.appSummary
			modSummary:'模块状态概况',//EwayLocale.monitor.summary.modSummary
			boxSummary:'钞箱状态概况',//EwayLocale.monitor.summary.boxSummary
			netSummary:'网络状态概况'//EwayLocale.monitor.summary.netSummary
		},
		deviceStatus:{
			Healthy:'正常',
			Warning:'警告',
			Fatal:'故障',
			Unknown:'未知',
			NoDevice:'无设备'
		},
		devMonitor:{
			title:'状态监控',//EwayLocale.monitor.devMonitor.title
			comboxStatus:{
				runStatus:'运行状态',//EwayLocale.monitor.devMonitor.comboxStatus.runStatus
				modStatus:'模块状态',//EwayLocale.monitor.devMonitor.comboxStatus.modStatus
				boxStatus:'钞箱状态',//EwayLocale.monitor.devMonitor.comboxStatus.boxStatus
				netStatus:'网络状态'//EwayLocale.monitor.devMonitor.comboxStatus.netStatus
			},
			monitorState:'订阅条件',//EwayLocale.monitor.devMonitor.monitorState
			showWay:'展示方式',//EwayLocale.monitor.devMonitor.showWay
			comboxShowWay:{
				summaryPattern:'全局模式',//EwayLocale.monitor.devMonitor.comboxShowWay.summaryPattern
				matrixPattern:'矩形方式',//EwayLocale.monitor.devMonitor.comboxShowWay.matrixPattern
				maxIconPattern:'超大图标',//EwayLocale.monitor.devMonitor.comboxShowWay.maxIconPattern
				listPattern:'列表方式',//EwayLocale.monitor.devMonitor.comboxShowWay.listPattern
				boxPattern:'钞箱方式'//EwayLocale.monitor.devMonitor.comboxShowWay.boxPattern
			},
			noData:'无记录',//EwayLocale.monitor.devMonitor.noData
			retainCardCount:'当前吞卡数量',//EwayLocale.monitor.devMonitor.retainCardCount
			cash:{
				boxInitCount:'钞箱初始金额',//EwayLocale.monitor.devMonitor.cash.boxInitCount
				boxCurrentCount:'钞箱当前金额',//EwayLocale.monitor.devMonitor.cash.boxCurrentCount
				cashboxLimit:'钞箱报警金额阈值',//EwayLocale.monitor.devMonitor.cash.cashboxLimit
				initAmount:'加钞总金额',//EwayLocale.monitor.devMonitor.cash.initAmount
				amount:'剩钞金额',//EwayLocale.monitor.devMonitor.cash.amount
				dispenseAmount:'出钞总金额',//EwayLocale.monitor.devMonitor.cash.dispenseAmount
				rejectAmount:'废钞金额',//EwayLocale.monitor.devMonitor.cash.rejectAmount
				retractCount:'钞票回收次数',//EwayLocale.monitor.devMonitor.cash.retractCount
				minAmount:'最小取款金额',//EwayLocale.monitor.devMonitor.cash.minAmount
				boxId:'钞箱标识',//EwayLocale.monitor.devMonitor.cash.boxId
				type:'钞箱类型',//EwayLocale.monitor.devMonitor.cash.type
				initialCount:'初始张数',//EwayLocale.monitor.devMonitor.cash.initialCount
				cashInCount:'存款张数',//EwayLocale.monitor.devMonitor.cash.cashInCount
				currentCount:'当前计数[张/笔]',//EwayLocale.monitor.devMonitor.cash.currentCount
				noteValue:'钞箱面值',//EwayLocale.monitor.devMonitor.cash.noteValue
				currency:'钞箱币种',//EwayLocale.monitor.devMonitor.cash.currency
				boxDetail:'钞箱详情',//EwayLocale.monitor.devMonitor.cash.boxDetail
				cimFull:'存款钞满',//EwayLocale.monitor.devMonitor.cash.cimFull
				cdmEmpty:'取款钞空',//EwayLocale.monitor.devMonitor.cash.cdmEmpty
				cdmLow:'取款钞少',//EwayLocale.monitor.devMonitor.cash.cdmLow
				low:'钞少',//EwayLocale.monitor.devMonitor.cash.low
				empty:'钞空',//EwayLocale.monitor.devMonitor.cash.empty
				cimAFull:'存款钞将满',//EwayLocale.monitor.devMonitor.cash.cimAFull
				cashFault:'钞箱故障',//EwayLocale.monitor.devMonitor.cash.cashFault
				cashUnknow:'钞箱未知'//EwayLocale.monitor.devMonitor.cash.cashUnknow

			},
			modStateGraphic:'模块状态图示',//EwayLocale.monitor.devMonitor.modStateGraphic
			modGraphic:'模块图示',//EwayLocale.monitor.devMonitor.modGraphic
			registerStatus:'注册状态',//EwayLocale.monitor.devMonitor.registerStatus
			devModStatus:'设备模块状态',//EwayLocale.monitor.devMonitor.devModStatus
			mod:{
				idc:'读卡器',//EwayLocale.monitor.devMonitor.mod.idc
				jpr:'日志打印机',//EwayLocale.monitor.devMonitor.mod.jpr
				cdm:'取款模块',//EwayLocale.monitor.devMonitor.mod.cdm
				cim:'存款模块',//EwayLocale.monitor.devMonitor.mod.cim
				siu:'传感器',//EwayLocale.monitor.devMonitor.mod.siu
				rpr:'凭条打印机',//EwayLocale.monitor.devMonitor.mod.rpr
				pin:'密码键盘',//EwayLocale.monitor.devMonitor.mod.pin
				ttu:'文本终端',//EwayLocale.monitor.devMonitor.mod.ttu
				isc:'身份证扫描仪',//EwayLocale.monitor.devMonitor.mod.isc
				icc:'发卡器',//EwayLocale.monitor.devMonitor.mod.icc
				fgp:'指纹仪',//EwayLocale.monitor.devMonitor.mod.fgp
				pbk:'存折打印机',//EwayLocale.monitor.devMonitor.mod.pbk
				healthy:'模块正常'//EwayLocale.monitor.devMonitor.mod.healthy

			},
			remote:{
				control:'远程控制',//EwayLocale.monitor.devMonitor.remote.control
				screen:'远程抓屏',//EwayLocale.monitor.devMonitor.remote.screen
				log:'提取电子日志',//EwayLocale.monitor.devMonitor.remote.log
				net:'查看网络连接',//EwayLocale.monitor.devMonitor.remote.net
				softwareList:'获取软件列表',//EwayLocale.monitor.devMonitor.remote.softwareList
				powerOff:'关机',//EwayLocale.monitor.devMonitor.remote.powerOff
				closeWays:'请选择关机方式',//EwayLocale.monitor.devMonitor.remote.closeWays
				restart:'重启',//EwayLocale.monitor.devMonitor.remote.restart
				restartWay:'请选择重启方式',//EwayLocale.monitor.devMonitor.remote.restartWay
				logicOpen:'开启服务',//EwayLocale.monitor.devMonitor.remote.logicOpen
				logicClose:'暂停服务',//EwayLocale.monitor.devMonitor.remote.logicClose
				remoteBrowser:'远程浏览',//EwayLocale.monitor.devMonitor.remote.remoteBrowser
				processList:'查看进程信息',//EwayLocale.monitor.devMonitor.remote.processList
				screenCamera:'屏幕录制',//EwayLocale.monitor.devMonitor.remote.screenCamera
				reset:'强制复位',//EwayLocale.monitor.devMonitor.remote.reset
				remoteLook:'查看应用版本',//EwayLocale.monitor.devMonitor.remote.remoteLook
				remoteCheckATM:'ATM体检',//EwayLocale.monitor.devMonitor.remote.remoteCheckATM
				halfSer:'半功能',//EwayLocale.monitor.devMonitor.remote.halfSer
				healthy:'正常服务',//EwayLocale.monitor.devMonitor.remote.healthy
				staff:'维护',//EwayLocale.monitor.devMonitor.remote.staff
				pFault:'交易前置故障',//EwayLocale.monitor.devMonitor.remote.pFault
				stop:'报停',//EwayLocale.monitor.devMonitor.remote.stop
				manualStop:'人工报停',////EwayLocale.monitor.devMonitor.remote.manualStop
				stopFault:'暂停服务-模块故障',//EwayLocale.monitor.devMonitor.remote.stopFault
				stopCash:'暂停服务-未加钞',//EwayLocale.monitor.devMonitor.remote.stopCash
				pauseSer:'暂停服务',//EwayLocale.monitor.devMonitor.remote.pauseSer
				pauseCash:'现金暂停',//EwayLocale.monitor.devMonitor.remote.pauseCash   ----中文不明，我猜的----
				pauseSerUnknow:'未知原因暂停服务',//EwayLocale.monitor.devMonitor.remote.pauseSerUnknow
				manaAndstaff:'管机员'//EwayLocale.monitor.devMonitor.remote.manaAndstaff
			},
			atmGroup:'分组',//EwayLocale.monitor.devMonitor.atmGroup
			atmGroupTip:'分组详情',//EwayLocale.monitor.devMonitor.atmGroupTip
			solution:'建议解决方案',//EwayLocale.monitor.devMonitor.solution
			faultDescription:'模块故障描述',//EwayLocale.monitor.devMonitor.faultDescription
			fastChoose:'快捷选择',//EwayLocale.monitor.devMonitor.fastChoose
			init:'初始化',//EwayLocale.monitor.devMonitor.init
			accTrans:'客户交易',//EwayLocale.monitor.devMonitor.accTrans
			factureStaff:'厂商模式维护',//EwayLocale.monitor.devMonitor.factureStaff
			netHealthy:'网络正常',//EwayLocale.monitor.devMonitor.netHealthy
			netUnStable:'网络不稳定',//EwayLocale.monitor.devMonitor.netUnStable
			netFatal:'网络故障',//EwayLocale.monitor.devMonitor.netFatal
			filterManager: {
				title: '订阅条件管理',//EwayLocale.monitor.devMonitor.filterManager.title
				add: '创建订阅条件',//EwayLocale.monitor.devMonitor.filterManager.add
				update: '修改订阅条件',//EwayLocale.monitor.devMonitor.filterManager.update
				filterForm: {
					filterName: '订阅名称'//EwayLocale.monitor.devMonitor.filterManager.filterForm.filterName
				}
			}
		},
		business:{
			transaction:{
				card:'交易卡号',//EwayLocale.monitor.business.transaction.card
				dateTime:'交易时间',//EwayLocale.monitor.business.transaction.dateTime
				transCode:'交易类型',//EwayLocale.monitor.business.transaction.transCode
				amt:'交易金额',//EwayLocale.monitor.business.transaction.amt
				currency:'交易币种',//EwayLocale.monitor.business.transaction.currency
				transId:'交易流水号',//EwayLocale.monitor.business.transaction.transId
				amtfield:'金额范围',//EwayLocale.monitor.business.transaction.amtfield
				toNum:'至',//EwayLocale.monitor.business.transaction.toNum
				transContainer:'交易时间段',//EwayLocale.monitor.business.transaction.transContainer
				debitAccountOrCard:'客户账号或者卡号',//EwayLocale.monitor.business.transaction.debitAccountOrCard
				creditAccountOrCard:'对方账号或者卡号',//EwayLocale.monitor.business.transaction.creditAccountOrCard
				debitAccount:'客户帐号',//EwayLocale.monitor.business.transaction.debitAccount
				creditAccount:'对方账号',//EwayLocale.monitor.business.transaction.creditAccount
				localRet:'ATMC本地代码',//EwayLocale.monitor.business.transaction.localRet
				hostRet:'主机返回码',//EwayLocale.monitor.business.transaction.hostRet
				userName:'用户姓名',//EwayLocale.monitor.business.transaction.userName
				historyTransaction:{
					title:'历史交易查询'//EwayLocale.monitor.business.transaction.historyTransaction.title

				},
				transactionMonitor:{
					title:'实时交易监控',//EwayLocale.monitor.business.transaction.transactionMonitor.title
					begin:'开始监控',//EwayLocale.monitor.business.transaction.transactionMonitor.begin
					stop:'停止监控',//EwayLocale.monitor.business.transaction.transactionMonitor.stop
					clear:'清屏'//EwayLocale.monitor.business.transaction.transactionMonitor.clear
					}
			},
			blackList:{
				title:'黑名单卡管理',//EwayLocale.monitor.business.blackList.title
				black:'黑名单卡',//EwayLocale.monitor.business.blackList.black
				addBlack:'添加黑名单卡',//EwayLocale.monitor.business.blackList.addBlack
				cardBank:'所属银行',//EwayLocale.monitor.business.blackList.cardBank
				importData:'批量导入',//EwayLocale.monitor.business.blackList.importData
				addDate:'添加日期',//EwayLocale.monitor.business.blackList.addDate
				importTitle:'批量导入黑名单卡',//EwayLocale.monitor.business.blackList.importTitle
				importFile:'导入文件',//EwayLocale.monitor.business.blackList.importFile
				chooseFileRegex:'请选择导入文件,只支持.xls和.xlsx格式的文件',//EwayLocale.monitor.business.blackList.chooseFileRegex
				fileRegex:'只支持.xls和.xlsx格式的文件',//EwayLocale.monitor.business.blackList.fileRegex
				tempDownload:'模版下载',//EwayLocale.monitor.business.blackList.tempDownload
				importNow:'导入',//EwayLocale.monitor.business.blackList.importNow
				updateTitle:'更改黑名单卡信息'//EwayLocale.monitor.business.blackList.updateTitle
			},
			card:{
				title:'吞卡管理',//EwayLocale.monitor.business.card.title
				addTitle:'增加吞卡信息',//EwayLocale.monitor.business.card.addTitle
				time:'吞卡时间',//EwayLocale.monitor.business.card.time
				reason:'吞卡原因',//EwayLocale.monitor.business.card.reason
				destroy:'吞卡销毁',//EwayLocale.monitor.business.card.destory
				cardHolder:'发卡行',//EwayLocale.monitor.business.card.cardHolder
				cardRegex:'允许的最大长度为',//EwayLocale.monitor.business.card.cardRegex
				beginEndDate:'吞卡起始日期不能大于吞卡截止日期,请重新选择',//EwayLocale.monitor.business.card.beginEndDate
				orgBelongs:'所属机构 (包含处理机构)',//EwayLocale.monitor.business.card.orgBelongs
				beginTime:'开始时间',//EwayLocale.monitor.business.card.beginTime
				endTime:'结束时间',//EwayLocale.monitor.business.card.endTime
				accGetCard:'客户领卡',//EwayLocale.monitor.business.card.accGetCard
				transferCard:'卡片移交',//EwayLocale.monitor.business.card.transferCard
				processOrg:'处理机构',//EwayLocale.monitor.business.card.processOrg
				type:'吞卡类型',//EwayLocale.monitor.business.card.type
				manual:'手动添加',//EwayLocale.monitor.business.card.manual
				auto:'自动添加',//EwayLocale.monitor.business.card.auto
				comboxStatus:{
					wait:'待领',//EwayLocale.monitor.business.card.comboxStatus.wait
					received:'已领',//EwayLocale.monitor.business.card.comboxStatus.received
					destroy:'销毁',//EwayLocale.monitor.business.card.comboxStatus.destroy
					bringed:'调出'//EwayLocale.monitor.business.card.comboxStatus.bringed
				},
				treatmentPeople:'处理人员',//EwayLocale.monitor.business.card.treatmentPeople
				treatmentTime:'处理时间',//EwayLocale.monitor.business.card.treatmentTime
				customerName:'客户姓名',//EwayLocale.monitor.business.card.customerName
				customerPhone:'客户电话',//EwayLocale.monitor.business.card.customerPhone
				customerPapers:'客户证件号',//EwayLocale.monitor.business.card.customerPapers
				processCard:'吞卡处理',//EwayLocale.monitor.business.card.processCard
				destroyCard:'卡片销毁',//EwayLocale.monitor.business.card.destroyCard
				exportData:'导出',//EwayLocale.monitor.business.card.exportData
				paperType:'证件类型',//EwayLocale.monitor.business.card.paperType
				paperCode:'证件号',//EwayLocale.monitor.business.card.paperCode
				idCard:'身份证',//EwayLocale.monitor.business.card.idCard
				accountPaper:'户口本',//EwayLocale.monitor.business.card.accountPaper
				drivePaper:'驾驶执照',//EwayLocale.monitor.business.card.drivePaper
				passport:'护照',//EwayLocale.monitor.business.card.passport
				officer:'军官证',//EwayLocale.monitor.business.card.officer
				soldier:'士兵证',//EwayLocale.monitor.business.card.soldier
				busnessPaper:'法人营业证',//EwayLocale.monitor.business.card.busnessPaper
				busnessCode:'法人代码证',//EwayLocale.monitor.business.card.busnessCode
				taxPaper:'税务登记证',//EwayLocale.monitor.business.card.taxPaper
				withDev:'按设备'//EwayLocale.monitor.business.card.withDev
			},


			cashInit:{
				titile:'加钞信息查询',//EwayLocale.monitor.business.cashInit.titile
				uuId:'加钞ID',//EwayLocale.monitor.business.cashInit.uuId
				date:'加钞日期',//EwayLocale.monitor.business.cashInit.date
				amt:'加钞金额',//EwayLocale.monitor.business.cashInit.amt
				info:'加钞详细信息',//EwayLocale.monitor.business.cashInit.info
				boxId:'钞箱ID',//EwayLocale.monitor.business.cashInit.boxId
				boxCurrency:'币种',//EwayLocale.monitor.business.cashInit.boxCurrency
				boxInitAmt:'初始金额',//EwayLocale.monitor.business.cashInit.boxInitAmt
				lastAmt:'剩余金额'//EwayLocale.monitor.business.cashInit.lastAmt
			},
			settlement:{
				title:'清机信息查询',//EwayLocale.monitor.business.settlement.title
				deTitle:'清机详细信息',//EwayLocale.monitor.business.settlement.deTitle
				settleId:'清机ID',//EwayLocale.monitor.business.settlement.settleId
				uuId:'周期ID',//EwayLocale.monitor.business.settlement.uuId
				endAmt:'尾箱余额',//EwayLocale.monitor.business.settlement.endAmt
				endDate:'结帐日期',//EwayLocale.monitor.business.settlement.endDate
				cimNum:'存款笔数',//EwayLocale.monitor.business.settlement.cimNum
				cdmNum:'取款笔数',//EwayLocale.monitor.business.settlement.cdmNum
				totalNum:'交易总笔数',//EwayLocale.monitor.business.settlement.totalNum
				leftDate:'结账日期',//EwayLocale.monitor.business.settlement.leftDate
				cimAmt:'存款金额',//EwayLocale.monitor.business.settlement.cimAmt
				cdmAmt:'取款金额',//EwayLocale.monitor.business.settlement.cdmAmt
				tranAmt:'交易总金额'//EwayLocale.monitor.business.settlement.tranAmt
			}
		}
	},
	//**********************************************************/
	agent:{
		remote:{
			screen:{
				message:'信息',
				startcustom:'开始录制客户前屏',
				stopcustom:'停止录制客户前屏',
				startadmin:'开始录制管理后屏',
				stopadmin: '停止录制管理后屏',
				startadvertise: '开始录制广告屏',
				stopadvertise: '停止录制广告屏',
				startCameraDate: '开始录制时间',
				stopCameraDate: '结束录制时间',
				monitorType: '屏幕类型',
				fileNameClient: '文件名称',
				nowCamera:'正在录制...',
				finishCamera:'完成录制',
				videoLoad:'正在将视频文件下载至服务端...',
				stopManage:'自动停止.如需取得视频文件,请联系管理员!',
				manage: '操作',
				loading:'下载',//EwayLocale.agent.remote.screen.loading
				screenCamera: '屏幕录制',
			},
			discInfo: '磁盘信息',
			discName: '磁盘分区名称',
			fileSys: '磁盘文件系统',
			totalSize: '磁盘总大小',
			freeSize: '磁盘可用空间大小',
			networkInfo:'网络连接信息',
			conenctRate: '连接速率',
			receivedByte: '接收到的字节数',
			sendByte: '发动的字节数',
			loadData: '加载数据中，请稍候...',
			refresh: '刷新',
			name: '名称',//EwayLocale.agent.remote.loading
			format: '格式',
			totalSize: '总大小',
			freeSize: '可用空间',
			refreshFailure:'刷新失败！',
			back:'返回',
			upload: '上传',
			Mkdir: '新建文件夹',
			MkFile: '新建文件',
			MKcatalog:'新建目录',
			catalogName:'目录名称',
			remove: '删除',
			execute: '执行',
			path: '路径',
			search: '搜索',
			size: '大小',
			fileSize:'文件大小：',
			lastTime: '最后修改时间',
			clickLoadFile:'单击即可下载该文件',
			loadFileSize:'下载文件 不能超过200M！',
			nowLoadFile:'正在下载文件......',
			judgeLoad: '是否续传下载！',
			loadFailure:'下载失败！',
			number: '编号',
			programName: '程序名称',
			version: '版本号',
			publisher: '发布商',
			diskUsed: '磁盘使用',
			softwayList: '软件安装列表',
			networkInfo: '网络信息',
			networkLinkStatus: '网络连接状态',
			send: '已发送',
			receive: '已接收',
			bite: '字节:',
			speed: '速度(Mbps):',
			bandWidth: '宽带测速',
			unit: '单位：MB',
			againTest: '重新测试',
			impressionName: '印象名称',
			userName: '用户名',
			memoryRate: '内存使用',
			systemProgressInfo:'系统进程信息',
			screenShotTime: '截屏时间',
			distanceScreen: '远程抓屏',
			distanceExplorer: '远程浏览',
			ATMExamination:'ATM体检',
			checkATM: '重新体检',
			ATMExamInfo: 'ATM体检详情',
			cpuIdle: 'CPU空闲率',
			memoryIdle: '内存空闲率',
			hardDiskIdle: '硬盘空闲率',
			uploadFile: '上传文件',
			rules:'不能包含一下字符:\/?*":<>|',
			nowCreat:'正在新建...',
			nowPath: '当前路径',
			confirm: '保存',
			reset: '重置',
			back: '返回',
			prepareFile:'待上传文件',
			choseUploadFile:'请选择上传文件',
			nowUploadFile:'正在上传文件...',
			explorer: '浏览...',
			serverPath: '文件在服务器上的位置',
			distanceSuccess:'远程创建成功.',
			distanceFailure:'远程创建失败.',
			confirmDelete:'确定删除',
			confirmExecute:'确定执行',
			choseDeleteFile:'请选择要删除的文件.',
			distanceExecuteSuccess:'远程执行成功.',
			distanceExecuteFailure:'远程执行失败.',
			choseExecuteFile:'请选择要执行的文件.',
			distanceExplorer:'远程浏览:',
			distanceExplorerFailure:'远程浏览失败.',
			fileExist:'该文件已存在！',
			nowUploadFile:'正在上传文件......',
			uploadSuccess:'上传成功.',
			yes: "续传",
			no: "覆盖",
			cancel: "取消",
			choseFile:'未选择上传文件,请选择文件.',
			returnFailure:'返回失败.',
			refreshFailure:'刷新失败.',
			catalogExist:'搜索的目录不存在,请重新输入.',
			testBandWidth:'测试宽带异常.',
			networkMaxSpeed:'网络最大接入速度为',
			minutes:'秒',
			amount:'相当于',
			specialLine:'专线',
			bandWidth:'宽带',
			handle:'正在处理......',
			offServer:'与服务器断开连接.',//EwayLocale.agent.offServer
			submitingWaiting:'正在提交,请稍等...',//EwayLocale.agent.submitingWaiting
			ATMCheck:'正在ATM体检中...',
			excellent:'优',
			fine:'良',
			middle:'中',
			bad:'差',
			point:'分',
			checkFailure:'ATM体检失败,请重新操作.',
			checkVersionInfo:'查看版本信息',
			versionInfo:'您要查看的版本信息如下:',
			ATMCVersion:'ATMC应用版本',
			monitorVersion: '监控客户端版本',
			mergeload:'查看下载列表',
			mergeDownLoad:'执行下载任务',
			clickAddLoadFile:'单击添加到下载列表',
			removeFile:'删除文件',
			addFileSuccess:'成功添加到下载列表!',
            maxDownLoadFileSize:'添加失败,下载列表中文件总大小不能超过200M.',
            exitDownLoadFile:'添加失败,该文件已添加到下载列表中.',
            removeSuccess:'删除成功.',
            mustIncludeOneFile:'请至少添加一个要下载的文件.',

		}
	}
});
