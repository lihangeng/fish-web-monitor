Ext.apply(EwayLocale,{
	
	person:{
		bankOrg :{
			title:'Banks',//EwayLocale.person.bankOrg.title
			moveBankTitle:'Migrate Bank',//EwayLocale.person.bankOrg.moveBankTitle
			updateBankTitle:'Modify Bank Info',//EwayLocale.person.bankOrg.updateBankTitle
			addBankOrgTitle:'Add Bank Info',//EwayLocale.person.bankOrg.addBankOrgTitle
			code:'Code',//EwayLocale.person.bankOrg.code
			orgType:'Type',//EwayLocale.person.bankOrg.orgType
			name:'Name',//EwayLocale.person.bankOrg.name
			orgLevel:'Level',//EwayLocale.person.bankOrg.orgLevel
			zip:'Zip Code',//EwayLocale.person.bankOrg.zip
			removeManager:'Delete',//EwayLocale.person.bankOrg.removeManager
			manager:'Manager',//EwayLocale.person.bankOrg.manager
			address:'Address',//EwayLocale.person.bankOrg.address
			upgradeOrg:'Upper',//EwayLocale.person.bankOrg.upgradeOrg
			description:'Description',//EwayLocale.person.bankOrg.description
			serOrganization:'All',//EwayLocale.person.bankOrg.serOrganization
			organizationType:{
				serviceOrg:'Maintenance'//EwayLocale.person.bankOrg.organizationType.serviceOrg
			},
			organizationLevelDict:{
				rootBank:'Head Office',//EwayLocale.person.bankOrg.organizationLevelDict.rootBank
				branchBank:'Branch',//EwayLocale.person.bankOrg.organizationLevelDict.branchBank
				tagBranchBank:'SubBranch',//EwayLocale.person.bankOrg.organizationLevelDict.tagBranchBank
				netBank:'Outlets'//EwayLocale.person.bankOrg.organizationLevelDict.netBank
			},
			addOrgTitle:'Person under this organization',//EwayLocale.person.bankOrg.addOrgTitle
			linkPeronTitle:'Person under the organization:',//EwayLocale.person.bankOrg.linkPeronTitle
			personList:'Person list',//EwayLocale.person.bankOrg.personList
			orgLinkTitle:'Person and device which link this organization',//EwayLocale.person.bankOrg.orgLinkTitle
			machineOrg:'Device under the organization:',//EwayLocale.person.bankOrg.machineOrg
			personOrg:'Person under the organization:'//EwayLocale.person.bankOrg.personOrg

		},
		bankPer :{
			title:'Bankers',//EwayLocale.person.bankPer.title
			organizationName:'Bank',//EwayLocale.person.bankPer.organizationName
			orgNavi:'All',//EwayLocale.person.bankPer.orgNavi
			addBankPerTitle:'Add Banker Info',//EwayLocale.person.bankPer.addBankPerTitle
			updateBankPerTitle:'Update Banker Info '//EwayLocale.person.bankPer.updateBankPerTitle

		},
		serviceOrg:{
			title:'Maintenance Providers',//EwayLocale.person.serviceOrg.title
			serviceNavi:'All',//EwayLocale.person.serviceOrg.serviceNavi
			serviceOrgAdmin:'Manager',//EwayLocale.person.serviceOrg.serviceOrgAdmin
			setManager:'Settings',//EwayLocale.person.serviceOrg.setManager
			removeManager:'Delete',//EwayLocale.person.serviceOrg.removeManager
			code:'Code',//EwayLocale.person.serviceOrg.code
			name:'Name',//EwayLocale.person.serviceOrg.name
			zip:'Zip Code',//EwayLocale.person.serviceOrg.zip
			zip:'Z.C.',//EwayLocale.person.serviceOrg.zip
			shortName:'Maintenance Provider',//EwayLocale.person.serviceOrg.shortName
			address:'Address',//EwayLocale.person.serviceOrg.address
			description:'Description',//EwayLocale.person.serviceOrg.description
			addServiceTitle:'Add Maintenance Provider',//EwayLocale.person.serviceOrg.addServiceTitle
			upgradeService:'Parent',//EwayLocale.person.serviceOrg.upgradeService
			updateServiceTitle:'Update Maintenance Provider',//EwayLocale.person.serviceOrg.updateServiceTitle
			personDevSerLink:'Person and device which link this manufacturer',//EwayLocale.person.serviceOrg.personDevSerLink
			devSerLink:'Device under the manufacturer',//EwayLocale.person.serviceOrg.devSerLink
			personSerLink:'Person under the manufacturer:',//EwayLocale.person.serviceOrg.personSerLink

		},
		servicePer:{
			title:'Maintenance Engineers',//EwayLocale.person.servicePer.title
			servicePerlink:'Binding Device',//EwayLocale.person.servicePer.servicePerlink
			addServicePerTitle:'Add maintenancer',//EwayLocale.person.servicePer.addServicePerTitle
			updateServicePerTitle:'Update maintenancer',//EwayLocale.person.servicePer.updateServicePerTitle

		},
		user:{
			title:'Users',//EwayLocale.person.user.title
			code:'Code',//EwayLocale.person.user.code
			clickToCheckLog:'Click to Query Log',//EwayLocale.person.user.clickToCheckLog
			userLog:' s log',//EwayLocale.person.user.userLog
			resetPasswd:'Reset Password',//EwayLocale.person.user.resetPasswd
			clickToPasswdInit:'Click to set the password to initialize',//EwayLocale.person.user.clickToPasswdInit
			clickToRole:'Click to check all roles list',//EwayLocale.person.user.clickToRole
			addUserTitle:'Add user information',//EwayLocale.person.user.addUserTitle
			userType:'User Type',//EwayLocale.person.user.userType
			role:'Role',//EwayLocale.person.user.role
			roleGiven:'Optional Roles',//EwayLocale.person.user.roleGiven
			roleName:'Name',//EwayLocale.person.user.roleName
			roleDescription:'Description',//EwayLocale.person.user.roleDescription
			userListTitle:'userList',//EwayLocale.person.user.userListTitle
			updateUserTitle:'Modify user role(use drafting way)',//EwayLocale.person.user.updateUserTitle
			roleCanBeAdd:'Role list can be added',//EwayLocale.person.user.roleCanBeAdd
			roleAlreadyBeAdd:'Role list already be added',//EwayLocale.person.user.roleAlreadyBeAdd
			operCode:'Operator no',//EwayLocale.person.user.operCode
			operName:'Operator name',//EwayLocale.person.user.operName
			operTime:'Operate time',//EwayLocale.person.user.operTime
			operResult:'Operate result',//EwayLocale.person.user.operResult
			operContent:'Operate content',//EwayLocale.person.user.operContent
			roleListTitle:'User roles list',//EwayLocale.person.user.roleListTitle
			operTitle:'Operator log',//EwayLocale.person.user.operTitle
			operDetailTitle:'Operate logs',//EwayLocale.person.user.operDetailTitle
			operLogList:'Operate logs list',//EwayLocale.person.user.operLogList
			personDevice:'User<-->Device',//EwayLocale.person.user.personDevice
			rootUser:'Super user',//EwayLocale.person.user.rootUser
			generalUser:'General User'//EwayLocale.person.user.generalUser
		}

	},

	
	permission:{
		systemMenu:'Menus', //EwayLocale.permission.systemMenu
		role:{
			title:'Roles',//EwayLocale.permission.role.title
			update:'Update Role',//EwayLocale.permission.role.update
			name:'Name',//EwayLocale.permission.role.name
			type:'Type',//EwayLocale.permission.role.type
			description:'Description',//EwayLocale.permission.role.description
			isSysRole:'Is Default Role',//EwayLocale.permission.role.isSysRole
			chooseRight:'Please choose permissions.',//EwayLocale.permission.role.chooseRight
			add:'Add Role',//EwayLocale.permission.role.add
			wrongRoleParam:'Invalid input exists,please try again.'//EwayLocale.permission.role.wrongRoleParam
		},
		permission:{
			menuName:'Name',//EwayLocale.permission.permission.menuName
			menuDescription:'Description',//EwayLocale.permission.permission.menuDescription
			menuPermission:'Permission'//EwayLocale.permission.permission.menuPermission
		}
	}
});
