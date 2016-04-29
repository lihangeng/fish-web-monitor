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
	 * 根据设备号和版本号判断当前是否存在自动更新参数任务
	 * @param deviceId
	 * @param versionNo
	 * @return
	 */
	public IParamPublishResult getParamPublishResult(long deviceId,long versionNo);
	
	/**
	 * 进行下发通知
	 * @param msg
	 */
	public boolean notice(IParamPublishResult msg,IDevice device);
}
