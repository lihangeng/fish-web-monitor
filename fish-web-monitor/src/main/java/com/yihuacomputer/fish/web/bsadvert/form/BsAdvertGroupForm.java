package com.yihuacomputer.fish.web.bsadvert.form;



public class BsAdvertGroupForm {

	private long id;

	private String groupType;

	private long orgId;
	
	private String orgName;
	
	private int orgLevelId;
	
	private String orgLevel;

	private String groupName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
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
	
}
