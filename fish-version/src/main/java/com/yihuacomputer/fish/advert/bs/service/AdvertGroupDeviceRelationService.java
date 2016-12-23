package com.yihuacomputer.fish.advert.bs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.advert.bs.entity.AdvertGroupDeviceRelation;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupDeviceRelation;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupDeviceRelationService;

/**
 * @author YiHua
 *
 */
public class AdvertGroupDeviceRelationService implements IAdvertGroupDeviceRelationService {
	
	@Autowired
	private IGenericDao dao;
	
	@Override
	public IAdvertGroupDeviceRelation getGroup(long deviceId,List<Long> groupIdList){
		IFilter filter = new Filter();
		filter.eq("deviceId", deviceId);
		filter.in("groupId", groupIdList);
		return dao.findUniqueByFilter(filter, AdvertGroupDeviceRelation.class);
	}
}
