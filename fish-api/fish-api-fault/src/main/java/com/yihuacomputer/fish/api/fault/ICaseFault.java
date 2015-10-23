package com.yihuacomputer.fish.api.fault;

import java.util.Date;
import java.util.List;

import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceMod;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IPerson;

public interface ICaseFault {

	public long getId();

	public void setId(long id);

	/**
	 * 设备号
	 * @return
	 */
	public String getTerminalId();

	public void setTerminalId(String terminalId);

	/**
	 * ATMC运行状态
	 * @return
	 */
	public RunStatus getAppStatus();

	public void setAppStatus(RunStatus appStatus);
	/**
	 * 故障模块
	 * @return
	 */
	public DeviceMod getDevMod();

	public void setDevMod(DeviceMod devMod);

	/**
	 * 故障分类
	 * @return
	 */
	public IFaultClassify getFaultClassify();

	public void setFaultClassify(IFaultClassify faultClassify);

	/**
	 * 故障码
	 * @return
	 */
	public String getFaultCode();

	public void setFaultCode(String faultCode);

	/**
	 * 厂商故障码
	 * @return
	 */
	public String getVendorHwCode();

	public void setVendorHwCode(String vendorHwCode);

	/**
	 * 故障开始时间
	 * @return
	 */
	public Date getFaultTime();

	public void setFaultTime(Date faultTime);
	
	/**
	 * 设置故障创建日期，
	 * 格式yyyymmdd 
	 * 比如20150812
	 * @since 2.0.0.3便于统计使用
	 * @return
	 */
	public Long getFaultDate();
	
	public void setFaultDate(Long faultDate);

	/**
	 * 故障关闭时间
	 * @return
	 */
	public Date getClosedTime();

	public void setClosedTime(Date closedTime);

	/**
	 * 故障持续时长(单位:小时)
	 * @return
	 */
	public double getDuration();

	public void setDuration(double duration);

	/**
	 * 故障状态
	 * @return
	 */
	public FaultStatus getFaultStatus();

	public void setFaultStatus(FaultStatus faultStatus);

	/**
	 * 已经升级次数
	 * @return
	 */
	public int getUpgrade();

	public void setUpgrade(int upgrade);


	/**
	 * 发生故障机具所属机构
	 * @return
	 */
	public IOrganization getOrg();
	/**
	 * 发生故障机具银行相关人员
	 * @return
	 */
	public List<IPerson> getBankPerson();

	/**
	 * 发生故障机具服务商相关人员
	 * @return
	 */
	public List<IPerson> getServicePerson();

	/**
	 * 获得设备
	 * @return
	 * @since 0.24
	 */
	public IDevice getDevice();
}
