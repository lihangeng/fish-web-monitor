Ext.apply(EwayLocale,{
	addSuccess : 'Add successful.',//EwayLocale.addSuccess 增加成功.
	updateSuccess : 'Update successful.',//EwayLocale.updateSuccess 更改成功.
	deleteSuccess : 'Delete successful.',//EwayLocale.updateSuccess 删除成功.
	choiceUpdateMsg :'Please select the record which you want to update.',//EwayLocale.choiceUpdateMsg 请选择您要更改的记录.
	choiceDeleteMsg :'Please select the record which you want to delete.',//EwayLocale.choiceDeleteMsg 请选择您要删除的记录.

	myTable:'Workbench',//EwayLocale.myTable 我的工作台
	ATMV:'ATM View',//EwayLocale.ATMV 自助设备监控系统(ATMV)
	welcome:'Welcome,',//EwayLocale.welcome 欢迎你,
	personalConf:'Personal Settings',//EwayLocale.personalConf 个人设置
	systemHelp:'Help',///EwayLocale.systemHelp 系统帮助
	exitSystem:'Log Out',//EwayLocale.exitSystem 退出系统
	title:{
		msg:'Message'//EwayLocale.title.msg 信息
	},
	msg:{
		perviewFailForText:'Preview failed:the charater and rolling advertisement is supported to preview',//EwayLocale.msg.perviewFailForText 预览失败:不支持文字滚动广告和公告的预览.
		perviewFailNoResource:'Preview failed:no resource found in this advertisement.',//EwayLocale.msg.perviewFailNoResource 预览失败:此广告没有配置广告资源.
		choseResToPerview:'Please select the advertisement which you want to preview.',//EwayLocale.msg.choseResToPerview 请选择您要预览的广告.
		noAdvertResAtTheResolution:'No allocation of advertising resources under Resolution.',//EwayLocale.msg.noAdvertResAtTheResolution
		chooseAdvert:'Please select an advertisement.',//EwayLocale.msg.chooseAdvert 请选择一条广告.
		chooseOneDevice:'Please select a machine.',//EwayLocale.msg.chooseOneDevice 请选择一台设备.
		downLoadedAdvertCantDelete:'Delete failed:can not delete the advertisement which status is "issued" or "wait for issue".',//EwayLocale.msg.downLoadedAdvertCantDelete 删除失败:不能删除"已下发"和"等待下发"状态的广告.
		chooseAdvertToDelete:'Please select the advertisement which you want to delete.',//EwayLocale.msg.chooseAdvertToDelete 请选择您要删除的广告.
		chooseAdvertToDownload:'Please select the advertisement which you want to issue.',//EwayLocale.msg.chooseAdvertToDownload 请选择您要下发的广告.
		generalVersionFailForDownloaded:'Generate  the version-file failed:the status "issued" advertisement can not generate version-information any more',//EwayLocale.msg.generalVersionFailForDownloaded 生成版本文件失败:"已下发"状态的广告不能再生成版本信息.
		generalVersionSuccess:"Generate the version file successful",//EwayLocale.msg.generalVersionSuccess 生成版本文件成功.
		createSuccess:"Create successful.",//EwayLocale.msg.createSuccess 创建成功.
		mustHaveOneResource:'At least has one resource of advertisement!',//EwayLocale.msg.mustHaveOneResource 至少包含一个广告资源!
		saveFail:'Save failed',//EwayLocale.msg.saveFail 保存失败
		saveFailPleaseRefresh:'Save failed, please refresh retry',//EwayLocale.msg.saveFailPleaseRefresh
		saveFileSizeMaxFail:'Save failed:the max size of each single file is 30M',//EwayLocale.msg.saveFileSizeMaxFail 保存失败:超过最大单个文件大小限制（最大30M）
		saveFileCommunicationFail:'Save failed:connection refused',//EwayLocale.msg.saveFileCommunicationFail 保存失败:与服务器通讯失败
		chooseDevice:"Please select a machine",//EwayLocale.msg.chooseDevice 请选择设备.
		downloadFailForNoVersion:"Version file issue failed :no generated version-file found or version-file lost,please generate version file first.",//EwayLocale.msg.downloadFailForNoVersion 下发版本文件失败:还没有生成版本文件或者版本文件丢失,请先生成版本文件.
		saveSuccess:'Save successful',//EwayLocale.msg.saveSuccess 保存成功！

		removeSuccess:'Relieve success',//EwayLocale.msg.removeSuccess
		removeFail:'Relieve failure',//EwayLocale.msg.removeFail
		someStripRemoveFailePleaseRefresh:'Article lift failed. Please refresh retry！',//EwayLocale.msg.EwayLocale.msg.someStripRemoveFailePleaseRefresh
		versionDownloaded:'Can not delete the advertisement which status is "issued" or "wait for issue',//EwayLocale.msg.versionDownloaded 不能删除"等待下发"和"已下发"状态的版本.
		selectVersionToDelete:'Please choose the version which you want to delete',//EwayLocale.msg.selectVersionToDelete 请选择您要删除的版本.
		communicationFail:'Add failed : connction refused.',//EwayLocale.msg.communicationFail 增加失败:与服务器通讯失败.
		sameVersionNoFail:'Add failed: same version no exists.',//EwayLocale.msg.sameVersionNoFail 增加失败:已经存在相同的版本号.
		fileSizeMaxFail:'Add failed:the max size of the file is 300M',//EwayLocale.msg.fileSizeMaxFail 增加失败:超过最大文件大小限制（最大300M）
		fileUnzipFail:'Add failed: zip-file can not be unziped',//EwayLocale.msg.fileUnzipFail 增加失败:上传的压缩包不能正常解压
		addFileFail:'Add failed :',//EwayLocale.msg.addFileFail 增加失败:
		mustSelectDevice:'please choose at least one machine.',//EwayLocale.msg.mustSelectDevice 请至少选择一个设备.
		selectVersionRecord:'Please choose the version which you want to issue',//EwayLocale.msg.selectVersionRecord 请选择您要下发的版本.
		missVersionFile:"Version files lost,can not execute",//EwayLocale.msg.missVersionFile 版本文件丢失,暂不能对版本进行下发控制.
		mustSelectPerson:"Please select personnel must"//EwayLocale.msg.mustSelectPerson
	},
	confirm:{
		titleSure:'Confirm',//EwayLocale.confirm.titleSure 确认
		todoDelete:'Delete this record?',//EwayLocale.confirm.todoDelete 是否删除该记录?
		title:'Tip',//EwayLocale.confirm.title 提示
		withoutNumTaskConfirmInfo:'Job save successful,skip to the "Distribute monitor" page?',//EwayLocale.confirm.withoutNumTaskConfirmInfo 作业保存成功,是否跳转到"分发监控"页面?
		//TODO *为数字需要替换
		taskConfirmInfo0:'第',//EwayLocale.confirm.taskConfirmInfo0 第
		taskConfirmInfo1:'次作业保存成功,是否跳转到"分发监控"页面?'//EwayLocale.confirm.taskConfirmInfo1 次作业保存成功,是否跳转到"分发监控"页面?
	},
	button:{
		search:'Search',//EwayLocale.button.search 查询
		add : 'Add', //EwayLocale.button.add 增加
		update:'Update',//EwayLocale.button.update 更改
		remove:'Delete',//EwayLocale.button.remove 删除
		refresh:'Refresh',//EwayLocale.button.refresh 刷新
		reset:'Reset',//EwayLocale.button.reset 重置
		back:'Back',//EwayLocale.button.back 返回
		apply:'Apply',//EwayLocale.button.apply 应用
		link:'Binding',//EwayLocale.button.link
		unlink:'Remove',//EwayLocale.button.unlink
		//bankOrg
		deepQuery:'Deep Search', //深度查询
		bankOrgMove:'Migrate',  //组织迁移
		bankOrgAdmin:'Manager', //管理员
		//bankPerson
		bankPerlink:'Binding Devices', //绑定设备
		sure:'Confirm',//EwayLocale.button.sure 确定
		confirm:'Confirm',//EwayLocale.button.confirm 确认
		cancle:'Cancel',//EwayLocale.button.cancle 取消
		choose:'Select',//EwayLocale.button.choose 选择
		pause:'Pause',//EwayLocale.button.pause 暂停

		exported:'Export',//EwayLocale.button.exported 导出
		select:'Select', //选择
		info :'Detail', //详细信息
		move:'Move', //移机
		exportXLS:'Export as XLS', //导出XLS
		exportPDF:'Export as PDF', //导出PDF
//		massExport:'批量导入',
		massExport:'Batch Import', //批量导入
		download:'Deploy', //下发
		downloadToolTip:'Deploy Settings', //配置下发作业
		save:'Save',//EwayLocale.button.save 保存
		openPlan:'Open Plan',//EwayLocale.button.openPlan 开机方案
		adminBtn:'Manager',//管理员
		personM:'Maintencer',//EwayLocale.button.personM 厂商管理员
		personTM:'Banker',//EwayLocale.button.personTM管机员
		orgAdmin:'Bank Administrator',//EwayLocale.button.orgAdmin机构管理员
	},
	//引用其他模块
	refs:{
		selectAll:'All',//EwayLocale.refs.selectAll 全部
		orgName:'Bank',//EwayLocale.refs.orgName 机构
		terminalId:'Terminal ID',//EwayLocale.refs.terminalId 设备编号
		ip:'IP',//EwayLocale.refs.ip IP地址
		devType:"Device Type"//EwayLocale.refs.devType 设备型号
	},
	//**********************************************************/
	tip:{
		search :{
			warn:'Error input for query.',//查询条件存在错误项.
			record:'Please choose the record which you want to check'//请选择您要查看的记录.
		},
		update:{
			one:'Only one record can be choose to modify',//只能选择一条记录更改.
			two:'This record can not be modified'//此条记录不能被更改.
		},
		remove :{
			none:'Plase choose the record which you want to delete',//请选择您要删除的记录.
			one:'Only one record can be choose to delete', //只能选择一条记录删除
			confirm:{
				title:'Please confirm',//请确认
				info:'Delete this record'//是否删除该记录?
			},
			error:'Delete failed:'//删除失败
		},
		own:{
			have:'yes',//有
			nothing:'no'//无
		},
		right:{
			yes:'yes', //是
			no:'no' //否
		},
		add:{
			error:'Add failed'//EwayLocale.tip.add.error 新增失败
		},
		success:'Successful.',//EwayLocale.tip.success 成功
		fail:'Failed:',//EwayLocale.tip.fail 失败
		phone:'Please enter the right telephone number', //请输入正确的电话号码
		remind:'Tip',//EwayLocale.tip.remind 提示
		displayMessage:'Total:{2} items,display{0}-{1}',//总共：{2}条，显示{0}-{1}
		formatPageBfMsg: 'Per page',// EwayLocale.tip.formatPageBfMsg
		formatPageAfMsg: 'items',// EwayLocale.tip.formatPageAfMsg

		unCertain:'Unknown',//未知
		searchOfNoLegal:'There are some illegal parameters in the query condition,can not commit ',//查询项中存在不合法的输入,不能提交.
		choseExportDevInfo:'Please select the machine which you want to export for detail ',//请选择要导出信息的设备
		nowLink:'Connecting',//正在连接......
		linkFailure:'Connecting failed.',//EwayLocale.tip.linkFailure 连接失败
		inputError:'Illegal input',//输入有误
		numberExist:'Exist ID, please enter again',//此编号已经存在,请重新输入.
		isConfirmRemove:'The relationship of the group will be deleted with this delete operation , make sure you want to delete?',//删除分组,关联关系也被删除,是否真的要删除指定分组?
		noGroupInfo:'No data of the group ,can not execute query',//没有组信息,无法查询.
		selectAdd:'Please select the record which you want to add',//请选择您要增加的记录.
		continueAdd:'Add successful,continue add machine to this group?',//添加成功,是否继续向组内添加设备?
		addFail:'Add failed.',//添加失败.
		isRemoveDev:'Remove this machine from this group?',//是否从该组移除该设备?
		removeFail:'Remove failed',//移除失败.
		selectRemoveGroup:'Please select the group which the machine you want to remove belongs.',//请选择您要移除的设备所在组.
		selectRemoveDev:'Please select the machine which you want to remove.',//请选择您要移除的设备.
		selectMoveDev:'Please select the machine which you want to move.',//请选择要移动的设备.
		moveSuc:'Move the machine successful',//移机成功.
		dateReSelect:'The begin date can not be later than end date,please select again',//开始时间不能大于结束日期,请重新选择
		selectPlan:'Please select the program which you want to execute',//请选择您应用的方案.
		removeFail:'Relieve failed',//解除失败.
		selectRemoveDev:'Please select the machine which you want to relieve.',//请选择要解除的设备.
		relatedFail:'Binding machine.',//关联失败.
		selectRelatedDev:'Please choose machines which you want to bind.',//请选择要关联的设备
		planNoUpdate:'This program has been executed ,can not be modified ',//该方案已经执行,不可修改.
		planNoRemove:'This program has been executed ,can not be deleted ',///该方案已经执行,不可删除.
		exportFiles: 'Please choose a file to import,only support .xls or .xlxs files ',//请选择导入文件,只支持.xls和.xlsx格式的文件
		noChange:'Did not change the data, change and then click OK!',//EwayLocale.tip.noChange没有更改数据,请更改后再点击确定
		operateSuc:'Successful operation',//EwayLocale.tip.operateSuc操作成功
		operateWrong:'Successful operation',//EwayLocale.tip.operateWrong操作成功
		deleteOne:'You can only delete a record.',//EwayLocale.tip.deleteOne一次只能删除一条记录
		chooseRecord:'Please select the records that you want to associate.',//EwayLocale.tip.chooseRecord请选择您要关联的记录
		choosePlan:'Please select the program you want to view',//EwayLocale.tip.choosePlan请选择您要查看的方案
		planDetail:'Programme details',//EwayLocale.tip.planDetail方案详情
		planDate:'Programme details (date)',//EwayLocale.tip.planDate方案详情(日期)
		planWeek:'Programme details (Week)',//EwayLocale.tip.planWeek方案详情(星期)
		planNoConf:'The program no detailed settings！',//EwayLocale.tip.planNoConf该方案无详细设置
		chooseRelatedDev:'Please select the device you want to associate！',//EwayLocale.tip.chooseRelatedDev请选择您要关联的设备
		devRelatedPlan:'Device is associated with the boot program！',//EwayLocale.tip.devRelatedPlan设备已关联开机方案
		//add by panxin
		tips:'Tips',//EwayLocale.tip.tips 提示
		input:'Please enter the right item',//EwayLocale.tip.input//请正确输入
		roleName:'Just for character ‘a-z’ or ‘A-Z’ or numbers ‘0-9’ ,max length is 40',//EwayLocale.tip.roleName 由字母‘a-z’或‘A-Z’、数字‘0-9’，最多可输入 40位
		roleDescription:'Just for character ‘a-z’ or ‘A-Z’、number‘0-9’、minussign‘-’、underline‘_’ or point‘.’,only start with Chinesecharacter,character or number,max length is 100',//EwayLocale.tip.roleDescription
		notNull:'Can not be null',//EwayLocale.tip.notNull 不能为空
		cardNo:'Just for 13 to 19 numbers',//EwayLocale.tip.cardNo 只能输入13到19个数字
		blankBegin:'Can not start with blank',//EwayLocale.tip.blankBegin 不能以空格开头
		passwd:{
			confirmPasswd:'Reset the password ,confirm?',//EwayLocale.tip.confirmPasswd 是否确认密码重置？
			resetPasswding:'Reseting the password.....',//EwayLocale.tip.resetPasswding 正在重置密码......
			resetPasswdFail:'Reset the password failed'//EwayLocale.tip.resetPasswdFail 密码重置失败！
		},
		operateDate:{
			operateDateBegin:'Operate date start with ',//EwayLocale.tip.operateDate.operateDateBegin 操作日期开始于
			operateDateEnd:'Operate date end  with'//EwayLocale.tip.operateDate.operateDateEnd 操作日期结束于
		},
		bankOrg:{
			manager:{
				set:{
					chooseOrg:'Please choose the organization which you want to set',//EwayLocale.tip.bankOrg.manager.set.chooseOrg 请选择您要设置的机构.
					managerSuccess:'Manager setting successful.',//EwayLocale.tip.bankOrg.manager.set.managerSuccess 设置管理员成功.
					managerFail:'Manager setting failed.'//EwayLocale.tip.bankOrg.manager.set.managerFail 设置管理员失败.
				},
				remove:{
					confirm:'Delete the manager of this organization?',//EwayLocale.tip.bankOrg.manager.remove.confirm 是否删除该机构管理员?
					reChoose:'You have not choose the organization or there is no manager of this organization, please choose again',//EwayLocale.tip.bankOrg.manager.remove.reChoose 您未选择您要设置的机构或该机构下没有管理员，请重新选择
					delSuccess:'Delete the manager successful',//EwayLocale.tip.bankOrg.manager.remove.delSuccess 删除管理员成功.
					delFail:'Delete the manager failed'//EwayLocale.tip.bankOrg.manager.remove.delFail 删除管理员失败.
				}
			},
			orgEligible:'Organization which accordwith the conditions',//EwayLocale.tip.bankOrg.orgEligible 符合条件的机构
			downGradeOrg:'direct lower organization',//EwayLocale.tip.bankOrg.downGradeOrg 的直接下级机构
			move:{
				chooseOrg:'Please choose organization which you want to move.',//EwayLocale.tip.bankOrg.move.chooseOrg 请选择您要迁移的组织
				moveSuccess:'Move the organization successful.'//EwayLocale.tip.bankOrg.move.moveSuccess 组织迁移成功
			}
		},
		bankPer:{
			allPersonInfo:'ALL info of bank employeers',//EwayLocale.tip.bankPer.allPersonInfo 所有银行人员信息
			link:{
				linkPerson:'Please choose a employeer which you want to link.',//EwayLocale.tip.bankPer.link.linkPerson 请选择您关联的人员
				unLinkPersonFail:'Link failed.',//EwayLocale.tip.bankPer.link.unLinkPersonFail 关联失败
				unlinkDev:'Please choose a machine which you want to lift.',//EwayLocale.tip.bankPer.link.unlinkDev 请选择要解除的设备
				linkDev:'Please choose a machine which you want to link.',//EwayLocale.tip.bankPer.link.linkDev 请选择要关联的设备
				unLinkDevFail:'Lift failed.'//EwayLocale.tip.bankPer.link.unLinkDevFail 解除失败
			},
			personEligible:'People which accordwith the conditions',//EwayLocale.tip.bankPer.personEligible 符合条件的人员
			downGradePer:'and its lower organizastion personnel',//EwayLocale.tip.bankPer.downGradePer 以及其下属机构下的人员信息
			personBelongs:'personnel information belongs to'//EwayLocale.tip.bankPer.personBelongs
		},
		serviceOrg:{
			chooseOrg:'Please choose the service which you want to set.',//EwayLocale.tip.serviceOrg.chooseOrg 请选择您要设置的维护商.
			remove:{
				reChoose:'You hava not choose a service or no manager found in the service,please choose again.'//EwayLocale.tip.serviceOrg.remove.reChoose //您未选择要设置的维护商或该维护商下没有管理员,请重新选择.
			}
		},
		servicePer:{
			allSerPer:'All info of service people'//EwayLocale.tip.servicePer.allSerPer 所有维护商人员信息
		},
		user:{
			add:{
				createAcc:'Please choose the man which you want to create account',//EwayLocale.tip.user.add.createAcc 请选择您要创建的账号的人员
				createSuccess:'Create successful,newcreate account',//EwayLocale.tip.user.add.createSuccess 创建成功,新建账户
				initPasswd:'The init password is :888888'//EwayLocale.tip.user.add.initPasswd 初始密码为: 888888
			},
			remove:{
				failRoot:'Delete failed,system manager can not be deleted.',//EwayLocale.tip.user.remove.failRoot 删除失败:系统管理员用户,无法删除
				confirm:'Delete this record? User log will delete with this operation.',//EwayLocale.tip.user.remove.confirm 是否删除该记录:删除用户会删除该用户的日志信息.
				fail:'Delete failed: can not delete the role,please try again'//EwayLocale.tip.user.remove.fail //删除失败:无法删除角色,请重新操作.
			},
			update:{
				fail:'Update failed:the record dose not exist,please refresh.',//EwayLocale.tip.user.update.fail 更改失败:记录不存在,请刷新后操作
			},
			move:{
				choose:'Please choose the record which you want to move.'//EwayLocale.tip.user.move.choose 请选择需要移动的记录.

			}
		},
		business:{
			transaction:{
				transactionMonitor:{
					beginMonitor:'Please stop monitoring before input item,click start-monitor button to monitor!',//EwayLocale.tip.business.transaction.transactionMonitor.beginMonitor 请先停止监控后再输入条件，按开始监控按钮即可进行条件监控！
					input:'At least one of terminal no,creditAccount,debitAccount is necessary.',//EwayLocale.tip.business.transaction.transactionMonitor.input 设备号、对方账号、客户账号至少输入一个.
					left:'leave',//EwayLocale.tip.business.transaction.transactionMonitor.left 离开
				},
				historyTransaction:{
					input:'Terminal ID is necessary for the query.'//EwayLocale.tip.business.transaction.historyTransaction.input 查询必须输入设备号


				}
			},
			blackList:{
				importing:'Importing file',//EwayLocale.tip.business.blackList.importing 正在导入文件
				importSuccess:'Blackcard file importing successful'//EwayLocale.tip.business.blackList.importSuccess 导入黑名单卡文件成功
			},
			card:{
				returnFail:'Turnover failed:system error.',//EwayLocale.tip.business.card.returnFail 移交失败 后台处理出错
				choose:'Please choose the card which you want to turnover.',//EwayLocale.tip.business.card.choose 请选择您移交的卡片
				returnSucess:'Turnover successful.',//EwayLocale.tip.business.card.returnSucess 移交成功
				chooseBack:'Please choose the card which you want to draw.',//EwayLocale.tip.business.card.chooseBack 请选择要领取的卡片
				getSuccess:'Draw successful.',//EwayLocale.tip.business.card.getSuccess 领取成功
				destroyConfirm:'Destroy this card?',//EwayLocale.tip.business.card.destroyConfirm 是否销毁这张卡片
				destroySuccess:'Destroy successful',//EwayLocale.tip.business.card.destroySuccess 销毁成功
				chooseDestroy:'Please choose the card which you want to destroy.',//EwayLocale.tip.business.card.chooseDestroy 请选择要销毁的卡片
				idCardRegex:'Please input the correct ID number,15 or 18 numbers',//EwayLocale.tip.business.card.idCardRegex 请输入正确的身份证号码,15位或者18位
				accountRegex:'Please input the correct ID number of the householdRegister,15 or 18 numbers',//EwayLocale.tip.business.card.accountRegex 请正确输入户口本上的身份证号码,15位或者18位
				driveCardRegex:'Please input the correct ID number of the driver-license,15 or 18 numbers',//EwayLocale.tip.business.card.driveCardRegex 请正确输入驾驶证上的身份证号码,15位或者18位
				passportRegex:'Please input the correct ID number of the passport,15 or 18 numbers',//EwayLocale.tip.business.card.passportRegex 请正确输入护照上的身份证号码,15位或者18位
				soldierRegex:'Please input the correct number of officer-license ,1-5 Chinesecharacter or ‘1-10’ numbers',//EwayLocale.tip.business.card.soldierRegex 请输入正确的军官证号码,1-5位汉字和1-10位数字
				soldierCard:'Please input the correct number of soldier-license 7-8 numbers ',//EwayLocale.tip.business.card.soldierCard 请输入正确的士兵证,7-8位数字
				busnessPaper:'Please input the correct number of businesslicence,12-15 numbers',//EwayLocale.tip.business.card.busnessPaper 请输入正确的法人营业执照,12-15位数字
				busnessCode:'Please input the correct number of juridicalperson,15 numbers',//EwayLocale.tip.business.card.busnessCode 请输入正确的法人代码证,15位数字
				taxPaper:'Please input the correct number of taxregistration,15 numbers'//EwayLocale.tip.business.card.taxPaper 请输入正确的税务登记证,15位数字

			},
			device:{
				getCashInfoFail:'Get the cashbin info failed',//EwayLocale.tip.business.device.getCashInfoFail 获取钞箱信息失败
				operating:'Executing',//EwayLocale.tip.business.device.operating 正在执行
				reviewFail:'Failed to review.',//EwayLocale.tip.business.device.reviewFail 查看失败
				logLoadConfirm:'Get the applogs successful,download it?',//EwayLocale.tip.business.device.logLoadConfirm 应用日志提取成功,是否下载?
				logPullFail:'Get the applogs failed',//EwayLocale.tip.business.device.logPullFail 提取应用电子日志失败
				logFail:'Log process failed.',//EwayLocale.tip.business.device.logFail log 处理失败
				linkServerFail:'Connecting refused.',//EwayLocale.tip.business.device.linkServerFail 服务器连接失败
				logicOpen:'Confirm execute open-service command.',//EwayLocale.tip.business.device.logicOpen 确认要执行开启服务命令
				openSuccess:'Execute open-service command successful.',//EwayLocale.tip.business.device.openSuccess 执行开启服务命令成功.
				openFail:'Execute open-service command failed.',//EwayLocale.tip.business.device.openFail 执行开启服务命令失败.
				closeConfirm:'Confirm execute pause-service command?',//EwayLocale.tip.business.device.closeConfirm 确认要执行暂停服务命令?
				closeNormal:'Shut down normally.',//EwayLocale.tip.business.device.closeNormal 正常关机
				closeComfirm:'Confirm execute normal shut-down command,it may brings some risk?',//EwayLocale.tip.business.device.closeComfirm 确认要执行正常关机命令么,可能会存在风险?
				closing:'Shutting down normally.',//EwayLocale.tip.business.device.closing 正在执行正常关机
				closeSucess:'Shut down normally successful.',//EwayLocale.tip.business.device.closeSucess 正常关机成功
				closeFail:'Shut down normally failed.',//EwayLocale.tip.business.device.closeFail 正常关机失败
				closeSentFail:'Send the normal shut-down command failed ',//EwayLocale.tip.business.device.closeSentFail 正常关机命令发送失败.
				forceClose:'Force shut down',//EwayLocale.tip.business.device.forceClose 强制关机
				forceCloseComfirm:'Confirm execute force shut-down command,it may brings some risk?',//EwayLocale.tip.business.device.forceCloseComfirm 确认要执行强制关机命令么,可能会存在严重风险?
				forceClosing:'Shutting down force.',//EwayLocale.tip.business.device.forceClosing 正在执行强制关机
				forceCloseSucess:'Force-shut-down successful.',//EwayLocale.tip.business.device.forceCloseSucess 强制关机成功.
				forceCloseFail:'Force-shut-down failed.',//EwayLocale.tip.business.device.forceCloseFail 强制关机失败.
				ForceCloseSentFail:'Send the force-shut-down command failed ',//EwayLocale.tip.business.device.ForceCloseSentFail 强制关机命令发送失败.
				reboot:'Reboot normally.',//EwayLocale.tip.business.device.reboot 正常重启
				rebootConfirm:'Confirm execute normal-reboot command,it may brings some risk?',//EwayLocale.tip.business.device.rebootConfirm 确认要执行正常重启命令么,可能会存在风险?
				rebooting:'Normally rebooting',//EwayLocale.tip.business.device.rebooting 正在执行正常重启
				rebootSucess:'Normal-reboot successful.',//EwayLocale.tip.business.device.rebootSucess 正常重启成功
				rebootFail:'Normal-reboot failed.',//EwayLocale.tip.business.device.rebootFail 正常重启失败.
				rebootSendFail:'Send the  normal-reboot command failed ',//EwayLocale.tip.business.device.rebootSendFail 正常重启命令发送失败.
				forceReboot:'Force reboot',//EwayLocale.tip.business.device.forceReboot 强制重启
				forceRebootConfirm:'Confirm execute force-reboot command,it may brings some risk?',//EwayLocale.tip.business.device.forceRebootConfirm 确认要执行强制重启命令么,可能会存在严重风险
				forceRebooting:'Executing force-reboot command',//EwayLocale.tip.business.device.forceRebooting 正在执行强制重启
				forceRebootSuccess:'Force-reboot successful.',//EwayLocale.tip.business.device.forceRebootSuccess 强制重启成功
				forceRebootFail:'Force-reboot failed.',//EwayLocale.tip.business.device.forceRebootFail 强制重启失败.
				forceRebootSendFail:'Send the normal force-reboot command failed ',//EwayLocale.tip.business.device.forceRebootSendFail 强制重启命令发送失败.
				resetConfirm:'Confirm execute force-reset command?',//EwayLocale.tip.business.device.resetConfirm 确认要执行强制复位
				resetSuccess:'Force-reset successful',//EwayLocale.tip.business.device.resetSuccess 强制复位成功
				resetFail:'Force-reset failed',//EwayLocale.tip.business.device.resetFail 强制复位失败
				resetSendFail:'Send the force-reset command failed.',//EwayLocale.tip.business.device.resetSendFail 强制复位命令发送失败
				term:'Terminal',//EwayLocale.tip.business.device.term 设备
				detail:'Detail',//EwayLocale.tip.business.device.detail 详情
				refresh:'Refreshing......',//EwayLocale.tip.business.device.refresh 正在刷新......
				chooseOrg:'Screen organization',//EwayLocale.tip.business.device.chooseOrg 机构筛选
				stateSet:'Status-monitor Setting',//EwayLocale.tip.business.device.stateSet 状态监控项配置
				filterSet:'Filter setting',//EwayLocale.tip.business.device.filterSet 过滤条件项配置
				connFirst:'Monitor connection has been paused at the present,please connect the server first as ‘start monitor’"',//EwayLocale.tip.business.device.connFirst 当前已经暂停了与服务器的监控连接,请先与服务器建立连接,即"开始监控"
				matrixPattern:'Matrix Pattern',//EwayLocale.tip.business.device.matrixPattern 矩阵方式
				listPattern:'List Pattern'//EwayLocale.tip.business.device.listPattern 列表方式
			},
		},

	},

	//**********************************************************/

	combox:{
		select:'Please select',//EwayLocale.combox.select --请选择--
		explorer: 'Browse...',//EwayLocale.combox.explorer 浏览...
	},



	vtype:{
		ip:'Please enter the correct IP address',//请输入正确的IP地址
		zip:'Please enter the correct ZIP ,6 numbers', //请输入正确的邮编格式，6位的数字
		versionNo:'不是正确的版本号格式,格式说明：1.版本号由4个部分组成 A.B.C.D ;2.只有A部分是必须的 ；3. A、B、C、D必须为大于等于0的整数 ,每个部分最大长度为8位； 4.ABCD部分必须用.分隔',
		terminalId:'Input error,The terminal serialno can only be ‘a-z’ or ‘A-Z’ or number ‘0-9’ or‘-’ or‘_’ or ‘.’ and must be start with character or number and length 1-20',//输入错误,设备号由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’点号‘.’组成,只能以字母或数字开头,长度1到20位。
		mobile:'Input error,mobile number can only be 8-11 numbers',//输入错误,手机号码只能输入8到11位数字。
		cardNo:'Input error,bankcard number can only be 16-19 numbers',//输入错误,银行卡号只能输入16到19位数字。
		telephone:'Input error,phone number can only be 8-11 numbers',//输入错误,固定电话号码只能输入8到11位数字。
		daterange:'Range of time is not correct.',//日期段不正确
		numberrange:'Range of money is not correct.',//金额范围不正确

		bankOrgCode:'Just for 1-20 characters ‘a-z’ or ‘A-Z’ or number ‘0-9’ or‘-’ or‘_’ or ‘.’,and must be start with character or number',//只能输入1到20字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’、点号‘.’， 只能以字母或数字开头！
		zip:'Just for 6 ‘0-9’ numbers',//只能输入6个‘0-9’的数字！
		maxLength20:'Max length is 20',//允许的最大长度为20
		numberRule: 'Just for character ‘a-z’ or ‘A-Z’、number‘0-9’、minussign‘-’、underline‘_’ or point‘.’,only start with character or number',//由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’和点号‘.’，只能以字母或数字开头。
		numberRulesOne: 'Just for character ‘a-z’ or ‘A-Z’、number‘0-9’、minussign‘-’、underline‘_’ or point‘.’,only start with Chinesecharacter,character or number,max length is 100',//由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’和点号‘.’、汉字，只能以汉字,字母或数字开头,最多可输入100位
		numberRulesFour	: 'Just for character ‘a-z’ or ‘A-Z’、number‘0-9’,max length is 40',//由字母‘a-z’或‘A-Z’、数字‘0-9’，最多可输入 40位
		numberRules: 'Just for character ‘a-z’ or ‘A-Z’、number‘0-9’、minussign‘-’、underline‘_’ or point‘.’,only start with Chinesecharacter,character or number,max length is 200',//由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’和点号‘.’、汉字，只能以汉字,字母或数字开头,最多可输入200位


		mobileRules:'Input error,mobile number can only be 8-11 numbers',//手机电话号码只能输入8到11位数字‘0-9’
		choseDev:'Please choose the machine which you want to stop.',//请选择您报停的设备.
		dataLoad:'Data loading......',//正在加载数据
		devLinkNormal:'Please check the connection between server and machine.',//请检查与设备的连接是否正常
		hardwayInitialize:'Hardware module initializing......',//硬件模块正在初始化
		inputCorrect:'Please input correct data.',//请正确输入
		exportRepError:'Export report error,please try again',//导出报表出错，请重新操作!
		planOutdate:'(This program is out of date,can not be applied)',//(此方案已过期，不可应用！)

		emailRules: 'email must accord with the rule of *@*.* ',//email必须符合*@*.*标准。
		notifyTimesRules: 'Times of notice must be numbers,minimum 0,maximum 100.',//通知次数必须为数字，最小值为0,最大值为100。
		sendTimesRules: 'Times of sending must be numbers,minimum 0,maximum 100.',//发送次数必须为数字，最小值为0,最大值为100。

		launchTranscribe:'Starting record......', //正在启动录制
		stopTranscribe:'Stoping record......',//正在停止录制......
		inexistenceScreen:'Screen not found',//不存在此屏幕
		devEmploy:'This machine is being used by ', //该台设备已经被
		userEmploy:'!',//用户占用!
		loadTranscribe:'Downloading the recorded video ......',//正在下载录制好的视频文件
		remoteFailure:'Remote browse failed',//远程浏览失败
		versionChart:'Chart of version-issued histroy status',//版本下发历史状态分布图
		openRefresh:'Start the automatic refresh',//开启自动刷新
		choseTask:'Please choose a Task',//EwayLocale.vtype.choseTask 请选择一个任务
		cancelTask:'Can not cancel the job which status is ‘finish’.',//不能撤销"完成"状态的作业
		cancelParticularTask:'Cancel the job?(running job can only cancel the task which is not running)(正在运行的作业只会撤销还没有运行的任务.)',//是否真的要撤销指定的作业?(正在运行的作业只会撤销还没有运行的任务.)
		nowDelete:'Deleting......', //正在删除......
	},
	//**********************************************************/
	versionType:{
		title:'Version Type',//EwayLocale.versionType.title 软件分类管理
		treeRoot:'All Types',//EwayLocale.versionType.treeRoot 所有软件分类
		defaultInstallPath:'Default Install Path',//EwayLocale.versionType.defaultInstallPath 默认安装路径
		needRestart:'Need Reboot For Update',//EwayLocale.versionType.needRestart 需要重启设备完成升级
		devTypeOfUser:'Suitable Device Type',//EwayLocale.versionType.devTypeOfUser //适用的设备型号
		winTitle:'Version Type',//EwayLocale.versionType.winTitle 软件分类
		versionTypeNameRegText:'Just for character ‘a-z’ or ‘A-Z’、number‘0-9’、minussign‘-’、underline‘_’'//EwayLocale.versionType.versionTypeNameRegText 只能输入字母(a-z或A-Z)、数字(0-9)、下划线(_)、横线(-)
	},
	statics:{
		title:'Distribution Statitics',//EwayLocale.statics.title
		versionInfo:'Version Info'//EwayLocale.statics.versionInfo //版本信息
	},
	//广告模块
	advert:{
		title:'Advert',//EwayLocale.advert.title //广告管理
		createAdvert:'Create Advert',//EwayLocale.advert.createAdvert 创建广告
		idleAdvert:'Idle Advert',//EwayLocale.advert.idleAdvert 创建等待插卡广告
		transAdvert:'Transaction Advert',//EwayLocale.advert.transAdvert 创建交易页面广告
		textAdvert:'Text Advert',//EwayLocale.advert.textAdvert 创建文字滚动广告
		annoucementAdvert:'Create',//EwayLocale.advert.annoucementAdvert 创建公告
		updateTitle:'Modify',//EwayLocale.advert.updateTitle 更改广告信息
		downloadButton:'Deploy',//EwayLocale.advert.downloadButton 下发广告
		preview:'Preview',//EwayLocale.advert.preview 广告预览
		preview1024:'Preview 1024 resolution',//EwayLocale.advert.preview1024预览1024分辨率
		preview800:'Preview 800 resolution',//EwayLocale.advert.preview800预览800分辨率
		preview600:'Preview 600 resolution',//EwayLocale.advert.preview600预览600分辨率
		id:'ID',//EwayLocale.advert.id 广告ID
		type:'Advert Type',//EwayLocale.advert.type 广告类型
		downType:'Issuing way',//EwayLocale.advert.downType 广告下发方式
		validity:'Expiry date',//EwayLocale.advert.validity 广告有效期
		createdTime:'Created time',//EwayLocale.advert.createdTime 制作时间
		userName:'Creater',//EwayLocale.advert.userName 制作人
		versionStatus:'Version status',//EwayLocale.advert.versionStatus 广告版本状态
		advertVersionFile:'Version file',//EwayLocale.advert.advertVersionFile 版本文件
		createdTimeStart:'Create time begin with',//EwayLocale.advert.createdTimeStart 制作时间开始于
		createdTimeStop:'End time end with',//EwayLocale.advert.createdTimeStop 制作时间结束于
		downloadAdvertConfig:'Deploy Settings',//EwayLocale.advert.downloadAdvertConfig 下发广告配置
		versionType:'Version type',//EwayLocale.advert.versionType 软件分类
		jobPriority:'Job priority',//EwayLocale.advert.jobPriority //作业优先级
		jobType:'Job type',//EwayLocale.advert.jobType 作业类型
		toVersionButton:'Has not generate version information,click button to [generate version]。',//EwayLocale.advert.toVersionButton 还没有生成版本信息，可以单击按钮[生成版本]。
		playTime:'Play Time',//EwayLocale.advert.playTime 广告播放时长
		beginDate:'Start Date',//EwayLocale.advert.beginDate 开始日期
		endDate:'End Date',//EwayLocale.advert.endDate 结束日期
		beginTime:'Start Time',//EwayLocale.advert.beginTime 开始时间
		endTime:'End Time',//EwayLocale.advert.endTime 结束时间
		fileSize:'Resource size',//EwayLocale.advert.fileSize 资源大小
		content:'Comment of play resource ',//EwayLocale.advert.content 播放资源内容
		advertConfig:'Settings',//EwayLocale.advert.advertConfig 广告配置
		addIdleTitle:'Add card-insert waiting advertisement',//EwayLocale.advert.addIdleTitle 增加等待插卡广告信息
		addIdleMore:'Add resource of advertisement again',//EwayLocale.advert.addIdleMore 再增加一个广告资源
		advertBasicInfo:'Basic info',//EwayLocale.advert.advertBasicInfo 广告基本信息
		idleAdvertInfo:'Card-insert waiting advertisement',//EwayLocale.advert.idleAdvertInfo 等待插卡页面广告
		advertValidity:'Expiry date',//EwayLocale.advert.advertValidity 广告有效期
		validityTemp:'Play temporary',//EwayLocale.advert.validityTemp 临时播放
		validityAlways:'Play forever',//EwayLocale.advert.validityAlways 永久播放
		idleAdvertResConfig:'Set the resource of card-insert waiting advertisement',//EwayLocale.advert.idleAdvertResConfig 等待插卡页面广告资源配置
		addTransTitle:'Add information of transacton-advertisement',//EwayLocale.advert.addTransTitle 增加交易页面广告信息
		transInfoAdvert:'Page of transcatiion-advertisement',//EwayLocale.advert.transInfoAdvert	 交易页面广告
		transAdvertResConfig:'Set the resource of transacton-advertisement-page',//EwayLocale.advert.transAdvertResConfig 交易页面广告资源配置
		addTextTitle:'Add information of rolling-characters-advertisement',//EwayLocale.advert.addTextTitle 增加文字滚动广告信息
		textInfoAdvert:'Characters-advertisement',//EwayLocale.advert.textInfoAdvert	 文字广告
		textAdvertResConfig:'Set the resource of rolling-characters-advertisement',//EwayLocale.advert.textAdvertResConfig 文字广告资源配置
		addAnnoucementTitle:'Add announcement',//EwayLocale.advert.addAnnoucementTitle 增加公告信息
		annoucementBasicInfo:'Basic information of announcement ',//EwayLocale.advert.annoucementBasicInfo 公告基本信息
		annoucementInfoAdvert:'Announcement',//EwayLocale.advert.annoucementInfoAdvert	 公告
		annoucementAdvertResConfig:'Set the resource of announcement-advertisement',//EwayLocale.advert.annoucementAdvertResConfig 公告页面广告资源配置
		advertTypeSelectEmpty:'Please choose type of advertisement',//EwayLocale.advert.advertTypeSelectEmpty 请选择广告类型
		advertTypeTrans:'Page of transaction',//EwayLocale.advert.advertTypeTrans 交易页面广告
		advertTypeIdle:'Page of card-insert waiting',//EwayLocale.advert.advertTypeIdle 等待插卡广告
		advertTypeText:'Rolling character advertisement',//EwayLocale.advert.advertTypeText 文字滚动广告
		advertTypeAnnou:'Announcement',//EwayLocale.advert.advertTypeAnnou 公告
		annoucementMoreTitle:'Add announcement',//EwayLocale.advert.annoucementMoreTitle 添加公告
		annoucementContext:'Comment of announcement',//EwayLocale.advert.annoucementContext Add announcement
		annoucementContextRegText:'Blank is not allowed',//EwayLocale.advert.annoucementContextRegText 不能包含空格
		times:'Duration',//EwayLocale.advert.times 时长
		timesTips:'Unit:second,tip:duration below 60 second will be better',//EwayLocale.advert.timesTips 单位:秒，提示：广告播放时长请控制在60秒内
		hourDisplay:'Hour',//EwayLocale.advert.hourDisplay 时
		minuteDisplay:'Minute',//EwayLocale.advert.minuteDisplay 分
		secondeDisplay:'Second',//EwayLocale.advert.secondeDisplay 秒
		textMoreTitle:'Add page with rolling-character',//EwayLocale.advert.textMoreTitle 添加文字滚动页面广告
		textContext:'Rolling character',//EwayLocale.advert.textContext 滚动文字
		idleMoreTitle:'Add page of card-insert waiting advertisement',//EwayLocale.advert.idleMoreTitle 添加等待插卡页面广告
		chooseMediaFile:'Please choose media file',//EwayLocale.advert.chooseMediaFile 请选择媒体文件
		uploadResource:'Upload resource...',//EwayLocale.advert.uploadResource 上传资源
		uploadResourceBlank:'Please upload resource...',//EwayLocale.advert.uploadResourceBlank 请上传资源
		uploadRegText:'Unsupported resource uploaded ,only .jpg,.aiv file can be uploaded',//EwayLocale.advert.uploadRegText 上传的资源格式不支持,只能上传.jpg、.avi格式的文件
		resourceFormatTips:'Only .jpg,.aiv file is supported',//EwayLocale.advert.resourceFormatTips (仅支持.jpg、.avi格式的文件)
		resourceAlias:'File name after modified',//EwayLocale.advert.resourceAlias 修改后的文件名
		transMoreTitle:'Add page of transaction advertisement',//EwayLocale.advert.transMoreTitle 添加交易页面广告
		chooseMediaFile:'Please choose media file',//EwayLocale.advert.chooseMediaFile 请选择媒体文件
		uploadResource:'Upload resource...',//EwayLocale.advert.uploadResource 上传资源
		uploadResourceBlank:'Please upload resource',//EwayLocale.advert.uploadResourceBlank 请上传资源
		addMorePic:'Add pictures',//EwayLocale.advert.addMorePic添加图片
		uploadRegText:'Unsupported resource uploaded ,only .jpg,.aiv file can be uploaded',//EwayLocale.advert.uploadRegText 上传的资源格式不支持,只能上传.jpg、.avi格式的文件
		resourceFormatTips:'Only .jpg,.aiv file is supported',//EwayLocale.advert.resourceFormatTips (仅支持.jpg、.avi格式的文件)
		resourceAlias:'File name after modified',//EwayLocale.advert.resourceAlias 修改后的文件名
		advertDownMethodCover:'Override',//EwayLocale.advert.advertDownMethodCover 覆盖
		uploading:'Resource uploading...',//EwayLocale.advert.uploading 正在上传资源...
		advertPreviewTitle0:'Preview advertisement (Total ',//EwayLocale.advert.advertPreviewTitle0 广告预览(共有
		advertPreviewTitle1:'resources,play the no. ',//EwayLocale.advert.advertPreviewTitle1 个资源,当前播放第
		advertPreviewTitle2:'at the present) ',//EwayLocale.advert.advertPreviewTitle2 //个
		choosedAdvertRes:'You have choosed',//EwayLocale.advert.choosedAdvertRes 您已经选择了
		limitNumberTenForEveryResolution:'Only upload up to 10 pictures at each resolution.',//EwayLocale.advert.limitNumberTenForEveryResolution每种分辨率下最多只能上传10张图片
		mustContainerOnePicAt1024:'1024 image resolution comprises at least one',//EwayLocale.advert.mustContainerOnePicAt10241024分辨率下至少包含一个图片
		deleteAdvertResource:'Delete the picture',//EwayLocale.advert.deleteAdvertResource删除该图片
		fileName:'File Name',//EwayLocale.advert.fileName文件名
		resourceName:'Resource Name',//EwayLocale.advert.resourceName资源名称
		perviewAdertWithIEBrowse:'Unsupported preview the video without IE explorer.',//EwayLocale.advert.perviewAdertWithIEBrowse 非IE浏览器不支持视频广告的预览
		fileFormatTipsInfo:"<font color='red'>Upload picture format is not supported, only upload .jpg format images</font>",//EwayLocale.advert.fileFormatTipsInfo上传的图片格式不支持,只能上传.jpg格式的图片
		idleAdvertUpTipsInfo:'<font color="red">Only supports .jpg and .gif format images; each resolution upload up to 10 pictures; each picture maximum 5M</font>',//EwayLocale.advert.idleAdvertUpTipsInfo仅支持.jpg和.gif格式的图片;每种分辨率最多上传10张图片;每张图片最大5M
		configTitle:'Detail Settings'//EwayLocale.advert.configTitle 广告详细配置
	},
	//版本管理模块
	version:{
		selectDeviceInfo0:"Device selected(<font color='red'>",//EwayLocale.version.selectDeviceInfo0 //已选择的设备 (<font color='red'>
		selectDeviceInfo1:"</font>)",//EwayLocale.version.selectDeviceInfo1 </font>)台
		addVersionTitle:'Add version information',//EwayLocale.version.addVersionTitle 增加版本信息
		batchTaskName:'Batch task name',//EwayLocale.version.batchTaskName 任务批次名称
		batchTaskNameEmpty:'Example:****first requirement update',//EwayLocale.version.batchTaskNameEmpty 例如:****需求第1批次升级
		UpdateTitle:'Change version information',//EwayLocale.version.UpdateTitle 更改版本信息
		addJobTitle:'Set version information',//EwayLocale.version.addJobTitle 配置作业信息
		downloadVersionId:'Issue version ID',//EwayLocale.version.downloadVersionId 下发版本ID
		taskType:'Task type',//EwayLocale.version.taskType 任务类型
		taskTypeManual:'Manual update',//EwayLocale.version.taskTypeManual 手动升级
		taskTypeAuto:'Auto update',//EwayLocale.version.taskTypeAuto 自动升级
		taskTypeScheduler:'Job plan',//EwayLocale.version.taskTypeScheduler 计划作业
		planTime:'Plan to execute time',//EwayLocale.version.planTime 计划执行时间
		selectableDevice:'Deivice can be issue',//EwayLocale.version.selectableDevice 可以下发的设备
		linkedDevice:'Selected device',//EwayLocale.version.linkedDevice 已选择的设备
		downloadVerFile:'Download version file',//EwayLocale.version.downloadVerFile 下载版本文件
		View:{
			title:'Version', //版本管理
			versionDetail:'Version Detail',//EwayLocale.version.View.versionDetail 版本详情
			remark:'description', //EwayLocale.version.View.remark 备注
			newCreate:'Create',//EwayLocale.version.View.newCreate 新建
			downLoaded:'DownLoaded',//EwayLocale.version.View.downLoaded 已下发
			waitting:'Waitting',//EwayLocale.version.View.waitting 等待下发
			versionPath:'Version path',//EwayLocale.version.View.versionPath 版本路径
			versionPathRegText:'不符合文件路径规则，规则如下：1.文件名只能包含英文字母(a-z A-Z)、数字(0-9)、下划线(_)、横线(-) ； 2.路径统一用正斜杠(/)作为分隔符 ；3.不区分大小 ; 示例 E: E:/yihua',//EwayLocale.version.View.versionPathRegText
			versionPathDesc:'(path of version file be installed)',//EwayLocale.version.View.versionPathDesc (版本文件在自助设备上的安装路径)
			versionPerson:'Version Creater',//EwayLocale.version.View.versionPerson 创建人
			versionType:'Version Type',//EwayLocale.version.View.versionType 版本类型
			versionFile:'Version File',//EwayLocale.version.View.versionFile 版本文件
			versionFileButton:'Choose...',//EwayLocale.version.View.versionFileButton 浏览...
			versionFileRegexText:'Only .zip or .rar file can be uploaded',//EwayLocale.version.View.versionFileRegexText 只能上传zip或rar格式的文件
			versionFileUploadMsg:'File uploading...',//EwayLocale.version.View.versionFileUploadMsg 正在上传文件...
			versionFileEmpty:'Please zip the version file(or folder) to .zip or .rar format',//EwayLocale.version.View.versionFileEmpty 请将要下发的版本文件(或者文件夹)打包zip或rar格式
			versionTypeCode:'Code',//EwayLocale.version.View.versionTypeCode 软件分类编码
			versionTypeName:'Name',//EwayLocale.version.View.versionTypeName 软件分类名称
			versionTypeId:'ID',//EwayLocale.version.View.versionTypeId 版本类型ID
			versionTypeEmpty:'-select versionType-',//EwayLocale.version.View.versionTypeEmpty -请选择版本类型-
			versionTime:'Create time',//EwayLocale.version.View.versionTime 创建时间
			versionNo:'Version No.',//EwayLocale.version.View.versionNo 版本号
			nowVersionNo:'Current Version No.',//EwayLocale.version.View.nowVersionNo 当前版本号
			versionStatus:'Version Status',//EwayLocale.version.View.versionStatus 版本状态
			versionStatusEmptyText:'All', //全部
			autoUpdate:'Auto Update',//EwayLocale.version.View.autoUpdate 允许自动更新
			autoUpdateYes:'Yes',//EwayLocale.version.View.autoUpdateYes //是
			autoUpdateNo:'No',//EwayLocale.version.View.autoUpdateNo 否
			autoUpdateEmptyText:'All', //全部
			dependVersion:'Dependented Version',//EwayLocale.version.View.dependVersion 依赖版本
			dependVersionEmptyText:'Please select type dependent on', //请选择依赖类型
			execBefore:'Execute script before update',//EwayLocale.version.View.execBefore 升级前执行脚本
			execBeforeEmptyText:'Please input the file in the update package which name is end with bat or cmd',//EwayLocale.version.View.execBeforeEmptyText 请输入升级包中的以bat或cmd结尾的文件
			execBeforeRegexText:'Only the file which name end with ‘bat’ or ‘cmd’ can be inputed',//EwayLocale.version.View.execBeforeRegexText 只能输入bat或cmd结尾的文件
			versionDesc:'Description',//EwayLocale.version.View.versionDesc 版本描述
			versionDescEmpty:'Please describe this version with words (At most 20 words)',//EwayLocale.version.View.versionDescEmpty 请用文字描述此版本需求
			otherConfigTitle:'Other Setting',//EwayLocale.version.View.otherConfigTitle 其他配置
			otherConfigAutoDown:'Allow update automatic (only while the ATM check new version from server ,the version which update automatic will be return to ATM )',//EwayLocale.version.View.otherConfigAutoDown 允许自动更新(当ATM向服务器检查新版本时，允许自动更新的版本才可以返回给ATM)
			otherConfigUncompress:'Uzip automatic(when choose this option, the ATM will unzip automatic )&nbsp;<font color="red">attention:if the file is not zip file at first ,and then ziped to zip file ,please select this option!</font>',//EwayLocale.version.View.otherConfigUncompress 自动解压缩(选中此项时，在ATM端会自动解压缩)&nbsp;<font color="red">注意：如果版本文件本来不符合zip格式，后被压缩成zip时，请选中此项！</font>
			versionServerPath:'File Path In Server', //EwayLocale.version.View.versionServerPath 文件在服务器上的位置
			versionName:'Name',//EwayLocale.version.View.versionName 版本名称
			downloadVersionName:'Version issued',//EwayLocale.version.View.downloadVersionName 下发的版本
			downloadVersionNameEmpty:'Please choose a version which you want to issue',//EwayLocale.version.View.downloadVersionNameEmpty 下发的版本
			distributionPic:'Chart of version distribute',//EwayLocale.version.View.distributionPic 版本分布图
			job:{
				newCreate:'New', //新建
				running:'Running', //运行中
				scheduler:'In plan',//计划中
				ready:'Prepare running',//准备运行
				pause:'Pause',//暂停
				complete:'Finish' //完成
			}
		},
		VersionInstallInfo:{
			title:'Chart of version installed count' //版本安装信息统计图
		},
		jobPriority:{
			hight:'Hight',//EwayLocale.version.jobPriority.hight 高
			middle:'middle',//EwayLocale.version.jobPriority.middle 中等
			general:'general'//EwayLocale.version.jobPriority.general 普通
		},
		jobType:{
			float:'Manual job(issue immediately)',//EwayLocale.version.jobType.float 手工作业(立即下发)
			fix:'Plan job (issue timer)'//EwayLocale.version.jobType.fix 计划作业(定时下发)
		},
		taskStatus:{
			news:'New',//EwayLocale.version.taskStatus.news 新建
			running:'running',//EwayLocale.version.taskStatus.running 运行中
			noticed:'Notice successful',//EwayLocale.version.taskStatus.noticed 通知成功
			noticedFail:'Notice failed',//EwayLocale.version.taskStatus.noticedFail 通知失败
			downloaded:'Issued',//EwayLocale.version.taskStatus.downloaded 已下发
			downloadedFail:'Issue failed',//EwayLocale.version.taskStatus.downloadedFail 下发失败
			deployed:'Installing',//EwayLocale.version.taskStatus.deployed 正在部署
			deployedWait:'Wait for install',//EwayLocale.version.taskStatus.deployedWait 等待部署
			deployedFail:'Install failed',//EwayLocale.version.taskStatus.deployedFail 部署失败
			checked:'Confirmed to install',//EwayLocale.version.taskStatus.checked 部署已确认
			noticeOk:'Noticed application',//EwayLocale.version.taskStatus.noticeOk 已通知应用
			taskResetSuccessTips:'Success reset task!',//EwayLocale.version.taskStatus.taskResetSuccessTips
			noticeFail:'Notic application failed'//EwayLocale.version.taskStatus.noticeFail 通知应用失败
		},
		versionCatalog:{
			app:'Application',//EwayLocale.version.versionCatalog.app 应用程序
			agent:'Monitor agent',//EwayLocale.version.versionCatalog.agent 监控代理
			param:'Application settings'//EwayLocale.version.versionCatalog.param 应用配置
		},
		download:{
			title:'Distribute minitor',//EwayLocale.version.download.title 分发监控
			updateTitle:'Modify version information',//EwayLocale.version.download.updateTitle 修改版本下发信息
			taskQueryTips:'Query job detail by condition',//EwayLocale.version.download.taskQueryTips 根据条件查询选中作业下的详情信息
			autoRefresh:'Start auto refresh',//EwayLocale.version.download.autoRefresh 开启自动刷新
			stopAutoRefresh:'Stop auto refresh',//EwayLocale.version.download.stopAutoRefresh 停止自动刷新
			cancelBatch:'cancelBatch',//EwayLocale.version.download.cancelBatch
			autoRefreshTips:'Refresh periodic 60 second',//EwayLocale.version.download.autoRefreshTips 刷新周期60秒
			resetTaskStatus:'Reset Status',//EwayLocale.version.download.resetTaskStatus
			selectTask:'Please select a task record！',//EwayLocale.version.download.selectTask请选择一条任务记录
			selectAllDevice:'All equipment',//EwayLocale.version.download.selectAllDevice全部设备
			checkedTaskCantResetTips:'Non-running task can not be reset！',//EwayLocale.version.download.checkedTaskCantResetTips非运行中的任务无法重置
			taskExportTips:'Export all issue result of job selected'//EwayLocale.version.download.taskExportTips 导出选中作业下的全部下发结果
		},
		task:{
			selectJobStartRefresh:'Please choose a job,and then start automatic refresh',//EwayLocale.version.task.selectJobStartRefresh 请选择一个作业,再开启定时刷新！
			jobBatchName:'Job bacth name',//EwayLocale.version.task.jobBatchName 作业批次名称
			patchVersion:'Distribute version',//EwayLocale.version.task.patchVersion 分发版本
			taskStatus:'Task status',//EwayLocale.version.task.taskStatus 任务状态
			beforeUpdate:'Version before distribute',//EwayLocale.version.task.beforeUpdate 分发前的版本
			exceptVersion:'Version expect',//EwayLocale.version.task.exceptVersion 预期版本
			actionTime:'Execute time',//EwayLocale.version.task.actionTime 执行时间
			downSource:'Source download',//EwayLocale.version.task.downSource 下载源
			planTime:'Plan time',//EwayLocale.version.task.planTime 计划时间
			excuteMachine:'Execute machine',//EwayLocale.version.task.excuteMachine 执行服务器
			restartATM:'Restart ATM',//EwayLocale.version.task.restartATM 重启ATM
			restartATMTips:'Confirm execute reboot command?it may brings some risk.',//EwayLocale.version.task.restartATMTips 执行重启命令可能存在风险,确认重启?
			sendRestartCmd:'Reboot command has been sent!',//EwayLocale.version.task.sendRestartCmd 已发送重启命令！
			cancelDownloadSuccess:'Cancel issue successful!',//EwayLocale.version.task.cancelDownloadSuccess 取消下发成功
			cancelDownload:'Cancel issue',//EwayLocale.version.task.cancelDownload 取消下发
			jobName:'Job name',//EwayLocale.version.task.jobName 作业名称
			jobStatus:'Job status',//EwayLocale.version.task.jobStatus 作业状态
			chooseTitleDevice:'Choose machine',//EwayLocale.version.task.chooseTitleDevice 选择设备
			closeWindow:'Close',//EwayLocale.version.task.closeWindow 关闭窗口
			queryByFilter:'Query by filter',//EwayLocale.version.task.queryByFilter 根据条件查找
			displayNumPerPage:'Display on each page',//EwayLocale.version.task.displayNumPerPage 每页显示条数
			targetVersionNo:'Target version',//EwayLocale.version.task.targetVersionNo 目标版本
			downloadStatus:'Issue status',//EwayLocale.version.task.downloadStatus 下发状态
			downloadResult:'Issue result',//EwayLocale.version.task.downloadResult 下发结果
			cancelJob:'Cancel job',//EwayLocale.version.task.cancelJob 撤销作业
			jobId:'Job ID',	//EwayLocale.version.task.jobId 作业ID
			selectDownloadDevice:'Choose deivce to issue',	//EwayLocale.version.task.selectDownloadDevice 选择下发的设备
			versionNoBeforeUpdate:'Version before update',	//EwayLocale.version.task.versionNoBeforeUpdate 升级前版本号
			versionNoAfterUpdate:'Target version no',	//EwayLocale.version.task.versionNoAfterUpdate 目标版本号
			deviceVersionHis:'View device history version',	//EwayLocale.version.task.deviceVersionHis 查看设备历史版本
			downloadUser:'Issue man',	//EwayLocale.version.task.downloadUser 下发人
			downloadTime:'Issue time',	//EwayLocale.version.task.downloadTime 下发时间
			downloadResult:'Issue result',	//EwayLocale.version.task.downloadResult 下发结果
			deviceVersionHisTitle:'Information of device history version',	//EwayLocale.version.task.deviceVersionHisTitle 设备历史版本信息
			deviceVersions:'Device version',	//EwayLocale.version.task.deviceVersions 设备版本
			deviceVersionHisTip:'View information of device history version',	//EwayLocale.version.task.deviceVersionHisTip 查看设备历史版本信息
			autoUpdateInfo:'Information of auto update',//EwayLocale.version.task.autoUpdateInfo 自动更新信息
			selectAJob:'Please choose a job.',//EwayLocale.version.task.selectAJob 请选择一个作业
			versionDownHisStatusPic:'StatusDistribute',//EwayLocale.version.task.versionDownHisStatusPic 版本下发历史状态分布图
			versionNoPic:'VersionDistribute',//EwayLocale.version.task.versionNoPic
			cantCancelCompleteJob:'Can not cancel job which status is ‘finish’.',//EwayLocale.version.task.cantCancelCompleteJob 不能撤销"完成"状态的作业
			doSureCancelTheJob:'Cancel the selected job?(running job can only cancel the task which has not running)',//EwayLocale.version.task.doSureCancelTheJob 是否真的要撤销指定的作业?(正在运行的作业只会撤销还没有运行的任务.)
			deleting:'Deleting......',//EwayLocale.version.task.deleting 正在删除
			cancelSuccessBut:'Cancel the task in the job which has not run yet successful,the status is "running" at the present,please refresh the list',//EwayLocale.version.task.cancelSuccessBut
			cancelJobSuccess:'Cancel job successful',//EwayLocale.version.task.cancelJobSuccess 成功撤销作业
			updateResult:'Update result'	//EwayLocale.version.task.updateResult 升级结果
		}
	},


	//**********************************************************/
	agent:{
		remote:{
			screen:{
				message:'info', //信息
				startcustom:'Start recording front-screen of customer', //开始录制客户前屏
				stopcustom:'Stop recording front-screen of customer',//停止录制客户前屏
				startadmin:'Start recording front-screen of manager',//开始录制管理后屏
				stopadmin: 'Stop recording front-screen of manager',//停止录制管理后屏
				startadvertise: 'Start recording advertisement-screen',//开始录制广告屏
				stopadvertise: 'Stop recording advertisement-screen',//停止录制广告屏
				startCameraDate: 'Recording start time',//开始录制时间
				stopCameraDate: 'Recording end time',//停止录制时间
				monitorType: 'Screen type', //屏幕类型
				fileNameClient: 'File name',//文件名称
				nowCamera:'Recording...',//正在录制
				finishCamera:'Recording finish',//完成录制
				videoLoad:'Downloading the video file to the server...',//正在将视频文件下载至服务端...
				stopManage:'Automatic stopped ,please contact the manager!',//自动停止.如需取得视频文件,请联系管理员!
				manage: 'Operater',	//操作
				loading:'DownLoad',//EwayLocale.agent.remote.screen.loading 下载
				screenCamera: 'Screen recording',//屏幕录制
			},
			discInfo: 'Disk info', //磁盘信息
			discName: 'Disk Pattern name',//磁盘分区名称
			fileSys: 'Disk file system',//磁盘文件系统
			totalSize: 'Disk size total',//磁盘总大小
			freeSize: 'Disk size usable',//磁盘可用空间大小
			networkInfo:'Net connection info',//网络连接信息
			conenctRate: 'Connection speed',//连接速率
			receivedByte: 'Bytes received',//接收到的字节数
			sendByte: 'Bytes sended',//发动的字节数
			loadData: 'Data loading,please wait...',//加载数据中，请稍候...
			refresh: 'Refresh',//刷新
			name: 'Name',//名称
			format: 'Format',//格式
			totalSize: 'Total Size',//总大小
			freeSize: 'Usable size',//可用空间
			refreshFailure:'Refresh failed!',//刷新失败
			back:'Back',//返回
			upload: 'upload',//上传
			Mkdir: 'New folder',//新建文件夹
			MkFile: 'New file',//新建文件
			MKcatalog:'New folder',//新建文件夹
			catalogName:'Folder name',//目录名称
			remove: 'Delete',//删除
			execute: 'Execute',//执行
			path: 'Path',//路径
			search: 'Search',//搜索
			size: 'Size',//大小
			fileSize:'File size：',//文件大小：
			lastTime: 'Last modify time',//最后修改时间
			clickLoadFile:'Click to download this file',//单击即可下载该文件
			loadFileSize:'Max size of download file is 300M',//下载文件 不能超过200M！
			nowLoadFile:'File downloading...',//正在下载文件......
			judgeLoad: '是否续传下载！',//是否续传下载！
			loadFailure:'Download failed！',//下载失败！
			number: 'Serial number',//编号
			programName: 'Application name',//程序名称
			version: 'Version number',//版本号
			publisher: 'Publishers',//发布商
			diskUsed: 'Disk useage',//磁盘使用
			softwayList: 'List of software installed',//软件安装列表
			networkInfo: 'Network info',//网络信息
			networkLinkStatus: 'Netwok connect status',//网络连接状态
			send: 'Sended',//已发送
			receive: 'Received',//已接收
			bite: 'byte:',//字节
			speed: 'Speed(Mbps):',//速度
			bandWidth: 'Broadband speed test',//宽带测速
			unit: 'Unit:MB',//单位：MB
			againTest: 'Test again',//重新测试
			impressionName: 'Impression name',//印象名称
			userName: 'User Name',//用户名
			memoryRate: 'Memory used',//内存使用
			systemProgressInfo:'System process information',//系统进程信息
			screenShotTime: 'Screen shot time',//截屏时间
			distanceScreen: 'Remote screenshot',//远程抓屏
			distanceExplorer: 'Remote browse',//远程浏览
			ATMExamination:'Physical ATM',//ATM体检
			checkATM: 'Physical again',//重新体检
			ATMExamInfo: 'Physical ATM detail',//ATM体检详情
			cpuIdle: 'Free rate of CPU',//CPU空闲率
			memoryIdle: 'Free rate of memory',//内存空闲率
			hardDiskIdle: 'Free rate of disk',//硬盘空闲率
			uploadFile: 'Upload file',//上传文件
			rules:'Can not contain:\/?*":<>|',//不能包含一下字符
			nowCreat:'Creating...',//正在新建
			nowPath: 'Current path',//当前路径
			confirm: 'Save', //保存
			reset: 'Reset',//重置
			back: 'Back',//返回
			prepareFile:'File wait for upload',//待上传文件
			choseUploadFile:'Please choose file to upload',//请选择上传文件
			nowUploadFile:'File uploading',//正在上传文件...
			explorer: 'Browse...',//浏览...
			serverPath: 'Path of file in server',//文件在服务器上的位置
			distanceSuccess:'Remote create successful.',//远程创建成功
			distanceFailure:'Remote create failed.',//远程创建失败
			confirmDelete:'Confirm delete',//确定删除
			confirmExecute:'Confirm execute',//确定执行
			choseDeleteFile:'Please choose file which you want to delete.',//请选择要删除的文件
			distanceExecuteSuccess:'Remote execute successful.',//远程执行成功
			distanceExecuteFailure:'Remote execute failed.',//远程执行失败
			choseExecuteFile:'Please choose file which you want to execute.',//请选择要执行的文件
			distanceExplorer:'Remote browse:',//远程浏览
			distanceExplorerFailure:'Remote browse failed.',//远程浏览失败
			fileExist:'File already exist!',//该文件已存在！
			nowUploadFile:'File uploading......',//正在上传文件......
			uploadSuccess:'Upload successful.',//上传成功
			yes: "Continue transfer",//续传
			no: "Override",//覆盖
			cancel: "Cancel", //取消
			choseFile:'No file selected for upload,please choose.',//未选择上传文件,请选择文件.
			returnFailure:'Return fail.',//返回失败
			refreshFailure:'Refresh failed.',//刷新失败
			catalogExist:'The folder does not exist,please input again',//搜索的目录不存在,请重新输入.
			testBandWidth:'Test broadband error.', //测试宽带异常
			networkMaxSpeed:'Max net speed is',//网络最大接入速度为
			minutes:'Second',//秒
			amount:'Like',//相当于
			specialLine:'Special line',//专线
			bandWidth:'Broadband',//宽带
			handle:'Processing......',//正在处理......
			offServer:'Lost connection from server.',//与服务器断开连接
			submitingWaiting:'Being submitted, please wait...',//EwayLocale.agent.submitingWaiting正在提交,请稍等
			ATMCheck:'Executing physical of ATM...',//正在ATM体检中
			excellent:'excellent',//优
			fine:'good',//良
			middle:'middle',//中
			bad:'bad',//差
			point:'point',//分
			checkFailure:'Execute physical of ATM failed,please try again.',//ATM体检失败,请重新操作
			checkVersionInfo:'View version information',//查看版本信息
			versionInfo:'The version information :',//您要查看的版本信息如下:
			ATMCVersion:'Version of ATMC application',//ATMC应用版本
			monitorVersion: 'Version of monintor client',//监控客户端版本

		}
	},
	commen:{

		jobNum:'JobNo',//EwayLocale.commen.jobNum 工号
		name:'Name',//EwayLocale.commen.name 姓名
		personJobName:'Quarters',//EwayLocale.commen.personJobName 岗位
		state:'Status',//EwayLocale.commen.state 状态
		birthday:'Birthday',//EwayLocale.commen.birthday 生日
		comboxStatus:{
			onJob:'On job',//EwayLocale.commen.comboxStatus.onJob 在岗
			onAdjust:'On adjust',//EwayLocale.commen.comboxStatus.onAdjust 调休
			onVacation:'On vacation',//EwayLocale.commen.comboxStatus.onVacation 休假
			other:'other',//EwayLocale.commen.comboxStatus.other 其他
			dredge:'Dredge',//EwayLocale.commen.comboxStatus.dredge 开通
			open:'Open',//EwayLocale.commen.comboxStatus.open 启用
			close:'Close',//EwayLocale.commen.comboxStatus.close 停用
			pastDue:'Expired',//EwayLocale.commen.comboxStatus.pastDue过期
			pastDueSoon:'About to expire',//EwayLocale.commen.comboxStatus.pastDueSoon即将过期
		},
		type:'Type',//EwayLocale.commen.type 类型
		comboxType:{
			machineManager:'Machine manager',//EwayLocale.commen.comboxType.machineManager 管机员
			machineRepairer:'Machine repairer'//EwayLocale.commen.comboxType.machineRepairer 维修人员
		},
		mobile:'Mobile',//EwayLocale.commen.mobile 手机
		email:'Email',//EwayLocale.commen.email 邮箱
		phone:'Phone',//EwayLocale.commen.phone 固话
		gender:'Gender',//EwayLocale.commen.gender 性别
		all:'All',//EwayLocale.commen.all 全部
		comboxGender:{
			male:'Male',//EwayLocale.commen.comboxGender.male 男
			female:'Female',//EwayLocale.commen.comboxGender.female 女
			unknow:'Unknow'//EwayLocale.commen.comboxGender.unknow 未知
		},
		remark:'Remark',//EwayLocale.commen.remark 备注
		terminalId:'Terminal Id',//EwayLocale.commen.terminalId 设备号
		ip:'IP',//EwayLocale.commen.ip 网络地址
		orgNameBelongs:'Organization belongs',//EwayLocale.commen.orgNameBelongs 所属机构
		devTypeName:'Deivce type',//EwayLocale.commen.devTypeName 设备型号
		devVendorName:'Deivce vendor',//EwayLocale.commen.devVendorName 设备品牌
		devCatalogName:'Device catalog',//EwayLocale.commen.devCatalogName 设备类型
		devStatus:'Device status',//EwayLocale.commen.devStatus 设备状态
		comboxDevStatus:{
			upOpen:'upOpen',//EwayLocale.commen.comboxDevStatus.upOpen
			open:'Open',//EwayLocale.commen.comboxDevStatus.open
			stop:'Stop',//EwayLocale.commen.comboxDevStatus.stop
			scrapped:'Scrapped'//EwayLocale.commen.comboxDevStatus.Scrapped
		},
		setManager:'Setting',//EwayLocale.commen.setManager 设置
		devServiceName:'Maintenance of device',//EwayLocale.commen.devServiceName 设备维护商
		cashboxLimit:'Alarm of money in cashbox',//EwayLocale.commen.cashboxLimit 钞箱报警金额
		installDate:'Install Date',//EwayLocale.commen.installDate 安装日期
		address:'Address',//EwayLocale.commen.address 地址
		areaCode:'Area code',//EwayLocale.commen.areaCode 区域编号
		areaName:'Area name',//EwayLocale.commen.areaName 区域名称
		toolbar:'Total：{2},display{0}-{1}',//EwayLocale.commen.toolbar 总共：{2}条，显示{0}-{1}
		bindMachine :'Device binded',//EwayLocale.commen.bindMachine 已关联的设备
		lift:'Lift',//EwayLocale.commen.lift 解除
		canBindMachine:'Device can be binded',//EwayLocale.commen.canBindMachine 可关联的设备
		bind:'Bind',//EwayLocale.commen.bind 关联
		filter:'Filter',//EwayLocale.commen.filter 过滤条件
		stateDict:{
			newCreate:'New',//EwayLocale.commen.stateDict.newCreate 新建
			normal:'Normal',//EwayLocale.commen.stateDict.normal 正常
			locked:'Locked',//EwayLocale.commen.stateDict.locked 锁定
			disable:'Disable',//EwayLocale.commen.stateDict.disable 无效
			frozen:'Frozen',//EwayLocale.commen.stateDict.frozen 冻结
			deleted:'Deleted'//EwayLocale.commen.stateDict.deleted 已删除
		},
		yes:'yes',//EwayLocale.commen.yes 是
		no:'no',//EwayLocale.commen.no 否
		selectAll:'Select all',//EwayLocale.commen.selectAll 全部选择
		selectNon:'select non',//EwayLocale.commen.selectNon 全部不选
		content:'Content',//EwayLocale.commen.content 消息
		upgrade:'Upgrade',//EwayLocale.commen.upgrade 上级
		port:'port',//EwayLocale.commen.port 网络Port
		previous:'Previous',//EwayLocale.commen.previous 上一页
		next:'next',//EwayLocale.commen.next 下一页
		installAddr:'Install address',//EwayLocale.commen.installAddr 装机地址
		seviceMode:'Service mode',//EwayLocale.commen.seviceMode 经营方式
		insideOutside:'In bank flag',//EwayLocale.commen.insideOutside 在行标志
		appVersion:'Application version',//EwayLocale.commen.appVersion 应用版本号
		devInfo:'Device basic info',//EwayLocale.commen.devInfo 设备基本信息
		//check end
		personnel:'Contacter',//EwayLocale.commen.personnel 联系人
		warn:'Warn',//EwayLocale.commen.warn 警告
		fatal:'Fatal',//EwayLocale.commen.fatal 故障
		unStable:'UnStable',//EwayLocale.commen.unStable 不稳定
		unknow:'Unknow',//EwayLocale.commen.unknow 未知
		description:'Description',//EwayLocale.commen.description 描述
		info:'Detail',//EwayLocale.commen.info 详细信息
		startDataTime:'Start time',//EwayLocale.commen.startDataTime 开始时间
		endDataTime:'End time',//EwayLocale.commen.endDataTime 结束时间
		year:'Year',//EwayLocale.commen.year 年
		month:'Month',//EwayLocale.commen.month 月
		day:'Day',//EwayLocale.commen.day 日
		yearTime:'Year',//EwayLocale.commen.yearTime 年份
		monthTime:'Month',//EwayLocale.commen.monthTime 月份
		dayTime:'Day',//EwayLocale.commen.dayTime 日期
		orgFramework:'Organization',//EwayLocale.commen.orgFramework 组织机构
		matchOrg:'Organization matched',//EwayLocale.commen.matchOrg 匹配机构
		orgID:'Organization ID',//EwayLocale.commen.orgID 机构ID
		endValidty:'Valid date until',//EwayLocale.commen.endValidty 截止有效期
		publishDate:'Issue date',//EwayLocale.commen.publishDate 发布日期
		announceTheme:'Announce theme',//EwayLocale.commen.announceTheme 公告主题



	},
	//**********************************************************/
	person:{
		bankOrg :{
			title:'Banks',//EwayLocale.person.bankOrg.title 银行机构管理
			moveBankTitle:'Migrate organization',//EwayLocale.person.bankOrg.moveBankTitle 组织迁移
			updateBankTitle:'Modify organization info',//EwayLocale.person.bankOrg.updateBankTitle 更改银行机构信息
			addBankOrgTitle:'Add organization info',//EwayLocale.person.bankOrg.addBankOrgTitle 增加银行机构信息
			code:'Code',//EwayLocale.person.bankOrg.code 机构编号
			orgType:'Type',//EwayLocale.person.bankOrg.orgType 机构类型
			name:'Name',//EwayLocale.person.bankOrg.name 机构名称
			orgLevel:'Level',//EwayLocale.person.bankOrg.orgLevel 机构级别
			orgNavi:'Navigator',//EwayLocale.person.bankOrg.orgNavi 机构导航
			zip:'Zip',//EwayLocale.person.bankOrg.zip 邮政编码
			removeManager:'Remove',//EwayLocale.person.bankOrg.removeManager 删除
			manager:'Manager',//EwayLocale.person.bankOrg.manager 管理员
			address:'Address',//EwayLocale.person.bankOrg.address 机构地址
			upgradeOrg:'Upper',//EwayLocale.person.bankOrg.upgradeOrg 上级机构
			description:'Description',//EwayLocale.person.bankOrg.description 机构描述
			serOrganization:'Maintenance description',//EwayLocale.person.bankOrg.serOrganization
			organizationType:{
				bank:'Bank',//EwayLocale.person.bankOrg.organizationType.bank 银行
				serviceOrg:'Maintenance'//EwayLocale.person.bankOrg.organizationType.serviceOrg 维护商
			},
			organizationLevelDict:{
				rootBank:'Head Office',//EwayLocale.person.bankOrg.organizationLevelDict.rootBank 总行
				branchBank:'Branch',//EwayLocale.person.bankOrg.organizationLevelDict.branchBank 分行
				tagBranchBank:'SubBranch',//EwayLocale.person.bankOrg.organizationLevelDict.tagBranchBank 支行
				netBank:'netBank'//EwayLocale.person.bankOrg.organizationLevelDict.netBank 网点
			},
			addOrgTitle:'Person under this organization',//EwayLocale.person.bankOrg.addOrgTitle 该机构人员信息
			linkPeronTitle:'Person under the organization:',//EwayLocale.person.bankOrg.linkPeronTitle 当前机构下人员
			personList:'Person list',//EwayLocale.person.bankOrg.personList 人员列表
			orgLinkTitle:'Person and device which link this organization',//EwayLocale.person.bankOrg.orgLinkTitle 该机构的关联设备和人员
			machineOrg:'Device under the organization:',//EwayLocale.person.bankOrg.machineOrg
			personOrg:'Person under the organization:'//EwayLocale.person.bankOrg.personOrg

		},
		bankPer :{
			title:'Bankers',//EwayLocale.person.bankPer.title 银行人员管理
			organizationName:'Organization',//EwayLocale.person.bankPer.organizationName 机构
			orgNavi:'Organization Navigator',//EwayLocale.person.bankPer.orgNavi 机构导航
			addBankPerTitle:'Add information of bank person ',//EwayLocale.person.bankPer.addBankPerTitle 增加银行人员信息
			updateBankPerTitle:'Modify information of bank person '//EwayLocale.person.bankPer.updateBankPerTitle 更改银行人员信息

		},
		serviceOrg:{
			title:'Maintenance',//EwayLocale.person.serviceOrg.title 维护商管理
			serviceNavi:'Manufacturer Navigator',//EwayLocale.person.serviceOrg.serviceNavi 厂商导航
			serviceOrgAdmin:'Manager',//EwayLocale.person.serviceOrg.serviceOrgAdmin 管理员
			setManager:'Setting',//EwayLocale.person.serviceOrg.setManager 设置
			removeManager:'Delete',//EwayLocale.person.serviceOrg.removeManager 删除
			code:'Manufacturer No',//EwayLocale.person.serviceOrg.code 厂商编号
			name:'Manufacturer name',//EwayLocale.person.serviceOrg.name 厂商名称
			zip:'Zipcode',//EwayLocale.person.serviceOrg.zip 邮政编码
			shortName:'Manufacturer',//EwayLocale.person.serviceOrg.shortName 厂商
			address:'Manufacturer address',//EwayLocale.person.serviceOrg.address 厂商地址
			description:'Manufacturer description',//EwayLocale.person.serviceOrg.description 厂商描述
			addServiceTitle:'Add manufacturer',//EwayLocale.person.serviceOrg.addServiceTitle 增加维护商信息
			upgradeService:'Manufacturer upgrade',//EwayLocale.person.serviceOrg.upgradeService 上级厂商
			updateServiceTitle:'Modify manufacturer',//EwayLocale.person.serviceOrg.updateServiceTitle 更改维护商信息
			personDevSerLink:'Person and device which link this manufacturer',//EwayLocale.person.serviceOrg.personDevSerLink 该厂商的关联设备和人员
			devSerLink:'Device under the manufacturer',//EwayLocale.person.serviceOrg.devSerLink 该厂商下设备
			personSerLink:'Person under the manufacturer:',//EwayLocale.person.serviceOrg.personSerLink 该厂商下人员

			directOrganization:'的直接下级机构'
		},
		servicePer:{
			title:'Maintenancers',//EwayLocale.person.servicePer.title 维护人员管理
			servicePerlink:'Binding Device',//EwayLocale.person.servicePer.servicePerlink 关联设备
			addServicePerTitle:'Add maintenancer',//EwayLocale.person.servicePer.addServicePerTitle 增加维护人员信息
			updateServicePerTitle:'Update maintenancer',//EwayLocale.person.servicePer.updateServicePerTitle 更改维护人员信息

			maintainInfo:'All Maintenancers'//所有维护商人员信息
		},
		user:{
			title:'Users',//EwayLocale.person.user.title 用户管理
			code:'Code',//EwayLocale.person.user.code 用户名
			clickToCheckLog:'Click to Query Log',//EwayLocale.person.user.clickToCheckLog 单击即可查看用户
			userLog:' s log',//EwayLocale.person.user.userLog  的操作日志
			resetPasswd:'Reset Password',//EwayLocale.person.user.resetPasswd 密码重置
			clickToPasswdInit:'Click to set the password to initialize',//EwayLocale.person.user.clickToPasswdInit 单击即可密码重置为初始化密码
			clickToRole:'Click to check all roles list',//EwayLocale.person.user.clickToRole 单击即可查看所有角色列表
			addUserTitle:'Add user information',//EwayLocale.person.user.addUserTitle 增加用户信息
			clickToUser:'Please click query ,choose a  person',//EwayLocale.person.user.clickToUser 请点击查询，选择人员
			userType:'User Type',//EwayLocale.person.user.userType 用户类型
			role:'Role',//EwayLocale.person.user.role 角色
			roleGiven:'Role given',//EwayLocale.person.user.roleGiven 角色赋予
			roleName:'Name',//EwayLocale.person.user.roleName 角色名称
			roleDescription:'Description',//EwayLocale.person.user.roleDescription 角色描述
			userListTitle:'userList',//EwayLocale.person.user.userListTitle 人员列表
			updateUserTitle:'Modify user role(use drafting way)',//EwayLocale.person.user.updateUserTitle 更改用户角色（使用拖拽的方式）
			roleCanBeAdd:'Role list can be added',//EwayLocale.person.user.roleCanBeAdd 可添加的角色列表
			roleAlreadyBeAdd:'Role list already be added',//EwayLocale.person.user.roleAlreadyBeAdd 已添加的角色列表
			operCode:'Operator no',//EwayLocale.person.user.operCode 操作人编号
			operName:'Operator name',//EwayLocale.person.user.operName 操作人姓名
			operTime:'Operate time',//EwayLocale.person.user.operTime 操作时间
			operResult:'Operate result',//EwayLocale.person.user.operResult 操作结果
			operContent:'Operate content',//EwayLocale.person.user.operContent 操作内容
			roleListTitle:'User roles list',//EwayLocale.person.user.roleListTitle 用户角色列表
			operTitle:'Operator log',//EwayLocale.person.user.operTitle 操作员日志
			operDetailTitle:'Operate logs',//EwayLocale.person.user.operDetailTitle 操作日志信息
			operLogList:'Operate logs list',//EwayLocale.person.user.operLogList 操作日志列表
			personDevice:'User<-->Device',//EwayLocale.person.user.personDevice 人员<-->设备
			rootUser:'Super user',//EwayLocale.person.user.rootUser 超级用户
			generalUser:'General User'//EwayLocale.person.user.generalUser 普通用户
		}

	},

	//**********************************************************/
	permission:{
		systemMenu:'System', //系统菜单
		role:{
			title:'Roles',//EwayLocale.permission.role.title 角色管理
			update:'Update role',//EwayLocale.permission.role.update 更改角色
			name:'Role name',//EwayLocale.permission.role.name 角色名称
			type:'Role type',//EwayLocale.permission.role.type 角色类型
			description:'Role description',//EwayLocale.permission.role.description 角色描述
			isSysRole:'Is system role',//EwayLocale.permission.role.isSysRole 是否是系统内置角色
			chooseRight:'Please choose permission of menu',//EwayLocale.permission.role.chooseRight 请选择菜单权限
			add:'Add role'//EwayLocale.permission.role.add 增加角色
		},
		permission:{
			menuName:'Menu name',//EwayLocale.permission.permission.menuName 菜单名称
			menuDescription:'Menu description',//EwayLocale.permission.permission.menuDescription 菜单描述
			menuPermission:'Menu permission'//EwayLocale.permission.permission.menuPermission 菜单权限
		}
	},

	//**********************************************************/
	monitor:{
		summary:{
			title:'Status Overview',//EwayLocale.monitor.summary.title
			allSummary:'All',//EwayLocale.monitor.summary.allSummary
			appSummary:'ATMC',//EwayLocale.monitor.summary.appSummary
			modSummary:'Module',//EwayLocale.monitor.summary.modSummary
			boxSummary:'Cashbox',//EwayLocale.monitor.summary.boxSummary
			netSummary:'Net'//EwayLocale.monitor.summary.netSummary
		},
		devMonitor:{
			title:'Status Monitor',//EwayLocale.monitor.devMonitor.title 状态监控
			comboxStatus:{
				runStatus:'ATMC Status',//EwayLocale.monitor.devMonitor.comboxStatus.runStatus 运行状态
				modStatus:'Module Status',//EwayLocale.monitor.devMonitor.comboxStatus.modStatus 模块状态
				boxStatus:'Cashbox Status',//EwayLocale.monitor.devMonitor.comboxStatus.boxStatus 钞箱状态
				netStatus:'Net Status'//EwayLocale.monitor.devMonitor.comboxStatus.netStatus 网络状态
			},
			monitorState:'Monitor Status',//EwayLocale.monitor.devMonitor.monitorState 监控状态
			showWay:'Show way',//EwayLocale.monitor.devMonitor.showWay 展示方式
			comboxShowWay:{
				summaryPattern:'Overview',//EwayLocale.monitor.devMonitor.comboxShowWay.summaryPattern
				matrixPattern:'Matrix',//EwayLocale.monitor.devMonitor.comboxShowWay.matrixPattern 矩形方式
				maxIconPattern:'Large Matrix',//EwayLocale.monitor.devMonitor.comboxShowWay.maxIconPattern 超大图标
				listPattern:'List',//EwayLocale.monitor.devMonitor.comboxShowWay.listPattern 列表方式
				boxPattern:'Cashbox'//EwayLocale.monitor.devMonitor.comboxShowWay.boxPattern 钞箱方式
			},
			numberfield:'Number of monitor machine',//EwayLocale.monitor.devMonitor.numberfield 监控台数
			noData:'No data',//EwayLocale.monitor.devMonitor.noData 无记录
			retainCardCount:'Retain card count',//EwayLocale.monitor.devMonitor.retainCardCount 当前吞卡数量
			cash:{
				boxInitCount:'Initial amount in cashbox',//EwayLocale.monitor.devMonitor.cash.boxInitCount 钞箱初始金额
				boxCurrentCount:'Current amount in cashbox',//EwayLocale.monitor.devMonitor.cash.boxCurrentCount 钞箱当前金额
				cashboxLimit:'Value of cash limit alarm',//EwayLocale.monitor.devMonitor.cash.cashboxLimit 钞箱报警金额阈值
				initAmount:'Total initial amount',//EwayLocale.monitor.devMonitor.cash.initAmount 加钞总金额
				amount:'Amount left',//EwayLocale.monitor.devMonitor.cash.amount
				dispenseAmount:'Amount paid',//EwayLocale.monitor.devMonitor.cash.dispenseAmount 出钞总金额
				rejectAmount:'Amount invalid',//EwayLocale.monitor.devMonitor.cash.rejectAmount 废钞金额
				retractCount:'Time of cash retrieve',//EwayLocale.monitor.devMonitor.cash.retractCount 钞票回收次数
				minAmount:'Amount mini draw',//EwayLocale.monitor.devMonitor.cash.minAmount 最小取款金额
				boxId:'Cashbox ID',//EwayLocale.monitor.devMonitor.cash.boxId 钞箱标识
				type:'Cashbox type',//EwayLocale.monitor.devMonitor.cash.type 钞箱类型
				initialCount:'Initial count',//EwayLocale.monitor.devMonitor.cash.initialCount 初始张数
				cashInCount:'Deposit count',//EwayLocale.monitor.devMonitor.cash.cashInCount 存款张数
				currentCount:'Count',//EwayLocale.monitor.devMonitor.cash.currentCount 当前计数
				noteValue:'Cashbox denomination',//EwayLocale.monitor.devMonitor.cash.noteValue 钞箱面值
				currency:'Cashbox currency',//EwayLocale.monitor.devMonitor.cash.currency 钞箱币种
				boxDetail:'Cashbox detail',//EwayLocale.monitor.devMonitor.cash.boxDetail 钞箱详情
				cimFull:'Cashbox full',//EwayLocale.monitor.devMonitor.cash.cimFull 存款钞满
				cdmEmpty:'Draw cash empty',//EwayLocale.monitor.devMonitor.cash.cdmEmpty 取款钞空
				cdmLow:'Draw cash low',//EwayLocale.monitor.devMonitor.cash.cdmLow 取款钞少
				low:'Cash empty',//EwayLocale.monitor.devMonitor.cash.low 钞少
				empty:'Cash empty',//EwayLocale.monitor.devMonitor.cash.empty 钞空
				cimAFull:'Deposit almost full',//EwayLocale.monitor.devMonitor.cash.cimAFull 存款钞将满
				cashFault:'Cashbox error',//EwayLocale.monitor.devMonitor.cash.cashFault 钞箱故障
				cashUnknow:'Cashbox unknown'//EwayLocale.monitor.devMonitor.cash.cashUnknow 钞箱未知

			},
			modStateGraphic:'Module status graphic',//EwayLocale.monitor.devMonitor.modStateGraphic 模块状态图示
			modGraphic:'Module graphic',//EwayLocale.monitor.devMonitor.modGraphic
			registerStatus:'Register status',//EwayLocale.monitor.devMonitor.registerStatus 注册状态
			devModStatus:'Status of module',//EwayLocale.monitor.devMonitor.devModStatus 设备模块状态
			mod:{
				idc:'CardReader',//EwayLocale.monitor.devMonitor.mod.idc 读卡器
				jpr:'Log printer',//EwayLocale.monitor.devMonitor.mod.jpr 日志打印机
				cdm:'Draw module',//EwayLocale.monitor.devMonitor.mod.cdm 取款模块
				cim:'Deposit module',//EwayLocale.monitor.devMonitor.mod.cim 存款模块
				siu:'Sensor',//EwayLocale.monitor.devMonitor.mod.siu 传感器
				rpr:'Voucher printer',//EwayLocale.monitor.devMonitor.mod.rpr 凭条打印机
				pin:'Pin keyboard',//EwayLocale.monitor.devMonitor.mod.pin 密码键盘
				ttu:'Text terminal',//EwayLocale.monitor.devMonitor.mod.ttu 文本终端
				isc:'I.D.card scanner',//EwayLocale.monitor.devMonitor.mod.isc 身份证扫描仪
				icc:'Issue card module',//EwayLocale.monitor.devMonitor.mod.icc 发卡器
				fgp:'Fingerprints',//EwayLocale.monitor.devMonitor.mod.fgp 指纹仪
				healthy:'Healthy'//EwayLocale.monitor.devMonitor.mod.healthy 模块正常

			},
			remote:{
				control:'Remote control',//EwayLocale.monitor.devMonitor.remote.control 远程控制
				screen:'Remote screenshot',//EwayLocale.monitor.devMonitor.remote.screen 远程抓拍
				log:'Get logs',//EwayLocale.monitor.devMonitor.remote.log 提取电子日志
				net:'Check Network',//EwayLocale.monitor.devMonitor.remote.net 查看网络连接
				softwareList:'Get software list',//EwayLocale.monitor.devMonitor.remote.softwareList 获取软件列表
				powerOff:'PowerOff',//EwayLocale.monitor.devMonitor.remote.powerOff 关机
				closeWays:'Choose way of poweroff',//EwayLocale.monitor.devMonitor.remote.closeWays 请选择关机方式
				restart:'Reboot',//EwayLocale.monitor.devMonitor.remote.restart 重启
				restartWay:'Choose way of reboot',//EwayLocale.monitor.devMonitor.remote.restartWay 请选择重启方式
				logicOpen:'Open service',//EwayLocale.monitor.devMonitor.remote.logicOpen 开启服务
				logicClose:'Pause service',//EwayLocale.monitor.devMonitor.remote.logicClose 暂停服务
				remoteBrowser:'Browse',//EwayLocale.monitor.devMonitor.remote.remoteBrowser 远程浏览
				processList:'Check process',//EwayLocale.monitor.devMonitor.remote.processList 查看进程信息
				screenCamera:'Screen recording',//EwayLocale.monitor.devMonitor.remote.screenCamera 屏幕录制
				reset:'Force reset',//EwayLocale.monitor.devMonitor.remote.reset 强制复位
				remoteLook:'Check version application',//EwayLocale.monitor.devMonitor.remote.remoteLook 查看应用版本
				remoteCheckATM:'Physical of ATM',//EwayLocale.monitor.devMonitor.remote.remoteCheckATM ATM体检
				halfSer:'Half service',//EwayLocale.monitor.devMonitor.remote.halfSer 半功能
				healthy:'Healthy',//EwayLocale.monitor.devMonitor.remote.healthy 正常服务
				staff:'Maintenance',//EwayLocale.monitor.devMonitor.remote.staff 维护

				pFault:'ATMP error',//EwayLocale.monitor.devMonitor.remote.pFault 交易前置故障

				stop:'Stop',//EwayLocale.monitor.devMonitor.remote.stop 报停
				manualStop:'Stop manual',////EwayLocale.monitor.devMonitor.remote.manualStop 人工报停
				stopFault:'Pause serivce-module error',//EwayLocale.monitor.devMonitor.remote.stopFault 暂停服务
				stopCash:'Pause serivce-no cash',//EwayLocale.monitor.devMonitor.remote.stopCash 暂停服务-未加钞
				pauseSer:'Pause serivce',//EwayLocale.monitor.devMonitor.remote.pauseSer 暂停服务
				pauseCash:'Pause cash',//EwayLocale.monitor.devMonitor.remote.pauseCash   ----中文不明，我猜的----
				pauseSerUnknow:'Pause serivce-unkown',//EwayLocale.monitor.devMonitor.remote.pauseSerUnknow 未知原因暂停服务
				manaAndstaff:'Machine manager'//EwayLocale.monitor.devMonitor.remote.manaAndstaff 管机员
			},
			atmGroup:'Group',//EwayLocale.monitor.devMonitor.atmGroup 分组
			atmGroupTip:'GroupDetail',//EwayLocale.monitor.devMonitor.atmGroupTip
			solution:'Solution suggest',//EwayLocale.monitor.devMonitor.solution 建议解决方案
			faultDescription:'Module error description',//EwayLocale.monitor.devMonitor.faultDescription 模块故障描述
			fastChoose:'Fast choose',//EwayLocale.monitor.devMonitor.fastChoose 快捷选择
			init:'Initialization',//EwayLocale.monitor.devMonitor.init 初始化
			accTrans:'Transaction',//EwayLocale.monitor.devMonitor.accTrans 客户交易
			factureStaff:'Manufacturer model',//EwayLocale.monitor.devMonitor.factureStaff 厂商模式维护
			netHealthy:'Net healthy',//EwayLocale.monitor.devMonitor.netHealthy 网络正常
			netUnStable:'Net unstable',//EwayLocale.monitor.devMonitor.netUnStable 网络不稳定
			netFatal:'Net error',//EwayLocale.monitor.devMonitor.netFatal 网络故障
			filterManager: {
				title: 'FilterManager',//EwayLocale.monitor.devMonitor.filterManager.title
				add: 'CreateFilter',//EwayLocale.monitor.devMonitor.filterManager.add
				update: 'UpdateFilter',//EwayLocale.monitor.devMonitor.filterManager.update
				filterForm: {
					filterName: 'FilterName'//EwayLocale.monitor.devMonitor.filterManager.filterForm.filterName
				}
			}
		},
		business:{
			transaction:{
				card:'Card',//EwayLocale.monitor.business.transaction.card 交易卡号
				dateTime:'Time',//EwayLocale.monitor.business.transaction.dateTime 交易时间
				transCode:'Type',//EwayLocale.monitor.business.transaction.transCode 交易类型
				amt:'Amt',//EwayLocale.monitor.business.transaction.amt 交易金额
				currency:'currency',//EwayLocale.monitor.business.transaction.currency 交易币种
				transId:'Serialno',//EwayLocale.monitor.business.transaction.transId 交易流水号
				amtfield:'Amount scope',//EwayLocale.monitor.business.transaction.amtfield 金额范围
				toNum:'to',//EwayLocale.monitor.business.transaction.toNum 至
				transContainer:'Time scope',//EwayLocale.monitor.business.transaction.transContainer 交易时间段
				debitAccountOrCard:'Debit/Card',//EwayLocale.monitor.business.transaction.debitAccountOrCard 客户账号或者卡号
				creditAccountOrCard:'Credit/Card',//EwayLocale.monitor.business.transaction.creditAccountOrCard 对方账号或者卡号
				debitAccount:'Debit',//EwayLocale.monitor.business.transaction.debitAccount 客户帐号
				creditAccount:'Credit',//EwayLocale.monitor.business.transaction.creditAccount 对方账号
				localRet:'Local code',//EwayLocale.monitor.business.transaction.localRet ATMC本地代码
				hostRet:'Host code',//EwayLocale.monitor.business.transaction.hostRet 主机返回码
				userName:'User name',//EwayLocale.monitor.business.transaction.userName 用户姓名
				historyTransaction:{
					title:'Query histroy transaction '//EwayLocale.monitor.business.transaction.historyTransaction.title //历史交易查询
				},
				transactionMonitor:{
					title:'Transaction monitoring',//EwayLocale.monitor.business.transaction.transactionMonitor.title 实时交易监控
					begin:'Start',//EwayLocale.monitor.business.transaction.transactionMonitor.begin 开始监控
					stop:'Stop',//EwayLocale.monitor.business.transaction.transactionMonitor.stop 停止监控
					clear:'Clear screen'//EwayLocale.monitor.business.transaction.transactionMonitor.clear清屏
					}
			},
			blackList:{
				title:'BlackCard',//EwayLocale.monitor.business.blackList.title 黑名单卡管理
				black:'BlackCard',//EwayLocale.monitor.business.blackList.black 黑名单卡
				addBlack:'Add',//EwayLocale.monitor.business.blackList.addBlack 添加黑名单卡
				cardBank:'Bank',//EwayLocale.monitor.business.blackList.cardBank 所属银行
				importData:'Import',//EwayLocale.monitor.business.blackList.importData 批量导入
				addDate:'Add date',//EwayLocale.monitor.business.blackList.addDate 添加日期
				importTitle:'Bacth import',//EwayLocale.monitor.business.blackList.importTitle 批量导入黑名单卡
				importFile:'Import file',//EwayLocale.monitor.business.blackList.importFile 导入文件
				chooseFileRegex:'Please choose file to import,only .xls or .xlsx file is supported',//EwayLocale.monitor.business.blackList.chooseFileRegex 请选择导入文件,只支持.xls和.xlsx格式的文件
				fileRegex:'only .xls or .xlsx file is supported',//EwayLocale.monitor.business.blackList.fileRegex 只支持.xls和.xlsx格式的文件
				tempDownload:'Download temple',//EwayLocale.monitor.business.blackList.tempDownload 模版下载
				importNow:'Import',//EwayLocale.monitor.business.blackList.importNow 导入
				updateTitle:'Modify'//EwayLocale.monitor.business.blackList.updateTitle 更改黑名单卡信息
			},
			card:{
				title:'Retained Card',//EwayLocale.monitor.business.card.title 吞卡管理
				addTitle:'Add',//EwayLocale.monitor.business.card.addTitle 增加吞卡信息
				time:'Time',//EwayLocale.monitor.business.card.time 吞卡时间
				reason:'Reason',//EwayLocale.monitor.business.card.reason  吞卡原因
				destroy:'Destroy',//EwayLocale.monitor.business.card.destory 吞卡销毁
				cardHolder:'Bank holder',//EwayLocale.monitor.business.card.cardHolder 发卡行
				cardRegex:'Max length is ',//EwayLocale.monitor.business.card.cardRegex 允许的最大长度为
				beginEndDate:'Begin date can not be later than end date,please choose again',//EwayLocale.monitor.business.card.beginEndDate 吞卡起始日期不能大于吞卡截止日期,请重新选择
				orgBelongs:'Org belongs(Include process org)',//EwayLocale.monitor.business.card.orgBelongs 所属机构 (包含处理机构)
				beginTime:'Begin time ',//EwayLocale.monitor.business.card.beginTime 开始时间
				endTime:'End time',//EwayLocale.monitor.business.card.endTime
				accGetCard:'Customer get card',//EwayLocale.monitor.business.card.accGetCard 客户领卡
				transferCard:'Turnover',//EwayLocale.monitor.business.card.transferCard 卡片移交
				processOrg:'Process org',//EwayLocale.monitor.business.card.processOrg 处理机构
				type:'Type',//EwayLocale.monitor.business.card.type 吞卡类型
				manual:'Add manually',//EwayLocale.monitor.business.card.manual 手动添加
				auto:'Add automatically',//EwayLocale.monitor.business.card.auto 自动添加
				comboxStatus:{
					wait:'Wait',//EwayLocale.monitor.business.card.comboxStatus.wait 待领
					received:'Received',//EwayLocale.monitor.business.card.comboxStatus.received 已领
					destroy:'Destroy',//EwayLocale.monitor.business.card.comboxStatus.destroy 销毁
					bringed:'Bringed'//EwayLocale.monitor.business.card.comboxStatus.bringed 调出
				},
				treatmentPeople:'Processor',//EwayLocale.monitor.business.card.treatmentPeople 处理人员
				treatmentTime:'Time',//EwayLocale.monitor.business.card.treatmentTime 处理时间
				customerName:'Customer name',//EwayLocale.monitor.business.card.customerName 客户姓名
				customerPhone:'Telephone',//EwayLocale.monitor.business.card.customerPhone 客户电话
				customerPapers:'License code',//EwayLocale.monitor.business.card.customerPapers 客户证件号
				processCard:'Process',//EwayLocale.monitor.business.card.processCard 吞卡处理
				destroyCard:'Destroy',//EwayLocale.monitor.business.card.destroyCard 卡片销毁
				exportData:'Export',//EwayLocale.monitor.business.card.exportData 导出
				paperType:'License type',//EwayLocale.monitor.business.card.paperType 证件类型
				paperCode:'License code',//EwayLocale.monitor.business.card.paperCode 证件号
				idCard:'I.D card',//EwayLocale.monitor.business.card.idCard 身份证
				accountPaper:'Register',//EwayLocale.monitor.business.card.accountPaper 户口本
				drivePaper:'Drive License',//EwayLocale.monitor.business.card.drivePaper 驾驶执照
				passport:'Passport',//EwayLocale.monitor.business.card.passport 护照
				officer:'Officer License',//EwayLocale.monitor.business.card.officer 军官证
				soldier:'Soldier License',//EwayLocale.monitor.business.card.soldier 士兵证
				busnessPaper:'Juridicalperson License',//EwayLocale.monitor.business.card.busnessPaper 法人营业证
				busnessCode:'Juridicalperson code',//EwayLocale.monitor.business.card.busnessCode 法人代码证
				taxPaper:'Taxregistration License',//EwayLocale.monitor.business.card.taxPaper 税务登记证
				withDev:'With device'//EwayLocale.monitor.business.card.withDev 按设备
			},


			cashInit:{
				titile:'Load Cash',//EwayLocale.monitor.business.cashInit.titile 加钞信息查询
				uuId:'CashID',//EwayLocale.monitor.business.cashInit.uuId 加钞ID
				date:'Date',//EwayLocale.monitor.business.cashInit.date 加钞日期
				amt:'Amount',//EwayLocale.monitor.business.cashInit.amt 金额
				info:'Information',//EwayLocale.monitor.business.cashInit.info 加钞详细信息
				boxId:'CashboxID',//EwayLocale.monitor.business.cashInit.boxId 钞箱ID
				boxCurrency:'Currency',//EwayLocale.monitor.business.cashInit.boxCurrency 币种
				boxInitAmt:'Amount init',//EwayLocale.monitor.business.cashInit.boxInitAmt 初始金额
				lastAmt:'Amount left'//EwayLocale.monitor.business.cashInit.lastAmt 剩余金额
			},
			settlement:{
				title:'Settlement',//EwayLocale.monitor.business.settlement.title 清机信息查询
				deTitle:'Information',//EwayLocale.monitor.business.settlement.deTitle 清机详细信息
				settleId:'SettleID',//EwayLocale.monitor.business.settlement.settleId
				uuId:'CycleID',//EwayLocale.monitor.business.settlement.uuId 周期ID
				endAmt:'Endbox amount',//EwayLocale.monitor.business.settlement.endAmt 尾箱余额
				endDate:'Date settlement',//EwayLocale.monitor.business.settlement.endDate 结帐日期
				cimNum:'Deposit count',//EwayLocale.monitor.business.settlement.cimNum 存款笔数
				cdmNum:'Draw count',//EwayLocale.monitor.business.settlement.cdmNum 取款笔数
				totalNum:'Toatl',//EwayLocale.monitor.business.settlement.totalNum
				leftDate:'Date settlement',//EwayLocale.monitor.business.settlement.leftDate 结帐日期
				cimAmt:'Deposit amount',//EwayLocale.monitor.business.settlement.cimAmt 存款金额
				cdmAmt:'Draw amount',//EwayLocale.monitor.business.settlement.cdmAmt 取款金额
				tranAmt:'Total amount'//EwayLocale.monitor.business.settlement.tranAmt 交易总金额
			}
		/*}*/
		}
	},

	//**********************************************************/
	machine:{
		atmBrand : {
			title:'Device Brand',//品牌管理
			name: 'Name', //品牌名称
			country:'Country',//生产商国家或地区
			hotline1:'Hotline',//生产商热线1
			hotline2:'Hotline2',//生产商热线2
			address:'Producer address',//生产商地址
			status:'Producer hotline',//生产商状态
			comboxStatus:{
				provider:'Device supply',//设备供应
				maintance:'Device use'//设备服役
			}
		},
		atmCatalog:{
			title:'Device Catalog',//EwayLocale.machine.title ATM分类
			name:'Name',//EwayLocale.machine.atmCatalog.name 分类名称
			note:'Description',//备注
			addTitle:'Add catalog',//增加ATM分类信息
			updateTitle:'Update Catalog', //更改ATM型号信息
			number:'Code',//EwayLocale.machine.atmCatalog.number 编号
		},
		atmGroup : {
			terminalId:'Terminal ID', //设备号
			ip: 'IP',//设备IP地址
			orgName:'Org belongs',//
			devTypeName:'Device Type',//设备型号
			devVendorName:'Device Brand',//设备品牌
			devCatalogName:'Device Catalog',//设备类型
			devGroupName: 'Device Group',//设备分组
			status:'Device Status',//设备状态
			comboxStatus:{
				dredge:'Dredge',//开通
				open:'Open',//启用
				close:'Close'//close
			},
			awayFlag:'AwayFlag',//离行标志
			comboxAwayFlag:{
				inBank:'Inside bank',//在行自助服务区
				outBank:'Outside bank',//离行自助银行
				clickBank:'Alone out-bank self-service'//单机离行自助服务点
			},
			devServiceName:'Manufacturer',//设备维护商
			cashboxLimit:'Alarm cashbox',//钞箱报警金额
			installDate:'Install date',//安装日期
			address:'Address',//地址
			gourpDev:'Group<-->Device',//分组<-->设备
			addTitle: 'Add info of machine-group ',//增加设备组信息
			groupName:'Group name',//组名
			note:'Remark',//备注
			updateTitle:'Update info of machine-group '//更改设备组信息
		},
		atmModule:{
			moduleName:'Module name',//模块名称
			note:'remark',//备注
			atmModules:'ATM module'	//ATM模块
		},
		atmMove:{
			title:'Move manager',//移机管理
			moveDev:'Move machine',//移机
			moveDevRec:'Move machine and generate the move record',//移动设备并产生移机记录
			moveRecordInfo:'Move machine record',//移机记录信息
			waitMove:'Wait for move machine',//待移动的机器
			terminalId:'Terminal Id',//设备号
			address:'Source address',//源地址
			orgName:'Source org',//源机构
			targetAddress:'Target address',//目标地址
			targetOrganization:'Target organization',//目标机构
			targetPerson:'目标机构负责人',//目标机构负责人
			responsibility:'负责人',//负责人
			destPerson:'源机构负责人',//源机构负责人
			date:'date',//日期
			recoverDate:'Recover date',//恢复时间
			notice:'remark',//备注
			sAddress:'Address',//所属地址
			sOrgName:'Organization',//所属机构
			to:'to'//至
		},
		atmRuntimeInfo:{
			exportName:'Export', //导出
			exportDateRangeText:'Start time can not be later than end time',//开始时间不能大于结束时间
			terminalId:'Terminal Id',//终端号
			terminalIp:'IP',//终端IP
			startDate:'Begin time',//开始时间
			endDate:'End time',//结束时间
			exportLast30: 'Export last 30 days info',//导出最后30天汇总信息
			terminalId:'terminalId',//编号
			netIp:'Ip',//网络地址
			msgCollect:'Collect info of customservice'//客服信息采集
		},
		atmType:{
			title:'Device type',//设备型号
			atmName:'Device Type',//ATM型号
			name:'Name',//设备型号
			devVendorName:'Brand', //所属品牌
			devCatalogName:'Catalog',//所属类型
			devTerminalName:'Type',//所属型号
			no:'Code',//编号
			cashtype:'Cash flag',//非现金标志
			iscash:'Cash',//现金
			nocash:'Not cash'//非现金
		},
		device:{
			title:'Deivce Info',//设备信息管理
			devDetailInfo:'Module information',//设备模块详细信息
		    IDC:'Card reader(IDC)',
			JPR:'Log printer(JPR)',
			CDM:'Draw module(CDM)',
			SIU:'Sensor module(SIU)',
			CIM:'Deposit module(CIM)',
			TTU:'Text terminal(TTU)',
			RPR:'Voucher printer(RPR)',
			PIN:'Pin keyboard(PIN)',
			CDMInfo:'Draw module（CDM）property',
			hasStack:'是否具有暂存器',
			hasShutter:'Shutter door exist',//是否具有shutter门
			canRetract:'Ability of recovery', //是否具有回收能力
			canDetectCashTaken:'Check if cash taked',//是否探测钞币被拿走
			canTestPhysicalUnits:'Test physical unit',//是否能测试物理单元
			maxDispensBills:'Get max count of gug cash in each trans',//获取单笔最大挖钞张数
			logicalUnits:'Count of logic cashbox',//逻辑钞箱个数
			physicalUnits:'Count of physical cashbox',//物理钞箱个数
			currency:'Currency supported count',//支持的币种类别总个数
			currencies:'Currency category supported ',//支持的币种类别
			exponents:'index',//指数
			
			CIMInfo:'Deposit module (CIM)property',//存款模块
			canEscrow:'是否具有暂存器X',

			shutterControlSupported:'Support shutter door',//是否支持控制shutter门
			maxAcceptItems:'Max count of inspect cash in each trans',//单笔最大验钞张数
			canDetectCashInserted:'Check if cash bringed',//是否能探测钞票放入
			canDetectCashTaken:'Check if cash taked',//是否能探测钞票被取走
			retractAreas:'Recovery position',//回收位置


			IDCInfo:'Card reader(IDC) property',//读卡器模块(IDC)属性信息
			variant:'Card reader type',//读卡器类型
			canEjectCard:'Ability of quit card',//是否具有退卡能力
			trackJisiiRead:'Ability of read TrackJisii',//是否具有TrackJisii读能力
			track1Read:'Ability of read first track',//是否具有读一磁道数据能力
			track2Read:'Ability of read second track',//是否具有读二磁道数据能力
			track3Read:'Ability of read third track',//是否具有读二磁道数据能力
			canCapture:'Ability of retain card',//是否具有吞卡能力
			binCapacity:'Max count of retain card',//最大吞卡张数
			security:'Safety support',//是否具有安全支持
			trackJisiiWrite:'Ability of write TrackJisii',//是否具有TrackJisii写能力
			track1Write:'Ability of write first track',//是否具有写一磁道数据能力
			track2Write:'Ability of write first track',//是否具有写二磁道数据能力
			track3Write:'Ability of write first track',//是否具有写三磁道数据能力
			

			JPRInfo:'Log printer(JPR) property',
			canEject:'Ability of quit paper',//是否具有退纸能力
			canCapture:'Ability of recovery',//是否具有回收能力
			canStack:'是否具有暂存能力',//是否具有暂存能力

			PINInfo:'Pin keyboard(PIN) property',
			canEBC:'EBC',//能否EBC
			canCBC:'CBC',//能否CBC
			canMAC:'MAC',//能否MAC
			canRSA:'RSA',//能否RSA
			canVerifyVISA:'Inspect VISA',//能否验证VISA
			canVerifyDES:'Inspect DES',//能否验证DES
			functionKeys:'Function support',//功能键支持
			canTripleEBC:'Multi EBC support',
			canTripleCBC:'Multi CBC support',
			canTripleMAC:'Multi MAC support',
			canTripleCFB:'Multi CFB support',
			canVerifyECB:'Inspect ECB',
			canDESOffset:'DeS shifting',

			RPRInfo:'Voucher printer(RPR) property',
			canEject:'Ability of quit paper',//是否具有退纸能力
			canCapture:'Ability of recovery',//是否具有回收能力

			canStack:'是否具有暂存能力',
			maxRetract:'Max count of recovery paper',//最大回收张数

			SIUInfo:'SIU property',//SIU能力属性信息
			operatorSwitchSupported:'Operator swicth support',//是否支持操作员开关
			cabinetSupported:'Ability of induce  behindDoor open support',//是否支持后盖门打开传感能力
			safeSupported:'Ability of induce safeDoor open support',//是否支持安全门打开传感能力
			indicatorSupported:'Ability of induce closeto support',//是否支持靠近传感能力
			guidelightIdcSupported:'Ability of card insert light support',//是否支持插卡指示灯能力
			guidelightCdmSupported:'Ability of draw light  support',//是否支持取款指示灯能力
			guidelightReceiptSupported:'Ability of voucher print light support',//是否支持凭条打印指示灯能力
			guidelightCimSupported:'Ability of deposit light support',//是否支持存款指示灯能力


			TTUInfo:'Text terminal (TTU) property',
			alphanumericKeysPresent:'Ability of character/number input support',//是否支持字母数字键输入
			numericKeysPresent:'Ability of number input support',//是否支持数字键输入
			displayLightPresent:'Ability of number adjust screen-brightness support',//是否支持屏幕亮度调节
			cursorSupported:'Mouse support',//支持鼠标
			resolutionX:'Resolution of cross axle',//横轴分辨率
			hexadecimalKeysPresent:'Hexadecimal input support',//是否支持十六进制键输入
			keyboardLockPresent:'Lock keyboard support',//是否支持键盘锁定
			formsSupported:'Table support',//是否支持表格
			resolutionY:'Resolution of vertical axle',//纵轴分辨率

			comStatus:'Manufacturer',//厂商状态信息
			hwCode:'Fault code',//厂商故障码
			CDMStatus:'Draw module(CDM) status',//取款模块(CDM)状态信息
			cashUnits:'Cashbox',//钞箱状态
			safeDoor:'SafeDoor',//安全门状态
			intermediateStacker:'暂存器状态',
			outBox:'Cashbox draw',//取款钞箱
			pcuId:'Relationship between physical and logic cashbox',//物理逻辑钞箱对应关系
			cuId:'Logic cashboxID',//逻辑钞箱ID
			cuCurrency:'Logic cashbox currency',//逻辑钞箱币种
			cuCurrentCount:'Count of logic cashbox currently',//逻辑钞箱当前张数
			cuInitialCount:'Count of logic cashbox initially',//逻辑钞箱初始张数
			cuRejectCount:'Count of logic cashbox rejected',//逻辑钞箱reject张数
			cuNoteValue:'Denomination of logic cashbox',//逻辑钞箱面值
			cuBinStatus:'Logic cashbox status',//逻辑钞箱状态
			puId:'Physical cashbox ID',//物理钞箱ID
			puPosName:'Name of physical cashbox',//物理钞箱位置名称
			puBinStatus:'Physical cashbox status',//物理钞箱状态
			puCurrentCount:'Count of physical cashbox currently',//物理钞箱当前张数
			puInitialCount:'Count of physical cashbox initially',//物理钞箱初始张数
			puRejectCount:'Count of physical cashbox rejected',//物理钞箱Reject张数
			cuBinType:'Logic cashbox status',//逻辑钞箱类型

			CIMStatus:'Deposit module(CIM) status',
			baffle:'Baffle',//挡板状态
			inOutPositionStatus:'Transfer status',//传输状态
			inBox:'Deposit cashbox',//存款钞箱
			puCashInCount:'Count of physical cashbox in',//物理钞箱入钞张数
			pcuId:'Relationship between physical and logic cashbox',//物理钞箱与逻辑钞箱对应关系
			cuType:'Logic cashbox type',//逻辑钞箱类型
			cuBinStatus:'Logic cashbox status',//逻辑钞箱状态
			cuCurrentCount:'Count of logic cashbox',//逻辑钞箱当前张数
			cuCurrency:'Currency of logic cashbox',//逻辑钞箱币种
			cuNoteValue:'Denomination of logic cashbox',//逻辑钞箱面值

			IDCStatus:'Card reader (IDC) status',
			media:'Media',//媒体状态
			retainBin:'Recovery box status',//回收盒状态
			cards:'Recovery box count',//回收盒数量

			JRPStatus:'Log printer (JPR) status',
			supplyLevel:'Log printer status',
			ink:'Ink',//墨水
			toner:'Ribbon',//色带

			PINStatus:'Pin keyboard(PIN) status',

			RPRStatus:'Voucher printer(RPR)status',//凭条打印机模块(RPR)状态信息
			bin:'Recovery unit status',//回收单元状态

			SIUStatus:'SIU status',//SIU能力状态信息
			vandalShield:'Shield',
			operatorSwitch:'Operate butoon status',
			ambientLight:'Environment light',//环境灯状态
			cabinet:'Box door',//箱门状态
			safe:'Safe door',//安全门状态
			idcGuidelight:'Guide card-insert light',//插卡导引灯状态
			cdmGuidelight:'Guide draw light',//取钞引导指示灯状态
			receiptGuidelight:'Guide voucher light',//凭条导引灯状态
			cimGuidelight:'Guide CIM light',//CIM导引灯状态

			TTUStatus:'Text terminal(TTU) status',//文本终端单元(TTU)状态信息

			devPerson:'Deivce person',//设备人员信息
			devModuleMsg:'Device module property',//设备模块属性信息
			devBasicMsg:'Basic info',//设备基本信息
			devTailMsg:'Detail',//设备详细信息
			managePerson:'Manager',//管机员
			maintainPerson:'Maintainer',//维护员
			name:'Name',//姓名
			mobile:'Mobile',//手机
			phone:'Phone',//固定电话
			email:'Email',//邮件地址
			deviceBasicInfo:'Basic info',//设备基本信息
			lineLogo:'AwayFlag',//
			alarmRateRMB:'Alarm cashbox(RMB)',//钞箱报警金额
			operation:'Service mode',//经营方式
			ipAddress:'IP address',//IP地址
			swallowCard:'Count of retain card',//吞卡张数
			alarmRateHKD:'Alarm cashbox(HKD)',//钞箱报警金额(港币)
			adminPhone:'Manager(mobile)',//管理员(手机号)
			maintainPhone: 'Maintainer(mobile)',//维护员(手机号)
			log:'Flag',//钞箱标识
			style: 'type',//钞箱类型
			status: 'Status',//钞箱状态
			initailnumber: 'Init count',//初始张数
			postnumber: 'Deposit count',//存款张数
			currentnumber: 'Current count',//当前钞箱张数
			facevalue: 'Denomination',//钞箱面值
			currency: 'Currency',//钞箱币种
			systemHardwareInfo: 'Soft/hard ware info',//系统软硬件信息
			moduleVersionInfo:'Hardware-module version(actual)',//模块硬件版本信息（实时）
			devModuleStatusInfo: 'Module status(actual)',//设备模块状态（实时）
			devModuleAttributeInfo: 'Module property(actual)',//设备模块属性信息（实时）

			remoteControl: 'Control',//远程控制
			collectJPR:'Logs getting',//提取日志
			remoteScreen:'Screenshot',//远程抓屏
			processCheck:'View process',//进程查看
			remoteExplorer:'Browse',//远程浏览
			netWorkLink:'Connect',//网络连接
			remoteRestart:'Reboot',//远程重启

			progressTip:'Progress tip',//进度提示
			updateProBar:'This is generate with dynamic update',//这是通过动态更新内容形成的进度条
			currentProcess:'Progress currently',//当前进度


			restartApply: 'Restar app',//重启应用
			confirmRestartApply:'Restar app?',//确定要重启应用？
			nowRestartApply:'Restarting',//正在重启应用
			restartApplySuc:'Restarting app successful!',//成功重启该设备应用
			restartApplyFail:'Restarting app failed!',//重启应用失败


			restartDrive:'Restar drivers',//重启硬件驱动
			confirmRestartDrive:'Restar drivers?',//确定要重启硬件驱动？
			nowRestartDrive:'Restarting',//正在重启硬件驱动
			restartDriveSuc:'Restarting drivers successful!',//成功重启该设备硬件驱动
			restartDriveFail:'Restarting drivers failed!',//重启硬件驱动失败！

			restartOS:'Restar os',//重启操作系统
			confirmRestartOS:'Restar os?',//确定要重启操作系统
			nowRestartOS:'Restarting',//正在重启操作系统
			restartOSSuc:'Restarting os successful!',//成功重启该设备操作系统
			restartOSFail:'Restarting os failed!',//重启操作系统失败

			remoteShutdown:'Shutdown',//关机
			shutdownApply:'Shutdown app',//关闭应用
			confirmShutdownApply:'Shutdown app?',//确定要关闭应用？
			nowShutdownApply:'Shutting down',//
			shutdownApplySuc:'Shutdown app successful!',//成功关闭该设备应用
			shutdownApplyFail:'Shutdown app failed!',//关闭该设备应用失败

			shutdownDrive:'Shutdown drivers',//关闭硬件驱动
			confirmShutdownDrive:'Shutdown drivers?',//确定要关闭硬件驱动？
			nowShutdownDrive:'Shutting down',//正在关闭硬件驱动
			shutdownDriveSuc:'Shutdown drivers successful!',//成功关闭硬件驱动
			shutdownDriveFail:'Shutdown drivers failed!',//关闭硬件驱动失败！

			shutdownOS:'Shutdown os',//关闭操作系统
			confirmShutdownOS:'Shutdown os?',//确定要关闭操作系统？
			nowShutdownOS:'Shutting down',//正在关闭操作系统
			shutdownOSSuc:'Shutdown os successful!',//成功关闭该设备操作系统
			shutdownOSFail:'Shutdown os failed!',//关闭操作系统失败！
			getSoftwareList:'Get software installed info',//获取软件安装列表
			forceReset:'Reset force',//强制复位
			openService:'Open service',//开启服务
			pauseService:'Pause service',//暂停服务
			checkStatus:'Test status',//状态检测

			remoteBrowseDisk:'Browse',//远程浏览

			sysHardwareInfo:'Hardware info',//系统硬件信息
			diskMem:'Disk size',//硬盘大小
			biosVersion:'Bios version',//Bios版本
			biosVendor:'Bios producer',//Bios厂商
			biosReleaseDate:'Bios date',//Bios发布日期
			memorySize:'Total memory',//内存总数
			memoryUsed:'Used memory',//已使用内存
			memoryFree:'Free memory',//空闲内存
			memoryPercent:'Use rate memory',//
			cpuItemID:'CPU info',//cpu信息
			cpuFrequency:'CPU(MHz)',//CPU频率(MHz)
			cpuVendor:'CPU producer',//CPU的厂商
			cpuModel:'CPU type',//CPU的类别
			cacheSize:'Count of cache storage',//缓冲存储器数量
			totalCores:'CPU cores',//CPU核数
			userUsePercent:'Rate user used',//用户使用率
			sysUsePercent:'Rate system used',//系统使用率
			idlePercent:'Rate free current',//当前空闲率
			combinedPercent:'Rate total used',//
			diskItemID:'Disk info',//磁盘信息
			diskName:'Pattern name',//磁盘分区名称
			diskFileSys:'File system',//磁盘文件系统
			diskTotalSize:'Total size',//磁盘总大小
			diskFreeSize:'Total free size',//磁盘可用空间大小
			sysSoftInfo:'Software info',//系统软件信息
			OSID:'OS-ID',//操作系统ID
			OSDescription:'OS describe',//OS描述
			OSType:'OS type',//OS类型
			sysPatchLevel:'OS patch level',//系统补丁级别
			chkCashData:'BV-version',//验钞数据版本
			OSVendor:'OS producer',//OS供应商
			OSVendorName:'OS supplier',//OS供应商名
			sysVersion:'OS version',//系统版本号
			devAddress:'Address',//设备地址
			basicInfo:'Basic info',//基本信息
			virtual:'Virtual devCode',//虚拟设备号
			serial:'Serial',//设备序列号
			carrier:'Carrieroperator',//运营商
			moneyOrg:'Cash org',//加钞机构
			costInterest:'Rate of fund cost',//资金成本利率
			atmcSoft:'atmc software',//atmc软件
			spType:'sp type',//厂商sp类型
			column:'Date',//日期信息
			buyDate:'Buy date',//设备购买日期
			installDate:'Install date',//设备安装日期
			startDate:'Start date',//设备启用日期
			stopDate:'Stop date',//设备停用日期
			expireDate:'Guaranteed date',//保修到期日期
			daliyOpen:'Start time everyday',//每日开机时间
			openTimeHour:'Hour',//时
			openTimeMinute:'Minute',//分
			openTimeSecond:'Second',//秒
			daliyClose:'Close time everyday',//每日关机时间
			lastPmDate:'Last check date',//上次巡检日期
			expirePmDate:'Next check date',//巡检到期日期
			costInfo:'Expenses',//费用信息
			price:'Cost',//入账成本(元)

			depreciationLife:'折旧年限(年)',
			decoration:'Decorate charge',//装修费用
			decorationCost:'Decorate charge(year)',//装修摊销年限(年)
			governanceRent:'Property charge(RMB/mon)',//物业租赁费(元/月)
			governanceCost:'Property charge(RMB/mon)',//物业管理费用(元/月)
			netCost:'Net charge(元/月)',//通讯线路费用(元/月)
			powerCost:'Electriccharge(RMB/mon)',
			moneyCost:'Cash charge(Rmb/time)',//加钞维护费用(元/次)
			statusInfo:'Status',//状态信息
			deviceAttention:'follow with ',//设备关注程序
			stress:'Important',//重要
			medium:'Medium',//中等
			ordinary:'Ordinary',//一般
			notCashSignal:'Cash flag',//非现金标志
			cash: 'Cash',//现金
			notCash:'Not cash',//非现金
			installStyle: 'Insatall way',//安装方式
			crossWall: 'CrossWall',//穿墙
			mainRoom: 'MainRoom',//mainRoom
			netType: 'Net type',//网络类型
			wired: 'Wired',//有线
			wireless: 'Wireless',//wireless
			wiredAndWireless: 'Wired flag',//wiredAndWireless
			onBankSignal:'Inbank flag',//在行离行标志
			inBank:'In-bank self-servce area',//在行自助服务区
			outBank:'Out-bank self-servce',//离行自助银行
			clickBank:'Alone out-bank self-service',//单机离行自助服务点
			operation:'Service mode',//经营方式
			operationSelf: 'Self-business',//自营
			cooperation: 'Cooperation',//合作
			epiboly: 'Outsource',//外包
			managePerson:'Manager',//管机员
			maintainPerson:'Maintainer',
			to:'至',//to
			range: 'Range 1-100 years',//范围1－－100年
			roleDescription:'Role description',//角色描述
			roleName:'Role name',//角色名称



			devices:'Device',//设备
			configuration:'Setting',//配置信息
			spVersion:'SP',//SP 版本
			notSupport:'Unsupport',//不支持
			drive:'Driver',//驱动
			firmway: 'Firmware',//固件
			noDevice:'No device',//无设备
			devTypeInfo: 'Device type',//设备型号信息

			devInfo:'Device info',//设备信息
			unable:'Can not',//不可以
			able:'Can',//能

			addDevInfo:'Additional equipment information',//EwayLocale.machine.device.addDevInfo增加设备信息
			effectiveDate:'effective date',//EwayLocale.machine.device.effectiveDate生效日期
			changeDevInfo:'Changing the Device Information',//EwayLocale.machine.device.changeDevInfo更改设备信息
			devManage:'Device Management',//EwayLocale.machine.device.devManage设备管理
			efficientDev:'Device Information in force',//EwayLocale.machine.device.efficientDev已生效设备信息
			unEfficientDev:'Device information not active',//EwayLocale.machine.device.unEfficientDev未生效设备信息
			person:{
				week:'Week',//EwayLocale.machine.device.person.week星期
				Mon:'Monday',//EwayLocale.machine.device.person.Mon星期一
				Tues:'Tuesday',//EwayLocale.machine.device.person.Tues星期二
				Wed:'Wednesday',//EwayLocale.machine.device.person.Wed星期三
				Thur:'Thursday',//EwayLocale.machine.device.person.Thur星期四
				Fri:'Friday',//EwayLocale.machine.device.person.Fri星期五
				Sat:'Saturday',//EwayLocale.machine.device.person.Sat星期六
				Sun:'Sunday',//EwayLocale.machine.device.person.Sun星期日
				openClose:'Power On / Off',//EwayLocale.machine.device.person.openClose开机/关机
				Open:'Power',//EwayLocale.machine.device.person.Open开机
				Close:'Shutdown'//EwayLocale.machine.device.person.Close关机
			},
		},
		param:{
			paramKey:'Parameter',//EwayLocale.machine.param.paramKey 参数
			paramValue:'Value',//EwayLocale.machine.param.paramValue参数值
			classify:'Type',//EwayLocale.machine.param.classify 类型
			paramType:'Type',//EwayLocale.machine.param.paramType 参数类型
			modifyFlag:'Can be Modify?',//EwayLocale.machine.param.ModifyFlag 是否可以修改
			comboxClassify:{
				unableUpdate:'Yes',//不可修改
				ableUpdate:'No'//可以修改
			},
			description:'Description',//EwayLocale.machine.param.description 参数信息描述
			systemCon:'System Setting',//EwayLocale.machine.param.systemCon 系统配置
			updateSystemCon:'Update system Setting'//EwayLocale.machine.param.updateSystemCon 更改系统配置
		},
		quittingNotice:{
			addCloseMsg:'Add Report Stop',//增加报停信息
			updateCloseMsg:'Udpate stop',//
			dateRangeText:'Recover date can not be earlier than stop date,please choose again',//恢复日期不能小于等于停止日期,请重新选择
			click:'Click query to choose device',//请点击查询，选择设备
			stopTime:'Stop time',//停机时间
			openTime:'Recover time',//恢复时间
			currentStatus:'Current status',//当前状态
			closeType:'Type',//停机类型
			responsibilityName:'Personincharge',//停机负责人
			stopReason:'Reason',//停机原因
			address:'Address',//所属地址
			selectDev:'Please select the device which you want to stop',//选择需要报停的设备
			to:'to',//至
			stopType:'Type',//停机类型
			comboxStopType:{

				recess:'Holiday',//放假
				fit:'Decorate',//装修
				power:'Powercut',
				devFailue:'Error never fix',//设备故障未修复
				other:'Other'	//其他
			},
			setTime:'Set time',//
			closeManage:'Stop manager'//报停管理
		}
	},

	//**********************************************************/

	index:{
		indexPage:'Home',//EwayLocale.index.indexPage 首页
		dailyFaultPic:'Faults Trend Chart',//EwayLocale.index.dailyFaultPic 日均故障趋势图
		faultAmount:' CaseFault number: ',//EwayLocale.index.faultAmount
		devStatusDisPic:'Device status Pie',//EwayLocale.index.devStatusDisPic
		normalDev:'Device normal',//EwayLocale.index.normalDev 正常设备
		unknownDev:'Device-unknown',//EwayLocale.index.unknownDev
		exceptionDev:'Device unusual',//EwayLocale.index.exceptionDev 异常设备
		amount:'',//EwayLocale.index.amount 台
		versionDistributePie:'Version Distribution',//EwayLocale.index.versionDistributePie
		retainCardTrendTitle:'Retain Card Trend Chart'//EwayLocale.index.retainCardTrendTitle
	},
	//**********************************************************/
	report:{
		baseReport:{
			date:'Cash date',//EwayLocale.report.baseReport.date 加钞日期
			amt:'Cash amount',//EwayLocale.report.baseReport.amt 加钞金额
			boxId:'Cashbox ID',//EwayLocale.report.baseReport.boxId 钞箱ID
			boxCurrency:'Currency',//EwayLocale.report.baseReport.boxCurrency 币种
			boxInitAmt:'InitAmt',//EwayLocale.report.baseReport.boxInitAmt 初始金额
			lastAmt:'LeftAmt',//EwayLocale.report.baseReport.lastAmt
			cashAddRep:'CashAdd detail report',//EwayLocale.report.baseReport.cashAddRep 加钞情况报表
			boxBalanceRep:'Cashbox left report',//EwayLocale.report.baseReport.boxBalanceRep 钞箱余额报表
			sysConfRep:'Hardware report',//EwayLocale.report.baseReport.sysConfRep 系统硬件配置报表
			devDetailRep:'Device detail report',//EwayLocale.report.baseReport.devDetailRep 设备明细报表
			devBrandRep:'Device brand report',//EwayLocale.report.baseReport.devBrandRep 设备品牌统计报表
			devRunInfoRep:'Device run report',//EwayLocale.report.baseReport.devRunInfoRep 设备运行情况报表
			eatCardRep:'Retain card report',//EwayLocale.report.baseReport.eatCardRep 吞卡统计报表
			eatCardDetailRep:'Retain card detail report',//EwayLocale.report.baseReport.eatCardDetailRep 吞卡明细报表
			clearDate:'Clear date',//EwayLocale.report.baseReport.clearDate 清机日期
			clearTable:'Clear report',//EwayLocale.report.baseReport.clearTable 清机情况报表
			dependDev:'Depend on Dev',//EwayLocale.report.baseReport.dependDev 按设备
			tradeRep:'Transation report',//EwayLocale.report.baseReport.tradeRep 交易统计报表
			tradeResultRep:'Transation result report',//EwayLocale.report.baseReport.tradeResultRep 交易结果统计报表
			tradeDaysCountRep:'Transaction day count report',//EwayLocale.report.baseReport.tradeDaysCountRep
			tradeHoursCountRep:'Transaction hour count report',//EwayLocale.report.baseReport.tradeHoursCountRep
			tradeCount:'Transaction count',//EwayLocale.report.baseReport.tradeCount
		},
		openrate:{
			device:{
				statisticsMethod:'Statistics way',//EwayLocale.report.openrate.device.statisticsMethod 统计方式
				statistics:'Statistics',//EwayLocale.report.openrate.device.statistics 统计
				importStat:'Export',//EwayLocale.report.openrate.device.importStat 导出
				statDate:'Statistics date',//EwayLocale.report.openrate.device.statDate 统计日期
				openTimes:'Time device should run',//EwayLocale.report.openrate.device.openTimes 设备应工作时长
				healthyTimeReal:'Time device run normally',//EwayLocale.report.openrate.device.healthyTimeReal 正常状态时长
				maintainTimeReal:'Time device manage-mode',//EwayLocale.report.openrate.device.maintainTimeReal 管机员维护时长
				unknownTimeReal:'Time device off-line',//EwayLocale.report.openrate.device.unknownTimeReal 离线未知时长
				faultTimeReal:'Time device hardware-error',//EwayLocale.report.openrate.device.faultTimeReal 硬件故障停机时长
				atmpTimeReal:'Time ATMP error',//EwayLocale.report.openrate.device.atmpTimeReal ATMP故障时长
				stopTimeReal:'Time other reason stop-service',//EwayLocale.report.openrate.device.stopTimeReal 其它暂停服务状态时长
				openRate:'OpenRate',//EwayLocale.report.openrate.device.openRate 实际工作开机率
				devOpenRate:'OpenRate',//EwayLocale.report.openrate.device.devOpenRate 设备开机率
				organizationName:'Org',//EwayLocale.report.openrate.device.organizationName 机构
			},
			org:{
				orgOpenRate:'OpenRate-org',//EwayLocale.report.openrate.org.orgOpenRate 机构开机率
			},
			type:{
				terminalId:'Type',//EwayLocale.report.openrate.type.terminalId 型号
				typeOpenRate:'OpenRate of device type',//EwayLocale.report.openrate.type.typeOpenRate 型号开机率
			},
		},
		plan:{
			addPlan:'Add Plan',//EwayLocale.report.plan.addPlan 增加方案
			name:'Name',//EwayLocale.report.plan.name 名称
			startDate:'Start time',//EwayLocale.report.plan.startDate 有效开始时间
			endDate:'End time',//EwayLocale.report.plan.endDate 有效结束时间
			terminalId:'Code',//EwayLocale.report.plan.terminalId 编号
			cashboxLimit:'Alarm cashbox(unit:piece)',//EwayLocale.report.plan.cashboxLimit 钞箱报警金额(单位：张数)
			perToDev:'Person<-->Device',//EwayLocale.report.plan.perToDev 人员<-->设备
			changePlan:'Modify plan',//EwayLocale.report.plan.changePlan 更改方案
			openPlan:'Start up plan',//EwayLocale.report.plan.openPlan 开机方案
		},
		openplan:{
			name:'Programme Name',//EwayLocale.report.openplan.name方案名称
			machineQuantity:'Number of devices',//EwayLocale.report.openplan.machineQuantity设备数量
			state:'Status',//EwayLocale.report.openplan.state状态
			openDate:'Effective boot time',//EwayLocale.report.openplan.openDate有效开机时间
			closeDate:'Effective shutdown time',//EwayLocale.report.openplan.closeDate有效关机时间
			createDateTime:'Created',//EwayLocale.report.openplan.createDateTime创建时间
			date:'Date',//EwayLocale.report.openplan.date日期
			week:'Week',//EwayLocale.report.openplan.week星期
			inportLinkedMachine:'Import related equipment',//EwayLocale.report.openplan.inportLinkedMachine导入关联设备
			selectFile:'Select the file',//EwayLocale.report.openplan.selectFile选择文件
			placeUploadingResource:'Please upload resources',//EwayLocale.report.openplan.placeUploadingResource请上传资源
			fileNotSupport:'Import file format is not supported, according to the template import device information',//EwayLocale.report.openplan.fileNotSupport导入的文件格式不支持,请按模板导入设备信息
			exportExplain:'Import instructions',//EwayLocale.report.openplan.exportExplain导入说明
			thisIsTooLong:'Please add the device continuously import template device number to be issued, up to a one-time import 2000 data (takes about 5 minutes), a minimum import data',//EwayLocale.report.openplan.thisIsTooLong请在设备导入模板中连续添加要下发的设备号,最多一次性导入2000条数据(约耗时5分钟),最少导入1条数据
			thisHardToTranslate:'Click to download introducing device ID template',//EwayLocale.report.openplan.thisHardToTranslate点击下载导入设备号模板
			placeSelect:'---Please select---',//EwayLocale.report.openplan.placeSelect请选择
			planDevice:'Program <--> Device',//EwayLocale.report.openplan.planDevice方案<-->设备
			timeEare:'Enter the time is incorrect, please re-enter！',//EwayLocale.report.openplan.timeEare输入时间段有误，请重新输入
			planOlonOne:'The same program can only set a startup or shutdown',//EwayLocale.report.openplan.planOlonOne同方案只能设置开机或关机的一种
			addSuccess:'Added successfully',//EwayLocale.report.openplan.addSuccess添加成功
			addFail:'Add Failed',//EwayLocale.report.openplan.addFail添加失败
			setTime:'Please set the detailed time',//EwayLocale.report.openplan.setTime请设置详细时间
			thisPlanStop:'(This program is disabled, can not apply!)',//EwayLocale.report.openplan.thisPlanStop(此方案已停用，不可应用！)
			placeRefresh:'Article lift failed. Please refresh view！',//EwayLocale.report.openplan.placeRefresh条解除失败，请刷新后查看
			linking:'Being associated equipment....',//EwayLocale.report.openplan.linking正在关联设备
			testingPlaceWaiting:'Device number is judged to meet the requirements, please wait...',//EwayLocale.report.openplan.testingPlaceWaiting正在判断设备号是否符合要求，请耐心等待
			leastOne:'Importing a device at least once information, please re-select the import file!',//EwayLocale.report.openplan.leastOne最少一次导入1条设备信息，请重新选择导入文件
			notMore:'Up to 2000 the first import device information, please re-select the import file!',//EwayLocale.report.openplan.notMore最多一次导入2000条设备信息，请重新选择导入文件
			checkFile:'Please check the import file',//EwayLocale.report.openplan.checkFile请检查导入文件
			fileNotAllowed:'Documents do not meet requirements！',//EwayLocale.report.openplan.fileNotAllowed文件不符合要求
			tipExportSuccess:'Article data successfully imported',//EwayLocale.report.openplan.tipExportSuccess条数据,成功导入
			tipLookUp:'Bar, click View import details!',//EwayLocale.report.openplan.tipLookUp条,点击查看导入详情!
			tochenkDervice:'Please select the device you want to change',//EwayLocale.report.openplan.tochenkDervice请选择您要更改的设备
			tochenckPeople:'Please select personnel',//EwayLocale.report.openplan.tochenckPeople请选择人员
			tipAddError:'Article Adding failed. Please refresh view',//EwayLocale.report.openplan.tipAddError条添加失败,请刷新后查看
			planIsHaved:'',
			linkSuccess:'Associate success',//EwayLocale.report.openplan.linkSuccess关联成功
			Mon:'Mon',//EwayLocale.report.openplan.Mon一
			Tues:'Tues',//EwayLocale.report.openplan.Tues二
			Wed:'Wed',//EwayLocale.report.openplan.Wed三
			Thur:'Thur',//EwayLocale.report.openplan.Thur四
			Fri:'Fri',//EwayLocale.report.openplan.Fri五
			Sat:'Sat',//EwayLocale.report.openplan.Sat六
			Sun:'Sun',//EwayLocale.report.openplan.Sun日
			useSuccess:'Normal start',//EwayLocale.report.openplan.useSuccess正常启用
			notSuccess:'Not Enabled',//EwayLocale.report.openplan.notSuccess未启用
			lastOneGroup:'Please select at least one in the group',//EwayLocale.report.openplan.lastOneGroup请在组内至少选中一项
			lanDetailWeek:'Week program detailed list',//EwayLocale.report.openplan.lanDetailWeek星期方案详细列表
			planDetailDay:'Date program detailed list',//EwayLocale.report.openplan.planDetailDay日期方案详细列表
			selectPlan:'To select a startup program',//EwayLocale.report.openplan.selectPlan选择开机方案
			weekSelect:'Notify way'//EwayLocale.report.openplan.weekSelect通知方式
		}
	},

	//**********************************************************/
	card:{
		cardNum:'Card num',//EwayLocale.card.cardNum 卡号
		onlyNumber:'Just for numbers,13-19 numbers',//EwayLocale.card.onlyNumber 只能输入数字,13-19位
		cardStatus:'Status',//EwayLocale.card.cardStatus 卡片状态
		eatCardTime:'Time',//EwayLocale.card.eatCardTime 吞卡时间
		IDType:'License type',//EwayLocale.card.IDType 证件类型
		customerName:'CustomerName',//EwayLocale.card.customerName 客户姓名
		customerPapers:'CustomerPapers',//EwayLocale.card.customerPapers 客户证件号
		customerPhone:'CustomerPhone',//EwayLocale.card.customerPhone 客户电话
		endData:'EndData',//EwayLocale.card.endData 吞卡截止日期
		startData:'StartData',//EwayLocale.card.startData 吞卡起始日期
		add:'Add',//EwayLocale.card.add增加
		dell:'Delete'//EwayLocale.card.dell删除
	},

	//**********************************************************/

	cases:{
		confirm:'Confirm',//确认
		cancel:'Cancel',//取消
		concern:'Attention please',//请关注
		SRCBView:'',//上海农商行新监控发送
		nowExportFile:'File Importing',//正在导入文件
		exportFaultInfo:'Import fault code successful',//导入厂商故障信息成功.
		caseFault:{
			faultRelevantInfo:'SMS about fault',//故障相关短信
			faultModule:'Fault module ',
			cardReaderModule:'Card reader',//读卡器模块
			depoistModule:'Deposit module',//存款模块
			drawModule:'Draw module',//取款模块
			rprModule:'Voucher printer',//凭条打印模块
			jprModule:'Log printer',//日志打印模块
			pinModule:'Pin keyboard',//密码键盘模块
			textTerminalUnit:'Text terminal',//文本终端单元
			sensoModule:'Sensor',//传感器模块
			faultClassify: 'Fault catalog',//故障分类
			faultCode : 'Fault code',//故障码
			providerFaultCode: 'Fault code manufacturer',//厂商故障码
			faultStartTime : 'Open time',//故障开始时间
			faultCloseTime : 'Close time',//故障关闭时间
			faultContinueTime : 'Time last',//持续时长
		    faultState : 'Status',//故障状态
		    status:{
		    	open:'Opening',//未关闭
		    	close:'Closed'//已关闭
		    },
		    closeType:{
		    	force : 'closeByForce',
		    	normal : 'closeNormal'
		    },
		    closeByForce : 'click to close fault by hand',
		    faultCloseType : 'fault close type',
		    none : 'none',

		    upgradeTimes: 'Upgrade times',//升级次数
		    message: 'SMS',//短信
		    checkDetails: 'Detail',//查看详情
		    bankPer: 'Bnak contact',//银行联系人
		    serPer: 'SUppliers contact',//供应商联系人
		    createTime: 'Create time',//创建时间
		    informContent: 'Notify content',//通知内容
		    messageContentDetail: 'SMS content',//短信内容详情
		    informWay: 'Notify way',//通知方式
		    mail:'Email',//邮件
		    messageAndMail:'SMS and email',//短信和邮件
		    informMobile: 'Notify mobile',//通知手机号
		    notifyTimes: 'Notify times',//通知次数
		    notifyRepeatTimes: 'Repeat notify times',//重复通知次数
		    sendTimes: 'Send times',//发送次数
		    sendInterval: 'Intervals between each send',//发送时间间隔
		    sendTime: 'Send time',//发送时间
		    faultSearch:'Fault search',//故障查询
		    none : 'Do not send'//不发送

		},
		caseNotify:{
			fault:'Fault',//故障
			faultDetails:'Detail',//故障详情
			faultlastTime: 'Fault last(unit:hour)',//故障持续时长(单位:小时)
			checkFailure:'View failed!',//查看失败！
			innerFault:'System error',//EwayLocale.cases.caseNotify.innerFault 内部错误
			messageCheck:'Query SMS'//短信查询
		},
		faultClassify:{
			faultClassifyName: 'Fault type name',//故障分类名称
			faultresponsorType: 'Fault personliable type', //故障责任人类型
			maintain:'Maintainer',//维护员
			manageAndMaintain:'Manager&Maintainer',//管机员和维护员
			upGradeTimes: 'Max upgrade times',//最高升级次数
			faultInformWay:'Notify way',//故障通知方式
			faultCloseInterval:'Intervals between close(unit:hour)',//故障规定关闭时间间隔（单位:小时）
			faultTypeConfiguration: 'Fault type setting',//故障类型配置
			updateFaultTypeConfiguration: 'Modify fault type',//更改故障类型配置
			number:'Just for numbers ‘0-9’',//由数字‘0-9’,‘.’组成
			informNumber:'Can not be 0,1-5 numbers'//通知次数不能为0,由数字‘0-9’组成,1-5位
		},
		notifyMould:{
			noticeType:'Type',//通知类型
			createNotice:'Create',//创建通知
			upgradeNotice:'Upgrade',//升级通知
			closeNotice:'Close',//关闭通知
			noticeValue: 'Parameters',//通知参数
			messageContentConfiguration:'SMS content',//短信内容配置
			updateMessageContentConfiguration: 'Modify SMS content',//更改短信内容配置
			necessaryOption: 'This option is necessary',//此项为必选项
			faultType:'Fault type',//故障类型
			applyStatus:'App status'//应用状态
		},
		vendorCode:{
			exportProviderInfo: 'Import manufacturer faultCode',//导入厂商故障信息
			provider:'Manufacturer',
			exportFile: 'Import file',//导入文件
			deleteFaultInfo:'Delete manufacturer faultCode',//删除厂商故障信息
			templateLoad:'Example download',//模板下载
			massRemove: 'Batch delete',//批量删除
			providerDescription:'DaultCode describe',//厂商故障描述
			solveProject: 'Solution',//解决方案
			providerFaultInfo:'Manufacturer faultCode'//厂商故障信息管理
		}
	},

	//**********************************************************/
	personal:{
		baseInfo:'Basic info',//EwayLocale.personal.baseInfo 基本信息
		accountNum:'User Code',//EwayLocale.personal.accountNum 账号
		personalInfo:'Profile',//EwayLocale.personal.personalInfo 个人信息
		changePwd:'Udpate password',//EwayLocale.personal.changePwd 修改密码
		nowLogin:'Current Account',//EwayLocale.personal.nowLogin 当前登录账号
		inputOldPwd:'Input initial passwd',//EwayLocale.personal.inputOldPwd 输入原始密码
		inputNewPwd:'Input new passwd',//EwayLocale.personal.inputNewPwd 输入新密码
		inputVali:'Just for 8-20 characters ‘a-z’ or ‘A-Z’、number‘0-9’、minussign‘-’、underline‘_’',//EwayLocale.personal.inputVali 只能输入8到20位字母‘a-z’或‘A-Z’、数字‘0-9’、特殊字符！
		inputAgain:'Input again',//EwayLocale.personal.inputAgain 再次输入新密码
		pwdNotSame:'Passwds not same',//EwayLocale.personal.pwdNotSame 两次密码不一致！
		rememberPwd:'Click confirm to update passwd ,please remember it',//EwayLocale.personal.rememberPwd 单击确定即可修改密码，请牢记新密码！
		pwdSameNoChange:'New password is same with the old one,can not commit',//EwayLocale.personal.pwdSameNoChange 输入的新密码与旧密码相同,不可修改.
		reOperate:'Can not modify passwd,please try again',//EwayLocale.personal.reOperate 无法修改密码,请重新操作.
	},

	//**********************************************************/
	atmLog:{
		dayBackup:'Back-up log result today',//EwayLocale.atmLog.dayBackup 当日备份结果
		whole:'All',//EwayLocale.atmLog.whole 所有
		execute:'Executing',//EwayLocale.atmLog.execute 执行中
		unKnownFail:'Failed with unknown reason',//EwayLocale.atmLog.unKnownFail 未知原因失败
		logDate:'Log date',//EwayLocale.atmLog.logDate 日志日期
		backupResult:'Back-up result',//EwayLocale.atmLog.backupResult 备份结果
		checkFailInfo:'Check detail of back-up failed',//EwayLocale.atmLog.checkFailInfo 查看备份失败详情
		checkSucInfo:'Check detail of back-up successful',//EwayLocale.atmLog.checkSucInfo 查看备份成功详情
		backupSucAmount:'Count of machine back-up successful',//EwayLocale.atmLog.backupSucAmount 备份成功台数
		backupFailAmount:'Count of machine back-up failed',//EwayLocale.atmLog.backupFailAmount 备份失败台数
		backupAllAmount:'Total count',//EwayLocale.atmLog.backupAllAmount 总备份台数
		logBackupSta:'Back-up log total',//EwayLocale.atmLog.logBackupSta 日志备份统计
		dailyBackup:'Back-up task everyday',//EwayLocale.atmLog.dailyBackup 每日备份任务
		lastDoDate:'LastExecute Date',//EwayLocale.atmLog.lastDoDate
		getLog:'GetLog',//EwayLocale.atmLog.getLog
		backupDate:'Back-up date',//EwayLocale.atmLog.backupDate 备份日期
		dayBackupResult:'Back-up result today',//EwayLocale.atmLog.dayBackupResult 当日备份结果
		backupProcess:'Backing-up',//EwayLocale.atmLog.backupProcess 正在备份
		backupSuccess:'Back-up successful',//EwayLocale.atmLog.backupSuccess 备份成功
		backupError:'Back-up error',//EwayLocale.atmLog.backupError 备份错误
		logDevAccount:'Log back-up machine count total',//EwayLocale.atmLog.logDevAccount 日志设备数量累计
		reform:'Reform',//EwayLocale.atmLog.reform 重做
		busLogAnalysis:'Business log analysis',//EwayLocale.atmLog.busLogAnalysis 业务日志分析
		selectAnalysis:'Please choose the log file which you want to analyse, the result will show in Excel file',//EwayLocale.atmLog.selectAnalysis 请选择需要分析的日志文件，分析的结果将以Excel表格文件导出
		selectLog:'Choose log',//EwayLocale.atmLog.selectLog 选择日志
		pleaseDownload:'Please download',//EwayLocale.atmLog.pleaseDownload 请下载
		appLogDownload:'Download app logs',//EwayLocale.atmLog.appLogDownload 应用日志下载
		lastBackupTime:'Last back-up time',//EwayLocale.atmLog.lastBackupTime 最后一次备份时间
		noBegin:'noBegin',//EwayLocale.atmLog.noBegin 未开始
		noLog:'No log',//EwayLocale.atmLog.noLog 无日志
		connectFail:'Connect fail',//EwayLocale.atmLog.connectFail 连接失败
		fileSize:'File size',//EwayLocale.atmLog.fileSize 文件大小
		searchIllegal:'Query option has illegal input,can not export.'//EwayLocale.atmLog.searchIllegal 查询项中存在不合法的输入,不能导出.
	},
	//**********************************************************/
	system:{
		sysRegist:'SystemRegist',//EwayLocale.system.sysRegist 系统注册
		registCode:'Regist code',//EwayLocale.system.registCode 注册码
		startDate:'Start date',//EwayLocale.system.startDate 开始时间
		endDate:'End date',//EwayLocale.system.endDate 到期时间
		registType:'Regist type',//EwayLocale.system.registType 注册类型
		serialNum:'SerialNo',//EwayLocale.system.serialNum 序列号
		getSerialNum:'Getting SerialNo......',//EwayLocale.system.getSerialNum 正在获取序列号......
		checkCode:'Check code',//EwayLocale.system.checkCode 校验码
		tryOut:'Tryout',//EwayLocale.system.tryOut 试用
		noLimit:'Limitless',//EwayLocale.system.noLimit 没有限制
		getSerialNumFail:'Get SerialNo failed',//EwayLocale.system.getSerialNumFail 序列号获取失败
		registSuc:'Regist successful',//EwayLocale.system.registSuc 注册成功
		registFail:'Regist failed',//EwayLocale.system.registFail 注册失败
		appearInnerFalse:'System error',//EwayLocale.system.appearInnerFalse 出现内部错误
		regist:'Regist',//EwayLocale.system.regist 注册
		aboutSystem:'About',//EwayLocale.system.aboutSystem 关于系统
		softwareName:'Name',//EwayLocale.system.softwareName 软件名称
		ATMV:'Self-service machine monitor system(ATMV)',//EwayLocale.system.ATMV 金融自助设备集中监控系统
		softwareVersion:'Software-Version',//EwayLocale.system.softwareVersion 软件版本
		innerVersion:'InnerVersion',//EwayLocale.system.innerVersion 内部版本号
		copyRight:'CopyRight：&copy;YIHUA ',//EwayLocale.system.copyRight 版权信息
		introduction:'introduction:',//EwayLocale.system.introduction 系统简介
		introductionA:'This system is used for monitor ATM-basic-info,',//EwayLocale.system.introductionA 本系统是监控系统的基础功能有ATM信息管理
		introductionB:'自动化版本分发管理、 ATM设备监控等功能。通过这些功能，各大银行可以集中管理ATM设备信息  监视 远程的ATM，对远程ATM机器上的软件升',//EwayLocale.system.introductionB
		introductionC:'级和软件维护，方便了各大银 行对自助设备进行高效的管理和维护。',//EwayLocale.system.introductionC

		guideUsers:'This handbook help user to use this system',//EwayLocale.system.guideUsers 本手册指导用户操作本系统，更快的掌握系统的各项功能。
		systemHelp:'Help',//EwayLocale.system.systemHelp 系统帮助
		helpName:'Name',//EwayLocale.system.helpName 名称
		helpExpain:'Instructions',//EwayLocale.system.helpExpain 说明
		helpDownload:'Download',//EwayLocale.system.helpDownload 下载
		clickDownload:'Click to download',//EwayLocale.system.clickDownload 单击此处即可下载该文档
	},
	thread:{

	}
});
