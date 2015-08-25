package com.yihuacomputer.fish.system.service.base;


import com.yihuacomputer.fish.system.entity.ShortMessage;
import com.yihuacomputer.fish.system.service.api.IDomainShortMessageService;

public abstract class DomainShortMessageService implements IDomainShortMessageService{
	@Override
	public ShortMessage make(){
		ShortMessage msg =new ShortMessage(this);
		return msg;
	}
}
