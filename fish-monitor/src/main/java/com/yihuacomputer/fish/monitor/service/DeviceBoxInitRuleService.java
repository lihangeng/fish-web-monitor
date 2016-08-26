package com.yihuacomputer.fish.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.box.BoxInitRuleType;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInitRule;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInitRuleService;
import com.yihuacomputer.fish.monitor.entity.box.DeviceBoxInitRule;

@Service
@Transactional
public class DeviceBoxInitRuleService implements IDeviceBoxInitRuleService {

	@Autowired
	private IGenericDao dao;
	@Override
	public IPageResult<IDeviceBoxInitRule> page(int start, int limit, IFilter filter) {
		return dao.page(start, limit, filter, DeviceBoxInitRule.class);
	}
	@Override
	public IDeviceBoxInitRule update(IDeviceBoxInitRule deviceBoxInitRule) {
		return dao.update(deviceBoxInitRule);
	}
	@Override
	public IDeviceBoxInitRule get(long id) {
		return dao.get(id, DeviceBoxInitRule.class);
	}

	public IDeviceBoxInitRule get(BoxInitRuleType ruleType){
		IFilter filter  = new Filter();
		filter.eq("ruleType", ruleType);
		return dao.findUniqueByFilter(filter, IDeviceBoxInitRule.class);
	}
}
