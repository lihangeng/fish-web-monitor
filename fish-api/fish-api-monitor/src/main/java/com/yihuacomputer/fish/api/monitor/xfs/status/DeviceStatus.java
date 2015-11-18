package com.yihuacomputer.fish.api.monitor.xfs.status;
/**
 * 设备模块状态
 * @author YiHua
 *
 */
public enum DeviceStatus {
	/**模块正常*/
	Healthy("DeviceStatus.Healthy","#56cf3e"),//green
	
	/**报警*/
	Warning("DeviceStatus.Warning","#e4d603"),//orange
	
	/**故障*/
	Fatal("DeviceStatus.Fatal","#da0404"), //red
	
	/**未知*/
	Unknown("DeviceStatus.Unknown","#a9a89d"),//gray
	
	/**无设备*/
	NoDevice("DeviceStatus.NoDevice","#A6BDB8");
	
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
