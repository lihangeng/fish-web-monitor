package com.yihuacomputer.fish.api.parameter;

import com.yihuacomputer.fish.api.device.IDevice;


public interface IParamPublishResultService {
	public IParamPublishResult make();
	public IParamPublishResult save(IParamPublishResult publishResult);
	public IParamPublishResult get(long id);
	public IParamPublishResult update(IParamPublishResult publishResult);
	public IParamPublishResult update(long id,String ret);
	public IParamPublishService getParamPublishService();
	
	/**
	 * 进行下发通知
	 * @param msg
	 */
	public boolean notice(IParamPublishResult msg,IDevice device);
}
