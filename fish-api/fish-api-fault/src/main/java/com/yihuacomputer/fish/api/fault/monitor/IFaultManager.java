package com.yihuacomputer.fish.api.fault.monitor;

import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;

/**
 *
 * 故障处理管理器
 * @since 2.0.0.0
 *
 */
public interface IFaultManager {

	/**
	 * 处理模块故障信息
	 * @param xfsStatus
	 */
	public void handleFault(IXfsStatus xfsStatus);


}
