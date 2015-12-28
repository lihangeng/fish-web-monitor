package com.yihuacomputer.fish.system.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IPermissionService;

@Entity
@Table(name = "SM_PERMISSION")
public class Permission implements IPermission ,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5061010132143690700L;

	@Id
	@Column(name = "PERMISSION_ID")
	private String id;

	@Column(name = "CODE", unique = true, nullable = false, length = 40)
	private String code;

	@Column(name = "DESCRIPTION", nullable = true, length = 40)
	private String description;
	
	@Column(name = "PER_ACTION", length = 40)
	private String action;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "IS_BUTTON", columnDefinition="CHAR",length=1)	
	private boolean isButton;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = com.yihuacomputer.fish.system.entity.Permission.class)
	@JoinColumn(name = "PARENT_ID")
	private IPermission parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", targetEntity = com.yihuacomputer.fish.system.entity.Permission.class)
	private List<IPermission> children = new ArrayList<IPermission>();

	@Transient
	private int childrenSize = 0;

	@Transient
	private IPermissionService service;

	public Permission() {
	    this.isButton = false;
	}

	
	public boolean isButton() {
		return isButton;
	}


	public void setButton(boolean isButton) {
		this.isButton = isButton;
	}


	public Permission(String id) {
		this.id = id;
	}

	public IPermissionService getService() {
		return service;
	}

	public void setService(IPermissionService service) {
		this.service = service;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public IPermission getChild(String code) {
		for (IPermission item : getChildren()) {
			if (code.equals(item.getCode())) {
				return item;
			}
		}
		throw new NotFoundException("child not found");
	}

	@Override
	public Permission addChild(String id,String code, String description,boolean isButton) {
		Permission child = (Permission)service.add(id,code, description,isButton, this);
		children.add(child);
		return child;
	}

	@Override
	public void removeChild(String code) {
		IPermission child = getChild(code);
		getChildren().remove(child);
		service.remove(child);
	}

	@Override
	public List<IPermission> listChildren() {
		return getChildren();
	}

	@Override
	public IPermission getParent() {
		return parent;
	}

	@Override
	public void setParent(IPermission permission) {
		this.parent = permission;
	}

	public void copyProperties(IPermission permission) {
		setCode(permission.getCode());
		setDescription(permission.getDescription());
	}

	public List<IPermission> getChildren() {
		if (this.childrenSize < 1) {
			this.children = service.listChildByParentId(this.id);
			this.childrenSize = children.size();
		}
		return children;
	}

	public void setChildren(List<IPermission> children) {
		this.children = children;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
