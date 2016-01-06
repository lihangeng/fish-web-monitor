Ext.apply(EwayLocale,{

	
	monitor:{
		summary:{
			title:'Status Overview',//EwayLocale.summary.title
			allSummary:'All',//EwayLocale.summary.allSummary
			appSummary:'ATMC',//EwayLocale.summary.appSummary
			modSummary:'Module',//EwayLocale.summary.modSummary
			boxSummary:'Cashbox',//EwayLocale.summary.boxSummary
			netSummary:'Network'//EwayLocale.summary.netSummary
		},
		deviceStatus:{
			Healthy:'Healthy',//EwayLocale.deviceStatus.Healthy
			Warning:'Warning',//EwayLocale.deviceStatus.Warning
			Fatal:'Fatal',//EwayLocale.deviceStatus.Fatal
			Unknown:'Unknown',//EwayLocale.deviceStatus.Unknown
			NoDevice:'No Device'//EwayLocale.deviceStatus.NoDevice
		},
		devMonitor:{
			title:'Status Monitor',//EwayLocale.devMonitor.title
			comboxStatus:{
				runStatus:'ATMC Status',//EwayLocale.comboxStatus.runStatus
				modStatus:'Module Status',//EwayLocale.comboxStatus.modStatus
				boxStatus:'Cashbox Status',//EwayLocale.comboxStatus.boxStatus
				netStatus:'Network Status'//EwayLocale.comboxStatus.netStatus
			},
			monitorState:'Filters',//EwayLocale.comboxStatus.monitorState
			showWay:'Show way',//EwayLocale.comboxStatus.showWay
			comboxShowWay:{
				summaryPattern:'Overview',//EwayLocale.comboxShowWay.summaryPattern
				matrixPattern:'Matrix',//EwayLocale.comboxShowWay.matrixPattern
				maxIconPattern:'Large Matrix',//EwayLocale.comboxShowWay.maxIconPattern
				listPattern:'List',//EwayLocale.comboxShowWay.listPattern
				boxPattern:'Cashbox'//EwayLocale.comboxShowWay.boxPattern
			},
			noData:'No data',//EwayLocale.comboxShowWay.noData
			retainCardCount:'Retained Card Amount',//EwayLocale.comboxShowWay.retainCardCount
			cash:{
				boxInitCount:'Initial Amount of Cashbox',//EwayLocale.cash.boxInitCount
				boxCurrentCount:'Current Amount of Cashbox',//EwayLocale.cash.boxCurrentCount
				cashboxLimit:'Cashbox Alarm Amount',//EwayLocale.cash.cashboxLimit
				initAmount:'Initial Amount',//EwayLocale.cash.initAmount
				amount:'Amount Left',//EwayLocale.cash.amount
				dispenseAmount:'Cash Out Amount',//EwayLocale.cash.dispenseAmount
				rejectAmount:'Reject Amount',//EwayLocale.cash.rejectAmount
				retractCount:'Retract Count',//EwayLocale.cash.retractCount
				minAmount:'Minimum Withdraw Amount',//EwayLocale.cash.minAmount
				boxId:'ID',//EwayLocale.cash.boxId
				type:'Type',//EwayLocale.cash.type
				boxStatus:'Status',//EwayLocale.cash.boxStatus
				initialCount:'Initial Amount',//EwayLocale.cash.initialCount
				cashInCount:'CashIn Count',//EwayLocale.cash.cashInCount
				currentCount:'Current Count',//EwayLocale.cash.currentCount
				noteValue:'Bank Note',//EwayLocale.cash.noteValue
				currency:'Currency',//EwayLocale.cash.currency
				boxDetail:'Cashbox detail',//EwayLocale.cash.boxDetail
				cimFull:'Full',//EwayLocale.cash.cimFull
				cdmEmpty:'Empty',//EwayLocale.cash.cdmEmpty
				cdmLow:'Low',//EwayLocale.cash.cdmLow
				low:'Low',//EwayLocale.cash.low
				empty:'Empty',//EwayLocale.cash.empty
				cimAFull:'Will Full',//EwayLocale.cash.cimAFull
				cashFault:'Fatal',//EwayLocale.cash.cashFault
				cashUnknow:'Unknown'//EwayLocale.cash.cashUnknow

			},
			modStateGraphic:'Module status graphic',//EwayLocale.cash.modStateGraphic
			modGraphic:'Module graphic',//EwayLocale.cash.modGraphic
			registerStatus:'Register Status',//EwayLocale.cash.registerStatus
			devModStatus:'Status of Modules',//EwayLocale.cash.devModStatus
			mod:{
				idc:'Card Reader',//EwayLocale.mod.idc
				jpr:'Journal Printer',//EwayLocale.mod.jpr
				cdm:'Dispenser',//EwayLocale.mod.cdm
				cim:'Deposit',//EwayLocale.mod.cim
				siu:'Sensors',//EwayLocale.mod.siu
				rpr:'Receipt Printer',//EwayLocale.mod.rpr
				pin:'PIN',//EwayLocale.mod.pin
				ttu:'TTU',//EwayLocale.mod.ttu
				isc:'ID Scanner',//EwayLocale.mod.isc
				icc:'Card Dispenser',//EwayLocale.mod.icc
				fgp:'Finger Printer',//EwayLocale.mod.fgp
				healthy:'Healthy',//EwayLocale.mod.healthy
				cam:'Camera',//EwayLocale.mod.cam
				bcr:'Barcode Reader',//EwayLocale.mod.bcr
				pbk:'Passbook Printer'//EwayLocale.mod.pbk
			},
			remote:{
				control:'Remote Control',//EwayLocale.remote.control
				screen:'Screenshot',//EwayLocale.remote.screen
				commandRet:'Command Ret',//EwayLocale.remote.commandRet
				log:'Fetch Journal Logs',//EwayLocale.remote.log
				net:'Check Network',//EwayLocale.remote.net
				softwareList:'Fetch Software List',//EwayLocale.remote.softwareList
				powerOff:'PowerOff',//EwayLocale.remote.powerOff
				closeWays:'Choose way of poweroff',//EwayLocale.remote.closeWays
				restart:'Reboot',//EwayLocale.remote.restart
				restartWay:'Choose way of reboot',//EwayLocale.remote.restartWay
				logicOpen:'Start Service',//EwayLocale.remote.logicOpen
				logicClose:'Stop Service',//EwayLocale.remote.logicClose
				remoteBrowser:'File Browse',//EwayLocale.remote.remoteBrowser
				processList:'Fetch Process',//EwayLocale.remote.processList
				screenCamera:'Screen recording',//EwayLocale.remote.screenCamera
				reset:'Force Reset',//EwayLocale.remote.reset
				remoteLook:'Fetch Application Info',//EwayLocale.remote.remoteLook
				remoteCheckATM:'Physical of ATM',//EwayLocale.remote.remoteCheckATM
				halfSer:'Half service',//EwayLocale.remote.halfSer
				healthy:'Healthy',//EwayLocale.remote.healthy
				staff:'Maintenance',//EwayLocale.remote.staff

				pFault:'ATMP Error',//EwayLocale.remote.pFault

				stop:'Stop',//EwayLocale.remote.stop
				manualStop:'Stop manual',//EwayLocale.remote.manualStop
				stopFault:'Pause serivce-module error',//EwayLocale.remote.stopFault
				stopCash:'Pause serivce-no cash',//EwayLocale.remote.stopCash
				pauseSer:'Pause serivce',//EwayLocale.remote.pauseSer
				pauseCash:'Pause cash',//EwayLocale.remote.pauseCash
				pauseSerUnknow:'Pause serivce-unkown',//EwayLocale.remote.pauseSerUnknow
				manaAndstaff:'Device Manager',//EwayLocale.remote.manaAndstaff
				screenFailed:'Connection refused.'//EwayLocale.remote.screenFailed
			},
			atmGroup:'Group',//EwayLocale.remote.atmGroup
			atmGroupTip:'GroupDetail',//EwayLocale.remote.atmGroupTip
			solution:'Solution suggest',//EwayLocale.remote.solution
			faultDescription:'Module error description',//EwayLocale.remote.faultDescription
			fastChoose:'Fast choose',//EwayLocale.remote.fastChoose
			init:'Initialization',//EwayLocale.remote.init
			accTrans:'Transaction',//EwayLocale.remote.accTrans
			factureStaff:'Manufacturer model',//EwayLocale.remote.factureStaff
			netHealthy:'Net healthy',//EwayLocale.remote.netHealthy
			netUnStable:'Net unstable',//EwayLocale.remote.netUnStable
			netFatal:'Net error',//EwayLocale.remote.netFatal
			filterManager: {//EwayLocale.remote.filterManager
				title: 'Filters Manager',//EwayLocale.remote.title
				add: 'Create Filter',//EwayLocale.remote.add
				update: 'Update Filter',//EwayLocale.remote.update
				filterForm: {//EwayLocale.remote.filterForm
					filterName: 'Filter Name'//EwayLocale.remote.filterName
				}
			}
		},
		business:{
			transaction:{
				card:'Card No.',//EwayLocale.transaction.card
				dateTime:'Time',//EwayLocale.transaction.dateTime
				transCode:'Transaction Type',//EwayLocale.transaction.transCode
				amt:'Amount',//EwayLocale.transaction.amt
				currency:'Currency',//EwayLocale.transaction.currency
				transId:'Trans ID.',//EwayLocale.transaction.transId
				amtfield:'Amount from',//EwayLocale.transaction.amtfield
				toNum:'to',//EwayLocale.transaction.toNum
				transContainer:'Time Scope',//EwayLocale.transaction.transContainer
				debitAccountOrCard:'Debit Card Number',//EwayLocale.transaction.debitAccountOrCard
				creditAccountOrCard:'Credit Card Number',//EwayLocale.transaction.creditAccountOrCard
				debitAccount:'Debit Card Number',//EwayLocale.transaction.debitAccount
				creditAccount:'Credit Card Number',//EwayLocale.transaction.creditAccount
				localRet:'Local Code',//EwayLocale.transaction.localRet
				hostRet:'Host Code',//EwayLocale.transaction.hostRet
				userName:'User Name',//EwayLocale.transaction.userName
				historyTransaction:{
					title:'Histroy Transaction '//EwayLocale.historyTransaction.title
				},
				transactionMonitor:{
					title:'Real-time Transaction',//EwayLocale.transactionMonitor.title
					begin:'Start',//EwayLocale.transactionMonitor.begin
					stop:'Stop',//EwayLocale.transactionMonitor.stop
					clear:'Clear Screen',//EwayLocale.transactionMonitor.clear
					scroll:'Scroll',//EwayLocale.transactionMonitor.scroll
					up:'up',//EwayLocale.transactionMonitor.up
					down:'down'//EwayLocale.transactionMonitor.down
				}
			},
			blackList:{
				title:'Black Card',//EwayLocale.blackList.title
				black:'BlackCard',//EwayLocale.blackList.black
				addBlack:'Add',//EwayLocale.blackList.addBlack
				cardBank:'Bank',//EwayLocale.blackList.cardBank
				importData:'Import',//EwayLocale.blackList.importData
				addDate:'Add date',//EwayLocale.blackList.addDate
				importTitle:'Bacth import',//EwayLocale.blackList.importTitle
				importFile:'Import file',//EwayLocale.blackList.importFile
				chooseFileRegex:'Please choose file to import,only .xls or .xlsx file is supported',//EwayLocale.blackList.chooseFileRegex
				fileRegex:'only .xls or .xlsx file is supported',//EwayLocale.blackList.fileRegex
				tempDownload:'Download temple',//EwayLocale.blackList.tempDownload
				importNow:'Import',//EwayLocale.blackList.importNow
				updateTitle:'Modify'//EwayLocale.blackList.updateTitle
			},
			card:{
				title:'Retained Card',//EwayLocale.card.title
				addTitle:'Add Retained Card Information',//EwayLocale.card.addTitle
				time:'Time',//EwayLocale.card.time
				reason:'Reason',//EwayLocale.card.reason
				destroy:'Destroy',//EwayLocale.card.destroy
				cardHolder:'Issuing Bank',//EwayLocale.card.cardHolder
				cardRegex:'Max length is ',//EwayLocale.card.cardRegex
				beginEndDate:'Begin date can not be later than end date,please choose again',//EwayLocale.card.beginEndDate
				orgBelongs:'Org belongs(Include process org)',//EwayLocale.card.orgBelongs
				beginTime:'Begin time ',//EwayLocale.card.beginTime
				endTime:'End time',//EwayLocale.card.endTime
				accGetCard:'Customer get card',//EwayLocale.card.accGetCard
				transferCard:'Turnover',//EwayLocale.card.transferCard
				processOrg:'Process org',//EwayLocale.card.processOrg
				type:'Retained Type',//EwayLocale.card.type
				manual:'Manually',//EwayLocale.card.manual
				auto:'Automatically',//EwayLocale.card.auto
				comboxStatus:{
					wait:'Wait for claimant',//EwayLocale.comboxStatus.wait
					received:'Received',//EwayLocale.comboxStatus.received
					destroy:'Destroy',//EwayLocale.comboxStatus.destroy
					bringed:'Bringed'//EwayLocale.comboxStatus.bringed
				},
				treatmentPeople:'Processor',//EwayLocale.comboxStatus.treatmentPeople
				treatmentTime:'Time',//EwayLocale.comboxStatus.treatmentTime
				customerName:'Customer name',//EwayLocale.comboxStatus.customerName
				customerPhone:'Telephone',//EwayLocale.comboxStatus.customerPhone
				customerPapers:'License code',//EwayLocale.comboxStatus.customerPapers
				processCard:'Process',//EwayLocale.comboxStatus.processCard
				destroyCard:'Destroy',//EwayLocale.comboxStatus.destroyCard
				exportData:'Export',//EwayLocale.comboxStatus.exportData
				paperType:'License type',//EwayLocale.comboxStatus.paperType
				paperCode:'License code',//EwayLocale.comboxStatus.paperCode
				idCard:'I.D card',//EwayLocale.comboxStatus.idCard
				accountPaper:'Register',//EwayLocale.comboxStatus.accountPaper
				drivePaper:'Drive License',//EwayLocale.comboxStatus.drivePaper
				passport:'Passport',//EwayLocale.comboxStatus.passport
				officer:'Officer License',//EwayLocale.comboxStatus.officer
				soldier:'Soldier License',//EwayLocale.comboxStatus.soldier
				busnessPaper:'Juridicalperson License',//EwayLocale.comboxStatus.busnessPaper
				busnessCode:'Juridicalperson code',//EwayLocale.comboxStatus.busnessCode
				taxPaper:'Taxregistration License',//EwayLocale.comboxStatus.taxPaper
				withDev:'With device'//EwayLocale.comboxStatus.withDev
			},


			cashInit:{
				titile:'Load Cash Info',//EwayLocale.cashInit.titile
				uuId:'Cash ID',//EwayLocale.cashInit.uuId
				date:'Date',//EwayLocale.cashInit.date
				amt:'Amount',//EwayLocale.cashInit.amt
				info:'Information',//EwayLocale.cashInit.info
				boxId:'CashboxID',//EwayLocale.cashInit.boxId
				boxCurrency:'Currency',//EwayLocale.cashInit.boxCurrency
				boxInitAmt:'Amount init',//EwayLocale.cashInit.boxInitAmt
				lastAmt:'Amount left'//EwayLocale.cashInit.lastAmt
			},
			settlement:{
				title:'Settlement Info',//EwayLocale.settlement.title
				deTitle:'Information',//EwayLocale.settlement.deTitle
				settleId:'SettleID',//EwayLocale.settlement.settleId
				uuId:'Cycle ID',//EwayLocale.settlement.uuId
				endAmt:'Endbox amount',//EwayLocale.settlement.endAmt
				endDate:'Date settlement',//EwayLocale.settlement.endDate
				cimNum:'Deposit count',//EwayLocale.settlement.cimNum
				cdmNum:'Draw count',//EwayLocale.settlement.cdmNum
				totalNum:'Total',//EwayLocale.settlement.totalNum
				leftDate:'Date settlement',//EwayLocale.settlement.leftDate
				cimAmt:'Deposit amount',//EwayLocale.settlement.cimAmt
				cdmAmt:'Draw amount',//EwayLocale.settlement.cdmAmt
				tranAmt:'Total amount'//EwayLocale.settlement.tranAmt
			}
		/*}*///EwayLocale.settlement./*}*/
		},
		remoteCommand:{
			titile:'Remote Command Info',//EwayLocale.remoteCommand.titile
			form:{
				date:'Date'//EwayLocale.form.date
			},
			grid:{
				commandType:'Type',//EwayLocale.grid.commandType
				commandResult:'Result',//EwayLocale.grid.commandResult
				datetime:'Date',//EwayLocale.grid.datetime
				handlePerson:'Personnel'//EwayLocale.grid.handlePerson
			}
		}
	},

	
	agent:{
		remote:{
			screen:{
				message:'info', //EwayLocale.screen.message
				startcustom:'Start recording front-screen of customer', //EwayLocale.screen.startcustom
				stopcustom:'Stop recording front-screen of customer',//EwayLocale.screen.stopcustom
				startadmin:'Start recording front-screen of manager',//EwayLocale.screen.startadmin
				stopadmin: 'Stop recording front-screen of manager',//EwayLocale.screen.stopadmin
				startadvertise: 'Start recording advertisement-screen',//EwayLocale.screen.startadvertise
				stopadvertise: 'Stop recording advertisement-screen',//EwayLocale.screen.stopadvertise
				startCameraDate: 'Recording start time',//EwayLocale.screen.startCameraDate
				stopCameraDate: 'Recording end time',//EwayLocale.screen.stopCameraDate
				monitorType: 'Screen type', //EwayLocale.screen.monitorType
				fileNameClient: 'File name',//EwayLocale.screen.fileNameClient
				nowCamera:'Recording...',//EwayLocale.screen.nowCamera
				finishCamera:'Recording finish',//EwayLocale.screen.finishCamera
				videoLoad:'Downloading the video file to the server...',//EwayLocale.screen.videoLoad
				stopManage:'Automatic stopped ,please contact the manager!',//EwayLocale.screen.stopManage
				manage: 'Operater',	//EwayLocale.screen.manage
				loading:'DownLoad',//EwayLocale.screen.loading
				screenCamera: 'Screen recording'//EwayLocale.screen.screenCamera
			},
			discInfo: 'Disk info', //EwayLocale.screen.discInfo
			discName: 'Name',//EwayLocale.screen.discName
			fileSys: 'File system',//EwayLocale.screen.fileSys
			totalSize: 'Total Size',//EwayLocale.screen.totalSize
			freeSize: 'Free Space',//EwayLocale.screen.freeSize
			networkInfo:'Net connection info',//EwayLocale.screen.networkInfo
			conenctRate: 'Connection speed',//EwayLocale.screen.conenctRate
			receivedByte: 'Bytes received',//EwayLocale.screen.receivedByte
			sendByte: 'Bytes sended',//EwayLocale.screen.sendByte
			loadData: 'Data loading,please wait...',//EwayLocale.screen.loadData
			refresh: 'Refresh',//EwayLocale.screen.refresh
			name: 'Name',//EwayLocale.screen.name
			format: 'File system',//EwayLocale.screen.format
			totalSize: 'Total Size',//EwayLocale.screen.totalSize
			freeSize: 'Free Space',//EwayLocale.screen.freeSize
			refreshFailure:'Refresh failed!',//EwayLocale.screen.refreshFailure
			back:'Back',//EwayLocale.screen.back
			upload: 'Upload',//EwayLocale.screen.upload
			Mkdir: 'New folder',//EwayLocale.screen.Mkdir
			MkFile: 'New file',//EwayLocale.screen.MkFile
			MKcatalog:'New folder',//EwayLocale.screen.MKcatalog
			catalogName:'Folder name',//EwayLocale.screen.catalogName
			remove: 'Delete',//EwayLocale.screen.remove
			execute: 'Execute',//EwayLocale.screen.execute
			path: 'Path',//EwayLocale.screen.path
			search: 'Search',//EwayLocale.screen.search
			size: 'Size',//EwayLocale.screen.size
			fileSize:'File size：',//EwayLocale.screen.fileSize
			lastTime: 'Date modified',//EwayLocale.screen.lastTime
			clickLoadFile:'Click to download this file',//EwayLocale.screen.clickLoadFile
			loadFileSize:'Max size of download file is 200M',//EwayLocale.screen.loadFileSize
			nowLoadFile:'File downloading...',//EwayLocale.screen.nowLoadFile
			judgeLoad: 'Broken-point Continuingly-transferring?',//EwayLocale.screen.judgeLoad
			loadFailure:'Download failed!',//EwayLocale.screen.loadFailure
			number: 'ID',//EwayLocale.screen.number
			programName: 'Program Name',//EwayLocale.screen.programName
			version: 'Version Num',//EwayLocale.screen.version
			publisher: 'Publishers',//EwayLocale.screen.publisher
			diskUsed: 'Disk usage',//EwayLocale.screen.diskUsed
			softwayList: 'List of Software',//EwayLocale.screen.softwayList
			networkInfo: 'Network info',//EwayLocale.screen.networkInfo
			networkLinkStatus: 'Network connect status',//EwayLocale.screen.networkLinkStatus
			send: 'Sended',//EwayLocale.screen.send
			receive: 'Received',//EwayLocale.screen.receive
			bite: 'byte:',//EwayLocale.screen.bite
			speed: 'Speed(Mbps):',//EwayLocale.screen.speed
			bandWidth: 'Broadband speed test',//EwayLocale.screen.bandWidth
			unit: 'Unit:MB',//EwayLocale.screen.unit
			againTest: 'Test again',//EwayLocale.screen.againTest
			impressionName: 'Image Name',//EwayLocale.screen.impressionName
			userName: 'User Name',//EwayLocale.screen.userName
			memoryRate: 'Memory',//EwayLocale.screen.memoryRate
			systemProgressInfo:'System process info',//EwayLocale.screen.systemProgressInfo
			screenShotTime: 'Screenshot time',//EwayLocale.screen.screenShotTime
			distanceScreen: 'Remote screenshot',//EwayLocale.screen.distanceScreen
			distanceExplorer: 'Remote browse',//EwayLocale.screen.distanceExplorer
			ATMExamination:'Physical ATM',//EwayLocale.screen.ATMExamination
			checkATM: 'Physical Again',//EwayLocale.screen.checkATM
			ATMExamInfo: 'Physical ATM detail',//EwayLocale.screen.ATMExamInfo
			cpuIdle: 'Free rate of CPU',//EwayLocale.screen.cpuIdle
			memoryIdle: 'Free rate of memory',//EwayLocale.screen.memoryIdle
			hardDiskIdle: 'Free rate of disk',//EwayLocale.screen.hardDiskIdle
			uploadFile: 'Upload file',//EwayLocale.screen.uploadFile
			rules:'Can not contain:\/?*":<>|',//EwayLocale.screen.rules
			nowCreat:'Creating...',//EwayLocale.screen.nowCreat
			nowPath: 'Current path',//EwayLocale.screen.nowPath
			confirm: 'Save', //EwayLocale.screen.confirm
			reset: 'Reset',//EwayLocale.screen.reset
			back: 'Back',//EwayLocale.screen.back
			prepareFile:'File wait for upload',//EwayLocale.screen.prepareFile
			choseUploadFile:'Please choose file to upload',//EwayLocale.screen.choseUploadFile
			nowUploadFile:'File uploading',//EwayLocale.screen.nowUploadFile
			explorer: 'Browse...',//EwayLocale.screen.explorer
			serverPath: 'Path of file in server',//EwayLocale.screen.serverPath
			distanceSuccess:'Remote create successfully.',//EwayLocale.screen.distanceSuccess
			distanceFailure:'Remote create failed.',//EwayLocale.screen.distanceFailure
			confirmDelete:'Confirm delete',//EwayLocale.screen.confirmDelete
			confirmExecute:'Confirm execute',//EwayLocale.screen.confirmExecute
			choseDeleteFile:'Please choose file which you want to delete.',//EwayLocale.screen.choseDeleteFile
			distanceExecuteSuccess:'Remote execute successfully.',//EwayLocale.screen.distanceExecuteSuccess
			distanceExecuteFailure:'Remote execute failed.',//EwayLocale.screen.distanceExecuteFailure
			choseExecuteFile:'Please choose file which you want to execute.',//EwayLocale.screen.choseExecuteFile
			distanceExplorer:'Remote browse:',//EwayLocale.screen.distanceExplorer
			distanceExplorerFailure:'Remote browse failed.',//EwayLocale.screen.distanceExplorerFailure
			fileExist:'File already exist!',//EwayLocale.screen.fileExist
			nowUploadFile:'File uploading......',//EwayLocale.screen.nowUploadFile
			uploadSuccess:'Upload successfully.',//EwayLocale.screen.uploadSuccess
			yes: "Continue transfer",//EwayLocale.screen.yes
			no: "Override",//EwayLocale.screen.no
			cancel: "Cancel", //EwayLocale.screen.cancel
			choseFile:'No file selected for upload,please choose.',//EwayLocale.screen.choseFile
			returnFailure:'Return fail.',//EwayLocale.screen.returnFailure
			refreshFailure:'Refresh failed.',//EwayLocale.screen.refreshFailure
			catalogExist:'The folder does not exist,please input again',//EwayLocale.screen.catalogExist
			testBandWidth:'Test broadband error.', //EwayLocale.screen.testBandWidth
			networkMaxSpeed:'Max net speed is',//EwayLocale.screen.networkMaxSpeed
			minutes:'Second',//EwayLocale.screen.minutes
			amount:'Like',//EwayLocale.screen.amount
			specialLine:'Special line',//EwayLocale.screen.specialLine
			bandWidth:'Broadband',//EwayLocale.screen.bandWidth
			handle:'Processing......',//EwayLocale.screen.handle
			offServer:'Lost connection from server.',//EwayLocale.screen.offServer
			submitingWaiting:'Being submitted, please wait...',//EwayLocale.screen.submitingWaiting
			ATMCheck:'Executing physical of ATM...',//EwayLocale.screen.ATMCheck
			excellent:'excellent',//EwayLocale.screen.excellent
			fine:'good',//EwayLocale.screen.fine
			middle:'middle',//EwayLocale.screen.middle
			bad:'bad',//EwayLocale.screen.bad
			point:'point',//EwayLocale.screen.point
			checkFailure:'Execute physical of ATM failed,please try again.',//EwayLocale.screen.checkFailure
			checkVersionInfo:'View version information',//EwayLocale.screen.checkVersionInfo
			versionInfo:'The version information :',//EwayLocale.screen.versionInfo
			ATMCVersion:'Version of ATMC application',//EwayLocale.screen.ATMCVersion
			monitorVersion: 'Version of monintor client',//EwayLocale.screen.monitorVersion
			mergeload:'List of download',//EwayLocale.screen.mergeload
			mergeDownLoad:'Execute the task of download',//EwayLocale.screen.mergeDownLoad
			clickAddLoadFile:'Add the list of download on click',//EwayLocale.screen.clickAddLoadFile
			removeFile:'Remove the file from list',//EwayLocale.screen.removeFile
			mustIncludeOneFile:'Must exit File to downLoad.',//EwayLocale.screen.mustIncludeOneFile
			resetBackUp:'Click to backup the logs of this day.',//EwayLocale.screen.resetBackUp
			backupAppLogsSuccess:'Backup atmc logs successfully.',//EwayLocale.screen.backupAppLogsSuccess
			backupAppLogsFail:'backup atmc logs failed.',//EwayLocale.screen.backupAppLogsFail
			backupLogSucList:' backup atmc logs list success.',//EwayLocale.screen.backupLogSucList
			backupLogFailList:' backup atmc logs list failed.',//EwayLocale.screen.backupLogFailList
			exitDownLoadFile:'add failed,the file have exited in the list of download.',//EwayLocale.screen.exitDownLoadFile
			maxDownLoadFileSize:'add failed ,all the fileSize must less than 200M.',//EwayLocale.screen.maxDownLoadFileSize
            removeSuccess:'Delete successfully.',//EwayLocale.screen.removeSuccess
			addFileSuccess:'add file to list successfully.',//EwayLocale.screen.addFileSuccess
		}
	}
});
