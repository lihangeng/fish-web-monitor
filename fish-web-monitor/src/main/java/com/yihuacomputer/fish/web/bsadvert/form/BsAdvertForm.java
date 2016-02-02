package com.yihuacomputer.fish.web.bsadvert.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.web.advert.form.AdvertResourceForm;



public class BsAdvertForm {
	private long id;

	private long groupId;
	
	private String groupName;
	
	private String advertType;

	private String advertName;
	
	private boolean actived;

	private String lastTime;

	private long personId;

	private String personName;

	private long activePersonId;
	
	private String activePersonName;

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

	public List<AdvertResourceForm> getAdvertResources() {
        List<AdvertResourceForm> advertResources = new ArrayList<AdvertResourceForm>();
        if (StringUtils.isNotEmpty(this.resources)) {
            try {
                JsonUtils.om.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
                advertResources = JsonUtils.om.readValue(this.resources, new TypeReference<List<AdvertResourceForm>>() {
                });
            }
            catch (Exception e) {
                e.printStackTrace();
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
