package com.yihuacomputer.fish.monitor.entity.xfs.status;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusPbk;

/**
 * @author YiHua
 *
 */
@Embeddable
public class StatusPbk implements IStatusPbk ,Serializable {

	private static final long serialVersionUID = 1L;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "PBK_STATUS")
    private DeviceStatus pbk;

    @Column(name = "PBK_CODE")
    private String pbkCode;

    @Transient
    private String pbkHwCode;

    /**
     * 初始化
     */
    public StatusPbk() {
        this.pbk = DeviceStatus.Unknown;
    }

    /**
     * 获取硬件主状态
     *
     * @return 硬件主状态
     */
    @Override
    public DeviceStatus getStatus() {
        return this.pbk;
    }

    /**
     * 设置凭条打印张数
     *
     * @param rpr
     */
    @Override
    public void setStatus(DeviceStatus pbk) {
        this.pbk = pbk;
    }

    /**
     * 获取状态代码
     *
     * @return 状态代码
     */
    @Override
    public String getCode() {
        return this.pbkCode;
    }

    @Override
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
