package com.yihuacomputer.fish.web.advert.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.yihuacomputer.common.jackson.JsonUtils;

/**
 * @author YiHua
 *
 */
public class AdvertForm {
	
	private Logger logger = LoggerFactory.getLogger(AdvertForm.class);
	
    private long id;

    private String advertType;

    private String advertDownMethod;

    private String advertValidity;

    private String createdTime;

    private String resources;

    private long versionId;

    private String versionStatus;

    private String versionFile;

    private String versionType;

    private String versionNo;

    private String userName;
    
    private String versionDesc;

    public AdvertForm() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdvertType() {
        return advertType;
    }

    public void setAdvertType(String advertType) {
        this.advertType = advertType;
    }

    public String getAdvertDownMethod() {
        return advertDownMethod;
    }

    public void setAdvertDownMethod(String advertDownMethod) {
        this.advertDownMethod = advertDownMethod;
    }

    public String getAdvertValidity() {
        return advertValidity;
    }

    public void setAdvertValidity(String advertValidity) {
        this.advertValidity = advertValidity;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
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
            	logger.error(String.format("[%s]", e));
            }
        }
        return advertResources;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public long getVersionId() {
        return versionId;
    }

    public void setVersionId(long versionId) {
        this.versionId = versionId;
    }

    public String getVersionStatus() {
        return versionStatus;
    }

    public void setVersionStatus(String versionStatus) {
        this.versionStatus = versionStatus;
    }

    public String getVersionFile() {
        return versionFile;
    }

    public void setVersionFile(String versionFile) {
        this.versionFile = versionFile;
    }

    public String getVersionType() {
        return versionType;
    }

    public void setVersionType(String versionType) {
        this.versionType = versionType;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


	public String getVersionDesc() {
		return versionDesc;
	}


	public void setVersionDesc(String versionDesc) {
		this.versionDesc = versionDesc;
	}

}