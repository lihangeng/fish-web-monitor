package com.yihuacomputer.fish.api.atmMove;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * 移机服务
 * @author xuxiang
 *
 */
public interface IAtmMoveService
{
    /**
     * @return
     */
    public IAtmMove make();

    /**
     * @param id
     * @return
     */
    public IAtmMove get(long id);

    /**
     * @param atmMove
     * @return
     */
    public IAtmMove add(IAtmMove atmMove);

    /**
     * @param id
     */
    public void remove(long id);

    /**
     * @param atmMove
     */
    public void update(IAtmMove atmMove);

    /**
     * @return
     */
    public Iterable<IAtmMove> list();

    /**
     * @param offset
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<IAtmMove> page(int offset, int limit,IFilter filter);

    /**
     * @param filter
     * @return
     */
    public Iterable<IAtmMove> list(IFilter filter);
}
