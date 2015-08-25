package com.yihuacomputer.fish.relation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 描述角色和权限间的关系实体
 * 
 * @author SuYang
 * 
 * @date 2010-8-25
 * 
 */
@Entity
@Table(name = "SM_ROLE_PERMISSION")
public class RolePermissionObj {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SM_ROLE_PERMISSION")
	@SequenceGenerator(name = "SEQ_SM_ROLE_PERMISSION", sequenceName = "SEQ_SM_ROLE_PERMISSION")
	@Column(name = "ROLE_PERMISSION_ID")
	private Long id;

	@Column(name = "ROLE_ID")
	private Long roleId;
	@Column(name = "PERMISSION_ID")
	private String permissionId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	/**
	 * 根据指定的权限和角色创建关系实体
	 * 
	 * @param master
	 * @param role
	 * @return
	 */
	public static RolePermissionObj make(String permissionId, Long roleId) {
		RolePermissionObj obj = new RolePermissionObj();
		obj.permissionId = permissionId;
		obj.roleId = roleId;
		return obj;
	}
}
