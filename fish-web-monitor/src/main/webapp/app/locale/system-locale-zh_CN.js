Ext.apply(EwayLocale,{
	
	person:{
		bankOrg :{
			title:'银行机构管理',//EwayLocale.bankOrg.title
			moveBankTitle:'组织迁移',//EwayLocale.bankOrg.moveBankTitle
			updateBankTitle:'更改银行机构信息',//EwayLocale.bankOrg.updateBankTitle
			addBankOrgTitle:'增加银行机构信息',//EwayLocale.bankOrg.addBankOrgTitle
			code:'机构编号',//EwayLocale.bankOrg.code
			orgType:'机构类型',//EwayLocale.bankOrg.orgType
			name:'机构名称',//EwayLocale.bankOrg.name
			orgLevel:'机构级别',//EwayLocale.bankOrg.orgLevel
			orgNavi:'机构导航',//EwayLocale.bankOrg.orgNavi
			zip:'邮政编码',//EwayLocale.bankOrg.zip
			removeManager:'删除',//EwayLocale.bankOrg.removeManager
			manager:'管理员',//EwayLocale.bankOrg.manager
			address:'机构地址',//EwayLocale.bankOrg.address
			upgradeOrg:'上级机构',//EwayLocale.bankOrg.upgradeOrg
			description:'机构描述',//EwayLocale.bankOrg.description
			serOrganization:'维护商描述',//EwayLocale.bankOrg.serOrganization
			organizationType:{
				bank:'银行',//EwayLocale.organizationType.bank
				serviceOrg:'维护商'//EwayLocale.organizationType.serviceOrg
			},
			organizationLevelDict:{
				rootBank:'总行',//EwayLocale.organizationLevelDict.rootBank
				branchBank:'分行',//EwayLocale.organizationLevelDict.branchBank
				tagBranchBank:'支行',//EwayLocale.organizationLevelDict.tagBranchBank
				netBank:'网点'//EwayLocale.organizationLevelDict.netBank
			},
			addOrgTitle:'该机构人员信息',//EwayLocale.organizationLevelDict.addOrgTitle
			linkPeronTitle:'当前机构下人员：',//EwayLocale.organizationLevelDict.linkPeronTitle
			personList:'人员列表',//EwayLocale.organizationLevelDict.personList
			orgLinkTitle:'该机构的关联设备和人员',//EwayLocale.organizationLevelDict.orgLinkTitle
			machineOrg:' 该机构下设备',//EwayLocale.organizationLevelDict.machineOrg
			personOrg:'该机构下人员'//EwayLocale.organizationLevelDict.personOrg

		},
		bankPer :{
			title:'银行人员管理',//EwayLocale.bankPer.title
			organizationName:'机构',//EwayLocale.bankPer.organizationName
			orgNavi:'机构导航',//EwayLocale.bankPer.orgNavi
			addBankPerTitle:'增加银行人员信息',//EwayLocale.bankPer.addBankPerTitle
			updateBankPerTitle:'更改银行人员信息'//EwayLocale.bankPer.updateBankPerTitle

		},
		serviceOrg:{
			title:'维护商管理',//EwayLocale.serviceOrg.title
			serviceNavi:'厂商导航',//EwayLocale.serviceOrg.serviceNavi
			serviceOrgAdmin:'管理员',//EwayLocale.serviceOrg.serviceOrgAdmin
			setManager:'设置',//EwayLocale.serviceOrg.setManager
			removeManager:'删除',//EwayLocale.serviceOrg.removeManager
			code:'厂商编号',//EwayLocale.serviceOrg.code
			name:'厂商名称',//EwayLocale.serviceOrg.name
			zip:'邮政编码',//EwayLocale.serviceOrg.zip
			shortName:'厂商',//EwayLocale.serviceOrg.shortName
			address:'厂商地址',//EwayLocale.serviceOrg.address
			description:'厂商描述',//EwayLocale.serviceOrg.description
			addServiceTitle:'增加维护商信息',//EwayLocale.serviceOrg.addServiceTitle
			upgradeService:'上级厂商',//EwayLocale.serviceOrg.upgradeService
			updateServiceTitle:'更改维护商信息',//EwayLocale.serviceOrg.updateServiceTitle
			personDevSerLink:'该厂商的关联设备和人员',//EwayLocale.serviceOrg.personDevSerLink
			devSerLink:'该厂商下设备',//EwayLocale.serviceOrg.devSerLink
			personSerLink:'该厂商下人员',//EwayLocale.serviceOrg.personSerLink

			directOrganization:'的直接下级机构'//EwayLocale.serviceOrg.directOrganization
		},
		servicePer:{
			title:'维护人员管理',//EwayLocale.servicePer.title
			servicePerlink:'关联设备',//EwayLocale.servicePer.servicePerlink
			addServicePerTitle:'增加维护人员信息',//EwayLocale.servicePer.addServicePerTitle
			updateServicePerTitle:'更改维护人员信息',//EwayLocale.servicePer.updateServicePerTitle

			maintainInfo:'所有维护商人员信息'//EwayLocale.servicePer.maintainInfo
		},
		user:{
			title:'用户管理',//EwayLocale.user.title
			code:'用户名',//EwayLocale.user.code
			clickToCheckLog:'单击即可查看用户 ',//EwayLocale.user.clickToCheckLog
			userLog:' 的操作日志',//EwayLocale.user.userLog
			resetPasswd:'密码重置',//EwayLocale.user.resetPasswd
			clickToPasswdInit:'单击即可密码重置为初始化密码',//EwayLocale.user.clickToPasswdInit
			clickToRole:'单击即可查看所有角色列表',//EwayLocale.user.clickToRole
			addUserTitle:'增加用户信息',//EwayLocale.user.addUserTitle
			clickToUser:'请点击查询，选择人员',//EwayLocale.user.clickToUser
			userType:'用户类型',//EwayLocale.user.userType
			role:'角色',//EwayLocale.user.role
			roleGiven:'角色赋予',//EwayLocale.user.roleGiven
			roleName:'角色名称',//EwayLocale.user.roleName
			roleDescription:'角色描述',//EwayLocale.user.roleDescription
			userListTitle:'人员列表',//EwayLocale.user.userListTitle
			updateUserTitle:'更改用户角色（使用拖拽的方式）',//EwayLocale.user.updateUserTitle
			roleCanBeAdd:'可添加的角色列表',//EwayLocale.user.roleCanBeAdd
			roleAlreadyBeAdd:'已添加的角色列表',//EwayLocale.user.roleAlreadyBeAdd
			operCode:'操作人编号',//EwayLocale.user.operCode
			operName:'操作人姓名',//EwayLocale.user.operName
			operTime:'操作时间',//EwayLocale.user.operTime
			operResult:'操作结果',//EwayLocale.user.operResult
			operContent:'操作内容',//EwayLocale.user.operContent
			roleListTitle:'用户角色列表',//EwayLocale.user.roleListTitle
			operTitle:'操作员日志',//EwayLocale.user.operTitle
			operDetailTitle:'操作日志信息',//EwayLocale.user.operDetailTitle
			operLogList:'操作日志列表',//EwayLocale.user.operLogList
			personDevice:'人员<-->设备',//EwayLocale.user.personDevice
			rootUser:'超级用户',//EwayLocale.user.rootUser
			generalUser:'普通用户'//EwayLocale.user.generalUser
		}

	},

	
	permission:{
		systemMenu:'系统菜单',//EwayLocale.permission.systemMenu
		role:{
			title:'角色管理',//EwayLocale.role.title
			update:'更改角色',//EwayLocale.role.update
			name:'角色名称',//EwayLocale.role.name
			type:'角色类型',//EwayLocale.role.type
			description:'角色描述',//EwayLocale.role.description
			isSysRole:'是否是系统内置角色',//EwayLocale.role.isSysRole
			chooseRight:'请选择菜单权限',//EwayLocale.role.chooseRight
			add:'增加角色',//EwayLocale.role.add
			wrongRoleParam:'存在不合法的输入项.'//EwayLocale.role.wrongRoleParam
		},
		permission:{
			menuName:'菜单名称',//EwayLocale.permission.menuName
			menuDescription:'菜单描述',//EwayLocale.permission.menuDescription
			menuPermission:'菜单权限'//EwayLocale.permission.menuPermission
		}
	}
});
