package com.yihuacomputer.fish.system.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.permission.IRoleService;
import com.yihuacomputer.fish.api.permission.RoleType;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.IPersonService;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.person.UserState;
import com.yihuacomputer.fish.api.relation.IUserRoleRelation;
import com.yihuacomputer.fish.system.H2TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class UserRoleRelationServiceTest extends BindSessionInTest2{
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserRoleRelation userRoleRelationService;
	@Autowired
	private IPersonService personService;

	@Test
	public void test(){
		IRole noRole = roleService.make("管理员1");
		noRole.setDescription("我是管理员1");
		noRole.setType(RoleType.SLAVE_USE);
		noRole.setInherit(true);
		noRole.setSystem(true);
		roleService.add(noRole);

		IRole supRole = roleService.make("超级管理员1");
		supRole.setDescription("我是超级管理员1");
		supRole.setType(RoleType.INNER);
		supRole.setInherit(true);
		supRole.setSystem(true);
		roleService.add(supRole);

		 IPerson person1 = personService.add("person3");
		 IPerson person2 = personService.add("person4");

		IUser user2 = userService.make();
		user2.setCode("123");
		user2.setPlainText("123");
		user2.setPerson(person1);
		user2.setState(UserState.REMOVED);
		user2.setPlainText("aaaaa");
		user2.setSystem(false);
		userService.add(user2);

		IUser user1 = userService.make();
		user1.setCode("456");
		user1.setPlainText("456");
		user1.setPerson(person2);
		user1.setState(UserState.NEW);
		user1.setSystem(true);
		userService.add(user1);


		/**
		 * 测试link方法
		 */
		userRoleRelationService.link(user2, supRole);
		/**
		 * 测试listUserByRole
		 */
		List<IUser> users = userRoleRelationService.listUserByRole(supRole);
		assertEquals(1,users.size());
		/**
		 * 测试listRoleByUser方法
		 */
		List<IRole> roles = userRoleRelationService.listRoleByUser(user2);
		assertEquals(1,roles.size());
		/**
		 * 测试pageRoleByUser方法
		 */
		IFilter filter = new Filter();
		filter.eq("name", "超级管理员1");
		IPageResult<IRole> page = userRoleRelationService.pageRoleByUser(0, 10, user2, filter);
		assertEquals(1, page.getTotal());
		/**
		 * 测试pageUnlinkRoleByUser
		 */
		IFilter filter1 = new Filter();
		filter1.eq("name", "管理员1");
		IPageResult<IRole> pageRole = userRoleRelationService.pageUnlinkRoleByUser(0, 0, user2, filter1);
//		System.out.println("EEEEE"+pageRole.getTotal());
		assertEquals(1,pageRole.getTotal());
		/**
		 * 测试unlink方法
		 */
		userRoleRelationService.unlink(user2, supRole);
		List<IUser> oUser = userRoleRelationService.listUserByRole(supRole);
		assertEquals(0,oUser.size());

	}
}
