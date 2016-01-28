package com.yihuacomputer.fish.api.advert.bs;

public interface IAdvertGroupDeviceRelation {

	public long getId();
	public void setId(long id);
	
	/**
	 * 将被请求广告组ID
	 * @return
	 */
	public long getGroupId();
	public void setGroupId(long groupId);

	public long getDeviceId() ;
	public void setDeviceId(long deviceId);
	
	/**
	 * 将被请求广告资源ID
	 * @return
	 */
	public long getAdvertId() ;
	public void setAdvertId(long advertId);

	/**
	 * 正在使用中广告资源ID;
	 * @return
	 */
	public long getAdvertIdUsing();
	public void setAdvertIdUsing(long advertIdUsing);

}
