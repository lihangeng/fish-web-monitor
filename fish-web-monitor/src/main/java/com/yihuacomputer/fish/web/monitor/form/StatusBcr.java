package com.yihuacomputer.fish.web.monitor.form;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;


public class StatusBcr {
	/**
     * 获取硬件主状态
     */
    private DeviceStatus status = DeviceStatus.Unknown;

    private String statusName;
    /**
     * 获取状态代码
     */
    private String code;

    /**
     * 获取厂商故障代码
     */
    private String hwCode;
    
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

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
}
