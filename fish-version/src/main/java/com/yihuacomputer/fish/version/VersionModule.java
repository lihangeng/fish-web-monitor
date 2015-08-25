package com.yihuacomputer.fish.version;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IDeviceVersionService;
import com.yihuacomputer.fish.api.version.IVersionDownloadService;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.IVersionStaticsService;
import com.yihuacomputer.fish.api.version.IVersionStaticsStautsService;
import com.yihuacomputer.fish.api.version.IVersionTypeAtmTypeRelationService;
import com.yihuacomputer.fish.api.version.IVersionTypeService;
import com.yihuacomputer.fish.api.version.job.IJobService;
import com.yihuacomputer.fish.api.version.job.IUpdateDeployDateHistoryService;
import com.yihuacomputer.fish.api.version.job.task.ITaskCollection;
import com.yihuacomputer.fish.api.version.job.task.ITaskDetailService;
import com.yihuacomputer.fish.api.version.job.task.ITaskManager;
import com.yihuacomputer.fish.version.interceptor.VersionEntityInjector;
import com.yihuacomputer.fish.version.service.api.IDomainTaskService;
import com.yihuacomputer.fish.version.service.db.DeviceSoftVersionService;
import com.yihuacomputer.fish.version.service.db.DeviceVersionService;
import com.yihuacomputer.fish.version.service.db.JobService;
import com.yihuacomputer.fish.version.service.db.TaskDetailService;
import com.yihuacomputer.fish.version.service.db.TaskService;
import com.yihuacomputer.fish.version.service.db.UpdateDeployDateHistoryService;
import com.yihuacomputer.fish.version.service.db.VersionDownloadService;
import com.yihuacomputer.fish.version.service.db.VersionService;
import com.yihuacomputer.fish.version.service.db.VersionStaticsService;
import com.yihuacomputer.fish.version.service.db.VersionStaticsStatusService;
import com.yihuacomputer.fish.version.service.db.VersionTypeAtmTypeRelationService;
import com.yihuacomputer.fish.version.service.db.VersionTypeService;
import com.yihuacomputer.fish.version.task.TaskCollection;
import com.yihuacomputer.fish.version.task.TaskManager;

@Configuration
public class VersionModule {

	@Bean
	public IVersionTypeAtmTypeRelationService versionTypeAtmTypeRelationService() {
		return new VersionTypeAtmTypeRelationService();
	}

	@Bean
	public IVersionTypeService versionTypeService() {
		return new VersionTypeService();
	}

	@Bean
	public IVersionService versionService() {
		return new VersionService();
	}

	@Bean
	public IUpdateDeployDateHistoryService updateDeployDateHistoryService() {
		return new UpdateDeployDateHistoryService();
	}

	@Bean
	public IDeviceVersionService deviceVersionService() {
		return new DeviceVersionService();
	}

	@Bean
	public IDeviceSoftVersionService deviceSoftVersionService() {
		return new DeviceSoftVersionService();
	}

	@Bean
	public ITaskDetailService taskDetailService() {
		return new TaskDetailService();
	}

	/**
	 * 特别的配置，不是ITaskService
	 * @return
	 */
	@Bean
	public IDomainTaskService taskService() {
		return new TaskService();
	}

	@Bean
	public IJobService jobService(){
		return new JobService();
	}

	@Bean
	public IVersionStaticsService versionStaticsService(){
		return new VersionStaticsService();
	}

	@Bean
	public IVersionDownloadService versionDownloadService() {
		return new VersionDownloadService();
	}

	@Bean
	public VersionEntityInjector versionEntityInjector() {
		return new VersionEntityInjector();
	}

	@Bean
	public ITaskCollection taskCollection(){
		return  new TaskCollection();
	}

	@Bean
	public ITaskManager taskMgr(){
		return new TaskManager();
	}
	@Bean
	public IVersionStaticsStautsService versionStaticsStautsService(){
		return new VersionStaticsStatusService();
	}

}
