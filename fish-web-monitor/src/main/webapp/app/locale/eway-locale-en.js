Ext.override(Eway,{
	addSuccess : 'Add successful.',//Eway.addSuccess 增加成功.
	updateSuccess : 'Update successful.',//Eway.updateSuccess 更改成功.
	deleteSuccess : 'Delete successful.',//Eway.deleteSuccess 删除成功.
	choiceUpdateMsg :'Please select the record which you want to update.',//Eway.choiceUpdateMsg 请选择您要更改的记录.
	choiceDeleteMsg :'Please select the record which you want to delete.',//Eway.choiceDeleteMsg 请选择您要删除的记录.
	locale:{
		myTable:'My workbench',//Eway.locale.myTable 我的工作台
		ATMV:'Self-service machine monitor system(ATMV)',//Eway.locale.ATMV 自助设备监控系统(ATMV)
		welcome:'welcome,',//Eway.locale.welcome 欢迎你,
		personalConf:'Personal settings',//Eway.locale.personalConf 个人设置
		systemHelp:'System help',///Eway.locale.systemHelp 系统帮助
		exitSystem:'Quit',//Eway.locale.exitSystem 退出系统
		title:{
			msg:'Message'//Eway.locale.title.msg 信息
		},
		msg:{
			perviewFailForText:'Preview failed:the charater and rolling advertisement is supported to preview',//Eway.locale.msg.perviewFailForText 预览失败:不支持文字滚动广告和公告的预览.
			perviewFailNoResource:'Preview failed:no resource found in this advertisement.',//Eway.locale.msg.perviewFailNoResource 预览失败:此广告没有配置广告资源.
			choseResToPerview:'Please select the advertisement which you want to preview.',//Eway.locale.msg.choseResToPerview 请选择您要预览的广告.
			chooseAdvert:'Please select an advertisement.',//Eway.locale.msg.chooseAdvert 请选择一条广告.
			chooseOneDevice:'Please select a machine.',//Eway.locale.msg.chooseOneDevice 请选择一台设备.
			downLoadedAdvertCantDelete:'Delete failed:can not delete the advertisement which status is "issued" or "wait for issue".',//Eway.locale.msg.downLoadedAdvertCantDelete 删除失败:不能删除"已下发"和"等待下发"状态的广告.
			chooseAdvertToDelete:'Please select the advertisement which you want to delete.',//Eway.locale.msg.chooseAdvertToDelete 请选择您要删除的广告.
			chooseAdvertToDownload:'Please select the advertisement which you want to issue.',//Eway.locale.msg.chooseAdvertToDownload 请选择您要下发的广告.
			generalVersionFailForDownloaded:'生成版本文件失败:"已下发"状态的广告不能再生成版本信息.',//Eway.locale.msg.generalVersionFailForDownloaded 生成版本文件失败:"已下发"状态的广告不能再生成版本信息.
			generalVersionSuccess:"Generate the version file successful",//Eway.locale.msg.generalVersionSuccess 生成版本文件成功.
			createSuccess:"Create successful.",//Eway.locale.msg.createSuccess 创建成功.
			mustHaveOneResource:'At least has one resource of advertisement!',//Eway.locale.msg.mustHaveOneResource 至少包含一个广告资源!
			saveFail:'Save failed',//Eway.locale.msg.saveFail 保存失败
			saveFileSizeMaxFail:'Save failed:the max size of each single file is 30M',//Eway.locale.msg.saveFileSizeMaxFail 保存失败:超过最大单个文件大小限制（最大30M）
			saveFileCommunicationFail:'Save failed:connection refused',//Eway.locale.msg.saveFileCommunicationFail 保存失败:与服务器通讯失败
			chooseDevice:"Please select a machine",//Eway.locale.msg.chooseDevice 请选择设备.
			downloadFailForNoVersion:"下发版本文件失败:还没有生成版本文件或者版本文件丢失,请先生成版本文件.",//Eway.locale.msg.downloadFailForNoVersion 下发版本文件失败:还没有生成版本文件或者版本文件丢失,请先生成版本文件.
			saveSuccess:'Save successful',//Eway.locale.msg.saveSuccess 保存成功！
			
			versionDownloaded:'Can not delete the advertisement which status is "issued" or "wait for issue',//Eway.locale.msg.versionDownloaded 不能删除"等待下发"和"已下发"状态的版本.
			selectVersionToDelete:'Please choose the version which you want to delete',//Eway.locale.msg.selectVersionToDelete 请选择您要删除的版本.
			communicationFail:'Add failed : connction refused.',//Eway.locale.msg.communicationFail 增加失败:与服务器通讯失败.
			sameVersionNoFail:'Add failed: same version no exists.',//Eway.locale.msg.sameVersionNoFail 增加失败:已经存在相同的版本号.
			fileSizeMaxFail:'Add failed:the max size of the file is 300M',//Eway.locale.msg.fileSizeMaxFail 增加失败:超过最大文件大小限制（最大300M）
			fileUnzipFail:'增加失败:上传的压缩包不能正常解压',//Eway.locale.msg.fileUnzipFail 增加失败:上传的压缩包不能正常解压
			addFileFail:'Add failed :',//Eway.locale.msg.addFileFail 增加失败:
			mustSelectDevice:'please choose at least one machine.',//Eway.locale.msg.mustSelectDevice 请至少选择一个设备.
			selectVersionRecord:'Please choose the version which you want to issue',//Eway.locale.msg.selectVersionRecord 请选择您要下发的版本.
			missVersionFile:"Version files lost,can not execute"//Eway.locale.msg.missVersionFile 版本文件丢失,暂不能对版本进行下发控制.
		},
		confirm:{
			titleSure:'Confirm',//Eway.locale.confirm.titleSure 确认
			todoDelete:'Delete this record?',//Eway.locale.confirm.todoDelete 是否删除该记录?
			title:'Tip',//Eway.locale.confirm.title 提示
			withoutNumTaskConfirmInfo:'Job save successful,skip to the "Distribute monitor" page?',//Eway.locale.confirm.withoutNumTaskConfirmInfo 作业保存成功,是否跳转到"分发监控"页面?
			//TODO *为数字需要替换
			taskConfirmInfo0:'第',//Eway.locale.confirm.taskConfirmInfo0 第
			taskConfirmInfo1:'次作业保存成功,是否跳转到"分发监控"页面?'//Eway.locale.confirm.taskConfirmInfo1 次作业保存成功,是否跳转到"分发监控"页面?
		},
		button:{
			search:'Search',//Eway.locale.button.search 查询
			add : 'Add', //Eway.locale.button.add 增加
			update:'Update',//Eway.locale.button.update 更改
			remove:'Delete',//Eway.locale.button.remove 删除
			refresh:'Refresh',//Eway.locale.button.refresh 刷新
			reset:'Reset',//Eway.locale.button.reset 重置
			back:'Back',//Eway.locale.button.back 返回
			apply:'Apply',//Eway.locale.button.apply 应用
			//bankOrg
			deepQuery:'Deep search', //深度查询
			bankOrgMove:'Migrate organization',  //组织迁移
			bankOrgAdmin:'Manager', //管理员
			//bankPerson
			bankPerlink:'Binding machine', //绑定设备
			sure:'Confirm',//Eway.locale.button.sure 确定
			confirm:'Confirm',//Eway.locale.button.confirm 确认
			cancle:'Cancle',//Eway.locale.button.cancle 取消
			choose:'Select',//Eway.locale.button.choose 选择
			pause:'Pause',//Eway.locale.button.pause 暂停
				
			exported:'Export',//Eway.locale.button.exported 导出
			select:'Select', //选择
			info :'Detail', //详细信息
			move:'Move', //移机
			exportXLS:'Export as XLS', //导出XLS
			exportPDF:'Export as PDF', //导出PDF
//			massExport:'批量导入',
			massExport:'Batch import', //批量导入
			download:'issue', //下发
			downloadToolTip:'配置下发作业', //配置下发作业
			save:'save',//Eway.locale.button.save 保存
		},
		//引用其他模块
		refs:{
			selectAll:'All',//Eway.locale.refs.selectAll 全部
			orgName:'Organization',//Eway.locale.refs.orgName 机构
			terminalId:'Terminal ID',//Eway.locale.refs.terminalId 设备编号
			ip:'IP address',//Eway.locale.refs.ip IP地址
			devType:"Device Type"//Eway.locale.refs.devType 设备型号
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
				error:'Add failed'//Eway.locale.tip.add.error 新增失败
			},
			success:'Successful.',//Eway.locale.tip.success 成功
			fail:'Failed:',//Eway.locale.tip.fail 失败
			phone:'Please enter the right telephone number', //请输入正确的电话号码
			remind:'Tip',//Eway.locale.tip.remind 提示
			displayMessage:'Total:{2} items,display{0}-{1}',//总共：{2}条，显示{0}-{1}
			
			unCertain:'Unkown',//未知
			searchOfNoLegal:'There are some illegal parameters in the query condition,can not commit ',//查询项中存在不合法的输入,不能提交.
			choseExportDevInfo:'Please select the machine which you want to export for detail ',//请选择要导出信息的设备
			nowLink:'Connecting',//正在连接......
			linkFailure:'Connecting failed.',//Eway.locale.tip.linkFailure 连接失败
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
			
			//add by panxin
			tips:'Tips',//Eway.locale.tip.tips 提示
			input:'Please enter the right item',//Eway.locale.tip.input//请正确输入
			roleName:'Just for character ‘a-z’ or ‘A-Z’ or numbers ‘0-9’ ,max length is 40',//Eway.locale.tip.roleName 由字母‘a-z’或‘A-Z’、数字‘0-9’，最多可输入 40位
			roleDescription:'由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’和点号‘.’、汉字，只能以汉字,字母或数字开头,最多可输入100位',//Eway.locale.tip.roleDescription
			notNull:'Can not be null',//Eway.locale.tip.notNull 不能为空
			cardNo:'Just for 13 to 19 numbers',//Eway.locale.tip.cardNo 只能输入13到19个数字
			blankBegin:'Can not start with blank',//Eway.locale.tip.blankBegin 不能以空格开头
			passwd:{
				confirmPasswd:'Reset the password ,confirm?',//Eway.locale.tip.confirmPasswd 是否确认密码重置？
				resetPasswding:'Reseting the password.....',//Eway.locale.tip.resetPasswding 正在重置密码......
				resetPasswdFail:'Reset the password failed'//Eway.locale.tip.resetPasswdFail 密码重置失败！
			},
			operateDate:{
				operateDateBegin:'Operate date start with ',//Eway.locale.tip.operateDate.operateDateBegin 操作日期开始于
				operateDateEnd:'Operate date end  with'//Eway.locale.tip.operateDate.operateDateEnd 操作日期结束于
			},
			bankOrg:{
				manager:{
					set:{
						chooseOrg:'Please choose the organization which you want to set',//Eway.locale.tip.bankOrg.manager.set.chooseOrg 请选择您要设置的机构.
						managerSuccess:'Manager setting successful.',//Eway.locale.tip.bankOrg.manager.set.managerSuccess 设置管理员成功.
						managerFail:'Manager setting failed.'//Eway.locale.tip.bankOrg.manager.set.managerFail 设置管理员失败.
					},
					remove:{
						confirm:'Delete the manager of this organization?',//Eway.locale.tip.bankOrg.manager.remove.confirm 是否删除该机构管理员?
						reChoose:'You have not choose the organization or there is no manager of this organization, please choose again',//Eway.locale.tip.bankOrg.manager.remove.reChoose 您未选择您要设置的机构或该机构下没有管理员，请重新选择
						delSuccess:'Delete the manager successful',//Eway.locale.tip.bankOrg.manager.remove.delSuccess 删除管理员成功.
						delFail:'Delete the manager failed'//Eway.locale.tip.bankOrg.manager.remove.delFail 删除管理员失败.
					}
				},
				orgEligible:'符合条件的机构',//Eway.locale.tip.bankOrg.orgEligible 符合条件的机构
				downGradeOrg:'的直接下级机构',//Eway.locale.tip.bankOrg.downGradeOrg 的直接下级机构
				move:{
					chooseOrg:'请选择您要迁移的组织.',//Eway.locale.tip.bankOrg.move.chooseOrg 请选择您要迁移的组织
					moveSuccess:'组织迁移成功.'//Eway.locale.tip.bankOrg.move.moveSuccess 组织迁移成功
				}
			},
			bankPer:{
				allPersonInfo:'ALL info of bank employeers',//Eway.locale.tip.bankPer.allPersonInfo 所有银行人员信息
				link:{
					linkPerson:'Please choose a employeer which you want to link.',//Eway.locale.tip.bankPer.link.linkPerson 请选择您关联的人员
					unLinkPersonFail:'Link failed.',//Eway.locale.tip.bankPer.link.unLinkPersonFail 关联失败
					unlinkDev:'Please choose a machine which you want to lift.',//Eway.locale.tip.bankPer.link.unlinkDev 请选择要解除的设备
					linkDev:'Please choose a machine which you want to link.',//Eway.locale.tip.bankPer.link.linkDev 请选择要关联的设备
					unLinkDevFail:'Lift failed.'//Eway.locale.tip.bankPer.link.unLinkDevFail 解除失败
				},
				personEligible:'符合条件的人员',//Eway.locale.tip.bankPer.personEligible
				downGradePer:'以及其下属机构下的人员信息',//Eway.locale.tip.bankPer.downGradePer
				personBelongs:'下的人员信息'//Eway.locale.tip.bankPer.personBelongs
			},
			serviceOrg:{
				chooseOrg:'Please choose the service which you want to set.',//Eway.locale.tip.serviceOrg.chooseOrg 请选择您要设置的维护商.
				remove:{
					reChoose:'You hava not choose a service or no manager found in the service,please choose again.'//Eway.locale.tip.serviceOrg.remove.reChoose //您未选择要设置的维护商或该维护商下没有管理员,请重新选择.
				}
			},
			servicePer:{
				allSerPer:'All info of service pepole'//Eway.locale.tip.servicePer.allSerPer 所有维护商人员信息
			},
			user:{
				add:{
					createAcc:'Please choose the man which you want to create account',//Eway.locale.tip.user.add.createAcc 请选择您要创建的账号的人员
					createSuccess:'Create successful,newcreate account',//Eway.locale.tip.user.add.createSuccess 创建成功,新建账户
					initPasswd:'The init password is :888888'//Eway.locale.tip.user.add.initPasswd 初始密码为: 888888
				},
				remove:{
					failRoot:'Delete failed,system manager can not be deleted.',//Eway.locale.tip.user.remove.failRoot 删除失败:系统管理员用户,无法删除
					confirm:'Delete this record? User log will delete with this operation.',//Eway.locale.tip.user.remove.confirm 是否删除该记录:删除用户会删除该用户的日志信息.
					fail:'Delete failed: can not delete the role,please try again'//Eway.locale.tip.user.remove.fail //删除失败:无法删除角色,请重新操作.
				},
				update:{
					fail:'Update failed:the record dose not exist,please refresh.',//Eway.locale.tip.user.update.fail 更改失败:记录不存在,请刷新后操作
				},
				move:{
					choose:'Please choose the record which you want to move.'//Eway.locale.tip.user.move.choose 请选择需要移动的记录.
					
				}
			},
			business:{
				transaction:{
					transactionMonitor:{
						beginMonitor:'请先停止监控后再输入条件，按开始监控按钮即可进行条件监控！',//Eway.locale.tip.business.transaction.transactionMonitor.beginMonitor
						input:'设备号、对方账号、客户账号至少输入一个.',//Eway.locale.tip.business.transaction.transactionMonitor.input 设备号、对方账号、客户账号至少输入一个.
						left:'leave',//Eway.locale.tip.business.transaction.transactionMonitor.left 离开
					},
					historyTransaction:{
						input:'Terminal ID is necessary for the query.'//Eway.locale.tip.business.transaction.historyTransaction.input 查询必须输入设备号
						
						
					}
				},
				blackList:{
					importing:'Importing file',//Eway.locale.tip.business.blackList.importing 正在导入文件
					importSuccess:'Blackcard file importing successful'//Eway.locale.tip.business.blackList.importSuccess 导入黑名单卡文件成功
				},
				card:{
					returnFail:'移交失败:后台处理出错.',//Eway.locale.tip.business.card.returnFail
					choose:'请选择您移交的卡片.',//Eway.locale.tip.business.card.choose 
					returnSucess:'移交成功.',//Eway.locale.tip.business.card.returnSucess
					chooseBack:'请选择要领取的卡片',//Eway.locale.tip.business.card.chooseBack
					getSuccess:'领取成功.',//Eway.locale.tip.business.card.getSuccess
					destroyConfirm:'是否销毁这张卡片?',//Eway.locale.tip.business.card.destroyConfirm
					destroySuccess:'销毁成功',//Eway.locale.tip.business.card.destroySuccess
					chooseDestroy:'请选择要销毁的卡片.',//Eway.locale.tip.business.card.chooseDestroy
					idCardRegex:'请输入正确的身份证号码,15位或者18位',//Eway.locale.tip.business.card.idCardRegex
					accountRegex:'请正确输入户口本上的身份证号码,15位或者18位',//Eway.locale.tip.business.card.accountRegex
					driveCardRegex:'请正确输入驾驶证上的身份证号码,15位或者18位',//Eway.locale.tip.business.card.driveCardRegex
					passportRegex:'请正确输入护照上的身份证号码,15位或者18位',//Eway.locale.tip.business.card.passportRegex
					soldierRegex:'请输入正确的军官证号码,1-5位汉字和1-10位数字',//Eway.locale.tip.business.card.soldierRegex
					soldierCard:'请输入正确的士兵证,7-8位数字',//Eway.locale.tip.business.card.soldierCard
					busnessPaper:'请输入正确的法人营业执照,12-15位数字',//Eway.locale.tip.business.card.busnessPaper
					busnessCode:'请输入正确的法人代码证,15位数字',//Eway.locale.tip.business.card.busnessCode
					taxPaper:'请输入正确的税务登记证,15位数字'//Eway.locale.tip.business.card.taxPaper
					
				},
				device:{
					getCashInfoFail:'Get the cashbin info failed',//Eway.locale.tip.business.device.getCashInfoFail 获取钞箱信息失败
					operating:'Executing',//Eway.locale.tip.business.device.operating 正在执行
					reviewFail:'Failed to review.',//Eway.locale.tip.business.device.reviewFail 查看失败
					logLoadConfirm:'Get the applogs successful,download it?',//Eway.locale.tip.business.device.logLoadConfirm 应用日志提取成功,是否下载?
					logPullFail:'Get the applogs failed',//Eway.locale.tip.business.device.logPullFail 提取应用电子日志失败
					logFail:'Log process failed.',//Eway.locale.tip.business.device.logFail log 处理失败
					linkServerFail:'Connecting refused.',//Eway.locale.tip.business.device.linkServerFail 服务器连接失败
					logicOpen:'Confirm to execute open-service command.',//Eway.locale.tip.business.device.logicOpen 确认要执行开启服务命令
					openSuccess:'Execute open-service command successful.',//Eway.locale.tip.business.device.openSuccess 执行开启服务命令成功.
					openFail:'Execute open-service command failed.',//Eway.locale.tip.business.device.openFail 执行开启服务命令失败.
					closeConfirm:'Confirm to execute pause-service command?',//Eway.locale.tip.business.device.closeConfirm 确认要执行暂停服务命令?
					closeNormal:'Shut down normally.',//Eway.locale.tip.business.device.closeNormal 正常关机
					closeComfirm:'Confirm to execute normal shut-down command,it may brings some risk?',//Eway.locale.tip.business.device.closeComfirm 确认要执行正常关机命令么,可能会存在风险?
					closing:'Shutting down normally.',//Eway.locale.tip.business.device.closing 正在执行正常关机
					closeSucess:'Shut down normally successful.',//Eway.locale.tip.business.device.closeSucess 正常关机成功
					closeFail:'Shut down normally failed.',//Eway.locale.tip.business.device.closeFail 正常关机失败
					closeSentFail:'Send the normal shut-down command failed ',//Eway.locale.tip.business.device.closeSentFail 正常关机命令发送失败.
					forceClose:'Force shut down',//Eway.locale.tip.business.device.forceClose 强制关机
					forceCloseComfirm:'Confirm to execute force shut-down command,it may brings some risk?',//Eway.locale.tip.business.device.forceCloseComfirm 确认要执行强制关机命令么,可能会存在严重风险?
					forceClosing:'Shutting down force.',//Eway.locale.tip.business.device.forceClosing 正在执行强制关机
					forceCloseSucess:'Force-shut-down successful.',//Eway.locale.tip.business.device.forceCloseSucess 强制关机成功.
					forceCloseFail:'Force-shut-down failed.',//Eway.locale.tip.business.device.forceCloseFail 强制关机失败.
					ForceCloseSentFail:'Send the force-shut-down command failed ',//Eway.locale.tip.business.device.ForceCloseSentFail 强制关机命令发送失败.
					reboot:'Reboot normally.',//Eway.locale.tip.business.device.reboot 正常重启
					rebootConfirm:'Confirm to execute normal-reboot command,it may brings some risk?',//Eway.locale.tip.business.device.rebootConfirm 确认要执行正常重启命令么,可能会存在风险?
					rebooting:'Normally rebooting',//Eway.locale.tip.business.device.rebooting 正在执行正常重启
					rebootSucess:'Normal-reboot successful.',//Eway.locale.tip.business.device.rebootSucess 正常重启成功
					rebootFail:'Normal-reboot failed.',//Eway.locale.tip.business.device.rebootFail 正常重启失败.
					rebootSendFail:'Send the  normal-reboot command failed ',//Eway.locale.tip.business.device.rebootSendFail 正常重启命令发送失败.
					forceReboot:'Force reboot',//Eway.locale.tip.business.device.forceReboot 强制重启
					forceRebootConfirm:'Confirm to execute force-reboot command,it may brings some risk?',//Eway.locale.tip.business.device.forceRebootConfirm 确认要执行强制重启命令么,可能会存在严重风险
					forceRebooting:'Executing force-reboot command',//Eway.locale.tip.business.device.forceRebooting 正在执行强制重启
					forceRebootSuccess:'Force-reboot successful.',//Eway.locale.tip.business.device.forceRebootSuccess 强制重启成功
					forceRebootFail:'Force-reboot failed.',//Eway.locale.tip.business.device.forceRebootFail 强制重启失败.
					forceRebootSendFail:'Send the normal force-reboot command failed ',//Eway.locale.tip.business.device.forceRebootSendFail 强制重启命令发送失败.
					resetConfirm:'Confirm to execute force-reset command?',//Eway.locale.tip.business.device.resetConfirm 确认要执行强制复位
					resetSuccess:'Force-reset successful',//Eway.locale.tip.business.device.resetSuccess 强制复位成功
					resetFail:'Force-reset failed',//Eway.locale.tip.business.device.resetFail 强制复位失败
					resetSendFail:'Send the force-reset command failed.',//Eway.locale.tip.business.device.resetSendFail 强制复位命令发送失败
					term:'Terminal',//Eway.locale.tip.business.device.term 设备
					detail:'Detail',//Eway.locale.tip.business.device.detail 详情
					refresh:'Refreshing......',//Eway.locale.tip.business.device.refresh 正在刷新......
					chooseOrg:'机构筛选',//Eway.locale.tip.business.device.chooseOrg 机构筛选
					stateSet:'状态监控项配置',//Eway.locale.tip.business.device.stateSet
					filterSet:'过滤条件项配置',//Eway.locale.tip.business.device.filterSet
					connFirst:'当前已经暂停了与服务器的监控连接,请先与服务器建立连接,即"开始监控"',//Eway.locale.tip.business.device.connFirst
					matrixPattern:'Matrix Pattern',//Eway.locale.tip.business.device.matrixPattern 矩阵方式
					listPattern:'List Pattern'//Eway.locale.tip.business.device.listPattern 列表方式
				},
			},
			
		},
		
		//**********************************************************/
		
		combox:{
			select:'Please select',//Eway.locale.combox.select --请选择--
			explorer: 'Browse...',//Eway.locale.combox.explorer 浏览...
		},
		
		
		
		vtype:{
			ip:'Please enter the correct IP address',//请输入正确的IP地址
			zip:'Please enter the correct ZIP ,6 numbers', //请输入正确的邮编格式，6位的数字
			versionNo:'不是正确的版本号格式,格式说明：1.版本号由4个部分组成 A.B.C.D ;2.只有A部分是必须的 ；3. A、B、C、D必须为大于等于0的整数 ,每个部分最大长度为8位； 4.ABCD部分必须用.分隔',
			terminalId:'Input error,The terminal serialno can only be ‘a-z’ or ‘A-Z’ or number ‘0-9’ or‘-’ or‘_’ or ‘.’ and must be start with character or number and length 1-20',//输入错误,设备号由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’点号‘.’组成,只能以字母或数字开头,长度1到20位。
			mobile:'Input error,mobile number can only be 8-11 numbers',//输入错误,手机号码只能输入8到11位数字。
			cardNo:'Input error,bankcard number can only be 16-19 numbers',//输入错误,银行卡号只能输入16到19位数字。
			telephone:'Input error,phone number can only be 8-11 numbers',//输入错误,固定电话号码只能输入8到11位数字。
			daterange:'日期段不正确.',
			numberrange:'金额范围不正确.',//金额范围不正确
			
			bankOrgCode:'Just input 1-20 characters ‘a-z’ or ‘A-Z’ or number ‘0-9’ or‘-’ or‘_’ or ‘.’,and must be start with character or number',//只能输入1到20字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’、点号‘.’， 只能以字母或数字开头！
			zip:'Just for 6 ‘0-9’ numbers',//只能输入6个‘0-9’的数字！
			maxLength20:'Max length is 20',//允许的最大长度为20
			numberRule: '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’和点号‘.’，只能以字母或数字开头。',
			numberRulesOne: '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’和点号‘.’、汉字，只能以汉字,字母或数字开头,最多可输入100位',
			numberRulesFour	: '由字母‘a-z’或‘A-Z’、数字‘0-9’，最多可输入 40位',
			numberRules: '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’和点号‘.’、汉字，只能以汉字,字母或数字开头,最多可输入200位',
			
			
			mobileRules:'Input error,mobile number can only be 8-11 numbers',//手机电话号码只能输入8到11位数字‘0-9’
			choseDev:'请选择您报停的设备.',
			dataLoad:'Data loading......',//正在加载数据
			devLinkNormal:'Please check the connection between server and machine.',//请检查与设备的连接是否正常
			hardwayInitialize:'Hardware module initializing......',//硬件模块正在初始化
			inputCorrect:'Please input correct data.',//请正确输入
			exportRepError:'Export report error,please try again',//导出报表出错，请重新操作!
			planOutdate:'(This program is out of date,can not be applied)',//(此方案已过期，不可应用！)
			
			emailRules: 'email必须符合*@*.*标准。',
			notifyTimesRules: '通知次数必须为数字，最小值为0,最大值为100。',
			sendTimesRules: '发送次数必须为数字，最小值为0,最大值为100。'	,
			
			launchTranscribe:'Starting record......', //正在启动录制
			stopTranscribe:'Stoping record......',//正在停止录制......
			inexistenceScreen:'Screen not found',//不存在此屏幕
			devEmploy:'This machine has been used by ', //该台设备已经被
			userEmploy:'!',//用户占用!
			loadTranscribe:'Downloading the recorded video ......',//正在下载录制好的视频文件
			remoteFailure:'远程浏览失败',//远程浏览失败
			versionChart:'版本下发历史状态分布图',
			openRefresh:'Start the automatic refresh',//开启自动刷新
			choseTask:'Please choose a job',//请选择一个作业
			cancelTask:'不能撤销"完成"状态的作业.',
			cancelParticularTask:'是否真的要撤销指定的作业?(正在运行的作业只会撤销还没有运行的任务.)',
			nowDelete:'Deleting......', //正在删除......
		},
		//**********************************************************/
		versionType:{
			title:'软件分类管理',//Eway.locale.versionType.title
			treeRoot:'所有软件分类',//Eway.locale.versionType.treeRoot
			defaultInstallPath:'默认安装路径',//Eway.locale.versionType.defaultInstallPath
			needRestart:'需要重启设备完成升级',//Eway.locale.versionType.needRestart
			devTypeOfUser:'适用的设备型号',//Eway.locale.versionType.devTypeOfUser
			winTitle:'软件分类',//Eway.locale.versionType.winTitle
			versionTypeNameRegText:'只能输入字母(a-z或A-Z)、数字(0-9)、下划线(_)、横线(-)'//Eway.locale.versionType.versionTypeNameRegText
		},
		statics:{
			title:'下发结果统计',//Eway.locale.statics.title
			versionInfo:'Version info'//Eway.locale.statics.versionInfo //版本信息
		},
		//广告模块
		advert:{
			title:'Advertisement',//Eway.locale.advert.title //广告管理
			createAdvert:'Create Advertisement',//Eway.locale.advert.createAdvert 创建广告
			idleAdvert:'创建等待插卡广告',//Eway.locale.advert.idleAdvert
			transAdvert:'创建交易页面广告',//Eway.locale.advert.transAdvert
			textAdvert:'创建文字滚动广告',//Eway.locale.advert.textAdvert
			annoucementAdvert:'创建公告',//Eway.locale.advert.annoucementAdvert
			updateTitle:'Modify advertisement infomation',//Eway.locale.advert.updateTitle 更改广告信息
			downloadButton:'下发广告',//Eway.locale.advert.downloadButton
			preview:'Advertisement preview',//Eway.locale.advert.preview 广告预览
			id:'AdvertisementID',//Eway.locale.advert.id 广告ID
			type:'Advertisement type',//Eway.locale.advert.type 广告类型
			downType:'广告下发方式',//Eway.locale.advert.downType
			validity:'广告有效期',//Eway.locale.advert.validity
			createdTime:'Created time',//Eway.locale.advert.createdTime 制作时间
			userName:'Creater',//Eway.locale.advert.userName 制作人
			versionStatus:'广告版本状态',//Eway.locale.advert.versionStatus
			advertVersionFile:'版本文件',//Eway.locale.advert.advertVersionFile
			createdTimeStart:'制作时间开始于',//Eway.locale.advert.createdTimeStart
			createdTimeStop:'制作时间结束于',//Eway.locale.advert.createdTimeStop
			downloadAdvertConfig:'下发广告配置',//Eway.locale.advert.downloadAdvertConfig
			versionType:'Software type',//Eway.locale.advert.versionType 软件分类
			jobPriority:'作业优先级',//Eway.locale.advert.jobPriority
			jobType:'Job type',//Eway.locale.advert.jobType 作业类型
			toVersionButton:'还没有生成版本信息，可以单击按钮[生成版本]。',//Eway.locale.advert.toVersionButton
			playTime:'广告播放时长',//Eway.locale.advert.playTime
			beginDate:'Start date',//Eway.locale.advert.beginDate 开始日期
			endDate:'End date',//Eway.locale.advert.endDate 结束日期
			beginTime:'Start time',//Eway.locale.advert.beginTime 开始时间
			endTime:'End time',//Eway.locale.advert.endTime 结束时间
			fileSize:'Resource size',//Eway.locale.advert.fileSize 资源大小
			content:'播放资源内容',//Eway.locale.advert.content
			advertConfig:'广告配置',//Eway.locale.advert.advertConfig
			addIdleTitle:'增加等待插卡广告信息',//Eway.locale.advert.addIdleTitle
			addIdleMore:'再增加一个广告资源',//Eway.locale.advert.addIdleMore
			advertBasicInfo:'广告基本信息',//Eway.locale.advert.advertBasicInfo
			idleAdvertInfo:'等待插卡页面广告',//Eway.locale.advert.idleAdvertInfo
			advertValidity:'广告有效期',//Eway.locale.advert.advertValidity
			validityTemp:'临时播放',//Eway.locale.advert.validityTemp
			validityAlways:'永久播放',//Eway.locale.advert.validityAlways
			idleAdvertResConfig:'等待插卡页面广告资源配置',//Eway.locale.advert.idleAdvertResConfig
			addTransTitle:'增加交易页面广告信息',//Eway.locale.advert.addTransTitle
			transInfoAdvert:'交易页面广告',//Eway.locale.advert.transInfoAdvert	
			transAdvertResConfig:'交易页面广告资源配置',//Eway.locale.advert.transAdvertResConfig
			addTextTitle:'增加文字滚动广告信息',//Eway.locale.advert.addTextTitle
			textInfoAdvert:'文字广告',//Eway.locale.advert.textInfoAdvert	
			textAdvertResConfig:'文字广告资源配置',//Eway.locale.advert.textAdvertResConfig
			addAnnoucementTitle:'增加公告信息',//Eway.locale.advert.addAnnoucementTitle
			annoucementBasicInfo:'公告基本信息',//Eway.locale.advert.annoucementBasicInfo
			annoucementInfoAdvert:'公告',//Eway.locale.advert.annoucementInfoAdvert	
			annoucementAdvertResConfig:'公告页面广告资源配置',//Eway.locale.advert.annoucementAdvertResConfig
			advertTypeSelectEmpty:'请选择广告类型',//Eway.locale.advert.advertTypeSelectEmpty
			advertTypeTrans:'交易页面广告',//Eway.locale.advert.advertTypeTrans
			advertTypeIdle:'等待插卡广告',//Eway.locale.advert.advertTypeIdle
			advertTypeText:'文字滚动广告',//Eway.locale.advert.advertTypeText
			advertTypeAnnou:'公告',//Eway.locale.advert.advertTypeAnnou
			annoucementMoreTitle:'添加公告',//Eway.locale.advert.annoucementMoreTitle
			annoucementContext:'公告内容',//Eway.locale.advert.annoucementContext
			annoucementContextRegText:'不能包含空格',//Eway.locale.advert.annoucementContextRegText
			times:'时长',//Eway.locale.advert.times
			timesTips:'单位:秒，提示：广告播放时长请控制在60秒内',//Eway.locale.advert.timesTips
			hourDisplay:'时',//Eway.locale.advert.hourDisplay
			minuteDisplay:'分',//Eway.locale.advert.minuteDisplay
			secondeDisplay:'秒',//Eway.locale.advert.secondeDisplay
			textMoreTitle:'添加文字滚动页面广告',//Eway.locale.advert.textMoreTitle
			textContext:'滚动文字',//Eway.locale.advert.textContext
			idleMoreTitle:'添加等待插卡页面广告',//Eway.locale.advert.idleMoreTitle
			chooseMediaFile:'请选择媒体文件',//Eway.locale.advert.chooseMediaFile
			uploadResource:'上传资源...',//Eway.locale.advert.uploadResource
			uploadResourceBlank:'请上传资源',//Eway.locale.advert.uploadResourceBlank
			uploadRegText:'上传的资源格式不支持,只能上传.jpg、.avi格式的文件',//Eway.locale.advert.uploadRegText
			resourceFormatTips:'(仅支持.jpg、.avi格式的文件)',//Eway.locale.advert.resourceFormatTips
			resourceAlias:'修改后的文件名',//Eway.locale.advert.resourceAlias
			transMoreTitle:'添加交易页面广告',//Eway.locale.advert.transMoreTitle
			chooseMediaFile:'请选择媒体文件',//Eway.locale.advert.chooseMediaFile
			uploadResource:'上传资源...',//Eway.locale.advert.uploadResource
			uploadResourceBlank:'请上传资源',//Eway.locale.advert.uploadResourceBlank
			uploadRegText:'上传的资源格式不支持,只能上传.jpg、.avi格式的文件',//Eway.locale.advert.uploadRegText
			resourceFormatTips:'(仅支持.jpg、.avi格式的文件)',//Eway.locale.advert.resourceFormatTips
			resourceAlias:'修改后的文件名',//Eway.locale.advert.resourceAlias
			advertDownMethodCover:'覆盖',//Eway.locale.advert.advertDownMethodCover
			uploading:'正在上传资源...',//Eway.locale.advert.uploading
			advertPreviewTitle0:'广告预览(共有 ',//Eway.locale.advert.advertPreviewTitle0
			advertPreviewTitle1:'个资源,当前播放第 ',//Eway.locale.advert.advertPreviewTitle1
			advertPreviewTitle2:'个) ',//Eway.locale.advert.advertPreviewTitle2
			choosedAdvertRes:'您已经选择了',//Eway.locale.advert.choosedAdvertRes
			perviewAdertWithIEBrowse:'非IE浏览器不支持视频广告的预览.',//Eway.locale.advert.perviewAdertWithIEBrowse
			configTitle:'广告详细配置'//Eway.locale.advert.configTitle
		},
		//版本管理模块
		version:{
			selectDeviceInfo0:"已选择的设备(<font color='red'>",//Eway.locale.version.selectDeviceInfo0
			selectDeviceInfo1:"</font>)台",//Eway.locale.version.selectDeviceInfo1
			addVersionTitle:'增加版本信息',//Eway.locale.version.addVersionTitle
			batchTaskName:'任务批次名称',//Eway.locale.version.batchTaskName
			batchTaskNameEmpty:'例如:****需求第1批次升级',//Eway.locale.version.batchTaskNameEmpty
			UpdateTitle:'更改版本信息',//Eway.locale.version.UpdateTitle
			addJobTitle:'配置作业信息',//Eway.locale.version.addJobTitle
			downloadVersionId:'下发版本ID',//Eway.locale.version.downloadVersionId
			taskType:'任务类型',//Eway.locale.version.taskType
			taskTypeManual:'手动升级',//Eway.locale.version.taskTypeManual
			taskTypeAuto:'自动升级',//Eway.locale.version.taskTypeAuto
			taskTypeScheduler:'计划作业',//Eway.locale.version.taskTypeScheduler
			planTime:'计划执行时间',//Eway.locale.version.planTime
			selectableDevice:'可以下发的设备',//Eway.locale.version.selectableDevice
			linkedDevice:'已选择的设备',//Eway.locale.version.linkedDevice
			downloadVerFile:'下载版本文件',//Eway.locale.version.downloadVerFile
			View:{
				title:'Version', //版本管理
				versionDetail:'Version detail',//Eway.locale.version.View.versionDetail 版本详情
				remark:'remark', //Eway.locale.version.View.remark 备注
				newCreate:'Create',//Eway.locale.version.View.newCreate 新建
				downLoaded:'DownLoaded',//Eway.locale.version.View.downLoaded 已下发
				waitting:'Waitting',//Eway.locale.version.View.waitting 等待下发
				versionPath:'Version path',//Eway.locale.version.View.versionPath 版本路径
				versionPathRegText:'不符合文件路径规则，规则如下：1.文件名只能包含英文字母(a-z A-Z)、数字(0-9)、下划线(_)、横线(-) ； 2.路径统一用正斜杠(/)作为分隔符 ；3.不区分大小 ; 示例 E: E:/yihua',//Eway.locale.version.View.versionPathRegText
				versionPathDesc:'(版本文件在自助设备上的安装路径)',//Eway.locale.version.View.versionPathDesc
				versionPerson:'Version maker',//Eway.locale.version.View.versionPerson 创建人
				versionType:'Version type',//Eway.locale.version.View.versionType 版本类型
				versionFile:'Version file',//Eway.locale.version.View.versionFile 版本文件
				versionFileButton:'choose...',//Eway.locale.version.View.versionFileButton 浏览...
				versionFileRegexText:'只能上传zip或rar格式的文件',//Eway.locale.version.View.versionFileRegexText
				versionFileUploadMsg:'正在上传文件...',//Eway.locale.version.View.versionFileUploadMsg
				versionFileEmpty:'请将要下发的版本文件(或者文件夹)打包zip或rar格式',//Eway.locale.version.View.versionFileEmpty
				versionTypeCode:'软件分类编码',//Eway.locale.version.View.versionTypeCode
				versionTypeName:'软件分类名称',//Eway.locale.version.View.versionTypeName
				versionTypeId:'版本类型ID',//Eway.locale.version.View.versionTypeId
				versionTypeEmpty:'-请选择版本类型-',//Eway.locale.version.View.versionTypeEmpty
				versionTime:'Create time',//Eway.locale.version.View.versionTime 创建时间
				versionNo:'Version serialno',//Eway.locale.version.View.versionNo 版本号
				nowVersionNo:'当前版本号',//Eway.locale.version.View.nowVersionNo 当前版本号
				versionStatus:'版本状态',//Eway.locale.version.View.versionStatus
				versionStatusEmptyText:'All', //全部
				autoUpdate:'Update automatic permission',//Eway.locale.version.View.autoUpdate 允许自动更新
				autoUpdateYes:'yes',//Eway.locale.version.View.autoUpdateYes //是
				autoUpdateNo:'no',//Eway.locale.version.View.autoUpdateNo 否
				autoUpdateEmptyText:'All', //全部
				dependVersion:'依赖版本',//Eway.locale.version.View.dependVersion
				dependVersionEmptyText:'请选择依赖类型',
				execBefore:'升级前执行脚本',//Eway.locale.version.View.execBefore
				execBeforeEmptyText:'请输入升级包中的以bat或cmd结尾的文件',//Eway.locale.version.View.execBeforeEmptyText
				execBeforeRegexText:'只能输入bat或cmd结尾的文件',//Eway.locale.version.View.execBeforeRegexText
				versionDesc:'版本描述',//Eway.locale.version.View.versionDesc
				versionDescEmpty:'请用文字描述此版本需求(最长20字符串)',//Eway.locale.version.View.versionDescEmpty
				otherConfigTitle:'其他配置',//Eway.locale.version.View.otherConfigTitle
				otherConfigAutoDown:'允许自动更新(当ATM向服务器检查新版本时，允许自动更新的版本才可以返回给ATM)',//Eway.locale.version.View.otherConfigAutoDown
				otherConfigUncompress:'自动解压缩(选中此项时，在ATM端会自动解压缩)&nbsp;<font color="red">注意：如果版本文件本来不符合zip格式，后被压缩成zip时，请选中此项！</font>',//Eway.locale.version.View.otherConfigUncompress
				versionServerPath:'文件在服务器上的位置', //Eway.locale.version.View.versionServerPath
				versionName:'版本名称',//Eway.locale.version.View.versionName
				downloadVersionName:'下发的版本',//Eway.locale.version.View.downloadVersionName
				downloadVersionNameEmpty:'请选择下发的版本',//Eway.locale.version.View.downloadVersionNameEmpty
				distributionPic:'版本分布图',//Eway.locale.version.View.distributionPic
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
				title:'版本安装信息统计图'
			},
			jobPriority:{
				hight:'Hight',//Eway.locale.version.jobPriority.hight 高
				middle:'middle',//Eway.locale.version.jobPriority.middle 中等
				general:'general'//Eway.locale.version.jobPriority.general 普通
			},
			jobType:{
				float:'手工作业(立即下发)',//Eway.locale.version.jobType.float
				fix:'计划作业(定时下发)'//Eway.locale.version.jobType.fix
			},
			taskStatus:{
				news:'new',//Eway.locale.version.taskStatus.news 新建
				running:'running',//Eway.locale.version.taskStatus.running 运行中
				noticed:'Notice successful',//Eway.locale.version.taskStatus.noticed 通知成功
				noticedFail:'Notice failed',//Eway.locale.version.taskStatus.noticedFail 通知失败
				downloaded:'已下发',//Eway.locale.version.taskStatus.downloaded
				downloadedFail:'下发失败',//Eway.locale.version.taskStatus.downloadedFail
				deployed:'正在部署',//Eway.locale.version.taskStatus.deployed
				deployedWait:'等待部署',//Eway.locale.version.taskStatus.deployedWait
				deployedFail:'部署失败',//Eway.locale.version.taskStatus.deployedFail
				checked:'部署已确认',//Eway.locale.version.taskStatus.checked
				noticeOk:'已通知应用',//Eway.locale.version.taskStatus.noticeOk
				noticeFail:'通知应用失败'//Eway.locale.version.taskStatus.noticeFail
			},
			versionCatalog:{
				app:'Application',//Eway.locale.version.versionCatalog.app 应用程序
				agent:'Monitor agent',//Eway.locale.version.versionCatalog.agent 监控代理
				param:'Application settings'//Eway.locale.version.versionCatalog.param 应用配置
			},
			download:{
				title:'分发监控',//Eway.locale.version.download.title
				updateTitle:'修改版本下发信息',//Eway.locale.version.download.updateTitle
				taskQueryTips:'根据条件查询选中作业下的详情信息',//Eway.locale.version.download.taskQueryTips
				autoRefresh:'开启自动刷新',//Eway.locale.version.download.autoRefresh
				stopAutoRefresh:'停止自动刷新',//Eway.locale.version.download.stopAutoRefresh
				autoRefreshTips:'刷新周期60秒',//Eway.locale.version.download.autoRefreshTips
				taskExportTips:'导出选中作业下的全部下发结果'//Eway.locale.version.download.taskExportTips
			},
			task:{
				selectJobStartRefresh:'请选择一个作业,再开启定时刷新！',//Eway.locale.version.task.selectJobStartRefresh
				jobBatchName:'作业批次名称',//Eway.locale.version.task.jobBatchName
				patchVersion:'分发版本',//Eway.locale.version.task.patchVersion
				taskStatus:'任务状态',//Eway.locale.version.task.taskStatus
				beforeUpdate:'分发前的版本',//Eway.locale.version.task.beforeUpdate
				exceptVersion:'预期版本',//Eway.locale.version.task.exceptVersion
				actionTime:'执行时间',//Eway.locale.version.task.actionTime
				downSource:'下载源',//Eway.locale.version.task.downSource
				planTime:'计划时间',//Eway.locale.version.task.planTime
				excuteMachine:'执行服务器',//Eway.locale.version.task.excuteMachine
				restartATM:'重启ATM',//Eway.locale.version.task.restartATM
				restartATMTips:'执行重启命令可能存在风险,确认重启?',//Eway.locale.version.task.restartATMTips
				sendRestartCmd:'已发送重启命令！',//Eway.locale.version.task.sendRestartCmd
				cancelDownloadSuccess:'取消下发成功！',//Eway.locale.version.task.cancelDownloadSuccess
				cancelDownload:'取消下发',//Eway.locale.version.task.cancelDownload
				jobName:'作业名称',//Eway.locale.version.task.jobName
				jobStatus:'作业状态',//Eway.locale.version.task.jobStatus
				chooseTitleDevice:'选择设备',//Eway.locale.version.task.chooseTitleDevice
				closeWindow:'关闭窗口',//Eway.locale.version.task.closeWindow
				queryByFilter:'根据条件查找',//Eway.locale.version.task.queryByFilter
				displayNumPerPage:'每页显示条数',//Eway.locale.version.task.displayNumPerPage
				targetVersionNo:'目标版本',//Eway.locale.version.task.targetVersionNo
				downloadStatus:'下发状态',//Eway.locale.version.task.downloadStatus
				downloadResult:'下发结果',//Eway.locale.version.task.downloadResult
				cancelJob:'下发结果',//Eway.locale.version.task.cancelJob
				jobId:'作业ID',	//Eway.locale.version.task.jobId
				selectDownloadDevice:'选择下发的设备',	//Eway.locale.version.task.selectDownloadDevice
				versionNoBeforeUpdate:'升级前版本号',	//Eway.locale.version.task.versionNoBeforeUpdate
				versionNoAfterUpdate:'目标版本号',	//Eway.locale.version.task.versionNoAfterUpdate
				deviceVersionHis:'查看设备历史版本',	//Eway.locale.version.task.deviceVersionHis
				downloadUser:'下发人',	//Eway.locale.version.task.downloadUser
				downloadTime:'下发时间',	//Eway.locale.version.task.downloadTime
				downloadResult:'下发结果',	//Eway.locale.version.task.downloadResult
				deviceVersionHisTitle:'设备历史版本信息',	//Eway.locale.version.task.deviceVersionHisTitle
				deviceVersions:'设备版本',	//Eway.locale.version.task.deviceVersions
				deviceVersionHisTip:'查看设备历史版本信息',	//Eway.locale.version.task.deviceVersionHisTip
				autoUpdateInfo:'自动更新信息',//Eway.locale.version.task.autoUpdateInfo
				selectAJob:'请选择一个作业.',//Eway.locale.version.task.selectAJob
				versionDownHisStatusPic:'版本下发历史状态分布图',//Eway.locale.version.task.versionDownHisStatusPic
				cantCancelCompleteJob:'不能撤销"完成"状态的作业.',//Eway.locale.version.task.cantCancelCompleteJob
				doSureCancelTheJob:'是否真的要撤销指定的作业?(正在运行的作业只会撤销还没有运行的任务.)',//Eway.locale.version.task.doSureCancelTheJob
				deleting:'正在删除......',//Eway.locale.version.task.deleting
				cancelSuccessBut:'已经成功撤销作业中还没有运行的任务,此时作业的状态仍然是"运行中",请稍等后刷新作业列表.',//Eway.locale.version.task.cancelSuccessBut
				cancelJobSuccess:'成功撤销作业',//Eway.locale.version.task.cancelJobSuccess
				updateResult:'升级结果'	//Eway.locale.version.task.updateResult
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
					loading:'DownLoad',//Eway.locale.agent.remote.screen.loading 下载
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
				publisher: '发布商',
				diskUsed: 'Disk useage',//磁盘使用
				softwayList: 'List of software installed',//软件安装列表
				networkInfo: 'Network info',//网络信息
				networkLinkStatus: 'Netwok connect status',//网络连接状态
				send: 'Sended',//已发送
				receive: 'Received',//已接收
				bite: 'byte:',//字节
				speed: 'Speed(Mbps):',//速度
				bandWidth: '宽带测速',
				unit: '单位：MB',//
				againTest: 'Test again',//重新测试
				impressionName: '印象名称',
				userName: 'User name',//用户名
				memoryRate: 'Memory used',//内存使用
				systemProgressInfo:'系统进程信息',//系统进程信息
				screenShotTime: 'Screen shot time',//截屏时间
				distanceScreen: '远程抓屏',//远程抓屏
				distanceExplorer: '远程浏览',
				ATMExamination:'ATM体检',
				checkATM: '重新体检',
				ATMExamInfo: 'ATM体检详情',
				cpuIdle: 'Free rate of CPU',//CPU空闲率
				memoryIdle: 'Free rate of memory',//内存空闲率
				hardDiskIdle: 'Free rate of disk',//硬盘空闲率
				uploadFile: 'Upload file',//上传文件
				rules:'Can not contain:\/?*":<>|',//不能包含一下字符
				nowCreat:'Creating...',//正在新建
				nowPath: '当前路径',//当前路径
				confirm: 'Save', //保存
				reset: 'Reset',//重置
				back: 'Back',//返回
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
				cancel: "Cancel", //取消
				choseFile:'未选择上传文件,请选择文件.',
				returnFailure:'返回失败.',
				refreshFailure:'刷新失败.',
				catalogExist:'搜索的目录不存在,请重新输入.',
				testBandWidth:'测试宽带异常.',
				networkMaxSpeed:'网络最大接入速度为',
				minutes:'Second',//秒
				amount:'Like',//相当于
				specialLine:'专线',
				bandWidth:'宽带',
				handle:'Processing......',//正在处理......
				offServer:'Lost connection from server.',//与服务器断开连接
				ATMCheck:'正在ATM体检中...',
				excellent:'good',//优
				fine:'良',//
				middle:'中',
				bad:'bad',//差
				point:'',//分
				checkFailure:'ATM体检失败,请重新操作.',
				checkVersionInfo:'查看版本信息',
				versionInfo:'您要查看的版本信息如下:',
				ATMCVersion:'ATMC应用版本',
				monitorVersion: '监控客户端版本',
				
			}
		},
		commen:{
			
			jobNum:'Job number',//Eway.locale.commen.jobNum 工号
			name:'name',//Eway.locale.commen.name 姓名
			personJobName:'Job name',//Eway.locale.commen.personJobName 岗位
			state:'status',//Eway.locale.commen.state 状态
			birthday:'Birthday',//Eway.locale.commen.birthday 生日
			comboxStatus:{
				onJob:'On job',//Eway.locale.commen.comboxStatus.onJob 在岗
				onAdjust:'On adjust',//Eway.locale.commen.comboxStatus.onAdjust 调休
				onVacation:'On vacation',//Eway.locale.commen.comboxStatus.onVacation 休假
				other:'other',//Eway.locale.commen.comboxStatus.other 其他
				dredge:'Dredge',//Eway.locale.commen.comboxStatus.dredge 开通
				open:'Open',//Eway.locale.commen.comboxStatus.open 启用
				close:'Close',//Eway.locale.commen.comboxStatus.close 停用
			},
			type:'Type',//Eway.locale.commen.type 类型
			comboxType:{
				machineManager:'Machine manager',//Eway.locale.commen.comboxType.machineManager 管机员
				machineRepairer:'Machine repairer'//Eway.locale.commen.comboxType.machineRepairer 维修人员
			},
			mobile:'Mobile',//Eway.locale.commen.mobile 手机
			email:'Email',//Eway.locale.commen.email 邮箱
			phone:'Phone',//Eway.locale.commen.phone 固话
			gender:'Gender',//Eway.locale.commen.gender 性别
			all:'All',//Eway.locale.commen.all 全部
			comboxGender:{
				male:'Male',//Eway.locale.commen.comboxGender.male 男
				female:'Female',//Eway.locale.commen.comboxGender.female 女
				unknow:'Unknow'//Eway.locale.commen.comboxGender.unknow 未知
			},
			remark:'Remark',//Eway.locale.commen.remark 备注
			terminalId:'Terminal serialno',//Eway.locale.commen.terminalId 设备号
			ip:'IP address',//Eway.locale.commen.ip 网络地址
			orgNameBelongs:'Organization belongs',//Eway.locale.commen.orgNameBelongs 所属机构
			devTypeName:'Deivce type',//Eway.locale.commen.devTypeName 设备型号
			devVendorName:'Deivce vendor',//Eway.locale.commen.devVendorName 设备品牌
			devCatalogName:'Device catalog',//Eway.locale.commen.devCatalogName 设备类型
			devStatus:'Device status',//Eway.locale.commen.devStatus 设备状态
			comboxDevStatus:{
				open:'Open',//Eway.locale.commen.comboxDevStatus.open 开通
				stop:'Stop'//Eway.locale.commen.comboxDevStatus.stop 停用
			},
			setManager:'Set',//Eway.locale.commen.setManager 设置
			devServiceName:'设备维护商',//Eway.locale.commen.devServiceName
			cashboxLimit:'钞箱报警金额',//Eway.locale.commen.cashboxLimit
			installDate:'Install Date',//Eway.locale.commen.installDate 安装日期
			address:'Address',//Eway.locale.commen.address 地址
			areaCode:'Area code',//Eway.locale.commen.areaCode 区域编号
			areaName:'Area name',//Eway.locale.commen.areaName 区域名称
			toolbar:'Total：{2},display{0}-{1}',//Eway.locale.commen.toolbar 总共：{2}条，显示{0}-{1}
			bindMachine :'Device binded',//Eway.locale.commen.bindMachine 已关联的设备
			lift:'Lift',//Eway.locale.commen.lift 解除
			canBindMachine:'Device can be binded',//Eway.locale.commen.canBindMachine 可关联的设备
			bind:'Bind',//Eway.locale.commen.bind 关联
			filter:'Filter',//Eway.locale.commen.filter 过滤条件
			stateDict:{
				newCreate:'new',//Eway.locale.commen.stateDict.newCreate 新建
				normal:'Normal',//Eway.locale.commen.stateDict.normal 正常
				locked:'Locked',//Eway.locale.commen.stateDict.locked 锁定
				disable:'Disable',//Eway.locale.commen.stateDict.disable 无效
				frozen:'Frozen',//Eway.locale.commen.stateDict.frozen 冻结
				deleted:'Deleted'//Eway.locale.commen.stateDict.deleted 已删除
			},
			yes:'yes',//Eway.locale.commen.yes 是
			no:'no',//Eway.locale.commen.no 否
			selectAll:'Select all',//Eway.locale.commen.selectAll 全部选择
			selectNon:'select non',//Eway.locale.commen.selectNon 全部不选
			content:'Content',//Eway.locale.commen.content 消息
			upgrade:'Upgrade',//Eway.locale.commen.upgrade 上级
			port:'port',//Eway.locale.commen.port 网络Port
			previous:'Previous',//Eway.locale.commen.previous 上一页
			next:'next',//Eway.locale.commen.next 下一页
			installAddr:'Install address',//Eway.locale.commen.installAddr 装机地址
			seviceMode:'Service mode',//Eway.locale.commen.seviceMode 经营方式
			insideOutside:'在行标志',//Eway.locale.commen.insideOutside
			appVersion:'Application version',//Eway.locale.commen.appVersion 应用版本号
			devInfo:'Device basic info',//Eway.locale.commen.devInfo 设备基本信息
			//check end
			personnel:'Contacter',//Eway.locale.commen.personnel 联系人
			warn:'Warn',//Eway.locale.commen.warn 警告
			fatal:'Fatal',//Eway.locale.commen.fatal 故障
			unStable:'UnStable',//Eway.locale.commen.unStable 不稳定
			unknow:'Unknow',//Eway.locale.commen.unknow 未知
			description:'Description',//Eway.locale.commen.description 描述
			info:'Detail',//Eway.locale.commen.info 详细信息
			startDataTime:'Start time',//Eway.locale.commen.startDataTime 开始时间
			endDataTime:'End time',//Eway.locale.commen.endDataTime 结束时间
			year:'Year',//Eway.locale.commen.year 年
			month:'Month',//Eway.locale.commen.month 月
			day:'Day',//Eway.locale.commen.day 日
			yearTime:'Year',//Eway.locale.commen.yearTime 年份
			monthTime:'Month',//Eway.locale.commen.monthTime 月份
			dayTime:'Day',//Eway.locale.commen.dayTime 日期
			orgFramework:'组织机构',//Eway.locale.commen.orgFramework
			matchOrg:'匹配机构',//Eway.locale.commen.matchOrg
			orgID:'Organization ID',//Eway.locale.commen.orgID 机构ID
			endValidty:'截止有效期',//Eway.locale.commen.endValidty
			publishDate:'发布日期',//Eway.locale.commen.publishDate
			announceTheme:'公告主题',//Eway.locale.commen.announceTheme
			
			
			
		},
		//**********************************************************/
		person:{
			bankOrg :{
				title:'银行机构管理',//Eway.locale.person.bankOrg.title
				moveBankTitle:'Migrate organization',//Eway.locale.person.bankOrg.moveBankTitle 组织迁移
				updateBankTitle:'Modify organization info',//Eway.locale.person.bankOrg.updateBankTitle 更改银行机构信息
				addBankOrgTitle:'Add organization info',//Eway.locale.person.bankOrg.addBankOrgTitle 增加银行机构信息
				code:'Organization code',//Eway.locale.person.bankOrg.code 机构编号
				orgType:'Organization type',//Eway.locale.person.bankOrg.orgType 机构类型
				name:'Organization name',//Eway.locale.person.bankOrg.name 机构名称
				orgLevel:'Organization level',//Eway.locale.person.bankOrg.orgLevel 机构级别
				orgNavi:'Organization navigator',//Eway.locale.person.bankOrg.orgNavi 机构导航
				zip:'Zip',//Eway.locale.person.bankOrg.zip 邮政编码
				removeManager:'Remove',//Eway.locale.person.bankOrg.removeManager 删除
				manager:'Manager',//Eway.locale.person.bankOrg.manager 管理员
				address:'Organization address',//Eway.locale.person.bankOrg.address 机构地址
				upgradeOrg:'Organization upgrade',//Eway.locale.person.bankOrg.upgradeOrg 上级机构
				description:'Organization description',//Eway.locale.person.bankOrg.description 机构描述
				serOrganization:'维护商描述',//Eway.locale.person.bankOrg.serOrganization
				organizationType:{
					bank:'Bank',//Eway.locale.person.bankOrg.organizationType.bank 银行
					serviceOrg:'维护商'//Eway.locale.person.bankOrg.organizationType.serviceOrg
				},
				organizationLevelDict:{
					rootBank:'Root bank',//Eway.locale.person.bankOrg.organizationLevelDict.rootBank 总行
					branchBank:'分行',//Eway.locale.person.bankOrg.organizationLevelDict.branchBank
					tagBranchBank:'支行',//Eway.locale.person.bankOrg.organizationLevelDict.tagBranchBank
					netBank:'网点'//Eway.locale.person.bankOrg.organizationLevelDict.netBank
				},
				addOrgTitle:'该机构人员信息',//Eway.locale.person.bankOrg.addOrgTitle
				linkPeronTitle:'当前机构下人员：',//Eway.locale.person.bankOrg.linkPeronTitle
				personList:'人员列表',//Eway.locale.person.bankOrg.personList
				orgLinkTitle:'该机构的关联设备和人员',//Eway.locale.person.bankOrg.orgLinkTitle
				machineOrg:' 该机构下设备',//Eway.locale.person.bankOrg.machineOrg
				personOrg:'该机构下人员'//Eway.locale.person.bankOrg.personOrg
				
			},
			bankPer :{
				title:'银行人员管理',//Eway.locale.person.bankPer.title
				organizationName:'机构',//Eway.locale.person.bankPer.organizationName
				orgNavi:'机构导航',//Eway.locale.person.bankPer.orgNavi
				addBankPerTitle:'增加银行人员信息',//Eway.locale.person.bankPer.addBankPerTitle
				updateBankPerTitle:'更改银行人员信息'//Eway.locale.person.bankPer.updateBankPerTitle
				
			},
			serviceOrg:{
				title:'维护商管理',//Eway.locale.person.serviceOrg.title
				serviceNavi:'厂商导航',//Eway.locale.person.serviceOrg.serviceNavi
				serviceOrgAdmin:'管理员',//Eway.locale.person.serviceOrg.serviceOrgAdmin
				setManager:'设置',//Eway.locale.person.serviceOrg.setManager
				removeManager:'删除',//Eway.locale.person.serviceOrg.removeManager
				code:'厂商编号',//Eway.locale.person.serviceOrg.code
				name:'厂商名称',//Eway.locale.person.serviceOrg.name
				zip:'邮政编码',//Eway.locale.person.serviceOrg.zip
				shortName:'厂商',//Eway.locale.person.serviceOrg.shortName
				address:'厂商地址',//Eway.locale.person.serviceOrg.address
				description:'厂商描述',//Eway.locale.person.serviceOrg.description
				addServiceTitle:'增加维护商信息',//Eway.locale.person.serviceOrg.addServiceTitle
				upgradeService:'上级厂商',//Eway.locale.person.serviceOrg.upgradeService
				updateServiceTitle:'更改维护商信息',//Eway.locale.person.serviceOrg.updateServiceTitle
				personDevSerLink:'该厂商的关联设备和人员',//Eway.locale.person.serviceOrg.personDevSerLink
				devSerLink:'该厂商下设备',//Eway.locale.person.serviceOrg.devSerLink
				personSerLink:'该厂商下人员',//Eway.locale.person.serviceOrg.personSerLink

				directOrganization:'的直接下级机构'
			},
			servicePer:{
				title:'维护人员管理',//Eway.locale.person.servicePer.title
				servicePerlink:'关联设备',//Eway.locale.person.servicePer.servicePerlink
				addServicePerTitle:'增加维护人员信息',//Eway.locale.person.servicePer.addServicePerTitle
				updateServicePerTitle:'更改维护人员信息',//Eway.locale.person.servicePer.updateServicePerTitle

				maintainInfo:'所有维护商人员信息'
			},
			user:{
				title:'User',//Eway.locale.person.user.title 用户管理
				code:'User name',//Eway.locale.person.user.code 用户名
				clickToCheckLog:'Click to check',//Eway.locale.person.user.clickToCheckLog 单击即可查看用户
				userLog:' s log',//Eway.locale.person.user.userLog  的操作日志
				resetPasswd:'Reset password',//Eway.locale.person.user.resetPasswd 密码重置
				clickToPasswdInit:'Click to set the password to initialize',//Eway.locale.person.user.clickToPasswdInit 单击即可密码重置为初始化密码
				clickToRole:'Click to check all roles list',//Eway.locale.person.user.clickToRole 单击即可查看所有角色列表
				addUserTitle:'Add user infomation',//Eway.locale.person.user.addUserTitle 增加用户信息
				clickToUser:'Please click query ,choose a  person',//Eway.locale.person.user.clickToUser 请点击查询，选择人员
				userType:'User type',//Eway.locale.person.user.userType 用户类型
				role:'Role',//Eway.locale.person.user.role 角色
				roleGiven:'角色赋予',//Eway.locale.person.user.roleGiven
				roleName:'Role name',//Eway.locale.person.user.roleName 角色名称
				roleDescription:'Role description',//Eway.locale.person.user.roleDescription 角色描述
				userListTitle:'User list',//Eway.locale.person.user.userListTitle 人员列表
				updateUserTitle:'更改用户角色（使用拖拽的方式）',//Eway.locale.person.user.updateUserTitle
				roleCanBeAdd:'可添加的角色列表',//Eway.locale.person.user.roleCanBeAdd
				roleAlreadyBeAdd:'可添加的角色列表',//Eway.locale.person.user.roleAlreadyBeAdd
				operCode:'Operator no',//Eway.locale.person.user.operCode 操作人编号
				operName:'Operator name',//Eway.locale.person.user.operName 操作人姓名
				operTime:'Operate time',//Eway.locale.person.user.operTime 操作时间
				operResult:'Operate result',//Eway.locale.person.user.operResult 操作结果
				operContent:'Operate content',//Eway.locale.person.user.operContent 操作内容
				roleListTitle:'User roles list',//Eway.locale.person.user.roleListTitle 用户角色列表
				operTitle:'Operator log',//Eway.locale.person.user.operTitle 操作员日志
				operDetailTitle:'操作日志信息',//Eway.locale.person.user.operDetailTitle
				operLogList:'操作日志列表',//Eway.locale.person.user.operLogList
				personDevice:'User<-->Device',//Eway.locale.person.user.personDevice 人员<-->设备
				rootUser:'Super user',//Eway.locale.person.user.rootUser 超级用户
				generalUser:'General User'//Eway.locale.person.user.generalUser 普通用户
			}
			
		},
		
		//**********************************************************/
		permission:{
			systemMenu:'System', //系统菜单
			role:{
				title:'Role',//Eway.locale.permission.role.title 角色管理
				update:'Update role',//Eway.locale.permission.role.update 更改角色
				name:'Role name',//Eway.locale.permission.role.name 角色名称
				type:'Role type',//Eway.locale.permission.role.type 角色类型
				description:'Role description',//Eway.locale.permission.role.description 角色描述
				isSysRole:'Is system role or not',//Eway.locale.permission.role.isSysRole 是否是系统内置角色
				chooseRight:'请选择菜单权限',//Eway.locale.permission.role.chooseRight
				add:'Add role'//Eway.locale.permission.role.add 增加角色
			},
			permission:{
				menuName:'Menu name',//Eway.locale.permission.permission.menuName 菜单名称
				menuDescription:'Menu description',//Eway.locale.permission.permission.menuDescription 菜单描述
				menuPermission:'Menu permission'//Eway.locale.permission.permission.menuPermission 菜单权限
			}
		},
		
		//**********************************************************/
		monitor:{
			devMonitor:{
				title:'Status',//Eway.locale.monitor.devMonitor.title 状态监控
				comboxStatus:{
					runStatus:'Run status',//Eway.locale.monitor.devMonitor.comboxStatus.runStatus 运行状态
					modStatus:'Module status',//Eway.locale.monitor.devMonitor.comboxStatus.modStatus 模块状态
					boxStatus:'Cashbin status',//Eway.locale.monitor.devMonitor.comboxStatus.boxStatus 钞箱状态
					netStatus:'Net status'//Eway.locale.monitor.devMonitor.comboxStatus.netStatus 网络状态
				},
				monitorState:'监控状态',//Eway.locale.monitor.devMonitor.monitorState
				showWay:'Show way',//Eway.locale.monitor.devMonitor.showWay 展示方式
				comboxShowWay:{
					matrixPattern:'Matrix pattern',//Eway.locale.monitor.devMonitor.comboxShowWay.matrixPattern 矩形方式
					maxIconPattern:'MaxIcon pattern',//Eway.locale.monitor.devMonitor.comboxShowWay.maxIconPattern 超大图标
					listPattern:'List pattern',//Eway.locale.monitor.devMonitor.comboxShowWay.listPattern 列表方式
					boxPattern:'Cashbin pattern'//Eway.locale.monitor.devMonitor.comboxShowWay.boxPattern 钞箱方式
				},
				numberfield:'Number of monitor machine',//Eway.locale.monitor.devMonitor.numberfield 监控台数
				noData:'No data',//Eway.locale.monitor.devMonitor.noData 无记录
				retainCardCount:'Retain card count',//Eway.locale.monitor.devMonitor.retainCardCount 当前吞卡数量
				cash:{
					boxInitCount:'钞箱初始金额',//Eway.locale.monitor.devMonitor.cash.boxInitCount
					boxCurrentCount:'钞箱当前金额',//Eway.locale.monitor.devMonitor.cash.boxCurrentCount
					cashboxLimit:'钞箱报警金额阈值',//Eway.locale.monitor.devMonitor.cash.cashboxLimit
					initAmount:'加钞总金额',//Eway.locale.monitor.devMonitor.cash.initAmount
					amount:'剩钞金额',//Eway.locale.monitor.devMonitor.cash.amount
					dispenseAmount:'出钞总金额',//Eway.locale.monitor.devMonitor.cash.dispenseAmount
					rejectAmount:'废钞金额',//Eway.locale.monitor.devMonitor.cash.rejectAmount
					retractCount:'钞票回收次数',//Eway.locale.monitor.devMonitor.cash.retractCount
					minAmount:'最小取款金额',//Eway.locale.monitor.devMonitor.cash.minAmount
					boxId:'钞箱标识',//Eway.locale.monitor.devMonitor.cash.boxId
					type:'钞箱类型',//Eway.locale.monitor.devMonitor.cash.type
					initialCount:'初始张数',//Eway.locale.monitor.devMonitor.cash.initialCount
					cashInCount:'存款张数',//Eway.locale.monitor.devMonitor.cash.cashInCount
					currentCount:'当前计数[张/笔]',//Eway.locale.monitor.devMonitor.cash.currentCount
					noteValue:'钞箱面值',//Eway.locale.monitor.devMonitor.cash.noteValue
					currency:'钞箱币种',//Eway.locale.monitor.devMonitor.cash.currency
					boxDetail:'钞箱详情',//Eway.locale.monitor.devMonitor.cash.boxDetail
					cimFull:'存款钞满',//Eway.locale.monitor.devMonitor.cash.cimFull
					cdmEmpty:'取款钞空',//Eway.locale.monitor.devMonitor.cash.cdmEmpty
					cdmLow:'取款钞少',//Eway.locale.monitor.devMonitor.cash.cdmLow
					low:'钞少',//Eway.locale.monitor.devMonitor.cash.low
					empty:'钞空',//Eway.locale.monitor.devMonitor.cash.empty
					cimAFull:'存款钞将满',//Eway.locale.monitor.devMonitor.cash.cimAFull
					cashFault:'钞箱故障',//Eway.locale.monitor.devMonitor.cash.cashFault
					cashUnknow:'钞箱未知'//Eway.locale.monitor.devMonitor.cash.cashUnknow
					
				},
				modStateGraphic:'模块状态图示',//Eway.locale.monitor.devMonitor.modStateGraphic
				modGraphic:'模块图示',//Eway.locale.monitor.devMonitor.modGraphic
				registerStatus:'注册状态',//Eway.locale.monitor.devMonitor.registerStatus
				devModStatus:'设备模块状态',//Eway.locale.monitor.devMonitor.devModStatus
				mod:{
					idc:'Card reader',//Eway.locale.monitor.devMonitor.mod.idc 读卡器
					jpr:'Log printer',//Eway.locale.monitor.devMonitor.mod.jpr 日志打印机
					cdm:'CDM module',//Eway.locale.monitor.devMonitor.mod.cdm 取款模块
					cim:'CIM module',//Eway.locale.monitor.devMonitor.mod.cim 存款模块
					siu:'传感器',//Eway.locale.monitor.devMonitor.mod.siu 传感器
					rpr:'凭条打印机',//Eway.locale.monitor.devMonitor.mod.rpr 凭条打印机
					pin:'Pin keyboard',//Eway.locale.monitor.devMonitor.mod.pin 密码键盘
					ttu:'文本终端',//Eway.locale.monitor.devMonitor.mod.ttu 文本终端
					isc:'身份证扫描仪',//Eway.locale.monitor.devMonitor.mod.isc 身份证扫描仪
					icc:'发卡器',//Eway.locale.monitor.devMonitor.mod.icc 发卡器
					fgp:'指纹仪',//Eway.locale.monitor.devMonitor.mod.fgp 指纹仪
					healthy:'Healthy'//Eway.locale.monitor.devMonitor.mod.healthy 模块正常
					
				},
				remote:{
					control:'远程控制',//Eway.locale.monitor.devMonitor.remote.control
					screen:'远程抓屏',//Eway.locale.monitor.devMonitor.remote.screen
					log:'提取电子日志',//Eway.locale.monitor.devMonitor.remote.log
					net:'查看网络连接',//Eway.locale.monitor.devMonitor.remote.net
					softwareList:'获取软件列表',//Eway.locale.monitor.devMonitor.remote.softwareList
					powerOff:'关机',//Eway.locale.monitor.devMonitor.remote.powerOff
					closeWays:'请选择关机方式',//Eway.locale.monitor.devMonitor.remote.closeWays
					restart:'重启',//Eway.locale.monitor.devMonitor.remote.restart
					restartWay:'请选择重启方式',//Eway.locale.monitor.devMonitor.remote.restartWay
					logicOpen:'开启服务',//Eway.locale.monitor.devMonitor.remote.logicOpen
					logicClose:'暂停服务',//Eway.locale.monitor.devMonitor.remote.logicClose
					remoteBrowser:'远程浏览',//Eway.locale.monitor.devMonitor.remote.remoteBrowser
					processList:'查看进程信息',//Eway.locale.monitor.devMonitor.remote.processList
					screenCamera:'屏幕录制',//Eway.locale.monitor.devMonitor.remote.screenCamera
					reset:'强制复位',//Eway.locale.monitor.devMonitor.remote.reset
					remoteLook:'查看应用版本',//Eway.locale.monitor.devMonitor.remote.remoteLook
					remoteCheckATM:'ATM体检',//Eway.locale.monitor.devMonitor.remote.remoteCheckATM
					halfSer:'半功能',//Eway.locale.monitor.devMonitor.remote.halfSer
					healthy:'正常服务',//Eway.locale.monitor.devMonitor.remote.healthy
					staff:'维护',//Eway.locale.monitor.devMonitor.remote.staff
					pFault:'交易前置故障',//Eway.locale.monitor.devMonitor.remote.pFault
					stop:'报停',//Eway.locale.monitor.devMonitor.remote.stop
					manualStop:'人工报停',////Eway.locale.monitor.devMonitor.remote.manualStop
					stopFault:'暂停服务-模块故障',//Eway.locale.monitor.devMonitor.remote.stopFault
					stopCash:'暂停服务-未加钞',//Eway.locale.monitor.devMonitor.remote.stopCash
					pauseSer:'暂停服务',//Eway.locale.monitor.devMonitor.remote.pauseSer
					pauseCash:'现金暂停',//Eway.locale.monitor.devMonitor.remote.pauseCash   ----中文不明，我猜的----
					pauseSerUnknow:'未知原因暂停服务',//Eway.locale.monitor.devMonitor.remote.pauseSerUnknow
					manaAndstaff:'管机员'//Eway.locale.monitor.devMonitor.remote.manaAndstaff
				},
				atmGroup:'分组',//Eway.locale.monitor.devMonitor.atmGroup
				solution:'建议解决方案',//Eway.locale.monitor.devMonitor.solution
				faultDescription:'模块故障描述',//Eway.locale.monitor.devMonitor.faultDescription
				fastChoose:'快捷选择',//Eway.locale.monitor.devMonitor.fastChoose
				init:'初始化',//Eway.locale.monitor.devMonitor.init
				accTrans:'客户交易',//Eway.locale.monitor.devMonitor.accTrans
				factureStaff:'厂商模式维护',//Eway.locale.monitor.devMonitor.factureStaff
				netHealthy:'网络正常',//Eway.locale.monitor.devMonitor.netHealthy
				netUnStable:'网络不稳定',//Eway.locale.monitor.devMonitor.netUnStable
				netFatal:'网络故障'//Eway.locale.monitor.devMonitor.netFatal
			},
			business:{
				transaction:{
					card:'交易卡号',//Eway.locale.monitor.business.transaction.card
					dateTime:'交易时间',//Eway.locale.monitor.business.transaction.dateTime
					transCode:'交易类型',//Eway.locale.monitor.business.transaction.transCode
					amt:'交易金额',//Eway.locale.monitor.business.transaction.amt
					currency:'交易币种',//Eway.locale.monitor.business.transaction.currency
					transId:'交易流水号',//Eway.locale.monitor.business.transaction.transId
					amtfield:'金额范围',//Eway.locale.monitor.business.transaction.amtfield
					toNum:'至',//Eway.locale.monitor.business.transaction.toNum
					transContainer:'交易时间段',//Eway.locale.monitor.business.transaction.transContainer
					debitAccountOrCard:'客户账号或者卡号',//Eway.locale.monitor.business.transaction.debitAccountOrCard
					creditAccountOrCard:'对方账号或者卡号',//Eway.locale.monitor.business.transaction.creditAccountOrCard
					debitAccount:'客户帐号',//Eway.locale.monitor.business.transaction.debitAccount
					creditAccount:'对方账号',//Eway.locale.monitor.business.transaction.creditAccount
					localRet:'ATMC本地代码',//Eway.locale.monitor.business.transaction.localRet
					hostRet:'主机返回码',//Eway.locale.monitor.business.transaction.hostRet
					userName:'用户姓名',//Eway.locale.monitor.business.transaction.userName
					historyTransaction:{
						title:'历史交易查询'//Eway.locale.monitor.business.transaction.historyTransaction.title
						
					},
					transactionMonitor:{
						title:'实时交易监控',//Eway.locale.monitor.business.transaction.transactionMonitor.title
						begin:'开始监控',//Eway.locale.monitor.business.transaction.transactionMonitor.begin
						stop:'停止监控'//Eway.locale.monitor.business.transaction.transactionMonitor.stop
						}
				},
				blackList:{
					title:'黑名单卡管理',//Eway.locale.monitor.business.blackList.title
					black:'黑名单卡',//Eway.locale.monitor.business.blackList.black
					addBlack:'添加黑名单卡',//Eway.locale.monitor.business.blackList.addBlack
					cardBank:'所属银行',//Eway.locale.monitor.business.blackList.cardBank
					importData:'批量导入',//Eway.locale.monitor.business.blackList.importData
					addDate:'添加日期',//Eway.locale.monitor.business.blackList.addDate
					importTitle:'批量导入黑名单卡',//Eway.locale.monitor.business.blackList.importTitle
					importFile:'导入文件',//Eway.locale.monitor.business.blackList.importFile
					chooseFileRegex:'请选择导入文件,只支持.xls和.xlsx格式的文件',//Eway.locale.monitor.business.blackList.chooseFileRegex
					fileRegex:'只支持.xls和.xlsx格式的文件',//Eway.locale.monitor.business.blackList.fileRegex
					tempDownload:'模版下载',//Eway.locale.monitor.business.blackList.tempDownload
					importNow:'导入',//Eway.locale.monitor.business.blackList.importNow
					updateTitle:'更改黑名单卡信息'//Eway.locale.monitor.business.blackList.updateTitle
				},
				card:{
					title:'吞卡管理',//Eway.locale.monitor.business.card.title
					addTitle:'增加吞卡信息',//Eway.locale.monitor.business.card.addTitle
					time:'吞卡时间',//Eway.locale.monitor.business.card.time
					reason:'吞卡原因',//Eway.locale.monitor.business.card.reason
					destroy:'吞卡销毁',//Eway.locale.monitor.business.card.destory
					cardHolder:'发卡行',//Eway.locale.monitor.business.card.cardHolder
					cardRegex:'允许的最大长度为',//Eway.locale.monitor.business.card.cardRegex
					beginEndDate:'吞卡起始日期不能大于吞卡截止日期,请重新选择',//Eway.locale.monitor.business.card.beginEndDate
					orgBelongs:'所属机构 (包含处理机构)',//Eway.locale.monitor.business.card.orgBelongs
					beginTime:'开始时间',//Eway.locale.monitor.business.card.beginTime
					endTime:'结束时间',//Eway.locale.monitor.business.card.endTime
					accGetCard:'客户领卡',//Eway.locale.monitor.business.card.accGetCard
					transferCard:'卡片移交',//Eway.locale.monitor.business.card.transferCard
					processOrg:'处理机构',//Eway.locale.monitor.business.card.processOrg
					type:'吞卡类型',//Eway.locale.monitor.business.card.type
					manual:'手动添加',//Eway.locale.monitor.business.card.manual
					auto:'自动添加',//Eway.locale.monitor.business.card.auto
					comboxStatus:{
						wait:'待领',//Eway.locale.monitor.business.card.comboxStatus.wait
						received:'已领',//Eway.locale.monitor.business.card.comboxStatus.received
						destroy:'销毁',//Eway.locale.monitor.business.card.comboxStatus.destroy
						bringed:'调出'//Eway.locale.monitor.business.card.comboxStatus.bringed
					},
					treatmentPeople:'处理人员',//Eway.locale.monitor.business.card.treatmentPeople
					treatmentTime:'处理时间',//Eway.locale.monitor.business.card.treatmentTime
					customerName:'客户姓名',//Eway.locale.monitor.business.card.customerName
					customerPhone:'客户电话',//Eway.locale.monitor.business.card.customerPhone
					customerPapers:'客户证件号',//Eway.locale.monitor.business.card.customerPapers
					processCard:'吞卡处理',//Eway.locale.monitor.business.card.processCard
					destroyCard:'卡片销毁',//Eway.locale.monitor.business.card.destroyCard
					exportData:'导出',//Eway.locale.monitor.business.card.exportData
					paperType:'证件类型',//Eway.locale.monitor.business.card.paperType
					paperCode:'证件号',//Eway.locale.monitor.business.card.paperCode
					idCard:'身份证',//Eway.locale.monitor.business.card.idCard
					accountPaper:'户口本',//Eway.locale.monitor.business.card.accountPaper
					drivePaper:'驾驶执照',//Eway.locale.monitor.business.card.drivePaper
					passport:'护照',//Eway.locale.monitor.business.card.passport
					officer:'军官证',//Eway.locale.monitor.business.card.officer
					soldier:'士兵证',//Eway.locale.monitor.business.card.soldier
					busnessPaper:'法人营业证',//Eway.locale.monitor.business.card.busnessPaper
					busnessCode:'法人代码证',//Eway.locale.monitor.business.card.busnessCode
					taxPaper:'税务登记证',//Eway.locale.monitor.business.card.taxPaper
					withDev:'按设备'//Eway.locale.monitor.business.card.withDev
				},
				
				
				cashInit:{
					titile:'加钞信息查询',//Eway.locale.monitor.business.cashInit.titile
					uuId:'加钞ID',//Eway.locale.monitor.business.cashInit.uuId
					date:'加钞日期',//Eway.locale.monitor.business.cashInit.date
					amt:'加钞金额',//Eway.locale.monitor.business.cashInit.amt
					info:'加钞详细信息',//Eway.locale.monitor.business.cashInit.info
					boxId:'钞箱ID',//Eway.locale.monitor.business.cashInit.boxId
					boxCurrency:'币种',//Eway.locale.monitor.business.cashInit.boxCurrency
					boxInitAmt:'初始金额',//Eway.locale.monitor.business.cashInit.boxInitAmt
					lastAmt:'剩余金额'//Eway.locale.monitor.business.cashInit.lastAmt
				},
				settlement:{
					title:'清机信息查询',//Eway.locale.monitor.business.settlement.title
					deTitle:'清机详细信息',//Eway.locale.monitor.business.settlement.deTitle
					settleId:'清机ID',//Eway.locale.monitor.business.settlement.settleId
					uuId:'周期ID',//Eway.locale.monitor.business.settlement.uuId
					endAmt:'尾箱余额',//Eway.locale.monitor.business.settlement.endAmt
					endDate:'结帐日期',//Eway.locale.monitor.business.settlement.endDate
					cimNum:'存款笔数',//Eway.locale.monitor.business.settlement.cimNum
					cdmNum:'取款笔数',//Eway.locale.monitor.business.settlement.cdmNum
					totalNum:'交易总笔数',//Eway.locale.monitor.business.settlement.totalNum
					leftDate:'结账日期',//Eway.locale.monitor.business.settlement.leftDate
					cimAmt:'存款金额',//Eway.locale.monitor.business.settlement.cimAmt
					cdmAmt:'取款金额',//Eway.locale.monitor.business.settlement.cdmAmt
					tranAmt:'交易总金额'//Eway.locale.monitor.business.settlement.tranAmt
				}		
			/*}*/
			}
		},
		
		//**********************************************************/
		machine:{
			atmBrand : {
				title:'Brand',//品牌管理
				name: 'Brand name', //品牌名称
				country:'Country or area of prduction',//生产商国家或地区
				hotline1:'Product hotline',//生产商热线1
				hotline2:'生产商热线2',//生产商热线2
				address:'生产商地址',//生产商地址
				status:'生产商状态',//生产商状态
				comboxStatus:{
					provider:'设备供应',
					maintance:'设备服役'
				}
			},
			atmCatalog:{
				title:'ATM分类',//Eway.locale.machine.title
				name:'分类名称',//Eway.locale.machine.atmCatalog.name
				note:'备注',
				addTitle:'增加ATM分类信息',
				updateTitle:'更改ATM型号信息',
				number:'编号',//Eway.locale.machine.atmCatalog.number
			},
			atmGroup : {
				terminalId:'Terminal number', //设备号
				ip: 'IP address',//设备IP地址
				orgName:'所属机构',
				devTypeName:'Device type',//设备型号
				devVendorName:'Device vendor',//设备品牌
				devCatalogName:'Device catalog',//设备类型
				devGroupName: 'Device group',//设备分组
				status:'Device status',//设备状态
				comboxStatus:{
					dredge:'Dredge',//开通
					open:'Open',//启用
					close:'Close'//close
				},
				awayFlag:'离行标志',
				comboxAwayFlag:{
					inBank:'在行自助服务区',
					outBank:'离行自助银行',
					clickBank:'单机离行自助服务点'
				},
				devServiceName:'设备维护商',
				cashboxLimit:'钞箱报警金额',
				installDate:'Install date',//安装日期
				address:'Address',//地址
				gourpDev:'Group<-->Device',//分组<-->设备
				addTitle: 'Add info of machine-group ',//增加设备组信息
				groupName:'Group name',//组名
				note:'remark',//备注
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
				terminalId:'Terminal number',//设备号
				address:'源地址',
				orgName:'源机构',
				targetAddress:'Target address',//目标地址
				targetOrganization:'Target organization',//目标机构
				targetPerson:'目标机构负责人',//目标机构负责人
				responsibility:'负责人',
				destPerson:'源机构负责人',
				date:'date',//日期
				recoverDate:'Recover date',//恢复时间
				notice:'remark',//备注
				sAddress:'所属地址',
				sOrgName:'所属机构',
				to:'至'
			},
			atmRuntimeInfo:{
				exportName:'Export', //导出
				exportDateRangeText:'Start time can not be later than end time',//开始时间不能大于结束时间
				terminalId:'Terminal number',//终端号
				terminalIp:'IP',//终端IP
				startDate:'Begin time',//开始时间
				endDate:'End time',//结束时间
				exportLast30: '导出最后30天汇总信息',
				terminalId:'number',//编号
				netIp:'Net address',//网络地址
				msgCollect:'客服信息采集'
			},
			atmType:{
				title:'Device type',//设备型号
				atmName:'ATM Type',//ATM型号
				name:'Device type',//设备型号
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

				 
				JPRInfo:'日志打印机模块(JPR)属性信息',
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
				
				devPerson:'设备人员信息',
				devModuleMsg:'设备模块属性信息',
				devBasicMsg:'设备基本信息',
				devTailMsg:'设备详细信息',
				managePerson:'管机员',
				maintainPerson:'维护员',
				name:'姓名',
				mobile:'手机',
				phone:'固定电话',
				email:'邮件地址',
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
				basicInfo:'基本信息',
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

				
					
				devices:'设备',
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
				
			},
			param:{
				paramKey:'参数',//Eway.locale.machine.param.paramKey
				paramValue:'参数值',//Eway.locale.machine.param.paramValue
				classify:'类型',//Eway.locale.machine.param.classify
				comboxClassify:{
					unableUpdate:'不可修改',
					ableUpdate:'可以修改'
				},
				description:'参数信息描述',//Eway.locale.machine.param.description
				systemCon:'系统配置',//Eway.locale.machine.param.systemCon
				updateSystemCon:'更改系统配置'//Eway.locale.machine.param.updateSystemCon
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
			indexPage:'Page index',//Eway.locale.index.indexPage 首页
			dailyFaultPic:'日均故障趋势图',//Eway.locale.index.dailyFaultPic
			faultAmount:' 产生的故障数量: ',//Eway.locale.index.faultAmount
			devStatusDisPic:'设备状态分布图',//Eway.locale.index.devStatusDisPic
			normalDev:'正常设备',//Eway.locale.index.normalDev
			unknownDev:'Device-unkown',//Eway.locale.index.unknownDev
			exceptionDev:'异常设备',//Eway.locale.index.exceptionDev
			amount:'台',//Eway.locale.index.amount
		},
		//**********************************************************/
		report:{
			baseReport:{
				date:'加钞日期',//Eway.locale.report.baseReport.date
				amt:'加钞金额',//Eway.locale.report.baseReport.amt
				boxId:'钞箱ID',//Eway.locale.report.baseReport.boxId
				boxCurrency:'币种',//Eway.locale.report.baseReport.boxCurrency
				boxInitAmt:'初始金额',//Eway.locale.report.baseReport.boxInitAmt
				lastAmt:'剩余金额',//Eway.locale.report.baseReport.lastAmt
				cashAddRep:'加钞情况报表',//Eway.locale.report.baseReport.cashAddRep
				boxBalanceRep:'钞箱余额报表',//Eway.locale.report.baseReport.boxBalanceRep
				sysConfRep:'系统硬件配置报表',//Eway.locale.report.baseReport.sysConfRep
				devDetailRep:'设备明细报表',//Eway.locale.report.baseReport.devDetailRep
				devBrandRep:'设备品牌统计报表',//Eway.locale.report.baseReport.devBrandRep
				devRunInfoRep:'设备运行情况报表',//Eway.locale.report.baseReport.devRunInfoRep
				eatCardRep:'吞卡统计报表',//Eway.locale.report.baseReport.eatCardRep
				eatCardDetailRep:'吞卡明细报表',//Eway.locale.report.baseReport.eatCardDetailRep
				clearDate:'清机日期',//Eway.locale.report.baseReport.clearDate
				clearTable:'清机情况报表',//Eway.locale.report.baseReport.clearTable
				dependDev:'按设备',//Eway.locale.report.baseReport.dependDev
				tradeRep:'交易统计报表',//Eway.locale.report.baseReport.tradeRep
				tradeResultRep:'交易结果统计报表',//Eway.locale.report.baseReport.tradeResultRep
			},
			openrate:{
				device:{
					statisticsMethod:'统计方式',//Eway.locale.report.openrate.device.statisticsMethod
					statistics:'统计',//Eway.locale.report.openrate.device.statistics
					importStat:'导出',//Eway.locale.report.openrate.device.importStat
					statDate:'统计日期',//Eway.locale.report.openrate.device.statDate
					openTimes:'设备应工作时长',//Eway.locale.report.openrate.device.openTimes
					healthyTimeReal:'正常状态时长',//Eway.locale.report.openrate.device.healthyTimeReal
					maintainTimeReal:'管机员维护时长',//Eway.locale.report.openrate.device.maintainTimeReal
					unknownTimeReal:'离线未知时长',//Eway.locale.report.openrate.device.unknownTimeReal
					faultTimeReal:'硬件故障停机时长',//Eway.locale.report.openrate.device.faultTimeReal
					atmpTimeReal:'ATMP故障时长',//Eway.locale.report.openrate.device.atmpTimeReal
					stopTimeReal:'其它暂停服务状态时长',//Eway.locale.report.openrate.device.stopTimeReal
					openRate:'实际工作开机率',//Eway.locale.report.openrate.device.openRate
					devOpenRate:'设备开机率',//Eway.locale.report.openrate.device.devOpenRate
					organizationName:'机构',//Eway.locale.report.openrate.device.organizationName
				},
				org:{
					orgOpenRate:'机构开机率',//Eway.locale.report.openrate.org.orgOpenRate
				},
				type:{
					terminalId:'型号',//Eway.locale.report.openrate.type.terminalId
					typeOpenRate:'型号开机率',//Eway.locale.report.openrate.type.typeOpenRate
				},
			},
			plan:{
				addPlan:'增加方案',//Eway.locale.report.plan.addPlan
				name:'名称',//Eway.locale.report.plan.name
				startDate:'有效开始时间',//Eway.locale.report.plan.startDate
				endDate:'有效结束时间',//Eway.locale.report.plan.endDate
				terminalId:'编号',//Eway.locale.report.plan.terminalId
				cashboxLimit:'钞箱报警金额(单位：张数)',//Eway.locale.report.plan.cashboxLimit
				perToDev:'人员<-->设备',//Eway.locale.report.plan.perToDev
				changePlan:'更改方案',//Eway.locale.report.plan.changePlan
				openPlan:'开机方案',//Eway.locale.report.plan.openPlan
			}
		},
		
		//**********************************************************/
		card:{
			cardNum:'卡号',//Eway.locale.card.cardNum
			onlyNumber:'只能输入数字,13-19位',//Eway.locale.card.onlyNumber
			cardStatus:'卡片状态',//Eway.locale.card.cardStatus
			eatCardTime:'吞卡时间',//Eway.locale.card.eatCardTime
			IDType:'证件类型',//Eway.locale.card.IDType
			customerName:'客户姓名',//Eway.locale.card.customerName
			customerPapers:'客户证件号',//Eway.locale.card.customerPapers
			customerPhone:'客户电话',//Eway.locale.card.customerPhone
			endData:'吞卡截止日期',//Eway.locale.card.endData
			startData:'吞卡起始日期',//Eway.locale.card.startData
		},
		
		//**********************************************************/
		
		cases:{
			confirm:'确认',
			cancel:'取消',
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
			    upgradeTimes: '升级次数',
			    message: '短信',
			    checkDetails: '查看详情',
			    bankPer: '银行联系人',
			    serPer: '供应商联系人',
			    createTime: '创建时间',
			    informContent: '通知内容',
			    messageContentDetail: '短信内容详情',
			    informWay: '通知方式',
			    mail:'邮件',
			    messageAndMail:'短信和邮件',
			    informMobile: '通知手机号',
			    notifyTimes: '通知次数',
			    notifyRepeatTimes: '重复通知次数',
			    sendTimes: '发送次数',
			    sendInterval: '发送时间间隔',
			    sendTime: '发送时间',
			    faultSearch:'故障查询'
			    
			},
			caseNotify:{
				fault:'故障',
				faultDetails:'故障详情',
				faultlastTime: '故障持续时长(单位:小时)',
				checkFailure:'查看失败！',
				innerFault:'内部错误',//Eway.locale.cases.caseNotify.innerFault
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
			baseInfo:'基本信息',//Eway.locale.personal.baseInfo
			accountNum:'账号',//Eway.locale.personal.accountNum
			personalInfo:'个人信息',//Eway.locale.personal.personalInfo
			changePwd:'修改密码',//Eway.locale.personal.changePwd
			nowLogin:'当前登录账号',//Eway.locale.personal.nowLogin
			inputOldPwd:'输入原始密码',//Eway.locale.personal.inputOldPwd
			inputNewPwd:'输入新密码',//Eway.locale.personal.inputNewPwd
			inputVali:'只能输入8到20位字母‘a-z’或‘A-Z’、数字‘0-9’、特殊字符！',//Eway.locale.personal.inputVali
			inputAgain:'再次输入新密码',//Eway.locale.personal.inputAgain
			pwdNotSame:'两次密码不一致！',//Eway.locale.personal.pwdNotSame
			rememberPwd:'单击确定即可修改密码，请牢记新密码！',//Eway.locale.personal.rememberPwd
			pwdSameNoChange:'输入的新密码与旧密码相同,不可修改.',//Eway.locale.personal.pwdSameNoChange
			reOperate:'无法修改密码,请重新操作.',//Eway.locale.personal.reOperate
		},
		
		//**********************************************************/
		atmLog:{
			dayBackup:'当日备份结果',//Eway.locale.atmLog.dayBackup
			whole:'所有',//Eway.locale.atmLog.whole
			execute:'执行中',//Eway.locale.atmLog.execute
			unKnownFail:'未知原因失败',//Eway.locale.atmLog.unKnownFail
			logDate:'日志日期',//Eway.locale.atmLog.logDate
			backupResult:'备份结果',//Eway.locale.atmLog.backupResult
			checkFailInfo:'查看备份失败详情',//Eway.locale.atmLog.checkFailInfo
			checkSucInfo:'查看备份成功详情',//Eway.locale.atmLog.checkSucInfo
			backupSucAmount:'备份成功台数',//Eway.locale.atmLog.backupSucAmount
			backupFailAmount:'备份失败台数',//Eway.locale.atmLog.backupFailAmount
			backupAllAmount:'总备份台数',//Eway.locale.atmLog.backupAllAmount
			logBackupSta:'日志备份统计',//Eway.locale.atmLog.logBackupSta
			dailyBackup:'每日备份任务',//Eway.locale.atmLog.dailyBackup
			backupDate:'备份日期',//Eway.locale.atmLog.backupDate
			dayBackupResult:'当日备份结果',//Eway.locale.atmLog.dayBackupResult
			backupProcess:'正在备份',//Eway.locale.atmLog.backupProcess
			backupSuccess:'备份成功',//Eway.locale.atmLog.backupSuccess
			backupError:'备份错误',//Eway.locale.atmLog.backupError
			logDevAccount:'日志设备数量累计',//Eway.locale.atmLog.logDevAccount
			reform:'重做',//Eway.locale.atmLog.reform
			busLogAnalysis:'业务日志分析',//Eway.locale.atmLog.busLogAnalysis
			selectAnalysis:'请选择需要分析的日志文件，分析的结果将以Excel表格文件导出',//Eway.locale.atmLog.selectAnalysis
			selectLog:'选择日志',//Eway.locale.atmLog.selectLog
			pleaseDownload:'请下载',//Eway.locale.atmLog.pleaseDownload
			appLogDownload:'应用日志下载',//Eway.locale.atmLog.appLogDownload
			lastBackupTime:'最后一次备份时间',//Eway.locale.atmLog.lastBackupTime
			noBegin:'未开始',//Eway.locale.atmLog.noBegin
			noLog:'无日志',//Eway.locale.atmLog.noLog
			connectFail:'连接失败',//Eway.locale.atmLog.connectFail
			fileSize:'文件大小',//Eway.locale.atmLog.fileSize
			searchIllegal:'查询项中存在不合法的输入,不能导出.'//Eway.locale.atmLog.searchIllegal
		},
		//**********************************************************/
		system:{
			sysRegist:'系统注册',//Eway.locale.system.sysRegist
			registCode:'注册码',//Eway.locale.system.registCode
			startDate:'开始时间',//Eway.locale.system.startDate
			endDate:'到期时间',//Eway.locale.system.endDate
			registType:'注册类型',//Eway.locale.system.registType
			serialNum:'序列号',//Eway.locale.system.serialNum
			getSerialNum:'正在获取序列号......',//Eway.locale.system.getSerialNum
			checkCode:'校验码',//Eway.locale.system.checkCode
			tryOut:'试用',//Eway.locale.system.tryOut
			noLimit:'没有限制',//Eway.locale.system.noLimit
			getSerialNumFail:'序列号获取失败',//Eway.locale.system.getSerialNumFail
			registSuc:'注册成功',//Eway.locale.system.registSuc
			registFail:'注册失败',//Eway.locale.system.registFail
			appearInnerFalse:'出现内部错误',//Eway.locale.system.appearInnerFalse
			regist:'注册',//Eway.locale.system.regist
			aboutSystem:'关于系统',//Eway.locale.system.aboutSystem
			softwareName:'软件名称',//Eway.locale.system.softwareName
			ATMV:'金融自助设备集中监控系统',//Eway.locale.system.ATMV
			softwareVersion:'软件版本',//Eway.locale.system.softwareVersion
			innerVersion:'内部版本号',//Eway.locale.system.innerVersion
			copyRight:'版权信息：&copy;深圳市怡化电脑有限公司 版权所有',//Eway.locale.system.copyRight
			introduction:'系统简介：',//Eway.locale.system.introduction
			introductionA:'本系统是监控系统的基础功能有ATM信息管理、',//Eway.locale.system.introductionA
			introductionB:'自动化版本分发管理、 ATM设备监控等功能。通过这些功能，各大银行可以集中管理ATM设备信息  监视 远程的ATM，对远程ATM机器上的软件升',//Eway.locale.system.introductionB
			introductionC:'级和软件维护，方便了各大银 行对自助设备进行高效的管理和维护。',//Eway.locale.system.introductionC
			guideUsers:'本手册指导用户操作本系统，更快的掌握系统的各项功能。',//Eway.locale.system.guideUsers
			systemHelp:'系统帮助',//Eway.locale.system.systemHelp
			helpName:'名称',//Eway.locale.system.helpName
			helpExpain:'说明',//Eway.locale.system.helpExpain
			helpDownload:'下载',//Eway.locale.system.helpDownload
			clickDownload:'单击此处即可下载该文档',//Eway.locale.system.clickDownload 
		},
		thread:{
			
		},
		
		
		
		
		
	}
});
