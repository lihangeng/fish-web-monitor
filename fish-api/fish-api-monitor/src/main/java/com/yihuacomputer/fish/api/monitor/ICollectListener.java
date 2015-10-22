package com.yihuacomputer.fish.api.monitor;

import org.springframework.context.MessageSource;

import com.yihuacomputer.fish.api.monitor.report.IDeviceReport;
import com.yihuacomputer.fish.api.monitor.report.IWorkUnit;


/**
 * 数据采集事件监听器.
 * @author yantao
 *
 */
public interface ICollectListener {

	public void setWorkUnit(IWorkUnit workUnit);
	
	/**
	 * 设备注册
	 * @param deviceId
	 */
	public void registered(String deviceId);

	/**
	 * 设备签到
	 * @param deviceId
	 */
	public void signed(String deviceId,IDeviceReport deviceReport,MessageSource messageSourceRef);

	/**
	 * 网络超时
	 * @param deviceId
	 */
	public void timeout(String deviceId);

	/**
	 * 系统信息
	 * @param deviceId
	 * @param info
	 */
	public void receivedSystem(String deviceId, SystemInfo info);

	/**
	 * ATMC业务信息
	 * @param deviceId
	 * @param info
	 */
	public void receivedBusiness(String deviceId, BusinessInfo info,IDeviceReport deviceReport);

	/**
	 * 报警
	 * @param deviceId
	 * @param info
	 */
	public void receivedAlarm(String deviceId, AlarmInfo info,IDeviceReport deviceReport);
	
	/**
	 * 模块状态
	 * @param deviceId
	 */
	public void receivedStatus(String deviceId,IDeviceReport deviceReport,MessageSource messageSourceRef);

	/**
	 * 模块属性
	 * @param deviceId
	 */
	public void receivedProperty(String deviceId);

}
