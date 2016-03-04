package com.yihuacomputer.fish.version.job;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.version.job.JobStatus;
import com.yihuacomputer.fish.version.entity.Job;

public class JobQueue {

	private static int jobQueueLength = 50;//默认作业队列中只放50个作业
	private static List<Job> jobQueue = new ArrayList<Job>();

	/**
	 * 从作业队列中获取下一个需要被执行的作业, 作业队列中只有状态为READY_RUN的作业才能被加载执行
	 * */
	public synchronized Job getNextJob() {
		Job nextJob = null;
		for (Job job : JobQueue.jobQueue) {
			if (job.getJobStatus().equals(JobStatus.READY_RUN) && (nextJob == null || nextJob.getJobPriority().compareTo(job.getJobPriority()) < 0)) {
				nextJob = job;
			}
		}
		return nextJob;
	}

	/**
	 * 从作业队列中获取作业
	 * */
	public Job getJobById(long jobId) {
		for (Job job : JobQueue.jobQueue) {
			if (job.getJobId() == jobId) {
				return job;
			}
		}

		return null;
	}

	/**
	 * 完成一个作业，需要从jobQueue中将响应的队列删除
	 * */
	public void removeJob(Job job) {
		if (job != null) {
			JobQueue.jobQueue.remove(job);
		}
	}

	/**
	 * 向队列中增加一个作业
	 * */
	public boolean addJob(Job job) {
		if (this.canAddJob()) {
			JobQueue.jobQueue.add(job);
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 判断能否向作业队列中增加作业
	 * */
	public synchronized boolean canAddJob() {
		if (JobQueue.jobQueueLength > jobQueue.size()) {
			return true;
		} else {
			return false;
		}
	}

	public List<Job> list() {
		return JobQueue.jobQueue;
	}

	public int getJobQueueLength(){
		return JobQueue.jobQueueLength;
	}
	public int getActiveJobQueueLength(){
		return jobQueue.size();
	}

}
