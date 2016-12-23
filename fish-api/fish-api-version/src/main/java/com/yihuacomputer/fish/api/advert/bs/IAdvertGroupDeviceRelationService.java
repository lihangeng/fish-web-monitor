package com.yihuacomputer.fish.api.advert.bs;

import java.util.List;

/**
 * @author YiHua
 *
 */
public interface IAdvertGroupDeviceRelationService {
	/**
	 * @param deviceId
	 * @param groupIdList
	 * @return
	 */
	public IAdvertGroupDeviceRelation getGroup(long deviceId,List<Long> groupIdList);
}
