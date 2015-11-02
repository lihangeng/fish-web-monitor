package com.yihuacomputer.fish.report.service.db;

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
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.report.IStartPlan;
import com.yihuacomputer.fish.api.report.IStartPlanDeviceRelation;
import com.yihuacomputer.fish.report.base.entity.StartPlanDeviceObj;

@Service
@Transactional
public class StartPlanDeviceRelation implements IStartPlanDeviceRelation{

	@Autowired
	private IGenericDao dao;

	@Autowired
	private IOrganizationService orgService;

	/**
	 * 将设备和开机方案进行关联：
	 */
	@Override
	public void link(IStartPlan startPlan, IDevice device) {
		dao.save(StartPlanDeviceObj.make(startPlan.getId(), device.getId()));
	}

	/**
	 * 设备和开机方案进行解除关联关系：
	 */
	@Override
	public void unlink(IStartPlan startPlan, IDevice device) {
		Filter filter = new Filter();
		filter.addFilterEntry(FilterFactory.eq("planId", startPlan.getId()));
		filter.addFilterEntry(FilterFactory.eq("deviceId", device.getId()));
		StartPlanDeviceObj obj = dao.findUniqueByFilter(filter,
				StartPlanDeviceObj.class);
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
	public IPageResult<IDevice> pageUnlinkDeviceByPlan(int offset, int limit,
			IStartPlan startPlan, IFilter filter, String orgId) {
		StringBuffer hql = new StringBuffer();
		IOrganization org = orgService.get(orgId);
		List<Object> valueObj = new ArrayList<Object>();
		IFilterEntry startCashboxLimit = filter
				.getFilterEntry("startCashboxLimit");
		IFilterEntry endCashboxLimit = filter.getFilterEntry("endCashboxLimit");
		IFilterEntry startInstallDate = filter
				.getFilterEntry("startInstallDate");
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
		hql.append("(select distinct startPlanDeviceObj.deviceId from StartPlan startPlan,StartPlanDeviceObj startPlanDeviceObj where startPlan.id = startPlanDeviceObj.planId and startPlan.id = ?) ");
		valueObj.add(startPlan.getId());
		hql.append(" and device.devType.devCatalog.no != '01' ");
		hql.append(" and device.devType.devCatalog.no != '05' ");
		hql.append(" and device.devType.devCatalog.no != '08' ");
		hql.append("and device.organization.orgFlag like ? ");
		valueObj.add("%" + org.getOrgFlag());
		if (startCashboxLimit != null) {
			hql.append("and device.cashboxLimit>=? ");
			valueObj.add(startCashboxLimit.getValue());
		}
		if (endCashboxLimit != null) {
			hql.append("and device.cashboxLimit<=? ");
			valueObj.add(endCashboxLimit.getValue());
		}
		if (startInstallDate != null) {
			hql.append("and device.deviceExtend.installDate>=? ");
			valueObj.add(startInstallDate.getValue());
		}
		if (endInstallDate != null) {
			hql.append("and device.deviceExtend.installDate<=? ");
			valueObj.add(endInstallDate.getValue());
		}
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
		if (devServiceId != null) {
			IOrganization devService = orgService.get(String
					.valueOf(devServiceId.getValue()));
			hql.append("and device.devService.orgFlag like ? ");
			valueObj.add("%" + devService.getOrgFlag());
		}
		if (organizationId != null) {
			IOrganization organization = orgService.get(String
					.valueOf(organizationId.getValue()));
			hql.append("and device.organization.orgFlag like ? ");
			valueObj.add("%" + organization.getOrgFlag());
		}
		if (ip != null) {
			hql.append("and device.ip=? ");
			valueObj.add(ip.getValue());
		}
		if (awayFlag != null) {
			hql.append("and device.awayFlag=? ");
			valueObj.add(awayFlag.getValue());
		}
		if (terminalId != null) {
			hql.append("and device.terminalId like ? ");
			valueObj.add("%" + terminalId.getValue() + "%");
		}
		if (address != null) {
			hql.append("and device.address like ? ");
			valueObj.add("%" + address.getValue() + "%");
		}       
		IPageResult<IDevice> result = (IPageResult<IDevice>) this.dao.page(
				offset, limit, hql.toString(), valueObj.toArray());
		return result;
	}

	/**
	 * 分页显示获得条件下的该开机方案关联的设备：
	 */
	@Override
	public IPageResult<IDevice> pageDeviceByPlan(int offset, int limit,
			IStartPlan startPlan, IFilter filter, String orgId) {
		StringBuffer hql = new StringBuffer();
		IOrganization org = orgService.get(orgId);

		List<Object> valueObj = new ArrayList<Object>();
		IFilterEntry startCashboxLimit = filter
				.getFilterEntry("startCashboxLimit");
		IFilterEntry endCashboxLimit = filter.getFilterEntry("endCashboxLimit");
		IFilterEntry startInstallDate = filter
				.getFilterEntry("startInstallDate");
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
		hql.append("select device from Device device ,StartPlanDeviceObj startPlanDeviceObj ");
		hql.append("where device.id = startPlanDeviceObj.deviceId and startPlanDeviceObj.planId = ? and device.organization.orgFlag like ? ");
		valueObj.add(startPlan.getId());
		valueObj.add("%" + org.getOrgFlag());
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
			IOrganization devService = orgService.get(String
					.valueOf(devServiceId.getValue()));
			hql.append("and device.devService.orgFlag like ? ");
			valueObj.add("%" + devService.getOrgFlag());
		}
		if (organizationId != null) {
			IOrganization organization = orgService.get(String
					.valueOf(organizationId.getValue()));
			hql.append("and device.organization.orgFlag like ? ");
			valueObj.add("%" + organization.getOrgFlag());
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
		IPageResult<IDevice> result = (IPageResult<IDevice>) this.dao.page(
				offset, limit, hql.toString(), valueObj.toArray());
		return result;
	}

	/**
	 * 获得该设备相关的开机方案：
	 */
	@Override
	public List<IStartPlan> listPlanByDevice(IDevice device) {
		StringBuffer hql = new StringBuffer();
		hql.append("select t from StartPlan t ,StartPlanDeviceObj t1 ");
		hql.append("where t.id = t1.planId and t1.deviceId = ?");
		List<IStartPlan> startPlans = dao.findByHQL(hql.toString(),
				device.getId());
		return startPlans;
	}

}
