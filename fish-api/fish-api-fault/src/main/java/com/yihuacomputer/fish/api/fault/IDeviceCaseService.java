package com.yihuacomputer.fish.api.fault;

import com.yihuacomputer.fish.api.monitor.business.IDeviceRegister;
import com.yihuacomputer.fish.api.monitor.business.IRetaincard;
import com.yihuacomputer.fish.api.monitor.business.IRunInfo;
import com.yihuacomputer.fish.api.monitor.report.IRuntimeInfo;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;

public interface IDeviceCaseService {

	/**
	 * 处理设备安装注册
	 *
	 * @param deviceRegister
	 */
	public void handleDeviceRegister(IDeviceRegister deviceRegister);


	/**
	 * 处理吞卡消息
	 * @param retianCard
	 */
	public void handleRetainCard(IRetaincard retainCard);

	/**
	 *  收集运行信息
	 * @param runtimeInfo
	 */
	public void handleRumtimeInfo(IRuntimeInfo runtimeInfo);


	/**
	 * 处理设备模块状态
	 * @param xfsStatus
	 */
	public void handleModStatus(IXfsStatus xfsStatus,IXfsStatus hisXfsStatus);

	/**
	 * 处理应用状态
	 * @param appStatus
	 */
	public void handleAppStatus(IRunInfo appStatus);

	/**
	 * 处理设备模块状态
	 * @param xfsStatus
	 */
	public void handleModStatus(IXfsStatus xfsStatus);
}
