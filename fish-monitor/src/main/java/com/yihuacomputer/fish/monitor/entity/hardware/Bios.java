package com.yihuacomputer.fish.monitor.entity.hardware;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.yihuacomputer.fish.api.monitor.hardware.IBios;

@Embeddable
public class Bios implements IBios {

	@Column(name = "VERSION",length=256)
	private String biosVersion;

	@Column(name = "VENDOR",length=50)
	private String biosVendor;

	@Column(name = "RELEASE_DATE",length=20)
	private String biosReleaseDate;

	public void setBiosVersion(String biosVersion) {
		this.biosVersion = biosVersion;
	}

	public void setBiosVendor(String biosVendor) {
		this.biosVendor = biosVendor;
	}

	public void setBiosReleaseDate(String biosReleaseDate) {
		this.biosReleaseDate = biosReleaseDate;
	}

	public String getBiosVendor() {
		return this.biosVendor;
	}

	public String getBiosVersion() {
		return this.biosVersion;
	}

	public String getBiosReleaseDate() {
		return this.biosReleaseDate;
	}
}
