package com.yihuacomputer.fish.monitor.entity.xfs.status;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusIsc;

@Embeddable
public class StatusIsc implements IStatusIsc {

    @Enumerated(EnumType.STRING)
    @Column(name = "ISC_STATUS")
    private DeviceStatus isc;

    @Column(name = "ISC_CODE")
    private String iscCode;

    @Transient
    private String iscHwCode;

    public StatusIsc() {
        this.isc = DeviceStatus.Unknown;
    }

    /**
     * 获取硬件主状态
     *
     * @return 硬件主状态
     */
    public DeviceStatus getStatus() {
        return this.isc;
    }

    /**
     * 设置硬件状态
     *
     * @param idc
     */
    public void setStatus(DeviceStatus isc) {
        this.isc = isc;
    }

    /**
     * 获取状态代码
     *
     * @return 状态代码
     */
    public String getCode() {
        return this.iscCode;
    }

    /**
     * 设置状态码
     *
     * @param code
     */
    public void setCode(String code) {
        this.iscCode = code;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((isc == null) ? 0 : isc.hashCode());
        result = prime * result + ((iscCode == null) ? 0 : iscCode.hashCode());
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
        StatusIsc other = (StatusIsc) obj;
        if (isc != other.isc) {
            return false;
        }
        if (iscCode == null) {
            if (other.iscCode != null) {
                return false;
            }
        } else if (!iscCode.equals(other.iscCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String getHwCode() {
        return this.iscHwCode;
    }

    @Override
    public void setHwCode(String hwCode) {
        this.iscHwCode = hwCode;
    }

}
