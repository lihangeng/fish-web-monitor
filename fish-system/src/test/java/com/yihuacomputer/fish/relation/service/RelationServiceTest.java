package com.yihuacomputer.fish.relation.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Set;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IPermissionService;
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.permission.IRoleService;
import com.yihuacomputer.fish.api.permission.RoleType;
import com.yihuacomputer.fish.api.person.Gender;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.IPersonService;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.person.UserState;
import com.yihuacomputer.fish.api.relation.IRelationService;
import com.yihuacomputer.fish.api.relation.IRolePermissionRelation;
import com.yihuacomputer.fish.api.relation.IUserRoleRelation;
import com.yihuacomputer.fish.relation.H2TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class RelationServiceTest extends BindSessionInTest2{
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IPermissionService permissionService;
	@Autowired
	private IRelationService relationService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IPersonService personService;
	@Autowired
	private IOrganizationService organizationService;
	@Autowired
	private IRolePermissionRelation rolePermissionRelationService;
	@Autowired
	private IUserRoleRelation userRoleRelationService;

	//@Test
	public void test(){
		/**
		 * 测试findPermissonsByUser方法
		 */
        IOrganization organization1 = organizationService.make();
        organization1.setCode("1234TT");
        organization1.setName("TestTT");
        organizationService.add(organization1);

		 IPerson person2 = personService.make();
	        person2.setBirthday(new Date());
	        person2.setCode("0002TT");
	        person2.setEmail("123@qq.com");
	        person2.setGender(Gender.MALE);
	        person2.setMobile("12345678910");
	        person2.setName("person2");
	        person2.setOrganization(organization1);
	        person2.setPhone("025-00123456");
	        person2 = personService.add(person2);

	        IUser user2 = userService.make();
	        user2.setCode("321");
	        user2.setPerson(person2);
	        user2.setPlainText("123");
	        user2.setState(UserState.REMOVED);
	        user2 = userService.add(user2);

			IRole noRole = roleService.make("管理员5");
			noRole.setDescription("我是管理员5");
			noRole.setType(RoleType.SLAVE_USE);
			noRole.setInherit(true);
			noRole.setSystem(false);
			roleService.add(noRole);

			IPermission permission = permissionService.make("C001");
			permission.setDescription("我是C001");
			permissionService.add(permission);

			rolePermissionRelationService.link(noRole, permission);
			userRoleRelationService.link(user2, noRole);


			Set<IPermission> permissions = relationService.findPermissonsByUser(user2.getId());
			assertEquals(1,permissions.size());
			for(IPermission item : permissions){
				assertEquals("我是C001",item.getDescription());
			}
	}
}
