package com.yihuacomputer.fish.api.parameter;

import java.util.List;
import java.util.Map;

import com.yihuacomputer.common.IFilter;


/**
 * 执行参数下发
 * @author GQ
 *
 */
public interface IParamPublishService {

	/**
	 * @return
	 */
	IParamPublish make();
	/**
	 * @param publish
	 * @return
	 */
	IParamPublish update(IParamPublish publish);
	/**
	 * @param id
	 * @param ret
	 * @return
	 */
	IParamPublish update(long id,String ret);
	/**
	 * @param publish
	 * @return
	 */
	IParamPublish save(IParamPublish publish);
	/**
	 * @param id
	 * @return
	 */
	IParamPublish get(long id);
	/**
	 * @return
	 */
	IParamPublishResultService getParamPulishResultService() ;
	
	
	/**
	 * @param filter
	 * @return
	 */
	List<IParamPublish> list(IFilter filter);
	
	/**
	 * 根据模板ID和操作人员信息进行下发操作
	 * @param templateId
	 * @param personId
	 * @return 下发任务详情
	 */
	long paramPublishByTemplate(long templateId,long personId);
	
	/**
	 * 根据设备ID列表和操作人员信息进行下发操作
	 * @param deviceId
	 * @param personId
	 * @return 下发任务详情
	 */
	long paramPublishByDeviceIds(List<Long> deviceId,long personId);
	
	/**
	 * 获取设备最新的参数版本号
	 * @param deviceId
	 * @return
	 */
	public Map<String, Long> getMaxVersionNoInfoByDeviceId(long deviceId);
	
	
	
	
}
