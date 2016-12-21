package com.yihuacomputer.fish.monitor.entity.filter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.yihuacomputer.fish.api.monitor.filter.IBoxStatusFilter;
@Embeddable
public class BoxStatusFilter implements IBoxStatusFilter {
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "BOX_ALL",columnDefinition="CHAR",length=1)
	private boolean all;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "BOX_HEALTHY",columnDefinition="CHAR",length=1)
	private boolean healthy;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "BOX_FULL",columnDefinition="CHAR",length=1)
	private boolean full;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "BOX_LOW",columnDefinition="CHAR",length=1)
	private boolean low;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "BOX_EMPTY",columnDefinition="CHAR",length=1)
	private boolean empty;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "BOX_HIGH",columnDefinition="CHAR",length=1)
	private boolean high;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "BOX_FATAL",columnDefinition="CHAR",length=1)
	private boolean fatal;
	
	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
	@Column(name = "BOX_UNKNOW",columnDefinition="CHAR",length=1)
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
	public boolean isHealthy() {
		return healthy;
	}
	
	@Override
	public void setHealthy(boolean healthy) {
		this.healthy = healthy;
	}
	
	@Override
	public boolean isFull() {
		return full;
	}
	
	@Override
	public void setFull(boolean full) {
		this.full = full;
	}
	
	@Override
	public boolean isLow() {
		return low;
	}
	
	@Override
	public void setLow(boolean low) {
		this.low = low;
	}
	
	@Override
	public boolean isEmpty() {
		return empty;
	}
	
	@Override
	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	
	@Override
	public boolean isHigh() {
		return high;
	}
	
	@Override
	public void setHigh(boolean high) {
		this.high = high;
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
		return "BoxStatusFilter [all=" + all + ", healthy=" + healthy
				+ ", full=" + full + ", low=" + low + ", empty=" + empty
				+ ", high=" + high + ", fatal=" + fatal + ", unknown="
				+ unknown + "]";
	}

}
