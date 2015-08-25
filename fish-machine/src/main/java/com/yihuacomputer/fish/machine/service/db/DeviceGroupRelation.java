package com.yihuacomputer.fish.machine.service.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.atm.IAtmGroup;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.relation.IDeviceGroupRelation;
import com.yihuacomputer.fish.machine.entity.DeviceGroupObj;

/**
 * 设备和分组关联的服务：
 * @author huxiaobao
 *
 */
@Service
@Transactional
public class DeviceGroupRelation implements IDeviceGroupRelation
{

    @Autowired
    private IGenericDao dao;

    @Autowired
	private IOrganizationService orgService;

    /**
     * 将设备和分组进行关联：
     */
    @Override
    public void link(IAtmGroup atmGroup, IDevice device)
    {
        dao.save(DeviceGroupObj.make(atmGroup.getId(), device.getId()));
    }

    /**
     * 设备和分组进行解除关联关系：
     */
    @Override
    public void unlink(IAtmGroup atmGroup, IDevice device)
    {
        Filter filter = new Filter();
        filter.addFilterEntry(FilterFactory.eq("groupId", atmGroup.getId()));
        filter.addFilterEntry(FilterFactory.eq("deviceId", device.getId()));
        DeviceGroupObj obj = dao.findUniqueByFilter(filter, DeviceGroupObj.class);
        if(obj!=null){
            dao.delete(obj);
        }
    }


    /**
     * 获得该分组无关联的设备列表：
     * @param person
     * @param filter
     * @return
     */
    @SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
    public IPageResult<IDevice> pageUnlinkDeviceByGroup(int offset, int limit,IAtmGroup atmGroup,IFilter filter,String orgId,boolean flag){

    	StringBuffer hql = new StringBuffer();
    	IOrganization org = orgService.get(orgId);
    	List<Object> valueObj = new ArrayList<Object>();
    	IFilterEntry startCashboxLimit = filter.getFilterEntry("startCashboxLimit");
    	IFilterEntry endCashboxLimit = filter.getFilterEntry("endCashboxLimit");
    	IFilterEntry startInstallDate = filter.getFilterEntry("startInstallDate");
    	IFilterEntry endInstallDate = filter.getFilterEntry("endInstallDate");
    	IFilterEntry devType = filter.getFilterEntry("devType");
    	IFilterEntry devCatalogId = filter.getFilterEntry("devCatalogId");
    	IFilterEntry devVendorId = filter.getFilterEntry("devVendorId");
    	IFilterEntry devServiceId = filter.getFilterEntry("devService");
    	IFilterEntry organizationId = filter.getFilterEntry("organization");
    	IFilterEntry ip = filter.getFilterEntry("ip");
    	IFilterEntry awayFlag = filter.getFilterEntry("awayFlag");
    	IFilterEntry terminalId = filter.getFilterEntry("terminalId");
    	IFilterEntry address = filter.getFilterEntry("address");
    	hql.append("from Device device where device.id not in ");
        hql.append("(select distinct deviceGroupObj.deviceId from AtmGroup atmGroup,DeviceGroupObj deviceGroupObj where atmGroup.id = deviceGroupObj.groupId and atmGroup.id = ?) ");
        valueObj.add(atmGroup.getId());
    	//flag ==true 为银行人员登录
    	if(flag){
    		hql.append("and device.organization.orgFlag like ? ");
    	}else{
    		hql.append("and device.devService.orgFlag like ? ");
    	}
		valueObj.add("%"+org.getOrgFlag());
		if(startCashboxLimit!=null){
			hql.append("and device.cashboxLimit>=? ");
			valueObj.add(startCashboxLimit.getValue());
		}
		if(endCashboxLimit!=null){
			hql.append("and device.cashboxLimit<=? ");
			valueObj.add(endCashboxLimit.getValue());
		}
		if(startInstallDate!=null){
			hql.append("and device.deviceExtend.installDate>=? ");
			valueObj.add(startInstallDate.getValue());
		}
		if(endInstallDate!=null){
			hql.append("and device.deviceExtend.installDate<=? ");
			valueObj.add(endInstallDate.getValue());
		}
		if(devType!=null){
			hql.append("and device.devType.id=? ");
			valueObj.add(devType.getValue());
		}
		if(devCatalogId!=null){
			hql.append("and device.devType.devCatalog.id=? ");
			valueObj.add(devCatalogId.getValue());
		}
		if(devVendorId!=null){
			hql.append("and device.devType.devVendor.id=? ");
			valueObj.add(devVendorId.getValue());
		}
		if(devServiceId!=null){
			IOrganization devService = orgService.get(String.valueOf(devServiceId.getValue()));
			hql.append("and device.devService.orgFlag like ? ");
			valueObj.add("%"+devService.getOrgFlag());
		}
		if(organizationId!=null){
			IOrganization organization = orgService.get(String.valueOf(organizationId.getValue()));
			hql.append("and device.organization.orgFlag like ? ");
			valueObj.add("%"+organization.getOrgFlag());
		}
		if(ip!=null){
			hql.append("and device.ip=? ");
			valueObj.add(ip.getValue());
		}
		if(awayFlag!=null){
			hql.append("and device.awayFlag=? ");
			valueObj.add(awayFlag.getValue());
		}
		if(terminalId!=null){
			hql.append("and device.terminalId like ? ");
			valueObj.add("%"+terminalId.getValue()+"%");
		}
		if(address!=null){
			hql.append("and device.address like ? ");
			valueObj.add("%"+address.getValue()+"%");
		}
		hql.append(" order by device.id desc ");
		IPageResult<IDevice> result = (IPageResult<IDevice>)this.dao.page(offset,limit,hql.toString(), valueObj.toArray());
    	return result;
    }

    /**
     * 分页显示获得条件下的该分组关联的设备：
     */
    @SuppressWarnings("unchecked")
	@Override
    @Transactional(readOnly = true)
    public IPageResult<IDevice> pageDeviceByTypeGroup(int offset, int limit,
    		IAtmGroup atmGroup, IFilter filter ,String orgId,boolean flag)
    {
    	StringBuffer hql = new StringBuffer();
    	IOrganization org = orgService.get(orgId);

    	List<Object> valueObj = new ArrayList<Object>();
    	IFilterEntry startCashboxLimit = filter.getFilterEntry("startCashboxLimit");
    	IFilterEntry endCashboxLimit = filter.getFilterEntry("endCashboxLimit");
    	IFilterEntry startInstallDate = filter.getFilterEntry("startInstallDate");
    	IFilterEntry endInstallDate = filter.getFilterEntry("endInstallDate");
    	IFilterEntry devType = filter.getFilterEntry("devType");
    	IFilterEntry devCatalogId = filter.getFilterEntry("devCatalogId");
    	IFilterEntry devVendorId = filter.getFilterEntry("devVendorId");
    	IFilterEntry devServiceId = filter.getFilterEntry("devService");
    	IFilterEntry organizationId = filter.getFilterEntry("organization");
    	IFilterEntry ip = filter.getFilterEntry("ip");
    	IFilterEntry awayFlag = filter.getFilterEntry("awayFlag");
    	IFilterEntry terminalId = filter.getFilterEntry("terminalId");
    	IFilterEntry address = filter.getFilterEntry("address");
    	hql.append("select device from Device device ,DeviceGroupObj deviceGroupObj ");
    	//flag ==true 时，银行人员登录查询,否则为维护商登录
    	if(flag){
    		hql.append("where device.id = deviceGroupObj.deviceId and deviceGroupObj.groupId = ? and device.organization.orgFlag like ? ");
    	}else{
    		hql.append("where device.id = deviceGroupObj.deviceId and deviceGroupObj.groupId = ? and device.devService.orgFlag like ? ");
    	}
    	valueObj.add(atmGroup.getId());
		valueObj.add("%" + org.getOrgFlag());
		if(startCashboxLimit!=null){
			hql.append(" and device.cashboxLimit>=?");
			valueObj.add(startCashboxLimit.getValue());
		}
		if(endCashboxLimit!=null){
			hql.append(" and device.cashboxLimit<=?");
			valueObj.add(endCashboxLimit.getValue());
		}
		if(startInstallDate!=null){
			hql.append(" and device.deviceExtend.installDate>=?");
			valueObj.add(startInstallDate.getValue());
		}
		if(endInstallDate!=null){
			hql.append(" and device.deviceExtend.installDate<=?");
			valueObj.add(endInstallDate.getValue());
		}
		if(devType!=null){
			hql.append("and device.devType.id=? ");
			valueObj.add(devType.getValue());
		}
		if(devCatalogId!=null){
			hql.append(" and device.devType.devCatalog.id=?");
			valueObj.add(devCatalogId.getValue());
		}
		if(devVendorId!=null){
			hql.append(" and device.devType.devVendor.id=?");
			valueObj.add(devVendorId.getValue());
		}
		if(devServiceId!=null){
			IOrganization devService = orgService.get(String.valueOf(devServiceId.getValue()));
			hql.append("and device.devService.orgFlag like ? ");
			valueObj.add("%"+devService.getOrgFlag());
		}
		if(organizationId!=null){
			IOrganization organization = orgService.get(String.valueOf(organizationId.getValue()));
			hql.append("and device.organization.orgFlag like ? ");
			valueObj.add("%"+organization.getOrgFlag());
		}
		if(ip!=null){
			hql.append(" and device.ip=?");
			valueObj.add(ip.getValue());
		}
		if(awayFlag!=null){
			hql.append(" and device.awayFlag=?");
			valueObj.add(awayFlag.getValue());
		}
		if(terminalId!=null){
			hql.append(" and device.terminalId like?");
			valueObj.add("%"+terminalId.getValue()+"%");
		}
		if(address!=null){
			hql.append(" and device.address like?");
			valueObj.add("%"+address.getValue()+"%");
		}
		hql.append(" order by device.id desc ");
		IPageResult<IDevice> result = (IPageResult<IDevice>)this.dao.page(offset,limit,hql.toString(), valueObj.toArray());
    	return result;
    }

    /**
     * 获得该设备相关的分组：
     */
    @Override
    @Transactional(readOnly = true)
    public List<IAtmGroup> listGroupByDevice(IDevice device)
    {
        StringBuffer hql = new StringBuffer();
        hql.append("select t from AtmGroup t ,DeviceGroupObj t1 ");
        hql.append("where t.id = t1.groupId and t1.deviceId = ?");
        List<IAtmGroup> atmGroups = dao.findByHQL(hql.toString(), device.getId());
        return atmGroups;
    }

}
