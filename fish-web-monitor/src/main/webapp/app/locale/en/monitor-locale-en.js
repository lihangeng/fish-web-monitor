Ext.apply(EwayLocale,{

	
	monitor:{
		summary:{
			title:'Status Overview',//EwayLocale.monitor.summary.title
			allSummary:'All',//EwayLocale.monitor.summary.allSummary
			appSummary:'ATMC',//EwayLocale.monitor.summary.appSummary
			modSummary:'Module',//EwayLocale.monitor.summary.modSummary
			boxSummary:'Cashbox',//EwayLocale.monitor.summary.boxSummary
			netSummary:'Network'//EwayLocale.monitor.summary.netSummary
		},
		deviceStatus:{
			Healthy:'Healthy',//EwayLocale.monitor.deviceStatus.Healthy
			Warning:'Warning',//EwayLocale.monitor.deviceStatus.Warning
			Fatal:'Fatal',//EwayLocale.monitor.deviceStatus.Fatal
			Unknown:'Unknown',//EwayLocale.monitor.deviceStatus.Unknown
			NoDevice:'No Device'//EwayLocale.monitor.deviceStatus.NoDevice
		},
		devMonitor:{
			title:'Status Monitor',//EwayLocale.monitor.devMonitor.title
			comboxStatus:{
				runStatus:'ATMC Status',//EwayLocale.monitor.devMonitor.comboxStatus.runStatus
				modStatus:'Module Status',//EwayLocale.monitor.devMonitor.comboxStatus.modStatus
				boxStatus:'Cashbox Status',//EwayLocale.monitor.devMonitor.comboxStatus.boxStatus
				netStatus:'Network Status'//EwayLocale.monitor.devMonitor.comboxStatus.netStatus
			},
			monitorState:'Filters',//EwayLocale.monitor.devMonitor.monitorState
			showWay:'Show Way',//EwayLocale.monitor.devMonitor.showWay
			comboxShowWay:{
				summaryPattern:'Overview',//EwayLocale.monitor.devMonitor.comboxShowWay.summaryPattern
				matrixPattern:'Matrix',//EwayLocale.monitor.devMonitor.comboxShowWay.matrixPattern
				maxIconPattern:'Large Matrix',//EwayLocale.monitor.devMonitor.comboxShowWay.maxIconPattern
				listPattern:'List',//EwayLocale.monitor.devMonitor.comboxShowWay.listPattern
				boxPattern:'Cashbox'//EwayLocale.monitor.devMonitor.comboxShowWay.boxPattern
			},
			noData:'No Device',//EwayLocale.monitor.devMonitor.noData
			retainCardCount:'Retained Card Amount',//EwayLocale.monitor.devMonitor.retainCardCount
			cash:{
				boxInitCount:'Initial Amount Of Cashbox',//EwayLocale.monitor.devMonitor.cash.boxInitCount
				boxCurrentCount:'Current Amount Of Cashbox',//EwayLocale.monitor.devMonitor.cash.boxCurrentCount
				cashboxLimit:'Cashbox Alarm Amount',//EwayLocale.monitor.devMonitor.cash.cashboxLimit
				initAmount:'Initial Amount',//EwayLocale.monitor.devMonitor.cash.initAmount
				amount:'Amount Left',//EwayLocale.monitor.devMonitor.cash.amount
				dispenseAmount:'Cash Out Amount',//EwayLocale.monitor.devMonitor.cash.dispenseAmount
				rejectAmount:'Reject Amount',//EwayLocale.monitor.devMonitor.cash.rejectAmount
				retractCount:'Retract Count',//EwayLocale.monitor.devMonitor.cash.retractCount
				minAmount:'Minimum Withdraw Amount',//EwayLocale.monitor.devMonitor.cash.minAmount
				boxId:'ID',//EwayLocale.monitor.devMonitor.cash.boxId
				type:'Type',//EwayLocale.monitor.devMonitor.cash.type
				boxStatus:'Status',//EwayLocale.monitor.devMonitor.cash.boxStatus
				initialCount:'Initial Amount',//EwayLocale.monitor.devMonitor.cash.initialCount
				cashInCount:'CashIn Count',//EwayLocale.monitor.devMonitor.cash.cashInCount
				currentCount:'Current Count',//EwayLocale.monitor.devMonitor.cash.currentCount
				noteValue:'Bank Note',//EwayLocale.monitor.devMonitor.cash.noteValue
				currency:'Currency',//EwayLocale.monitor.devMonitor.cash.currency
				boxDetail:'Cashbox Detail',//EwayLocale.monitor.devMonitor.cash.boxDetail
				cimFull:'Full',//EwayLocale.monitor.devMonitor.cash.cimFull
				cdmEmpty:'Empty',//EwayLocale.monitor.devMonitor.cash.cdmEmpty
				cdmLow:'Low',//EwayLocale.monitor.devMonitor.cash.cdmLow
				low:'Low',//EwayLocale.monitor.devMonitor.cash.low
				empty:'Empty',//EwayLocale.monitor.devMonitor.cash.empty
				cimAFull:'Will Full',//EwayLocale.monitor.devMonitor.cash.cimAFull
				cashFault:'Fatal',//EwayLocale.monitor.devMonitor.cash.cashFault
				cashUnknow:'Unknown'//EwayLocale.monitor.devMonitor.cash.cashUnknow

			},
			modGraphic:'Module Graphic',//EwayLocale.monitor.devMonitor.modGraphic
			registerStatus:'Register Status',//EwayLocale.monitor.devMonitor.registerStatus
			devModStatus:'Status Of Modules',//EwayLocale.monitor.devMonitor.devModStatus
			mod:{
				idc:'Card Reader',//EwayLocale.monitor.devMonitor.mod.idc
				nfc:'RFCardReader',//EwayLocale.monitor.devMonitor.mod.nfc
				jpr:'Journal Printer',//EwayLocale.monitor.devMonitor.mod.jpr
				cdm:'Dispenser',//EwayLocale.monitor.devMonitor.mod.cdm
				cim:'Deposit',//EwayLocale.monitor.devMonitor.mod.cim
				siu:'Sensors',//EwayLocale.monitor.devMonitor.mod.siu
				rpr:'Receipt Printer',//EwayLocale.monitor.devMonitor.mod.rpr
				pin:'PIN',//EwayLocale.monitor.devMonitor.mod.pin
				ttu:'TTU',//EwayLocale.monitor.devMonitor.mod.ttu
				isc:'ID Scanner',//EwayLocale.monitor.devMonitor.mod.isc
				icc:'Card Dispenser',//EwayLocale.monitor.devMonitor.mod.icc
				fgp:'Finger Printer',//EwayLocale.monitor.devMonitor.mod.fgp
				healthy:'Healthy',//EwayLocale.monitor.devMonitor.mod.healthy
				cam:'Camera',//EwayLocale.monitor.devMonitor.mod.cam
				bcr:'Barcode Reader',//EwayLocale.monitor.devMonitor.mod.bcr
				pbk:'Passbook Printer'//EwayLocale.monitor.devMonitor.mod.pbk
			},
			remote:{
				control:'Remote Control',//EwayLocale.monitor.devMonitor.remote.control
				screen:'Screenshot',//EwayLocale.monitor.devMonitor.remote.screen
				commandRet:'Command Ret',//EwayLocale.monitor.devMonitor.remote.commandRet
				log:'Fetch Journal Logs',//EwayLocale.monitor.devMonitor.remote.log
				net:'Check Network',//EwayLocale.monitor.devMonitor.remote.net
				softwareList:'Fetch Software List',//EwayLocale.monitor.devMonitor.remote.softwareList
				powerOff:'PowerOff',//EwayLocale.monitor.devMonitor.remote.powerOff
				closeWays:'Choose Way Of Poweroff',//EwayLocale.monitor.devMonitor.remote.closeWays
				restart:'Reboot',//EwayLocale.monitor.devMonitor.remote.restart
				restartWay:'Choose Way Of Reboot',//EwayLocale.monitor.devMonitor.remote.restartWay
				logicOpen:'Start Service',//EwayLocale.monitor.devMonitor.remote.logicOpen
				logicClose:'Stop Service',//EwayLocale.monitor.devMonitor.remote.logicClose
				remoteBrowser:'File Browse',//EwayLocale.monitor.devMonitor.remote.remoteBrowser
				processList:'Fetch Process',//EwayLocale.monitor.devMonitor.remote.processList
				screenCamera:'Screen Recording',//EwayLocale.monitor.devMonitor.remote.screenCamera
				reset:'Force Reset',//EwayLocale.monitor.devMonitor.remote.reset
				remoteLook:'Fetch Application Info',//EwayLocale.monitor.devMonitor.remote.remoteLook
				remoteCheckATM:'Physical Of ATM',//EwayLocale.monitor.devMonitor.remote.remoteCheckATM
				halfSer:'Half Service',//EwayLocale.monitor.devMonitor.remote.halfSer
				healthy:'Healthy',//EwayLocale.monitor.devMonitor.remote.healthy
				staff:'Maintenance',//EwayLocale.monitor.devMonitor.remote.staff

				pFault:'ATMP Error',//EwayLocale.monitor.devMonitor.remote.pFault

				stop:'Stop',//EwayLocale.monitor.devMonitor.remote.stop
				stopCash:'Pause Serivce-no Cash',//EwayLocale.monitor.devMonitor.remote.stopCash
				pauseSer:'Pause Serivce',//EwayLocale.monitor.devMonitor.remote.pauseSer
				pauseCash:'Pause Cash',//EwayLocale.monitor.devMonitor.remote.pauseCash
				pauseSerUnknow:'Pause Serivce-unkown',//EwayLocale.monitor.devMonitor.remote.pauseSerUnknow
				manaAndstaff:'Device Manager',//EwayLocale.monitor.devMonitor.remote.manaAndstaff
				screenFailed:'Connection Refused.'//EwayLocale.monitor.devMonitor.remote.screenFailed
			},
			atmGroup:'Group',//EwayLocale.monitor.devMonitor.atmGroup
			atmGroupTip:'GroupDetail',//EwayLocale.monitor.devMonitor.atmGroupTip
			solution:'Solution Suggest',//EwayLocale.monitor.devMonitor.solution
			faultDescription:'Module Error Description',//EwayLocale.monitor.devMonitor.faultDescription
			fastChoose:'Fast Choose',//EwayLocale.monitor.devMonitor.fastChoose
			init:'Initialization',//EwayLocale.monitor.devMonitor.init
			accTrans:'Transaction',//EwayLocale.monitor.devMonitor.accTrans
			factureStaff:'VDM',//EwayLocale.monitor.devMonitor.factureStaff
			netHealthy:'Healthy',//EwayLocale.monitor.devMonitor.netHealthy
			netUnStable:'Unstable',//EwayLocale.monitor.devMonitor.netUnStable
			netFatal:'Fatal',//EwayLocale.monitor.devMonitor.netFatal
			filterManager: {
				title: 'Filters Manager',//EwayLocale.monitor.devMonitor.filterManager.title
				add: 'Create Filter',//EwayLocale.monitor.devMonitor.filterManager.add
				update: 'Update Filter',//EwayLocale.monitor.devMonitor.filterManager.update
				filterForm: {
					filterName: 'Filter Name'//EwayLocale.monitor.devMonitor.filterManager.filterForm.filterName
				}
			}
		},
		business:{
			transaction:{
				card:'Card No.',//EwayLocale.monitor.business.transaction.card
				dateTime:'Time',//EwayLocale.monitor.business.transaction.dateTime
				transCode:'Transaction Type',//EwayLocale.monitor.business.transaction.transCode
				amt:'Amount',//EwayLocale.monitor.business.transaction.amt
				currency:'Currency',//EwayLocale.monitor.business.transaction.currency
				transId:'Trans ID.',//EwayLocale.monitor.business.transaction.transId
				amtfield:'Amount From',//EwayLocale.monitor.business.transaction.amtfield
				toNum:'to',//EwayLocale.monitor.business.transaction.toNum
				transContainer:'Time Scope',//EwayLocale.monitor.business.transaction.transContainer
				debitAccountOrCard:'Debit Card Number',//EwayLocale.monitor.business.transaction.debitAccountOrCard
				creditAccountOrCard:'Credit Card Number',//EwayLocale.monitor.business.transaction.creditAccountOrCard
				debitAccount:'Debit Card Number',//EwayLocale.monitor.business.transaction.debitAccount
				creditAccount:'Credit Card Number',//EwayLocale.monitor.business.transaction.creditAccount
				localRet:'Local Code',//EwayLocale.monitor.business.transaction.localRet
				hostRet:'Host Code',//EwayLocale.monitor.business.transaction.hostRet
				userName:'User Name',//EwayLocale.monitor.business.transaction.userName
				historyTransaction:{
					title:'Histroy Transaction'//EwayLocale.monitor.business.transaction.historyTransaction.title
				},
				transactionMonitor:{
					title:'Real-time Transaction',//EwayLocale.monitor.business.transaction.transactionMonitor.title
					begin:'Start',//EwayLocale.monitor.business.transaction.transactionMonitor.begin
					stop:'Stop',//EwayLocale.monitor.business.transaction.transactionMonitor.stop
					clear:'Clear Screen',//EwayLocale.monitor.business.transaction.transactionMonitor.clear
					scroll:'Scroll',//EwayLocale.monitor.business.transaction.transactionMonitor.scroll
					up:'up',//EwayLocale.monitor.business.transaction.transactionMonitor.up
					down:'down'//EwayLocale.monitor.business.transaction.transactionMonitor.down
				}
			},
			blackList:{
				title:'Black Card',//EwayLocale.monitor.business.blackList.title
				black:'BlackCard',//EwayLocale.monitor.business.blackList.black
				addBlack:'Add',//EwayLocale.monitor.business.blackList.addBlack
				cardBank:'Bank',//EwayLocale.monitor.business.blackList.cardBank
				importData:'Import',//EwayLocale.monitor.business.blackList.importData
				addDate:'Add Date',//EwayLocale.monitor.business.blackList.addDate
				importTitle:'Bacth Import',//EwayLocale.monitor.business.blackList.importTitle
				importFile:'Import File',//EwayLocale.monitor.business.blackList.importFile
				chooseFileRegex:'Please Choose File To Import,Only.xls Or .xlsx File Is Supported',//EwayLocale.monitor.business.blackList.chooseFileRegex
				fileRegex:'only .xls Or .xlsx File Is Supported',//EwayLocale.monitor.business.blackList.fileRegex
				tempDownload:'Download Temple',//EwayLocale.monitor.business.blackList.tempDownload
				importNow:'Import',//EwayLocale.monitor.business.blackList.importNow
				updateTitle:'Modify'//EwayLocale.monitor.business.blackList.updateTitle
			},
			card:{
				title:'Retained Card',//EwayLocale.monitor.business.card.title
				addTitle:'Add Retained Card Information',//EwayLocale.monitor.business.card.addTitle
				time:'Time',//EwayLocale.monitor.business.card.time
				reason:'Reason',//EwayLocale.monitor.business.card.reason
				destroy:'Destroy',//EwayLocale.monitor.business.card.destroy
				cardHolder:'Issuing Bank',//EwayLocale.monitor.business.card.cardHolder
				cardRegex:'Max Length Is',//EwayLocale.monitor.business.card.cardRegex
				beginEndDate:'Begin Date Can Not Be Later Than End Date,PleaseChoose Again',//EwayLocale.monitor.business.card.beginEndDate
				orgBelongs:'Org Belongs(Include Process Org)',//EwayLocale.monitor.business.card.orgBelongs
				beginTime:'Begin Time',//EwayLocale.monitor.business.card.beginTime
				endTime:'End Time',//EwayLocale.monitor.business.card.endTime
				accGetCard:'Customer Get Card',//EwayLocale.monitor.business.card.accGetCard
				transferCard:'Turnover',//EwayLocale.monitor.business.card.transferCard
				processOrg:'Process Org',//EwayLocale.monitor.business.card.processOrg
				type:'Retained Type',//EwayLocale.monitor.business.card.type
				manual:'Manually',//EwayLocale.monitor.business.card.manual
				auto:'Automatically',//EwayLocale.monitor.business.card.auto
				comboxStatus:{
					wait:'Wait For Claimant',//EwayLocale.monitor.business.card.comboxStatus.wait
					received:'Received',//EwayLocale.monitor.business.card.comboxStatus.received
					destroy:'Destroy',//EwayLocale.monitor.business.card.comboxStatus.destroy
					bringed:'Bringed'//EwayLocale.monitor.business.card.comboxStatus.bringed
				},
				treatmentPeople:'Processor',//EwayLocale.monitor.business.card.treatmentPeople
				treatmentTime:'Time',//EwayLocale.monitor.business.card.treatmentTime
				customerName:'Customer Name',//EwayLocale.monitor.business.card.customerName
				customerPhone:'Telephone',//EwayLocale.monitor.business.card.customerPhone
				customerPapers:'License Code',//EwayLocale.monitor.business.card.customerPapers
				processCard:'Process',//EwayLocale.monitor.business.card.processCard
				destroyCard:'Destroy',//EwayLocale.monitor.business.card.destroyCard
				exportData:'Export',//EwayLocale.monitor.business.card.exportData
				paperType:'License Type',//EwayLocale.monitor.business.card.paperType
				paperCode:'License Code',//EwayLocale.monitor.business.card.paperCode
				idCard:'I.D Card',//EwayLocale.monitor.business.card.idCard
				accountPaper:'Register',//EwayLocale.monitor.business.card.accountPaper
				drivePaper:'Drive License',//EwayLocale.monitor.business.card.drivePaper
				passport:'Passport',//EwayLocale.monitor.business.card.passport
				officer:'Officer License',//EwayLocale.monitor.business.card.officer
				soldier:'Soldier License',//EwayLocale.monitor.business.card.soldier
				busnessPaper:'Juridicalperson License',//EwayLocale.monitor.business.card.busnessPaper
				busnessCode:'Juridicalperson Code',//EwayLocale.monitor.business.card.busnessCode
				taxPaper:'Taxregistration License',//EwayLocale.monitor.business.card.taxPaper
				withDev:'With Device'//EwayLocale.monitor.business.card.withDev
			},


			cashInit:{
				titile:'Load Cash Info',//EwayLocale.monitor.business.cashInit.titile
				uuId:'Cash ID',//EwayLocale.monitor.business.cashInit.uuId
				date:'Date',//EwayLocale.monitor.business.cashInit.date
				amt:'Amount',//EwayLocale.monitor.business.cashInit.amt
				info:'Information',//EwayLocale.monitor.business.cashInit.info
				boxId:'CashboxID',//EwayLocale.monitor.business.cashInit.boxId
				boxCurrency:'Currency',//EwayLocale.monitor.business.cashInit.boxCurrency
				boxInitAmt:'Amount Init',//EwayLocale.monitor.business.cashInit.boxInitAmt
				lastAmt:'Amount Left'//EwayLocale.monitor.business.cashInit.lastAmt
			},
			settlement:{
				title:'Settlement Info',//EwayLocale.monitor.business.settlement.title
				deTitle:'Information',//EwayLocale.monitor.business.settlement.deTitle
				settleId:'SettleID',//EwayLocale.monitor.business.settlement.settleId
				uuId:'Cycle ID',//EwayLocale.monitor.business.settlement.uuId
				endAmt:'Balance Of Cashbox',//EwayLocale.monitor.business.settlement.endAmt
				endDate:'Date Settlement',//EwayLocale.monitor.business.settlement.endDate
				cimNum:'Deposit Count',//EwayLocale.monitor.business.settlement.cimNum
				cdmNum:'Withdraw Count',//EwayLocale.monitor.business.settlement.cdmNum
				totalNum:'Total Count',//EwayLocale.monitor.business.settlement.totalNum
				leftDate:'Settlement Datetime',//EwayLocale.monitor.business.settlement.leftDate
				cimAmt:'Deposit Amount',//EwayLocale.monitor.business.settlement.cimAmt
				cdmAmt:'Withdraw Amount',//EwayLocale.monitor.business.settlement.cdmAmt
				tranAmt:'Total Amount'//EwayLocale.monitor.business.settlement.tranAmt
			}
		},
		remoteCommand:{
			titile:'Remote Command Info',//EwayLocale.monitor.remoteCommand.titile
			form:{
				date:'Date'//EwayLocale.monitor.remoteCommand.form.date
			},
			grid:{
				commandType:'Type',//EwayLocale.monitor.remoteCommand.grid.commandType
				commandResult:'Result',//EwayLocale.monitor.remoteCommand.grid.commandResult
				datetime:'Date',//EwayLocale.monitor.remoteCommand.grid.datetime
				handlePerson:'Personnel'//EwayLocale.monitor.remoteCommand.grid.handlePerson
			}
		}
	},

	
	agent:{
		remote:{
			screen:{
				message:'info', //EwayLocale.agent.remote.screen.message
				startcustom:'Start Recording Front-screen Of Customer', //EwayLocale.agent.remote.screen.startcustom
				stopcustom:'Stop Recording Front-screen Of Customer',//EwayLocale.agent.remote.screen.stopcustom
				startadmin:'Start Recording Front-screen Of Manager',//EwayLocale.agent.remote.screen.startadmin
				stopadmin: 'Stop Recording Front-screen Of Manager',//EwayLocale.agent.remote.screen.stopadmin
				startadvertise: 'Start Recording Advertisement-screen',//EwayLocale.agent.remote.screen.startadvertise
				stopadvertise: 'Stop Recording Advertisement-screen',//EwayLocale.agent.remote.screen.stopadvertise
				startCameraDate: 'Recording Start Time',//EwayLocale.agent.remote.screen.startCameraDate
				stopCameraDate: 'Recording End Time',//EwayLocale.agent.remote.screen.stopCameraDate
				monitorType: 'Screen Type', //EwayLocale.agent.remote.screen.monitorType
				fileNameClient: 'File Name',//EwayLocale.agent.remote.screen.fileNameClient
				nowCamera:'Recording...',//EwayLocale.agent.remote.screen.nowCamera
				finishCamera:'Recording Finish',//EwayLocale.agent.remote.screen.finishCamera
				videoLoad:'Downloading The Video File To The Server...',//EwayLocale.agent.remote.screen.videoLoad
				stopManage:'Automatic Stopped , Please Contact The Manager!',//EwayLocale.agent.remote.screen.stopManage
				manage: 'Operater',	//EwayLocale.agent.remote.screen.manage
				loading:'DownLoad',//EwayLocale.agent.remote.screen.loading
				screenCamera: 'Screen Recording'//EwayLocale.agent.remote.screen.screenCamera
			},
			discInfo: 'Disk Info', //EwayLocale.agent.remote.discInfo
			discName: 'Name',//EwayLocale.agent.remote.discName
			fileSys: 'File System',//EwayLocale.agent.remote.fileSys
			totalSize: 'Total Size',//EwayLocale.agent.remote.totalSize
			freeSize: 'Free Space',//EwayLocale.agent.remote.freeSize
			networkInfo:'Net Connection Info',//EwayLocale.agent.remote.networkInfo
			conenctRate: 'Connection Speed',//EwayLocale.agent.remote.conenctRate
			receivedByte: 'Bytes Received',//EwayLocale.agent.remote.receivedByte
			sendByte: 'Bytes Sended',//EwayLocale.agent.remote.sendByte
			loadData: 'Data Loading,PleaseWait...',//EwayLocale.agent.remote.loadData
			refresh: 'Refresh',//EwayLocale.agent.remote.refresh
			name: 'Name',//EwayLocale.agent.remote.name
			format: 'File System',//EwayLocale.agent.remote.format
			totalSize: 'Total Size',//EwayLocale.agent.remote.totalSize
			freeSize: 'Free Space',//EwayLocale.agent.remote.freeSize
			refreshFailure:'Refresh Failed!',//EwayLocale.agent.remote.refreshFailure
			back:'Back',//EwayLocale.agent.remote.back
			upload: 'Upload',//EwayLocale.agent.remote.upload
			Mkdir: 'New Folder',//EwayLocale.agent.remote.Mkdir
			MkFile: 'New File',//EwayLocale.agent.remote.MkFile
			MKcatalog:'New Folder',//EwayLocale.agent.remote.MKcatalog
			catalogName:'Folder Name',//EwayLocale.agent.remote.catalogName
			remove: 'Delete',//EwayLocale.agent.remote.remove
			execute: 'Execute',//EwayLocale.agent.remote.execute
			path: 'Path',//EwayLocale.agent.remote.path
			search: 'Search',//EwayLocale.agent.remote.search
			size: 'Size',//EwayLocale.agent.remote.size
			fileSize:'File Size:',//EwayLocale.agent.remote.fileSize
			lastTime: 'Date Modified',//EwayLocale.agent.remote.lastTime
			clickLoadFile:'Click To Download This File',//EwayLocale.agent.remote.clickLoadFile
			loadFileSize:'Max Size Of Download File Is 200M',//EwayLocale.agent.remote.loadFileSize
			nowLoadFile:'File Downloading...',//EwayLocale.agent.remote.nowLoadFile
			judgeLoad: 'Broken-point Continuingly-transferring?',//EwayLocale.agent.remote.judgeLoad
			loadFailure:'Download Failed!',//EwayLocale.agent.remote.loadFailure
			number: 'ID',//EwayLocale.agent.remote.number
			programName: 'Program Name',//EwayLocale.agent.remote.programName
			version: 'Version Num',//EwayLocale.agent.remote.version
			publisher: 'Publishers',//EwayLocale.agent.remote.publisher
			diskUsed: 'Disk Usage',//EwayLocale.agent.remote.diskUsed
			softwayList: 'List Of Software',//EwayLocale.agent.remote.softwayList
			networkInfo: 'Network Info',//EwayLocale.agent.remote.networkInfo
			networkLinkStatus: 'Network Connect Status',//EwayLocale.agent.remote.networkLinkStatus
			send: 'Sended',//EwayLocale.agent.remote.send
			receive: 'Received',//EwayLocale.agent.remote.receive
			bite: 'byte:',//EwayLocale.agent.remote.bite
			speed: 'Speed(Mbps):',//EwayLocale.agent.remote.speed
			bandWidth: 'Broadband Speed Test',//EwayLocale.agent.remote.bandWidth
			unit: 'Unit:MB',//EwayLocale.agent.remote.unit
			againTest: 'Test Again',//EwayLocale.agent.remote.againTest
			impressionName: 'Image Name',//EwayLocale.agent.remote.impressionName
			userName: 'User Name',//EwayLocale.agent.remote.userName
			memoryRate: 'Memory',//EwayLocale.agent.remote.memoryRate
			systemProgressInfo:'System Process Info',//EwayLocale.agent.remote.systemProgressInfo
			screenShotTime: 'Screenshot Time',//EwayLocale.agent.remote.screenShotTime
			distanceScreen: 'Remote Screenshot',//EwayLocale.agent.remote.distanceScreen
			distanceExplorer: 'Remote Browse',//EwayLocale.agent.remote.distanceExplorer
			ATMExamination:'Physical ATM',//EwayLocale.agent.remote.ATMExamination
			checkATM: 'Physical Again',//EwayLocale.agent.remote.checkATM
			ATMExamInfo: 'Physical ATM Detail',//EwayLocale.agent.remote.ATMExamInfo
			cpuIdle: 'Free Rate Of CPU',//EwayLocale.agent.remote.cpuIdle
			memoryIdle: 'Free Rate Of Memory',//EwayLocale.agent.remote.memoryIdle
			hardDiskIdle: 'Free Rate Of Disk',//EwayLocale.agent.remote.hardDiskIdle
			uploadFile: 'Upload File',//EwayLocale.agent.remote.uploadFile
			rules:'Can Not Contain:\/?*":<>|',//EwayLocale.agent.remote.rules
			nowCreat:'Creating...',//EwayLocale.agent.remote.nowCreat
			nowPath: 'Current Path',//EwayLocale.agent.remote.nowPath
			confirm: 'Save', //EwayLocale.agent.remote.confirm
			reset: 'Reset',//EwayLocale.agent.remote.reset
			back: 'Back',//EwayLocale.agent.remote.back
			prepareFile:'File Wait For Upload',//EwayLocale.agent.remote.prepareFile
			choseUploadFile:'Please Choose File To Upload',//EwayLocale.agent.remote.choseUploadFile
			nowUploadFile:'File Uploading',//EwayLocale.agent.remote.nowUploadFile
			explorer: 'Browse...',//EwayLocale.agent.remote.explorer
			serverPath: 'Path Of File In Server',//EwayLocale.agent.remote.serverPath
			distanceSuccess:'Remote Create Successfully.',//EwayLocale.agent.remote.distanceSuccess
			distanceFailure:'Remote Create Failed.',//EwayLocale.agent.remote.distanceFailure
			confirmDelete:'Confirm Delete',//EwayLocale.agent.remote.confirmDelete
			confirmExecute:'Confirm Execute',//EwayLocale.agent.remote.confirmExecute
			choseDeleteFile:'Please Choose File Which You Want To Delete.',//EwayLocale.agent.remote.choseDeleteFile
			distanceExecuteSuccess:'Remote Execute Successfully.',//EwayLocale.agent.remote.distanceExecuteSuccess
			distanceExecuteFailure:'Remote Execute Failed.',//EwayLocale.agent.remote.distanceExecuteFailure
			choseExecuteFile:'Please Choose File Which You Want To Execute.',//EwayLocale.agent.remote.choseExecuteFile
			distanceExplorer:'Remote Browse:',//EwayLocale.agent.remote.distanceExplorer
			distanceExplorerFailure:'Remote Browse Failed.',//EwayLocale.agent.remote.distanceExplorerFailure
			fileExist:'File Already Exist!',//EwayLocale.agent.remote.fileExist
			nowUploadFile:'File Uploading......',//EwayLocale.agent.remote.nowUploadFile
			uploadSuccess:'Upload Successfully.',//EwayLocale.agent.remote.uploadSuccess
			yes: "Continue transfer",//EwayLocale.agent.remote.yes
			no: "Override",//EwayLocale.agent.remote.no
			cancel: "Cancel", //EwayLocale.agent.remote.cancel
			choseFile:'No File Selected For Upload,PleaseChoose.',//EwayLocale.agent.remote.choseFile
			returnFailure:'Return Fail.',//EwayLocale.agent.remote.returnFailure
			refreshFailure:'Refresh Failed.',//EwayLocale.agent.remote.refreshFailure
			catalogExist:'The Folder Does Not Exist,PleaseInput Again',//EwayLocale.agent.remote.catalogExist
			testBandWidth:'Test Broadband Error.', //EwayLocale.agent.remote.testBandWidth
			networkMaxSpeed:'Max Net Speed Is',//EwayLocale.agent.remote.networkMaxSpeed
			minutes:'Second',//EwayLocale.agent.remote.minutes
			amount:'Like',//EwayLocale.agent.remote.amount
			specialLine:'Special Line',//EwayLocale.agent.remote.specialLine
			bandWidth:'Broadband',//EwayLocale.agent.remote.bandWidth
			handle:'Processing......',//EwayLocale.agent.remote.handle
			offServer:'Lost Connection From Server.',//EwayLocale.agent.remote.offServer
			ATMCheck:'Executing Physical Of ATM...',//EwayLocale.agent.remote.ATMCheck
			excellent:'excellent',//EwayLocale.agent.remote.excellent
			fine:'good',//EwayLocale.agent.remote.fine
			middle:'middle',//EwayLocale.agent.remote.middle
			bad:'bad',//EwayLocale.agent.remote.bad
			point:'point',//EwayLocale.agent.remote.point
			checkFailure:'Execute Physical Of ATM Failed,PleaseTry Again.',//EwayLocale.agent.remote.checkFailure
			checkVersionInfo:'View Version Information',//EwayLocale.agent.remote.checkVersionInfo
			versionInfo:'The Version Information :',//EwayLocale.agent.remote.versionInfo
			mergeload:'List Of Download',//EwayLocale.agent.remote.mergeload
			mergeDownLoad:'Execute The Task Of Download',//EwayLocale.agent.remote.mergeDownLoad
			clickAddLoadFile:'Add The List Of Download On Click',//EwayLocale.agent.remote.clickAddLoadFile
			removeFile:'Remove The File From List',//EwayLocale.agent.remote.removeFile
			mustIncludeOneFile:'Must Exit File To DownLoad.',//EwayLocale.agent.remote.mustIncludeOneFile
			resetBackUp:'Click To Backup The Logs Of This Day.',//EwayLocale.agent.remote.resetBackUp
			backupAppLogsSuccess:'Backup Atmc Logs Successfully.',//EwayLocale.agent.remote.backupAppLogsSuccess
			backupAppLogsFail:'backup Atmc Logs Failed.',//EwayLocale.agent.remote.backupAppLogsFail
			backupLogSucList:' Backup Atmc Logs List Success.',//EwayLocale.agent.remote.backupLogSucList
			backupLogFailList:' Backup Atmc Logs List Failed.',//EwayLocale.agent.remote.backupLogFailList
			exitDownLoadFile:'add Failed,TheFile Have Exited In The List Of Download.',//EwayLocale.agent.remote.exitDownLoadFile
			maxDownLoadFileSize:'add Failed , All The FileSize Must Less Than 200M.',//EwayLocale.agent.remote.maxDownLoadFileSize
			addFileSuccess:'add File To List Successfully.',//EwayLocale.agent.remote.addFileSuccess
		}
	}
});
