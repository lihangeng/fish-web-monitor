package com.yihuacomputer.fish.monitor.entity.xfs.status;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusPin;

/**
 * 密码键盘状态接口
 *
 * @author zhangcheng
 *
 */
@Embeddable
public class StatusPin implements IStatusPin {

    @Enumerated(EnumType.STRING)
    @Column(name = "PIN_STATUS")
    private DeviceStatus pin;

    @Column(name = "PIN_CODE")
    private String pinCode;

    @Transient
    private String pinHwCode;

    public StatusPin() {
        this.pin = DeviceStatus.Unknown;
    }

    /**
     * 获取硬件主状态
     *
     * @return 硬件主状态
     */
    public DeviceStatus getStatus() {
        return this.pin;
    }

    /**
     * 设置硬件主状态
     *
     * @param pin
     */
    public void setStatus(DeviceStatus pin) {
        this.pin = pin;
    }

    /**
     * 获取状态代码
     *
     * @return 状态代码
     */
    public String getCode() {
        return this.pinCode;
    }

    /**
     * 设置状态代码
     *
     * @param code
     */
    public void setCode(String code) {
        this.pinCode = code;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pin == null) ? 0 : pin.hashCode());
        result = prime * result + ((pinCode == null) ? 0 : pinCode.hashCode());
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
        StatusPin other = (StatusPin) obj;
        if (pin != other.pin) {
            return false;
        }
        if (pinCode == null) {
            if (other.pinCode != null) {
                return false;
            }
        } else if (!pinCode.equals(other.pinCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String getHwCode() {
        return this.pinHwCode;
    }

    @Override
    public void setHwCode(String hwCode) {
        this.pinHwCode = hwCode;
    }

}
