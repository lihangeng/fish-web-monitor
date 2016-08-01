package com.yihuacomputer.fish.api;

public interface ISMSLibMessageService {
	
	public boolean init();
	public void sendMsg (String mobile,String context);
}
