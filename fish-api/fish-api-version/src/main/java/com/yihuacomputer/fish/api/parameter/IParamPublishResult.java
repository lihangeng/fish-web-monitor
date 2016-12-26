package com.yihuacomputer.fish.api.parameter;

import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;

/**
 * @author YiHua
 *
 */
public interface IParamPublishResult {
	/**
	 * @return
	 */
	public long getId();
	/**
	 * @param id
	 */
	public void setId(long id) ;

	/**
	 * 归属下发批次
	 * @return
	 */
	public IParamPublish getParamPublish() ;
	/**
	 * 归属下发批次
	 * @param paramPublish
	 */
	public void setParamPublish(IParamPublish paramPublish) ;

	/**
	 * 下发的设备
	 * @return
	 */
	public IDevice getDevice() ;
	/**
	 * 下发的设备
	 * @param deviceId
	 */
	public void setDevice(IDevice device) ;

	/**
	 * 下发的设备
	 * @return
	 */
	public long getDeviceId() ;
	/**
	 * 下发的设备
	 * @param deviceId
	 */
	public void setDeviceId(long deviceId) ;
	
	/**
	 * 下发的版本
	 * @return
	 */
	public long getVersionNo() ;
	/**
	 * 下发的版本
	 * @param versionNo
	 */
	public void setVersionNo(long versionNo) ;

	/**
	 * 下发开始时间
	 * @return
	 */
	public String getDownloadStartTime() ;
	/**
	 * 下发开始时间
	 * @param downloadStartTime
	 */
	public void setDownloadStartTime(String downloadStartTime) ;

	/**
	 * 下发结束时间
	 * @return
	 */
	public String getDownloadFinishTime() ;
	/**
	 * 下发结束时间
	 * @param downloadFinishTime
	 */
	public void setDownloadFinishTime(String downloadFinishTime) ;

	/**
	 * 下发结果
	 * @return
	 */
	public boolean isSuccess() ;
	/**
	 * 下发结果
	 * @param success
	 */
	public void setSuccess(boolean success) ;

	/**
	 * 下发失败原因
	 * @return
	 */
	public String getReason() ;
	/**
	 * 下发失败原因
	 * @param reason
	 */
	public void setReason(String reason) ;

	/**
	 * 下发状态
	 * @return
	 */
	public TaskStatus getRet() ;
	/**
	 * 下发状态
	 * @param ret
	 */
	public void setRet(TaskStatus ret) ;
}
