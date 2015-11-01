package com.yihuacomputer.fish.api.openplan;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.device.IDevice;

/**
 * 开机方案与设备关联实体接口
 * @author huxiaobao
 *
 */

public interface IOpenPlanDeviceRelation {
    /**
     * 开机方案和设备建立关系
     *
     * @param person
     * @param device
     */
    public void link(IDeviceOpenPlan openPlan, IDevice device);

    /**
     * 开机方案和设备解除关系
     *
     * @param person
     * @param device
     */
    public void unlink(IDeviceOpenPlan openPlan, IDevice device);

    /**
     * 根据开机方案获得没有关联的设备
     *
     * @param offset
     * @param limit
     * @param person
     * @param filter
     * @return
     */
    public IPageResult<IDevice> pageUnlinkDeviceByPlan(int offset, int limit,
            IDeviceOpenPlan openPlan, IFilter filter, String orgId);

    /**
     * 根据某开机方案的所有关联设备分页
     */
    public IPageResult<IDevice> pageDeviceByPlan(int offset, int limit,
            IDeviceOpenPlan openPlan, IFilter filter, String orgId);

    /**
     * 根据设备获得该设备的关联开机方案
     *
     * @param device
     * @return
     */
    public List<IDeviceOpenPlan> listPlanByDevice(IDevice device);


    public boolean isExistPlanLink(IFilter filter);

	public List<IDevicePlanRelation> devicePlanRelations();

	public List<IDevice> getDevicebyCode(String deviceCode, String orgId);
}
