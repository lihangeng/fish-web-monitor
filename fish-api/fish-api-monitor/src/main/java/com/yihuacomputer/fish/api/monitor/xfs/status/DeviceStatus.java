package com.yihuacomputer.fish.api.monitor.xfs.status;
/**
 * 设备模块状态
 * @author YiHua
 *
 */
public enum DeviceStatus {
	/**模块正常*/
	Healthy("DeviceStatus.Healthy"),
	
	/**报警*/
	Warning("DeviceStatus.Warning"),
	
	/**故障*/
	Fatal("DeviceStatus.Fatal"), 
	
	/**未知*/
	Unknown("DeviceStatus.Unknown"),
	
	/**无设备*/
	NoDevice("DeviceStatus.NoDevice");
	
	private String text;
	
	private DeviceStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
