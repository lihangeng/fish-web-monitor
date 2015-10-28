package com.yihuacomputer.fish.api.monitor.xfs.status;
/**
 * 设备模块状态
 * @author YiHua
 *
 */
public enum DeviceStatus {
	/**模块正常*/
	Healthy("DeviceStatus.Healthy","#33ff00"),
	
	/**报警*/
	Warning("DeviceStatus.Warning","#FFFF33"),
	
	/**故障*/
	Fatal("DeviceStatus.Fatal","#FF0000"), 
	
	/**未知*/
	Unknown("DeviceStatus.Unknown","#ccccff"),
	
	/**无设备*/
	NoDevice("DeviceStatus.NoDevice","#ffffff");
	
	private String text;

	private String color;
	private DeviceStatus(String text,String color) {
		this.text = text;
		this.color = color;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
