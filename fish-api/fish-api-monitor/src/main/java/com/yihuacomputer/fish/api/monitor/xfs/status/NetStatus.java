package com.yihuacomputer.fish.api.monitor.xfs.status;

/**
 * 设备网络状态
 * @author YiHua
 *
 */
public enum NetStatus {
	/**网络正常*/
	Healthy("NetStatus.Healthy","#56cf3e"),//green
	
	/**网络不稳定*/
	Warning("NetStatus.Warning","#e4d603"),//orange
	
	/**网络故障*/
	Fatal("NetStatus.Fatal","#da0404"), //red
	
	/**未知*/
	Unknown("NetStatus.Unknown","#a9a89d");//grey
	
	private String text;
	
	private String color;
	
	private NetStatus(String text,String color) {
		this.text = text;
		this.color = color;
	}

	public String getText() {
		return text;
	}

	public String getColor() {
		return color;
	}

}
