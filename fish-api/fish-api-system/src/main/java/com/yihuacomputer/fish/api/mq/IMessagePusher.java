/**
 * 
 */
package com.yihuacomputer.fish.api.mq;

/**
 * 消息推送器
 * @author xuxiang
 *
 */
public interface IMessagePusher {
	
	public void pushStatusToWeb(String message);
	
	public void pushTransToWeb(String message);

}
