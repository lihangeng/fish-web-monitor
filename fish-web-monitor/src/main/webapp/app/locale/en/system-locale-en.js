Ext.apply(EwayLocale,{
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
			title:'Maintenance Providers',//EwayLocale.person.serviceOrg.title 维护商管理
			serviceNavi:'All',//EwayLocale.person.serviceOrg.serviceNavi 厂商导航
			serviceOrgAdmin:'Manager',//EwayLocale.person.serviceOrg.serviceOrgAdmin 管理员
			setManager:'Settings',//EwayLocale.person.serviceOrg.setManager 设置
			removeManager:'Delete',//EwayLocale.person.serviceOrg.removeManager 删除
			code:'Code',//EwayLocale.person.serviceOrg.code 厂商编号
			name:'Name',//EwayLocale.person.serviceOrg.name 厂商名称
			zip:'Zip',//EwayLocale.person.serviceOrg.zip 邮政编码
			shortName:'Manufacturer',//EwayLocale.person.serviceOrg.shortName 厂商
			address:'Address',//EwayLocale.person.serviceOrg.address 厂商地址
			description:'Description',//EwayLocale.person.serviceOrg.description 厂商描述
			addServiceTitle:'Add Maintenance Provider',//EwayLocale.person.serviceOrg.addServiceTitle 增加维护商信息
			upgradeService:'Parent',//EwayLocale.person.serviceOrg.upgradeService 上级厂商
			updateServiceTitle:'Update Maintenance Provider',//EwayLocale.person.serviceOrg.updateServiceTitle 更改维护商信息
			personDevSerLink:'Person and device which link this manufacturer',//EwayLocale.person.serviceOrg.personDevSerLink 该厂商的关联设备和人员
			devSerLink:'Device under the manufacturer',//EwayLocale.person.serviceOrg.devSerLink 该厂商下设备
			personSerLink:'Person under the manufacturer:',//EwayLocale.person.serviceOrg.personSerLink 该厂商下人员

			directOrganization:' Maintenance Providers'
		},
		servicePer:{
			title:'Maintenancers',//EwayLocale.person.servicePer.title 维护人员管理
			servicePerlink:'Binding Device',//EwayLocale.person.servicePer.servicePerlink 关联设备
			addServicePerTitle:'Add maintenancer',//EwayLocale.person.servicePer.addServicePerTitle 增加维护人员信息
			updateServicePerTitle:'Update maintenancer',//EwayLocale.person.servicePer.updateServicePerTitle 更改维护人员信息

			maintainInfo:'All Maintenance Staff'//所有维护商人员信息
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
		systemMenu:'Menus', //系统菜单permission.systemMenu
		role:{
			title:'Roles',//EwayLocale.permission.role.title 角色管理
			update:'Update Role',//EwayLocale.permission.role.update 更改角色
			name:'Name',//EwayLocale.permission.role.name 角色名称
			type:'Type',//EwayLocale.permission.role.type 角色类型
			description:'Description',//EwayLocale.permission.role.description 角色描述
			isSysRole:'Is Default Role',//EwayLocale.permission.role.isSysRole 是否是系统内置角色
			chooseRight:'Please choose permissions.',//EwayLocale.permission.role.chooseRight 请选择菜单权限
			add:'Add Role'//EwayLocale.permission.role.add 增加角色
		},
		permission:{
			menuName:'Name',//EwayLocale.permission.permission.menuName 菜单名称
			menuDescription:'Description',//EwayLocale.permission.permission.menuDescription 菜单描述
			menuPermission:'Permission'//EwayLocale.permission.permission.menuPermission 菜单权限
		}
	}
});
