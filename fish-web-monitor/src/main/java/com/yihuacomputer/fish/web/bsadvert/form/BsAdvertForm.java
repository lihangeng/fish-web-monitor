package com.yihuacomputer.fish.web.bsadvert.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.yihuacomputer.common.jackson.JsonUtils;



public class BsAdvertForm {
	
	private Logger logger = LoggerFactory.getLogger(BsAdvertForm.class);
	
	private long id;

	private long groupId;
	
	private String groupName;
	
	private String advertType;

	private String advertName;
	
	private String bsAdvertStatus;

	private String lastTime;

	private long userId;

	private String userName;

	private long activeUserId;
	
	private String activeUserName;

    private String resources;
    
    private String advertFileName;

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

	public String getBsAdvertStatus() {
		return bsAdvertStatus;
	}

	public void setBsAdvertStatus(String bsAdvertStatus) {
		this.bsAdvertStatus = bsAdvertStatus;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getActiveUserId() {
		return activeUserId;
	}

	public void setActiveUserId(long activeUserId) {
		this.activeUserId = activeUserId;
	}

	public String getActiveUserName() {
		return activeUserName;
	}

	public void setActiveUserName(String activeUserName) {
		this.activeUserName = activeUserName;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

    public String getAdvertType() {
		return advertType;
	}

	public void setAdvertType(String advertType) {
		this.advertType = advertType;
	}

	public List<BsAdvertResourceForm> getAdvertResources() {
        List<BsAdvertResourceForm> advertResources = new ArrayList<BsAdvertResourceForm>();
        if (StringUtils.isNotEmpty(this.resources)) {
            try {
                JsonUtils.om.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
                advertResources = JsonUtils.om.readValue(this.resources, new TypeReference<List<BsAdvertResourceForm>>() {
                });
            }
            catch (Exception e) {
            	logger.error(e.getMessage());
            }
        }
        return advertResources;
    }

	public String getAdvertFileName() {
		return advertFileName;
	}

	public void setAdvertFileName(String advertFileName) {
		this.advertFileName = advertFileName;
	}
	
}
