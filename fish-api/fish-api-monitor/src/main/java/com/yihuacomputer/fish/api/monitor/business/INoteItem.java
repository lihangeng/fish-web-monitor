package com.yihuacomputer.fish.api.monitor.business;

/**
 * 每张钱币的信息
 * @author YiHua
 *
 */
public interface INoteItem{
	/**
	 * 序号.
	 * @return 序号
	 */
	public int getSerial();
	
	/**
	 * 获取冠字号.
	 * @return 冠字号
	 */
	public String getNoteCode();

}