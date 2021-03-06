package com.yihuacomputer.fish.monitor.entity.xfs.status;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusFgp;

/**
 * @author YiHua
 *
 */
@Embeddable
public class StatusFgp implements IStatusFgp ,Serializable {

	private static final long serialVersionUID = 1L;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "FGP_STATUS")
    private DeviceStatus fgp;

    @Column(name = "FGP_CODE")
    private String fgpCode;

    @Column(name = "FGP_HW_CODE")
    private String fgpHwCode;

    /**
     * 初始化
     */
    public StatusFgp() {
        this.fgp = DeviceStatus.Unknown;
    }

    /**
     * 获取硬件主状态
     *
     * @return 硬件主状态
     */
    @Override
    public DeviceStatus getStatus() {
        return this.fgp;
    }

    /**
     * 设置硬件状态
     *
     * @param fgp
     */
    @Override
    public void setStatus(DeviceStatus fgp) {
        this.fgp = fgp;
    }

    /**
     * 获取状态代码
     *
     * @return 状态代码
     */
    @Override
    public String getCode() {
        return this.fgpCode;
    }

    /**
     * 设置状态码
     *
     * @param code
     */
    @Override
    public void setCode(String code) {
        this.fgpCode = code;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fgp == null) ? 0 : fgp.hashCode());
        result = prime * result + ((fgpCode == null) ? 0 : fgpCode.hashCode());
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
        StatusFgp other = (StatusFgp) obj;
        if (fgp != other.fgp) {
            return false;
        }
        if (fgpCode == null) {
            if (other.fgpCode != null) {
                return false;
            }
        } else if (!fgpCode.equals(other.fgpCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String getHwCode() {
        return this.fgpHwCode;
    }

    @Override
    public void setHwCode(String hwCode) {
        this.fgpHwCode = hwCode;
    }

}
