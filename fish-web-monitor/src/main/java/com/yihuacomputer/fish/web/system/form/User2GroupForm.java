package com.yihuacomputer.fish.web.system.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.permission.IGroup;
import com.yihuacomputer.fish.api.person.IOrganization;

public class User2GroupForm {
	
	private long id;
	private String name;
	//先不参与操作
//	private IOrganization organization;
	
	private String orgCode = "";
	private String orgName = "";
	private String userName = "";
	
	public User2GroupForm(){
		
	}
	
	public User2GroupForm(IGroup group){
		this.id = group.getId();
		this.name = group.getName();
		
		IOrganization organization = group.getOrganization();
		if(organization != null){
			this.orgCode = organization.getCode();
			this.orgName = organization.getName();
//			IUser user = organization.getManager();
//			if(user != null){
//				this.userName = user.getName();
//			}
		}
	}
	
	
	public static List<User2GroupForm> toForm(List<IGroup> groups){
		List<User2GroupForm> forms = new ArrayList<User2GroupForm>();
		for(IGroup group : groups){
			forms.add(new User2GroupForm(group));
		}
		return forms;
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
