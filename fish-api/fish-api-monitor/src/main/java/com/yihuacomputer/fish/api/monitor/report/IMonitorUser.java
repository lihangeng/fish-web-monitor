package com.yihuacomputer.fish.api.monitor.report;

import org.cometd.bayeux.server.ServerSession;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.fish.api.monitor.filter.IClassifyModStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IProcessFilter;
import com.yihuacomputer.fish.api.monitor.filter.IRetaincardFilter;
import com.yihuacomputer.fish.api.monitor.filter.IStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.ITransationFilter;
/**
 * 监控用户条件信息
 * @author YiHua
 *
 */
public interface IMonitorUser {

	/**
	 * 获取用户ID
	 * @return
	 */
	public String getUserId();
	/**
	 * @param userId
	 */
	public void setUserId(String userId);

	/**
	 * 获取用户名
	 * @return
	 */
	public String getUserName();

	/**
	 * 获取用户Session
	 * @return
	 */
	public ServerSession getUserSession();

	/**
	 * 设置监控用户Session
	 * @param userSession
	 */
	public void setUserSession(ServerSession userSession);

	/**
	 * 设置状态监控条件
	 *
	 * @param statusFilter
	 */
	public void setStatusFilter(IStatusFilter statusFilter);

	/**
	 * 获取交易监控条件
	 * @return
	 */
	public ITransationFilter getTransFilter();

	/**
	 * 设置交易监控条件
	 * @param transFilter
	 */
	public void setTransFilter(ITransationFilter transFilter);

	/**
	 * 设置假币疑问币监控条件
	 * @param transFilter
	 */
	public void setCounterFeitMoneyFilter(ITransationFilter transFilter);

	/**
	 * 获取进程监控条件
	 * @return
	 */
	public IProcessFilter getProcessFilter();

	/**
	 * 设置进程监控条件
	 * @param processFilter
	 */
	public void setProcessFilter(IProcessFilter processFilter);
	/**
	 * 获取用户监控单元
	 * @return
	 */
	public IWorkUnit getWorkUnit();

	/**
	 * 重置或者初始化
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public PageResult<IDeviceReport> restart(int offset, int limit, IFilter filter);

	/**
	 * 获取偏移量
	 * @return
	 */
	public int getOffset();

	/**
	 * 获取监控设备数
	 * @return
	 */
	public int getLimit();

	/**
	 * 获取监控条件
	 * @return
	 */
	public IFilter getFilter();

	/**
	 * 获取监控设备信息
	 * @return
	 */
	public PageResult<IDeviceReport> getResult();
	/**
	 * 取状态监控条件
	 * @return
	 */
	public IStatusFilter getStatusFilter();

	/**
	 * 取交易监控条件
	 * @return
	 */
	public ITransationFilter getTransationFilter();

	/**
	 * 取假币疑问币监控条件
	 * @return
	 */
	public ITransationFilter getCounterFeitMoneyFilter();

	/**
	 * 取吞卡监控条件
	 *
	 * @return
	 */
	public IRetaincardFilter getRetaincardFilter();

	/**
	 * 设置吞卡监控条件
	 *
	 * @param retaincardFilter
	 */
	public void setRetaincardFilter(IRetaincardFilter retaincardFilter);


	/**
	 * 取模块监控条件
	 * @return
	 */
	public IClassifyModStatusFilter getModStatusFilter();

	/**
	 * 设置模块监控条件
	 * @param modStatusFilter
	 */
	public void setModStatusFilter(IClassifyModStatusFilter modStatusFilter);


}
