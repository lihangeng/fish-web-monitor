package com.yihuacomputer.fish.api.monitor.filter;



public interface IFilterService {

	/**
	 * 存储设备状态监控条件
	 *
	 * @param filter
	 */
	public void updateStatusFilter(IStatusFilter filter);

	/**
	 * 获取设备状态监控条件
	 *
	 * @param userId
	 * @return
	 */
	public IStatusFilter loadStatusFilter(String userId);

	/**
	 * 创建状态监控条件实体
	 *
	 * @return
	 */
	public IStatusFilter makeStatusFilter();

	/**
	 * 创建并保存状态监控实体
	 *
	 * @param userId
	 * @return
	 */
	public IStatusFilter makeAndSaveStatusFilter(String userId);

	/**
	 * 创建交易监控条件实体类
	 *
	 * @return
	 */
	public ITransationFilter makeTransactionFilter();

	/**
	 * 创建进程监控实体类
	 *
	 * @return
	 */
	public IProcessFilter makeProcessFilter();

	/**
	 * 创建吞卡监控条件实体类
	 *
	 * @return
	 */
	public IRetaincardFilter makeRetaincardFilter();



	public IClassifyModStatusFilter makeClassifyModStatusFilter();

//	public IClassifyBoxStatusFilter makeClassifyBoxStatusFilter() ;
//
//	public IClassifyNetStatusFilter makeClassifyNetStatusFilter();

}
