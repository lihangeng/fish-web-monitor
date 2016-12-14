package com.yihuacomputer.fish.api.monitor.xfs.propertise;

/**
 * 设备模块属性
 * @author YiHua
 *
 */
public enum DeviceProp {
	/**具备*/
	T("DeviceProp.T"),
	
	/**不具备*/
	F("DeviceProp.F");
	
	private String text;
	
	private DeviceProp(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
