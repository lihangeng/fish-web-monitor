package com.yihuacomputer.fish.web.monitor.form;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;

/**
 * 设备状态TTU的信息
 * 
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2012-1-13 下午02:17:51
 */
public class StatusTtu {
    /**
     * 硬件主状态
     */
    private DeviceStatus status;

    private String statusName;

    /**
     * 状态代码
     */
    private String code;

    /**
     * 厂商硬件状态码
     */
    private String hwCode;

    public String getHwCode() {
        return hwCode;
    }

    public void setHwCode(String hwCode) {
        this.hwCode = hwCode;
    }

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

    public String getStatusName() {
        return statusName == null ? getStatus().getText() : statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
