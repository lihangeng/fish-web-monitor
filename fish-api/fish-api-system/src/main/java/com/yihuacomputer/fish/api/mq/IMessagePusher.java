/**
 * 
 */
package com.yihuacomputer.fish.api.mq;

/**
 * 消息推送器，推送到页面
 * @author xuxiang
 * @since 2.0.0.6
 */
public interface IMessagePusher {
	
	public void pushStatusToWeb(String message);
	
	public void pushTransToWeb(String message);

}
