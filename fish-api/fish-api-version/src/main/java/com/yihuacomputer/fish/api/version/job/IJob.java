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
	 * */
	public void setJobId(long jobId);

	/**
	 * 获取作业编号
	 * */
	public long getJobId();
	/**
	 * 获得job名称
	 * @return
	 */
	public String getJobName();
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
	public void setDesc(String desc);
	/**
	 * 获取作业创建时间
	 */
	public Date getCreatedTime();
	public void setCreatedTime(Date createdTime);

	/**
	 * 获取定时job计划执行时间
	 * 实时作业默认为创建时间
	 * @return
	 */
	public Date getPlanTime();
	public void setPlanTime(Date jobTime);
	/**
	 * 获得作业开始执行时间
	 * @return
	 */
	public Date getStartTime();
	/**
	 * 设置作业开始执行时间
	 * @param startExecuteTime
	 */
	public void setStartTime(Date startTime);
	/**
	 * 获得作业执行完成时间
	 * @return
	 */
	public Date getFinishTime();
	/**
	 * 设置作业执行完成时间
	 * @param finishExecuteTime
	 */
	public void setFinishTime(Date finishTime);
	/**
	 * 设置作业类型
	 * */
	public void setJobType(JobType jobType);

	/**
	 * 获取作业类型
	 * */
	public JobType getJobType();

	/**
	 * 获取作业状态
	 * */
	public JobStatus getJobStatus();

	public void setJobStatus(JobStatus jobStatus);

	public JobPriority getJobPriority();

	/**
	 * 设置作业优先级
	 * */
	public void setJobPriority(JobPriority jobPriority);


	/**
	 * 添加任务
	 * */

	public void addTasks(List<ITask> tasks);

	public void addTask(ITask task);

	/**
	 * 获取任务列表
	 */

	public List<ITask> getTasks();
	/**
	 * 获取任务个数
	 */
	public int getTaskSize();

    /**
     * 设置开始部署时间
     * @param deployStartdate
     * @since 0.21
     */
    public void setDeployStartDate(Date deployStartDate);
    public Date getDeployStartDate();

    /**
     * 设置部署结束时间，
     * 不是必须的，
     * @param deployEndDate
     * @since 0.21
     */
    public void setDeployEndDate(Date deployEndDate);
    public Date getDeployEndDate();

    /**
	 * 版本下发人
	 * @param user
	 */
	public void setCreateUser(IUser user);
	public void setCreateUserId(long userId);
	public IUser getCreateUser();

	/**
	 * 取消上一次正在升级版本标志
	 * @param cancelPreVer
	 */
	public void setCancelPreVer(long cancelPreVer) ;
	public long getCancelPreVer() ;

	/**
	 * 重启升级标志
	 * @param rebootUpdate
	 */
	public void setRebootUpdate(long rebootUpdate) ;
	public long getRebootUpdate() ;
	
	/**
	 * 集群服务器IP
	 * @return
	 */
	public String getServerIp();
	public void setServerIp(String serverIp);

}
