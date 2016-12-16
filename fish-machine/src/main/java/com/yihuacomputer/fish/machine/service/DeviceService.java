package com.yihuacomputer.fish.machine.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.ServiceException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceListener;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.quittingNotice.IQuittingNotice;
import com.yihuacomputer.fish.api.quittingNotice.IQuittingNoticeService;
import com.yihuacomputer.fish.api.relation.IDevicePersonRelation;
import com.yihuacomputer.fish.machine.entity.Device;

/**
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2012-2-22 下午03:11:18
 */
@Service
@Transactional
public class DeviceService implements IDeviceService {
	@Autowired
	private IGenericDao dao;

	@Autowired
	private IOrganizationService orgService;

	@Autowired
	private IDevicePersonRelation devicePersonRelation;

	@Autowired
	private IQuittingNoticeService quittingNoticeService;
	
	
	@Autowired
	private MessageSource messageSource;

	public List<IDeviceListener> deviceListeners = new ArrayList<IDeviceListener>();

	public Device make() {
		return new Device(this);
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = "devices", key = "#id")
	public IDevice get(long id) {
		return dao.get(id, Device.class);
	}

	@Override
	@Transactional(readOnly = true)
	public IDevice get(String terminalId) {
		IDevice device = (IDevice) dao.getCriteria(Device.class).add(Restrictions.eq("terminalId", terminalId)).uniqueResult();
		return device;
	}

	@Override
	public IDevice add(IDevice device) {
		for (IDeviceListener each : this.deviceListeners) {
			each.beforeAdd(device);
		}

		IDevice entity = dao.save(device);

		for (IDeviceListener each : this.deviceListeners) {
			each.afterAdd(device);
		}
		return entity;
	}

	@Override
	@CacheEvict(value = "devices", key = "#id")
	public void remove(long id) {
		IDevice device = get(id);
		if (device == null) {
			return;
		}
		remove(device);
	}

	public void remove(IDevice device) {
		for (IDeviceListener each : this.deviceListeners) {
			each.beforeDelete(device);
		}

		if (device.getStatus() == DevStatus.OPEN) {
			// 设备启用时，不能删除
			throw new ServiceException(messageSource.getMessage("exception.device.alreadyUsed", null, FishCfg.locale));
		}

		List<IPerson> personList = devicePersonRelation.listPersonByDevice(device.getTerminalId());

		// 是否与人员有关联
		if (personList == null || personList.isEmpty()) {
			IFilter filter = new Filter();
			// 是否有停机记录没有删除
			filter.eq("deviceCode", device.getTerminalId());
			List<IQuittingNotice> list = (List<IQuittingNotice>) quittingNoticeService.list(filter);
			// 先删除运行参数

			dao.delete(device);

			for (IQuittingNotice quitNotice : list) {
				quittingNoticeService.remove(quitNotice.getId());
			}

		} else {
			throw new ServiceException(messageSource.getMessage("exception.device.alreadyBind", null, FishCfg.locale));
		}

		for (IDeviceListener each : this.deviceListeners) {
			each.afterDelete(device);
		}
	}

	@Override
	public void update(IDevice device) {
		for (IDeviceListener each : this.deviceListeners) {
			each.beforeUpdate(device);
		}

		dao.update(device);

		for (IDeviceListener each : this.deviceListeners) {
			each.afterUpdate(device);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<IDevice> list() {
		return list(new Filter());
	}

	@Override
	@Transactional(readOnly = true)
	public List<IDevice> list(IFilter filter) {
		List<Device> deviceList = dao.findByFilter(filter, Device.class);
		return EntityUtils.<IDevice> convert(deviceList);
	}

	@Override
	@Transactional(readOnly = true)
	public List<IDevice> orgList(IFilter filter, long id) {

		IOrganization org = orgService.get(String.valueOf(id));
		filter.like("organization.orgFlag",org.getOrgFlag()  + "%");

		return list(filter);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public IPageResult<IDevice> page(int offset, int limit, IFilter filter, String orgId) {
		StringBuffer hql = new StringBuffer();
		List<Object> fixedFilters = new ArrayList<Object>();
		hql.append("from Device device where 1=1 ");

		if (orgId != null && !"".equals(orgId)) {
			hql.append(" and device.organization.orgFlag like ? ");
			IOrganization org = orgService.get(orgId);
			fixedFilters.add(org.getOrgFlag() + "%");
		}
		hql.append(" order by device.id desc ");
		return (IPageResult<IDevice>) dao.page(offset, limit, filter, hql.toString(), fixedFilters.toArray());
	}

	ComplexDeviceService s;

	@Override
	@Transactional(readOnly = true)
	public IPageResult<IDevice> page(int offset, int limit, IFilter filter) {
		return page(offset, limit, filter, null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<IDevice> listDeviceByType(IAtmType atmType) {
		return dao.findByHQL("from Device device where device.devType.id = ?", atmType.getId());
	}

	@Override
	@Transactional(readOnly = true)
	public List<IDevice> list(String hql, List<Object> fixedFilters) {
		return dao.findByHQL(hql, fixedFilters.toArray());
	}

	@Override
	public long getOpeningDeviceTotal() {
		Object object = dao.findUniqueByHql("select count(t.id) from Device t where t.status = ?", DevStatus.OPEN);
		return Long.parseLong(object.toString());
	}

	@Override
	public long getOpeningDeviceTotal(IOrganization org) {
		Object object = dao.findUniqueByHql("select count(t.id) from Device t where t.status = ? and t.organization.orgFlag like ?", DevStatus.OPEN,org.getOrgFlag()+  "%" );
		return Long.parseLong(object.toString());
	}

	@Override
	public void addDeviceListener(IDeviceListener deviceListener) {
		this.deviceListeners.add(deviceListener);
	}

	@Override
	public void removeDeviceListener(IDeviceListener deviceListener) {
		this.deviceListeners.remove(deviceListener);
	}

	@Override
	public IDevice getByIp(String ip) {
		// IDevice device = (IDevice)
		// dao.getCriteria(Device.class).add(Restrictions.eq("ip", new IP(ip)))
		// .uniqueResult();
		// return device;

		// 解决初始化ip地址重复报错的问题
		List<IDevice> list = dao.findByHQL("from Device device where device.ip = ?", new IP(ip));
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}

	}

	@Override
	public IDevice getByVirtual(String virtual) {
		IDevice device = (IDevice) dao.getCriteria(Device.class).add(Restrictions.eq("virtual", virtual)).uniqueResult();
		return device;
	}
}
