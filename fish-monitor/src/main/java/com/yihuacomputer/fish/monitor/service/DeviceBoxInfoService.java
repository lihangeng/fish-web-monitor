package com.yihuacomputer.fish.monitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
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
	@Override
	public IPageResult<IDeviceBoxInfo> list(int offset,int limit,IFilter filter) {
		return dao.page(offset, limit, filter, IDeviceBoxInfo.class);
	}

}
