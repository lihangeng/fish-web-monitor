package com.yihuacomputer.fish.api.parameter;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.device.IDevice;


/**
 * @author YiHua
 *
 */
public interface IParamPublishResultService {
	/**
	 * @return
	 */
	public IParamPublishResult make();
	/**
	 * 新建下发结果对象时，自动建应用类型下发结果对象
	 * @param publishResult
	 * @return
	 */
	public IParamPublishResult save(IParamPublishResult publishResult);
	/**
	 * @param id
	 * @return
	 */
	public IParamPublishResult get(long id);
	/**
	 * @param publishResult
	 * @return
	 */
	public IParamPublishResult update(IParamPublishResult publishResult);
	/**
	 * @param id
	 * @param ret
	 * @return
	 */
	public IParamPublishResult update(long id,String ret);
	/**
	 * @return
	 */
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
	 * @param device
	 * @return
	 */
	public boolean notice(IParamPublishResult msg,IDevice device);
	
	/**
	 * @param start
	 * @param limit
	 * @param filter
	 * @return
	 */
	IPageResult<ParamDownloadResultForm> page(int start,int limit,IFilter filter);
	/**
	 * @param start
	 * @param limit
	 * @param filter
	 * @param publishId
	 * @return
	 */
	IPageResult<ParamDownloadResultForm> getByPublishId(int start,int limit,IFilter filter, String publishId);
	/**
	 * @param pubId
	 * @return
	 */
	List<IParamPublishAppResult> getStatus(long pubId);
}
