package com.yihuacomputer.fish.monitor.entity.xfs.status;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusCam;
@Embeddable
public class StatusCam implements IStatusCam, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "CAM_STATUS")
    private DeviceStatus cam;

    @Column(name = "CAM_CODE")
    private String camCode;

    @Transient
    private String camHwCode;
	

	@Override
	public DeviceStatus getStatus() {
		return this.cam;
	}

	@Override
	public String getCode() {
		return this.camCode;
	}

	@Override
	public void setStatus(DeviceStatus cam) {
		this.cam = cam;
	}

	@Override
	public void setCode(String code) {
		this.camCode=code;
	}

	@Override
	public String getHwCode() {
		return this.camHwCode;
	}

	@Override
	public void setHwCode(String hwCode) {
		this.camHwCode = hwCode;
	}

}
