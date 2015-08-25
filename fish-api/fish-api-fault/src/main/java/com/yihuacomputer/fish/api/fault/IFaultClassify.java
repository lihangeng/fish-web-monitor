package com.yihuacomputer.fish.api.fault;

public interface IFaultClassify {
	
	public String getId();

	public void setId(String id);

	/**
	 * 故障分类名称
	 * @return
	 */
	public String getClassifyName();

	public void setClassifyName(String classifyName);

	/**
	 * 故障责任人类型
	 * @return
	 */
	public ResponsorType getResponsorType();

	public void setResponsorType(ResponsorType responsorType);

	/**
	 * 故障规定关闭时间间隔（单位:小时）
	 * @return
	 */
	public double getResolveHour();

	public void setResolveHour(double resolveHour);
	
	/**
	 * 故障规定时间未关闭，最高升级次数
	 * @return
	 */
	public int getUpgrade();

	public void setUpgrade(int upgrade);
	
	/**
	 * 重复通知次数
	 * @param notifyTimes
	 */
	public void setNotifyTimes(int notifyTimes);
	
	public int getNotifyTimes();
	
	/**
	 * 故障通知方式
	 * @return
	 */
	public NotifyWay getNotifyWay();

	public void setNotifyWay(NotifyWay notifyWay);
}
