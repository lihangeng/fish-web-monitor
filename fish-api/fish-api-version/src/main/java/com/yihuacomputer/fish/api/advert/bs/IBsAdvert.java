package com.yihuacomputer.fish.api.advert.bs;

import java.util.Date;
import java.util.List;

import com.yihuacomputer.fish.api.advert.AdvertType;
import com.yihuacomputer.fish.api.advert.Screen;


/**
 * @author YiHua
 *
 */
public interface IBsAdvert {
	/**
	 * @return
	 */
	public long getId();
	/**
	 * @param id
	 */
	public void setId(long id);

	/**
	 * @return
	 */
	public long getGroupId();
	/**
	 * @param groupId
	 */
	public void setGroupId(long groupId);

	/**
	 * @return
	 */
	public String getAdvertName() ;
	/**
	 * @param advertName
	 */
	public void setAdvertName(String advertName);

	/**
	 * @return
	 */
	public BsAdvertStatus getBsAdvertStatus();
	/**
	 * @param bsAdvertStatus
	 */
	public void setBsAdvertStatus(BsAdvertStatus bsAdvertStatus);

	/**
	 * @return
	 */
	public Date getLastTime();
	/**
	 * @param lastTime
	 */
	public void setLastTime(Date lastTime);



	/**
	 * @return
	 */
	public long getUserId();
	/**
	 * @param userId
	 */
	public void setUserId(long userId);

	/**
	 * @return
	 */
	public long getActiveUserId();
	/**
	 * @param activeUserId
	 */
	public void setActiveUserId(long activeUserId);
	
	/**
	 * @return
	 */
	public List<IBsAdvertResource> getAdvertResources();
	/**
	 * @param advertResources
	 */
	public void setAdvertResources(List<IBsAdvertResource> advertResources);

	/**
	 * @param advertType
	 */
	public void setAdvertType(AdvertType advertType);
	/**
	 * @return
	 */
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


    /**
     * @param screen
     * @return
     */
    public String getAdvertConfigByScreen(Screen screen);

    /**
     * @param advertService
     */
    public void insertBsAdvertService(IBsAdvertService advertService);
}
