package com.yihuacomputer.fish.monitor.entity.hardware;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.yihuacomputer.fish.api.monitor.hardware.IBios;

/**
 * @author YiHua
 *
 */
@Embeddable
public class Bios implements IBios {

	@Column(name = "VERSION",length=256)
	private String biosVersion;

	@Column(name = "VENDOR",length=50)
	private String biosVendor;

	@Column(name = "RELEASE_DATE",length=20)
	private String biosReleaseDate;

	@Override
	public void setBiosVersion(String biosVersion) {
		this.biosVersion = biosVersion;
	}

	@Override
	public void setBiosVendor(String biosVendor) {
		this.biosVendor = biosVendor;
	}

	@Override
	public void setBiosReleaseDate(String biosReleaseDate) {
		this.biosReleaseDate = biosReleaseDate;
	}

	@Override
	public String getBiosVendor() {
		return this.biosVendor;
	}

	@Override
	public String getBiosVersion() {
		return this.biosVersion;
	}

	@Override
	public String getBiosReleaseDate() {
		return this.biosReleaseDate;
	}
}
