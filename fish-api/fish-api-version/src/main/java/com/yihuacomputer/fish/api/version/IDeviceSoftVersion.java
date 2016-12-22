package com.yihuacomputer.fish.api.version;

import java.util.Date;

import com.yihuacomputer.fish.api.device.IDevice;
/**
 * 设备上最新的每种软件分类的版本
 * @author xuxigang
 *
 */
public interface IDeviceSoftVersion {
	public void setId(long id);
	public long getId();
	public void setTypeName(String typeName);
	public String getTypeName();
	public void setVersionNo(String versionNo);
	public String getVersionNo();
	public IVersion getVersion();
	public void setTerminalId(String terminalId);
	public String getTerminalId();
	public IDevice getDevice();
	public void setCreatedTime(Date createTime);
	public Date getCreatedTime();
	public void setLastUpdatedTime(Date lastUpdatedTime);
	public Date getLastUpdatedTime();
	public void setDesc(String desc);
	public String getDesc();
	public String getVersionStr();
	public void setVersionStr(String versionStr);
	public IVersionType getVersionType();
	public void setVersionType(IVersionType versionType);
}
