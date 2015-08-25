package com.yihuacomputer.fish.web.system.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.permission.IRole;

public class Group2RoleForm {
	
	private long id;
	private String name;
	private String type;
	
	private String inherit;
	private String autoRelation;
	
	//organization
	private String orgCode;
	private String orgName;
	
	//organization-user
	private String userName;
	
	public Group2RoleForm(){
		
	}
	
	public Group2RoleForm(IRole role){
		this.id = role.getId();
		this.name = role.getName();
		this.type = role.getType().getText();
		//TODO
	}
	
	public static List<Group2RoleForm> toForm(List<IRole> roles){
		List<Group2RoleForm> result = new ArrayList<Group2RoleForm>();
		for(IRole role : roles){
			result.add(new Group2RoleForm(role));
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getInherit() {
		return inherit;
	}

	public void setInherit(String inherit) {
		this.inherit = inherit;
	}

	public String getAutoRelation() {
		return autoRelation;
	}

	public void setAutoRelation(String autoRelation) {
		this.autoRelation = autoRelation;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	

}
