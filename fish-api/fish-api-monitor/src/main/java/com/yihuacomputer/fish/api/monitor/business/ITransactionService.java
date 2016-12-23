package com.yihuacomputer.fish.api.monitor.business;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.util.ThreeTuple;

/**
 * @author YiHua
 *
 */
public interface ITransactionService
{

    /**
     * 创建实体
     *
     * @return
     */
    public ITransaction make();

    /**
     * 保存交易信息
     *
     * @param transaction
     */
    public void save(ITransaction transaction);

    /**
     * 通过过滤条件查询黑名单卡交易信息并分页：
     * @param offset
     * @param limit
     * @param filter
     * @param orgId
     * @return
     */
    public IPageResult<ITransaction> pageBlackList(int offset, int limit, IFilter filter,long orgId);

    /**
     * 通过过滤条件查询非黑名单卡交易信息并分页：
     * @param offset
     * @param limit
     * @param filter
     * @param orgId
     * @return
     */
    public IPageResult<ITransaction> pageNoBlackList(int offset, int limit, IFilter filter,long orgId);

    /**
     * 分页获得全部交易
     * @param offset
     * @param limit
     * @param filter
     * @param orgId
     * @return
     */
    public IPageResult<ITransaction> page(int offset, int limit, IFilter filter,long orgId);

    /**
     * 获得交易返回码信息
     * @return
     */
    public List<IHostRet> listHostRet();

    /**
     * 根据条件查询交易记录
     * @param offset
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<ITransaction> page(int offset, int limit, IFilter filter);


    /**
     *查询日均交易统计
     * @param filter
     * @return
     */
    public List<Object> statisticsTransTrend(IFilter filter);

    /**
     *查询时均交易统计
     * @param filter
     * @return
     */
    public List<Object> statisticsTransHourTrend(IFilter filter);
    
	/**
	 * 单台设备指定时间内各种交易次数
	 * @param filter
	 * @return
	 */
	public List<ThreeTuple<String, Integer,Double>> statisticsTransCountForDevice(IFilter filter);
}
