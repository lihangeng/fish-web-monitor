package com.yihuacomputer.fish.api.monitor;

import com.yihuacomputer.fish.api.monitor.report.IDeviceReport;
import com.yihuacomputer.fish.api.monitor.report.IMonitorUser;
import com.yihuacomputer.fish.api.monitor.report.IWorkUnit;
import com.yihuacomputer.fish.api.monitor.report.MonitorUserType;

/**
 * 设备监控服务.
 * 
 * @author YiHua
 * @since 2012/2/6
 * @version 1.0
 */
public interface IMonitorService {

	
	/**
	 * 增加监控监听.
	 * @param listener
	 */
	public void setMonitorListener(IMonitorListener listener);

	/**
	 * @return
	 */
	public IMonitorListener getMonitorListener();
	/***
	 * 初始化服务.
	 */
	public void init();

	/**
	 * 关闭服务.
	 */
	public void close();

	/**
	 * 获取设备动态报告信息.
	 * @param deviceId
	 * @return
	 */
	public IDeviceReport getMonitorReport(String deviceId);

	/**
	 * 添加一个数据加工单元.
	 * @param workUnit
	 */
	public void addWorkUnit(IWorkUnit workUnit);

	/**
	 * 根据用户ID创建用户监控条件实例.
	 * @param userId
	 * @return
	 */
	public IMonitorUser makeMonitorUser(String userId);
	
	/**
	 * 添加一个监控用户.
	 * @param user
	 * @param type
	 */
	public void addMonitorUser(IMonitorUser user,MonitorUserType type);

	/**
	 * 获取一个监控用户.
	 * @param userId
	 * @return
	 */
	
	public IMonitorUser getMonitoruser(String userId);

	/**
	 * 删除一个监控用户.
	 * @param userId
	 * @param type
	 */
	public void removeMonitorUser(String userId,MonitorUserType type);	

}
