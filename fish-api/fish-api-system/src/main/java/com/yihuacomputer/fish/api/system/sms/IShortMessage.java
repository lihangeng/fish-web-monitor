package com.yihuacomputer.fish.api.system.sms;

import java.util.List;

public interface IShortMessage {

	/**
	 * 接收短信手机列表.
	 * @return 手机号码列表
	 */
	public List<String> listMobile();
	
	public void addMobile(String mobile);
	
	public void addMobile(List<String> mobiles);
	
	/**
	 * 短信内容.
	 * @return 短信内容
	 */
	public String getContent();
	
	public void setContent(String content);
	
	/**
	 * 短信发送号码.
	 * 如果为null或""，发送号码显示缺省值。可填入手机号码或sp号码。
	 * @return 发送号码
	 */
	public String getSendCode();
	
}
