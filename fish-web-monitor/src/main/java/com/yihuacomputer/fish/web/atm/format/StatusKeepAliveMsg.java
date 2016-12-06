package com.yihuacomputer.fish.web.atm.format;

/**
 * 状态心跳消息体
 * @author GQ
 * @since 1.6.11
 * 如果当前的设备状态和之前的状态没有变化，则直接发送状态心跳报告
 */
public class StatusKeepAliveMsg {
	private String termId;

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}
	
	public StatusKeepAliveMsg(){}
}
