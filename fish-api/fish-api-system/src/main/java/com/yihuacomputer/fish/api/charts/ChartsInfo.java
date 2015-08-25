package com.yihuacomputer.fish.api.charts;

/**
 * 绘图所需元素
 * @author GQ
 *
 */
public class ChartsInfo {
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 标题所对应的值
	 */
	private long value;
	
	/**
	 * VersionStaticsStatus版本当前状态标识
	 */
	private int flag;
	
	/**
	 * 当前图形描述版本的ID
	 */
	private long versionId;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public long getVersionId() {
		return versionId;
	}
	public void setVersionId(long versionId) {
		this.versionId = versionId;
	}
	
}
