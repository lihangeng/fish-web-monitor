package com.yihuacomputer.fish.monitor.entity.xfs.status;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusRpr;

/**
 * @author YiHua
 *
 */
@Embeddable
public class StatusRpr implements IStatusRpr ,Serializable {

	private static final long serialVersionUID = 1L;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "PRP_STATUS")
    private DeviceStatus rpr;

    @Column(name = "PRP_CODE")
    private String rprCode;

    @Transient
    private String rprHwCode;

    /**
     * 初始化
     */
    public StatusRpr() {
        this.rpr = DeviceStatus.Unknown;
    }

    /**
     * 获取硬件主状态
     *
     * @return 硬件主状态
     */
    @Override
    public DeviceStatus getStatus() {
        return this.rpr;
    }

    /**
     * 设置凭条打印张数
     *
     * @param rpr
     */
    @Override
    public void setStatus(DeviceStatus rpr) {
        this.rpr = rpr;
    }

    /**
     * 获取状态代码
     *
     * @return 状态代码
     */
    @Override
    public String getCode() {
        return this.rprCode;
    }

    @Override
    public void setCode(String code) {
        this.rprCode = code;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((rpr == null) ? 0 : rpr.hashCode());
        result = prime * result + ((rprCode == null) ? 0 : rprCode.hashCode());
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
        StatusRpr other = (StatusRpr) obj;
        if (rpr != other.rpr) {
            return false;
        }
        if (rprCode == null) {
            if (other.rprCode != null) {
                return false;
            }
        } else if (!rprCode.equals(other.rprCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String getHwCode() {
        return this.rprHwCode;
    }

    @Override
    public void setHwCode(String hwCode) {
        this.rprHwCode = hwCode;
    }

}
