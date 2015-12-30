package com.yihuacomputer.fish.web.monitor.form;

public class PropertyBcr {
	/**
	 * 是否为合成设备
	 * @return
	 */
	public String canCompound;

	/**
	 * 能否辨别制定的条码
	 * @return
	 */
	public String canFilterSymbologies;

	public String getCanCompound() {
		return canCompound;
	}

	public void setCanCompound(String canCompound) {
		this.canCompound = canCompound;
	}

	public String getCanFilterSymbologies() {
		return canFilterSymbologies;
	}

	public void setCanFilterSymbologies(String canFilterSymbologies) {
		this.canFilterSymbologies = canFilterSymbologies;
	}
}
