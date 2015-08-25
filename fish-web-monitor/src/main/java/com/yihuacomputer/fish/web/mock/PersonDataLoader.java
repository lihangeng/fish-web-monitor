/**
 *
 */
package com.yihuacomputer.fish.web.mock;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IPermissionService;
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.permission.IRoleService;
import com.yihuacomputer.fish.api.permission.RoleType;
import com.yihuacomputer.fish.api.person.Gender;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.IPersonJob;
import com.yihuacomputer.fish.api.person.IPersonJobService;
import com.yihuacomputer.fish.api.person.IPersonService;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.person.OrganizationLevel;
import com.yihuacomputer.fish.api.person.OrganizationType;
import com.yihuacomputer.fish.api.person.PersonState;
import com.yihuacomputer.fish.api.person.PersonType;
import com.yihuacomputer.fish.api.person.UserState;
import com.yihuacomputer.fish.api.relation.IRolePermissionRelation;
import com.yihuacomputer.fish.api.relation.IUserRoleRelation;

/**
 * @author dell
 *
 */
public class PersonDataLoader {
	@Autowired
	private IOrganizationService  orgService;
	@Autowired
	private IPersonService  personService;
	@Autowired
	private IUserService  userService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IUserRoleRelation userRoleRelation;
	@Autowired
	private IPermissionService permissionService;
	@Autowired
	private IRolePermissionRelation rolePermissionRelation;
	@Autowired
    private IPersonJobService personJobService;

	public void init(){
		//初始化组织
		initOrg();

		//初始化权限
		initPermission();

		//初始化人员职责
        initPersonJob();

		//初始化角色
		initRole();

		//初始化用户
		initUser();

		//初始化关系
		initRelation();
	}

	private void initRelation() {
		//role link permission
		IRole superAdmin = roleService.get("超级管理员");
		for(IPermission p : permissionService.list()){
			rolePermissionRelation.link(superAdmin, p);
		}
		//user link role
		IUser user = userService.get("admin");
		if(user != null){
			for(IRole role : roleService.list()){
				userRoleRelation.link(user, role);
			}
		}
	}

	private void initPersonJob(){
    	IPersonJob personJob1 = personJobService.make();
    	personJob1.setId(1);
    	personJob1.setCode("0000");
    	personJob1.setName("董事长");
    	personJobService.add(personJob1);

    	IPersonJob personJob2 = personJobService.make();
    	personJob2.setId(2);
    	personJob2.setCode("0001");
    	personJob2.setName("行长");
    	personJobService.add(personJob2);

    	IPersonJob personJob3 = personJobService.make();
    	personJob3.setId(3);
    	personJob3.setCode("0002");
    	personJob3.setName("总行总经理");
    	personJobService.add(personJob3);

    	IPersonJob personJob4 = personJobService.make();
    	personJob4.setId(4);
    	personJob4.setCode("0003");
    	personJob4.setName("总行副总经理");
    	personJobService.add(personJob4);

    	IPersonJob personJob5 = personJobService.make();
    	personJob5.setId(5);
    	personJob5.setCode("0004");
    	personJob5.setName("分行行长");
    	personJobService.add(personJob5);

    	IPersonJob personJob6 = personJobService.make();
    	personJob6.setId(6);
    	personJob6.setCode("0005");
    	personJob6.setName("科长");
    	personJobService.add(personJob6);

    	IPersonJob personJob7 = personJobService.make();
    	personJob7.setId(7);
    	personJob7.setCode("0006");
    	personJob7.setName("会计");
    	personJobService.add(personJob7);

    }

	private void initUser() {
		IPerson	person1 = personService.make();
		person1.setCode("admin");
		person1.setName("admin");
		person1.setJobNum("0001");
        person1.setPersonJob(personJobService.get(1));
        person1.setState(PersonState.VACATION);
		person1.setOrganization(orgService.getByCode("root"));
		person1.setBirthday(new Date());
		person1.setEmail("11@qq.com");
		person1.setGender(Gender.MALE);
		person1.setMobile("15855772942");
		person1.setPhone("1859999");
		person1.setType(PersonType.MANAGE);
		personService.add(person1);

		IUser user = userService.make();
		user.setCode("admin");
		user.setPerson(person1);
		user.setPlainText("admin");
		user.setState(UserState.NORMAL);
		userService.add(user);

		IPerson	person2 = personService.make();
		person2.setCode("bankuser");
		person2.setName("bankuser");
		person2.setJobNum("0002");
		IPersonJob job1 = personJobService.get(2);
        person2.setPersonJob(job1);
        person2.setState(PersonState.OTHER);
		person2.setOrganization(orgService.getByCode("shnsh001"));
		person2.setBirthday(new Date());
		person2.setEmail("shnsh001@qq.com");
		person2.setGender(Gender.MALE);
		person2.setMobile("15855778911");
		person2.setPhone("1859999");
		person2.setType(PersonType.MANAGE);
		personService.add(person2);

		IPerson	person3 = personService.make();
		person3.setCode("fixMan001");
		person3.setName("维护人员01");
		person3.setJobNum("0003");
		person3.setOrganization(orgService.getByCode("yihua001",OrganizationType.MAINTAINER));
		person3.setBirthday(new Date());
		person3.setEmail("fixMan001@qq.com");
		person3.setState(PersonState.OFF);
		person3.setGender(Gender.MALE);
		person3.setMobile("15800775656");
		person3.setPhone("025-56585965");
		person3.setType(PersonType.FIXMAN);
		personService.add(person3);

		IPerson	person4 = personService.make();
		person4.setCode("fixMan002");
		person4.setName("维护人员02");
		person4.setJobNum("0004");
		person4.setOrganization(orgService.getByCode("yihua002",OrganizationType.MAINTAINER));
		person4.setBirthday(new Date());
		person4.setEmail("fixMan002@qq.com");
		person4.setGender(Gender.MALE);
		person4.setState(PersonState.ONGUARD);
		person4.setMobile("15866688899");
		person4.setPhone("025-56585888");
		person4.setType(PersonType.FIXMAN);
		personService.add(person4);

	}

	private void initRole() {
		IRole role = roleService.make("超级管理员");
		role.setDescription("系统默认的超级管理员");
		role.setType(RoleType.PERMISSION);
		role.setSystem(true);
		roleService.add(role);

		IRole role2 = roleService.make("普通用户");
		role2.setType(RoleType.PERMISSION);
		role.setSystem(true);
		roleService.add(role2);

	}


	private void initPermission() {
		//load Permission
		IPermission root = permissionService.add("0","root", "菜单列表",false);

		IPermission info = permissionService.make("A");
		info.setCode("info");
		info.setDescription("系统管理");
		info.setButton(false);
		info.setParent(root);
		permissionService.add(info);

		IPermission person = permissionService.make("A01");
		person.setCode("people");
		person.setDescription("人员管理");
		person.setButton(false);
		person.setParent(info);
		permissionService.add(person);

		IPermission bankOrg = permissionService.make("A0101");
		bankOrg.setCode("bankOrg");
		bankOrg.setDescription("银行机构管理");
		bankOrg.setButton(false);
		bankOrg.setParent(person);
		permissionService.add(bankOrg);

		IPermission bankOrgQuery = permissionService.make("A010101");
		bankOrgQuery.setCode("bankOrgQuery");
		bankOrgQuery.setDescription("查询");
		bankOrgQuery.setButton(true);
		bankOrgQuery.setParent(bankOrg);
		permissionService.add(bankOrgQuery);

		IPermission bankOrgAdd = permissionService.make("A010102");
		bankOrgAdd.setCode("bankOrgAdd");
		bankOrgAdd.setDescription("添加");
		bankOrgAdd.setButton(true);
		bankOrgAdd.setParent(bankOrg);
		permissionService.add(bankOrgAdd);

		IPermission bankOrgUpdate = permissionService.make("A010103");
		bankOrgUpdate.setCode("bankOrgUpdate");
		bankOrgUpdate.setDescription("更改");
		bankOrgUpdate.setButton(true);
		bankOrgUpdate.setParent(bankOrg);
		permissionService.add(bankOrgUpdate);

		IPermission bankOrgDel = permissionService.make("A010104");
		bankOrgDel.setCode("bankOrgDel");
		bankOrgDel.setDescription("删除");
		bankOrgDel.setButton(true);
		bankOrgDel.setParent(bankOrg);
		permissionService.add(bankOrgDel);

		IPermission bankOrgAdmin = permissionService.make("A010105");
		bankOrgAdmin.setCode("bankOrgAdmin");
		bankOrgAdmin.setDescription("管理员");
		bankOrgAdmin.setButton(true);
		bankOrgAdmin.setParent(bankOrg);
		permissionService.add(bankOrgAdmin);

		IPermission bankOrgMove = permissionService.make("A010106");
		bankOrgMove.setCode("bankOrgMove");
		bankOrgMove.setDescription(" 组织迁移");
		bankOrgMove.setButton(true);
		bankOrgMove.setParent(bankOrg);
		permissionService.add(bankOrgMove);

		IPermission bankPer = permissionService.make("A0102");
		bankPer.setCode("bankPer");
		bankPer.setDescription("银行人员管理");
		bankPer.setButton(false);
		bankPer.setParent(person);
		permissionService.add(bankPer);

		IPermission bankPerQuery = permissionService.make("A010201");
		bankPerQuery.setCode("bankPerQuery");
		bankPerQuery.setDescription("查询");
		bankPerQuery.setButton(true);
		bankPerQuery.setParent(bankPer);
		permissionService.add(bankPerQuery);

		IPermission bankPerAdd = permissionService.make("A010202");
		bankPerAdd.setCode("bankPerAdd");
		bankPerAdd.setDescription("添加");
		bankPerAdd.setButton(true);
		bankPerAdd.setParent(bankPer);
		permissionService.add(bankPerAdd);

		IPermission bankPerUpdate = permissionService.make("A010203");
		bankPerUpdate.setCode("bankPerUpdate");
		bankPerUpdate.setDescription("更改");
		bankPerUpdate.setButton(true);
		bankPerUpdate.setParent(bankPer);
		permissionService.add(bankPerUpdate);

		IPermission bankPerDel = permissionService.make("A010204");
		bankPerDel.setCode("bankPerDel");
		bankPerDel.setDescription("删除");
		bankPerDel.setButton(true);
		bankPerDel.setParent(bankPer);
		permissionService.add(bankPerDel);

		IPermission bankPerlink = permissionService.make("A010205");
		bankPerlink.setCode("bankPerlink");
		bankPerlink.setDescription("关联设备");
		bankPerlink.setButton(true);
		bankPerlink.setParent(bankPer);
		permissionService.add(bankPerlink);

		IPermission serviceOrg = permissionService.make("A0103");
		serviceOrg.setCode("serviceOrg");
		serviceOrg.setDescription("维护商管理");
		serviceOrg.setButton(false);
		serviceOrg.setParent(person);
		permissionService.add(serviceOrg);

		IPermission serviceOrgQuery = permissionService.make("A010301");
		serviceOrgQuery.setCode("serviceOrgQuery");
		serviceOrgQuery.setDescription("查询");
		serviceOrgQuery.setButton(true);
		serviceOrgQuery.setParent(serviceOrg);
		permissionService.add(serviceOrgQuery);

		IPermission serviceOrgAdd = permissionService.make("A010302");
		serviceOrgAdd.setCode("serviceOrgAdd");
		serviceOrgAdd.setDescription("添加");
		serviceOrgAdd.setButton(true);
		serviceOrgAdd.setParent(serviceOrg);
		permissionService.add(serviceOrgAdd);

		IPermission serviceOrgUpdate = permissionService.make("A010303");
		serviceOrgUpdate.setCode("serviceOrgUpdate");
		serviceOrgUpdate.setDescription("更改");
		serviceOrgUpdate.setButton(true);
		serviceOrgUpdate.setParent(serviceOrg);
		permissionService.add(serviceOrgUpdate);

		IPermission serviceOrgDel = permissionService.make("A010304");
		serviceOrgDel.setCode("serviceOrgDel");
		serviceOrgDel.setDescription("删除");
		serviceOrgDel.setButton(true);
		serviceOrgDel.setParent(serviceOrg);
		permissionService.add(serviceOrgDel);

		IPermission serviceOrgAdmin = permissionService.make("A010305");
		serviceOrgAdmin.setCode("serviceOrgAdmin");
		serviceOrgAdmin.setDescription("管理员");
		serviceOrgAdmin.setButton(true);
		serviceOrgAdmin.setParent(serviceOrg);
		permissionService.add(serviceOrgAdmin);

		IPermission servicePer = permissionService.make("A0104");
		servicePer.setCode("servicePer");
		servicePer.setDescription("维护人员管理");
		servicePer.setButton(false);
		servicePer.setParent(person);
		permissionService.add(servicePer);

		IPermission servicePerQuery = permissionService.make("A010401");
		servicePerQuery.setCode("servicePerQuery");
		servicePerQuery.setDescription("查询");
		servicePerQuery.setButton(true);
		servicePerQuery.setParent(servicePer);
		permissionService.add(servicePerQuery);

		IPermission servicePerAdd = permissionService.make("A010402");
		servicePerAdd.setCode("servicePerAdd");
		servicePerAdd.setDescription("添加");
		servicePerAdd.setButton(true);
		servicePerAdd.setParent(servicePer);
		permissionService.add(servicePerAdd);

		IPermission servicePerUpdate = permissionService.make("A010403");
		servicePerUpdate.setCode("servicePerUpdate");
		servicePerUpdate.setDescription("更改");
		servicePerUpdate.setButton(true);
		servicePerUpdate.setParent(servicePer);
		permissionService.add(servicePerUpdate);

		IPermission servicePerDel = permissionService.make("A010404");
		servicePerDel.setCode("servicePerDel");
		servicePerDel.setDescription("删除");
		servicePerDel.setButton(true);
		servicePerDel.setParent(servicePer);
		permissionService.add(servicePerDel);

		IPermission servicePerlink = permissionService.make("A010405");
		servicePerlink.setCode("servicePerlink");
		servicePerlink.setDescription("关联设备");
		servicePerlink.setButton(true);
		servicePerlink.setParent(servicePer);
		permissionService.add(servicePerlink);

		IPermission user = permissionService.make("A0105");
		user.setCode("user");
		user.setDescription("用户管理");
		user.setButton(false);
		user.setParent(person);
		permissionService.add(user);

		IPermission userQuery = permissionService.make("A010501");
		userQuery.setCode("userQuery");
		userQuery.setDescription("查询");
		userQuery.setButton(true);
		userQuery.setParent(user);
		permissionService.add(userQuery);

		IPermission userAdd = permissionService.make("A010502");
		userAdd.setCode("userAdd");
		userAdd.setDescription("添加");
		userAdd.setButton(true);
		userAdd.setParent(user);
		permissionService.add(userAdd);

		IPermission userUpdate = permissionService.make("A010503");
		userUpdate.setCode("userUpdate");
		userUpdate.setDescription("更改");
		userUpdate.setButton(true);
		userUpdate.setParent(user);
		permissionService.add(userUpdate);

		IPermission userDel = permissionService.make("A010504");
		userDel.setCode("userDel");
		userDel.setDescription("删除");
		userDel.setButton(true);
		userDel.setParent(user);
		permissionService.add(userDel);

		IPermission resetPassword = permissionService.make("A0106");
		resetPassword.setCode("resetPassword");
		resetPassword.setDescription("个人密码管理");
		resetPassword.setButton(false);
		resetPassword.setParent(person);
		permissionService.add(resetPassword);

		IPermission userLog = permissionService.make("A0107");
		userLog.setCode("userLog");
		userLog.setDescription("操作员日志");
		userLog.setButton(false);
		userLog.setParent(person);
		permissionService.add(userLog);

		IPermission deviceManager = permissionService.make("A02");
		deviceManager.setCode("deviceManager");
		deviceManager.setDescription("设备管理");
		deviceManager.setButton(false);
		deviceManager.setParent(root);
		permissionService.add(deviceManager);

		IPermission device = permissionService.make("A0201");
		device.setCode("device");
		device.setDescription("ATM管理");
		device.setButton(false);
		device.setParent(deviceManager);
		permissionService.add(device);

		IPermission deviceQuery = permissionService.make("A020101");
		deviceQuery.setCode("deviceQuery");
		deviceQuery.setDescription("查询");
		deviceQuery.setButton(true);
		deviceQuery.setParent(device);
		permissionService.add(deviceQuery);

		IPermission deviceAdd = permissionService.make("A020102");
		deviceAdd.setCode("deviceAdd");
		deviceAdd.setDescription("添加");
		deviceAdd.setButton(true);
		deviceAdd.setParent(device);
		permissionService.add(deviceAdd);

		IPermission deviceUpdate = permissionService.make("A020103");
		deviceUpdate.setCode("deviceUpdate");
		deviceUpdate.setDescription("更改");
		deviceUpdate.setButton(true);
		deviceUpdate.setParent(device);
		permissionService.add(deviceUpdate);

		IPermission deviceDel = permissionService.make("A020104");
		deviceDel.setCode("deviceDel");
		deviceDel.setDescription("删除");
		deviceDel.setButton(true);
		deviceDel.setParent(device);
		permissionService.add(deviceDel);

		IPermission atmCatalog = permissionService.make("A0202");
		atmCatalog.setCode("atmCatalog");
		atmCatalog.setDescription("ATM分类");
		atmCatalog.setButton(false);
		atmCatalog.setParent(deviceManager);
		permissionService.add(atmCatalog);

		IPermission atmCatalogQuery = permissionService.make("A020201");
		atmCatalogQuery.setCode("atmCatalogQuery");
		atmCatalogQuery.setDescription("查询");
		atmCatalogQuery.setButton(true);
		atmCatalogQuery.setParent(atmCatalog);
		permissionService.add(atmCatalogQuery);

		IPermission atmBrand = permissionService.make("A0203");
		atmBrand.setCode("atmBrand");
		atmBrand.setDescription("ATM品牌");
		atmBrand.setButton(false);
		atmBrand.setParent(deviceManager);
		permissionService.add(atmBrand);

		IPermission atmBrandQuery = permissionService.make("A020301");
		atmBrandQuery.setCode("atmBrandQuery");
		atmBrandQuery.setDescription("查询");
		atmBrandQuery.setButton(true);
		atmBrandQuery.setParent(atmBrand);
		permissionService.add(atmBrandQuery);

		IPermission atmBrandAdd = permissionService.make("A020302");
		atmBrandAdd.setCode("atmBrandAdd");
		atmBrandAdd.setDescription("添加");
		atmBrandAdd.setButton(true);
		atmBrandAdd.setParent(atmBrand);
		permissionService.add(atmBrandAdd);

		IPermission atmBrandUpdate = permissionService.make("A020303");
		atmBrandUpdate.setCode("atmBrandUpdate");
		atmBrandUpdate.setDescription("更改");
		atmBrandUpdate.setButton(true);
		atmBrandUpdate.setParent(atmBrand);
		permissionService.add(atmBrandUpdate);

		IPermission atmBrandDel = permissionService.make("A020304");
		atmBrandDel.setCode("atmBrandDel");
		atmBrandDel.setDescription("删除");
		atmBrandDel.setButton(true);
		atmBrandDel.setParent(atmBrand);
		permissionService.add(atmBrandDel);

		IPermission atmType = permissionService.make("A0204");
		atmType.setCode("atmType");
		atmType.setDescription("ATM型号");
		atmType.setButton(false);
		atmType.setParent(deviceManager);
		permissionService.add(atmType);

		IPermission atmTypeQuery = permissionService.make("A020401");
		atmTypeQuery.setCode("atmTypeQuery");
		atmTypeQuery.setDescription("查询");
		atmTypeQuery.setButton(true);
		atmTypeQuery.setParent(atmType);
		permissionService.add(atmTypeQuery);

		IPermission atmTypeAdd = permissionService.make("A020402");
		atmTypeAdd.setCode("atmTypeAdd");
		atmTypeAdd.setDescription("添加");
		atmTypeAdd.setButton(true);
		atmTypeAdd.setParent(atmType);
		permissionService.add(atmTypeAdd);

		IPermission atmTypeUpdate = permissionService.make("A020403");
		atmTypeUpdate.setCode("atmTypeUpdate");
		atmTypeUpdate.setDescription("更改");
		atmTypeUpdate.setButton(true);
		atmTypeUpdate.setParent(atmType);
		permissionService.add(atmTypeUpdate);

		IPermission atmTypeDel = permissionService.make("A020404");
		atmTypeDel.setCode("atmTypeDel");
		atmTypeDel.setDescription("删除");
		atmTypeDel.setButton(true);
		atmTypeDel.setParent(atmType);
		permissionService.add(atmTypeDel);

		IPermission atmGroup = permissionService.make("A0206");
		atmGroup.setCode("atmGroup");
		atmGroup.setDescription("ATM分组");
		atmGroup.setButton(false);
		atmGroup.setParent(deviceManager);
		permissionService.add(atmGroup);

		IPermission atmGroupQuery = permissionService.make("A020601");
		atmGroupQuery.setCode("atmGroupQuery");
		atmGroupQuery.setDescription("查询");
		atmGroupQuery.setButton(true);
		atmGroupQuery.setParent(atmGroup);
		permissionService.add(atmGroupQuery);

		IPermission atmGroupAdd = permissionService.make("A020602");
		atmGroupAdd.setCode("atmGroupAdd");
		atmGroupAdd.setDescription("增加");
		atmGroupAdd.setButton(true);
		atmGroupAdd.setParent(atmGroup);
		permissionService.add(atmGroupAdd);

		IPermission atmGroupUpdate = permissionService.make("A020603");
		atmGroupUpdate.setCode("atmGroupUpdate");
		atmGroupUpdate.setDescription("更改");
		atmGroupUpdate.setButton(true);
		atmGroupUpdate.setParent(atmGroup);
		permissionService.add(atmGroupUpdate);

		IPermission atmGroupDel = permissionService.make("A020604");
		atmGroupDel.setCode("atmGroupDel");
		atmGroupDel.setDescription("删除");
		atmGroupDel.setButton(true);
		atmGroupDel.setParent(atmGroup);
		permissionService.add(atmGroupDel);

		IPermission atmGroupDeviceQuery = permissionService.make("A020605");
		atmGroupDeviceQuery.setCode("atmGroupDeviceQuery");
		atmGroupDeviceQuery.setDescription("查询设备");
		atmGroupDeviceQuery.setButton(true);
		atmGroupDeviceQuery.setParent(atmGroup);
		permissionService.add(atmGroupDeviceQuery);

		IPermission atmGroupDeviceAdd = permissionService.make("A020606");
		atmGroupDeviceAdd.setCode("atmGroupDeviceAdd");
		atmGroupDeviceAdd.setDescription("增加设备");
		atmGroupDeviceAdd.setButton(true);
		atmGroupDeviceAdd.setParent(atmGroup);
		permissionService.add(atmGroupDeviceAdd);

		IPermission atmGroupDeviceDel = permissionService.make("A020607");
		atmGroupDeviceDel.setCode("atmGroupDeviceDel");
		atmGroupDeviceDel.setDescription("删除设备");
		atmGroupDeviceDel.setButton(true);
		atmGroupDeviceDel.setParent(atmGroup);
		permissionService.add(atmGroupDeviceDel);

		IPermission permissionConfig = permissionService.make("A03");
		permissionConfig.setCode("permissionConfig");
		permissionConfig.setDescription("权限配置");
		permissionConfig.setButton(false);
		permissionConfig.setParent(info);
		permissionService.add(permissionConfig);

		IPermission role = permissionService.make("A0301");
		role.setCode("role");
		role.setDescription("角色管理");
		role.setButton(false);
		role.setParent(permissionConfig);
		permissionService.add(role);

		IPermission roleQuery = permissionService.make("A030101");
		roleQuery.setCode("roleQuery");
		roleQuery.setDescription("查询");
		roleQuery.setButton(true);
		roleQuery.setParent(role);
		permissionService.add(roleQuery);

		IPermission roleAdd = permissionService.make("A030102");
		roleAdd.setCode("roleAdd");
		roleAdd.setDescription("添加");
		roleAdd.setButton(true);
		roleAdd.setParent(role);
		permissionService.add(roleAdd);

		IPermission roleUpdate = permissionService.make("A030103");
		roleUpdate.setCode("roleUpdate");
		roleUpdate.setDescription("更改");
		roleUpdate.setButton(true);
		roleUpdate.setParent(role);
		permissionService.add(roleUpdate);

		IPermission roleDel = permissionService.make("A030104");
		roleDel.setCode("roleDel");
		roleDel.setDescription("删除");
		roleDel.setButton(true);
		roleDel.setParent(role);
		permissionService.add(roleDel);

		IPermission permission = permissionService.make("A0302");
		permission.setCode("permission");
		permission.setDescription("菜单权限");
		permission.setButton(false);
		permission.setParent(permissionConfig);
		permissionService.add(permission);

		IPermission shortcutMenu = permissionService.make("A0303");
		shortcutMenu.setCode("shortcutMenu");
		shortcutMenu.setDescription("快捷菜单");
		shortcutMenu.setButton(false);
		shortcutMenu.setParent(permissionConfig);
		permissionService.add(shortcutMenu);

		IPermission configuration = permissionService.make("A04");
		configuration.setCode("configuration");
		configuration.setDescription("参数配置");
		configuration.setButton(false);
		configuration.setParent(info);
		permissionService.add(configuration);

		IPermission configurationUpdateQuery = permissionService.make("A0401");
		configurationUpdateQuery.setCode("configurationUpdateQuery");
		configurationUpdateQuery.setDescription("查询");
		configurationUpdateQuery.setButton(true);
		configurationUpdateQuery.setParent(configuration);
		permissionService.add(configurationUpdateQuery);

		IPermission configurationUpdate = permissionService.make("A0402");
		configurationUpdate.setCode("configurationUpdate");
		configurationUpdate.setDescription("更改");
		configurationUpdate.setButton(true);
		configurationUpdate.setParent(configuration);
		permissionService.add(configurationUpdate);

		IPermission announcement = permissionService.make("A05");
		announcement.setCode("announcement");
		announcement.setDescription("公告管理");
		announcement.setButton(false);
		announcement.setParent(info);
		permissionService.add(announcement);

		IPermission announcementQuery = permissionService.make("A0501");
		announcementQuery.setCode("announcementQuery");
		announcementQuery.setDescription("查询");
		announcementQuery.setButton(true);
		announcementQuery.setParent(announcement);
		permissionService.add(announcementQuery);


		IPermission announcementAdd = permissionService.make("A0502");
		announcementAdd.setCode("announcementAdd");
		announcementAdd.setDescription("添加");
		announcementAdd.setButton(true);
		announcementAdd.setParent(announcement);
		permissionService.add(announcementAdd);

		IPermission announcementUpdate = permissionService.make("A0503");
		announcementUpdate.setCode("announcementUpdate");
		announcementUpdate.setDescription("更改");
		announcementUpdate.setButton(true);
		announcementUpdate.setParent(announcement);
		permissionService.add(announcementUpdate);

		IPermission announcementDel = permissionService.make("A0504");
		announcementDel.setCode("announcementDel");
		announcementDel.setDescription("删除");
		announcementDel.setButton(true);
		announcementDel.setParent(announcement);
		permissionService.add(announcementDel);

		IPermission serviceThread = permissionService.make("A06");
		serviceThread.setCode("serviceThread");
		serviceThread.setDescription("服务器线程");
		serviceThread.setButton(false);
		serviceThread.setParent(info);
		permissionService.add(serviceThread);

		IPermission atmDailyWork = permissionService.make("B");
		atmDailyWork.setCode("atmDailyWork");
		atmDailyWork.setDescription("ATM日常管理");
		atmDailyWork.setButton(false);
		atmDailyWork.setParent(root);
		permissionService.add(atmDailyWork);

		IPermission atmLog = permissionService.make("B01");
		atmLog.setCode("atmLog");
		atmLog.setDescription("应用日志备份");
		atmLog.setButton(false);
		atmLog.setParent(atmDailyWork);
		permissionService.add(atmLog);

		IPermission logBackup = permissionService.make("B0101");
		logBackup.setCode("logBackup");
		logBackup.setDescription("应用日志下载");
		logBackup.setButton(false);
		logBackup.setParent(atmLog);
		permissionService.add(logBackup);

		IPermission logBackupQuery = permissionService.make("B010101");
		logBackupQuery.setCode("logBackupQuery");
		logBackupQuery.setDescription("查询");
		logBackupQuery.setButton(true);
		logBackupQuery.setParent(logBackup);
		permissionService.add(logBackupQuery);

		IPermission dayBackupJob = permissionService.make("B0102");
		dayBackupJob.setCode("dayBackupJob");
		dayBackupJob.setDescription("每日备份任务");
		dayBackupJob.setButton(false);
		dayBackupJob.setParent(atmLog);
		permissionService.add(dayBackupJob);

		IPermission dayBackupJobQuery = permissionService.make("B010201");
		dayBackupJobQuery.setCode("dayBackupJobQuery");
		dayBackupJobQuery.setDescription("查询");
		dayBackupJobQuery.setButton(true);
		dayBackupJobQuery.setParent(dayBackupJob);
		permissionService.add(dayBackupJobQuery);

		IPermission logAnalysis = permissionService.make("B0103");
		logAnalysis.setCode("logAnalysis");
		logAnalysis.setDescription("业务日志分析");
		logAnalysis.setButton(false);
		logAnalysis.setParent(atmLog);
		permissionService.add(logAnalysis);

		IPermission atmLogInfo = permissionService.make("B0104");
		atmLogInfo.setCode("atmLogInfo");
		atmLogInfo.setDescription("日志备份统计");
		atmLogInfo.setButton(false);
		atmLogInfo.setParent(atmLog);
		permissionService.add(atmLogInfo);

		IPermission atmLogInfoQuery = permissionService.make("B010401");
		atmLogInfoQuery.setCode("atmLogInfoQuery");
		atmLogInfoQuery.setDescription("查询");
		atmLogInfoQuery.setButton(true);
		atmLogInfoQuery.setParent(atmLogInfo);
		permissionService.add(atmLogInfoQuery);

		IPermission retainCard = permissionService.make("B02");
		retainCard.setCode("retainCard");
		retainCard.setDescription("吞卡管理");
		retainCard.setButton(false);
		retainCard.setParent(atmDailyWork);
		permissionService.add(retainCard);

		IPermission monitor_cardInfo = permissionService.make("B0201");
		monitor_cardInfo.setCode("monitor_cardInfo");
		monitor_cardInfo.setDescription("吞卡查询");
		monitor_cardInfo.setButton(false);
		monitor_cardInfo.setParent(retainCard);
		permissionService.add(monitor_cardInfo);

		IPermission cardQuery = permissionService.make("B020101");
		cardQuery.setCode("cardQuery");
		cardQuery.setDescription("查询");
		cardQuery.setButton(true);
		cardQuery.setParent(monitor_cardInfo);
		permissionService.add(cardQuery);

		IPermission cardAdd = permissionService.make("B020102");
		cardAdd.setCode("cardAdd");
		cardAdd.setDescription("添加");
		cardAdd.setButton(true);
		cardAdd.setParent(monitor_cardInfo);
		permissionService.add(cardAdd);

		IPermission cardDel = permissionService.make("B020103");
		cardDel.setCode("cardDel");
		cardDel.setDescription("删除");
		cardDel.setButton(true);
		cardDel.setParent(monitor_cardInfo);
		permissionService.add(cardDel);

		IPermission monitor_cardAction = permissionService.make("B0202");
		monitor_cardAction.setCode("monitor_cardAction");
		monitor_cardAction.setDescription("吞卡处理");
		monitor_cardAction.setButton(false);
		monitor_cardAction.setParent(retainCard);
		permissionService.add(monitor_cardAction);

		IPermission cardReceiveQuery = permissionService.make("B020201");
		cardReceiveQuery.setCode("cardReceiveQuery");
		cardReceiveQuery.setDescription("查询");
		cardReceiveQuery.setButton(true);
		cardReceiveQuery.setParent(monitor_cardAction);
		permissionService.add(cardReceiveQuery);

		IPermission cardReceive = permissionService.make("B020202");
		cardReceive.setCode("cardReceive");
		cardReceive.setDescription("客户领卡");
		cardReceive.setButton(true);
		cardReceive.setParent(monitor_cardAction);
		permissionService.add(cardReceive);

		IPermission cardHandOver = permissionService.make("B020203");
		cardHandOver.setCode("cardHandOver");
		cardHandOver.setDescription("卡片移交");
		cardHandOver.setButton(true);
		cardHandOver.setParent(monitor_cardAction);
		permissionService.add(cardHandOver);

		IPermission monitor_cardDestroy = permissionService.make("B0203");
		monitor_cardDestroy.setCode("monitor_cardDestroy");
		monitor_cardDestroy.setDescription("吞卡销毁");
		monitor_cardDestroy.setButton(false);
		monitor_cardDestroy.setParent(retainCard);
		permissionService.add(monitor_cardDestroy);

		IPermission cardDestoryQuery = permissionService.make("B020301");
		cardDestoryQuery.setCode("cardDestoryQuery");
		cardDestoryQuery.setDescription("查询");
		cardDestoryQuery.setButton(true);
		cardDestoryQuery.setParent(monitor_cardDestroy);
		permissionService.add(cardDestoryQuery);

		IPermission cardDestory = permissionService.make("B020302");
		cardDestory.setCode("cardDestory");
		cardDestory.setDescription("卡片销毁");
		cardDestory.setButton(true);
		cardDestory.setParent(monitor_cardDestroy);
		permissionService.add(cardDestory);

		IPermission blackCard = permissionService.make("B03");
		blackCard.setCode("blackCard");
		blackCard.setDescription("黑名单卡管理");
		blackCard.setButton(false);
		blackCard.setParent(atmDailyWork);
		permissionService.add(blackCard);

		IPermission blackCardQuery = permissionService.make("B0301");
		blackCardQuery.setCode("blackCardQuery");
		blackCardQuery.setDescription("查询");
		blackCardQuery.setButton(true);
		blackCardQuery.setParent(blackCard);
		permissionService.add(blackCardQuery);

		IPermission blackCardAdd = permissionService.make("B0302");
		blackCardAdd.setCode("blackCardAdd");
		blackCardAdd.setDescription("增加");
		blackCardAdd.setButton(true);
		blackCardAdd.setParent(blackCard);
		permissionService.add(blackCardAdd);

		IPermission blackCardUpdate = permissionService.make("B0303");
		blackCardUpdate.setCode("blackCardUpdate");
		blackCardUpdate.setDescription("更改");
		blackCardUpdate.setButton(true);
		blackCardUpdate.setParent(blackCard);
		permissionService.add(blackCardUpdate);

		IPermission blackCardImportBtn = permissionService.make("B0304");
		blackCardImportBtn.setCode("blackCardImportBtn");
		blackCardImportBtn.setDescription("批量导入");
		blackCardImportBtn.setButton(true);
		blackCardImportBtn.setParent(blackCard);
		permissionService.add(blackCardImportBtn);

		IPermission blackCardDel = permissionService.make("B0305");
		blackCardDel.setCode("blackCardDel");
		blackCardDel.setDescription("删除");
		blackCardDel.setButton(true);
		blackCardDel.setParent(blackCard);
		permissionService.add(blackCardDel);

		IPermission device_runtimeParams = permissionService.make("B04");
		device_runtimeParams.setCode("device_runtimeParams");
		device_runtimeParams.setDescription("ATM参数管理");
		device_runtimeParams.setButton(false);
		device_runtimeParams.setParent(atmDailyWork);
		permissionService.add(device_runtimeParams);

		IPermission deviceRuntimeParamsQuery = permissionService.make("B0401");
		deviceRuntimeParamsQuery.setCode("deviceRuntimeParamsQuery");
		deviceRuntimeParamsQuery.setDescription("查询");
		deviceRuntimeParamsQuery.setButton(true);
		deviceRuntimeParamsQuery.setParent(device_runtimeParams);
		permissionService.add(deviceRuntimeParamsQuery);

		IPermission deviceRuntimeParamsActivation = permissionService.make("B0402");
		deviceRuntimeParamsActivation.setCode("deviceRuntimeParamsActivation");
		deviceRuntimeParamsActivation.setDescription("激活");
		deviceRuntimeParamsActivation.setButton(true);
		deviceRuntimeParamsActivation.setParent(device_runtimeParams);
		permissionService.add(deviceRuntimeParamsActivation);

		IPermission device_runtimeInfo = permissionService.make("B05");
		device_runtimeInfo.setCode("device_runtimeInfo");
		device_runtimeInfo.setDescription("客服信息采集");
		device_runtimeInfo.setButton(false);
		device_runtimeInfo.setParent(atmDailyWork);
		permissionService.add(device_runtimeInfo);

		IPermission deviceRuntimeInfoQuery = permissionService.make("B0501");
		deviceRuntimeInfoQuery.setCode("deviceRuntimeInfoQuery");
		deviceRuntimeInfoQuery.setDescription("查询");
		deviceRuntimeInfoQuery.setButton(true);
		deviceRuntimeInfoQuery.setParent(device_runtimeInfo);
		permissionService.add(deviceRuntimeInfoQuery);


		IPermission device_quittingNotice = permissionService.make("B06");
		device_quittingNotice.setCode("device_quittingNotice");
		device_quittingNotice.setDescription("ATM设备报停");
		device_quittingNotice.setButton(false);
		device_quittingNotice.setParent(atmDailyWork);
		permissionService.add(device_quittingNotice);

		IPermission quittingNoticeQuery = permissionService.make("B0601");
		quittingNoticeQuery.setCode("quittingNoticeQuery");
		quittingNoticeQuery.setDescription("查询");
		quittingNoticeQuery.setButton(true);
		quittingNoticeQuery.setParent(device_quittingNotice);
		permissionService.add(quittingNoticeQuery);


		IPermission quittingNoticeAdd = permissionService.make("B0602");
		quittingNoticeAdd.setCode("quittingNoticeAdd");
		quittingNoticeAdd.setDescription("增加");
		quittingNoticeAdd.setButton(true);
		quittingNoticeAdd.setParent(device_quittingNotice);
		permissionService.add(quittingNoticeAdd);

		IPermission quittingNoticeUpdate = permissionService.make("B0603");
		quittingNoticeUpdate.setCode("quittingNoticeUpdate");
		quittingNoticeUpdate.setDescription("更改");
		quittingNoticeUpdate.setButton(true);
		quittingNoticeUpdate.setParent(device_quittingNotice);
		permissionService.add(quittingNoticeUpdate);

		IPermission quittingNoticeDel = permissionService.make("B0604");
		quittingNoticeDel.setCode("quittingNoticeDel");
		quittingNoticeDel.setDescription("删除");
		quittingNoticeDel.setButton(true);
		quittingNoticeDel.setParent(device_quittingNotice);
		permissionService.add(quittingNoticeDel);

		IPermission device_move = permissionService.make("B07");
		device_move.setCode("device_move");
		device_move.setDescription("ATM设备移机");
		device_move.setButton(false);
		device_move.setParent(atmDailyWork);
		permissionService.add(device_move);

		IPermission deviceMoveQuery = permissionService.make("B0701");
		deviceMoveQuery.setCode("deviceMoveQuery");
		deviceMoveQuery.setDescription("待移机查询");
		deviceMoveQuery.setButton(true);
		deviceMoveQuery.setParent(device_move);
		permissionService.add(deviceMoveQuery);

		IPermission deviceMove = permissionService.make("B0702");
		deviceMove.setCode("deviceMove");
		deviceMove.setDescription("移机");
		deviceMove.setButton(true);
		deviceMove.setParent(device_move);
		permissionService.add(deviceMove);

		IPermission deviceMoveRecordQuery = permissionService.make("B0703");
		deviceMoveRecordQuery.setCode("deviceMoveRecordQuery");
		deviceMoveRecordQuery.setDescription("移机记录查询");
		deviceMoveRecordQuery.setButton(true);
		deviceMoveRecordQuery.setParent(device_move);
		permissionService.add(deviceMoveRecordQuery);

		IPermission moveDel = permissionService.make("B0704");
		moveDel.setCode("moveDel");
		moveDel.setDescription("删除");
		moveDel.setButton(true);
		moveDel.setParent(device_move);
		permissionService.add(moveDel);

		IPermission mapMarker = permissionService.make("B08");
		mapMarker.setCode("mapMarker");
		mapMarker.setDescription("地图标注");
		mapMarker.setButton(false);
		mapMarker.setParent(atmDailyWork);
		permissionService.add(mapMarker);

		IPermission mapMarkerQuery = permissionService.make("B0801");
		mapMarkerQuery.setCode("mapMarkerQuery");
		mapMarkerQuery.setDescription("查询");
		mapMarkerQuery.setButton(true);
		mapMarkerQuery.setParent(mapMarker);
		permissionService.add(mapMarkerQuery);

		IPermission crown = permissionService.make("B09");
		crown.setCode("crown");
		crown.setDescription("冠字号查询");
		crown.setButton(false);
		crown.setParent(atmDailyWork);
		permissionService.add(crown);

		IPermission crownQuery = permissionService.make("B0901");
		crownQuery.setCode("crownQuery");
		crownQuery.setDescription("查询");
		crownQuery.setButton(true);
		crownQuery.setParent(crown);
		permissionService.add(crownQuery);

		IPermission monitor = permissionService.make("C");
		monitor.setCode("monitor");
		monitor.setDescription("实时监控");
		monitor.setButton(false);
		monitor.setParent(root);
		permissionService.add(monitor);

		IPermission monitor_device = permissionService.make("C01");
		monitor_device.setCode("monitor_device");
		monitor_device.setDescription("状态监控");
		monitor_device.setButton(false);
		monitor_device.setParent(monitor);
		permissionService.add(monitor_device);

		IPermission remoteScreen = permissionService.make("C0101");
		remoteScreen.setCode("remoteScreen");
		remoteScreen.setDescription("远程抓屏");
		remoteScreen.setButton(true);
		remoteScreen.setParent(monitor_device);
		permissionService.add(remoteScreen);

		IPermission takeLog = permissionService.make("C0102");
		takeLog.setCode("takeLog");
		takeLog.setDescription("提取电子日志");
		takeLog.setButton(true);
		takeLog.setParent(monitor_device);
		permissionService.add(takeLog);

		IPermission close = permissionService.make("C0103");
		close.setCode("close");
		close.setDescription("关机");
		close.setButton(true);
		close.setParent(monitor_device);
		permissionService.add(close);

		IPermission restart = permissionService.make("C0104");
		restart.setCode("restart");
		restart.setDescription("重新启动");
		restart.setButton(true);
		restart.setParent(monitor_device);
		permissionService.add(restart);

		IPermission logicOpen = permissionService.make("C0105");
		logicOpen.setCode("logicOpen");
		logicOpen.setDescription("开启服务");
		logicOpen.setButton(true);
		logicOpen.setParent(monitor_device);
		permissionService.add(logicOpen);

		IPermission logicClose = permissionService.make("C0106");
		logicClose.setCode("logicClose");
		logicClose.setDescription("暂停服务");
		logicClose.setButton(true);
		logicClose.setParent(monitor_device);
		permissionService.add(logicClose);

		IPermission remoteBrowser = permissionService.make("C0107");
		remoteBrowser.setCode("remoteBrowser");
		remoteBrowser.setDescription("远程浏览");
		remoteBrowser.setButton(true);
		remoteBrowser.setParent(monitor_device);
		permissionService.add(remoteBrowser);

		IPermission remoteUpFile = permissionService.make("C010701");
		remoteUpFile.setCode("remoteUpFile");
		remoteUpFile.setDescription("上传");
		remoteUpFile.setButton(true);
		remoteUpFile.setParent(remoteBrowser);
		permissionService.add(remoteUpFile);

		IPermission remoteDownFile = permissionService.make("C010702");
		remoteDownFile.setCode("remoteDownFile");
		remoteDownFile.setDescription("下载");
		remoteDownFile.setButton(true);
		remoteDownFile.setParent(remoteBrowser);
		permissionService.add(remoteDownFile);

		IPermission screenCamera = permissionService.make("C0108");
		screenCamera.setCode("screenCamera");
		screenCamera.setDescription("屏幕录制");
		screenCamera.setButton(true);
		screenCamera.setParent(monitor_device);
		permissionService.add(screenCamera);

		IPermission reset = permissionService.make("C0109");
		reset.setCode("reset");
		reset.setDescription("强制复位");
		reset.setButton(true);
		reset.setParent(monitor_device);
		permissionService.add(reset);

		IPermission modStatusGraphic = permissionService.make("C0110");
		modStatusGraphic.setCode("modStatusGraphic");
		modStatusGraphic.setDescription("模块状态图形化展示");
		modStatusGraphic.setButton(true);
		modStatusGraphic.setParent(monitor_device);
		permissionService.add(modStatusGraphic);

		IPermission mapView = permissionService.make("C0111");
		mapView.setCode("mapView");
		mapView.setDescription("地图方式");
		mapView.setButton(true);
		mapView.setParent(monitor_device);
		permissionService.add(mapView);

		IPermission trans = permissionService.make("C02");
		trans.setCode("trans");
		trans.setDescription("交易监视");
		trans.setButton(false);
		trans.setParent(monitor);
		permissionService.add(trans);

		IPermission hits_trans = permissionService.make("C0201");
		hits_trans.setCode("hits_trans");
		hits_trans.setDescription("历史交易查询");
		hits_trans.setButton(false);
		hits_trans.setParent(trans);
		permissionService.add(hits_trans);

		IPermission hitsTransQuery = permissionService.make("C020101");
		hitsTransQuery.setCode("hitsTransQuery");
		hitsTransQuery.setDescription("查询");
		hitsTransQuery.setButton(true);
		hitsTransQuery.setParent(hits_trans);
		permissionService.add(hitsTransQuery);


		IPermission monitor_trans = permissionService.make("C0202");
		monitor_trans.setCode("monitor_trans");
		monitor_trans.setDescription("实时交易监控");
		monitor_trans.setButton(false);
		monitor_trans.setParent(trans);
		permissionService.add(monitor_trans);

		IPermission cash_init = permissionService.make("C0203");
		cash_init.setCode("cash_init");
		cash_init.setDescription("加钞信息查询");
		cash_init.setButton(false);
		cash_init.setParent(trans);
		permissionService.add(cash_init);

		IPermission cashInitQuery = permissionService.make("C020301");
		cashInitQuery.setCode("cashInitQuery");
		cashInitQuery.setDescription("查询");
		cashInitQuery.setButton(true);
		cashInitQuery.setParent(cash_init);
		permissionService.add(cashInitQuery);

		IPermission settlement = permissionService.make("C0204");
		settlement.setCode("settlement");
		settlement.setDescription("清机信息查询");
		settlement.setButton(false);
		settlement.setParent(trans);
		permissionService.add(settlement);

		IPermission settlementQuery = permissionService.make("C020401");
		settlementQuery.setCode("settlementQuery");
		settlementQuery.setDescription("查询");
		settlementQuery.setButton(true);
		settlementQuery.setParent(settlement);
		permissionService.add(settlementQuery);

		IPermission process = permissionService.make("C03");
		process.setCode("process");
		process.setDescription("进程监视");
		process.setButton(false);
		process.setParent(monitor);
		permissionService.add(process);

		IPermission hits_process = permissionService.make("C0301");
		hits_process.setCode("hits_process");
		hits_process.setDescription("历史黑名单进程");
		hits_process.setButton(false);
		hits_process.setParent(process);
		permissionService.add(hits_process);

		IPermission hitsProcessQuery = permissionService.make("C030101");
		hitsProcessQuery.setCode("hitsProcessQuery");
		hitsProcessQuery.setDescription("查询");
		hitsProcessQuery.setButton(true);
		hitsProcessQuery.setParent(hits_process);
		permissionService.add(hitsProcessQuery);

		IPermission monitor_process = permissionService.make("C0302");
		monitor_process.setCode("monitor_process");
		monitor_process.setDescription("实时进程监控");
		monitor_process.setButton(false);
		monitor_process.setParent(process);
		permissionService.add(monitor_process);

		IPermission monitorProcessAdd = permissionService.make("C030201");
		monitorProcessAdd.setCode("monitorProcessAdd");
		monitorProcessAdd.setDescription("创建");
		monitorProcessAdd.setButton(true);
		monitorProcessAdd.setParent(monitor_process);
		permissionService.add(monitorProcessAdd);

		IPermission monitorProcessUpdate = permissionService.make("C030202");
		monitorProcessUpdate.setCode("monitorProcessUpdate");
		monitorProcessUpdate.setDescription("修改");
		monitorProcessUpdate.setButton(true);
		monitorProcessUpdate.setParent(monitor_process);
		permissionService.add(monitorProcessUpdate);

		IPermission monitorProcessDel = permissionService.make("C030203");
		monitorProcessDel.setCode("monitorProcessDel");
		monitorProcessDel.setDescription("删除");
		monitorProcessDel.setButton(true);
		monitorProcessDel.setParent(monitor_process);
		permissionService.add(monitorProcessDel);

		IPermission softDistribute = permissionService.make("D");
		softDistribute.setCode("softDistribute");
		softDistribute.setDescription("软件分发");
		softDistribute.setButton(false);
		softDistribute.setParent(root);
		permissionService.add(softDistribute);

		IPermission version = permissionService.make("D01");
		version.setCode("version");
		version.setDescription("版本管理");
		version.setButton(false);
		version.setParent(softDistribute);
		permissionService.add(version);

		IPermission versionQuery = permissionService.make("D0101");
		versionQuery.setCode("versionQuery");
		versionQuery.setDescription("查询");
		versionQuery.setButton(true);
		versionQuery.setParent(version);
		permissionService.add(versionQuery);

		IPermission versionExportBtn = permissionService.make("D0102");
		versionExportBtn.setCode("versionExportBtn");
		versionExportBtn.setDescription("下发");
		versionExportBtn.setButton(true);
		versionExportBtn.setParent(version);
		permissionService.add(versionExportBtn);

		IPermission versionAdd = permissionService.make("D0103");
		versionAdd.setCode("versionAdd");
		versionAdd.setDescription("增加");
		versionAdd.setButton(true);
		versionAdd.setParent(version);
		permissionService.add(versionAdd);

		IPermission versionUpdate = permissionService.make("D0104");
		versionUpdate.setCode("versionUpdate");
		versionUpdate.setDescription("更改");
		versionUpdate.setButton(true);
		versionUpdate.setParent(version);
		permissionService.add(versionUpdate);

		IPermission versionDel = permissionService.make("D0105");
		versionDel.setCode("versionDel");
		versionDel.setDescription("删除");
		versionDel.setButton(true);
		versionDel.setParent(version);
		permissionService.add(versionDel);

		IPermission versionTypeAdd = permissionService.make("D0106");
		versionTypeAdd.setCode("versionTypeAdd");
		versionTypeAdd.setDescription("增加（软件分类）");
		versionTypeAdd.setButton(true);
		versionTypeAdd.setParent(version);
		permissionService.add(versionTypeAdd);

		IPermission versionTypeDel = permissionService.make("D0107");
		versionTypeDel.setCode("versionTypeDel");
		versionTypeDel.setDescription("删除（软件分类）");
		versionTypeDel.setButton(true);
		versionTypeDel.setParent(version);
		permissionService.add(versionTypeDel);

		IPermission versionTypeUpdate = permissionService.make("D0108");
		versionTypeUpdate.setCode("versionTypeUpdate");
		versionTypeUpdate.setDescription("修改（软件分类）");
		versionTypeUpdate.setButton(true);
		versionTypeUpdate.setParent(version);
		permissionService.add(versionTypeUpdate);

		IPermission advert = permissionService.make("D02");
		advert.setCode("advert");
		advert.setDescription("广告管理");
		advert.setButton(false);
		advert.setParent(softDistribute);
		permissionService.add(advert);

		IPermission advertQuery = permissionService.make("D0201");
		advertQuery.setCode("advertQuery");
		advertQuery.setDescription("查询");
		advertQuery.setButton(true);
		advertQuery.setParent(advert);
		permissionService.add(advertQuery);

		IPermission advertCreate = permissionService.make("D0202");
		advertCreate.setCode("advertCreate");
		advertCreate.setDescription("创建广告");
		advertCreate.setButton(true);
		advertCreate.setParent(advert);
		permissionService.add(advertCreate);

		IPermission advertWait = permissionService.make("D020201");
		advertWait.setCode("advertWait");
		advertWait.setDescription("创建等待插卡广告");
		advertWait.setButton(true);
		advertWait.setParent(advertCreate);
		permissionService.add(advertWait);

		IPermission advertTrans = permissionService.make("D020202");
		advertTrans.setCode("advertTrans");
		advertTrans.setDescription("创建交易页面广告");
		advertTrans.setButton(true);
		advertTrans.setParent(advertCreate);
		permissionService.add(advertTrans);

		IPermission advertText = permissionService.make("D020203");
		advertText.setCode("advertText");
		advertText.setDescription("创建文字滚动广告");
		advertText.setButton(true);
		advertText.setParent(advertCreate);
		permissionService.add(advertText);

		IPermission advertAnnoucement = permissionService.make("D020204");
		advertAnnoucement.setCode("advertAnnoucement");
		advertAnnoucement.setDescription("创建公告");
		advertAnnoucement.setButton(true);
		advertAnnoucement.setParent(advertCreate);
		permissionService.add(advertAnnoucement);

		IPermission advertDownAdvert = permissionService.make("D0203");
		advertDownAdvert.setCode("advertDownAdvert");
		advertDownAdvert.setDescription("下发广告");
		advertDownAdvert.setButton(true);
		advertDownAdvert.setParent(advert);
		permissionService.add(advertDownAdvert);

		IPermission advertPreview = permissionService.make("D0204");
		advertPreview.setCode("advertPreview");
		advertPreview.setDescription("广告预览");
		advertPreview.setButton(true);
		advertPreview.setParent(advert);
		permissionService.add(advertPreview);

		IPermission advertDel = permissionService.make("D0205");
		advertDel.setCode("advertDel");
		advertDel.setDescription("删除");
		advertDel.setButton(true);
		advertDel.setParent(advert);
		permissionService.add(advertDel);

		IPermission versionMonitor = permissionService.make("D03");
		versionMonitor.setCode("versionMonitor");
		versionMonitor.setDescription("分发监控");
		versionMonitor.setButton(false);
		versionMonitor.setParent(softDistribute);
		permissionService.add(versionMonitor);

		IPermission versionMonitorQuery = permissionService.make("D0301");
		versionMonitorQuery.setCode("versionMonitorQuery");
		versionMonitorQuery.setDescription("查询");
		versionMonitorQuery.setButton(true);
		versionMonitorQuery.setParent(versionMonitor);
		permissionService.add(versionMonitorQuery);

		IPermission versionMonitorDel = permissionService.make("D0302");
		versionMonitorDel.setCode("versionMonitorDel");
		versionMonitorDel.setDescription("撤销作业");
		versionMonitorDel.setButton(true);
		versionMonitorDel.setParent(versionMonitor);
		permissionService.add(versionMonitorDel);

		IPermission exportJobToExcel = permissionService.make("D0303");
		exportJobToExcel.setCode("exportJobToExcel");
		exportJobToExcel.setDescription("导出");
		exportJobToExcel.setButton(true);
		exportJobToExcel.setParent(versionMonitor);
		permissionService.add(exportJobToExcel);

		IPermission deviceVersion = permissionService.make("D04");
		deviceVersion.setCode("deviceVersion");
		deviceVersion.setDescription("设备版本");
		deviceVersion.setButton(false);
		deviceVersion.setParent(softDistribute);
		permissionService.add(deviceVersion);

		IPermission deviceVersionQuery = permissionService.make("D0401");
		deviceVersionQuery.setCode("deviceVersionQuery");
		deviceVersionQuery.setDescription("查询");
		deviceVersionQuery.setButton(true);
		deviceVersionQuery.setParent(deviceVersion);
		permissionService.add(deviceVersionQuery);

		IPermission caseManger = permissionService.make("E");
		caseManger.setCode("case");
		caseManger.setDescription("故障管理");
		caseManger.setButton(false);
		caseManger.setParent(root);
		permissionService.add(caseManger);

		IPermission notifyMould = permissionService.make("E01");
		notifyMould.setCode("faultConfig");
		notifyMould.setDescription("故障类型配置");
		notifyMould.setButton(false);
		notifyMould.setParent(caseManger);
		permissionService.add(notifyMould);

		IPermission faultClassify = permissionService.make("E02");
		faultClassify.setCode("notifyMould");
		faultClassify.setDescription("短信内容配置");
		faultClassify.setButton(false);
		faultClassify.setParent(caseManger);
		permissionService.add(faultClassify);

		IPermission casefault = permissionService.make("E03");
		casefault.setCode("casefault");
		casefault.setDescription("故障查询");
		casefault.setButton(false);
		casefault.setParent(caseManger);
		permissionService.add(casefault);

		IPermission casenotify = permissionService.make("E04");
		casenotify.setCode("casenotify");
		casenotify.setDescription("短信查询");
		casenotify.setButton(false);
		casenotify.setParent(caseManger);
		permissionService.add(casenotify);

		IPermission vendorCode = permissionService.make("E05");
		vendorCode.setCode("vendorCode");
		vendorCode.setDescription("厂商故障信息管理");
		vendorCode.setButton(false);
		vendorCode.setParent(caseManger);
		permissionService.add(vendorCode);

		IPermission vendorCodeQuery = permissionService.make("E0501");
		vendorCodeQuery.setCode("vendorCodeQuery");
		vendorCodeQuery.setDescription("查询");
		vendorCodeQuery.setButton(true);
		vendorCodeQuery.setParent(vendorCode);
		permissionService.add(vendorCodeQuery);

		IPermission vendorCodeImport = permissionService.make("E0502");
		vendorCodeImport.setCode("vendorCodeImport");
		vendorCodeImport.setDescription("批量导入");
		vendorCodeImport.setButton(true);
		vendorCodeImport.setParent(vendorCode);
		permissionService.add(vendorCodeImport);

		IPermission vendorCodeDelete = permissionService.make("E0503");
		vendorCodeDelete.setCode("vendorCodeDelete");
		vendorCodeDelete.setDescription("批量删除");
		vendorCodeDelete.setButton(true);
		vendorCodeDelete.setParent(vendorCode);
		permissionService.add(vendorCodeDelete);

		IPermission report = permissionService.make("F");
		report.setCode("report");
		report.setDescription("报表管理");
		report.setButton(false);
		report.setParent(root);
		permissionService.add(report);

		//基础类报表
		IPermission basicReport =  permissionService.make("F01");
		basicReport.setCode("basicReport");
		basicReport.setDescription("基础类报表");
		basicReport.setButton(false);
		basicReport.setParent(report);
		permissionService.add(basicReport);

		IPermission reportVendor = permissionService.make("F0101");
		reportVendor.setCode("reportVendor");
		reportVendor.setDescription("设备品牌统计报表");
		reportVendor.setButton(false);
		reportVendor.setParent(basicReport);
		permissionService.add(reportVendor);

		IPermission reportDeviceBoxDetail = permissionService.make("F0102");
		reportDeviceBoxDetail.setCode("reportDeviceBoxDetail");
		reportDeviceBoxDetail.setDescription("钞箱余额报表");
		reportDeviceBoxDetail.setButton(false);
		reportDeviceBoxDetail.setParent(basicReport);
		permissionService.add(reportDeviceBoxDetail);

		IPermission reporDeviceHardWare = permissionService.make("F0103");
		reporDeviceHardWare.setCode("reporDeviceHardWare");
		reporDeviceHardWare.setDescription("系统硬件配置表");
		reporDeviceHardWare.setButton(false);
		reporDeviceHardWare.setParent(basicReport);
		permissionService.add(reporDeviceHardWare);

		IPermission reportDeviceUseCount = permissionService.make("F0104");
		reportDeviceUseCount.setCode("reportDeviceUseCount");
		reportDeviceUseCount.setDescription("设备运行情况报表");
		reportDeviceUseCount.setButton(false);
		reportDeviceUseCount.setParent(basicReport);
		permissionService.add(reportDeviceUseCount);

		IPermission reportDeviceInfo = permissionService.make("F0105");
		reportDeviceInfo.setCode("reportDeviceInfo");
		reportDeviceInfo.setDescription("设备明细报表");
		reportDeviceInfo.setButton(false);
		reportDeviceInfo.setParent(basicReport);
		permissionService.add(reportDeviceInfo);

		//交易类报表
		IPermission transReport =  permissionService.make("F02");
		transReport.setCode("transReport");
		transReport.setDescription("交易类报表");
		transReport.setButton(false);
		transReport.setParent(report);
		permissionService.add(transReport);

		IPermission reportRetainCardDetail = permissionService.make("F0201");
		reportRetainCardDetail.setCode("reportRetainCardDetail");
		reportRetainCardDetail.setDescription("吞卡明细报表");
		reportRetainCardDetail.setButton(false);
		reportRetainCardDetail.setParent(transReport);
		permissionService.add(reportRetainCardDetail);

		IPermission reportRetainCardCount = permissionService.make("F0202");
		reportRetainCardCount.setCode("reportRetainCardCount");
		reportRetainCardCount.setDescription("吞卡统计报表");
		reportRetainCardCount.setButton(false);
		reportRetainCardCount.setParent(transReport);
		permissionService.add(reportRetainCardCount);

		IPermission reportTransactionCount = permissionService.make("F0203");
		reportTransactionCount.setCode("reportTransactionCount");
		reportTransactionCount.setDescription("交易统计报表");
		reportTransactionCount.setButton(false);
		reportTransactionCount.setParent(transReport);
		permissionService.add(reportTransactionCount);

		IPermission reportTransactionResult = permissionService.make("F0204");
		reportTransactionResult.setCode("reportTransactionResult");
		reportTransactionResult.setDescription("交易结果统计报表");
		reportTransactionResult.setButton(false);
		reportTransactionResult.setParent(transReport);
		permissionService.add(reportTransactionResult);

		IPermission reportCashIn = permissionService.make("F0205");
		reportCashIn.setCode("reportCashIn");
		reportCashIn.setDescription("加钞情况报表");
		reportCashIn.setButton(false);
		reportCashIn.setParent(transReport);
		permissionService.add(reportCashIn);

		IPermission reportSettlement = permissionService.make("F0206");
		reportSettlement.setCode("reportSettlement");
		reportSettlement.setDescription("清机情况报表");
		reportSettlement.setButton(false);
		reportSettlement.setParent(transReport);
		permissionService.add(reportSettlement);

		//故障类报表
		IPermission caseReport =  permissionService.make("F03");
		caseReport.setCode("caseReport");
		caseReport.setDescription("故障类报表");
		caseReport.setButton(false);
		caseReport.setParent(report);
		permissionService.add(caseReport);

//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F0301', null, 'caseTrendReportByMonth','故障趋势图(月)','F03','0');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F030101', null, 'exportToPngForMonth','导出图片','F0301','1');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F030102', null, 'exportToExcelForMonth','导出Excel','F0301','1');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F030103', null, 'exportToPdfForMonth','导出PDF','F0301','1');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F0302', null, 'caseTrendReportByDay','故障趋势图(日)','F03','0');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F030201', null, 'exportToPngForDay','导出图片','F0302','1');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F030202', null, 'exportToExcelForDay','导出Excel','F0302','1');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F030203', null, 'exportToPdfForDay','导出PDF','F0302','1');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F0303', null, 'caseStatisticsReportByBrand','故障统计图(品牌)','F03','0');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F030301', null, 'exportToPngForBrand','导出图片','F0303','1');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F030302', null, 'exportToExcelForBrand','导出Excel','F0303','1');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F030303', null, 'exportToPdfForBrand','导出PDF','F0303','1');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F0304', null, 'caseStatisticsReportByDevice','故障统计图(设备)','F03','0');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F030401', null, 'exportToPngForDevice','导出图片','F0304','1');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F030402', null, 'exportToExcelForDevice','导出Excel','F0304','1');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F030403', null, 'exportToPdfForDevice','导出PDF','F0304','1');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F0305', null, 'caseStatisticsReportByModule','故障统计图(模块)','F03','0');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F030501', null, 'exportToPngForModule','导出图片','F0305','1');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F030502', null, 'exportToExcelForModule','导出Excel','F0305','1');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F030503', null, 'exportToPdfForModule','导出PDF','F0305','1');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F0306', null, 'caseStatisticsReportByCaseType','故障统计图(类型)','F03','0');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F030601', null, 'exportToPngForType','导出图片','F0306','1');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F030602', null, 'exportToExcelForType','导出Excel','F0306','1');
//		INSERT INTO SM_PERMISSION(PERMISSION_ID,PER_ACTION,CODE,DESCRIPTION,PARENT_ID,IS_BUTTON) VALUES ('F030603', null, 'exportToPdfForType','导出PDF','F0306','1');

		//开机率报表
		IPermission openRateReport =  permissionService.make("F04");
		openRateReport.setCode("openRateReport");
		openRateReport.setDescription("开机率报表");
		openRateReport.setButton(false);
		openRateReport.setParent(report);
		permissionService.add(openRateReport);

		IPermission deviceOpenRate = permissionService.make("F0401");
		deviceOpenRate.setCode("deviceOpenRate");
		deviceOpenRate.setDescription("设备开机率");
		deviceOpenRate.setButton(false);
		deviceOpenRate.setParent(openRateReport);
		permissionService.add(deviceOpenRate);

		IPermission typeOpenRate = permissionService.make("F0402");
		typeOpenRate.setCode("typeOpenRate");
		typeOpenRate.setDescription("型号开机率");
		typeOpenRate.setButton(false);
		typeOpenRate.setParent(openRateReport);
		permissionService.add(typeOpenRate);

		IPermission orgOpenRate = permissionService.make("F0403");
		orgOpenRate.setCode("orgOpenRate");
		orgOpenRate.setDescription("机构开机率");
		orgOpenRate.setButton(false);
		orgOpenRate.setParent(openRateReport);
		permissionService.add(orgOpenRate);

		IPermission help = permissionService.make("Z");
		help.setCode("help");
		help.setDescription("系统帮助");
		help.setButton(false);
		help.setParent(root);
		permissionService.add(help);

		IPermission register = permissionService.make("Z01");
		register.setCode("register");
		register.setDescription("系统注册");
		register.setButton(false);
		register.setParent(help);
		permissionService.add(register);

		IPermission handBook = permissionService.make("Z02");
		handBook.setCode("handBook");
		handBook.setDescription("帮助手册");
		handBook.setButton(false);
		handBook.setParent(help);
		permissionService.add(handBook);

		IPermission system = permissionService.make("Z03");
		system.setCode("system");
		system.setDescription("关于系统");
		system.setButton(false);
		system.setParent(help);
		permissionService.add(system);

	}


	private void initOrg() {

		IOrganization root = orgService.make();
		root.setCode("root");
		root.setName("组织机构");
	    orgService.add(root);

		IOrganization bank = orgService.make();
		bank.setCode("shnsh001");
		bank.setName("上海农商行");
		bank.setOrganizationType(OrganizationType.BANK);
		bank.setOrganizationLevel(OrganizationLevel.TOTAL);
		bank.setParent(root);
		bank.setAddress("上海市南京路255号");
		bank.setZip("201201");
		bank.setDescription("上海农商行");
	    orgService.add(bank);

	    IOrganization bank001 = orgService.make();
	    bank001.setCode("shnsh002");
	    bank001.setName("上海农商行民生路分行");
	    bank001.setOrganizationType(OrganizationType.BANK);
	    bank001.setOrganizationLevel(OrganizationLevel.BRANCH);
	    bank001.setParent(bank);
	    bank001.setAddress("上海市民生路255号");
	    bank001.setZip("201201");
	    bank001.setDescription("上海农商行民生路分行");
	    orgService.add(bank001);

		IOrganization weihushang001 = orgService.make();
		weihushang001.setCode("yihua001");
		weihushang001.setName("深圳怡化");
		weihushang001.setOrganizationType(OrganizationType.MAINTAINER);
		weihushang001.setParent(root);
		weihushang001.setAddress("深圳市福田路255号");
		weihushang001.setZip("510012");
		weihushang001.setDescription("深圳怡化运维总部");
	    orgService.add(weihushang001);

	    IOrganization weihushang002 = orgService.make();
	    weihushang002.setCode("yihua002");
	    weihushang002.setName("深圳怡化南京分公司运维部");
	    weihushang002.setOrganizationType(OrganizationType.MAINTAINER);
	    weihushang002.setParent(weihushang001);
	    weihushang002.setAddress("南京市郁金香路2号");
	    weihushang002.setZip("210012");
	    weihushang002.setDescription("深圳怡化南京分公司运维部");
	    orgService.add(weihushang002);

	}
}
