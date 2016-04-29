package com.yihuacomputer.fish.version;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.advert.bs.service.AdvertGroupDeviceRelationService;
import com.yihuacomputer.fish.advert.bs.service.AdvertGroupService;
import com.yihuacomputer.fish.advert.bs.service.BsAdvertResourceService;
import com.yihuacomputer.fish.advert.bs.service.BsAdvertService;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupDeviceRelationService;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupService;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvertResourceService;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvertService;
import com.yihuacomputer.fish.api.parameter.IAppSystemService;
import com.yihuacomputer.fish.api.parameter.IParamClassifyService;
import com.yihuacomputer.fish.api.parameter.IParamDeviceDetailService;
import com.yihuacomputer.fish.api.parameter.IParamElementService;
import com.yihuacomputer.fish.api.parameter.IParamPublishAppResultService;
import com.yihuacomputer.fish.api.parameter.IParamPublishResultService;
import com.yihuacomputer.fish.api.parameter.IParamPublishService;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDetailService;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDeviceRelationService;
import com.yihuacomputer.fish.api.parameter.IParamTemplateService;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IDeviceVersionService;
import com.yihuacomputer.fish.api.version.IVersionDownloadService;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.IVersionStaticsService;
import com.yihuacomputer.fish.api.version.IVersionStaticsStautsService;
import com.yihuacomputer.fish.api.version.IVersionTypeAtmTypeRelationService;
import com.yihuacomputer.fish.api.version.IVersionTypeService;
import com.yihuacomputer.fish.api.version.job.IJobManager;
import com.yihuacomputer.fish.api.version.job.IUpdateDeployDateHistoryService;
import com.yihuacomputer.fish.api.version.job.task.ITaskDetailService;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.api.version.relation.IDeviceAdvertRelation;
import com.yihuacomputer.fish.parameter.publishjob.PublishJobManager;
import com.yihuacomputer.fish.parameter.service.ParamClassifyService;
import com.yihuacomputer.fish.parameter.service.ParamDeviceDetailService;
import com.yihuacomputer.fish.parameter.service.ParamElementService;
import com.yihuacomputer.fish.parameter.service.ParamPublishAppResultService;
import com.yihuacomputer.fish.parameter.service.ParamPublishResultService;
import com.yihuacomputer.fish.parameter.service.ParamPublishService;
import com.yihuacomputer.fish.parameter.service.ParamTemplateDetailService;
import com.yihuacomputer.fish.parameter.service.ParamTemplateDeviceRelationService;
import com.yihuacomputer.fish.parameter.service.ParamTemplateService;
import com.yihuacomputer.fish.version.interceptor.VersionEntityInjector;
import com.yihuacomputer.fish.version.job.JobManager;
import com.yihuacomputer.fish.version.relation.DeviceAdvertRelation;
import com.yihuacomputer.fish.version.service.api.IDomainJobService;
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
import com.yihuacomputer.fish.version.task.TaskQueue;

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

	@Bean
	public ITaskService taskService() {
		return new TaskService();
	}

	/**
	 * 特别的配置，不是ITaskService
	 * @return
	 */
	@Bean
	public IDomainJobService jobService(){
		return  new JobService();
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
	public IJobManager jobManager() {
		return new JobManager();
	}
	@Bean
	public VersionEntityInjector versionEntityInjector() {
		return new VersionEntityInjector();
	}
		@Bean
	public TaskQueue taskQueue(){
		return new TaskQueue();
	}
//	@Bean
//	public ITaskManager taskMgr(){
//		return new TaskManager();
//	}
	@Bean
	public IVersionStaticsStautsService versionStaticsStautsService(){
		return new VersionStaticsStatusService();
	}
	@Bean
	public IBsAdvertService bsAdvertService(){
		return new BsAdvertService();
	}
	@Bean
	public IAdvertGroupService advertGroupService(){
		return new AdvertGroupService();
	}
	@Bean
	public IAdvertGroupDeviceRelationService advertGroupDeviceRelationService(){
		return new AdvertGroupDeviceRelationService();
	}
	
	@Bean
	public IDeviceAdvertRelation deviceAdvertRelation(){
		return new DeviceAdvertRelation();
	}
	
	@Bean
	public IBsAdvertResourceService bsAdvertResourceService(){
		return new BsAdvertResourceService();
	}
	@Bean
	public IAppSystemService AppSystemService(){
		return new com.yihuacomputer.fish.parameter.service.AppSystemService();
	}

	@Bean
	public IParamElementService elementService(){
		return new ParamElementService();
	}
	
	
	@Bean
	public IParamTemplateService templateService(){
		return new ParamTemplateService();
	}
	
	@Bean
	public IParamClassifyService classifyService(){
		return new ParamClassifyService();
	}
	
	@Bean
	public IParamTemplateDetailService paramTemplateDetailService(){
		return new ParamTemplateDetailService();
	}
	
	@Bean public IParamDeviceDetailService paramDeviceDetailService(){
		return new ParamDeviceDetailService();
	}
	
	@Bean public IParamTemplateDeviceRelationService paramTemplateDeviceRelationService(){
		return new ParamTemplateDeviceRelationService();
	}

	@Bean 
	public IParamPublishService paramPushService(){
		return new ParamPublishService();
	}
	
	@Bean 
	public IParamPublishResultService paramPublishResultService(){
		return new ParamPublishResultService();
	}
	@Bean
	public PublishJobManager publishJobManager(){
		return new PublishJobManager();
	}
	@Bean
	public IParamPublishAppResultService paramPublishAppResultService(){
		return new ParamPublishAppResultService();
	}
}
