package com.yihuacomputer.fish.web.monitor.form;

import com.yihuacomputer.fish.api.monitor.filter.IBoxStatusFilter;

/**
 * 钞箱状态过滤条件
 * 
 * @author pengwenchao
 *
 */
public class BoxStatusFilterForm {

    private boolean box_all;

    private boolean box_healthy;

    private boolean box_full;

    private boolean box_low;

    private boolean box_empty;

    private boolean box_high;

    private boolean box_fatal;

    private boolean box_unknown;

    public BoxStatusFilterForm() {
    }

    public BoxStatusFilterForm(IBoxStatusFilter boxStatusFilter) {
        setBox_all(boxStatusFilter.isAll());
        setBox_empty(boxStatusFilter.isEmpty());
        setBox_fatal(boxStatusFilter.isFatal());
        setBox_full(boxStatusFilter.isFull());
        setBox_healthy(boxStatusFilter.isHealthy());
        setBox_high(boxStatusFilter.isHigh());
        setBox_low(boxStatusFilter.isLow());
        setBox_unknown(boxStatusFilter.isUnknown());
    }

    public boolean isBox_all() {
        return box_all;
    }

    public void setBox_all(boolean box_all) {
        this.box_all = box_all;
    }

    public boolean isBox_healthy() {
        return box_healthy;
    }

    public void setBox_healthy(boolean box_healthy) {
        this.box_healthy = box_healthy;
    }

    public boolean isBox_full() {
        return box_full;
    }

    public void setBox_full(boolean box_full) {
        this.box_full = box_full;
    }

    public boolean isBox_low() {
        return box_low;
    }

    public void setBox_low(boolean box_low) {
        this.box_low = box_low;
    }

    public boolean isBox_empty() {
        return box_empty;
    }

    public void setBox_empty(boolean box_empty) {
        this.box_empty = box_empty;
    }

    public boolean isBox_high() {
        return box_high;
    }

    public void setBox_high(boolean box_high) {
        this.box_high = box_high;
    }

    public boolean isBox_fatal() {
        return box_fatal;
    }

    public void setBox_fatal(boolean box_fatal) {
        this.box_fatal = box_fatal;
    }

    public boolean isBox_unknown() {
        return box_unknown;
    }

    public void setBox_unknown(boolean box_unknown) {
        this.box_unknown = box_unknown;
    }
}
