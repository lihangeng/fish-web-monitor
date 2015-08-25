package com.yihuacomputer.fish.api.device;

/**
 * 设备元数据.
 * @author yantao
 *
 */
public interface IDeviceMeta {

	public void setId(long id);

	public long getId();

	/**
	 * 获取品牌.
	 * @return 设备品牌
	 */
	public DeviceBrand getBrand();
	
	/**
	 * 获取类型.
	 * @return 设备类型
	 */
	public String getType();
	
}
