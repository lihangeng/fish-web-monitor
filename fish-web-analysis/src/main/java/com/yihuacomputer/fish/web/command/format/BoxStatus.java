package com.yihuacomputer.fish.web.command.format;

public enum BoxStatus {
	
	/**
	 * 正常
	 */
	OK("BoxStatusW.OK"),
	
	/**
	 * 钞少
	 */
	LOW("BoxStatusW.LOW"),
	
	/**
	 * 钞空
	 */
	EMPTY("BoxStatusW.EMPTY"),
	
	/**
	 * 钞多
	 */
	HIGH("BoxStatusW.HIGH"),
	
	/**
	 * 钞满
	 */
	FULL("BoxStatusW.FULL"),
	
	/**
	 * 不存在
	 */
	MISSING("BoxStatusW.MISSING"),
	
	/**
	 * 故障
	 */
	BAD("BoxStatusW.BAD");
	
	private String text;
	
	private BoxStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

}
