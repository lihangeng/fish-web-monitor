package com.yihuacomputer.fish.api.fault;


/**
 * @author YiHua
 *
 */
public interface INotifyMould {
	
	/**
	 * @return
	 */
	public long getId();

	/**
	 * @param id
	 */
	public void setId(long id);
	
	/**
	 * 故障分类
	 * @return
	 */
	public IFaultClassify getFaultClassify();

	/**
	 * @param faultClassify
	 */
	public void setFaultClassify(IFaultClassify faultClassify);
	/**
	 * 通知类型
	 * @return
	 */
	public NotifyType getNotifyType();

	/**
	 * @param notifyType
	 */
	public void setNotifyType(NotifyType notifyType);

	/**
	 * 通知方式
	 * @return
	 */
	public NotifyWay getNotifyWay();

	/**
	 * @param notifyWay
	 */
	public void setNotifyWay(NotifyWay notifyWay);

	/**
	 * 通知内容参数
	 * @return
	 */
	public String getNotifySet();

	/**
	 * @param notifySet
	 */
	public void setNotifySet(String notifySet);
	
}
