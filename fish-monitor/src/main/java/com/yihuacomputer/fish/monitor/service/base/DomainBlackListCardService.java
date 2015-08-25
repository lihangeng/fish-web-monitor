package com.yihuacomputer.fish.monitor.service.base;

import com.yihuacomputer.fish.monitor.entity.business.BlackListCard;
import com.yihuacomputer.fish.monitor.service.api.IDomainBlackListCardService;

public abstract class DomainBlackListCardService implements IDomainBlackListCardService {

	@Override
	public BlackListCard make(){
		return new BlackListCard(this);
	}
}
