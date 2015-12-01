/**
 * 
 */
package com.yihuacomputer.fish.api.mq;

/**
 * 队列消息的生产者
 * @author xuxiang
 * @since 2.0.0.5
 */
public interface IMqProducer {
	
	/**
	 * 把消息放入队列
	 * @param message
	 */
	public void put(String message);
}
