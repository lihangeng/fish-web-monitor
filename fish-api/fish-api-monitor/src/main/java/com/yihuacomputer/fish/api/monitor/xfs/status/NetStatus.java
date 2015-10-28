package com.yihuacomputer.fish.api.monitor.xfs.status;

/**
 * 设备网络状态
 * @author YiHua
 *
 */
public enum NetStatus {
	/**网络正常*/
	Healthy("NetStatus.Healthy","#33ff00"),//green
	
	/**网络不稳定*/
	Warning("NetStatus.Warning","#FFFF33"),//orange
	
	/**网络故障*/
	Fatal("NetStatus.Fatal","#FF0000"), //red
	
	/**未知*/
	Unknown("NetStatus.Unknown","#ccccff");//grey
	
	private String text;
	
	private String color;
	
	private NetStatus(String text,String color) {
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
