package com.yihuacomputer.fish.api;

public interface ISMSLibMessageService {
	
	/**
	 * @param comName com口名称
	 * @return
	 */
	public boolean init(String comName);
	public void sendMsg (String mobile,String context,String comName);
}
