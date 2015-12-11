package com.yihuacomputer.fish.api.monitor.filter;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IFilterService {

    /**
     * 存储设备状态监控条件
     *
     * @param filter
     */
    public void updateStatusFilter(IStatusFilter filter);

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

    // public IClassifyBoxStatusFilter makeClassifyBoxStatusFilter() ;
    //
    // public IClassifyNetStatusFilter makeClassifyNetStatusFilter();

    /**
     * 保存监控状态
     * 
     * @param statusFilter
     */
    public void save(IStatusFilter statusFilter);

    /**
     * 通过ID获取状态监控
     * 
     * @param id
     * @return
     */
    public IStatusFilter get(long id);

    /**
     * 获取设备状态监控条件
     *
     * @param userId
     * @return
     */
    public List<IStatusFilter> loadUserStatusFilter(String userId);

    /**
     * 删除监控状态
     * 
     * @param statusFilter
     */
    public void delete(IStatusFilter statusFilter);

    /**
     * 删除监控状态
     * 
     * @param id
     */
    public void delete(long id);

    /**
     * 检索所有监控状态
     * 
     * @return
     */
    public List<IStatusFilter> list();

    /**
     * 根据条件检索监控状态
     * 
     * @param filter
     * @return
     */
    public List<IStatusFilter> list(IFilter filter);

    /**
     * 分页监控状态
     * 
     * @param offset
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<IStatusFilter> page(int offset, int limit, IFilter filter);
    
    
    public boolean isDuplicateName(String name);

}
