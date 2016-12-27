package com.yihuacomputer.fish.machine.service;

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
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.openplan.IDeviceOpenPlan;
import com.yihuacomputer.fish.api.openplan.IDevicePlanRelation;
import com.yihuacomputer.fish.api.openplan.IOpenPlanDeviceRelation;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.machine.entity.DevicePlanRelation;

/**
 * @author YiHua
 *
 */
@Service
@Transactional	
public class OpenPlanDeviceRelation implements IOpenPlanDeviceRelation {
	@Autowired
	private IGenericDao dao;

	@Autowired
	private IOrganizationService orgService;

	/**
	 * 将设备和开机方案进行关联：
	 */
	@Override
	public void link(IDeviceOpenPlan openPlan, IDevice device) {
		dao.save(DevicePlanRelation.make(openPlan.getId(), device.getId()));
	}

	/**
	 * 设备和开机方案进行解除关联关系：
	 */
	@Override
	public void unlink(IDeviceOpenPlan openPlan, IDevice device) {
		Filter filter = new Filter();
		filter.addFilterEntry(FilterFactory.eq("openPlanId", openPlan.getId()));
		filter.addFilterEntry(FilterFactory.eq("deviceId", device.getId()));
		DevicePlanRelation obj = dao.findUniqueByFilter(filter, DevicePlanRelation.class);
		if (obj != null) {
			dao.delete(obj);
		}
	}

	/**
	 * 获得该开机方案无关联的设备列表：
	 *
	 * @param startPlan
	 * @param filter
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public IPageResult<IDevice> pageUnlinkDeviceByPlan(int offset, int limit, IDeviceOpenPlan openPlan, IFilter filter, String orgId) {

		StringBuffer hql = new StringBuffer();
		IOrganization org = orgService.get(orgId);
		List<Object> valueObj = new ArrayList<Object>();
		IFilterEntry devType = filter.getFilterEntry("devType");
		IFilterEntry devCatalogId = filter.getFilterEntry("devCatalogId");
		IFilterEntry devVendorId = filter.getFilterEntry("devVendorId");
		IFilterEntry organizationId = filter.getFilterEntry("organization");
		IFilterEntry ip = filter.getFilterEntry("ip");
		IFilterEntry terminalId = filter.getFilterEntry("terminalId");
		hql.append("from Device device where device.id not in ");
		hql.append("(select distinct devicePlanRelation.deviceId from DevicePlanRelation devicePlanRelation ) ");
		hql.append("and device.organization.orgFlag like ? ");
		valueObj.add(org.getOrgFlag() + "%");
		if (devType != null) {
			hql.append("and device.devType.id=? ");
			valueObj.add(devType.getValue());
		}
		if (devCatalogId != null) {
			hql.append("and device.devType.devCatalog.id=? ");
			valueObj.add(devCatalogId.getValue());
		}
		if (devVendorId != null) {
			hql.append("and device.devType.devVendor.id=? ");
			valueObj.add(devVendorId.getValue());
		}
		if (organizationId != null) {
			IOrganization organization = orgService.get(String.valueOf(organizationId.getValue()));
			hql.append("and device.organization.orgFlag like ? ");
			valueObj.add(organization.getOrgFlag() + "%");
		}
		if (ip != null) {
			hql.append("and device.ip=? ");
			valueObj.add(ip.getValue());
		}
		if (terminalId != null) {
			hql.append("and device.terminalId like ? ");
			valueObj.add("%" + terminalId.getValue() + "%");
		}
		IPageResult<IDevice> result = (IPageResult<IDevice>) this.dao.page(offset, limit, hql.toString(), valueObj.toArray());
		return result;
	}

	/**
	 * 分页显示获得条件下的该开机方案关联的设备：
	 */
	@Override
	@SuppressWarnings("unchecked")
	public IPageResult<IDevice> pageDeviceByPlan(int offset, int limit, IDeviceOpenPlan openPlan, IFilter filter, String orgId) {
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
		hql.append("select device from Device device ,DevicePlanRelation devicePlanRelation ");
		hql.append("where device.id = devicePlanRelation.deviceId and devicePlanRelation.openPlanId = ? and device.organization.orgFlag like ? ");
		valueObj.add(openPlan.getId());
		valueObj.add(org.getOrgFlag() + "%");
		if (startCashboxLimit != null) {
			hql.append(" and device.cashboxLimit>=?");
			valueObj.add(startCashboxLimit.getValue());
		}
		if (endCashboxLimit != null) {
			hql.append(" and device.cashboxLimit<=?");
			valueObj.add(endCashboxLimit.getValue());
		}
		if (startInstallDate != null) {
			hql.append(" and device.deviceExtend.installDate>=?");
			valueObj.add(startInstallDate.getValue());
		}
		if (endInstallDate != null) {
			hql.append(" and device.deviceExtend.installDate<=?");
			valueObj.add(endInstallDate.getValue());
		}
		if (devType != null) {
			hql.append("and device.devType.id=? ");
			valueObj.add(devType.getValue());
		}
		if (devCatalogId != null) {
			hql.append(" and device.devType.devCatalog.id=?");
			valueObj.add(devCatalogId.getValue());
		}
		if (devVendorId != null) {
			hql.append(" and device.devType.devVendor.id=?");
			valueObj.add(devVendorId.getValue());
		}
		if (devServiceId != null) {
			IOrganization devService = orgService.get(String.valueOf(devServiceId.getValue()));
			hql.append("and device.devService.orgFlag like ? ");
			valueObj.add(devService.getOrgFlag() + "%");
		}
		if (organizationId != null) {
			IOrganization organization = orgService.get(String.valueOf(organizationId.getValue()));
			hql.append("and device.organization.orgFlag like ? ");
			valueObj.add(organization.getOrgFlag() + "%");
		}
		if (ip != null) {
			hql.append(" and device.ip=?");
			valueObj.add(ip.getValue());
		}
		if (awayFlag != null) {
			hql.append(" and device.awayFlag=?");
			valueObj.add(awayFlag.getValue());
		}
		if (terminalId != null) {
			hql.append(" and device.terminalId like?");
			valueObj.add("%" + terminalId.getValue() + "%");
		}
		if (address != null) {
			hql.append(" and device.address like?");
			valueObj.add("%" + address.getValue() + "%");
		}
		IPageResult<IDevice> result = (IPageResult<IDevice>) this.dao.page(offset, limit, hql.toString(), valueObj.toArray());
		return result;
	}

	/**
	 * 获得该设备相关的开机方案：
	 */
	@Override
	public List<IDeviceOpenPlan> listPlanByDevice(IDevice device) {
		StringBuffer hql = new StringBuffer();
		hql.append("select t from DeviceOpenPlan t ,DevicePlanRelation t1 ");
		hql.append("where t.id = t1.openPlanId and t1.deviceId = ?");
		List<IDeviceOpenPlan> startPlans = dao.findByHQL(hql.toString(), device.getId());
		return startPlans;
	}

	@Override
	public boolean isExistPlanLink(IFilter filter) {
		return dao.findByFilter(filter, DevicePlanRelation.class).size() > 0;
	}
	/**
	 * 得到已经被关联的设备号
	 * @param DeviceCode
	 * @param orgId
	 * @return
	 */
	@Override
   public List<IDevicePlanRelation> devicePlanRelations()
   {
	   StringBuffer  hql = new StringBuffer();
	   hql.append("select devicePlanRelation from DevicePlanRelation devicePlanRelation ");
	   List<IDevicePlanRelation> devicePlanRelation = dao.findByHQL(hql.toString());
	   return devicePlanRelation;
   }
   /**
    *
    * @param deviceCode
    * @param orgId
    * @return
    */
	@Override
   public List<IDevice> getDevicebyCode(String deviceCode,String orgId)
   {
	   IOrganization org = orgService.get(orgId);
	   StringBuffer hql = new StringBuffer();
	   List<Object> valueObj = new ArrayList<Object>();
	  
	   hql.append("select device from Device device where device.terminalId = ? and device.organization.orgFlag like ?  ");
	   valueObj.add(deviceCode);
	   valueObj.add( org.getOrgFlag() +"%");
		hql.append(" and device.devType.devCatalog.no != '01' ");
		hql.append(" and device.devType.devCatalog.no != '05' ");
		hql.append(" and device.devType.devCatalog.no != '08' ");
		 List<IDevice> device = dao.findByHQL(hql.toString(), valueObj.toArray());
	   return device;

   }


}
