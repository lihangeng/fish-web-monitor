package com.yihuacomputer.fish.api.monitor.business;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.util.PageResult;


/**
 * @author YiHua
 *
 */
public interface IUncommonTransService{
	 /**
     * 创建实体
     *
     * @return
     */
    public IUncommonTrans make();
    
    /**
     * 通过id,获取异常交易对象
     *
     * @param id
     *            　设备id
     * @return 异常交易对象
     */
    public IUncommonTrans get(long id);
    
    /**
     * 通过交易流水号,获取异常交易对象
     *
     * @param transId
     *            　设备id
     * @return 异常交易对象
     */
    public IUncommonTrans get(String transId);
    
    /**
     * 修改异常交易
     *
     * @param trans
     *            需要修改的异常交易
     */
    public void update(IUncommonTrans trans);
    
    /**
     * 保存交易信息
     *
     * @param uncommonTrans
     */
    public void save(IUncommonTrans uncommonTrans);
    
    /**
     * 分页获得全部异常信息
     * @param offset
     * @param limit
     * @param filter
     * @param orgId
     * @return
     */
    public IPageResult<IUncommonTrans> page(int offset, int limit, IFilter filter,long orgId);
    
    /**
     * 根据条件查询异常信息
     * @param offset
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<IUncommonTrans> page(int offset, int limit, IFilter filter);
	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public PageResult<Object> pageObj(int offset, int limit, IFilter filter);
}
	