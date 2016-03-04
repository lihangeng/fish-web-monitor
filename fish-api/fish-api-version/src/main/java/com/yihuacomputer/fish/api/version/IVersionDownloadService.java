/**
 *
 */
package com.yihuacomputer.fish.api.version;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.version.job.IJob;

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
	public IPageResult<LinkedDeviceForm> pageDevices(int start,int limit,IVersion selectedVversion,IJob job,IFilter filter,long currentLoginUserId);
	public IPageResult<?> pageDownDevices(int start,int limit,IVersion version,IFilter filter);
	/**
	 * 分页显示作业已经关联的设备列表
	 * @param start
	 * @param limit
	 * @param job
	 * @param filter
	 * @return
	 */
	public IPageResult<IDevice> pageLinkedDevices(int start,int limit,IJob job,IFilter filter);

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

	/**
	 * 全选设备时查询当前可下发的全部设备列表
	 * @param version
	 * @param org
	 * @return
	 */
	public List<IDevice> getSelectAll(IVersion version,IOrganization org);

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
	public IPageResult<LinkedDeviceForm> pageDevices(int start,int limit,IVersion selectedVversion,IJob job,IFilter filter,long currentLoginUserId,IVersion devVersion);

	/**
	 * 全选设备时查询当前可下发的全部设备列表
	 * @param version
	 * @param org
	 * @return
	 */
	public List<Object> getSelectAllForList(IVersion version, IOrganization org) ;
	public IPageResult<Object> getCanPushDevicePagesInfo(int start, int limit,IVersion version,IFilter outerFilter);
	 public IPageResult<LinkedDeviceForm> pageDevices(int start, int limit,IVersion version,IFilter outerFilter);
}
