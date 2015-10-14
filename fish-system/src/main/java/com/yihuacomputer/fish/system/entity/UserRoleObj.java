package com.yihuacomputer.fish.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 主帐号与角色之间的使用授权关系实体  
 * 
 * @author SuYang
 * 
 * @date 2010-8-25
 * 
 */
@Entity
@Table(name = "SM_USER_ROLE")
public class UserRoleObj implements Serializable {

	/** serialVersionUID **/
	private static final long serialVersionUID = 1304047025603006338L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SM_USER_ROLE")
	@SequenceGenerator(name = "SEQ_SM_USER_ROLE", sequenceName = "SEQ_SM_USER_ROLE")
	@Column(name = "USER_ROLE_ID")
	private Long id;

	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "ROLE_ID")
	private Long roleId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * 根据指定的主帐号和角色创建关系实体
	 * 
	 * @param master
	 * @param role
	 * @return
	 */
	public static UserRoleObj make(Long userId, Long roleId) {
		UserRoleObj obj = new UserRoleObj();
		obj.userId = userId;
		obj.roleId = roleId;
		return obj;
	}
}
