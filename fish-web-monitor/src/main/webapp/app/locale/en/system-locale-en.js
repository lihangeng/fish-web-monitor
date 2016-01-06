Ext.apply(EwayLocale,{
	
	person:{
		bankOrg :{
			title:'Banks',//EwayLocale.bankOrg.title
			moveBankTitle:'Migrate Bank',//EwayLocale.bankOrg.moveBankTitle
			updateBankTitle:'Modify Bank Info',//EwayLocale.bankOrg.updateBankTitle
			addBankOrgTitle:'Add Bank Info',//EwayLocale.bankOrg.addBankOrgTitle
			code:'Code',//EwayLocale.bankOrg.code
			orgType:'Type',//EwayLocale.bankOrg.orgType
			name:'Name',//EwayLocale.bankOrg.name
			orgLevel:'Level',//EwayLocale.bankOrg.orgLevel
			orgNavi:'Navigator',//EwayLocale.bankOrg.orgNavi
			zip:'Zip Code',//EwayLocale.bankOrg.zip
			removeManager:'Delete',//EwayLocale.bankOrg.removeManager
			manager:'Manager',//EwayLocale.bankOrg.manager
			address:'Address',//EwayLocale.bankOrg.address
			upgradeOrg:'Upper',//EwayLocale.bankOrg.upgradeOrg
			description:'Description',//EwayLocale.bankOrg.description
			serOrganization:'All',//EwayLocale.bankOrg.serOrganization
			organizationType:{
				bank:'Bank',//EwayLocale.organizationType.bank
				serviceOrg:'Maintenance'//EwayLocale.organizationType.serviceOrg
			},
			organizationLevelDict:{
				rootBank:'Head Office',//EwayLocale.organizationLevelDict.rootBank
				branchBank:'Branch',//EwayLocale.organizationLevelDict.branchBank
				tagBranchBank:'SubBranch',//EwayLocale.organizationLevelDict.tagBranchBank
				netBank:'Location'//EwayLocale.organizationLevelDict.netBank
			},
			addOrgTitle:'Person under this organization',//EwayLocale.organizationLevelDict.addOrgTitle
			linkPeronTitle:'Person under the organization:',//EwayLocale.organizationLevelDict.linkPeronTitle
			personList:'Person list',//EwayLocale.organizationLevelDict.personList
			orgLinkTitle:'Person and device which link this organization',//EwayLocale.organizationLevelDict.orgLinkTitle
			machineOrg:'Device under the organization:',//EwayLocale.organizationLevelDict.machineOrg
			personOrg:'Person under the organization:'//EwayLocale.organizationLevelDict.personOrg

		},
		bankPer :{
			title:'Bankers',//EwayLocale.bankPer.title
			organizationName:'Bank',//EwayLocale.bankPer.organizationName
			orgNavi:'All',//EwayLocale.bankPer.orgNavi
			addBankPerTitle:'Add Banker Info',//EwayLocale.bankPer.addBankPerTitle
			updateBankPerTitle:'Update Banker Info '//EwayLocale.bankPer.updateBankPerTitle

		},
		serviceOrg:{
			title:'Maintenance Providers',//EwayLocale.serviceOrg.title
			serviceNavi:'All',//EwayLocale.serviceOrg.serviceNavi
			serviceOrgAdmin:'Manager',//EwayLocale.serviceOrg.serviceOrgAdmin
			setManager:'Settings',//EwayLocale.serviceOrg.setManager
			removeManager:'Delete',//EwayLocale.serviceOrg.removeManager
			code:'Code',//EwayLocale.serviceOrg.code
			name:'Name',//EwayLocale.serviceOrg.name
			zip:'Zip Code',//EwayLocale.serviceOrg.zip
			zip:'Z.C.',//EwayLocale.serviceOrg.zip
			shortName:'Maintenance Provider',//EwayLocale.serviceOrg.shortName
			address:'Address',//EwayLocale.serviceOrg.address
			description:'Description',//EwayLocale.serviceOrg.description
			addServiceTitle:'Add Maintenance Provider',//EwayLocale.serviceOrg.addServiceTitle
			upgradeService:'Parent',//EwayLocale.serviceOrg.upgradeService
			updateServiceTitle:'Update Maintenance Provider',//EwayLocale.serviceOrg.updateServiceTitle
			personDevSerLink:'Person and device which link this manufacturer',//EwayLocale.serviceOrg.personDevSerLink
			devSerLink:'Device under the manufacturer',//EwayLocale.serviceOrg.devSerLink
			personSerLink:'Person under the manufacturer:',//EwayLocale.serviceOrg.personSerLink

			directOrganization:' Maintenance Providers'//EwayLocale.serviceOrg.directOrganization
		},
		servicePer:{
			title:'Maintenance Engineers',//EwayLocale.servicePer.title
			servicePerlink:'Binding Device',//EwayLocale.servicePer.servicePerlink
			addServicePerTitle:'Add maintenancer',//EwayLocale.servicePer.addServicePerTitle
			updateServicePerTitle:'Update maintenancer',//EwayLocale.servicePer.updateServicePerTitle

			maintainInfo:'All Maintenance Engineers'//EwayLocale.servicePer.maintainInfo
		},
		user:{
			title:'Users',//EwayLocale.user.title
			code:'Code',//EwayLocale.user.code
			clickToCheckLog:'Click to Query Log',//EwayLocale.user.clickToCheckLog
			userLog:' s log',//EwayLocale.user.userLog
			resetPasswd:'Reset Password',//EwayLocale.user.resetPasswd
			clickToPasswdInit:'Click to set the password to initialize',//EwayLocale.user.clickToPasswdInit
			clickToRole:'Click to check all roles list',//EwayLocale.user.clickToRole
			addUserTitle:'Add user information',//EwayLocale.user.addUserTitle
			clickToUser:'Please click query ,choose a  person',//EwayLocale.user.clickToUser
			userType:'User Type',//EwayLocale.user.userType
			role:'Role',//EwayLocale.user.role
			roleGiven:'Optional Roles',//EwayLocale.user.roleGiven
			roleName:'Name',//EwayLocale.user.roleName
			roleDescription:'Description',//EwayLocale.user.roleDescription
			userListTitle:'userList',//EwayLocale.user.userListTitle
			updateUserTitle:'Modify user role(use drafting way)',//EwayLocale.user.updateUserTitle
			roleCanBeAdd:'Role list can be added',//EwayLocale.user.roleCanBeAdd
			roleAlreadyBeAdd:'Role list already be added',//EwayLocale.user.roleAlreadyBeAdd
			operCode:'Operator no',//EwayLocale.user.operCode
			operName:'Operator name',//EwayLocale.user.operName
			operTime:'Operate time',//EwayLocale.user.operTime
			operResult:'Operate result',//EwayLocale.user.operResult
			operContent:'Operate content',//EwayLocale.user.operContent
			roleListTitle:'User roles list',//EwayLocale.user.roleListTitle
			operTitle:'Operator log',//EwayLocale.user.operTitle
			operDetailTitle:'Operate logs',//EwayLocale.user.operDetailTitle
			operLogList:'Operate logs list',//EwayLocale.user.operLogList
			personDevice:'User<-->Device',//EwayLocale.user.personDevice
			rootUser:'Super user',//EwayLocale.user.rootUser
			generalUser:'General User'//EwayLocale.user.generalUser
		}

	},

	
	permission:{
		systemMenu:'Menus', //EwayLocale.permission.systemMenu
		role:{
			title:'Roles',//EwayLocale.role.title
			update:'Update Role',//EwayLocale.role.update
			name:'Name',//EwayLocale.role.name
			type:'Type',//EwayLocale.role.type
			description:'Description',//EwayLocale.role.description
			isSysRole:'Is Default Role',//EwayLocale.role.isSysRole
			chooseRight:'Please choose permissions.',//EwayLocale.role.chooseRight
			add:'Add Role',//EwayLocale.role.add
			wrongRoleParam:'Invalid input exists,please try again.'//EwayLocale.role.wrongRoleParam
		},
		permission:{
			menuName:'Name',//EwayLocale.permission.menuName
			menuDescription:'Description',//EwayLocale.permission.menuDescription
			menuPermission:'Permission'//EwayLocale.permission.menuPermission
		}
	}
});
