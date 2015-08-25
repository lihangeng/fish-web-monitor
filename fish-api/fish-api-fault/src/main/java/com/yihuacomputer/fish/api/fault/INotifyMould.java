package com.yihuacomputer.fish.api.fault;


public interface INotifyMould {
	
	public long getId();

	public void setId(long id);
	
	/**
	 * 故障分类
	 * @return
	 */
	public IFaultClassify getFaultClassify();

	public void setFaultClassify(IFaultClassify faultClassify);
	/**
	 * 通知类型
	 * @return
	 */
	public NotifyType getNotifyType();

	public void setNotifyType(NotifyType notifyType);

	/**
	 * 通知方式
	 * @param notifyWay
	 */
	public NotifyWay getNotifyWay();

	public void setNotifyWay(NotifyWay notifyWay);

	/**
	 * 通知内容参数
	 * @return
	 */
	public String getNotifySet();

	public void setNotifySet(String notifySet);
	
}
