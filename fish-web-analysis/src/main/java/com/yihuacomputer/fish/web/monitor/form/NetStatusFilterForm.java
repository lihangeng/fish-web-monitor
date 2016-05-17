package com.yihuacomputer.fish.web.monitor.form;

import com.yihuacomputer.fish.api.monitor.filter.INetStatusFilter;

public class NetStatusFilterForm {

    private boolean net_all;

    private boolean net_healthy;

    private boolean net_warning;

    private boolean net_fatal;

    private boolean net_unknown;

    public NetStatusFilterForm() {
    }

    public NetStatusFilterForm(INetStatusFilter netStatusFilter) {
        setNet_all(netStatusFilter.isAll());
        setNet_fatal(netStatusFilter.isFatal());
        setNet_healthy(netStatusFilter.isHealth());
        setNet_unknown(netStatusFilter.isUnknown());
        setNet_warning(netStatusFilter.isWarning());
    }

    public boolean isNet_all() {
        return net_all;
    }

    public void setNet_all(boolean net_all) {
        this.net_all = net_all;
    }

    public boolean isNet_healthy() {
        return net_healthy;
    }

    public void setNet_healthy(boolean net_healthy) {
        this.net_healthy = net_healthy;
    }

    public boolean isNet_warning() {
        return net_warning;
    }

    public void setNet_warning(boolean net_warning) {
        this.net_warning = net_warning;
    }

    public boolean isNet_fatal() {
        return net_fatal;
    }

    public void setNet_fatal(boolean net_fatal) {
        this.net_fatal = net_fatal;
    }

    public boolean isNet_unknown() {
        return net_unknown;
    }

    public void setNet_unknown(boolean net_unknown) {
        this.net_unknown = net_unknown;
    }
}
