package com.yihuacomputer.fish.monitor.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxDetailInfo;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxDetailInfoService;
import com.yihuacomputer.fish.monitor.entity.box.DeviceBoxDetailInfo;

@Service
@Transactional
public class DeviceBoxDetailInfoService implements IDeviceBoxDetailInfoService {

	private Logger logger = LoggerFactory.getLogger(DeviceBoxDetailInfoService.class);

	
	@Autowired
	private IGenericDao dao;
	
	@Override
	public IDeviceBoxDetailInfo make() {
		return new DeviceBoxDetailInfo();
	}

	@Override
	public IDeviceBoxDetailInfo save(IDeviceBoxDetailInfo dbdi) {
		return dao.save(dbdi);
	}

	@Override
	public IDeviceBoxDetailInfo update(IDeviceBoxDetailInfo dbdi) {
		return dao.update(dbdi);
	}

	@Override
	public void delete(IDeviceBoxDetailInfo dbdi) {
		dao.delete(dbdi);
	}

	@Override
	public List<IDeviceBoxDetailInfo> list(IFilter filter) {
		return dao.findByFilter(filter, IDeviceBoxDetailInfo.class);
	}

	@Override
	public boolean updateBoxDetailEffect(long deviceBoxInfoId) {
		StringBuffer sb = new StringBuffer();
		sb.append("update ").append(DeviceBoxDetailInfo.class.getSimpleName())
		.append(" dbdi set dbdi.effect='0' where dbdi.deviceBoxInfo.id=?");
		try{
			dao.batchUpdate(sb.toString(), new Object[]{deviceBoxInfoId});
		}catch(Exception e){
			logger.error(e.getMessage());
			return false;
		}
		return false;
	}

	@Override
	public Map<String, IDeviceBoxDetailInfo> getCashIdMap(IFilter filter) {
		List<IDeviceBoxDetailInfo> list = this.list(filter);
		Map<String, IDeviceBoxDetailInfo> map = new HashMap<String, IDeviceBoxDetailInfo>();
		for(IDeviceBoxDetailInfo idbdi:list){
			map.put(idbdi.getCashId(), idbdi);
		}
		return map;
	}
	

}
