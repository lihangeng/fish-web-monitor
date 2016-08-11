package com.yihuacomputer.fish.monitor.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfo;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfoService;
import com.yihuacomputer.fish.monitor.entity.box.DeviceBoxInfo;

@Service
@Transactional
public class DeviceBoxInfoService implements IDeviceBoxInfoService {
	@Autowired
	private IGenericDao dao;
	
	@Override
	public IDeviceBoxInfo make() {
		return new DeviceBoxInfo();
	}

	@Override
	public IDeviceBoxInfo save(IDeviceBoxInfo dbi) {
		return dao.save(interface2Entity(dbi));
	}

	@Override
	public IDeviceBoxInfo update(IDeviceBoxInfo dbi) {
		return dao.update(dbi);
	}

	@Override
	public IDeviceBoxInfo findByDeviceId(long deviceId) {
		return dao.findUniqueByHql(" from DeviceBoxInfo dbi where dbi.deviceId.id=?", deviceId);
	}

	@Override
	public void delete(IDeviceBoxInfo dbdi) {
		 dao.delete(dbdi);
	}
	 private DeviceBoxInfo interface2Entity(IDeviceBoxInfo dbdi) {
	        if (dbdi instanceof DeviceBoxInfo) {
	            return (DeviceBoxInfo) dbdi;
	        }
	        return null;
	    }
	@Override
	public List<IDeviceBoxInfo> list(IFilter filter) {
		return dao.findByFilter(filter, IDeviceBoxInfo.class);
	}
	@SuppressWarnings("unchecked")
	@Override
	public IPageResult<IDeviceBoxInfo> list(int offset,int limit,IFilter filter) {
		
		StringBuffer hql = new StringBuffer();
		List<Object> fixedFilters = new ArrayList<Object>();
		hql.append("from ").append(DeviceBoxInfo.class.getSimpleName()).
		append(" dbi where 1=1 ");
		IFilterEntry filterEntryOrg = filter.getFilterEntry("deviceId.organization.orgFlag");
		if(null!=filterEntryOrg){
			hql.append(" and ").append(filterEntryOrg.getKey()).append(" like ? ");
			fixedFilters.add(filterEntryOrg.getValue() + "%");
		}
		
		return (IPageResult<IDeviceBoxInfo>) dao.page(offset, limit, filter, hql.toString(), fixedFilters.toArray());
	}

	@Override
	public IDeviceBoxInfo get(long id) {
		return dao.get(id, DeviceBoxInfo.class);
	}
	
	@Override
	public boolean synchronizedUpdate(IDeviceBoxInfo deviceBoxInfo, IFilter filter) {
		filter.eq("deviceId.devType.id", deviceBoxInfo.getDeviceId().getDevType().getId());
		StringBuffer hql = new StringBuffer();
		List<Object> fixedFilters1= new ArrayList<Object>();
		hql.append("from ").append(DeviceBoxInfo.class.getSimpleName()).
		append(" dbi where 1=1 ");
		IFilterEntry filterEntryOrg = filter.getFilterEntry("deviceId.organization.orgFlag");
		if(null!=filterEntryOrg){
			hql.append(" and ").append(filterEntryOrg.getKey()).append(" like ? ");
			fixedFilters1.add(filterEntryOrg.getValue() + "%");
		}
		IFilterEntry filterEntrydevType = filter.getFilterEntry("deviceId.devType.id");
		if(null!=filterEntrydevType){
			hql.append(" and ").append(filterEntrydevType.getKey()).append(" = ?");
			fixedFilters1.add(filterEntrydevType.getValue());
		}
		List<IDeviceBoxInfo> list = dao.findByHQL(hql.toString(), fixedFilters1.toArray());
		StringBuffer sb = new StringBuffer();
		sb.append("update ").append(DeviceBoxInfo.class.getSimpleName()).append(" as dbi set ")
		.append(" dbi.maxAlarm=?,dbi.minAlarm=? where 1=1 and dbi.id in (");
		for(int i=0;i<list.size();i++){
			sb.append(list.get(i).getId()).append((i==list.size()-1)?")":",");
		}

		List<Object> fixedFilters = new ArrayList<Object>();
		fixedFilters.add(deviceBoxInfo.getMaxAlarm());
		fixedFilters.add(deviceBoxInfo.getMinAlarm());
		try{
			dao.batchUpdate(sb.toString(), fixedFilters.toArray());
		}catch(Exception e){
			return false;
		}
		return true;
	}
	private HqlQueryStruct getHqlFromFilter(IFilter filter){
		StringBuffer sb = new StringBuffer();
		Set<IFilterEntry> set = filter.entrySet();
		Iterator<IFilterEntry> iterator = set.iterator();
		HqlQueryStruct hqStruct = new HqlQueryStruct();
		while(iterator.hasNext()){
			IFilterEntry entry = iterator.next();
			sb.append(" and dbi.").append(entry.getKey()).append(" ").append(entry.getOperator().getSign())
			.append(" ?");
			hqStruct.addArg(entry.getValue());
		}
		hqStruct.setHql(sb.toString());
		return hqStruct;
	}
}
class HqlQueryStruct{
	private String hql;
	private List<Object> argList = new ArrayList<Object>();
	public String getHql() {
		return hql;
	}
	public void setHql(String hql) {
		this.hql = hql;
	}
	public List<Object> getArgList() {
		return argList;
	}
	public void addArg(Object arg) {
		this.argList.add(arg);
	}
	
}
