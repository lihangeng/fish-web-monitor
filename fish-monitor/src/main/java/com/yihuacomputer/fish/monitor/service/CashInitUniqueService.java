package com.yihuacomputer.fish.monitor.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.box.ICashInitUnique;
import com.yihuacomputer.fish.api.monitor.box.ICashInitUniqueService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.machine.entity.Device;
import com.yihuacomputer.fish.monitor.entity.cashplan.CashInitUnique;

@Service
@Transactional
public class CashInitUniqueService implements ICashInitUniqueService {

	@Autowired
	private IGenericDao dao;
	
	@Override
	public ICashInitUnique make() {
		return new CashInitUnique();
	}

	@Override
	public ICashInitUnique save(ICashInitUnique cashInitUnique) {
		return dao.save(cashInitUnique);
	}

	@Override
	public ICashInitUnique update(ICashInitUnique cashInitUnique) {
		return dao.update(cashInitUnique);
	}

	@Override
	public void remove(ICashInitUnique cashInitUnique) {
		dao.delete(cashInitUnique);
	}

	@Override
	public List<ICashInitUnique> list(IFilter filter) {
		return dao.findByFilter(filter, ICashInitUnique.class);
	}

	@Override
	public ICashInitUnique getByTerminalId(String terminalId) {
		IFilter filter = new Filter();
		filter.eq("terminalId", terminalId);
		return dao.findUniqueByFilter(filter, ICashInitUnique.class);
	}
	
	public List<ICashInitUnique> getCashInitByOrg(IOrganization org,int cashInitDays){
		StringBuilder sb = new StringBuilder();
		String date = DateUtils.getDate(DateUtils.getDate(-cashInitDays));
		sb.append("select cashInitUnique from ").
		append(CashInitUnique.class.getSimpleName()).append(" cashInitUnique,")
		.append(Device.class.getSimpleName()).append(" device ")
		.append( "where device.terminalId = cashInitUnique.terminalId and")
		.append(" cashInitUnique.date<? and device.organization.orgFlag like ?");
		return dao.findByHQL(sb.toString(), new Object[]{date+" 00:0:00",org.getOrgFlag()+"%"});
	}

	/**
	 * 获取指定机构设备的加钞信息集合
	 * @param org
	 * @return
	 */
	public Map<String, ICashInitUnique> getCashInitMap(IOrganization org){
		Map<String, ICashInitUnique> cashInitMap = new HashMap<String, ICashInitUnique>();
		StringBuilder sb = new StringBuilder();
		sb.append("select cashInitUnique from ").
		append(CashInitUnique.class.getSimpleName()).append(" cashInitUnique,")
		.append(Device.class.getSimpleName()).append(" device ")
		.append( "where device.terminalId = cashInitUnique.terminalId and")
		.append("  device.organization.orgFlag like ?");
		List<ICashInitUnique> cashInitList =  dao.findByHQL(sb.toString(), new Object[]{org.getOrgFlag()+"%"});
		for(ICashInitUnique cashInit:cashInitList){
			cashInitMap.put(cashInit.getTerminalId(), cashInit);
		}
		return cashInitMap;
	}
}
