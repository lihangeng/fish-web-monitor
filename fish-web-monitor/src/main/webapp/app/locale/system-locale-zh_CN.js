Ext.apply(EwayLocale,{

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
			zip:'邮政编码',//EwayLocale.person.bankOrg.zip
			removeManager:'删除',//EwayLocale.person.bankOrg.removeManager
			manager:'管理员',//EwayLocale.person.bankOrg.manager
			address:'机构地址',//EwayLocale.person.bankOrg.address
			upgradeOrg:'上级机构',//EwayLocale.person.bankOrg.upgradeOrg
			description:'机构描述',//EwayLocale.person.bankOrg.description
			serOrganization:'维护商描述',//EwayLocale.person.bankOrg.serOrganization
			organizationType:{
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
			personType:'人员类型',//EwayLocale.person.bankPer.personType
			bankPerson:'银行人员',//EwayLocale.person.bankPer.bankPerson
			dealerPerson:'维护商人员',//EwayLocale.person.bankPer.dealerPerson
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
			personSerLink:'该厂商下人员'//EwayLocale.person.serviceOrg.personSerLink

		},
		servicePer:{
			title:'维护人员管理',//EwayLocale.person.servicePer.title
			servicePerlink:'关联设备',//EwayLocale.person.servicePer.servicePerlink
			addServicePerTitle:'增加维护人员信息',//EwayLocale.person.servicePer.addServicePerTitle
			updateServicePerTitle:'更改维护人员信息'//EwayLocale.person.servicePer.updateServicePerTitle

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
			personDevice:'关联设备（可使用拖拽的方式）',//EwayLocale.person.user.personDevice
			rootUser:'超级用户',//EwayLocale.person.user.rootUser
			generalUser:'普通用户',//EwayLocale.person.user.generalUser
			serverIp:'服务器IP',//EwayLocale.person.user.serverIp
			clientIP:'浏览器IP',//EwayLocale.person.user.clientIP
			times:'执行时间(ms)'//EwayLocale.person.user.times
		}

	},


	permission:{
		systemMenu:'系统菜单',//EwayLocale.permission.systemMenu
		role:{
			title:'角色管理',//EwayLocale.permission.role.title
			update:'更改角色',//EwayLocale.permission.role.update
			name:'角色名称',//EwayLocale.permission.role.name
			type:'角色类型',//EwayLocale.permission.role.type
			description:'角色描述',//EwayLocale.permission.role.description
			isSysRole:'是否是系统内置角色',//EwayLocale.permission.role.isSysRole
			chooseRight:'请选择菜单权限',//EwayLocale.permission.role.chooseRight
			add:'增加角色',//EwayLocale.permission.role.add
			wrongRoleParam:'存在不合法的输入项.'//EwayLocale.permission.role.wrongRoleParam
		},
		permission:{
			menuName:'菜单名称',//EwayLocale.permission.permission.menuName
			menuDescription:'菜单描述',//EwayLocale.permission.permission.menuDescription
			menuPermission:'菜单权限'//EwayLocale.permission.permission.menuPermission
		}
	}
});
