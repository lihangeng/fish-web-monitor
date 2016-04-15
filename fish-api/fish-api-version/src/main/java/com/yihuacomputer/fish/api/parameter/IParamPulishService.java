package com.yihuacomputer.fish.api.parameter;

/**
 * 执行参数下发
 * @author GQ
 *
 */
public interface IParamPulishService {
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
	long generateParamFileByDevice(long deviceId);
	
	/**
	 * 模板参数下发
	 * @param templateId
	 * @return
	 */
	boolean noticeDeviceDownloadParamFileByTemplate(long templateId,long versionNo);
	
	/**
	 * 设备参数下发
	 * @param deviceId
	 * @return
	 */
	boolean noticeDeviceDownloadParamFileByDevice(long deviceId,long versionNo);
}
