package com.yihuacomputer.fish.fault.entity;

/**
 * 故障通知内容（短信、邮件）
 * 
 * @author YiHua
 *
 */
public class NotifyContent {

	private String smsNotify;
	
	private String mailNotify;

	public String getSmsNotify() {
		return smsNotify;
	}

	public void setSmsNotify(String smsNotify) {
		this.smsNotify = smsNotify;
	}

	public String getMailNotify() {
		return mailNotify;
	}

	public void setMailNotify(String mailNotify) {
		this.mailNotify = mailNotify;
	}	
	
}
