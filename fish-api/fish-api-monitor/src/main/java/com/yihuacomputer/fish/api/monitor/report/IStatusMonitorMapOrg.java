package com.yihuacomputer.fish.api.monitor.report;

/**
 * @author YiHua
 *
 */
public interface IStatusMonitorMapOrg {
    /**
     * 获取纬度
     * 
     * @return
     */
    public String getLatitude();

    /**
     * 设置纬度
     * 
     * @param latitude
     */
    public void setLatitude(String latitude);

    /**
     * 获取经度
     * 
     * @return
     */
    public String getLongtitude();

    /**
     * 设置经度
     * 
     * @param longtitude
     */
    public void setLongtitude(String longtitude);

    /**
     * 获取缩放级别
     * 
     * @return
     */
    public String getZoom();

    /**
     * 设置缩放级别
     * 
     * @param zoom
     */
    public void setZoom(String zoom);

    /**
     * 获取机构ID
     * 
     * @return
     */
    public long getId();

    /**
     * 设置机构ID
     * 
     * @param id
     */
    public void setId(long id);

    /**
     * 获取机构地址
     * 
     * @return
     */
    public String getAddress();

    /**
     * 设置机构地址
     * 
     * @param address
     */
    public void setAddress(String address);

    /**
     * 获取机构编号
     * 
     * @return
     */
    public String getCode();

    /**
     * 设置机构编号
     * 
     * @param code
     */
    public void setCode(String code);

    /**
     * 获取机构说明
     * 
     * @return
     */
    public String getDescription();

    /**
     * 设置机构说明
     * 
     * @param description
     */
    public void setDescription(String description);

    /**
     * 获取机构名称
     * 
     * @return
     */
    public String getName();

    /**
     * 设置机构名称
     * 
     * @param name
     */
    public void setName(String name);

    /**
     * 获取机构标识
     * 
     * @return
     */
    public String getOrgFlag();

    /**
     * 设置机构标识
     * 
     * @param orgFlag
     */
    public void setOrgFlag(String orgFlag);

    /**
     * 获取机构类型
     * 
     * @return
     */
    public String getOrgType();

    /**
     * 设置机构类型
     * 
     * @param orgType
     */
    public void setOrgType(String orgType);

    /**
     * 获取机构状态
     * 
     * @return
     */
    public String getState();

    /**
     * 设置机构状态
     * 
     * @param state
     */
    public void setState(String state);
}
