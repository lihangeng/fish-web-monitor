package com.yihuacomputer.fish.api.monitor.xfs.status;
/**
 * 钞箱状态
 * @author YiHua
 *
 */
public enum BoxStatus {
	
	/**模块正常*/
	Healthy("BoxStatus.Healthy","#33ff00"),
	
	/**取款钞满正常*/
	Full("BoxStatus.Full","#FFCC00"),
	
	/**钞少*/
	Low("BoxStatus.Low","#9999FF"),
	
	/**钞空*/
	Empty("BoxStatus.Empty","#FFFFFF"),
	
	/**存款入钞满*/
	High("BoxStatus.High","#FFFF66"),
	
	/**钞箱故障*/
	Fatal("BoxStatus.Fatal","#FFCC00"),
	
	/**钞箱未知*/
	Unknown("BoxStatus.Unknown","#ccccff");

	private String text;
	private String color;
	
	private BoxStatus(String text,String color) {
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
