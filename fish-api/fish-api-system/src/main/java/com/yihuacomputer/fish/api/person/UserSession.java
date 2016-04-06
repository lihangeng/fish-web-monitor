package com.yihuacomputer.fish.api.person;

import java.io.Serializable;

public class UserSession implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
     * 用户ID
     */
    private long userId;

    /**
     * 用户名
     */
    private String userCode;

    /**
     * 用户名字
     */
    private String userName;

    /**
     * 人员状态
     */
    private UserState UserState;

    /**
     * 机构ID
     */
    private long orgId;

    /**
     * 机构名称
     */
    private String orgName;
    
    /**
     * 机构编码
     */
    private String orgCode;
    
    private String personId;


    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * 机构服务对象ID
     */
    private long orgServiceObjectId;

    /**
     * 机构服务对象名称
     */
    private String orgServiceObjectName;

    /**
     * 机构标识
     */
    private String orgFlag;

    /**
     * 机构类型
     */
    private OrganizationType orgType;

    private String mapUrl;

    public UserState getUserState() {
        return UserState;
    }

    public void setUserState(UserState userState) {
        UserState = userState;
    }

    public OrganizationType getOrgType() {
        return orgType;
    }

    public void setOrgType(OrganizationType orgType) {
        this.orgType = orgType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgFlag() {
        return orgFlag;
    }

    public void setOrgFlag(String orgFlag) {
        this.orgFlag = orgFlag;
    }

    public long getOrgServiceObjectId() {
        return orgServiceObjectId;
    }

    public void setOrgServiceObjectId(long orgServiceObjectId) {
        this.orgServiceObjectId = orgServiceObjectId;
    }

    public String getOrgServiceObjectName() {
        return orgServiceObjectName;
    }

    public void setOrgServiceObjectName(String orgServiceObjectName) {
        this.orgServiceObjectName = orgServiceObjectName;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}
    
}
