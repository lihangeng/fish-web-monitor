package com.yihuacomputer.fish.monitor.entity.xfs.status;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusBcr;
/**
 * @author YiHua
 *
 */
@Embeddable
public class StatusBcr implements IStatusBcr,Serializable{
private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    @Column(name = "BCR_STATUS")
    private DeviceStatus bcr;

    @Column(name = "BCR_CODE")
    private String bcrCode;

    @Transient
    private String bcrHwCode;
    
    /**
     * 初始化
     */
    public StatusBcr(){
    	this.bcr = DeviceStatus.Unknown;
    }
    /**
     * 获取硬件主状态
     *
     * @return 硬件主状态
     */
    @Override
	public DeviceStatus getStatus() {
		return this.bcr;
	}

	/**
     * 获取状态代码
     *
     * @return 状态代码
     */
    @Override
	public String getCode() {
		return this.bcrCode;
	}

	/**
     * 设置硬件主状态
     *
     * @param cdm
     */
    @Override
	public void setStatus(DeviceStatus bcr) {
		this.bcr = bcr;
	}

	 /**
     * 设置状态码
     *
     * @param code
     */
    @Override
	public void setCode(String code) {
		this.bcrCode = code;
	}

	@Override
	public String getHwCode() {
		// TODO Auto-generated method stub
		return this.bcrHwCode;
	}

	@Override
	public void setHwCode(String hwCode) {
		this.bcrHwCode = hwCode;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bcr == null) ? 0 : bcr.hashCode());
        result = prime * result + ((bcrCode == null) ? 0 : bcrCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        StatusBcr other = (StatusBcr) obj;
        if (bcr != other.bcr) {
            return false;
        }
        if (bcrCode == null) {
            if (other.bcrCode != null) {
                return false;
            }
        } else if (!bcrCode.equals(other.bcrCode)) {
            return false;
        }
        return true;
    }

}
