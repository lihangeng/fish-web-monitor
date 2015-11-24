package com.yihuacomputer.fish.monitor.entity.xfs.status;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusIdc;

@Embeddable
public class StatusIdc implements IStatusIdc {

    @Enumerated(EnumType.STRING)
    @Column(name = "IDC_STATUS")
    private DeviceStatus idc;

    @Column(name = "IDC_CODE")
    private String idcCode;

    @Column(name = "IDC_CAPTURE_BIN_COUNT")
    private int cards;

    @Transient
    private String idcHwCode;

    public StatusIdc() {
        this.idc = DeviceStatus.NoDevice;
        this.cards = -1;
    }

    /**
     * 获取硬件主状态
     *
     * @return 硬件主状态
     */
    public DeviceStatus getStatus() {
        return this.idc;
    }

    /**
     * 设置硬件状态
     *
     * @param idc
     */
    public void setStatus(DeviceStatus idc) {
        this.idc = idc;
    }

    /**
     * 获取状态代码
     *
     * @return 状态代码
     */
    public String getCode() {
        return this.idcCode;
    }

    /**
     * 设置状态码
     *
     * @param code
     */
    public void setCode(String code) {
        this.idcCode = code;
    }

    /**
     * 获取吞卡张数
     *
     * @return 吞卡张数
     */
    public int getCards() {
        return this.cards;
    }

    /**
     * 设置吞卡张数
     *
     * @param cards
     */
    public void setCards(int cards) {
        this.cards = cards;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + cards;
        result = prime * result + ((idc == null) ? 0 : idc.hashCode());
        result = prime * result + ((idcCode == null) ? 0 : idcCode.hashCode());
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
        StatusIdc other = (StatusIdc) obj;
        if (cards != other.cards) {
            return false;
        }
        if (idc != other.idc) {
            return false;
        }
        if (idcCode == null) {
            if (other.idcCode != null) {
                return false;
            }
        } else if (!idcCode.equals(other.idcCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String getHwCode() {
        return this.idcHwCode;
    }

    @Override
    public void setHwCode(String hwCode) {
        this.idcHwCode = hwCode;
    }

}
