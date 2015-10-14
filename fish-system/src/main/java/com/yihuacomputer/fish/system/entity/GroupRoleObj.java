package com.yihuacomputer.fish.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * 组与角色之间的关系实体
 * 
 * @author liuwei
 * 
 * 
 */
//@Entity
//@Table(name = "SM_GROUP_ROLE")
public class GroupRoleObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SM_GROUP_ROLE")
	@SequenceGenerator(name = "SEQ_SM_GROUP_ROLE", sequenceName = "SEQ_SM_GROUP_ROLE")
	@Column(name = "RE_GROUP_ROLE_ID")
	private Long id;
	
	@Column(name = "GROUP_ID")
	private Long groupId;

	@Column(name = "ROLE_ID")
	private Long roleId;
	

	public static GroupRoleObj make(Long groupId, Long roleId) {
		GroupRoleObj obj = new GroupRoleObj();
		obj.setGroupId(groupId);
		obj.setRoleId(roleId);
		return obj;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}


}
