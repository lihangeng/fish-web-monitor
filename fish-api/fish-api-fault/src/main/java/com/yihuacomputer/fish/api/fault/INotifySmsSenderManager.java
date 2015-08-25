package com.yihuacomputer.fish.api.fault;

public interface INotifySmsSenderManager {

	/**
	 * 发送故障工单短信通知
	 * 
	 * @param caseNotify
	 */
	public void notifySmsSend(ICaseNotify caseNotify);
}
