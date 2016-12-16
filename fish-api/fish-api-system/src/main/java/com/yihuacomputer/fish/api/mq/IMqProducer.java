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
	 * @since 2.1.1.11
	 */
	@Deprecated
	public void put(String message);
	

	/**
	 * 把状态消息放入队列
	 * @param statusMessage
	 * @since 2.1.1.11
	 */
	public void putStatus(String statusMessage);
	/**
	 * 把交易消息放入队列
	 * @param message
	 * @since 2.1.1.11
	 */
	public void putTransaction(String transMessage);
	/**
	 * 把登录消息放入队列
	 * @param message
	 * @since 2.1.1.11
	 */
	public void putLogin(String loginMessage);
	/**
	 * 把退出消息放入队列
	 * @param message
	 * @since 2.1.1.11
	 */
	public void putLogout(String logoutMessage);
	
	/**
	 * 把故障信息放入对列
	 * @param caseFault
	 */
	public void putCaseFault(String caseFault);
}
