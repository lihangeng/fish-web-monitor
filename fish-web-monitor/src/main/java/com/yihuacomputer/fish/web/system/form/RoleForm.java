package com.yihuacomputer.fish.web.system.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.permission.IRole;

/**
 * @author
 * @version
 * @date
 */
public class RoleForm {

	private long id;
	private String name;
	private String description;
	private String permissions;
	private boolean system;

	public RoleForm() {
	}

	/**
	 * @param role
	 */
	public RoleForm(IRole role) {
		this.id = role.getId();
		this.name = role.getName();
		// this.type = String.valueOf(role.getType().getId());
		this.description = role.getDescription();
		this.system = role.isSystem();
	}

	/**
	 * 转换
	 * @param resources
	 * @return
	 */
	public static List<RoleForm> convert(List<IRole> resources) {
		List<RoleForm> result = new ArrayList<RoleForm>();
		for (IRole resource : resources) {
			result.add(new RoleForm(resource));
		}
		return result;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// public String getType() {
	// return type;
	// }

	// public void setType(String type) {
	// this.type = type;
	// }
	//
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public boolean isSystem() {
		return system;
	}

	public void setSystem(boolean system) {
		this.system = system;
	}

}
