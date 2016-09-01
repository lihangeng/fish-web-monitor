package com.yihuacomputer.fish.api.report.device.etl;

import java.util.Date;

/**
 * 吞卡数据抽取服务
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface IRetainCardEtlService {
	
	/**
	 * 每周抽取
	 * @param date
	 */
	public void extractByWeek(Date date);
	
	/**
	 * 每月抽取
	 * @param date
	 */
	public void extractByMonth(Date date);
}
