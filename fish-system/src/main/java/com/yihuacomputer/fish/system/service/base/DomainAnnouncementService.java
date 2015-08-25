package com.yihuacomputer.fish.system.service.base;

import com.yihuacomputer.fish.system.entity.Announcement;
import com.yihuacomputer.fish.system.service.api.IDomainAnnouncementService;

public abstract class DomainAnnouncementService implements IDomainAnnouncementService{
	
	@Override
	public Announcement make(){
		Announcement ann =new Announcement(this);
		return ann;
	}

}
