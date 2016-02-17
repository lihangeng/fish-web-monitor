package com.yihuacomputer.fish.web.bsadvert.form;

import com.yihuacomputer.fish.api.advert.bs.GroupType;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroup;



public class BsAdvertGroupForm {

	private long id;

	private int groupType;

	private long orgId;
	
	private String orgName;
	
	private int orgLevelId;
	
	private String orgLevel;

	private String groupName;
	
	private String resourcePath;
	
	private String activedAdv;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGroupType() {
		return groupType;
	}

	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public int getOrgLevelId() {
		return orgLevelId;
	}

	public void setOrgLevelId(int orgLevelId) {
		this.orgLevelId = orgLevelId;
	}

	public String getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}
	
	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	
	public String getActivedAdv() {
		return activedAdv;
	}

	public void setActivedAdv(String activedAdv) {
		this.activedAdv = activedAdv;
	}

	public void translate(IAdvertGroup advertGroup){
		
		int groupType = getGroupType();
		advertGroup.setGroupName(getGroupName());
		advertGroup.setGroupType((1==groupType)?GroupType.DEFAULT:GroupType.NORMAl);
		advertGroup.setOrgId(getOrgId());
		advertGroup.setPath(getResourcePath());
		
	}
	
}
