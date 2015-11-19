Ext.apply(EwayLocale,{
	//**********************************************************/
	machine:{
		atmBrand : {
			title:'Device Brand',//品牌管理
			name: 'Name', //品牌名称
			country:'Country',//生产商国家或地区
			hotline1:'Hotline',//生产商热线1
			hotline2:'Hotline2',//生产商热线2
			address:'Address',//生产商地址
			status:'Status',//生产商状态
			comboxStatus:{
				provider:'Supplier',//设备供应
				maintance:'Service Provider'//设备服役
			}
		},
		atmCatalog:{
			title:'Device Catalog',//EwayLocale.machine.title ATM分类
			name:'Name',//EwayLocale.machine.atmCatalog.name 分类名称
			note:'Description',//备注
			addTitle:'Add catalog',//增加ATM分类信息
			updateTitle:'Update Catalog', //更改ATM型号信息
			number:'Code',//EwayLocale.machine.atmCatalog.number 编号
		},
		atmGroup : {
			terminalId:'Terminal ID', //设备号
			ip: 'IP',//设备IP地址
			orgName:'Bank',//
			devTypeName:'Device Type',//设备型号
			devVendorName:'Device Brand',//设备品牌
			devCatalogName:'Device Catalog',//设备类型
			devGroupName: 'Device Group',//设备分组
			status:'Device Status',//设备状态
			comboxStatus:{
				dredge:'Dredge',//开通
				open:'Open',//启用
				close:'Close'//close
			},
			awayFlag:'AwayFlag',//离行标志
			comboxAwayFlag:{
				inBank:'Inside bank',//在行自助服务区
				outBank:'Outside bank',//离行自助银行
				clickBank:'Alone out-bank self-service'//单机离行自助服务点
			},
			devServiceName:'Maintenance Provider',//设备维护商
			cashboxLimit:'Cashbox Alarm Limit',//钞箱报警金额
			installDate:'Install Date',//安装日期
			address:'Address',//地址
			gourpDev:'Group<-->Device',//分组<-->设备
			addTitle: 'Add Group Info',//增加设备组信息
			groupName:'Group Name',//组名
			note:'Description',//备注
			updateTitle:'Update Group Info'//更改设备组信息
		},
		atmModule:{
			moduleName:'Module Name',//模块名称
			note:'Description',//备注
			atmModules:'ATM module'	//ATM模块
		},
		atmMove:{
			title:'Move Manager',//移机管理
			moveDev:'Move machine',//移机
			moveDevRec:'Move machine and generate the move record',//移动设备并产生移机记录
			moveRecordInfo:'Move machine record',//移机记录信息
			waitMove:'Wait for move machine',//待移动的机器
			terminalId:'Terminal Id',//设备号
			address:'Source address',//源地址
			orgName:'Source org',//源机构
			targetAddress:'Target address',//目标地址
			targetOrganization:'Target organization',//目标机构
			targetPerson:'目标机构负责人',//目标机构负责人
			responsibility:'负责人',//负责人
			destPerson:'源机构负责人',//源机构负责人
			date:'date',//日期
			recoverDate:'Recover date',//恢复时间
			notice:'remark',//备注
			sAddress:'Address',//所属地址
			sOrgName:'Organization',//所属机构
			to:'to'//至
		},
		atmRuntimeInfo:{
			exportName:'Export', //导出
			exportDateRangeText:'Start time can not be later than end time',//开始时间不能大于结束时间
			terminalId:'Terminal Id',//终端号
			terminalIp:'IP',//终端IP
			startDate:'Begin time',//开始时间
			endDate:'End time',//结束时间
			exportLast30: 'Export last 30 days info',//导出最后30天汇总信息
			terminalId:'terminalId',//编号
			netIp:'Ip',//网络地址
			msgCollect:'Collect info of customservice'//客服信息采集
		},
		atmType:{
			title:'Device Type',//设备型号
			atmName:'Device Type',//ATM型号
			name:'Name',//设备型号
			devVendorName:'Brand', //所属品牌
			devCatalogName:'Catalog',//所属类型
			devTerminalName:'Type',//所属型号
			no:'Code',//编号
			cashtype:'Cash Flag',//非现金标志
			iscash:'Cash',//现金
			nocash:'Not Cash',//非现金
			modules:'Module contains'
		},
		device:{
			title:'Deivce Info',//设备信息管理
			devDetailInfo:'Module Info',//设备模块详细信息
		    IDC:'Card reader(IDC)',
			JPR:'Log printer(JPR)',
			CDM:'Draw module(CDM)',
			SIU:'Sensor module(SIU)',
			CIM:'Deposit module(CIM)',
			TTU:'Text terminal(TTU)',
			RPR:'Voucher printer(RPR)',
			PIN:'Pin keyboard(PIN)',
			CDMInfo:'Draw module（CDM）property',
			hasStack:'是否具有暂存器',
			hasShutter:'Shutter door exist',//是否具有shutter门
			canRetract:'Ability of recovery', //是否具有回收能力
			canDetectCashTaken:'Check if cash taked',//是否探测钞币被拿走
			canTestPhysicalUnits:'Test physical unit',//是否能测试物理单元
			maxDispensBills:'Get max count of gug cash in each trans',//获取单笔最大挖钞张数
			logicalUnits:'Count of logic cashbox',//逻辑钞箱个数
			physicalUnits:'Count of physical cashbox',//物理钞箱个数
			currency:'Currency supported count',//支持的币种类别总个数
			currencies:'Currency category supported ',//支持的币种类别
			exponents:'index',//指数
			
			CIMInfo:'Deposit module (CIM)property',//存款模块
			canEscrow:'是否具有暂存器X',

			shutterControlSupported:'Support shutter door',//是否支持控制shutter门
			maxAcceptItems:'Max count of inspect cash in each trans',//单笔最大验钞张数
			canDetectCashInserted:'Check if cash bringed',//是否能探测钞票放入
			canDetectCashTaken:'Check if cash taked',//是否能探测钞票被取走
			retractAreas:'Recovery position',//回收位置


			IDCInfo:'Card reader(IDC) property',//读卡器模块(IDC)属性信息
			variant:'Card reader type',//读卡器类型
			canEjectCard:'Ability of quit card',//是否具有退卡能力
			trackJisiiRead:'Ability of read TrackJisii',//是否具有TrackJisii读能力
			track1Read:'Ability of read first track',//是否具有读一磁道数据能力
			track2Read:'Ability of read second track',//是否具有读二磁道数据能力
			track3Read:'Ability of read third track',//是否具有读二磁道数据能力
			canCapture:'Ability of retain card',//是否具有吞卡能力
			binCapacity:'Max count of retain card',//最大吞卡张数
			security:'Safety support',//是否具有安全支持
			trackJisiiWrite:'Ability of write TrackJisii',//是否具有TrackJisii写能力
			track1Write:'Ability of write first track',//是否具有写一磁道数据能力
			track2Write:'Ability of write first track',//是否具有写二磁道数据能力
			track3Write:'Ability of write first track',//是否具有写三磁道数据能力
			

			JPRInfo:'Log printer(JPR) property',
			canEject:'Ability of quit paper',//是否具有退纸能力
			canCapture:'Ability of recovery',//是否具有回收能力
			canStack:'是否具有暂存能力',//是否具有暂存能力

			PINInfo:'Pin keyboard(PIN) property',
			canEBC:'EBC',//能否EBC
			canCBC:'CBC',//能否CBC
			canMAC:'MAC',//能否MAC
			canRSA:'RSA',//能否RSA
			canVerifyVISA:'Inspect VISA',//能否验证VISA
			canVerifyDES:'Inspect DES',//能否验证DES
			functionKeys:'Function support',//功能键支持
			canTripleEBC:'Multi EBC support',
			canTripleCBC:'Multi CBC support',
			canTripleMAC:'Multi MAC support',
			canTripleCFB:'Multi CFB support',
			canVerifyECB:'Inspect ECB',
			canDESOffset:'DeS shifting',

			RPRInfo:'Voucher printer(RPR) property',
			canEject:'Ability of quit paper',//是否具有退纸能力
			canCapture:'Ability of recovery',//是否具有回收能力

			canStack:'是否具有暂存能力',
			maxRetract:'Max count of recovery paper',//最大回收张数

			SIUInfo:'SIU property',//SIU能力属性信息
			operatorSwitchSupported:'Operator swicth support',//是否支持操作员开关
			cabinetSupported:'Ability of induce  behindDoor open support',//是否支持后盖门打开传感能力
			safeSupported:'Ability of induce safeDoor open support',//是否支持安全门打开传感能力
			indicatorSupported:'Ability of induce closeto support',//是否支持靠近传感能力
			guidelightIdcSupported:'Ability of card insert light support',//是否支持插卡指示灯能力
			guidelightCdmSupported:'Ability of draw light  support',//是否支持取款指示灯能力
			guidelightReceiptSupported:'Ability of voucher print light support',//是否支持凭条打印指示灯能力
			guidelightCimSupported:'Ability of deposit light support',//是否支持存款指示灯能力


			TTUInfo:'Text terminal (TTU) property',
			alphanumericKeysPresent:'Ability of character/number input support',//是否支持字母数字键输入
			numericKeysPresent:'Ability of number input support',//是否支持数字键输入
			displayLightPresent:'Ability of number adjust screen-brightness support',//是否支持屏幕亮度调节
			cursorSupported:'Mouse support',//支持鼠标
			resolutionX:'Resolution of cross axle',//横轴分辨率
			hexadecimalKeysPresent:'Hexadecimal input support',//是否支持十六进制键输入
			keyboardLockPresent:'Lock keyboard support',//是否支持键盘锁定
			formsSupported:'Table support',//是否支持表格
			resolutionY:'Resolution of vertical axle',//纵轴分辨率

			comStatus:'Manufacturer',//厂商状态信息
			hwCode:'Fault code',//厂商故障码
			CDMStatus:'Draw module(CDM) status',//取款模块(CDM)状态信息
			cashUnits:'Cashbox',//钞箱状态
			safeDoor:'SafeDoor',//安全门状态
			intermediateStacker:'暂存器状态',
			outBox:'Cashbox draw',//取款钞箱
			pcuId:'Relationship between physical and logic cashbox',//物理逻辑钞箱对应关系
			cuId:'Logic cashboxID',//逻辑钞箱ID
			cuCurrency:'Logic cashbox currency',//逻辑钞箱币种
			cuCurrentCount:'Count of logic cashbox currently',//逻辑钞箱当前张数
			cuInitialCount:'Count of logic cashbox initially',//逻辑钞箱初始张数
			cuRejectCount:'Count of logic cashbox rejected',//逻辑钞箱reject张数
			cuNoteValue:'Denomination of logic cashbox',//逻辑钞箱面值
			cuBinStatus:'Logic cashbox status',//逻辑钞箱状态
			puId:'Physical cashbox ID',//物理钞箱ID
			puPosName:'Name of physical cashbox',//物理钞箱位置名称
			puBinStatus:'Physical cashbox status',//物理钞箱状态
			puCurrentCount:'Count of physical cashbox currently',//物理钞箱当前张数
			puInitialCount:'Count of physical cashbox initially',//物理钞箱初始张数
			puRejectCount:'Count of physical cashbox rejected',//物理钞箱Reject张数
			cuBinType:'Logic cashbox status',//逻辑钞箱类型

			CIMStatus:'Deposit module(CIM) status',
			baffle:'Baffle',//挡板状态
			inOutPositionStatus:'Transfer status',//传输状态
			inBox:'Deposit cashbox',//存款钞箱
			puCashInCount:'Count of physical cashbox in',//物理钞箱入钞张数
			pcuId:'Relationship between physical and logic cashbox',//物理钞箱与逻辑钞箱对应关系
			cuType:'Logic cashbox type',//逻辑钞箱类型
			cuBinStatus:'Logic cashbox status',//逻辑钞箱状态
			cuCurrentCount:'Count of logic cashbox',//逻辑钞箱当前张数
			cuCurrency:'Currency of logic cashbox',//逻辑钞箱币种
			cuNoteValue:'Denomination of logic cashbox',//逻辑钞箱面值

			IDCStatus:'Card reader (IDC) status',
			media:'Media',//媒体状态
			retainBin:'Recovery box status',//回收盒状态
			cards:'Recovery box count',//回收盒数量

			JRPStatus:'Log printer (JPR) status',
			supplyLevel:'Log printer status',
			ink:'Ink',//墨水
			toner:'Ribbon',//色带

			PINStatus:'Pin keyboard(PIN) status',

			RPRStatus:'Voucher printer(RPR)status',//凭条打印机模块(RPR)状态信息
			bin:'Recovery unit status',//回收单元状态

			SIUStatus:'SIU status',//SIU能力状态信息
			vandalShield:'Shield',
			operatorSwitch:'Operate butoon status',
			ambientLight:'Environment light',//环境灯状态
			cabinet:'Box door',//箱门状态
			safe:'Safe door',//安全门状态
			idcGuidelight:'Guide card-insert light',//插卡导引灯状态
			cdmGuidelight:'Guide draw light',//取钞引导指示灯状态
			receiptGuidelight:'Guide voucher light',//凭条导引灯状态
			cimGuidelight:'Guide CIM light',//CIM导引灯状态

			TTUStatus:'Text terminal(TTU) status',//文本终端单元(TTU)状态信息

			devPerson:'Deivce person',//设备人员信息
			devModuleMsg:'Device module property',//设备模块属性信息
			devBasicMsg:'Basic info',//设备基本信息
			devTailMsg:'Detail',//设备详细信息
			managePerson:'Manager',//管机员
			maintainPerson:'Maintainer',//维护员
			name:'Name',//姓名
			mobile:'Mobile',//手机
			phone:'Phone',//固定电话
			email:'Email',//邮件地址
			deviceBasicInfo:'Additional info',//设备基本信息
			lineLogo:'AwayFlag',//
			alarmRateRMB:'Alarm cashbox(RMB)',//钞箱报警金额
			operation:'Service mode',//经营方式
			ipAddress:'IP address',//IP地址
			swallowCard:'Count of retain card',//吞卡张数
			alarmRateHKD:'Alarm cashbox(HKD)',//钞箱报警金额(港币)
			adminPhone:'Manager(mobile)',//管理员(手机号)
			maintainPhone: 'Maintainer(mobile)',//维护员(手机号)
			log:'Flag',//钞箱标识
			style: 'type',//钞箱类型
			status: 'Status',//钞箱状态
			initailnumber: 'Init count',//初始张数
			postnumber: 'Deposit count',//存款张数
			currentnumber: 'Current count',//当前钞箱张数
			facevalue: 'Denomination',//钞箱面值
			currency: 'Currency',//钞箱币种
			systemHardwareInfo: 'Hardware & Software',//系统软硬件信息
			moduleVersionInfo:'Versions of Module(Real-time)',//模块硬件版本信息（实时）
			devModuleStatusInfo: 'Module Status(Real-time)',//设备模块状态（实时）
			devModuleAttributeInfo: 'Module Property(Real-time)',//设备模块属性信息（实时）

			remoteControl: 'Control',//远程控制
			collectJPR:'Logs getting',//提取日志
			remoteScreen:'Screenshot',//远程抓屏
			processCheck:'View process',//进程查看
			remoteExplorer:'Browse',//远程浏览
			netWorkLink:'Connect',//网络连接
			remoteRestart:'Reboot',//远程重启

			progressTip:'Progress tip',//进度提示
			updateProBar:'This is generate with dynamic update',//这是通过动态更新内容形成的进度条
			currentProcess:'Progress currently',//当前进度


			restartApply: 'Restar app',//重启应用
			confirmRestartApply:'Restar app?',//确定要重启应用？
			nowRestartApply:'Restarting',//正在重启应用
			restartApplySuc:'Restarting app successful!',//成功重启该设备应用
			restartApplyFail:'Restarting app failed!',//重启应用失败


			restartDrive:'Restar drivers',//重启硬件驱动
			confirmRestartDrive:'Restar drivers?',//确定要重启硬件驱动？
			nowRestartDrive:'Restarting',//正在重启硬件驱动
			restartDriveSuc:'Restarting drivers successful!',//成功重启该设备硬件驱动
			restartDriveFail:'Restarting drivers failed!',//重启硬件驱动失败！

			restartOS:'Restar os',//重启操作系统
			confirmRestartOS:'Restar os?',//确定要重启操作系统
			nowRestartOS:'Restarting',//正在重启操作系统
			restartOSSuc:'Restarting os successful!',//成功重启该设备操作系统
			restartOSFail:'Restarting os failed!',//重启操作系统失败

			remoteShutdown:'Shutdown',//关机
			shutdownApply:'Shutdown app',//关闭应用
			confirmShutdownApply:'Shutdown app?',//确定要关闭应用？
			nowShutdownApply:'Shutting down',//
			shutdownApplySuc:'Shutdown app successful!',//成功关闭该设备应用
			shutdownApplyFail:'Shutdown app failed!',//关闭该设备应用失败

			shutdownDrive:'Shutdown drivers',//关闭硬件驱动
			confirmShutdownDrive:'Shutdown drivers?',//确定要关闭硬件驱动？
			nowShutdownDrive:'Shutting down',//正在关闭硬件驱动
			shutdownDriveSuc:'Shutdown drivers successful!',//成功关闭硬件驱动
			shutdownDriveFail:'Shutdown drivers failed!',//关闭硬件驱动失败！

			shutdownOS:'Shutdown os',//关闭操作系统
			confirmShutdownOS:'Shutdown os?',//确定要关闭操作系统？
			nowShutdownOS:'Shutting down',//正在关闭操作系统
			shutdownOSSuc:'Shutdown os successful!',//成功关闭该设备操作系统
			shutdownOSFail:'Shutdown os failed!',//关闭操作系统失败！
			getSoftwareList:'Get software installed info',//获取软件安装列表
			forceReset:'Reset force',//强制复位
			openService:'Open service',//开启服务
			pauseService:'Pause service',//暂停服务
			checkStatus:'Test status',//状态检测

			remoteBrowseDisk:'Browse',//远程浏览

			sysHardwareInfo:'Hardware info',//系统硬件信息
			diskMem:'Disk size',//硬盘大小
			biosVersion:'Bios version',//Bios版本
			biosVendor:'Bios producer',//Bios厂商
			biosReleaseDate:'Bios date',//Bios发布日期
			memorySize:'Total memory',//内存总数
			memoryUsed:'Used memory',//已使用内存
			memoryFree:'Free memory',//空闲内存
			memoryPercent:'Use rate memory',//
			cpuItemID:'CPU info',//cpu信息
			cpuFrequency:'CPU(MHz)',//CPU频率(MHz)
			cpuVendor:'CPU producer',//CPU的厂商
			cpuModel:'CPU type',//CPU的类别
			cacheSize:'Count of cache storage',//缓冲存储器数量
			totalCores:'CPU cores',//CPU核数
			userUsePercent:'Rate user used',//用户使用率
			sysUsePercent:'Rate system used',//系统使用率
			idlePercent:'Rate free current',//当前空闲率
			combinedPercent:'Rate total used',//
			diskItemID:'Disk info',//磁盘信息
			diskName:'Pattern name',//磁盘分区名称
			diskFileSys:'File system',//磁盘文件系统
			diskTotalSize:'Total size',//磁盘总大小
			diskFreeSize:'Total free size',//磁盘可用空间大小
			sysSoftInfo:'Software info',//系统软件信息
			OSID:'OS ID',//操作系统ID
			OSDescription:'OS Description',//OS描述
			OSType:'OS type',//OS类型
			sysPatchLevel:'OS patch level',//系统补丁级别
			chkCashData:'BV-version',//验钞数据版本
			OSVendor:'OS producer',//OS供应商
			OSVendorName:'OS supplier',//OS供应商名
			sysVersion:'OS version',//系统版本号
			devAddress:'Address',//设备地址
			basicInfo:'Additional Info',//基本信息
			virtual:'Virtual teller No.',//虚拟设备号
			serial:'Serial number',//设备序列号
			carrier:'Carrieroperator',//运营商
			moneyOrg:'Cash org',//加钞机构
			costInterest:'Rate of fund cost',//资金成本利率
			atmcSoft:'atmc software',//atmc软件
			spType:'sp type',//厂商sp类型
			column:'Date',//日期信息
			buyDate:'Buy date',//设备购买日期
			installDate:'Installation Time',//设备安装日期
			startDate:'Start date',//设备启用日期
			stopDate:'Stop date',//设备停用日期
			expireDate:'Guaranteed date',//保修到期日期
			daliyOpen:'Start time everyday',//每日开机时间
			openTimeHour:'Hour',//时
			openTimeMinute:'Minute',//分
			openTimeSecond:'Second',//秒
			daliyClose:'Close time everyday',//每日关机时间
			lastPmDate:'Last check date',//上次巡检日期
			expirePmDate:'Next check date',//巡检到期日期
			costInfo:'Expenses',//费用信息
			price:'Cost',//入账成本(元)

			
			notCashSignal:'Cash Flag',//非现金标志
			cash: 'Cash',//现金
			notCash:'Not Cash',//非现金
			installStyle: 'Installation Choice',//安装方式
			crossWall: 'Through the wall',//穿墙
			mainRoom: 'Lobby',//mainRoom
			netType: 'Net type',//网络类型
			wired: 'Wired',//有线
			wireless: 'Wireless',//wireless
			wiredAndWireless: 'Wired & Wireless',//wiredAndWireless
			onBankSignal:'Installation Location',//在行离行标志
			inBank:'Line',//在行自助服务区
			outBank:'Off-line',//离行自助银行
			clickBank:'Half Off-line',//单机离行自助服务点
			managePerson:'Manager',//管机员
			maintainPerson:'Maintence Staff',
			to:'至',//to
			range: 'Range 1-100 years',//范围1－－100年
			roleDescription:'Description',//角色描述
			roleName:'Name',//角色名称



			devices:'Device',//设备
			configuration:'Setting',//配置信息
			spVersion:'SP',//SP 版本
			notSupport:'Unsupport',//不支持
			drive:'Driver',//驱动
			firmway: 'Firmware',//固件
			noDevice:'No device',//无设备
			devTypeInfo: 'Device Type',//设备型号信息

			devInfo:'Device info',//设备信息
			unable:'Can not',//不可以
			able:'Can',//能

			addDevInfo:'Additional equipment information',//EwayLocale.machine.device.addDevInfo增加设备信息
			effectiveDate:'effective date',//EwayLocale.machine.device.effectiveDate生效日期
			changeDevInfo:'Changing the Device Information',//EwayLocale.machine.device.changeDevInfo更改设备信息
			devManage:'Device Management',//EwayLocale.machine.device.devManage设备管理
			efficientDev:'Device Information in force',//EwayLocale.machine.device.efficientDev已生效设备信息
			unEfficientDev:'Device information not active',//EwayLocale.machine.device.unEfficientDev未生效设备信息
			person:{
				week:'Week',//EwayLocale.machine.device.person.week星期
				Mon:'Monday',//EwayLocale.machine.device.person.Mon星期一
				Tues:'Tuesday',//EwayLocale.machine.device.person.Tues星期二
				Wed:'Wednesday',//EwayLocale.machine.device.person.Wed星期三
				Thur:'Thursday',//EwayLocale.machine.device.person.Thur星期四
				Fri:'Friday',//EwayLocale.machine.device.person.Fri星期五
				Sat:'Saturday',//EwayLocale.machine.device.person.Sat星期六
				Sun:'Sunday',//EwayLocale.machine.device.person.Sun星期日
				openClose:'Power On / Off',//EwayLocale.machine.device.person.openClose开机/关机
				Open:'Power',//EwayLocale.machine.device.person.Open开机
				Close:'Shutdown'//EwayLocale.machine.device.person.Close关机
			},
		},
		param:{
			paramKey:'Parameter',//EwayLocale.machine.param.paramKey 参数
			paramValue:'Value',//EwayLocale.machine.param.paramValue参数值
			classify:'Type',//EwayLocale.machine.param.classify 类型
			paramType:'Type',//EwayLocale.machine.param.paramType 参数类型
			modifyFlag:'Can be Modify?',//EwayLocale.machine.param.ModifyFlag 是否可以修改
			comboxClassify:{
				unableUpdate:'Yes',//不可修改
				ableUpdate:'No'//可以修改
			},
			description:'Description',//EwayLocale.machine.param.description 参数信息描述
			systemCon:'System Setting',//EwayLocale.machine.param.systemCon 系统配置
			updateSystemCon:'Update system Setting'//EwayLocale.machine.param.updateSystemCon 更改系统配置
		},
		quittingNotice:{
			addCloseMsg:'Add Report Stop',//增加报停信息
			updateCloseMsg:'Udpate stop',//
			dateRangeText:'Recover date can not be earlier than stop date,please choose again',//恢复日期不能小于等于停止日期,请重新选择
			click:'Click query to choose device',//请点击查询，选择设备
			stopTime:'Stop time',//停机时间
			openTime:'Recover time',//恢复时间
			currentStatus:'Current status',//当前状态
			closeType:'Type',//停机类型
			responsibilityName:'Personincharge',//停机负责人
			stopReason:'Reason',//停机原因
			address:'Address',//所属地址
			selectDev:'Please select the device which you want to stop',//选择需要报停的设备
			to:'to',//至
			stopType:'Type',//停机类型
			comboxStopType:{

				recess:'Holiday',//放假
				fit:'Decorate',//装修
				power:'Powercut',
				devFailue:'Error never fix',//设备故障未修复
				other:'Other'	//其他
			},
			setTime:'Set time',//
			closeManage:'Stop manager'//报停管理
		}
	},
	atmLog:{
		dayBackup:'Back-up log result today',//EwayLocale.atmLog.dayBackup 当日备份结果
		whole:'All',//EwayLocale.atmLog.whole 所有
		execute:'Executing',//EwayLocale.atmLog.execute 执行中
		unKnownFail:'Failed with unknown reason',//EwayLocale.atmLog.unKnownFail 未知原因失败
		logDate:'Log date',//EwayLocale.atmLog.logDate 日志日期
		backupResult:'Back-up result',//EwayLocale.atmLog.backupResult 备份结果
		checkFailInfo:'Check detail of back-up failed',//EwayLocale.atmLog.checkFailInfo 查看备份失败详情
		checkSucInfo:'Check detail of back-up successful',//EwayLocale.atmLog.checkSucInfo 查看备份成功详情
		backupSucAmount:'Count of machine back-up successful',//EwayLocale.atmLog.backupSucAmount 备份成功台数
		backupFailAmount:'Count of machine back-up failed',//EwayLocale.atmLog.backupFailAmount 备份失败台数
		backupAllAmount:'Total count',//EwayLocale.atmLog.backupAllAmount 总备份台数
		logBackupSta:'Back-up log total',//EwayLocale.atmLog.logBackupSta 日志备份统计
		dailyBackup:'Back-up task everyday',//EwayLocale.atmLog.dailyBackup 每日备份任务
		lastDoDate:'LastExecute Date',//EwayLocale.atmLog.lastDoDate
		getLog:'GetLog',//EwayLocale.atmLog.getLog
		backupDate:'Back-up date',//EwayLocale.atmLog.backupDate 备份日期
		dayBackupResult:'Back-up result today',//EwayLocale.atmLog.dayBackupResult 当日备份结果
		backupProcess:'Backing-up',//EwayLocale.atmLog.backupProcess 正在备份
		backupSuccess:'Back-up successful',//EwayLocale.atmLog.backupSuccess 备份成功
		backupError:'Back-up error',//EwayLocale.atmLog.backupError 备份错误
		logDevAccount:'Log back-up machine count total',//EwayLocale.atmLog.logDevAccount 日志设备数量累计
		reform:'Reform',//EwayLocale.atmLog.reform 重做
		busLogAnalysis:'Business log analysis',//EwayLocale.atmLog.busLogAnalysis 业务日志分析
		selectAnalysis:'Please choose the log file which you want to analyse, the result will show in Excel file',//EwayLocale.atmLog.selectAnalysis 请选择需要分析的日志文件，分析的结果将以Excel表格文件导出
		selectLog:'Choose log',//EwayLocale.atmLog.selectLog 选择日志
		pleaseDownload:'Please download',//EwayLocale.atmLog.pleaseDownload 请下载
		appLogDownload:'Download app logs',//EwayLocale.atmLog.appLogDownload 应用日志下载
		lastBackupTime:'Last back-up time',//EwayLocale.atmLog.lastBackupTime 最后一次备份时间
		noBegin:'noBegin',//EwayLocale.atmLog.noBegin 未开始
		noLog:'No log',//EwayLocale.atmLog.noLog 无日志
		connectFail:'Connect fail',//EwayLocale.atmLog.connectFail 连接失败
		fileSize:'File size',//EwayLocale.atmLog.fileSize 文件大小
		searchIllegal:'Query option has illegal input,can not export.'//EwayLocale.atmLog.searchIllegal 查询项中存在不合法的输入,不能导出.
	},
	//**********************************************************/
	card:{
		cardNum:'Card num',//EwayLocale.card.cardNum 卡号
		onlyNumber:'Just for numbers,13-19 numbers',//EwayLocale.card.onlyNumber 只能输入数字,13-19位
		cardStatus:'Status',//EwayLocale.card.cardStatus 卡片状态
		eatCardTime:'Time',//EwayLocale.card.eatCardTime 吞卡时间
		IDType:'License type',//EwayLocale.card.IDType 证件类型
		customerName:'CustomerName',//EwayLocale.card.customerName 客户姓名
		customerPapers:'CustomerPapers',//EwayLocale.card.customerPapers 客户证件号
		customerPhone:'CustomerPhone',//EwayLocale.card.customerPhone 客户电话
		endData:'EndData',//EwayLocale.card.endData 吞卡截止日期
		startData:'StartData',//EwayLocale.card.startData 吞卡起始日期
		add:'Add',//EwayLocale.card.add增加
		dell:'Delete'//EwayLocale.card.dell删除
	},
	plan:{
		addPlan:'Add Plan',//EwayLocale.machine.plan.addPlan 增加方案
		name:'Name',//EwayLocale.machine.plan.name 名称
		startDate:'Start time',//EwayLocale.machine.plan.startDate 有效开始时间
		endDate:'End time',//EwayLocale.machine.plan.endDate 有效结束时间
		terminalId:'Code',//EwayLocale.machine.plan.terminalId 编号
		cashboxLimit:'Alarm cashbox(unit:piece)',//EwayLocale.machine.plan.cashboxLimit 钞箱报警金额(单位：张数)
		perToDev:'Person<-->Device',//EwayLocale.machine.plan.perToDev 人员<-->设备
		changePlan:'Modify plan',//EwayLocale.machine.plan.changePlan 更改方案
		servicePlan:'Start up plan',//EwayLocale.machine.plan.servicePlan 开机方案
	},
	serviceplan:{
		name:'Programme Name',//EwayLocale.machine.serviceplan.name方案名称
		machineQuantity:'Number of devices',//EwayLocale.machine.serviceplan.machineQuantity设备数量
		state:'Status',//EwayLocale.machine.serviceplan.state状态
		openDate:'Effective boot time',//EwayLocale.machine.serviceplan.openDate有效开机时间
		closeDate:'Effective shutdown time',//EwayLocale.machine.serviceplan.closeDate有效关机时间
		createDateTime:'Created',//EwayLocale.machine.serviceplan.createDateTime创建时间
		date:'Date',//EwayLocale.machine.serviceplan.date日期
		week:'Week',//EwayLocale.machine.serviceplan.week星期
		inportLinkedMachine:'Import related equipment',//EwayLocale.machine.serviceplan.inportLinkedMachine导入关联设备
		selectFile:'Select the file',//EwayLocale.machine.serviceplan.selectFile选择文件
		placeUploadingResource:'Please upload resources',//EwayLocale.machine.serviceplan.placeUploadingResource请上传资源
		fileNotSupport:'Import file format is not supported, according to the template import device information',//EwayLocale.machine.serviceplan.fileNotSupport导入的文件格式不支持,请按模板导入设备信息
		exportExplain:'Import instructions',//EwayLocale.machine.serviceplan.exportExplain导入说明
		thisIsTooLong:'Please add the device continuously import template device number to be issued, up to a one-time import 2000 data (takes about 5 minutes), a minimum import data',//EwayLocale.machine.serviceplan.thisIsTooLong请在设备导入模板中连续添加要下发的设备号,最多一次性导入2000条数据(约耗时5分钟),最少导入1条数据
		thisHardToTranslate:'Click to download introducing device ID template',//EwayLocale.machine.serviceplan.thisHardToTranslate点击下载导入设备号模板
		placeSelect:'---Please select---',//EwayLocale.machine.serviceplan.placeSelect请选择
		planDevice:'Program <--> Device',//EwayLocale.machine.serviceplan.planDevice方案<-->设备
		timeEare:'Enter the time is incorrect, please re-enter！',//EwayLocale.machine.serviceplan.timeEare输入时间段有误，请重新输入
		planOlonOne:'The same program can only set a startup or shutdown',//EwayLocale.machine.serviceplan.planOlonOne同方案只能设置开机或关机的一种
		addSuccess:'Added successfully',//EwayLocale.machine.serviceplan.addSuccess添加成功
		addFail:'Add Failed',//EwayLocale.machine.serviceplan.addFail添加失败
		setTime:'Please set the detailed time',//EwayLocale.machine.serviceplan.setTime请设置详细时间
		thisPlanStop:'(This program is disabled, can not apply!)',//EwayLocale.machine.serviceplan.thisPlanStop(此方案已停用，不可应用！)
		placeRefresh:'Article lift failed. Please refresh view！',//EwayLocale.machine.serviceplan.placeRefresh条解除失败，请刷新后查看
		linking:'Being associated equipment....',//EwayLocale.machine.serviceplan.linking正在关联设备
		testingPlaceWaiting:'Device number is judged to meet the requirements, please wait...',//EwayLocale.machine.serviceplan.testingPlaceWaiting正在判断设备号是否符合要求，请耐心等待
		leastOne:'Importing a device at least once information, please re-select the import file!',//EwayLocale.machine.serviceplan.leastOne最少一次导入1条设备信息，请重新选择导入文件
		notMore:'Up to 2000 the first import device information, please re-select the import file!',//EwayLocale.machine.serviceplan.notMore最多一次导入2000条设备信息，请重新选择导入文件
		checkFile:'Please check the import file',//EwayLocale.machine.serviceplan.checkFile请检查导入文件
		fileNotAllowed:'Documents do not meet requirements！',//EwayLocale.machine.serviceplan.fileNotAllowed文件不符合要求
		tipExportSuccess:'Article data successfully imported',//EwayLocale.machine.serviceplan.tipExportSuccess条数据,成功导入
		tipLookUp:'Bar, click View import details!',//EwayLocale.machine.serviceplan.tipLookUp条,点击查看导入详情!
		tochenkDervice:'Please select the device you want to change',//EwayLocale.machine.serviceplan.tochenkDervice请选择您要更改的设备
		tochenckPeople:'Please select personnel',//EwayLocale.machine.serviceplan.tochenckPeople请选择人员
		tipAddError:'Article Adding failed. Please refresh view',//EwayLocale.machine.serviceplan.tipAddError条添加失败,请刷新后查看
		planIsHaved:'',
		linkSuccess:'Associate success',//EwayLocale.machine.serviceplan.linkSuccess关联成功
		Mon:'Mon',//EwayLocale.machine.serviceplan.Mon一
		Tues:'Tues',//EwayLocale.machine.serviceplan.Tues二
		Wed:'Wed',//EwayLocale.machine.serviceplan.Wed三
		Thur:'Thur',//EwayLocale.machine.serviceplan.Thur四
		Fri:'Fri',//EwayLocale.machine.serviceplan.Fri五
		Sat:'Sat',//EwayLocale.machine.serviceplan.Sat六
		Sun:'Sun',//EwayLocale.machine.serviceplan.Sun日
		useSuccess:'Normal start',//EwayLocale.machine.serviceplan.useSuccess正常启用
		notSuccess:'Not Enabled',//EwayLocale.machine.serviceplan.notSuccess未启用
		lastOneGroup:'Please select at least one in the group',//EwayLocale.machine.serviceplan.lastOneGroup请在组内至少选中一项
		lanDetailWeek:'Week program detailed list',//EwayLocale.machine.serviceplan.lanDetailWeek星期方案详细列表
		planDetailDay:'Date program detailed list',//EwayLocale.machine.serviceplan.planDetailDay日期方案详细列表
		selectPlan:'To select a startup program',//EwayLocale.machine.serviceplan.selectPlan选择开机方案
		weekSelect:'Notify way'//EwayLocale.machine.serviceplan.weekSelect通知方式
	}


});
