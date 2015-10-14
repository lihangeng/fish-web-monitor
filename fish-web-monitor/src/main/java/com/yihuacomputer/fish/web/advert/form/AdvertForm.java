package com.yihuacomputer.fish.web.advert.form;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.advert.IAdvert;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.VersionCfg;

public class AdvertForm {
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

    public AdvertForm() {
    }

    public AdvertForm(IAdvert advert) {
        this.id = advert.getId();
        this.advertType = advert.getAdvertType().name();
        this.advertDownMethod = advert.getAdvertDownMethod().name();
        this.advertValidity = advert.getAdvertValidity().name();
        this.createdTime = DateUtils.getTimestamp(advert.getCreatedTime());
        IVersion version = advert.getVersion();
        if (version != null) {
            this.setVersion(version);
        }
    }

    public void setVersion(IVersion version) {
        this.versionId = version.getId();
        this.versionType = version.getVersionType().getTypeName();
        this.versionFile = getVersionFile(versionType, version.getServerPath());
        this.versionStatus = version.getVersionStatus().getText();
        this.versionNo = version.getVersionNo();
    }

    private String getVersionFile(String typeName, String fileName) {
        File file = new File(VersionCfg.getVersionDir() + File.separator + typeName + File.separator + fileName);
        return file.exists() ? fileName : null;
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
                e.printStackTrace();
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

}
