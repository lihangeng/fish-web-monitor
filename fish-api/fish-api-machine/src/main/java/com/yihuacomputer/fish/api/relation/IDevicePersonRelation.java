package com.yihuacomputer.fish.api.relation;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.PersonType;

public interface IDevicePersonRelation
{
   /**
    * 人员和设备建立关系
    * @param person
    * @param device
    */
    public void link(IPerson person, IDevice device);
    
   /**
    * 人员和设备解除关系
    * @param person
    * @param device
    */
    public void unlink(IPerson person, IDevice device);
    
    /**
     * 根据人员获得没有关联的设备
     * @param offset
     * @param limit
     * @param person
     * @param filter
     * @return
     */
    public IPageResult<IDevice> pageUnlinkDeviceByPerson(int offset, int limit, IPerson person,IFilter filter,String bankOrgId,String serOrgId);

   /**
    * 列出某人员的所有关联设备
    * @param person
    * @return
    */
    public List<IDevice> listDeviceByPerson(IPerson person);

    /**
     * 根据某人员的所有关联设备分页
     * @param offset
     * @param limit
     * @param person
     * @param filter
     * @return
     */
    public IPageResult<IDevice> pageDeviceByPerson(int offset, int limit, IPerson person, IFilter filter);
    
    /**
     * 根据某人员的所有关联设备分页
     */
    public IPageResult<IDevice> pageDeviceByTypePerson(int offset, int limit, IPerson person, IFilter filter,String orgId,boolean flag);
    
    /**
     * 根据设备获得该设备的关联人员
     * @param device
     * @return
     */
    public List<IPerson> listPersonByDevice(String terminalId);
    
    /**
     * 根据设备号获得该设备的关联的管机员或者维护人员
     * @param terminalId
     * @param personType
     * @return
     */
    public List<IPerson> listAdminMaintainPersonByDevice(String terminalId,PersonType personType);
}
