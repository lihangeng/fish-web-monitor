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
	systemHelp:'系统帮助',///EwayLocale.systemHelp
	exitSystem:'退出系统',//EwayLocale.exitSystem
	title:{
		msg:'信息'//EwayLocale.title.msg
	},
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
		someStripRemoveFailePleaseRefresh:'条解除失败，请刷新后重试！',//EwayLocale.msg.EwayLocale.msg.someStripRemoveFailePleaseRefresh
		versionDownloaded:'不能删除"等待下发"和"已下发"状态的版本.',//EwayLocale.msg.versionDownloaded
		selectVersionToDelete:'请选择您要删除的版本.',//EwayLocale.msg.selectVersionToDelete
		communicationFail:'增加失败:与服务器通讯失败.',//EwayLocale.msg.communicationFail
		sameVersionNoFail:'增加失败:已经存在相同的版本号.',//EwayLocale.msg.sameVersionNoFail
		fileSizeMaxFail:'增加失败:超过最大文件大小限制（最大300M）',//EwayLocale.msg.fileSizeMaxFail
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
		//TODO *为数字需要替换
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
		//bankOrg
		deepQuery:'深度查询',
		bankOrgMove:'组织迁移',
		bankOrgAdmin:'管理员',
		//bankPerson
		bankPerlink:'绑定设备',
		sure:'确定',//EwayLocale.button.sure
		confirm:'确认',//EwayLocale.button.confirm
		cancle:'取消',//EwayLocale.button.cancle
		choose:'选择',//EwayLocale.button.choose
		pause:'暂停',//EwayLocale.button.pause

		exported:'导出',//EwayLocale.button.exported
		select:'选择',
		info :'详细信息',
		move:'移机',
		exportXLS:'导出XLS',
		exportPDF:'导出PDF',
		massExport:'批量导入',
		download:'下发',
		downloadToolTip:'配置下发作业',
		save:'保&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存',//EwayLocale.button.save
		openPlan:'开机方案',//EwayLocale.button.openPlan
		adminBtn:'管理员',
		personM:'厂商管理员',//EwayLocale.button.personM
		personTM:'管机员',//EwayLocale.button.personTM
		orgAdmin:'机构管理员',//EwayLocale.button.orgAdmin
		
	},
	//引用其他模块
	refs:{
		selectAll:'全部',//EwayLocale.refs.selectAll
		orgName:'机构',//EwayLocale.refs.orgName
		terminalId:'设备编号',//EwayLocale.refs.terminalId
		ip:'IP地址',//EwayLocale.refs.ip
		devType:"设备型号"//EwayLocale.refs.devType
	},
	//**********************************************************/
	tip:{
		search :{
			warn:'查询条件存在错误项.',//EwayLocale.tip.search.warn
			record:'请选择您要查看的记录.',//EwayLocale.tip.search.record
		},
		update:{
			one:'只能选择一条记录更改.',//EwayLocale.tip.update.one
			two:'此条记录不能被更改.'//EwayLocale.tip.update.two
		},
		remove :{
			none:'请选择您要删除的记录.',
			one:'只能选择一条记录删除',//EwayLocale.tip.remove.one
			confirm:{
				title:'请确认',//EwayLocale.tip.remove.confirm.title
				info:'是否删除该记录?'//EwayLocale.tip.remove.confirm.info
			},
			error:'删除失败:'//EwayLocale.tip.remove.error
		},
		own:{
			have:'有',
			nothing:'无'
		},
		right:{
			yes:'是',
			no:'否'
		},
		add:{
			error:'新增失败'//EwayLocale.tip.add.error
		},
		success:'成功.',//EwayLocale.tip.success
		fail:'失败:',//EwayLocale.tip.fail
		phone:'请输入正确的电话号码',
		remind:'提示',//EwayLocale.tip.remind
		displayMessage:'总共：{2}条，显示{0}-{1}',
		
		formatPageBfMsg: '每页显示',// EwayLocale.tip.formatPageBfMsg
		formatPageAfMsg: '条',// EwayLocale.tip.formatPageAfMsg

		unCertain:'未知',
		searchOfNoLegal:'查询项中存在不合法的输入,不能提交.',
		choseExportDevInfo:'请选择要导出信息的设备',
		nowLink:'正在连接......',
		linkFailure:'连接失败.',//EwayLocale.tip.linkFailure
		inputError:'输入有误.',
		numberExist:'此编号已经存在,请重新输入.',
		isConfirmRemove:'删除分组,关联关系也被删除,是否真的要删除指定分组?',
		noGroupInfo:'没有组信息,无法查询.',
		selectAdd:'请选择您要增加的记录.',
		continueAdd:'添加成功,是否继续向组内添加设备?',
		addFail:'添加失败.',
		isRemoveDev:'是否从该组移除该设备?',
		removeFail:'移除失败.',
		selectRemoveGroup:'请选择您要移除的设备所在组.',
		selectRemoveDev:'请选择您要移除的设备.',
		selectMoveDev:'请选择要移动的设备.',
		moveSuc:'移机成功.',
		dateReSelect:'开始时间不能大于结束日期,请重新选择',
		selectPlan:'请选择您应用的方案.',//EwayLocale.tip.selectPlan
		removeFail:'解除失败.',//EwayLocale.tip.removeFail
		selectRemoveDev:'请选择要解除的设备.',//EwayLocale.tip.selectRemoveDev
		relatedFail:'关联失败.',//EwayLocale.tip.relatedFail
		selectRelatedDev:'请选择要关联的设备.',//EwayLocale.tip.selectRelatedDev
		planNoUpdate:'该方案已经执行,不可修改.',
		planNoRemove:'该方案已经执行,不可删除.',
		exportFiles: '请选择导入文件,只支持.xls和.xlsx格式的文件',
		noChange:'没有更改数据,请更改后再点击确定!',//EwayLocale.tip.noChange
		operateSuc:'操作成功',//EwayLocale.tip.operateSuc
		operateWrong:'操作成功',//EwayLocale.tip.operateWrong
		deleteOne:'一次只能删除一条记录.',//EwayLocale.tip.deleteOne
		chooseRecord:'请选择您要关联的记录.',//EwayLocale.tip.chooseRecord
		choosePlan:'请选择您要查看的方案',//EwayLocale.tip.choosePlan
		planDetail:'方案详情',//EwayLocale.tip.planDetail
		planDate:'方案详情(日期)',//EwayLocale.tip.planDate
		planWeek:'方案详情(星期)',//EwayLocale.tip.planWeek
		planNoConf:'该方案无详细设置！',//EwayLocale.tip.planNoConf
		chooseRelatedDev:'请选择您要关联的设备！',//EwayLocale.tip.chooseRelatedDev
		devRelatedPlan:'设备已关联开机方案！',//EwayLocale.tip.devRelatedPlan
		//add by panxin
		tips:'提示',//EwayLocale.tip.tips
		input:'请正确输入',//EwayLocale.tip.input
		roleName:'由字母‘a-z’或‘A-Z’、数字‘0-9’，最多可输入 40位',//EwayLocale.tip.roleName
		roleDescription:'由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’和点号‘.’、汉字，只能以汉字,字母或数字开头,最多可输入100位',//EwayLocale.tip.roleDescription
		notNull:'不能为空',//EwayLocale.tip.notNull
		cardNo:'只能输入13到19个数字',//EwayLocale.tip.cardNo
		blankBegin:'不能以空格开头',//EwayLocale.tip.blankBegin
		passwd:{
			confirmPasswd:'是否确认密码重置？',//EwayLocale.tip.confirmPasswd
			resetPasswding:'正在重置密码......',//EwayLocale.tip.resetPasswding
			resetPasswdFail:'密码重置失败！'//EwayLocale.tip.resetPasswdFail
		},
		operateDate:{
			operateDateBegin:'操作日期开始于',//EwayLocale.tip.operateDate.operateDateBegin
			operateDateEnd:'操作日期结束于'//EwayLocale.tip.operateDate.operateDateEnd
		},
		bankOrg:{
			manager:{
				set:{
					chooseOrg:'请选择您要设置的机构.',//EwayLocale.tip.bankOrg.manager.set.chooseOrg
					managerSuccess:'设置管理员成功.',//EwayLocale.tip.bankOrg.manager.set.managerSuccess
					managerFail:'设置管理员失败.'//EwayLocale.tip.bankOrg.manager.set.managerFail
				},
				remove:{
					confirm:'是否删除该机构管理员?',//EwayLocale.tip.bankOrg.manager.remove.confirm
					reChoose:'您未选择您要设置的机构或该机构下没有管理员，请重新选择.',//EwayLocale.tip.bankOrg.manager.remove.reChoose
					delSuccess:'删除管理员成功.',//EwayLocale.tip.bankOrg.manager.remove.delSuccess
					delFail:'删除管理员失败.'//EwayLocale.tip.bankOrg.manager.remove.delFail
				}
			},
			orgEligible:'符合条件的机构',//EwayLocale.tip.bankOrg.orgEligible
			downGradeOrg:'的直接下级机构',//EwayLocale.tip.bankOrg.downGradeOrg
			move:{
				chooseOrg:'请选择您要迁移的组织.',//EwayLocale.tip.bankOrg.move.chooseOrg
				moveSuccess:'组织迁移成功.'//EwayLocale.tip.bankOrg.move.moveSuccess
			}
		},
		bankPer:{
			allPersonInfo:'所有银行人员信息',//EwayLocale.tip.bankPer.allPersonInfo
			link:{
				linkPerson:'请选择您关联的人员',//EwayLocale.tip.bankPer.link.linkPerson
				unLinkPersonFail:'关联失败.',//EwayLocale.tip.bankPer.link.unLinkPersonFail
				unlinkDev:'请选择要解除的设备.',//EwayLocale.tip.bankPer.link.unlinkDev
				linkDev:'请选择要关联的设备.',//EwayLocale.tip.bankPer.link.linkDev
				unLinkDevFail:'解除失败.'//EwayLocale.tip.bankPer.link.unLinkDevFail
			},
			personEligible:'符合条件的人员',//EwayLocale.tip.bankPer.personEligible
			downGradePer:'以及其下属机构下的人员信息',//EwayLocale.tip.bankPer.downGradePer
			personBelongs:'下的人员信息'//EwayLocale.tip.bankPer.personBelongs
		},
		serviceOrg:{
			chooseOrg:'请选择您要设置的维护商.',//EwayLocale.tip.serviceOrg.chooseOrg
			remove:{
				reChoose:'您未选择要设置的维护商或该维护商下没有管理员,请重新选择.'//EwayLocale.tip.serviceOrg.remove.reChoose
			}
		},
		servicePer:{
			allSerPer:'所有维护商人员信息'//EwayLocale.tip.servicePer.allSerPer
		},
		user:{
			add:{
				createAcc:'请选择您要创建的账号的人员',//EwayLocale.tip.user.add.createAcc
				createSuccess:'创建成功,新建账户',//EwayLocale.tip.user.add.createSuccess
				initPasswd:'初始密码为: 888888'//EwayLocale.tip.user.add.initPasswd
			},
			remove:{
				failRoot:'删除失败:系统管理员用户,无法删除.',//EwayLocale.tip.user.remove.failRoot
				confirm:'是否删除该记录:删除用户会删除该用户的日志信息.',//EwayLocale.tip.user.remove.confirm
				fail:'删除失败:无法删除角色,请重新操作.'//EwayLocale.tip.user.remove.fail
			},
			update:{
				fail:'更改失败:记录不存在,请刷新后操作.',//EwayLocale.tip.user.update.fail
			},
			move:{
				choose:'请选择需要移动的记录.'//EwayLocale.tip.user.move.choose

			}
		},
		business:{
			transaction:{
				transactionMonitor:{
					beginMonitor:'请先停止监控后再输入条件，按开始监控按钮即可进行条件监控！',//EwayLocale.tip.business.transaction.transactionMonitor.beginMonitor
					input:'设备号、对方账号、客户账号至少输入一个.',//EwayLocale.tip.business.transaction.transactionMonitor.input
					left:'离开',//EwayLocale.tip.business.transaction.transactionMonitor.left
				},
				historyTransaction:{
					input:'查询必须输入设备号.'//EwayLocale.tip.business.transaction.historyTransaction.input


				}
			},
			blackList:{
				importing:'正在导入文件',//EwayLocale.tip.business.blackList.importing
				importSuccess:'导入黑名单卡文件成功'//EwayLocale.tip.business.blackList.importSuccess
			},
			card:{
				returnFail:'移交失败:后台处理出错.',//EwayLocale.tip.business.card.returnFail
				choose:'请选择您移交的卡片.',//EwayLocale.tip.business.card.choose
				returnSucess:'移交成功.',//EwayLocale.tip.business.card.returnSucess
				chooseBack:'请选择要领取的卡片',//EwayLocale.tip.business.card.chooseBack
				getSuccess:'领取成功.',//EwayLocale.tip.business.card.getSuccess
				destroyConfirm:'是否销毁这张卡片?',//EwayLocale.tip.business.card.destroyConfirm
				destroySuccess:'销毁成功',//EwayLocale.tip.business.card.destroySuccess
				chooseDestroy:'请选择要销毁的卡片.',//EwayLocale.tip.business.card.chooseDestroy
				idCardRegex:'请输入正确的身份证号码,15位或者18位',//EwayLocale.tip.business.card.idCardRegex
				accountRegex:'请正确输入户口本上的身份证号码,15位或者18位',//EwayLocale.tip.business.card.accountRegex
				driveCardRegex:'请正确输入驾驶证上的身份证号码,15位或者18位',//EwayLocale.tip.business.card.driveCardRegex
				passportRegex:'请正确输入护照上的身份证号码,15位或者18位',//EwayLocale.tip.business.card.passportRegex
				soldierRegex:'请输入正确的军官证号码,1-5位汉字和1-10位数字',//EwayLocale.tip.business.card.soldierRegex
				soldierCard:'请输入正确的士兵证,7-8位数字',//EwayLocale.tip.business.card.soldierCard
				busnessPaper:'请输入正确的法人营业执照,12-15位数字',//EwayLocale.tip.business.card.busnessPaper
				busnessCode:'请输入正确的法人代码证,15位数字',//EwayLocale.tip.business.card.busnessCode
				taxPaper:'请输入正确的税务登记证,15位数字'//EwayLocale.tip.business.card.taxPaper

			},
			device:{
				getCashInfoFail:'获取钞箱信息失败',//EwayLocale.tip.business.device.getCashInfoFail
				operating:'正在执行',//EwayLocale.tip.business.device.operating
				reviewFail:'查看失败.',//EwayLocale.tip.business.device.reviewFail
				logLoadConfirm:'应用日志提取成功,是否下载?',//EwayLocale.tip.business.device.logLoadConfirm
				logPullFail:'提取应用电子日志失败',//EwayLocale.tip.business.device.logPullFail
				logFail:'log处理失败.',//EwayLocale.tip.business.device.logFail
				linkServerFail:'服务器连接失败.',//EwayLocale.tip.business.device.linkServerFail
				logicOpen:'确认要执行开启服务命令',//EwayLocale.tip.business.device.logicOpen
				openSuccess:'执行开启服务命令成功.',//EwayLocale.tip.business.device.openSuccess
				openFail:'执行开启服务命令失败.',//EwayLocale.tip.business.device.openFail
				closeConfirm:'确认要执行暂停服务命令?',//EwayLocale.tip.business.device.closeConfirm
				closeNormal:'正常关机',//EwayLocale.tip.business.device.closeNormal
				closeComfirm:'确认要执行正常关机命令么,可能会存在风险?',//EwayLocale.tip.business.device.closeComfirm
				closing:'正在执行正常关机',//EwayLocale.tip.business.device.closing
				closeSucess:'正常关机成功.',//EwayLocale.tip.business.device.closeSucess
				closeFail:'正常关机失败.',//EwayLocale.tip.business.device.closeFail
				closeSentFail:'正常关机命令发送失败.',//EwayLocale.tip.business.device.closeSentFail
				forceClose:'强制关机',//EwayLocale.tip.business.device.forceClose
				forceCloseComfirm:'确认要执行强制关机命令么,可能会存在严重风险?',//EwayLocale.tip.business.device.forceCloseComfirm
				forceClosing:'正在执行强制关机',//EwayLocale.tip.business.device.forceClosing
				forceCloseSucess:'强制关机成功.',//EwayLocale.tip.business.device.forceCloseSucess
				forceCloseFail:'强制关机失败.',//EwayLocale.tip.business.device.forceCloseFail
				ForceCloseSentFail:'强制关机命令发送失败.',//EwayLocale.tip.business.device.ForceCloseSentFail
				reboot:'正常重启',//EwayLocale.tip.business.device.reboot
				rebootConfirm:'确认要执行正常重启命令么,可能会存在风险?',//EwayLocale.tip.business.device.rebootConfirm
				rebooting:'正在执行正常重启',//EwayLocale.tip.business.device.rebooting
				rebootSucess:'正常重启成功.',//EwayLocale.tip.business.device.rebootSucess
				rebootFail:'正常重启失败.',//EwayLocale.tip.business.device.rebootFail
				rebootSendFail:'正常重启命令发送失败.',//EwayLocale.tip.business.device.rebootSendFail
				forceReboot:'强制重启',//EwayLocale.tip.business.device.forceReboot
				forceRebootConfirm:'确认要执行强制重启命令么,可能会存在严重风险?',//EwayLocale.tip.business.device.forceRebootConfirm
				forceRebooting:'正在执行强制重启',//EwayLocale.tip.business.device.forceRebooting
				forceRebootSuccess:'强制重启成功.',//EwayLocale.tip.business.device.forceRebootSuccess
				forceRebootFail:'强制重启失败.',//EwayLocale.tip.business.device.forceRebootFail
				forceRebootSendFail:'强制重启命令发送失败.',//EwayLocale.tip.business.device.forceRebootSendFail
				resetConfirm:'确认要执行强制复位?',//EwayLocale.tip.business.device.resetConfirm
				resetSuccess:'强制复位成功',//EwayLocale.tip.business.device.resetSuccess
				resetFail:'强制复位失败',//EwayLocale.tip.business.device.resetFail
				resetSendFail:'强制复位命令发送失败.',//EwayLocale.tip.business.device.resetSendFail
				term:'设备',//EwayLocale.tip.business.device.term
				detail:'详情',//EwayLocale.tip.business.device.detail
				refresh:'正在刷新......',//EwayLocale.tip.business.device.refresh
				chooseOrg:'机构筛选',//EwayLocale.tip.business.device.chooseOrg
				stateSet:'状态监控项配置',//EwayLocale.tip.business.device.stateSet
				filterSet:'过滤条件项配置',//EwayLocale.tip.business.device.filterSet
				connFirst:'当前已经暂停了与服务器的监控连接,请先与服务器建立连接,即"开始监控"',//EwayLocale.tip.business.device.connFirst
				matrixPattern:'矩阵方式',//EwayLocale.tip.business.device.matrixPattern
				listPattern:'列表方式'//EwayLocale.tip.business.device.listPattern
			},
		},

	},

	//**********************************************************/

	combox:{
		select:'--请选择--',//EwayLocale.combox.select
		explorer: '浏览...',//EwayLocale.combox.explorer
	},



	vtype:{
		ip:'请输入正确的IP地址',//EwayLocale.vtype.ip
		zip:'请输入正确的邮编格式，6位的数字',
		versionNo:'不是正确的版本号格式,格式说明：1.版本号由4个部分组成 A.B.C.D ;2.只有A部分是必须的 ；3. A、B、C、D必须为大于等于0的整数 ,每个部分最大长度为8位； 4.ABCD部分必须用.分隔',
		terminalId:'输入错误,设备号由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’点号‘.’组成,只能以字母或数字开头,长度1到20位。',
		mobile:'输入错误,手机号码只能输入8到11位数字。',
		cardNo:'输入错误,银行卡号只能输入16到19位数字。',
		telephone:'输入错误,固定电话号码只能输入8到11位数字。',
		daterange:'日期段不正确.',
		numberrange:'金额范围不正确.',

		bankOrgCode:'只能输入1到20字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’、点号‘.’， 只能以字母或数字开头！',
		zip:'只能输入6个‘0-9’的数字！',
		maxLength20:'允许的最大长度为20',
		numberRule: '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’和点号‘.’，只能以字母或数字开头。',
		numberRulesOne: '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’和点号‘.’、汉字，只能以汉字,字母或数字开头,最多可输入100位',
		numberRulesFour	: '由字母‘a-z’或‘A-Z’、数字‘0-9’，最多可输入 40位',
		numberRules: '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’和点号‘.’、汉字，只能以汉字,字母或数字开头,最多可输入200位',


		mobileRules:'手机电话号码只能输入8到11位数字‘0-9’',
		choseDev:'请选择您报停的设备.',
		dataLoad:'正在加载数据......',
		devLinkNormal:'请检查与设备的连接是否正常.',
		hardwayInitialize:'硬件模块正在初始化......',
		inputCorrect:'请正确输入.',
		exportRepError:'导出报表出错，请重新操作!',
		planOutdate:'(此方案已过期，不可应用！)',

		emailRules: 'email必须符合*@*.*标准。',
		notifyTimesRules: '通知次数必须为数字，最小值为0,最大值为100。',
		sendTimesRules: '发送次数必须为数字，最小值为0,最大值为100。'	,

		launchTranscribe:'正在启动录制......',
		stopTranscribe:'正在停止录制......',
		inexistenceScreen:'不存在此屏幕',
		devEmploy:'该台设备已经被',
		userEmploy:'用户占用!',
		loadTranscribe:'正在下载录制好的视频文件......',
		remoteFailure:'远程浏览失败',
		versionChart:'版本下发历史状态分布图',
		openRefresh:'开启自动刷新',
		choseTask:'请选择一个任务',
		cancelTask:'不能撤销"完成"状态的作业.',
		cancelParticularTask:'是否真的要撤销指定的作业?(正在运行的作业只会撤销还没有运行的任务.)',
		nowDelete:'正在删除......',
	},
	//**********************************************************/
	versionType:{
		title:'软件分类管理',//EwayLocale.versionType.title
		treeRoot:'所有软件分类',//EwayLocale.versionType.treeRoot
		defaultInstallPath:'默认安装路径',//EwayLocale.versionType.defaultInstallPath
		needRestart:'需要重启设备完成升级',//EwayLocale.versionType.needRestart
		devTypeOfUser:'适用的设备型号',//EwayLocale.versionType.devTypeOfUser
		winTitle:'软件分类',//EwayLocale.versionType.winTitle
		versionTypeNameRegText:'只能输入字母(a-z或A-Z)、数字(0-9)、下划线(_)、横线(-)'//EwayLocale.versionType.versionTypeNameRegText
	},
	statics:{
		title:'下发结果统计',//EwayLocale.statics.title
		versionInfo:'版本信息'//EwayLocale.statics.versionInfo
	},
	//广告模块
	advert:{
		title:'广告管理',//EwayLocale.advert.title
		createAdvert:'创建广告',//EwayLocale.advert.createAdvert
		idleAdvert:'创建等待插卡广告',//EwayLocale.advert.idleAdvert
		transAdvert:'创建交易页面广告',//EwayLocale.advert.transAdvert
		textAdvert:'创建文字滚动广告',//EwayLocale.advert.textAdvert
		annoucementAdvert:'创建公告',//EwayLocale.advert.annoucementAdvert
		updateTitle:'更改广告信息',//EwayLocale.advert.updateTitle
		downloadButton:'下发广告',//EwayLocale.advert.downloadButton
		preview:'广告预览',//EwayLocale.advert.preview
		preview1024:'预览1024分辨率',//EwayLocale.advert.preview1024
		preview800:'预览800分辨率',//EwayLocale.advert.preview800
		preview600:'预览600分辨率',//EwayLocale.advert.preview600

		id:'广告ID',//EwayLocale.advert.id
		type:'广告类型',//EwayLocale.advert.type
		downType:'广告下发方式',//EwayLocale.advert.downType
		validity:'广告有效期',//EwayLocale.advert.validity
		createdTime:'制作时间',//EwayLocale.advert.createdTime
		userName:'制作人',//EwayLocale.advert.userName
		versionStatus:'广告版本状态',//EwayLocale.advert.versionStatus
		advertVersionFile:'版本文件',//EwayLocale.advert.advertVersionFile
		createdTimeStart:'制作时间开始于',//EwayLocale.advert.createdTimeStart
		createdTimeStop:'制作时间结束于',//EwayLocale.advert.createdTimeStop
		downloadAdvertConfig:'下发广告配置',//EwayLocale.advert.downloadAdvertConfig
		versionType:'软件分类',//EwayLocale.advert.versionType
		jobPriority:'作业优先级',//EwayLocale.advert.jobPriority
		jobType:'作业类型',//EwayLocale.advert.jobType
		toVersionButton:'还没有生成版本信息，可以单击按钮[生成版本]。',//EwayLocale.advert.toVersionButton
		playTime:'广告播放时长',//EwayLocale.advert.playTime
		beginDate:'开始日期',//EwayLocale.advert.beginDate
		endDate:'结束日期',//EwayLocale.advert.endDate
		beginTime:'开始时间',//EwayLocale.advert.beginTime
		endTime:'结束时间',//EwayLocale.advert.endTime
		fileSize:'资源大小',//EwayLocale.advert.fileSize
		content:'播放资源内容',//EwayLocale.advert.content
		advertConfig:'广告配置',//EwayLocale.advert.advertConfig
		addIdleTitle:'增加等待插卡广告信息',//EwayLocale.advert.addIdleTitle
		addIdleMore:'再增加一个广告资源',//EwayLocale.advert.addIdleMore
		advertBasicInfo:'广告基本信息',//EwayLocale.advert.advertBasicInfo
		idleAdvertInfo:'等待插卡页面广告',//EwayLocale.advert.idleAdvertInfo
		advertValidity:'广告有效期',//EwayLocale.advert.advertValidity
		validityTemp:'临时播放',//EwayLocale.advert.validityTemp
		validityAlways:'永久播放',//EwayLocale.advert.validityAlways
		idleAdvertResConfig:'等待插卡页面广告资源配置',//EwayLocale.advert.idleAdvertResConfig
		addTransTitle:'增加交易页面广告信息',//EwayLocale.advert.addTransTitle
		transInfoAdvert:'交易页面广告',//EwayLocale.advert.transInfoAdvert
		transAdvertResConfig:'交易页面广告资源配置',//EwayLocale.advert.transAdvertResConfig
		addTextTitle:'增加文字滚动广告信息',//EwayLocale.advert.addTextTitle
		textInfoAdvert:'文字广告',//EwayLocale.advert.textInfoAdvert
		textAdvertResConfig:'文字广告资源配置',//EwayLocale.advert.textAdvertResConfig
		addAnnoucementTitle:'增加公告信息',//EwayLocale.advert.addAnnoucementTitle
		annoucementBasicInfo:'公告基本信息',//EwayLocale.advert.annoucementBasicInfo
		annoucementInfoAdvert:'公告',//EwayLocale.advert.annoucementInfoAdvert
		annoucementAdvertResConfig:'公告页面广告资源配置',//EwayLocale.advert.annoucementAdvertResConfig
		advertTypeSelectEmpty:'请选择广告类型',//EwayLocale.advert.advertTypeSelectEmpty
		advertTypeTrans:'交易页面广告',//EwayLocale.advert.advertTypeTrans
		advertTypeIdle:'等待插卡广告',//EwayLocale.advert.advertTypeIdle
		advertTypeText:'文字滚动广告',//EwayLocale.advert.advertTypeText
		advertTypeAnnou:'公告',//EwayLocale.advert.advertTypeAnnou
		annoucementMoreTitle:'添加公告',//EwayLocale.advert.annoucementMoreTitle
		annoucementContext:'公告内容',//EwayLocale.advert.annoucementContext
		annoucementContextRegText:'不能包含空格',//EwayLocale.advert.annoucementContextRegText
		times:'时长',//EwayLocale.advert.times
		timesTips:'单位:秒，提示：广告播放时长请控制在60秒内',//EwayLocale.advert.timesTips
		hourDisplay:'时',//EwayLocale.advert.hourDisplay
		minuteDisplay:'分',//EwayLocale.advert.minuteDisplay
		secondeDisplay:'秒',//EwayLocale.advert.secondeDisplay
		textMoreTitle:'添加文字滚动页面广告',//EwayLocale.advert.textMoreTitle
		textContext:'滚动文字',//EwayLocale.advert.textContext
		idleMoreTitle:'添加等待插卡页面广告',//EwayLocale.advert.idleMoreTitle
		chooseMediaFile:'请选择媒体文件',//EwayLocale.advert.chooseMediaFile
		uploadResource:'上传资源...',//EwayLocale.advert.uploadResource
		uploadResourceBlank:'请上传资源',//EwayLocale.advert.uploadResourceBlank
		uploadRegText:'上传的资源格式不支持,只能上传.jpg、.gif格式的文件',//EwayLocale.advert.uploadRegText
		resourceFormatTips:'(仅支持.jpg、.gif格式的文件)',//EwayLocale.advert.resourceFormatTips
		resourceAlias:'修改后的文件名',//EwayLocale.advert.resourceAlias
		transMoreTitle:'添加交易页面广告',//EwayLocale.advert.transMoreTitle
		chooseMediaFile:'请选择媒体文件',//EwayLocale.advert.chooseMediaFile
		uploadResource:'上传资源...',//EwayLocale.advert.uploadResource
		uploadResourceBlank:'请上传资源',//EwayLocale.advert.uploadResourceBlank
		addMorePic:'添加图片',//EwayLocale.advert.addMorePic
		uploadRegText:'上传的资源格式不支持,只能上传.jpg、.gif格式的文件',//EwayLocale.advert.uploadRegText
		resourceFormatTips:'(仅支持.jpg、.gif格式的文件)',//EwayLocale.advert.resourceFormatTips
		resourceAlias:'修改后的文件名',//EwayLocale.advert.resourceAlias
		advertDownMethodCover:'覆盖',//EwayLocale.advert.advertDownMethodCover
		uploading:'正在上传资源...',//EwayLocale.advert.uploading
		advertPreviewTitle0:'广告预览(共有 ',//EwayLocale.advert.advertPreviewTitle0
		advertPreviewTitle1:'个资源,当前播放第 ',//EwayLocale.advert.advertPreviewTitle1
		advertPreviewTitle2:'个) ',//EwayLocale.advert.advertPreviewTitle2
		choosedAdvertRes:'您已经选择了',//EwayLocale.advert.choosedAdvertRes
		limitNumberTenForEveryResolution:'每种分辨率下最多只能上传10张图片.',//EwayLocale.advert.limitNumberTenForEveryResolution
		mustContainerOnePicAt1024:'1024分辨率下至少包含一个图片',//EwayLocale.advert.mustContainerOnePicAt1024
		deleteAdvertResource:'删除该图片',//EwayLocale.advert.deleteAdvertResource
		fileName:'文件名',//EwayLocale.advert.fileName
		resourceName:'资源名称',//EwayLocale.advert.resourceName
		perviewAdertWithIEBrowse:'非IE浏览器不支持视频广告的预览.',//EwayLocale.advert.perviewAdertWithIEBrowse
		fileFormatTipsInfo:"<font color='red'>上传的图片格式不支持,只能上传.jpg格式的图片</font>",//EwayLocale.advert.fileFormatTipsInfo
		idleAdvertUpTipsInfo:'<font color="red">仅支持.jpg和.gif格式的图片;每种分辨率最多上传10张图片;每张图片最大5M</font>',//EwayLocale.advert.idleAdvertUpTipsInfo
		configTitle:'广告详细配置'//EwayLocale.advert.configTitle
	},
	//版本管理模块
	version:{
		selectDeviceInfo0:"已选择的设备(<font color='red'>",//EwayLocale.version.selectDeviceInfo0
		selectDeviceInfo1:"</font>)台",//EwayLocale.version.selectDeviceInfo1
		addVersionTitle:'增加版本信息',//EwayLocale.version.addVersionTitle
		batchTaskName:'任务批次名称',//EwayLocale.version.batchTaskName
		batchTaskNameEmpty:'例如:****需求第1批次升级',//EwayLocale.version.batchTaskNameEmpty
		UpdateTitle:'更改版本信息',//EwayLocale.version.UpdateTitle
		addJobTitle:'配置作业信息',//EwayLocale.version.addJobTitle
		downloadVersionId:'下发版本ID',//EwayLocale.version.downloadVersionId
		taskType:'任务类型',//EwayLocale.version.taskType
		taskTypeManual:'手动升级',//EwayLocale.version.taskTypeManual
		taskTypeAuto:'自动升级',//EwayLocale.version.taskTypeAuto
		taskTypeScheduler:'计划作业',//EwayLocale.version.taskTypeScheduler
		planTime:'计划执行时间',//EwayLocale.version.planTime
		selectableDevice:'可以下发的设备',//EwayLocale.version.selectableDevice
		linkedDevice:'已选择的设备',//EwayLocale.version.linkedDevice
		downloadVerFile:'下载版本文件',//EwayLocale.version.downloadVerFile
		View:{
			title:'版本管理',
			versionDetail:'版本详情',//EwayLocale.version.View.versionDetail
			remark:'备注', //EwayLocale.version.View.remark
			newCreate:'新建',//EwayLocale.version.View.newCreate
			downLoaded:'已下发',//EwayLocale.version.View.downLoaded
			waitting:'等待下发',//EwayLocale.version.View.waitting
			versionPath:'版本路径',//EwayLocale.version.View.versionPath
			versionPathRegText:'不符合文件路径规则，规则如下：1.文件名只能包含英文字母(a-z A-Z)、数字(0-9)、下划线(_)、横线(-) ； 2.路径统一用正斜杠(/)作为分隔符 ；3.不区分大小 ; 示例 E: E:/yihua',//EwayLocale.version.View.versionPathRegText
			versionPathDesc:'(版本文件在自助设备上的安装路径)',//EwayLocale.version.View.versionPathDesc
			versionPerson:'创建人',//EwayLocale.version.View.versionPerson
			versionType:'版本类型',//EwayLocale.version.View.versionType
			versionFile:'版本文件',//EwayLocale.version.View.versionFile
			versionFileButton:'浏览...',//EwayLocale.version.View.versionFileButton
			versionFileRegexText:'只能上传zip或rar格式的文件',//EwayLocale.version.View.versionFileRegexText
			versionFileUploadMsg:'正在上传文件...',//EwayLocale.version.View.versionFileUploadMsg
			versionFileEmpty:'请将要下发的版本文件(或者文件夹)打包zip或rar格式',//EwayLocale.version.View.versionFileEmpty
			versionTypeCode:'软件分类编码',//EwayLocale.version.View.versionTypeCode
			versionTypeName:'软件分类名称',//EwayLocale.version.View.versionTypeName
			versionTypeId:'版本类型ID',//EwayLocale.version.View.versionTypeId
			versionTypeEmpty:'-请选择版本类型-',//EwayLocale.version.View.versionTypeEmpty
			versionTime:'创建时间',//EwayLocale.version.View.versionTime
			versionNo:'版本号',//EwayLocale.version.View.versionNo
			nowVersionNo:'当前版本号',//EwayLocale.version.View.nowVersionNo
			versionStatus:'版本状态',//EwayLocale.version.View.versionStatus
			versionStatusEmptyText:'全部',
			autoUpdate:'允许自动更新',//EwayLocale.version.View.autoUpdate
			autoUpdateYes:'是',//EwayLocale.version.View.autoUpdateYes
			autoUpdateNo:'否',//EwayLocale.version.View.autoUpdateNo
			autoUpdateEmptyText:'全部',
			dependVersion:'依赖版本',//EwayLocale.version.View.dependVersion
			dependVersionEmptyText:'请选择依赖类型',
			execBefore:'升级前执行脚本',//EwayLocale.version.View.execBefore
			execBeforeEmptyText:'请输入升级包中的以bat或cmd结尾的文件',//EwayLocale.version.View.execBeforeEmptyText
			execBeforeRegexText:'只能输入bat或cmd结尾的文件',//EwayLocale.version.View.execBeforeRegexText
			versionDesc:'版本描述',//EwayLocale.version.View.versionDesc
			versionDescEmpty:'请用文字描述此版本需求(最长20字符串)',//EwayLocale.version.View.versionDescEmpty
			otherConfigTitle:'其他配置',//EwayLocale.version.View.otherConfigTitle
			otherConfigAutoDown:'允许自动更新(当ATM向服务器检查新版本时，允许自动更新的版本才可以返回给ATM)',//EwayLocale.version.View.otherConfigAutoDown
			otherConfigUncompress:'自动解压缩(选中此项时，在ATM端会自动解压缩)&nbsp;<font color="red">注意：如果版本文件本来不符合zip格式，后被压缩成zip时，请选中此项！</font>',//EwayLocale.version.View.otherConfigUncompress
			versionServerPath:'文件在服务器上的位置', //EwayLocale.version.View.versionServerPath
			versionName:'版本名称',//EwayLocale.version.View.versionName
			downloadVersionName:'下发的版本',//EwayLocale.version.View.downloadVersionName
			downloadVersionNameEmpty:'请选择下发的版本',//EwayLocale.version.View.downloadVersionNameEmpty
			distributionPic:'版本分布图',//EwayLocale.version.View.distributionPic
			job:{
				newCreate:'新建',
				running:'运行中',
				scheduler:'计划中',
				ready:'准备运行',
				pause:'暂停',
				complete:'完成'
			}
		},
		VersionInstallInfo:{
			title:'版本安装信息统计图'
		},
		jobPriority:{
			hight:'高',//EwayLocale.version.jobPriority.hight
			middle:'中等',//EwayLocale.version.jobPriority.middle
			general:'普通'//EwayLocale.version.jobPriority.general
		},
		jobType:{
			float:'手工作业(立即下发)',//EwayLocale.version.jobType.float
			fix:'计划作业(定时下发)'//EwayLocale.version.jobType.fix
		},
		taskStatus:{
			news:'新建',//EwayLocale.version.taskStatus.news
			running:'运行中',//EwayLocale.version.taskStatus.running
			noticed:'通知成功',//EwayLocale.version.taskStatus.noticed
			noticedFail:'通知失败',//EwayLocale.version.taskStatus.noticedFail
			downloaded:'已下发',//EwayLocale.version.taskStatus.downloaded
			downloadedFail:'下发失败',//EwayLocale.version.taskStatus.downloadedFail
			deployed:'正在部署',//EwayLocale.version.taskStatus.deployed
			deployedWait:'等待部署',//EwayLocale.version.taskStatus.deployedWait
			deployedFail:'部署失败',//EwayLocale.version.taskStatus.deployedFail
			checked:'部署已确认',//EwayLocale.version.taskStatus.checked
			noticeOk:'已通知应用',//EwayLocale.version.taskStatus.noticeOk
			taskResetSuccessTips:'任务重置成功！',//EwayLocale.version.taskStatus.taskResetSuccessTips
			noticeFail:'通知应用失败'//EwayLocale.version.taskStatus.noticeFail
		},
		versionCatalog:{
			app:'应用程序',//EwayLocale.version.versionCatalog.app
			agent:'监控代理',//EwayLocale.version.versionCatalog.agent
			param:'应用配置'//EwayLocale.version.versionCatalog.param
		},
		download:{
			title:'分发监控',//EwayLocale.version.download.title
			updateTitle:'修改版本下发信息',//EwayLocale.version.download.updateTitle
			taskQueryTips:'根据条件查询选中作业下的详情信息',//EwayLocale.version.download.taskQueryTips
			autoRefresh:'开启自动刷新',//EwayLocale.version.download.autoRefresh
			stopAutoRefresh:'停止自动刷新',//EwayLocale.version.download.stopAutoRefresh
			cancelBatch:'批次取消',//EwayLocale.version.download.cancelBatch
			autoRefreshTips:'刷新周期60秒',//EwayLocale.version.download.autoRefreshTips
			resetTaskStatus:'重置任务状态',//EwayLocale.version.download.resetTaskStatus
			selectTask:'请选择一条任务记录！',//EwayLocale.version.download.selectTask
			selectAllDevice:'全部设备',//EwayLocale.version.download.selectAllDevice
			checkedTaskCantResetTips:'非运行中的任务无法重置！',//EwayLocale.version.download.checkedTaskCantResetTips
			taskExportTips:'导出选中作业下的全部下发结果'//EwayLocale.version.download.taskExportTips
		},
		task:{
			selectJobStartRefresh:'请选择一个作业,再开启定时刷新！',//EwayLocale.version.task.selectJobStartRefresh
			jobBatchName:'作业批次名称',//EwayLocale.version.task.jobBatchName
			patchVersion:'分发版本',//EwayLocale.version.task.patchVersion
			taskStatus:'任务状态',//EwayLocale.version.task.taskStatus
			beforeUpdate:'分发前的版本',//EwayLocale.version.task.beforeUpdate
			exceptVersion:'预期版本',//EwayLocale.version.task.exceptVersion
			actionTime:'执行时间',//EwayLocale.version.task.actionTime
			downSource:'下载源',//EwayLocale.version.task.downSource
			planTime:'计划时间',//EwayLocale.version.task.planTime
			excuteMachine:'执行服务器',//EwayLocale.version.task.excuteMachine
			restartATM:'重启ATM',//EwayLocale.version.task.restartATM
			restartATMTips:'执行重启命令可能存在风险,确认重启?',//EwayLocale.version.task.restartATMTips
			sendRestartCmd:'已发送重启命令！',//EwayLocale.version.task.sendRestartCmd
			cancelDownloadSuccess:'取消下发通知成功！',//EwayLocale.version.task.cancelDownloadSuccess
			cancelDownload:'取消下发',//EwayLocale.version.task.cancelDownload
			jobName:'作业名称',//EwayLocale.version.task.jobName
			jobStatus:'作业状态',//EwayLocale.version.task.jobStatus
			chooseTitleDevice:'选择设备',//EwayLocale.version.task.chooseTitleDevice
			closeWindow:'关闭窗口',//EwayLocale.version.task.closeWindow
			queryByFilter:'根据条件查找',//EwayLocale.version.task.queryByFilter
			displayNumPerPage:'每页显示条数',//EwayLocale.version.task.displayNumPerPage
			targetVersionNo:'目标版本',//EwayLocale.version.task.targetVersionNo
			downloadStatus:'下发状态',//EwayLocale.version.task.downloadStatus
			downloadResult:'下发结果',//EwayLocale.version.task.downloadResult
			cancelJob:'下发结果',//EwayLocale.version.task.cancelJob
			jobId:'作业ID',	//EwayLocale.version.task.jobId
			selectDownloadDevice:'选择下发的设备',	//EwayLocale.version.task.selectDownloadDevice
			versionNoBeforeUpdate:'升级前版本号',	//EwayLocale.version.task.versionNoBeforeUpdate
			versionNoAfterUpdate:'目标版本号',	//EwayLocale.version.task.versionNoAfterUpdate
			deviceVersionHis:'查看设备历史版本',	//EwayLocale.version.task.deviceVersionHis
			downloadUser:'下发人',	//EwayLocale.version.task.downloadUser
			downloadTime:'下发时间',	//EwayLocale.version.task.downloadTime
			downloadResult:'下发结果',	//EwayLocale.version.task.downloadResult
			deviceVersionHisTitle:'设备历史版本信息',	//EwayLocale.version.task.deviceVersionHisTitle
			deviceVersions:'设备版本',	//EwayLocale.version.task.deviceVersions
			deviceVersionHisTip:'查看设备历史版本信息',	//EwayLocale.version.task.deviceVersionHisTip
			autoUpdateInfo:'自动更新信息',//EwayLocale.version.task.autoUpdateInfo
			selectAJob:'请选择一个作业.',//EwayLocale.version.task.selectAJob
			versionDownHisStatusPic:'版本下发历史状态分布图',//EwayLocale.version.task.versionDownHisStatusPic
			versionNoPic:'版本分布图',//EwayLocale.version.task.versionNoPic
			cantCancelCompleteJob:'不能撤销"完成"状态的作业.',//EwayLocale.version.task.cantCancelCompleteJob
			doSureCancelTheJob:'是否真的要撤销指定的作业?(正在运行的作业只会撤销还没有运行的任务.)',//EwayLocale.version.task.doSureCancelTheJob
			deleting:'正在删除......',//EwayLocale.version.task.deleting
			cancelSuccessBut:'已经成功撤销作业中还没有运行的任务,此时作业的状态仍然是"运行中",请稍等后刷新作业列表.',//EwayLocale.version.task.cancelSuccessBut
			cancelJobSuccess:'成功撤销作业',//EwayLocale.version.task.cancelJobSuccess
			updateResult:'升级结果'	//EwayLocale.version.task.updateResult
		}
	},


	//**********************************************************/
	agent:{
		remote:{
			screen:{
				message:'信息',
				startcustom:'开始录制客户前屏',
				stopcustom:'停止录制客户前屏',
				startadmin:'开始录制管理后屏',
				stopadmin: '停止录制管理后屏',
				startadvertise: '开始录制广告屏',
				stopadvertise: '停止录制广告屏',
				startCameraDate: '开始录制时间',
				stopCameraDate: '结束录制时间',
				monitorType: '屏幕类型',
				fileNameClient: '文件名称',
				nowCamera:'正在录制...',
				finishCamera:'完成录制',
				videoLoad:'正在将视频文件下载至服务端...',
				stopManage:'自动停止.如需取得视频文件,请联系管理员!',
				manage: '操作',
				loading:'下载',//EwayLocale.agent.remote.screen.loading
				screenCamera: '屏幕录制',
			},
			discInfo: '磁盘信息',
			discName: '磁盘分区名称',
			fileSys: '磁盘文件系统',
			totalSize: '磁盘总大小',
			freeSize: '磁盘可用空间大小',
			networkInfo:'网络连接信息',
			conenctRate: '连接速率',
			receivedByte: '接收到的字节数',
			sendByte: '发动的字节数',
			loadData: '加载数据中，请稍候...',
			refresh: '刷新',
			name: '名称',//EwayLocale.agent.remote.loading
			format: '格式',
			totalSize: '总大小',
			freeSize: '可用空间',
			refreshFailure:'刷新失败！',
			back:'返回',
			upload: '上传',
			Mkdir: '新建文件夹',
			MkFile: '新建文件',
			MKcatalog:'新建目录',
			catalogName:'目录名称',
			remove: '删除',
			execute: '执行',
			path: '路径',
			search: '搜索',
			size: '大小',
			fileSize:'文件大小：',
			lastTime: '最后修改时间',
			clickLoadFile:'单击即可下载该文件',
			loadFileSize:'下载文件 不能超过200M！',
			nowLoadFile:'正在下载文件......',
			judgeLoad: '是否续传下载！',
			loadFailure:'下载失败！',
			number: '编号',
			programName: '程序名称',
			version: '版本号',
			publisher: '发布商',
			diskUsed: '磁盘使用',
			softwayList: '软件安装列表',
			networkInfo: '网络信息',
			networkLinkStatus: '网络连接状态',
			send: '已发送',
			receive: '已接收',
			bite: '字节:',
			speed: '速度(Mbps):',
			bandWidth: '宽带测速',
			unit: '单位：MB',
			againTest: '重新测试',
			impressionName: '印象名称',
			userName: '用户名',
			memoryRate: '内存使用',
			systemProgressInfo:'系统进程信息',
			screenShotTime: '截屏时间',
			distanceScreen: '远程抓屏',
			distanceExplorer: '远程浏览',
			ATMExamination:'ATM体检',
			checkATM: '重新体检',
			ATMExamInfo: 'ATM体检详情',
			cpuIdle: 'CPU空闲率',
			memoryIdle: '内存空闲率',
			hardDiskIdle: '硬盘空闲率',
			uploadFile: '上传文件',
			rules:'不能包含一下字符:\/?*":<>|',
			nowCreat:'正在新建...',
			nowPath: '当前路径',
			confirm: '保存',
			reset: '重置',
			back: '返回',
			prepareFile:'待上传文件',
			choseUploadFile:'请选择上传文件',
			nowUploadFile:'正在上传文件...',
			explorer: '浏览...',
			serverPath: '文件在服务器上的位置',
			distanceSuccess:'远程创建成功.',
			distanceFailure:'远程创建失败.',
			confirmDelete:'确定删除',
			confirmExecute:'确定执行',
			choseDeleteFile:'请选择要删除的文件.',
			distanceExecuteSuccess:'远程执行成功.',
			distanceExecuteFailure:'远程执行失败.',
			choseExecuteFile:'请选择要执行的文件.',
			distanceExplorer:'远程浏览:',
			distanceExplorerFailure:'远程浏览失败.',
			fileExist:'该文件已存在！',
			nowUploadFile:'正在上传文件......',
			uploadSuccess:'上传成功.',
			yes: "续传",
			no: "覆盖",
			cancel: "取消",
			choseFile:'未选择上传文件,请选择文件.',
			returnFailure:'返回失败.',
			refreshFailure:'刷新失败.',
			catalogExist:'搜索的目录不存在,请重新输入.',
			testBandWidth:'测试宽带异常.',
			networkMaxSpeed:'网络最大接入速度为',
			minutes:'秒',
			amount:'相当于',
			specialLine:'专线',
			bandWidth:'宽带',
			handle:'正在处理......',
			offServer:'与服务器断开连接.',//EwayLocale.agent.offServer
			submitingWaiting:'正在提交,请稍等...',//EwayLocale.agent.submitingWaiting
			ATMCheck:'正在ATM体检中...',
			excellent:'优',
			fine:'良',
			middle:'中',
			bad:'差',
			point:'分',
			checkFailure:'ATM体检失败,请重新操作.',
			checkVersionInfo:'查看版本信息',
			versionInfo:'您要查看的版本信息如下:',
			ATMCVersion:'ATMC应用版本',
			monitorVersion: '监控客户端版本',

		}
	},
	commen:{

		jobNum:'工号',//EwayLocale.commen.jobNum
		name:'姓名',//EwayLocale.commen.name
		personJobName:'岗位',//EwayLocale.commen.personJobName
		state:'状态',//EwayLocale.commen.state
		birthday:'生日',//EwayLocale.commen.birthday
		comboxStatus:{
			onJob:'在岗',//EwayLocale.commen.comboxStatus.onJob
			onAdjust:'调休',//EwayLocale.commen.comboxStatus.onAdjust
			onVacation:'休假',//EwayLocale.commen.comboxStatus.onVacation
			other:'其他',//EwayLocale.commen.comboxStatus.other
			dredge:'开通',//EwayLocale.commen.comboxStatus.dredge
			open:'开通',//EwayLocale.commen.comboxStatus.open
			close:'停用',//EwayLocale.commen.comboxStatus.close
			pastDue:'过期',//EwayLocale.commen.comboxStatus.pastDue
			pastDueSoon:'即将过期',//EwayLocale.commen.comboxStatus.pastDueSoon
		},
		type:'类型',//EwayLocale.commen.type
		comboxType:{
			machineManager:'管机员',//EwayLocale.commen.comboxType.machineManager
			machineRepairer:'维修人员'//EwayLocale.commen.comboxType.machineRepairer
		},
		mobile:'手机',//EwayLocale.commen.mobile
		email:'邮箱',//EwayLocale.commen.email
		phone:'固话',//EwayLocale.commen.phone
		gender:'性别',//EwayLocale.commen.gender
		all:'全部',//EwayLocale.commen.all
		comboxGender:{
			male:'男',//EwayLocale.commen.comboxGender.male
			female:'女',//EwayLocale.commen.comboxGender.female
			unknow:'未知'//EwayLocale.commen.comboxGender.unknow
		},
		remark:'备注',//EwayLocale.commen.remark
		terminalId:'设备号',//EwayLocale.commen.terminalId
		ip:'网络地址',//EwayLocale.commen.ip
		orgNameBelongs:'所属机构',//EwayLocale.commen.orgNameBelongs
		devTypeName:'设备型号',//EwayLocale.commen.devTypeName
		devVendorName:'设备品牌',//EwayLocale.commen.devVendorName
		devCatalogName:'设备类型',//EwayLocale.commen.devCatalogName
		devStatus:'设备状态',//EwayLocale.commen.devStatus
		comboxDevStatus:{
			upOpen:'未开通',//EwayLocale.commen.comboxDevStatus.upOpen
			open:'开通',//EwayLocale.commen.comboxDevStatus.open
			stop:'停用',//EwayLocale.commen.comboxDevStatus.stop
			scrapped:'报废'//EwayLocale.commen.comboxDevStatus.Scrapped
		},
		setManager:'设置',//EwayLocale.commen.setManager
		devServiceName:'设备维护商',//EwayLocale.commen.devServiceName
		cashboxLimit:'钱箱报警金额(单位：张数)',//EwayLocale.commen.cashboxLimit
		installDate:'安装日期',//EwayLocale.commen.installDate
		address:'地址',//EwayLocale.commen.address
		areaCode:'区域编号',//EwayLocale.commen.areaCode
		areaName:'区域名称',//EwayLocale.commen.areaName
		toolbar:'总共：{2}条，显示{0}-{1}',//EwayLocale.commen.toolbar
		bindMachine :'已关联的设备',//EwayLocale.commen.bindMachine
		lift:'解除',//EwayLocale.commen.lift
		canBindMachine:'可关联的设备',//EwayLocale.commen.canBindMachine
		bind:'关联',//EwayLocale.commen.bind
		filter:'过滤条件',//EwayLocale.commen.filter
		stateDict:{
			newCreate:'新建',//EwayLocale.commen.stateDict.newCreate
			normal:'正常',//EwayLocale.commen.stateDict.normal
			locked:'锁定',//EwayLocale.commen.stateDict.locked
			disable:'无效',//EwayLocale.commen.stateDict.disable
			frozen:'冻结',//EwayLocale.commen.stateDict.frozen
			deleted:'已删除'//EwayLocale.commen.stateDict.deleted
		},
		yes:'是',//EwayLocale.commen.yes
		no:'否',//EwayLocale.commen.no
		selectAll:'全部选择',//EwayLocale.commen.selectAll
		selectNon:'全部不选',//EwayLocale.commen.selectNon
		content:'消息',//EwayLocale.commen.content
		upgrade:'上级',//EwayLocale.commen.upgrade
		port:'网络Port',//EwayLocale.commen.port
		previous:'上一页',//EwayLocale.commen.previous
		next:'下一页',//EwayLocale.commen.next
		installAddr:'装机地址',//EwayLocale.commen.installAddr
		seviceMode:'经营方式',//EwayLocale.commen.seviceMode
		insideOutside:'在行标志',//EwayLocale.commen.insideOutside
		appVersion:'应用版本号',//EwayLocale.commen.appVersion
		devInfo:'设备基本信息',//EwayLocale.commen.devInfo
		//check end
		personnel:'联系人',//EwayLocale.commen.personnel
		warn:'警告',//EwayLocale.commen.warn
		fatal:'故障',//EwayLocale.commen.fatal
		unStable:'不稳定',//EwayLocale.commen.unStable
		unknow:'未知',//EwayLocale.commen.unknow
		description:'描述',//EwayLocale.commen.description
		info:'详细信息',//EwayLocale.commen.info
		startDataTime:'开始时间',//EwayLocale.commen.startDataTime
		endDataTime:'结束时间',//EwayLocale.commen.endDataTime
		year:'年',//EwayLocale.commen.year
		month:'月',//EwayLocale.commen.month
		day:'日',//EwayLocale.commen.day
		yearTime:'年份',//EwayLocale.commen.yearTime
		monthTime:'月份',//EwayLocale.commen.monthTime
		dayTime:'日期',//EwayLocale.commen.dayTime
		orgFramework:'组织机构',//EwayLocale.commen.orgFramework
		matchOrg:'匹配机构',//EwayLocale.commen.matchOrg
		orgID:'机构ID',//EwayLocale.commen.orgID
		endValidty:'截止有效期',//EwayLocale.commen.endValidty
		publishDate:'发布日期',//EwayLocale.commen.publishDate
		announceTheme:'公告主题',//EwayLocale.commen.announceTheme



	},
	//**********************************************************/
	person:{
		bankOrg :{
			title:'银行机构管理',//EwayLocale.person.bankOrg.title
			moveBankTitle:'组织迁移',//EwayLocale.person.bankOrg.moveBankTitle
			updateBankTitle:'更改银行机构信息',//EwayLocale.person.bankOrg.updateBankTitle
			addBankOrgTitle:'增加银行机构信息',//EwayLocale.person.bankOrg.addBankOrgTitle
			code:'机构编号',//EwayLocale.person.bankOrg.code
			orgType:'机构类型',//EwayLocale.person.bankOrg.orgType
			name:'机构名称',//EwayLocale.person.bankOrg.name
			orgLevel:'机构级别',//EwayLocale.person.bankOrg.orgLevel
			orgNavi:'机构导航',//EwayLocale.person.bankOrg.orgNavi
			zip:'邮政编码',//EwayLocale.person.bankOrg.zip
			removeManager:'删除',//EwayLocale.person.bankOrg.removeManager
			manager:'管理员',//EwayLocale.person.bankOrg.manager
			address:'机构地址',//EwayLocale.person.bankOrg.address
			upgradeOrg:'上级机构',//EwayLocale.person.bankOrg.upgradeOrg
			description:'机构描述',//EwayLocale.person.bankOrg.description
			serOrganization:'维护商描述',//EwayLocale.person.bankOrg.serOrganization
			organizationType:{
				bank:'银行',//EwayLocale.person.bankOrg.organizationType.bank
				serviceOrg:'维护商'//EwayLocale.person.bankOrg.organizationType.serviceOrg
			},
			organizationLevelDict:{
				rootBank:'总行',//EwayLocale.person.bankOrg.organizationLevelDict.rootBank
				branchBank:'分行',//EwayLocale.person.bankOrg.organizationLevelDict.branchBank
				tagBranchBank:'支行',//EwayLocale.person.bankOrg.organizationLevelDict.tagBranchBank
				netBank:'网点'//EwayLocale.person.bankOrg.organizationLevelDict.netBank
			},
			addOrgTitle:'该机构人员信息',//EwayLocale.person.bankOrg.addOrgTitle
			linkPeronTitle:'当前机构下人员：',//EwayLocale.person.bankOrg.linkPeronTitle
			personList:'人员列表',//EwayLocale.person.bankOrg.personList
			orgLinkTitle:'该机构的关联设备和人员',//EwayLocale.person.bankOrg.orgLinkTitle
			machineOrg:' 该机构下设备',//EwayLocale.person.bankOrg.machineOrg
			personOrg:'该机构下人员'//EwayLocale.person.bankOrg.personOrg

		},
		bankPer :{
			title:'银行人员管理',//EwayLocale.person.bankPer.title
			organizationName:'机构',//EwayLocale.person.bankPer.organizationName
			orgNavi:'机构导航',//EwayLocale.person.bankPer.orgNavi
			addBankPerTitle:'增加银行人员信息',//EwayLocale.person.bankPer.addBankPerTitle
			updateBankPerTitle:'更改银行人员信息'//EwayLocale.person.bankPer.updateBankPerTitle

		},
		serviceOrg:{
			title:'维护商管理',//EwayLocale.person.serviceOrg.title
			serviceNavi:'厂商导航',//EwayLocale.person.serviceOrg.serviceNavi
			serviceOrgAdmin:'管理员',//EwayLocale.person.serviceOrg.serviceOrgAdmin
			setManager:'设置',//EwayLocale.person.serviceOrg.setManager
			removeManager:'删除',//EwayLocale.person.serviceOrg.removeManager
			code:'厂商编号',//EwayLocale.person.serviceOrg.code
			name:'厂商名称',//EwayLocale.person.serviceOrg.name
			zip:'邮政编码',//EwayLocale.person.serviceOrg.zip
			shortName:'厂商',//EwayLocale.person.serviceOrg.shortName
			address:'厂商地址',//EwayLocale.person.serviceOrg.address
			description:'厂商描述',//EwayLocale.person.serviceOrg.description
			addServiceTitle:'增加维护商信息',//EwayLocale.person.serviceOrg.addServiceTitle
			upgradeService:'上级厂商',//EwayLocale.person.serviceOrg.upgradeService
			updateServiceTitle:'更改维护商信息',//EwayLocale.person.serviceOrg.updateServiceTitle
			personDevSerLink:'该厂商的关联设备和人员',//EwayLocale.person.serviceOrg.personDevSerLink
			devSerLink:'该厂商下设备',//EwayLocale.person.serviceOrg.devSerLink
			personSerLink:'该厂商下人员',//EwayLocale.person.serviceOrg.personSerLink

			directOrganization:'的直接下级机构'
		},
		servicePer:{
			title:'维护人员管理',//EwayLocale.person.servicePer.title
			servicePerlink:'关联设备',//EwayLocale.person.servicePer.servicePerlink
			addServicePerTitle:'增加维护人员信息',//EwayLocale.person.servicePer.addServicePerTitle
			updateServicePerTitle:'更改维护人员信息',//EwayLocale.person.servicePer.updateServicePerTitle

			maintainInfo:'所有维护商人员信息'
		},
		user:{
			title:'用户管理',//EwayLocale.person.user.title
			code:'用户名',//EwayLocale.person.user.code
			clickToCheckLog:'单击即可查看用户 ',//EwayLocale.person.user.clickToCheckLog
			userLog:' 的操作日志',//EwayLocale.person.user.userLog
			resetPasswd:'密码重置',//EwayLocale.person.user.resetPasswd
			clickToPasswdInit:'单击即可密码重置为初始化密码',//EwayLocale.person.user.clickToPasswdInit
			clickToRole:'单击即可查看所有角色列表',//EwayLocale.person.user.clickToRole
			addUserTitle:'增加用户信息',//EwayLocale.person.user.addUserTitle
			clickToUser:'请点击查询，选择人员',//EwayLocale.person.user.clickToUser
			userType:'用户类型',//EwayLocale.person.user.userType
			role:'角色',//EwayLocale.person.user.role
			roleGiven:'角色赋予',//EwayLocale.person.user.roleGiven
			roleName:'角色名称',//EwayLocale.person.user.roleName
			roleDescription:'角色描述',//EwayLocale.person.user.roleDescription
			userListTitle:'人员列表',//EwayLocale.person.user.userListTitle
			updateUserTitle:'更改用户角色（使用拖拽的方式）',//EwayLocale.person.user.updateUserTitle
			roleCanBeAdd:'可添加的角色列表',//EwayLocale.person.user.roleCanBeAdd
			roleAlreadyBeAdd:'已添加的角色列表',//EwayLocale.person.user.roleAlreadyBeAdd
			operCode:'操作人编号',//EwayLocale.person.user.operCode
			operName:'操作人姓名',//EwayLocale.person.user.operName
			operTime:'操作时间',//EwayLocale.person.user.operTime
			operResult:'操作结果',//EwayLocale.person.user.operResult
			operContent:'操作内容',//EwayLocale.person.user.operContent
			roleListTitle:'用户角色列表',//EwayLocale.person.user.roleListTitle
			operTitle:'操作员日志',//EwayLocale.person.user.operTitle
			operDetailTitle:'操作日志信息',//EwayLocale.person.user.operDetailTitle
			operLogList:'操作日志列表',//EwayLocale.person.user.operLogList
			personDevice:'人员<-->设备',//EwayLocale.person.user.personDevice
			rootUser:'超级用户',//EwayLocale.person.user.rootUser
			generalUser:'普通用户'//EwayLocale.person.user.generalUser
		}

	},

	//**********************************************************/
	permission:{
		systemMenu:'系统菜单',
		role:{
			title:'角色管理',//EwayLocale.permission.role.title
			update:'更改角色',//EwayLocale.permission.role.update
			name:'角色名称',//EwayLocale.permission.role.name
			type:'角色类型',//EwayLocale.permission.role.type
			description:'角色描述',//EwayLocale.permission.role.description
			isSysRole:'是否是系统内置角色',//EwayLocale.permission.role.isSysRole
			chooseRight:'请选择菜单权限',//EwayLocale.permission.role.chooseRight
			add:'增加角色'//EwayLocale.permission.role.add
		},
		permission:{
			menuName:'菜单名称',//EwayLocale.permission.permission.menuName
			menuDescription:'菜单描述',//EwayLocale.permission.permission.menuDescription
			menuPermission:'菜单权限'//EwayLocale.permission.permission.menuPermission
		}
	},

	//**********************************************************/
	monitor:{
		summary:{
			title:'监控总览',//EwayLocale.monitor.summary.title
			allSummary:'整体状态概况',//EwayLocale.monitor.summary.allSummary
			appSummary:'应用状态概况',//EwayLocale.monitor.summary.appSummary
			modSummary:'模块状态概况',//EwayLocale.monitor.summary.modSummary
			boxSummary:'钞箱状态概况',//EwayLocale.monitor.summary.boxSummary
			netSummary:'网络状态概况'//EwayLocale.monitor.summary.netSummary
		},
		devMonitor:{
			title:'状态监控',//EwayLocale.monitor.devMonitor.title
			comboxStatus:{
				runStatus:'运行状态',//EwayLocale.monitor.devMonitor.comboxStatus.runStatus
				modStatus:'模块状态',//EwayLocale.monitor.devMonitor.comboxStatus.modStatus
				boxStatus:'钞箱状态',//EwayLocale.monitor.devMonitor.comboxStatus.boxStatus
				netStatus:'网络状态'//EwayLocale.monitor.devMonitor.comboxStatus.netStatus
			},
			monitorState:'订阅条件',//EwayLocale.monitor.devMonitor.monitorState
			showWay:'展示方式',//EwayLocale.monitor.devMonitor.showWay
			comboxShowWay:{
				summaryPattern:'全局模式',//EwayLocale.monitor.devMonitor.comboxShowWay.summaryPattern
				matrixPattern:'矩形方式',//EwayLocale.monitor.devMonitor.comboxShowWay.matrixPattern
				maxIconPattern:'超大图标',//EwayLocale.monitor.devMonitor.comboxShowWay.maxIconPattern
				listPattern:'列表方式',//EwayLocale.monitor.devMonitor.comboxShowWay.listPattern
				boxPattern:'钞箱方式'//EwayLocale.monitor.devMonitor.comboxShowWay.boxPattern
			},
			numberfield:'监控台数',//EwayLocale.monitor.devMonitor.numberfield
			noData:'无记录',//EwayLocale.monitor.devMonitor.noData
			retainCardCount:'当前吞卡数量',//EwayLocale.monitor.devMonitor.retainCardCount
			cash:{
				boxInitCount:'钞箱初始金额',//EwayLocale.monitor.devMonitor.cash.boxInitCount
				boxCurrentCount:'钞箱当前金额',//EwayLocale.monitor.devMonitor.cash.boxCurrentCount
				cashboxLimit:'钞箱报警金额阈值',//EwayLocale.monitor.devMonitor.cash.cashboxLimit
				initAmount:'加钞总金额',//EwayLocale.monitor.devMonitor.cash.initAmount
				amount:'剩钞金额',//EwayLocale.monitor.devMonitor.cash.amount
				dispenseAmount:'出钞总金额',//EwayLocale.monitor.devMonitor.cash.dispenseAmount
				rejectAmount:'废钞金额',//EwayLocale.monitor.devMonitor.cash.rejectAmount
				retractCount:'钞票回收次数',//EwayLocale.monitor.devMonitor.cash.retractCount
				minAmount:'最小取款金额',//EwayLocale.monitor.devMonitor.cash.minAmount
				boxId:'钞箱标识',//EwayLocale.monitor.devMonitor.cash.boxId
				type:'钞箱类型',//EwayLocale.monitor.devMonitor.cash.type
				initialCount:'初始张数',//EwayLocale.monitor.devMonitor.cash.initialCount
				cashInCount:'存款张数',//EwayLocale.monitor.devMonitor.cash.cashInCount
				currentCount:'当前计数[张/笔]',//EwayLocale.monitor.devMonitor.cash.currentCount
				noteValue:'钞箱面值',//EwayLocale.monitor.devMonitor.cash.noteValue
				currency:'钞箱币种',//EwayLocale.monitor.devMonitor.cash.currency
				boxDetail:'钞箱详情',//EwayLocale.monitor.devMonitor.cash.boxDetail
				cimFull:'存款钞满',//EwayLocale.monitor.devMonitor.cash.cimFull
				cdmEmpty:'取款钞空',//EwayLocale.monitor.devMonitor.cash.cdmEmpty
				cdmLow:'取款钞少',//EwayLocale.monitor.devMonitor.cash.cdmLow
				low:'钞少',//EwayLocale.monitor.devMonitor.cash.low
				empty:'钞空',//EwayLocale.monitor.devMonitor.cash.empty
				cimAFull:'存款钞将满',//EwayLocale.monitor.devMonitor.cash.cimAFull
				cashFault:'钞箱故障',//EwayLocale.monitor.devMonitor.cash.cashFault
				cashUnknow:'钞箱未知'//EwayLocale.monitor.devMonitor.cash.cashUnknow

			},
			modStateGraphic:'模块状态图示',//EwayLocale.monitor.devMonitor.modStateGraphic
			modGraphic:'模块图示',//EwayLocale.monitor.devMonitor.modGraphic
			registerStatus:'注册状态',//EwayLocale.monitor.devMonitor.registerStatus
			devModStatus:'设备模块状态',//EwayLocale.monitor.devMonitor.devModStatus
			mod:{
				idc:'读卡器',//EwayLocale.monitor.devMonitor.mod.idc
				jpr:'日志打印机',//EwayLocale.monitor.devMonitor.mod.jpr
				cdm:'取款模块',//EwayLocale.monitor.devMonitor.mod.cdm
				cim:'存款模块',//EwayLocale.monitor.devMonitor.mod.cim
				siu:'传感器',//EwayLocale.monitor.devMonitor.mod.siu
				rpr:'凭条打印机',//EwayLocale.monitor.devMonitor.mod.rpr
				pin:'密码键盘',//EwayLocale.monitor.devMonitor.mod.pin
				ttu:'文本终端',//EwayLocale.monitor.devMonitor.mod.ttu
				isc:'身份证扫描仪',//EwayLocale.monitor.devMonitor.mod.isc
				icc:'发卡器',//EwayLocale.monitor.devMonitor.mod.icc
				fgp:'指纹仪',//EwayLocale.monitor.devMonitor.mod.fgp
				healthy:'模块正常'//EwayLocale.monitor.devMonitor.mod.healthy

			},
			remote:{
				control:'远程控制',//EwayLocale.monitor.devMonitor.remote.control
				screen:'远程抓屏',//EwayLocale.monitor.devMonitor.remote.screen
				log:'提取电子日志',//EwayLocale.monitor.devMonitor.remote.log
				net:'查看网络连接',//EwayLocale.monitor.devMonitor.remote.net
				softwareList:'获取软件列表',//EwayLocale.monitor.devMonitor.remote.softwareList
				powerOff:'关机',//EwayLocale.monitor.devMonitor.remote.powerOff
				closeWays:'请选择关机方式',//EwayLocale.monitor.devMonitor.remote.closeWays
				restart:'重启',//EwayLocale.monitor.devMonitor.remote.restart
				restartWay:'请选择重启方式',//EwayLocale.monitor.devMonitor.remote.restartWay
				logicOpen:'开启服务',//EwayLocale.monitor.devMonitor.remote.logicOpen
				logicClose:'暂停服务',//EwayLocale.monitor.devMonitor.remote.logicClose
				remoteBrowser:'远程浏览',//EwayLocale.monitor.devMonitor.remote.remoteBrowser
				processList:'查看进程信息',//EwayLocale.monitor.devMonitor.remote.processList
				screenCamera:'屏幕录制',//EwayLocale.monitor.devMonitor.remote.screenCamera
				reset:'强制复位',//EwayLocale.monitor.devMonitor.remote.reset
				remoteLook:'查看应用版本',//EwayLocale.monitor.devMonitor.remote.remoteLook
				remoteCheckATM:'ATM体检',//EwayLocale.monitor.devMonitor.remote.remoteCheckATM
				halfSer:'半功能',//EwayLocale.monitor.devMonitor.remote.halfSer
				healthy:'正常服务',//EwayLocale.monitor.devMonitor.remote.healthy
				staff:'维护',//EwayLocale.monitor.devMonitor.remote.staff
				pFault:'交易前置故障',//EwayLocale.monitor.devMonitor.remote.pFault
				stop:'报停',//EwayLocale.monitor.devMonitor.remote.stop
				manualStop:'人工报停',////EwayLocale.monitor.devMonitor.remote.manualStop
				stopFault:'暂停服务-模块故障',//EwayLocale.monitor.devMonitor.remote.stopFault
				stopCash:'暂停服务-未加钞',//EwayLocale.monitor.devMonitor.remote.stopCash
				pauseSer:'暂停服务',//EwayLocale.monitor.devMonitor.remote.pauseSer
				pauseCash:'现金暂停',//EwayLocale.monitor.devMonitor.remote.pauseCash   ----中文不明，我猜的----
				pauseSerUnknow:'未知原因暂停服务',//EwayLocale.monitor.devMonitor.remote.pauseSerUnknow
				manaAndstaff:'管机员'//EwayLocale.monitor.devMonitor.remote.manaAndstaff
			},
			atmGroup:'分组',//EwayLocale.monitor.devMonitor.atmGroup
			atmGroupTip:'分组详情',//EwayLocale.monitor.devMonitor.atmGroupTip
			solution:'建议解决方案',//EwayLocale.monitor.devMonitor.solution
			faultDescription:'模块故障描述',//EwayLocale.monitor.devMonitor.faultDescription
			fastChoose:'快捷选择',//EwayLocale.monitor.devMonitor.fastChoose
			init:'初始化',//EwayLocale.monitor.devMonitor.init
			accTrans:'客户交易',//EwayLocale.monitor.devMonitor.accTrans
			factureStaff:'厂商模式维护',//EwayLocale.monitor.devMonitor.factureStaff
			netHealthy:'网络正常',//EwayLocale.monitor.devMonitor.netHealthy
			netUnStable:'网络不稳定',//EwayLocale.monitor.devMonitor.netUnStable
			netFatal:'网络故障',//EwayLocale.monitor.devMonitor.netFatal
			filterManager: {
				title: '订阅条件管理',//EwayLocale.monitor.devMonitor.filterManager.title
				add: '创建订阅条件',//EwayLocale.monitor.devMonitor.filterManager.add
				update: '修改订阅条件',//EwayLocale.monitor.devMonitor.filterManager.update
				filterForm: {
					filterName: '订阅名称'//EwayLocale.monitor.devMonitor.filterManager.filterForm.filterName
				}
			}
		},
		business:{
			transaction:{
				card:'交易卡号',//EwayLocale.monitor.business.transaction.card
				dateTime:'交易时间',//EwayLocale.monitor.business.transaction.dateTime
				transCode:'交易类型',//EwayLocale.monitor.business.transaction.transCode
				amt:'交易金额',//EwayLocale.monitor.business.transaction.amt
				currency:'交易币种',//EwayLocale.monitor.business.transaction.currency
				transId:'交易流水号',//EwayLocale.monitor.business.transaction.transId
				amtfield:'金额范围',//EwayLocale.monitor.business.transaction.amtfield
				toNum:'至',//EwayLocale.monitor.business.transaction.toNum
				transContainer:'交易时间段',//EwayLocale.monitor.business.transaction.transContainer
				debitAccountOrCard:'客户账号或者卡号',//EwayLocale.monitor.business.transaction.debitAccountOrCard
				creditAccountOrCard:'对方账号或者卡号',//EwayLocale.monitor.business.transaction.creditAccountOrCard
				debitAccount:'客户帐号',//EwayLocale.monitor.business.transaction.debitAccount
				creditAccount:'对方账号',//EwayLocale.monitor.business.transaction.creditAccount
				localRet:'ATMC本地代码',//EwayLocale.monitor.business.transaction.localRet
				hostRet:'主机返回码',//EwayLocale.monitor.business.transaction.hostRet
				userName:'用户姓名',//EwayLocale.monitor.business.transaction.userName
				historyTransaction:{
					title:'历史交易查询'//EwayLocale.monitor.business.transaction.historyTransaction.title

				},
				transactionMonitor:{
					title:'实时交易监控',//EwayLocale.monitor.business.transaction.transactionMonitor.title
					begin:'开始监控',//EwayLocale.monitor.business.transaction.transactionMonitor.begin
					stop:'停止监控',//EwayLocale.monitor.business.transaction.transactionMonitor.stop
					clear:'清屏'//EwayLocale.monitor.business.transaction.transactionMonitor.clear
					}
			},
			blackList:{
				title:'黑名单卡管理',//EwayLocale.monitor.business.blackList.title
				black:'黑名单卡',//EwayLocale.monitor.business.blackList.black
				addBlack:'添加黑名单卡',//EwayLocale.monitor.business.blackList.addBlack
				cardBank:'所属银行',//EwayLocale.monitor.business.blackList.cardBank
				importData:'批量导入',//EwayLocale.monitor.business.blackList.importData
				addDate:'添加日期',//EwayLocale.monitor.business.blackList.addDate
				importTitle:'批量导入黑名单卡',//EwayLocale.monitor.business.blackList.importTitle
				importFile:'导入文件',//EwayLocale.monitor.business.blackList.importFile
				chooseFileRegex:'请选择导入文件,只支持.xls和.xlsx格式的文件',//EwayLocale.monitor.business.blackList.chooseFileRegex
				fileRegex:'只支持.xls和.xlsx格式的文件',//EwayLocale.monitor.business.blackList.fileRegex
				tempDownload:'模版下载',//EwayLocale.monitor.business.blackList.tempDownload
				importNow:'导入',//EwayLocale.monitor.business.blackList.importNow
				updateTitle:'更改黑名单卡信息'//EwayLocale.monitor.business.blackList.updateTitle
			},
			card:{
				title:'吞卡管理',//EwayLocale.monitor.business.card.title
				addTitle:'增加吞卡信息',//EwayLocale.monitor.business.card.addTitle
				time:'吞卡时间',//EwayLocale.monitor.business.card.time
				reason:'吞卡原因',//EwayLocale.monitor.business.card.reason
				destroy:'吞卡销毁',//EwayLocale.monitor.business.card.destory
				cardHolder:'发卡行',//EwayLocale.monitor.business.card.cardHolder
				cardRegex:'允许的最大长度为',//EwayLocale.monitor.business.card.cardRegex
				beginEndDate:'吞卡起始日期不能大于吞卡截止日期,请重新选择',//EwayLocale.monitor.business.card.beginEndDate
				orgBelongs:'所属机构 (包含处理机构)',//EwayLocale.monitor.business.card.orgBelongs
				beginTime:'开始时间',//EwayLocale.monitor.business.card.beginTime
				endTime:'结束时间',//EwayLocale.monitor.business.card.endTime
				accGetCard:'客户领卡',//EwayLocale.monitor.business.card.accGetCard
				transferCard:'卡片移交',//EwayLocale.monitor.business.card.transferCard
				processOrg:'处理机构',//EwayLocale.monitor.business.card.processOrg
				type:'吞卡类型',//EwayLocale.monitor.business.card.type
				manual:'手动添加',//EwayLocale.monitor.business.card.manual
				auto:'自动添加',//EwayLocale.monitor.business.card.auto
				comboxStatus:{
					wait:'待领',//EwayLocale.monitor.business.card.comboxStatus.wait
					received:'已领',//EwayLocale.monitor.business.card.comboxStatus.received
					destroy:'销毁',//EwayLocale.monitor.business.card.comboxStatus.destroy
					bringed:'调出'//EwayLocale.monitor.business.card.comboxStatus.bringed
				},
				treatmentPeople:'处理人员',//EwayLocale.monitor.business.card.treatmentPeople
				treatmentTime:'处理时间',//EwayLocale.monitor.business.card.treatmentTime
				customerName:'客户姓名',//EwayLocale.monitor.business.card.customerName
				customerPhone:'客户电话',//EwayLocale.monitor.business.card.customerPhone
				customerPapers:'客户证件号',//EwayLocale.monitor.business.card.customerPapers
				processCard:'吞卡处理',//EwayLocale.monitor.business.card.processCard
				destroyCard:'卡片销毁',//EwayLocale.monitor.business.card.destroyCard
				exportData:'导出',//EwayLocale.monitor.business.card.exportData
				paperType:'证件类型',//EwayLocale.monitor.business.card.paperType
				paperCode:'证件号',//EwayLocale.monitor.business.card.paperCode
				idCard:'身份证',//EwayLocale.monitor.business.card.idCard
				accountPaper:'户口本',//EwayLocale.monitor.business.card.accountPaper
				drivePaper:'驾驶执照',//EwayLocale.monitor.business.card.drivePaper
				passport:'护照',//EwayLocale.monitor.business.card.passport
				officer:'军官证',//EwayLocale.monitor.business.card.officer
				soldier:'士兵证',//EwayLocale.monitor.business.card.soldier
				busnessPaper:'法人营业证',//EwayLocale.monitor.business.card.busnessPaper
				busnessCode:'法人代码证',//EwayLocale.monitor.business.card.busnessCode
				taxPaper:'税务登记证',//EwayLocale.monitor.business.card.taxPaper
				withDev:'按设备'//EwayLocale.monitor.business.card.withDev
			},


			cashInit:{
				titile:'加钞信息查询',//EwayLocale.monitor.business.cashInit.titile
				uuId:'加钞ID',//EwayLocale.monitor.business.cashInit.uuId
				date:'加钞日期',//EwayLocale.monitor.business.cashInit.date
				amt:'加钞金额',//EwayLocale.monitor.business.cashInit.amt
				info:'加钞详细信息',//EwayLocale.monitor.business.cashInit.info
				boxId:'钞箱ID',//EwayLocale.monitor.business.cashInit.boxId
				boxCurrency:'币种',//EwayLocale.monitor.business.cashInit.boxCurrency
				boxInitAmt:'初始金额',//EwayLocale.monitor.business.cashInit.boxInitAmt
				lastAmt:'剩余金额'//EwayLocale.monitor.business.cashInit.lastAmt
			},
			settlement:{
				title:'清机信息查询',//EwayLocale.monitor.business.settlement.title
				deTitle:'清机详细信息',//EwayLocale.monitor.business.settlement.deTitle
				settleId:'清机ID',//EwayLocale.monitor.business.settlement.settleId
				uuId:'周期ID',//EwayLocale.monitor.business.settlement.uuId
				endAmt:'尾箱余额',//EwayLocale.monitor.business.settlement.endAmt
				endDate:'结帐日期',//EwayLocale.monitor.business.settlement.endDate
				cimNum:'存款笔数',//EwayLocale.monitor.business.settlement.cimNum
				cdmNum:'取款笔数',//EwayLocale.monitor.business.settlement.cdmNum
				totalNum:'交易总笔数',//EwayLocale.monitor.business.settlement.totalNum
				leftDate:'结账日期',//EwayLocale.monitor.business.settlement.leftDate
				cimAmt:'存款金额',//EwayLocale.monitor.business.settlement.cimAmt
				cdmAmt:'取款金额',//EwayLocale.monitor.business.settlement.cdmAmt
				tranAmt:'交易总金额'//EwayLocale.monitor.business.settlement.tranAmt
			}
		/*}*/
		}
	},

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
			number:'编号',//EwayLocale.machine.atmCatalog.number
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
			nocash:'非现金'
		},
		device:{
			title:'设备信息管理',
			devDetailInfo:'设备模块详细信息',
		    IDC:'读卡器模块(IDC)',
			JPR:'日志打印机模块(JPR)',
			CDM:'取款模块(CDM)',
			SIU:'传感器模块(SIU)',
			CIM:'存款模块(CIM)',
			TTU:'文本终端单元(TTU)',
			RPR:'凭条打印机模块(RPR)',
			PIN:'密码键盘模块(PIN)',
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
			},

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
		}
	},

	//**********************************************************/

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
	//**********************************************************/
	report:{
		baseReport:{
			date:'加钞日期',//EwayLocale.report.baseReport.date
			amt:'加钞金额',//EwayLocale.report.baseReport.amt
			boxId:'钞箱ID',//EwayLocale.report.baseReport.boxId
			boxCurrency:'币种',//EwayLocale.report.baseReport.boxCurrency
			boxInitAmt:'初始金额',//EwayLocale.report.baseReport.boxInitAmt
			lastAmt:'剩余金额',//EwayLocale.report.baseReport.lastAmt
			cashAddRep:'加钞情况报表',//EwayLocale.report.baseReport.cashAddRep
			boxBalanceRep:'钞箱余额报表',//EwayLocale.report.baseReport.boxBalanceRep
			sysConfRep:'系统硬件配置报表',//EwayLocale.report.baseReport.sysConfRep
			devDetailRep:'设备明细报表',//EwayLocale.report.baseReport.devDetailRep
			devBrandRep:'设备品牌统计报表',//EwayLocale.report.baseReport.devBrandRep
			devRunInfoRep:'设备运行情况报表',//EwayLocale.report.baseReport.devRunInfoRep
			eatCardRep:'吞卡统计报表',//EwayLocale.report.baseReport.eatCardRep
			eatCardDetailRep:'吞卡明细报表',//EwayLocale.report.baseReport.eatCardDetailRep
			clearDate:'清机日期',//EwayLocale.report.baseReport.clearDate
			clearTable:'清机情况报表',//EwayLocale.report.baseReport.clearTable
			dependDev:'按设备',//EwayLocale.report.baseReport.dependDev
			tradeRep:'交易统计报表',//EwayLocale.report.baseReport.tradeRep
			tradeResultRep:'交易结果统计报表',//EwayLocale.report.baseReport.tradeResultRep
			tradeDaysCountRep:'日均交易笔数趋势图',//EwayLocale.report.baseReport.tradeDaysCountRep
			tradeHoursCountRep:'一日内时均交易笔数趋势图',//EwayLocale.report.baseReport.tradeHoursCountRep
			tradeCount:'交易笔数',//EwayLocale.report.baseReport.tradeCount
		},
		openrate:{
			device:{
				statisticsMethod:'统计方式',//EwayLocale.report.openrate.device.statisticsMethod
				statistics:'统计',//EwayLocale.report.openrate.device.statistics
				importStat:'导出',//EwayLocale.report.openrate.device.importStat
				statDate:'统计日期',//EwayLocale.report.openrate.device.statDate
				openTimes:'设备应工作时长',//EwayLocale.report.openrate.device.openTimes
				healthyTimeReal:'正常状态时长',//EwayLocale.report.openrate.device.healthyTimeReal
				maintainTimeReal:'管机员维护时长',//EwayLocale.report.openrate.device.maintainTimeReal
				unknownTimeReal:'离线未知时长',//EwayLocale.report.openrate.device.unknownTimeReal
				faultTimeReal:'硬件故障停机时长',//EwayLocale.report.openrate.device.faultTimeReal
				atmpTimeReal:'ATMP故障时长',//EwayLocale.report.openrate.device.atmpTimeReal
				stopTimeReal:'其它暂停服务状态时长',//EwayLocale.report.openrate.device.stopTimeReal
				openRate:'实际工作开机率',//EwayLocale.report.openrate.device.openRate
				devOpenRate:'设备开机率',//EwayLocale.report.openrate.device.devOpenRate
				organizationName:'机构',//EwayLocale.report.openrate.device.organizationName
			},
			org:{
				orgOpenRate:'机构开机率',//EwayLocale.report.openrate.org.orgOpenRate
			},
			type:{
				terminalId:'型号',//EwayLocale.report.openrate.type.terminalId
				typeOpenRate:'型号开机率',//EwayLocale.report.openrate.type.typeOpenRate
			},
		},
		plan:{
			addPlan:'增加开机方案',//EwayLocale.report.plan.addPlan
			name:'方案名称',//EwayLocale.report.plan.name
			type:'方案类型',//EwayLocale.report.plan.type
			startDate:'有效开始时间',//EwayLocale.report.plan.startDate
			endDate:'有效结束时间',//EwayLocale.report.plan.endDate
			terminalId:'编号',//EwayLocale.report.plan.terminalId
			cashboxLimit:'钞箱报警金额(单位：张数)',//EwayLocale.report.plan.cashboxLimit
			perToDev:'人员<-->设备',//EwayLocale.report.plan.perToDev
			changePlan:'更改方案',//EwayLocale.report.plan.changePlan
			openPlan:'开机方案',//EwayLocale.report.plan.openPlan
		},

		openplan:{
			name:'方案名称',//EwayLocale.report.openplan.name
			machineQuantity:'设备数量',//EwayLocale.report.openplan.machineQuantity
			state:'状态',//EwayLocale.report.openplan.state
			openDate:'有效开机时间',//EwayLocale.report.openplan.openDate
			closeDate:'有效关机时间',//EwayLocale.report.openplan.closeDate
			createDateTime:'创建时间',//EwayLocale.report.openplan.createDateTime
			date:'日期',//EwayLocale.report.openplan.date
			week:'星期',//EwayLocale.report.openplan.week
			inportLinkedMachine:'导入关联设备',//EwayLocale.report.openplan.inportLinkedMachine
			selectFile:'选择文件',//EwayLocale.report.openplan.selectFile
			placeUploadingResource:'请上传资源',//EwayLocale.report.openplan.placeUploadingResource
			fileNotSupport:'导入的文件格式不支持,请按模板导入设备信息',//EwayLocale.report.openplan.fileNotSupport
			exportExplain:'导入说明',//EwayLocale.report.openplan.exportExplain
			thisIsTooLong:'请在设备导入模板中连续添加要下发的设备号,最多一次性导入2000条数据(约耗时5分钟),最少导入1条数据',//EwayLocale.report.openplan.thisIsTooLong
			thisHardToTranslate:'点击下载导入设备号模板',//EwayLocale.report.openplan.thisHardToTranslate
			placeSelect:'---请选择---',//EwayLocale.report.openplan.placeSelect
			planDevice:'方案<-->设备',//EwayLocale.report.openplan.planDevice
			timeEare:'输入时间段有误，请重新输入！',//EwayLocale.report.openplan.timeEare
			planOlonOne:'同方案只能设置开机或关机的一种',//EwayLocale.report.openplan.planOlonOne
			addSuccess:'添加成功',//EwayLocale.report.openplan.addSuccess
			addFail:'添加失败',//EwayLocale.report.openplan.addFail
			setTime:'请设置详细时间',//EwayLocale.report.openplan.setTime
			thisPlanStop:'(此方案已停用，不可应用！)',//EwayLocale.report.openplan.thisPlanStop
			placeRefresh:'条解除失败，请刷新后查看！',//EwayLocale.report.openplan.placeRefresh
			linking:'正在关联设备....',//EwayLocale.report.openplan.linking
			testingPlaceWaiting:'正在判断设备号是否符合要求，请耐心等待...',//EwayLocale.report.openplan.testingPlaceWaiting
			leastOne:'最少一次导入1条设备信息，请重新选择导入文件!',//EwayLocale.report.openplan.leastOne
			notMore:'最多一次导入2000条设备信息，请重新选择导入文件!',//EwayLocale.report.openplan.notMore
			checkFile:'请检查导入文件',//EwayLocale.report.openplan.checkFile
			fileNotAllowed:'文件不符合要求！',//EwayLocale.report.openplan.fileNotAllowed
			tipExportSuccess:'条数据,成功导入',//EwayLocale.report.openplan.tipExportSuccess
			tipLookUp:'条,点击查看导入详情!',//EwayLocale.report.openplan.tipLookUp
			tochenkDervice:'请选择您要更改的设备',//EwayLocale.report.openplan.tochenkDervice
			tochenckPeople:'请选择人员',//EwayLocale.report.openplan.tochenckPeople
			tipAddError:'条添加失败,请刷新后查看',//EwayLocale.report.openplan.tipAddError
			planIsHaved:'',
			linkSuccess:'关联成功',//EwayLocale.report.openplan.linkSuccess
			Mon:'一',//EwayLocale.report.openplan.Mon
			Tues:'二',//EwayLocale.report.openplan.Tues
			Wed:'三',//EwayLocale.report.openplan.Wed
			Thur:'四',//EwayLocale.report.openplan.Thur
			Fri:'五',//EwayLocale.report.openplan.Fri
			Sat:'六',//EwayLocale.report.openplan.Sat
			Sun:'日',//EwayLocale.report.openplan.Sun
			useSuccess:'正常启用',//EwayLocale.report.openplan.useSuccess
			notSuccess:'未启用',//EwayLocale.report.openplan.notSuccess
			lastOneGroup:'请在组内至少选中一项',//EwayLocale.report.openplan.lastOneGroup
			lanDetailWeek:'星期方案详细列表',//EwayLocale.report.openplan.lanDetailWeek
			planDetailDay:'日期方案详细列表',//EwayLocale.report.openplan.planDetailDay
			selectPlan:'选择开机方案',//EwayLocale.report.openplan.selectPlan
			weekSelect:'通知方式'//EwayLocale.report.openplan.weekSelect
		}
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
	},

	//**********************************************************/

	cases:{
		confirm:'确认',//EwayLocale.cases.confirm
		cancel:'取消',//EwayLocale.cases.cancel
		concern:'请关注',
		SRCBView:'上海农商行新监控发送',
		nowExportFile:'正在导入文件',
		exportFaultInfo:'导入厂商故障信息成功.',
		caseFault:{
			faultRelevantInfo:'故障相关短信',
			faultModule:'故障模块',
			cardReaderModule:'读卡器模块',
			depoistModule:'存款模块',
			drawModule:'取款模块',
			rprModule:'凭条打印模块',
			jprModule:'日志打印模块',
			pinModule:'密码键盘模块',
			textTerminalUnit:'文本终端单元',
			sensoModule:'传感器模块',
			faultClassify: '故障分类',
			faultCode : '故障码',
			providerFaultCode: '厂商故障码',
			faultStartTime : '故障开始时间',
			faultCloseTime : '故障关闭时间',
			faultContinueTime : '持续时长',
		    faultState : '故障状态',
		    status:{
		    	open:'未关闭',
		    	close:'已关闭'
		    },
		    closeType:{
		    	force : '手工关闭',
		    	normal : '正常关闭'
		    },
		    closeByForce : '点击手工关闭故障',
		    faultCloseType : '故障关闭方式',
		    none : '没有',
		    upgradeTimes: '升级次数',
		    message: '短信',
		    checkDetails: '查看详情',
		    bankPer: '银行联系人',
		    serPer: '供应商联系人',
		    createTime: '创建时间',
		    informContent: '通知内容',
		    messageContentDetail: '短信内容详情',
		    informWay: '通知方式',//EwayLocale.cases.informWay
		    mail:'邮件',
		    messageAndMail:'短信和邮件',
		    informMobile: '通知手机号',
		    notifyTimes: '通知次数',
		    notifyRepeatTimes: '重复通知次数',
		    sendTimes: '发送次数',
		    sendInterval: '发送时间间隔',
		    sendTime: '发送时间',
		    faultSearch:'故障查询',
		    none : '不发送'

		},
		caseNotify:{
			fault:'故障',
			faultDetails:'故障详情',
			faultlastTime: '故障持续时长(单位:小时)',
			checkFailure:'查看失败！',
			innerFault:'内部错误',//EwayLocale.cases.caseNotify.innerFault
			messageCheck:'短信查询'
		},
		faultClassify:{
			faultClassifyName: '故障分类名称',
			faultresponsorType: '故障责任人类型',
			maintain:'维护员',
			manageAndMaintain:'管机员和维护员 ',
			upGradeTimes: '最高升级次数',
			faultInformWay:'故障通知方式',
			faultCloseInterval:'故障规定关闭时间间隔（单位:小时）',
			faultTypeConfiguration: '故障类型配置',
			updateFaultTypeConfiguration: '更改故障类型配置',
			number:'由数字‘0-9’,‘.’组成',
			informNumber:'通知次数不能为0,由数字‘0-9’组成,1-5位'
		},
		notifyMould:{
			noticeType:'通知类型',
			createNotice:'创建通知',
			upgradeNotice:'升级通知',
			closeNotice:'关闭通知',
			noticeValue: '通知参数',
			messageContentConfiguration:'短信内容配置',
			updateMessageContentConfiguration: '更改短信内容配置',
			necessaryOption: '此项为必选项',
			faultType:'故障类型',
			applyStatus:'应用状态'
		},
		vendorCode:{
			exportProviderInfo: '导入厂商故障信息',
			provider:'厂商',
			exportFile: '导入文件',
			deleteFaultInfo:'删除厂商故障信息',
			templateLoad:'模板下载',
			massRemove: '批量删除',
			providerDescription:'厂商故障描述',
			solveProject: '解决方案',
			providerFaultInfo:'厂商故障信息管理'
		}
	},

	//**********************************************************/
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
		reOperate:'无法修改密码,请重新操作.',//EwayLocale.personal.reOperate
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
		logDevAccount:'日志设备数量累计',//EwayLocale.atmLog.logDevAccount
		reform:'重做',//EwayLocale.atmLog.reform
		busLogAnalysis:'业务日志分析',//EwayLocale.atmLog.busLogAnalysis
		selectAnalysis:'请选择需要分析的日志文件，分析的结果将以Excel表格文件导出',//EwayLocale.atmLog.selectAnalysis
		selectLog:'选择日志',//EwayLocale.atmLog.selectLog
		pleaseDownload:'请下载',//EwayLocale.atmLog.pleaseDownload
		appLogDownload:'应用日志下载',//EwayLocale.atmLog.appLogDownload
		lastBackupTime:'最后一次备份时间',//EwayLocale.atmLog.lastBackupTime
		noBegin:'未开始',//EwayLocale.atmLog.noBegin
		noLog:'无日志',//EwayLocale.atmLog.noLog
		connectFail:'连接失败',//EwayLocale.atmLog.connectFail
		fileSize:'文件大小',//EwayLocale.atmLog.fileSize
		searchIllegal:'查询项中存在不合法的输入,不能导出.'//EwayLocale.atmLog.searchIllegal
	},
	//**********************************************************/
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
		clickDownload:'单击此处即可下载该文档',//EwayLocale.system.clickDownload
	},
	thread:{

	}

});
