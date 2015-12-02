/**
 * 
 */
package com.yihuacomputer.fish.api.mq;

/**
 * 队列消息的消费者
 * @author xuxiang
 *
 */
public interface IMqConsumer {
	
	/**
	 * 从消息队列中获取消息
	 * @return
	 */
	public String get();
}
