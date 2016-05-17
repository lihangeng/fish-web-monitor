package com.yihuacomputer.fish.web.monitor.form;
public class HardwareVersion {

	private String device;

	private String spVersion;

	private String driverVersion;

	private String fwVersion;

	public HardwareVersion() {
	}

	public HardwareVersion(HardwareVersion version) {
		setDevice(version.getDevice());
		setDriverVersion(version.getDriverVersion());
		setFwVersion(version.getFwVersion());
		setSpVersion(version.getSpVersion());
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getSpVersion() {
		return spVersion;
	}

	public void setSpVersion(String spVersion) {
		this.spVersion = spVersion;
	}

	public String getDriverVersion() {
		return driverVersion;
	}

	public void setDriverVersion(String driverVersion) {
		this.driverVersion = driverVersion;
	}

	public String getFwVersion() {
		return fwVersion;
	}

	public void setFwVersion(String fwVersion) {
		this.fwVersion = fwVersion;
	}

}
