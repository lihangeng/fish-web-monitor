package com.yihuacomputer.fish.api.report.engine;

/**
 * 报表数据抽取ETL模型
 * @author YiHua
 *
 */
public interface IReportDataETL {

	/**
	 * 初始化
	 */
	public void init();
	
	/**
	 * 抽取执行器名称
	 * @return
	 */
	public String getReportETLName();
	
	/**
	 * 执行数据抽取动作
	 * @param date yyyy-MM-dd
	 */
	public void reportETL(String date);
}
