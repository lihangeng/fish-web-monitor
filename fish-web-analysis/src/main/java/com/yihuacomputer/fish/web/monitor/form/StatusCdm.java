package com.yihuacomputer.fish.web.monitor.form;

import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;

public class StatusCdm {
    /**
     * 获取硬件主状态
     */
    private DeviceStatus status;

    private String statusName;

    /**
     * 厂商硬件状态码
     */
    private String hwCode;

    /**
     * 获取状态代码
     */
    private String code;

    /**
     * 获取安全门状态
     */
    private String safeDoor;

    /**
     * 获取钞箱状态
     */
    private String cashUnits;

    /**
     * 获取暂存器状态
     */
    private String intermediateStacker;

    /**
     * 获取出钞口位置
     */
    private String[] outPosition;

    /**
     * 获取出钞口状态
     */
    private String[] outPositionStatus;

    /**
     * 获shutter门状态
     */
    private String[] shutter;

    /**
     * 获钞道状态
     */
    private String[] cdmTransport;

    /**
     * 获取钞道钞票状态
     */
    private String[] cdmTransportItems;

    /**
     * 获取物理钞箱ID
     */
    private String[] puId;

    /**
     * 获取物理钞箱位置名称
     */
    private String[] puPosName;

    /**
     * 获取物理钞箱状态
     */
    private String[] puBinStatus;

    /**
     * 获取物理钞箱当前张数
     */
    private int[] puCurrentCount;

    /**
     * 获取物理钞箱初始张数
     */
    private int[] puInitialCount;

    /**
     * 获取物理钞箱Reject张数
     */
    private int[] puRejectCount;

    /**
     * 获取物理逻辑钞箱对应关系
     */
    private String[] pcuId;

    /**
     * 获取逻辑钞箱ID
     */
    private String[] cuId;

    /**
     * 获取逻辑钞箱币种
     */
    private String[] cuCurrency;

    /**
     * 获取逻辑钞箱当前张数
     */
    private int[] cuCurrentCount;

    /**
     * 获取逻辑钞箱初始张数
     */
    private int[] cuInitialCount;

    /**
     * 获取逻辑钞箱reject张数
     */
    private int[] cuRejectCount;

    /**
     * 获取逻辑钞箱面值
     */
    private int[] cuNoteValue;

    /**
     * 获取逻辑钞箱状态
     */
    private String[] cuBinStatus;

    /**
     * 获取逻辑钞箱类型
     */
    private String[] cuBinType;

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

    public String getSafeDoor() {
        return safeDoor;
    }

    public void setSafeDoor(String safeDoor) {
        this.safeDoor = safeDoor;
    }

    public String getCashUnits() {
        return cashUnits;
    }

    public void setCashUnits(String cashUnits) {
        this.cashUnits = cashUnits;
    }

    public String getIntermediateStacker() {
        return intermediateStacker;
    }

    public void setIntermediateStacker(String intermediateStacker) {
        this.intermediateStacker = intermediateStacker;
    }

    public String[] getOutPosition() {
        return outPosition;
    }

    public void setOutPosition(String[] outPosition) {
        this.outPosition = outPosition;
    }

    public String[] getOutPositionStatus() {
        return outPositionStatus;
    }

    public void setOutPositionStatus(String[] outPositionStatus) {
        this.outPositionStatus = outPositionStatus;
    }

    public String[] getShutter() {
        return shutter;
    }

    public void setShutter(String[] shutter) {
        this.shutter = shutter;
    }

    public String[] getCdmTransport() {
        return cdmTransport;
    }

    public void setCdmTransport(String[] cdmTransport) {
        this.cdmTransport = cdmTransport;
    }

    public String[] getCdmTransportItems() {
        return cdmTransportItems;
    }

    public void setCdmTransportItems(String[] cdmTransportItems) {
        this.cdmTransportItems = cdmTransportItems;
    }

    public String[] getPuId() {
        return puId;
    }

    public void setPuId(String[] puId) {
        this.puId = puId;
    }

    public String[] getPuPosName() {
        return puPosName;
    }

    public void setPuPosName(String[] puPosName) {
        this.puPosName = puPosName;
    }

    public String[] getPuBinStatus() {
        return puBinStatus;
    }

    public void setPuBinStatus(String[] puBinStatus) {
        this.puBinStatus = puBinStatus;
    }

    public int[] getPuCurrentCount() {
        return puCurrentCount;
    }

    public void setPuCurrentCount(int[] puCurrentCount) {
        this.puCurrentCount = puCurrentCount;
    }

    public int[] getPuInitialCount() {
        return puInitialCount;
    }

    public void setPuInitialCount(int[] puInitialCount) {
        this.puInitialCount = puInitialCount;
    }

    public int[] getPuRejectCount() {
        return puRejectCount;
    }

    public void setPuRejectCount(int[] puRejectCount) {
        this.puRejectCount = puRejectCount;
    }

    public String[] getPcuId() {
        return pcuId;
    }

    public void setPcuId(String[] pcuId) {
        this.pcuId = pcuId;
    }

    public String[] getCuId() {
        return cuId;
    }

    public void setCuId(String[] cuId) {
        this.cuId = cuId;
    }

    public String[] getCuCurrency() {
        return cuCurrency;
    }

    public void setCuCurrency(String[] cuCurrency) {
        this.cuCurrency = cuCurrency;
    }

    public int[] getCuCurrentCount() {
        return cuCurrentCount;
    }

    public void setCuCurrentCount(int[] cuCurrentCount) {
        this.cuCurrentCount = cuCurrentCount;
    }

    public int[] getCuInitialCount() {
        return cuInitialCount;
    }

    public void setCuInitialCount(int[] cuInitialCount) {
        this.cuInitialCount = cuInitialCount;
    }

    public int[] getCuRejectCount() {
        return cuRejectCount;
    }

    public void setCuRejectCount(int[] cuRejectCount) {
        this.cuRejectCount = cuRejectCount;
    }

    public int[] getCuNoteValue() {
        return cuNoteValue;
    }

    public void setCuNoteValue(int[] cuNoteValue) {
        this.cuNoteValue = cuNoteValue;
    }

    public String[] getCuBinStatus() {
        return cuBinStatus;
    }

    public void setCuBinStatus(String[] cuBinStatus) {
        this.cuBinStatus = cuBinStatus;
    }

    public String[] getCuBinType() {
        return cuBinType;
    }

    public void setCuBinType(String[] cuBinType) {
        this.cuBinType = cuBinType;
    }

    public String getStatusName() {
        return statusName == null ? getStatus().getText() : statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getHwCode() {
        return hwCode;
    }

    public void setHwCode(String hwCode) {
        this.hwCode = hwCode;
    }
}
