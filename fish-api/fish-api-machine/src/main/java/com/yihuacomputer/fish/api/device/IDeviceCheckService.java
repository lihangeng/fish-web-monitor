package com.yihuacomputer.fish.api.device;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IDeviceCheckService
{
    /**
     * 获取需要审核设备的分页信息
     *
     * @param offset
     *            开始
     * @param limit
     *            结束
     * @param filter
     *            过滤
     * @return　分页信息　
     */
    public IPageResult<IDevice> pageCheck(int offset, int limit, IFilter filter, String orgId,long userId);


    /**
     * 获取审核通过的设备分页信息
     *
     * @param offset
     *            开始
     * @param limit
     *            结束
     * @param filter
     *            过滤
     * @return　分页信息　
     */
    public IPageResult<IDevice> pageCheckYes(int offset, int limit, IFilter filter, String orgId);


    /**
     * 获取审核未通过的设备分页信息
     *
     * @param offset
     *            开始
     * @param limit
     *            结束
     * @param filter
     *            过滤
     * @return　分页信息　
     */
    public IPageResult<IDevice> pageCheckNo(int offset, int limit, IFilter filter, String orgId);




}
