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



    /**
     * 获取硬件主状态
     *
     * @return 硬件主状态
     */
	public DeviceStatus getStatus() {
		return this.cam;
	}

	 /**
     * 获取状态代码
     *
     * @return 状态代码
     */
	public String getCode() {
		return this.camCode;
	}

	/**
     * 设置硬件主状态
     *
     * @param cdm
     */
	public void setStatus(DeviceStatus cam) {
		this.cam = cam;
	}

	 /**
     * 设置状态码
     *
     * @param code
     */
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

	@Override
	public int hashCode() {
		final int prime=31;
        int result=1;
        result=prime*result+((cam==null)?0:cam.hashCode());
        result=prime*result+((camCode==null)?0:camCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
        if(this==obj){
        	return true;
        }
        if(obj==null){
        	return false;
        }
        if(getClass()!=obj.getClass()){
        	return false;
        }
        StatusCam other=(StatusCam)obj;
        if(cam!=other.cam){
        	return false;
        }
        if(camCode==null){
        	if(other.camCode!=null){
        		return false;
        	}
        }else if(!camCode.equals(other.camCode)){
    		return false;
        }
		return true;
	}


}
