package com.yihuacomputer.fish.monitor.entity.filter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.yihuacomputer.fish.api.monitor.filter.IModStatusFilter;

@Embeddable
public class ModStatusFilter implements IModStatusFilter {
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "MOD_ALL",columnDefinition="CHAR",length=1)
	private boolean all;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "MOD_HEALTH",columnDefinition="CHAR",length=1)
	private boolean health;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "MOD_WARN",columnDefinition="CHAR",length=1)
	private boolean warning;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "MOD_FATAL",columnDefinition="CHAR",length=1)
	private boolean fatal;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "MOD_UNKNOW",columnDefinition="CHAR",length=1)
	private boolean unknown;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "MOD_NODEVICE",columnDefinition="CHAR",length=1)
	private boolean nodevice;
	
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
	public boolean isWarning() {
		return warning;
	}
	
	@Override
	public void setWarning(boolean warning) {
		this.warning = warning;
	}
	
	@Override
	public boolean isFatal() {
		return fatal;
	}
	
	@Override
	public void setFatal(boolean fatal) {
		this.fatal = fatal;
	}
	
	@Override
	public boolean isUnknown() {
		return unknown;
	}
	
	@Override
	public void setUnknown(boolean unknown) {
		this.unknown = unknown;
	}
	
	@Override
	public boolean isNodevice() {
		return nodevice;
	}
	
	@Override
	public void setNodevice(boolean nodevice) {
		this.nodevice = nodevice;
	}
	@Override
	public String toString() {
		return "ModStatusFilter [all=" + all + ", health=" + health
				+ ", warning=" + warning + ", fatal=" + fatal + ", unknown="
				+ unknown + ", nodevice=" + nodevice + "]";
	}	
	
}
