package com.yihuacomputer.fish.web.monitor.form;

/**
 * 获取ATM运行信息时传入的参数
 * 
 * @author wangchao
 * 
 */
public class RuntimeInfoMsg {
	private String date;

	private int limit;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
