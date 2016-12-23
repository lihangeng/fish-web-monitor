package com.yihuacomputer.fish.api.fault;

import java.util.Date;
import java.util.List;

/**
 * 故障统计服务
 * @author xuxiang
 * @since 2.0.0.3
 *
 */
public interface IFaultStatisticsService {

	/**
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Object> statisticsFaultTrend(Date start, Date end);
	
	/**
	 * 指定时间范围和设备统计故障信息
	 * @param start
	 * @param end
	 * @param terminalId
	 * @return
	 */
	public List<Object> statisticsFaultTrendByTerminalId(Date start, Date end,String terminalId);

}
