package com.yihuacomputer.fish.monitor.entity.software;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.yihuacomputer.fish.api.monitor.software.IAnti;

/**
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2011-12-26 上午10:28:47
 * @version 病毒软件
 */
@Embeddable
public class Anti implements IAnti {
	/**
	 * 病毒软件版本
	 */	
	@Column(name = "ANTI_VERSION",length=20)
	private String antiVer;

	/**
	 * 病毒软件名称
	 */
	@Column(name = "ANTI_SYSTEM",length=50)
	private String antiName;

	public void setAntiVer(String antiVer) {
		this.antiVer = antiVer;
	}

	@Override
	public String getAntiVer() {
		return this.antiVer;
	}

	public void setAntiName(String antiName) {
		this.antiVer = antiName;
	}

	@Override
	public String getAntiName() {
		return this.antiName;
	}

}
