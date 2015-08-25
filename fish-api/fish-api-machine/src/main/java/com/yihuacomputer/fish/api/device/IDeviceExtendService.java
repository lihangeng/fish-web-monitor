package com.yihuacomputer.fish.api.device;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2012-2-22 下午04:04:29
 */
public interface IDeviceExtendService
{
    public IDeviceExtend make();

    public IDeviceExtend get(long id);

    public IDeviceExtend get(String code);

    public IDeviceExtend add(IDeviceExtend deviceExtend);

    public void remove(long id);

    public void remove(String code);

    public void update(IDeviceExtend deviceExtend);

    public Iterable<IDeviceExtend> list();

    public IPageResult<IDeviceExtend> page(int offset, int limit, IFilter filter);

    public Iterable<IDeviceExtend> list(IFilter filter);
}
