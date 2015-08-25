package com.yihuacomputer.fish.system.service.base;

import java.util.Date;

import com.yihuacomputer.fish.system.entity.Message;
import com.yihuacomputer.fish.system.service.api.IDomainMessageService;

public abstract class DomainMessageService implements IDomainMessageService{
	
	@Override
	public Message make(){
		Message msg =new Message(this);
		msg.setCreateTime(new Date());
		return msg;
	}

}
