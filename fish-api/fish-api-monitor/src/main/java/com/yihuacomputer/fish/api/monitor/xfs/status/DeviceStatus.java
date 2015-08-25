package com.yihuacomputer.fish.api.monitor.xfs.status;
/**
 * 设备模块状态
 * @author YiHua
 *
 */
public enum DeviceStatus {
	/**模块正常*/
	Healthy("正常"),
	
	/**报警*/
	Warning("警告"),
	
	/**故障*/
	Fatal("故障"), 
	
	/**未知*/
	Unknown("未知"),
	
	/**无设备*/
	NoDevice("无设备");
	
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
