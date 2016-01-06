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
		choseResToPerview:'请选择您要预览的广告.',//EwayLocale.msg.choseResToPerview
		noAdvertResAtTheResolution:'分辨率下没有配置广告资源.',//EwayLocale.msg.noAdvertResAtTheResolution
		chooseAdvert:'请选择一条广告.',//EwayLocale.msg.chooseAdvert
		chooseOneDevice:'请选择一台设备.',//EwayLocale.msg.chooseOneDevice
		downLoadedAdvertCantDelete:'删除失败:不能删除"已下发"和"等待下发"状态的广告.',//EwayLocale.msg.downLoadedAdvertCantDelete
		chooseAdvertToDelete:'请选择您要删除的广告.',//EwayLocale.msg.chooseAdvertToDelete
		generalVersionFailForDownloaded:'生成版本文件失败:"已下发"状态的广告不能再生成版本信息.',//EwayLocale.msg.generalVersionFailForDownloaded
		generalVersionSuccess:"生成版本文件成功.",//EwayLocale.msg.generalVersionSuccess
		createSuccess:"创建成功.",//EwayLocale.msg.createSuccess
		mustHaveOneResource:'至少包含一个广告资源!',//EwayLocale.msg.mustHaveOneResource
		saveFileCommunicationFail:'保存失败:与服务器通讯失败',//EwayLocale.msg.saveFileCommunicationFail
		downloadFailForNoVersion:"下发版本文件失败:还没有生成版本文件或者版本文件丢失,请先生成版本文件.",//EwayLocale.msg.downloadFailForNoVersion
		saveSuccess:'保存成功！',//EwayLocale.msg.saveSuccess

		removeSuccess:'解除成功',//EwayLocale.msg.removeSuccess
		removeFail:'解除失败',//EwayLocale.msg.removeFail
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
		exportXLS:'导出XLS',//EwayLocale.button.exportXLS
		exportPDF:'导出PDF',//EwayLocale.button.exportPDF
		massExport:'批量导入',//EwayLocale.button.massExport
		download:'下发',//EwayLocale.button.download
		downloadToolTip:'配置下发作业',//EwayLocale.button.downloadToolTip
		save:'保&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存',//EwayLocale.button.save
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
			warn:'查询条件存在错误项.',//EwayLocale.tip.search.warn
			record:'请选择您要查看的记录.'//EwayLocale.tip.search.record
		},
		update:{
			one:'只能选择一条记录更改.',//EwayLocale.tip.update.one
			two:'此条记录不能被更改.'//EwayLocale.tip.update.two
		},
		remove :{
			none:'请选择您要删除的记录.',//EwayLocale.tip.remove.none
			one:'只能选择一条记录删除',//EwayLocale.tip.remove.one
			confirm:{
				title:'请确认',//EwayLocale.tip.remove.confirm.title
				info:'是否删除该记录?'//EwayLocale.tip.remove.confirm.info
			},
			error:'删除失败:'//EwayLocale.tip.remove.error
		},
		own:{
			have:'有',//EwayLocale.tip.own.have
			nothing:'无'//EwayLocale.tip.own.nothing
		},
		right:{
			yes:'是',//EwayLocale.tip.right.yes
			no:'否'//EwayLocale.tip.right.no
		},
		add:{
			error:'新增失败'//EwayLocale.tip.add.error
		},
		success:'成功.',//EwayLocale.tip.success
		fail:'失败:',//EwayLocale.tip.fail
		phone:'请输入正确的电话号码',//EwayLocale.tip.phone
		remind:'提示',//EwayLocale.tip.remind
		formatPageBfMsg: '每页显示',//EwayLocale.tip.formatPageBfMsg
		formatPageAfMsg: '条',//EwayLocale.tip.formatPageAfMsg

		unCertain:'未知',//EwayLocale.tip.unCertain
		searchOfNoLegal:'查询项中存在不合法的输入,不能提交.',//EwayLocale.tip.searchOfNoLegal
		choseExportDevInfo:'请选择要导出信息的设备',//EwayLocale.tip.choseExportDevInfo
		nowLink:'正在连接......',//EwayLocale.tip.nowLink
		linkFailure:'连接失败.',//EwayLocale.tip.linkFailure
		inputError:'输入有误.',//EwayLocale.tip.inputError
		numberExist:'此编号已经存在,请重新输入.',//EwayLocale.tip.numberExist
		isConfirmRemove:'删除分组,关联关系也被删除,是否真的要删除指定分组?',//EwayLocale.tip.isConfirmRemove
		noGroupInfo:'没有组信息,无法查询.',//EwayLocale.tip.noGroupInfo
		selectAdd:'请选择您要增加的记录.',//EwayLocale.tip.selectAdd
		continueAdd:'添加成功,是否继续向组内添加设备?',//EwayLocale.tip.continueAdd
		addFail:'添加失败.',//EwayLocale.tip.addFail
		isRemoveDev:'是否从该组移除该设备?',//EwayLocale.tip.isRemoveDev
		removeFail:'移除失败.',//EwayLocale.tip.removeFail
		selectRemoveGroup:'请选择您要移除的设备所在组.',//EwayLocale.tip.selectRemoveGroup
		selectRemoveDev:'请选择您要移除的设备.',//EwayLocale.tip.selectRemoveDev
		selectMoveDev:'请选择要移动的设备.',//EwayLocale.tip.selectMoveDev
		moveSuc:'移机成功.',//EwayLocale.tip.moveSuc
		dateReSelect:'开始时间不能大于结束日期,请重新选择',//EwayLocale.tip.dateReSelect
		selectPlan:'请选择您应用的方案.',//EwayLocale.tip.selectPlan
		removeFail:'解除失败.',//EwayLocale.tip.removeFail
		selectRemoveDev:'请选择要解除的设备.',//EwayLocale.tip.selectRemoveDev
		relatedFail:'关联失败.',//EwayLocale.tip.relatedFail
		selectRelatedDev:'请选择要关联的设备.',//EwayLocale.tip.selectRelatedDev
		exportFiles: '请选择导入文件,只支持.xls和.xlsx格式的文件',//EwayLocale.tip.exportFiles
		operateSuc:'操作成功',//EwayLocale.tip.operateSuc
		operateWrong:'操作成功',//EwayLocale.tip.operateWrong
		choosePlan:'请选择您要查看的方案',//EwayLocale.tip.choosePlan
		planDetail:'方案详情',//EwayLocale.tip.planDetail
		planDate:'方案详情(日期)',//EwayLocale.tip.planDate
		planWeek:'方案详情(星期)',//EwayLocale.tip.planWeek
		planNoConf:'该方案无详细设置！',//EwayLocale.tip.planNoConf
		devRelatedPlan:'设备已关联开机方案！',//EwayLocale.tip.devRelatedPlan
		
		tips:'提示',//EwayLocale.tip.tips
		input:'请正确输入',//EwayLocale.tip.input
		roleName:'由字母‘a-z’或‘A-Z’、数字‘0-9’或中文，最多可输入 40位',//EwayLocale.tip.roleName
		notNull:'不能为空',//EwayLocale.tip.notNull
		cardNo:'只能输入13到19个数字',//EwayLocale.tip.cardNo
		blankBegin:'不能以空格开头',//EwayLocale.tip.blankBegin
		passwd:{
			confirmPasswd:'是否确认密码重置？',//EwayLocale.tip.passwd.confirmPasswd
			resetPasswding:'正在重置密码......',//EwayLocale.tip.passwd.resetPasswding
			resetPasswdFail:'密码重置失败！'//EwayLocale.tip.passwd.resetPasswdFail
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
					managerFail:'设置管理员失败.',//EwayLocale.tip.bankOrg.manager.set.managerFail
					chooseOneManager:'请至少选择一条记录'//EwayLocale.tip.bankOrg.manager.set.chooseOneManager
				},
				remove:{
					confirm:'是否删除该机构管理员?',//EwayLocale.tip.bankOrg.manager.remove.confirm
					reChoose:'您未选择您要设置的机构或该机构下没有管理员，请重新选择.',//EwayLocale.tip.bankOrg.manager.remove.reChoose
					delSuccess:'删除管理员成功.',//EwayLocale.tip.bankOrg.manager.remove.delSuccess
					delFail:'删除管理员失败.'//EwayLocale.tip.bankOrg.manager.remove.delFail
				}
			},
			move:{
				chooseOrg:'请选择您要迁移的组织.',//EwayLocale.tip.bankOrg.move.chooseOrg
				moveSuccess:'组织迁移成功.'//EwayLocale.tip.bankOrg.move.moveSuccess
			}
		},
		bankPer:{
			allPersonInfo:'所有银行人员信息',//EwayLocale.tip.bankPer.allPersonInfo
			link:{
				linkPerson:'请选择您关联的人员',//EwayLocale.tip.bankPer.link.linkPerson
				linkBankPerson:'请选择您关联的人员',//EwayLocale.tip.bankPer.link.linkBankPerson
				unLinkPersonFail:'关联失败.',//EwayLocale.tip.bankPer.link.unLinkPersonFail
				unlinkDev:'请选择要解除的设备.',//EwayLocale.tip.bankPer.link.unlinkDev
				linkDev:'请选择要关联的设备.',//EwayLocale.tip.bankPer.link.linkDev
				unLinkDevFail:'解除失败.'//EwayLocale.tip.bankPer.link.unLinkDevFail
			},
			personEligible:'符合条件的人员',//EwayLocale.tip.bankPer.personEligible
			downGradePer:'以及其下属机构下的人员信息',//EwayLocale.tip.bankPer.downGradePer
		},
		serviceOrg:{
			chooseOrg:'请选择您要设置的维护商.',//EwayLocale.tip.serviceOrg.chooseOrg
			remove:{
				reChoose:'您未选择要设置的维护商或该维护商下没有管理员,请重新选择.'//EwayLocale.tip.serviceOrg.remove.reChoose
			}
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
				fail:'更改失败:记录不存在,请刷新后操作.'//EwayLocale.tip.user.update.fail
			}
		},
		business:{
			transaction:{
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
				closeServiceSuccess:'执行暂停服务命令成功.',//EwayLocale.tip.business.device.closeServiceSuccess
				closeServiceFail:'执行暂停服务命令失败.',//EwayLocale.tip.business.device.closeServiceFail
				closeNormal:'正常关机',//EwayLocale.tip.business.device.closeNormal
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
				remoteCommandMsg:'命令发送成功'//EwayLocale.tip.business.device.remoteCommandMsg
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


		choseDev:'请选择您报停的设备.',//EwayLocale.vtype.choseDev
		dataLoad:'正在加载数据......',//EwayLocale.vtype.dataLoad
		devLinkNormal:'请检查与设备的连接是否正常.',//EwayLocale.vtype.devLinkNormal
		hardwayInitialize:'硬件模块正在初始化......',//EwayLocale.vtype.hardwayInitialize
		inputCorrect:'请正确输入.',//EwayLocale.vtype.inputCorrect
		exportRepError:'导出报表出错，请重新操作!',//EwayLocale.vtype.exportRepError

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
		choseTask:'请选择一个任务',//EwayLocale.vtype.choseTask
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
			close:'停用',//EwayLocale.commen.comboxStatus.close
			pastDue:'过期',//EwayLocale.commen.comboxStatus.pastDue
			pastDueSoon:'即将过期'//EwayLocale.commen.comboxStatus.pastDueSoon
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
			scrapped:'报废'//EwayLocale.commen.comboxDevStatus.scrapped
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
		seviceMode:'经营方式',//EwayLocale.commen.seviceMode
		insideOutside:'在行标志',//EwayLocale.commen.insideOutside
		appVersion:'应用版本号',//EwayLocale.commen.appVersion
		devInfo:'设备基本信息',//EwayLocale.commen.devInfo
		
		personnel:'联系人',//EwayLocale.commen.personnel
		warn:'警告',//EwayLocale.commen.warn
		fatal:'故障',//EwayLocale.commen.fatal
		unStable:'不稳定',//EwayLocale.commen.unStable
		unknow:'未知',//EwayLocale.commen.unknow
		noDevice:'无设备',//EwayLocale.commen.noDevice
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
		filterDelete: '已删除'//EwayLocale.commen.filterDelete


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
