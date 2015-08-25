package com.yihuacomputer.fish.api.monitor.business;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.util.PageResult;


public interface ICounterFeitMoneyService{
	
	  /**
     * 创建实体
     *
     * @return
     */
    public ICounterFeitMoney make();
    
    /**
     * 保存交易信息
     *
     * @param transaction
     */
    public void save(ICounterFeitMoney counterFeitMoney);
    
    /**
     * 分页获得全部疑问币信息
     * @param offset
     * @param limit
     * @param filter
     * @param orgId
     * @return
     */
    public IPageResult<ICounterFeitMoney> page(int offset, int limit, IFilter filter,long orgId);
    
    /**
     * 根据条件查询疑问币信息
     * @param offset
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<ICounterFeitMoney> page(int offset, int limit, IFilter filter);
    
	public PageResult<Object> pageObj(int offset, int limit, IFilter filter);

}