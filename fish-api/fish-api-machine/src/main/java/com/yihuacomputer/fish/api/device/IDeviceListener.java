/**
 *
 */
package com.yihuacomputer.fish.api.device;

/**
 * 隔离monitor在base中的引用
 *
 * 用于和设备的若关联关系时的数据，在增加、修改、删除特备是删除可能导致的数据不一致的问题。
 *
 * @author xuxiang
 * @since 0.15
 */
public interface IDeviceListener {

	/**
	 * @return
	 */
	public String getListenerName();

	/**
	 * @param device
	 */
	public void beforeAdd(IDevice device);

	/**
	 * @param device
	 */
	public void afterAdd(IDevice device);

	/**
	 * @param device
	 */
	public void beforeUpdate(IDevice device);

	/**
	 * @param device
	 */
	public void afterUpdate(IDevice device);

	/**
	 * @param device
	 */
	public void beforeDelete(IDevice device);

	/**
	 * @param device
	 */
	public void afterDelete(IDevice device);

}
