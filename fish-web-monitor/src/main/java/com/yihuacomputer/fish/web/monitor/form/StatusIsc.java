package com.yihuacomputer.fish.web.monitor.form;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;

/**
 * 身份证扫描仪状态ISC的信息
 *
 * @author 王佩琪
 */
public class StatusIsc {
    /**
     * 硬件主状态
     */
    private DeviceStatus status = DeviceStatus.Unknown;

    private String statusName;

    /**
     * 状态代码
     */
    private String code;

    /**
     * 厂商硬件状态码
     */
    private String hwCode;

    /**
     * media状态
     */
    private String media;

    /**
     * 芯片模块状态
     */
    private String chipModule;

    public DeviceStatus getStatus() {
        return status;
    }

    public void setStatus(DeviceStatus status) {
        this.status = status;
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

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getChipModule() {
        return chipModule;
    }

    public void setChipModule(String chipModule) {
        this.chipModule = chipModule;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}