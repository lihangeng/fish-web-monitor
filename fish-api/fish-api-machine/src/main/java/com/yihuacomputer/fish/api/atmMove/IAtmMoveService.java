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
    public IAtmMove make();

    public IAtmMove get(long id);

    public IAtmMove add(IAtmMove atmMove);

    public void remove(long id);

    public void update(IAtmMove atmMove);

    public Iterable<IAtmMove> list();

    public IPageResult<IAtmMove> page(int offset, int limit,IFilter filter);

    public Iterable<IAtmMove> list(IFilter filter);
}
