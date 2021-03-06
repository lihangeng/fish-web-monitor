package com.yihuacomputer.fish.monitor.entity.xfs.status;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusNfc;

/**
 * @author YiHua
 *
 */
@Embeddable
public class StatusNfc implements IStatusNfc ,Serializable {

	private static final long serialVersionUID = 1L;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "NFC_STATUS")
    private DeviceStatus nfc;

    @Column(name = "NFC_CODE")
    private String nfcCode;

    @Transient
    private String nfcHwCode;

    /**
     * 初始化
     */
    public StatusNfc() {
        this.nfc = DeviceStatus.Unknown;
    }

    /**
     * 获取硬件主状态
     *
     * @return 硬件主状态
     */
    @Override
    public DeviceStatus getStatus() {
        return this.nfc;
    }

    /**
     * 设置硬件状态
     *
     * @param nfc
     */
    @Override
    public void setStatus(DeviceStatus nfc) {
        this.nfc = nfc;
    }

    /**
     * 获取状态代码
     *
     * @return 状态代码
     */
    @Override
    public String getCode() {
        return this.nfcCode;
    }

    /**
     * 设置状态码
     *
     * @param code
     */
    @Override
    public void setCode(String code) {
        this.nfcCode = code;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nfc == null) ? 0 : nfc.hashCode());
        result = prime * result + ((nfcCode == null) ? 0 : nfcCode.hashCode());
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
        StatusNfc other = (StatusNfc) obj;

        if (nfc != other.nfc) {
            return false;
        }
        if (nfcCode == null) {
            if (other.nfcCode != null) {
                return false;
            }
        } else if (!nfcCode.equals(other.nfcCode)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String getHwCode() {
        return this.nfcHwCode;
    }

    @Override
    public void setHwCode(String hwCode) {
        this.nfcHwCode = hwCode;
    }
}
