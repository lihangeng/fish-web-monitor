package com.yihuacomputer.fish.web.monitor.form;

import com.yihuacomputer.fish.api.monitor.filter.IBoxStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IModStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.INetStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IRunStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IStatusFilter;

/**
 * @author YiHua
 *
 */
public class StatusFilterForm {
	
	private String userId;
	private String areaCode;
	
	private boolean box_all;
	private boolean box_healthy;
	private boolean box_full;
	private boolean box_low;
	private boolean box_empty;
	private boolean box_high;
	private boolean box_fatal;
	private boolean box_unknown;
	

	
	private boolean module_all;
	private boolean module_healthy;
	private boolean module_waring;
	private boolean module_fatal;
	private boolean module_unknown;
	private boolean module_noDevice;
	
	private boolean net_all;
	private boolean net_healthy;
	private boolean net_warning;
	private boolean net_fatal;
	private boolean net_unknown;
	
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
	
	public StatusFilterForm() {
		
	}
	
	
	/**
	 * @param statusFilter
	 * @return
	 */
	public static StatusFilterForm toForm(IStatusFilter statusFilter){
		StatusFilterForm result = new StatusFilterForm();
		result.setUserId(statusFilter.getUserId());
		result.setAreaCode(statusFilter.getTerminalId());
		
		IRunStatusFilter runStatusFilter = statusFilter.getRunStatusFilter();
		if(runStatusFilter == null){
			result.setRun_all(true);
			result.setRun_unknown(true);
			result.setRun_initial(true);
			result.setRun_healthy(true);
			result.setRun_subHealth(true);
			result.setRun_customer(true);
			result.setRun_maintain(true);
			result.setRun_vdm(true);
			result.setRun_halt(true);
			result.setRun_reboot(true);
			result.setRun_stopAtmp(true);
			result.setRun_stopManmade(true);
			result.setRun_stopMod(true);
			result.setRun_stopUnCashIn(true);
			result.setRun_stopunknown(true);
		}
		else {
			result.setRun_all(runStatusFilter.isAll());
			if(runStatusFilter.isAll()){
				result.setRun_unknown(true);
				result.setRun_initial(true);
				result.setRun_healthy(true);
				result.setRun_subHealth(true);
				result.setRun_customer(true);
				result.setRun_maintain(true);
				result.setRun_vdm(true);
				result.setRun_halt(true);
				result.setRun_reboot(true);
				result.setRun_stopAtmp(true);
				result.setRun_stopManmade(true);
				result.setRun_stopMod(true);
				result.setRun_stopUnCashIn(true);
				result.setRun_stopunknown(true);
			}
			else {
				result.setRun_unknown(runStatusFilter.isUnknow());
				result.setRun_initial(runStatusFilter.isInitial());
				result.setRun_healthy(runStatusFilter.isHealth());
				result.setRun_subHealth(runStatusFilter.isHalf());
				result.setRun_customer(runStatusFilter.isCustomer());
				result.setRun_maintain(runStatusFilter.isMaintain());
				result.setRun_vdm(runStatusFilter.isVdm());
				result.setRun_halt(runStatusFilter.isHalf());
				result.setRun_reboot(runStatusFilter.isReBoot());
				result.setRun_stopAtmp(runStatusFilter.isAtmpStop());
				result.setRun_stopManmade(runStatusFilter.isStopManMade());
				result.setRun_stopMod(runStatusFilter.isStopMod());
				result.setRun_stopUnCashIn(runStatusFilter.isStopUnCashIn());
				result.setRun_stopunknown(runStatusFilter.isStop());
			}
		}
				
		IBoxStatusFilter boxStatusFilter = statusFilter.getBoxStatusFilter();
		if(boxStatusFilter == null){
			result.setBox_all(true);
			result.setBox_healthy(true);
			result.setBox_full(true);
			result.setBox_low(true);
			result.setBox_empty(true);
			result.setBox_high(true);
			result.setBox_fatal(true);
			result.setBox_unknown(true);
		}
		else {
			result.setBox_all(boxStatusFilter.isAll());
			if(boxStatusFilter.isAll()){
				result.setBox_full(true);
				result.setBox_full(true);
				result.setBox_low(true);
				result.setBox_empty(true);
				result.setBox_high(true);
				result.setBox_fatal(true);
				result.setBox_unknown(true);
				result.setBox_healthy(true);
			}
			else {
				result.setBox_full(boxStatusFilter.isFull());
				result.setBox_low(boxStatusFilter.isLow());
				result.setBox_empty(boxStatusFilter.isEmpty());
				result.setBox_high(boxStatusFilter.isHigh());
				result.setBox_fatal(boxStatusFilter.isFatal());
				result.setBox_unknown(boxStatusFilter.isUnknown());
				result.setBox_healthy(boxStatusFilter.isHealthy());
			}
		}
		
		
		IModStatusFilter modStatusFilter = statusFilter.getModStatusFilter();
		if(modStatusFilter == null){
			result.setModule_all(true);
			result.setModule_healthy(true);
			result.setModule_waring(true);
			result.setModule_fatal(true);
			result.setModule_unknown(true);
			result.setModule_noDevice(true);
		}
		else {
			result.setModule_all(modStatusFilter.isAll());
			if(modStatusFilter.isAll()){
				result.setModule_healthy(true);
				result.setModule_waring(true);
				result.setModule_fatal(true);
				result.setModule_unknown(true);
				result.setModule_noDevice(true);
			}
			else {
				result.setModule_healthy(modStatusFilter.isHealth());
				result.setModule_waring(modStatusFilter.isWarning());
				result.setModule_fatal(modStatusFilter.isFatal());
				result.setModule_unknown(modStatusFilter.isUnknown());
				result.setModule_noDevice(modStatusFilter.isNodevice());
			}
			
		}
		
		
		INetStatusFilter netStatusFilter = statusFilter.getNetStatusFilter();
		if(netStatusFilter == null){
			result.setNet_all(true);
			result.setNet_healthy(true);
			result.setNet_warning(true);
			result.setNet_fatal(true);
			result.setNet_unknown(true);
		}
		else {
			result.setNet_all(netStatusFilter.isAll());
			if(netStatusFilter.isAll()){
				result.setNet_healthy(true);
				result.setNet_warning(true);
				result.setNet_fatal(true);
				result.setNet_unknown(true);
			}
			else {
				result.setNet_healthy(netStatusFilter.isHealth());
				result.setNet_warning(netStatusFilter.isWarning());
				result.setNet_fatal(netStatusFilter.isFatal());
				result.setNet_unknown(netStatusFilter.isUnknown());
			}
		}
		return result;
	}
	

	public boolean isBox_healthy() {
		return box_healthy;
	}


	public void setBox_healthy(boolean box_healthy) {
		this.box_healthy = box_healthy;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}


	public boolean isBox_all() {
		return box_all;
	}


	public void setBox_all(boolean boxAll) {
		box_all = boxAll;
	}


	public boolean isBox_full() {
		return box_full;
	}


	public void setBox_full(boolean boxFull) {
		box_full = boxFull;
	}


	public boolean isBox_low() {
		return box_low;
	}


	public void setBox_low(boolean boxLow) {
		box_low = boxLow;
	}


	public boolean isBox_empty() {
		return box_empty;
	}


	public void setBox_empty(boolean boxEmpty) {
		box_empty = boxEmpty;
	}


	public boolean isBox_high() {
		return box_high;
	}


	public void setBox_high(boolean boxHigh) {
		box_high = boxHigh;
	}


	public boolean isBox_fatal() {
		return box_fatal;
	}


	public void setBox_fatal(boolean boxFatal) {
		box_fatal = boxFatal;
	}


	public boolean isBox_unknown() {
		return box_unknown;
	}


	public void setBox_unknown(boolean boxUnknown) {
		box_unknown = boxUnknown;
	}


	public boolean isModule_all() {
		return module_all;
	}


	public void setModule_all(boolean moduleAll) {
		module_all = moduleAll;
	}


	public boolean isModule_healthy() {
		return module_healthy;
	}


	public void setModule_healthy(boolean moduleHealthy) {
		module_healthy = moduleHealthy;
	}


	public boolean isModule_waring() {
		return module_waring;
	}


	public void setModule_waring(boolean moduleWaring) {
		module_waring = moduleWaring;
	}


	public boolean isModule_fatal() {
		return module_fatal;
	}


	public void setModule_fatal(boolean moduleFatal) {
		module_fatal = moduleFatal;
	}


	public boolean isModule_unknown() {
		return module_unknown;
	}


	public void setModule_unknown(boolean moduleUnknown) {
		module_unknown = moduleUnknown;
	}


	public boolean isModule_noDevice() {
		return module_noDevice;
	}


	public void setModule_noDevice(boolean moduleNoDevice) {
		module_noDevice = moduleNoDevice;
	}


	public boolean isNet_all() {
		return net_all;
	}


	public void setNet_all(boolean netAll) {
		net_all = netAll;
	}


	public boolean isNet_healthy() {
		return net_healthy;
	}


	public void setNet_healthy(boolean netHealthy) {
		net_healthy = netHealthy;
	}


	public boolean isNet_warning() {
		return net_warning;
	}


	public void setNet_warning(boolean netWarning) {
		net_warning = netWarning;
	}


	public boolean isNet_fatal() {
		return net_fatal;
	}


	public void setNet_fatal(boolean netFatal) {
		net_fatal = netFatal;
	}


	public boolean isNet_unknown() {
		return net_unknown;
	}


	public void setNet_unknown(boolean netUnknown) {
		net_unknown = netUnknown;
	}


	public boolean isRun_all() {
		return run_all;
	}


	public void setRun_all(boolean runAll) {
		run_all = runAll;
	}


	public boolean isRun_unknown() {
		return run_unknown;
	}


	public void setRun_unknown(boolean runUnknown) {
		run_unknown = runUnknown;
	}


	public boolean isRun_initial() {
		return run_initial;
	}


	public void setRun_initial(boolean runInitial) {
		run_initial = runInitial;
	}


	public boolean isRun_healthy() {
		return run_healthy;
	}


	public void setRun_healthy(boolean runHealthy) {
		run_healthy = runHealthy;
	}


	public boolean isRun_subHealth() {
		return run_subHealth;
	}


	public void setRun_subHealth(boolean runSubHealth) {
		run_subHealth = runSubHealth;
	}


	public boolean isRun_customer() {
		return run_customer;
	}


	public void setRun_customer(boolean runCustomer) {
		run_customer = runCustomer;
	}


	public boolean isRun_maintain() {
		return run_maintain;
	}


	public void setRun_maintain(boolean runMaintain) {
		run_maintain = runMaintain;
	}


	public boolean isRun_vdm() {
		return run_vdm;
	}


	public void setRun_vdm(boolean runVdm) {
		run_vdm = runVdm;
	}


	public boolean isRun_halt() {
		return run_halt;
	}


	public void setRun_halt(boolean runHalt) {
		run_halt = runHalt;
	}


	public boolean isRun_reboot() {
		return run_reboot;
	}


	public void setRun_reboot(boolean runReboot) {
		run_reboot = runReboot;
	}


	public boolean isRun_stopAtmp() {
		return run_stopAtmp;
	}


	public void setRun_stopAtmp(boolean runStopAtmp) {
		run_stopAtmp = runStopAtmp;
	}


	public boolean isRun_stopManmade() {
		return run_stopManmade;
	}


	public void setRun_stopManmade(boolean runStopManmade) {
		run_stopManmade = runStopManmade;
	}


	public boolean isRun_stopMod() {
		return run_stopMod;
	}


	public void setRun_stopMod(boolean runStopMod) {
		run_stopMod = runStopMod;
	}


	public boolean isRun_stopUnCashIn() {
		return run_stopUnCashIn;
	}


	public void setRun_stopUnCashIn(boolean runStopUnCashIn) {
		run_stopUnCashIn = runStopUnCashIn;
	}


	public boolean isRun_stopunknown() {
		return run_stopunknown;
	}


	public void setRun_stopunknown(boolean runStopunknown) {
		run_stopunknown = runStopunknown;
	}

	
}
