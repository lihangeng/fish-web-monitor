package com.yihuacomputer.fish.api.fault;

public interface INotifyContentService {
	
	/**
	 * 根据通知模板,变量信息，
	 * 将短信内容替换长实际的短信内容
	 * @return
	 */
	public String handleNotifyContent(String notifyContent,INotifyMouldSet notifyMouldSet);

}
