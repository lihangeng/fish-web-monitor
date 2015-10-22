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
	
	
	
	
	/**
	 * 获取可下发设备列表（可以进行封装）Object==Device
	 * @param start
	 * @param limit
	 * @param version
	 * @param outerFilter
	 * @return
	 */
	public IPageResult<Object> getCanPushDevicePagesInfo(int start, int limit, IVersion version, IFilter outerFilter);
	 /**
	  * 指定版本可以下发的设备信息（分两类，1.无依赖关系的设备，当前版本下发失败的;2.有依赖关系的，版本号依赖完全正确才可以下发）
	 * @param start
	 * @param limit
	 * @param version
	 * @param outerFilter
	 * @return
	 */
	public IPageResult<LinkedDeviceForm> pageDownLoadDevices(int start, int limit, IVersion version, IFilter outerFilter);
	
}
