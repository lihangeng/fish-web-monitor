Ext.apply(EwayLocale,{
	
	monitor:{
		summary:{
			title:'监控总览',//EwayLocale.summary.title
			allSummary:'整体状态概况',//EwayLocale.summary.allSummary
			appSummary:'应用状态概况',//EwayLocale.summary.appSummary
			modSummary:'模块状态概况',//EwayLocale.summary.modSummary
			boxSummary:'钞箱状态概况',//EwayLocale.summary.boxSummary
			netSummary:'网络状态概况'//EwayLocale.summary.netSummary
		},
		deviceStatus:{
			Healthy:'正常',//EwayLocale.deviceStatus.Healthy
			Warning:'警告',//EwayLocale.deviceStatus.Warning
			Fatal:'故障',//EwayLocale.deviceStatus.Fatal
			Unknown:'未知',//EwayLocale.deviceStatus.Unknown
			NoDevice:'无设备'//EwayLocale.deviceStatus.NoDevice
		},
		devMonitor:{
			title:'状态监控',//EwayLocale.devMonitor.title
			comboxStatus:{
				runStatus:'运行状态',//EwayLocale.comboxStatus.runStatus
				modStatus:'模块状态',//EwayLocale.comboxStatus.modStatus
				boxStatus:'钞箱状态',//EwayLocale.comboxStatus.boxStatus
				netStatus:'网络状态'//EwayLocale.comboxStatus.netStatus
			},
			monitorState:'订阅条件',//EwayLocale.comboxStatus.monitorState
			showWay:'展示方式',//EwayLocale.comboxStatus.showWay
			comboxShowWay:{
				summaryPattern:'全局模式',//EwayLocale.comboxShowWay.summaryPattern
				matrixPattern:'矩形方式',//EwayLocale.comboxShowWay.matrixPattern
				maxIconPattern:'超大图标',//EwayLocale.comboxShowWay.maxIconPattern
				listPattern:'列表方式',//EwayLocale.comboxShowWay.listPattern
				boxPattern:'钞箱方式'//EwayLocale.comboxShowWay.boxPattern
			},
			noData:'无记录',//EwayLocale.comboxShowWay.noData
			retainCardCount:'当前吞卡数量',//EwayLocale.comboxShowWay.retainCardCount
			cash:{
				boxInitCount:'钞箱初始金额',//EwayLocale.cash.boxInitCount
				boxCurrentCount:'钞箱当前金额',//EwayLocale.cash.boxCurrentCount
				cashboxLimit:'钞箱报警金额阈值',//EwayLocale.cash.cashboxLimit
				initAmount:'加钞总金额',//EwayLocale.cash.initAmount
				amount:'剩钞金额',//EwayLocale.cash.amount
				dispenseAmount:'出钞总金额',//EwayLocale.cash.dispenseAmount
				rejectAmount:'废钞金额',//EwayLocale.cash.rejectAmount
				retractCount:'钞票回收次数',//EwayLocale.cash.retractCount
				minAmount:'最小取款金额',//EwayLocale.cash.minAmount
				boxId:'钞箱标识',//EwayLocale.cash.boxId
				type:'钞箱类型',//EwayLocale.cash.type
				boxStatus:'钞箱状态',//EwayLocale.cash.boxStatus
				initialCount:'初始张数',//EwayLocale.cash.initialCount
				cashInCount:'存款张数',//EwayLocale.cash.cashInCount
				currentCount:'当前计数[张/笔]',//EwayLocale.cash.currentCount
				noteValue:'钞箱面值',//EwayLocale.cash.noteValue
				currency:'钞箱币种',//EwayLocale.cash.currency
				boxDetail:'钞箱详情',//EwayLocale.cash.boxDetail
				cimFull:'存款钞满',//EwayLocale.cash.cimFull
				cdmEmpty:'取款钞空',//EwayLocale.cash.cdmEmpty
				cdmLow:'取款钞少',//EwayLocale.cash.cdmLow
				low:'钞少',//EwayLocale.cash.low
				empty:'钞空',//EwayLocale.cash.empty
				cimAFull:'存款钞将满',//EwayLocale.cash.cimAFull
				cashFault:'钞箱故障',//EwayLocale.cash.cashFault
				cashUnknow:'钞箱未知'//EwayLocale.cash.cashUnknow

			},
			modStateGraphic:'模块状态图示',//EwayLocale.cash.modStateGraphic
			modGraphic:'模块图示',//EwayLocale.cash.modGraphic
			registerStatus:'注册状态',//EwayLocale.cash.registerStatus
			devModStatus:'设备模块状态',//EwayLocale.cash.devModStatus
			mod:{
				idc:'读卡器',//EwayLocale.mod.idc
				jpr:'日志打印机',//EwayLocale.mod.jpr
				cdm:'取款模块',//EwayLocale.mod.cdm
				cim:'存款模块',//EwayLocale.mod.cim
				siu:'传感器',//EwayLocale.mod.siu
				rpr:'凭条打印机',//EwayLocale.mod.rpr
				pin:'密码键盘',//EwayLocale.mod.pin
				ttu:'文本终端',//EwayLocale.mod.ttu
				isc:'身份证扫描仪',//EwayLocale.mod.isc
				icc:'发卡器',//EwayLocale.mod.icc
				fgp:'指纹仪',//EwayLocale.mod.fgp
				pbk:'存折打印机',//EwayLocale.mod.pbk
				cam:'摄像头',//EwayLocale.mod.cam
				bcr:'二维码扫描仪',//EwayLocale.mod.bcr
				healthy:'模块正常'//EwayLocale.mod.healthy

			},
			remote:{
				control:'远程控制',//EwayLocale.remote.control
				screen:'远程抓屏',//EwayLocale.remote.screen
				commandRet:'远程命令结果',//EwayLocale.remote.commandRet
				log:'提取电子日志',//EwayLocale.remote.log
				net:'查看网络连接',//EwayLocale.remote.net
				softwareList:'获取软件列表',//EwayLocale.remote.softwareList
				powerOff:'关机',//EwayLocale.remote.powerOff
				closeWays:'请选择关机方式',//EwayLocale.remote.closeWays
				restart:'重启',//EwayLocale.remote.restart
				restartWay:'请选择重启方式',//EwayLocale.remote.restartWay
				logicOpen:'开启服务',//EwayLocale.remote.logicOpen
				logicClose:'暂停服务',//EwayLocale.remote.logicClose
				remoteBrowser:'远程浏览',//EwayLocale.remote.remoteBrowser
				processList:'查看进程信息',//EwayLocale.remote.processList
				screenCamera:'屏幕录制',//EwayLocale.remote.screenCamera
				reset:'强制复位',//EwayLocale.remote.reset
				remoteLook:'查看应用版本',//EwayLocale.remote.remoteLook
				remoteCheckATM:'ATM体检',//EwayLocale.remote.remoteCheckATM
				halfSer:'半功能',//EwayLocale.remote.halfSer
				healthy:'正常服务',//EwayLocale.remote.healthy
				staff:'维护',//EwayLocale.remote.staff
				pFault:'交易前置故障',//EwayLocale.remote.pFault
				stop:'报停',//EwayLocale.remote.stop
				manualStop:'人工报停',//EwayLocale.remote.manualStop
				stopFault:'暂停服务-模块故障',//EwayLocale.remote.stopFault
				stopCash:'暂停服务-未加钞',//EwayLocale.remote.stopCash
				pauseSer:'暂停服务',//EwayLocale.remote.pauseSer
				pauseCash:'现金暂停',//EwayLocale.remote.pauseCash
				pauseSerUnknow:'未知原因暂停服务',//EwayLocale.remote.pauseSerUnknow
				manaAndstaff:'管机员',//EwayLocale.remote.manaAndstaff
				screenFailed:'截屏失败.'//EwayLocale.remote.screenFailed
			},
			atmGroup:'分组',//EwayLocale.remote.atmGroup
			atmGroupTip:'分组详情',//EwayLocale.remote.atmGroupTip
			solution:'建议解决方案',//EwayLocale.remote.solution
			faultDescription:'模块故障描述',//EwayLocale.remote.faultDescription
			fastChoose:'快捷选择',//EwayLocale.remote.fastChoose
			init:'初始化',//EwayLocale.remote.init
			accTrans:'客户交易',//EwayLocale.remote.accTrans
			factureStaff:'厂商模式维护',//EwayLocale.remote.factureStaff
			netHealthy:'网络正常',//EwayLocale.remote.netHealthy
			netUnStable:'网络不稳定',//EwayLocale.remote.netUnStable
			netFatal:'网络故障',//EwayLocale.remote.netFatal
			filterManager: {//EwayLocale.remote.filterManager
				title: '订阅条件管理',//EwayLocale.remote.title
				add: '创建订阅条件',//EwayLocale.remote.add
				update: '修改订阅条件',//EwayLocale.remote.update
				filterForm: {//EwayLocale.remote.filterForm
					filterName: '订阅名称'//EwayLocale.remote.filterName
				}
			}
		},
		business:{
			transaction:{
				card:'交易卡号',//EwayLocale.transaction.card
				dateTime:'交易时间',//EwayLocale.transaction.dateTime
				transCode:'交易类型',//EwayLocale.transaction.transCode
				amt:'交易金额',//EwayLocale.transaction.amt
				currency:'交易币种',//EwayLocale.transaction.currency
				transId:'交易流水号',//EwayLocale.transaction.transId
				amtfield:'金额范围',//EwayLocale.transaction.amtfield
				toNum:'至',//EwayLocale.transaction.toNum
				transContainer:'交易时间段',//EwayLocale.transaction.transContainer
				debitAccountOrCard:'客户账号或者卡号',//EwayLocale.transaction.debitAccountOrCard
				creditAccountOrCard:'对方账号或者卡号',//EwayLocale.transaction.creditAccountOrCard
				debitAccount:'客户帐号',//EwayLocale.transaction.debitAccount
				creditAccount:'对方账号',//EwayLocale.transaction.creditAccount
				localRet:'ATMC本地代码',//EwayLocale.transaction.localRet
				hostRet:'主机返回码',//EwayLocale.transaction.hostRet
				userName:'用户姓名',//EwayLocale.transaction.userName
				historyTransaction:{
					title:'历史交易查询'//EwayLocale.historyTransaction.title

				},
				transactionMonitor:{
					title:'实时交易监控',//EwayLocale.transactionMonitor.title
					begin:'开始监控',//EwayLocale.transactionMonitor.begin
					stop:'停止监控',//EwayLocale.transactionMonitor.stop
					clear:'清屏',//EwayLocale.transactionMonitor.clear
					scroll:'滚屏方式',//EwayLocale.transactionMonitor.scroll
					up:'向上',//EwayLocale.transactionMonitor.up
					down:'向下'//EwayLocale.transactionMonitor.down
				}
			},
			blackList:{
				title:'黑名单卡管理',//EwayLocale.blackList.title
				black:'黑名单卡',//EwayLocale.blackList.black
				addBlack:'添加黑名单卡',//EwayLocale.blackList.addBlack
				cardBank:'所属银行',//EwayLocale.blackList.cardBank
				importData:'批量导入',//EwayLocale.blackList.importData
				addDate:'添加日期',//EwayLocale.blackList.addDate
				importTitle:'批量导入黑名单卡',//EwayLocale.blackList.importTitle
				importFile:'导入文件',//EwayLocale.blackList.importFile
				chooseFileRegex:'请选择导入文件,只支持.xls和.xlsx格式的文件',//EwayLocale.blackList.chooseFileRegex
				fileRegex:'只支持.xls和.xlsx格式的文件',//EwayLocale.blackList.fileRegex
				tempDownload:'模版下载',//EwayLocale.blackList.tempDownload
				importNow:'导入',//EwayLocale.blackList.importNow
				updateTitle:'更改黑名单卡信息'//EwayLocale.blackList.updateTitle
			},
			card:{
				title:'吞卡管理',//EwayLocale.card.title
				addTitle:'增加吞卡信息',//EwayLocale.card.addTitle
				time:'吞卡时间',//EwayLocale.card.time
				reason:'吞卡原因',//EwayLocale.card.reason
				destroy:'吞卡销毁',//EwayLocale.card.destroy
				cardHolder:'发卡行',//EwayLocale.card.cardHolder
				cardRegex:'允许的最大长度为',//EwayLocale.card.cardRegex
				beginEndDate:'吞卡起始日期不能大于吞卡截止日期,请重新选择',//EwayLocale.card.beginEndDate
				orgBelongs:'所属机构 (包含处理机构)',//EwayLocale.card.orgBelongs
				beginTime:'开始时间',//EwayLocale.card.beginTime
				endTime:'结束时间',//EwayLocale.card.endTime
				accGetCard:'客户领卡',//EwayLocale.card.accGetCard
				transferCard:'卡片移交',//EwayLocale.card.transferCard
				processOrg:'处理机构',//EwayLocale.card.processOrg
				type:'吞卡类型',//EwayLocale.card.type
				manual:'手动添加',//EwayLocale.card.manual
				auto:'自动添加',//EwayLocale.card.auto
				comboxStatus:{
					wait:'待领',//EwayLocale.comboxStatus.wait
					received:'已领',//EwayLocale.comboxStatus.received
					destroy:'销毁',//EwayLocale.comboxStatus.destroy
					bringed:'调出'//EwayLocale.comboxStatus.bringed
				},
				treatmentPeople:'处理人员',//EwayLocale.comboxStatus.treatmentPeople
				treatmentTime:'处理时间',//EwayLocale.comboxStatus.treatmentTime
				customerName:'客户姓名',//EwayLocale.comboxStatus.customerName
				customerPhone:'客户电话',//EwayLocale.comboxStatus.customerPhone
				customerPapers:'客户证件号',//EwayLocale.comboxStatus.customerPapers
				processCard:'吞卡处理',//EwayLocale.comboxStatus.processCard
				destroyCard:'卡片销毁',//EwayLocale.comboxStatus.destroyCard
				exportData:'导出',//EwayLocale.comboxStatus.exportData
				paperType:'证件类型',//EwayLocale.comboxStatus.paperType
				paperCode:'证件号',//EwayLocale.comboxStatus.paperCode
				idCard:'身份证',//EwayLocale.comboxStatus.idCard
				accountPaper:'户口本',//EwayLocale.comboxStatus.accountPaper
				drivePaper:'驾驶执照',//EwayLocale.comboxStatus.drivePaper
				passport:'护照',//EwayLocale.comboxStatus.passport
				officer:'军官证',//EwayLocale.comboxStatus.officer
				soldier:'士兵证',//EwayLocale.comboxStatus.soldier
				busnessPaper:'法人营业证',//EwayLocale.comboxStatus.busnessPaper
				busnessCode:'法人代码证',//EwayLocale.comboxStatus.busnessCode
				taxPaper:'税务登记证',//EwayLocale.comboxStatus.taxPaper
				withDev:'按设备'//EwayLocale.comboxStatus.withDev
			},


			cashInit:{
				titile:'加钞信息查询',//EwayLocale.cashInit.titile
				uuId:'加钞ID',//EwayLocale.cashInit.uuId
				date:'加钞日期',//EwayLocale.cashInit.date
				amt:'加钞金额',//EwayLocale.cashInit.amt
				info:'加钞详细信息',//EwayLocale.cashInit.info
				boxId:'钞箱ID',//EwayLocale.cashInit.boxId
				boxCurrency:'币种',//EwayLocale.cashInit.boxCurrency
				boxInitAmt:'初始金额',//EwayLocale.cashInit.boxInitAmt
				lastAmt:'剩余金额'//EwayLocale.cashInit.lastAmt
			},
			settlement:{
				title:'清机信息查询',//EwayLocale.settlement.title
				deTitle:'清机详细信息',//EwayLocale.settlement.deTitle
				settleId:'清机ID',//EwayLocale.settlement.settleId
				uuId:'周期ID',//EwayLocale.settlement.uuId
				endAmt:'尾箱余额',//EwayLocale.settlement.endAmt
				endDate:'结帐日期',//EwayLocale.settlement.endDate
				cimNum:'存款笔数',//EwayLocale.settlement.cimNum
				cdmNum:'取款笔数',//EwayLocale.settlement.cdmNum
				totalNum:'交易总笔数',//EwayLocale.settlement.totalNum
				leftDate:'结账日期',//EwayLocale.settlement.leftDate
				cimAmt:'存款金额',//EwayLocale.settlement.cimAmt
				cdmAmt:'取款金额',//EwayLocale.settlement.cdmAmt
				tranAmt:'交易总金额'//EwayLocale.settlement.tranAmt
			}
		},
		remoteCommand:{
			titile:'远程命令查询',//EwayLocale.remoteCommand.titile
			form:{
				date:'执行日期'//EwayLocale.form.date
			},
			grid:{
				commandType:'命令类型',//EwayLocale.grid.commandType
				commandResult:'执行结果',//EwayLocale.grid.commandResult
				datetime:'执行日期',//EwayLocale.grid.datetime
				handlePerson:'操作人'//EwayLocale.grid.handlePerson
			}
		}
	},
	
	agent:{
		remote:{
			screen:{
				message:'信息',//EwayLocale.screen.message
				startcustom:'开始录制客户前屏',//EwayLocale.screen.startcustom
				stopcustom:'停止录制客户前屏',//EwayLocale.screen.stopcustom
				startadmin:'开始录制管理后屏',//EwayLocale.screen.startadmin
				stopadmin: '停止录制管理后屏',//EwayLocale.screen.stopadmin
				startadvertise: '开始录制广告屏',//EwayLocale.screen.startadvertise
				stopadvertise: '停止录制广告屏',//EwayLocale.screen.stopadvertise
				startCameraDate: '开始录制时间',//EwayLocale.screen.startCameraDate
				stopCameraDate: '结束录制时间',//EwayLocale.screen.stopCameraDate
				monitorType: '屏幕类型',//EwayLocale.screen.monitorType
				fileNameClient: '文件名称',//EwayLocale.screen.fileNameClient
				nowCamera:'正在录制...',//EwayLocale.screen.nowCamera
				finishCamera:'完成录制',//EwayLocale.screen.finishCamera
				videoLoad:'正在将视频文件下载至服务端...',//EwayLocale.screen.videoLoad
				stopManage:'自动停止.如需取得视频文件,请联系管理员!',//EwayLocale.screen.stopManage
				manage: '操作',//EwayLocale.screen.manage
				loading:'下载',//EwayLocale.screen.loading
				screenCamera: '屏幕录制',//EwayLocale.screen.screenCamera
			},
			discInfo: '磁盘信息',//EwayLocale.screen.discInfo
			discName: '磁盘分区名称',//EwayLocale.screen.discName
			fileSys: '磁盘文件系统',//EwayLocale.screen.fileSys
			totalSize: '磁盘总大小',//EwayLocale.screen.totalSize
			freeSize: '磁盘可用空间大小',//EwayLocale.screen.freeSize
			networkInfo:'网络连接信息',//EwayLocale.screen.networkInfo
			conenctRate: '连接速率',//EwayLocale.screen.conenctRate
			receivedByte: '接收到的字节数',//EwayLocale.screen.receivedByte
			sendByte: '发动的字节数',//EwayLocale.screen.sendByte
			loadData: '加载数据中，请稍候...',//EwayLocale.screen.loadData
			refresh: '刷新',//EwayLocale.screen.refresh
			name: '名称',//EwayLocale.screen.name
			format: '格式',//EwayLocale.screen.format
			totalSize: '总大小',//EwayLocale.screen.totalSize
			freeSize: '可用空间',//EwayLocale.screen.freeSize
			refreshFailure:'刷新失败！',//EwayLocale.screen.refreshFailure
			back:'返回',//EwayLocale.screen.back
			upload: '上传',//EwayLocale.screen.upload
			Mkdir: '新建文件夹',//EwayLocale.screen.Mkdir
			MkFile: '新建文件',//EwayLocale.screen.MkFile
			MKcatalog:'新建目录',//EwayLocale.screen.MKcatalog
			catalogName:'目录名称',//EwayLocale.screen.catalogName
			remove: '删除',//EwayLocale.screen.remove
			execute: '执行',//EwayLocale.screen.execute
			path: '路径',//EwayLocale.screen.path
			search: '搜索',//EwayLocale.screen.search
			size: '大小',//EwayLocale.screen.size
			fileSize:'文件大小：',//EwayLocale.screen.fileSize
			lastTime: '最后修改时间',//EwayLocale.screen.lastTime
			clickLoadFile:'单击即可下载该文件',//EwayLocale.screen.clickLoadFile
			loadFileSize:'下载文件 不能超过200M！',//EwayLocale.screen.loadFileSize
			nowLoadFile:'正在下载文件......',//EwayLocale.screen.nowLoadFile
			judgeLoad: '是否续传下载！',//EwayLocale.screen.judgeLoad
			loadFailure:'下载失败！',//EwayLocale.screen.loadFailure
			number: '编号',//EwayLocale.screen.number
			programName: '程序名称',//EwayLocale.screen.programName
			version: '版本号',//EwayLocale.screen.version
			publisher: '发布商',//EwayLocale.screen.publisher
			diskUsed: '磁盘使用',//EwayLocale.screen.diskUsed
			softwayList: '软件安装列表',//EwayLocale.screen.softwayList
			networkInfo: '网络信息',//EwayLocale.screen.networkInfo
			networkLinkStatus: '网络连接状态',//EwayLocale.screen.networkLinkStatus
			send: '已发送',//EwayLocale.screen.send
			receive: '已接收',//EwayLocale.screen.receive
			bite: '字节:',//EwayLocale.screen.bite
			speed: '速度(Mbps):',//EwayLocale.screen.speed
			bandWidth: '宽带测速',//EwayLocale.screen.bandWidth
			unit: '单位：MB',//EwayLocale.screen.unit
			againTest: '重新测试',//EwayLocale.screen.againTest
			impressionName: '印象名称',//EwayLocale.screen.impressionName
			userName: '用户名',//EwayLocale.screen.userName
			memoryRate: '内存使用',//EwayLocale.screen.memoryRate
			systemProgressInfo:'系统进程信息',//EwayLocale.screen.systemProgressInfo
			screenShotTime: '截屏时间',//EwayLocale.screen.screenShotTime
			distanceScreen: '远程抓屏',//EwayLocale.screen.distanceScreen
			distanceExplorer: '远程浏览',//EwayLocale.screen.distanceExplorer
			ATMExamination:'ATM体检',//EwayLocale.screen.ATMExamination
			checkATM: '重新体检',//EwayLocale.screen.checkATM
			ATMExamInfo: 'ATM体检详情',//EwayLocale.screen.ATMExamInfo
			cpuIdle: 'CPU空闲率',//EwayLocale.screen.cpuIdle
			memoryIdle: '内存空闲率',//EwayLocale.screen.memoryIdle
			hardDiskIdle: '硬盘空闲率',//EwayLocale.screen.hardDiskIdle
			uploadFile: '上传文件',//EwayLocale.screen.uploadFile
			rules:'不能包含一下字符:\/?*":<>|',//EwayLocale.screen.rules
			nowCreat:'正在新建...',//EwayLocale.screen.nowCreat
			nowPath: '当前路径',//EwayLocale.screen.nowPath
			confirm: '保存',//EwayLocale.screen.confirm
			reset: '重置',//EwayLocale.screen.reset
			back: '返回',//EwayLocale.screen.back
			prepareFile:'待上传文件',//EwayLocale.screen.prepareFile
			choseUploadFile:'请选择上传文件',//EwayLocale.screen.choseUploadFile
			nowUploadFile:'正在上传文件...',//EwayLocale.screen.nowUploadFile
			explorer: '浏览...',//EwayLocale.screen.explorer
			serverPath: '文件在服务器上的位置',//EwayLocale.screen.serverPath
			distanceSuccess:'远程创建成功.',//EwayLocale.screen.distanceSuccess
			distanceFailure:'远程创建失败.',//EwayLocale.screen.distanceFailure
			confirmDelete:'确定删除',//EwayLocale.screen.confirmDelete
			confirmExecute:'确定执行',//EwayLocale.screen.confirmExecute
			choseDeleteFile:'请选择要删除的文件.',//EwayLocale.screen.choseDeleteFile
			distanceExecuteSuccess:'远程执行成功.',//EwayLocale.screen.distanceExecuteSuccess
			distanceExecuteFailure:'远程执行失败.',//EwayLocale.screen.distanceExecuteFailure
			choseExecuteFile:'请选择要执行的文件.',//EwayLocale.screen.choseExecuteFile
			distanceExplorer:'远程浏览:',//EwayLocale.screen.distanceExplorer
			distanceExplorerFailure:'远程浏览失败.',//EwayLocale.screen.distanceExplorerFailure
			fileExist:'该文件已存在！',//EwayLocale.screen.fileExist
			nowUploadFile:'正在上传文件......',//EwayLocale.screen.nowUploadFile
			uploadSuccess:'上传成功.',//EwayLocale.screen.uploadSuccess
			yes: "续传",//EwayLocale.screen.yes
			no: "覆盖",//EwayLocale.screen.no
			cancel: "取消",//EwayLocale.screen.cancel
			choseFile:'未选择上传文件,请选择文件.',//EwayLocale.screen.choseFile
			returnFailure:'返回失败.',//EwayLocale.screen.returnFailure
			refreshFailure:'刷新失败.',//EwayLocale.screen.refreshFailure
			catalogExist:'搜索的目录不存在,请重新输入.',//EwayLocale.screen.catalogExist
			testBandWidth:'测试宽带异常.',//EwayLocale.screen.testBandWidth
			networkMaxSpeed:'网络最大接入速度为',//EwayLocale.screen.networkMaxSpeed
			minutes:'秒',//EwayLocale.screen.minutes
			amount:'相当于',//EwayLocale.screen.amount
			specialLine:'专线',//EwayLocale.screen.specialLine
			bandWidth:'宽带',//EwayLocale.screen.bandWidth
			handle:'正在处理......',//EwayLocale.screen.handle
			offServer:'与服务器断开连接.',//EwayLocale.screen.offServer
			submitingWaiting:'正在提交,请稍等...',//EwayLocale.screen.submitingWaiting
			ATMCheck:'正在ATM体检中...',//EwayLocale.screen.ATMCheck
			excellent:'优',//EwayLocale.screen.excellent
			fine:'良',//EwayLocale.screen.fine
			middle:'中',//EwayLocale.screen.middle
			bad:'差',//EwayLocale.screen.bad
			point:'分',//EwayLocale.screen.point
			checkFailure:'ATM体检失败,请重新操作.',//EwayLocale.screen.checkFailure
			checkVersionInfo:'查看版本信息',//EwayLocale.screen.checkVersionInfo
			versionInfo:'您要查看的版本信息如下:',//EwayLocale.screen.versionInfo
			ATMCVersion:'ATMC应用版本',//EwayLocale.screen.ATMCVersion
			monitorVersion: '监控客户端版本',//EwayLocale.screen.monitorVersion
			mergeload:'下载列表',//EwayLocale.screen.mergeload
			mergeDownLoad:'执行下载任务',//EwayLocale.screen.mergeDownLoad
			clickAddLoadFile:'单击添加到下载列表',//EwayLocale.screen.clickAddLoadFile
			removeFile:'删除文件',//EwayLocale.screen.removeFile
			addFileSuccess:'成功添加到下载列表!',//EwayLocale.screen.addFileSuccess
            maxDownLoadFileSize:'添加失败,下载列表中文件总大小不能超过200M.',//EwayLocale.screen.maxDownLoadFileSize
            exitDownLoadFile:'添加失败,该文件已添加到下载列表中.',//EwayLocale.screen.exitDownLoadFile
            removeSuccess:'删除成功.',//EwayLocale.screen.removeSuccess
            mustIncludeOneFile:'请至少添加一个要下载的文件.',//EwayLocale.screen.mustIncludeOneFile
            resetBackUp:'点击重新备份当天日志.',//EwayLocale.screen.resetBackUp
            backupAppLogsSuccess:'日志备份成功.',//EwayLocale.screen.backupAppLogsSuccess
            backupAppLogsFail:'日志备份失败.',//EwayLocale.screen.backupAppLogsFail
            backupLogSucList:'日备份日志成功设备列表.',//EwayLocale.screen.backupLogSucList
            backupLogFailList:'日备份日志失败设备列表.',//EwayLocale.screen.backupLogFailList

		}
	}
});
