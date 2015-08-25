package com.yihuacomputer.fish.monitor.entity.filter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.yihuacomputer.fish.api.monitor.filter.IRunStatusFilter;

@Embeddable
public class RunStatusFilter implements IRunStatusFilter {
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "RUN_ALL",columnDefinition="CHAR",length=1)
	private boolean all;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "RUN_INITAL",columnDefinition="CHAR",length=1)
	private boolean initial;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "RUN_CUSTOMER",columnDefinition="CHAR",length=1)
	private boolean customer;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "RUN_HEALTH",columnDefinition="CHAR",length=1)
	private boolean health;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "RUN_HALF",columnDefinition="CHAR",length=1)
	private boolean half;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "RUN_UNKNOW",columnDefinition="CHAR",length=1)
	private boolean unknow;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "RUN_STOP",columnDefinition="CHAR",length=1)
	private boolean stop;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "RUN_STOP_ATMP",columnDefinition="CHAR",length=1)
	private boolean atmpStop;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "RUN_STOP_MOD",columnDefinition="CHAR",length=1)
	private boolean stopMod;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "RUN_STOP_UNCAHSIN",columnDefinition="CHAR",length=1)
	private boolean stopUnCashIn;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "RUN_STOP_MANMADE",columnDefinition="CHAR",length=1)
	private boolean stopManMade;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "RUN_MAINTAIN",columnDefinition="CHAR",length=1)
	private boolean maintain;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "RUN_SHUTDOWN",columnDefinition="CHAR",length=1)
	private boolean shutdown;	
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "RUN_REBOOT",columnDefinition="CHAR",length=1)
	private boolean reBoot;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "RUN_VDM",columnDefinition="CHAR",length=1)
	private boolean vdm;	 
	
	public boolean isAll() {
		return all;
	}
	public void setAll(boolean all) {
		this.all = all;
	}
	public boolean isHealth() {
		return health;
	}
	public void setHealth(boolean health) {
		this.health = health;
	}
	public boolean isStop() {
		return stop;
	}
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	public boolean isHalf() {
		return half;
	}
	public void setHalf(boolean half) {
		this.half = half;
	}
	public boolean isUnknow() {
		return unknow;
	}
	public void setUnknow(boolean unknow) {
		this.unknow = unknow;
	}
	public boolean isAtmpStop() {
		return atmpStop;
	}
	public void setAtmpStop(boolean atmpStop) {
		this.atmpStop = atmpStop;
	}
	public boolean isMaintain() {
		return maintain;
	}
	public void setMaintain(boolean maintain) {
		this.maintain = maintain;
	}
	public boolean isShutdown() {
		return shutdown;
	}
	public void setShutdown(boolean shutdown) {
		this.shutdown = shutdown;
	}
	public boolean isStopManMade() {
		return stopManMade;
	}
	public void setStopManMade(boolean stopManMade) {
		this.stopManMade = stopManMade;
	}
	public boolean isVdm() {
		return vdm;
	}
	public void setVdm(boolean vdm) {
		this.vdm = vdm;
	}
	public boolean isInitial() {
		return initial;
	}
	public void setInitial(boolean initial) {
		this.initial = initial;
	}
	public boolean isCustomer() {
		return customer;
	}
	public void setCustomer(boolean customer) {
		this.customer = customer;
	}
	public boolean isStopMod() {
		return stopMod;
	}
	public void setStopMod(boolean stopMod) {
		this.stopMod = stopMod;
	}
	public boolean isStopUnCashIn() {
		return stopUnCashIn;
	}
	public void setStopUnCashIn(boolean stopUnCashIn) {
		this.stopUnCashIn = stopUnCashIn;
	}
	public boolean isReBoot() {
		return reBoot;
	}
	public void setReBoot(boolean reBoot) {
		this.reBoot = reBoot;
	}
	@Override
	public String toString() {
		return "RunStatusFilter [all=" + all + ", initial=" + initial
				+ ", customer=" + customer + ", health=" + health + ", half="
				+ half + ", unknow=" + unknow + ", stop=" + stop
				+ ", atmpStop=" + atmpStop + ", stopMod=" + stopMod
				+ ", stopUnCashIn=" + stopUnCashIn + ", stopManMade="
				+ stopManMade + ", maintain=" + maintain + ", shutdown="
				+ shutdown + ", reBoot=" + reBoot + ", vdm=" + vdm + "]";
	}
	
}
