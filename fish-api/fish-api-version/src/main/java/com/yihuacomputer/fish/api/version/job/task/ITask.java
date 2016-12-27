package com.yihuacomputer.fish.api.version.job.task;

import java.util.Date;

import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.job.IJob;

/**
 * 作业中的任务
 *
 * @author xuxigang
 *
 */
public interface ITask {
    /**
     * @return
     */
    public long getId();

    /**
     * @param id
     */
    public void setId(long id);

    /**
     * 设置设备
     *
     * @return
     */
     public IDevice getDevice();
    /**
     * 获得设备
     *
     * @param device
     */
     public void setDevice(IDevice device);
    /**
     * 获得下发的版本
     *
     * @return
     */
    public IVersion getVersion();

    /**
     * 设置下发的版本
     *
     * @param version
     */
    public void setVersion(IVersion version);

    /**
     * 设置任务状态
     *
     * @return
     */
    public TaskStatus getStatus();

    /**
     * 获得任务状态
     *
     * @param status
     */
    public void setStatus(TaskStatus status);

    /**
     * 获得执行时间
     *
     * @return
     */
    public Date getExcuteTime();

    /**
     * 设置任务执行时间
     *
     * @param excuteTime
     */
    public void setExcuteTime(Date excuteTime);

    /**
     * 设置执行结果
     *
     * @param success
     */
    public void setSuccess(boolean success);

    /**
     * 获得执行结果
     *
     * @return
     */
    public boolean isSuccess();

    /**
     * 设置失败原因
     *
     * @param reason
     */
    public void setReason(String reason);

    /**
     * 获得失败原因
     *
     * @return
     */
    public String getReason();

    /**
     * 设置隶属于的作业
     *
     * @param job
     */
    public void setJob(IJob job);

    /**
     * 获得隶属的作业
     *
     * @return
     */
    public IJob getJob();

    /**
     * 得到状态 根据任务状态和执行结果组合起来的状态
     *
     * @return
     */
    public String getState();

    /**
     * 是否通知监控客户端成功
     *
     * @return
     */
    public boolean isNoticedSuccess();

    /**
     * 是否下发成功
     *
     * @return
     */
    public boolean isDownloadSuccess();

    /**
     * 是否部署成功
     *
     * @return
     */
    public boolean isDeploySuccess();

    /**
     * 获得升级前的软件版本
     *
     * @return
     */
    public String getVersionBeforeUpdate();

    /**
     * 设置升级前的软件版本
     *
     * @param versionBeforeUpdate
     *            软件分类_版本号
     */
    public void setVersionBeforeUpdate(String versionBeforeUpdate);

    /**
     * 获得预期的版本
     *
     * @return
     */
    public String getExceptVersion();

    /**
     * 设置预期的版本
     * @param exceptVersion
     */
    public void setExceptVersion(String exceptVersion);

    /**
     * 是否立即重启ATM,软重启
     *
     * @return
     */
    public boolean isEagerRestart();

    /**
     * @param eagerRestart
     */
    public void setEagerRestart(boolean eagerRestart);

    /**
     * 设置任务类型 默认是TaskType.MANUAL
     *
     * @param taskType
     * @since 0.17
     */
    public void setTaskType(TaskType taskType);

    /**
     * @return
     */
    public TaskType getTaskType();

    /**
     * 获得部署开始时间 江苏农行的要求
     *
     * @return
     * @since 0.21
     */
    public Date getDeployStartDate();

    /**
     * 获得部署结束时间 江苏农行的要求
     *
     * @return
     * @since 0.21
     */
    public Date getDeployEndDate();

    /**
     * 版本下发进度 江苏农信
     * @return
     * */
    public double getProcess();

    /**
     * @param process
     */
    public void setProcess(double process);

    /**
     * @return
     */
    public long getDeviceId();

    /**
     * @param deviceId
     */
    public void setDeviceId(long deviceId);


    /**
     * 版本文件下载开始时间
     * @param downloadStartTime
     */
    public void setDownloadStartTime(String downloadStartTime) ;

    /**
     * @return
     */
    public String getDownloadStartTime() ;

    /**
     * 版本文件下载完成时间
     * @param downloadFinishTime
     */
    public void setDownloadFinishTime(String downloadFinishTime) ;

    /**
     * @return
     */
    public String getDownloadFinishTime() ;
	/**
	 * @return
	 */
	public long getDownloadTime();

	/**
	 * @param downloadTime
	 */
	public void setDownloadTime(long downloadTime) ;
}
