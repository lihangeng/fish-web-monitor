package com.yihuacomputer.fish.api.atmlog;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IAtmLogGlobalStatisticsService {
	
	/**
	 * 根据时间，组织机构，以及品牌分页查询日志的全局统计情况
	 * @param start
	 * @param limit
	 * @param filter
	 * @param orgId
	 * @return
	 */
	public IPageResult<IAtmLogGlobalStatistics> pageList(int start , int limit , IFilter filter,long orgId);
	
	/**
	 * 根据相应条件查对日志进行统计
	 * @param filter
	 * @param orgId
	 * @return
	 */
	public List<IAtmLogGlobalStatistics> list(IFilter filter, long orgId);
	
	/**
	 * 根据具体条件查询失败的日志信息
	 * @param filter
	 * @param orgId
	 * @return
	 */
	public List<IErrorLogsDeviceInfo> loadDayErrorLogs(IFilter filter, long orgId);
	
	
}
