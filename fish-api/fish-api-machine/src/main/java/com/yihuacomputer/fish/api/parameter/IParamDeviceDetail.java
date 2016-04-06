package com.yihuacomputer.fish.api.parameter;

import com.yihuacomputer.fish.api.device.IDevice;

public interface IParamDeviceDetail {
	public IDevice getDevice();

	public void setDevice(IDevice device);

	public IParamElement getElement();

	public void setElement(IParamElement element);

	public long getId();

	public void setId(long id);

	public String getParamValue();

	public void setParamValue(String paramValue);
}
