package com.yihuacomputer.fish.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfo;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfoService;
import com.yihuacomputer.fish.monitor.entity.cashplan.CashInitPlanDeviceInfo;
@Service
@Transactional
public class CashInitPlanDeviceInfoService implements ICashInitPlanDeviceInfoService {

	@Autowired
	private IGenericDao dao;
	
	@Override
	public ICashInitPlanDeviceInfo make() {
		return new CashInitPlanDeviceInfo();
	}

	@Override
	public ICashInitPlanDeviceInfo save(ICashInitPlanDeviceInfo cashInitPlanInfo) {
		return dao.save(cashInitPlanInfo);
	}

	@Override
	public ICashInitPlanDeviceInfo update(ICashInitPlanDeviceInfo cashInitPlanInfo) {
		return dao.update(cashInitPlanInfo);
	}

	@Override
	public void remove(ICashInitPlanDeviceInfo cashInitPlanInfo) {
		dao.delete(cashInitPlanInfo);
	}

	@Override
	public ICashInitPlanDeviceInfo get(long id) {
		return dao.get(id, CashInitPlanDeviceInfo.class);
	}

}
