package com.yihuacomputer.fish.web.system.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IPermissionService;

/**
 * 
 * @author YiHua
 *
 */
public class PermissionForm {
	
	private String id;
	private String code;
	private String description;
	private String parent;
	private boolean isButton;

	public boolean isButton() {
		return isButton;
	}


	public void setButton(boolean isButton) {
		this.isButton = isButton;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public String getParent() {
		return parent;
	}


	public void setParent(String parent) {
		this.parent = parent;
	}

	
	public PermissionForm(IPermission permission) {
		id = permission.getId();
		code = permission.getCode();
		description = permission.getDescription();
		if(permission.getParent() != null) {
			parent = permission.getParent().getCode();
		}
	}
	
	/**
	 * 
	 * 方法描述 : 增加
	 * 
	 * @param service
	 */
	public void add(IPermissionService service){
		IPermission permission = service.make(code);		
		permission = service.add(id,code, description,isButton);
		id = permission.getId();
		this.parent = permission.getParent().getCode();
	}

	/**
	 * 
	 * 方法描述 : 更新
	 * 
	 * @param service
	 */

	public void update(IPermissionService service) {
		IPermission permission = service.get(id);
		permission.setCode(code);
		permission.setDescription(description);
		permission.setParent(permission);
		service.update(permission);
		this.parent = permission.getParent().getCode();
	}
	
	
	/**
	 * 
	 * 方法描述 : 转换
	 * 
	 * @param resources
	 * @return List
	 */

	public static List<PermissionForm> convert(List<IPermission> resources) {
		List<PermissionForm> result = new ArrayList<PermissionForm>();
		for(IPermission resource : resources) {
			result.add(new PermissionForm(resource));
		}
		return result;
	}
}
