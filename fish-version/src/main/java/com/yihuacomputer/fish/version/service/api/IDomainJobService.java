/**
 * 
 */
package com.yihuacomputer.fish.version.service.api;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.job.IJob;
import com.yihuacomputer.fish.api.version.job.IJobService;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.version.entity.Job;

/**
 * @author xuxigang
 *
 */
public interface IDomainJobService extends IJobService{
	public ITaskService getTaskService();
	/**
	 * 获得需要重新加载的作业
	 * @return
	 */
	public List<IJob> findReloadJob();
	
	@Override
	public Job cascadeAdd(IJob job,IFilter fitler);
	
	@Override
	public Job getById(long jobId);
	
	public int getNotRemovedTasks(IJob job);
	
	public int getRelationJobSzie(IVersion version);
	
	public IUserService getUserService();
	
}
