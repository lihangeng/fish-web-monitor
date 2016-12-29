package com.yihuacomputer.fish.web.index.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.IOrganization;

/**
 * @author YiHua
 *
 */
public class LoginUserForm {

	private String id;
	private String name;
	private String organizationCode;
	private String organizationName;
	private boolean isDynamicPassword;
	private boolean isNew;
	private List<String> permissions = new ArrayList<String>();
	
	/**
	 * @param master
	 * @param permissions
	 */
	public LoginUserForm(IUser master,List<IPermission> permissions) {
		this.id = master.getCode();
		this.name = master.getName();
		IOrganization organization = master.getOrganization();
		this.organizationCode = organization.getCode();
		this.organizationName = organization.getName();
		for(IPermission permission : permissions) {
			this.permissions.add(permission.getCode());
		}
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public List<String> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public boolean isDynamicPassword() {
		return isDynamicPassword;
	}

	public void setDynamicPassword(boolean isDynamicPassword) {
		this.isDynamicPassword = isDynamicPassword;
	}

	public boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
	}
	
}
