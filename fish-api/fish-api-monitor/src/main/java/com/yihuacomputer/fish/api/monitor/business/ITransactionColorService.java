package com.yihuacomputer.fish.api.monitor.business;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface ITransactionColorService {
    /**
     * 实例化交易返回码颜色对象
     *
     * @return 设备对象
     */
    public ITransactionColor make();

    /**
     * 通过id,获取交易返回码颜色对象
     *
     * @param id
     * @return
     */
    public ITransactionColor get(long id);

    /**
     * 通过返回码,获取交易返回码颜色对象
     *
     * @param hostRet
     * @return
     */
    public ITransactionColor get(String hostRet);

    /**
     * 增加交易返回码颜色对象
     *
     * @param transactionColor
     */
    public void add(ITransactionColor transactionColor);

    /**
     * 删除交易返回码颜色对象
     *
     * @param id
     */
    public void remove(long id);

    /**
     * 删除交易返回码颜色对象
     *
     * @param hostRet
     */
    public void remove(String hostRet);

    /**
     * 批量删除用户名
     * 
     * @param userName
     */
    public void batchRemove(String userName);

    /**
     * 修改交易返回码颜色对象
     *
     * @param transactionColor
     */
    public void update(ITransactionColor transactionColor);

    /**
     * 获取所有交易返回码颜色对象
     *
     * @return
     */
    public List<ITransactionColor> list();

    /**
     * 获取符合条件的交易返回码颜色对象
     *
     * @param filter
     * @return
     */
    public List<ITransactionColor> list(IFilter filter);

    /**
     * 获取符合条件的交易返回码颜色对象分页
     *
     * @param offset
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<ITransactionColor> page(int offset, int limit, IFilter filter);

}
