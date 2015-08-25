package com.yihuacomputer.fish.api.system.im;

public interface ISMSLibMessageService {

	/**
	 * 发送短信
	 * @param telePhoneNum
	 * @param MessageInfo
	 */
	void sendMsg(String telePhoneNum,String MessageInfo);
	void destory();
}
