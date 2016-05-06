package com.yihuacomputer.fish.api.parameter;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.device.IDevice;


public interface IParamPublishResultService {
	public IParamPublishResult make();
	/**
	 * 新建下发结果对象时，自动建应用类型下发结果对象
	 * @param publishResult
	 * @return
	 */
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
	
	IPageResult<ParamDownloadResultForm> page(int start,int limit,IFilter filter);
	IPageResult<ParamDownloadResultForm> getByPublishId(int start,int limit,IFilter filter, String publishId);
	List<IParamPublishAppResult> getStatus(long pubId);
}
