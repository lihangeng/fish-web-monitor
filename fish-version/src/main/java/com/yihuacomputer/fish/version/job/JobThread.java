/**
 *
 */
package com.yihuacomputer.fish.version.job;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yihuacomputer.fish.api.version.job.JobStatus;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
import com.yihuacomputer.fish.version.entity.Job;
import com.yihuacomputer.fish.version.task.TaskQueue;
import com.yihuacomputer.fish.version.task.TaskThreadPool;

/**
 * @author xuxigang
 *
 */
public class JobThread implements Runnable {
	private Logger logger = LoggerFactory.getLogger(JobThread.class);

	private Job job;
	private JobRuner jobRuner;
	private TaskThreadPool taskThreadPool;
	private TaskQueue taskQueue;

	public JobThread() {
	}

	public void setTaskThreadPool(TaskThreadPool taskThreadPool,TaskQueue taskQueue){
		this.taskThreadPool = taskThreadPool;
		this.taskQueue = taskQueue;
	}
	
	public TaskQueue getTaskQueue() {
		return taskQueue;
	}

	public JobThread(Job job) {
		this.job = job;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public void setJobRuner(JobRuner jobRuner) {
		this.jobRuner = jobRuner;
	}

	public JobRuner getJobRuner() {
		return this.jobRuner;
	}

	/**
	 * 执行作业
	 * */
	public synchronized void run() {
		try {
			logger.info("start to run a job" + this.job.toString());
			List<ITask> tasks = this.job.getTasks();			
        	Collections.shuffle(tasks);//对队列进行混乱,使下发无序
			for (ITask task : tasks) {
				if(job.getJobStatus().equals(JobStatus.FINISH)){
					break;
				}
					//没有下载成功,没有通知成功，新建的任务放入任务队列
					if(TaskStatus.canRun(task.getStatus())){
						this.getTaskQueue().getTaskQueue().put(task);
					}else{
						logger.info(String.format("ignore a task [%s]",task.toString()));
					}
				}
		} catch (Exception e) {
			logger.error(String.format("execute job error [%s]",e.getMessage()));
		}
		while(true){
		    try {
                Thread.sleep(30000);
            }
            catch (InterruptedException e) {
            	logger.error(String.format("InterruptedException is [%s]",e.getMessage()));
            }
		    if(taskThreadPool.getTaskExecutor().getActiveCount()==0){
		        break;
		    }
		}
		this.jobRuner.finishJob(this.job);
		logger.info("Job closed!");
	}

	/**
	 * 结束任务
	 * */
	public void notifyTask(String devNo) {
		// this.get
	}

	/**
	 * 启动线程
	 *
	 * @param runnable 线程
	 */
	public void thread(Runnable runnable) {
		Thread thread = new Thread(runnable);
		thread.setName(String.valueOf(this.getJob().getJobId()));
		thread.setDaemon(false);
		thread.start();
	}

}
