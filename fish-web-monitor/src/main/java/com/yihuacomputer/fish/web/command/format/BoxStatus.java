package com.yihuacomputer.fish.web.command.format;

public enum BoxStatus {
	
	/**
	 * 正常
	 */
	OK("正常"),
	
	/**
	 * 钞少
	 */
	LOW("钞少"),
	
	/**
	 * 钞空
	 */
	EMPTY("钞空"),
	
	/**
	 * 钞多
	 */
	HIGH("钞多"),
	
	/**
	 * 钞满
	 */
	FULL("钞满"),
	
	/**
	 * 不存在
	 */
	MISSING("不存在"),
	
	/**
	 * 故障
	 */
	BAD("故障");
	
	private String text;
	
	private BoxStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
