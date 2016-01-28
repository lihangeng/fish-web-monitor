package com.yihuacomputer.fish.api.advert.bs;

import java.util.Date;
import java.util.List;


public interface IBsAdvert {
	public long getId();
	public void setId(long id);

	public long getGroupId();
	public void setGroupId(long groupId);

	public String getAdvertName() ;
	public void setAdvertName(String advertName);

	public boolean getActived();
	public void setActived(boolean actived);

	public Date getLastTime();
	public void setLastTime(Date lastTime);

	public long getPersonId();
	public void setPersonId(long personId);

	public long getActivePersonId();
	public void setActivePersonId(long activePersonId);

	public List<IBsAdvertResource> getAdvertResources();
	public void setAdvertResources(List<IBsAdvertResource> advertResources);
}
