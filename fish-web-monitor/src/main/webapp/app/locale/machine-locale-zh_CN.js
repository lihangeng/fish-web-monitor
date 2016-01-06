Ext.apply(EwayLocale,{
	
	machine:{
		atmBrand : {//EwayLocale.machine.atmBrand
			title:'设备品牌',//EwayLocale.machine.title
			name: '品牌名称',//EwayLocale.machine.name
			country:'生产商国家或地区',//EwayLocale.machine.country
			hotline1:'生产商热线1',//EwayLocale.machine.hotline1
			hotline2:'生产商热线2',//EwayLocale.machine.hotline2
			address:'生产商地址',//EwayLocale.machine.address
			status:'生产商状态',//EwayLocale.machine.status
			comboxStatus:{
				provider:'设备供应',//EwayLocale.comboxStatus.provider
				maintance:'设备服役'//EwayLocale.comboxStatus.maintance
			},
			devBrandInfo:'设备品牌信息'//EwayLocale.comboxStatus.devBrandInfo
		},
		atmCatalog:{
			title:'设备分类',//EwayLocale.atmCatalog.title
			name:'分类名称',//EwayLocale.atmCatalog.name
			note:'备注',//EwayLocale.atmCatalog.note
			addTitle:'增加ATM分类信息',//EwayLocale.atmCatalog.addTitle
			updateTitle:'更改ATM型号信息',//EwayLocale.atmCatalog.updateTitle
			number:'编号'//EwayLocale.atmCatalog.number
		},
		atmGroup : {//EwayLocale.atmCatalog.atmGroup
			terminalId:'设备号',//EwayLocale.atmCatalog.terminalId
			ip: '设备IP地址',//EwayLocale.atmCatalog.ip
			orgName:'所属机构',//EwayLocale.atmCatalog.orgName
			devTypeName:'设备型号',//EwayLocale.atmCatalog.devTypeName
			devVendorName:'设备品牌',//EwayLocale.atmCatalog.devVendorName
			devCatalogName:'设备类型',//EwayLocale.atmCatalog.devCatalogName
			devGroupName: '设备分组',//EwayLocale.atmCatalog.devGroupName
			status:'设备状态',//EwayLocale.atmCatalog.status
			comboxStatus:{
				dredge:'开通',//EwayLocale.comboxStatus.dredge
				open:'开通',//EwayLocale.comboxStatus.open
				close:'停用'//EwayLocale.comboxStatus.close
			},
			awayFlag:'离行标志',//EwayLocale.comboxStatus.awayFlag
			comboxAwayFlag:{
				inBank:'在行自助服务区',//EwayLocale.comboxAwayFlag.inBank
				outBank:'离行自助银行',//EwayLocale.comboxAwayFlag.outBank
				clickBank:'单机离行自助服务点'//EwayLocale.comboxAwayFlag.clickBank
			},
			devServiceName:'设备维护商',//EwayLocale.comboxAwayFlag.devServiceName
			cashboxLimit:'钞箱报警金额',//EwayLocale.comboxAwayFlag.cashboxLimit
			installDate:'安装日期',//EwayLocale.comboxAwayFlag.installDate
			address:'地址',//EwayLocale.comboxAwayFlag.address
			gourpDev:'分组<-->设备',//EwayLocale.comboxAwayFlag.gourpDev
			addTitle: '增加设备组信息',//EwayLocale.comboxAwayFlag.addTitle
			groupName:'组名',//EwayLocale.comboxAwayFlag.groupName
			note:'备注',//EwayLocale.comboxAwayFlag.note
			updateTitle:'更改设备组信息'//EwayLocale.comboxAwayFlag.updateTitle
		},
		atmModule:{
			moduleName:'模块名称',//EwayLocale.atmModule.moduleName
			note:'备注',//EwayLocale.atmModule.note
			atmModules:'ATM模块'//EwayLocale.atmModule.atmModules
		},
		atmMove:{
			title:'移机管理',//EwayLocale.atmMove.title
			moveDev:'移机',//EwayLocale.atmMove.moveDev
			moveDevRec:'移动设备并产生移机记录',//EwayLocale.atmMove.moveDevRec
			moveRecordInfo:'移机记录信息',//EwayLocale.atmMove.moveRecordInfo
			waitMove:'待移动的机器',//EwayLocale.atmMove.waitMove
			terminalId:'设备号',//EwayLocale.atmMove.terminalId
			address:'源地址',//EwayLocale.atmMove.address
			orgName:'源机构',//EwayLocale.atmMove.orgName
			targetAddress:'目标地址',//EwayLocale.atmMove.targetAddress
			targetOrganization:'目标机构',//EwayLocale.atmMove.targetOrganization
			targetPerson:'目标机构负责人',//EwayLocale.atmMove.targetPerson
			responsibility:'负责人',//EwayLocale.atmMove.responsibility
			destPerson:'源机构负责人',//EwayLocale.atmMove.destPerson
			date:'日期',//EwayLocale.atmMove.date
			recoverDate:'恢复时间',//EwayLocale.atmMove.recoverDate
			notice:'备注',//EwayLocale.atmMove.notice
			sAddress:'所属地址',//EwayLocale.atmMove.sAddress
			sOrgName:'所属机构',//EwayLocale.atmMove.sOrgName
			to:'至'//EwayLocale.atmMove.to
		},
		atmRuntimeInfo:{
			exportName:'导出',//EwayLocale.atmRuntimeInfo.exportName
			exportDateRangeText:'开始时间不能大于结束时间',//EwayLocale.atmRuntimeInfo.exportDateRangeText
			terminalId:'终端号',//EwayLocale.atmRuntimeInfo.terminalId
			terminalIp:'终端IP',//EwayLocale.atmRuntimeInfo.terminalIp
			startDate:'开始时间',//EwayLocale.atmRuntimeInfo.startDate
			endDate:'结束时间',//EwayLocale.atmRuntimeInfo.endDate
			exportLast30: '导出最后30天汇总信息',//EwayLocale.atmRuntimeInfo.exportLast30
			terminalId:'编号',//EwayLocale.atmRuntimeInfo.terminalId
			netIp:'网络地址',//EwayLocale.atmRuntimeInfo.netIp
			msgCollect:'客服信息采集'//EwayLocale.atmRuntimeInfo.msgCollect
		},
		atmType:{
			title:'设备型号',//EwayLocale.atmType.title
			atmName:'ATM型号',//EwayLocale.atmType.atmName
			name:'设备型号',//EwayLocale.atmType.name
			devVendorName:'所属品牌',//EwayLocale.atmType.devVendorName
			devCatalogName:'所属类型',//EwayLocale.atmType.devCatalogName
			devTerminalName:'所属型号',//EwayLocale.atmType.devTerminalName
			spec:'设备规格',//EwayLocale.atmType.spec
			weight:'设备重量',//EwayLocale.atmType.weight
			watt:'平均功率',//EwayLocale.atmType.watt
			no:'编号',//EwayLocale.atmType.no
			cashtype:'非现金标志',//EwayLocale.atmType.cashtype
			iscash:'现金',//EwayLocale.atmType.iscash
			nocash:'非现金',//EwayLocale.atmType.nocash
			modules:'包含的设备模块'//EwayLocale.atmType.modules
		},
		device:{
			title:'设备信息管理',//EwayLocale.device.title
			devDetailInfo:'设备模块详细信息',//EwayLocale.device.devDetailInfo
		    idc:'读卡器',//EwayLocale.device.idc
			jpr:'日志打印机',//EwayLocale.device.jpr
			cdm:'取款模块',//EwayLocale.device.cdm
			siu:'传感器',//EwayLocale.device.siu
			cim:'存款模块',//EwayLocale.device.cim
			ttu:'文本终端',//EwayLocale.device.ttu
			rpr:'凭条打印机',//EwayLocale.device.rpr
			pin:'密码键盘',//EwayLocale.device.pin
			icc:'发卡器',//EwayLocale.device.icc
			fgp:'指纹仪',//EwayLocale.device.fgp
			pbk:'存折打印机',//EwayLocale.device.pbk
			
			CAMStatusInfo:'摄像头(CAM)状态信息',//EwayLocale.device.CAMStatusInfo
			CAMRoomStatus:'房间摄像头',//EwayLocale.device.CAMRoomStatus
			CAMPersonStatus:'客户摄像头',//EwayLocale.device.CAMPersonStatus
			CAMExitSlotStatus:'出钞口摄像头',//EwayLocale.device.CAMExitSlotStatus

			CAMInfo:'摄像头(CAM)属性信息',//EwayLocale.device.CAMInfo
			CAMMaxPictures:'最大拍照张数',//EwayLocale.device.CAMMaxPictures
			CAMMaxDataLength:'最大文字数',//EwayLocale.device.CAMMaxDataLength
			
			BCRInfo:'二维码扫描仪(BCR)属性信息',//EwayLocale.device.BCRInfo
			BCRCanCompound:'是否为合成设备',//EwayLocale.device.BCRCanCompound
			BCRCanFilterSymbologies:'能否辨别制定的条码',//EwayLocale.device.BCRCanFilterSymbologies
			
			CDMInfo:'取款模块（CDM）属性信息',//EwayLocale.device.CDMInfo
			hasStack:'是否具有暂存器',//EwayLocale.device.hasStack
			hasShutter:'是否具有shutter门',//EwayLocale.device.hasShutter
			canRetract:'是否具有回收能力',//EwayLocale.device.canRetract
			canDetectCashTaken:'是否探测钞币被拿走',//EwayLocale.device.canDetectCashTaken
			canTestPhysicalUnits:'是否能测试物理单元',//EwayLocale.device.canTestPhysicalUnits
			maxDispensBills:'获取单笔最大挖钞张数',//EwayLocale.device.maxDispensBills
			logicalUnits:'逻辑钞箱个数',//EwayLocale.device.logicalUnits
			physicalUnits:'物理钞箱个数',//EwayLocale.device.physicalUnits
			currency:'支持的币种类别总个数',//EwayLocale.device.currency
			currencies:'支持的币种类别',//EwayLocale.device.currencies
			exponents:'指数',//EwayLocale.device.exponents

			CIMInfo:'存款模块 （CIM）属性信息',//EwayLocale.device.CIMInfo
			canEscrow:'是否具有暂存器X',//EwayLocale.device.canEscrow
			shutterControlSupported:'是否支持控制shutter门',//EwayLocale.device.shutterControlSupported
			maxAcceptItems:'单笔最大验钞张数',//EwayLocale.device.maxAcceptItems
			canDetectCashInserted:'是否能探测钞票放入',//EwayLocale.device.canDetectCashInserted
			canDetectCashTaken:'是否能探测钞票被取走',//EwayLocale.device.canDetectCashTaken
			retractAreas:'回收位置',//EwayLocale.device.retractAreas


			IDCInfo:'读卡器模块(IDC)属性信息',//EwayLocale.device.IDCInfo
			variant:'读卡器类型',//EwayLocale.device.variant
			canEjectCard:'是否具有退卡能力',//EwayLocale.device.canEjectCard
			trackJisiiRead:'是否具有TrackJisii读能力',//EwayLocale.device.trackJisiiRead
			track1Read:'是否具有读一磁道数据能力',//EwayLocale.device.track1Read
			track2Read:'是否具有读二磁道数据能力',//EwayLocale.device.track2Read
			track3Read:'是否具有读三磁道数据能力',//EwayLocale.device.track3Read
			canCapture:'是否具有吞卡能力',//EwayLocale.device.canCapture
			binCapacity:'最大吞卡张数',//EwayLocale.device.binCapacity
			security:'是否具有安全支持',//EwayLocale.device.security
			trackJisiiWrite:'是否具有TrackJisii写能力',//EwayLocale.device.trackJisiiWrite
			track1Write:'是否具有写一磁道数据能力',//EwayLocale.device.track1Write
			track2Write:'是否具有写二磁道数据能力',//EwayLocale.device.track2Write
			track3Write:'是否具有写三磁道数据能力',//EwayLocale.device.track3Write


			JPRInfo:'日志打印机模块(JPR)属性信息',//EwayLocale.device.JPRInfo
			canEject:'是否具有退纸能力',//EwayLocale.device.canEject
			canCapture:'是否具有回收能力',//EwayLocale.device.canCapture
			canStack:'是否具有暂存能力',//EwayLocale.device.canStack

			PINInfo:'密码键盘(PIN)属性信息',//EwayLocale.device.PINInfo
			canEBC:'能否EBC',//EwayLocale.device.canEBC
			canCBC:'能否CBC',//EwayLocale.device.canCBC
			canMAC:'能否MAC',//EwayLocale.device.canMAC
			canRSA:'能否RSA',//EwayLocale.device.canRSA
			canVerifyVISA:'能否验证VISA',//EwayLocale.device.canVerifyVISA
			canVerifyDES:'能否验证DES',//EwayLocale.device.canVerifyDES
			functionKeys:'功能键支持',//EwayLocale.device.functionKeys
			canTripleEBC:'是否支持多重EBC',//EwayLocale.device.canTripleEBC
			canTripleCBC:'是否支持多重CBC',//EwayLocale.device.canTripleCBC
			canTripleMAC:'是否支持多重MAC',//EwayLocale.device.canTripleMAC
			canTripleCFB:'是否支持多重CFB',//EwayLocale.device.canTripleCFB
			canVerifyECB:'能否验证ECB',//EwayLocale.device.canVerifyECB
			canDESOffset:'能否DeS偏移',//EwayLocale.device.canDESOffset

			RPRInfo:'凭条打印机(RPR)属性信息',//EwayLocale.device.RPRInfo
			canEject:'是否具有退纸能力',//EwayLocale.device.canEject
			canCapture:'是否具有回收能力',//EwayLocale.device.canCapture
			canStack:'是否具有暂存能力',//EwayLocale.device.canStack
			maxRetract:'最大回收张数',//EwayLocale.device.maxRetract

			SIUInfo:'SIU能力属性信息',//EwayLocale.device.SIUInfo
			operatorSwitchSupported:'是否支持操作员开关',//EwayLocale.device.operatorSwitchSupported
			cabinetSupported:'是否支持后盖门打开传感能力',//EwayLocale.device.cabinetSupported
			safeSupported:'是否支持安全门打开传感能力',//EwayLocale.device.safeSupported
			indicatorSupported:'是否支持靠近传感能力',//EwayLocale.device.indicatorSupported
			guidelightIdcSupported:'是否支持插卡指示灯能力',//EwayLocale.device.guidelightIdcSupported
			guidelightCdmSupported:'是否支持取款指示灯能力',//EwayLocale.device.guidelightCdmSupported
			guidelightReceiptSupported:'是否支持凭条打印指示灯能力',//EwayLocale.device.guidelightReceiptSupported
			guidelightCimSupported:'是否支持存款指示灯能力',//EwayLocale.device.guidelightCimSupported

			TTUInfo:'文本终端单元(TTU)属性信息',//EwayLocale.device.TTUInfo
			alphanumericKeysPresent:'是否支持字母数字键输入',//EwayLocale.device.alphanumericKeysPresent
			numericKeysPresent:'是否支持数字键输入',//EwayLocale.device.numericKeysPresent
			displayLightPresent:'是否支持屏幕亮度调节',//EwayLocale.device.displayLightPresent
			cursorSupported:'是否支持鼠标',//EwayLocale.device.cursorSupported
			resolutionX:'横轴分辨率',//EwayLocale.device.resolutionX
			hexadecimalKeysPresent:'是否支持十六进制键输入',//EwayLocale.device.hexadecimalKeysPresent
			keyboardLockPresent:'是否支持键盘锁定',//EwayLocale.device.keyboardLockPresent
			formsSupported:'是否支持表格',//EwayLocale.device.formsSupported
			resolutionY:'纵轴分辨率',//EwayLocale.device.resolutionY
			
			ISCInfo:'身份证扫描仪(ISC)属性信息',//EwayLocale.device.ISCInfo
			
			ICCInfo:'发卡器(ICC)属性信息',//EwayLocale.device.ICCInfo
			dispenseCard:'是否具有发卡读能力',//EwayLocale.device.dispenseCard
			
			FGPInfo:'指纹仪(FGP)属性信息',//EwayLocale.device.FGPInfo
			fgp_variant:'指纹仪类型',//EwayLocale.device.fgp_variant
			canCompare:'是否具有比较特值功能',//EwayLocale.device.canCompare
			
			PBKInfo:'存折打印机(PBK)属性信息',//EwayLocale.device.PBKInfo
			
			comStatus:'厂商状态信息',//EwayLocale.device.comStatus
			hwCode:'厂商故障码',//EwayLocale.device.hwCode
			CDMStatus:'取款模块(CDM)状态信息',//EwayLocale.device.CDMStatus
			cashUnits:'钞箱状态',//EwayLocale.device.cashUnits
			safeDoor:'安全门状态',//EwayLocale.device.safeDoor
			intermediateStacker:'暂存器状态',//EwayLocale.device.intermediateStacker
			outBox:'取款钞箱',//EwayLocale.device.outBox
			pcuId:'物理逻辑钞箱对应关系',//EwayLocale.device.pcuId
			cuId:'逻辑钞箱ID',//EwayLocale.device.cuId
			cuCurrency:'逻辑钞箱币种',//EwayLocale.device.cuCurrency
			cuCurrentCount:'逻辑钞箱当前张数',//EwayLocale.device.cuCurrentCount
			cuInitialCount:'逻辑钞箱初始张数',//EwayLocale.device.cuInitialCount
			cuRejectCount:'逻辑钞箱reject张数',//EwayLocale.device.cuRejectCount
			cuNoteValue:'逻辑钞箱面值',//EwayLocale.device.cuNoteValue
			cuBinStatus:'逻辑钞箱状态',//EwayLocale.device.cuBinStatus
			puId:'物理钞箱ID',//EwayLocale.device.puId
			puPosName:'物理钞箱位置名称',//EwayLocale.device.puPosName
			puBinStatus:'物理钞箱状态',//EwayLocale.device.puBinStatus
			puCurrentCount:'物理钞箱当前张数',//EwayLocale.device.puCurrentCount
			puInitialCount:'物理钞箱初始张数',//EwayLocale.device.puInitialCount
			puRejectCount:'物理钞箱Reject张数',//EwayLocale.device.puRejectCount
			cuBinType:'逻辑钞箱类型',//EwayLocale.device.cuBinType

			CIMStatus:'存款模块(CIM)状态信息',//EwayLocale.device.CIMStatus
			baffle:'挡板状态',//EwayLocale.device.baffle
			inOutPositionStatus:'传输状态',//EwayLocale.device.inOutPositionStatus
			inBox:'存款钞箱',//EwayLocale.device.inBox
			puCashInCount:'物理钞箱入钞张数',//EwayLocale.device.puCashInCount
			pcuId:'物理钞箱与逻辑钞箱对应关系',//EwayLocale.device.pcuId
			cuType:'逻辑钞箱类型',//EwayLocale.device.cuType
			cuBinStatus:'逻辑钞箱状态',//EwayLocale.device.cuBinStatus
			cuCurrentCount:'逻辑钞箱当前张数',//EwayLocale.device.cuCurrentCount
			cuCurrency:'逻辑钞箱币种',//EwayLocale.device.cuCurrency
			cuNoteValue:'逻辑钞箱面值',//EwayLocale.device.cuNoteValue

			IDCStatus:'读卡器模块(IDC)状态信息',//EwayLocale.device.IDCStatus
			media:'媒体状态',//EwayLocale.device.media
			retainBin:'回收盒状态',//EwayLocale.device.retainBin
			cards:'回收盒数量',//EwayLocale.device.cards

			JRPStatus:'日志打印机模块(JPR)状态信息',//EwayLocale.device.JRPStatus
			supplyLevel:'打印纸状态',//EwayLocale.device.supplyLevel
			ink:'墨水',//EwayLocale.device.ink
			toner:'色带',//EwayLocale.device.toner

			PINStatus:'密码键盘模块(PIN)状态信息',//EwayLocale.device.PINStatus

			RPRStatus:'凭条打印机模块(RPR)状态信息',//EwayLocale.device.RPRStatus
			bin:'回收单元状态',//EwayLocale.device.bin

			SIUStatus:'SIU能力状态信息',//EwayLocale.device.SIUStatus
			vandalShield:'防护罩状态',//EwayLocale.device.vandalShield
			operatorSwitch:'操作员按钮状态',//EwayLocale.device.operatorSwitch
			ambientLight:'环境灯状态',//EwayLocale.device.ambientLight
			cabinet:'箱门状态',//EwayLocale.device.cabinet
			safe:'安全门状态',//EwayLocale.device.safe
			idcGuidelight:'插卡导引灯状态',//EwayLocale.device.idcGuidelight
			cdmGuidelight:'取钞引导指示灯状态',//EwayLocale.device.cdmGuidelight
			receiptGuidelight:'凭条导引灯状态',//EwayLocale.device.receiptGuidelight
			cimGuidelight:'CIM导引灯状态',//EwayLocale.device.cimGuidelight

			TTUStatus:'文本终端单元(TTU)状态信息',//EwayLocale.device.TTUStatus
			
			ISCStatus:'身份证扫描仪(ISC)状态信息',//EwayLocale.device.ISCStatus
			
			ICCStatus:'发卡器(ICC)状态信息',//EwayLocale.device.ICCStatus
			
			FGPStatus:'指纹仪(FGP)状态信息',//EwayLocale.device.FGPStatus
			
			PBKStatus:'存折打印机(PBK)状态信息',//EwayLocale.device.PBKStatus

			devPerson:'设备人员信息',//EwayLocale.device.devPerson
			devModuleMsg:'设备模块属性信息',//EwayLocale.device.devModuleMsg
			devBasicMsg:'设备基本信息',//EwayLocale.device.devBasicMsg
			devTailMsg:'设备详细信息',//EwayLocale.device.devTailMsg
			managePerson:'管机员',//EwayLocale.device.managePerson
			maintainPerson:'维护员',//EwayLocale.device.maintainPerson
			name:'姓名',//EwayLocale.device.name
			mobile:'手机',//EwayLocale.device.mobile
			phone:'固定电话',//EwayLocale.device.phone
			email:'邮件地址',//EwayLocale.device.email
			deviceBasicInfo:'设备基本信息',//EwayLocale.device.deviceBasicInfo
			lineLogo:'在行标志',//EwayLocale.device.lineLogo
			alarmRateRMB:'钞箱报警金额(人民币)',//EwayLocale.device.alarmRateRMB
			operation:'经营方式',//EwayLocale.device.operation
			ipAddress:'IP地址',//EwayLocale.device.ipAddress
			swallowCard:'吞卡张数',//EwayLocale.device.swallowCard
			alarmRateHKD:'钞箱报警金额(港币)',//EwayLocale.device.alarmRateHKD
			adminPhone:'管理员(手机号)',//EwayLocale.device.adminPhone
			maintainPhone: '维护员(手机号)',//EwayLocale.device.maintainPhone
			log:'钞箱标识',//EwayLocale.device.log
			style: '钞箱类型',//EwayLocale.device.style
			status: '钞箱状态',//EwayLocale.device.status
			initailnumber: '初始张数',//EwayLocale.device.initailnumber
			postnumber: '存款张数',//EwayLocale.device.postnumber
			currentnumber: '当前钞箱张数',//EwayLocale.device.currentnumber
			facevalue: '钞箱面值',//EwayLocale.device.facevalue
			currency: '钞箱币种',//EwayLocale.device.currency
			systemHardwareInfo: '系统软硬件信息',//EwayLocale.device.systemHardwareInfo
			moduleVersionInfo:'模块硬件版本信息（实时）',//EwayLocale.device.moduleVersionInfo
			devModuleStatusInfo: '设备模块状态（实时）',//EwayLocale.device.devModuleStatusInfo
			devModuleAttributeInfo: '设备模块属性信息（实时）',//EwayLocale.device.devModuleAttributeInfo

			remoteControl: '远程控制',//EwayLocale.device.remoteControl
			collectJPR:'提取日志',//EwayLocale.device.collectJPR
			remoteScreen:'远程抓屏',//EwayLocale.device.remoteScreen
			processCheck:'进程查看',//EwayLocale.device.processCheck
			remoteExplorer:'远程浏览',//EwayLocale.device.remoteExplorer
			netWorkLink:'网络连接',//EwayLocale.device.netWorkLink
			remoteRestart:'远程重启',//EwayLocale.device.remoteRestart

			progressTip:'进度提示',//EwayLocale.device.progressTip
			updateProBar:'这是通过动态更新内容形成的进度条',//EwayLocale.device.updateProBar
			currentProcess:'当前进度',//EwayLocale.device.currentProcess


			restartApply: ' 重启应用',//EwayLocale.device.restartApply
			confirmRestartApply:'确定要重启应用？',//EwayLocale.device.confirmRestartApply
			nowRestartApply:'正在重启应用',//EwayLocale.device.nowRestartApply
			restartApplySuc:'成功重启该设备应用',//EwayLocale.device.restartApplySuc
			restartApplyFail:'重启应用失败！',//EwayLocale.device.restartApplyFail


			restartDrive:'重启硬件驱动',//EwayLocale.device.restartDrive
			confirmRestartDrive:'确定要重启硬件驱动？',//EwayLocale.device.confirmRestartDrive
			nowRestartDrive:'正在重启硬件驱动',//EwayLocale.device.nowRestartDrive
			restartDriveSuc:'成功重启该设备硬件驱动',//EwayLocale.device.restartDriveSuc
			restartDriveFail:'重启硬件驱动失败！',//EwayLocale.device.restartDriveFail

			restartOS:'重启操作系统',//EwayLocale.device.restartOS
			confirmRestartOS:'确定要重启操作系统？',//EwayLocale.device.confirmRestartOS
			nowRestartOS:'正在重启操作系统',//EwayLocale.device.nowRestartOS
			restartOSSuc:'成功重启该设备操作系统',//EwayLocale.device.restartOSSuc
			restartOSFail:'重启操作系统失败！',//EwayLocale.device.restartOSFail

			remoteShutdown:'远程关机',//EwayLocale.device.remoteShutdown
			shutdownApply:'关闭应用',//EwayLocale.device.shutdownApply
			confirmShutdownApply:'确定要关闭应用？',//EwayLocale.device.confirmShutdownApply
			nowShutdownApply:'正在关闭应用',//EwayLocale.device.nowShutdownApply
			shutdownApplySuc:'成功关闭该设备应用',//EwayLocale.device.shutdownApplySuc
			shutdownApplyFail:'关闭应用失败！',//EwayLocale.device.shutdownApplyFail

			shutdownDrive:'关闭硬件驱动',//EwayLocale.device.shutdownDrive
			confirmShutdownDrive:'确定要关闭硬件驱动？',//EwayLocale.device.confirmShutdownDrive
			nowShutdownDrive:'正在关闭硬件驱动',//EwayLocale.device.nowShutdownDrive
			shutdownDriveSuc:'成功关闭该设备硬件驱动',//EwayLocale.device.shutdownDriveSuc
			shutdownDriveFail:'关闭硬件驱动失败！',//EwayLocale.device.shutdownDriveFail

			shutdownOS:'关闭操作系统',//EwayLocale.device.shutdownOS
			confirmShutdownOS:'确定要关闭操作系统？',//EwayLocale.device.confirmShutdownOS
			nowShutdownOS:'正在关闭操作系统',//EwayLocale.device.nowShutdownOS
			shutdownOSSuc:'成功关闭该设备操作系统',//EwayLocale.device.shutdownOSSuc
			shutdownOSFail:'关闭操作系统失败！',//EwayLocale.device.shutdownOSFail
			getSoftwareList:'获取软件安装列表',//EwayLocale.device.getSoftwareList
			forceReset:'强制复位',//EwayLocale.device.forceReset
			openService:'开启服务',//EwayLocale.device.openService
			pauseService:'暂停服务',//EwayLocale.device.pauseService
			checkStatus:'状态检测',//EwayLocale.device.checkStatus

			remoteBrowseDisk:'远程浏览',//EwayLocale.device.remoteBrowseDisk

			sysHardwareInfo:'系统硬件信息',//EwayLocale.device.sysHardwareInfo
			diskMem:'硬盘大小',//EwayLocale.device.diskMem
			biosVersion:'Bios版本',//EwayLocale.device.biosVersion
			biosVendor:'Bios厂商',//EwayLocale.device.biosVendor
			biosReleaseDate:'Bios发布日期',//EwayLocale.device.biosReleaseDate
			memorySize:'内存总数',//EwayLocale.device.memorySize
			memoryUsed:'已使用内存',//EwayLocale.device.memoryUsed
			memoryFree:'空闲内存',//EwayLocale.device.memoryFree
			memoryPercent:'内存使用率',//EwayLocale.device.memoryPercent
			cpuItemID:'cpu信息',//EwayLocale.device.cpuItemID
			cpuFrequency:'CPU频率(MHz)',//EwayLocale.device.cpuFrequency
			cpuVendor:'CPU的厂商',//EwayLocale.device.cpuVendor
			cpuModel:'CPU的类别',//EwayLocale.device.cpuModel
			cacheSize:'缓冲存储器数量',//EwayLocale.device.cacheSize
			totalCores:'CPU核数',//EwayLocale.device.totalCores
			userUsePercent:'用户使用率',//EwayLocale.device.userUsePercent
			sysUsePercent:'系统使用率',//EwayLocale.device.sysUsePercent
			idlePercent:'当前空闲率',//EwayLocale.device.idlePercent
			combinedPercent:'总的使用率',//EwayLocale.device.combinedPercent
			diskItemID:'磁盘信息',//EwayLocale.device.diskItemID
			diskName:'磁盘分区名称',//EwayLocale.device.diskName
			diskFileSys:'磁盘文件系统',//EwayLocale.device.diskFileSys
			diskTotalSize:'磁盘总大小',//EwayLocale.device.diskTotalSize
			diskFreeSize:'磁盘可用空间大小',//EwayLocale.device.diskFreeSize
			sysSoftInfo:'系统软件信息',//EwayLocale.device.sysSoftInfo
			OSID:'操作系统ID',//EwayLocale.device.OSID
			OSDescription:'OS描述',//EwayLocale.device.OSDescription
			OSType:'OS类型',//EwayLocale.device.OSType
			sysPatchLevel:'系统补丁级别',//EwayLocale.device.sysPatchLevel
			chkCashData:'验钞数据版本',//EwayLocale.device.chkCashData
			OSVendor:'OS供应商',//EwayLocale.device.OSVendor
			OSVendorName:'OS供应商名',//EwayLocale.device.OSVendorName
			sysVersion:'系统版本号',//EwayLocale.device.sysVersion
			devAddress:'设备地址',//EwayLocale.device.devAddress
			basicInfo:'其它信息',//EwayLocale.device.basicInfo
			virtual:'虚拟设备号',//EwayLocale.device.virtual
			serial:'设备序列号',//EwayLocale.device.serial
			carrier:'运营商',//EwayLocale.device.carrier
			moneyOrg:'加钞机构',//EwayLocale.device.moneyOrg
			costInterest:'资金成本利率',//EwayLocale.device.costInterest
			atmcSoft:'atmc软件',//EwayLocale.device.atmcSoft
			spType:'厂商sp类型',//EwayLocale.device.spType
			column:'日期信息',//EwayLocale.device.column
			buyDate:'设备购买日期',//EwayLocale.device.buyDate
			installDate:'设备安装日期',//EwayLocale.device.installDate
			startDate:'设备启用日期',//EwayLocale.device.startDate
			stopDate:'设备停用日期',//EwayLocale.device.stopDate
			expireDate:'保修到期日期',//EwayLocale.device.expireDate
			daliyOpen:'每日开机时间',//EwayLocale.device.daliyOpen
			openTimeHour:'时',//EwayLocale.device.openTimeHour
			openTimeMinute:'分',//EwayLocale.device.openTimeMinute
			openTimeSecond:'秒',//EwayLocale.device.openTimeSecond
			daliyClose:'每日关机时间',//EwayLocale.device.daliyClose
			lastPmDate:'上次巡检日期',//EwayLocale.device.lastPmDate
			expirePmDate:'巡检到期日期',//EwayLocale.device.expirePmDate
			costInfo:'费用信息',//EwayLocale.device.costInfo
			price:'入账成本(元)',//EwayLocale.device.price
			depreciationLife:'折旧年限(年)',//EwayLocale.device.depreciationLife
			decoration:'装修费用',//EwayLocale.device.decoration
			decorationCost:'装修摊销年限(年)',//EwayLocale.device.decorationCost
			governanceRent:'物业租赁费(元/月)',//EwayLocale.device.governanceRent
			governanceCost:'物业管理费用(元/月)',//EwayLocale.device.governanceCost
			netCost:'通讯线路费用(元/月)',//EwayLocale.device.netCost
			powerCost:'电费(元/月)',//EwayLocale.device.powerCost
			moneyCost:'加钞维护费用(元/次)',//EwayLocale.device.moneyCost
			statusInfo:'状态信息',//EwayLocale.device.statusInfo
			deviceAttention:'设备关注程序',//EwayLocale.device.deviceAttention
			stress:'重点',//EwayLocale.device.stress
			medium:'中等',//EwayLocale.device.medium
			ordinary:'一般',//EwayLocale.device.ordinary
			notCashSignal:'非现金标志',//EwayLocale.device.notCashSignal
			cash: '现金',//EwayLocale.device.cash
			notCash:'非现金',//EwayLocale.device.notCash
			installStyle: '安装方式',//EwayLocale.device.installStyle
			crossWall: '穿墙',//EwayLocale.device.crossWall
			mainRoom: '大堂',//EwayLocale.device.mainRoom
			netType: '网络类型',//EwayLocale.device.netType
			wired: '有线',//EwayLocale.device.wired
			wireless: '无线',//EwayLocale.device.wireless
			wiredAndWireless: '有线无线',//EwayLocale.device.wiredAndWireless
			onBankSignal:'在行离行标志',//EwayLocale.device.onBankSignal
			inBank:'在行自助服务区',//EwayLocale.device.inBank
			outBank:'离行自助银行',//EwayLocale.device.outBank
			clickBank:'单机离行自助服务点',//EwayLocale.device.clickBank
			operation:'经营方式',//EwayLocale.device.operation
			operationSelf: '自营',//EwayLocale.device.operationSelf
			cooperation: '合作',//EwayLocale.device.cooperation
			epiboly: '外包',//EwayLocale.device.epiboly
			managePerson:'管机员',//EwayLocale.device.managePerson
			maintainPerson:'维护员',//EwayLocale.device.maintainPerson
			to:'至',//EwayLocale.device.to
			range: '范围1－－100年',//EwayLocale.device.range
			roleDescription:'角色描述',//EwayLocale.device.roleDescription
			roleName:'角色名称',//EwayLocale.device.roleName



			devices:'设备',//EwayLocale.device.devices
			configuration:'配置信息',//EwayLocale.device.configuration
			spVersion:'SP版本',//EwayLocale.device.spVersion
			notSupport:'不支持',//EwayLocale.device.notSupport
			drive:'驱动',//EwayLocale.device.drive
			firmway: '固件',//EwayLocale.device.firmway
			noDevice:'无设备',//EwayLocale.device.noDevice
			devTypeInfo: '设备型号信息',//EwayLocale.device.devTypeInfo

			devInfo:'设备信息',//EwayLocale.device.devInfo
			unable:'不可以',//EwayLocale.device.unable
			able:'能',//EwayLocale.device.able

			addDevInfo:'增加设备信息',//EwayLocale.device.addDevInfo
			effectiveDate:'生效日期',//EwayLocale.device.effectiveDate
			changeDevInfo:'更改设备信息',//EwayLocale.device.changeDevInfo
			devManage:'设备管理',//EwayLocale.device.devManage
			efficientDev:'已生效设备信息',//EwayLocale.device.efficientDev
			unEfficientDev:'未生效设备信息',//EwayLocale.device.unEfficientDev
			person:{
				week:'星期',//EwayLocale.person.week
				Mon:'星期一',//EwayLocale.person.Mon
				Tues:'星期二',//EwayLocale.person.Tues
				Wed:'星期三',//EwayLocale.person.Wed
				Thur:'星期四',//EwayLocale.person.Thur
				Fri:'星期五',//EwayLocale.person.Fri
				Sat:'星期六',//EwayLocale.person.Sat
				Sun:'星期日',//EwayLocale.person.Sun
				openClose:'开机/关机',//EwayLocale.person.openClose
				Open:'开机',//EwayLocale.person.Open
				Close:'关机'//EwayLocale.person.Close
			}

		},
		param:{
			paramKey:'参数',//EwayLocale.param.paramKey
			paramValue:'参数值',//EwayLocale.param.paramValue
			classify:'类型',//EwayLocale.param.classify
			paramType:'参数类型',//EwayLocale.param.paramType
			modifyFlag:'是否可以修改',//EwayLocale.param.modifyFlag
			comboxClassify:{
				unableUpdate:'不可修改',//EwayLocale.comboxClassify.unableUpdate
				ableUpdate:'可以修改'//EwayLocale.comboxClassify.ableUpdate
			},
			description:'描述',//EwayLocale.comboxClassify.description
			systemCon:'系统配置',//EwayLocale.comboxClassify.systemCon
			updateSystemCon:'更改系统配置'//EwayLocale.comboxClassify.updateSystemCon
		},
		quittingNotice:{
			addCloseMsg:'增加报停信息',//EwayLocale.quittingNotice.addCloseMsg
			updateCloseMsg:'更改报停信息',//EwayLocale.quittingNotice.updateCloseMsg
			dateRangeText:'恢复日期不能小于等于停止日期,请重新选择',//EwayLocale.quittingNotice.dateRangeText
			click:'请点击查询，选择设备',//EwayLocale.quittingNotice.click
			stopTime:'停机时间',//EwayLocale.quittingNotice.stopTime
			openTime:'恢复时间',//EwayLocale.quittingNotice.openTime
			currentStatus:'当前状态',//EwayLocale.quittingNotice.currentStatus
			closeType:'停机类型',//EwayLocale.quittingNotice.closeType
			responsibilityName:'停机负责人',//EwayLocale.quittingNotice.responsibilityName
			stopReason:'停机原因',//EwayLocale.quittingNotice.stopReason
			address:'所属地址',//EwayLocale.quittingNotice.address
			selectDev:'选择需要报停的设备',//EwayLocale.quittingNotice.selectDev
			updateUnable:'不能对已执行完成的报停记录进行修改.',//EwayLocale.quittingNotice.updateUnable
			to:'至',//EwayLocale.quittingNotice.to
			stopType:'停机类型',//EwayLocale.quittingNotice.stopType
			comboxStopType:{
				recess:'放假',//EwayLocale.comboxStopType.recess
				fit:'装修',//EwayLocale.comboxStopType.fit
				power:'停电',//EwayLocale.comboxStopType.power
				devFailue:'设备故障未修复',//EwayLocale.comboxStopType.devFailue
				other:'其他'//EwayLocale.comboxStopType.other
			},
			setTime:'创建时间',//EwayLocale.comboxStopType.setTime
			closeManage:'报停管理'//EwayLocale.comboxStopType.closeManage
		},
		plan:{
			addPlan:'增加开机方案',//EwayLocale.plan.addPlan
			name:'方案名称',//EwayLocale.plan.name
			type:'方案类型',//EwayLocale.plan.type
			startDate:'有效开始时间',//EwayLocale.plan.startDate
			endDate:'有效结束时间',//EwayLocale.plan.endDate
			terminalId:'编号',//EwayLocale.plan.terminalId
			cashboxLimit:'钞箱报警金额(单位：张数)',//EwayLocale.plan.cashboxLimit
			perToDev:'人员<-->设备',//EwayLocale.plan.perToDev
			changePlan:'更改方案',//EwayLocale.plan.changePlan
			servicePlan:'开机方案'//EwayLocale.plan.servicePlan
		},
		serviceplan:{
			title:'开机方案',//EwayLocale.serviceplan.title
			name:'方案名称',//EwayLocale.serviceplan.name
			machineQuantity:'设备数量',//EwayLocale.serviceplan.machineQuantity
			state:'状态',//EwayLocale.serviceplan.state
			openDate:'有效开机时间',//EwayLocale.serviceplan.openDate
			closeDate:'有效关机时间',//EwayLocale.serviceplan.closeDate
			createDateTime:'创建时间',//EwayLocale.serviceplan.createDateTime
			date:'日期',//EwayLocale.serviceplan.date
			week:'星期',//EwayLocale.serviceplan.week
			weekDay:'星期',//EwayLocale.serviceplan.weekDay
			inportLinkedMachine:'导入关联设备',//EwayLocale.serviceplan.inportLinkedMachine
			selectFile:'选择文件',//EwayLocale.serviceplan.selectFile
			placeUploadingResource:'请上传资源',//EwayLocale.serviceplan.placeUploadingResource
			fileNotSupport:'导入的文件格式不支持,请按模板导入设备信息',//EwayLocale.serviceplan.fileNotSupport
			exportExplain:'导入说明',//EwayLocale.serviceplan.exportExplain
			thisIsTooLong:'请在设备导入模板中连续添加要下发的设备号,最多一次性导入2000条数据(约耗时5分钟),最少导入1条数据',//EwayLocale.serviceplan.thisIsTooLong
			thisHardToTranslate:'点击下载导入设备号模板',//EwayLocale.serviceplan.thisHardToTranslate
			planDevice:'方案<-->设备',//EwayLocale.serviceplan.planDevice
			timeEare:'输入时间段有误，请重新输入！',//EwayLocale.serviceplan.timeEare
			timeError:'输入时间段有误，请重新输入！',//EwayLocale.serviceplan.timeError
			planOlonOne:'同方案只能设置开机或关机的一种',//EwayLocale.serviceplan.planOlonOne
			setTime:'请设置详细时间',//EwayLocale.serviceplan.setTime
			thisPlanStop:'(此方案已停用，不可应用！)',//EwayLocale.serviceplan.thisPlanStop
			placeRefresh:'条解除失败，请刷新后查看！',//EwayLocale.serviceplan.placeRefresh
			linking:'正在关联设备....',//EwayLocale.serviceplan.linking
			testingPlaceWaiting:'正在判断设备号是否符合要求，请耐心等待...',//EwayLocale.serviceplan.testingPlaceWaiting
			leastOne:'最少一次导入1条设备信息，请重新选择导入文件!',//EwayLocale.serviceplan.leastOne
			notMore:'最多一次导入2000条设备信息，请重新选择导入文件!',//EwayLocale.serviceplan.notMore
			checkFile:'请检查导入文件',//EwayLocale.serviceplan.checkFile
			fileNotAllowed:'文件不符合要求！',//EwayLocale.serviceplan.fileNotAllowed
			tipExportSuccess:'条数据,成功导入',//EwayLocale.serviceplan.tipExportSuccess
			tipLookUp:'条,点击查看导入详情!',//EwayLocale.serviceplan.tipLookUp
			tochenkDervice:'请选择您要更改的设备',//EwayLocale.serviceplan.tochenkDervice
			tochenckPeople:'请选择人员',//EwayLocale.serviceplan.tochenckPeople
			tipAddError:'条添加失败,请刷新后查看',//EwayLocale.serviceplan.tipAddError
			planIsHaved:'',//EwayLocale.serviceplan.planIsHaved
			chooseOne:'至少选择一个',//EwayLocale.serviceplan.chooseOne
			linkSuccess:'关联成功',//EwayLocale.serviceplan.linkSuccess
			Mon:'一',//EwayLocale.serviceplan.Mon
			Tues:'二',//EwayLocale.serviceplan.Tues
			Wed:'三',//EwayLocale.serviceplan.Wed
			Thur:'四',//EwayLocale.serviceplan.Thur
			Fri:'五',//EwayLocale.serviceplan.Fri
			Sat:'六',//EwayLocale.serviceplan.Sat
			Sun:'日',//EwayLocale.serviceplan.Sun
			useSuccess:'正常启用',//EwayLocale.serviceplan.useSuccess
			notSuccess:'未启用',//EwayLocale.serviceplan.notSuccess
			lastOneGroup:'请在组内至少选中一项',//EwayLocale.serviceplan.lastOneGroup
			lanDetailWeek:'星期方案详细列表',//EwayLocale.serviceplan.lanDetailWeek
			planDetailDay:'日期方案详细列表',//EwayLocale.serviceplan.planDetailDay
			selectPlan:'选择开机方案',//EwayLocale.serviceplan.selectPlan
			weekSelect:'通知方式'//EwayLocale.serviceplan.weekSelect
		}
	},
	
	atmLog:{
		dayBackup:'当日备份结果',//EwayLocale.atmLog.dayBackup
		whole:'所有',//EwayLocale.atmLog.whole
		execute:'执行中',//EwayLocale.atmLog.execute
		unKnownFail:'未知原因失败',//EwayLocale.atmLog.unKnownFail
		logDate:'日志日期',//EwayLocale.atmLog.logDate
		backupResult:'备份结果',//EwayLocale.atmLog.backupResult
		checkFailInfo:'查看备份失败详情',//EwayLocale.atmLog.checkFailInfo
		checkSucInfo:'查看备份成功详情',//EwayLocale.atmLog.checkSucInfo
		backupSucAmount:'备份成功台数',//EwayLocale.atmLog.backupSucAmount
		backupFailAmount:'备份失败台数',//EwayLocale.atmLog.backupFailAmount
		backupAllAmount:'总备份台数',//EwayLocale.atmLog.backupAllAmount
		logBackupSta:'日志备份统计',//EwayLocale.atmLog.logBackupSta
		lastDoDate:'最后执行时间',//EwayLocale.atmLog.lastDoDate
		getLog:'提取日志',//EwayLocale.atmLog.getLog
		dailyBackup:'每日备份任务',//EwayLocale.atmLog.dailyBackup
		backupDate:'备份日期',//EwayLocale.atmLog.backupDate
		dayBackupResult:'当日备份结果',//EwayLocale.atmLog.dayBackupResult
		backupProcess:'正在备份',//EwayLocale.atmLog.backupProcess
		backupSuccess:'备份成功',//EwayLocale.atmLog.backupSuccess
		backupError:'备份错误',//EwayLocale.atmLog.backupError
		logDevSucAccount:'日志备份成功设备数',//EwayLocale.atmLog.logDevSucAccount
		logDevFailAccount:'日志备份失败设备数',//EwayLocale.atmLog.logDevFailAccount
		reform:'重做',//EwayLocale.atmLog.reform
		busLogAnalysis:'业务日志分析',//EwayLocale.atmLog.busLogAnalysis
		selectAnalysis:'请选择需要分析的日志文件，分析的结果将以Excel表格文件导出',//EwayLocale.atmLog.selectAnalysis
		selectLog:'选择日志',//EwayLocale.atmLog.selectLog
		pleaseDownload:'请下载',//EwayLocale.atmLog.pleaseDownload
		title:'应用日志',//EwayLocale.atmLog.title
		lastBackupTime:'最后一次备份时间',//EwayLocale.atmLog.lastBackupTime
		noBegin:'未开始',//EwayLocale.atmLog.noBegin
		noLog:'无日志',//EwayLocale.atmLog.noLog
		connectFail:'连接失败',//EwayLocale.atmLog.connectFail
		fileSize:'文件大小',//EwayLocale.atmLog.fileSize
		searchIllegal:'查询项中存在不合法的输入,不能导出.'//EwayLocale.atmLog.searchIllegal
	},
	
	card:{
		cardNum:'卡号',//EwayLocale.card.cardNum
		onlyNumber:'只能输入数字,13-19位',//EwayLocale.card.onlyNumber
		cardStatus:'卡片状态',//EwayLocale.card.cardStatus
		eatCardTime:'吞卡时间',//EwayLocale.card.eatCardTime
		IDType:'证件类型',//EwayLocale.card.IDType
		customerName:'客户姓名',//EwayLocale.card.customerName
		customerPapers:'客户证件号',//EwayLocale.card.customerPapers
		customerPhone:'客户电话',//EwayLocale.card.customerPhone
		endData:'吞卡截止日期',//EwayLocale.card.endData
		startData:'吞卡起始日期',//EwayLocale.card.startData
		add:'添加',//EwayLocale.card.add
		dell:'删除'//EwayLocale.card.dell
	}
});
