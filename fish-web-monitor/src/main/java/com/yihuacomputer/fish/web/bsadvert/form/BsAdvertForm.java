package com.yihuacomputer.fish.web.bsadvert.form;



public class BsAdvertForm {
	private long id;

	private long groupId;
	
	private String groupName;

	private String advertName;
	
	private boolean actived;

	private String lastTime;

	private long personId;

	private String personName;

	private long activePersonId;
	
	private String activePersonName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getAdvertName() {
		return advertName;
	}

	public void setAdvertName(String advertName) {
		this.advertName = advertName;
	}

	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public long getActivePersonId() {
		return activePersonId;
	}

	public void setActivePersonId(long activePersonId) {
		this.activePersonId = activePersonId;
	}

	public String getActivePersonName() {
		return activePersonName;
	}

	public void setActivePersonName(String activePersonName) {
		this.activePersonName = activePersonName;
	}

	
}
