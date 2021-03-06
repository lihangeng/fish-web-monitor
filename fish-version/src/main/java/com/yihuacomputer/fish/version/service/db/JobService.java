/**
 *
 */
package com.yihuacomputer.fish.version.service.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.AppException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.version.IDeviceVersion;
import com.yihuacomputer.fish.api.version.IDeviceVersionService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionDownloadService;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.VersionCatalog;
import com.yihuacomputer.fish.api.version.VersionStatus;
import com.yihuacomputer.fish.api.version.job.IJob;
import com.yihuacomputer.fish.api.version.job.IUpdateDeployDateHistoryService;
import com.yihuacomputer.fish.api.version.job.JobStatus;
import com.yihuacomputer.fish.api.version.job.JobType;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
import com.yihuacomputer.fish.version.entity.DeviceVersion;
import com.yihuacomputer.fish.version.entity.Job;
import com.yihuacomputer.fish.version.entity.deploy.DeployThreadService;
import com.yihuacomputer.fish.version.entity.deploy.UpdateDeployDateThread;
import com.yihuacomputer.fish.version.service.base.DomainJobService;

/**
 * @author xuxigang
 *
 */
@Service
@Transactional
public class JobService extends DomainJobService {

	private Logger logger = LoggerFactory.getLogger(JobService.class);
    @Autowired
    private IGenericDao dao;

    @Autowired(required = false)
   	private IUserService userService;

    @Autowired
    private ITaskService taskService;

	@Autowired
    private IVersionService versionService;
	@Autowired
    private IVersionDownloadService versionDownloadService;

    @Autowired
    private IDeviceVersionService dvService;

    @Autowired
    private IUpdateDeployDateHistoryService deployService;

    @Autowired
    private MessageSource messageVersionSource;
    
    public ITaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}

	public IVersionService getVersionService() {
		return versionService;
	}

	public void setVersionService(IVersionService versionService) {
		this.versionService = versionService;
	}

	public IUpdateDeployDateHistoryService getDeployService() {
		return deployService;
	}

	public void setDeployService(IUpdateDeployDateHistoryService deployService) {
		this.deployService = deployService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@Override
    @Transactional(readOnly = true)
    public List<IJob> findReloadJob() {
        IFilter filter = new Filter();
        filter.eq("serverIp", FishCfg.getHostIp());//只加载本机的版本任务
        filter.ne("jobStatus", JobStatus.COMPLETE);
        filter.ne("jobStatus", JobStatus.FINISH);
        filter.descOrder("jobPriority");
        List<IJob> jobs = this.list(filter);
        for (IJob job : jobs) {
            Hibernate.initialize(job.getTasks());
        }
        return jobs;
    }

    @Override
    @Transactional(readOnly = true)
    public Job getById(long jobId) {
        return dao.get(jobId, Job.class);
    }

    @Override
    @Transactional(readOnly = true)
    public IJob getByName(String jobName) {
        IFilter filter = new Filter();
        filter.eq("jobName", jobName);
        return dao.findUniqueByFilter(filter, Job.class);
    }

    @Override
    public Job cascadeAdd(IJob job,IFilter filter) {
        // 保存作业信息
    	job.setServerIp(FishCfg.getHostIp());//集群时设置执行的服务IP
        Job entity = dao.save(this.interface2Entity(job, false));
        // 保存任务列表
        IVersion version = entity.getVersion();
        Long versionId = version.getId();
        if(null!=filter){
        	versionDownloadService.selectAllDeviceToTask(job, filter);
        }
        List<IDeviceVersion> dvs = dvService.findDeviceVersionContainsRemoved(version.getId());
        Map<Long,IDeviceVersion> maps = convertToMap(dvs);
        long start1 = System.currentTimeMillis();
        if (version.getVersionStatus().equals(VersionStatus.NEW)) {
            version.setVersionStatus(VersionStatus.WAITING);
            versionService.update(version);
        }
        IFilter taskFilter = new Filter();
        taskFilter.eq("job", entity);
        List<ITask> taskList = taskService.list(taskFilter);
        for (ITask task : entity.getTasks()) {
        
            Long deviceId = task.getDeviceId();
            IDeviceVersion dv = maps.get(deviceId);
            if(dv == null){
                dv = new DeviceVersion();
                dv.setDeviceId(deviceId);
                dv.setVersionId(versionId);
                dv.setTaskStatus(TaskStatus.NEW);
                dv.setLastUpdatedTime(new Date());
                dv.setDesc(null);
                dao.saveOrUpdate(dv);
            }
        }
        long t = System.currentTimeMillis();
        logger.info("create task times " + (t -start1));
        entity.addTasks(taskList);
        return entity;
    }

    private Map<Long, IDeviceVersion> convertToMap(List<IDeviceVersion> dvs) {
        Map<Long, IDeviceVersion> maps = new HashMap<Long, IDeviceVersion>();
        for(IDeviceVersion dv : dvs){
            maps.put(dv.getDeviceId(), dv);
        }
        return maps;
    }

    @Override
    public void onlyUpdateJob(IJob job) {
        // 更新作业信息
        try{
        	 Job entity = this.interface2Entity(job, true);
        	 dao.merge(entity);
        }catch(Exception ex){
        	logger.error(String.format("update job status fail [%s] " ,ex));
        }

    }

    /**
     * 级联删除作业及作业的相关信息
     */
    @Override
    public void cascadeDelete(IJob job) {
        // 删除作业下的任务
        taskService.removeTasks(job);
        IVersion version = job.getVersion();
        // 删除作业
        dao.delete(job.getJobId(), Job.class);
        //重置版本到NEW状态
        this.resetVersionStatus(version);
    }

    /**
     * 重置版本状态
     * 1.版本没有关联的作业时，重置到new状态
     * @param version
     */
    private void resetVersionStatus(IVersion version){
        if(getRelationJobSzie(version) == 0){
             if(version.getVersionStatus().equals(VersionStatus.WAITING)){
                version.setVersionStatus(VersionStatus.NEW);
                versionService.update(version);
            }
        }
    }

    @Override
    public int getRelationJobSzie(IVersion version){
        IFilter filter = new Filter();
        filter.eq("version.id", version.getId());
        return list(filter).size();
    }

    @Override
    public void cascadeDelete(long id) {
        this.cascadeDelete(this.getById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<IJob> list(IFilter outerFilter) {
        IFilter filter = null;
        if(outerFilter == null){
            filter = new Filter();
        }else{
            filter = outerFilter;
        }
        filter.ne("jobType", JobType.AUTO_UPDATE);
        return dao.findByFilter(filter, IJob.class);
    }

    @Override
    @Transactional(readOnly = true)
    public IPageResult<IJob> page(int offset, int limit, IFilter outerFilter) {
        IFilter filter = null;
        if(outerFilter == null){
            filter = new Filter();
        }else{
            filter = outerFilter;
        }
        filter.ne("id", new Long(1));
        return dao.page(offset, limit, filter, Job.class);
    }

    private Job interface2Entity(IJob job, boolean load) {
        if (job instanceof Job) {
            return (Job) job;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public int getNotRemovedTasks(IJob job) {
        List<ITask> tasks = new ArrayList<ITask>();
        for(ITask task : job.getTasks()){
            if(!task.getStatus().equals(TaskStatus.REMOVED)){
                tasks.add(task);
            }
        }
        return tasks.size();
    }

    /**
     * 修改作业的部署时间
     */
    @Override
    public void updateDeployDate(long jobId, Date deployStartDate, Date deployEndDate) {
        IJob job = getById(jobId);
        IVersion version = job.getVersion();
        if(version.getVersionType().getVersionCatalog().equals(VersionCatalog.APP)){
            job.setDeployStartDate(deployStartDate);
            this.onlyUpdateJob(job);
        	List<ITask> tasks = job.getTasks();
        	int coreSize = tasks.size() <= 20 ? tasks.size() : 20;
        	DeployThreadService threadService = new DeployThreadService(coreSize);
        	for(ITask task : tasks){
        		threadService.execute(new UpdateDeployDateThread(task,deployService,deployStartDate));
        	}
        }else{
        	throw new AppException(messageVersionSource.getMessage("version.download.notUpdateTime", null, FishCfg.locale));
        }
    }

    public IUserService getUserService(){
        return this.userService;
    }

	@Override
	public IJob getAutoJobByVersionId(IVersion version) {
		 IFilter filter = new Filter();
         filter.eq("version", version);
         filter.eq("jobType", JobType.AUTO_UPDATE) ;
         List<Job> jobList = dao.findByFilter(filter, Job.class) ;
         if(jobList!=null && !jobList.isEmpty()){
        	 return jobList.get(0) ;
         }
         return null ;
	}

	@Override
	public int getRepeatTaskByJob(long jobId) {
		String hql = "select  t.deviceId,count(t.id)  from Task t where t.job.id=? group by t.deviceId having count(t.id)>1" ;
		List<Object> valueObj = new ArrayList<Object>();
		valueObj.add(jobId) ;
		List<Object> list = this.dao.findByHQL(hql, valueObj.toArray()) ;
		return list.size() ;
	}
	
	@Override
	public boolean batchCancelTaskByJob(long jobId){
		IJob job = this.getById(jobId);
		if(job==null){
			logger.error(String.format("job %d not exist", jobId));
			return false;
		}
		if(job.getVersion()==null){
			logger.error(String.format("%d job's version  not exist", jobId));
			return false;
		}
		StringBuffer taskSb = new StringBuffer();
		taskSb.append("update VER_TASK  set TASK_STATUS='REMOVED' ");
		taskSb.append(" where JOB_ID=? and (TASK_STATUS='NEW' OR TASK_STATUS='RUN')");
		

		StringBuffer deviceVersionSb = new StringBuffer();
		deviceVersionSb.append("update VER_DEVICE_VERSION  set TASK_STATUS='REMOVED' ");
		deviceVersionSb.append(" where VERSION_ID=? and (TASK_STATUS='NEW' OR TASK_STATUS='RUN' ) and DEVICE_ID in(  ");
		deviceVersionSb.append(" select DEVICE_ID from VER_TASK where JOB_ID=? and (TASK_STATUS='NEW' OR TASK_STATUS='RUN'))");
		
		StringBuffer insertDeviceVersionSb = new StringBuffer();
		insertDeviceVersionSb.append("insert into  VER_DEVICE_VERSION (DEVICE_ID,VERSION_ID,TASK_STATUS,CREATED_TIME,LAST_UPDATED_TIME)    ");
		insertDeviceVersionSb.append(" select DEVICE_ID,?,?,?,? from VER_TASK where JOB_ID=? and (TASK_STATUS='NEW' OR TASK_STATUS='RUN')");
		insertDeviceVersionSb.append(" and DEVICE_ID not in (select DEVICE_ID from VER_DEVICE_VERSION)");
		
		
		SQLQuery deviceQuery = dao.getSQLQuery(deviceVersionSb.toString());
		deviceQuery.setLong(0, job.getVersion().getId());
		deviceQuery.setLong(1, jobId);
		int deviceVersionUpdate = deviceQuery.executeUpdate();
		
		
		Date date = new Date();
		SQLQuery insertDeviceVersionQuery = dao.getSQLQuery(insertDeviceVersionSb.toString());
		insertDeviceVersionQuery.setLong(0, job.getVersion().getId());
		insertDeviceVersionQuery.setString(1, "REMOVED");
		insertDeviceVersionQuery.setDate(2, date);
		insertDeviceVersionQuery.setDate(3, date);
		insertDeviceVersionQuery.setLong(4, jobId);
		int deviceVersionInsert = insertDeviceVersionQuery.executeUpdate();

		SQLQuery taskQuery = dao.getSQLQuery(taskSb.toString());
		taskQuery.setLong(0, jobId);
		int taskUpdate = taskQuery.executeUpdate();
		return taskUpdate!=0&&(deviceVersionUpdate+deviceVersionInsert)!=0;
		
	}

}
