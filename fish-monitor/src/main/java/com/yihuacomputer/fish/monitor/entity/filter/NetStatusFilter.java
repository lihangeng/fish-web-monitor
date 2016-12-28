package com.yihuacomputer.fish.monitor.entity.filter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.yihuacomputer.fish.api.monitor.filter.INetStatusFilter;

/**
 * @author YiHua
 *
 */
@Embeddable
public class NetStatusFilter implements INetStatusFilter {
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "NET_ALL",columnDefinition="CHAR",length=1)
	private boolean all;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "NET_HEALTH",columnDefinition="CHAR",length=1)
	private boolean health;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "NET_WARN",columnDefinition="CHAR",length=1)
	private boolean warning;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "NET_FATAL",columnDefinition="CHAR",length=1)
	private boolean fatal;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "NET_UNKNOW",columnDefinition="CHAR",length=1)
	private boolean unknown;
	
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
	public String toString() {
		return "NetStatusFilter [all=" + all + ", health=" + health
				+ ", warning=" + warning + ", fatal=" + fatal + ", unknown="
				+ unknown + "]";
	}

}
