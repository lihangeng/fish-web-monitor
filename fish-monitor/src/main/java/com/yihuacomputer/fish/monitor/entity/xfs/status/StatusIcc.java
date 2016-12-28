package com.yihuacomputer.fish.monitor.entity.xfs.status;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IStatusIcc;

/**
 * @author YiHua
 *
 */
@Embeddable
public class StatusIcc implements IStatusIcc  ,Serializable {

	private static final long serialVersionUID = 1L;
	
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

    /**
     * 初始化
     */
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
    @Override
    public DeviceStatus getStatus() {
        return this.icc;
    }

    /**
     * 设置硬件状态
     *
     * @param idc
     */
    @Override
    public void setStatus(DeviceStatus icc) {
        this.icc = icc;
    }

    /**
     * 获取状态代码
     *
     * @return 状态代码
     */
    @Override
    public String getCode() {
        return this.iccCode;
    }

    /**
     * 设置状态码
     *
     * @param code
     */
    @Override
    public void setCode(String code) {
        this.iccCode = code;
    }

    /**
     * 获取吞卡张数
     *
     * @return 吞卡张数
     */
    @Override
    public int getCards() {
        return this.cards;
    }

    /**
     * 设置吞卡张数
     *
     * @param cards
     */
    @Override
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
