package com.yihuacomputer.fish.api.monitor.xfs.status;
/**
 * 钞箱状态
 * @author YiHua
 *
 */
public enum BoxStatus {
	
	/**模块正常*/
	Healthy("正常"),
	
	/**取款钞满正常*/
	Full("存款钞满"),
	
	/**钞少*/
	Low("取款钞少"),
	
	/**钞空*/
	Empty("取款钞空"),
	
	/**存款入钞满*/
	High("存款钞将满"),
	
	/**钞箱故障*/
	Fatal("钞箱故障"),
	
	/**钞箱未知*/
	Unknown("未知");
	
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
