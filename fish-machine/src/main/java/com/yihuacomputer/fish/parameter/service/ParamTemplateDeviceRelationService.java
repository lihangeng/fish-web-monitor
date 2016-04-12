package com.yihuacomputer.fish.parameter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDeviceRelation;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDeviceRelationService;
import com.yihuacomputer.fish.parameter.entity.ParamTemplateDeviceRelation;

@Service
@Transactional
public class ParamTemplateDeviceRelationService implements IParamTemplateDeviceRelationService {
	
	@Autowired
	private IGenericDao dao;

	@Override
	public IParamTemplateDeviceRelation getById(long id) {
		return dao.get(id, ParamTemplateDeviceRelation.class);
	}

	@Override
	public List<IParamTemplateDeviceRelation> getByDeviceId(long deviceId) {
		StringBuffer hql=new StringBuffer();
		hql.append("select ptdr from ParamTemplateDeviceRelation ptdr");
		hql.append("where ptdr.deviceId = ?");
		List<IParamTemplateDeviceRelation> realtionList=dao.findByHQL(hql.toString(), deviceId);
		return realtionList;
	}
	

}
