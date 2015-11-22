package com.yihuacomputer.fish.api.relation;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.atm.IAtmGroup;
import com.yihuacomputer.fish.api.device.IDevice;

public interface IDeviceGroupRelation {
    /**
     * 组和设备建立关系
     * 
     * @param person
     * @param device
     */
    public void link(IAtmGroup atmGroup, IDevice device);

    /**
     * 组和设备解除关系
     * 
     * @param person
     * @param device
     */
    public void unlink(IAtmGroup atmGroup, IDevice device);

    /**
     * 根据组获得没有关联的设备
     * 
     * @param offset
     * @param limit
     * @param person
     * @param filter
     * @return
     */
    public IPageResult<IDevice> pageUnlinkDeviceByGroup(int offset, int limit, IAtmGroup atmGroup, IFilter filter,
            String orgId, boolean flag);

    /**
     * 根据某组的所有关联设备分页
     */
    public IPageResult<IDevice> pageDeviceByTypeGroup(int offset, int limit, IAtmGroup atmGroup, IFilter filter,
            String orgId, boolean flag);

    /**
     * 根据设备获得该设备的关联组
     * 
     * @param device
     * @return
     */
    public List<IAtmGroup> listGroupByDevice(IDevice device);

    /**
     * 根据分组,得到该组内所有符合条件的设备
     * 
     * @param atmGroup
     *            分组
     * @param orgId
     *            机构ID
     * @return
     */
    public List<IDevice> listByDevice(long atmGroupId, String orgId);
}
