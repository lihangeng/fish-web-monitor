package com.yihuacomputer.fish.web.monitor.form;
import com.yihuacomputer.fish.web.atm.format.PropertiseMsg;

public class ModProperty {
    private PropertyCdm cdm;

    private PropertyCim cim;

    private PropertyIdc idc;

    private PropertyJpr jpr;

    private PropertyPin pin;

    private PropertyRpr rpr;

    private PropertyTtu ttu;

    private PropertySiu siu;

    private PropertiseMsg properties;

    private Boolean success;

    public PropertyCdm getCdm() {
        return cdm;
    }

    public void setCdm(PropertyCdm cdm) {
        this.cdm = cdm;
    }

    public PropertyCim getCim() {
        return cim;
    }

    public void setCim(PropertyCim cim) {
        this.cim = cim;
    }

    public PropertyIdc getIdc() {
        return idc;
    }

    public void setIdc(PropertyIdc idc) {
        this.idc = idc;
    }

    public PropertyJpr getJpr() {
        return jpr;
    }

    public void setJpr(PropertyJpr jpr) {
        this.jpr = jpr;
    }

    public PropertyPin getPin() {
        return pin;
    }

    public void setPin(PropertyPin pin) {
        this.pin = pin;
    }

    public PropertyRpr getRpr() {
        return rpr;
    }

    public void setRpr(PropertyRpr rpr) {
        this.rpr = rpr;
    }

    public PropertyTtu getTtu() {
        return ttu;
    }

    public void setTtu(PropertyTtu ttu) {
        this.ttu = ttu;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public PropertySiu getSiu() {
        return siu;
    }

    public void setSiu(PropertySiu siu) {
        this.siu = siu;
    }

    public PropertiseMsg getProperties() {
        return properties;
    }

    public void setProperties(PropertiseMsg properties) {
        this.properties = properties;
    }
}
