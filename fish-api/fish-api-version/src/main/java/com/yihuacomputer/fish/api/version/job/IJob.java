package com.yihuacomputer.fish.api.version.job;

import java.util.Date;
import java.util.List;

import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.job.task.ITask;

/**
 * 版本分发服务中关于作业的定义
 * @author xuxigang
 *
 */
public interface IJob {

	/**
	 *设置作业编号
	 *@param jobId
	 * */
	public void setJobId(long jobId);

	/**
	 * 获取作业编号
	 * @return
	 * */
	public long getJobId();
	/**
	 * 获得job名称
	 * @return
	 */
	public String getJobName();
	/**
	 * @param jobName
	 */
	public void setJobName(String jobName);

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
	 * 获得job备注信息
	 * @return
	 */
	public String getDesc();
	/**
	 * @param desc
	 */
	public void setDesc(String desc);
	/**
	 * 获取作业创建时间
	 * @return
	 */
	public Date getCreatedTime();
	/**
	 * @param createdTime
	 */
	public void setCreatedTime(Date createdTime);

	/**
	 * 获取定时job计划执行时间
	 * 实时作业默认为创建时间
	 * @return
	 */
	public Date getPlanTime();
	/**
	 * @param jobTime
	 */
	public void setPlanTime(Date jobTime);
	/**
	 * 获得作业开始执行时间
	 * @return
	 */
	public Date getStartTime();
	/**
	 * 设置作业开始执行时间
	 * @param startTime
	 */
	public void setStartTime(Date startTime);
	/**
	 * 获得作业执行完成时间
	 * @return
	 */
	public Date getFinishTime();
	/**
	 * 设置作业执行完成时间
	 * @param finishTime
	 */
	public void setFinishTime(Date finishTime);
	/**
	 * 设置作业类型
	 * @param jobType
	 * */
	public void setJobType(JobType jobType);

	/**
	 * 获取作业类型
	 * @return
	 * */
	public JobType getJobType();

	/**
	 * 获取作业状态
	 * @return
	 * */
	public JobStatus getJobStatus();

	/**
	 * @param jobStatus
	 */
	public void setJobStatus(JobStatus jobStatus);

	/**
	 * @return
	 */
	public JobPriority getJobPriority();

	/**
	 * 设置作业优先级
	 * @param jobPriority
	 * */
	public void setJobPriority(JobPriority jobPriority);


	/**
	 * 添加任务
	 * @param tasks
	 * */

	public void addTasks(List<ITask> tasks);

	/**
	 * @param task
	 */
	public void addTask(ITask task);

	/**
	 * 获取任务列表
	 * @return
	 */
	public List<ITask> getTasks();
	/**
	 * 获取任务个数
	 * @return
	 */
	public int getTaskSize();

    /**
     * 设置开始部署时间
     * @param deployStartDate
     * @since 0.21
     */
    public void setDeployStartDate(Date deployStartDate);
    /**
     * @return
     */
    public Date getDeployStartDate();

    /**
     * 设置部署结束时间，
     * 不是必须的，
     * @param deployEndDate
     * @since 0.21
     */
    public void setDeployEndDate(Date deployEndDate);
    /**
     * @return
     */
    public Date getDeployEndDate();

    /**
	 * 版本下发人
	 * @param user
	 */
	public void setCreateUser(IUser user);
	/**
	 * @param userId
	 */
	public void setCreateUserId(long userId);
	/**
	 * @return
	 */
	public IUser getCreateUser();

	/**
	 * 取消上一次正在升级版本标志
	 * @param cancelPreVer
	 */
	public void setCancelPreVer(long cancelPreVer) ;
	/**
	 * @return
	 */
	public long getCancelPreVer() ;

	/**
	 * 重启升级标志
	 * @param rebootUpdate
	 */
	public void setRebootUpdate(long rebootUpdate) ;
	/**
	 * @return
	 */
	public long getRebootUpdate() ;
	
	/**
	 * 集群服务器IP
	 * @return
	 */
	public String getServerIp();
	/**
	 * @param serverIp
	 */
	public void setServerIp(String serverIp);
	
	/**
	 * 当前任务对应的下发版本下发次数
	 * @return
	 */
	public int getDownCounter();
	/**
	 * @param downCounter
	 */
	public void setDownCounter(int downCounter);
}
