Ext.apply(EwayLocale,{

	//**********************************************************/
	monitor:{
		summary:{
			title:'Status Overview',//EwayLocale.monitor.summary.title
			allSummary:'All',//EwayLocale.monitor.summary.allSummary
			appSummary:'ATMC',//EwayLocale.monitor.summary.appSummary
			modSummary:'Module',//EwayLocale.monitor.summary.modSummary
			boxSummary:'Cashbox',//EwayLocale.monitor.summary.boxSummary
			netSummary:'Network'//EwayLocale.monitor.summary.netSummary
		},
		devMonitor:{
			title:'Status Monitor',//EwayLocale.monitor.devMonitor.title 状态监控
			comboxStatus:{
				runStatus:'ATMC Status',//EwayLocale.monitor.devMonitor.comboxStatus.runStatus 运行状态
				modStatus:'Module Status',//EwayLocale.monitor.devMonitor.comboxStatus.modStatus 模块状态
				boxStatus:'Cashbox Status',//EwayLocale.monitor.devMonitor.comboxStatus.boxStatus 钞箱状态
				netStatus:'Network Status'//EwayLocale.monitor.devMonitor.comboxStatus.netStatus 网络状态
			},
			monitorState:'Filters',//EwayLocale.monitor.devMonitor.monitorState 监控状态
			showWay:'Show way',//EwayLocale.monitor.devMonitor.showWay 展示方式
			comboxShowWay:{
				summaryPattern:'Overview',//EwayLocale.monitor.devMonitor.comboxShowWay.summaryPattern
				matrixPattern:'Matrix',//EwayLocale.monitor.devMonitor.comboxShowWay.matrixPattern 矩形方式
				maxIconPattern:'Large Matrix',//EwayLocale.monitor.devMonitor.comboxShowWay.maxIconPattern 超大图标
				listPattern:'List',//EwayLocale.monitor.devMonitor.comboxShowWay.listPattern 列表方式
				boxPattern:'Cashbox'//EwayLocale.monitor.devMonitor.comboxShowWay.boxPattern 钞箱方式
			},
			noData:'No data',//EwayLocale.monitor.devMonitor.noData 无记录
			retainCardCount:'Retained Card Amount',//EwayLocale.monitor.devMonitor.retainCardCount 当前吞卡数量
			cash:{
				boxInitCount:'Initial Amount of Cashbox',//EwayLocale.monitor.devMonitor.cash.boxInitCount 钞箱初始金额
				boxCurrentCount:'Current Amount of Cashbox',//EwayLocale.monitor.devMonitor.cash.boxCurrentCount 钞箱当前金额
				cashboxLimit:'Cashbox Alarm Amount',//EwayLocale.monitor.devMonitor.cash.cashboxLimit 钞箱报警金额阈值
				initAmount:'Initial Amount',//EwayLocale.monitor.devMonitor.cash.initAmount 加钞总金额
				amount:'Amount left',//EwayLocale.monitor.devMonitor.cash.amount
				dispenseAmount:'Amount paid',//EwayLocale.monitor.devMonitor.cash.dispenseAmount 出钞总金额
				rejectAmount:'Amount invalid',//EwayLocale.monitor.devMonitor.cash.rejectAmount 废钞金额
				retractCount:'Time of cash retrieve',//EwayLocale.monitor.devMonitor.cash.retractCount 钞票回收次数
				minAmount:'Amount mini draw',//EwayLocale.monitor.devMonitor.cash.minAmount 最小取款金额
				boxId:'Cashbox ID',//EwayLocale.monitor.devMonitor.cash.boxId 钞箱标识
				type:'Cashbox Type',//EwayLocale.monitor.devMonitor.cash.type 钞箱类型
				initialCount:'Initial count',//EwayLocale.monitor.devMonitor.cash.initialCount 初始张数
				cashInCount:'Deposit count',//EwayLocale.monitor.devMonitor.cash.cashInCount 存款张数
				currentCount:'Count',//EwayLocale.monitor.devMonitor.cash.currentCount 当前计数
				noteValue:'Cashbox denomination',//EwayLocale.monitor.devMonitor.cash.noteValue 钞箱面值
				currency:'Cashbox currency',//EwayLocale.monitor.devMonitor.cash.currency 钞箱币种
				boxDetail:'Cashbox detail',//EwayLocale.monitor.devMonitor.cash.boxDetail 钞箱详情
				cimFull:'Cashbox full',//EwayLocale.monitor.devMonitor.cash.cimFull 存款钞满
				cdmEmpty:'Draw cash empty',//EwayLocale.monitor.devMonitor.cash.cdmEmpty 取款钞空
				cdmLow:'Draw cash low',//EwayLocale.monitor.devMonitor.cash.cdmLow 取款钞少
				low:'Cash empty',//EwayLocale.monitor.devMonitor.cash.low 钞少
				empty:'Cash empty',//EwayLocale.monitor.devMonitor.cash.empty 钞空
				cimAFull:'Deposit almost full',//EwayLocale.monitor.devMonitor.cash.cimAFull 存款钞将满
				cashFault:'Cashbox error',//EwayLocale.monitor.devMonitor.cash.cashFault 钞箱故障
				cashUnknow:'Cashbox unknown'//EwayLocale.monitor.devMonitor.cash.cashUnknow 钞箱未知

			},
			modStateGraphic:'Module status graphic',//EwayLocale.monitor.devMonitor.modStateGraphic 模块状态图示
			modGraphic:'Module graphic',//EwayLocale.monitor.devMonitor.modGraphic
			registerStatus:'Register Status',//EwayLocale.monitor.devMonitor.registerStatus 注册状态
			devModStatus:'Status of module',//EwayLocale.monitor.devMonitor.devModStatus 设备模块状态
			mod:{
				idc:'CardReader',//EwayLocale.monitor.devMonitor.mod.idc 读卡器
				jpr:'Log printer',//EwayLocale.monitor.devMonitor.mod.jpr 日志打印机
				cdm:'Draw module',//EwayLocale.monitor.devMonitor.mod.cdm 取款模块
				cim:'Deposit module',//EwayLocale.monitor.devMonitor.mod.cim 存款模块
				siu:'Sensor',//EwayLocale.monitor.devMonitor.mod.siu 传感器
				rpr:'Voucher printer',//EwayLocale.monitor.devMonitor.mod.rpr 凭条打印机
				pin:'Pin keyboard',//EwayLocale.monitor.devMonitor.mod.pin 密码键盘
				ttu:'Text terminal',//EwayLocale.monitor.devMonitor.mod.ttu 文本终端
				isc:'I.D.card scanner',//EwayLocale.monitor.devMonitor.mod.isc 身份证扫描仪
				icc:'Issue card module',//EwayLocale.monitor.devMonitor.mod.icc 发卡器
				fgp:'Fingerprints',//EwayLocale.monitor.devMonitor.mod.fgp 指纹仪
				healthy:'Healthy'//EwayLocale.monitor.devMonitor.mod.healthy 模块正常

			},
			remote:{
				control:'Remote control',//EwayLocale.monitor.devMonitor.remote.control 远程控制
				screen:'Remote screenshot',//EwayLocale.monitor.devMonitor.remote.screen 远程抓拍
				log:'Get logs',//EwayLocale.monitor.devMonitor.remote.log 提取电子日志
				net:'Check Network',//EwayLocale.monitor.devMonitor.remote.net 查看网络连接
				softwareList:'Get software list',//EwayLocale.monitor.devMonitor.remote.softwareList 获取软件列表
				powerOff:'PowerOff',//EwayLocale.monitor.devMonitor.remote.powerOff 关机
				closeWays:'Choose way of poweroff',//EwayLocale.monitor.devMonitor.remote.closeWays 请选择关机方式
				restart:'Reboot',//EwayLocale.monitor.devMonitor.remote.restart 重启
				restartWay:'Choose way of reboot',//EwayLocale.monitor.devMonitor.remote.restartWay 请选择重启方式
				logicOpen:'Open service',//EwayLocale.monitor.devMonitor.remote.logicOpen 开启服务
				logicClose:'Pause service',//EwayLocale.monitor.devMonitor.remote.logicClose 暂停服务
				remoteBrowser:'Browse',//EwayLocale.monitor.devMonitor.remote.remoteBrowser 远程浏览
				processList:'Check process',//EwayLocale.monitor.devMonitor.remote.processList 查看进程信息
				screenCamera:'Screen recording',//EwayLocale.monitor.devMonitor.remote.screenCamera 屏幕录制
				reset:'Force reset',//EwayLocale.monitor.devMonitor.remote.reset 强制复位
				remoteLook:'Check version application',//EwayLocale.monitor.devMonitor.remote.remoteLook 查看应用版本
				remoteCheckATM:'Physical of ATM',//EwayLocale.monitor.devMonitor.remote.remoteCheckATM ATM体检
				halfSer:'Half service',//EwayLocale.monitor.devMonitor.remote.halfSer 半功能
				healthy:'Healthy',//EwayLocale.monitor.devMonitor.remote.healthy 正常服务
				staff:'Maintenance',//EwayLocale.monitor.devMonitor.remote.staff 维护

				pFault:'ATMP error',//EwayLocale.monitor.devMonitor.remote.pFault 交易前置故障

				stop:'Stop',//EwayLocale.monitor.devMonitor.remote.stop 报停
				manualStop:'Stop manual',////EwayLocale.monitor.devMonitor.remote.manualStop 人工报停
				stopFault:'Pause serivce-module error',//EwayLocale.monitor.devMonitor.remote.stopFault 暂停服务
				stopCash:'Pause serivce-no cash',//EwayLocale.monitor.devMonitor.remote.stopCash 暂停服务-未加钞
				pauseSer:'Pause serivce',//EwayLocale.monitor.devMonitor.remote.pauseSer 暂停服务
				pauseCash:'Pause cash',//EwayLocale.monitor.devMonitor.remote.pauseCash   ----中文不明，我猜的----
				pauseSerUnknow:'Pause serivce-unkown',//EwayLocale.monitor.devMonitor.remote.pauseSerUnknow 未知原因暂停服务
				manaAndstaff:'Machine manager'//EwayLocale.monitor.devMonitor.remote.manaAndstaff 管机员
			},
			atmGroup:'Group',//EwayLocale.monitor.devMonitor.atmGroup 分组
			atmGroupTip:'GroupDetail',//EwayLocale.monitor.devMonitor.atmGroupTip
			solution:'Solution suggest',//EwayLocale.monitor.devMonitor.solution 建议解决方案
			faultDescription:'Module error description',//EwayLocale.monitor.devMonitor.faultDescription 模块故障描述
			fastChoose:'Fast choose',//EwayLocale.monitor.devMonitor.fastChoose 快捷选择
			init:'Initialization',//EwayLocale.monitor.devMonitor.init 初始化
			accTrans:'Transaction',//EwayLocale.monitor.devMonitor.accTrans 客户交易
			factureStaff:'Manufacturer model',//EwayLocale.monitor.devMonitor.factureStaff 厂商模式维护
			netHealthy:'Net healthy',//EwayLocale.monitor.devMonitor.netHealthy 网络正常
			netUnStable:'Net unstable',//EwayLocale.monitor.devMonitor.netUnStable 网络不稳定
			netFatal:'Net error',//EwayLocale.monitor.devMonitor.netFatal 网络故障
			filterManager: {
				title: 'FilterManager',//EwayLocale.monitor.devMonitor.filterManager.title
				add: 'CreateFilter',//EwayLocale.monitor.devMonitor.filterManager.add
				update: 'UpdateFilter',//EwayLocale.monitor.devMonitor.filterManager.update
				filterForm: {
					filterName: 'FilterName'//EwayLocale.monitor.devMonitor.filterManager.filterForm.filterName
				}
			}
		},
		business:{
			transaction:{
				card:'Card',//EwayLocale.monitor.business.transaction.card 交易卡号
				dateTime:'Time',//EwayLocale.monitor.business.transaction.dateTime 交易时间
				transCode:'Type',//EwayLocale.monitor.business.transaction.transCode 交易类型
				amt:'Amt',//EwayLocale.monitor.business.transaction.amt 交易金额
				currency:'currency',//EwayLocale.monitor.business.transaction.currency 交易币种
				transId:'Serialno',//EwayLocale.monitor.business.transaction.transId 交易流水号
				amtfield:'Amount scope',//EwayLocale.monitor.business.transaction.amtfield 金额范围
				toNum:'to',//EwayLocale.monitor.business.transaction.toNum 至
				transContainer:'Time scope',//EwayLocale.monitor.business.transaction.transContainer 交易时间段
				debitAccountOrCard:'Debit/Card',//EwayLocale.monitor.business.transaction.debitAccountOrCard 客户账号或者卡号
				creditAccountOrCard:'Credit/Card',//EwayLocale.monitor.business.transaction.creditAccountOrCard 对方账号或者卡号
				debitAccount:'Debit',//EwayLocale.monitor.business.transaction.debitAccount 客户帐号
				creditAccount:'Credit',//EwayLocale.monitor.business.transaction.creditAccount 对方账号
				localRet:'Local code',//EwayLocale.monitor.business.transaction.localRet ATMC本地代码
				hostRet:'Host code',//EwayLocale.monitor.business.transaction.hostRet 主机返回码
				userName:'User name',//EwayLocale.monitor.business.transaction.userName 用户姓名
				historyTransaction:{
					title:'Query histroy transaction '//EwayLocale.monitor.business.transaction.historyTransaction.title //历史交易查询
				},
				transactionMonitor:{
					title:'Transaction monitoring',//EwayLocale.monitor.business.transaction.transactionMonitor.title 实时交易监控
					begin:'Start',//EwayLocale.monitor.business.transaction.transactionMonitor.begin 开始监控
					stop:'Stop',//EwayLocale.monitor.business.transaction.transactionMonitor.stop 停止监控
					clear:'Clear screen'//EwayLocale.monitor.business.transaction.transactionMonitor.clear清屏
					}
			},
			blackList:{
				title:'BlackCard',//EwayLocale.monitor.business.blackList.title 黑名单卡管理
				black:'BlackCard',//EwayLocale.monitor.business.blackList.black 黑名单卡
				addBlack:'Add',//EwayLocale.monitor.business.blackList.addBlack 添加黑名单卡
				cardBank:'Bank',//EwayLocale.monitor.business.blackList.cardBank 所属银行
				importData:'Import',//EwayLocale.monitor.business.blackList.importData 批量导入
				addDate:'Add date',//EwayLocale.monitor.business.blackList.addDate 添加日期
				importTitle:'Bacth import',//EwayLocale.monitor.business.blackList.importTitle 批量导入黑名单卡
				importFile:'Import file',//EwayLocale.monitor.business.blackList.importFile 导入文件
				chooseFileRegex:'Please choose file to import,only .xls or .xlsx file is supported',//EwayLocale.monitor.business.blackList.chooseFileRegex 请选择导入文件,只支持.xls和.xlsx格式的文件
				fileRegex:'only .xls or .xlsx file is supported',//EwayLocale.monitor.business.blackList.fileRegex 只支持.xls和.xlsx格式的文件
				tempDownload:'Download temple',//EwayLocale.monitor.business.blackList.tempDownload 模版下载
				importNow:'Import',//EwayLocale.monitor.business.blackList.importNow 导入
				updateTitle:'Modify'//EwayLocale.monitor.business.blackList.updateTitle 更改黑名单卡信息
			},
			card:{
				title:'Retained Card',//EwayLocale.monitor.business.card.title 吞卡管理
				addTitle:'Add',//EwayLocale.monitor.business.card.addTitle 增加吞卡信息
				time:'Time',//EwayLocale.monitor.business.card.time 吞卡时间
				reason:'Reason',//EwayLocale.monitor.business.card.reason  吞卡原因
				destroy:'Destroy',//EwayLocale.monitor.business.card.destory 吞卡销毁
				cardHolder:'Bank holder',//EwayLocale.monitor.business.card.cardHolder 发卡行
				cardRegex:'Max length is ',//EwayLocale.monitor.business.card.cardRegex 允许的最大长度为
				beginEndDate:'Begin date can not be later than end date,please choose again',//EwayLocale.monitor.business.card.beginEndDate 吞卡起始日期不能大于吞卡截止日期,请重新选择
				orgBelongs:'Org belongs(Include process org)',//EwayLocale.monitor.business.card.orgBelongs 所属机构 (包含处理机构)
				beginTime:'Begin time ',//EwayLocale.monitor.business.card.beginTime 开始时间
				endTime:'End time',//EwayLocale.monitor.business.card.endTime
				accGetCard:'Customer get card',//EwayLocale.monitor.business.card.accGetCard 客户领卡
				transferCard:'Turnover',//EwayLocale.monitor.business.card.transferCard 卡片移交
				processOrg:'Process org',//EwayLocale.monitor.business.card.processOrg 处理机构
				type:'Type',//EwayLocale.monitor.business.card.type 吞卡类型
				manual:'Add manually',//EwayLocale.monitor.business.card.manual 手动添加
				auto:'Add automatically',//EwayLocale.monitor.business.card.auto 自动添加
				comboxStatus:{
					wait:'Wait',//EwayLocale.monitor.business.card.comboxStatus.wait 待领
					received:'Received',//EwayLocale.monitor.business.card.comboxStatus.received 已领
					destroy:'Destroy',//EwayLocale.monitor.business.card.comboxStatus.destroy 销毁
					bringed:'Bringed'//EwayLocale.monitor.business.card.comboxStatus.bringed 调出
				},
				treatmentPeople:'Processor',//EwayLocale.monitor.business.card.treatmentPeople 处理人员
				treatmentTime:'Time',//EwayLocale.monitor.business.card.treatmentTime 处理时间
				customerName:'Customer name',//EwayLocale.monitor.business.card.customerName 客户姓名
				customerPhone:'Telephone',//EwayLocale.monitor.business.card.customerPhone 客户电话
				customerPapers:'License code',//EwayLocale.monitor.business.card.customerPapers 客户证件号
				processCard:'Process',//EwayLocale.monitor.business.card.processCard 吞卡处理
				destroyCard:'Destroy',//EwayLocale.monitor.business.card.destroyCard 卡片销毁
				exportData:'Export',//EwayLocale.monitor.business.card.exportData 导出
				paperType:'License type',//EwayLocale.monitor.business.card.paperType 证件类型
				paperCode:'License code',//EwayLocale.monitor.business.card.paperCode 证件号
				idCard:'I.D card',//EwayLocale.monitor.business.card.idCard 身份证
				accountPaper:'Register',//EwayLocale.monitor.business.card.accountPaper 户口本
				drivePaper:'Drive License',//EwayLocale.monitor.business.card.drivePaper 驾驶执照
				passport:'Passport',//EwayLocale.monitor.business.card.passport 护照
				officer:'Officer License',//EwayLocale.monitor.business.card.officer 军官证
				soldier:'Soldier License',//EwayLocale.monitor.business.card.soldier 士兵证
				busnessPaper:'Juridicalperson License',//EwayLocale.monitor.business.card.busnessPaper 法人营业证
				busnessCode:'Juridicalperson code',//EwayLocale.monitor.business.card.busnessCode 法人代码证
				taxPaper:'Taxregistration License',//EwayLocale.monitor.business.card.taxPaper 税务登记证
				withDev:'With device'//EwayLocale.monitor.business.card.withDev 按设备
			},


			cashInit:{
				titile:'Load Cash',//EwayLocale.monitor.business.cashInit.titile 加钞信息查询
				uuId:'CashID',//EwayLocale.monitor.business.cashInit.uuId 加钞ID
				date:'Date',//EwayLocale.monitor.business.cashInit.date 加钞日期
				amt:'Amount',//EwayLocale.monitor.business.cashInit.amt 金额
				info:'Information',//EwayLocale.monitor.business.cashInit.info 加钞详细信息
				boxId:'CashboxID',//EwayLocale.monitor.business.cashInit.boxId 钞箱ID
				boxCurrency:'Currency',//EwayLocale.monitor.business.cashInit.boxCurrency 币种
				boxInitAmt:'Amount init',//EwayLocale.monitor.business.cashInit.boxInitAmt 初始金额
				lastAmt:'Amount left'//EwayLocale.monitor.business.cashInit.lastAmt 剩余金额
			},
			settlement:{
				title:'Settlement',//EwayLocale.monitor.business.settlement.title 清机信息查询
				deTitle:'Information',//EwayLocale.monitor.business.settlement.deTitle 清机详细信息
				settleId:'SettleID',//EwayLocale.monitor.business.settlement.settleId
				uuId:'CycleID',//EwayLocale.monitor.business.settlement.uuId 周期ID
				endAmt:'Endbox amount',//EwayLocale.monitor.business.settlement.endAmt 尾箱余额
				endDate:'Date settlement',//EwayLocale.monitor.business.settlement.endDate 结帐日期
				cimNum:'Deposit count',//EwayLocale.monitor.business.settlement.cimNum 存款笔数
				cdmNum:'Draw count',//EwayLocale.monitor.business.settlement.cdmNum 取款笔数
				totalNum:'Toatl',//EwayLocale.monitor.business.settlement.totalNum
				leftDate:'Date settlement',//EwayLocale.monitor.business.settlement.leftDate 结帐日期
				cimAmt:'Deposit amount',//EwayLocale.monitor.business.settlement.cimAmt 存款金额
				cdmAmt:'Draw amount',//EwayLocale.monitor.business.settlement.cdmAmt 取款金额
				tranAmt:'Total amount'//EwayLocale.monitor.business.settlement.tranAmt 交易总金额
			}
		/*}*/
		}
	},

	//**********************************************************/
	agent:{
		remote:{
			screen:{
				message:'info', //信息
				startcustom:'Start recording front-screen of customer', //开始录制客户前屏
				stopcustom:'Stop recording front-screen of customer',//停止录制客户前屏
				startadmin:'Start recording front-screen of manager',//开始录制管理后屏
				stopadmin: 'Stop recording front-screen of manager',//停止录制管理后屏
				startadvertise: 'Start recording advertisement-screen',//开始录制广告屏
				stopadvertise: 'Stop recording advertisement-screen',//停止录制广告屏
				startCameraDate: 'Recording start time',//开始录制时间
				stopCameraDate: 'Recording end time',//停止录制时间
				monitorType: 'Screen type', //屏幕类型
				fileNameClient: 'File name',//文件名称
				nowCamera:'Recording...',//正在录制
				finishCamera:'Recording finish',//完成录制
				videoLoad:'Downloading the video file to the server...',//正在将视频文件下载至服务端...
				stopManage:'Automatic stopped ,please contact the manager!',//自动停止.如需取得视频文件,请联系管理员!
				manage: 'Operater',	//操作
				loading:'DownLoad',//EwayLocale.agent.remote.screen.loading 下载
				screenCamera: 'Screen recording',//屏幕录制
			},
			discInfo: 'Disk info', //磁盘信息
			discName: 'Disk Pattern name',//磁盘分区名称
			fileSys: 'Disk file system',//磁盘文件系统
			totalSize: 'Disk size total',//磁盘总大小
			freeSize: 'Disk size usable',//磁盘可用空间大小
			networkInfo:'Net connection info',//网络连接信息
			conenctRate: 'Connection speed',//连接速率
			receivedByte: 'Bytes received',//接收到的字节数
			sendByte: 'Bytes sended',//发动的字节数
			loadData: 'Data loading,please wait...',//加载数据中，请稍候...
			refresh: 'Refresh',//刷新
			name: 'Name',//名称
			format: 'Format',//格式
			totalSize: 'Total Size',//总大小
			freeSize: 'Usable size',//可用空间
			refreshFailure:'Refresh failed!',//刷新失败
			back:'Back',//返回
			upload: 'upload',//上传
			Mkdir: 'New folder',//新建文件夹
			MkFile: 'New file',//新建文件
			MKcatalog:'New folder',//新建文件夹
			catalogName:'Folder name',//目录名称
			remove: 'Delete',//删除
			execute: 'Execute',//执行
			path: 'Path',//路径
			search: 'Search',//搜索
			size: 'Size',//大小
			fileSize:'File size：',//文件大小：
			lastTime: 'Last modify time',//最后修改时间
			clickLoadFile:'Click to download this file',//单击即可下载该文件
			loadFileSize:'Max size of download file is 300M',//下载文件 不能超过200M！
			nowLoadFile:'File downloading...',//正在下载文件......
			judgeLoad: '是否续传下载！',//是否续传下载！
			loadFailure:'Download failed！',//下载失败！
			number: 'Serial number',//编号
			programName: 'Application name',//程序名称
			version: 'Version number',//版本号
			publisher: 'Publishers',//发布商
			diskUsed: 'Disk useage',//磁盘使用
			softwayList: 'List of software installed',//软件安装列表
			networkInfo: 'Network info',//网络信息
			networkLinkStatus: 'Netwok connect status',//网络连接状态
			send: 'Sended',//已发送
			receive: 'Received',//已接收
			bite: 'byte:',//字节
			speed: 'Speed(Mbps):',//速度
			bandWidth: 'Broadband speed test',//宽带测速
			unit: 'Unit:MB',//单位：MB
			againTest: 'Test again',//重新测试
			impressionName: 'Impression name',//印象名称
			userName: 'User Name',//用户名
			memoryRate: 'Memory used',//内存使用
			systemProgressInfo:'System process information',//系统进程信息
			screenShotTime: 'Screen shot time',//截屏时间
			distanceScreen: 'Remote screenshot',//远程抓屏
			distanceExplorer: 'Remote browse',//远程浏览
			ATMExamination:'Physical ATM',//ATM体检
			checkATM: 'Physical again',//重新体检
			ATMExamInfo: 'Physical ATM detail',//ATM体检详情
			cpuIdle: 'Free rate of CPU',//CPU空闲率
			memoryIdle: 'Free rate of memory',//内存空闲率
			hardDiskIdle: 'Free rate of disk',//硬盘空闲率
			uploadFile: 'Upload file',//上传文件
			rules:'Can not contain:\/?*":<>|',//不能包含一下字符
			nowCreat:'Creating...',//正在新建
			nowPath: 'Current path',//当前路径
			confirm: 'Save', //保存
			reset: 'Reset',//重置
			back: 'Back',//返回
			prepareFile:'File wait for upload',//待上传文件
			choseUploadFile:'Please choose file to upload',//请选择上传文件
			nowUploadFile:'File uploading',//正在上传文件...
			explorer: 'Browse...',//浏览...
			serverPath: 'Path of file in server',//文件在服务器上的位置
			distanceSuccess:'Remote create successful.',//远程创建成功
			distanceFailure:'Remote create failed.',//远程创建失败
			confirmDelete:'Confirm delete',//确定删除
			confirmExecute:'Confirm execute',//确定执行
			choseDeleteFile:'Please choose file which you want to delete.',//请选择要删除的文件
			distanceExecuteSuccess:'Remote execute successful.',//远程执行成功
			distanceExecuteFailure:'Remote execute failed.',//远程执行失败
			choseExecuteFile:'Please choose file which you want to execute.',//请选择要执行的文件
			distanceExplorer:'Remote browse:',//远程浏览
			distanceExplorerFailure:'Remote browse failed.',//远程浏览失败
			fileExist:'File already exist!',//该文件已存在！
			nowUploadFile:'File uploading......',//正在上传文件......
			uploadSuccess:'Upload successful.',//上传成功
			yes: "Continue transfer",//续传
			no: "Override",//覆盖
			cancel: "Cancel", //取消
			choseFile:'No file selected for upload,please choose.',//未选择上传文件,请选择文件.
			returnFailure:'Return fail.',//返回失败
			refreshFailure:'Refresh failed.',//刷新失败
			catalogExist:'The folder does not exist,please input again',//搜索的目录不存在,请重新输入.
			testBandWidth:'Test broadband error.', //测试宽带异常
			networkMaxSpeed:'Max net speed is',//网络最大接入速度为
			minutes:'Second',//秒
			amount:'Like',//相当于
			specialLine:'Special line',//专线
			bandWidth:'Broadband',//宽带
			handle:'Processing......',//正在处理......
			offServer:'Lost connection from server.',//与服务器断开连接
			submitingWaiting:'Being submitted, please wait...',//EwayLocale.agent.submitingWaiting正在提交,请稍等
			ATMCheck:'Executing physical of ATM...',//正在ATM体检中
			excellent:'excellent',//优
			fine:'good',//良
			middle:'middle',//中
			bad:'bad',//差
			point:'point',//分
			checkFailure:'Execute physical of ATM failed,please try again.',//ATM体检失败,请重新操作
			checkVersionInfo:'View version information',//查看版本信息
			versionInfo:'The version information :',//您要查看的版本信息如下:
			ATMCVersion:'Version of ATMC application',//ATMC应用版本
			monitorVersion: 'Version of monintor client',//监控客户端版本
			mergeload:'Check the list of download',//查看下载列表
			mergeDownLoad:'Execute the task of download',//执行下载任务
			clickAddLoadFile:'Add the list of download on click',//单击添加到下载列表
			removeFile:'Remove the file from list',//删除

		}
	}
});
