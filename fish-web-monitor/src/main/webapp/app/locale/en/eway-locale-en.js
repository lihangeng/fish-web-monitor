Ext.apply(EwayLocale,{
	addSuccess : 'Add successfully.',//EwayLocale.addSuccess 增加成功.
	updateSuccess : 'Update successfully.',//EwayLocale.updateSuccess 更改成功.
	deleteSuccess : 'Delete successfully.',//EwayLocale.deleteSuccess 删除成功.
	choiceUpdateMsg :'Please choose the record which you want to update.',//EwayLocale.choiceUpdateMsg 请选择您要更改的记录.
	choiceDeleteMsg :'Please choose the record which you want to delete.',//EwayLocale.choiceDeleteMsg 请选择您要删除的记录.

	myTable:'Workbench',//EwayLocale.myTable 我的工作台
	ATMV:'ATM View',//EwayLocale.ATMV 自助设备监控系统(ATMV)
	welcome:'Welcome,',//EwayLocale.welcome 欢迎你,
	personalConf:'Personal Settings',//EwayLocale.personalConf 个人设置
	systemHelp:'Help',///EwayLocale.systemHelp 系统帮助
	exitSystem:'Log Out',//EwayLocale.exitSystem 退出系统
	msg:{
		perviewFailForText:'Preview failed:the charater and rolling advertisement is supported to preview',//EwayLocale.msg.perviewFailForText 预览失败:不支持文字滚动广告和公告的预览.
		perviewFailNoResource:'Preview failed:no resource found in this advertisement.',//EwayLocale.msg.perviewFailNoResource 预览失败:此广告没有配置广告资源.
		choseResToPerview:'Please choose the advertisement which you want to preview.',//EwayLocale.msg.choseResToPerview 请选择您要预览的广告.
		noAdvertResAtTheResolution:'No allocation of advertising resources under Resolution.',//EwayLocale.msg.noAdvertResAtTheResolution
		chooseAdvert:'Please choose an advertisement.',//EwayLocale.msg.chooseAdvert 请选择一条广告.
		chooseOneDevice:'Please choose a machine.',//EwayLocale.msg.chooseOneDevice 请选择一台设备.
		downLoadedAdvertCantDelete:'Delete failed:can not delete the advertisement which status is "issued" or "wait for issue".',//EwayLocale.msg.downLoadedAdvertCantDelete 删除失败:不能删除"已下发"和"等待下发"状态的广告.
		chooseAdvertToDelete:'Please choose the advertisement which you want to delete.',//EwayLocale.msg.chooseAdvertToDelete 请选择您要删除的广告.
		chooseAdvertToDownload:'Please choose the advertisement which you want to issue.',//EwayLocale.msg.chooseAdvertToDownload 请选择您要下发的广告.
		generalVersionFailForDownloaded:'Generate  the version-file failed:the status "issued" advertisement can not generate version-information any more',//EwayLocale.msg.generalVersionFailForDownloaded 生成版本文件失败:"已下发"状态的广告不能再生成版本信息.
		generalVersionSuccess:"Generate the version file successfully",//EwayLocale.msg.generalVersionSuccess 生成版本文件成功.
		createSuccess:"Create Successfully.",//EwayLocale.msg.createSuccess 创建成功.
		mustHaveOneResource:'At least has one resource of advertisement!',//EwayLocale.msg.mustHaveOneResource 至少包含一个广告资源!
		saveFail:'Save failed',//EwayLocale.msg.saveFail 保存失败
		saveFailPleaseRefresh:'Save failed, please refresh retry',//EwayLocale.msg.saveFailPleaseRefresh
		saveFileSizeMaxFail:'Save failed:the max size of each single file is 30M',//EwayLocale.msg.saveFileSizeMaxFail 保存失败:超过最大单个文件大小限制（最大30M）
		saveFileCommunicationFail:'Save failed:connection refused',//EwayLocale.msg.saveFileCommunicationFail 保存失败:与服务器通讯失败
		chooseDevice:"Please choose a machine",//EwayLocale.msg.chooseDevice 请选择设备.
		downloadFailForNoVersion:"Version file issue failed :no generated version-file found or version-file lost,please generate version file first.",//EwayLocale.msg.downloadFailForNoVersion 下发版本文件失败:还没有生成版本文件或者版本文件丢失,请先生成版本文件.
		saveSuccess:'Save successfully',//EwayLocale.msg.saveSuccess 保存成功！

		removeSuccess:'Relieve successfully',//EwayLocale.msg.removeSuccess
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
		mustSelectPerson:"Please choose personnel must"//EwayLocale.msg.mustSelectPerson
	},
	confirm:{
		titleSure:'Confirm',//EwayLocale.confirm.titleSure 确认
		todoDelete:'Delete this record?',//EwayLocale.confirm.todoDelete 是否删除该记录?
		title:'Tip',//EwayLocale.confirm.title 提示
		withoutNumTaskConfirmInfo:'Job save successfully,skip to the "Distribute monitor" page?',//EwayLocale.confirm.withoutNumTaskConfirmInfo 作业保存成功,是否跳转到"分发监控"页面?
		timeout:'Session timeout, 3 seconds after the automatic jump to the system login page',//EwayLocale.confirm.timeout
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
		unlink:'Delete',//EwayLocale.button.unlink
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
		personM:'Maintenancer',//EwayLocale.button.personM 厂商管理员
		personTM:'Banker',//EwayLocale.button.personTM管机员
		orgAdmin:'Bank Administrator'//EwayLocale.button.orgAdmin机构管理员
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
			record:'Please choose one record'//请选择您要查看的记录.
		},
		update:{
			one:'Only choose one record',//只能选择一条记录更改.
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
			have:'yes',//有 EwayLocale.tip.own.have
			nothing:'no'//无
		},
		right:{
			yes:'yes', //是 EwayLocale.tip.right.yes
			no:'no' //否
		},
		add:{
			error:'Add failed'//EwayLocale.tip.add.error 新增失败
		},
		success:'Successfully.',//EwayLocale.tip.success 成功
		fail:'Failed:',//EwayLocale.tip.fail 失败
		phone:'Please enter the right telephone number', //请输入正确的电话号码
		remind:'Tip',//EwayLocale.tip.remind 提示
		formatPageBfMsg: 'Per page',// EwayLocale.tip.formatPageBfMsg
		formatPageAfMsg: 'items',// EwayLocale.tip.formatPageAfMsg

		unCertain:'Unknown',//未知
		searchOfNoLegal:'There are some illegal parameters in the query condition,can not commit ',//查询项中存在不合法的输入,不能提交.
		choseExportDevInfo:'Please choose the machine which you want to export for detail ',//请选择要导出信息的设备
		nowLink:'Connecting...',//正在连接......
		linkFailure:'Connecting failed.',//EwayLocale.tip.linkFailure 连接失败
		inputError:'Illegal input',//输入有误
		numberExist:'Exist ID, please enter again',//此编号已经存在,请重新输入.
		isConfirmRemove:'The relationship of the group will be deleted with this delete operation , make sure you want to delete?',//删除分组,关联关系也被删除,是否真的要删除指定分组?
		noGroupInfo:'No data of the group ,can not execute query',//没有组信息,无法查询.
		selectAdd:'Please choose the record which you want to add',//请选择您要增加的记录.
		continueAdd:'Add successfully,continue add machine to this group?',//添加成功,是否继续向组内添加设备?
		addFail:'Add failed.',//添加失败.
		isRemoveDev:'Remove this machine from this group?',//是否从该组移除该设备?
		removeFail:'Remove failed',//移除失败.
		selectRemoveGroup:'Please choose the group which the machine you want to remove belongs.',//请选择您要移除的设备所在组.
		selectRemoveDev:'Please choose the machine which you want to remove.',//请选择您要移除的设备.
		selectMoveDev:'Please choose the machine which you want to move.',//请选择要移动的设备.
		moveSuc:'Move the machine successfully',//移机成功.
		dateReSelect:'The begin date can not be later than end date,please select again',//开始时间不能大于结束日期,请重新选择
		selectPlan:'Please choose the program which you want to execute',//请选择您应用的方案.
		removeFail:'Relieve failed',//解除失败.
		selectRemoveDev:'Please choose the machine which you want to relieve.',//请选择要解除的设备.
		relatedFail:'Binding machine failed.',//关联失败.
		selectRelatedDev:'Please choose machines which you want to bind.',//请选择要关联的设备
		planNoUpdate:'This program has been executed ,can not be modified ',//该方案已经执行,不可修改.
		planNoRemove:'This program has been executed ,can not be deleted ',///该方案已经执行,不可删除.
		exportFiles: 'Please choose a file to import,only support .xls or .xlsx files ',//请选择导入文件,只支持.xls和.xlsx格式的文件
		noChange:'Did not change the data, change and then click OK!',//EwayLocale.tip.noChange没有更改数据,请更改后再点击确定
		operateSuc:'Successful operation',//EwayLocale.tip.operateSuc操作成功
		operateWrong:'Successful operation',//EwayLocale.tip.operateWrong操作成功
		deleteOne:'You can only delete a record.',//EwayLocale.tip.deleteOne一次只能删除一条记录
		chooseRecord:'Please choose the records that you want to associate.',//EwayLocale.tip.chooseRecord请选择您要关联的记录
		choosePlan:'Please choose the program you want to view',//EwayLocale.tip.choosePlan请选择您要查看的方案
		planDetail:'Programme details',//EwayLocale.tip.planDetail方案详情
		planDate:'Programme details (date)',//EwayLocale.tip.planDate方案详情(日期)
		planWeek:'Programme details (Week)',//EwayLocale.tip.planWeek方案详情(星期)
		planNoConf:'The program no detailed settings！',//EwayLocale.tip.planNoConf该方案无详细设置
		chooseRelatedDev:'Please choose the device you want to associate！',//EwayLocale.tip.chooseRelatedDev请选择您要关联的设备
		devRelatedPlan:'Device has been bound with the service plan!',//EwayLocale.tip.devRelatedPlan设备已关联开机方案
		//add by panxin
		tips:'Tips',//EwayLocale.tip.tips 提示
		input:'Please enter the right item',//EwayLocale.tip.input//请正确输入
		roleName:'Just for character ‘a-z’ or ‘A-Z’ or numbers ‘0-9’ ,max length is 40',//EwayLocale.tip.roleName 由字母‘a-z’或‘A-Z’、数字‘0-9’，最多可输入 40位
		notNull:'Can not be null',//EwayLocale.tip.notNull 不能为空
		cardNo:'Just for 13-19 numbers ‘0-9’',//EwayLocale.tip.cardNo 只能输入13到19个数字
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
					managerSuccess:'Set manager successfully.',//EwayLocale.tip.bankOrg.manager.set.managerSuccess 设置管理员成功.
					managerFail:'Set manager failed.',//EwayLocale.tip.bankOrg.manager.set.managerFail 设置管理员失败.
					chooseOneManager:'Please choose at least one record'//EwayLocale.tip.bankOrg.manager.set.chooseOneManager 请至少选择一条记录
				},
				remove:{
					confirm:'Delete the manager of this organization?',//EwayLocale.tip.bankOrg.manager.remove.confirm 是否删除该机构管理员?
					reChoose:'You have not choose the organization or there is no manager of this organization, please choose again',//EwayLocale.tip.bankOrg.manager.remove.reChoose 您未选择您要设置的机构或该机构下没有管理员，请重新选择
					delSuccess:'Delete the manager successfully',//EwayLocale.tip.bankOrg.manager.remove.delSuccess 删除管理员成功.
					delFail:'Delete the manager failed'//EwayLocale.tip.bankOrg.manager.remove.delFail 删除管理员失败.
				}
			},
			orgEligible:'Organization which accordwith the conditions',//EwayLocale.tip.bankOrg.orgEligible 符合条件的机构
			downGradeOrg:'direct lower organization',//EwayLocale.tip.bankOrg.downGradeOrg 的直接下级机构
			move:{
				chooseOrg:'Please choose organization which you want to move.',//EwayLocale.tip.bankOrg.move.chooseOrg 请选择您要迁移的组织
				moveSuccess:'Move the organization successfully.'//EwayLocale.tip.bankOrg.move.moveSuccess 组织迁移成功
			}
		},
		bankPer:{
			allPersonInfo:'ALL info of bank employeers',//EwayLocale.tip.bankPer.allPersonInfo 所有银行人员信息
			link:{
				linkPerson:'Please choose a maintenaner which you want to bind with.',//EwayLocale.tip.bankPer.link.linkPerson 请选择您关联的人员
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
				createSuccess:'Create successfully,newcreate account',//EwayLocale.tip.user.add.createSuccess 创建成功,新建账户
				initPasswd:'The init password is :888888'//EwayLocale.tip.user.add.initPasswd 初始密码为: 888888
			},
			remove:{
				failRoot:'Delete failed,system manager can not be deleted.',//EwayLocale.tip.user.remove.failRoot 删除失败:系统管理员用户,无法删除
				confirm:'Delete this record? User log will delete with this operation.',//EwayLocale.tip.user.remove.confirm 是否删除该记录:删除用户会删除该用户的日志信息.
				fail:'Delete failed: can not delete the role,please try again'//EwayLocale.tip.user.remove.fail //删除失败:无法删除角色,请重新操作.
			},
			update:{
				fail:'Update failed:the record dose not exist,please refresh.'//EwayLocale.tip.user.update.fail 更改失败:记录不存在,请刷新后操作
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
					left:'leave'//EwayLocale.tip.business.transaction.transactionMonitor.left 离开
				},
				historyTransaction:{
					input:'Terminal ID is necessary for the query.'//EwayLocale.tip.business.transaction.historyTransaction.input 查询必须输入设备号


				}
			},
			blackList:{
				importing:'Importing file',//EwayLocale.tip.business.blackList.importing 正在导入文件
				importSuccess:'Blackcard file importing successfully'//EwayLocale.tip.business.blackList.importSuccess 导入黑名单卡文件成功
			},
			card:{
				returnFail:'Turnover failed:system error.',//EwayLocale.tip.business.card.returnFail 移交失败 后台处理出错
				choose:'Please choose the card which you want to turnover.',//EwayLocale.tip.business.card.choose 请选择您移交的卡片
				returnSucess:'Turnover successfully.',//EwayLocale.tip.business.card.returnSucess 移交成功
				chooseBack:'Please choose the card which you want to draw.',//EwayLocale.tip.business.card.chooseBack 请选择要领取的卡片
				getSuccess:'Draw successfully.',//EwayLocale.tip.business.card.getSuccess 领取成功
				destroyConfirm:'Destroy this card?',//EwayLocale.tip.business.card.destroyConfirm 是否销毁这张卡片
				destroySuccess:'Destroy successfully',//EwayLocale.tip.business.card.destroySuccess 销毁成功
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
				logLoadConfirm:'Get the applogs successfully,download it?',//EwayLocale.tip.business.device.logLoadConfirm 应用日志提取成功,是否下载?
				logPullFail:'Get the applogs failed',//EwayLocale.tip.business.device.logPullFail 提取应用电子日志失败
				logFail:'Log process failed.',//EwayLocale.tip.business.device.logFail log 处理失败
				linkServerFail:'Connecting refused.',//EwayLocale.tip.business.device.linkServerFail 服务器连接失败
				logicOpen:'Confirm execute open-service command.',//EwayLocale.tip.business.device.logicOpen 确认要执行开启服务命令
				openSuccess:'Execute open-service command successfully.',//EwayLocale.tip.business.device.openSuccess 执行开启服务命令成功.
				openFail:'Execute open-service command failed.',//EwayLocale.tip.business.device.openFail 执行开启服务命令失败.
				closeConfirm:'Confirm execute pause-service command?',//EwayLocale.tip.business.device.closeConfirm 确认要执行暂停服务命令?
				closeServiceSuccess:'Execute pause-service command successfully.',//EwayLocale.tip.business.device.closeServiceSuccess 执行暂停服务成功
				closeServiceFail:'Execute pause-service command failed.',//EwayLocale.tip.business.device.closeServiceFail 执行暂停服务失败
				closeNormal:'Shut down normally.',//EwayLocale.tip.business.device.closeNormal 正常关机
				closeComfirm:'Confirm execute normal shut-down command,it may brings some risk?',//EwayLocale.tip.business.device.closeComfirm 确认要执行正常关机命令么,可能会存在风险?
				closing:'Shutting down normally.',//EwayLocale.tip.business.device.closing 正在执行正常关机
				closeSucess:'Shut down normally successfully.',//EwayLocale.tip.business.device.closeSucess 正常关机成功
				closeFail:'Shut down normally failed.',//EwayLocale.tip.business.device.closeFail 正常关机失败
				closeSentFail:'Send the normal shut-down command failed ',//EwayLocale.tip.business.device.closeSentFail 正常关机命令发送失败.
				forceClose:'Force shut down',//EwayLocale.tip.business.device.forceClose 强制关机
				forceCloseComfirm:'Confirm execute force shut-down command,it may brings some risk?',//EwayLocale.tip.business.device.forceCloseComfirm 确认要执行强制关机命令么,可能会存在严重风险?
				forceClosing:'Shutting down force.',//EwayLocale.tip.business.device.forceClosing 正在执行强制关机
				forceCloseSucess:'Force-shut-down successfully.',//EwayLocale.tip.business.device.forceCloseSucess 强制关机成功.
				forceCloseFail:'Force-shut-down failed.',//EwayLocale.tip.business.device.forceCloseFail 强制关机失败.
				ForceCloseSentFail:'Send the force-shut-down command failed ',//EwayLocale.tip.business.device.ForceCloseSentFail 强制关机命令发送失败.
				reboot:'Reboot normally.',//EwayLocale.tip.business.device.reboot 正常重启
				rebootConfirm:'Confirm execute normal-reboot command,it may brings some risk?',//EwayLocale.tip.business.device.rebootConfirm 确认要执行正常重启命令么,可能会存在风险?
				rebooting:'Normally rebooting',//EwayLocale.tip.business.device.rebooting 正在执行正常重启
				rebootSucess:'Normal-reboot successfully.',//EwayLocale.tip.business.device.rebootSucess 正常重启成功
				rebootFail:'Normal-reboot failed.',//EwayLocale.tip.business.device.rebootFail 正常重启失败.
				rebootSendFail:'Send the  normal-reboot command failed ',//EwayLocale.tip.business.device.rebootSendFail 正常重启命令发送失败.
				forceReboot:'Force reboot',//EwayLocale.tip.business.device.forceReboot 强制重启
				forceRebootConfirm:'Confirm execute force-reboot command,it may brings some risk?',//EwayLocale.tip.business.device.forceRebootConfirm 确认要执行强制重启命令么,可能会存在严重风险
				forceRebooting:'Executing force-reboot command',//EwayLocale.tip.business.device.forceRebooting 正在执行强制重启
				forceRebootSuccess:'Force-reboot successfully.',//EwayLocale.tip.business.device.forceRebootSuccess 强制重启成功
				forceRebootFail:'Force-reboot failed.',//EwayLocale.tip.business.device.forceRebootFail 强制重启失败.
				forceRebootSendFail:'Send the normal force-reboot command failed ',//EwayLocale.tip.business.device.forceRebootSendFail 强制重启命令发送失败.
				resetConfirm:'Confirm execute force-reset command?',//EwayLocale.tip.business.device.resetConfirm 确认要执行强制复位
				resetSuccess:'Force-reset successfully',//EwayLocale.tip.business.device.resetSuccess 强制复位成功
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
				listPattern:'List Pattern',//EwayLocale.tip.business.device.listPattern 列表方式
				remoteCommandMsg:'Remote Command send Successful'//EwayLocale.tip.business.device.remoteCommandMsg 命令发送成功
			}
		}

	},

	//**********************************************************/

	combox:{
		select:'Please select',//EwayLocale.combox.select --请选择--
		explorer: 'Browse...'//EwayLocale.combox.explorer 浏览...
	},



	vtype:{
		ip:'Please enter the correct IP address',//请输入正确的IP地址
		zip:'Please enter the correct Z.C ,6 numbers', //请输入正确的邮编格式，6位的数字
		versionNo:'Incorrect Version No,format explain：1.Version No make up with 4 part:A.B.C.D ;2.Only part A is necessary ;3. A、B、C、D must be an integer which greater than 0,max length of each part is 8;4.Part ABCD must split with `.`',
		terminalId:'Just for 1-20 characters ‘a-z’ or ‘A-Z’ or numbers ‘0-9’ or‘-’ or‘_’ or ‘.’,and must be start with character or number.',//输入错误,设备号由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’点号‘.’组成,只能以字母或数字开头,长度1到20位。
		mobile:'Input error,mobile number can only be 8-11 numbers',//输入错误,手机号码只能输入8到11位数字。
		cardNo:'Input error,bankcard number can only be 16-19 numbers',//输入错误,银行卡号只能输入16到19位数字。
		telephone:'Input error,phone number can only be 8-11 numbers',//输入错误,固定电话号码只能输入8到11位数字。
		daterange:'Range of time is not correct.',//日期段不正确
		numberrange:'Range of money is not correct.',//金额范围不正确

		bankOrgCode:'Just for 1-20 characters ‘a-z’ or ‘A-Z’ or number ‘0-9’ or‘-’ or‘_’ or ‘.’,and must be start with character or number',//只能输入1到20字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’、点号‘.’， 只能以字母或数字开头！
		zip:'Just for 6 ‘0-9’ numbers',//只能输入6个‘0-9’的数字！
		maxLength20:'The maximum length for this field is 20.',//允许的最大长度为20
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
		endDateGtBenginDate:' Operate date start with can not be later than Operate date end with'//其实时间不能大于开始时间......
	},

	commen:{
		jobNum:'Job Number',//EwayLocale.commen.jobNum 工号
		name:'Name',//EwayLocale.commen.name 姓名
		personJobName:'Quarters',//EwayLocale.commen.personJobName 岗位
		state:'Status',//EwayLocale.commen.state 状态
		birthday:'Birthday',//EwayLocale.commen.birthday 生日
		comboxStatus:{
			onJob:'Incumbency',//EwayLocale.commen.comboxStatus.onJob 在岗
			onAdjust:'Leaving',//EwayLocale.commen.comboxStatus.onAdjust 离职
			onVacation:'Vacation',//EwayLocale.commen.comboxStatus.onVacation 休假
			other:'other',//EwayLocale.commen.comboxStatus.other 其他
			dredge:'Dredge',//EwayLocale.commen.comboxStatus.dredge 开通
			open:'Open',//EwayLocale.commen.comboxStatus.open 启用
			close:'Close',//EwayLocale.commen.comboxStatus.close 停用
			pastDue:'Expired',//EwayLocale.commen.comboxStatus.pastDue过期
			pastDueSoon:'About to expire'//EwayLocale.commen.comboxStatus.pastDueSoon即将过期
		},
		type:'Type',//EwayLocale.commen.type 类型
		comboxType:{
			machineManager:'Machine manager',//EwayLocale.commen.comboxType.machineManager 管机员
			machineRepairer:'Machine repairer'//EwayLocale.commen.comboxType.machineRepairer 维修人员
		},
		mobile:'Mobile',//EwayLocale.commen.mobile 手机
		email:'E-Mail',//EwayLocale.commen.email 邮箱
		phone:'Phone',//EwayLocale.commen.phone 固话
		gender:'Gender',//EwayLocale.commen.gender 性别
		all:'All',//EwayLocale.commen.all 全部
		comboxGender:{
			male:'Male',//EwayLocale.commen.comboxGender.male 男
			female:'Female',//EwayLocale.commen.comboxGender.female 女
			unknow:'Unknow'//EwayLocale.commen.comboxGender.unknow 未知
		},
		remark:'Description',//EwayLocale.commen.remark 备注
		terminalId:'Terminal ID',//EwayLocale.commen.terminalId 设备号
		ip:'IP',//EwayLocale.commen.ip 网络地址
		orgNameBelongs:'Bank',//EwayLocale.commen.orgNameBelongs 所属机构
		devTypeName:'Deivce Type',//EwayLocale.commen.devTypeName 设备型号
		devVendorName:'Deivce Brand',//EwayLocale.commen.devVendorName 设备品牌
		devCatalogName:'Device Catalog',//EwayLocale.commen.devCatalogName 设备类型
		devStatus:'Status',//EwayLocale.commen.devStatus 设备状态
		comboxDevStatus:{
			upOpen:'upOpen',//EwayLocale.commen.comboxDevStatus.upOpen
			open:'Open',//EwayLocale.commen.comboxDevStatus.open
			stop:'Stop',//EwayLocale.commen.comboxDevStatus.stop
			scrapped:'Scrapped'//EwayLocale.commen.comboxDevStatus.Scrapped
		},
		setManager:'Settings',//EwayLocale.commen.setManager 设置
		devServiceName:'Maintenance Provider',//EwayLocale.commen.devServiceName 设备维护商
		cashboxLimit:'Alarm of money in cashbox',//EwayLocale.commen.cashboxLimit 钞箱报警金额
		installDate:'Installation Time',//EwayLocale.commen.installDate 安装日期
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
		selectAll:'Select All',//EwayLocale.commen.selectAll 全部选择
		selectNon:'Select None',//EwayLocale.commen.selectNon 全部不选
		content:'Content',//EwayLocale.commen.content 消息
		upgrade:'Upgrade',//EwayLocale.commen.upgrade 上级
		port:'port',//EwayLocale.commen.port 网络Port
		seviceMode:'Service mode',//EwayLocale.commen.seviceMode 经营方式
		insideOutside:'In bank flag',//EwayLocale.commen.insideOutside 在行标志
		appVersion:'Atmc Version',//EwayLocale.commen.appVersion 应用版本号
		devInfo:'Device Basic Info',//EwayLocale.commen.devInfo 设备基本信息
		//check end
		personnel:'Contacts',//EwayLocale.commen.personnel 联系人
		warn:'Warning',//EwayLocale.commen.warn 警告
		fatal:'Fatal',//EwayLocale.commen.fatal 故障
		unStable:'UnStable',//EwayLocale.commen.unStable 不稳定
		unknow:'Unknown',//EwayLocale.commen.unknow 未知
		noDevice:'NoDevice',//EwayLocale.commen.noDevice 无设备
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
		announceTheme:'Announce theme'//EwayLocale.commen.announceTheme 公告主题



	},
	//**********************************************************/

	index:{
		indexPage:'Home',//EwayLocale.index.indexPage 首页
		dailyFaultPic:'Faults Trend Chart',//EwayLocale.index.dailyFaultPic 日均故障趋势图
		faultAmount:' Amount of Faults: ',//EwayLocale.index.faultAmount
		devStatusDisPic:'Device Status Pie',//EwayLocale.index.devStatusDisPic
		normalDev:'Healthy',//EwayLocale.index.normalDev 正常设备
		unknownDev:'Unknow',//EwayLocale.index.unknownDev
		exceptionDev:'Fatal',//EwayLocale.index.exceptionDev 异常设备
		amount:'',//EwayLocale.index.amount 台
		versionDistributePie:'Patch Distribution',//EwayLocale.index.versionDistributePie
		retainCardTrendTitle:'Retained Card Trend Chart'//EwayLocale.index.retainCardTrendTitle
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
		inputVali:"Just for 1-20 characters 'a-z'or 'A-Z'or number '0-9' or '-' or '_' or special characters",//EwayLocale.personal.inputVali 只能输入8到20位字母‘a-z’或‘A-Z’、数字‘0-9’、特殊字符！
		inputAgain:'Input again',//EwayLocale.personal.inputAgain 再次输入新密码
		pwdNotSame:'New password is not match',//EwayLocale.personal.pwdNotSame 两次密码不一致！
		rememberPwd:'Click confirm to update passwd ,please remember it',//EwayLocale.personal.rememberPwd 单击确定即可修改密码，请牢记新密码！
		pwdSameNoChange:'New password is the same with the old one,can not commit',//EwayLocale.personal.pwdSameNoChange 输入的新密码与旧密码相同,不可修改.
		reOperate:'Can not modify passwd,please try again'//EwayLocale.personal.reOperate 无法修改密码,请重新操作.
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
		registSuc:'Regist successfully',//EwayLocale.system.registSuc 注册成功
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
		clickDownload:'Click to download'//EwayLocale.system.clickDownload 单击此处即可下载该文档
	}
});
