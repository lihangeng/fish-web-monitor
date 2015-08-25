package com.yihuacomputer.fish.web.monitor.form;
/**
 * 模块硬件版本信息
 * 
 * @author pengwenchao
 * 
 */
public class HalVersion {

    /**
     * 硬件名称
     */
    private String name;

    /**
     * 硬件英文名称
     */
    private String enName;

    /**
     * SP version
     */
    private String spVersion;

    /**
     * Driver version
     */
    private String driverVersion;

    /**
     * FW version
     */
    private String fwVersion;

    /**
     * 返回SP version
     */
    public String getSpVersion() {
        return spVersion;
    }

    public void setSpVersion(String spVersion) {
        this.spVersion = spVersion;
    }

    /**
     * 返回Driver version
     * 
     * @return
     */
    public String getDriverVersion() {
        return driverVersion;
    }

    public void setDriverVersion(String driverVersion) {
        this.driverVersion = driverVersion;
    }

    /**
     * 返回 FW version
     * 
     * @return
     */
    public String getFwVersion() {
        return fwVersion;
    }

    public void setFwVersion(String fwVersion) {
        this.fwVersion = fwVersion;
    }

    /**
     * 返回硬件名称
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

}
