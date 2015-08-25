package com.yihuacomputer.fish.monitor.entity.report;

import com.yihuacomputer.fish.api.monitor.report.IStatusMonitorMapOrg;

public class StatusMonitorMapOrg implements IStatusMonitorMapOrg {
    /**
     * 纬度
     */
    private String latitude;

    /**
     * 经度
     */
    private String longtitude;

    /**
     * 缩放级别
     */
    private String zoom;

    /**
     * ID
     */
    private long id;

    /**
     * 机构地址
     */
    private String address;

    /**
     * 机构编号
     */
    private String code;

    /**
     * 机构说明
     */
    private String description;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 机构标识
     */
    private String orgFlag;

    /**
     * 机构类型<BR>
     * BANK(0,"银行"), MAINTAINER(1, "维护商");
     */
    private String orgType;

    /**
     * 机构状态<BR>
     * NEW(0,"新建"), NORMAL(1, "正常"), LOCK(2, "锁定"), INVALID(3, "无效"), FREEZE(4,
     * "冻结");
     */
    private String state;

    @Override
    public String getLatitude() {
        return latitude;
    }

    @Override
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String getLongtitude() {
        return longtitude;
    }

    @Override
    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    @Override
    public String getZoom() {
        return zoom;
    }

    @Override
    public void setZoom(String zoom) {
        this.zoom = zoom;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getOrgFlag() {
        return orgFlag;
    }

    @Override
    public void setOrgFlag(String orgFlag) {
        this.orgFlag = orgFlag;
    }

    @Override
    public String getOrgType() {
        return orgType;
    }

    @Override
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

}
