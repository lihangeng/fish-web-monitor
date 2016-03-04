package com.yihuacomputer.fish.version.service.base;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.version.entity.Job;
import com.yihuacomputer.fish.version.service.api.IDomainJobService;

/**
 * @author xuxigang
 * 
 */
public abstract class DomainJobService implements IDomainJobService {

	@Autowired
	private ITaskService taskService;

	@Override
	public Job make() {
		return new Job();
	}

	public ITaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}
	
	

}
