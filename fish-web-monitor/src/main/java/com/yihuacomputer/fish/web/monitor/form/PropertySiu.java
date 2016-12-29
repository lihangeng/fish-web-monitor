package com.yihuacomputer.fish.web.monitor.form;
/**
 * @author YiHua
 *
 */
public class PropertySiu {
    /**
     * 是否支持操作员开关
     */
    private String operatorSwitchSupported;

    /**
     * 是否支持后盖门打开传感能力
     */
    private String cabinetSupported;

    /**
     * 是否支持安全门打开传感能力
     */
    private String safeSupported;

    /**
     * 是否支持靠近传感能力
     */
    private String indicatorSupported;

    /**
     * 是否支持插卡指示灯能力
     */
    private String guidelightIdcSupported;

    /**
     * 是否支持取款指示灯能力
     */
    private String guidelightCdmSupported;

    /**
     * 是否支持凭条打印指示灯能力
     */
    private String guidelightReceiptSupported;

    /**
     * 是否支持存款指示灯能力
     */
    private String guidelightCimSupported;

    public String getOperatorSwitchSupported() {
        return operatorSwitchSupported;
    }

    public void setOperatorSwitchSupported(String operatorSwitchSupported) {
        this.operatorSwitchSupported = operatorSwitchSupported;
    }

    public String getCabinetSupported() {
        return cabinetSupported;
    }

    public void setCabinetSupported(String cabinetSupported) {
        this.cabinetSupported = cabinetSupported;
    }

    public String getSafeSupported() {
        return safeSupported;
    }

    public void setSafeSupported(String safeSupported) {
        this.safeSupported = safeSupported;
    }

    public String getIndicatorSupported() {
        return indicatorSupported;
    }

    public void setIndicatorSupported(String indicatorSupported) {
        this.indicatorSupported = indicatorSupported;
    }

    public String getGuidelightIdcSupported() {
        return guidelightIdcSupported;
    }

    public void setGuidelightIdcSupported(String guidelightIdcSupported) {
        this.guidelightIdcSupported = guidelightIdcSupported;
    }

    public String getGuidelightCdmSupported() {
        return guidelightCdmSupported;
    }

    public void setGuidelightCdmSupported(String guidelightCdmSupported) {
        this.guidelightCdmSupported = guidelightCdmSupported;
    }

    public String getGuidelightReceiptSupported() {
        return guidelightReceiptSupported;
    }

    public void setGuidelightReceiptSupported(String guidelightReceiptSupported) {
        this.guidelightReceiptSupported = guidelightReceiptSupported;
    }

    public String getGuidelightCimSupported() {
        return guidelightCimSupported;
    }

    public void setGuidelightCimSupported(String guidelightCimSupported) {
        this.guidelightCimSupported = guidelightCimSupported;
    }
}
