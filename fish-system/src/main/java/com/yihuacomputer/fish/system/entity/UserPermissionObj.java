package com.yihuacomputer.fish.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SM_USER_PERMISSION")
public class UserPermissionObj {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SM_USER_PERMISSION")
	@SequenceGenerator(name = "SEQ_SM_USER_PERMISSION", sequenceName = "SEQ_SM_USER_PERMISSION")
	@Column(name = "USER_PERMISSION_ID")
	private Long id;
	
	@Column(name = "USER_ID")
	private Long userId;
	
	@Column(name = "PERMISSION_ID")
	private String permissionId;

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

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	
	public static UserPermissionObj make(String permissionId, Long userId){
		UserPermissionObj obj = new UserPermissionObj();
		obj.permissionId = permissionId;
		obj.userId = userId;
		return obj;
	}
}
