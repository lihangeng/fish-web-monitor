package com.yihuacomputer.fish.api.openplan;

/**
 * 设备/运营方案关联关系
 * @author YiHua
 *
 */
public interface IDevicePlanRelation {
	
	/**
	 * @return
	 */
	public long getId();

	/**
	 * @param id
	 */
	public void setId(long id);

	/**
	 * 设备id
	 * @return
	 */
	public long getDeviceId();

	/**
	 * @param deviceId
	 */
	public void setDeviceId(long deviceId);

	/**
	 * 方案id
	 * @return
	 */
	public long getOpenPlanId();

	/**
	 * @param openPlanId
	 */
	public void setOpenPlanId(long openPlanId);
}
