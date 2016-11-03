Ext.apply(EwayLocale,{
	
	machine:{
		atmBrand : {
			title:'设备品牌',//EwayLocale.machine.atmBrand.title
			name: '品牌名称',//EwayLocale.machine.atmBrand.name
			country:'生产商国家或地区',//EwayLocale.machine.atmBrand.country
			hotline1:'生产商热线1',//EwayLocale.machine.atmBrand.hotline1
			hotline2:'生产商热线2',//EwayLocale.machine.atmBrand.hotline2
			address:'生产商地址',//EwayLocale.machine.atmBrand.address
			status:'生产商状态',//EwayLocale.machine.atmBrand.status
			comboxStatus:{
				provider:'设备供应',//EwayLocale.machine.atmBrand.comboxStatus.provider
				maintance:'设备服役'//EwayLocale.machine.atmBrand.comboxStatus.maintance
			}
		},
		atmCatalog:{
			title:'设备分类',//EwayLocale.machine.atmCatalog.title
			name:'分类名称',//EwayLocale.machine.atmCatalog.name
			note:'备注',//EwayLocale.machine.atmCatalog.note
			addTitle:'增加ATM分类信息',//EwayLocale.machine.atmCatalog.addTitle
			updateTitle:'更改ATM型号信息',//EwayLocale.machine.atmCatalog.updateTitle
			number:'编号'//EwayLocale.machine.atmCatalog.number
		},
		atmGroup : {
			terminalId:'设备号',//EwayLocale.machine.atmGroup.terminalId
			ip: '设备IP地址',//EwayLocale.machine.atmGroup.ip
			orgName:'所属机构',//EwayLocale.machine.atmGroup.orgName
			devTypeName:'设备型号',//EwayLocale.machine.atmGroup.devTypeName
			devVendorName:'设备品牌',//EwayLocale.machine.atmGroup.devVendorName
			devCatalogName:'设备类型',//EwayLocale.machine.atmGroup.devCatalogName
			devGroupName: '设备分组',//EwayLocale.machine.atmGroup.devGroupName
			status:'设备状态',//EwayLocale.machine.atmGroup.status
			comboxStatus:{
				dredge:'开通',//EwayLocale.machine.atmGroup.comboxStatus.dredge
				open:'开通',//EwayLocale.machine.atmGroup.comboxStatus.open
				close:'停用'//EwayLocale.machine.atmGroup.comboxStatus.close
			},
			awayFlag:'离行标志',//EwayLocale.machine.atmGroup.awayFlag
			comboxAwayFlag:{
				inBank:'在行自助服务区',//EwayLocale.machine.atmGroup.comboxAwayFlag.inBank
				outBank:'离行自助银行',//EwayLocale.machine.atmGroup.comboxAwayFlag.outBank
				clickBank:'单机离行自助服务点'//EwayLocale.machine.atmGroup.comboxAwayFlag.clickBank
			},
			devServiceName:'设备维护商',//EwayLocale.machine.atmGroup.devServiceName
			cashboxLimit:'钞箱报警金额',//EwayLocale.machine.atmGroup.cashboxLimit
			installDate:'安装日期',//EwayLocale.machine.atmGroup.installDate
			address:'地址',//EwayLocale.machine.atmGroup.address
			addTitle: '增加设备组信息',//EwayLocale.machine.atmGroup.addTitle
			groupName:'组名',//EwayLocale.machine.atmGroup.groupName
			note:'备注',//EwayLocale.machine.atmGroup.note
			updateTitle:'更改设备组信息'//EwayLocale.machine.atmGroup.updateTitle
		},
		atmModule:{
			moduleName:'模块名称',//EwayLocale.machine.atmModule.moduleName
			note:'备注',//EwayLocale.machine.atmModule.note
			atmModules:'ATM模块'//EwayLocale.machine.atmModule.atmModules
		},
		atmMove:{
			title:'移机管理',//EwayLocale.machine.atmMove.title
			moveDev:'移机',//EwayLocale.machine.atmMove.moveDev
			moveRecordInfo:'移机记录信息',//EwayLocale.machine.atmMove.moveRecordInfo
			waitMove:'待移动的机器',//EwayLocale.machine.atmMove.waitMove
			terminalId:'设备号',//EwayLocale.machine.atmMove.terminalId
			address:'源地址',//EwayLocale.machine.atmMove.address
			orgName:'源机构',//EwayLocale.machine.atmMove.orgName
			targetAddress:'目标地址',//EwayLocale.machine.atmMove.targetAddress
			targetOrganization:'目标机构',//EwayLocale.machine.atmMove.targetOrganization
			targetPerson:'目标机构负责人',//EwayLocale.machine.atmMove.targetPerson
			responsibility:'负责人',//EwayLocale.machine.atmMove.responsibility
			destPerson:'源机构负责人',//EwayLocale.machine.atmMove.destPerson
			date:'日期',//EwayLocale.machine.atmMove.date
			recoverDate:'恢复时间',//EwayLocale.machine.atmMove.recoverDate
			notice:'备注',//EwayLocale.machine.atmMove.notice
			sAddress:'所属地址',//EwayLocale.machine.atmMove.sAddress
			sOrgName:'所属机构',//EwayLocale.machine.atmMove.sOrgName
			to:'至'//EwayLocale.machine.atmMove.to
		},
		atmRuntimeInfo:{
			exportName:'导出',//EwayLocale.machine.atmRuntimeInfo.exportName
			exportDateRangeText:'开始时间不能大于结束时间',//EwayLocale.machine.atmRuntimeInfo.exportDateRangeText
			terminalId:'终端号',//EwayLocale.machine.atmRuntimeInfo.terminalId
			terminalIp:'终端IP',//EwayLocale.machine.atmRuntimeInfo.terminalIp
			startDate:'开始时间',//EwayLocale.machine.atmRuntimeInfo.startDate
			endDate:'结束时间',//EwayLocale.machine.atmRuntimeInfo.endDate
			exportLast30: '导出最后30天汇总信息',//EwayLocale.machine.atmRuntimeInfo.exportLast30
			terminalId:'编号',//EwayLocale.machine.atmRuntimeInfo.terminalId
			netIp:'网络地址',//EwayLocale.machine.atmRuntimeInfo.netIp
			msgCollect:'客服信息采集'//EwayLocale.machine.atmRuntimeInfo.msgCollect
		},
		atmType:{
			title:'设备型号',//EwayLocale.machine.atmType.title
			name:'设备型号',//EwayLocale.machine.atmType.name
			devVendorName:'所属品牌',//EwayLocale.machine.atmType.devVendorName
			devCatalogName:'所属类型',//EwayLocale.machine.atmType.devCatalogName
			devTerminalName:'所属型号',//EwayLocale.machine.atmType.devTerminalName
			spec:'设备规格',//EwayLocale.machine.atmType.spec
			weight:'设备重量',//EwayLocale.machine.atmType.weight
			watt:'平均功率',//EwayLocale.machine.atmType.watt
			no:'编号',//EwayLocale.machine.atmType.no
			cashtype:'非现金标志',//EwayLocale.machine.atmType.cashtype
			iscash:'现金',//EwayLocale.machine.atmType.iscash
			nocash:'非现金',//EwayLocale.machine.atmType.nocash
			modules:'包含的设备模块'//EwayLocale.machine.atmType.modules
		},
		device:{
			title:'设备信息管理',//EwayLocale.machine.device.title
			devDetailInfo:'设备模块详细信息',//EwayLocale.machine.device.devDetailInfo
		    idc:'读卡器',//EwayLocale.machine.device.idc
		    rfc:'非接读卡器',//EwayLocale.machine.device.rfc
			jpr:'日志打印机',//EwayLocale.machine.device.jpr
			cdm:'取款模块',//EwayLocale.machine.device.cdm
			siu:'传感器',//EwayLocale.machine.device.siu
			cim:'存款模块',//EwayLocale.machine.device.cim
			ttu:'文本终端',//EwayLocale.machine.device.ttu
			rpr:'凭条打印机',//EwayLocale.machine.device.rpr
			pin:'密码键盘',//EwayLocale.machine.device.pin
			fgp:'指纹仪',//EwayLocale.machine.device.fgp
			
			CAMStatusInfo:'摄像头(CAM)状态信息',//EwayLocale.machine.device.CAMStatusInfo
			CAMRoomStatus:'房间摄像头',//EwayLocale.machine.device.CAMRoomStatus
			CAMPersonStatus:'客户摄像头',//EwayLocale.machine.device.CAMPersonStatus
			CAMExitSlotStatus:'出钞口摄像头',//EwayLocale.machine.device.CAMExitSlotStatus

			CAMInfo:'摄像头(CAM)属性信息',//EwayLocale.machine.device.CAMInfo
			CAMMaxPictures:'最大拍照张数',//EwayLocale.machine.device.CAMMaxPictures
			CAMMaxDataLength:'最大文字数',//EwayLocale.machine.device.CAMMaxDataLength
			
			BCRInfo:'二维码扫描仪(BCR)属性信息',//EwayLocale.machine.device.BCRInfo
			BCRCanCompound:'是否为合成设备',//EwayLocale.machine.device.BCRCanCompound
			BCRCanFilterSymbologies:'能否辨别制定的条码',//EwayLocale.machine.device.BCRCanFilterSymbologies
			
			CDMInfo:'取款模块（CDM）属性信息',//EwayLocale.machine.device.CDMInfo
			hasStack:'是否具有暂存器',//EwayLocale.machine.device.hasStack
			hasShutter:'是否具有shutter门',//EwayLocale.machine.device.hasShutter
			canRetract:'是否具有回收能力',//EwayLocale.machine.device.canRetract
			canDetectCashTaken:'是否探测钞币被拿走',//EwayLocale.machine.device.canDetectCashTaken
			canTestPhysicalUnits:'是否能测试物理单元',//EwayLocale.machine.device.canTestPhysicalUnits
			maxDispensBills:'获取单笔最大挖钞张数',//EwayLocale.machine.device.maxDispensBills
			logicalUnits:'逻辑钞箱个数',//EwayLocale.machine.device.logicalUnits
			physicalUnits:'物理钞箱个数',//EwayLocale.machine.device.physicalUnits
			currency:'支持的币种类别总个数',//EwayLocale.machine.device.currency
			currencies:'支持的币种类别',//EwayLocale.machine.device.currencies
			exponents:'指数',//EwayLocale.machine.device.exponents

			CIMInfo:'存款模块 （CIM）属性信息',//EwayLocale.machine.device.CIMInfo
			canEscrow:'是否具有暂存器',//EwayLocale.machine.device.canEscrow
			shutterControlSupported:'是否支持控制shutter门',//EwayLocale.machine.device.shutterControlSupported
			maxAcceptItems:'单笔最大验钞张数',//EwayLocale.machine.device.maxAcceptItems
			canDetectCashInserted:'是否能探测钞票放入',//EwayLocale.machine.device.canDetectCashInserted
			canDetectCashTaken:'是否能探测钞票被取走',//EwayLocale.machine.device.canDetectCashTaken
			retractAreas:'回收位置',//EwayLocale.machine.device.retractAreas


			IDCInfo:'读卡器模块(IDC)属性信息',//EwayLocale.machine.device.IDCInfo
			RFCInfo:'非接读卡器模块(RFC)属性信息',//EwayLocale.machine.device.RFCInfo
			variant:'读卡器类型',//EwayLocale.machine.device.variant
			canEjectCard:'是否具有退卡能力',//EwayLocale.machine.device.canEjectCard
			trackJisiiRead:'是否具有TrackJisii读能力',//EwayLocale.machine.device.trackJisiiRead
			track1Read:'是否具有读一磁道数据能力',//EwayLocale.machine.device.track1Read
			track2Read:'是否具有读二磁道数据能力',//EwayLocale.machine.device.track2Read
			track3Read:'是否具有读三磁道数据能力',//EwayLocale.machine.device.track3Read
			canCapture:'是否具有吞卡能力',//EwayLocale.machine.device.canCapture
			binCapacity:'最大吞卡张数',//EwayLocale.machine.device.binCapacity
			security:'是否具有安全支持',//EwayLocale.machine.device.security
			trackJisiiWrite:'是否具有TrackJisii写能力',//EwayLocale.machine.device.trackJisiiWrite
			track1Write:'是否具有写一磁道数据能力',//EwayLocale.machine.device.track1Write
			track2Write:'是否具有写二磁道数据能力',//EwayLocale.machine.device.track2Write
			track3Write:'是否具有写三磁道数据能力',//EwayLocale.machine.device.track3Write


			JPRInfo:'日志打印机模块(JPR)属性信息',//EwayLocale.machine.device.JPRInfo
			canEject:'是否具有退纸能力',//EwayLocale.machine.device.canEject
			canCapture:'是否具有回收能力',//EwayLocale.machine.device.canCapture
			canStack:'是否具有暂存能力',//EwayLocale.machine.device.canStack

			PINInfo:'密码键盘(PIN)属性信息',//EwayLocale.machine.device.PINInfo
			canEBC:'能否EBC',//EwayLocale.machine.device.canEBC
			canCBC:'能否CBC',//EwayLocale.machine.device.canCBC
			canMAC:'能否MAC',//EwayLocale.machine.device.canMAC
			canRSA:'能否RSA',//EwayLocale.machine.device.canRSA
			canVerifyVISA:'能否验证VISA',//EwayLocale.machine.device.canVerifyVISA
			canVerifyDES:'能否验证DES',//EwayLocale.machine.device.canVerifyDES
			functionKeys:'功能键支持',//EwayLocale.machine.device.functionKeys
			canTripleEBC:'是否支持多重EBC',//EwayLocale.machine.device.canTripleEBC
			canTripleCBC:'是否支持多重CBC',//EwayLocale.machine.device.canTripleCBC
			canTripleMAC:'是否支持多重MAC',//EwayLocale.machine.device.canTripleMAC
			canTripleCFB:'是否支持多重CFB',//EwayLocale.machine.device.canTripleCFB
			canVerifyECB:'能否验证ECB',//EwayLocale.machine.device.canVerifyECB
			canDESOffset:'能否DeS偏移',//EwayLocale.machine.device.canDESOffset

			RPRInfo:'凭条打印机(RPR)属性信息',//EwayLocale.machine.device.RPRInfo
			canEject:'是否具有退纸能力',//EwayLocale.machine.device.canEject
			canCapture:'是否具有回收能力',//EwayLocale.machine.device.canCapture
			canStack:'是否具有暂存能力',//EwayLocale.machine.device.canStack
			maxRetract:'最大回收张数',//EwayLocale.machine.device.maxRetract

			SIUInfo:'SIU能力属性信息',//EwayLocale.machine.device.SIUInfo
			operatorSwitchSupported:'是否支持操作员开关',//EwayLocale.machine.device.operatorSwitchSupported
			cabinetSupported:'是否支持后盖门打开传感能力',//EwayLocale.machine.device.cabinetSupported
			safeSupported:'是否支持安全门打开传感能力',//EwayLocale.machine.device.safeSupported
			indicatorSupported:'是否支持靠近传感能力',//EwayLocale.machine.device.indicatorSupported
			guidelightIdcSupported:'是否支持插卡指示灯能力',//EwayLocale.machine.device.guidelightIdcSupported
			guidelightCdmSupported:'是否支持取款指示灯能力',//EwayLocale.machine.device.guidelightCdmSupported
			guidelightReceiptSupported:'是否支持凭条打印指示灯能力',//EwayLocale.machine.device.guidelightReceiptSupported
			guidelightCimSupported:'是否支持存款指示灯能力',//EwayLocale.machine.device.guidelightCimSupported

			TTUInfo:'文本终端单元(TTU)属性信息',//EwayLocale.machine.device.TTUInfo
			alphanumericKeysPresent:'是否支持字母数字键输入',//EwayLocale.machine.device.alphanumericKeysPresent
			numericKeysPresent:'是否支持数字键输入',//EwayLocale.machine.device.numericKeysPresent
			displayLightPresent:'是否支持屏幕亮度调节',//EwayLocale.machine.device.displayLightPresent
			cursorSupported:'是否支持鼠标',//EwayLocale.machine.device.cursorSupported
			resolutionX:'横轴分辨率',//EwayLocale.machine.device.resolutionX
			hexadecimalKeysPresent:'是否支持十六进制键输入',//EwayLocale.machine.device.hexadecimalKeysPresent
			keyboardLockPresent:'是否支持键盘锁定',//EwayLocale.machine.device.keyboardLockPresent
			formsSupported:'是否支持表格',//EwayLocale.machine.device.formsSupported
			resolutionY:'纵轴分辨率',//EwayLocale.machine.device.resolutionY
			
			ISCInfo:'身份证扫描仪(ISC)属性信息',//EwayLocale.machine.device.ISCInfo
			
			ICCInfo:'发卡器(ICC)属性信息',//EwayLocale.machine.device.ICCInfo
			dispenseCard:'是否具有发卡读能力',//EwayLocale.machine.device.dispenseCard
			
			FGPInfo:'指纹仪(FGP)属性信息',//EwayLocale.machine.device.FGPInfo
			fgp_variant:'指纹仪类型',//EwayLocale.machine.device.fgp_variant
			canCompare:'是否具有比较特值功能',//EwayLocale.machine.device.canCompare

			UKRInfo:'读UKey模块(UKR)属性信息',//EwayLocale.machine.device.UKRInfo
			variantUKR:'读UKey类型',//EwayLocale.machine.device.variant
			canEjectUKR:'是否具有退UKey能力',//EwayLocale.machine.device.canEjectUKR
			canCaptureUKR:'是否具有吞UKey能力',//EwayLocale.machine.device.canCaptureUKR
			binCapacityUKR:'获取最大吞UKey个数',//EwayLocale.machine.device.binCapacityUKR

			UKDInfo:'发UKey模块(UKD)属性信息',//EwayLocale.machine.device.UKDInfo
			variantUKD:'发UKey类型',//EwayLocale.machine.device.variant
			canEjectUKD:'是否具有退UKey能力',//EwayLocale.machine.device.canEjectUKD
			canCaptureUKD:'是否具有吞UKey能力',//EwayLocale.machine.device.canCaptureUKD
			binCapacityUKD:'获取最大吞UKey个数',//EwayLocale.machine.device.binCapacity
			dispenseCardUKD:'是否可发UKey',//EwayLocale.machine.device.dispenseCardUKD
			
			PBKInfo:'存折打印机(PBK)属性信息',//EwayLocale.machine.device.PBKInfo
			
			comStatus:'厂商状态信息',//EwayLocale.machine.device.comStatus
			hwCode:'厂商故障码',//EwayLocale.machine.device.hwCode
			CDMStatus:'取款模块(CDM)状态信息',//EwayLocale.machine.device.CDMStatus
			cashUnits:'钞箱状态',//EwayLocale.machine.device.cashUnits
			safeDoor:'安全门状态',//EwayLocale.machine.device.safeDoor
			intermediateStacker:'暂存器状态',//EwayLocale.machine.device.intermediateStacker
			outBox:'取款钞箱',//EwayLocale.machine.device.outBox
			pcuId:'物理逻辑钞箱对应关系',//EwayLocale.machine.device.pcuId
			cuId:'逻辑钞箱ID',//EwayLocale.machine.device.cuId
			cuCurrency:'逻辑钞箱币种',//EwayLocale.machine.device.cuCurrency
			cuCurrentCount:'逻辑钞箱当前张数',//EwayLocale.machine.device.cuCurrentCount
			cuInitialCount:'逻辑钞箱初始张数',//EwayLocale.machine.device.cuInitialCount
			cuRejectCount:'逻辑钞箱reject张数',//EwayLocale.machine.device.cuRejectCount
			cuNoteValue:'逻辑钞箱面值',//EwayLocale.machine.device.cuNoteValue
			cuBinStatus:'逻辑钞箱状态',//EwayLocale.machine.device.cuBinStatus
			puId:'物理钞箱ID',//EwayLocale.machine.device.puId
			puPosName:'物理钞箱位置名称',//EwayLocale.machine.device.puPosName
			puBinStatus:'物理钞箱状态',//EwayLocale.machine.device.puBinStatus
			puCurrentCount:'物理钞箱当前张数',//EwayLocale.machine.device.puCurrentCount
			puInitialCount:'物理钞箱初始张数',//EwayLocale.machine.device.puInitialCount
			puRejectCount:'物理钞箱Reject张数',//EwayLocale.machine.device.puRejectCount
			cuBinType:'逻辑钞箱类型',//EwayLocale.machine.device.cuBinType

			CIMStatus:'存款模块(CIM)状态信息',//EwayLocale.machine.device.CIMStatus
			baffle:'挡板状态',//EwayLocale.machine.device.baffle
			inOutPositionStatus:'传输状态',//EwayLocale.machine.device.inOutPositionStatus
			inBox:'存款钞箱',//EwayLocale.machine.device.inBox
			puCashInCount:'物理钞箱入钞张数',//EwayLocale.machine.device.puCashInCount
			pcuId:'物理钞箱与逻辑钞箱对应关系',//EwayLocale.machine.device.pcuId
			cuType:'逻辑钞箱类型',//EwayLocale.machine.device.cuType
			cuBinStatus:'逻辑钞箱状态',//EwayLocale.machine.device.cuBinStatus
			cuCurrentCount:'逻辑钞箱当前张数',//EwayLocale.machine.device.cuCurrentCount
			cuCurrency:'逻辑钞箱币种',//EwayLocale.machine.device.cuCurrency
			cuNoteValue:'逻辑钞箱面值',//EwayLocale.machine.device.cuNoteValue

			IDCStatus:'读卡器模块(IDC)状态信息',//EwayLocale.machine.device.IDCStatus
			RFCStatus:'非接读卡器模块(RFC) status',//EwayLocale.machine.device.RFCStatus
			media:'媒体状态',//EwayLocale.machine.device.media
			retainBin:'回收盒状态',//EwayLocale.machine.device.retainBin
			cards:'回收盒数量',//EwayLocale.machine.device.cards

			JRPStatus:'日志打印机模块(JPR)状态信息',//EwayLocale.machine.device.JRPStatus
			supplyLevel:'打印纸状态',//EwayLocale.machine.device.supplyLevel
			ink:'墨水',//EwayLocale.machine.device.ink
			toner:'色带',//EwayLocale.machine.device.toner

			PINStatus:'密码键盘模块(PIN)状态信息',//EwayLocale.machine.device.PINStatus

			RPRStatus:'凭条打印机模块(RPR)状态信息',//EwayLocale.machine.device.RPRStatus
			bin:'回收单元状态',//EwayLocale.machine.device.bin

			SIUStatus:'SIU能力状态信息',//EwayLocale.machine.device.SIUStatus
			vandalShield:'防护罩状态',//EwayLocale.machine.device.vandalShield
			operatorSwitch:'操作员按钮状态',//EwayLocale.machine.device.operatorSwitch
			ambientLight:'环境灯状态',//EwayLocale.machine.device.ambientLight
			cabinet:'箱门状态',//EwayLocale.machine.device.cabinet
			safe:'安全门状态',//EwayLocale.machine.device.safe
			idcGuidelight:'插卡导引灯状态',//EwayLocale.machine.device.idcGuidelight
			cdmGuidelight:'取钞引导指示灯状态',//EwayLocale.machine.device.cdmGuidelight
			receiptGuidelight:'凭条导引灯状态',//EwayLocale.machine.device.receiptGuidelight
			cimGuidelight:'CIM导引灯状态',//EwayLocale.machine.device.cimGuidelight

			TTUStatus:'文本终端单元(TTU)状态信息',//EwayLocale.machine.device.TTUStatus
			
			ISCStatus:'身份证扫描仪(ISC)状态信息',//EwayLocale.machine.device.ISCStatus
			
			ICCStatus:'发卡器(ICC)状态信息',//EwayLocale.machine.device.ICCStatus
			
			FGPStatus:'指纹仪(FGP)状态信息',//EwayLocale.machine.device.FGPStatus
			
			PBKStatus:'存折打印机(PBK)状态信息',//EwayLocale.machine.device.PBKStatus

			UKRStatus:'读UKEY模块(UKR)状态信息',//EwayLocale.machine.device.UKRStatus
			chipModule:'芯片模块',//EwayLocale.machine.device.chipModule
			binStatus:'获取回收匣状态',//EwayLocale.machine.device.binStatus
			ukeys:'吞UKEY个数',//EwayLocale.machine.device.ukeys

			UKDStatus:'发UKEY模块(UKD)状态信息',//EwayLocale.machine.device.UKDStatus
			ukeyCount:'UKEY个数',//EwayLocale.machine.device.ukeyCount

			devPerson:'设备人员信息',//EwayLocale.machine.device.devPerson
			devModuleMsg:'设备模块属性信息',//EwayLocale.machine.device.devModuleMsg
			devBasicMsg:'设备基本信息',//EwayLocale.machine.device.devBasicMsg
			devTailMsg:'设备详细信息',//EwayLocale.machine.device.devTailMsg
			managePerson:'管机员',//EwayLocale.machine.device.managePerson
			maintainPerson:'维护员',//EwayLocale.machine.device.maintainPerson
			name:'姓名',//EwayLocale.machine.device.name
			mobile:'手机',//EwayLocale.machine.device.mobile
			phone:'固定电话',//EwayLocale.machine.device.phone
			email:'邮件地址',//EwayLocale.machine.device.email
			deviceBasicInfo:'设备基本信息',//EwayLocale.machine.device.deviceBasicInfo
			lineLogo:'在行标志',//EwayLocale.machine.device.lineLogo
			alarmRateRMB:'钞箱报警金额(人民币)',//EwayLocale.machine.device.alarmRateRMB
			operation:'经营方式',//EwayLocale.machine.device.operation
			ipAddress:'IP地址',//EwayLocale.machine.device.ipAddress
			swallowCard:'吞卡张数',//EwayLocale.machine.device.swallowCard
			alarmRateHKD:'钞箱报警金额(港币)',//EwayLocale.machine.device.alarmRateHKD
			adminPhone:'管理员(手机号)',//EwayLocale.machine.device.adminPhone
			maintainPhone: '维护员(手机号)',//EwayLocale.machine.device.maintainPhone
			log:'钞箱标识',//EwayLocale.machine.device.log
			style: '钞箱类型',//EwayLocale.machine.device.style
			status: '钞箱状态',//EwayLocale.machine.device.status
			initailnumber: '初始张数',//EwayLocale.machine.device.initailnumber
			postnumber: '存款张数',//EwayLocale.machine.device.postnumber
			currentnumber: '当前钞箱张数',//EwayLocale.machine.device.currentnumber
			facevalue: '钞箱面值',//EwayLocale.machine.device.facevalue
			currency: '钞箱币种',//EwayLocale.machine.device.currency
			systemHardwareInfo: '系统软硬件信息',//EwayLocale.machine.device.systemHardwareInfo
			moduleVersionInfo:'模块硬件版本信息（实时）',//EwayLocale.machine.device.moduleVersionInfo
			devModuleStatusInfo: '设备模块状态（实时）',//EwayLocale.machine.device.devModuleStatusInfo
			devModuleAttributeInfo: '设备模块属性信息（实时）',//EwayLocale.machine.device.devModuleAttributeInfo

			remoteControl: '远程控制',//EwayLocale.machine.device.remoteControl
			collectJPR:'提取日志',//EwayLocale.machine.device.collectJPR
			remoteScreen:'远程抓屏',//EwayLocale.machine.device.remoteScreen
			processCheck:'进程查看',//EwayLocale.machine.device.processCheck
			remoteExplorer:'远程浏览',//EwayLocale.machine.device.remoteExplorer
			netWorkLink:'网络连接',//EwayLocale.machine.device.netWorkLink
			remoteRestart:'远程重启',//EwayLocale.machine.device.remoteRestart

			progressTip:'进度提示',//EwayLocale.machine.device.progressTip
			updateProBar:'这是通过动态更新内容形成的进度条',//EwayLocale.machine.device.updateProBar


			restartApply: ' 重启应用',//EwayLocale.machine.device.restartApply
			confirmRestartApply:'确定要重启应用？',//EwayLocale.machine.device.confirmRestartApply
			nowRestartApply:'正在重启应用',//EwayLocale.machine.device.nowRestartApply
			restartApplySuc:'成功重启该设备应用',//EwayLocale.machine.device.restartApplySuc
			restartApplyFail:'重启应用失败！',//EwayLocale.machine.device.restartApplyFail


			restartDrive:'重启硬件驱动',//EwayLocale.machine.device.restartDrive
			confirmRestartDrive:'确定要重启硬件驱动？',//EwayLocale.machine.device.confirmRestartDrive
			nowRestartDrive:'正在重启硬件驱动',//EwayLocale.machine.device.nowRestartDrive
			restartDriveSuc:'成功重启该设备硬件驱动',//EwayLocale.machine.device.restartDriveSuc
			restartDriveFail:'重启硬件驱动失败！',//EwayLocale.machine.device.restartDriveFail

			restartOS:'重启操作系统',//EwayLocale.machine.device.restartOS
			confirmRestartOS:'确定要重启操作系统？',//EwayLocale.machine.device.confirmRestartOS
			nowRestartOS:'正在重启操作系统',//EwayLocale.machine.device.nowRestartOS
			restartOSSuc:'成功重启该设备操作系统',//EwayLocale.machine.device.restartOSSuc
			restartOSFail:'重启操作系统失败！',//EwayLocale.machine.device.restartOSFail

			remoteShutdown:'远程关机',//EwayLocale.machine.device.remoteShutdown
			shutdownApply:'关闭应用',//EwayLocale.machine.device.shutdownApply
			confirmShutdownApply:'确定要关闭应用？',//EwayLocale.machine.device.confirmShutdownApply
			nowShutdownApply:'正在关闭应用',//EwayLocale.machine.device.nowShutdownApply
			shutdownApplySuc:'成功关闭该设备应用',//EwayLocale.machine.device.shutdownApplySuc
			shutdownApplyFail:'关闭应用失败！',//EwayLocale.machine.device.shutdownApplyFail

			shutdownDrive:'关闭硬件驱动',//EwayLocale.machine.device.shutdownDrive
			confirmShutdownDrive:'确定要关闭硬件驱动？',//EwayLocale.machine.device.confirmShutdownDrive
			nowShutdownDrive:'正在关闭硬件驱动',//EwayLocale.machine.device.nowShutdownDrive
			shutdownDriveSuc:'成功关闭该设备硬件驱动',//EwayLocale.machine.device.shutdownDriveSuc
			shutdownDriveFail:'关闭硬件驱动失败！',//EwayLocale.machine.device.shutdownDriveFail

			shutdownOS:'关闭操作系统',//EwayLocale.machine.device.shutdownOS
			confirmShutdownOS:'确定要关闭操作系统？',//EwayLocale.machine.device.confirmShutdownOS
			nowShutdownOS:'正在关闭操作系统',//EwayLocale.machine.device.nowShutdownOS
			shutdownOSSuc:'成功关闭该设备操作系统',//EwayLocale.machine.device.shutdownOSSuc
			shutdownOSFail:'关闭操作系统失败！',//EwayLocale.machine.device.shutdownOSFail
			getSoftwareList:'获取软件安装列表',//EwayLocale.machine.device.getSoftwareList
			forceReset:'强制复位',//EwayLocale.machine.device.forceReset
			openService:'开启服务',//EwayLocale.machine.device.openService
			pauseService:'暂停服务',//EwayLocale.machine.device.pauseService
			checkStatus:'状态检测',//EwayLocale.machine.device.checkStatus

			remoteBrowseDisk:'远程浏览',//EwayLocale.machine.device.remoteBrowseDisk

			sysHardwareInfo:'系统硬件信息',//EwayLocale.machine.device.sysHardwareInfo
			diskMem:'硬盘大小',//EwayLocale.machine.device.diskMem
			biosVersion:'Bios版本',//EwayLocale.machine.device.biosVersion
			biosVendor:'Bios厂商',//EwayLocale.machine.device.biosVendor
			biosReleaseDate:'Bios发布日期',//EwayLocale.machine.device.biosReleaseDate
			memorySize:'内存总数',//EwayLocale.machine.device.memorySize
			memoryUsed:'已使用内存',//EwayLocale.machine.device.memoryUsed
			memoryFree:'空闲内存',//EwayLocale.machine.device.memoryFree
			memoryPercent:'内存使用率',//EwayLocale.machine.device.memoryPercent
			cpuItemID:'cpu信息',//EwayLocale.machine.device.cpuItemID
			cpuFrequency:'CPU频率(MHz)',//EwayLocale.machine.device.cpuFrequency
			cpuVendor:'CPU的厂商',//EwayLocale.machine.device.cpuVendor
			cpuModel:'CPU的类别',//EwayLocale.machine.device.cpuModel
			cacheSize:'缓冲存储器数量',//EwayLocale.machine.device.cacheSize
			totalCores:'CPU核数',//EwayLocale.machine.device.totalCores
			userUsePercent:'用户使用率',//EwayLocale.machine.device.userUsePercent
			sysUsePercent:'系统使用率',//EwayLocale.machine.device.sysUsePercent
			idlePercent:'当前空闲率',//EwayLocale.machine.device.idlePercent
			combinedPercent:'总的使用率',//EwayLocale.machine.device.combinedPercent
			diskItemID:'磁盘信息',//EwayLocale.machine.device.diskItemID
			diskName:'磁盘分区名称',//EwayLocale.machine.device.diskName
			diskFileSys:'磁盘文件系统',//EwayLocale.machine.device.diskFileSys
			diskTotalSize:'磁盘总大小',//EwayLocale.machine.device.diskTotalSize
			diskFreeSize:'磁盘可用空间大小',//EwayLocale.machine.device.diskFreeSize
			sysSoftInfo:'系统软件信息',//EwayLocale.machine.device.sysSoftInfo
			OSID:'操作系统ID',//EwayLocale.machine.device.OSID
			OSDescription:'OS描述',//EwayLocale.machine.device.OSDescription
			OSType:'OS类型',//EwayLocale.machine.device.OSType
			sysPatchLevel:'系统补丁级别',//EwayLocale.machine.device.sysPatchLevel
			chkCashData:'验钞数据版本',//EwayLocale.machine.device.chkCashData
			OSVendor:'OS供应商',//EwayLocale.machine.device.OSVendor
			OSVendorName:'OS供应商名',//EwayLocale.machine.device.OSVendorName
			sysVersion:'系统版本号',//EwayLocale.machine.device.sysVersion
			devAddress:'设备地址',//EwayLocale.machine.device.devAddress
			basicInfo:'其它信息',//EwayLocale.machine.device.basicInfo
			virtual:'虚拟设备号',//EwayLocale.machine.device.virtual
			serial:'设备序列号',//EwayLocale.machine.device.serial
			installDate:'设备安装日期',//EwayLocale.machine.device.installDate
			cash: '现金',//EwayLocale.machine.device.cash
			installStyle: '安装方式',//EwayLocale.machine.device.installStyle
			crossWall: '穿墙',//EwayLocale.machine.device.crossWall
			mainRoom: '大堂',//EwayLocale.machine.device.mainRoom
			netType: '网络类型',//EwayLocale.machine.device.netType
			wired: '有线',//EwayLocale.machine.device.wired
			wireless: '无线',//EwayLocale.machine.device.wireless
			wiredAndWireless: '有线无线',//EwayLocale.machine.device.wiredAndWireless
			onBankSignal:'在行离行标志',//EwayLocale.machine.device.onBankSignal
			inBank:'在行自助服务区',//EwayLocale.machine.device.inBank
			outBank:'离行自助银行',//EwayLocale.machine.device.outBank
			clickBank:'单机离行自助服务点',//EwayLocale.machine.device.clickBank
			operation:'经营方式',//EwayLocale.machine.device.operation
			operationSelf: '自营',//EwayLocale.machine.device.operationSelf
			cooperation: '合作',//EwayLocale.machine.device.cooperation
			epiboly: '外包',//EwayLocale.machine.device.epiboly
			managePerson:'管机员',//EwayLocale.machine.device.managePerson
			maintainPerson:'维护员',//EwayLocale.machine.device.maintainPerson
			to:'至',//EwayLocale.machine.device.to



			devices:'设备',//EwayLocale.machine.device.devices
			configuration:'配置信息',//EwayLocale.machine.device.configuration
			spVersion:'SP版本',//EwayLocale.machine.device.spVersion
			notSupport:'不支持',//EwayLocale.machine.device.notSupport
			drive:'驱动',//EwayLocale.machine.device.drive
			firmway: '固件',//EwayLocale.machine.device.firmway
			noDevice:'无设备',//EwayLocale.machine.device.noDevice
			devTypeInfo: '设备型号信息',//EwayLocale.machine.device.devTypeInfo

			devInfo:'设备信息',//EwayLocale.machine.device.devInfo
			unable:'不可以',//EwayLocale.machine.device.unable
			able:'能',//EwayLocale.machine.device.able

			addDevInfo:'增加设备信息',//EwayLocale.machine.device.addDevInfo
			effectiveDate:'生效日期',//EwayLocale.machine.device.effectiveDate
			changeDevInfo:'更改设备信息',//EwayLocale.machine.device.changeDevInfo
			person:{
				week:'星期',//EwayLocale.machine.device.person.week
				Mon:'星期一',//EwayLocale.machine.device.person.Mon
				Tues:'星期二',//EwayLocale.machine.device.person.Tues
				Wed:'星期三',//EwayLocale.machine.device.person.Wed
				Thur:'星期四',//EwayLocale.machine.device.person.Thur
				Fri:'星期五',//EwayLocale.machine.device.person.Fri
				Sat:'星期六',//EwayLocale.machine.device.person.Sat
				Sun:'星期日',//EwayLocale.machine.device.person.Sun
				openClose:'开机/关机',//EwayLocale.machine.device.person.openClose
				Open:'开机',//EwayLocale.machine.device.person.Open
				Close:'关机'//EwayLocale.machine.device.person.Close
			}

		},
		param:{
			paramKey:'参数',//EwayLocale.machine.param.paramKey
			paramValue:'参数值',//EwayLocale.machine.param.paramValue
			paramClassify:'参数分类',//EwayLocale.machine.param.paramClassify
			modifyFlag:'是否可以修改',//EwayLocale.machine.param.modifyFlag
			comboxClassify:{
				unableUpdate:'不可修改',//EwayLocale.machine.param.comboxClassify.unableUpdate
				ableUpdate:'可以修改'//EwayLocale.machine.param.comboxClassify.ableUpdate
			},
			description:'描述',//EwayLocale.machine.param.description
			systemCon:'系统配置',//EwayLocale.machine.param.systemCon
			updateSystemCon:'更改系统配置'//EwayLocale.machine.param.updateSystemCon
		},
		quittingNotice:{
			addCloseMsg:'增加报停信息',//EwayLocale.machine.quittingNotice.addCloseMsg
			updateCloseMsg:'更改报停信息',//EwayLocale.machine.quittingNotice.updateCloseMsg
			dateRangeText:'恢复日期不能小于等于停止日期,请重新选择',//EwayLocale.machine.quittingNotice.dateRangeText
			click:'请点击查询，选择设备',//EwayLocale.machine.quittingNotice.click
			stopTime:'停机时间',//EwayLocale.machine.quittingNotice.stopTime
			openTime:'恢复时间',//EwayLocale.machine.quittingNotice.openTime
			currentStatus:'当前状态',//EwayLocale.machine.quittingNotice.currentStatus
			closeType:'停机类型',//EwayLocale.machine.quittingNotice.closeType
			responsibilityName:'停机负责人',//EwayLocale.machine.quittingNotice.responsibilityName
			stopReason:'停机原因',//EwayLocale.machine.quittingNotice.stopReason
			address:'所属地址',//EwayLocale.machine.quittingNotice.address
			selectDev:'选择需要报停的设备',//EwayLocale.machine.quittingNotice.selectDev
			updateUnable:'不能对已执行完成的报停记录进行修改.',//EwayLocale.machine.quittingNotice.updateUnable
			to:'至',//EwayLocale.machine.quittingNotice.to
			stopType:'停机类型',//EwayLocale.machine.quittingNotice.stopType
			comboxStopType:{
				recess:'放假',//EwayLocale.machine.quittingNotice.comboxStopType.recess
				fit:'装修',//EwayLocale.machine.quittingNotice.comboxStopType.fit
				power:'停电',//EwayLocale.machine.quittingNotice.comboxStopType.power
				devFailue:'设备故障未修复',//EwayLocale.machine.quittingNotice.comboxStopType.devFailue
				other:'其他'//EwayLocale.machine.quittingNotice.comboxStopType.other
			},
			setTime:'创建时间',//EwayLocale.machine.quittingNotice.setTime
			closeManage:'报停管理'//EwayLocale.machine.quittingNotice.closeManage
		},
		plan:{
			addPlan:'增加开机方案',//EwayLocale.machine.plan.addPlan
			name:'方案名称',//EwayLocale.machine.plan.name
			type:'方案类型',//EwayLocale.machine.plan.type
			startDate:'有效开始时间',//EwayLocale.machine.plan.startDate
			endDate:'有效结束时间',//EwayLocale.machine.plan.endDate
			changePlan:'更改方案',//EwayLocale.machine.plan.changePlan
			servicePlan:'开机方案'//EwayLocale.machine.plan.servicePlan
		},
		serviceplan:{
			title:'开机方案',//EwayLocale.machine.serviceplan.title
			name:'方案名称',//EwayLocale.machine.serviceplan.name
			machineQuantity:'设备数量',//EwayLocale.machine.serviceplan.machineQuantity
			state:'状态',//EwayLocale.machine.serviceplan.state
			openDate:'有效开机时间',//EwayLocale.machine.serviceplan.openDate
			closeDate:'有效关机时间',//EwayLocale.machine.serviceplan.closeDate
			createDateTime:'创建时间',//EwayLocale.machine.serviceplan.createDateTime
			date:'日期',//EwayLocale.machine.serviceplan.date
			week:'星期',//EwayLocale.machine.serviceplan.week
			weekDay:'星期',//EwayLocale.machine.serviceplan.weekDay
			inportLinkedMachine:'导入关联设备',//EwayLocale.machine.serviceplan.inportLinkedMachine
			selectFile:'选择文件',//EwayLocale.machine.serviceplan.selectFile
			placeUploadingResource:'请上传资源',//EwayLocale.machine.serviceplan.placeUploadingResource
			fileNotSupport:'导入的文件格式不支持,请按模板导入设备信息',//EwayLocale.machine.serviceplan.fileNotSupport
			exportExplain:'导入说明',//EwayLocale.machine.serviceplan.exportExplain
			thisIsTooLong:'请在设备导入模板中连续添加要下发的设备号,最多一次性导入2000条数据(约耗时5分钟),最少导入1条数据',//EwayLocale.machine.serviceplan.thisIsTooLong
			thisHardToTranslate:'点击下载导入设备号模板',//EwayLocale.machine.serviceplan.thisHardToTranslate
			planDevice:'方案<-->设备',//EwayLocale.machine.serviceplan.planDevice
			timeEare:'输入时间段有误，请重新输入！',//EwayLocale.machine.serviceplan.timeEare
			timeError:'输入时间段有误，请重新输入！',//EwayLocale.machine.serviceplan.timeError
			planOlonOne:'同方案只能设置开机或关机的一种',//EwayLocale.machine.serviceplan.planOlonOne
			setTime:'请设置详细时间',//EwayLocale.machine.serviceplan.setTime
			thisPlanStop:'(此方案已停用，不可应用！)',//EwayLocale.machine.serviceplan.thisPlanStop
			placeRefresh:'条解除失败，请刷新后查看！',//EwayLocale.machine.serviceplan.placeRefresh
			linking:'正在关联设备....',//EwayLocale.machine.serviceplan.linking
			testingPlaceWaiting:'正在判断设备号是否符合要求，请耐心等待...',//EwayLocale.machine.serviceplan.testingPlaceWaiting
			leastOne:'最少一次导入1条设备信息，请重新选择导入文件!',//EwayLocale.machine.serviceplan.leastOne
			notMore:'最多一次导入2000条设备信息，请重新选择导入文件!',//EwayLocale.machine.serviceplan.notMore
			checkFile:'请检查导入文件',//EwayLocale.machine.serviceplan.checkFile
			fileNotAllowed:'文件不符合要求！',//EwayLocale.machine.serviceplan.fileNotAllowed
			tipExportSuccess:'条数据,成功导入',//EwayLocale.machine.serviceplan.tipExportSuccess
			tipLookUp:'条,点击查看导入详情!',//EwayLocale.machine.serviceplan.tipLookUp
			tipAddError:'条添加失败,请刷新后查看',//EwayLocale.machine.serviceplan.tipAddError
			tipRelevanceError:'条关联失败，请刷新后查看！',//EwayLocale.machine.serviceplan.tipRelevanceError
			chooseOne:'至少选择一个',//EwayLocale.machine.serviceplan.chooseOne
			linkSuccess:'关联成功',//EwayLocale.machine.serviceplan.linkSuccess
			Mon:'一',//EwayLocale.machine.serviceplan.Mon
			Tues:'二',//EwayLocale.machine.serviceplan.Tues
			Wed:'三',//EwayLocale.machine.serviceplan.Wed
			Thur:'四',//EwayLocale.machine.serviceplan.Thur
			Fri:'五',//EwayLocale.machine.serviceplan.Fri
			Sat:'六',//EwayLocale.machine.serviceplan.Sat
			Sun:'日',//EwayLocale.machine.serviceplan.Sun
			useSuccess:'正常启用',//EwayLocale.machine.serviceplan.useSuccess
			notSuccess:'未启用',//EwayLocale.machine.serviceplan.notSuccess
			lanDetailWeek:'星期方案详细列表',//EwayLocale.machine.serviceplan.lanDetailWeek
			planDetailDay:'日期方案详细列表',//EwayLocale.machine.serviceplan.planDetailDay
			selectPlan:'选择开机方案',//EwayLocale.machine.serviceplan.selectPlan
			weekSelect:'通知方式'//EwayLocale.machine.serviceplan.weekSelect
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
		searchIllegal:'查询项中存在不合法的输入,不能导出.',//EwayLocale.atmLog.searchIllegal
		analysis:'分析',//EwayLocale.atmLog.analysis
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
	},
	deviceInfo:{
		title:'设备信息',//EwayLocale.deviceInfo.title
		importTerminalId:'请输入设备号!',//EwayLocale.deviceInfo.importTerminalId
		runInfo:'设备运行信息',//EwayLocale.deviceInfo.runInfo
		tradingInfoChart:'交易信息图',//EwayLocale.deviceInfo.tradingInfoChart
		tradingCount:'交易笔数:',//EwayLocale.deviceInfo.tradingCount
		tradingAmt:'交易金额:',//EwayLocale.deviceInfo.tradingAmt
		OpenRateInfo:'开机率日均趋势图',//EwayLocale.deviceInfo.OpenRateInfo
		CashSettleInit:'清机加钞信息',//EwayLocale.deviceInfo.CashSettleInit
		clearAmt:'清机金额',//EwayLocale.deviceInfo.clearAmt
		withdrawalCount:'取款次数',//EwayLocale.deviceInfo.withdrawalCount
		depositCount:'存款次数',//EwayLocale.deviceInfo.depositCount
		controllerInfo:'控制信息',//EwayLocale.deviceInfo.controllerInfo
		boxInitAmt:'钱箱初始金额',//EwayLocale.deviceInfo.boxInitAmt
		boxCurrentAmt:'钱箱当前金额',//EwayLocale.deviceInfo.boxCurrentAmt
		deviceSwallowCardCount:'设备吞卡数量',//EwayLocale.deviceInfo.deviceSwallowCardCount
		otherInfo:'其他信息',//EwayLocale.deviceInfo.otherInfo
		lastUpdateTime:'上次升级时间',//EwayLocale.deviceInfo.lastUpdateTime
		maxUpdateVersion:'可升级版本',//EwayLocale.deviceInfo.maxUpdateVersion
		basicInfo:'基础信息',//EwayLocale.deviceInfo.basicInfo
		deviceBasicInfo:'设备基础信息',//EwayLocale.deviceInfo.deviceBasicInfo
		deviceDetailInfo:'设备信息明细',//EwayLocale.deviceInfo.deviceDetailInfo
		statusInfo:'状态信息',//EwayLocale.deviceInfo.statusInfo
		personInfo:'人员信息',//EwayLocale.deviceInfo.personInfo
		collapse:'收起',//EwayLocale.deviceInfo.collapse
		expand:'展开',//EwayLocale.deviceInfo.expand
		none:'无',//EwayLocale.deviceInfo.none
		noneUpdateVersion:'当前无可升级版本',//EwayLocale.deviceInfo.noneUpdateVersion
		appReleaseInfo:'可升级版本信息'//EwayLocale.deviceInfo.appReleaseInfo
	}
	
});
