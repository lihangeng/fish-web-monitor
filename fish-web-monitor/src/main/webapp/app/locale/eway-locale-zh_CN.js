Ext.apply(EwayLocale,{
	addSuccess : '增加成功.',//EwayLocale.addSuccess
	updateSuccess : '更改成功.',//EwayLocale.updateSuccess
	deleteSuccess : '删除成功.',//EwayLocale.deleteSuccess
	choiceUpdateMsg :'请选择您要更改的记录.',//EwayLocale.choiceUpdateMsg
	choiceDeleteMsg :'请选择您要删除的记录.',//EwayLocale.choiceDeleteMsg

	myTable:'我的工作台',//EwayLocale.myTable
	ATMV:'自助设备监控系统',//EwayLocale.ATMV
	welcome:'欢迎你,',//EwayLocale.welcome
	personalConf:'个人设置',//EwayLocale.personalConf
	systemHelp:'系统帮助',//EwayLocale.systemHelp
	exitSystem:'退出系统',//EwayLocale.exitSystem
	msg:{
		perviewFailForText:'预览失败:不支持文字滚动广告和公告的预览.',//EwayLocale.msg.perviewFailForText
		perviewFailNoResource:'预览失败:此广告没有配置广告资源.',//EwayLocale.msg.perviewFailNoResource
		choseResToPerview:'请选择您要预览的广告.',//EwayLocale.msg.choseResToPerview
		noAdvertResAtTheResolution:'分辨率下没有配置广告资源.',//EwayLocale.msg.noAdvertResAtTheResolution
		chooseAdvert:'请选择一条广告.',//EwayLocale.msg.chooseAdvert
		chooseOneDevice:'请选择一台设备.',//EwayLocale.msg.chooseOneDevice
		downLoadedAdvertCantDelete:'删除失败:不能删除"已下发"和"等待下发"状态的广告.',//EwayLocale.msg.downLoadedAdvertCantDelete
		chooseAdvertToDelete:'请选择您要删除的广告.',//EwayLocale.msg.chooseAdvertToDelete
		chooseAdvertToDownload:'请选择您要删除的广告.',//EwayLocale.msg.chooseAdvertToDownload
		generalVersionFailForDownloaded:'生成版本文件失败:"已下发"状态的广告不能再生成版本信息.',//EwayLocale.msg.generalVersionFailForDownloaded
		generalVersionSuccess:"生成版本文件成功.",//EwayLocale.msg.generalVersionSuccess
		createSuccess:"创建成功.",//EwayLocale.msg.createSuccess
		mustHaveOneResource:'至少包含一个广告资源!',//EwayLocale.msg.mustHaveOneResource
		saveFail:'保存失败',//EwayLocale.msg.saveFail
		saveFailPleaseRefresh:'保存失败，请刷新重试',//EwayLocale.msg.saveFailPleaseRefresh
		saveFileSizeMaxFail:'保存失败:超过最大单个文件大小限制（最大30M）',//EwayLocale.msg.saveFileSizeMaxFail
		saveFileCommunicationFail:'保存失败:与服务器通讯失败',//EwayLocale.msg.saveFileCommunicationFail
		chooseDevice:'请选择设备.',//EwayLocale.msg.chooseDevice
		downloadFailForNoVersion:"下发版本文件失败:还没有生成版本文件或者版本文件丢失,请先生成版本文件.",//EwayLocale.msg.downloadFailForNoVersion
		saveSuccess:'保存成功！',//EwayLocale.msg.saveSuccess

		removeSuccess:'解除成功',//EwayLocale.msg.removeSuccess
		removeFail:'解除失败',//EwayLocale.msg.removeFail
		someStripRemoveFailePleaseRefresh:'条解除失败，请刷新后重试！',//EwayLocale.msg.someStripRemoveFailePleaseRefresh
		versionDownloaded:'不能删除"等待下发"和"已下发"状态的版本.',//EwayLocale.msg.versionDownloaded
		selectVersionToDelete:'请选择您要删除的版本.',//EwayLocale.msg.selectVersionToDelete
		communicationFail:'增加失败:与服务器通讯失败.',//EwayLocale.msg.communicationFail
		sameVersionNoFail:'增加失败:已经存在相同的版本号.',//EwayLocale.msg.sameVersionNoFail
		fileSizeMaxFail:'增加失败:超过最大文件大小限制（最大200M）',//EwayLocale.msg.fileSizeMaxFail
		fileUnzipFail:'增加失败:上传的压缩包不能正常解压',//EwayLocale.msg.fileUnzipFail
		addFileFail:'增加失败:',//EwayLocale.msg.addFileFail
		mustSelectDevice:'请至少选择一个设备.',//EwayLocale.msg.mustSelectDevice
		selectVersionRecord:'请选择您要下发的版本.',//EwayLocale.msg.selectVersionRecord
		missVersionFile:"版本文件丢失,暂不能对版本进行下发控制.",//EwayLocale.msg.missVersionFile
		mustSelectPerson:"请必须选择人员"//EwayLocale.msg.mustSelectPerson
	},
	confirm:{
		titleSure:'确认',//EwayLocale.confirm.titleSure
		todoDelete:'是否删除该记录?',//EwayLocale.confirm.todoDelete
		title:'提示',//EwayLocale.confirm.title
		withoutNumTaskConfirmInfo:'作业保存成功,是否跳转到"分发监控"页面?',//EwayLocale.confirm.withoutNumTaskConfirmInfo
		timeout:'会话超时，3秒后自动跳转到登录页面',//EwayLocale.confirm.timeout
		
		taskConfirmInfo0:'第',//EwayLocale.confirm.taskConfirmInfo0
		taskConfirmInfo1:'次作业保存成功,是否跳转到"分发监控"页面?'//EwayLocale.confirm.taskConfirmInfo1
		
	},
	button:{
		search:'查询',//EwayLocale.button.search
		add : '增加',//EwayLocale.button.add
		update:'更改',//EwayLocale.button.update
		remove:'删除',//EwayLocale.button.remove
		refresh:'刷新',//EwayLocale.button.refresh
		reset:'重置',//EwayLocale.button.reset
		back:'返回',//EwayLocale.button.back
		apply:'应用',//EwayLocale.button.apply
		link:'关联',//EwayLocale.button.link
		unlink:'解除',//EwayLocale.button.unlink
		
		deepQuery:'深度查询',//EwayLocale.button.deepQuery
		bankOrgMove:'组织迁移',//EwayLocale.button.bankOrgMove
		bankOrgAdmin:'管理员',//EwayLocale.button.bankOrgAdmin
		
		bankPerlink:'绑定设备',//EwayLocale.button.bankPerlink
		sure:'确定',//EwayLocale.button.sure
		confirm:'确认',//EwayLocale.button.confirm
		cancle:'取消',//EwayLocale.button.cancle
		choose:'选择',//EwayLocale.button.choose
		pause:'暂停',//EwayLocale.button.pause

		exported:'导出',//EwayLocale.button.exported
		select:'选择',//EwayLocale.button.select
		info :'详细信息',//EwayLocale.button.info
		move:'移机',//EwayLocale.button.move
		exportXLS:'导出XLS',//EwayLocale.button.exportXLS
		exportPDF:'导出PDF',//EwayLocale.button.exportPDF
		massExport:'批量导入',//EwayLocale.button.massExport
		download:'下发',//EwayLocale.button.download
		downloadToolTip:'配置下发作业',//EwayLocale.button.downloadToolTip
		save:'保&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存',//EwayLocale.button.save
		servicePlan:'开机方案',//EwayLocale.button.servicePlan
		adminBtn:'管理员',//EwayLocale.button.adminBtn
		personM:'厂商管理员',//EwayLocale.button.personM
		personTM:'管机员',//EwayLocale.button.personTM
		orgAdmin:'机构管理员'//EwayLocale.button.orgAdmin

	},
	
	refs:{
		selectAll:'全部',//EwayLocale.refs.selectAll
		orgName:'机构',//EwayLocale.refs.orgName
		terminalId:'设备编号',//EwayLocale.refs.terminalId
		ip:'IP地址',//EwayLocale.refs.ip
		devType:"设备型号"//EwayLocale.refs.devType
	},
	
	tip:{
		search :{
			warn:'查询条件存在错误项.',//EwayLocale.search.warn
			record:'请选择您要查看的记录.'//EwayLocale.search.record
		},
		update:{
			one:'只能选择一条记录更改.',//EwayLocale.update.one
			two:'此条记录不能被更改.'//EwayLocale.update.two
		},
		remove :{
			none:'请选择您要删除的记录.',//EwayLocale.remove.none
			one:'只能选择一条记录删除',//EwayLocale.remove.one
			confirm:{
				title:'请确认',//EwayLocale.confirm.title
				info:'是否删除该记录?'//EwayLocale.confirm.info
			},
			error:'删除失败:'//EwayLocale.confirm.error
		},
		own:{
			have:'有',//EwayLocale.own.have
			nothing:'无'//EwayLocale.own.nothing
		},
		right:{
			yes:'是',//EwayLocale.right.yes
			no:'否'//EwayLocale.right.no
		},
		add:{
			error:'新增失败'//EwayLocale.add.error
		},
		success:'成功.',//EwayLocale.add.success
		fail:'失败:',//EwayLocale.add.fail
		phone:'请输入正确的电话号码',//EwayLocale.add.phone
		remind:'提示',//EwayLocale.add.remind
		formatPageBfMsg: '每页显示',//EwayLocale.add.formatPageBfMsg
		formatPageAfMsg: '条',//EwayLocale.add.formatPageAfMsg

		unCertain:'未知',//EwayLocale.add.unCertain
		searchOfNoLegal:'查询项中存在不合法的输入,不能提交.',//EwayLocale.add.searchOfNoLegal
		choseExportDevInfo:'请选择要导出信息的设备',//EwayLocale.add.choseExportDevInfo
		nowLink:'正在连接......',//EwayLocale.add.nowLink
		linkFailure:'连接失败.',//EwayLocale.add.linkFailure
		inputError:'输入有误.',//EwayLocale.add.inputError
		numberExist:'此编号已经存在,请重新输入.',//EwayLocale.add.numberExist
		isConfirmRemove:'删除分组,关联关系也被删除,是否真的要删除指定分组?',//EwayLocale.add.isConfirmRemove
		noGroupInfo:'没有组信息,无法查询.',//EwayLocale.add.noGroupInfo
		selectAdd:'请选择您要增加的记录.',//EwayLocale.add.selectAdd
		continueAdd:'添加成功,是否继续向组内添加设备?',//EwayLocale.add.continueAdd
		addFail:'添加失败.',//EwayLocale.add.addFail
		isRemoveDev:'是否从该组移除该设备?',//EwayLocale.add.isRemoveDev
		removeFail:'移除失败.',//EwayLocale.add.removeFail
		selectRemoveGroup:'请选择您要移除的设备所在组.',//EwayLocale.add.selectRemoveGroup
		selectRemoveDev:'请选择您要移除的设备.',//EwayLocale.add.selectRemoveDev
		selectMoveDev:'请选择要移动的设备.',//EwayLocale.add.selectMoveDev
		moveSuc:'移机成功.',//EwayLocale.add.moveSuc
		dateReSelect:'开始时间不能大于结束日期,请重新选择',//EwayLocale.add.dateReSelect
		selectPlan:'请选择您应用的方案.',//EwayLocale.add.selectPlan
		removeFail:'解除失败.',//EwayLocale.add.removeFail
		selectRemoveDev:'请选择要解除的设备.',//EwayLocale.add.selectRemoveDev
		relatedFail:'关联失败.',//EwayLocale.add.relatedFail
		selectRelatedDev:'请选择要关联的设备.',//EwayLocale.add.selectRelatedDev
		planNoUpdate:'该方案已经执行,不可修改.',//EwayLocale.add.planNoUpdate
		planNoRemove:'该方案已经执行,不可删除.',//EwayLocale.add.planNoRemove
		exportFiles: '请选择导入文件,只支持.xls和.xlsx格式的文件',//EwayLocale.add.exportFiles
		noChange:'没有更改数据,请更改后再点击确定!',//EwayLocale.add.noChange
		operateSuc:'操作成功',//EwayLocale.add.operateSuc
		operateWrong:'操作成功',//EwayLocale.add.operateWrong
		deleteOne:'一次只能删除一条记录.',//EwayLocale.add.deleteOne
		chooseRecord:'请选择您要关联的记录.',//EwayLocale.add.chooseRecord
		choosePlan:'请选择您要查看的方案',//EwayLocale.add.choosePlan
		planDetail:'方案详情',//EwayLocale.add.planDetail
		planDate:'方案详情(日期)',//EwayLocale.add.planDate
		planWeek:'方案详情(星期)',//EwayLocale.add.planWeek
		planNoConf:'该方案无详细设置！',//EwayLocale.add.planNoConf
		chooseRelatedDev:'请选择您要关联的设备！',//EwayLocale.add.chooseRelatedDev
		devRelatedPlan:'设备已关联开机方案！',//EwayLocale.add.devRelatedPlan
		
		tips:'提示',//EwayLocale.add.tips
		input:'请正确输入',//EwayLocale.add.input
		roleName:'由字母‘a-z’或‘A-Z’、数字‘0-9’或中文，最多可输入 40位',//EwayLocale.add.roleName
		notNull:'不能为空',//EwayLocale.add.notNull
		cardNo:'只能输入13到19个数字',//EwayLocale.add.cardNo
		blankBegin:'不能以空格开头',//EwayLocale.add.blankBegin
		passwd:{
			confirmPasswd:'是否确认密码重置？',//EwayLocale.passwd.confirmPasswd
			resetPasswding:'正在重置密码......',//EwayLocale.passwd.resetPasswding
			resetPasswdFail:'密码重置失败！'//EwayLocale.passwd.resetPasswdFail
		},
		operateDate:{
			operateDateBegin:'操作日期开始于',//EwayLocale.operateDate.operateDateBegin
			operateDateEnd:'操作日期结束于'//EwayLocale.operateDate.operateDateEnd
		},
		bankOrg:{
			manager:{
				set:{
					chooseOrg:'请选择您要设置的机构.',//EwayLocale.set.chooseOrg
					managerSuccess:'设置管理员成功.',//EwayLocale.set.managerSuccess
					managerFail:'设置管理员失败.',//EwayLocale.set.managerFail
					chooseOneManager:'请至少选择一条记录'//EwayLocale.set.chooseOneManager
				},
				remove:{
					confirm:'是否删除该机构管理员?',//EwayLocale.remove.confirm
					reChoose:'您未选择您要设置的机构或该机构下没有管理员，请重新选择.',//EwayLocale.remove.reChoose
					delSuccess:'删除管理员成功.',//EwayLocale.remove.delSuccess
					delFail:'删除管理员失败.'//EwayLocale.remove.delFail
				}
			},
			orgEligible:'符合条件的机构',//EwayLocale.remove.orgEligible
			downGradeOrg:'的直接下级机构',//EwayLocale.remove.downGradeOrg
			move:{
				chooseOrg:'请选择您要迁移的组织.',//EwayLocale.move.chooseOrg
				moveSuccess:'组织迁移成功.'//EwayLocale.move.moveSuccess
			}
		},
		bankPer:{
			allPersonInfo:'所有银行人员信息',//EwayLocale.bankPer.allPersonInfo
			link:{
				linkPerson:'请选择您关联的人员',//EwayLocale.link.linkPerson
				linkBankPerson:'请选择您关联的人员',//EwayLocale.link.linkBankPerson
				unLinkPersonFail:'关联失败.',//EwayLocale.link.unLinkPersonFail
				unlinkDev:'请选择要解除的设备.',//EwayLocale.link.unlinkDev
				linkDev:'请选择要关联的设备.',//EwayLocale.link.linkDev
				unLinkDevFail:'解除失败.'//EwayLocale.link.unLinkDevFail
			},
			personEligible:'符合条件的人员',//EwayLocale.link.personEligible
			downGradePer:'以及其下属机构下的人员信息',//EwayLocale.link.downGradePer
			personBelongs:'下的人员信息'//EwayLocale.link.personBelongs
		},
		serviceOrg:{
			chooseOrg:'请选择您要设置的维护商.',//EwayLocale.serviceOrg.chooseOrg
			remove:{
				reChoose:'您未选择要设置的维护商或该维护商下没有管理员,请重新选择.'//EwayLocale.remove.reChoose
			}
		},
		servicePer:{
			allSerPer:'所有维护商人员信息'//EwayLocale.servicePer.allSerPer
		},
		user:{
			add:{
				createAcc:'请选择您要创建的账号的人员',//EwayLocale.add.createAcc
				createSuccess:'创建成功,新建账户',//EwayLocale.add.createSuccess
				initPasswd:'初始密码为: 888888'//EwayLocale.add.initPasswd
			},
			remove:{
				failRoot:'删除失败:系统管理员用户,无法删除.',//EwayLocale.remove.failRoot
				confirm:'是否删除该记录:删除用户会删除该用户的日志信息.',//EwayLocale.remove.confirm
				fail:'删除失败:无法删除角色,请重新操作.'//EwayLocale.remove.fail
			},
			update:{
				fail:'更改失败:记录不存在,请刷新后操作.'//EwayLocale.update.fail
			},
			move:{
				choose:'请选择需要移动的记录.'//EwayLocale.move.choose

			}
		},
		business:{
			transaction:{
				transactionMonitor:{
					beginMonitor:'请先停止监控后再输入条件，按开始监控按钮即可进行条件监控！',//EwayLocale.transactionMonitor.beginMonitor
					input:'设备号、对方账号、客户账号至少输入一个.',//EwayLocale.transactionMonitor.input
					left:'离开'//EwayLocale.transactionMonitor.left
				},
				historyTransaction:{
					input:'查询必须输入设备号.'//EwayLocale.historyTransaction.input


				}
			},
			blackList:{
				importing:'正在导入文件',//EwayLocale.blackList.importing
				importSuccess:'导入黑名单卡文件成功'//EwayLocale.blackList.importSuccess
			},
			card:{
				returnFail:'移交失败:后台处理出错.',//EwayLocale.card.returnFail
				choose:'请选择您移交的卡片.',//EwayLocale.card.choose
				returnSucess:'移交成功.',//EwayLocale.card.returnSucess
				chooseBack:'请选择要领取的卡片',//EwayLocale.card.chooseBack
				getSuccess:'领取成功.',//EwayLocale.card.getSuccess
				destroyConfirm:'是否销毁这张卡片?',//EwayLocale.card.destroyConfirm
				destroySuccess:'销毁成功',//EwayLocale.card.destroySuccess
				chooseDestroy:'请选择要销毁的卡片.',//EwayLocale.card.chooseDestroy
				idCardRegex:'请输入正确的身份证号码,15位或者18位',//EwayLocale.card.idCardRegex
				accountRegex:'请正确输入户口本上的身份证号码,15位或者18位',//EwayLocale.card.accountRegex
				driveCardRegex:'请正确输入驾驶证上的身份证号码,15位或者18位',//EwayLocale.card.driveCardRegex
				passportRegex:'请正确输入护照上的身份证号码,15位或者18位',//EwayLocale.card.passportRegex
				soldierRegex:'请输入正确的军官证号码,1-5位汉字和1-10位数字',//EwayLocale.card.soldierRegex
				soldierCard:'请输入正确的士兵证,7-8位数字',//EwayLocale.card.soldierCard
				busnessPaper:'请输入正确的法人营业执照,12-15位数字',//EwayLocale.card.busnessPaper
				busnessCode:'请输入正确的法人代码证,15位数字',//EwayLocale.card.busnessCode
				taxPaper:'请输入正确的税务登记证,15位数字'//EwayLocale.card.taxPaper

			},
			device:{
				getCashInfoFail:'获取钞箱信息失败',//EwayLocale.device.getCashInfoFail
				operating:'正在执行',//EwayLocale.device.operating
				reviewFail:'查看失败.',//EwayLocale.device.reviewFail
				logLoadConfirm:'应用日志提取成功,是否下载?',//EwayLocale.device.logLoadConfirm
				logPullFail:'提取应用电子日志失败',//EwayLocale.device.logPullFail
				logFail:'log处理失败.',//EwayLocale.device.logFail
				linkServerFail:'服务器连接失败.',//EwayLocale.device.linkServerFail
				logicOpen:'确认要执行开启服务命令',//EwayLocale.device.logicOpen
				openSuccess:'执行开启服务命令成功.',//EwayLocale.device.openSuccess
				openFail:'执行开启服务命令失败.',//EwayLocale.device.openFail
				closeConfirm:'确认要执行暂停服务命令?',//EwayLocale.device.closeConfirm
				closeServiceSuccess:'执行暂停服务命令成功.',//EwayLocale.device.closeServiceSuccess
				closeServiceFail:'执行暂停服务命令失败.',//EwayLocale.device.closeServiceFail
				closeNormal:'正常关机',//EwayLocale.device.closeNormal
				closeComfirm:'确认要执行正常关机命令么,可能会存在风险?',//EwayLocale.device.closeComfirm
				closing:'正在执行正常关机',//EwayLocale.device.closing
				closeSucess:'正常关机成功.',//EwayLocale.device.closeSucess
				closeFail:'正常关机失败.',//EwayLocale.device.closeFail
				closeSentFail:'正常关机命令发送失败.',//EwayLocale.device.closeSentFail
				forceClose:'强制关机',//EwayLocale.device.forceClose
				forceCloseComfirm:'确认要执行强制关机命令么,可能会存在严重风险?',//EwayLocale.device.forceCloseComfirm
				forceClosing:'正在执行强制关机',//EwayLocale.device.forceClosing
				forceCloseSucess:'强制关机成功.',//EwayLocale.device.forceCloseSucess
				forceCloseFail:'强制关机失败.',//EwayLocale.device.forceCloseFail
				ForceCloseSentFail:'强制关机命令发送失败.',//EwayLocale.device.ForceCloseSentFail
				reboot:'正常重启',//EwayLocale.device.reboot
				rebootConfirm:'确认要执行正常重启命令么,可能会存在风险?',//EwayLocale.device.rebootConfirm
				rebooting:'正在执行正常重启',//EwayLocale.device.rebooting
				rebootSucess:'正常重启成功.',//EwayLocale.device.rebootSucess
				rebootFail:'正常重启失败.',//EwayLocale.device.rebootFail
				rebootSendFail:'正常重启命令发送失败.',//EwayLocale.device.rebootSendFail
				forceReboot:'强制重启',//EwayLocale.device.forceReboot
				forceRebootConfirm:'确认要执行强制重启命令么,可能会存在严重风险?',//EwayLocale.device.forceRebootConfirm
				forceRebooting:'正在执行强制重启',//EwayLocale.device.forceRebooting
				forceRebootSuccess:'强制重启成功.',//EwayLocale.device.forceRebootSuccess
				forceRebootFail:'强制重启失败.',//EwayLocale.device.forceRebootFail
				forceRebootSendFail:'强制重启命令发送失败.',//EwayLocale.device.forceRebootSendFail
				resetConfirm:'确认要执行强制复位?',//EwayLocale.device.resetConfirm
				resetSuccess:'强制复位成功',//EwayLocale.device.resetSuccess
				resetFail:'强制复位失败',//EwayLocale.device.resetFail
				resetSendFail:'强制复位命令发送失败.',//EwayLocale.device.resetSendFail
				term:'设备',//EwayLocale.device.term
				detail:'详情',//EwayLocale.device.detail
				refresh:'正在刷新......',//EwayLocale.device.refresh
				chooseOrg:'机构筛选',//EwayLocale.device.chooseOrg
				stateSet:'状态监控项配置',//EwayLocale.device.stateSet
				filterSet:'过滤条件项配置',//EwayLocale.device.filterSet
				connFirst:'当前已经暂停了与服务器的监控连接,请先与服务器建立连接,即"开始监控"',//EwayLocale.device.connFirst
				matrixPattern:'矩阵方式',//EwayLocale.device.matrixPattern
				listPattern:'列表方式',//EwayLocale.device.listPattern
				remoteCommandMsg:'命令发送成功'//EwayLocale.device.remoteCommandMsg
			}
		}

	},

	

	combox:{
		select:'--请选择--',//EwayLocale.combox.select
		explorer: '浏览...'//EwayLocale.combox.explorer
	},



	vtype:{
		ip:'请输入正确的IP地址',//EwayLocale.vtype.ip
		zip:'请输入正确的邮编格式，6位的数字',//EwayLocale.vtype.zip
		versionNo:'不是正确的版本号格式,格式说明：1.版本号由4个部分组成 A.B.C.D ;2.只有A部分是必须的 ；3. A、B、C、D必须为大于等于0的整数 ,每个部分最大长度为8位； 4.ABCD部分必须用.分隔',//EwayLocale.vtype.versionNo
		terminalId:'输入错误,设备号由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’点号‘.’组成,只能以字母或数字开头,长度1到20位。',//EwayLocale.vtype.terminalId
		mobile:'输入错误,手机号码只能输入8到11位数字。',//EwayLocale.vtype.mobile
		cardNo:'输入错误,银行卡号只能输入16到19位数字。',//EwayLocale.vtype.cardNo
		telephone:'输入错误,固定电话号码只能输入8到12位数字。',//EwayLocale.vtype.telephone
		daterange:'日期段不正确.',//EwayLocale.vtype.daterange
		numberrange:'金额范围不正确.',//EwayLocale.vtype.numberrange

		bankOrgCode:'只能输入1到20字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’、点号‘.’， 只能以字母或数字开头！',//EwayLocale.vtype.bankOrgCode
		zip:'只能输入6个‘0-9’的数字！',//EwayLocale.vtype.zip
		maxLength20:'允许的最大长度为20',//EwayLocale.vtype.maxLength20
		numberRule: '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’和点号‘.’，只能以字母或数字开头。',//EwayLocale.vtype.numberRule
		numberRulesOne: '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’和点号‘.’、汉字，只能以汉字,字母或数字开头,最多可输入100位',//EwayLocale.vtype.numberRulesOne
		numberRulesFour	: '由字母‘a-z’或‘A-Z’、数字‘0-9’、中文组成，最多可输入 40位',//EwayLocale.vtype.numberRulesFour
		numberRules: '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’和点号‘.’、汉字，只能以汉字,字母或数字开头,最多可输入200位',//EwayLocale.vtype.numberRules


		mobileRules:'手机电话号码只能输入8到11位数字‘0-9’',//EwayLocale.vtype.mobileRules
		choseDev:'请选择您报停的设备.',//EwayLocale.vtype.choseDev
		dataLoad:'正在加载数据......',//EwayLocale.vtype.dataLoad
		devLinkNormal:'请检查与设备的连接是否正常.',//EwayLocale.vtype.devLinkNormal
		hardwayInitialize:'硬件模块正在初始化......',//EwayLocale.vtype.hardwayInitialize
		inputCorrect:'请正确输入.',//EwayLocale.vtype.inputCorrect
		exportRepError:'导出报表出错，请重新操作!',//EwayLocale.vtype.exportRepError
		planOutdate:'(此方案已过期，不可应用！)',//EwayLocale.vtype.planOutdate

		emailRules: 'email必须符合*@*.*标准。',//EwayLocale.vtype.emailRules
		notifyTimesRules: '通知次数必须为数字，最小值为0,最大值为100。',//EwayLocale.vtype.notifyTimesRules
		sendTimesRules: '发送次数必须为数字，最小值为0,最大值为100。'	,//EwayLocale.vtype.sendTimesRules

		launchTranscribe:'正在启动录制......',//EwayLocale.vtype.launchTranscribe
		stopTranscribe:'正在停止录制......',//EwayLocale.vtype.stopTranscribe
		inexistenceScreen:'不存在此屏幕',//EwayLocale.vtype.inexistenceScreen
		devEmploy:'该台设备已经被',//EwayLocale.vtype.devEmploy
		userEmploy:'用户占用!',//EwayLocale.vtype.userEmploy
		loadTranscribe:'正在下载录制好的视频文件......',//EwayLocale.vtype.loadTranscribe
		remoteFailure:'远程浏览失败',//EwayLocale.vtype.remoteFailure
		versionChart:'版本下发历史状态分布图',//EwayLocale.vtype.versionChart
		openRefresh:'开启自动刷新',//EwayLocale.vtype.openRefresh
		choseTask:'请选择一个任务',//EwayLocale.vtype.choseTask
		cancelTask:'不能撤销"完成"状态的作业.',//EwayLocale.vtype.cancelTask
		cancelParticularTask:'是否真的要撤销指定的作业?(正在运行的作业只会撤销还没有运行的任务.)',//EwayLocale.vtype.cancelParticularTask
		nowDelete:'正在删除......'//EwayLocale.vtype.nowDelete
	},

	commen:{

		jobNum:'工号',//EwayLocale.commen.jobNum
		name:'姓名',//EwayLocale.commen.name
		personJobName:'岗位',//EwayLocale.commen.personJobName
		state:'状态',//EwayLocale.commen.state
		birthday:'生日',//EwayLocale.commen.birthday
		comboxStatus:{
			onJob:'在岗',//EwayLocale.comboxStatus.onJob
			onAdjust:'调休',//EwayLocale.comboxStatus.onAdjust
			onVacation:'休假',//EwayLocale.comboxStatus.onVacation
			other:'其他',//EwayLocale.comboxStatus.other
			dredge:'开通',//EwayLocale.comboxStatus.dredge
			open:'开通',//EwayLocale.comboxStatus.open
			close:'停用',//EwayLocale.comboxStatus.close
			pastDue:'过期',//EwayLocale.comboxStatus.pastDue
			pastDueSoon:'即将过期'//EwayLocale.comboxStatus.pastDueSoon
		},
		type:'类型',//EwayLocale.comboxStatus.type
		comboxType:{
			machineManager:'管机员',//EwayLocale.comboxType.machineManager
			machineRepairer:'维修人员'//EwayLocale.comboxType.machineRepairer
		},
		mobile:'手机',//EwayLocale.comboxType.mobile
		email:'邮箱',//EwayLocale.comboxType.email
		phone:'固话',//EwayLocale.comboxType.phone
		gender:'性别',//EwayLocale.comboxType.gender
		all:'全部',//EwayLocale.comboxType.all
		comboxGender:{
			male:'男',//EwayLocale.comboxGender.male
			female:'女',//EwayLocale.comboxGender.female
			unknow:'未知'//EwayLocale.comboxGender.unknow
		},
		remark:'备注',//EwayLocale.comboxGender.remark
		terminalId:'设备号',//EwayLocale.comboxGender.terminalId
		ip:'网络地址',//EwayLocale.comboxGender.ip
		orgNameBelongs:'所属机构',//EwayLocale.comboxGender.orgNameBelongs
		devTypeName:'设备型号',//EwayLocale.comboxGender.devTypeName
		devVendorName:'设备品牌',//EwayLocale.comboxGender.devVendorName
		devCatalogName:'设备类型',//EwayLocale.comboxGender.devCatalogName
		devStatus:'设备状态',//EwayLocale.comboxGender.devStatus
		comboxDevStatus:{
			upOpen:'未开通',//EwayLocale.comboxDevStatus.upOpen
			open:'开通',//EwayLocale.comboxDevStatus.open
			stop:'停用',//EwayLocale.comboxDevStatus.stop
			scrapped:'报废'//EwayLocale.comboxDevStatus.scrapped
		},
		setManager:'设置',//EwayLocale.comboxDevStatus.setManager
		devServiceName:'设备维护商',//EwayLocale.comboxDevStatus.devServiceName
		cashboxLimit:'钱箱报警金额(单位：张数)',//EwayLocale.comboxDevStatus.cashboxLimit
		installDate:'安装日期',//EwayLocale.comboxDevStatus.installDate
		address:'地址',//EwayLocale.comboxDevStatus.address
		areaCode:'区域编号',//EwayLocale.comboxDevStatus.areaCode
		areaName:'区域名称',//EwayLocale.comboxDevStatus.areaName
		toolbar:'总共：{2}条，显示{0}-{1}',//EwayLocale.comboxDevStatus.toolbar
		bindMachine :'已关联的设备',//EwayLocale.comboxDevStatus.bindMachine
		lift:'解除',//EwayLocale.comboxDevStatus.lift
		canBindMachine:'可关联的设备',//EwayLocale.comboxDevStatus.canBindMachine
		bind:'关联',//EwayLocale.comboxDevStatus.bind
		filter:'过滤条件',//EwayLocale.comboxDevStatus.filter
		stateDict:{
			newCreate:'新建',//EwayLocale.stateDict.newCreate
			normal:'正常',//EwayLocale.stateDict.normal
			locked:'锁定',//EwayLocale.stateDict.locked
			disable:'无效',//EwayLocale.stateDict.disable
			frozen:'冻结',//EwayLocale.stateDict.frozen
			deleted:'已删除'//EwayLocale.stateDict.deleted
		},
		yes:'是',//EwayLocale.stateDict.yes
		no:'否',//EwayLocale.stateDict.no
		selectAll:'全部选择',//EwayLocale.stateDict.selectAll
		selectNon:'全部不选',//EwayLocale.stateDict.selectNon
		content:'消息',//EwayLocale.stateDict.content
		upgrade:'上级',//EwayLocale.stateDict.upgrade
		port:'网络Port',//EwayLocale.stateDict.port
		seviceMode:'经营方式',//EwayLocale.stateDict.seviceMode
		insideOutside:'在行标志',//EwayLocale.stateDict.insideOutside
		appVersion:'应用版本号',//EwayLocale.stateDict.appVersion
		devInfo:'设备基本信息',//EwayLocale.stateDict.devInfo
		
		personnel:'联系人',//EwayLocale.stateDict.personnel
		warn:'警告',//EwayLocale.stateDict.warn
		fatal:'故障',//EwayLocale.stateDict.fatal
		unStable:'不稳定',//EwayLocale.stateDict.unStable
		unknow:'未知',//EwayLocale.stateDict.unknow
		noDevice:'无设备',//EwayLocale.stateDict.noDevice
		description:'描述',//EwayLocale.stateDict.description
		info:'详细信息',//EwayLocale.stateDict.info
		startDataTime:'开始时间',//EwayLocale.stateDict.startDataTime
		endDataTime:'结束时间',//EwayLocale.stateDict.endDataTime
		year:'年',//EwayLocale.stateDict.year
		month:'月',//EwayLocale.stateDict.month
		day:'日',//EwayLocale.stateDict.day
		yearTime:'年份',//EwayLocale.stateDict.yearTime
		monthTime:'月份',//EwayLocale.stateDict.monthTime
		dayTime:'日期',//EwayLocale.stateDict.dayTime
		orgFramework:'组织机构',//EwayLocale.stateDict.orgFramework
		matchOrg:'匹配机构',//EwayLocale.stateDict.matchOrg
		orgID:'机构ID',//EwayLocale.stateDict.orgID
		endValidty:'截止有效期',//EwayLocale.stateDict.endValidty
		publishDate:'发布日期',//EwayLocale.stateDict.publishDate
		announceTheme:'公告主题',//EwayLocale.stateDict.announceTheme
		filterDelete: '已删除'//EwayLocale.stateDict.filterDelete


	},

	

	index:{
		indexPage:'首页',//EwayLocale.index.indexPage
		dailyFaultPic:'日均故障趋势图',//EwayLocale.index.dailyFaultPic
		faultAmount:' 产生的故障数量: ',//EwayLocale.index.faultAmount
		devStatusDisPic:'设备状态分布图',//EwayLocale.index.devStatusDisPic
		normalDev:'1.0.0.0',//EwayLocale.index.normalDev
		unknownDev:'1.3.2.0',//EwayLocale.index.unknownDev
		exceptionDev:'2.0.0.0',//EwayLocale.index.exceptionDev
		amount:'台设备',//EwayLocale.index.amount
		versionDistributePie:'版本分布图',//EwayLocale.index.versionDistributePie
		retainCardTrendTitle:'日均吞卡趋势图'//EwayLocale.index.retainCardTrendTitle
	},

	
	personal:{
		baseInfo:'基本信息',//EwayLocale.personal.baseInfo
		accountNum:'账号',//EwayLocale.personal.accountNum
		personalInfo:'个人信息',//EwayLocale.personal.personalInfo
		changePwd:'修改密码',//EwayLocale.personal.changePwd
		nowLogin:'当前登录账号',//EwayLocale.personal.nowLogin
		inputOldPwd:'输入原始密码',//EwayLocale.personal.inputOldPwd
		inputNewPwd:'输入新密码',//EwayLocale.personal.inputNewPwd
		inputVali:'只能输入8到20位字母‘a-z’或‘A-Z’、数字‘0-9’、特殊字符！',//EwayLocale.personal.inputVali
		inputAgain:'再次输入新密码',//EwayLocale.personal.inputAgain
		pwdNotSame:'两次密码不一致！',//EwayLocale.personal.pwdNotSame
		rememberPwd:'单击确定即可修改密码，请牢记新密码！',//EwayLocale.personal.rememberPwd
		pwdSameNoChange:'输入的新密码与旧密码相同,不可修改.',//EwayLocale.personal.pwdSameNoChange
		reOperate:'无法修改密码,请重新操作.'//EwayLocale.personal.reOperate
	},

	
	system:{
		sysRegist:'系统注册',//EwayLocale.system.sysRegist
		registCode:'注册码',//EwayLocale.system.registCode
		startDate:'开始时间',//EwayLocale.system.startDate
		endDate:'到期时间',//EwayLocale.system.endDate
		registType:'注册类型',//EwayLocale.system.registType
		serialNum:'序列号',//EwayLocale.system.serialNum
		getSerialNum:'正在获取序列号......',//EwayLocale.system.getSerialNum
		checkCode:'校验码',//EwayLocale.system.checkCode
		tryOut:'试用',//EwayLocale.system.tryOut
		noLimit:'没有限制',//EwayLocale.system.noLimit
		getSerialNumFail:'序列号获取失败',//EwayLocale.system.getSerialNumFail
		registSuc:'注册成功',//EwayLocale.system.registSuc
		registFail:'注册失败',//EwayLocale.system.registFail
		appearInnerFalse:'出现内部错误',//EwayLocale.system.appearInnerFalse
		regist:'注册',//EwayLocale.system.regist
		aboutSystem:'关于系统',//EwayLocale.system.aboutSystem
		softwareName:'软件名称',//EwayLocale.system.softwareName
		ATMV:'金融自助设备集中监控系统',//EwayLocale.system.ATMV
		softwareVersion:'软件版本',//EwayLocale.system.softwareVersion
		innerVersion:'内部版本号',//EwayLocale.system.innerVersion
		copyRight:'版权信息：&copy;深圳市怡化电脑有限公司 版权所有',//EwayLocale.system.copyRight
		introduction:'系统简介：',//EwayLocale.system.introduction
		introductionA:'本系统是监控系统的基础功能有ATM信息管理、',//EwayLocale.system.introductionA
		introductionB:'自动化版本分发管理、 ATM设备监控等功能。通过这些功能，各大银行可以集中管理ATM设备信息  监视 远程的ATM，对远程ATM机器上的软件升',//EwayLocale.system.introductionB
		introductionC:'级和软件维护，方便了各大银 行对自助设备进行高效的管理和维护。',//EwayLocale.system.introductionC
		guideUsers:'本手册指导用户操作本系统，更快的掌握系统的各项功能。',//EwayLocale.system.guideUsers
		systemHelp:'系统帮助',//EwayLocale.system.systemHelp
		helpName:'名称',//EwayLocale.system.helpName
		helpExpain:'说明',//EwayLocale.system.helpExpain
		helpDownload:'下载',//EwayLocale.system.helpDownload
		clickDownload:'单击此处即可下载该文档'//EwayLocale.system.clickDownload
	}

});
