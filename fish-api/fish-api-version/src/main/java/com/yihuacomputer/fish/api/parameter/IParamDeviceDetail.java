package com.yihuacomputer.fish.api.parameter;

import com.yihuacomputer.fish.api.device.IDevice;

/**
 * @author YiHua
 *
 */
public interface IParamDeviceDetail {
	/**
	 * @return
	 */
	public IDevice getDevice();

	/**
	 * @param device
	 */
	public void setDevice(IDevice device);

	/**
	 * @return
	 */
	public IParamElement getElement();

	/**
	 * @param element
	 */
	public void setElement(IParamElement element);

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
	public String getParamValue();

	/**
	 * @param paramValue
	 */
	public void setParamValue(String paramValue);
	
	/**
	 * @return
	 */
	public long getVersionTimeStamp();
	
	/**
	 * @param versionTimeStamp
	 */
	public void setVersionTimeStamp(long versionTimeStamp);
}
