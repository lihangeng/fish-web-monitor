package com.yihuacomputer.fish.monitor.entity.xfs.status;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusJpr;

/**
 * @author YiHua
 *
 */
@Embeddable
public class StatusJpr implements IStatusJpr  ,Serializable {

	private static final long serialVersionUID = 1L;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "JPR_STATUS")
    private DeviceStatus jpr;

    @Column(name = "JPR_CODE")
    private String jprCode;

    @Transient
    private String jprHwCode;

    /**
     * 初始化
     */
    public StatusJpr() {
        this.jpr = DeviceStatus.Unknown;
    }

    /**
     * 获取硬件主状态
     *
     * @return 硬件主状态
     */
    @Override
    public DeviceStatus getStatus() {
        return this.jpr;
    }

    /**
     * 设置硬件状态
     *
     * @param jpr
     */
    @Override
    public void setStatus(DeviceStatus jpr) {
        this.jpr = jpr;
    }

    /**
     * 获取状态代码
     *
     * @return 状态代码
     */
    @Override
    public String getCode() {
        return this.jprCode;
    }

    /**
     * 设置硬件状态码
     *
     * @param code
     */
    @Override
    public void setCode(String code) {
        this.jprCode = code;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((jpr == null) ? 0 : jpr.hashCode());
        result = prime * result + ((jprCode == null) ? 0 : jprCode.hashCode());
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
        StatusJpr other = (StatusJpr) obj;
        if (jpr != other.jpr) {
            return false;
        }
        if (jprCode == null) {
            if (other.jprCode != null) {
                return false;
            }
        } else if (!jprCode.equals(other.jprCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String getHwCode() {

        return jprHwCode;
    }

    @Override
    public void setHwCode(String hwCode) {
        this.jprHwCode = hwCode;
    }

}
