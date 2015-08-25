package com.yihuacomputer.fish.web.version.form;

import com.yihuacomputer.fish.api.device.IDevice;

public class VersionDeviceForm {
    private long id;

    private String terminalId;

    private String ip;

    private String orgName;

    private String devType;

    private String devBrand;

    private String devCatalog;

    private String appVersion;

    public VersionDeviceForm() {
    }

    public VersionDeviceForm(IDevice device) {
        this.id = device.getId();
        this.terminalId = device.getTerminalId();
        this.ip = device.getIp().toString();
        this.orgName = device.getOrganization().getName();
        this.devType = device.getDevType().getName();
        this.devBrand = device.getDevType().getDevVendor().getName();
        this.devCatalog = device.getDevType().getDevCatalog().getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType;
    }

    public String getDevBrand() {
        return devBrand;
    }

    public void setDevBrand(String devBrand) {
        this.devBrand = devBrand;
    }

    public String getDevCatalog() {
        return devCatalog;
    }

    public void setDevCatalog(String devCatalog) {
        this.devCatalog = devCatalog;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

}
