package com.yihuacomputer.fish.web.monitor.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.monitor.report.IStatusMonitorMapOrg;

/**
 * 地图监控，机构模式
 * 
 * @author pengwenchao
 * 
 */
public class MapMonitorForm {

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

    public MapMonitorForm() {

    }

    public MapMonitorForm(IStatusMonitorMapOrg mapOrg) {
        setAddress(mapOrg.getAddress());
        setCode(mapOrg.getCode());
        setDescription(mapOrg.getDescription());
        setId(mapOrg.getId());
        setLatitude(mapOrg.getLatitude());
        setLongtitude(mapOrg.getLongtitude());
        setName(mapOrg.getName());
        setOrgFlag(mapOrg.getOrgFlag());
        setOrgType(mapOrg.getOrgType());
        setState(mapOrg.getState());
        setZoom(mapOrg.getZoom());
    }

    public static List<MapMonitorForm> convert(List<IStatusMonitorMapOrg> list) {
        List<MapMonitorForm> result = new ArrayList<MapMonitorForm>();
        for (IStatusMonitorMapOrg item : list) {
            result.add(new MapMonitorForm(item));
        }
        return result;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getZoom() {
        return zoom;
    }

    public void setZoom(String zoom) {
        this.zoom = zoom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgFlag() {
        return orgFlag;
    }

    public void setOrgFlag(String orgFlag) {
        this.orgFlag = orgFlag;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
