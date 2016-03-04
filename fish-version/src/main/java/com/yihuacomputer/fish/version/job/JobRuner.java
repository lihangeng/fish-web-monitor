package com.yihuacomputer.fish.version.job;

import java.lang.Thread.State;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.fish.api.version.job.JobStatus;
import com.yihuacomputer.fish.version.entity.Job;
import com.yihuacomputer.fish.version.service.api.IDomainJobService;
import com.yihuacomputer.fish.version.task.TaskQueue;
import com.yihuacomputer.fish.version.task.TaskThreadPool;

/**
 * 在整个系统只保存一份JobRuner实例 作业是一个一个执行的，只有一个作业执行完毕，才会执行下一个作业 一个作业的执行由一个作业线程自己负责
 * 
 * @author xuxigang
 * 
 */
public class JobRuner implements Runnable {

	private Logger logger = LoggerFactory.getLogger(JobRuner.class);

	private Thread currentThread;
	private JobQueue jobQueue;
	private IDomainJobService jobService;
	private State jobRunerState;
	private JobThread  currentJobThread;
	private TaskThreadPool taskThreadPool;
	private TaskQueue taskQueue;
	
	public void setTaskThreadPool(TaskThreadPool taskThreadPool,TaskQueue taskQueue){
		this.taskThreadPool = taskThreadPool;
		this.taskQueue = taskQueue;
	}
	
	public TaskQueue getTaskQueue() {
		return taskQueue;
	}

	public JobThread getCurrentJobThread() {
        return this.currentJobThread;
    }

    public JobRuner() {
		this.jobRunerState = State.BLOCKED;
	}

	public void setJobQueue(JobQueue jobQueue) {
		this.jobQueue = jobQueue;
	}

	public void setJobService(IDomainJobService jobService) {
		this.jobService = jobService;
	}

	public void setJobRunerState(State jobRunerState) {
		this.jobRunerState = jobRunerState;
	}
	
	

	/**
	 * 调度作业执行
	 * */
	public synchronized void run() {
	
		while (true) {
			Job job = this.jobQueue.getNextJob();

			if (job != null) {
				JobThread jobThread = new JobThread(job);// 创建一个作业线程，但是还没有启动
				jobThread.setJobRuner(this);
				jobThread.setTaskThreadPool(taskThreadPool,this.taskQueue);
				this.runJob(jobThread);// 启动创建好的线程
				// 更新作业状态
				job.setJobStatus(JobStatus.RUN);
				jobService.onlyUpdateJob(job);
			} 

			synchronized (this) {
				try {
					this.wait();
				} catch (InterruptedException e) {						
					logger.error(String.format("Job Thread wait error![%s]",e));
					break;
				}
			}
		}		
	}
	public void close(){
		if(currentThread!=null){
			currentThread.interrupt();
			logger.error("Job closed！");
		}
	}

	/**
	 * 完成作业执行
	 * 
	 * @param job
	 */
	public void finishJob(Job job) {
		job.setJobStatus(JobStatus.COMPLETE);
		this.jobService.onlyUpdateJob(job);// 更新状态
		this.jobQueue.removeJob(job);// 从队列中删除
		this.setJobRunerState(State.WAITING);
		this.currentJobThread = null;
		this.currentThread = null;
		this.notifyJobRuner();
	}

	/**
	 * 唤醒继续工作
	 * */
	public synchronized void notifyJobRuner() {
		try {
			synchronized (this) {
				if (!this.jobRunerState.equals(State.RUNNABLE)) {
					this.notify();
				}
			}

		} catch (Exception e) {
			logger.error("Notify job runner error！[%s]",e.getMessage());
		}
	}

	/**
	 * 暂停作业
	 * */
	public void suspendJob(Job job) {
		if (this.currentThread.getName().equals(getThreadName(job))) {
			job.setJobStatus(JobStatus.PAUSE);
			jobService.onlyUpdateJob(job);// 修改在数据库中的状态
			this.currentThread.interrupt();//中断线程
			this.setJobRunerState(State.WAITING);
			this.notifyJobRuner();
		}
	}

	/**
	 * 恢复作业
	 * */

	public void resumeJob(Job job) {
		job.setJobStatus(JobStatus.READY_RUN);
		jobService.onlyUpdateJob(job);// 修改在数据库中的状态
		this.notifyJobRuner();
	}

	/**
	 * 启动线程
	 * */
	private void runJob(JobThread job) {
		this.setJobRunerState(State.RUNNABLE);
		this.currentJobThread = job;
		this.currentThread = new Thread(job);
		currentThread.setName(getThreadName(job.getJob()));
		//currentThread.setDaemon(false);
		currentThread.start();// 这里才真正的启动线程
	}
	
	private String getThreadName(Job job){
		return String.format("jobThread_%s",job.getJobId());
	}

}
