package com.yihuacomputer.fish.monitor.service;

import org.springframework.context.MessageSource;

import com.yihuacomputer.fish.api.monitor.AlarmInfo;
import com.yihuacomputer.fish.api.monitor.BusinessInfo;
import com.yihuacomputer.fish.api.monitor.ICollectListener;
import com.yihuacomputer.fish.api.monitor.SystemInfo;
import com.yihuacomputer.fish.api.monitor.report.IDeviceReport;
import com.yihuacomputer.fish.api.monitor.report.IWorkUnit;


/**
 * 数据采集事件监听器.
 * @author yantao
 *
 */
public class CollectListener implements ICollectListener{

	private IWorkUnit workUnit;

	@Override
	public void setWorkUnit(IWorkUnit workUnit){
		this.workUnit = workUnit;
	}
	/**
	 * 设备注册
	 * @param deviceId
	 */
	@Override
	public void registered(String deviceId){

	}

	/**
	 * 设备签到
	 * @param deviceId
	 */
	public void signed(String deviceId){

	}

	/**
	 * 网络超时
	 * @param deviceId
	 */
	@Override
	public void timeout(String deviceId){

	}

	/**
	 * 系统信息
	 * @param deviceId
	 * @param info
	 */
	@Override
	public void receivedSystem(String deviceId, SystemInfo info){

	}

	/**
	 * ATMC业务信息
	 * @param deviceId
	 * @param info
	 */
	@Override
	@SuppressWarnings("incomplete-switch")
	public void receivedBusiness(String deviceId, BusinessInfo info,IDeviceReport deviceReport){

		switch (info) {
			case TRANSACTION:{
				if(workUnit.hasDevice(deviceId)){
					workUnit.fireTransationUser(deviceId, deviceReport);
				}
				break;
			}
			case RETAIN_CARD: {
				if (workUnit.hasDevice(deviceId)) {
					workUnit.fireRetaincardUser(deviceId, deviceReport);
				}
				break;
			}
			case COUNTERFEITMONEY:{
				if(workUnit.hasDevice(deviceId)){
					workUnit.fireCounterFeitMoneyUser(deviceId, deviceReport);
				}
			}
		}
	}

	/**
	 * 报警
	 * @param deviceId
	 * @param info
	 */
	@Override
	public void receivedAlarm(String deviceId, AlarmInfo info,IDeviceReport deviceReport){
		if(workUnit.hasDevice(deviceId)){
			workUnit.fireProcessUser(deviceId, deviceReport);
		}
	}

	/**
	 * 模块状态
	 * @param deviceId
	 */
	@Override
	public void receivedStatus(String deviceId,IDeviceReport deviceReport,MessageSource messageSourceRef){
		if(workUnit.hasDevice(deviceId)){
			workUnit.fireMonitorUser(deviceId,deviceReport,messageSourceRef);
		}
	}

	/**
	 * 模块属性
	 * @param deviceId
	 */
	@Override
	public void receivedProperty(String deviceId){

	}
	@Override
	public void signed(String deviceId, IDeviceReport deviceReport,MessageSource messageSourceRef) {
		if(workUnit.hasDevice(deviceId)){
			workUnit.fireBootSign(deviceId, deviceReport,messageSourceRef);
		}
	}

}
