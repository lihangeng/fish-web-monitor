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
	public long getId();

	public void setId(long id);

	public String getTerminalId();

	public void setTerminalId(String terminalId);

	public ITypeIP getIp();

	public void setIp(ITypeIP ip);

	public IAtmType getDevType();

	public void setDevType(IAtmType devType);

	public DevStatus getStatus();

	public void setStatus(DevStatus status);

	public IOrganization getDevService();

	public void setDevService(IOrganization devService);

	public String getAddress();

	public void setAddress(String address);

	public int getCashboxLimit();

	public void setCashboxLimit(int cashboxLimit);

	public void setOrganization(IOrganization organization);

	public IOrganization getOrganization();

	public SetupType getSetupType();

	public void setSetupType(SetupType setupType);

	public AwayFlag getAwayFlag();

	public void setAwayFlag(AwayFlag awayFlag);

	public WorkType getWorkType();

	public void setWorkType(WorkType workType);

    /*
     * 虚拟柜员号
     */
	public String getVirtual();

	public void setVirtual(String Virtual);
    /*
     * mac地址
     */
	public String getMac();

	public void setMac(String mac);
	
	/**
	 * 之前deviceExtend表的字段
	 */
	public String getSerial();

	public void setSerial(String serial);
	
	public NetType getNetType();

	public void setNetType(NetType netType);
	
	//安装时间
	public Date getInstallDate();

	public void setInstallDate(Date installDate);
	
	
	
	
}
