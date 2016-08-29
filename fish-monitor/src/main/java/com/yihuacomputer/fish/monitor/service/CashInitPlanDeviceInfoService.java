package com.yihuacomputer.fish.monitor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfo;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfoService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.monitor.entity.box.DeviceBoxInfo;
import com.yihuacomputer.fish.monitor.entity.cashplan.CashInitPlanDeviceInfo;
@Service
@Transactional
public class CashInitPlanDeviceInfoService implements ICashInitPlanDeviceInfoService {

	@Autowired
	private IGenericDao dao;
	@Autowired
	private IOrganizationService orgService;
	
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

	@Override
	public List<ICashInitPlanDeviceInfo> list(IFilter filter) {
		return dao.findByFilter(filter, ICashInitPlanDeviceInfo.class);
	}
	
	public List<Object> listPage(IFilter filter){
		StringBuilder sb = new StringBuilder();
		sb.append("select cashInitPlanDevice,deviceBoxInfo from ").append(CashInitPlanDeviceInfo.class.getSimpleName()).append(" cashInitPlanDevice ,")
		.append(DeviceBoxInfo.class.getSimpleName()).append(" deviceBoxInfo where deviceBoxInfo.deviceId.terminalId = cashInitPlanDevice.terminalId ");
		Object terminalId = filter.getValue("terminalId");
		Object orgId = filter.getValue("orgId");
		Object awayflag = filter.getValue("awayflag");
		Object devType = filter.getValue("devType");
		Object planId = filter.getValue("cashInitPlanInfo.id");
		List<Object> list = new ArrayList<Object>();
		if(planId!=null){
			sb.append(" and cashInitPlanDevice.cashInitPlanInfo.id=? ");
			list.add(Long.parseLong(String.valueOf(planId)));
		}
		if(terminalId!=null){
			sb.append(" and cashInitPlanDevice.terminalId=? ");
			list.add(terminalId.toString());
		}
		if(awayflag!=null){
			sb.append(" and deviceBoxInfo.deviceId.awayFlag=? ");
			list.add(awayflag.toString());
		}
		if(devType!=null){
			sb.append(" and deviceBoxInfo.deviceId.devType.id=? ");
			list.add(Long.parseLong(devType.toString()));
		}
		if(orgId!=null){
			sb.append(" and deviceBoxInfo.deviceId.organization.orgFlag like ? ");
			IOrganization org = orgService.get(String.valueOf(orgId));
			list.add(org.getOrgFlag()+"%");
		}
		return dao.findByHQL(sb.toString(), list.toArray());
	}

}
