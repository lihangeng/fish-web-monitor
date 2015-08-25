package com.yihuacomputer.fish.relation.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IPermissionService;
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.permission.IRoleService;
import com.yihuacomputer.fish.api.permission.RoleType;
import com.yihuacomputer.fish.api.relation.IRolePermissionRelation;
import com.yihuacomputer.fish.relation.H2TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = H2TestConfig.class)
public class RolePermissionRelationServiceTest extends BindSessionInTest2{
	@Autowired
	private IRolePermissionRelation rolePermissionRelationService;
	@Autowired
	private IPermissionService permissionService;
	@Autowired
	private IRoleService roleService;


	@Test
	public void testLinkandUnlink(){
		IRole noRole = roleService.make("管理员");
		noRole.setDescription("我是管理员");
		noRole.setType(RoleType.SLAVE_USE);
		noRole.setInherit(true);
		noRole.setSystem(true);
		roleService.add(noRole);

		IRole supRole = roleService.make("超级管理员");
		supRole.setDescription("我是超级管理员");
		supRole.setType(RoleType.INNER);
		supRole.setInherit(true);
		supRole.setSystem(true);
		roleService.add(supRole);

		IPermission permission = permissionService.make("B001");
		permission.setCode("B001");
		permission.setDescription("我是001");
		permissionService.add(permission);
		assertEquals("B001",permission.getId());

		IPermission permission1 = permissionService.make("B002");
		permission1.setCode("B0011");
		permission1.setDescription("我是002");
		permission1.setParent(permission);
		permissionService.add(permission1);
		assertEquals("B002",permission1.getId());

		IPermission permission2 = permissionService.make("B003");
		permission2.setCode("B00111");
		permission2.setDescription("我是003");
		permissionService.add(permission2);
		assertEquals("B003",permission2.getId());

		/**
		 * 测试link,unlink和haslink 方法
		 */
		rolePermissionRelationService.link(supRole, permission);
		List<IPermission> lists= rolePermissionRelationService.listMenuByRole(supRole);
		assertEquals(1,lists.size());
		rolePermissionRelationService.link(noRole, permission);

		boolean re = rolePermissionRelationService.haslink(supRole, permission);
		assertTrue(re);
		rolePermissionRelationService.unlink(noRole, permission);
		boolean nu = rolePermissionRelationService.haslink(noRole, permission);
		assertTrue(!nu);

		/**
		 * 测试listPermissionByRole方法
		 */
		List<IPermission> permissions = rolePermissionRelationService.listPermissionByRole(supRole);
		assertEquals(1,permissions.size());

		List<IPermission> permissionss = rolePermissionRelationService.listMenuByRole(supRole);
        assertEquals(1,permissionss.size());

		/**
		 * 测试listRoleByPermission方法
		 */
		List<IRole> roles = rolePermissionRelationService.listRoleByPermission(permission);
		assertEquals(1,roles.size());
		for(IRole role : roles){
			assertEquals("超级管理员",role.getName());
		}

		/**
		 * 测试pagePermissionByRole
		 */
		IPageResult<IPermission> page = rolePermissionRelationService.pagePermissionByRole(0, 10, supRole);
		assertEquals(1,page.getTotal());

		/**
		 * 测试pageUnlinkPermissionByRole
		 */
		IPageResult<IPermission> unlinkPage = rolePermissionRelationService.pageUnlinkPermissionByRole(0, 10, supRole);
		assertEquals(1,unlinkPage.getTotal());
	}
}
