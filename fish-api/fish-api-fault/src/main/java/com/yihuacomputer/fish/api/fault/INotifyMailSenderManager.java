package com.yihuacomputer.fish.api.fault;

public interface INotifyMailSenderManager {

	/**
	 * 发送故障工单邮件通知
	 * 
	 * @param caseNotify
	 */
	public void notifyMailSend(ICaseNotify caseNotify);
}
