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

	public List<Object> statisticsFaultTrend(Date start, Date end);

}
