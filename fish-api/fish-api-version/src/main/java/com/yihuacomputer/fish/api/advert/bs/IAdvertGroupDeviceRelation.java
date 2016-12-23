package com.yihuacomputer.fish.api.advert.bs;

/**
 * @author YiHua
 *
 */
public interface IAdvertGroupDeviceRelation {

	/**
	 * @return
	 */
	public long getId();
	/**
	 * @param id
	 */
	public void setId(long id);
	
	/**
	 * 将被请求广告组ID
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
	public long getDeviceId() ;
	/**
	 * @param deviceId
	 */
	public void setDeviceId(long deviceId);
	
	/**
	 * 将被请求广告资源ID
	 * @return
	 */
	public long getAdvertId() ;
	/**
	 * @param advertId
	 */
	public void setAdvertId(long advertId);

	/**
	 * 正在使用中广告资源ID;
	 * @return
	 */
	public long getAdvertIdUsing();
	/**
	 * @param advertIdUsing
	 */
	public void setAdvertIdUsing(long advertIdUsing);

}
