package com.yihuacomputer.domain.spring;

public enum DataSources {
	/**
	 *监控系统数据源 
	 */
	Monitor("dataSource"), 
	/**
	 *分析系统数据源 
	 */
	Analysis("analysis");
	private String text;

	private DataSources(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
