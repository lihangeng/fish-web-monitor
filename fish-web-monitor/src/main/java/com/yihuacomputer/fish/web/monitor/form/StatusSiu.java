package com.yihuacomputer.fish.web.monitor.form;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;

/**
 * @author YiHua
 *
 */
public class StatusSiu {
    /**
     * 获取硬件主状态
     */
    private DeviceStatus status;

    private String statusName;

    /**
     * 获取状态代码
     */
    private String code;

    /**
     * 获取操作员按钮状态
     */
    private String operatorSwitch;

    /**
     * 获取环境灯状态
     */
    private String ambientLight;

    /**
     * 获取箱门状态
     */
    private String cabinet;

    /**
     * 获取安全门状态
     */
    private String safe;

    /**
     * 获取防护罩状态
     */
    private String vandalShield;

    /**
     * 获取插卡导引灯状态
     */
    private String idcGuidelight;

    /**
     * 获取取钞引导指示灯状态
     */
    private String cdmGuidelight;

    /**
     * 获取凭条导引灯状态
     */
    private String receiptGuidelight;

    /**
     * 获取CIM导引灯状态
     */
    private String cimGuidelight;

    /**
     * 厂商硬件状态码
     */
    private String hwCode;

    public String getHwCode() {
        return hwCode;
    }

    public void setHwCode(String hwCode) {
        this.hwCode = hwCode;
    }

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

    public String getOperatorSwitch() {
        return operatorSwitch;
    }

    public void setOperatorSwitch(String operatorSwitch) {
        this.operatorSwitch = operatorSwitch;
    }

    public String getAmbientLight() {
        return ambientLight;
    }

    public void setAmbientLight(String ambientLight) {
        this.ambientLight = ambientLight;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public String getSafe() {
        return safe;
    }

    public void setSafe(String safe) {
        this.safe = safe;
    }

    public String getVandalShield() {
        return vandalShield;
    }

    public void setVandalShield(String vandalShield) {
        this.vandalShield = vandalShield;
    }

    public String getIdcGuidelight() {
        return idcGuidelight;
    }

    public void setIdcGuidelight(String idcGuidelight) {
        this.idcGuidelight = idcGuidelight;
    }

    public String getCdmGuidelight() {
        return cdmGuidelight;
    }

    public void setCdmGuidelight(String cdmGuidelight) {
        this.cdmGuidelight = cdmGuidelight;
    }

    public String getReceiptGuidelight() {
        return receiptGuidelight;
    }

    public void setReceiptGuidelight(String receiptGuidelight) {
        this.receiptGuidelight = receiptGuidelight;
    }

    public String getCimGuidelight() {
        return cimGuidelight;
    }

    public void setCimGuidelight(String cimGuidelight) {
        this.cimGuidelight = cimGuidelight;
    }

    public String getStatusName() {
        return statusName == null ? getStatus().getText() : statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
