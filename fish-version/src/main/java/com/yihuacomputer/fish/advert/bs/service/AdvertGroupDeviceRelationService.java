package com.yihuacomputer.fish.advert.bs.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.advert.bs.entity.AdvertGroupDeviceRelation;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupDeviceRelation;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupDeviceRelationService;

public class AdvertGroupDeviceRelationService implements
		IAdvertGroupDeviceRelationService {
	@Autowired
	private IGenericDao dao;
	public IAdvertGroupDeviceRelation getGroup(String deviceId){
		StringBuffer hql=new StringBuffer("select id from");
		hql.append(AdvertGroupDeviceRelation.class.getSimpleName()).append("aGDR");
		hql.append("where deviceId=?");
		IAdvertGroupDeviceRelation advertGroupDeviceRelation=dao.findUniqueByHql(hql.toString(), deviceId);
		return advertGroupDeviceRelation;
		
	}
}
