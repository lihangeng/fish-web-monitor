package com.yihuacomputer.fish.api.monitor.xfs.propertise;

/**
 * 设备模块属性
 * @author YiHua
 *
 */
public enum DeviceProp {
	/**具备*/
	T("有"),
	
	/**不具备*/
	F("无");
	
	private String text;
	
	private DeviceProp(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
