package com.yihuacomputer.fish.api.version;

import java.util.Date;

import com.yihuacomputer.fish.api.device.IDevice;
/**
 * 设备上最新的每种软件分类的版本
 * @author xuxigang
 *
 */
public interface IDeviceSoftVersion {
	/**
	 * @param id
	 */
	public void setId(long id);
	/**
	 * @return
	 */
	public long getId();
	/**
	 * @param typeName
	 */
	public void setTypeName(String typeName);
	/**
	 * @return
	 */
	public String getTypeName();
	/**
	 * @param versionNo
	 */
	public void setVersionNo(String versionNo);
	/**
	 * @return
	 */
	public String getVersionNo();
	/**
	 * @return
	 */
	public IVersion getVersion();
	/**
	 * @param terminalId
	 */
	public void setTerminalId(String terminalId);
	/**
	 * @return
	 */
	public String getTerminalId();
	/**
	 * @return
	 */
	public IDevice getDevice();
	/**
	 * @param createTime
	 */
	public void setCreatedTime(Date createTime);
	/**
	 * @return
	 */
	public Date getCreatedTime();
	/**
	 * @param lastUpdatedTime
	 */
	public void setLastUpdatedTime(Date lastUpdatedTime);
	/**
	 * @return
	 */
	public Date getLastUpdatedTime();
	/**
	 * @param desc
	 */
	public void setDesc(String desc);
	/**
	 * @return
	 */
	public String getDesc();
	/**
	 * @return
	 */
	public String getVersionStr();
	/**
	 * @param versionStr
	 */
	public void setVersionStr(String versionStr);
	/**
	 * @return
	 */
	public IVersionType getVersionType();
	/**
	 * @param versionType
	 */
	public void setVersionType(IVersionType versionType);
}
