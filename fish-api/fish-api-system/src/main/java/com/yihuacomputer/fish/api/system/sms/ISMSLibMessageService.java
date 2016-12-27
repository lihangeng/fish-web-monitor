package com.yihuacomputer.fish.api.system.sms;

/**
 * @author YiHua
 *
 */
public interface ISMSLibMessageService {
	
	/**
	 * @param comName com口名称
	 * @return
	 */
	public boolean init(String comName);
	/**
	 * @param mobile
	 * @param context
	 * @param comName
	 */
	public void sendMsg (String mobile,String context,String comName);
	/**
	 * 销毁信息
	 */
	void destory();
}
