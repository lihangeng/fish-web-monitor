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
import com.yihuacomputer.fish.api.parameter.IParamPublishSearchService;
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
import com.yihuacomputer.fish.parameter.service.ParamPublishSearchService;
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

/**
 * @author YiHua
 *
 */
@Configuration
public class VersionModule {

	/**
	 * @return
	 */
	@Bean
	public IVersionTypeAtmTypeRelationService versionTypeAtmTypeRelationService() {
		return new VersionTypeAtmTypeRelationService();
	}

	/**
	 * @return
	 */
	@Bean
	public IVersionTypeService versionTypeService() {
		return new VersionTypeService();
	}

	/**
	 * @return
	 */
	@Bean
	public IVersionService versionService() {
		return new VersionService();
	}

	/**
	 * @return
	 */
	@Bean
	public IUpdateDeployDateHistoryService updateDeployDateHistoryService() {
		return new UpdateDeployDateHistoryService();
	}

	/**
	 * @return
	 */
	@Bean
	public IDeviceVersionService deviceVersionService() {
		return new DeviceVersionService();
	}

	/**
	 * @return
	 */
	@Bean
	public IDeviceSoftVersionService deviceSoftVersionService() {
		return new DeviceSoftVersionService();
	}

	/**
	 * @return
	 */
	@Bean
	public ITaskDetailService taskDetailService() {
		return new TaskDetailService();
	}

	/**
	 * @return
	 */
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

	/**
	 * @return
	 */
	@Bean
	public IVersionStaticsService versionStaticsService(){
		return new VersionStaticsService();
	}

	/**
	 * @return
	 */
	@Bean
	public IVersionDownloadService versionDownloadService() {
		return new VersionDownloadService();
	}

	/**
	 * @return
	 */
	@Bean
	public IJobManager jobManager() {
		return new JobManager();
	}
	/**
	 * @return
	 */
	@Bean
	public VersionEntityInjector versionEntityInjector() {
		return new VersionEntityInjector();
	}
	
	/**
	 * @return
	 */
	@Bean
	public TaskQueue taskQueue(){
		return new TaskQueue();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IVersionStaticsStautsService versionStaticsStautsService(){
		return new VersionStaticsStatusService();
	}
	/**
	 * @return
	 */
	@Bean
	public IBsAdvertService bsAdvertService(){
		return new BsAdvertService();
	}
	/**
	 * @return
	 */
	@Bean
	public IAdvertGroupService advertGroupService(){
		return new AdvertGroupService();
	}
	/**
	 * @return
	 */
	@Bean
	public IAdvertGroupDeviceRelationService advertGroupDeviceRelationService(){
		return new AdvertGroupDeviceRelationService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IDeviceAdvertRelation deviceAdvertRelation(){
		return new DeviceAdvertRelation();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IBsAdvertResourceService bsAdvertResourceService(){
		return new BsAdvertResourceService();
	}
	/**
	 * @return
	 */
	@Bean
	public IAppSystemService AppSystemService(){
		return new com.yihuacomputer.fish.parameter.service.AppSystemService();
	}

	/**
	 * @return
	 */
	@Bean
	public IParamElementService elementService(){
		return new ParamElementService();
	}
	
	
	/**
	 * @return
	 */
	@Bean
	public IParamTemplateService templateService(){
		return new ParamTemplateService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IParamClassifyService classifyService(){
		return new ParamClassifyService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IParamTemplateDetailService paramTemplateDetailService(){
		return new ParamTemplateDetailService();
	}
	
	/**
	 * @return
	 */
	@Bean 
	public IParamDeviceDetailService paramDeviceDetailService(){
		return new ParamDeviceDetailService();
	}
	
	/**
	 * @return
	 */
	@Bean 
	public IParamTemplateDeviceRelationService paramTemplateDeviceRelationService(){
		return new ParamTemplateDeviceRelationService();
	}

	/**
	 * @return
	 */
	@Bean 
	public IParamPublishService paramPushService(){
		return new ParamPublishService();
	}
	
	/**
	 * @return
	 */
	@Bean 
	public IParamPublishResultService paramPublishResultService(){
		return new ParamPublishResultService();
	}
	/**
	 * @return
	 */
	@Bean
	public PublishJobManager publishJobManager(){
		return new PublishJobManager();
	}
	/**
	 * @return
	 */
	@Bean
	public IParamPublishAppResultService paramPublishAppResultService(){
		return new ParamPublishAppResultService();
	}
	/**
	 * @return
	 */
	@Bean
	public IParamPublishSearchService paramPublishSearchService(){
		return new ParamPublishSearchService();
	}
}
