package com.yihuacomputer.fish.api.advert.bs;

import java.util.Date;
import java.util.List;

import com.yihuacomputer.fish.api.advert.AdvertType;
import com.yihuacomputer.fish.api.advert.Screen;


public interface IBsAdvert {
	public long getId();
	public void setId(long id);

	public long getGroupId();
	public void setGroupId(long groupId);

	public String getAdvertName() ;
	public void setAdvertName(String advertName);

	public BsAdvertStatus getBsAdvertStatus();
	public void setBsAdvertStatus(BsAdvertStatus bsAdvertStatus);

	public Date getLastTime();
	public void setLastTime(Date lastTime);



	public long getUserId();
	public void setUserId(long userId);

	public long getActiveUserId();
	public void setActiveUserId(long activeUserId);
	
	public List<IBsAdvertResource> getAdvertResources();
	public void setAdvertResources(List<IBsAdvertResource> advertResources);

	public void setAdvertType(AdvertType advertType);
	public AdvertType getAdvertType();
	

    /**
     * 增加广告资源
     *
     * @param resource
     */
    public void addAdvertResource(IBsAdvertResource resource);

    /**
     * 删除广告资源
     *
     * @param resource
     */
    public void removeAdvertResource(IBsAdvertResource resource);


    public String getAdvertConfigByScreen(Screen screen);

    public void insertBsAdvertService(IBsAdvertService advertService);
}
