package com.yihuacomputer.fish.monitor.entity.xfs.status;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusTtu;

/**
 * 文本终端状态接口
 *
 * @author zhangcheng
 *
 */
@Embeddable
public class StatusTtu implements IStatusTtu ,Serializable {

	private static final long serialVersionUID = 1L;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "TTU_STATUS")
    private DeviceStatus ttu;

    @Column(name = "TTU_CODE")
    private String ttuCode;

    @Transient
    private String ttuHwCode;

    public StatusTtu() {
        this.ttu = DeviceStatus.Unknown;
    }

    /**
     * 获取硬件主状态
     *
     * @return 硬件主状态
     */
    public DeviceStatus getStatus() {
        return this.ttu;
    }

    /**
     * 设置硬件主状态
     *
     * @param ttu
     */
    public void setStatus(DeviceStatus ttu) {
        this.ttu = ttu;
    }

    /**
     * 获取状态代码
     *
     * @return 状态代码
     */
    public String getCode() {
        return this.ttuCode;
    }

    /**
     * 设置状态码
     *
     * @param code
     */
    public void setCode(String code) {
        this.ttuCode = code;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ttu == null) ? 0 : ttu.hashCode());
        result = prime * result + ((ttuCode == null) ? 0 : ttuCode.hashCode());
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
        StatusTtu other = (StatusTtu) obj;
        if (ttu != other.ttu) {
            return false;
        }
        if (ttuCode == null) {
            if (other.ttuCode != null) {
                return false;
            }
        } else if (!ttuCode.equals(other.ttuCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String getHwCode() {
        return this.ttuHwCode;
    }

    @Override
    public void setHwCode(String hwCode) {
        this.ttuHwCode = hwCode;
    }

}
