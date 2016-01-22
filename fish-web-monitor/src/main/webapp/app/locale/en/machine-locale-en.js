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
			addTitle:'Add Catalog',//EwayLocale.machine.atmCatalog.addTitle
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
				inBank:'Bank',//EwayLocale.machine.atmGroup.comboxAwayFlag.inBank
				outBank:'Non-Bank',//EwayLocale.machine.atmGroup.comboxAwayFlag.outBank
				clickBank:'Alone Out-bank Self-service'//EwayLocale.machine.atmGroup.comboxAwayFlag.clickBank
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
			atmModules:'ATM Module'	//EwayLocale.machine.atmModule.atmModules
		},
		atmMove:{
			title:'Move Manager',//EwayLocale.machine.atmMove.title
			moveDev:'Move Machine',//EwayLocale.machine.atmMove.moveDev
			moveRecordInfo:'Move Machine Record',//EwayLocale.machine.atmMove.moveRecordInfo
			waitMove:'Wait For Move Machine',//EwayLocale.machine.atmMove.waitMove
			terminalId:'Terminal Id',//EwayLocale.machine.atmMove.terminalId
			address:'Source Address',//EwayLocale.machine.atmMove.address
			orgName:'Source Org',//EwayLocale.machine.atmMove.orgName
			targetAddress:'Target Address',//EwayLocale.machine.atmMove.targetAddress
			targetOrganization:'Target Organization',//EwayLocale.machine.atmMove.targetOrganization
			targetPerson:'目标机构负责人',//EwayLocale.machine.atmMove.targetPerson
			responsibility:'负责人',//EwayLocale.machine.atmMove.responsibility
			destPerson:'源机构负责人',//EwayLocale.machine.atmMove.destPerson
			date:'date',//EwayLocale.machine.atmMove.date
			recoverDate:'Recover Date',//EwayLocale.machine.atmMove.recoverDate
			notice:'remark',//EwayLocale.machine.atmMove.notice
			sAddress:'Address',//EwayLocale.machine.atmMove.sAddress
			sOrgName:'Organization',//EwayLocale.machine.atmMove.sOrgName
			to:'to'//EwayLocale.machine.atmMove.to
		},
		atmRuntimeInfo:{
			exportName:'Export', //EwayLocale.machine.atmRuntimeInfo.exportName
			exportDateRangeText:'Start Time Can Not Be Later Than End Time',//EwayLocale.machine.atmRuntimeInfo.exportDateRangeText
			terminalId:'Terminal ID',//EwayLocale.machine.atmRuntimeInfo.terminalId
			terminalIp:'IP',//EwayLocale.machine.atmRuntimeInfo.terminalIp
			startDate:'Begin Time',//EwayLocale.machine.atmRuntimeInfo.startDate
			endDate:'End Time',//EwayLocale.machine.atmRuntimeInfo.endDate
			exportLast30: 'Export Last 30 Days Info',//EwayLocale.machine.atmRuntimeInfo.exportLast30
			terminalId:'terminalId',//EwayLocale.machine.atmRuntimeInfo.terminalId
			netIp:'Ip',//EwayLocale.machine.atmRuntimeInfo.netIp
			msgCollect:'Collect Info Of Customservice'//EwayLocale.machine.atmRuntimeInfo.msgCollect
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
			modules:'Module Contains'//EwayLocale.machine.atmType.modules
		},
		device:{
			title:'Device Info',//EwayLocale.machine.device.title
			devDetailInfo:'Status Of Modules',//EwayLocale.machine.device.devDetailInfo
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
			
			CAMStatusInfo:'Camera Status',//EwayLocale.machine.device.CAMStatusInfo
			CAMRoomStatus:'Room Camera',//EwayLocale.machine.device.CAMRoomStatus
			CAMPersonStatus:'Customer Camera',//EwayLocale.machine.device.CAMPersonStatus
			CAMExitSlotStatus:'Exit Slot Camera',//EwayLocale.machine.device.CAMExitSlotStatus

			CAMInfo:'Camera Property',//EwayLocale.machine.device.CAMInfo
			CAMMaxPictures:'Max Pictures',//EwayLocale.machine.device.CAMMaxPictures
			CAMMaxDataLength:'Max Data Length',//EwayLocale.machine.device.CAMMaxDataLength
			
			BCRInfo:'BarCode Reader Property',//EwayLocale.machine.device.BCRInfo
			BCRCanCompound:'Can Compound',//EwayLocale.machine.device.BCRCanCompound
			BCRCanFilterSymbologies:'Can Filter Symbologies',//EwayLocale.machine.device.BCRCanFilterSymbologies
			
			
			
			CDMInfo:'Dispenser Property',//EwayLocale.machine.device.CDMInfo
			hasStack:'Stacker Exists',//EwayLocale.machine.device.hasStack
			hasShutter:'Shutter Door Exist',//EwayLocale.machine.device.hasShutter
			canRetract:'Ability Of Recovery', //EwayLocale.machine.device.canRetract
			canDetectCashTaken:'Check If Cash Taked',//EwayLocale.machine.device.canDetectCashTaken
			canTestPhysicalUnits:'Test Physical Unit',//EwayLocale.machine.device.canTestPhysicalUnits
			maxDispensBills:'Get Max Count Of Gug Cash In Each Trans',//EwayLocale.machine.device.maxDispensBills
			logicalUnits:'Count Of Logic Cashbox',//EwayLocale.machine.device.logicalUnits
			physicalUnits:'Count Of Physical Cashbox',//EwayLocale.machine.device.physicalUnits
			currency:'Currency Supported Count',//EwayLocale.machine.device.currency
			currencies:'Currency Category Supported',//EwayLocale.machine.device.currencies
			exponents:'index',//EwayLocale.machine.device.exponents
			
			CIMInfo:'Deposit Module (CIM)property',//EwayLocale.machine.device.CIMInfo
			canEscrow:'Stacker Exists',//EwayLocale.machine.device.canEscrow

			shutterControlSupported:'Support Shutter Door',//EwayLocale.machine.device.shutterControlSupported
			maxAcceptItems:'Max Count Of Inspect Cash In Each Trans',//EwayLocale.machine.device.maxAcceptItems
			canDetectCashInserted:'Check If Cash Bringed',//EwayLocale.machine.device.canDetectCashInserted
			canDetectCashTaken:'Check If Cash Taked',//EwayLocale.machine.device.canDetectCashTaken
			retractAreas:'Recovery Position',//EwayLocale.machine.device.retractAreas


			IDCInfo:'Card Reader(IDC) Property',//EwayLocale.machine.device.IDCInfo
			RFCInfo:'RFCardReader(RFC)property',//EwayLocale.machine.device.RFCInfo
			variant:'Card Reader Type',//EwayLocale.machine.device.variant
			canEjectCard:'Ability Of Quit Card',//EwayLocale.machine.device.canEjectCard
			trackJisiiRead:'Ability Of Read TrackJisii',//EwayLocale.machine.device.trackJisiiRead
			track1Read:'Ability Of Read First Track',//EwayLocale.machine.device.track1Read
			track2Read:'Ability Of Read Second Track',//EwayLocale.machine.device.track2Read
			track3Read:'Ability Of Read Third Track',//EwayLocale.machine.device.track3Read
			canCapture:'Ability Of Retain Card',//EwayLocale.machine.device.canCapture
			binCapacity:'Max Count Of Retain Card',//EwayLocale.machine.device.binCapacity
			security:'Safety Support',//EwayLocale.machine.device.security
			trackJisiiWrite:'Ability Of Write TrackJisii',//EwayLocale.machine.device.trackJisiiWrite
			track1Write:'Ability Of Write First Track',//EwayLocale.machine.device.track1Write
			track2Write:'Ability Of Write First Track',//EwayLocale.machine.device.track2Write
			track3Write:'Ability Of Write First Track',//EwayLocale.machine.device.track3Write
			

			JPRInfo:'Journal Printer(JPR) Property',//EwayLocale.machine.device.JPRInfo
			canEject:'Ability Of Quit Paper',//EwayLocale.machine.device.canEject
			canCapture:'Ability Of Recovery',//EwayLocale.machine.device.canCapture
			canStack:'Ability Of Deposit',//EwayLocale.machine.device.canStack
			
			ISCInfo:'ID Scanner(ISC) Property',//EwayLocale.machine.device.ISCInfo
			
			ICCInfo:'Card Dispenser(ICC) Property',//EwayLocale.machine.device.ICCInfo
			dispenseCard:'Ability Of Read',//EwayLocale.machine.device.dispenseCard
			
			FGPInfo:'Finger Printer(FGP) Property',//EwayLocale.machine.device.FGPInfo
			fgp_variant:'Finger Type',//EwayLocale.machine.device.fgp_variant
			canCompare:'Ability Of Compare',//EwayLocale.machine.device.canCompare
			
			PBKInfo:'Passbook Printer(PBK) Property',//EwayLocale.machine.device.PBKInfo

			PINInfo:'PIN Property',//EwayLocale.machine.device.PINInfo
			canEBC:'EBC',//EwayLocale.machine.device.canEBC
			canCBC:'CBC',//EwayLocale.machine.device.canCBC
			canMAC:'MAC',//EwayLocale.machine.device.canMAC
			canRSA:'RSA',//EwayLocale.machine.device.canRSA
			canVerifyVISA:'Inspect VISA',//EwayLocale.machine.device.canVerifyVISA
			canVerifyDES:'Inspect DES',//EwayLocale.machine.device.canVerifyDES
			functionKeys:'Function Support',//EwayLocale.machine.device.functionKeys
			canTripleEBC:'Multi EBC Support',//EwayLocale.machine.device.canTripleEBC
			canTripleCBC:'Multi CBC Support',//EwayLocale.machine.device.canTripleCBC
			canTripleMAC:'Multi MAC Support',//EwayLocale.machine.device.canTripleMAC
			canTripleCFB:'Multi CFB Support',//EwayLocale.machine.device.canTripleCFB
			canVerifyECB:'Inspect ECB',//EwayLocale.machine.device.canVerifyECB
			canDESOffset:'Des Shifting',//EwayLocale.machine.device.canDESOffset

			RPRInfo:'Receipt Printer(RPR) Property',//EwayLocale.machine.device.RPRInfo
			canEject:'Ability Of Quit Paper',//EwayLocale.machine.device.canEject
			canCapture:'Ability Of Recovery',//EwayLocale.machine.device.canCapture

			maxRetract:'Max Count Of Recovery Paper',//EwayLocale.machine.device.maxRetract

			SIUInfo:'Sensors(SIU) Property',//EwayLocale.machine.device.SIUInfo
			operatorSwitchSupported:'Operator Swicth Support',//EwayLocale.machine.device.operatorSwitchSupported
			cabinetSupported:'Ability Of Induce BehindDoor Open Support',//EwayLocale.machine.device.cabinetSupported
			safeSupported:'Ability Of Induce SafeDoor Open Support',//EwayLocale.machine.device.safeSupported
			indicatorSupported:'Ability Of Induce Closeto Support',//EwayLocale.machine.device.indicatorSupported
			guidelightIdcSupported:'Ability Of Card Insert Light Support',//EwayLocale.machine.device.guidelightIdcSupported
			guidelightCdmSupported:'Ability Of Draw Light Support',//EwayLocale.machine.device.guidelightCdmSupported
			guidelightReceiptSupported:'Ability Of Voucher Print Light Support',//EwayLocale.machine.device.guidelightReceiptSupported
			guidelightCimSupported:'Ability Of Deposit Light Support',//EwayLocale.machine.device.guidelightCimSupported


			TTUInfo:'TTU Property',//EwayLocale.machine.device.TTUInfo
			alphanumericKeysPresent:'Ability Of Character/number Input Support',//EwayLocale.machine.device.alphanumericKeysPresent
			numericKeysPresent:'Ability Of Number Input Support',//EwayLocale.machine.device.numericKeysPresent
			displayLightPresent:'Ability Of Number Adjust Screen-brightness Support',//EwayLocale.machine.device.displayLightPresent
			cursorSupported:'Mouse Support',//EwayLocale.machine.device.cursorSupported
			resolutionX:'Resolution Of Cross Axle',//EwayLocale.machine.device.resolutionX
			hexadecimalKeysPresent:'Hexadecimal Input Support',//EwayLocale.machine.device.hexadecimalKeysPresent
			keyboardLockPresent:'Lock Keyboard Support',//EwayLocale.machine.device.keyboardLockPresent
			formsSupported:'Table Support',//EwayLocale.machine.device.formsSupported
			resolutionY:'Resolution Of Vertical Axle',//EwayLocale.machine.device.resolutionY

			comStatus:'Manufacturer',//EwayLocale.machine.device.comStatus
			hwCode:'Fault Code',//EwayLocale.machine.device.hwCode
			CDMStatus:'Withdrawal Module(CDM) Status',//EwayLocale.machine.device.CDMStatus
			cashUnits:'Cashbox',//EwayLocale.machine.device.cashUnits
			safeDoor:'SafeDoor',//EwayLocale.machine.device.safeDoor
			intermediateStacker:'Stacker Status',//EwayLocale.machine.device.intermediateStacker
			outBox:'Cashbox Draw',//EwayLocale.machine.device.outBox
			pcuId:'Relationship Between Physical And Logic Cashbox',//EwayLocale.machine.device.pcuId
			cuId:'Logic CashboxID',//EwayLocale.machine.device.cuId
			cuCurrency:'Logic Cashbox Currency',//EwayLocale.machine.device.cuCurrency
			cuCurrentCount:'Count Of Logic Cashbox Currently',//EwayLocale.machine.device.cuCurrentCount
			cuInitialCount:'Count Of Logic Cashbox Initially',//EwayLocale.machine.device.cuInitialCount
			cuRejectCount:'Count Of Logic Cashbox Rejected',//EwayLocale.machine.device.cuRejectCount
			cuNoteValue:'Denomination Of Logic Cashbox',//EwayLocale.machine.device.cuNoteValue
			cuBinStatus:'Logic Cashbox Status',//EwayLocale.machine.device.cuBinStatus
			puId:'Physical Cashbox ID',//EwayLocale.machine.device.puId
			puPosName:'Name Of Physical Cashbox',//EwayLocale.machine.device.puPosName
			puBinStatus:'Physical Cashbox Status',//EwayLocale.machine.device.puBinStatus
			puCurrentCount:'Count Of Physical Cashbox Currently',//EwayLocale.machine.device.puCurrentCount
			puInitialCount:'Count Of Physical Cashbox Initially',//EwayLocale.machine.device.puInitialCount
			puRejectCount:'Count Of Physical Cashbox Rejected',//EwayLocale.machine.device.puRejectCount
			cuBinType:'Logic Cashbox Status',//EwayLocale.machine.device.cuBinType

			CIMStatus:'Deposit(CIM) Status',//EwayLocale.machine.device.CIMStatus
			baffle:'Baffle',//EwayLocale.machine.device.baffle
			inOutPositionStatus:'Transfer Status',//EwayLocale.machine.device.inOutPositionStatus
			inBox:'Deposit Cashbox',//EwayLocale.machine.device.inBox
			puCashInCount:'Count Of Physical Cashbox In',//EwayLocale.machine.device.puCashInCount
			pcuId:'Relationship Between Physical And Logic Cashbox',//EwayLocale.machine.device.pcuId
			cuType:'Logic Cashbox Type',//EwayLocale.machine.device.cuType
			cuBinStatus:'Logic Cashbox Status',//EwayLocale.machine.device.cuBinStatus
			cuCurrentCount:'Count Of Logic Cashbox',//EwayLocale.machine.device.cuCurrentCount
			cuCurrency:'Currency Of Logic Cashbox',//EwayLocale.machine.device.cuCurrency
			cuNoteValue:'Denomination Of Logic Cashbox',//EwayLocale.machine.device.cuNoteValue

			IDCStatus:'Card Reader (IDC) Status',//EwayLocale.machine.device.IDCStatus
			RFCStatus:'RFCardReader (RFC) Status',//EwayLocale.machine.device.RFCStatus
			media:'Media',//EwayLocale.machine.device.media
			retainBin:'Recovery Box Status',//EwayLocale.machine.device.retainBin
			cards:'Recovery Box Count',//EwayLocale.machine.device.cards

			JRPStatus:'Journal Printer(JPR) Status',//EwayLocale.machine.device.JRPStatus
			supplyLevel:'Journal Printer Status',//EwayLocale.machine.device.supplyLevel
			ink:'Ink',//EwayLocale.machine.device.ink
			toner:'Ribbon',//EwayLocale.machine.device.toner

			PINStatus:'PIN Status',//EwayLocale.machine.device.PINStatus

			RPRStatus:'Receipt Printer(RPR)status',//EwayLocale.machine.device.RPRStatus
			bin:'Recovery Unit Status',//EwayLocale.machine.device.bin

			SIUStatus:'SIU Status',//EwayLocale.machine.device.SIUStatus
			vandalShield:'Shield',//EwayLocale.machine.device.vandalShield
			operatorSwitch:'Operate Butoon Status',//EwayLocale.machine.device.operatorSwitch
			ambientLight:'Environment Light',//EwayLocale.machine.device.ambientLight
			cabinet:'Box Door',//EwayLocale.machine.device.cabinet
			safe:'Safe Door',//EwayLocale.machine.device.safe
			idcGuidelight:'Guide Card-insert Light',//EwayLocale.machine.device.idcGuidelight
			cdmGuidelight:'Guide Draw Light',//EwayLocale.machine.device.cdmGuidelight
			receiptGuidelight:'Guide Voucher Light',//EwayLocale.machine.device.receiptGuidelight
			cimGuidelight:'Guide CIM Light',//EwayLocale.machine.device.cimGuidelight

			TTUStatus:'Text Terminal(TTU) Status',//EwayLocale.machine.device.TTUStatus
			
			ISCStatus:'ID Scanner(ISC) Status',//EwayLocale.machine.device.ISCStatus
			
			ICCStatus:'Card Dispenser(ICC) Status',//EwayLocale.machine.device.ICCStatus
			
			FGPStatus:'Finger Printer(FGP) Status',//EwayLocale.machine.device.FGPStatus
			
			PBKStatus:'Passbook Printer(PBK) Status',//EwayLocale.machine.device.PBKStatus
			
			devPerson:'Device Person',//EwayLocale.machine.device.devPerson
			devModuleMsg:'Device Module Property',//EwayLocale.machine.device.devModuleMsg
			devBasicMsg:'Basic Info',//EwayLocale.machine.device.devBasicMsg
			devTailMsg:'Detail',//EwayLocale.machine.device.devTailMsg
			managePerson:'Manager',//EwayLocale.machine.device.managePerson
			maintainPerson:'Maintainer',//EwayLocale.machine.device.maintainPerson
			name:'Name',//EwayLocale.machine.device.name
			mobile:'Mobile',//EwayLocale.machine.device.mobile
			phone:'Phone',//EwayLocale.machine.device.phone
			email:'Email',//EwayLocale.machine.device.email
			deviceBasicInfo:'Additional Info',//EwayLocale.machine.device.deviceBasicInfo
			lineLogo:'AwayFlag',//EwayLocale.machine.device.lineLogo
			alarmRateRMB:'Alarm Cashbox(RMB)',//EwayLocale.machine.device.alarmRateRMB
			operation:'Service Mode',//EwayLocale.machine.device.operation
			ipAddress:'IP Address',//EwayLocale.machine.device.ipAddress
			swallowCard:'Count Of Retain Card',//EwayLocale.machine.device.swallowCard
			alarmRateHKD:'Alarm Cashbox(HKD)',//EwayLocale.machine.device.alarmRateHKD
			adminPhone:'Manager(mobile)',//EwayLocale.machine.device.adminPhone
			maintainPhone: 'Maintainer(mobile)',//EwayLocale.machine.device.maintainPhone
			log:'Flag',//EwayLocale.machine.device.log
			style: 'type',//EwayLocale.machine.device.style
			status: 'Status',//EwayLocale.machine.device.status
			initailnumber: 'Init Count',//EwayLocale.machine.device.initailnumber
			postnumber: 'Deposit Count',//EwayLocale.machine.device.postnumber
			currentnumber: 'Current Count',//EwayLocale.machine.device.currentnumber
			facevalue: 'Denomination',//EwayLocale.machine.device.facevalue
			currency: 'Currency',//EwayLocale.machine.device.currency
			systemHardwareInfo: 'Hardware & Software',//EwayLocale.machine.device.systemHardwareInfo
			moduleVersionInfo:'Versions Of Module(Real-time)',//EwayLocale.machine.device.moduleVersionInfo
			devModuleStatusInfo: 'Module Status(Real-time)',//EwayLocale.machine.device.devModuleStatusInfo
			devModuleAttributeInfo: 'Module Property(Real-time)',//EwayLocale.machine.device.devModuleAttributeInfo

			remoteControl: 'Control',//EwayLocale.machine.device.remoteControl
			collectJPR:'Logs Getting',//EwayLocale.machine.device.collectJPR
			remoteScreen:'Screenshot',//EwayLocale.machine.device.remoteScreen
			processCheck:'View Process',//EwayLocale.machine.device.processCheck
			remoteExplorer:'Browse',//EwayLocale.machine.device.remoteExplorer
			netWorkLink:'Connect',//EwayLocale.machine.device.netWorkLink
			remoteRestart:'Reboot',//EwayLocale.machine.device.remoteRestart

			progressTip:'Progress Tip',//EwayLocale.machine.device.progressTip
			updateProBar:'This Is Generate With Dynamic Update',//EwayLocale.machine.device.updateProBar


			restartApply: 'Restar App',//EwayLocale.machine.device.restartApply
			confirmRestartApply:'Restar App?',//EwayLocale.machine.device.confirmRestartApply
			nowRestartApply:'Restarting',//EwayLocale.machine.device.nowRestartApply
			restartApplySuc:'Restarting App Successfully!',//EwayLocale.machine.device.restartApplySuc
			restartApplyFail:'Restarting App Failed!',//EwayLocale.machine.device.restartApplyFail


			restartDrive:'Restar Drivers',//EwayLocale.machine.device.restartDrive
			confirmRestartDrive:'Restar Drivers?',//EwayLocale.machine.device.confirmRestartDrive
			nowRestartDrive:'Restarting',//EwayLocale.machine.device.nowRestartDrive
			restartDriveSuc:'Restarting Drivers Successfully!',//EwayLocale.machine.device.restartDriveSuc
			restartDriveFail:'Restarting Drivers Failed!',//EwayLocale.machine.device.restartDriveFail

			restartOS:'Restar Os',//EwayLocale.machine.device.restartOS
			confirmRestartOS:'Restar Os?',//EwayLocale.machine.device.confirmRestartOS
			nowRestartOS:'Restarting',//EwayLocale.machine.device.nowRestartOS
			restartOSSuc:'Restarting Os Successfully!',//EwayLocale.machine.device.restartOSSuc
			restartOSFail:'Restarting Os Failed!',//EwayLocale.machine.device.restartOSFail

			remoteShutdown:'Shutdown',//EwayLocale.machine.device.remoteShutdown
			shutdownApply:'Shutdown App',//EwayLocale.machine.device.shutdownApply
			confirmShutdownApply:'Shutdown App?',//EwayLocale.machine.device.confirmShutdownApply
			nowShutdownApply:'Shutting Down',//EwayLocale.machine.device.nowShutdownApply
			shutdownApplySuc:'Shutdown App Successfully!',//EwayLocale.machine.device.shutdownApplySuc
			shutdownApplyFail:'Shutdown App Failed!',//EwayLocale.machine.device.shutdownApplyFail

			shutdownDrive:'Shutdown Drivers',//EwayLocale.machine.device.shutdownDrive
			confirmShutdownDrive:'Shutdown Drivers?',//EwayLocale.machine.device.confirmShutdownDrive
			nowShutdownDrive:'Shutting Down',//EwayLocale.machine.device.nowShutdownDrive
			shutdownDriveSuc:'Shutdown Drivers Successfully!',//EwayLocale.machine.device.shutdownDriveSuc
			shutdownDriveFail:'Shutdown Drivers Failed!',//EwayLocale.machine.device.shutdownDriveFail

			shutdownOS:'Shutdown Os',//EwayLocale.machine.device.shutdownOS
			confirmShutdownOS:'Shutdown Os?',//EwayLocale.machine.device.confirmShutdownOS
			nowShutdownOS:'Shutting Down',//EwayLocale.machine.device.nowShutdownOS
			shutdownOSSuc:'Shutdown Os Successfully!',//EwayLocale.machine.device.shutdownOSSuc
			shutdownOSFail:'Shutdown Os Failed!',//EwayLocale.machine.device.shutdownOSFail
			getSoftwareList:'Get Software Installed Info',//EwayLocale.machine.device.getSoftwareList
			forceReset:'Reset Force',//EwayLocale.machine.device.forceReset
			openService:'Open Service',//EwayLocale.machine.device.openService
			pauseService:'Pause Service',//EwayLocale.machine.device.pauseService
			checkStatus:'Test Status',//EwayLocale.machine.device.checkStatus

			remoteBrowseDisk:'Browse',//EwayLocale.machine.device.remoteBrowseDisk

			sysHardwareInfo:'Hardware Info',//EwayLocale.machine.device.sysHardwareInfo
			diskMem:'Disk Size',//EwayLocale.machine.device.diskMem
			biosVersion:'Bios Version',//EwayLocale.machine.device.biosVersion
			biosVendor:'Bios Producer',//EwayLocale.machine.device.biosVendor
			biosReleaseDate:'Bios Date',//EwayLocale.machine.device.biosReleaseDate
			memorySize:'Total Memory',//EwayLocale.machine.device.memorySize
			memoryUsed:'Used Memory',//EwayLocale.machine.device.memoryUsed
			memoryFree:'Free Memory',//EwayLocale.machine.device.memoryFree
			memoryPercent:'Memory Used(%)',//EwayLocale.machine.device.memoryPercent
			cpuItemID:'CPU Info',//EwayLocale.machine.device.cpuItemID
			cpuFrequency:'CPU(MHz)',//EwayLocale.machine.device.cpuFrequency
			cpuVendor:'Manufacturer',//EwayLocale.machine.device.cpuVendor
			cpuModel:'CPU Type',//EwayLocale.machine.device.cpuModel
			cacheSize:'Count Of Cache Storage',//EwayLocale.machine.device.cacheSize
			totalCores:'CPU Cores',//EwayLocale.machine.device.totalCores
			userUsePercent:'User Used',//EwayLocale.machine.device.userUsePercent
			sysUsePercent:'System Used',//EwayLocale.machine.device.sysUsePercent
			idlePercent:'Idle(%s)',//EwayLocale.machine.device.idlePercent
			combinedPercent:'Used(%)',//EwayLocale.machine.device.combinedPercent
			diskItemID:'Disk Info',//EwayLocale.machine.device.diskItemID
			diskName:'Name',//EwayLocale.machine.device.diskName
			diskFileSys:'File System',//EwayLocale.machine.device.diskFileSys
			diskTotalSize:'Total Size',//EwayLocale.machine.device.diskTotalSize
			diskFreeSize:'Free Space',//EwayLocale.machine.device.diskFreeSize
			sysSoftInfo:'Software Info',//EwayLocale.machine.device.sysSoftInfo
			OSID:'OS ID',//EwayLocale.machine.device.OSID
			OSDescription:'OS Description',//EwayLocale.machine.device.OSDescription
			OSType:'OS Type',//EwayLocale.machine.device.OSType
			sysPatchLevel:'OS Patch Level',//EwayLocale.machine.device.sysPatchLevel
			chkCashData:'BV-version',//EwayLocale.machine.device.chkCashData
			OSVendor:'OS Producer',//EwayLocale.machine.device.OSVendor
			OSVendorName:'OS Supplier',//EwayLocale.machine.device.OSVendorName
			sysVersion:'OS Version',//EwayLocale.machine.device.sysVersion
			devAddress:'Address',//EwayLocale.machine.device.devAddress
			basicInfo:'Additional Info',//EwayLocale.machine.device.basicInfo
			virtual:'Virtual Teller No.',//EwayLocale.machine.device.virtual
			serial:'Serial Number',//EwayLocale.machine.device.serial
			installDate:'Installation Time',//EwayLocale.machine.device.installDate

			
			cash: 'Cash',//EwayLocale.machine.device.cash
			installStyle: 'Installation Way',//EwayLocale.machine.device.installStyle
			crossWall: 'Through The Wall',//EwayLocale.machine.device.crossWall
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
			to:'To',//EwayLocale.machine.device.to



			devices:'Device',//EwayLocale.machine.device.devices
			configuration:'Setting',//EwayLocale.machine.device.configuration
			spVersion:'SP',//EwayLocale.machine.device.spVersion
			notSupport:'Unsupport',//EwayLocale.machine.device.notSupport
			drive:'Driver',//EwayLocale.machine.device.drive
			firmway: 'Firmware',//EwayLocale.machine.device.firmway
			noDevice:'NoDevice',//EwayLocale.machine.device.noDevice
			devTypeInfo: 'Device Type',//EwayLocale.machine.device.devTypeInfo

			devInfo:'Device Info',//EwayLocale.machine.device.devInfo
			unable:'Can Not',//EwayLocale.machine.device.unable
			able:'Can',//EwayLocale.machine.device.able

			addDevInfo:'Additional Equipment Information',//EwayLocale.machine.device.addDevInfo
			effectiveDate:'effective Date',//EwayLocale.machine.device.effectiveDate
			changeDevInfo:'Update The Device Information',//EwayLocale.machine.device.changeDevInfo
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
			modifyFlag:'Can Be Modify?',//EwayLocale.machine.param.modifyFlag
			comboxClassify:{
				unableUpdate:'No',//EwayLocale.machine.param.comboxClassify.unableUpdate
				ableUpdate:'Yes'//EwayLocale.machine.param.comboxClassify.ableUpdate
			},
			description:'Description',//EwayLocale.machine.param.description
			systemCon:'System Setting',//EwayLocale.machine.param.systemCon
			updateSystemCon:'Update System Setting'//EwayLocale.machine.param.updateSystemCon
		},
		quittingNotice:{
			addCloseMsg:'Add Service Stop',//EwayLocale.machine.quittingNotice.addCloseMsg
			updateCloseMsg:'Update Service Stop',//EwayLocale.machine.quittingNotice.updateCloseMsg
			dateRangeText:'Recover Date Can Not Be Earlier Than Stop Date,PleaseChoose Again',//EwayLocale.machine.quittingNotice.dateRangeText
			click:'Click Query To Choose Device',//EwayLocale.machine.quittingNotice.click
			stopTime:'Stop Time',//EwayLocale.machine.quittingNotice.stopTime
			openTime:'Recover Time',//EwayLocale.machine.quittingNotice.openTime
			currentStatus:'Current Status',//EwayLocale.machine.quittingNotice.currentStatus
			closeType:'Type',//EwayLocale.machine.quittingNotice.closeType
			responsibilityName:'Person In Charge',//EwayLocale.machine.quittingNotice.responsibilityName
			stopReason:'Reason',//EwayLocale.machine.quittingNotice.stopReason
			address:'Address',//EwayLocale.machine.quittingNotice.address
			selectDev:'Please Choose The Device Which You Want To Stop',//EwayLocale.machine.quittingNotice.selectDev
			updateUnable:'Can Not Update The Record Which Is Recovered.',//EwayLocale.machine.quittingNotice.updateUnable
			to:'to',//EwayLocale.machine.quittingNotice.to
			stopType:'Type',//EwayLocale.machine.quittingNotice.stopType
			comboxStopType:{
				recess:'Holiday',//EwayLocale.machine.quittingNotice.comboxStopType.recess
				fit:'Decoration',//EwayLocale.machine.quittingNotice.comboxStopType.fit
				power:'Power Cut',//EwayLocale.machine.quittingNotice.comboxStopType.power
				devFailue:'Device Fault',//EwayLocale.machine.quittingNotice.comboxStopType.devFailue
				other:'Other'	//EwayLocale.machine.quittingNotice.comboxStopType.other
			},
			setTime:'Create Time',//EwayLocale.machine.quittingNotice.setTime
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
			machineQuantity:'Number Of Devices Used',//EwayLocale.machine.serviceplan.machineQuantity
			state:'Status',//EwayLocale.machine.serviceplan.state
			openDate:'Start Time',//EwayLocale.machine.serviceplan.openDate
			closeDate:'End Time',//EwayLocale.machine.serviceplan.closeDate
			createDateTime:'Created Time',//EwayLocale.machine.serviceplan.createDateTime
			date:'Date',//EwayLocale.machine.serviceplan.date
			week:'Week',//EwayLocale.machine.serviceplan.week
			weekDay:'',//EwayLocale.machine.serviceplan.weekDay
			inportLinkedMachine:'Import Related Devices',//EwayLocale.machine.serviceplan.inportLinkedMachine
			selectFile:'Select The File',//EwayLocale.machine.serviceplan.selectFile
			placeUploadingResource:'Please Upload Resources',//EwayLocale.machine.serviceplan.placeUploadingResource
			fileNotSupport:'Import File Format Is Not Supported, According To The Template Import Device Information',//EwayLocale.machine.serviceplan.fileNotSupport
			exportExplain:'Import Instructions',//EwayLocale.machine.serviceplan.exportExplain
			thisIsTooLong:'Please Add The Device Continuously Import Template Device Number To Be Issued, Up To A One-time Import 2000 Data (takes About 5 Minutes), A Minimum Import Data',//EwayLocale.machine.serviceplan.thisIsTooLong
			thisHardToTranslate:'Click To Download Introducing Device ID Template',//EwayLocale.machine.serviceplan.thisHardToTranslate
			planDevice:'Service Plan <--> Device',//EwayLocale.machine.serviceplan.planDevice
			timeEare:'Enter The Time Is Incorrect, Please Re-enter!',//EwayLocale.machine.serviceplan.timeEare
			timeError:'invalid,PleaseRe-enter.',//EwayLocale.machine.serviceplan.timeError
			planOlonOne:'The Same Plan Can Only Set A Startup Or Shutdown',//EwayLocale.machine.serviceplan.planOlonOne
			setTime:'Please Set The Detailed Time',//EwayLocale.machine.serviceplan.setTime
			thisPlanStop:'(This Plan Is Disabled, Can Not Apply!)',//EwayLocale.machine.serviceplan.thisPlanStop
			placeRefresh:'Article Lift Failed. Please Refresh View!',//EwayLocale.machine.serviceplan.placeRefresh
			linking:'Being Associated Devices....',//EwayLocale.machine.serviceplan.linking
			testingPlaceWaiting:'Device Number Is Judged To Meet The Requirements, Please Wait...',//EwayLocale.machine.serviceplan.testingPlaceWaiting
			leastOne:'Importing A Device At Least Once Information, Please Re-select The Import File!',//EwayLocale.machine.serviceplan.leastOne
			notMore:'Up To 2000 The First Import Device Information, Please Re-select The Import File!',//EwayLocale.machine.serviceplan.notMore
			checkFile:'Please Check The Import File',//EwayLocale.machine.serviceplan.checkFile
			fileNotAllowed:'Documents Do Not Meet Requirements!',//EwayLocale.machine.serviceplan.fileNotAllowed
			tipExportSuccess:'Article Data Successfully Imported',//EwayLocale.machine.serviceplan.tipExportSuccess
			tipLookUp:'Bar, Click View Import Details!',//EwayLocale.machine.serviceplan.tipLookUp
			tipAddError:'Article Adding Failed. Please Refresh View',//EwayLocale.machine.serviceplan.tipAddError

			chooseOne:'At Least One',//EwayLocale.machine.serviceplan.chooseOne
			linkSuccess:'Associate Success',//EwayLocale.machine.serviceplan.linkSuccess

			linkSuccess:'Associate Successfully',//EwayLocale.machine.serviceplan.linkSuccess

			Mon:'Mon',//EwayLocale.machine.serviceplan.Mon
			Tues:'Tues',//EwayLocale.machine.serviceplan.Tues
			Wed:'Wed',//EwayLocale.machine.serviceplan.Wed
			Thur:'Thur',//EwayLocale.machine.serviceplan.Thur
			Fri:'Fri',//EwayLocale.machine.serviceplan.Fri
			Sat:'Sat',//EwayLocale.machine.serviceplan.Sat
			Sun:'Sun',//EwayLocale.machine.serviceplan.Sun
			useSuccess:'Normal',//EwayLocale.machine.serviceplan.useSuccess
			notSuccess:'Never Used',//EwayLocale.machine.serviceplan.notSuccess
			lanDetailWeek:'Week Detailed List',//EwayLocale.machine.serviceplan.lanDetailWeek
			planDetailDay:'Date Detailed List',//EwayLocale.machine.serviceplan.planDetailDay
			selectPlan:'Select A Service Plan',//EwayLocale.machine.serviceplan.selectPlan
			weekSelect:'Notify Way'//EwayLocale.machine.serviceplan.weekSelect
		}

	},
	
	atmLog:{
		dayBackup:'Backup Results',//EwayLocale.atmLog.dayBackup
		whole:'All',//EwayLocale.atmLog.whole
		execute:'Executing',//EwayLocale.atmLog.execute
		unKnownFail:'Failed For Unknown Reason',//EwayLocale.atmLog.unKnownFail
		logDate:'Log Date',//EwayLocale.atmLog.logDate
		backupResult:'Backup Result',//EwayLocale.atmLog.backupResult
		checkFailInfo:'Check Detail Of Backup Failed',//EwayLocale.atmLog.checkFailInfo
		checkSucInfo:'Check Detail Of Backup Successfully',//EwayLocale.atmLog.checkSucInfo
		backupSucAmount:'Count Of Machine Backup Successfully',//EwayLocale.atmLog.backupSucAmount
		backupFailAmount:'Count Of Machine Backup Failed',//EwayLocale.atmLog.backupFailAmount
		backupAllAmount:'Total Count',//EwayLocale.atmLog.backupAllAmount
		logBackupSta:'Backup Log Total',//EwayLocale.atmLog.logBackupSta
		lastDoDate:'LastExecute Date',//EwayLocale.atmLog.lastDoDate
		getLog:'GetLog',//EwayLocale.atmLog.getLog
		backupDate:'Backup Date',//EwayLocale.atmLog.backupDate
		dayBackupResult:'Backup Result',//EwayLocale.atmLog.dayBackupResult
		backupProcess:'Backuping',//EwayLocale.atmLog.backupProcess
		backupSuccess:'Success',//EwayLocale.atmLog.backupSuccess
		backupError:'Backup Error',//EwayLocale.atmLog.backupError
		logDevSucAccount:'Number Of Backup Success',//EwayLocale.atmLog.logDevSucAccount
		logDevFailAccount:'Number Of Backup Failed',//EwayLocale.atmLog.logDevFailAccount
		reform:'Reform',//EwayLocale.atmLog.reform
		busLogAnalysis:'Business Log Analysis',//EwayLocale.atmLog.busLogAnalysis
		selectAnalysis:'Please Choose The Log File Which You Want To Analyse, The Result Will Show In Excel File',//EwayLocale.atmLog.selectAnalysis
		selectLog:'Choose Log',//EwayLocale.atmLog.selectLog
		pleaseDownload:'Please Download',//EwayLocale.atmLog.pleaseDownload
		title:'ATMC Logs',//EwayLocale.atmLog.title
		lastBackupTime:'Last Backup Time',//EwayLocale.atmLog.lastBackupTime
		noBegin:'noBegin',//EwayLocale.atmLog.noBegin
		noLog:'No Log',//EwayLocale.atmLog.noLog
		connectFail:'Connect Fail',//EwayLocale.atmLog.connectFail
		fileSize:'File Size',//EwayLocale.atmLog.fileSize
		searchIllegal:'Query Option Has Illegal Input,CanNot Export.'//EwayLocale.atmLog.searchIllegal
	},
	
	card:{
		cardNum:'CardNo',//EwayLocale.card.cardNum
		onlyNumber:'Just For Numbers,13-19Numbers',//EwayLocale.card.onlyNumber
		cardStatus:'Status',//EwayLocale.card.cardStatus
		eatCardTime:'Time',//EwayLocale.card.eatCardTime
		IDType:'License Type',//EwayLocale.card.IDType
		customerName:'CustomerName',//EwayLocale.card.customerName
		customerPapers:'CustomerPapers',//EwayLocale.card.customerPapers
		customerPhone:'CustomerPhone',//EwayLocale.card.customerPhone
		endData:'EndData',//EwayLocale.card.endData
		startData:'StartData',//EwayLocale.card.startData
		add:'Add',//EwayLocale.card.add
		dell:'Delete'//EwayLocale.card.dell
	}
	

});
