package com.yihuacomputer.fish.version.job;

import java.lang.Thread.State;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.AppException;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.job.IJob;
import com.yihuacomputer.fish.api.version.job.IJobManager;
import com.yihuacomputer.fish.api.version.job.IJobManangerStatus;
import com.yihuacomputer.fish.api.version.job.JobPriority;
import com.yihuacomputer.fish.api.version.job.JobStatus;
import com.yihuacomputer.fish.api.version.job.JobType;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
import com.yihuacomputer.fish.version.entity.Job;
import com.yihuacomputer.fish.version.scheduler.Scheduler;
import com.yihuacomputer.fish.version.service.api.IDomainJobService;
import com.yihuacomputer.fish.version.task.TaskManager;
import com.yihuacomputer.fish.version.task.TaskQueue;
import com.yihuacomputer.fish.version.task.TaskThreadPool;

public class JobManager implements IJobManager,IJobManangerStatus {

	private Logger logger = LoggerFactory.getLogger(JobManager.class);

	private JobRuner jobRuner;

	private TaskManager taskManager;

	private JobQueue jobQueue;

	private Thread jobManager;

	private Thread taskManagerThread;

	private TaskThreadPool taskThreadPool;

	@Autowired
	private IDomainJobService jobService;
	@Autowired
	private IVersionService versionService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private MessageSource messageSources;
	@Autowired
	private TaskQueue taskQueue;
	

	/**
	 * 初始化作业调度管理器
	 * */
	private void initJobManager() {
		jobRuner = new JobRuner();
		jobRuner.setJobQueue(jobQueue);
		jobRuner.setJobService(jobService);
		jobRuner.setTaskThreadPool(taskThreadPool,taskQueue);

		jobManager = new Thread(jobRuner);
		jobManager.setName("Job_Manager");
		jobManager.start();
		logger.info(String.format("started thread jobManager = %s" ,jobManager.getName()));
	}

	/**
	 * 初始化任务调度管理器
	 * */
	private void initTaskManager() {
		taskManager = new TaskManager();
		taskManager.setTaskService(taskService,taskThreadPool,taskQueue,messageSources);
		taskManagerThread = new Thread(taskManager);
		taskManagerThread.setName("Task_Manager");
		taskManagerThread.start();
		logger.info(String.format("started thread taskManager = %s" ,taskManagerThread.getName()));
	}

	/**
	 * 默认的构造函数
	 */
	public JobManager() {
	}
	@PostConstruct
	public void init() {
		this.taskThreadPool = new TaskThreadPool(taskQueue);
		this.jobQueue = new JobQueue();
		this.initJobManager();
		this.initTaskManager();
		this.loadDowntimeJobs();
	}

	@PreDestroy
	public void stop(){
		this.taskThreadPool.close();
		this.taskManagerThread.interrupt();

		this.jobRuner.close();
		this.jobManager.interrupt();
		logger.info("Version mananger Theads closed！");

	}
	/**
	 * 作业调度
	 * */
	public void despatchJob(Job job) {
		Scheduler scheduler = new Scheduler();
		scheduler.setJobRuner(this.jobRuner);
		job.setScheduler(scheduler);
		scheduler.schedulerJob(job);
	}

	public IJob createJob(IJob job,IFilter filter) {
		// 如果没有入库，则入库
		IJob jobValue = job;
		if (jobValue.getJobId() < 1) {
			jobValue = jobService.cascadeAdd(jobValue,filter);
		}
		Job entity = (Job)jobValue;
		// 放到缓存队列
		boolean success = this.jobQueue.addJob(entity);
		// 放入缓存成功才调度作业
		if(success){
			this.despatchJob(entity);
		}else{
			logger.warn(String.format("job[%s] not be despatched",entity.getJobId()));
		}
		return entity;
	}

	/**
	 * 创建作业，并将作业放到JobQueue中
	 * */
	public IJob createJob(String jobName, long versionId, JobType jobType, JobPriority jobPriority, Date schedulerDate, List<ITask> tasks) {
		IJob job = jobService.make();
		job.setJobName(jobName);
		job.setVersion(versionService.getById(versionId));
		job.setJobType(jobType);
		job.setJobPriority(jobPriority);
		if (jobType.equals(JobType.SCHEDULER)) {
			job.setPlanTime(schedulerDate);
			job.setJobStatus(JobStatus.SCHEDULER);
		} else {
			job.setJobStatus(JobStatus.NEW);
			job.setPlanTime(new Date());
		}
		job.addTasks(tasks);

		return createJob(job,null);
	}

	/**
	 * 删除作业
	 * */
	public void cancelJob(long jobId) {
		Job jobInQueue = this.jobQueue.getJobById(jobId);

		//作业不在作业队列中 表明作业还没有加入队列或者作业已执行完毕
		if (jobInQueue == null) {//update  @since 1.4.0
			return;
		}
		logger.info("jobQueue status "+getI18NInfo(jobInQueue.getJobStatus().getText()));
		//作业在作业队列中，直接删除“新建”、“计划中”的作业(物理删除)
		if(jobInQueue.getJobStatus().equals(JobStatus.NEW) || jobInQueue.getJobStatus().equals(JobStatus.SCHEDULER)){
            jobInQueue.getScheduler().cancelTime();//取消定时器
            this.jobQueue.removeJob(jobInQueue);//从作业队列中移除
            this.jobService.cascadeDelete(jobInQueue);//删除作业及修改/删除相关的信息
			return ;
		}

		//作业在作业队列中，直接删除“准备运行”的作业(物理删除)
		if(jobInQueue.getJobStatus().equals(JobStatus.READY_RUN)){
	        this.jobQueue.removeJob(jobInQueue);//从作业队列中移除
	        this.jobService.cascadeDelete(jobInQueue);//删除作业及修改/删除相关的信息
            return ;
		}

		//作业正在运行，仅删除任务列表中“新建”状态的任务（逻辑删除）
		if(jobInQueue.getJobStatus().equals(JobStatus.RUN)){//从作业队列中移除
			jobService.batchCancelTaskByJob(jobId);
			jobInQueue.setJobStatus(JobStatus.FINISH);
		}else{
			String tip = getI18NInfos("job.exception.tip.cantCancelTask",new Object[]{getI18NInfo(jobInQueue.getJobStatus().getText())});
			logger.warn(tip);
			throw new AppException(tip);
		}
	}

	private String getI18NInfo(String code){
		if(null==code){
    		return "";
    	}
		return messageSources.getMessage(code, null, FishCfg.locale);
	}
	private String getI18NInfos(String code,Object[] args){
		if(null==code){
    		return "";
    	}
		return messageSources.getMessage(code, args, FishCfg.locale);
	}

	/**
	 * 取消一个任务
	 * @param taskId 任务ID
	 */
	public void cancelTask(long jobId,long taskId){
	    Job jobInQueue = this.jobQueue.getJobById(jobId);
	    jobInQueue = jobService.getById(jobId);
	    if(jobInQueue.getJobType().equals(JobType.SCHEDULER)){
	    	ITask task = taskService.get(taskId);
	    	if(task.getStatus().equals(TaskStatus.NEW)){
	    		task.setStatus(TaskStatus.CANCELED);
	    		taskService.updateTask(task);
	    	}
	    	return;
	    }
	    ITask task = findTask(jobInQueue,taskId);
	    if(task != null){
	        if(task.getStatus().equals(TaskStatus.NEW)){
                taskService.cancelTask(task);
	        }else{
	            throw new AppException(getI18NInfo("job.exception.tip.onlyCancelNewTask"));
	        }
	    }else{
	        task = taskService.get(taskId);
	        if(task != null){
	            taskService.cancelTask(task);
	        }
	    }
	}

	private ITask findTask(Job jobInQueue,long taskId){
	    for(ITask task : jobInQueue.getTasks()){
	        if(task.getId() == taskId){
	            return task;
	        }
	    }
	    return null;
	}

	/**
	 * 开始运行一个作业
	 * --无用
	 * @param jobId
	 */
	public void startJob(long jobId){
		Job entity = jobService.getById(jobId);
		// 放到缓存队列
		boolean success = this.jobQueue.addJob(entity);
		// 放入缓存成功才调度作业
		if(success){
			this.despatchJob(entity);
		}else{
			throw new AppException(String.format("job[%s] not be despatched",entity.getJobId()));
		}
	}

	/**
	 * 挂起作业（暂停作业）
	 * --无用
	 */
	public void suspendJob(long jobId) {
		Job jobInQueue = this.jobQueue.getJobById(jobId);
		if (jobInQueue == null) {
			logger.warn(String.format("Not found job[id = %s] in queue.", jobId));
			return;
		}
		this.jobRuner.suspendJob(jobInQueue);
	}

	/**
	 * 恢复作业
	 * --无用
	 */
	public void resumeJob(long jobId) {
		Job jobInQueue = this.jobQueue.getJobById(jobId);
		if (jobInQueue == null) {
			logger.warn(String.format("Not found job[id = %s] in queue.", jobId));
			return;
		}
		this.jobRuner.resumeJob(jobInQueue);
	}

	// 系统启动的时候，自动加载没有完成的作业
	public void loadDowntimeJobs() {
		List<IJob> jobs = jobService.findReloadJob();
		for (IJob job : jobs) {
			this.createJob(job,null);
		}
	}

	public IPageResult<IJob> pageDowntimeJobs(int offset, int limit, IFilter filter) {
		return jobService.page(offset, limit, filter);
	}

	@Override
	public IJob createSchedulerJob(String jobId, long versionId, JobPriority jobPriority, List<ITask> tasks) {
		return createJob(jobId, versionId, JobType.SCHEDULER, jobPriority, null, tasks);
	}

	@Override
	public IJob createSchedulerJob(String jobId, long versionId, List<ITask> tasks) {
		return createSchedulerJob(jobId, versionId, JobPriority.GENERAL, tasks);
	}

	@Override
	public IJob createManualJob(String jobId, long versionId, JobPriority jobPriority, List<ITask> tasks) {
		return createJob(jobId, versionId, JobType.MANUAL, jobPriority, null, tasks);
	}

	@Override
	public State getJobManagerState() {
		return jobManager.getState();
	}

	@Override
	public State getTaskMangerState() {
		return taskManagerThread.getState();
	}

	@Override
	public int getMaxJobCount() {
		return jobQueue.getJobQueueLength();
	}

	@Override
	public int getJobQueueCount() {
		return jobQueue.getActiveJobQueueLength();
	}

	@Override
	public int getActiveTaskCount() {
		if(taskThreadPool.getTaskExecutor()!=null){
			return taskThreadPool.getTaskExecutor().getActiveCount();
		}else{
			return -1;
		}
	}
}
