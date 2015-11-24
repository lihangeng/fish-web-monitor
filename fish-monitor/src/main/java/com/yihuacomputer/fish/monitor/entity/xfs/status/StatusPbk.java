package com.yihuacomputer.fish.monitor.entity.xfs.status;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusPbk;

@Embeddable
public class StatusPbk implements IStatusPbk {

    @Enumerated(EnumType.STRING)
    @Column(name = "PBK_STATUS")
    private DeviceStatus pbk;

    @Column(name = "PBK_CODE")
    private String pbkCode;

    @Transient
    private String pbkHwCode;

    public StatusPbk() {
        this.pbk = DeviceStatus.NoDevice;
    }

    /**
     * 获取硬件主状态
     *
     * @return 硬件主状态
     */
    public DeviceStatus getStatus() {
        return this.pbk;
    }

    /**
     * 设置凭条打印张数
     *
     * @param rpr
     */
    public void setStatus(DeviceStatus pbk) {
        this.pbk = pbk;
    }

    /**
     * 获取状态代码
     *
     * @return 状态代码
     */
    public String getCode() {
        return this.pbkCode;
    }

    public void setCode(String code) {
        this.pbkCode = code;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pbk == null) ? 0 : pbk.hashCode());
        result = prime * result + ((pbkCode == null) ? 0 : pbkCode.hashCode());
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
        StatusPbk other = (StatusPbk) obj;
        if (pbk != other.pbk) {
            return false;
        }
        if (pbkCode == null) {
            if (other.pbkCode != null) {
                return false;
            }
        } else if (!pbkCode.equals(other.pbkCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String getHwCode() {
        return this.pbkHwCode;
    }

    @Override
    public void setHwCode(String hwCode) {
        this.pbkHwCode = hwCode;
    }
}
