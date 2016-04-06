package com.yihuacomputer.fish.parameter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.parameter.DeviceParam;
import com.yihuacomputer.fish.api.parameter.IParamDeviceDetail;
import com.yihuacomputer.fish.api.parameter.IParamDeviceDetailService;
import com.yihuacomputer.fish.parameter.entity.ParamDeviceDetail;

public class ParamDeviceDetailService implements IParamDeviceDetailService {
	
	@Autowired
	private IGenericDao dao;

	@Override
	public IParamDeviceDetail get(long id) {
		return dao.get(id, ParamDeviceDetail.class);
	}

	@Override
	public void update(IParamDeviceDetail paramDeviceDetail) {
		dao.update(paramDeviceDetail instanceof ParamDeviceDetail ? (ParamDeviceDetail)paramDeviceDetail : null);
	}

	@Override
	public List<DeviceParam> list(IFilter filter, long tabId, long deviceId) {
		StringBuffer hql= new StringBuffer();
		hql.append("select pc.id,pc.name, pe.paramName ,pdd.paramValue ");
		hql.append("FROM ParamElement pe,ParamClassify pc,ParamDeviceDetail pdd ");
		hql.append("WHERE pe.paramClassify.id=pc.id AND pe.id = pdd.element.id AND pe.paramBelongs.id = ? AND pdd.device.id = ? ");
		List<DeviceParam> resultList=dao.findByHQL(hql.toString(), Long.valueOf(tabId),Long.valueOf(deviceId));
		return resultList;
	}

	@Override
	public Iterable<IParamDeviceDetail> list() {
		return dao.loadAll(IParamDeviceDetail.class);
	}

}
