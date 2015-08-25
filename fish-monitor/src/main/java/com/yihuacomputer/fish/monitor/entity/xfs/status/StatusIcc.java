package com.yihuacomputer.fish.monitor.entity.xfs.status;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusIcc;

@Embeddable
public class StatusIcc implements IStatusIcc {

    @Enumerated(EnumType.STRING)
    @Column(name = "ICC_STATUS")
    private DeviceStatus icc;

    @Column(name = "ICC_CODE")
    private String iccCode;

    @Column(name = "ICC_RETAIN_CARD_COUNT")
    private int cards;

    @Column(name = "ICC_HW_CODE")
    private String iccHwCode;

    @Column(name = "ICC_CAPTURE_BIN_COUNT")
    private int iccCurrCards;

    public StatusIcc() {
        this.icc = DeviceStatus.Unknown;
        this.cards = -1;
        this.iccCurrCards = -1;
    }

    /**
     * 获取硬件主状态
     *
     * @return 硬件主状态
     */
    public DeviceStatus getStatus() {
        return this.icc;
    }

    /**
     * 设置硬件状态
     *
     * @param idc
     */
    public void setStatus(DeviceStatus icc) {
        this.icc = icc;
    }

    /**
     * 获取状态代码
     *
     * @return 状态代码
     */
    public String getCode() {
        return this.iccCode;
    }

    /**
     * 设置状态码
     *
     * @param code
     */
    public void setCode(String code) {
        this.iccCode = code;
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
        result = prime * result + ((icc == null) ? 0 : icc.hashCode());
        result = prime * result + ((iccCode == null) ? 0 : iccCode.hashCode());
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
        StatusIcc other = (StatusIcc) obj;
        if (cards != other.cards) {
            return false;
        }
        if (icc != other.icc) {
            return false;
        }
        if (iccCode == null) {
            if (other.iccCode != null) {
                return false;
            }
        } else if (!iccCode.equals(other.iccCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String getHwCode() {
        return this.iccHwCode;
    }

    @Override
    public void setHwCode(String hwCode) {
        this.iccHwCode = hwCode;
    }

    @Override
    public void setIccCurrCards(int currCards) {
        this.iccCurrCards = currCards;
    }

    /**
     * 获取当前卡槽剩余卡数
     */
    @Override
    public int getIccCurrCards() {
        return this.iccCurrCards;
    }

}
