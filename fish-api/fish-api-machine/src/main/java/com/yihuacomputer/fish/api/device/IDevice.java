package com.yihuacomputer.fish.api.device;

import java.util.Date;

import com.yihuacomputer.common.ITypeIP;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.person.IOrganization;

/**
 * 设备基本信息
 *
 * @author yantao
 *
 */
public interface IDevice {
	/**
	 * @return
	 */
	public long getId();

	/**
	 * @param id
	 */
	public void setId(long id);

	/**
	 * @return
	 */
	public String getTerminalId();

	/**
	 * @param terminalId
	 */
	public void setTerminalId(String terminalId);

	/**
	 * @return
	 */
	public ITypeIP getIp();

	/**
	 * @param ip
	 */
	public void setIp(ITypeIP ip);

	/**
	 * @return
	 */
	public IAtmType getDevType();

	/**
	 * @param devType
	 */
	public void setDevType(IAtmType devType);

	/**
	 * @return
	 */
	public DevStatus getStatus();

	/**
	 * @param status
	 */
	public void setStatus(DevStatus status);

	/**
	 * @return
	 */
	public IOrganization getDevService();

	/**
	 * @param devService
	 */
	public void setDevService(IOrganization devService);

	/**
	 * @return
	 */
	public String getAddress();

	/**
	 * @param address
	 */
	public void setAddress(String address);

	/**
	 * @return
	 */
	public int getCashboxLimit();

	/**
	 * @param cashboxLimit
	 */
	public void setCashboxLimit(int cashboxLimit);

	/**
	 * @param organization
	 */
	public void setOrganization(IOrganization organization);

	/**
	 * @return
	 */
	public IOrganization getOrganization();

	/**
	 * @return
	 */
	public SetupType getSetupType();

	/**
	 * @param setupType
	 */
	public void setSetupType(SetupType setupType);

	/**
	 * @return
	 */
	public AwayFlag getAwayFlag();

	/**
	 * @param awayFlag
	 */
	public void setAwayFlag(AwayFlag awayFlag);

	/**
	 * @return
	 */
	public WorkType getWorkType();

	/**
	 * @param workType
	 */
	public void setWorkType(WorkType workType);

	/**虚拟柜员号
	 * @return
	 */
	public String getVirtual();

	/**
	 * @param virtual
	 */
	public void setVirtual(String virtual);
	/**mac地址
	 * @return
	 */
	public String getMac();

	/**
	 * @param mac
	 */
	public void setMac(String mac);
	
	/**之前deviceExtend表的字段
	 * @return
	 */
	public String getSerial();

	/**
	 * @param serial
	 */
	public void setSerial(String serial);
	
	/**
	 * @return
	 */
	public NetType getNetType();

	/**
	 * @param netType
	 */
	public void setNetType(NetType netType);
	
	/**安装时间
	 * @return
	 */
	public Date getInstallDate();

	/**
	 * @param installDate
	 */
	public void setInstallDate(Date installDate);
	
	
	
	
}
