package com.yihuacomputer.fish.api.fault;

/**
 * @author YiHua
 *
 */
public interface INotifyMailSenderManager {

	/**
	 * 发送故障工单邮件通知
	 * 
	 * @param caseNotify
	 */
	public void notifyMailSend(ICaseNotify caseNotify);
}
