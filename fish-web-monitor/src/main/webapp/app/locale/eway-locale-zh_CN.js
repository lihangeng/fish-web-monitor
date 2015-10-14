Ext.override(Eway.locale,{
	/*addSuccess : '增加成功.',//Eway.addSuccess
	updateSuccess : '更改成功.',//Eway.updateSuccess
	deleteSuccess : '删除成功.',//Eway.deleteSuccess
	choiceUpdateMsg :'请选择您要更改的记录.',//Eway.choiceUpdateMsg
	choiceDeleteMsg :'请选择您要删除的记录.',//Eway.choiceDeleteMsg
	locale:{*/
		myTable:'我的工作台',//Eway.locale.myTable
		ATMV:'自助设备监控系统(ATMV)',//Eway.locale.ATMV
		welcome:'欢迎你,',//Eway.locale.welcome
		personalConf：'个人设置',//Eway.locale.personalConf
		systemHelp:'系统帮助',///Eway.locale.systemHelp
		exitSystem:'退出系统',//Eway.locale.exitSystem
		button:{
			search:'查询XX',
			add : '增加XX',
			update:'更改XX',
			remove:'删除XX',
			refresh:'刷新XX',
			reset:'重置XX',//Eway.locale.button.reset
			back:'返回XX',
			apply:'应用XX',
			//bankOrg
			deepQuery:'深度查询XX',
			bankOrgMove:'组织迁移XX',
			bankOrgAdmin:'管理员XX',
			//bankPerson
			bankPerlink:'绑定设备XX',
			sure:'确定',//Eway.locale.button.sure
			confirm:'确认XX',//Eway.locale.button.confirm
			cancle:'取消XX',//Eway.locale.button.cancle
			choose:'选择',//Eway.locale.button.choose
			pause:'暂停'//Eway.locale.button.pause
				
			exported:'导出XX',
			select:'选择XX',
			info :'详细信息XX',
			move:'移机XX',
			exportXLS:'导出XLS',
			exportPDF:'导出PDF',
			massExport:'批量导入@@@@@@',
		},
		
		//**********************************************************/
		combox:{
			select:'--请选择--',
			explorer: '浏览...@@@@@@',
		},
		
		//**********************************************************/
		tip:{
			search :{
				warn:'查询条件存在错误项XX.',
				record:'请选择您要查看的记录.XX'
			},
			update:{
				one:'只能选择一条记录更改XX.',
				two:'此条记录不能被更改.XX'
			},
			remove :{
				none:'请选择您要删除的记录.XX',
				one:'只能选择一条记录删除XX',
				confirm:{
					title:'请确认XX',
					info:'是否删除该记录?XX'
				},
				error:'删除失败:XX'
			},
			own:{
				have:'有XX',
				nothing:'无XX'
			},
			right:{
				yes:'是XX',
				no:'否XX'
			},	
			success:'成功.XX',
			fail:'失败:XX',
			phone:'请输入正确的电话号码XX',
			remind:'提示XX',
			displayMessage:'总共：{2}条，显示{0}-{1}XX',
			
			unCertain:'未知XX',
			searchOfNoLegal:'查询项中存在不合法的输入,不能提交.XX',
			choseExportDevInfo:'请选择要导出信息的设备XX',
			nowLink:'正在连接......XX',
			linkFailure:'连接失败.XX',
			inputError:'输入有误.XX',
			numberExist:'此编号已经存在,请重新输入.XX',
			isConfirmRemove:'删除分组,关联关系也被删除,是否真的要删除指定分组?XX',
			noGroupInfo:'没有组信息,无法查询.XX',
			selectAdd:'请选择您要增加的记录.XX',
			continueAdd:'添加成功,是否继续向组内添加设备?XX',
			addFail:'添加失败.XX',
			isRemoveDev:'是否从该组移除该设备?XX',
			removeFail:'移除失败.XX',
			selectRemoveGroup:'请选择您要移除的设备所在组.XX',
			selectRemoveDev:'请选择您要移除的设备.XX',
			selectMoveDev:'请选择要移动的设备.XX',
			moveSuc:'移机成功.XX',
			dateReSelect:'开始时间不能大于结束日期,请重新选择XX',
			selectPlan:'请选择您应用的方案.XX',
			removeFail:'解除失败.XX',
			selectRemoveDev:'请选择要解除的设备.',
			relatedFail:'关联失败.',
			selectRelatedDev:'请选择要关联的设备.',
			planNoUpdate:'该方案已经执行,不可修改.',
			planNoRemove:'该方案已经执行,不可删除.',
			exportFiles: '@@请选择导入文件,只支持.xls和.xlsx格式的文件',
			
		},
		
		//**********************************************************/
		
		vtype:{
			ip:'请输入正确的IP地址',
			zip:'请输入正确的邮编格式，6位的数字',
			versionNo:'不是正确的版本号格式,格式说明：1.版本号由4个部分组成 A.B.C.D ;2.只有A部分是必须的 ；3. A、B、C、D必须为大于等于0的整数 ,每个部分最大长度为8位； 4.ABCD部分必须用.分隔',
			terminalId:'输入错误,设备号由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’点号‘.’组成,只能以字母或数字开头,长度1到20位。',
			mobile:'输入错误,手机号码只能输入8到11位数字。',
			cardNo:'输入错误,银行卡号只能输入16到19位数字。',
			telephone:'输入错误,固定电话号码只能输入8到11位数字。',
			daterange:'日期段不正确.',
			numberrange:'金额范围不正确.',
			
			bankOrgCode:'只能输入1到20字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’、点号‘.’， 只能以字母或数字开头！XX',
			zip:'只能输入6个‘0-9’的数字！XX',
			maxLength20:'允许的最大长度为20XX',
			numberRule: '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’和点号‘.’，只能以字母或数字开头。XX',
			numberRulesOne: '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’和点号‘.’、汉字，只能以汉字,字母或数字开头,最多可输入100位XX',
			numberRulesFour	: '由字母‘a-z’或‘A-Z’、数字‘0-9’，最多可输入 40位XX',
			numberRules: '由字母‘a-z’或‘A-Z’、数字‘0-9’、减号‘-’、下划线‘_’和点号‘.’、汉字，只能以汉字,字母或数字开头,最多可输入200位XX',
			
			
			mobileRules:'手机电话号码只能输入8到11位数字‘0-9’XX',
			choseDev:'请选择您报停的设备.XX',
			dataLoad:'正在加载数据......XX',
			devLinkNormal:'请检查与设备的连接是否正常.XX',
			hardwayInitialize:'硬件模块正在初始化......XX',
			inputCorrect:'请正确输入.XX',
			exportRepError:'导出报表出错，请重新操作!XX',
			planOutdate:'(此方案已过期，不可应用！)',
			
			emailRules: 'email必须符合*@*.*标准。@@@@',
			notifyTimesRules: '通知次数必须为数字，最小值为0,最大值为100。@@@',
			sendTimesRules: '发送次数必须为数字，最小值为0,最大值为100。@@@@@'	,
			
			launchTranscribe:'正在启动录制......$$',
			stopTranscribe:'正在停止录制......',
			inexistenceScreen:'不存在此屏幕$$',
			devEmploy:'该台设备已经被$$',
			userEmploy:'用户占用!$$',
			loadTranscribe:'正在下载录制好的视频文件......',
			remoteFailure:'远程浏览失败',
			versionChart:'版本下发历史状态分布图',
			openRefresh:'开启自动刷新',
			choseTask:'请选择一个作业',
			cancelTask:'不能撤销"完成"状态的作业.',
			cancelParticularTask:'是否真的要撤销指定的作业?(正在运行的作业只会撤销还没有运行的任务.)',
			nowDelete:'正在删除......',
		},
		
		//**********************************************************/
		commen:{
			
			jobNum:'工号XX',//Eway.locale.commen.jobNum
			name:'姓名XX',//Eway.locale.commen.name
			personJobName:'岗位XX',//Eway.locale.commen.personJobName
			state:'状态XX',//Eway.locale.commen.state
			birthday:'生日XX',//Eway.locale.commen.birthday
			comboxStatus:{
				onJob:'在岗XX',//Eway.locale.commen.comboxStatus.onJob
				onAdjust:'调休XX',//Eway.locale.commen.comboxStatus.onAdjust
				onVacation:'休假XX',//Eway.locale.commen.comboxStatus.onVacation
				other:'其他XX',//Eway.locale.commen.comboxStatus.other
				dredge:'开通XX',//Eway.locale.commen.comboxStatus.dredge
				open:'启用XX',//Eway.locale.commen.comboxStatus.open
				close:'停用XX',//Eway.locale.commen.comboxStatus.close
			},
			type:'类型XX',//Eway.locale.commen.type
			comboxType:{
				machineManager:'管机员XX',//Eway.locale.commen.comboxType.machineManager
				machineRepairer:'维修人员'//Eway.locale.commen.comboxType.machineRepairer
			},
			mobile:'手机XX',//Eway.locale.commen.mobile
			email:'邮箱XX',//Eway.locale.commen.email
			phone:'固话XX',//Eway.locale.commen.phone
			gender:'性别XX',//Eway.locale.commen.gender
			all:'全部XX',//Eway.locale.commen.all
			comboxGender:{
				male:'男XX',//Eway.locale.commen.comboxGender.male
				female:'女XX',//Eway.locale.commen.comboxGender.female
				unknow:'未知'//Eway.locale.commen.comboxGender.unknow
			},
			remark:'备注XX',//Eway.locale.commen.remark
			terminalId:'设备号XX',//Eway.locale.commen.terminalId
			ip:'网络地址XX',//Eway.locale.commen.ip
			orgNameBelongs:'所属机构XX',//Eway.locale.commen.orgNameBelongs
			devTypeName:'设备型号XX',//Eway.locale.commen.devTypeName
			devVendorName:'设备品牌XX',//Eway.locale.commen.devVendorName
			devCatalogName:'设备类型XX',//Eway.locale.commen.devCatalogName
			devStatus:'设备状态XX',//Eway.locale.commen.devStatus
			comboxDevStatus:{
				open:'开通XX',//Eway.locale.commen.comboxDevStatus.open
				stop:'停用'//Eway.locale.commen.comboxDevStatus.stop
			},
			setManager:'设置XX',//Eway.locale.commen.setManager
			devServiceName:'设备维护商XX',//Eway.locale.commen.devServiceName
			cashboxLimit:'钞箱报警金额XX',//Eway.locale.commen.cashboxLimit
			installDate:'安装日期XX',//Eway.locale.commen.installDate
			address:'地址XX',//Eway.locale.commen.address
			areaCode:'区域编号XX',//Eway.locale.commen.areaCode
			areaName:'区域名称XX',//Eway.locale.commen.areaName
			toolbar:'总共：{2}条，显示{0}-{1}XX',//Eway.locale.commen.toolbar
			bindMachine :'已关联的设备XX',//Eway.locale.commen.bindMachine
			lift:'解除XX',//Eway.locale.commen.lift
			canBindMachine:'可关联的设备XX',//Eway.locale.commen.canBindMachine
			bind:'关联XX',//Eway.locale.commen.bind
			filter:'过滤条件XX',//Eway.locale.commen.filter
			stateDict:{
				newCreate:'新建XX',//Eway.locale.commen.stateDict.newCreate
				normal:'正常XX',//Eway.locale.commen.stateDict.normal
				locked:'锁定XX',//Eway.locale.commen.stateDict.locked
				disable:'无效XX',//Eway.locale.commen.stateDict.disable
				frozen:'冻结XX',//Eway.locale.commen.stateDict.frozen
				deleted:'已删除XX'//Eway.locale.commen.stateDict.deleted
			},
			yes:'是XX',//Eway.locale.commen.yes
			no:'否XX',//Eway.locale.commen.no
			selectAll:'全部选择XX',//Eway.locale.commen.selectAll
			selectNon:'全部不选XX',//Eway.locale.commen.selectNon
			content:'消息XX',//Eway.locale.commen.content
			upgrade:'上级XX',//Eway.locale.commen.upgrade
			port:'网络PortXX',//Eway.locale.commen.port
			previous:'上一页XX',//Eway.locale.commen.previous
			next:'下一页XX',//Eway.locale.commen.next
			installAddr:'装机地址XX',//Eway.locale.commen.installAddr
			seviceMode:'经营方式XX',//Eway.locale.commen.seviceMode
			insideOutside:'在行标志XX',//Eway.locale.commen.insideOutside
			appVersion:'应用版本号XX',//Eway.locale.commen.appVersion
			devInfo:'设备基本信息XX',//Eway.locale.commen.devInfo
			//check end
			personnel:'联系人XX',//Eway.locale.commen.personnel
			warn:'警告XX',//Eway.locale.commen.warn
			fatal:'故障XX',//Eway.locale.commen.fatal
			unStable:'不稳定XX',//Eway.locale.commen.unStable
			unknow:'未知XX',//Eway.locale.commen.unknow
			description:'描述XX',//Eway.locale.commen.description
			info:'详细信息XX',//Eway.locale.commen.info
			startDataTime:'开始时间XXXX',//Eway.locale.commen.startDataTime
			endDataTime:'结束时间XXXX',//Eway.locale.commen.endDataTime
			year:'年XXXX',//Eway.locale.commen.year
			month:'月XXXX',//Eway.locale.commen.month
			day:'日XXXX',//Eway.locale.commen.day
			yearTime:'年份XXXX',//Eway.locale.commen.yearTime
			monthTime:'月份XX',//Eway.locale.commen.monthTime
			dayTime:'日期XX',//Eway.locale.commen.dayTime
			orgFramework:'组织机构XX',//Eway.locale.commen.orgFramework
			matchOrg:'匹配机构XX',//Eway.locale.commen.matchOrg
			orgID:'机构IDXX',//Eway.locale.commen.orgID
			endValidty:'截止有效期',//Eway.locale.commen.endValidty
			publishDate:'发布日期'//Eway.locale.commen.publishDate
			announceTheme:'公告主题',//Eway.locale.commen.announceTheme
			
			
			
		},
		//**********************************************************/
		person:{
			bankOrg :{
				title:'银行机构管理',//Eway.locale.person.bankOrg.title
				moveBankTitle:'组织迁移',//Eway.locale.person.bankOrg.moveBankTitle
				updateBankTitle:'更改银行机构信息',//Eway.locale.person.bankOrg.updateBankTitle
				addBankOrgTitle:'增加银行机构信息',//Eway.locale.person.bankOrg.addBankOrgTitle
				code:'机构编号',//Eway.locale.person.bankOrg.code
				orgType:'机构类型',//Eway.locale.person.bankOrg.orgType
				name:'机构名称',//Eway.locale.person.bankOrg.name
				orgLevel:'机构级别',//Eway.locale.person.bankOrg.orgLevel
				orgNavi:'机构导航',//Eway.locale.person.bankOrg.orgNavi
				zip:'邮政编码',//Eway.locale.person.bankOrg.zip
				removeManager:'删除',//Eway.locale.person.bankOrg.removeManager
				manager:'管理员',//Eway.locale.person.bankOrg.manager
				address:'机构地址',//Eway.locale.person.bankOrg.address
				upgradeOrg:'上级机构',//Eway.locale.person.bankOrg.upgradeOrg
				description:'机构描述',//Eway.locale.person.bankOrg.description
				serOrganization:'机构描述',//Eway.locale.person.bankOrg.serOrganization
				organizationType:{
					bank:'银行',//Eway.locale.person.bankOrg.organizationType.bank
					serviceOrg:'维护商'//Eway.locale.person.bankOrg.organizationType.serviceOrg
				},
				organizationLevelDict:{
					rootBank:'总行',//Eway.locale.person.bankOrg.organizationLevelDict.rootBank
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

				directOrganization:'的直接下级机构$$'
			},
			servicePer:{
				title:'维护人员管理',//Eway.locale.person.servicePer.title
				servicePerlink:'关联设备',//Eway.locale.person.servicePer.servicePerlink
				addServicePerTitle:'增加维护人员信息',//Eway.locale.person.servicePer.addServicePerTitle
				updateServicePerTitle:'更改维护人员信息',//Eway.locale.person.servicePer.updateServicePerTitle

				maintainInfo:'所有维护商人员信息'
			},
			user:{
				title:'用户管理',//Eway.locale.person.user.title
				code:'用户名',//Eway.locale.person.user.code
				clickToCheckLog:'单击即可查看用户 ',//Eway.locale.person.user.clickToCheckLog
				userLog:' 的操作日志',//Eway.locale.person.user.userLog
				resetPasswd:'密码重置',//Eway.locale.person.user.resetPasswd
				clickToPasswdInit:'单击即可密码重置为初始化密码',//Eway.locale.person.user.clickToPasswdInit
				clickToRole:'单击即可查看所有角色列表',//Eway.locale.person.user.clickToRole
				addUserTitle:'增加用户信息',//Eway.locale.person.user.addUserTitle
				clickToUser:'请点击查询，选择人员',//Eway.locale.person.user.clickToUser
				userType:'用户类型',//Eway.locale.person.user.userType
				role:'角色',//Eway.locale.person.user.role
				roleGiven:'角色赋予',//Eway.locale.person.user.roleGiven
				roleName:'角色名称',//Eway.locale.person.user.roleName
				roleDescription:'角色描述',//Eway.locale.person.user.roleDescription
				userListTitle:'人员列表',//Eway.locale.person.user.userListTitle
				updateUserTitle:'更改用户角色（使用拖拽的方式）',//Eway.locale.person.user.updateUserTitle
				roleCanBeAdd:'可添加的角色列表',//Eway.locale.person.user.roleCanBeAdd
				roleAlreadyBeAdd:'可添加的角色列表',//Eway.locale.person.user.roleAlreadyBeAdd
				operCode:'操作人编号',//Eway.locale.person.user.operCode
				operName:'操作人姓名',//Eway.locale.person.user.operName
				operTime:'操作时间',//Eway.locale.person.user.operTime
				operResult:'操作结果',//Eway.locale.person.user.operResult
				operContent:'操作内容',//Eway.locale.person.user.operContent
				roleListTitle:'用户角色列表',//Eway.locale.person.user.roleListTitle
				operTitle:'操作员日志',//Eway.locale.person.user.operTitle
				operDetailTitle:'操作日志信息',//Eway.locale.person.user.operDetailTitle
				operLogList:'操作日志列表',//Eway.locale.person.user.operLogList
				personDevice:'人员<-->设备',//Eway.locale.person.user.personDevice
				rootUser:'超级用户',//Eway.locale.person.user.rootUser
				generalUser:'普通用户'//Eway.locale.person.user.generalUser
			}
			
		},
		
		//**********************************************************/
		permission:{
			systemMenu:'系统菜单'
			role:{
				title:'角色管理',//Eway.locale.permission.role.title
				update:'更改角色',//Eway.locale.permission.role.update
				name:'角色名称',//Eway.locale.permission.role.name
				type:'角色类型',//Eway.locale.permission.role.type
				description:'角色描述',//Eway.locale.permission.role.description
				isSysRole:'是否是系统内置角色',//Eway.locale.permission.role.isSysRole
				chooseRight:'请选择菜单权限',//Eway.locale.permission.role.chooseRight
				add:'增加角色'//Eway.locale.permission.role.add
			},
			permission:{
				menuName:'菜单名称',//Eway.locale.permission.permission.menuName
				menuDescription:'菜单描述',//Eway.locale.permission.permission.menuDescription
				menuPermission:'菜单权限'//Eway.locale.permission.permission.menuPermission
			}
		},
		
		//**********************************************************/
		monitor:{
			devMonitor:{
				title:'状态监控',//Eway.locale.monitor.devMonitor.title
				comboxStatus:{
					runStatus:'运行状态',//Eway.locale.monitor.devMonitor.comboxStatus.runStatus
					modStatus:'模块状态',//Eway.locale.monitor.devMonitor.comboxStatus.modStatus
					boxStatus:'钞箱状态',//Eway.locale.monitor.devMonitor.comboxStatus.boxStatus
					netStatus:'网络状态'//Eway.locale.monitor.devMonitor.comboxStatus.netStatus
				},
				monitorState:'监控状态',//Eway.locale.monitor.devMonitor.monitorState
				showWay:'展示方式',//Eway.locale.monitor.devMonitor.showWay
				comboxShowWay:{
					matrixPattern:'矩形方式',//Eway.locale.monitor.devMonitor.comboxShowWay.matrixPattern
					maxIconPattern:'超大图标',//Eway.locale.monitor.devMonitor.comboxShowWay.maxIconPattern
					listPattern:'列表方式',//Eway.locale.monitor.devMonitor.comboxShowWay.listPattern
					boxPattern:'钞箱方式'//Eway.locale.monitor.devMonitor.comboxShowWay.boxPattern
				},
				numberfield:'监控台数',//Eway.locale.monitor.devMonitor.numberfield
				noData:'无记录',//Eway.locale.monitor.devMonitor.noData
				retainCardCount:'当前吞卡数量',//Eway.locale.monitor.devMonitor.retainCardCount
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
					idc:'读卡器',//Eway.locale.monitor.devMonitor.mod.idc
					jpr:'日志打印机',//Eway.locale.monitor.devMonitor.mod.jpr
					cdm:'取款模块',//Eway.locale.monitor.devMonitor.mod.cdm
					cim:'存款模块',//Eway.locale.monitor.devMonitor.mod.cim
					siu:'传感器',//Eway.locale.monitor.devMonitor.mod.siu
					rpr:'凭条打印机',//Eway.locale.monitor.devMonitor.mod.rpr
					pin:'密码键盘',//Eway.locale.monitor.devMonitor.mod.pin
					ttu:'文本终端',//Eway.locale.monitor.devMonitor.mod.ttu
					isc:'身份证扫描仪',//Eway.locale.monitor.devMonitor.mod.isc
					icc:'发卡器',//Eway.locale.monitor.devMonitor.mod.icc
					fgp:'指纹仪',//Eway.locale.monitor.devMonitor.mod.fgp
					healthy:'模块正常'//Eway.locale.monitor.devMonitor.mod.healthy
					
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
					stopFault:'暂停服务-模块故障',//Eway.locale.monitor.devMonitor.remote.pauseFault
					stopCash:'暂停服务-未加钞',//Eway.locale.monitor.devMonitor.remote.pauseCash
					pauseSer:'暂停服务',//Eway.locale.monitor.devMonitor.remote.pauseSer
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
				title:'品牌管理XX',
				name: '品牌名称XX',
				country:'生产商国家或地区XX',
				hotline1:'生产商热线1XX',
				hotline2:'生产商热线2XX',
				address:'生产商地址XX',
				status:'生产商状态XX',
				comboxStatus:{
					provider:'设备供应XX',
					maintance:'设备服役XX'
				}
			},
			atmCatalog:{
				title:'ATM分类XX',//Eway.locale.machine.title
				name:'分类名称XX',//Eway.locale.machine.atmCatalog.name
				note:'备注XX',
				addTitle:'增加ATM分类信息XX',
				updateTitle:'更改ATM型号信息XX',
				number:'编号',//Eway.locale.machine.atmCatalog.number
			},
			atmGroup : {
				terminalId:'设备号XX',
				ip: '设备IP地址XX',
				orgName:'所属机构XX',
				devTypeName:'设备型号XX',
				devVendorName:'设备品牌XX',
				devCatalogName:'设备类型XX',
				devGroupName: '设备分组XX',
				status:'设备状态XX',
				comboxStatus:{
					dredge:'开通XX',
					open:'启用XX',
					close:'停用XX'
				},
				awayFlag:'离行标志XX',
				comboxAwayFlag:{
					inBank:'在行自助服务区XX',
					outBank:'离行自助银行XX',
					clickBank:'单机离行自助服务点XX'
				},
				devServiceName:'设备维护商XX',
				cashboxLimit:'钞箱报警金额XX',
				installDate:'安装日期XX',
				address:'地址XX',
				gourpDev:'分组<-->设备XX',
				addTitle: '增加设备组信息XX',
				groupName:'组名XX',
				note:'备注XX',
				updateTitle:'更改设备组信息XX'
			},
			atmModule:{
				moduleName:'模块名称XX',
				note:'备注XX',
				atmModules:'ATM模块XX'	
			},
			atmMove:{
				title:'移机管理XX',
				moveDev:'移机XX',
				moveDevRec:'移动设备并产生移机记录XX',
				moveRecordInfo:'移机记录信息XX',
				waitMove:'待移动的机器XX',
				terminalId:'设备号XX',
				address:'源地址XX',
				orgName:'源机构XX',
				targetAddress:'目标地址XX',
				targetOrganization:'目标机构XX',
				targetPerson:'目标机构负责人XX',
				responsibility:'负责人XX',
				destPerson:'源机构负责人XX',
				date:'日期XX',
				recoverDate:'恢复时间XX',
				notice:'备注XX',
				sAddress:'所属地址XX',
				sOrgName:'所属机构XX',
				to:'至XX'
			},
			atmRuntimeInfo:{
				exportName:'导出XX',
				exportDateRangeText:'开始时间不能大于结束时间XX',
				terminalId:'终端号XX',
				terminalIp:'终端IPXX',
				startDate:'开始时间XX',
				endDate:'结束时间XX',
				exportLast30: '导出最后30天汇总信息XX',
				terminalId:'编号XX',
				netIp:'网络地址XX',
				msgCollect:'客服信息采集XX'
			},
			atmType:{
				title:'设备型号XX',
				atmName:'ATM型号XX',
				name:'设备型号XX',
				devVendorName:'所属品牌XX',
				devCatalogName:'所属类型XX',
				devTerminalName:'所属型号XX',
				spec:'设备规格XX',
				weight:'设备重量XX',
				watt:'平均功率XX',
				no:'编号XX',
				cashtype:'非现金标志XX',
				iscash:'现金XX',
				nocash:'非现金XX'
			},
			device:{
				title:'设备信息管理XX',
				devDetailInfo:'设备模块详细信息XX',
			    IDC:'读卡器模块(IDC)XX',
				JPR:'日志打印机模块(JPR)XX',
				CDM:'取款模块(CDM)XX',
				SIU:'传感器模块(SIU)XX',
				CIM:'存款模块(CIM)XX',
				TTU:'文本终端单元(TTU)XX',
				RPR:'凭条打印机模块(RPR)XX',
				PIN:'密码键盘模块(PIN)XX',
				CDMInfo:'取款模块（CDM）属性信息XX',
				hasStack:'是否具有暂存器XX',
				hasShutter:'是否具有shutter门XX',
				canRetract:'是否具有回收能力XX',
				canDetectCashTaken:'是否探测钞币被拿走XX',
				canTestPhysicalUnits:'是否能测试物理单元XX',
				maxDispensBills:'获取单笔最大挖钞张数XX',
				logicalUnits:'逻辑钞箱个数XX',
				physicalUnits:'物理钞箱个数XX',
				currency:'支持的币种类别总个数XX',
				currencies:'支持的币种类别XX',
				exponents:'指数XX',
				 
				CIMInfo:'存款模块 （CIM）属性信息XX',
				canEscrow:'是否具有暂存器X',
				shutterControlSupported:'是否支持控制shutter门XX',
				maxAcceptItems:'单笔最大验钞张数XX',
				canDetectCashInserted:'是否能探测钞票放入XX',
				canDetectCashTaken:'是否能探测钞票被取走XX',
				retractAreas:'回收位置XX',
				 
				 
				IDCInfo:'读卡器模块(IDC)属性信息XX',
				variant:'读卡器类型XX',
				canEjectCard:'是否具有退卡能力XX',
				trackJisiiRead:'是否具有TrackJisii读能力XX',
				track1Read:'是否具有读一磁道数据能力XX',
				track2Read:'是否具有读二磁道数据能力XX',
				track3Read:'是否具有读三磁道数据能力XX',
				canCapture:'是否具有吞卡能力XX',
				binCapacity:'最大吞卡张数XX',
				security:'是否具有安全支持XX',
				trackJisiiWrite:'是否具有TrackJisii写能力XX',
				track1Write:'是否具有写一磁道数据能力XX',
				track2Write:'是否具有写二磁道数据能力XX',
				track3Write:'是否具有写三磁道数据能力XX',

				 
				JPRInfo:'日志打印机模块(JPR)属性信息XX',
				canEject:'是否具有退纸能力XX',
				canCapture:'是否具有回收能力XX',
				canStack:'是否具有暂存能力XX',

				PINInfo:'密码键盘(PIN)属性信息XX',
				canEBC:'能否EBCXX',
				canCBC:'能否CBCXX',  
				canMAC:'能否MACXX',
				canRSA:'能否RSAXX',
				canVerifyVISA:'能否验证VISAXX',
				canVerifyDES:'能否验证DESXX',
				functionKeys:'功能键支持XX',
				canTripleEBC:'是否支持多重EBCXX',
				canTripleCBC:'是否支持多重CBCXX',
				canTripleMAC:'是否支持多重MACXX',
				canTripleCFB:'是否支持多重CFBXX',
				canVerifyECB:'能否验证ECBXX', 
				canDESOffset:'能否DeS偏移XX',

				RPRInfo:'凭条打印机(RPR)属性信息XX',
				canEject:'是否具有退纸能力XX',
				canCapture:'是否具有回收能力XX',
				canStack:'是否具有暂存能力XX',
				maxRetract:'最大回收张数XX',

				SIUInfo:'SIU能力属性信息XX',
				operatorSwitchSupported:'是否支持操作员开关XX',
				cabinetSupported:'是否支持后盖门打开传感能力XX',
				safeSupported:'是否支持安全门打开传感能力XX',
				indicatorSupported:'是否支持靠近传感能力XX',
				guidelightIdcSupported:'是否支持插卡指示灯能力XX',
				guidelightCdmSupported:'是否支持取款指示灯能力XX',
				guidelightReceiptSupported:'是否支持凭条打印指示灯能力XX',
				guidelightCimSupported:'是否支持存款指示灯能力XX',

				TTUInfo:'文本终端单元(TTU)属性信息XX',
				alphanumericKeysPresent:'是否支持字母数字键输入XX',
				numericKeysPresent:'是否支持数字键输入XX',
				displayLightPresent:'是否支持屏幕亮度调节XX',
				cursorSupported:'是否支持鼠标XX',
				resolutionX:'横轴分辨率XX',
				hexadecimalKeysPresent:'是否支持十六进制键输入XX',
				keyboardLockPresent:'是否支持键盘锁定XX',
				formsSupported:'是否支持表格XX',
				resolutionY:'纵轴分辨率XX',

				comStatus:'厂商状态信息XX',
				hwCode:'厂商故障码XX',
				CDMStatus:'取款模块(CDM)状态信息XX',
				cashUnits:'钞箱状态XX',
				safeDoor:'安全门状态XX',
				intermediateStacker:'暂存器状态XX',
				outBox:'取款钞箱XX',
				pcuId:'物理逻辑钞箱对应关系XX',
				cuId:'逻辑钞箱IDXX',
				cuCurrency:'逻辑钞箱币种XX',
				cuCurrentCount:'逻辑钞箱当前张数XX',
				cuInitialCount:'逻辑钞箱初始张数XX',
				cuRejectCount:'逻辑钞箱reject张数XX',
				cuNoteValue:'逻辑钞箱面值XX',
				cuBinStatus:'逻辑钞箱状态XX',
				puId:'物理钞箱IDXX',
				puPosName:'物理钞箱位置名称XX',
				puBinStatus:'物理钞箱状态XX',
				puCurrentCount:'物理钞箱当前张数XX',
				puInitialCount:'物理钞箱初始张数XX',
				puRejectCount:'物理钞箱Reject张数XX',
				cuBinType:'逻辑钞箱类型XX',

				CIMStatus:'存款模块(CIM)状态信息XX',
				baffle:'挡板状态XX',
				inOutPositionStatus:'传输状态XX',
				inBox:'存款钞箱XX',
				puCashInCount:'物理钞箱入钞张数XX',
				pcuId:'物理钞箱与逻辑钞箱对应关系XX',
				cuType:'逻辑钞箱类型XX',
				cuBinStatus:'逻辑钞箱状态XX',
				cuCurrentCount:'逻辑钞箱当前张数XX',
				cuCurrency:'逻辑钞箱币种XX',
				cuNoteValue:'逻辑钞箱面值XX',

				IDCStatus:'读卡器模块(IDC)状态信息XX',
				media:'媒体状态XX',
				retainBin:'回收盒状态XX',
				cards:'回收盒数量XX',

				JRPStatus:'日志打印机模块(JPR)状态信息XX',
				supplyLevel:'打印纸状态XX',
				ink:'墨水XX',
				toner:'色带XX',

				PINStatus:'密码键盘模块(PIN)状态信息XX',

				RPRStatus:'凭条打印机模块(RPR)状态信息XX',
				bin:'回收单元状态XX',

				SIUStatus:'SIU能力状态信息XX',
				vandalShield:'防护罩状态XX',
				operatorSwitch:'操作员按钮状态XX',
				ambientLight:'环境灯状态XX',
				cabinet:'箱门状态XX',
				safe:'安全门状态XX',
				idcGuidelight:'插卡导引灯状态XX',
				cdmGuidelight:'取钞引导指示灯状态XX',
				receiptGuidelight:'凭条导引灯状态XX',
				cimGuidelight:'CIM导引灯状态XX',

				TTUStatus:'文本终端单元(TTU)状态信息XX',
				
				devPerson:'设备人员信息XX',
				devModuleMsg:'设备模块属性信息XX',
				devBasicMsg:'设备基本信息XX',
				devTailMsg:'设备详细信息XX',
				managePerson:'管机员XX',
				maintainPerson:'维护员XX',
				name:'姓名XX',
				mobile:'手机XX',
				phone:'固定电话XX',
				email:'邮件地址XX',
				deviceBasicInfo:'设备基本信息XX',
				lineLogo:'在行标志XX',
				alarmRateRMB:'钞箱报警金额(人民币)XX',
				operation:'经营方式XX',
				ipAddress:'IP地址XX',
				swallowCard:'吞卡张数',
				alarmRateHKD:'钞箱报警金额(港币)XX',
				adminPhone:'管理员(手机号)XX',
				maintainPhone: '维护员(手机号)XX',
				log:'钞箱标识XX',
				style: '钞箱类型XX',
				status: '钞箱状态XX',
				initailnumber: '初始张数XX',
				postnumber: '存款张数XX',
				currentnumber: '当前钞箱张数XX',
				facevalue: '钞箱面值XX',
				currency: '钞箱币种XX',
				systemHardwareInfo: '系统软硬件信息XX',
				moduleVersionInfo:'模块硬件版本信息（实时）XX',
				devModuleStatusInfo: '设备模块状态（实时）XX',
				devModuleAttributeInfo: '设备模块属性信息（实时）XX',
				
				remoteControl: '远程控制XX',
				collectJPR:'提取日志XX',
				remoteScreen:'远程抓屏XX',
				processCheck:'进程查看XX',
				remoteExplorer:'远程浏览XX',
				netWorkLink:'网络连接XX',
				remoteRestart:'远程重启XX',
				
				progressTip:'进度提示XX',
				updateProBar:'这是通过动态更新内容形成的进度条XX',
				currentProcess:'当前进度XX',
				
				
				restartApply: ' 重启应用XX',
				confirmRestartApply:'确定要重启应用？XX',
				nowRestartApply:'正在重启应用XX',
				restartApplySuc:'成功重启该设备应用XX',
				restartApplyFail:'重启应用失败！XX',
				
				
				restartDrive:'重启硬件驱动XX',
				confirmRestartDrive:'确定要重启硬件驱动？XX',
				nowRestartDrive:'正在重启硬件驱动XX',
				restartDriveSuc:'成功重启该设备硬件驱动XX',
				restartDriveFail:'重启硬件驱动失败！XX',
				
				restartOS:'重启操作系统XX',
				confirmRestartOS:'确定要重启操作系统？XX',
				nowRestartOS:'正在重启操作系统XX',
				restartOSSuc:'成功重启该设备操作系统XX',
				restartOSFail:'重启操作系统失败！XX',
				
				remoteShutdown:'远程关机XX',
				shutdownApply:'关闭应用XX',
				confirmShutdownApply:'确定要关闭应用？XX',
				nowShutdownApply:'正在关闭应用XX',
				shutdownApplySuc:'成功关闭该设备应用XX',
				shutdownApplyFail:'关闭应用失败！XX',
				
				shutdownDrive:'关闭硬件驱动XX',
				confirmShutdownDrive:'确定要关闭硬件驱动？XX',
				nowShutdownDrive:'正在关闭硬件驱动XX',
				shutdownDriveSuc:'成功关闭该设备硬件驱动XX',
				shutdownDriveFail:'关闭硬件驱动失败！XX',
				
				shutdownOS:'关闭操作系统XX',
				confirmShutdownOS:'确定要关闭操作系统？XX',
				nowShutdownOS:'正在关闭操作系统XX',
				shutdownOSSuc:'成功关闭该设备操作系统XX',
				shutdownOSFail:'关闭操作系统失败！XX',
				getSoftwareList:'获取软件安装列表XX',
				forceReset:'强制复位XX',
				openService:'开启服务XX',
				pauseService:'暂停服务XX',
				checkStatus:'状态检测XX',
				
				remoteBrowseDisk:'远程浏览XX',
				
				sysHardwareInfo:'系统硬件信息XX',
				diskMem:'硬盘大小XX',
				biosVersion:'Bios版本XX',
				biosVendor:'Bios厂商XX',
				biosReleaseDate:'Bios发布日期XX',
				memorySize:'内存总数XX',
				memoryUsed:'已使用内存XX',
				memoryFree:'空闲内存XX',
				memoryPercent:'内存使用率XX',
				cpuItemID:'cpu信息XX',
				cpuFrequency:'CPU频率(MHz)XX',
				cpuVendor:'CPU的厂商XX',
				cpuModel:'CPU的类别XX',
				cacheSize:'缓冲存储器数量XX',
				totalCores:'CPU核数XX',
				userUsePercent:'用户使用率XX',
				sysUsePercent:'系统使用率XX',
				idlePercent:'当前空闲率XX',
				combinedPercent:'总的使用率XX',
				diskItemID:'磁盘信息XX',
				diskName:'磁盘分区名称XX',
				diskFileSys:'磁盘文件系统XX',
				diskTotalSize:'磁盘总大小XX',
				diskFreeSize:'磁盘可用空间大小XX',
				sysSoftInfo:'系统软件信息XX',
				OSID:'操作系统IDXX',
				OSDescription:'OS描述XX',
				OSType:'OS类型XX',
				sysPatchLevel:'系统补丁级别XX',
				chkCashData:'验钞数据版本XX',
				OSVendor:'OS供应商XX',
				OSVendorName:'OS供应商名XX',
				sysVersion:'系统版本号XX',
				devAddress:'设备地址XX',
				basicInfo:'基本信息XX',
				virtual:'虚拟设备号XX',
				serial:'设备序列号XX',
				carrier:'运营商XX',
				moneyOrg:'加钞机构XX',
				costInterest:'资金成本利率XX',
				atmcSoft:'atmc软件XX',
				spType:'厂商sp类型XX',
				column:'日期信息XX',
				buyDate:'设备购买日期XX',
				installDate:'设备安装日期XX',
				startDate:'设备启用日期XX',	
				stopDate:'设备停用日期XX',
				expireDate:'保修到期日期XX',
				daliyOpen:'每日开机时间XX',
				openTimeHour:'时XX',
				openTimeMinute:'分XX',
				openTimeSecond:'秒XX',
				daliyClose:'每日关机时间XX',
				lastPmDate:'上次巡检日期XX',
				expirePmDate:'巡检到期日期XX',
				costInfo:'费用信息XX',
				price:'入账成本(元)XX',
				depreciationLife:'折旧年限(年)XX',
				decoration:'装修费用XX',
				decorationCost:'装修摊销年限(年)XX',
				governanceRent:'物业租赁费(元/月)XX',
				governanceCost:'物业管理费用(元/月)XX',
				netCost:'通讯线路费用(元/月)XX',
				powerCost:'电费(元/月)XX',
				moneyCost:'加钞维护费用(元/次)XX',
				statusInfo:'状态信息XX',
				deviceAttention:'设备关注程序XX',
				stress:'重点XX',
				medium:'中等XX',
				ordinary:'一般XX',
				notCashSignal:'非现金标志XX',
				cash: '现金XX',
				notCash:'非现金XX',
				installStyle: '安装方式XX',
				crossWall: '穿墙XX',
				mainRoom: '大堂XX',
				netType: '网络类型XX',
				wired: '有线XX',
				wireless: '无线XX',
				wiredAndWireless: '有线无线XX',
				onBankSignal:'在行离行标志XX',
				inBank:'在行自助服务区XX',
				outBank:'离行自助银行XX',
				clickBank:'单机离行自助服务点XX',
				operation:'经营方式XX',
				operationSelf: '自营XX',
				cooperation: '合作XX',
				epiboly: '外包XX',
				managePerson:'管机员XX',
				maintainPerson:'维护员XX',
				to:'至XX',
				range: '范围1－－100年XX',
				roleDescription:'角色描述XX',
				roleName:'角色名称XX',

				
					
				devices:'设备XX',
				configuration:'配置信息XX',
				spVersion:'SP版本XX',
				notSupport:'不支持XX',
				drive:'驱动XX',
				firmway: '固件XX',
				noDevice:'无设备XX',
				devTypeInfo: '设备型号信息XX',
				
				devInfo:'设备信息$$',
				unable:'不可以',
				able:'能',
				
			},
			param:{
				paramKey:'参数XX',//Eway.locale.machine.param.paramKey
				paramValue:'参数值XX',//Eway.locale.machine.param.paramValue
				classify:'类型XX',//Eway.locale.machine.param.classify
				comboxClassify:{
					unableUpdate:'不可修改XX',
					ableUpdate:'可以修改XX'
				},
				description:'参数信息描述XX',//Eway.locale.machine.param.description
				systemCon:'系统配置XX',//Eway.locale.machine.param.systemCon
				updateSystemCon:'更改系统配置XX'//Eway.locale.machine.param.updateSystemCon
			},
			quittingNotice:{
				addCloseMsg:'增加报停信息XX',
				updateCloseMsg:'更改报停信息XX',
				dateRangeText:'恢复日期不能小于等于停止日期,请重新选择XX',
				click:'请点击查询，选择设备XX',
				stopTime:'停机时间XX',
				openTime:'恢复时间XX',
				currentStatus:'当前状态XX',
				closeType:'停机类型XX',
				responsibilityName:'停机负责人XX',
				stopReason:'停机原因XX',
				address:'所属地址XX',
				selectDev:'选择需要报停的设备XX',
				to:'至XX',
				stopType:'停机类型XX',
				comboxStopType:{
					recess:'放假XX',
					fit:'装修XX',
					power:'停电XX',
					devFailue:'设备故障未修复XX',
					other:'其他XX'	
				},
				setTime:'设置时间XX',
				closeManage:'报停管理XX'
			}
		},
		
		//**********************************************************/
		
		index:{
			indexPage:'首页',//Eway.locale.index.indexPage
			dailyFaultPic:'日均故障趋势图',//Eway.locale.index.dailyFaultPic
			faultAmount:' 产生的故障数量: ',//Eway.locale.index.faultAmount
			devStatusDisPic:'设备状态分布图',//Eway.locale.index.devStatusDisPic
			normalDev:'正常设备',//Eway.locale.index.normalDev
			unknownDev:'未知设备',//Eway.locale.index.unknownDev
			exceptionDev:'异常设备',//Eway.locale.index.exceptionDev
			amount:'台',//Eway.locale.index.amount
		},
		//**********************************************************/
		report:{
			baseReport:{
				date:'加钞日期XX',//Eway.locale.report.baseReport.date
				amt:'加钞金额XX',//Eway.locale.report.baseReport.amt
				boxId:'钞箱IDXX',//Eway.locale.report.baseReport.boxId
				boxCurrency:'币种XX',//Eway.locale.report.baseReport.boxCurrency
				boxInitAmt:'初始金额XX',//Eway.locale.report.baseReport.boxInitAmt
				lastAmt:'剩余金额XX',//Eway.locale.report.baseReport.lastAmt
				cashAddRep:'加钞情况报表XX',//Eway.locale.report.baseReport.cashAddRep
				boxBalanceRep:'钞箱余额报表XX',//Eway.locale.report.baseReport.boxBalanceRep
				sysConfRep:'系统硬件配置报表XX',//Eway.locale.report.baseReport.sysConfRep
				devDetailRep:'设备明细报表XX',//Eway.locale.report.baseReport.devDetailRep
				devBrandRep:'设备品牌统计报表XX',//Eway.locale.report.baseReport.devBrandRep
				devRunInfoRep:'设备运行情况报表XX',//Eway.locale.report.baseReport.devRunInfoRep
				eatCardRep:'吞卡统计报表XX',//Eway.locale.report.baseReport.eatCardRep
				eatCardDetailRep:'吞卡明细报表XX',//Eway.locale.report.baseReport.eatCardDetailRep
				clearDate:'清机日期XX',//Eway.locale.report.baseReport.clearDate
				clearTable:'清机情况报表XX',//Eway.locale.report.baseReport.clearTable
				dependDev:'按设备XX',//Eway.locale.report.baseReport.dependDev
				tradeRep:'交易统计报表XX',//Eway.locale.report.baseReport.tradeRep
				tradeResultRep:'交易结果统计报表XX',//Eway.locale.report.baseReport.tradeResultRep
			},
			openrate:{
				device:{
					statisticsMethod:'统计方式XX',//Eway.locale.report.openrate.device.statisticsMethod
					statistics:'统计XX',//Eway.locale.report.openrate.device.statistics
					importStat:'导出XX',//Eway.locale.report.openrate.device.importStat
					statDate:'统计日期XX',//Eway.locale.report.openrate.device.statDate
					openTimes:'设备应工作时长XX',//Eway.locale.report.openrate.device.openTimes
					healthyTimeReal:'正常状态时长XX',//Eway.locale.report.openrate.device.healthyTimeReal
					maintainTimeReal:'管机员维护时长XX',//Eway.locale.report.openrate.device.maintainTimeReal
					unknownTimeReal:'离线未知时长XX',//Eway.locale.report.openrate.device.unknownTimeReal
					faultTimeReal:'硬件故障停机时长XX',//Eway.locale.report.openrate.device.faultTimeReal
					atmpTimeReal:'ATMP故障时长XX',//Eway.locale.report.openrate.device.atmpTimeReal
					stopTimeReal:'其它暂停服务状态时长XX',//Eway.locale.report.openrate.device.stopTimeReal
					openRate:'实际工作开机率XX',//Eway.locale.report.openrate.device.openRate
					devOpenRate:'设备开机率XX',//Eway.locale.report.openrate.device.devOpenRate
					organizationName:'机构XX',//Eway.locale.report.openrate.device.organizationName
				},
				org:{
					orgOpenRate:'机构开机率XX',//Eway.locale.report.openrate.org.orgOpenRate
				},
				type:{
					terminalId:'型号XX',//Eway.locale.report.openrate.type.terminalId
					typeOpenRate:'型号开机率XX',//Eway.locale.report.openrate.type.typeOpenRate
				},
			},
			plan:{
				addPlan:'增加方案XX',//Eway.locale.report.plan.addPlan
				name:'名称XX',//Eway.locale.report.plan.name
				startDate:'有效开始时间XX',//Eway.locale.report.plan.startDate
				endDate:'有效结束时间XX',//Eway.locale.report.plan.endDate
				terminalId:'编号XX',//Eway.locale.report.plan.terminalId
				cashboxLimit:'钞箱报警金额(单位：张数)XX',//Eway.locale.report.plan.cashboxLimit
				perToDev:'人员<-->设备XX',//Eway.locale.report.plan.perToDev
				changePlan:'更改方案XX',//Eway.locale.report.plan.changePlan
				openPlan:'开机方案',//Eway.locale.report.plan.openPlan
			}
		},
		
		//**********************************************************/
		card:{
			cardNum:'卡号XX',//Eway.locale.card.cardNum
			onlyNumber:'只能输入数字,13-19位XX',//Eway.locale.card.onlyNumber
			cardStatus:'卡片状态XX',//Eway.locale.card.cardStatus
			eatCardTime:'吞卡时间XX',//Eway.locale.card.eatCardTime
			IDType:'证件类型XX',//Eway.locale.card.IDType
			customerName:'客户姓名XX',//Eway.locale.card.customerName
			customerPapers:'客户证件号XX',//Eway.locale.card.customerPapers
			customerPhone:'客户电话XX',//Eway.locale.card.customerPhone
			endData:'吞卡截止日期XX',//Eway.locale.card.endData
			startData:'吞卡起始日期XX',//Eway.locale.card.startData
		}
		
		//**********************************************************/
		
		cases:{
			confirm:'确认@@',
			cancel:'取消@@',
			concern:'请关注',
			SRCBView:'上海农商行新监控发送',
			nowExportFile:'正在导入文件',
			exportFaultInfo:'导入厂商故障信息成功.',
			caseFault:{
				faultRelevantInfo:'故障相关短信@@',
				faultModule:'故障模块@@',
				cardReaderModule:'读卡器模块@@',
				depoistModule:'存款模块@@',
				drawModule:'取款模块@@',
				rprModule:'凭条打印模块@@',
				jprModule:'日志打印模块@@',
				pinModule:'密码键盘模块@@',
				textTerminalUnit:'文本终端单元@@',
				sensoModule:'传感器模块@@',
				faultClassify: '故障分类@@',
				faultCode : '故障码@@',
				providerFaultCode: '厂商故障码@@',
				faultStartTime : '故障开始时间@@',
				faultCloseTime : '故障关闭时间@@',
				faultContinueTime : '持续时长@@',
			    faultState : '故障状态@@',
			    status:{
			    	open:'未关闭@@',
			    	close:'已关闭@@'
			    },
			    upgradeTimes: '升级次数@@',
			    message: '短信@@',
			    checkDetails: '查看详情@@',
			    bankPer: '银行联系人@@',
			    serPer: '供应商联系人@@',
			    createTime: '创建时间@@',
			    informContent: '通知内容@@',
			    messageContentDetail: '短信内容详情@@',
			    informWay: '通知方式@@',
			    mail:'邮件@@',
			    messageAndMail:'短信和邮件@@',
			    informMobile: '通知手机号@@',
			    notifyTimes: '通知次数@@',
			    notifyRepeatTimes: '重复通知次数@@',
			    sendTimes: '发送次数@@',
			    sendInterval: '发送时间间隔@@',
			    sendTime: '发送时间@@',
			    faultSearch:'故障查询@@'
			    
			},
			caseNotify:{
				fault:'故障@@',
				faultDetails:'故障详情@@',
				faultlastTime: '故障持续时长(单位:小时)@@',
				checkFailure:'查看失败！@@',
				innerFault:'内部错误@@',
				messageCheck:'短信查询@@'
			},
			faultClassify:{
				faultClassifyName: '故障分类名称@@',
				faultresponsorType: '故障责任人类型@@',
				maintain:'维护员@@',
				manageAndMaintain:'管机员和维护员@@ ',
				upGradeTimes: '最高升级次数@@',
				faultInformWay:'故障通知方式@@',
				faultCloseInterval:'故障规定关闭时间间隔（单位:小时）@@',
				faultTypeConfiguration: '故障类型配置@@',
				updateFaultTypeConfiguration: '更改故障类型配置@@',
				number:'由数字‘0-9’,‘.’组成',
				informNumber:'通知次数不能为0,由数字‘0-9’组成,1-5位'
			},
			notifyMould:{
				noticeType:'通知类型@@',
				createNotice:'创建通知@@',
				upgradeNotice:'升级通知@@',
				closeNotice:'关闭通知@@',
				noticeValue: '通知参数@@',
				messageContentConfiguration:'短信内容配置@@',
				updateMessageContentConfiguration: '更改短信内容配置@@',
				necessaryOption: '此项为必选项@@',
				faultType:'故障类型@@',
				applyStatus:'应用状态@@'
			},
			vendorCode:{
				exportProviderInfo: '导入厂商故障信息@@',
				provider:'厂商@@',
				exportFile: '导入文件@@',
				deleteFaultInfo:'删除厂商故障信息@@',
				templateLoad:'模板下载@@',
				massRemove: '批量删除@@',
				providerDescription:'厂商故障描述@@',
				solveProject: '解决方案@@',
				providerFaultInfo:'厂商故障信息管理@@'
			}
		},
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
			//未完待续
		},
		

	}
});
