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
			showWay:'Show way',//EwayLocale.monitor.devMonitor.showWay
			comboxShowWay:{
				summaryPattern:'Overview',//EwayLocale.monitor.devMonitor.comboxShowWay.summaryPattern
				matrixPattern:'Matrix',//EwayLocale.monitor.devMonitor.comboxShowWay.matrixPattern
				maxIconPattern:'Large Matrix',//EwayLocale.monitor.devMonitor.comboxShowWay.maxIconPattern
				listPattern:'List',//EwayLocale.monitor.devMonitor.comboxShowWay.listPattern
				boxPattern:'Cashbox'//EwayLocale.monitor.devMonitor.comboxShowWay.boxPattern
			},
			noData:'No device',//EwayLocale.monitor.devMonitor.noData
			retainCardCount:'Retained Card Count',//EwayLocale.monitor.devMonitor.retainCardCount
			cash:{
				boxInitCount:'Initial Amount of Cashbox',//EwayLocale.monitor.devMonitor.cash.boxInitCount
				boxCurrentCount:'Current Amount of Cashbox',//EwayLocale.monitor.devMonitor.cash.boxCurrentCount
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
				boxDetail:'Cashbox detail',//EwayLocale.monitor.devMonitor.cash.boxDetail
				cimFull:'Full',//EwayLocale.monitor.devMonitor.cash.cimFull
				cdmEmpty:'Empty',//EwayLocale.monitor.devMonitor.cash.cdmEmpty
				cdmLow:'Low',//EwayLocale.monitor.devMonitor.cash.cdmLow
				low:'Low',//EwayLocale.monitor.devMonitor.cash.low
				empty:'Empty',//EwayLocale.monitor.devMonitor.cash.empty
				cimAFull:'Will Full',//EwayLocale.monitor.devMonitor.cash.cimAFull
				cashFault:'Fatal',//EwayLocale.monitor.devMonitor.cash.cashFault
				cashUnknow:'Unknown'//EwayLocale.monitor.devMonitor.cash.cashUnknow

			},
			modGraphic:'Module graphic',//EwayLocale.monitor.devMonitor.modGraphic
			registerStatus:'Register Status',//EwayLocale.monitor.devMonitor.registerStatus
			devModStatus:'Status of Modules',//EwayLocale.monitor.devMonitor.devModStatus
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
				closeWays:'Choose way of poweroff',//EwayLocale.monitor.devMonitor.remote.closeWays
				restart:'Reboot',//EwayLocale.monitor.devMonitor.remote.restart
				restartWay:'Choose way of reboot',//EwayLocale.monitor.devMonitor.remote.restartWay
				logicOpen:'Start Service',//EwayLocale.monitor.devMonitor.remote.logicOpen
				logicClose:'Stop Service',//EwayLocale.monitor.devMonitor.remote.logicClose
				remoteBrowser:'File Browse',//EwayLocale.monitor.devMonitor.remote.remoteBrowser
				processList:'Fetch Process',//EwayLocale.monitor.devMonitor.remote.processList
				screenCamera:'Screen recording',//EwayLocale.monitor.devMonitor.remote.screenCamera
				reset:'Force Reset',//EwayLocale.monitor.devMonitor.remote.reset
				remoteLook:'Fetch Application Info',//EwayLocale.monitor.devMonitor.remote.remoteLook
				remoteCheckATM:'Physical of ATM',//EwayLocale.monitor.devMonitor.remote.remoteCheckATM
				halfSer:'Half service',//EwayLocale.monitor.devMonitor.remote.halfSer
				healthy:'Healthy',//EwayLocale.monitor.devMonitor.remote.healthy
				staff:'Maintenance',//EwayLocale.monitor.devMonitor.remote.staff

				pFault:'ATMP Error',//EwayLocale.monitor.devMonitor.remote.pFault

				stop:'Stop',//EwayLocale.monitor.devMonitor.remote.stop
				stopCash:'Pause serivce-no cash',//EwayLocale.monitor.devMonitor.remote.stopCash
				pauseSer:'Pause serivce',//EwayLocale.monitor.devMonitor.remote.pauseSer
				pauseCash:'Pause cash',//EwayLocale.monitor.devMonitor.remote.pauseCash
				pauseSerUnknow:'Pause serivce-unkown',//EwayLocale.monitor.devMonitor.remote.pauseSerUnknow
				manaAndstaff:'Device Manager',//EwayLocale.monitor.devMonitor.remote.manaAndstaff
				screenFailed:'Connection refused.'//EwayLocale.monitor.devMonitor.remote.screenFailed
			},
			atmGroup:'Group',//EwayLocale.monitor.devMonitor.atmGroup
			atmGroupTip:'GroupDetail',//EwayLocale.monitor.devMonitor.atmGroupTip
			solution:'Solution suggest',//EwayLocale.monitor.devMonitor.solution
			faultDescription:'Module error description',//EwayLocale.monitor.devMonitor.faultDescription
			fastChoose:'Fast choose',//EwayLocale.monitor.devMonitor.fastChoose
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
				amtfield:'Amount from',//EwayLocale.monitor.business.transaction.amtfield
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
					title:'Histroy Transaction '//EwayLocale.monitor.business.transaction.historyTransaction.title
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
				addDate:'Add date',//EwayLocale.monitor.business.blackList.addDate
				importTitle:'Bacth import',//EwayLocale.monitor.business.blackList.importTitle
				importFile:'Import file',//EwayLocale.monitor.business.blackList.importFile
				chooseFileRegex:'Please choose file to import,only .xls or .xlsx file is supported',//EwayLocale.monitor.business.blackList.chooseFileRegex
				fileRegex:'only .xls or .xlsx file is supported',//EwayLocale.monitor.business.blackList.fileRegex
				tempDownload:'Download temple',//EwayLocale.monitor.business.blackList.tempDownload
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
				cardRegex:'Max length is ',//EwayLocale.monitor.business.card.cardRegex
				beginEndDate:'Begin date can not be later than end date,please choose again',//EwayLocale.monitor.business.card.beginEndDate
				orgBelongs:'Org belongs(Include process org)',//EwayLocale.monitor.business.card.orgBelongs
				beginTime:'Begin time ',//EwayLocale.monitor.business.card.beginTime
				endTime:'End time',//EwayLocale.monitor.business.card.endTime
				accGetCard:'Customer get card',//EwayLocale.monitor.business.card.accGetCard
				transferCard:'Turnover',//EwayLocale.monitor.business.card.transferCard
				processOrg:'Process org',//EwayLocale.monitor.business.card.processOrg
				type:'Retained Type',//EwayLocale.monitor.business.card.type
				manual:'Manually',//EwayLocale.monitor.business.card.manual
				auto:'Automatically',//EwayLocale.monitor.business.card.auto
				comboxStatus:{
					wait:'Wait for claimant',//EwayLocale.monitor.business.card.comboxStatus.wait
					received:'Received',//EwayLocale.monitor.business.card.comboxStatus.received
					destroy:'Destroy',//EwayLocale.monitor.business.card.comboxStatus.destroy
					bringed:'Bringed'//EwayLocale.monitor.business.card.comboxStatus.bringed
				},
				treatmentPeople:'Processor',//EwayLocale.monitor.business.card.treatmentPeople
				treatmentTime:'Time',//EwayLocale.monitor.business.card.treatmentTime
				customerName:'Customer name',//EwayLocale.monitor.business.card.customerName
				customerPhone:'Telephone',//EwayLocale.monitor.business.card.customerPhone
				customerPapers:'License code',//EwayLocale.monitor.business.card.customerPapers
				processCard:'Process',//EwayLocale.monitor.business.card.processCard
				destroyCard:'Destroy',//EwayLocale.monitor.business.card.destroyCard
				exportData:'Export',//EwayLocale.monitor.business.card.exportData
				paperType:'License type',//EwayLocale.monitor.business.card.paperType
				paperCode:'License code',//EwayLocale.monitor.business.card.paperCode
				idCard:'I.D card',//EwayLocale.monitor.business.card.idCard
				accountPaper:'Register',//EwayLocale.monitor.business.card.accountPaper
				drivePaper:'Drive License',//EwayLocale.monitor.business.card.drivePaper
				passport:'Passport',//EwayLocale.monitor.business.card.passport
				officer:'Officer License',//EwayLocale.monitor.business.card.officer
				soldier:'Soldier License',//EwayLocale.monitor.business.card.soldier
				busnessPaper:'Juridicalperson License',//EwayLocale.monitor.business.card.busnessPaper
				busnessCode:'Juridicalperson code',//EwayLocale.monitor.business.card.busnessCode
				taxPaper:'Taxregistration License',//EwayLocale.monitor.business.card.taxPaper
				withDev:'With device'//EwayLocale.monitor.business.card.withDev
			},


			cashInit:{
				titile:'Load Cash Info',//EwayLocale.monitor.business.cashInit.titile
				uuId:'Cash ID',//EwayLocale.monitor.business.cashInit.uuId
				date:'Date',//EwayLocale.monitor.business.cashInit.date
				amt:'Amount',//EwayLocale.monitor.business.cashInit.amt
				info:'Information',//EwayLocale.monitor.business.cashInit.info
				boxId:'CashboxID',//EwayLocale.monitor.business.cashInit.boxId
				boxCurrency:'Currency',//EwayLocale.monitor.business.cashInit.boxCurrency
				boxInitAmt:'Amount init',//EwayLocale.monitor.business.cashInit.boxInitAmt
				lastAmt:'Amount left'//EwayLocale.monitor.business.cashInit.lastAmt
			},
			settlement:{
				title:'Settlement Info',//EwayLocale.monitor.business.settlement.title
				deTitle:'Information',//EwayLocale.monitor.business.settlement.deTitle
				settleId:'SettleID',//EwayLocale.monitor.business.settlement.settleId
				uuId:'Cycle ID',//EwayLocale.monitor.business.settlement.uuId
				endAmt:'Balance of Cashbox',//EwayLocale.monitor.business.settlement.endAmt
				endDate:'Date settlement',//EwayLocale.monitor.business.settlement.endDate
				cimNum:'Deposit count',//EwayLocale.monitor.business.settlement.cimNum
				cdmNum:'Withdraw count',//EwayLocale.monitor.business.settlement.cdmNum
				totalNum:'Total Count',//EwayLocale.monitor.business.settlement.totalNum
				leftDate:'Settlement Datetime',//EwayLocale.monitor.business.settlement.leftDate
				cimAmt:'Deposit amount',//EwayLocale.monitor.business.settlement.cimAmt
				cdmAmt:'Withdraw amount',//EwayLocale.monitor.business.settlement.cdmAmt
				tranAmt:'Total amount'//EwayLocale.monitor.business.settlement.tranAmt
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
				startcustom:'Start recording front-screen of customer', //EwayLocale.agent.remote.screen.startcustom
				stopcustom:'Stop recording front-screen of customer',//EwayLocale.agent.remote.screen.stopcustom
				startadmin:'Start recording front-screen of manager',//EwayLocale.agent.remote.screen.startadmin
				stopadmin: 'Stop recording front-screen of manager',//EwayLocale.agent.remote.screen.stopadmin
				startadvertise: 'Start recording advertisement-screen',//EwayLocale.agent.remote.screen.startadvertise
				stopadvertise: 'Stop recording advertisement-screen',//EwayLocale.agent.remote.screen.stopadvertise
				startCameraDate: 'Recording start time',//EwayLocale.agent.remote.screen.startCameraDate
				stopCameraDate: 'Recording end time',//EwayLocale.agent.remote.screen.stopCameraDate
				monitorType: 'Screen type', //EwayLocale.agent.remote.screen.monitorType
				fileNameClient: 'File name',//EwayLocale.agent.remote.screen.fileNameClient
				nowCamera:'Recording...',//EwayLocale.agent.remote.screen.nowCamera
				finishCamera:'Recording finish',//EwayLocale.agent.remote.screen.finishCamera
				videoLoad:'Downloading the video file to the server...',//EwayLocale.agent.remote.screen.videoLoad
				stopManage:'Automatic stopped ,please contact the manager!',//EwayLocale.agent.remote.screen.stopManage
				manage: 'Operater',	//EwayLocale.agent.remote.screen.manage
				loading:'DownLoad',//EwayLocale.agent.remote.screen.loading
				screenCamera: 'Screen recording'//EwayLocale.agent.remote.screen.screenCamera
			},
			discInfo: 'Disk info', //EwayLocale.agent.remote.discInfo
			discName: 'Name',//EwayLocale.agent.remote.discName
			fileSys: 'File system',//EwayLocale.agent.remote.fileSys
			totalSize: 'Total Size',//EwayLocale.agent.remote.totalSize
			freeSize: 'Free Space',//EwayLocale.agent.remote.freeSize
			networkInfo:'Net connection info',//EwayLocale.agent.remote.networkInfo
			conenctRate: 'Connection speed',//EwayLocale.agent.remote.conenctRate
			receivedByte: 'Bytes received',//EwayLocale.agent.remote.receivedByte
			sendByte: 'Bytes sended',//EwayLocale.agent.remote.sendByte
			loadData: 'Data loading,please wait...',//EwayLocale.agent.remote.loadData
			refresh: 'Refresh',//EwayLocale.agent.remote.refresh
			name: 'Name',//EwayLocale.agent.remote.name
			format: 'File system',//EwayLocale.agent.remote.format
			totalSize: 'Total Size',//EwayLocale.agent.remote.totalSize
			freeSize: 'Free Space',//EwayLocale.agent.remote.freeSize
			refreshFailure:'Refresh failed!',//EwayLocale.agent.remote.refreshFailure
			back:'Back',//EwayLocale.agent.remote.back
			upload: 'Upload',//EwayLocale.agent.remote.upload
			Mkdir: 'New folder',//EwayLocale.agent.remote.Mkdir
			MkFile: 'New file',//EwayLocale.agent.remote.MkFile
			MKcatalog:'New folder',//EwayLocale.agent.remote.MKcatalog
			catalogName:'Folder name',//EwayLocale.agent.remote.catalogName
			remove: 'Delete',//EwayLocale.agent.remote.remove
			execute: 'Execute',//EwayLocale.agent.remote.execute
			path: 'Path',//EwayLocale.agent.remote.path
			search: 'Search',//EwayLocale.agent.remote.search
			size: 'Size',//EwayLocale.agent.remote.size
			fileSize:'File size：',//EwayLocale.agent.remote.fileSize
			lastTime: 'Date modified',//EwayLocale.agent.remote.lastTime
			clickLoadFile:'Click to download this file',//EwayLocale.agent.remote.clickLoadFile
			loadFileSize:'Max size of download file is 200M',//EwayLocale.agent.remote.loadFileSize
			nowLoadFile:'File downloading...',//EwayLocale.agent.remote.nowLoadFile
			judgeLoad: 'Broken-point Continuingly-transferring?',//EwayLocale.agent.remote.judgeLoad
			loadFailure:'Download failed!',//EwayLocale.agent.remote.loadFailure
			number: 'ID',//EwayLocale.agent.remote.number
			programName: 'Program Name',//EwayLocale.agent.remote.programName
			version: 'Version Num',//EwayLocale.agent.remote.version
			publisher: 'Publishers',//EwayLocale.agent.remote.publisher
			diskUsed: 'Disk usage',//EwayLocale.agent.remote.diskUsed
			softwayList: 'List of Software',//EwayLocale.agent.remote.softwayList
			networkInfo: 'Network info',//EwayLocale.agent.remote.networkInfo
			networkLinkStatus: 'Network connect status',//EwayLocale.agent.remote.networkLinkStatus
			send: 'Sended',//EwayLocale.agent.remote.send
			receive: 'Received',//EwayLocale.agent.remote.receive
			bite: 'byte:',//EwayLocale.agent.remote.bite
			speed: 'Speed(Mbps):',//EwayLocale.agent.remote.speed
			bandWidth: 'Broadband speed test',//EwayLocale.agent.remote.bandWidth
			unit: 'Unit:MB',//EwayLocale.agent.remote.unit
			againTest: 'Test again',//EwayLocale.agent.remote.againTest
			impressionName: 'Image Name',//EwayLocale.agent.remote.impressionName
			userName: 'User Name',//EwayLocale.agent.remote.userName
			memoryRate: 'Memory',//EwayLocale.agent.remote.memoryRate
			systemProgressInfo:'System process info',//EwayLocale.agent.remote.systemProgressInfo
			screenShotTime: 'Screenshot time',//EwayLocale.agent.remote.screenShotTime
			distanceScreen: 'Remote screenshot',//EwayLocale.agent.remote.distanceScreen
			distanceExplorer: 'Remote browse',//EwayLocale.agent.remote.distanceExplorer
			ATMExamination:'Physical ATM',//EwayLocale.agent.remote.ATMExamination
			checkATM: 'Physical Again',//EwayLocale.agent.remote.checkATM
			ATMExamInfo: 'Physical ATM detail',//EwayLocale.agent.remote.ATMExamInfo
			cpuIdle: 'Free rate of CPU',//EwayLocale.agent.remote.cpuIdle
			memoryIdle: 'Free rate of memory',//EwayLocale.agent.remote.memoryIdle
			hardDiskIdle: 'Free rate of disk',//EwayLocale.agent.remote.hardDiskIdle
			uploadFile: 'Upload file',//EwayLocale.agent.remote.uploadFile
			rules:'Can not contain:\/?*":<>|',//EwayLocale.agent.remote.rules
			nowCreat:'Creating...',//EwayLocale.agent.remote.nowCreat
			nowPath: 'Current path',//EwayLocale.agent.remote.nowPath
			confirm: 'Save', //EwayLocale.agent.remote.confirm
			reset: 'Reset',//EwayLocale.agent.remote.reset
			back: 'Back',//EwayLocale.agent.remote.back
			prepareFile:'File wait for upload',//EwayLocale.agent.remote.prepareFile
			choseUploadFile:'Please choose file to upload',//EwayLocale.agent.remote.choseUploadFile
			nowUploadFile:'File uploading',//EwayLocale.agent.remote.nowUploadFile
			explorer: 'Browse...',//EwayLocale.agent.remote.explorer
			serverPath: 'Path of file in server',//EwayLocale.agent.remote.serverPath
			distanceSuccess:'Remote create successfully.',//EwayLocale.agent.remote.distanceSuccess
			distanceFailure:'Remote create failed.',//EwayLocale.agent.remote.distanceFailure
			confirmDelete:'Confirm delete',//EwayLocale.agent.remote.confirmDelete
			confirmExecute:'Confirm execute',//EwayLocale.agent.remote.confirmExecute
			choseDeleteFile:'Please choose file which you want to delete.',//EwayLocale.agent.remote.choseDeleteFile
			distanceExecuteSuccess:'Remote execute successfully.',//EwayLocale.agent.remote.distanceExecuteSuccess
			distanceExecuteFailure:'Remote execute failed.',//EwayLocale.agent.remote.distanceExecuteFailure
			choseExecuteFile:'Please choose file which you want to execute.',//EwayLocale.agent.remote.choseExecuteFile
			distanceExplorer:'Remote browse:',//EwayLocale.agent.remote.distanceExplorer
			distanceExplorerFailure:'Remote browse failed.',//EwayLocale.agent.remote.distanceExplorerFailure
			fileExist:'File already exist!',//EwayLocale.agent.remote.fileExist
			nowUploadFile:'File uploading......',//EwayLocale.agent.remote.nowUploadFile
			uploadSuccess:'Upload successfully.',//EwayLocale.agent.remote.uploadSuccess
			yes: "Continue transfer",//EwayLocale.agent.remote.yes
			no: "Override",//EwayLocale.agent.remote.no
			cancel: "Cancel", //EwayLocale.agent.remote.cancel
			choseFile:'No file selected for upload,please choose.',//EwayLocale.agent.remote.choseFile
			returnFailure:'Return fail.',//EwayLocale.agent.remote.returnFailure
			refreshFailure:'Refresh failed.',//EwayLocale.agent.remote.refreshFailure
			catalogExist:'The folder does not exist,please input again',//EwayLocale.agent.remote.catalogExist
			testBandWidth:'Test broadband error.', //EwayLocale.agent.remote.testBandWidth
			networkMaxSpeed:'Max net speed is',//EwayLocale.agent.remote.networkMaxSpeed
			minutes:'Second',//EwayLocale.agent.remote.minutes
			amount:'Like',//EwayLocale.agent.remote.amount
			specialLine:'Special line',//EwayLocale.agent.remote.specialLine
			bandWidth:'Broadband',//EwayLocale.agent.remote.bandWidth
			handle:'Processing......',//EwayLocale.agent.remote.handle
			offServer:'Lost connection from server.',//EwayLocale.agent.remote.offServer
			ATMCheck:'Executing physical of ATM...',//EwayLocale.agent.remote.ATMCheck
			excellent:'excellent',//EwayLocale.agent.remote.excellent
			fine:'good',//EwayLocale.agent.remote.fine
			middle:'middle',//EwayLocale.agent.remote.middle
			bad:'bad',//EwayLocale.agent.remote.bad
			point:'point',//EwayLocale.agent.remote.point
			checkFailure:'Execute physical of ATM failed,please try again.',//EwayLocale.agent.remote.checkFailure
			checkVersionInfo:'View version information',//EwayLocale.agent.remote.checkVersionInfo
			versionInfo:'The version information :',//EwayLocale.agent.remote.versionInfo
			mergeload:'List of download',//EwayLocale.agent.remote.mergeload
			mergeDownLoad:'Execute the task of download',//EwayLocale.agent.remote.mergeDownLoad
			clickAddLoadFile:'Add the list of download on click',//EwayLocale.agent.remote.clickAddLoadFile
			removeFile:'Remove the file from list',//EwayLocale.agent.remote.removeFile
			mustIncludeOneFile:'Must exit File to downLoad.',//EwayLocale.agent.remote.mustIncludeOneFile
			resetBackUp:'Click to backup the logs of this day.',//EwayLocale.agent.remote.resetBackUp
			backupAppLogsSuccess:'Backup atmc logs successfully.',//EwayLocale.agent.remote.backupAppLogsSuccess
			backupAppLogsFail:'backup atmc logs failed.',//EwayLocale.agent.remote.backupAppLogsFail
			backupLogSucList:' backup atmc logs list success.',//EwayLocale.agent.remote.backupLogSucList
			backupLogFailList:' backup atmc logs list failed.',//EwayLocale.agent.remote.backupLogFailList
			exitDownLoadFile:'add failed,the file have exited in the list of download.',//EwayLocale.agent.remote.exitDownLoadFile
			maxDownLoadFileSize:'add failed ,all the fileSize must less than 200M.',//EwayLocale.agent.remote.maxDownLoadFileSize
			addFileSuccess:'add file to list successfully.',//EwayLocale.agent.remote.addFileSuccess
		}
	}
});
