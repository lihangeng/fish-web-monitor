package com.yihuacomputer.fish.api.monitor.business;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface ITransactionViewService {

    /**
     * 创建实体
     *
     * @return
     */
    public ITransactionView make();

    /**
     * 通过过滤条件查询黑名单卡交易信息并分页：
     * @param offset
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<ITransactionView> pageBlackList(int offset, int limit, IFilter filter,long orgId);

    /**
     * 通过过滤条件查询非黑名单卡交易信息并分页：
     * @param offset
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<ITransactionView> pageNoBlackList(int offset, int limit, IFilter filter,long orgId);

    /**
     * 分页获得全部交易
     * @param offset
     * @param limit
     * @param filter
     * @param orgId
     * @return
     */
    public IPageResult<ITransactionView> page(int offset, int limit, IFilter filter,long orgId);

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
    public IPageResult<ITransactionView> page(int offset, int limit, IFilter filter);
}
