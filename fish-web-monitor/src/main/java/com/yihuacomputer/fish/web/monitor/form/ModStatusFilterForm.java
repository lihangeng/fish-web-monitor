package com.yihuacomputer.fish.web.monitor.form;

import com.yihuacomputer.fish.api.monitor.filter.IModStatusFilter;

/**
 * @author YiHua
 *
 */
public class ModStatusFilterForm {

    private boolean mod_all;

    private boolean mod_healthy;

    private boolean mod_waring;

    private boolean mod_fatal;

    private boolean mod_unknown;

    private boolean mod_noDevice;

    public ModStatusFilterForm() {
    }

    /**
     * @param modStatusFilter
     */
    public ModStatusFilterForm(IModStatusFilter modStatusFilter) {
        setMod_all(modStatusFilter.isAll());
        setMod_fatal(modStatusFilter.isFatal());
        setMod_healthy(modStatusFilter.isHealth());
        setMod_noDevice(modStatusFilter.isNodevice());
        setMod_unknown(modStatusFilter.isUnknown());
        setMod_waring(modStatusFilter.isWarning());
    }

    public boolean isMod_all() {
        return mod_all;
    }

    public void setMod_all(boolean mod_all) {
        this.mod_all = mod_all;
    }

    public boolean isMod_healthy() {
        return mod_healthy;
    }

    public void setMod_healthy(boolean mod_healthy) {
        this.mod_healthy = mod_healthy;
    }

    public boolean isMod_waring() {
        return mod_waring;
    }

    public void setMod_waring(boolean mod_waring) {
        this.mod_waring = mod_waring;
    }

    public boolean isMod_fatal() {
        return mod_fatal;
    }

    public void setMod_fatal(boolean mod_fatal) {
        this.mod_fatal = mod_fatal;
    }

    public boolean isMod_unknown() {
        return mod_unknown;
    }

    public void setMod_unknown(boolean mod_unknown) {
        this.mod_unknown = mod_unknown;
    }

    public boolean isMod_noDevice() {
        return mod_noDevice;
    }

    public void setMod_noDevice(boolean mod_noDevice) {
        this.mod_noDevice = mod_noDevice;
    }

}
