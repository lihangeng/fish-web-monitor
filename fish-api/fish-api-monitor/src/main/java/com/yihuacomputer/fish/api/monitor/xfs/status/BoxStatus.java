package com.yihuacomputer.fish.api.monitor.xfs.status;
/**
 * 钞箱状态
 * @author YiHua
 *
 */
public enum BoxStatus {
	
	/**模块正常*/
	Healthy("BoxStatus.Healthy"),
	
	/**取款钞满正常*/
	Full("BoxStatus.Full"),
	
	/**钞少*/
	Low("BoxStatus.Low"),
	
	/**钞空*/
	Empty("BoxStatus.Empty"),
	
	/**存款入钞满*/
	High("BoxStatus.High"),
	
	/**钞箱故障*/
	Fatal("BoxStatus.Fatal"),
	
	/**钞箱未知*/
	Unknown("BoxStatus.Unknown");
	
	private String text;
	
	private BoxStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
