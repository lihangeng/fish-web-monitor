package com.yihuacomputer.fish.api.monitor.xfs.status;

/**
 * 设备网络状态
 * @author YiHua
 *
 */
public enum NetStatus {
	/**网络正常*/
	Healthy("正常"),
	
	/**网络不稳定*/
	Warning("不稳定"),
	
	/**网络故障*/
	Fatal("故障"), 
	
	/**未知*/
	Unknown("未知");
	
	private String text;
	
	private NetStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
