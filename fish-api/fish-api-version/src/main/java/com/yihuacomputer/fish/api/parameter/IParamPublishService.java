package com.yihuacomputer.fish.api.parameter;

import java.util.List;


/**
 * 执行参数下发
 * @author GQ
 *
 */
public interface IParamPublishService {

	IParamPublish make();
	IParamPublish update(IParamPublish publish);
	IParamPublish update(long id,String ret);
	IParamPublish save(IParamPublish publish);
	IParamPublish get(long id);
	IParamPublishResultService getParamPulishResultService() ;
	
	boolean paramPublishByTemplate(long templateId,long personId);
	
	boolean paramPublishByDeviceIds(List<Long> deviceId,long personId);
	/**
	 * 根据模板生成参数文件并返回版本号
	 * @param templateId
	 * @return 返回零代表生成文件失败
	 */
	long generateParamFileByTemplate(long templateId);
	/**
	 * 根据设备号生成参数文件并返回版本号
	 * @param deviceId
	 * @return 返回零代表生成文件失败
	 */
	long generateParamFileByDevice(long deviceIds);
	
	/**
	 * 模板参数下发
	 * @param templateId
	 * @return
	 */
	boolean noticeDeviceDownloadParamFileByTemplate(long templateId,long versionNo,long personId);
	
	/**
	 * 设备参数下发
	 * @param deviceId
	 * @return
	 */
	boolean noticeDeviceDownloadParamFileByDevice(List<Long> deviceIdList,List<Long>  versionNoList,long personId);
}
