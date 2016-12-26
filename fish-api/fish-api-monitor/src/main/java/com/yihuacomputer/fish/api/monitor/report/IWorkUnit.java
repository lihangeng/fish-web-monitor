package com.yihuacomputer.fish.api.monitor.report;

import java.util.List;

import org.springframework.context.MessageSource;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.util.PageResult;

/**
 * 数据加工单元.
 * @author yantao
 *
 */
public interface IWorkUnit {

	/**
	 * 该数据单元设备数
	 * @return
	 */
	public int getTotalDevice();

	/**
	 * 该数据单元在线设备数
	 * @return
	 */
	public int getTotalOnline();

	/**
	 * 该数据单元设备列表
	 * @return
	 */
	public List<String> listDevice();

	/**
	 * 判断设备是否是需要加工.
	 * @param deviceId
	 * @return 判断结果
	 */
	public boolean hasDevice(String deviceId);

	/**
	 * @param offset
	 * @param limit
	 * @return
	 */
	public PageResult<IDeviceReport> page(int offset, int limit);

	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public PageResult<IDeviceReport> page(int offset, int limit, IFilter filter);


	/**
	 * 将用户与数据加工单元绑定
	 * @param user
	 * @param type
	 */
	public void link(IMonitorUser user,MonitorUserType type);

	/**
	 * 解除用户与数据加工单元的绑定关系
	 * @param user
	 * @param type
	 */
	public void unlink(IMonitorUser user,MonitorUserType type);

	/**
	 * 列出该数据单元的绑定的用户
	 * @return
	 */
	public List<String> listUser();

	/**
	 * 加工状态数据
	 * @param deviceId
	 * @param deviceReport
	 * @param messageSourceRef
	 */
	public void fireMonitorUser(String deviceId,IDeviceReport deviceReport,MessageSource messageSourceRef);

	/**
	 * 加工交易数据
	 * @param deviceId
	 * @param deviceReport
	 */
	public void fireTransationUser(String deviceId,IDeviceReport deviceReport);

	/**
	 * 加工白名单进程数据
	 * @param deviceId
	 * @param deviceReport
	 */
	public void fireProcessUser(String deviceId,IDeviceReport deviceReport);

	/**
	 * 加工设备签到注册信息
	 * @param deviceId
	 * @param deviceReport
	 * @param messageSourceRef
	 */
	public void fireBootSign(String deviceId,IDeviceReport deviceReport,MessageSource messageSourceRef);

	/**
	 * 加工假币疑问币数据
	 * @param deviceId
	 * @param deviceReport
	 */
	public void fireCounterFeitMoneyUser(String deviceId,IDeviceReport deviceReport);

	/**
	 * 加工吞卡数据
	 *
	 * @param deviceId
	 * @param deviceReport
	 */
	public void fireRetaincardUser(String deviceId, IDeviceReport deviceReport);
}
