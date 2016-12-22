package com.yihuacomputer.fish.version.scheduler;

import java.util.Timer;
import java.util.TimerTask;

import com.yihuacomputer.fish.api.version.job.JobStatus;
import com.yihuacomputer.fish.version.entity.Job;
import com.yihuacomputer.fish.version.job.JobRuner;

/**
 * 作业调度
 * 一个作业构成一个调度器
 * @author xuxigang
 *
 */
public class Scheduler {

	private Timer timer = new Timer();
	private JobRuner jobRuner;
	private Job job;

	public void setJobRuner(JobRuner jobRuner) {
		this.jobRuner = jobRuner;
	}

	/**
	 * 关闭定时器
	 * */
	public synchronized void cancelTime() {
		timer.cancel();
		job.setScheduler(null);//取消关联，让垃圾回收期收走Scheduler
	}
	
	//调度作业
	public void schedulerJob(Job jobPara){
		this.job = jobPara;
		timer.schedule(new TimerTask(){
			/**
			 * 定时任务启动之后将作业的状态置为READY_RUN，并且通知JobRuner去执行
			 * */
			@Override
			public void run() {
				if (job != null) {
					job.setJobStatus(JobStatus.READY_RUN);
				}
				// 该线程结束之后需要销毁父线程
				cancelTime();
				// 通知作业线程调度作业
				jobRuner.notifyJobRuner();
			}
		}, job.getPlanTime());
	}
	
}
