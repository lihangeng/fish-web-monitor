package com.yihuacomputer.fish.system.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IPermissionService;
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.permission.IRoleService;
import com.yihuacomputer.fish.api.permission.RoleType;
import com.yihuacomputer.fish.system.H2TestConfig;
/**
 * 角色权限管理测试类
 * @author wangchao
 *
 */
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class PermissionServiceTest extends BindSessionInTest2{
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IPermissionService permissionService;

	@Test
	public void testAdd(){
		assertNotNull(roleService);
		IRole role = roleService.make("管理员");
		role.setType(RoleType.PERMISSION);
		role.setDescription("这是管理员角色");
		role.setSystem(true);
		role.setAutoRelation(false);
		role.setInherit(false);
		roleService.add(role);

		IPermission parent = permissionService.make("A001");
		parent.setCode("A001");
		parent.setDescription("信息管理");
		parent.setButton(false);
		permissionService.add(parent);
	    assertEquals(parent.getId(),"A001");

		IPermission permission = permissionService.make("A002");
		permission.setCode("A002");
		permission.setAction("person");
		permission.setDescription("人员管理");
		permission.setButton(false);
		permission.setParent(parent);
		permissionService.add(permission);

		parent.addChild("A003","A003", "aaa",false);

		List<IPermission> permissions = permissionService.list();
		assertEquals(3,permissions.size());
	}

	@Test
	public void testRemoveandUpdate(){
		IRole role = roleService.make("管理员1");
		role.setType(RoleType.INNER);
		role.setDescription("我是管理员");
		roleService.add(role);

		IRole role1 = roleService.make("普通用户");
		role1.setType(RoleType.RESOURCE);
		role1.setDescription("普通用户");
		roleService.add(role1);
		List<IRole> roles = roleService.list();
		assertTrue(roles.size()>0);

		roleService.remove("普通用户");
		List<IRole> list = roleService.list();
		System.out.println(list.size());
		assertTrue(roles.size()>0);

		role.setName("超级管理员");
		roleService.update(role);
		assertEquals("超级管理员",role.getName());


		IPermission permission = permissionService.make("B001");
		permission.setCode("B001");
		permission.setDescription("我是001");
		permission.setButton(false);
		permissionService.add(permission);


	}

	@Test
	public void testListChildByParentId(){
		IPermission permission = permissionService.make("B004");
		permission.setCode("B004");
		permission.setDescription("我是004");
		permissionService.add(permission);

		IPermission permission1 = permissionService.make("B005");
		permission1.setCode("我是005");
		permission1.setDescription("我是005");
		permission1.setAction("005");
		permission1.setParent(permission);
		permissionService.add(permission1);

		IPermission permission2 = permissionService.make("B006");
		permission2.setCode("B006");
		permission2.setDescription("我是006");
		permission2.setAction("006");
		permission2.setParent(permission);
		permissionService.add(permission2);
		System.out.println(permission.getId());

		List<IPermission> permissions = permissionService.listChildByParentId(permission.getId());
		for(IPermission items : permissions){
			System.out.println(items.getDescription());
		}


	}

	@Test
	@Ignore
	public void testWithSybase(){

	}


}
