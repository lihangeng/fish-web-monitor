Ext.override(Eway,{
	addSuccess : 'Add successful.',//Eway.addSuccess 增加成功.
	updateSuccess : 'Update successful.',//Eway.updateSuccess 更改成功.
	deleteSuccess : 'Delete successful.',//Eway.deleteSuccess 删除成功.
	choiceUpdateMsg :'Please select the record which you want to update.',//Eway.choiceUpdateMsg 请选择您要更改的记录.
	choiceDeleteMsg :'Please select the record which you want to delete.',//Eway.choiceDeleteMsg 请选择您要删除的记录.
	locale:{
		myTable:'Workbench',//Eway.locale.myTable 我的工作台
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
			generalVersionFailForDownloaded:'Generate  the version-file failed:the status "issued" advertisement can not generate version-information any more',//Eway.locale.msg.generalVersionFailForDownloaded 生成版本文件失败:"已下发"状态的广告不能再生成版本信息.
			generalVersionSuccess:"Generate the version file successful",//Eway.locale.msg.generalVersionSuccess 生成版本文件成功.
			createSuccess:"Create successful.",//Eway.locale.msg.createSuccess 创建成功.
			mustHaveOneResource:'At least has one resource of advertisement!',//Eway.locale.msg.mustHaveOneResource 至少包含一个广告资源!
			saveFail:'Save failed',//Eway.locale.msg.saveFail 保存失败
			saveFileSizeMaxFail:'Save failed:the max size of each single file is 30M',//Eway.locale.msg.saveFileSizeMaxFail 保存失败:超过最大单个文件大小限制（最大30M）
			saveFileCommunicationFail:'Save failed:connection refused',//Eway.locale.msg.saveFileCommunicationFail 保存失败:与服务器通讯失败
			chooseDevice:"Please select a machine",//Eway.locale.msg.chooseDevice 请选择设备.
			downloadFailForNoVersion:"Version file issue failed :no generated version-file found or version-file lost,please generate version file first.",//Eway.locale.msg.downloadFailForNoVersion 下发版本文件失败:还没有生成版本文件或者版本文件丢失,请先生成版本文件.
			saveSuccess:'Save successful',//Eway.locale.msg.saveSuccess 保存成功！
			
			versionDownloaded:'Can not delete the advertisement which status is "issued" or "wait for issue',//Eway.locale.msg.versionDownloaded 不能删除"等待下发"和"已下发"状态的版本.
			selectVersionToDelete:'Please choose the version which you want to delete',//Eway.locale.msg.selectVersionToDelete 请选择您要删除的版本.
			communicationFail:'Add failed : connction refused.',//Eway.locale.msg.communicationFail 增加失败:与服务器通讯失败.
			sameVersionNoFail:'Add failed: same version no exists.',//Eway.locale.msg.sameVersionNoFail 增加失败:已经存在相同的版本号.
			fileSizeMaxFail:'Add failed:the max size of the file is 300M',//Eway.locale.msg.fileSizeMaxFail 增加失败:超过最大文件大小限制（最大300M）
			fileUnzipFail:'Add failed: zip-file can not be unziped',//Eway.locale.msg.fileUnzipFail 增加失败:上传的压缩包不能正常解压
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
			bankOrgMove:'Move',  //组织迁移
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
			downloadToolTip:'Issue job settings', //配置下发作业
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
			
			unCertain:'Unknown',//未知
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
			roleDescription:'Just for character ‘a-z’ or ‘A-Z’、number‘0-9’、minussign‘-’、underline‘_’ or point‘.’,only start with Chinesecharacter,character or number,max length is 100',//Eway.locale.tip.roleDescription
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
				orgEligible:'Organization which accordwith the conditions',//Eway.locale.tip.bankOrg.orgEligible 符合条件的机构
				downGradeOrg:'direct lower organization',//Eway.locale.tip.bankOrg.downGradeOrg 的直接下级机构
				move:{
					chooseOrg:'Please choose organization which you want to move.',//Eway.locale.tip.bankOrg.move.chooseOrg 请选择您要迁移的组织
					moveSuccess:'Move the organization successful.'//Eway.locale.tip.bankOrg.move.moveSuccess 组织迁移成功
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
				personEligible:'People which accordwith the conditions',//Eway.locale.tip.bankPer.personEligible 符合条件的人员
				downGradePer:'and its lower organizastion personnel',//Eway.locale.tip.bankPer.downGradePer 以及其下属机构下的人员信息
				personBelongs:'下的人员信息'//Eway.locale.tip.bankPer.personBelongs
			},
			serviceOrg:{
				chooseOrg:'Please choose the service which you want to set.',//Eway.locale.tip.serviceOrg.chooseOrg 请选择您要设置的维护商.
				remove:{
					reChoose:'You hava not choose a service or no manager found in the service,please choose again.'//Eway.locale.tip.serviceOrg.remove.reChoose //您未选择要设置的维护商或该维护商下没有管理员,请重新选择.
				}
			},
			servicePer:{
				allSerPer:'All info of service people'//Eway.locale.tip.servicePer.allSerPer 所有维护商人员信息
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
						beginMonitor:'Please stop monitoring before input item,click start-monitor button to monitor!',//Eway.locale.tip.business.transaction.transactionMonitor.beginMonitor 请先停止监控后再输入条件，按开始监控按钮即可进行条件监控！
						input:'At least one of terminal no,creditAccount,debitAccount is necessary.',//Eway.locale.tip.business.transaction.transactionMonitor.input 设备号、对方账号、客户账号至少输入一个.
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
					returnFail:'Turnover failed:system error.',//Eway.locale.tip.business.card.returnFail 移交失败 后台处理出错
					choose:'Please choose the card which you want to turnover.',//Eway.locale.tip.business.card.choose 请选择您移交的卡片
					returnSucess:'Turnover successful.',//Eway.locale.tip.business.card.returnSucess 移交成功
					chooseBack:'Please choose the card which you want to draw.',//Eway.locale.tip.business.card.chooseBack 请选择要领取的卡片
					getSuccess:'Draw successful.',//Eway.locale.tip.business.card.getSuccess 领取成功
					destroyConfirm:'Destroy this card?',//Eway.locale.tip.business.card.destroyConfirm 是否销毁这张卡片
					destroySuccess:'Destroy successful',//Eway.locale.tip.business.card.destroySuccess 销毁成功
					chooseDestroy:'Please choose the card which you want to destroy.',//Eway.locale.tip.business.card.chooseDestroy 请选择要销毁的卡片
					idCardRegex:'Please input the correct ID number,15 or 18 numbers',//Eway.locale.tip.business.card.idCardRegex 请输入正确的身份证号码,15位或者18位
					accountRegex:'Please input the correct ID number of the householdRegister,15 or 18 numbers',//Eway.locale.tip.business.card.accountRegex 请正确输入户口本上的身份证号码,15位或者18位
					driveCardRegex:'Please input the correct ID number of the driver-license,15 or 18 numbers',//Eway.locale.tip.business.card.driveCardRegex 请正确输入驾驶证上的身份证号码,15位或者18位
					passportRegex:'Please input the correct ID number of the passport,15 or 18 numbers',//Eway.locale.tip.business.card.passportRegex 请正确输入护照上的身份证号码,15位或者18位
					soldierRegex:'Please input the correct number of officer-license ,1-5 Chinesecharacter or ‘1-10’ numbers',//Eway.locale.tip.business.card.soldierRegex 请输入正确的军官证号码,1-5位汉字和1-10位数字
					soldierCard:'Please input the correct number of soldier-license 7-8 numbers ',//Eway.locale.tip.business.card.soldierCard 请输入正确的士兵证,7-8位数字
					busnessPaper:'Please input the correct number of businesslicence,12-15 numbers',//Eway.locale.tip.business.card.busnessPaper 请输入正确的法人营业执照,12-15位数字
					busnessCode:'Please input the correct number of juridicalperson,15 numbers',//Eway.locale.tip.business.card.busnessCode 请输入正确的法人代码证,15位数字
					taxPaper:'Please input the correct number of taxregistration,15 numbers'//Eway.locale.tip.business.card.taxPaper 请输入正确的税务登记证,15位数字
					
				},
				device:{
					getCashInfoFail:'Get the cashbin info failed',//Eway.locale.tip.business.device.getCashInfoFail 获取钞箱信息失败
					operating:'Executing',//Eway.locale.tip.business.device.operating 正在执行
					reviewFail:'Failed to review.',//Eway.locale.tip.business.device.reviewFail 查看失败
					logLoadConfirm:'Get the applogs successful,download it?',//Eway.locale.tip.business.device.logLoadConfirm 应用日志提取成功,是否下载?
					logPullFail:'Get the applogs failed',//Eway.locale.tip.business.device.logPullFail 提取应用电子日志失败
					logFail:'Log process failed.',//Eway.locale.tip.business.device.logFail log 处理失败
					linkServerFail:'Connecting refused.',//Eway.locale.tip.business.device.linkServerFail 服务器连接失败
					logicOpen:'Confirm execute open-service command.',//Eway.locale.tip.business.device.logicOpen 确认要执行开启服务命令
					openSuccess:'Execute open-service command successful.',//Eway.locale.tip.business.device.openSuccess 执行开启服务命令成功.
					openFail:'Execute open-service command failed.',//Eway.locale.tip.business.device.openFail 执行开启服务命令失败.
					closeConfirm:'Confirm execute pause-service command?',//Eway.locale.tip.business.device.closeConfirm 确认要执行暂停服务命令?
					closeNormal:'Shut down normally.',//Eway.locale.tip.business.device.closeNormal 正常关机
					closeComfirm:'Confirm execute normal shut-down command,it may brings some risk?',//Eway.locale.tip.business.device.closeComfirm 确认要执行正常关机命令么,可能会存在风险?
					closing:'Shutting down normally.',//Eway.locale.tip.business.device.closing 正在执行正常关机
					closeSucess:'Shut down normally successful.',//Eway.locale.tip.business.device.closeSucess 正常关机成功
					closeFail:'Shut down normally failed.',//Eway.locale.tip.business.device.closeFail 正常关机失败
					closeSentFail:'Send the normal shut-down command failed ',//Eway.locale.tip.business.device.closeSentFail 正常关机命令发送失败.
					forceClose:'Force shut down',//Eway.locale.tip.business.device.forceClose 强制关机
					forceCloseComfirm:'Confirm execute force shut-down command,it may brings some risk?',//Eway.locale.tip.business.device.forceCloseComfirm 确认要执行强制关机命令么,可能会存在严重风险?
					forceClosing:'Shutting down force.',//Eway.locale.tip.business.device.forceClosing 正在执行强制关机
					forceCloseSucess:'Force-shut-down successful.',//Eway.locale.tip.business.device.forceCloseSucess 强制关机成功.
					forceCloseFail:'Force-shut-down failed.',//Eway.locale.tip.business.device.forceCloseFail 强制关机失败.
					ForceCloseSentFail:'Send the force-shut-down command failed ',//Eway.locale.tip.business.device.ForceCloseSentFail 强制关机命令发送失败.
					reboot:'Reboot normally.',//Eway.locale.tip.business.device.reboot 正常重启
					rebootConfirm:'Confirm execute normal-reboot command,it may brings some risk?',//Eway.locale.tip.business.device.rebootConfirm 确认要执行正常重启命令么,可能会存在风险?
					rebooting:'Normally rebooting',//Eway.locale.tip.business.device.rebooting 正在执行正常重启
					rebootSucess:'Normal-reboot successful.',//Eway.locale.tip.business.device.rebootSucess 正常重启成功
					rebootFail:'Normal-reboot failed.',//Eway.locale.tip.business.device.rebootFail 正常重启失败.
					rebootSendFail:'Send the  normal-reboot command failed ',//Eway.locale.tip.business.device.rebootSendFail 正常重启命令发送失败.
					forceReboot:'Force reboot',//Eway.locale.tip.business.device.forceReboot 强制重启
					forceRebootConfirm:'Confirm execute force-reboot command,it may brings some risk?',//Eway.locale.tip.business.device.forceRebootConfirm 确认要执行强制重启命令么,可能会存在严重风险
					forceRebooting:'Executing force-reboot command',//Eway.locale.tip.business.device.forceRebooting 正在执行强制重启
					forceRebootSuccess:'Force-reboot successful.',//Eway.locale.tip.business.device.forceRebootSuccess 强制重启成功
					forceRebootFail:'Force-reboot failed.',//Eway.locale.tip.business.device.forceRebootFail 强制重启失败.
					forceRebootSendFail:'Send the normal force-reboot command failed ',//Eway.locale.tip.business.device.forceRebootSendFail 强制重启命令发送失败.
					resetConfirm:'Confirm execute force-reset command?',//Eway.locale.tip.business.device.resetConfirm 确认要执行强制复位
					resetSuccess:'Force-reset successful',//Eway.locale.tip.business.device.resetSuccess 强制复位成功
					resetFail:'Force-reset failed',//Eway.locale.tip.business.device.resetFail 强制复位失败
					resetSendFail:'Send the force-reset command failed.',//Eway.locale.tip.business.device.resetSendFail 强制复位命令发送失败
					term:'Terminal',//Eway.locale.tip.business.device.term 设备
					detail:'Detail',//Eway.locale.tip.business.device.detail 详情
					refresh:'Refreshing......',//Eway.locale.tip.business.device.refresh 正在刷新......
					chooseOrg:'Screen organization',//Eway.locale.tip.business.device.chooseOrg 机构筛选
					stateSet:'Status-monitor Setting',//Eway.locale.tip.business.device.stateSet 状态监控项配置
					filterSet:'Filter setting',//Eway.locale.tip.business.device.filterSet 过滤条件项配置
					connFirst:'Monitor connection has been paused at the present,please connect the server first as ‘start monitor’"',//Eway.locale.tip.business.device.connFirst 当前已经暂停了与服务器的监控连接,请先与服务器建立连接,即"开始监控"
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
			choseTask:'Please choose a job',//请选择一个作业
			cancelTask:'Can not cancel the job which status is ‘finish’.',//不能撤销"完成"状态的作业
			cancelParticularTask:'Cancel the job?(running job can only cancel the task which is not running)(正在运行的作业只会撤销还没有运行的任务.)',//是否真的要撤销指定的作业?(正在运行的作业只会撤销还没有运行的任务.)
			nowDelete:'Deleting......', //正在删除......
		},
		//**********************************************************/
		versionType:{
			title:'Software assortment',//Eway.locale.versionType.title 软件分类管理
			treeRoot:'All software assortment',//Eway.locale.versionType.treeRoot 所有软件分类
			defaultInstallPath:'Default install path',//Eway.locale.versionType.defaultInstallPath 默认安装路径
			needRestart:'If reboot is necessary for update',//Eway.locale.versionType.needRestart 需要重启设备完成升级
			devTypeOfUser:'Suitable device type',//Eway.locale.versionType.devTypeOfUser //适用的设备型号
			winTitle:'Software assortment',//Eway.locale.versionType.winTitle 软件分类
			versionTypeNameRegText:'Just for character ‘a-z’ or ‘A-Z’、number‘0-9’、minussign‘-’、underline‘_’'//Eway.locale.versionType.versionTypeNameRegText 只能输入字母(a-z或A-Z)、数字(0-9)、下划线(_)、横线(-)
		},
		statics:{
			title:'下发结果统计',//Eway.locale.statics.title
			versionInfo:'Version info'//Eway.locale.statics.versionInfo //版本信息
		},
		//广告模块
		advert:{
			title:'Advertisement',//Eway.locale.advert.title //广告管理
			createAdvert:'Create Advertisement',//Eway.locale.advert.createAdvert 创建广告
			idleAdvert:'Create card-insert waiting advertisement',//Eway.locale.advert.idleAdvert 创建等待插卡广告
			transAdvert:'Create transaction advertisement',//Eway.locale.advert.transAdvert 创建交易页面广告
			textAdvert:'Create rolling characters advertisement',//Eway.locale.advert.textAdvert 创建文字滚动广告
			annoucementAdvert:'Create announcement',//Eway.locale.advert.annoucementAdvert 创建公告
			updateTitle:'Modify advertisement information',//Eway.locale.advert.updateTitle 更改广告信息
			downloadButton:'Download advertisement',//Eway.locale.advert.downloadButton 下发广告
			preview:'Advertisement preview',//Eway.locale.advert.preview 广告预览
			id:'AdvertisementID',//Eway.locale.advert.id 广告ID
			type:'Advertisement type',//Eway.locale.advert.type 广告类型
			downType:'Issuing way',//Eway.locale.advert.downType 广告下发方式
			validity:'Expiry date',//Eway.locale.advert.validity 广告有效期
			createdTime:'Created time',//Eway.locale.advert.createdTime 制作时间
			userName:'Creater',//Eway.locale.advert.userName 制作人
			versionStatus:'Version status',//Eway.locale.advert.versionStatus 广告版本状态
			advertVersionFile:'Version file',//Eway.locale.advert.advertVersionFile 版本文件
			createdTimeStart:'Create time begin with',//Eway.locale.advert.createdTimeStart 制作时间开始于
			createdTimeStop:'End time end with',//Eway.locale.advert.createdTimeStop 制作时间结束于
			downloadAdvertConfig:'Issue settings',//Eway.locale.advert.downloadAdvertConfig 下发广告配置
			versionType:'Software type',//Eway.locale.advert.versionType 软件分类
			jobPriority:'Job priority',//Eway.locale.advert.jobPriority //作业优先级
			jobType:'Job type',//Eway.locale.advert.jobType 作业类型
			toVersionButton:'Has not generate version information,click button to [generate version]。',//Eway.locale.advert.toVersionButton 还没有生成版本信息，可以单击按钮[生成版本]。
			playTime:'Play Time',//Eway.locale.advert.playTime 广告播放时长
			beginDate:'Start date',//Eway.locale.advert.beginDate 开始日期
			endDate:'End date',//Eway.locale.advert.endDate 结束日期
			beginTime:'Start time',//Eway.locale.advert.beginTime 开始时间
			endTime:'End time',//Eway.locale.advert.endTime 结束时间
			fileSize:'Resource size',//Eway.locale.advert.fileSize 资源大小
			content:'Comment of play resource ',//Eway.locale.advert.content 播放资源内容
			advertConfig:'Settings',//Eway.locale.advert.advertConfig 广告配置
			addIdleTitle:'Add card-insert waiting advertisement',//Eway.locale.advert.addIdleTitle 增加等待插卡广告信息
			addIdleMore:'Add resource of advertisement again',//Eway.locale.advert.addIdleMore 再增加一个广告资源
			advertBasicInfo:'Basic info',//Eway.locale.advert.advertBasicInfo 广告基本信息
			idleAdvertInfo:'Card-insert waiting advertisement',//Eway.locale.advert.idleAdvertInfo 等待插卡页面广告
			advertValidity:'Expiry date',//Eway.locale.advert.advertValidity 广告有效期
			validityTemp:'Play temporary',//Eway.locale.advert.validityTemp 临时播放
			validityAlways:'Play forever',//Eway.locale.advert.validityAlways 永久播放
			idleAdvertResConfig:'Set the resource of card-insert waiting advertisement',//Eway.locale.advert.idleAdvertResConfig 等待插卡页面广告资源配置
			addTransTitle:'Add information of transacton-advertisement',//Eway.locale.advert.addTransTitle 增加交易页面广告信息
			transInfoAdvert:'Page of transcatiion-advertisement',//Eway.locale.advert.transInfoAdvert	 交易页面广告
			transAdvertResConfig:'Set the resource of transacton-advertisement-page',//Eway.locale.advert.transAdvertResConfig 交易页面广告资源配置
			addTextTitle:'Add information of rolling-characters-advertisement',//Eway.locale.advert.addTextTitle 增加文字滚动广告信息
			textInfoAdvert:'Characters-advertisement',//Eway.locale.advert.textInfoAdvert	 文字广告
			textAdvertResConfig:'Set the resource of rolling-characters-advertisement',//Eway.locale.advert.textAdvertResConfig 文字广告资源配置
			addAnnoucementTitle:'Add announcement',//Eway.locale.advert.addAnnoucementTitle 增加公告信息
			annoucementBasicInfo:'Basic information of announcement ',//Eway.locale.advert.annoucementBasicInfo 公告基本信息
			annoucementInfoAdvert:'Announcement',//Eway.locale.advert.annoucementInfoAdvert	 公告
			annoucementAdvertResConfig:'Set the resource of announcement-advertisement',//Eway.locale.advert.annoucementAdvertResConfig 公告页面广告资源配置
			advertTypeSelectEmpty:'Please choose type of advertisement',//Eway.locale.advert.advertTypeSelectEmpty 请选择广告类型
			advertTypeTrans:'Page of transaction',//Eway.locale.advert.advertTypeTrans 交易页面广告
			advertTypeIdle:'Page of card-insert waiting',//Eway.locale.advert.advertTypeIdle 等待插卡广告
			advertTypeText:'Rolling character advertisement',//Eway.locale.advert.advertTypeText 文字滚动广告
			advertTypeAnnou:'Announcement',//Eway.locale.advert.advertTypeAnnou 公告
			annoucementMoreTitle:'Add announcement',//Eway.locale.advert.annoucementMoreTitle 添加公告
			annoucementContext:'Comment of announcement',//Eway.locale.advert.annoucementContext Add announcement
			annoucementContextRegText:'Blank is not allowed',//Eway.locale.advert.annoucementContextRegText 不能包含空格
			times:'Duration',//Eway.locale.advert.times 时长
			timesTips:'Unit:second,tip:duration below 60 second will be better',//Eway.locale.advert.timesTips 单位:秒，提示：广告播放时长请控制在60秒内
			hourDisplay:'Hour',//Eway.locale.advert.hourDisplay 时
			minuteDisplay:'Minute',//Eway.locale.advert.minuteDisplay 分
			secondeDisplay:'Second',//Eway.locale.advert.secondeDisplay 秒
			textMoreTitle:'Add page with rolling-character',//Eway.locale.advert.textMoreTitle 添加文字滚动页面广告
			textContext:'Rolling character',//Eway.locale.advert.textContext 滚动文字
			idleMoreTitle:'Add page of card-insert waiting advertisement',//Eway.locale.advert.idleMoreTitle 添加等待插卡页面广告
			chooseMediaFile:'Please choose media file',//Eway.locale.advert.chooseMediaFile 请选择媒体文件
			uploadResource:'Upload resource...',//Eway.locale.advert.uploadResource 上传资源
			uploadResourceBlank:'Please upload resource...',//Eway.locale.advert.uploadResourceBlank 请上传资源
			uploadRegText:'Unsupported resource uploaded ,only .jpg,.aiv file can be uploaded',//Eway.locale.advert.uploadRegText 上传的资源格式不支持,只能上传.jpg、.avi格式的文件
			resourceFormatTips:'Only .jpg,.aiv file is supported',//Eway.locale.advert.resourceFormatTips (仅支持.jpg、.avi格式的文件)
			resourceAlias:'File name after modified',//Eway.locale.advert.resourceAlias 修改后的文件名
			transMoreTitle:'Add page of transaction advertisement',//Eway.locale.advert.transMoreTitle 添加交易页面广告
			chooseMediaFile:'Please choose media file',//Eway.locale.advert.chooseMediaFile 请选择媒体文件
			uploadResource:'Upload resource...',//Eway.locale.advert.uploadResource 上传资源
			uploadResourceBlank:'Please upload resource',//Eway.locale.advert.uploadResourceBlank 请上传资源
			uploadRegText:'Unsupported resource uploaded ,only .jpg,.aiv file can be uploaded',//Eway.locale.advert.uploadRegText 上传的资源格式不支持,只能上传.jpg、.avi格式的文件
			resourceFormatTips:'Only .jpg,.aiv file is supported',//Eway.locale.advert.resourceFormatTips (仅支持.jpg、.avi格式的文件)
			resourceAlias:'File name after modified',//Eway.locale.advert.resourceAlias 修改后的文件名
			advertDownMethodCover:'Override',//Eway.locale.advert.advertDownMethodCover 覆盖
			uploading:'Resource uploading...',//Eway.locale.advert.uploading 正在上传资源...
			advertPreviewTitle0:'Preview advertisement (Total ',//Eway.locale.advert.advertPreviewTitle0 广告预览(共有 
			advertPreviewTitle1:'resources,play the no. ',//Eway.locale.advert.advertPreviewTitle1 个资源,当前播放第
			advertPreviewTitle2:'at the present) ',//Eway.locale.advert.advertPreviewTitle2 //个
			choosedAdvertRes:'You have choosed',//Eway.locale.advert.choosedAdvertRes 您已经选择了
			perviewAdertWithIEBrowse:'Unsupported preview the video without IE explorer.',//Eway.locale.advert.perviewAdertWithIEBrowse 非IE浏览器不支持视频广告的预览
			configTitle:'Detail setting'//Eway.locale.advert.configTitle 广告详细配置
		},
		//版本管理模块
		version:{
			selectDeviceInfo0:"Device selected(<font color='red'>",//Eway.locale.version.selectDeviceInfo0 //已选择的设备 (<font color='red'>
			selectDeviceInfo1:"</font>)",//Eway.locale.version.selectDeviceInfo1 </font>)台
			addVersionTitle:'Add version information',//Eway.locale.version.addVersionTitle 增加版本信息
			batchTaskName:'Batch task name',//Eway.locale.version.batchTaskName 任务批次名称
			batchTaskNameEmpty:'Example:****first requirement update',//Eway.locale.version.batchTaskNameEmpty 例如:****需求第1批次升级
			UpdateTitle:'Change version information',//Eway.locale.version.UpdateTitle 更改版本信息
			addJobTitle:'Set version information',//Eway.locale.version.addJobTitle 配置作业信息
			downloadVersionId:'Issue version ID',//Eway.locale.version.downloadVersionId 下发版本ID
			taskType:'Task type',//Eway.locale.version.taskType 任务类型
			taskTypeManual:'Manual update',//Eway.locale.version.taskTypeManual 手动升级
			taskTypeAuto:'Auto update',//Eway.locale.version.taskTypeAuto 自动升级
			taskTypeScheduler:'Job plan',//Eway.locale.version.taskTypeScheduler 计划作业
			planTime:'Plan to execute time',//Eway.locale.version.planTime 计划执行时间
			selectableDevice:'Deivice can be issue',//Eway.locale.version.selectableDevice 可以下发的设备
			linkedDevice:'Selected device',//Eway.locale.version.linkedDevice 已选择的设备
			downloadVerFile:'Download version file',//Eway.locale.version.downloadVerFile 下载版本文件
			View:{
				title:'Version', //版本管理
				versionDetail:'Version detail',//Eway.locale.version.View.versionDetail 版本详情
				remark:'remark', //Eway.locale.version.View.remark 备注
				newCreate:'Create',//Eway.locale.version.View.newCreate 新建
				downLoaded:'DownLoaded',//Eway.locale.version.View.downLoaded 已下发
				waitting:'Waitting',//Eway.locale.version.View.waitting 等待下发
				versionPath:'Version path',//Eway.locale.version.View.versionPath 版本路径
				versionPathRegText:'不符合文件路径规则，规则如下：1.文件名只能包含英文字母(a-z A-Z)、数字(0-9)、下划线(_)、横线(-) ； 2.路径统一用正斜杠(/)作为分隔符 ；3.不区分大小 ; 示例 E: E:/yihua',//Eway.locale.version.View.versionPathRegText
				versionPathDesc:'(path of version file be installed)',//Eway.locale.version.View.versionPathDesc (版本文件在自助设备上的安装路径)
				versionPerson:'Version maker',//Eway.locale.version.View.versionPerson 创建人
				versionType:'Version type',//Eway.locale.version.View.versionType 版本类型
				versionFile:'Version file',//Eway.locale.version.View.versionFile 版本文件
				versionFileButton:'choose...',//Eway.locale.version.View.versionFileButton 浏览...
				versionFileRegexText:'Only .zip or .rar file can be uploaded',//Eway.locale.version.View.versionFileRegexText 只能上传zip或rar格式的文件
				versionFileUploadMsg:'File uploading...',//Eway.locale.version.View.versionFileUploadMsg 正在上传文件...
				versionFileEmpty:'Please zip the version file(or folder) to .zip or .rar format',//Eway.locale.version.View.versionFileEmpty 请将要下发的版本文件(或者文件夹)打包zip或rar格式
				versionTypeCode:'Software classify code',//Eway.locale.version.View.versionTypeCode 软件分类编码
				versionTypeName:'Software classify name',//Eway.locale.version.View.versionTypeName 软件分类名称
				versionTypeId:'Version type ID',//Eway.locale.version.View.versionTypeId 版本类型ID
				versionTypeEmpty:'-select versionType-',//Eway.locale.version.View.versionTypeEmpty -请选择版本类型-
				versionTime:'Create time',//Eway.locale.version.View.versionTime 创建时间
				versionNo:'Version serialno',//Eway.locale.version.View.versionNo 版本号
				nowVersionNo:'Current version number',//Eway.locale.version.View.nowVersionNo 当前版本号
				versionStatus:'Version status',//Eway.locale.version.View.versionStatus 版本状态
				versionStatusEmptyText:'All', //全部
				autoUpdate:'Update automatic permission',//Eway.locale.version.View.autoUpdate 允许自动更新
				autoUpdateYes:'yes',//Eway.locale.version.View.autoUpdateYes //是
				autoUpdateNo:'no',//Eway.locale.version.View.autoUpdateNo 否
				autoUpdateEmptyText:'All', //全部
				dependVersion:'Version dependent on',//Eway.locale.version.View.dependVersion 依赖版本
				dependVersionEmptyText:'Please select type dependent on', //请选择依赖类型
				execBefore:'Execute script before update',//Eway.locale.version.View.execBefore 升级前执行脚本
				execBeforeEmptyText:'Please input the file in the update package which name is end with bat or cmd',//Eway.locale.version.View.execBeforeEmptyText 请输入升级包中的以bat或cmd结尾的文件
				execBeforeRegexText:'Only the file which name end with ‘bat’ or ‘cmd’ can be inputed',//Eway.locale.version.View.execBeforeRegexText 只能输入bat或cmd结尾的文件
				versionDesc:'Version description',//Eway.locale.version.View.versionDesc 版本描述
				versionDescEmpty:'Please describe this version with words (At most 20 words)',//Eway.locale.version.View.versionDescEmpty 请用文字描述此版本需求
				otherConfigTitle:'Other settng',//Eway.locale.version.View.otherConfigTitle 其他配置
				otherConfigAutoDown:'Allow update automatic (only while the ATM check new version from server ,the version which update automatic will be return to ATM )',//Eway.locale.version.View.otherConfigAutoDown 允许自动更新(当ATM向服务器检查新版本时，允许自动更新的版本才可以返回给ATM)
				otherConfigUncompress:'Uzip automatic(when choose this option, the ATM will unzip automatic )&nbsp;<font color="red">attention:if the file is not zip file at first ,and then ziped to zip file ,please select this option!</font>',//Eway.locale.version.View.otherConfigUncompress 自动解压缩(选中此项时，在ATM端会自动解压缩)&nbsp;<font color="red">注意：如果版本文件本来不符合zip格式，后被压缩成zip时，请选中此项！</font>
				versionServerPath:'Path of file in server', //Eway.locale.version.View.versionServerPath 文件在服务器上的位置
				versionName:'Version name',//Eway.locale.version.View.versionName 版本名称
				downloadVersionName:'Version issued',//Eway.locale.version.View.downloadVersionName 下发的版本
				downloadVersionNameEmpty:'Please choose a version which you want to issue',//Eway.locale.version.View.downloadVersionNameEmpty 下发的版本
				distributionPic:'Chart of version distribute',//Eway.locale.version.View.distributionPic 版本分布图
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
				hight:'Hight',//Eway.locale.version.jobPriority.hight 高
				middle:'middle',//Eway.locale.version.jobPriority.middle 中等
				general:'general'//Eway.locale.version.jobPriority.general 普通
			},
			jobType:{
				float:'Manual job(issue immediately)',//Eway.locale.version.jobType.float 手工作业(立即下发)
				fix:'Plan job (issue timer)'//Eway.locale.version.jobType.fix 计划作业(定时下发)
			},
			taskStatus:{
				news:'New',//Eway.locale.version.taskStatus.news 新建
				running:'running',//Eway.locale.version.taskStatus.running 运行中
				noticed:'Notice successful',//Eway.locale.version.taskStatus.noticed 通知成功
				noticedFail:'Notice failed',//Eway.locale.version.taskStatus.noticedFail 通知失败
				downloaded:'Issued',//Eway.locale.version.taskStatus.downloaded 已下发
				downloadedFail:'Issue failed',//Eway.locale.version.taskStatus.downloadedFail 下发失败
				deployed:'Installing',//Eway.locale.version.taskStatus.deployed 正在部署
				deployedWait:'Wait for install',//Eway.locale.version.taskStatus.deployedWait 等待部署
				deployedFail:'Install failed',//Eway.locale.version.taskStatus.deployedFail 部署失败
				checked:'Confirmed to install',//Eway.locale.version.taskStatus.checked 部署已确认
				noticeOk:'Noticed application',//Eway.locale.version.taskStatus.noticeOk 已通知应用
				noticeFail:'Notic application failed'//Eway.locale.version.taskStatus.noticeFail 通知应用失败
			},
			versionCatalog:{
				app:'Application',//Eway.locale.version.versionCatalog.app 应用程序
				agent:'Monitor agent',//Eway.locale.version.versionCatalog.agent 监控代理
				param:'Application settings'//Eway.locale.version.versionCatalog.param 应用配置
			},
			download:{
				title:'Distribute minitor',//Eway.locale.version.download.title 分发监控
				updateTitle:'Modify version information',//Eway.locale.version.download.updateTitle 修改版本下发信息
				taskQueryTips:'Query job detail by condition',//Eway.locale.version.download.taskQueryTips 根据条件查询选中作业下的详情信息
				autoRefresh:'Start auto refresh',//Eway.locale.version.download.autoRefresh 开启自动刷新
				stopAutoRefresh:'Stop auto refresh',//Eway.locale.version.download.stopAutoRefresh 停止自动刷新
				autoRefreshTips:'Refresh periodic 60 second',//Eway.locale.version.download.autoRefreshTips 刷新周期60秒
				taskExportTips:'Export all issue result of job selected'//Eway.locale.version.download.taskExportTips 导出选中作业下的全部下发结果
			},
			task:{
				selectJobStartRefresh:'Please choose a job,and then start automatic refresh',//Eway.locale.version.task.selectJobStartRefresh 请选择一个作业,再开启定时刷新！
				jobBatchName:'Job bacth name',//Eway.locale.version.task.jobBatchName 作业批次名称
				patchVersion:'Distribute version',//Eway.locale.version.task.patchVersion 分发版本
				taskStatus:'Task status',//Eway.locale.version.task.taskStatus 任务状态
				beforeUpdate:'Version before distribute',//Eway.locale.version.task.beforeUpdate 分发前的版本
				exceptVersion:'Version expect',//Eway.locale.version.task.exceptVersion 预期版本
				actionTime:'Execute time',//Eway.locale.version.task.actionTime 执行时间
				downSource:'Source download',//Eway.locale.version.task.downSource 下载源
				planTime:'Plan time',//Eway.locale.version.task.planTime 计划时间
				excuteMachine:'Execute machine',//Eway.locale.version.task.excuteMachine 执行服务器
				restartATM:'Restart ATM',//Eway.locale.version.task.restartATM 重启ATM
				restartATMTips:'Confirm execute reboot command?it may brings some risk.',//Eway.locale.version.task.restartATMTips 执行重启命令可能存在风险,确认重启?
				sendRestartCmd:'Reboot command has been sent!',//Eway.locale.version.task.sendRestartCmd 已发送重启命令！
				cancelDownloadSuccess:'Cancel issue successful!',//Eway.locale.version.task.cancelDownloadSuccess 取消下发成功
				cancelDownload:'Cancel issue',//Eway.locale.version.task.cancelDownload 取消下发
				jobName:'Job name',//Eway.locale.version.task.jobName 作业名称
				jobStatus:'Job status',//Eway.locale.version.task.jobStatus 作业状态
				chooseTitleDevice:'Choose machine',//Eway.locale.version.task.chooseTitleDevice 选择设备
				closeWindow:'Close',//Eway.locale.version.task.closeWindow 关闭窗口
				queryByFilter:'Query by filter',//Eway.locale.version.task.queryByFilter 根据条件查找
				displayNumPerPage:'Display on each page',//Eway.locale.version.task.displayNumPerPage 每页显示条数
				targetVersionNo:'Target version',//Eway.locale.version.task.targetVersionNo 目标版本
				downloadStatus:'Issue status',//Eway.locale.version.task.downloadStatus 下发状态
				downloadResult:'Issue result',//Eway.locale.version.task.downloadResult 下发结果
				cancelJob:'Cancel job',//Eway.locale.version.task.cancelJob 撤销作业
				jobId:'Job ID',	//Eway.locale.version.task.jobId 作业ID
				selectDownloadDevice:'Choose deivce to issue',	//Eway.locale.version.task.selectDownloadDevice 选择下发的设备
				versionNoBeforeUpdate:'Version before update',	//Eway.locale.version.task.versionNoBeforeUpdate 升级前版本号
				versionNoAfterUpdate:'Target version no',	//Eway.locale.version.task.versionNoAfterUpdate 目标版本号
				deviceVersionHis:'View device history version',	//Eway.locale.version.task.deviceVersionHis 查看设备历史版本
				downloadUser:'Issue man',	//Eway.locale.version.task.downloadUser 下发人
				downloadTime:'Issue time',	//Eway.locale.version.task.downloadTime 下发时间
				downloadResult:'Issue result',	//Eway.locale.version.task.downloadResult 下发结果
				deviceVersionHisTitle:'Information of device history version',	//Eway.locale.version.task.deviceVersionHisTitle 设备历史版本信息
				deviceVersions:'Device version',	//Eway.locale.version.task.deviceVersions 设备版本
				deviceVersionHisTip:'View information of device history version',	//Eway.locale.version.task.deviceVersionHisTip 查看设备历史版本信息
				autoUpdateInfo:'Information of auto update',//Eway.locale.version.task.autoUpdateInfo 自动更新信息
				selectAJob:'Please choose a job.',//Eway.locale.version.task.selectAJob 请选择一个作业
				versionDownHisStatusPic:'版本下发历史状态分布图',//Eway.locale.version.task.versionDownHisStatusPic 版本下发历史状态分布图
				cantCancelCompleteJob:'Can not cancel job which status is ‘finish’.',//Eway.locale.version.task.cantCancelCompleteJob 不能撤销"完成"状态的作业
				doSureCancelTheJob:'Cancel the selected job?(running job can only cancel the task which has not running)',//Eway.locale.version.task.doSureCancelTheJob 是否真的要撤销指定的作业?(正在运行的作业只会撤销还没有运行的任务.)
				deleting:'Deleting......',//Eway.locale.version.task.deleting 正在删除
				cancelSuccessBut:'Cancel the task in the job which has not run yet successful,the status is "running" at the present,please refresh the list',//Eway.locale.version.task.cancelSuccessBut
				cancelJobSuccess:'Cancel job successful',//Eway.locale.version.task.cancelJobSuccess 成功撤销作业
				updateResult:'Update result'	//Eway.locale.version.task.updateResult 升级结果
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
				bandWidth: 'Broadband speed test',//宽带测速
				unit: 'Unit:MB',//单位：MB
				againTest: 'Test again',//重新测试
				impressionName: 'Impression name',//印象名称
				userName: 'User name',//用户名
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
				nowPath: '当前路径',//当前路径
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
			
			jobNum:'JobNo',//Eway.locale.commen.jobNum 工号
			name:'Name',//Eway.locale.commen.name 姓名
			personJobName:'Quarters',//Eway.locale.commen.personJobName 岗位
			state:'Status',//Eway.locale.commen.state 状态
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
			setManager:'Setting',//Eway.locale.commen.setManager 设置
			devServiceName:'Maintenance of device',//Eway.locale.commen.devServiceName 设备维护商
			cashboxLimit:'Alarm of money in cashbox',//Eway.locale.commen.cashboxLimit 钞箱报警金额
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
				newCreate:'New',//Eway.locale.commen.stateDict.newCreate 新建
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
			insideOutside:'In bank flag',//Eway.locale.commen.insideOutside 在行标志
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
			orgFramework:'Organization',//Eway.locale.commen.orgFramework 组织机构
			matchOrg:'Organization matched',//Eway.locale.commen.matchOrg 匹配机构
			orgID:'Organization ID',//Eway.locale.commen.orgID 机构ID
			endValidty:'Valid date until',//Eway.locale.commen.endValidty 截止有效期
			publishDate:'Issue date',//Eway.locale.commen.publishDate 发布日期
			announceTheme:'Announce theme',//Eway.locale.commen.announceTheme 公告主题
			
			
			
		},
		//**********************************************************/
		person:{
			bankOrg :{
				title:'Organization',//Eway.locale.person.bankOrg.title 银行机构管理
				moveBankTitle:'Migrate organization',//Eway.locale.person.bankOrg.moveBankTitle 组织迁移
				updateBankTitle:'Modify organization info',//Eway.locale.person.bankOrg.updateBankTitle 更改银行机构信息
				addBankOrgTitle:'Add organization info',//Eway.locale.person.bankOrg.addBankOrgTitle 增加银行机构信息
				code:'Code',//Eway.locale.person.bankOrg.code 机构编号
				orgType:'Type',//Eway.locale.person.bankOrg.orgType 机构类型
				name:'Name',//Eway.locale.person.bankOrg.name 机构名称
				orgLevel:'Level',//Eway.locale.person.bankOrg.orgLevel 机构级别
				orgNavi:'Navigator',//Eway.locale.person.bankOrg.orgNavi 机构导航
				zip:'Zipcode',//Eway.locale.person.bankOrg.zip 邮政编码
				removeManager:'Remove',//Eway.locale.person.bankOrg.removeManager 删除
				manager:'Manager',//Eway.locale.person.bankOrg.manager 管理员
				address:'Address',//Eway.locale.person.bankOrg.address 机构地址
				upgradeOrg:'Upper',//Eway.locale.person.bankOrg.upgradeOrg 上级机构
				description:'Description',//Eway.locale.person.bankOrg.description 机构描述
				serOrganization:'Maintenance description',//Eway.locale.person.bankOrg.serOrganization
				organizationType:{
					bank:'Bank',//Eway.locale.person.bankOrg.organizationType.bank 银行
					serviceOrg:'Maintenance'//Eway.locale.person.bankOrg.organizationType.serviceOrg 维护商
				},
				organizationLevelDict:{
					rootBank:'Root bank',//Eway.locale.person.bankOrg.organizationLevelDict.rootBank 总行
					branchBank:'branch',//Eway.locale.person.bankOrg.organizationLevelDict.branchBank 分行
					tagBranchBank:'subbranch',//Eway.locale.person.bankOrg.organizationLevelDict.tagBranchBank 支行
					netBank:'netBank'//Eway.locale.person.bankOrg.organizationLevelDict.netBank 网点
				},
				addOrgTitle:'Person under this organization',//Eway.locale.person.bankOrg.addOrgTitle 该机构人员信息
				linkPeronTitle:'Person under the organization:',//Eway.locale.person.bankOrg.linkPeronTitle 当前机构下人员
				personList:'Person list',//Eway.locale.person.bankOrg.personList 人员列表
				orgLinkTitle:'Person and device which link this organization',//Eway.locale.person.bankOrg.orgLinkTitle 该机构的关联设备和人员
				machineOrg:'Device under the organization:',//Eway.locale.person.bankOrg.machineOrg
				personOrg:'Person under the organization:'//Eway.locale.person.bankOrg.personOrg
				
			},
			bankPer :{
				title:'Bank person',//Eway.locale.person.bankPer.title 银行人员管理
				organizationName:'Organization',//Eway.locale.person.bankPer.organizationName 机构
				orgNavi:'Organization Navigator',//Eway.locale.person.bankPer.orgNavi 机构导航
				addBankPerTitle:'Add information of bank person ',//Eway.locale.person.bankPer.addBankPerTitle 增加银行人员信息
				updateBankPerTitle:'Modify information of bank person '//Eway.locale.person.bankPer.updateBankPerTitle 更改银行人员信息
				
			},
			serviceOrg:{
				title:'Maintenance',//Eway.locale.person.serviceOrg.title 维护商管理
				serviceNavi:'Manufacturer Navigator',//Eway.locale.person.serviceOrg.serviceNavi 厂商导航
				serviceOrgAdmin:'Manager',//Eway.locale.person.serviceOrg.serviceOrgAdmin 管理员
				setManager:'Setting',//Eway.locale.person.serviceOrg.setManager 设置
				removeManager:'Delete',//Eway.locale.person.serviceOrg.removeManager 删除
				code:'Manufacturer No',//Eway.locale.person.serviceOrg.code 厂商编号
				name:'Manufacturer name',//Eway.locale.person.serviceOrg.name 厂商名称
				zip:'Zipcode',//Eway.locale.person.serviceOrg.zip 邮政编码
				shortName:'Manufacturer',//Eway.locale.person.serviceOrg.shortName 厂商
				address:'Manufacturer address',//Eway.locale.person.serviceOrg.address 厂商地址
				description:'Manufacturer description',//Eway.locale.person.serviceOrg.description 厂商描述
				addServiceTitle:'Add manufacturer',//Eway.locale.person.serviceOrg.addServiceTitle 增加维护商信息
				upgradeService:'Manufacturer upgrade',//Eway.locale.person.serviceOrg.upgradeService 上级厂商
				updateServiceTitle:'Modify manufacturer',//Eway.locale.person.serviceOrg.updateServiceTitle 更改维护商信息
				personDevSerLink:'Person and device which link this manufacturer',//Eway.locale.person.serviceOrg.personDevSerLink 该厂商的关联设备和人员
				devSerLink:'Device under the manufacturer',//Eway.locale.person.serviceOrg.devSerLink 该厂商下设备
				personSerLink:'Person under the manufacturer:',//Eway.locale.person.serviceOrg.personSerLink 该厂商下人员

				directOrganization:'的直接下级机构'
			},
			servicePer:{
				title:'Maintenance person',//Eway.locale.person.servicePer.title 维护人员管理
				servicePerlink:'Link device',//Eway.locale.person.servicePer.servicePerlink 关联设备
				addServicePerTitle:'Add maintenance person',//Eway.locale.person.servicePer.addServicePerTitle 增加维护人员信息
				updateServicePerTitle:'Modify maintenance person',//Eway.locale.person.servicePer.updateServicePerTitle 更改维护人员信息

				maintainInfo:'All information of maintenance person'//所有维护商人员信息
			},
			user:{
				title:'User',//Eway.locale.person.user.title 用户管理
				code:'User name',//Eway.locale.person.user.code 用户名
				clickToCheckLog:'Click to check',//Eway.locale.person.user.clickToCheckLog 单击即可查看用户
				userLog:' s log',//Eway.locale.person.user.userLog  的操作日志
				resetPasswd:'Reset password',//Eway.locale.person.user.resetPasswd 密码重置
				clickToPasswdInit:'Click to set the password to initialize',//Eway.locale.person.user.clickToPasswdInit 单击即可密码重置为初始化密码
				clickToRole:'Click to check all roles list',//Eway.locale.person.user.clickToRole 单击即可查看所有角色列表
				addUserTitle:'Add user information',//Eway.locale.person.user.addUserTitle 增加用户信息
				clickToUser:'Please click query ,choose a  person',//Eway.locale.person.user.clickToUser 请点击查询，选择人员
				userType:'User type',//Eway.locale.person.user.userType 用户类型
				role:'Role',//Eway.locale.person.user.role 角色
				roleGiven:'Role given',//Eway.locale.person.user.roleGiven 角色赋予
				roleName:'Role name',//Eway.locale.person.user.roleName 角色名称
				roleDescription:'Role description',//Eway.locale.person.user.roleDescription 角色描述
				userListTitle:'User list',//Eway.locale.person.user.userListTitle 人员列表
				updateUserTitle:'Modify user role(use drafting way)',//Eway.locale.person.user.updateUserTitle 更改用户角色（使用拖拽的方式）
				roleCanBeAdd:'Role list can be added',//Eway.locale.person.user.roleCanBeAdd 可添加的角色列表
				roleAlreadyBeAdd:'Role list already be added',//Eway.locale.person.user.roleAlreadyBeAdd 已添加的角色列表
				operCode:'Operator no',//Eway.locale.person.user.operCode 操作人编号
				operName:'Operator name',//Eway.locale.person.user.operName 操作人姓名
				operTime:'Operate time',//Eway.locale.person.user.operTime 操作时间
				operResult:'Operate result',//Eway.locale.person.user.operResult 操作结果
				operContent:'Operate content',//Eway.locale.person.user.operContent 操作内容
				roleListTitle:'User roles list',//Eway.locale.person.user.roleListTitle 用户角色列表
				operTitle:'Operator log',//Eway.locale.person.user.operTitle 操作员日志
				operDetailTitle:'Operate logs',//Eway.locale.person.user.operDetailTitle 操作日志信息
				operLogList:'Operate logs list',//Eway.locale.person.user.operLogList 操作日志列表
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
				chooseRight:'Please choose permission of menu',//Eway.locale.permission.role.chooseRight 请选择菜单权限
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
				monitorState:'Monitor status',//Eway.locale.monitor.devMonitor.monitorState 监控状态
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
					boxInitCount:'Initial amount in cashbox',//Eway.locale.monitor.devMonitor.cash.boxInitCount 钞箱初始金额
					boxCurrentCount:'Current amount in cashbox',//Eway.locale.monitor.devMonitor.cash.boxCurrentCount 钞箱当前金额
					cashboxLimit:'Value of cash limit alarm',//Eway.locale.monitor.devMonitor.cash.cashboxLimit 钞箱报警金额阈值
					initAmount:'Total initial amount',//Eway.locale.monitor.devMonitor.cash.initAmount 加钞总金额
					amount:'Amount left',//Eway.locale.monitor.devMonitor.cash.amount
					dispenseAmount:'Amount paid',//Eway.locale.monitor.devMonitor.cash.dispenseAmount 出钞总金额
					rejectAmount:'Amount invalid',//Eway.locale.monitor.devMonitor.cash.rejectAmount 废钞金额
					retractCount:'Time of cash retrieve',//Eway.locale.monitor.devMonitor.cash.retractCount 钞票回收次数
					minAmount:'Amount mini draw',//Eway.locale.monitor.devMonitor.cash.minAmount 最小取款金额
					boxId:'Cashbox ID',//Eway.locale.monitor.devMonitor.cash.boxId 钞箱标识
					type:'Cashbox type',//Eway.locale.monitor.devMonitor.cash.type 钞箱类型
					initialCount:'Initial count',//Eway.locale.monitor.devMonitor.cash.initialCount 初始张数
					cashInCount:'Deposit count',//Eway.locale.monitor.devMonitor.cash.cashInCount 存款张数
					currentCount:'Count',//Eway.locale.monitor.devMonitor.cash.currentCount 当前计数
					noteValue:'Cashbox denomination',//Eway.locale.monitor.devMonitor.cash.noteValue 钞箱面值
					currency:'Cashbox currency',//Eway.locale.monitor.devMonitor.cash.currency 钞箱币种
					boxDetail:'Cashbox detail',//Eway.locale.monitor.devMonitor.cash.boxDetail 钞箱详情
					cimFull:'Cashbox full',//Eway.locale.monitor.devMonitor.cash.cimFull 存款钞满
					cdmEmpty:'Draw cash empty',//Eway.locale.monitor.devMonitor.cash.cdmEmpty 取款钞空
					cdmLow:'Draw cash low',//Eway.locale.monitor.devMonitor.cash.cdmLow 取款钞少
					low:'Cash empty',//Eway.locale.monitor.devMonitor.cash.low 钞少
					empty:'Cash empty',//Eway.locale.monitor.devMonitor.cash.empty 钞空
					cimAFull:'Deposit almost full',//Eway.locale.monitor.devMonitor.cash.cimAFull 存款钞将满
					cashFault:'Cashbox error',//Eway.locale.monitor.devMonitor.cash.cashFault 钞箱故障
					cashUnknow:'Cashbox unknown'//Eway.locale.monitor.devMonitor.cash.cashUnknow 钞箱未知
					
				},
				modStateGraphic:'Module status graphic',//Eway.locale.monitor.devMonitor.modStateGraphic 模块状态图示
				modGraphic:'Module graphic',//Eway.locale.monitor.devMonitor.modGraphic
				registerStatus:'Register status',//Eway.locale.monitor.devMonitor.registerStatus 注册状态
				devModStatus:'Status of module',//Eway.locale.monitor.devMonitor.devModStatus 设备模块状态
				mod:{
					idc:'Card reader',//Eway.locale.monitor.devMonitor.mod.idc 读卡器
					jpr:'Log printer',//Eway.locale.monitor.devMonitor.mod.jpr 日志打印机
					cdm:'Draw module',//Eway.locale.monitor.devMonitor.mod.cdm 取款模块
					cim:'Deposit module',//Eway.locale.monitor.devMonitor.mod.cim 存款模块
					siu:'Sensor',//Eway.locale.monitor.devMonitor.mod.siu 传感器
					rpr:'凭条打印机',//Eway.locale.monitor.devMonitor.mod.rpr 凭条打印机
					pin:'Pin keyboard',//Eway.locale.monitor.devMonitor.mod.pin 密码键盘
					ttu:'Text terminal',//Eway.locale.monitor.devMonitor.mod.ttu 文本终端
					isc:'I.D.card scanner',//Eway.locale.monitor.devMonitor.mod.isc 身份证扫描仪
					icc:'发卡器',//Eway.locale.monitor.devMonitor.mod.icc 发卡器
					fgp:'Fingerprints',//Eway.locale.monitor.devMonitor.mod.fgp 指纹仪
					healthy:'Healthy'//Eway.locale.monitor.devMonitor.mod.healthy 模块正常
					
				},
				remote:{
					control:'Remote control',//Eway.locale.monitor.devMonitor.remote.control 远程控制
					screen:'Remote screenshot',//Eway.locale.monitor.devMonitor.remote.screen 远程抓拍
					log:'Get logs',//Eway.locale.monitor.devMonitor.remote.log 提取电子日志
					net:'Check network',//Eway.locale.monitor.devMonitor.remote.net 查看网络连接
					softwareList:'Get software list',//Eway.locale.monitor.devMonitor.remote.softwareList 获取软件列表
					powerOff:'PowerOff',//Eway.locale.monitor.devMonitor.remote.powerOff 关机
					closeWays:'Choose way of poweroff',//Eway.locale.monitor.devMonitor.remote.closeWays 请选择关机方式
					restart:'Reboot',//Eway.locale.monitor.devMonitor.remote.restart 重启
					restartWay:'Choose way of reboot',//Eway.locale.monitor.devMonitor.remote.restartWay 请选择重启方式
					logicOpen:'Open service',//Eway.locale.monitor.devMonitor.remote.logicOpen 开启服务
					logicClose:'Pause service',//Eway.locale.monitor.devMonitor.remote.logicClose 暂停服务
					remoteBrowser:'Remote browse',//Eway.locale.monitor.devMonitor.remote.remoteBrowser 远程浏览
					processList:'Check process',//Eway.locale.monitor.devMonitor.remote.processList 查看进程信息
					screenCamera:'Screen recording',//Eway.locale.monitor.devMonitor.remote.screenCamera 屏幕录制
					reset:'Force reset',//Eway.locale.monitor.devMonitor.remote.reset 强制复位
					remoteLook:'Check version application',//Eway.locale.monitor.devMonitor.remote.remoteLook 查看应用版本
					remoteCheckATM:'Physical of ATM',//Eway.locale.monitor.devMonitor.remote.remoteCheckATM ATM体检
					halfSer:'Half service',//Eway.locale.monitor.devMonitor.remote.halfSer 半功能
					healthy:'Healthy',//Eway.locale.monitor.devMonitor.remote.healthy 正常服务
					staff:'Maintenance',//Eway.locale.monitor.devMonitor.remote.staff 维护
					pFault:'交易前置故障',//Eway.locale.monitor.devMonitor.remote.pFault 
					stop:'Stop',//Eway.locale.monitor.devMonitor.remote.stop 报停
					manualStop:'Stop manual',////Eway.locale.monitor.devMonitor.remote.manualStop 人工报停
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
			indexPage:'Home',//Eway.locale.index.indexPage 首页
			dailyFaultPic:'日均故障趋势图',//Eway.locale.index.dailyFaultPic
			faultAmount:' 产生的故障数量: ',//Eway.locale.index.faultAmount
			devStatusDisPic:'设备状态分布图',//Eway.locale.index.devStatusDisPic
			normalDev:'正常设备',//Eway.locale.index.normalDev
			unknownDev:'Device-unknown',//Eway.locale.index.unknownDev
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
