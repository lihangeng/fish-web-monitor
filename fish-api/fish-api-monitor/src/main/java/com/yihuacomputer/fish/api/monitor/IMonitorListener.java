package com.yihuacomputer.fish.api.monitor;

import org.cometd.bayeux.server.ServerSession;
import org.springframework.context.MessageSource;

import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.fish.api.monitor.report.IClassifyReport;
import com.yihuacomputer.fish.api.monitor.report.IDeviceReport;

/**
 * 页面监控事件监听器.
 *
 * @author YiHua
 * @since 2012/2/6
 * @version 1.0
 */

public interface IMonitorListener {

	/**
	 * 初始化或重置设备列表.
	 * @param userSerssion
	 * @param devices
	 */
	public void restart(ServerSession userSession, PageResult<IDeviceReport> devices);

	/**
	 * 更新设备.
	 * @param userSerssion
	 * @param device
	 */
	public void updateDevice(ServerSession userSession, IDeviceReport device);

	/**
	 * 添加设备.
	 * @param userSerssion
	 * @param device
	 */
	public void addDevice(ServerSession userSession, IDeviceReport device);


	/**
	 * 设备删除.
	 * @param userSerssion
	 * @param device
	 */
	public void removeDevice(ServerSession userSession, IDeviceReport device);

	/**增加监控数量.
	 * @param userSerssion
	 * @param total
	 * @return
	 */
	public int updateTotal(ServerSession userSersion, int total);

	/**
	 * 增加一笔交易数据
	 * @param userSerssion
	 * @param device
	 */
	public void addTransation(ServerSession userSession,IDeviceReport device);

	/**
	 * 增加一条进程报警数据
	 * @param userSession
	 * @param device
	 */
	public void addProcess(ServerSession userSession,IDeviceReport device);

	/**
	 * 设备签到
	 * @param userSession
	 * @param device
	 */
	public void deviceSign(ServerSession userSession,IDeviceReport device);

	/**
	 * 增加疑问币假币数据
	 * @param userSerssion
	 * @param device
	 */
	public void addCounterFeitMoney(ServerSession userSession,IDeviceReport device);

	/**
	 * 吞卡
	 * @param userSession
	 * @param device
	 */
	public void addRetaincard(ServerSession userSession, IDeviceReport device,int cardNum);

	/**
	 * 分类监控
	 * @param userSession
	 * @param classifyReport
	 */
	public void reportClassifyMonitor(ServerSession userSession,IClassifyReport classifyReport);

	public MessageSource getMessageSourceRef();

	public void setMessageSourceRef(MessageSource messageSourceRef);
}
