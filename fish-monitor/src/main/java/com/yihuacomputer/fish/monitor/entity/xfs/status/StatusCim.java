package com.yihuacomputer.fish.monitor.entity.xfs.status;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusCim;

/**
 * 存款模块状态接口
 *
 * @author zhangcheng
 *
 */
@Embeddable
public class StatusCim implements IStatusCim  ,Serializable {

	private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    @Column(name = "CIM_STATUS")
    private DeviceStatus cim;

    @Column(name = "CIM_CODE")
    private String cimCode;

    @Transient
    private String cimHwCode;

    public StatusCim() {
        this.cim = DeviceStatus.Unknown;
    }

    /**
     * 获取硬件主状态
     *
     * @return 硬件主状态
     */
    public DeviceStatus getStatus() {
        return this.cim;
    }

    /**
     * 设置硬件主状态
     *
     * @param cim
     */
    public void setStatus(DeviceStatus cim) {
        this.cim = cim;
    }

    /**
     * 获取状态代码
     *
     * @return 状态代码
     */
    public String getCode() {
        return this.cimCode;
    }

    /**
     * 设置状态码
     *
     * @param code
     */
    public void setCode(String code) {
        this.cimCode = code;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cim == null) ? 0 : cim.hashCode());
        result = prime * result + ((cimCode == null) ? 0 : cimCode.hashCode());
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
        StatusCim other = (StatusCim) obj;
        if (cim != other.cim) {
            return false;
        }
        if (cimCode == null) {
            if (other.cimCode != null) {
                return false;
            }
        } else if (!cimCode.equals(other.cimCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String getHwCode() {
        return this.cimHwCode;
    }

    @Override
    public void setHwCode(String hwCode) {
        this.cimHwCode = hwCode;
    }

}
