package com.yihuacomputer.fish.api.monitor.filter;

import java.util.List;

import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.monitor.report.IDeviceReport;

/**
 * 状态监控条件
 *
 * @author YiHua
 * @since 2012/2/8
 */
public interface IStatusFilter {
    
    /**
     * 主键
     * @return
     */
    public long getId();
    
    /**
     * 设置主键
     * @param id
     */
    public void setId(long id);
    
    /**
     * 订阅名称
     * @return
     */
    public String getFilterName();
    
    /**
     * 设置订阅名称
     * @param filterName
     */
    public void setFilterName(String filterName);
    

	/**
	 * 获取用户ID
	 *
	 * @return
	 */
	public String getUserId();

	/**
	 * 设置用户ID
	 *
	 * @param userId
	 */
	public void setUserId(String userId);

	/**
	 * 获取设备列表
	 *
	 * @return
	 */
	public List<String> getDeviceList();

	/**
	 * 运行状态
	 *
	 * @return
	 */
	public IRunStatusFilter getRunStatusFilter();

	/**
	 * 钞箱状态
	 *
	 * @return
	 */
	public IBoxStatusFilter getBoxStatusFilter();

	/**
	 * 模块状态
	 *
	 * @return
	 */
	public IModStatusFilter getModStatusFilter();

	/**
	 * 网络状态
	 *
	 * @return
	 */
	public INetStatusFilter getNetStatusFilter();

	/**
	 * 设备号
	 *
	 * @return
	 */
	public String getTerminalId();

	/**
	 * 每页显示设备数
	 *
	 * @return
	 */
	public int getLimit();

	/**
	 * 偏移量
	 *
	 * @return
	 */
	public int getOffset();

	/**
	 * 设置设备列表
	 *
	 * @param deviceList
	 */
	public void setDeviceList(List<String> deviceList);

	/**
	 * 监控增加一台设备
	 *
	 * @param terminalId
	 */
	public void addDevice(String terminalId);

	/**
	 * 监控删除一台设备
	 *
	 * @param terminalId
	 */
	public void remoteDevice(String terminalId);

	/**
	 * 设置运行状态监控条件
	 *
	 * @param runFilter
	 */
	public void setRunStattusFilter(IRunStatusFilter runFilter);

	/**
	 *
	 * @param boxFilter
	 */
	public void setBoxStatusFilter(IBoxStatusFilter boxFilter);

	/**
	 *
	 * @param modFilter
	 */
	public void setModStatusFilter(IModStatusFilter modFilter);

	/**
	 *
	 * @param netFilter
	 */
	public void setNetStatusFilter(INetStatusFilter netFilter);

	/**
	 *
	 * @param termialId
	 */
	public void setTerminalId(String termialId);

	/**
	 *
	 * @param limit
	 */
	public void setLimit(int limit);

	/**
	 *
	 * @param offset
	 */
	public void setOffset(int offset);

	/**
	 * 设备状态监控条件过滤
	 *
	 * @param deviceReport
	 * @return
	 */
	public ReportMedthod filterStatus(IDeviceReport deviceReport);

	/**
	 * 机构ID
	 *
	 * @return
	 */
	public String getOrgId();

	/**
	 *
	 * @param orgId
	 */
	public void setOrgId(String orgId);

	/**
	 *
	 * @return
	 */
	public long getDevVendor();

	/**
	 *
	 * @param devVendor
	 */
	public void setDevVendor(long devVendor);

	/**
	 * 设备类型
	 *
	 * @return
	 */
	public long getDevType();

	/**
	 * 设备分类
	 *
	 * @param devType
	 */
	public void setDevType(long devType);

	/**
	 * 经营方式
	 *
	 * @return
	 */
	public int getWorkType();

	/**
	 * 经营方式
	 *
	 * @param workType
	 */
	public void setWorkType(int workType);

	/**
	 * 在行离行标志
	 *
	 * @return
	 */
	public int getAwayFlag();

	/**
	 * 在行离行标志
	 *
	 * @param awayFlag
	 */
	public void setAwayFlag(int awayFlag);

	/**
	 * 获取管辖机构列表
	 *
	 * @return
	 */
	public List<Long> getSubOrg();

	/**
	 * 设置管辖机构列表
	 *
	 * @param subOrg
	 */
	public void setSubOrg(List<Long> subOrg);

	/**
	 * 获得设备分组ID
	 *
	 * @return
	 */
	public long getAtmGroup();

	/**
	 * 设置设备分组ID
	 *
	 * @param atmGroupId
	 */
	public void setAtmGroup(long atmGroupId);

	/**
	 * 设备设备状态(默认状态为DevStatus.OPEN)
	 *
	 * @param status
	 */
	public void setDeviceStatus(DevStatus status);

	/**
	 * 获得设备状态
	 *
	 * @return
	 */
	public DevStatus getDeviceStatus();

}
