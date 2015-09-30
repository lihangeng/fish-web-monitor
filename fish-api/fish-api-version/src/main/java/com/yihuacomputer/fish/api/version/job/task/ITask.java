package com.yihuacomputer.fish.api.version.job.task;

import java.util.Date;

import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.job.IJob;

/**
 *
 * 任务
 * 每一台设备和每个版本的关系构建成一个任务
 * (一台设备的一次升级过程叫做任务)
 * @author xuxigang
 *
 */
public interface ITask {
	public long getId();

	public void setId(long id);

	/**
	 * 设置设备
	 * @return
	 */
	public IDevice getDevice();
	/**
	 * 获得设备
	 * @param device
	 */
	public void setDevice(IDevice device);
	/**
	 * 获得下发的版本
	 * @return
	 */
	public IVersion getVersion();
	/**
	 * 设置下发的版本
	 * @param version
	 */
	public void setVersion(IVersion version);

	/**
	 * 设置任务状态
	 * @return
	 */
	public TaskStatus getStatus();
	/**
	 * 获得任务状态
	 * @param status
	 */
	public void setStatus(TaskStatus status);

	/**
	 * 获得任务计划开始时间
	 * @return
	 * @since 2.0
	 */
	public Date getPlanTime();
	/**
	 * 设置任务计划开始时间
	 * @param excuteTime
	 *  @since 2.0
	 */
	public void setPlanTime(Date planTime);

	/**
	 * 获得任务创建时间
	 * @return
	 * @since 2.0
	 */
	public Date getCreateTime();
	/**
	 * 设置任务创建时间
	 * @param excuteTime
	 *  @since 2.0
	 */
	public void setCreateTime(Date createTime);
	/**
	 * 获得执行时间
	 * @return
	 */
	public Date getExcuteTime();
	/**
	 * 设置任务执行时间
	 * @param excuteTime
	 */
	public void setExcuteTime(Date excuteTime);
	/**
	 * 设置执行结果
	 * @param success
	 */
	public void setSuccess(boolean success);
	/**
	 * 获得执行结果
	 * @return
	 */
	public boolean isSuccess();
	/**
	 * 设置失败原因
	 * @param reason
	 */
	public void setReason(String reason);
	/**
	 * 获得失败原因
	 * @return
	 */
	public String getReason();

	/**
	 * 设置任务归属的批次名称
	 * @param job
	 */
//	public void setJobName(String jobName);
	public void setJob(IJob job);
	/**
	 * 获得任务归属的批次名称
	 * 从２.０开始弱化ＩＪｏｂ的概念，只是一个普通属性，兼具定时器的作用
	 * @return
	 *
	 */
	public IJob getJob();
	public String getJobName();

	/**
	 * 得到状态
	 * 根据任务状态和执行结果组合起来的状态
	 * @return
	 */
	public String getState();
	/**
	 * 是否通知监控客户端成功
	 * @return
	 */
	public boolean isNoticedSuccess();
	/**
	 * 是否下发成功
	 * @return
	 */
	public boolean isDownloadSuccess();
	/**
	 * 是否部署成功
	 * @return
	 */
	public boolean isDeploySuccess();

    /**
     * 获得升级前的软件版本
     * @return
     */
    public String getVersionBeforeUpdate();
    /**
     * 设置升级前的软件版本
     * @param versionBeforeUpdate 软件分类_版本号
     */
    public void setVersionBeforeUpdate(String versionBeforeUpdate);

    /**
     * 获得预期的版本
     * @return
     */
    public String getExceptVersion();

    /**
     * 设置预期的版本
     */
    public void setExceptVersion(String exceptVersion);

    /**
     * 是否立即重启ATM,软重启
     * @return
     */
    public boolean isEagerRestart();
    public void setEagerRestart(boolean eagerRestart);

    /**
     * 设置任务类型
     * 默认是TaskType.MANUAL
     * @param taskType
     * @since 0.17
     */
    public void setTaskType(TaskType taskType);
    public TaskType getTaskType();

    /**
     * 获得部署开始时间
     * 江苏农行的要求
     * @return
     * @since 0.21
     */
    public Date getDeployStartDate();
    /**
     * 获得部署结束时间
     * 江苏农行的要求
     * @return
     * @since 0.21
     */
    public Date getDeployEndDate();

    public void setDeviceId(long deviceId);

    public long getDeviceId();

    /**
     * 获得执行服务器的ＩＰ信息
     * 在集群中使用
     * @return
     * @since 2.0
     */
    public String getExcuteMachine();

    /**
     * 设置执行任务服务器的IP信息
     * @param excuteMachine
     * @since 2.0
     */
    public void setExcuteMachine(String excuteMachine);

    /**
     * 获得文件的下载源，服务器的ＩＰ地址，或者是网点设备的的终端号/IP地址。
     * @return
     * @since 2.0
     */
    public String getDownSource();

    /**
     * 设置文件的下载源，服务器的ＩＰ地址，或者是网点设备的的终端号/IP地址。
     * @param downSource
     * @since
     */
    public void setDownSource(String downSource);

}
