Ext.apply(EwayLocale,{
	//**********************************************************/
	machine:{
		atmBrand : {
			title:'设备品牌',
			name: '品牌名称',
			country:'生产商国家或地区',
			hotline1:'生产商热线1',
			hotline2:'生产商热线2',
			address:'生产商地址',
			status:'生产商状态',
			comboxStatus:{
				provider:'设备供应',
				maintance:'设备服役'
			},
			devBrandInfo:'设备品牌信息'
		},
		atmCatalog:{
			title:'设备分类',//EwayLocale.machine.title
			name:'分类名称',//EwayLocale.machine.atmCatalog.name
			note:'备注',
			addTitle:'增加ATM分类信息',
			updateTitle:'更改ATM型号信息',
			number:'编号'//EwayLocale.machine.atmCatalog.number
		},
		atmGroup : {
			terminalId:'设备号',
			ip: '设备IP地址',
			orgName:'所属机构',
			devTypeName:'设备型号',
			devVendorName:'设备品牌',
			devCatalogName:'设备类型',
			devGroupName: '设备分组',
			status:'设备状态',
			comboxStatus:{
				dredge:'开通',//开通
				open:'开通',
				close:'停用'
			},
			awayFlag:'离行标志',
			comboxAwayFlag:{
				inBank:'在行自助服务区',
				outBank:'离行自助银行',
				clickBank:'单机离行自助服务点'
			},
			devServiceName:'设备维护商',
			cashboxLimit:'钞箱报警金额',
			installDate:'安装日期',
			address:'地址',
			gourpDev:'分组<-->设备',
			addTitle: '增加设备组信息',
			groupName:'组名',
			note:'备注',
			updateTitle:'更改设备组信息'
		},
		atmModule:{
			moduleName:'模块名称',
			note:'备注',
			atmModules:'ATM模块'
		},
		atmMove:{
			title:'移机管理',
			moveDev:'移机',
			moveDevRec:'移动设备并产生移机记录',
			moveRecordInfo:'移机记录信息',
			waitMove:'待移动的机器',
			terminalId:'设备号',
			address:'源地址',
			orgName:'源机构',
			targetAddress:'目标地址',
			targetOrganization:'目标机构',
			targetPerson:'目标机构负责人',
			responsibility:'负责人',
			destPerson:'源机构负责人',
			date:'日期',
			recoverDate:'恢复时间',
			notice:'备注',
			sAddress:'所属地址',
			sOrgName:'所属机构',
			to:'至'
		},
		atmRuntimeInfo:{
			exportName:'导出',
			exportDateRangeText:'开始时间不能大于结束时间',
			terminalId:'终端号',
			terminalIp:'终端IP',
			startDate:'开始时间',
			endDate:'结束时间',
			exportLast30: '导出最后30天汇总信息',
			terminalId:'编号',
			netIp:'网络地址',
			msgCollect:'客服信息采集'
		},
		atmType:{
			title:'设备型号',
			atmName:'ATM型号',
			name:'设备型号',
			devVendorName:'所属品牌',
			devCatalogName:'所属类型',
			devTerminalName:'所属型号',
			spec:'设备规格',
			weight:'设备重量',
			watt:'平均功率',
			no:'编号',
			cashtype:'非现金标志',
			iscash:'现金',
			nocash:'非现金',//非现金
			modules:'包含的设备模块'
		},
		device:{
			title:'设备信息管理',
			devDetailInfo:'设备模块详细信息',
		    idc:'读卡器',//读卡器模块(IDC)
			jpr:'日志打印机',// 日志打印机模块(JPR)
			cdm:'取款模块',// 取款模块(CDM)
			siu:'传感器',// 传感器模块(SIU)
			cim:'存款模块',// 存款模块(CIM)
			ttu:'文本终端',// 文本终端单元(TTU)
			rpr:'凭条打印机',// 凭条打印机模块(RPR)
			pin:'密码键盘',// 密码键盘模块(PIN)
			icc:'发卡器',
			fgp:'指纹仪',
			pbk:'存折打印机',
			
			CAMStatusInfo:'摄像头(CAM)状态信息',//EwayLocale.machine.device.CAMStatusInfo,
			CAMRoomStatus:'房间摄像头',//EwayLocale.machine.device.CAMRoomStatus,
			CAMPersonStatus:'客户摄像头',//EwayLocale.machine.device.CAMPersonStatus,
			CAMExitSlotStatus:'出钞口摄像头',//EwayLocale.machine.device.CAMExitSlotStatus,

			CAMInfo:'摄像头(CAM)属性信息',//EwayLocale.machine.device.CAMInfo,
			CAMMaxPictures:'最大拍照张数',//EwayLocale.machine.device.CAMMaxPictures,
			CAMMaxDataLength:'最大文字数',//EwayLocale.machine.device.MaxDataLength,
			
			BCRInfo:'二维码扫描仪(BCR)属性信息',//EwayLocale.machine.device.BCRInfo,
			BCRCanCompound:'是否为合成设备',//EwayLocale.machine.device.BCRCanCompound,
			BCRCanFilterSymbologies:'能否辨别制定的条码',//EwayLocale.machine.device.BCRCanFilterSymbologies,
			
			CDMInfo:'取款模块（CDM）属性信息',
			hasStack:'是否具有暂存器',
			hasShutter:'是否具有shutter门',
			canRetract:'是否具有回收能力',
			canDetectCashTaken:'是否探测钞币被拿走',
			canTestPhysicalUnits:'是否能测试物理单元',
			maxDispensBills:'获取单笔最大挖钞张数',
			logicalUnits:'逻辑钞箱个数',
			physicalUnits:'物理钞箱个数',
			currency:'支持的币种类别总个数',
			currencies:'支持的币种类别',
			exponents:'指数',

			CIMInfo:'存款模块 （CIM）属性信息',
			canEscrow:'是否具有暂存器X',
			shutterControlSupported:'是否支持控制shutter门',
			maxAcceptItems:'单笔最大验钞张数',
			canDetectCashInserted:'是否能探测钞票放入',
			canDetectCashTaken:'是否能探测钞票被取走',
			retractAreas:'回收位置',


			IDCInfo:'读卡器模块(IDC)属性信息',
			variant:'读卡器类型',
			canEjectCard:'是否具有退卡能力',
			trackJisiiRead:'是否具有TrackJisii读能力',
			track1Read:'是否具有读一磁道数据能力',
			track2Read:'是否具有读二磁道数据能力',
			track3Read:'是否具有读三磁道数据能力',
			canCapture:'是否具有吞卡能力',
			binCapacity:'最大吞卡张数',
			security:'是否具有安全支持',
			trackJisiiWrite:'是否具有TrackJisii写能力',
			track1Write:'是否具有写一磁道数据能力',
			track2Write:'是否具有写二磁道数据能力',
			track3Write:'是否具有写三磁道数据能力',


			JPRInfo:'日志打印机模块(JPR)属性信息',//EwayLocale.machine.device.JPRInfo
			canEject:'是否具有退纸能力',
			canCapture:'是否具有回收能力',
			canStack:'是否具有暂存能力',

			PINInfo:'密码键盘(PIN)属性信息',
			canEBC:'能否EBC',
			canCBC:'能否CBC',
			canMAC:'能否MAC',
			canRSA:'能否RSA',
			canVerifyVISA:'能否验证VISA',
			canVerifyDES:'能否验证DES',
			functionKeys:'功能键支持',
			canTripleEBC:'是否支持多重EBC',
			canTripleCBC:'是否支持多重CBC',
			canTripleMAC:'是否支持多重MAC',
			canTripleCFB:'是否支持多重CFB',
			canVerifyECB:'能否验证ECB',
			canDESOffset:'能否DeS偏移',

			RPRInfo:'凭条打印机(RPR)属性信息',
			canEject:'是否具有退纸能力',
			canCapture:'是否具有回收能力',
			canStack:'是否具有暂存能力',
			maxRetract:'最大回收张数',

			SIUInfo:'SIU能力属性信息',
			operatorSwitchSupported:'是否支持操作员开关',
			cabinetSupported:'是否支持后盖门打开传感能力',
			safeSupported:'是否支持安全门打开传感能力',
			indicatorSupported:'是否支持靠近传感能力',
			guidelightIdcSupported:'是否支持插卡指示灯能力',
			guidelightCdmSupported:'是否支持取款指示灯能力',
			guidelightReceiptSupported:'是否支持凭条打印指示灯能力',
			guidelightCimSupported:'是否支持存款指示灯能力',

			TTUInfo:'文本终端单元(TTU)属性信息',
			alphanumericKeysPresent:'是否支持字母数字键输入',
			numericKeysPresent:'是否支持数字键输入',
			displayLightPresent:'是否支持屏幕亮度调节',
			cursorSupported:'是否支持鼠标',
			resolutionX:'横轴分辨率',
			hexadecimalKeysPresent:'是否支持十六进制键输入',
			keyboardLockPresent:'是否支持键盘锁定',
			formsSupported:'是否支持表格',
			resolutionY:'纵轴分辨率',
			
			ISCInfo:'身份证扫描仪(ISC)属性信息',
			
			ICCInfo:'发卡器(ICC)属性信息',
			dispenseCard:'是否具有发卡读能力',
			
			FGPInfo:'指纹仪(FGP)属性信息',
			fgp_variant:'指纹仪类型',
			canCompare:'是否具有比较特值功能',
			
			PBKInfo:'存折打印机(PBK)属性信息',
			
			comStatus:'厂商状态信息',
			hwCode:'厂商故障码',
			CDMStatus:'取款模块(CDM)状态信息',
			cashUnits:'钞箱状态',
			safeDoor:'安全门状态',
			intermediateStacker:'暂存器状态',
			outBox:'取款钞箱',
			pcuId:'物理逻辑钞箱对应关系',
			cuId:'逻辑钞箱ID',
			cuCurrency:'逻辑钞箱币种',
			cuCurrentCount:'逻辑钞箱当前张数',
			cuInitialCount:'逻辑钞箱初始张数',
			cuRejectCount:'逻辑钞箱reject张数',
			cuNoteValue:'逻辑钞箱面值',
			cuBinStatus:'逻辑钞箱状态',
			puId:'物理钞箱ID',
			puPosName:'物理钞箱位置名称',
			puBinStatus:'物理钞箱状态',
			puCurrentCount:'物理钞箱当前张数',
			puInitialCount:'物理钞箱初始张数',
			puRejectCount:'物理钞箱Reject张数',
			cuBinType:'逻辑钞箱类型',

			CIMStatus:'存款模块(CIM)状态信息',
			baffle:'挡板状态',
			inOutPositionStatus:'传输状态',
			inBox:'存款钞箱',
			puCashInCount:'物理钞箱入钞张数',
			pcuId:'物理钞箱与逻辑钞箱对应关系',
			cuType:'逻辑钞箱类型',
			cuBinStatus:'逻辑钞箱状态',
			cuCurrentCount:'逻辑钞箱当前张数',
			cuCurrency:'逻辑钞箱币种',
			cuNoteValue:'逻辑钞箱面值',

			IDCStatus:'读卡器模块(IDC)状态信息',
			media:'媒体状态',
			retainBin:'回收盒状态',
			cards:'回收盒数量',

			JRPStatus:'日志打印机模块(JPR)状态信息',
			supplyLevel:'打印纸状态',
			ink:'墨水',
			toner:'色带',

			PINStatus:'密码键盘模块(PIN)状态信息',

			RPRStatus:'凭条打印机模块(RPR)状态信息',
			bin:'回收单元状态',

			SIUStatus:'SIU能力状态信息',
			vandalShield:'防护罩状态',
			operatorSwitch:'操作员按钮状态',
			ambientLight:'环境灯状态',
			cabinet:'箱门状态',
			safe:'安全门状态',
			idcGuidelight:'插卡导引灯状态',
			cdmGuidelight:'取钞引导指示灯状态',
			receiptGuidelight:'凭条导引灯状态',
			cimGuidelight:'CIM导引灯状态',

			TTUStatus:'文本终端单元(TTU)状态信息',
			
			ISCStatus:'身份证扫描仪(ISC)状态信息',
			
			ICCStatus:'发卡器(ICC)状态信息',
			
			FGPStatus:'指纹仪(FGP)状态信息',
			
			PBKStatus:'存折打印机(PBK)状态信息',

			devPerson:'设备人员信息',//EwayLocale.machine.device.devPerson
			devModuleMsg:'设备模块属性信息',
			devBasicMsg:'设备基本信息',
			devTailMsg:'设备详细信息',
			managePerson:'管机员',
			maintainPerson:'维护员',//EwayLocale.machine.device.maintainPerson
			name:'姓名',//EwayLocale.machine.device.name
			mobile:'手机',//EwayLocale.machine.device.mobile
			phone:'固定电话',//EwayLocale.machine.device.phone
			email:'邮件地址',//EwayLocale.machine.device.email
			deviceBasicInfo:'设备基本信息',
			lineLogo:'在行标志',
			alarmRateRMB:'钞箱报警金额(人民币)',
			operation:'经营方式',
			ipAddress:'IP地址',
			swallowCard:'吞卡张数',
			alarmRateHKD:'钞箱报警金额(港币)',
			adminPhone:'管理员(手机号)',
			maintainPhone: '维护员(手机号)',
			log:'钞箱标识',
			style: '钞箱类型',
			status: '钞箱状态',
			initailnumber: '初始张数',
			postnumber: '存款张数',
			currentnumber: '当前钞箱张数',
			facevalue: '钞箱面值',
			currency: '钞箱币种',
			systemHardwareInfo: '系统软硬件信息',
			moduleVersionInfo:'模块硬件版本信息（实时）',
			devModuleStatusInfo: '设备模块状态（实时）',
			devModuleAttributeInfo: '设备模块属性信息（实时）',

			remoteControl: '远程控制',
			collectJPR:'提取日志',
			remoteScreen:'远程抓屏',
			processCheck:'进程查看',
			remoteExplorer:'远程浏览',
			netWorkLink:'网络连接',
			remoteRestart:'远程重启',

			progressTip:'进度提示',
			updateProBar:'这是通过动态更新内容形成的进度条',
			currentProcess:'当前进度',


			restartApply: ' 重启应用',
			confirmRestartApply:'确定要重启应用？',
			nowRestartApply:'正在重启应用',
			restartApplySuc:'成功重启该设备应用',
			restartApplyFail:'重启应用失败！',


			restartDrive:'重启硬件驱动',
			confirmRestartDrive:'确定要重启硬件驱动？',
			nowRestartDrive:'正在重启硬件驱动',
			restartDriveSuc:'成功重启该设备硬件驱动',
			restartDriveFail:'重启硬件驱动失败！',

			restartOS:'重启操作系统',
			confirmRestartOS:'确定要重启操作系统？',
			nowRestartOS:'正在重启操作系统',
			restartOSSuc:'成功重启该设备操作系统',
			restartOSFail:'重启操作系统失败！',

			remoteShutdown:'远程关机',
			shutdownApply:'关闭应用',
			confirmShutdownApply:'确定要关闭应用？',
			nowShutdownApply:'正在关闭应用',
			shutdownApplySuc:'成功关闭该设备应用',
			shutdownApplyFail:'关闭应用失败！',

			shutdownDrive:'关闭硬件驱动',
			confirmShutdownDrive:'确定要关闭硬件驱动？',
			nowShutdownDrive:'正在关闭硬件驱动',
			shutdownDriveSuc:'成功关闭该设备硬件驱动',
			shutdownDriveFail:'关闭硬件驱动失败！',

			shutdownOS:'关闭操作系统',
			confirmShutdownOS:'确定要关闭操作系统？',
			nowShutdownOS:'正在关闭操作系统',
			shutdownOSSuc:'成功关闭该设备操作系统',
			shutdownOSFail:'关闭操作系统失败！',
			getSoftwareList:'获取软件安装列表',
			forceReset:'强制复位',
			openService:'开启服务',
			pauseService:'暂停服务',
			checkStatus:'状态检测',

			remoteBrowseDisk:'远程浏览',

			sysHardwareInfo:'系统硬件信息',
			diskMem:'硬盘大小',
			biosVersion:'Bios版本',
			biosVendor:'Bios厂商',
			biosReleaseDate:'Bios发布日期',
			memorySize:'内存总数',
			memoryUsed:'已使用内存',
			memoryFree:'空闲内存',
			memoryPercent:'内存使用率',
			cpuItemID:'cpu信息',
			cpuFrequency:'CPU频率(MHz)',
			cpuVendor:'CPU的厂商',
			cpuModel:'CPU的类别',
			cacheSize:'缓冲存储器数量',
			totalCores:'CPU核数',
			userUsePercent:'用户使用率',
			sysUsePercent:'系统使用率',
			idlePercent:'当前空闲率',
			combinedPercent:'总的使用率',
			diskItemID:'磁盘信息',
			diskName:'磁盘分区名称',
			diskFileSys:'磁盘文件系统',
			diskTotalSize:'磁盘总大小',
			diskFreeSize:'磁盘可用空间大小',
			sysSoftInfo:'系统软件信息',
			OSID:'操作系统ID',
			OSDescription:'OS描述',
			OSType:'OS类型',
			sysPatchLevel:'系统补丁级别',
			chkCashData:'验钞数据版本',
			OSVendor:'OS供应商',
			OSVendorName:'OS供应商名',
			sysVersion:'系统版本号',
			devAddress:'设备地址',
			basicInfo:'其它信息',
			virtual:'虚拟设备号',
			serial:'设备序列号',
			carrier:'运营商',
			moneyOrg:'加钞机构',
			costInterest:'资金成本利率',
			atmcSoft:'atmc软件',
			spType:'厂商sp类型',
			column:'日期信息',
			buyDate:'设备购买日期',
			installDate:'设备安装日期',
			startDate:'设备启用日期',
			stopDate:'设备停用日期',
			expireDate:'保修到期日期',
			daliyOpen:'每日开机时间',
			openTimeHour:'时',
			openTimeMinute:'分',
			openTimeSecond:'秒',
			daliyClose:'每日关机时间',
			lastPmDate:'上次巡检日期',
			expirePmDate:'巡检到期日期',
			costInfo:'费用信息',
			price:'入账成本(元)',
			depreciationLife:'折旧年限(年)',
			decoration:'装修费用',
			decorationCost:'装修摊销年限(年)',
			governanceRent:'物业租赁费(元/月)',
			governanceCost:'物业管理费用(元/月)',
			netCost:'通讯线路费用(元/月)',
			powerCost:'电费(元/月)',
			moneyCost:'加钞维护费用(元/次)',
			statusInfo:'状态信息',
			deviceAttention:'设备关注程序',
			stress:'重点',
			medium:'中等',
			ordinary:'一般',
			notCashSignal:'非现金标志',
			cash: '现金',
			notCash:'非现金',
			installStyle: '安装方式',
			crossWall: '穿墙',
			mainRoom: '大堂',
			netType: '网络类型',
			wired: '有线',
			wireless: '无线',
			wiredAndWireless: '有线无线',
			onBankSignal:'在行离行标志',
			inBank:'在行自助服务区',
			outBank:'离行自助银行',
			clickBank:'单机离行自助服务点',
			operation:'经营方式',
			operationSelf: '自营',
			cooperation: '合作',
			epiboly: '外包',
			managePerson:'管机员',
			maintainPerson:'维护员',
			to:'至',
			range: '范围1－－100年',
			roleDescription:'角色描述',
			roleName:'角色名称',



			devices:'设备',//EwayLocale.machine.device.devices
			configuration:'配置信息',
			spVersion:'SP版本',
			notSupport:'不支持',
			drive:'驱动',
			firmway: '固件',
			noDevice:'无设备',
			devTypeInfo: '设备型号信息',

			devInfo:'设备信息',
			unable:'不可以',
			able:'能',

			addDevInfo:'增加设备信息',//EwayLocale.machine.device.addDevInfo
			effectiveDate:'生效日期',//EwayLocale.machine.device.effectiveDate
			changeDevInfo:'更改设备信息',//EwayLocale.machine.device.changeDevInfo
			devManage:'设备管理',//EwayLocale.machine.device.devManage
			efficientDev:'已生效设备信息',//EwayLocale.machine.device.efficientDev
			unEfficientDev:'未生效设备信息',//EwayLocale.machine.device.unEfficientDev
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
			classify:'类型',//EwayLocale.machine.param.classify
			paramType:'参数类型',//EwayLocale.machine.param.paramType 参数类型
			modifyFlag:'是否可以修改',//EwayLocale.machine.param.modifyFlag 是否可以修改
			comboxClassify:{
				unableUpdate:'不可修改',
				ableUpdate:'可以修改'
			},
			description:'描述',//EwayLocale.machine.param.description
			systemCon:'系统配置',//EwayLocale.machine.param.systemCon
			updateSystemCon:'更改系统配置'//EwayLocale.machine.param.updateSystemCon
		},
		quittingNotice:{
			addCloseMsg:'增加报停信息',
			updateCloseMsg:'更改报停信息',
			dateRangeText:'恢复日期不能小于等于停止日期,请重新选择',
			click:'请点击查询，选择设备',
			stopTime:'停机时间',
			openTime:'恢复时间',
			currentStatus:'当前状态',
			closeType:'停机类型',
			responsibilityName:'停机负责人',
			stopReason:'停机原因',
			address:'所属地址',
			selectDev:'选择需要报停的设备',
			updateUnable:'不能对已执行完成的报停记录进行修改.',
			to:'至',
			stopType:'停机类型',
			comboxStopType:{
				recess:'放假',
				fit:'装修',
				power:'停电',
				devFailue:'设备故障未修复',
				other:'其他'
			},
			setTime:'设置时间',
			closeManage:'报停管理'
		},
		plan:{
			addPlan:'增加开机方案',//EwayLocale.machine.plan.addPlan
			name:'方案名称',//EwayLocale.machine.plan.name
			type:'方案类型',//EwayLocale.machine.plan.type
			startDate:'有效开始时间',//EwayLocale.machine.plan.startDate
			endDate:'有效结束时间',//EwayLocale.machine.plan.endDate
			terminalId:'编号',//EwayLocale.machine.plan.terminalId
			cashboxLimit:'钞箱报警金额(单位：张数)',//EwayLocale.machine.plan.cashboxLimit
			perToDev:'人员<-->设备',//EwayLocale.machine.plan.perToDev
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
			tochenkDervice:'请选择您要更改的设备',//EwayLocale.machine.serviceplan.tochenkDervice
			tochenckPeople:'请选择人员',//EwayLocale.machine.serviceplan.tochenckPeople
			tipAddError:'条添加失败,请刷新后查看',//EwayLocale.machine.serviceplan.tipAddError
			planIsHaved:'',
			chooseOne:'至少选择一个',
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
			lastOneGroup:'请在组内至少选中一项',//EwayLocale.machine.serviceplan.lastOneGroup
			lanDetailWeek:'星期方案详细列表',//EwayLocale.machine.serviceplan.lanDetailWeek
			planDetailDay:'日期方案详细列表',//EwayLocale.machine.serviceplan.planDetailDay
			selectPlan:'选择开机方案',//EwayLocale.machine.serviceplan.selectPlan
			weekSelect:'通知方式'//EwayLocale.machine.serviceplan.weekSelect
		}
	},
	//**********************************************************/
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
		title:'应用日志',//EwayLocale.atmLog.appLogDownload
		lastBackupTime:'最后一次备份时间',//EwayLocale.atmLog.lastBackupTime
		noBegin:'未开始',//EwayLocale.atmLog.noBegin
		noLog:'无日志',//EwayLocale.atmLog.noLog
		connectFail:'连接失败',//EwayLocale.atmLog.connectFail
		fileSize:'文件大小',//EwayLocale.atmLog.fileSize
		searchIllegal:'查询项中存在不合法的输入,不能导出.'//EwayLocale.atmLog.searchIllegal
	},
	//**********************************************************/
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
