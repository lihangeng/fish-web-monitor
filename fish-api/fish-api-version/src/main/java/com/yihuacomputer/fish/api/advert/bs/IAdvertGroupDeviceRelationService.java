package com.yihuacomputer.fish.api.advert.bs;

import java.util.List;

public interface IAdvertGroupDeviceRelationService {
	public IAdvertGroupDeviceRelation getGroup(long deviceId,List<Long> groupIdList);
}
