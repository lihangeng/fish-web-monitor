package com.yihuacomputer.fish.api.monitor.xfs.status;

/**
 * 设备网络状态
 * @author YiHua
 *
 */
public enum NetStatus {
	/**网络正常*/
	Healthy("NetStatus.Healthy"),
	
	/**网络不稳定*/
	Warning("NetStatus.Warning"),
	
	/**网络故障*/
	Fatal("NetStatus.Fatal"), 
	
	/**未知*/
	Unknown("NetStatus.Unknown");
	
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
