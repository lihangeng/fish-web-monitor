package com.yihuacomputer.fish.api.version.relation;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroup;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupDeviceRelation;
import com.yihuacomputer.fish.api.device.IDevice;

/**
 * @author YiHua
 *
 */
public interface IDeviceAdvertRelation
{
   /**
    * 广告组和设备建立关系
    * @param advertGroup
    * @param device
    */
    public void link(IAdvertGroup advertGroup, IDevice device);
    
   /**
    * 广告组和设备解除关系
    * @param advertGroup
    * @param device
    */
    public void unlink(IAdvertGroup advertGroup, IDevice device);
    
    /**
     * 根据广告组获得没有关联的设备
     * @param offset
     * @param limit
     * @param advertGroup
     * @param filter
     * @param bankOrgId
     * @param serOrgId
     * @return
     */
    public IPageResult<IDevice> pageUnlinkDeviceByAdvertGroup(int offset, int limit, IAdvertGroup advertGroup,IFilter filter,String bankOrgId, String serOrgId);

   /**
    * 列出某广告组的所有关联设备
    * @param advertGroup
    * @return
    */
    public List<IDevice> listDeviceByAdvertGroup(IAdvertGroup advertGroup);

    /**
     * 根据某广告组的所有关联设备分页
     * @param offset
     * @param limit
     * @param advertGroup
     * @param filter
     * @return
     */
    public IPageResult<IDevice> pageDeviceByAdvertGroup(int offset, int limit, IAdvertGroup advertGroup, IFilter filter);
    
    /**
     * 根据设备获得该设备的关联广告组
     * @param groupId
     * @return
     */
    public List<IAdvertGroup> listAdvertGroupByGroupId(Long groupId);
    
    
    /**
     * 根据设备获得该设备的关联广告组
     * @param deviceId
     * @return
     */
    public IAdvertGroupDeviceRelation getDeviceRelation(long deviceId);
    
    
    /**
     * 根据设备Id删除广告组关联
     * @param deviceId
     * @return
     */
    public void delete(long deviceId);
    
}
