package com.yihuacomputer.fish.api.monitor.xfs.status;
/**
 * 钞箱状态
 * @author YiHua
 *
 */
public enum BoxStatus {
	
	/**模块正常*/
	Healthy("BoxStatus.Healthy","#56cf3e"),//green
	
	/**取款钞满正常*/
	Full("BoxStatus.Full","#BDB517"),//黄色低亮度
	
	/**钞少*/
	Low("BoxStatus.Low","#FBF58C"),//黄色高亮度
	
	/**钞空*/
	Empty("BoxStatus.Empty","#D78C8C"),//红色高亮度
	
	/**存款入钞满*/
	High("BoxStatus.High","#e4d603"),//orange
	
	/**钞箱故障*/
	Fatal("BoxStatus.Fatal","#da0404"),//red
	
	/**钞箱未知*/
	Unknown("BoxStatus.Unknown","#a9a89d");//gray

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
