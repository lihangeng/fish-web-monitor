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
	
	@Override
	public boolean isAll() {
		return all;
	}
	
	@Override
	public void setAll(boolean all) {
		this.all = all;
	}
	
	@Override
	public boolean isHealth() {
		return health;
	}
	
	@Override
	public void setHealth(boolean health) {
		this.health = health;
	}
	
	@Override
	public boolean isStop() {
		return stop;
	}
	
	@Override
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	@Override
	public boolean isHalf() {
		return half;
	}
	
	@Override
	public void setHalf(boolean half) {
		this.half = half;
	}
	
	@Override
	public boolean isUnknow() {
		return unknow;
	}
	
	@Override
	public void setUnknow(boolean unknow) {
		this.unknow = unknow;
	}
	
	@Override
	public boolean isAtmpStop() {
		return atmpStop;
	}
	
	@Override
	public void setAtmpStop(boolean atmpStop) {
		this.atmpStop = atmpStop;
	}
	
	@Override
	public boolean isMaintain() {
		return maintain;
	}
	
	@Override
	public void setMaintain(boolean maintain) {
		this.maintain = maintain;
	}
	
	@Override
	public boolean isShutdown() {
		return shutdown;
	}
	
	@Override
	public void setShutdown(boolean shutdown) {
		this.shutdown = shutdown;
	}
	
	@Override
	public boolean isStopManMade() {
		return stopManMade;
	}
	
	@Override
	public void setStopManMade(boolean stopManMade) {
		this.stopManMade = stopManMade;
	}
	
	@Override
	public boolean isVdm() {
		return vdm;
	}
	
	@Override
	public void setVdm(boolean vdm) {
		this.vdm = vdm;
	}
	
	@Override
	public boolean isInitial() {
		return initial;
	}
	
	@Override
	public void setInitial(boolean initial) {
		this.initial = initial;
	}
	
	@Override
	public boolean isCustomer() {
		return customer;
	}
	
	@Override
	public void setCustomer(boolean customer) {
		this.customer = customer;
	}
	
	@Override
	public boolean isStopMod() {
		return stopMod;
	}
	
	@Override
	public void setStopMod(boolean stopMod) {
		this.stopMod = stopMod;
	}
	
	@Override
	public boolean isStopUnCashIn() {
		return stopUnCashIn;
	}
	
	@Override
	public void setStopUnCashIn(boolean stopUnCashIn) {
		this.stopUnCashIn = stopUnCashIn;
	}
	
	@Override
	public boolean isReBoot() {
		return reBoot;
	}
	
	@Override
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
