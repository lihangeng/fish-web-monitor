package com.yihuacomputer.fish.monitor.entity.xfs.status;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusSiu;

@Embeddable
public class StatusSiu implements IStatusSiu {

    @Enumerated(EnumType.STRING)
    @Column(name = "SIU_STATUS")
    private DeviceStatus siu;

    @Column(name = "SIU_CODE")
    private String siuCode;

    @Transient
    private String siuHwCode;

    public StatusSiu() {
        this.siu = DeviceStatus.Unknown;
    }

    /**
     * 获取硬件主状态
     *
     * @return 硬件主状态
     */
    public DeviceStatus getStatus() {
        return this.siu;
    }

    /**
     * 设置硬件主状态
     *
     * @param siu
     */
    public void setStatus(DeviceStatus siu) {
        this.siu = siu;
    }

    /**
     * 获取状态代码
     *
     * @return 状态代码
     */
    public String getCode() {
        return this.siuCode;
    }

    /**
     * 设置状态码
     *
     * @param code
     */
    public void setCode(String code) {
        this.siuCode = code;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((siu == null) ? 0 : siu.hashCode());
        result = prime * result + ((siuCode == null) ? 0 : siuCode.hashCode());
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
        StatusSiu other = (StatusSiu) obj;
        if (siu != other.siu) {
            return false;
        }
        if (siuCode == null) {
            if (other.siuCode != null) {
                return false;
            }
        } else if (!siuCode.equals(other.siuCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String getHwCode() {
        return this.siuHwCode;
    }

    @Override
    public void setHwCode(String hwCode) {
        this.siuHwCode = hwCode;
    }

}
