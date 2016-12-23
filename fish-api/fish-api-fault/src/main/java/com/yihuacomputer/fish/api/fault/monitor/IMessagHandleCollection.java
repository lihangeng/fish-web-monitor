package com.yihuacomputer.fish.api.fault.monitor;

import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;

/**
 * 任务集合
 *
 * @author xuxiang
 * @since 2.0
 */
public interface IMessagHandleCollection {

	/**
	 * 在集合中放入任务
	 * @param xfsStatus
	 */
	public void put(IXfsStatus xfsStatus);

	/**
	 * 在集合中取出下一个任务
	 * @return
	 */
	public IXfsStatus get();
}
