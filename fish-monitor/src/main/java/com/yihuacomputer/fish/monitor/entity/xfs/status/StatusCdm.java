package com.yihuacomputer.fish.monitor.entity.xfs.status;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusCdm;

/**
 * @author YiHua
 *
 */
@Embeddable
public class StatusCdm implements IStatusCdm ,Serializable {

	private static final long serialVersionUID = 1L;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "CDM_STATUS")
    private DeviceStatus cdm;

    @Column(name = "CDM_CODE")
    private String cdmCode;

    @Transient
    private String cdmHwCode;

    /**
     * 初始化
     */
    public StatusCdm() {
        this.cdm = DeviceStatus.Unknown;
    }

    /**
     * 获取硬件主状态
     *
     * @return 硬件主状态
     */
    @Override
    public DeviceStatus getStatus() {
        return this.cdm;
    }

    /**
     * 设置硬件主状态
     *
     * @param cdm
     */
    @Override
    public void setStatus(DeviceStatus cdm) {
        this.cdm = cdm;
    }

    /**
     * 获取状态代码
     *
     * @return 状态代码
     */
    @Override
    public String getCode() {
        return this.cdmCode;
    }

    /**
     * 设置状态码
     *
     * @param code
     */
    @Override
    public void setCode(String code) {
        this.cdmCode = code;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cdm == null) ? 0 : cdm.hashCode());
        result = prime * result + ((cdmCode == null) ? 0 : cdmCode.hashCode());
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
        StatusCdm other = (StatusCdm) obj;
        if (cdm != other.cdm) {
            return false;
        }
        if (cdmCode == null) {
            if (other.cdmCode != null) {
                return false;
            }
        } else if (!cdmCode.equals(other.cdmCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String getHwCode() {

        return this.cdmHwCode;
    }

    @Override
    public void setHwCode(String hwCode) {
        this.cdmHwCode = hwCode;
    }

}
