package com.yihuacomputer.fish.web.monitor.form;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;

/**
 * 设备状态ICC的信息
 *
 * @author 王佩琪
 */
public class StatusIcc {
    /**
     * 硬件主状态
     */
    private DeviceStatus status = DeviceStatus.Unknown;

    private String statusName;
    
    /**
     * 状态代码
     */
    private String code;

    /**
     * 厂商硬件状态码
     */
    private String hwCode;

    /**
     * media状态
     */
    private String media;

    /**
     * 回收匣状态
     */
    private String retainBin;

    /**
     * 吞卡张数
     */
    private int cards;

    /**
     * 芯片模块状态
     */
    private String chipModule;

    /**
     * 剩余卡张数
     */
    private long cardsAccount;

    public DeviceStatus getStatus() {
        return status;
    }

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHwCode() {
        return hwCode;
    }

    public void setHwCode(String hwCode) {
        this.hwCode = hwCode;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getRetainBin() {
        return retainBin;
    }

    public void setRetainBin(String retainBin) {
        this.retainBin = retainBin;
    }

    public int getCards() {
        return cards;
    }

    public void setCards(int cards) {
        this.cards = cards;
    }

    public String getChipModule() {
        return chipModule;
    }

    public void setChipModule(String chipModule) {
        this.chipModule = chipModule;
    }

    public long getCardsAccount() {
        return cardsAccount;
    }

    public void setCardsAccount(long cardsAccount) {
        this.cardsAccount = cardsAccount;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
