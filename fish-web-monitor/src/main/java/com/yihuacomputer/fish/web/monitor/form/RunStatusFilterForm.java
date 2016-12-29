package com.yihuacomputer.fish.web.monitor.form;

import com.yihuacomputer.fish.api.monitor.filter.IRunStatusFilter;

/**
 * @author YiHua
 *
 */
public class RunStatusFilterForm {

    private boolean run_all;

    private boolean run_unknown;

    private boolean run_initial;

    private boolean run_healthy;

    private boolean run_subHealth;

    private boolean run_customer;

    private boolean run_maintain;

    private boolean run_vdm;

    private boolean run_halt;

    private boolean run_reboot;

    private boolean run_stopAtmp;

    private boolean run_stopManmade;

    private boolean run_stopMod;

    private boolean run_stopUnCashIn;

    private boolean run_stopunknown;

    public RunStatusFilterForm() {
    }

    /**
     * @param runStatusFilter
     */
    public RunStatusFilterForm(IRunStatusFilter runStatusFilter) {
        setRun_all(runStatusFilter.isAll());
        setRun_customer(runStatusFilter.isCustomer());
        setRun_subHealth(runStatusFilter.isHalf());
        setRun_healthy(runStatusFilter.isHealth());
        setRun_initial(runStatusFilter.isInitial());
        setRun_maintain(runStatusFilter.isMaintain());
        setRun_reboot(runStatusFilter.isReBoot());
        setRun_unknown(runStatusFilter.isUnknow());
        setRun_vdm(runStatusFilter.isVdm());
        setRun_halt(runStatusFilter.isShutdown());
        setRun_stopunknown(runStatusFilter.isStop());
        setRun_stopAtmp(runStatusFilter.isAtmpStop());
        setRun_stopUnCashIn(runStatusFilter.isStopUnCashIn());
        setRun_stopManmade(runStatusFilter.isStopManMade());
        setRun_stopMod(runStatusFilter.isStopMod());
    }

    public boolean isRun_all() {
        return run_all;
    }

    public void setRun_all(boolean run_all) {
        this.run_all = run_all;
    }

    public boolean isRun_unknown() {
        return run_unknown;
    }

    public void setRun_unknown(boolean run_unknown) {
        this.run_unknown = run_unknown;
    }

    public boolean isRun_initial() {
        return run_initial;
    }

    public void setRun_initial(boolean run_initial) {
        this.run_initial = run_initial;
    }

    public boolean isRun_healthy() {
        return run_healthy;
    }

    public void setRun_healthy(boolean run_healthy) {
        this.run_healthy = run_healthy;
    }

    public boolean isRun_subHealth() {
        return run_subHealth;
    }

    public void setRun_subHealth(boolean run_subHealth) {
        this.run_subHealth = run_subHealth;
    }

    public boolean isRun_customer() {
        return run_customer;
    }

    public void setRun_customer(boolean run_customer) {
        this.run_customer = run_customer;
    }

    public boolean isRun_maintain() {
        return run_maintain;
    }

    public void setRun_maintain(boolean run_maintain) {
        this.run_maintain = run_maintain;
    }

    public boolean isRun_vdm() {
        return run_vdm;
    }

    public void setRun_vdm(boolean run_vdm) {
        this.run_vdm = run_vdm;
    }

    public boolean isRun_halt() {
        return run_halt;
    }

    public void setRun_halt(boolean run_halt) {
        this.run_halt = run_halt;
    }

    public boolean isRun_reboot() {
        return run_reboot;
    }

    public void setRun_reboot(boolean run_reboot) {
        this.run_reboot = run_reboot;
    }

    public boolean isRun_stopAtmp() {
        return run_stopAtmp;
    }

    public void setRun_stopAtmp(boolean run_stopAtmp) {
        this.run_stopAtmp = run_stopAtmp;
    }

    public boolean isRun_stopManmade() {
        return run_stopManmade;
    }

    public void setRun_stopManmade(boolean run_stopManmade) {
        this.run_stopManmade = run_stopManmade;
    }

    public boolean isRun_stopMod() {
        return run_stopMod;
    }

    public void setRun_stopMod(boolean run_stopMod) {
        this.run_stopMod = run_stopMod;
    }

    public boolean isRun_stopUnCashIn() {
        return run_stopUnCashIn;
    }

    public void setRun_stopUnCashIn(boolean run_stopUnCashIn) {
        this.run_stopUnCashIn = run_stopUnCashIn;
    }

    public boolean isRun_stopunknown() {
        return run_stopunknown;
    }

    public void setRun_stopunknown(boolean run_stopunknown) {
        this.run_stopunknown = run_stopunknown;
    }
}
