/**
 * 
 */
package com.yihuacomputer.fish.api.version;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.person.IOrganization;

/**
 * 版本分发服务
 * @author xuxigang
 *
 */
public interface IVersionDownloadService {
	/**
	 * 根据选择的版本，分页显示可以下发的设备
	 * @param start
	 * @param limit
	 * @param selectedVversion 被选择将要下发的版本
	 * @param job
	 * @param filter
	 * @param currentLoginUserId 当前登陆用户ID
	 * @return
	 */
	public IPageResult<LinkedDeviceForm> pageDevices(int start,int limit,IVersion selectedVversion,IFilter filter,long currentLoginUserId);
	public IPageResult<?> pageDownDevices(int start,int limit,IVersion version,IFilter filter);
	/**
	 * 分页显示作业已经关联的设备列表
	 * @param start
	 * @param limit
	 * @param job
	 * @param filter
	 * @return
	 */
	public IPageResult<IDevice> pageLinkedDevices(int start,int limit,IFilter filter);
	
	/**
	 * 重启ATM
	 * @param ips 设备的IP
	 */
	public void rebootATM(String ... ips);
	/**
	 * 根据软件分类的规则获得该版本能够下发的设备数量
	 * @param version
	 * @return
	 * @since 0.15
	 */
	public long getMayBeDownDevice(IVersion version,IOrganization org);
	
}
