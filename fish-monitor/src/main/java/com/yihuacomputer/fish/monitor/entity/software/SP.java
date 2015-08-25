package com.yihuacomputer.fish.monitor.entity.software;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.yihuacomputer.fish.api.monitor.software.ISP;

/**
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2011-12-26 上午10:26:19
 * @version 类说明
 */
@Embeddable
public class SP implements ISP {
	/**
	 * SP版本号
	 */	
	@Column(name = "SP_VERSION",length=30)
	private String spVersion;

	/**
	 * SP补丁信息
	 */
	@Column(name = "SP_PATCH",length=30)
	private String spPatch;

	/**
	 * 版本日期
	 */
	@Column(name = "SP_DATE",length=20)
	private String spDate;

	public void setSpVersion(String spVersion) {
		this.spVersion = spVersion;
	}

	public void setSpPatch(String spPatch) {
		this.spPatch = spPatch;
	}

	public void setSpDate(String spDate) {
		this.spDate = spDate;
	}

	public String getSpVersion() {
		return this.spVersion;
	}

	public String getSpPatch() {
		return this.spPatch;
	}

	public String getSpDate() {
		return this.spDate;
	}
}
