package com.yihuacomputer.fish.web.monitor.form;
public class ModStatus {
    private StatusCdm cdm;

    private StatusCim cim;

    private StatusIdc idc;

    private StatusJpr jpr;

    private StatusPin pin;

    private StatusRpr rpr;

    private StatusTtu ttu;
    
    private StatusSiu siu;

    private Boolean success;

    public StatusCdm getCdm() {
        return cdm;
    }

    public void setCdm(StatusCdm cdm) {
        this.cdm = cdm;
    }

    public StatusCim getCim() {
        return cim;
    }

    public void setCim(StatusCim cim) {
        this.cim = cim;
    }

    public StatusIdc getIdc() {
        return idc;
    }

    public void setIdc(StatusIdc idc) {
        this.idc = idc;
    }

    public StatusJpr getJpr() {
        return jpr;
    }

    public void setJpr(StatusJpr jpr) {
        this.jpr = jpr;
    }

    public StatusPin getPin() {
        return pin;
    }

    public void setPin(StatusPin pin) {
        this.pin = pin;
    }

    public StatusRpr getRpr() {
        return rpr;
    }

    public void setRpr(StatusRpr rpr) {
        this.rpr = rpr;
    }

    public StatusTtu getTtu() {
        return ttu;
    }

    public void setTtu(StatusTtu ttu) {
        this.ttu = ttu;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public StatusSiu getSiu() {
        return siu;
    }

    public void setSiu(StatusSiu siu) {
        this.siu = siu;
    }
}
