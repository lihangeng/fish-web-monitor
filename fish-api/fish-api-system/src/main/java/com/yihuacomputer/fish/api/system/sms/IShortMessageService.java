package com.yihuacomputer.fish.api.system.sms;




public interface IShortMessageService {
	
	public IShortMessage make();

	public boolean send(IShortMessage message)throws Exception;
	
}
