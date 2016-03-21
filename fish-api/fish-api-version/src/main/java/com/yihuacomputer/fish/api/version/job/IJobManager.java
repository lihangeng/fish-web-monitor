package com.yihuacomputer.fish.api.version.job;

import java.util.Date;
import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.version.job.task.ITask;

/**
 * Job管理器
 * 
 * @author xuxigang
 * 
 */
public interface IJobManager {

	public void init();
	/**
	 * 创建一个作业
	 * */
	public IJob createJob(String jobName, long versionId, JobType jobType, JobPriority jobPriority, Date schedulerDate, List<ITask> tasks);

	public IJob createJob(IJob job,IFilter filter);

	public IJob createManualJob(String jobName, long versionId, JobPriority jobPriority, List<ITask> tasks);

	public IJob createSchedulerJob(String jobName, long versionId, JobPriority jobPriority, List<ITask> tasks);

	public IJob createSchedulerJob(String jobName, long versionId, List<ITask> tasks);

	/**
	 * 取消一个作业
	 * */
	public void cancelJob(long jobId);
	
	/**
     * 取消一个任务
     * @param taskId 任务ID
     */
    public void cancelTask(long jobId,long taskId);

	/**
	 * 暂停执行作业
	 * */
	public void suspendJob(long jobId);

	/**
	 * 恢复暂停的作业执行
	 * */
	public void resumeJob(long jobId);

	/**
	 * 加载系统异常终止作业
	 * */
	public void loadDowntimeJobs();

	/**
	 * 分页显示没有完成的作业
	 * 
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IJob> pageDowntimeJobs(int offset, int limit, IFilter filter);

}
