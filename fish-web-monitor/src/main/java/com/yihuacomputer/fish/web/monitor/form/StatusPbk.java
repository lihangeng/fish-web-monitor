package com.yihuacomputer.fish.web.monitor.form;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;

/**
 * 存折打印机
 * 
 * @author pengwenchao
 *
 */
public class StatusPbk {
    /**
     * 获取硬件主状态
     * 
     * @return 硬件主状态
     */
    public DeviceStatus status;

    private String statusName;

    /**
     * 获取回收匣状态
     * 
     * @return 回收匣状态
     */
    public String bin;

    /**
     * 获取墨水状态
     * 
     * @return 墨水状态
     */
    public String ink;

    /**
     * 获取media状态
     * 
     * @return media状态
     */
    public String media;

    /**
     * 获取纸张供应状态
     * 
     * @return 纸张供应状态
     */
    public String supplyLevel;

    /**
     * 获取色带状态
     * 
     * @return 色带状态
     */
    public String toner;

    /**
     * 获取状态代码
     * 
     * @return 状态代码
     */
    public String code;

    /**
     * 厂商硬件状态码
     */
    private String hwCode;

    public DeviceStatus getStatus() {
        return status;
    }

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getInk() {
        return ink;
    }

    public void setInk(String ink) {
        this.ink = ink;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getSupplyLevel() {
        return supplyLevel;
    }

    public void setSupplyLevel(String supplyLevel) {
        this.supplyLevel = supplyLevel;
    }

    public String getToner() {
        return toner;
    }

    public void setToner(String toner) {
        this.toner = toner;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHwCode() {
        return hwCode;
    }

    public void setHwCode(String hwCode) {
        this.hwCode = hwCode;
    }
}
