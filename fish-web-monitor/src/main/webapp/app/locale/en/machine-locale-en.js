Ext.apply(EwayLocale,{
	
	machine:{
		atmBrand : {//EwayLocale.machine.atmBrand
			title:'Device Brand',//EwayLocale.machine.title
			name: 'Name', //EwayLocale.machine.name
			country:'Country',//EwayLocale.machine.country
			hotline1:'Hotline',//EwayLocale.machine.hotline1
			hotline2:'Hotline2',//EwayLocale.machine.hotline2
			address:'Address',//EwayLocale.machine.address
			status:'Status',//EwayLocale.machine.status
			comboxStatus:{
				provider:'Supplier',//EwayLocale.comboxStatus.provider
				maintance:'Service Provider'//EwayLocale.comboxStatus.maintance
			}
		},
		atmCatalog:{
			title:'Device Catalog',//EwayLocale.atmCatalog.title
			name:'Name',//EwayLocale.atmCatalog.name
			note:'Description',//EwayLocale.atmCatalog.note
			addTitle:'Add catalog',//EwayLocale.atmCatalog.addTitle
			updateTitle:'Update Catalog', //EwayLocale.atmCatalog.updateTitle
			number:'Code'//EwayLocale.atmCatalog.number
		},
		atmGroup : {//EwayLocale.atmCatalog.atmGroup
			terminalId:'Terminal ID', //EwayLocale.atmCatalog.terminalId
			ip: 'IP',//EwayLocale.atmCatalog.ip
			orgName:'Bank',//EwayLocale.atmCatalog.orgName
			devTypeName:'Device Type',//EwayLocale.atmCatalog.devTypeName
			devVendorName:'Device Brand',//EwayLocale.atmCatalog.devVendorName
			devCatalogName:'Device Catalog',//EwayLocale.atmCatalog.devCatalogName
			devGroupName: 'Device Group',//EwayLocale.atmCatalog.devGroupName
			status:'Device Status',//EwayLocale.atmCatalog.status
			comboxStatus:{
				dredge:'Dredge',//EwayLocale.comboxStatus.dredge
				open:'Open',//EwayLocale.comboxStatus.open
				close:'Close'//EwayLocale.comboxStatus.close
			},
			awayFlag:'AwayFlag',//EwayLocale.comboxStatus.awayFlag
			comboxAwayFlag:{
				inBank:'Inside bank',//EwayLocale.comboxAwayFlag.inBank
				outBank:'Outside bank',//EwayLocale.comboxAwayFlag.outBank
				clickBank:'Alone out-bank self-service'//EwayLocale.comboxAwayFlag.clickBank
			},
			devServiceName:'Maintenance Provider',//EwayLocale.comboxAwayFlag.devServiceName
			cashboxLimit:'Cashbox Alarm Amount',//EwayLocale.comboxAwayFlag.cashboxLimit
			installDate:'Installation Time',//EwayLocale.comboxAwayFlag.installDate
			address:'Address',//EwayLocale.comboxAwayFlag.address
			gourpDev:'Group<-->Device',//EwayLocale.comboxAwayFlag.gourpDev
			addTitle: 'Add Group Info',//EwayLocale.comboxAwayFlag.addTitle
			groupName:'Group Name',//EwayLocale.comboxAwayFlag.groupName
			note:'Description',//EwayLocale.comboxAwayFlag.note
			updateTitle:'Update Group Info'//EwayLocale.comboxAwayFlag.updateTitle
		},
		atmModule:{
			moduleName:'Module Name',//EwayLocale.atmModule.moduleName
			note:'Description',//EwayLocale.atmModule.note
			atmModules:'ATM module'	//EwayLocale.atmModule.atmModules
		},
		atmMove:{
			title:'Move Manager',//EwayLocale.atmMove.title
			moveDev:'Move machine',//EwayLocale.atmMove.moveDev
			moveDevRec:'Move machine and generate the move record',//EwayLocale.atmMove.moveDevRec
			moveRecordInfo:'Move machine record',//EwayLocale.atmMove.moveRecordInfo
			waitMove:'Wait for move machine',//EwayLocale.atmMove.waitMove
			terminalId:'Terminal Id',//EwayLocale.atmMove.terminalId
			address:'Source address',//EwayLocale.atmMove.address
			orgName:'Source org',//EwayLocale.atmMove.orgName
			targetAddress:'Target address',//EwayLocale.atmMove.targetAddress
			targetOrganization:'Target organization',//EwayLocale.atmMove.targetOrganization
			targetPerson:'目标机构负责人',//EwayLocale.atmMove.targetPerson
			responsibility:'负责人',//EwayLocale.atmMove.responsibility
			destPerson:'源机构负责人',//EwayLocale.atmMove.destPerson
			date:'date',//EwayLocale.atmMove.date
			recoverDate:'Recover date',//EwayLocale.atmMove.recoverDate
			notice:'remark',//EwayLocale.atmMove.notice
			sAddress:'Address',//EwayLocale.atmMove.sAddress
			sOrgName:'Organization',//EwayLocale.atmMove.sOrgName
			to:'to'//EwayLocale.atmMove.to
		},
		atmRuntimeInfo:{
			exportName:'Export', //EwayLocale.atmRuntimeInfo.exportName
			exportDateRangeText:'Start time can not be later than end time',//EwayLocale.atmRuntimeInfo.exportDateRangeText
			terminalId:'Terminal ID',//EwayLocale.atmRuntimeInfo.terminalId
			terminalIp:'IP',//EwayLocale.atmRuntimeInfo.terminalIp
			startDate:'Begin time',//EwayLocale.atmRuntimeInfo.startDate
			endDate:'End time',//EwayLocale.atmRuntimeInfo.endDate
			exportLast30: 'Export last 30 days info',//EwayLocale.atmRuntimeInfo.exportLast30
			terminalId:'terminalId',//EwayLocale.atmRuntimeInfo.terminalId
			netIp:'Ip',//EwayLocale.atmRuntimeInfo.netIp
			msgCollect:'Collect info of customservice'//EwayLocale.atmRuntimeInfo.msgCollect
		},
		atmType:{
			title:'Device Type',//EwayLocale.atmType.title
			atmName:'Device Type',//EwayLocale.atmType.atmName
			name:'Name',//EwayLocale.atmType.name
			devVendorName:'Brand', //EwayLocale.atmType.devVendorName
			devCatalogName:'Catalog',//EwayLocale.atmType.devCatalogName
			devTerminalName:'Type',//EwayLocale.atmType.devTerminalName
			no:'Code',//EwayLocale.atmType.no
			cashtype:'Cash Flag',//EwayLocale.atmType.cashtype
			iscash:'Cash',//EwayLocale.atmType.iscash
			nocash:'Not Cash',//EwayLocale.atmType.nocash
			modules:'Module contains'//EwayLocale.atmType.modules
		},
		device:{
			title:'Device Info',//EwayLocale.device.title
			devDetailInfo:'Status of Modules',//EwayLocale.device.devDetailInfo
		    idc:'Card Reader',//EwayLocale.device.idc
			jpr:'Journal Printer',//EwayLocale.device.jpr
			cdm:'Dispenser',//EwayLocale.device.cdm
			siu:'Sensor',//EwayLocale.device.siu
			cim:'Deposit',//EwayLocale.device.cim
			ttu:'Text',//EwayLocale.device.ttu
			rpr:'Receipt Printer',//EwayLocale.device.rpr
			pin:'PIN',//EwayLocale.device.pin
			icc:'Card Dispenser',//EwayLocale.device.icc
			fgp:'Finger Printer',//EwayLocale.device.fgp
			pbk:'Passbook Printer',//EwayLocale.device.pbk
			
			CAMStatusInfo:'Camera status',//EwayLocale.device.CAMStatusInfo
			CAMRoomStatus:'Room camera',//EwayLocale.device.CAMRoomStatus
			CAMPersonStatus:'Customer camera',//EwayLocale.device.CAMPersonStatus
			CAMExitSlotStatus:'Exit slot camera',//EwayLocale.device.CAMExitSlotStatus

			CAMInfo:'Camera property',//EwayLocale.device.CAMInfo
			CAMMaxPictures:'Max pictures',//EwayLocale.device.CAMMaxPictures
			CAMMaxDataLength:'Max data length',//EwayLocale.device.CAMMaxDataLength
			
			BCRInfo:'BarCode Reader property',//EwayLocale.device.BCRInfo
			BCRCanCompound:'Can compound',//EwayLocale.device.BCRCanCompound
			BCRCanFilterSymbologies:'Can filter symbologies',//EwayLocale.device.BCRCanFilterSymbologies
			
			
			
			CDMInfo:'Dispenser property',//EwayLocale.device.CDMInfo
			hasStack:'Stacker Exists',//EwayLocale.device.hasStack
			hasShutter:'Shutter door exist',//EwayLocale.device.hasShutter
			canRetract:'Ability of recovery', //EwayLocale.device.canRetract
			canDetectCashTaken:'Check if cash taked',//EwayLocale.device.canDetectCashTaken
			canTestPhysicalUnits:'Test physical unit',//EwayLocale.device.canTestPhysicalUnits
			maxDispensBills:'Get max count of gug cash in each trans',//EwayLocale.device.maxDispensBills
			logicalUnits:'Count of logic cashbox',//EwayLocale.device.logicalUnits
			physicalUnits:'Count of physical cashbox',//EwayLocale.device.physicalUnits
			currency:'Currency supported count',//EwayLocale.device.currency
			currencies:'Currency category supported ',//EwayLocale.device.currencies
			exponents:'index',//EwayLocale.device.exponents
			
			CIMInfo:'Deposit Module (CIM)property',//EwayLocale.device.CIMInfo
			canEscrow:'Stacker Exists',//EwayLocale.device.canEscrow

			shutterControlSupported:'Support shutter door',//EwayLocale.device.shutterControlSupported
			maxAcceptItems:'Max count of inspect cash in each trans',//EwayLocale.device.maxAcceptItems
			canDetectCashInserted:'Check if cash bringed',//EwayLocale.device.canDetectCashInserted
			canDetectCashTaken:'Check if cash taked',//EwayLocale.device.canDetectCashTaken
			retractAreas:'Recovery position',//EwayLocale.device.retractAreas


			IDCInfo:'Card Reader(IDC) property',//EwayLocale.device.IDCInfo
			variant:'Card Reader type',//EwayLocale.device.variant
			canEjectCard:'Ability of quit card',//EwayLocale.device.canEjectCard
			trackJisiiRead:'Ability of read TrackJisii',//EwayLocale.device.trackJisiiRead
			track1Read:'Ability of read first track',//EwayLocale.device.track1Read
			track2Read:'Ability of read second track',//EwayLocale.device.track2Read
			track3Read:'Ability of read third track',//EwayLocale.device.track3Read
			canCapture:'Ability of retain card',//EwayLocale.device.canCapture
			binCapacity:'Max count of retain card',//EwayLocale.device.binCapacity
			security:'Safety support',//EwayLocale.device.security
			trackJisiiWrite:'Ability of write TrackJisii',//EwayLocale.device.trackJisiiWrite
			track1Write:'Ability of write first track',//EwayLocale.device.track1Write
			track2Write:'Ability of write first track',//EwayLocale.device.track2Write
			track3Write:'Ability of write first track',//EwayLocale.device.track3Write
			

			JPRInfo:'Journal Printer(JPR) property',//EwayLocale.device.JPRInfo
			canEject:'Ability of quit paper',//EwayLocale.device.canEject
			canCapture:'Ability of recovery',//EwayLocale.device.canCapture
			canStack:'Ability of deposit',//EwayLocale.device.canStack
			
			ISCInfo:'ID Scanner(ISC) property',//EwayLocale.device.ISCInfo
			
			ICCInfo:'Card dispenser(ICC) property',//EwayLocale.device.ICCInfo
			dispenseCard:'Ability of read',//EwayLocale.device.dispenseCard
			
			FGPInfo:'Finger Printer(FGP) property',//EwayLocale.device.FGPInfo
			fgp_variant:'Finger type',//EwayLocale.device.fgp_variant
			canCompare:'Ability of compare',//EwayLocale.device.canCompare
			
			PBKInfo:'Passbook printer(PBK) property',//EwayLocale.device.PBKInfo

			PINInfo:'PIN property',//EwayLocale.device.PINInfo
			canEBC:'EBC',//EwayLocale.device.canEBC
			canCBC:'CBC',//EwayLocale.device.canCBC
			canMAC:'MAC',//EwayLocale.device.canMAC
			canRSA:'RSA',//EwayLocale.device.canRSA
			canVerifyVISA:'Inspect VISA',//EwayLocale.device.canVerifyVISA
			canVerifyDES:'Inspect DES',//EwayLocale.device.canVerifyDES
			functionKeys:'Function support',//EwayLocale.device.functionKeys
			canTripleEBC:'Multi EBC support',//EwayLocale.device.canTripleEBC
			canTripleCBC:'Multi CBC support',//EwayLocale.device.canTripleCBC
			canTripleMAC:'Multi MAC support',//EwayLocale.device.canTripleMAC
			canTripleCFB:'Multi CFB support',//EwayLocale.device.canTripleCFB
			canVerifyECB:'Inspect ECB',//EwayLocale.device.canVerifyECB
			canDESOffset:'Des shifting',//EwayLocale.device.canDESOffset

			RPRInfo:'Receipt Printer(RPR) property',//EwayLocale.device.RPRInfo
			canEject:'Ability of quit paper',//EwayLocale.device.canEject
			canCapture:'Ability of recovery',//EwayLocale.device.canCapture

			maxRetract:'Max count of recovery paper',//EwayLocale.device.maxRetract

			SIUInfo:'Sensors(SIU) property',//EwayLocale.device.SIUInfo
			operatorSwitchSupported:'Operator swicth support',//EwayLocale.device.operatorSwitchSupported
			cabinetSupported:'Ability of induce  behindDoor open support',//EwayLocale.device.cabinetSupported
			safeSupported:'Ability of induce safeDoor open support',//EwayLocale.device.safeSupported
			indicatorSupported:'Ability of induce closeto support',//EwayLocale.device.indicatorSupported
			guidelightIdcSupported:'Ability of card insert light support',//EwayLocale.device.guidelightIdcSupported
			guidelightCdmSupported:'Ability of draw light  support',//EwayLocale.device.guidelightCdmSupported
			guidelightReceiptSupported:'Ability of voucher print light support',//EwayLocale.device.guidelightReceiptSupported
			guidelightCimSupported:'Ability of deposit light support',//EwayLocale.device.guidelightCimSupported


			TTUInfo:'TTU property',//EwayLocale.device.TTUInfo
			alphanumericKeysPresent:'Ability of character/number input support',//EwayLocale.device.alphanumericKeysPresent
			numericKeysPresent:'Ability of number input support',//EwayLocale.device.numericKeysPresent
			displayLightPresent:'Ability of number adjust screen-brightness support',//EwayLocale.device.displayLightPresent
			cursorSupported:'Mouse support',//EwayLocale.device.cursorSupported
			resolutionX:'Resolution of cross axle',//EwayLocale.device.resolutionX
			hexadecimalKeysPresent:'Hexadecimal input support',//EwayLocale.device.hexadecimalKeysPresent
			keyboardLockPresent:'Lock keyboard support',//EwayLocale.device.keyboardLockPresent
			formsSupported:'Table support',//EwayLocale.device.formsSupported
			resolutionY:'Resolution of vertical axle',//EwayLocale.device.resolutionY

			comStatus:'Manufacturer',//EwayLocale.device.comStatus
			hwCode:'Fault code',//EwayLocale.device.hwCode
			CDMStatus:'Withdrawal module(CDM) status',//EwayLocale.device.CDMStatus
			cashUnits:'Cashbox',//EwayLocale.device.cashUnits
			safeDoor:'SafeDoor',//EwayLocale.device.safeDoor
			intermediateStacker:'Stacker Status',//EwayLocale.device.intermediateStacker
			outBox:'Cashbox draw',//EwayLocale.device.outBox
			pcuId:'Relationship between physical and logic cashbox',//EwayLocale.device.pcuId
			cuId:'Logic cashboxID',//EwayLocale.device.cuId
			cuCurrency:'Logic cashbox currency',//EwayLocale.device.cuCurrency
			cuCurrentCount:'Count of logic cashbox currently',//EwayLocale.device.cuCurrentCount
			cuInitialCount:'Count of logic cashbox initially',//EwayLocale.device.cuInitialCount
			cuRejectCount:'Count of logic cashbox rejected',//EwayLocale.device.cuRejectCount
			cuNoteValue:'Denomination of logic cashbox',//EwayLocale.device.cuNoteValue
			cuBinStatus:'Logic cashbox status',//EwayLocale.device.cuBinStatus
			puId:'Physical cashbox ID',//EwayLocale.device.puId
			puPosName:'Name of physical cashbox',//EwayLocale.device.puPosName
			puBinStatus:'Physical cashbox status',//EwayLocale.device.puBinStatus
			puCurrentCount:'Count of physical cashbox currently',//EwayLocale.device.puCurrentCount
			puInitialCount:'Count of physical cashbox initially',//EwayLocale.device.puInitialCount
			puRejectCount:'Count of physical cashbox rejected',//EwayLocale.device.puRejectCount
			cuBinType:'Logic cashbox status',//EwayLocale.device.cuBinType

			CIMStatus:'Deposit(CIM) status',//EwayLocale.device.CIMStatus
			baffle:'Baffle',//EwayLocale.device.baffle
			inOutPositionStatus:'Transfer status',//EwayLocale.device.inOutPositionStatus
			inBox:'Deposit cashbox',//EwayLocale.device.inBox
			puCashInCount:'Count of physical cashbox in',//EwayLocale.device.puCashInCount
			pcuId:'Relationship between physical and logic cashbox',//EwayLocale.device.pcuId
			cuType:'Logic cashbox type',//EwayLocale.device.cuType
			cuBinStatus:'Logic cashbox status',//EwayLocale.device.cuBinStatus
			cuCurrentCount:'Count of logic cashbox',//EwayLocale.device.cuCurrentCount
			cuCurrency:'Currency of logic cashbox',//EwayLocale.device.cuCurrency
			cuNoteValue:'Denomination of logic cashbox',//EwayLocale.device.cuNoteValue

			IDCStatus:'Card Reader (IDC) status',//EwayLocale.device.IDCStatus
			media:'Media',//EwayLocale.device.media
			retainBin:'Recovery box status',//EwayLocale.device.retainBin
			cards:'Recovery box count',//EwayLocale.device.cards

			JRPStatus:'Journal Printer(JPR) status',//EwayLocale.device.JRPStatus
			supplyLevel:'Journal Printer status',//EwayLocale.device.supplyLevel
			ink:'Ink',//EwayLocale.device.ink
			toner:'Ribbon',//EwayLocale.device.toner

			PINStatus:'PIN status',//EwayLocale.device.PINStatus

			RPRStatus:'Receipt Printer(RPR)status',//EwayLocale.device.RPRStatus
			bin:'Recovery unit status',//EwayLocale.device.bin

			SIUStatus:'SIU status',//EwayLocale.device.SIUStatus
			vandalShield:'Shield',//EwayLocale.device.vandalShield
			operatorSwitch:'Operate butoon status',//EwayLocale.device.operatorSwitch
			ambientLight:'Environment light',//EwayLocale.device.ambientLight
			cabinet:'Box door',//EwayLocale.device.cabinet
			safe:'Safe door',//EwayLocale.device.safe
			idcGuidelight:'Guide card-insert light',//EwayLocale.device.idcGuidelight
			cdmGuidelight:'Guide draw light',//EwayLocale.device.cdmGuidelight
			receiptGuidelight:'Guide voucher light',//EwayLocale.device.receiptGuidelight
			cimGuidelight:'Guide CIM light',//EwayLocale.device.cimGuidelight

			TTUStatus:'Text Terminal(TTU) status',//EwayLocale.device.TTUStatus
			
			ISCStatus:'ID Scanner(ISC) status',//EwayLocale.device.ISCStatus
			
			ICCStatus:'Card dispenser(ICC) status',//EwayLocale.device.ICCStatus
			
			FGPStatus:'Finger Printer(FGP) status',//EwayLocale.device.FGPStatus
			
			PBKStatus:'Passbook printer(PBK) status',//EwayLocale.device.PBKStatus
			
			devPerson:'Device person',//EwayLocale.device.devPerson
			devModuleMsg:'Device module property',//EwayLocale.device.devModuleMsg
			devBasicMsg:'Basic info',//EwayLocale.device.devBasicMsg
			devTailMsg:'Detail',//EwayLocale.device.devTailMsg
			managePerson:'Manager',//EwayLocale.device.managePerson
			maintainPerson:'Maintainer',//EwayLocale.device.maintainPerson
			name:'Name',//EwayLocale.device.name
			mobile:'Mobile',//EwayLocale.device.mobile
			phone:'Phone',//EwayLocale.device.phone
			email:'Email',//EwayLocale.device.email
			deviceBasicInfo:'Additional info',//EwayLocale.device.deviceBasicInfo
			lineLogo:'AwayFlag',//EwayLocale.device.lineLogo
			alarmRateRMB:'Alarm cashbox(RMB)',//EwayLocale.device.alarmRateRMB
			operation:'Service mode',//EwayLocale.device.operation
			ipAddress:'IP address',//EwayLocale.device.ipAddress
			swallowCard:'Count of retain card',//EwayLocale.device.swallowCard
			alarmRateHKD:'Alarm cashbox(HKD)',//EwayLocale.device.alarmRateHKD
			adminPhone:'Manager(mobile)',//EwayLocale.device.adminPhone
			maintainPhone: 'Maintainer(mobile)',//EwayLocale.device.maintainPhone
			log:'Flag',//EwayLocale.device.log
			style: 'type',//EwayLocale.device.style
			status: 'Status',//EwayLocale.device.status
			initailnumber: 'Init count',//EwayLocale.device.initailnumber
			postnumber: 'Deposit count',//EwayLocale.device.postnumber
			currentnumber: 'Current count',//EwayLocale.device.currentnumber
			facevalue: 'Denomination',//EwayLocale.device.facevalue
			currency: 'Currency',//EwayLocale.device.currency
			systemHardwareInfo: 'Hardware & Software',//EwayLocale.device.systemHardwareInfo
			moduleVersionInfo:'Versions of Module(Real-time)',//EwayLocale.device.moduleVersionInfo
			devModuleStatusInfo: 'Module Status(Real-time)',//EwayLocale.device.devModuleStatusInfo
			devModuleAttributeInfo: 'Module Property(Real-time)',//EwayLocale.device.devModuleAttributeInfo

			remoteControl: 'Control',//EwayLocale.device.remoteControl
			collectJPR:'Logs getting',//EwayLocale.device.collectJPR
			remoteScreen:'Screenshot',//EwayLocale.device.remoteScreen
			processCheck:'View process',//EwayLocale.device.processCheck
			remoteExplorer:'Browse',//EwayLocale.device.remoteExplorer
			netWorkLink:'Connect',//EwayLocale.device.netWorkLink
			remoteRestart:'Reboot',//EwayLocale.device.remoteRestart

			progressTip:'Progress tip',//EwayLocale.device.progressTip
			updateProBar:'This is generate with dynamic update',//EwayLocale.device.updateProBar
			currentProcess:'Progress currently',//EwayLocale.device.currentProcess


			restartApply: 'Restar app',//EwayLocale.device.restartApply
			confirmRestartApply:'Restar app?',//EwayLocale.device.confirmRestartApply
			nowRestartApply:'Restarting',//EwayLocale.device.nowRestartApply
			restartApplySuc:'Restarting app successful!',//EwayLocale.device.restartApplySuc
			restartApplyFail:'Restarting app failed!',//EwayLocale.device.restartApplyFail


			restartDrive:'Restar drivers',//EwayLocale.device.restartDrive
			confirmRestartDrive:'Restar drivers?',//EwayLocale.device.confirmRestartDrive
			nowRestartDrive:'Restarting',//EwayLocale.device.nowRestartDrive
			restartDriveSuc:'Restarting drivers successfully!',//EwayLocale.device.restartDriveSuc
			restartDriveFail:'Restarting drivers failed!',//EwayLocale.device.restartDriveFail

			restartOS:'Restar os',//EwayLocale.device.restartOS
			confirmRestartOS:'Restar os?',//EwayLocale.device.confirmRestartOS
			nowRestartOS:'Restarting',//EwayLocale.device.nowRestartOS
			restartOSSuc:'Restarting os successfully!',//EwayLocale.device.restartOSSuc
			restartOSFail:'Restarting os failed!',//EwayLocale.device.restartOSFail

			remoteShutdown:'Shutdown',//EwayLocale.device.remoteShutdown
			shutdownApply:'Shutdown app',//EwayLocale.device.shutdownApply
			confirmShutdownApply:'Shutdown app?',//EwayLocale.device.confirmShutdownApply
			nowShutdownApply:'Shutting down',//EwayLocale.device.nowShutdownApply
			shutdownApplySuc:'Shutdown app successfully!',//EwayLocale.device.shutdownApplySuc
			shutdownApplyFail:'Shutdown app failed!',//EwayLocale.device.shutdownApplyFail

			shutdownDrive:'Shutdown drivers',//EwayLocale.device.shutdownDrive
			confirmShutdownDrive:'Shutdown drivers?',//EwayLocale.device.confirmShutdownDrive
			nowShutdownDrive:'Shutting down',//EwayLocale.device.nowShutdownDrive
			shutdownDriveSuc:'Shutdown drivers successfully!',//EwayLocale.device.shutdownDriveSuc
			shutdownDriveFail:'Shutdown drivers failed!',//EwayLocale.device.shutdownDriveFail

			shutdownOS:'Shutdown os',//EwayLocale.device.shutdownOS
			confirmShutdownOS:'Shutdown os?',//EwayLocale.device.confirmShutdownOS
			nowShutdownOS:'Shutting down',//EwayLocale.device.nowShutdownOS
			shutdownOSSuc:'Shutdown os successfully!',//EwayLocale.device.shutdownOSSuc
			shutdownOSFail:'Shutdown os failed!',//EwayLocale.device.shutdownOSFail
			getSoftwareList:'Get software installed info',//EwayLocale.device.getSoftwareList
			forceReset:'Reset force',//EwayLocale.device.forceReset
			openService:'Open service',//EwayLocale.device.openService
			pauseService:'Pause service',//EwayLocale.device.pauseService
			checkStatus:'Test status',//EwayLocale.device.checkStatus

			remoteBrowseDisk:'Browse',//EwayLocale.device.remoteBrowseDisk

			sysHardwareInfo:'Hardware info',//EwayLocale.device.sysHardwareInfo
			diskMem:'Disk size',//EwayLocale.device.diskMem
			biosVersion:'Bios version',//EwayLocale.device.biosVersion
			biosVendor:'Bios producer',//EwayLocale.device.biosVendor
			biosReleaseDate:'Bios date',//EwayLocale.device.biosReleaseDate
			memorySize:'Total memory',//EwayLocale.device.memorySize
			memoryUsed:'Used memory',//EwayLocale.device.memoryUsed
			memoryFree:'Free memory',//EwayLocale.device.memoryFree
			memoryPercent:'Memory used(%)',//EwayLocale.device.memoryPercent
			cpuItemID:'CPU info',//EwayLocale.device.cpuItemID
			cpuFrequency:'CPU(MHz)',//EwayLocale.device.cpuFrequency
			cpuVendor:'Manufacturer',//EwayLocale.device.cpuVendor
			cpuModel:'CPU type',//EwayLocale.device.cpuModel
			cacheSize:'Count of cache storage',//EwayLocale.device.cacheSize
			totalCores:'CPU cores',//EwayLocale.device.totalCores
			userUsePercent:'User used',//EwayLocale.device.userUsePercent
			sysUsePercent:'System used',//EwayLocale.device.sysUsePercent
			idlePercent:'Idle(%s)',//EwayLocale.device.idlePercent
			combinedPercent:'Used(%)',//EwayLocale.device.combinedPercent
			diskItemID:'Disk info',//EwayLocale.device.diskItemID
			diskName:'Name',//EwayLocale.device.diskName
			diskFileSys:'File system',//EwayLocale.device.diskFileSys
			diskTotalSize:'Total Size',//EwayLocale.device.diskTotalSize
			diskFreeSize:'Free Space',//EwayLocale.device.diskFreeSize
			sysSoftInfo:'Software info',//EwayLocale.device.sysSoftInfo
			OSID:'OS ID',//EwayLocale.device.OSID
			OSDescription:'OS Description',//EwayLocale.device.OSDescription
			OSType:'OS type',//EwayLocale.device.OSType
			sysPatchLevel:'OS patch level',//EwayLocale.device.sysPatchLevel
			chkCashData:'BV-version',//EwayLocale.device.chkCashData
			OSVendor:'OS producer',//EwayLocale.device.OSVendor
			OSVendorName:'OS supplier',//EwayLocale.device.OSVendorName
			sysVersion:'OS version',//EwayLocale.device.sysVersion
			devAddress:'Address',//EwayLocale.device.devAddress
			basicInfo:'Additional Info',//EwayLocale.device.basicInfo
			virtual:'Virtual Teller No.',//EwayLocale.device.virtual
			serial:'Serial Number',//EwayLocale.device.serial
			carrier:'Carrieroperator',//EwayLocale.device.carrier
			moneyOrg:'Cash org',//EwayLocale.device.moneyOrg
			costInterest:'Rate of fund cost',//EwayLocale.device.costInterest
			atmcSoft:'atmc software',//EwayLocale.device.atmcSoft
			spType:'sp type',//EwayLocale.device.spType
			column:'Date',//EwayLocale.device.column
			buyDate:'Buy date',//EwayLocale.device.buyDate
			installDate:'Installation Time',//EwayLocale.device.installDate
			startDate:'Start date',//EwayLocale.device.startDate
			stopDate:'Stop date',//EwayLocale.device.stopDate
			expireDate:'Guaranteed date',//EwayLocale.device.expireDate
			daliyOpen:'Start time everyday',//EwayLocale.device.daliyOpen
			openTimeHour:'Hour',//EwayLocale.device.openTimeHour
			openTimeMinute:'Minute',//EwayLocale.device.openTimeMinute
			openTimeSecond:'Second',//EwayLocale.device.openTimeSecond
			daliyClose:'Close time everyday',//EwayLocale.device.daliyClose
			lastPmDate:'Last check date',//EwayLocale.device.lastPmDate
			expirePmDate:'Next check date',//EwayLocale.device.expirePmDate
			costInfo:'Expenses',//EwayLocale.device.costInfo
			price:'Cost',//EwayLocale.device.price

			
			notCashSignal:'Cash Flag',//EwayLocale.device.notCashSignal
			cash: 'Cash',//EwayLocale.device.cash
			notCash:'Not Cash',//EwayLocale.device.notCash
			installStyle: 'Installation Way',//EwayLocale.device.installStyle
			crossWall: 'Through the wall',//EwayLocale.device.crossWall
			mainRoom: 'Lobby',//EwayLocale.device.mainRoom
			netType: 'Network Type',//EwayLocale.device.netType
			wired: 'Wired',//EwayLocale.device.wired
			wireless: 'Wireless',//EwayLocale.device.wireless
			wiredAndWireless: 'Wired & Wireless',//EwayLocale.device.wiredAndWireless
			onBankSignal:'Location',//EwayLocale.device.onBankSignal
			inBank:'Bank',//EwayLocale.device.inBank
			outBank:'Non-Bank',//EwayLocale.device.outBank
			clickBank:'Half Non-Bank',//EwayLocale.device.clickBank
			managePerson:'Manager',//EwayLocale.device.managePerson
			maintainPerson:'Maintenance Engineers',//EwayLocale.device.maintainPerson
			to:'至',//EwayLocale.device.to
			range: 'Range 1-100 years',//EwayLocale.device.range
			roleDescription:'Description',//EwayLocale.device.roleDescription
			roleName:'Name',//EwayLocale.device.roleName



			devices:'Device',//EwayLocale.device.devices
			configuration:'Setting',//EwayLocale.device.configuration
			spVersion:'SP',//EwayLocale.device.spVersion
			notSupport:'Unsupport',//EwayLocale.device.notSupport
			drive:'Driver',//EwayLocale.device.drive
			firmway: 'Firmware',//EwayLocale.device.firmway
			noDevice:'NoDevice',//EwayLocale.device.noDevice
			devTypeInfo: 'Device Type',//EwayLocale.device.devTypeInfo

			devInfo:'Device info',//EwayLocale.device.devInfo
			unable:'Can not',//EwayLocale.device.unable
			able:'Can',//EwayLocale.device.able

			addDevInfo:'Additional equipment information',//EwayLocale.device.addDevInfo
			effectiveDate:'effective date',//EwayLocale.device.effectiveDate
			changeDevInfo:'Update the Device Information',//EwayLocale.device.changeDevInfo
			devManage:'Device Management',//EwayLocale.device.devManage
			efficientDev:'Device Information in force',//EwayLocale.device.efficientDev
			unEfficientDev:'Device information not active',//EwayLocale.device.unEfficientDev
			person:{
				week:'Week',//EwayLocale.person.week
				Mon:'Monday',//EwayLocale.person.Mon
				Tues:'Tuesday',//EwayLocale.person.Tues
				Wed:'Wednesday',//EwayLocale.person.Wed
				Thur:'Thursday',//EwayLocale.person.Thur
				Fri:'Friday',//EwayLocale.person.Fri
				Sat:'Saturday',//EwayLocale.person.Sat
				Sun:'Sunday',//EwayLocale.person.Sun
				openClose:'Power / Shutdown',//EwayLocale.person.openClose
				Open:'Power',//EwayLocale.person.Open
				Close:'Shutdown'//EwayLocale.person.Close
			}
		},
		param:{
			paramKey:'Name',//EwayLocale.param.paramKey
			paramValue:'Value',//EwayLocale.param.paramValue
			classify:'Type',//EwayLocale.param.classify
			paramType:'Type',//EwayLocale.param.paramType
			modifyFlag:'Can be Modify?',//EwayLocale.param.modifyFlag
			comboxClassify:{
				unableUpdate:'No',//EwayLocale.comboxClassify.unableUpdate
				ableUpdate:'Yes'//EwayLocale.comboxClassify.ableUpdate
			},
			description:'Description',//EwayLocale.comboxClassify.description
			systemCon:'System Setting',//EwayLocale.comboxClassify.systemCon
			updateSystemCon:'Update system Setting'//EwayLocale.comboxClassify.updateSystemCon
		},
		quittingNotice:{
			addCloseMsg:'Add Service Stop',//EwayLocale.quittingNotice.addCloseMsg
			updateCloseMsg:'Update Service Stop',//EwayLocale.quittingNotice.updateCloseMsg
			dateRangeText:'Recover date can not be earlier than stop date,please choose again',//EwayLocale.quittingNotice.dateRangeText
			click:'Click query to choose device',//EwayLocale.quittingNotice.click
			stopTime:'Stop time',//EwayLocale.quittingNotice.stopTime
			openTime:'Recover time',//EwayLocale.quittingNotice.openTime
			currentStatus:'Current status',//EwayLocale.quittingNotice.currentStatus
			closeType:'Type',//EwayLocale.quittingNotice.closeType
			responsibilityName:'Person in charge',//EwayLocale.quittingNotice.responsibilityName
			stopReason:'Reason',//EwayLocale.quittingNotice.stopReason
			address:'Address',//EwayLocale.quittingNotice.address
			selectDev:'Please choose the device which you want to stop',//EwayLocale.quittingNotice.selectDev
			updateUnable:'Can not update the record which is recovered.',//EwayLocale.quittingNotice.updateUnable
			to:'to',//EwayLocale.quittingNotice.to
			stopType:'Type',//EwayLocale.quittingNotice.stopType
			comboxStopType:{
				recess:'Holiday',//EwayLocale.comboxStopType.recess
				fit:'Decoration',//EwayLocale.comboxStopType.fit
				power:'Power Cut',//EwayLocale.comboxStopType.power
				devFailue:'Device Fault',//EwayLocale.comboxStopType.devFailue
				other:'Other'	//EwayLocale.comboxStopType.other
			},
			setTime:'Create time',//EwayLocale.comboxStopType.setTime
			closeManage:'Service Stop'//EwayLocale.comboxStopType.closeManage
		},
		plan:{
			addPlan:'Add Service Plan',//EwayLocale.plan.addPlan
			name:'Plan Name',//EwayLocale.plan.name
			type:'Type',//EwayLocale.plan.type
			startDate:'Start Time',//EwayLocale.plan.startDate
			endDate:'End Time',//EwayLocale.plan.endDate
			terminalId:'Terminal ID',//EwayLocale.plan.terminalId
			cashboxLimit:'Alarm cashbox(unit:piece)',//EwayLocale.plan.cashboxLimit
			perToDev:'Person<-->Device',//EwayLocale.plan.perToDev
			changePlan:'Update Service Plan',//EwayLocale.plan.changePlan
			servicePlan:'Service Plan'//EwayLocale.plan.servicePlan
		},
		serviceplan:{
			title:'Service Plan',//EwayLocale.serviceplan.title
			name:'Plan Name',//EwayLocale.serviceplan.name
			machineQuantity:'Number of devices Used',//EwayLocale.serviceplan.machineQuantity
			state:'Status',//EwayLocale.serviceplan.state
			openDate:'Start Time',//EwayLocale.serviceplan.openDate
			closeDate:'End Time',//EwayLocale.serviceplan.closeDate
			createDateTime:'Created Time',//EwayLocale.serviceplan.createDateTime
			date:'Date',//EwayLocale.serviceplan.date
			week:'Week',//EwayLocale.serviceplan.week
			weekDay:'',//EwayLocale.serviceplan.weekDay
			inportLinkedMachine:'Import related devices',//EwayLocale.serviceplan.inportLinkedMachine
			selectFile:'Select the file',//EwayLocale.serviceplan.selectFile
			placeUploadingResource:'Please upload resources',//EwayLocale.serviceplan.placeUploadingResource
			fileNotSupport:'Import file format is not supported, according to the template import device information',//EwayLocale.serviceplan.fileNotSupport
			exportExplain:'Import instructions',//EwayLocale.serviceplan.exportExplain
			thisIsTooLong:'Please add the device continuously import template device number to be issued, up to a one-time import 2000 data (takes about 5 minutes), a minimum import data',//EwayLocale.serviceplan.thisIsTooLong
			thisHardToTranslate:'Click to download introducing device ID template',//EwayLocale.serviceplan.thisHardToTranslate
			planDevice:'Service Plan <--> Device',//EwayLocale.serviceplan.planDevice
			timeEare:'Enter the time is incorrect, please re-enter!',//EwayLocale.serviceplan.timeEare
			timeError:'invalid,please re-enter.',//EwayLocale.serviceplan.timeError
			planOlonOne:'The same plan can only set a startup or shutdown',//EwayLocale.serviceplan.planOlonOne
			setTime:'Please set the detailed time',//EwayLocale.serviceplan.setTime
			thisPlanStop:'(This plan is disabled, can not apply!)',//EwayLocale.serviceplan.thisPlanStop
			placeRefresh:'Article lift failed. Please refresh view!',//EwayLocale.serviceplan.placeRefresh
			linking:'Being associated devices....',//EwayLocale.serviceplan.linking
			testingPlaceWaiting:'Device number is judged to meet the requirements, please wait...',//EwayLocale.serviceplan.testingPlaceWaiting
			leastOne:'Importing a device at least once information, please re-select the import file!',//EwayLocale.serviceplan.leastOne
			notMore:'Up to 2000 the first import device information, please re-select the import file!',//EwayLocale.serviceplan.notMore
			checkFile:'Please check the import file',//EwayLocale.serviceplan.checkFile
			fileNotAllowed:'Documents do not meet requirements！',//EwayLocale.serviceplan.fileNotAllowed
			tipExportSuccess:'Article data successfully imported',//EwayLocale.serviceplan.tipExportSuccess
			tipLookUp:'Bar, click View import details!',//EwayLocale.serviceplan.tipLookUp
			tochenkDervice:'Please choose the device you want to change',//EwayLocale.serviceplan.tochenkDervice
			tochenckPeople:'Please choose personnel',//EwayLocale.serviceplan.tochenckPeople
			tipAddError:'Article Adding failed. Please refresh view',//EwayLocale.serviceplan.tipAddError
			planIsHaved:'',//EwayLocale.serviceplan.planIsHaved

			chooseOne:'At Least One',//EwayLocale.serviceplan.chooseOne
			linkSuccess:'Associate success',//EwayLocale.serviceplan.linkSuccess

			linkSuccess:'Associate successfully',//EwayLocale.serviceplan.linkSuccess

			Mon:'Mon',//EwayLocale.serviceplan.Mon
			Tues:'Tues',//EwayLocale.serviceplan.Tues
			Wed:'Wed',//EwayLocale.serviceplan.Wed
			Thur:'Thur',//EwayLocale.serviceplan.Thur
			Fri:'Fri',//EwayLocale.serviceplan.Fri
			Sat:'Sat',//EwayLocale.serviceplan.Sat
			Sun:'Sun',//EwayLocale.serviceplan.Sun
			useSuccess:'Normal',//EwayLocale.serviceplan.useSuccess
			notSuccess:'Never used',//EwayLocale.serviceplan.notSuccess
			lastOneGroup:'Please select at least one in the group',//EwayLocale.serviceplan.lastOneGroup
			lanDetailWeek:'Week detailed list',//EwayLocale.serviceplan.lanDetailWeek
			planDetailDay:'Date detailed list',//EwayLocale.serviceplan.planDetailDay
			selectPlan:'Select a service plan',//EwayLocale.serviceplan.selectPlan
			weekSelect:'Notify way'//EwayLocale.serviceplan.weekSelect
		}

	},
	
	atmLog:{
		dayBackup:'Backup results',//EwayLocale.atmLog.dayBackup
		whole:'All',//EwayLocale.atmLog.whole
		execute:'Executing',//EwayLocale.atmLog.execute
		unKnownFail:'Failed with unknown reason',//EwayLocale.atmLog.unKnownFail
		logDate:'Log date',//EwayLocale.atmLog.logDate
		backupResult:'Backup result',//EwayLocale.atmLog.backupResult
		checkFailInfo:'Check detail of Backup failed',//EwayLocale.atmLog.checkFailInfo
		checkSucInfo:'Check detail of Backup successful',//EwayLocale.atmLog.checkSucInfo
		backupSucAmount:'Count of machine Backup successful',//EwayLocale.atmLog.backupSucAmount
		backupFailAmount:'Count of machine Backup failed',//EwayLocale.atmLog.backupFailAmount
		backupAllAmount:'Total count',//EwayLocale.atmLog.backupAllAmount
		logBackupSta:'Backup log total',//EwayLocale.atmLog.logBackupSta
		dailyBackup:'Backup task everyday',//EwayLocale.atmLog.dailyBackup
		lastDoDate:'LastExecute Date',//EwayLocale.atmLog.lastDoDate
		getLog:'GetLog',//EwayLocale.atmLog.getLog
		backupDate:'Backup date',//EwayLocale.atmLog.backupDate
		dayBackupResult:'Backup result',//EwayLocale.atmLog.dayBackupResult
		backupProcess:'Backuping',//EwayLocale.atmLog.backupProcess
		backupSuccess:'Success',//EwayLocale.atmLog.backupSuccess
		backupError:'Backup error',//EwayLocale.atmLog.backupError
		logDevAccount:'Log Backup machine count total',//EwayLocale.atmLog.logDevAccount
		logDevSucAccount:'Number of backup success',//EwayLocale.atmLog.logDevSucAccount
		logDevFailAccount:'Number of backup failed',//EwayLocale.atmLog.logDevFailAccount
		reform:'Reform',//EwayLocale.atmLog.reform
		busLogAnalysis:'Business log analysis',//EwayLocale.atmLog.busLogAnalysis
		selectAnalysis:'Please choose the log file which you want to analyse, the result will show in Excel file',//EwayLocale.atmLog.selectAnalysis
		selectLog:'Choose log',//EwayLocale.atmLog.selectLog
		pleaseDownload:'Please download',//EwayLocale.atmLog.pleaseDownload
		title:'ATMC Logs',//EwayLocale.atmLog.title
		lastBackupTime:'Last Backup time',//EwayLocale.atmLog.lastBackupTime
		noBegin:'noBegin',//EwayLocale.atmLog.noBegin
		noLog:'No log',//EwayLocale.atmLog.noLog
		connectFail:'Connect fail',//EwayLocale.atmLog.connectFail
		fileSize:'File size',//EwayLocale.atmLog.fileSize
		searchIllegal:'Query option has illegal input,can not export.'//EwayLocale.atmLog.searchIllegal
	},
	
	card:{
		cardNum:'CardNo',//EwayLocale.card.cardNum
		onlyNumber:'Just for numbers,13-19 numbers',//EwayLocale.card.onlyNumber
		cardStatus:'Status',//EwayLocale.card.cardStatus
		eatCardTime:'Time',//EwayLocale.card.eatCardTime
		IDType:'License type',//EwayLocale.card.IDType
		customerName:'CustomerName',//EwayLocale.card.customerName
		customerPapers:'CustomerPapers',//EwayLocale.card.customerPapers
		customerPhone:'CustomerPhone',//EwayLocale.card.customerPhone
		endData:'EndData',//EwayLocale.card.endData
		startData:'StartData',//EwayLocale.card.startData
		add:'Add',//EwayLocale.card.add
		dell:'Delete'//EwayLocale.card.dell
	}
	

});
