Ext.apply(EwayLocale,{
	
	machine:{
		atmBrand : {
			title:'Device Brand',//EwayLocale.machine.atmBrand.title
			name: 'Name', //EwayLocale.machine.atmBrand.name
			country:'Country',//EwayLocale.machine.atmBrand.country
			hotline1:'Hotline',//EwayLocale.machine.atmBrand.hotline1
			hotline2:'Hotline2',//EwayLocale.machine.atmBrand.hotline2
			address:'Address',//EwayLocale.machine.atmBrand.address
			status:'Status',//EwayLocale.machine.atmBrand.status
			comboxStatus:{
				provider:'Supplier',//EwayLocale.machine.atmBrand.comboxStatus.provider
				maintance:'Service Provider'//EwayLocale.machine.atmBrand.comboxStatus.maintance
			}
		},
		atmCatalog:{
			title:'Device Catalog',//EwayLocale.machine.atmCatalog.title
			name:'Name',//EwayLocale.machine.atmCatalog.name
			note:'Description',//EwayLocale.machine.atmCatalog.note
			addTitle:'Add catalog',//EwayLocale.machine.atmCatalog.addTitle
			updateTitle:'Update Catalog', //EwayLocale.machine.atmCatalog.updateTitle
			number:'Code'//EwayLocale.machine.atmCatalog.number
		},
		atmGroup : {
			terminalId:'Terminal ID', //EwayLocale.machine.atmGroup.terminalId
			ip: 'IP',//EwayLocale.machine.atmGroup.ip
			orgName:'Bank',//EwayLocale.machine.atmGroup.orgName
			devTypeName:'Device Type',//EwayLocale.machine.atmGroup.devTypeName
			devVendorName:'Device Brand',//EwayLocale.machine.atmGroup.devVendorName
			devCatalogName:'Device Catalog',//EwayLocale.machine.atmGroup.devCatalogName
			devGroupName: 'Device Group',//EwayLocale.machine.atmGroup.devGroupName
			status:'Device Status',//EwayLocale.machine.atmGroup.status
			comboxStatus:{
				dredge:'Dredge',//EwayLocale.machine.atmGroup.comboxStatus.dredge
				open:'Open',//EwayLocale.machine.atmGroup.comboxStatus.open
				close:'Close'//EwayLocale.machine.atmGroup.comboxStatus.close
			},
			awayFlag:'AwayFlag',//EwayLocale.machine.atmGroup.awayFlag
			comboxAwayFlag:{
				inBank:'Inside bank',//EwayLocale.machine.atmGroup.comboxAwayFlag.inBank
				outBank:'Outside bank',//EwayLocale.machine.atmGroup.comboxAwayFlag.outBank
				clickBank:'Alone out-bank self-service'//EwayLocale.machine.atmGroup.comboxAwayFlag.clickBank
			},
			devServiceName:'Maintenance Provider',//EwayLocale.machine.atmGroup.devServiceName
			cashboxLimit:'Cashbox Alarm Amount',//EwayLocale.machine.atmGroup.cashboxLimit
			installDate:'Installation Time',//EwayLocale.machine.atmGroup.installDate
			address:'Address',//EwayLocale.machine.atmGroup.address
			addTitle: 'Add Group Info',//EwayLocale.machine.atmGroup.addTitle
			groupName:'Group Name',//EwayLocale.machine.atmGroup.groupName
			note:'Description',//EwayLocale.machine.atmGroup.note
			updateTitle:'Update Group Info'//EwayLocale.machine.atmGroup.updateTitle
		},
		atmModule:{
			moduleName:'Module Name',//EwayLocale.machine.atmModule.moduleName
			note:'Description',//EwayLocale.machine.atmModule.note
			atmModules:'ATM module'	//EwayLocale.machine.atmModule.atmModules
		},
		atmMove:{
			title:'Move Manager',//EwayLocale.machine.atmMove.title
			moveDev:'Move machine',//EwayLocale.machine.atmMove.moveDev
			moveRecordInfo:'Move machine record',//EwayLocale.machine.atmMove.moveRecordInfo
			waitMove:'Wait for move machine',//EwayLocale.machine.atmMove.waitMove
			terminalId:'Terminal Id',//EwayLocale.machine.atmMove.terminalId
			address:'Source address',//EwayLocale.machine.atmMove.address
			orgName:'Source org',//EwayLocale.machine.atmMove.orgName
			targetAddress:'Target address',//EwayLocale.machine.atmMove.targetAddress
			targetOrganization:'Target organization',//EwayLocale.machine.atmMove.targetOrganization
			targetPerson:'目标机构负责人',//EwayLocale.machine.atmMove.targetPerson
			responsibility:'负责人',//EwayLocale.machine.atmMove.responsibility
			destPerson:'源机构负责人',//EwayLocale.machine.atmMove.destPerson
			date:'date',//EwayLocale.machine.atmMove.date
			recoverDate:'Recover date',//EwayLocale.machine.atmMove.recoverDate
			notice:'remark',//EwayLocale.machine.atmMove.notice
			sAddress:'Address',//EwayLocale.machine.atmMove.sAddress
			sOrgName:'Organization',//EwayLocale.machine.atmMove.sOrgName
			to:'to'//EwayLocale.machine.atmMove.to
		},
		atmRuntimeInfo:{
			exportName:'Export', //EwayLocale.machine.atmRuntimeInfo.exportName
			exportDateRangeText:'Start time can not be later than end time',//EwayLocale.machine.atmRuntimeInfo.exportDateRangeText
			terminalId:'Terminal ID',//EwayLocale.machine.atmRuntimeInfo.terminalId
			terminalIp:'IP',//EwayLocale.machine.atmRuntimeInfo.terminalIp
			startDate:'Begin time',//EwayLocale.machine.atmRuntimeInfo.startDate
			endDate:'End time',//EwayLocale.machine.atmRuntimeInfo.endDate
			exportLast30: 'Export last 30 days info',//EwayLocale.machine.atmRuntimeInfo.exportLast30
			terminalId:'terminalId',//EwayLocale.machine.atmRuntimeInfo.terminalId
			netIp:'Ip',//EwayLocale.machine.atmRuntimeInfo.netIp
			msgCollect:'Collect info of customservice'//EwayLocale.machine.atmRuntimeInfo.msgCollect
		},
		atmType:{
			title:'Device Type',//EwayLocale.machine.atmType.title
			name:'Name',//EwayLocale.machine.atmType.name
			devVendorName:'Brand', //EwayLocale.machine.atmType.devVendorName
			devCatalogName:'Catalog',//EwayLocale.machine.atmType.devCatalogName
			devTerminalName:'Type',//EwayLocale.machine.atmType.devTerminalName
			no:'Code',//EwayLocale.machine.atmType.no
			cashtype:'Cash Flag',//EwayLocale.machine.atmType.cashtype
			iscash:'Cash',//EwayLocale.machine.atmType.iscash
			nocash:'Not Cash',//EwayLocale.machine.atmType.nocash
			modules:'Module contains'//EwayLocale.machine.atmType.modules
		},
		device:{
			title:'Device Info',//EwayLocale.machine.device.title
			devDetailInfo:'Status of Modules',//EwayLocale.machine.device.devDetailInfo
		    idc:'Card Reader',//EwayLocale.machine.device.idc
		    rfc:'RFCardReader',//EwayLocale.machine.device.rfc
			jpr:'Journal Printer',//EwayLocale.machine.device.jpr
			cdm:'Dispenser',//EwayLocale.machine.device.cdm
			siu:'Sensor',//EwayLocale.machine.device.siu
			cim:'Deposit',//EwayLocale.machine.device.cim
			ttu:'Text',//EwayLocale.machine.device.ttu
			rpr:'Receipt Printer',//EwayLocale.machine.device.rpr
			pin:'PIN',//EwayLocale.machine.device.pin
			fgp:'Finger Printer',//EwayLocale.machine.device.fgp
			
			CAMStatusInfo:'Camera status',//EwayLocale.machine.device.CAMStatusInfo
			CAMRoomStatus:'Room camera',//EwayLocale.machine.device.CAMRoomStatus
			CAMPersonStatus:'Customer camera',//EwayLocale.machine.device.CAMPersonStatus
			CAMExitSlotStatus:'Exit slot camera',//EwayLocale.machine.device.CAMExitSlotStatus

			CAMInfo:'Camera property',//EwayLocale.machine.device.CAMInfo
			CAMMaxPictures:'Max pictures',//EwayLocale.machine.device.CAMMaxPictures
			CAMMaxDataLength:'Max data length',//EwayLocale.machine.device.CAMMaxDataLength
			
			BCRInfo:'BarCode Reader property',//EwayLocale.machine.device.BCRInfo
			BCRCanCompound:'Can compound',//EwayLocale.machine.device.BCRCanCompound
			BCRCanFilterSymbologies:'Can filter symbologies',//EwayLocale.machine.device.BCRCanFilterSymbologies
			
			
			
			CDMInfo:'Dispenser property',//EwayLocale.machine.device.CDMInfo
			hasStack:'Stacker Exists',//EwayLocale.machine.device.hasStack
			hasShutter:'Shutter door exist',//EwayLocale.machine.device.hasShutter
			canRetract:'Ability of recovery', //EwayLocale.machine.device.canRetract
			canDetectCashTaken:'Check if cash taked',//EwayLocale.machine.device.canDetectCashTaken
			canTestPhysicalUnits:'Test physical unit',//EwayLocale.machine.device.canTestPhysicalUnits
			maxDispensBills:'Get max count of gug cash in each trans',//EwayLocale.machine.device.maxDispensBills
			logicalUnits:'Count of logic cashbox',//EwayLocale.machine.device.logicalUnits
			physicalUnits:'Count of physical cashbox',//EwayLocale.machine.device.physicalUnits
			currency:'Currency supported count',//EwayLocale.machine.device.currency
			currencies:'Currency category supported ',//EwayLocale.machine.device.currencies
			exponents:'index',//EwayLocale.machine.device.exponents
			
			CIMInfo:'Deposit Module (CIM)property',//EwayLocale.machine.device.CIMInfo
			canEscrow:'Stacker Exists',//EwayLocale.machine.device.canEscrow

			shutterControlSupported:'Support shutter door',//EwayLocale.machine.device.shutterControlSupported
			maxAcceptItems:'Max count of inspect cash in each trans',//EwayLocale.machine.device.maxAcceptItems
			canDetectCashInserted:'Check if cash bringed',//EwayLocale.machine.device.canDetectCashInserted
			canDetectCashTaken:'Check if cash taked',//EwayLocale.machine.device.canDetectCashTaken
			retractAreas:'Recovery position',//EwayLocale.machine.device.retractAreas


			IDCInfo:'Card Reader(IDC) property',//EwayLocale.machine.device.IDCInfo
			RFCInfo:'RFCardReader(RFC)property',//EwayLocale.machine.device.RFCInfo
			variant:'Card Reader type',//EwayLocale.machine.device.variant
			canEjectCard:'Ability of quit card',//EwayLocale.machine.device.canEjectCard
			trackJisiiRead:'Ability of read TrackJisii',//EwayLocale.machine.device.trackJisiiRead
			track1Read:'Ability of read first track',//EwayLocale.machine.device.track1Read
			track2Read:'Ability of read second track',//EwayLocale.machine.device.track2Read
			track3Read:'Ability of read third track',//EwayLocale.machine.device.track3Read
			canCapture:'Ability of retain card',//EwayLocale.machine.device.canCapture
			binCapacity:'Max count of retain card',//EwayLocale.machine.device.binCapacity
			security:'Safety support',//EwayLocale.machine.device.security
			trackJisiiWrite:'Ability of write TrackJisii',//EwayLocale.machine.device.trackJisiiWrite
			track1Write:'Ability of write first track',//EwayLocale.machine.device.track1Write
			track2Write:'Ability of write first track',//EwayLocale.machine.device.track2Write
			track3Write:'Ability of write first track',//EwayLocale.machine.device.track3Write
			

			JPRInfo:'Journal Printer(JPR) property',//EwayLocale.machine.device.JPRInfo
			canEject:'Ability of quit paper',//EwayLocale.machine.device.canEject
			canCapture:'Ability of recovery',//EwayLocale.machine.device.canCapture
			canStack:'Ability of deposit',//EwayLocale.machine.device.canStack
			
			ISCInfo:'ID Scanner(ISC) property',//EwayLocale.machine.device.ISCInfo
			
			ICCInfo:'Card dispenser(ICC) property',//EwayLocale.machine.device.ICCInfo
			dispenseCard:'Ability of read',//EwayLocale.machine.device.dispenseCard
			
			FGPInfo:'Finger Printer(FGP) property',//EwayLocale.machine.device.FGPInfo
			fgp_variant:'Finger type',//EwayLocale.machine.device.fgp_variant
			canCompare:'Ability of compare',//EwayLocale.machine.device.canCompare
			
			PBKInfo:'Passbook printer(PBK) property',//EwayLocale.machine.device.PBKInfo

			PINInfo:'PIN property',//EwayLocale.machine.device.PINInfo
			canEBC:'EBC',//EwayLocale.machine.device.canEBC
			canCBC:'CBC',//EwayLocale.machine.device.canCBC
			canMAC:'MAC',//EwayLocale.machine.device.canMAC
			canRSA:'RSA',//EwayLocale.machine.device.canRSA
			canVerifyVISA:'Inspect VISA',//EwayLocale.machine.device.canVerifyVISA
			canVerifyDES:'Inspect DES',//EwayLocale.machine.device.canVerifyDES
			functionKeys:'Function support',//EwayLocale.machine.device.functionKeys
			canTripleEBC:'Multi EBC support',//EwayLocale.machine.device.canTripleEBC
			canTripleCBC:'Multi CBC support',//EwayLocale.machine.device.canTripleCBC
			canTripleMAC:'Multi MAC support',//EwayLocale.machine.device.canTripleMAC
			canTripleCFB:'Multi CFB support',//EwayLocale.machine.device.canTripleCFB
			canVerifyECB:'Inspect ECB',//EwayLocale.machine.device.canVerifyECB
			canDESOffset:'Des shifting',//EwayLocale.machine.device.canDESOffset

			RPRInfo:'Receipt Printer(RPR) property',//EwayLocale.machine.device.RPRInfo
			canEject:'Ability of quit paper',//EwayLocale.machine.device.canEject
			canCapture:'Ability of recovery',//EwayLocale.machine.device.canCapture

			maxRetract:'Max count of recovery paper',//EwayLocale.machine.device.maxRetract

			SIUInfo:'Sensors(SIU) property',//EwayLocale.machine.device.SIUInfo
			operatorSwitchSupported:'Operator swicth support',//EwayLocale.machine.device.operatorSwitchSupported
			cabinetSupported:'Ability of induce  behindDoor open support',//EwayLocale.machine.device.cabinetSupported
			safeSupported:'Ability of induce safeDoor open support',//EwayLocale.machine.device.safeSupported
			indicatorSupported:'Ability of induce closeto support',//EwayLocale.machine.device.indicatorSupported
			guidelightIdcSupported:'Ability of card insert light support',//EwayLocale.machine.device.guidelightIdcSupported
			guidelightCdmSupported:'Ability of draw light  support',//EwayLocale.machine.device.guidelightCdmSupported
			guidelightReceiptSupported:'Ability of voucher print light support',//EwayLocale.machine.device.guidelightReceiptSupported
			guidelightCimSupported:'Ability of deposit light support',//EwayLocale.machine.device.guidelightCimSupported


			TTUInfo:'TTU property',//EwayLocale.machine.device.TTUInfo
			alphanumericKeysPresent:'Ability of character/number input support',//EwayLocale.machine.device.alphanumericKeysPresent
			numericKeysPresent:'Ability of number input support',//EwayLocale.machine.device.numericKeysPresent
			displayLightPresent:'Ability of number adjust screen-brightness support',//EwayLocale.machine.device.displayLightPresent
			cursorSupported:'Mouse support',//EwayLocale.machine.device.cursorSupported
			resolutionX:'Resolution of cross axle',//EwayLocale.machine.device.resolutionX
			hexadecimalKeysPresent:'Hexadecimal input support',//EwayLocale.machine.device.hexadecimalKeysPresent
			keyboardLockPresent:'Lock keyboard support',//EwayLocale.machine.device.keyboardLockPresent
			formsSupported:'Table support',//EwayLocale.machine.device.formsSupported
			resolutionY:'Resolution of vertical axle',//EwayLocale.machine.device.resolutionY

			comStatus:'Manufacturer',//EwayLocale.machine.device.comStatus
			hwCode:'Fault code',//EwayLocale.machine.device.hwCode
			CDMStatus:'Withdrawal module(CDM) status',//EwayLocale.machine.device.CDMStatus
			cashUnits:'Cashbox',//EwayLocale.machine.device.cashUnits
			safeDoor:'SafeDoor',//EwayLocale.machine.device.safeDoor
			intermediateStacker:'Stacker Status',//EwayLocale.machine.device.intermediateStacker
			outBox:'Cashbox draw',//EwayLocale.machine.device.outBox
			pcuId:'Relationship between physical and logic cashbox',//EwayLocale.machine.device.pcuId
			cuId:'Logic cashboxID',//EwayLocale.machine.device.cuId
			cuCurrency:'Logic cashbox currency',//EwayLocale.machine.device.cuCurrency
			cuCurrentCount:'Count of logic cashbox currently',//EwayLocale.machine.device.cuCurrentCount
			cuInitialCount:'Count of logic cashbox initially',//EwayLocale.machine.device.cuInitialCount
			cuRejectCount:'Count of logic cashbox rejected',//EwayLocale.machine.device.cuRejectCount
			cuNoteValue:'Denomination of logic cashbox',//EwayLocale.machine.device.cuNoteValue
			cuBinStatus:'Logic cashbox status',//EwayLocale.machine.device.cuBinStatus
			puId:'Physical cashbox ID',//EwayLocale.machine.device.puId
			puPosName:'Name of physical cashbox',//EwayLocale.machine.device.puPosName
			puBinStatus:'Physical cashbox status',//EwayLocale.machine.device.puBinStatus
			puCurrentCount:'Count of physical cashbox currently',//EwayLocale.machine.device.puCurrentCount
			puInitialCount:'Count of physical cashbox initially',//EwayLocale.machine.device.puInitialCount
			puRejectCount:'Count of physical cashbox rejected',//EwayLocale.machine.device.puRejectCount
			cuBinType:'Logic cashbox status',//EwayLocale.machine.device.cuBinType

			CIMStatus:'Deposit(CIM) status',//EwayLocale.machine.device.CIMStatus
			baffle:'Baffle',//EwayLocale.machine.device.baffle
			inOutPositionStatus:'Transfer status',//EwayLocale.machine.device.inOutPositionStatus
			inBox:'Deposit cashbox',//EwayLocale.machine.device.inBox
			puCashInCount:'Count of physical cashbox in',//EwayLocale.machine.device.puCashInCount
			pcuId:'Relationship between physical and logic cashbox',//EwayLocale.machine.device.pcuId
			cuType:'Logic cashbox type',//EwayLocale.machine.device.cuType
			cuBinStatus:'Logic cashbox status',//EwayLocale.machine.device.cuBinStatus
			cuCurrentCount:'Count of logic cashbox',//EwayLocale.machine.device.cuCurrentCount
			cuCurrency:'Currency of logic cashbox',//EwayLocale.machine.device.cuCurrency
			cuNoteValue:'Denomination of logic cashbox',//EwayLocale.machine.device.cuNoteValue

			IDCStatus:'Card Reader (IDC) status',//EwayLocale.machine.device.IDCStatus
			RFCStatus:'RFCardReader (RFC) status',//EwayLocale.machine.device.RFCStatus
			media:'Media',//EwayLocale.machine.device.media
			retainBin:'Recovery box status',//EwayLocale.machine.device.retainBin
			cards:'Recovery box count',//EwayLocale.machine.device.cards

			JRPStatus:'Journal Printer(JPR) status',//EwayLocale.machine.device.JRPStatus
			supplyLevel:'Journal Printer status',//EwayLocale.machine.device.supplyLevel
			ink:'Ink',//EwayLocale.machine.device.ink
			toner:'Ribbon',//EwayLocale.machine.device.toner

			PINStatus:'PIN status',//EwayLocale.machine.device.PINStatus

			RPRStatus:'Receipt Printer(RPR)status',//EwayLocale.machine.device.RPRStatus
			bin:'Recovery unit status',//EwayLocale.machine.device.bin

			SIUStatus:'SIU status',//EwayLocale.machine.device.SIUStatus
			vandalShield:'Shield',//EwayLocale.machine.device.vandalShield
			operatorSwitch:'Operate butoon status',//EwayLocale.machine.device.operatorSwitch
			ambientLight:'Environment light',//EwayLocale.machine.device.ambientLight
			cabinet:'Box door',//EwayLocale.machine.device.cabinet
			safe:'Safe door',//EwayLocale.machine.device.safe
			idcGuidelight:'Guide card-insert light',//EwayLocale.machine.device.idcGuidelight
			cdmGuidelight:'Guide draw light',//EwayLocale.machine.device.cdmGuidelight
			receiptGuidelight:'Guide voucher light',//EwayLocale.machine.device.receiptGuidelight
			cimGuidelight:'Guide CIM light',//EwayLocale.machine.device.cimGuidelight

			TTUStatus:'Text Terminal(TTU) status',//EwayLocale.machine.device.TTUStatus
			
			ISCStatus:'ID Scanner(ISC) status',//EwayLocale.machine.device.ISCStatus
			
			ICCStatus:'Card dispenser(ICC) status',//EwayLocale.machine.device.ICCStatus
			
			FGPStatus:'Finger Printer(FGP) status',//EwayLocale.machine.device.FGPStatus
			
			PBKStatus:'Passbook printer(PBK) status',//EwayLocale.machine.device.PBKStatus
			
			devPerson:'Device person',//EwayLocale.machine.device.devPerson
			devModuleMsg:'Device module property',//EwayLocale.machine.device.devModuleMsg
			devBasicMsg:'Basic info',//EwayLocale.machine.device.devBasicMsg
			devTailMsg:'Detail',//EwayLocale.machine.device.devTailMsg
			managePerson:'Manager',//EwayLocale.machine.device.managePerson
			maintainPerson:'Maintainer',//EwayLocale.machine.device.maintainPerson
			name:'Name',//EwayLocale.machine.device.name
			mobile:'Mobile',//EwayLocale.machine.device.mobile
			phone:'Phone',//EwayLocale.machine.device.phone
			email:'Email',//EwayLocale.machine.device.email
			deviceBasicInfo:'Additional info',//EwayLocale.machine.device.deviceBasicInfo
			lineLogo:'AwayFlag',//EwayLocale.machine.device.lineLogo
			alarmRateRMB:'Alarm cashbox(RMB)',//EwayLocale.machine.device.alarmRateRMB
			operation:'Service mode',//EwayLocale.machine.device.operation
			ipAddress:'IP address',//EwayLocale.machine.device.ipAddress
			swallowCard:'Count of retain card',//EwayLocale.machine.device.swallowCard
			alarmRateHKD:'Alarm cashbox(HKD)',//EwayLocale.machine.device.alarmRateHKD
			adminPhone:'Manager(mobile)',//EwayLocale.machine.device.adminPhone
			maintainPhone: 'Maintainer(mobile)',//EwayLocale.machine.device.maintainPhone
			log:'Flag',//EwayLocale.machine.device.log
			style: 'type',//EwayLocale.machine.device.style
			status: 'Status',//EwayLocale.machine.device.status
			initailnumber: 'Init count',//EwayLocale.machine.device.initailnumber
			postnumber: 'Deposit count',//EwayLocale.machine.device.postnumber
			currentnumber: 'Current count',//EwayLocale.machine.device.currentnumber
			facevalue: 'Denomination',//EwayLocale.machine.device.facevalue
			currency: 'Currency',//EwayLocale.machine.device.currency
			systemHardwareInfo: 'Hardware & Software',//EwayLocale.machine.device.systemHardwareInfo
			moduleVersionInfo:'Versions of Module(Real-time)',//EwayLocale.machine.device.moduleVersionInfo
			devModuleStatusInfo: 'Module Status(Real-time)',//EwayLocale.machine.device.devModuleStatusInfo
			devModuleAttributeInfo: 'Module Property(Real-time)',//EwayLocale.machine.device.devModuleAttributeInfo

			remoteControl: 'Control',//EwayLocale.machine.device.remoteControl
			collectJPR:'Logs getting',//EwayLocale.machine.device.collectJPR
			remoteScreen:'Screenshot',//EwayLocale.machine.device.remoteScreen
			processCheck:'View process',//EwayLocale.machine.device.processCheck
			remoteExplorer:'Browse',//EwayLocale.machine.device.remoteExplorer
			netWorkLink:'Connect',//EwayLocale.machine.device.netWorkLink
			remoteRestart:'Reboot',//EwayLocale.machine.device.remoteRestart

			progressTip:'Progress tip',//EwayLocale.machine.device.progressTip
			updateProBar:'This is generate with dynamic update',//EwayLocale.machine.device.updateProBar


			restartApply: 'Restar app',//EwayLocale.machine.device.restartApply
			confirmRestartApply:'Restar app?',//EwayLocale.machine.device.confirmRestartApply
			nowRestartApply:'Restarting',//EwayLocale.machine.device.nowRestartApply
			restartApplySuc:'Restarting app successful!',//EwayLocale.machine.device.restartApplySuc
			restartApplyFail:'Restarting app failed!',//EwayLocale.machine.device.restartApplyFail


			restartDrive:'Restar drivers',//EwayLocale.machine.device.restartDrive
			confirmRestartDrive:'Restar drivers?',//EwayLocale.machine.device.confirmRestartDrive
			nowRestartDrive:'Restarting',//EwayLocale.machine.device.nowRestartDrive
			restartDriveSuc:'Restarting drivers successfully!',//EwayLocale.machine.device.restartDriveSuc
			restartDriveFail:'Restarting drivers failed!',//EwayLocale.machine.device.restartDriveFail

			restartOS:'Restar os',//EwayLocale.machine.device.restartOS
			confirmRestartOS:'Restar os?',//EwayLocale.machine.device.confirmRestartOS
			nowRestartOS:'Restarting',//EwayLocale.machine.device.nowRestartOS
			restartOSSuc:'Restarting os successfully!',//EwayLocale.machine.device.restartOSSuc
			restartOSFail:'Restarting os failed!',//EwayLocale.machine.device.restartOSFail

			remoteShutdown:'Shutdown',//EwayLocale.machine.device.remoteShutdown
			shutdownApply:'Shutdown app',//EwayLocale.machine.device.shutdownApply
			confirmShutdownApply:'Shutdown app?',//EwayLocale.machine.device.confirmShutdownApply
			nowShutdownApply:'Shutting down',//EwayLocale.machine.device.nowShutdownApply
			shutdownApplySuc:'Shutdown app successfully!',//EwayLocale.machine.device.shutdownApplySuc
			shutdownApplyFail:'Shutdown app failed!',//EwayLocale.machine.device.shutdownApplyFail

			shutdownDrive:'Shutdown drivers',//EwayLocale.machine.device.shutdownDrive
			confirmShutdownDrive:'Shutdown drivers?',//EwayLocale.machine.device.confirmShutdownDrive
			nowShutdownDrive:'Shutting down',//EwayLocale.machine.device.nowShutdownDrive
			shutdownDriveSuc:'Shutdown drivers successfully!',//EwayLocale.machine.device.shutdownDriveSuc
			shutdownDriveFail:'Shutdown drivers failed!',//EwayLocale.machine.device.shutdownDriveFail

			shutdownOS:'Shutdown os',//EwayLocale.machine.device.shutdownOS
			confirmShutdownOS:'Shutdown os?',//EwayLocale.machine.device.confirmShutdownOS
			nowShutdownOS:'Shutting down',//EwayLocale.machine.device.nowShutdownOS
			shutdownOSSuc:'Shutdown os successfully!',//EwayLocale.machine.device.shutdownOSSuc
			shutdownOSFail:'Shutdown os failed!',//EwayLocale.machine.device.shutdownOSFail
			getSoftwareList:'Get software installed info',//EwayLocale.machine.device.getSoftwareList
			forceReset:'Reset force',//EwayLocale.machine.device.forceReset
			openService:'Open service',//EwayLocale.machine.device.openService
			pauseService:'Pause service',//EwayLocale.machine.device.pauseService
			checkStatus:'Test status',//EwayLocale.machine.device.checkStatus

			remoteBrowseDisk:'Browse',//EwayLocale.machine.device.remoteBrowseDisk

			sysHardwareInfo:'Hardware info',//EwayLocale.machine.device.sysHardwareInfo
			diskMem:'Disk size',//EwayLocale.machine.device.diskMem
			biosVersion:'Bios version',//EwayLocale.machine.device.biosVersion
			biosVendor:'Bios producer',//EwayLocale.machine.device.biosVendor
			biosReleaseDate:'Bios date',//EwayLocale.machine.device.biosReleaseDate
			memorySize:'Total memory',//EwayLocale.machine.device.memorySize
			memoryUsed:'Used memory',//EwayLocale.machine.device.memoryUsed
			memoryFree:'Free memory',//EwayLocale.machine.device.memoryFree
			memoryPercent:'Memory used(%)',//EwayLocale.machine.device.memoryPercent
			cpuItemID:'CPU info',//EwayLocale.machine.device.cpuItemID
			cpuFrequency:'CPU(MHz)',//EwayLocale.machine.device.cpuFrequency
			cpuVendor:'Manufacturer',//EwayLocale.machine.device.cpuVendor
			cpuModel:'CPU type',//EwayLocale.machine.device.cpuModel
			cacheSize:'Count of cache storage',//EwayLocale.machine.device.cacheSize
			totalCores:'CPU cores',//EwayLocale.machine.device.totalCores
			userUsePercent:'User used',//EwayLocale.machine.device.userUsePercent
			sysUsePercent:'System used',//EwayLocale.machine.device.sysUsePercent
			idlePercent:'Idle(%s)',//EwayLocale.machine.device.idlePercent
			combinedPercent:'Used(%)',//EwayLocale.machine.device.combinedPercent
			diskItemID:'Disk info',//EwayLocale.machine.device.diskItemID
			diskName:'Name',//EwayLocale.machine.device.diskName
			diskFileSys:'File system',//EwayLocale.machine.device.diskFileSys
			diskTotalSize:'Total Size',//EwayLocale.machine.device.diskTotalSize
			diskFreeSize:'Free Space',//EwayLocale.machine.device.diskFreeSize
			sysSoftInfo:'Software info',//EwayLocale.machine.device.sysSoftInfo
			OSID:'OS ID',//EwayLocale.machine.device.OSID
			OSDescription:'OS Description',//EwayLocale.machine.device.OSDescription
			OSType:'OS type',//EwayLocale.machine.device.OSType
			sysPatchLevel:'OS patch level',//EwayLocale.machine.device.sysPatchLevel
			chkCashData:'BV-version',//EwayLocale.machine.device.chkCashData
			OSVendor:'OS producer',//EwayLocale.machine.device.OSVendor
			OSVendorName:'OS supplier',//EwayLocale.machine.device.OSVendorName
			sysVersion:'OS version',//EwayLocale.machine.device.sysVersion
			devAddress:'Address',//EwayLocale.machine.device.devAddress
			basicInfo:'Additional Info',//EwayLocale.machine.device.basicInfo
			virtual:'Virtual Teller No.',//EwayLocale.machine.device.virtual
			serial:'Serial Number',//EwayLocale.machine.device.serial
			installDate:'Installation Time',//EwayLocale.machine.device.installDate

			
			cash: 'Cash',//EwayLocale.machine.device.cash
			installStyle: 'Installation Way',//EwayLocale.machine.device.installStyle
			crossWall: 'Through the wall',//EwayLocale.machine.device.crossWall
			mainRoom: 'Lobby',//EwayLocale.machine.device.mainRoom
			netType: 'Network Type',//EwayLocale.machine.device.netType
			wired: 'Wired',//EwayLocale.machine.device.wired
			wireless: 'Wireless',//EwayLocale.machine.device.wireless
			wiredAndWireless: 'Wired & Wireless',//EwayLocale.machine.device.wiredAndWireless
			onBankSignal:'Location',//EwayLocale.machine.device.onBankSignal
			inBank:'Bank',//EwayLocale.machine.device.inBank
			outBank:'Non-Bank',//EwayLocale.machine.device.outBank
			clickBank:'Half Non-Bank',//EwayLocale.machine.device.clickBank
			managePerson:'Manager',//EwayLocale.machine.device.managePerson
			maintainPerson:'Maintenance Engineers',//EwayLocale.machine.device.maintainPerson
			to:'至',//EwayLocale.machine.device.to



			devices:'Device',//EwayLocale.machine.device.devices
			configuration:'Setting',//EwayLocale.machine.device.configuration
			spVersion:'SP',//EwayLocale.machine.device.spVersion
			notSupport:'Unsupport',//EwayLocale.machine.device.notSupport
			drive:'Driver',//EwayLocale.machine.device.drive
			firmway: 'Firmware',//EwayLocale.machine.device.firmway
			noDevice:'NoDevice',//EwayLocale.machine.device.noDevice
			devTypeInfo: 'Device Type',//EwayLocale.machine.device.devTypeInfo

			devInfo:'Device info',//EwayLocale.machine.device.devInfo
			unable:'Can not',//EwayLocale.machine.device.unable
			able:'Can',//EwayLocale.machine.device.able

			addDevInfo:'Additional equipment information',//EwayLocale.machine.device.addDevInfo
			effectiveDate:'effective date',//EwayLocale.machine.device.effectiveDate
			changeDevInfo:'Update the Device Information',//EwayLocale.machine.device.changeDevInfo
			person:{
				week:'Week',//EwayLocale.machine.device.person.week
				Mon:'Monday',//EwayLocale.machine.device.person.Mon
				Tues:'Tuesday',//EwayLocale.machine.device.person.Tues
				Wed:'Wednesday',//EwayLocale.machine.device.person.Wed
				Thur:'Thursday',//EwayLocale.machine.device.person.Thur
				Fri:'Friday',//EwayLocale.machine.device.person.Fri
				Sat:'Saturday',//EwayLocale.machine.device.person.Sat
				Sun:'Sunday',//EwayLocale.machine.device.person.Sun
				openClose:'Power / Shutdown',//EwayLocale.machine.device.person.openClose
				Open:'Power',//EwayLocale.machine.device.person.Open
				Close:'Shutdown'//EwayLocale.machine.device.person.Close
			}
		},
		param:{
			paramKey:'Name',//EwayLocale.machine.param.paramKey
			paramValue:'Value',//EwayLocale.machine.param.paramValue
			modifyFlag:'Can be Modify?',//EwayLocale.machine.param.modifyFlag
			comboxClassify:{
				unableUpdate:'No',//EwayLocale.machine.param.comboxClassify.unableUpdate
				ableUpdate:'Yes'//EwayLocale.machine.param.comboxClassify.ableUpdate
			},
			description:'Description',//EwayLocale.machine.param.description
			systemCon:'System Setting',//EwayLocale.machine.param.systemCon
			updateSystemCon:'Update system Setting'//EwayLocale.machine.param.updateSystemCon
		},
		quittingNotice:{
			addCloseMsg:'Add Service Stop',//EwayLocale.machine.quittingNotice.addCloseMsg
			updateCloseMsg:'Update Service Stop',//EwayLocale.machine.quittingNotice.updateCloseMsg
			dateRangeText:'Recover date can not be earlier than stop date,please choose again',//EwayLocale.machine.quittingNotice.dateRangeText
			click:'Click query to choose device',//EwayLocale.machine.quittingNotice.click
			stopTime:'Stop time',//EwayLocale.machine.quittingNotice.stopTime
			openTime:'Recover time',//EwayLocale.machine.quittingNotice.openTime
			currentStatus:'Current status',//EwayLocale.machine.quittingNotice.currentStatus
			closeType:'Type',//EwayLocale.machine.quittingNotice.closeType
			responsibilityName:'Person in charge',//EwayLocale.machine.quittingNotice.responsibilityName
			stopReason:'Reason',//EwayLocale.machine.quittingNotice.stopReason
			address:'Address',//EwayLocale.machine.quittingNotice.address
			selectDev:'Please choose the device which you want to stop',//EwayLocale.machine.quittingNotice.selectDev
			updateUnable:'Can not update the record which is recovered.',//EwayLocale.machine.quittingNotice.updateUnable
			to:'to',//EwayLocale.machine.quittingNotice.to
			stopType:'Type',//EwayLocale.machine.quittingNotice.stopType
			comboxStopType:{
				recess:'Holiday',//EwayLocale.machine.quittingNotice.comboxStopType.recess
				fit:'Decoration',//EwayLocale.machine.quittingNotice.comboxStopType.fit
				power:'Power Cut',//EwayLocale.machine.quittingNotice.comboxStopType.power
				devFailue:'Device Fault',//EwayLocale.machine.quittingNotice.comboxStopType.devFailue
				other:'Other'	//EwayLocale.machine.quittingNotice.comboxStopType.other
			},
			setTime:'Create time',//EwayLocale.machine.quittingNotice.setTime
			closeManage:'Service Stop'//EwayLocale.machine.quittingNotice.closeManage
		},
		plan:{
			addPlan:'Add Service Plan',//EwayLocale.machine.plan.addPlan
			name:'Plan Name',//EwayLocale.machine.plan.name
			type:'Type',//EwayLocale.machine.plan.type
			startDate:'Start Time',//EwayLocale.machine.plan.startDate
			endDate:'End Time',//EwayLocale.machine.plan.endDate
			changePlan:'Update Service Plan',//EwayLocale.machine.plan.changePlan
			servicePlan:'Service Plan'//EwayLocale.machine.plan.servicePlan
		},
		serviceplan:{
			title:'Service Plan',//EwayLocale.machine.serviceplan.title
			name:'Plan Name',//EwayLocale.machine.serviceplan.name
			machineQuantity:'Number of devices Used',//EwayLocale.machine.serviceplan.machineQuantity
			state:'Status',//EwayLocale.machine.serviceplan.state
			openDate:'Start Time',//EwayLocale.machine.serviceplan.openDate
			closeDate:'End Time',//EwayLocale.machine.serviceplan.closeDate
			createDateTime:'Created Time',//EwayLocale.machine.serviceplan.createDateTime
			date:'Date',//EwayLocale.machine.serviceplan.date
			week:'Week',//EwayLocale.machine.serviceplan.week
			weekDay:'',//EwayLocale.machine.serviceplan.weekDay
			inportLinkedMachine:'Import related devices',//EwayLocale.machine.serviceplan.inportLinkedMachine
			selectFile:'Select the file',//EwayLocale.machine.serviceplan.selectFile
			placeUploadingResource:'Please upload resources',//EwayLocale.machine.serviceplan.placeUploadingResource
			fileNotSupport:'Import file format is not supported, according to the template import device information',//EwayLocale.machine.serviceplan.fileNotSupport
			exportExplain:'Import instructions',//EwayLocale.machine.serviceplan.exportExplain
			thisIsTooLong:'Please add the device continuously import template device number to be issued, up to a one-time import 2000 data (takes about 5 minutes), a minimum import data',//EwayLocale.machine.serviceplan.thisIsTooLong
			thisHardToTranslate:'Click to download introducing device ID template',//EwayLocale.machine.serviceplan.thisHardToTranslate
			planDevice:'Service Plan <--> Device',//EwayLocale.machine.serviceplan.planDevice
			timeEare:'Enter the time is incorrect, please re-enter!',//EwayLocale.machine.serviceplan.timeEare
			timeError:'invalid,please re-enter.',//EwayLocale.machine.serviceplan.timeError
			planOlonOne:'The same plan can only set a startup or shutdown',//EwayLocale.machine.serviceplan.planOlonOne
			setTime:'Please set the detailed time',//EwayLocale.machine.serviceplan.setTime
			thisPlanStop:'(This plan is disabled, can not apply!)',//EwayLocale.machine.serviceplan.thisPlanStop
			placeRefresh:'Article lift failed. Please refresh view!',//EwayLocale.machine.serviceplan.placeRefresh
			linking:'Being associated devices....',//EwayLocale.machine.serviceplan.linking
			testingPlaceWaiting:'Device number is judged to meet the requirements, please wait...',//EwayLocale.machine.serviceplan.testingPlaceWaiting
			leastOne:'Importing a device at least once information, please re-select the import file!',//EwayLocale.machine.serviceplan.leastOne
			notMore:'Up to 2000 the first import device information, please re-select the import file!',//EwayLocale.machine.serviceplan.notMore
			checkFile:'Please check the import file',//EwayLocale.machine.serviceplan.checkFile
			fileNotAllowed:'Documents do not meet requirements！',//EwayLocale.machine.serviceplan.fileNotAllowed
			tipExportSuccess:'Article data successfully imported',//EwayLocale.machine.serviceplan.tipExportSuccess
			tipLookUp:'Bar, click View import details!',//EwayLocale.machine.serviceplan.tipLookUp
			tipAddError:'Article Adding failed. Please refresh view',//EwayLocale.machine.serviceplan.tipAddError

			chooseOne:'At Least One',//EwayLocale.machine.serviceplan.chooseOne
			linkSuccess:'Associate success',//EwayLocale.machine.serviceplan.linkSuccess

			linkSuccess:'Associate successfully',//EwayLocale.machine.serviceplan.linkSuccess

			Mon:'Mon',//EwayLocale.machine.serviceplan.Mon
			Tues:'Tues',//EwayLocale.machine.serviceplan.Tues
			Wed:'Wed',//EwayLocale.machine.serviceplan.Wed
			Thur:'Thur',//EwayLocale.machine.serviceplan.Thur
			Fri:'Fri',//EwayLocale.machine.serviceplan.Fri
			Sat:'Sat',//EwayLocale.machine.serviceplan.Sat
			Sun:'Sun',//EwayLocale.machine.serviceplan.Sun
			useSuccess:'Normal',//EwayLocale.machine.serviceplan.useSuccess
			notSuccess:'Never used',//EwayLocale.machine.serviceplan.notSuccess
			lanDetailWeek:'Week detailed list',//EwayLocale.machine.serviceplan.lanDetailWeek
			planDetailDay:'Date detailed list',//EwayLocale.machine.serviceplan.planDetailDay
			selectPlan:'Select a service plan',//EwayLocale.machine.serviceplan.selectPlan
			weekSelect:'Notify way'//EwayLocale.machine.serviceplan.weekSelect
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
		lastDoDate:'LastExecute Date',//EwayLocale.atmLog.lastDoDate
		getLog:'GetLog',//EwayLocale.atmLog.getLog
		backupDate:'Backup date',//EwayLocale.atmLog.backupDate
		dayBackupResult:'Backup result',//EwayLocale.atmLog.dayBackupResult
		backupProcess:'Backuping',//EwayLocale.atmLog.backupProcess
		backupSuccess:'Success',//EwayLocale.atmLog.backupSuccess
		backupError:'Backup error',//EwayLocale.atmLog.backupError
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
