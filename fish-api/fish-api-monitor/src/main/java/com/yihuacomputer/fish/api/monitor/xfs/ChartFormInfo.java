package com.yihuacomputer.fish.api.monitor.xfs;

public class ChartFormInfo {
	/**
	 * 图表展示名称
	 */
	private String displayName;
	/**
	 * 点击查询条件
	 */
	private String filterStr;
	
	/**
	 * 当前图所使用的颜色
	 */
	private String color;
	
	/**
	 * 图表中的数据量
	 */
	private int numberInfo;
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getFilterStr() {
		return filterStr;
	}
	public void setFilterStr(String filterStr) {
		this.filterStr = filterStr;
	}
	public int getNumberInfo() {
		return numberInfo;
	}
	public void setNumberInfo(int numberInfo) {
		this.numberInfo = numberInfo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
}
