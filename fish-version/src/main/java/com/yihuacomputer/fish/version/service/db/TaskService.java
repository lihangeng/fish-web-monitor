/**
 *
 */
package com.yihuacomputer.fish.version.service.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.ITypeIP;
import com.yihuacomputer.common.exception.AppException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.charts.ChartsInfo;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersion;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IDeviceVersionService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.VersionCatalog;
import com.yihuacomputer.fish.api.version.VersionStatus;
import com.yihuacomputer.fish.api.version.job.IJob;
import com.yihuacomputer.fish.api.version.job.IUpdateDeployDateHistory;
import com.yihuacomputer.fish.api.version.job.IUpdateDeployDateHistoryService;
import com.yihuacomputer.fish.api.version.job.NoticeStatus;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
import com.yihuacomputer.fish.api.version.job.task.TaskType;
import com.yihuacomputer.fish.version.entity.Task;
import com.yihuacomputer.fish.version.notice.NoticeForm;
import com.yihuacomputer.fish.version.service.api.IDomainJobService;

/**
 * @author xuxigang
 *
 */
@Service
@Transactional
public class TaskService implements ITaskService {

	@Autowired
	private IDeviceService deviceService;

    @Autowired
    private IDeviceSoftVersionService deviceSoftVersionService;

	private Logger logger = LoggerFactory.getLogger(TaskService.class);

    private final String path = "/ctr/patchpush";

	@Autowired
	private IGenericDao dao;

    @Autowired
    private IDeviceVersionService dvService;

    @Autowired
    private IVersionService versionService;

    @Autowired
    private IDomainJobService jobService;

    @Autowired
    private IUpdateDeployDateHistoryService updateDeployDateService;

    @Autowired
	private MessageSource messageSourceVersion;
    
	@Override
    @Transactional(readOnly = true)
	public List<ITask> findTasks(long deviceId) {
		IFilter filter = new Filter();
		filter.eq("deviceId", deviceId);
		return this.list(filter);
	}

	@Override
    @Transactional(readOnly = true)
    public List<ITask> findTask(long deviceId,String typeName,String versionNo){
	    String hql = "from Task task where task.job.version.versionType.typeName = ? and task.job.version.versionNo = ? and task.deviceId = ?";
	    return dao.findByHQL(hql, typeName,versionNo,deviceId);
	}

	@Override
	public ITask addTask(ITask task) {
		Task  entity = this.interface2Entity(task, false);
		dao.save(entity);
		//修改版本状态,NEW--> WAITING
		IVersion version = entity.getVersion();
        if (version.getVersionStatus().equals(VersionStatus.NEW)) {
            version.setVersionStatus(VersionStatus.WAITING);
            versionService.update(version);
        }
        //修改或者保存设备版本表
	    dvService.saveOrUpdateDeviceVersionForList(entity.getDeviceId(), version.getId(), TaskStatus.NEW, null);
		return entity;
	}

	@Override
	public void updateTask(ITask task) {
	    if(task.getStatus().equals(TaskStatus.REMOVED)){
	        cancelTask(task);
	    }else{
    		Task entity = this.interface2Entity(task, true);
    		dao.update(entity);

    		IVersion version = task.getVersion();
    		//修改版本状态,WAITING--> DOWNLOADED
    		if(version != null && version.getVersionStatus().equals(VersionStatus.WAITING)){
                version.setVersionStatus(VersionStatus.DOWNLOADED);
                dao.update(version);
            }
    		if(version !=null){
    			//修改
        		dvService.saveOrUpdateDeviceVersionForList(entity.getDeviceId(), version.getId(), entity.getStatus(), entity.getReason());
    		}
	    }
	}

	public void onlyUpdateTask(ITask task){
	    dao.update(task);
	}

	/**
	 * 取消任务
	 */
	public void cancelTask(ITask task){
	    task.setStatus(TaskStatus.REMOVED);
	    dao.update(task);
        List<ITask> tasks = new ArrayList<ITask>();
        tasks.add(task);
        cancelTasks(tasks);
	}
	
	public void resetTask(ITask task){
	    task.setStatus(TaskStatus.DEPLOYED_FAIL);
	    dao.update(task);
        List<ITask> tasks = new ArrayList<ITask>();
        tasks.add(task);
        resetTasks(tasks);
	}
	public void resetTasks(List<ITask> tasks){
	    int size = tasks.size();
	    if(size > 0){
            for(ITask task : tasks){//修改设备版本表的任务状态为“删除”
                dvService.updateDeviceVersionStatusForList(task.getDevice().getId(), task.getVersion().getId(), TaskStatus.DEPLOYED_FAIL);
            }
	    }
	}
	/**
	 * 取消前，任务的状态已经是removed
	 * 取消一批任务
	 */
	public void cancelTasks(List<ITask> tasks){
	    int size = tasks.size();
	    if(size > 0){
            for(ITask task : tasks){//修改设备版本表的任务状态为“删除”
            	logger.info("task.getDevice().getId()"+task.getDevice().getId());
            	logger.info("task.getVersion().getId()"+task.getVersion().getId());
                dvService.updateDeviceVersionStatusForList(task.getDevice().getId(), task.getVersion().getId(), TaskStatus.REMOVED);
            }
	    }
	}

	@Override
	public void removeTask(ITask task) {
		dao.delete(task.getId(),Task.class);
	}

	@Override
	public List<ITask> list(IFilter filter) {
		return dao.findByFilter(filter, ITask.class);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public IPageResult<ITask> page(int start, int limit, IFilter filter) {
	    StringBuffer hql = new StringBuffer();
	    hql.append("select task from Task task,Device device where task.deviceId = device.id ");
	    return (IPageResult<ITask>)dao.page(start, limit, filter, hql.toString());
	}

	private Task interface2Entity(ITask job, boolean load) {
		if (job instanceof Task) {
			return (Task)job;
		}
		return null;
	}

	@Override
    @Transactional(readOnly = true)
	public ITask findTask(long deviceId, long versionId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Task t where t.deviceId = ? and t.job.version.id = ? and t.status <> ?");
		return dao.findUniqueByHql(hql.toString(), deviceId,versionId,TaskStatus.REMOVED);
	}

    @Override
    public void removeTasks(IJob job) {
       for(ITask task : job.getTasks()){//修改设备版本表的任务状态为“删除”
           dvService.updateDeviceVersionStatusForList(task.getDevice().getId(), task.getVersion().getId(), TaskStatus.REMOVED);
       }
       dao.batchUpdate("delete Task task where task.job.jobId = ?", job.getJobId());
    }

    @Override
    @Transactional(readOnly = true)
    public ITask get(long taskId) {
       return dao.get(taskId, Task.class);
    }

    @Override
    public void updateTaskStatus(ITask task) {
        Task entity = this.interface2Entity(task, true);
        dao.update(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public IPageResult<ITask> pageAutoUpdateTasks(int start, int limit, IFilter outerFilter) {
        IFilter filter = null;
        if(outerFilter == null){
            filter = new Filter();
        }else{
            filter = outerFilter;
        }
        filter.eq("taskType", TaskType.AUTO_UPDATE);
        return dao.page(start, limit, filter, ITask.class);
    }

    @Override
    public void reDistribute(long taskId) {
        ITask task = get(taskId);
        if(!task.isSuccess()){
            noticeATM(task);
        }else{
            throw new AppException(messageSourceVersion.getMessage("exception.task.dontNeedReDO", null, FishCfg.locale));
        }
    }

    public boolean noticeATM(ITask task){
        boolean ignore = true;
        //暂时存放远程访问的返回值
        int retResult = 0 ;
        //下发之前判断是否任务已取消
        task = this.get(task.getId());
        if(TaskStatus.canRun(task.getStatus()) || TaskStatus.RUN.equals(task.getStatus())){
            // 通知
            try {
                IDevice device = task.getDevice();
                NoticeForm notice = new NoticeForm(task);
                notice = (NoticeForm)HttpProxy.httpPost(geNoticetUrl(device.getIp()), notice, NoticeForm.class, 10000, 30000);
                if(notice.getRet().equals("RET0100")){
                	 retResult = 1 ;
                	 task.setReason(messageSourceVersion.getMessage("exception.task.sameTaskRuningForAgentRefuse", null, FishCfg.locale));
                }else{
                	retResult = 2 ;
	                task.setReason("");
                }
            } catch (Exception ex) {
            	retResult = -1 ;
                task.setReason(messageSourceVersion.getMessage("exception.task.connectAgentFail", null, FishCfg.locale));
            }
            if(TaskStatus.REMOVED.equals(task.getStatus())){
            	task.setReason("");
            	task.setStatus(TaskStatus.CANCEL_UPDATE_OK);
            }else{
            	if(retResult==1){
            		task.setStatus(TaskStatus.NOTICED_FAIL);
            		task.setSuccess(false);
            	}else if(retResult==2){
            		task.setStatus(TaskStatus.NOTICED);
            		task.setSuccess(true);
            	}else if(retResult==-1){
            		task.setStatus(TaskStatus.NOTICED_FAIL);
            		task.setSuccess(false);
            	}
            }
            logger.info("taskStatus is "+task.getStatus());
            // 更新任务状态
            Date date = new Date();
            task.setExcuteTime(date);
            task.setDownloadStartTime(DateUtils.getTimestamp(date));
            this.updateTask(task);
            ignore = false;
        }
        return ignore;
    }


    private String geNoticetUrl(ITypeIP ip) {
        return MonitorCfg.getHttpUrl(ip.toString()) + path;
    }
    @Override
    public void deepCancelApp(long taskId) {
        ITask task = get(taskId);
        if(!task.isDeploySuccess() || task.getStatus().equals(TaskStatus.CHECKED)){
            try {
                IDevice device = task.getDevice();
                NoticeForm notice = new NoticeForm(task);
                HttpProxy.httpPost(getCancelUrl(device.getIp()), notice, NoticeForm.class);
            } catch (Exception ex) {
            	logger.error(String.format("Exception is [%s]", ex.getMessage()));
                throw new AppException(messageSourceVersion.getMessage("exception.task.cancelFial", null, FishCfg.locale) + ex.getMessage());
            }
        }else{
            throw new AppException(messageSourceVersion.getMessage("exception.task.cantCancelForComplete", null, FishCfg.locale));
        }
    }

    private String getCancelUrl(ITypeIP ip) {
        return MonitorCfg.getHttpUrl(ip.toString()) + "/ctr/patch/cancel";
    }

    @Override
    public void reNoticeApp(long updateDeployDateHisotryId) {
        IUpdateDeployDateHistory updateDeployDateHistory = updateDeployDateService.getById(updateDeployDateHisotryId);
        if(updateDeployDateHistory.getNoticeStatus().equals(NoticeStatus.FAIL) ||
                updateDeployDateHistory.getNoticeStatus().equals(NoticeStatus.UNKNOW)){
            ITask task = this.get(updateDeployDateHistory.getTaskId());
            try {
                IDevice device = task.getDevice();
                NoticeForm notice = new NoticeForm(task);
                HttpProxy.httpPost(MonitorCfg.getHttpUrl(device.getIp().toString()) + "/ctr/patch/updateDeployDate", notice, NoticeForm.class);
                updateDeployDateHistory.setNoticeStatus(NoticeStatus.SUCCESS);
            } catch (Exception ex) {
            	logger.error(String.format("Exception is [%s]", ex.getMessage()));
                updateDeployDateHistory.setNoticeStatus(NoticeStatus.FAIL);
                updateDeployDateHistory.setReason(messageSourceVersion.getMessage("exception.task.noticeFail", null, FishCfg.locale));
            }
            updateDeployDateService.update(updateDeployDateHistory);

        }else{
            throw new AppException(messageSourceVersion.getMessage("exception.task.dontReNoticeForNoticeSuccess", null, FishCfg.locale));
        }
    }

    @Override
    public List<ITask> findTaskByDeviceIdAndVersionId(long deviceId,long versionId)
    {
    	 return dao.findByHQL("from Task t where t.deviceId = ? and t.version.id = ? order by t.excuteTime desc", deviceId,versionId);
    }

    public void collectTaskReport(long taskId,double process){
    	ITask task = this.get(taskId);
    	task.setProcess(process);
    	this.updateTask(task);
    }

	@Override
	public List<ITask> findTaskCount(TaskType taskType) {
		List<ITask> tasks = dao.findByHQL("from Task t where t.taskType=? and t.status=? or t.status=?", TaskType.AUTO_UPDATE,TaskStatus.NEW,TaskStatus.DOWNLOADING) ;
		return tasks;
	}

    @Override
    public List<ITask> findOrg(IFilter filter) {
        StringBuffer hql = new StringBuffer();
        hql.append(" select task from Task task,Device device where task.deviceId = device.id ");

        List<Object> listObj = new ArrayList<Object>();

        IFilterEntry orgFlagEntry = filter.getFilterEntry("orgFlag");
        IFilterEntry versionEntry = filter.getFilterEntry("version");
        IFilterEntry taskTypeEntry = filter.getFilterEntry("taskType");


        if (orgFlagEntry != null) {
            hql.append(" and device.organization.orgFlag like ? ");
            listObj.add("%" + orgFlagEntry.getValue());
        }

        if (versionEntry != null) {
            hql.append(" and task.version = ? ");
            listObj.add(versionEntry.getValue());
        }

        if (taskTypeEntry != null) {
            hql.append(" and task.taskType = ? ");
            listObj.add(taskTypeEntry.getValue());
        }


        return dao.findByHQL(hql.toString(), listObj.toArray());
    }

	@Override
	public List<ITask> findTaskByDeviceId(long deviceId,
			VersionCatalog versionCatalog, List<TaskStatus> statusList) {
		StringBuffer hql = new StringBuffer() ;
		List<Object> paramsObj = new ArrayList<Object>();
		hql.append("select task from Task task,Version version,VersionType versionType ") ;
		hql.append(" where task.version.id=version.id and version.versionType.id=versionType.id") ;
		hql.append(" and task.deviceId=?") ;
		paramsObj.add(deviceId) ;
		if(statusList!=null && !statusList.isEmpty()){
			hql.append(" and (") ;
			for(int i=0;i<statusList.size();i++){
				hql.append("task.status=?") ;
				paramsObj.add(statusList.get(i)) ;
				if(i<statusList.size()-1){
					hql.append(" or ") ;
				}
			}
			hql.append(" ) ") ;
		}
		if(versionCatalog!=null){
			hql.append(" and versionType.versionCatalog=?") ;
			paramsObj.add(versionCatalog) ;
		}
		return dao.findByHQL(hql.toString(), paramsObj.toArray());
	}

	@Override
	public IPageResult<Object> pageForRepeat(int start, int limit, IFilter filter) {
		StringBuffer hql = new StringBuffer();
		List<Object> paramList = new ArrayList<Object>() ;
	    hql.append("select d.terminal_id,d.ip,o.name,v.version_before_update,v.task_status,v.job_time,v.download_start_time,v.download_finish_time,v.reason,v.id  ");
	    hql.append(" from ver_task v,dev_info d,sm_org o, ") ;
	    hql.append(" (select device_id did from ver_task ") ;
	    if(filter.getFilterEntry("task.job.jobId")!=null){
	    	Long jobId = Long.parseLong(filter.getFilterEntry("task.job.jobId").getValue().toString()) ;
	    	hql.append(" where job_id= ?");
	    	paramList.add(jobId) ;
	    }
	    hql.append(" group by device_id having count(device_id)>1) t ") ;
	    hql.append(" where v.device_id=d.id and v.device_id=t.did and d.org_id=o.id ") ;
	    if(filter.getFilterEntry("task.job.jobId")!=null){
	    	Long jobId = Long.parseLong(filter.getFilterEntry("task.job.jobId").getValue().toString()) ;
	    	hql.append(" and v.job_id= ?");
	    	paramList.add(jobId) ;
	    }
	    if(filter.getFilterEntry("task.status")!=null){
	    	String taskStatus = filter.getFilterEntry("task.status").getValue().toString() ;
	    	hql.append(" and v.task_status=?") ;
	    	paramList.add(taskStatus) ;
	    }
	    if(filter.getFilterEntry("device.terminalId")!=null){
	    	String terminalId = filter.getFilterEntry("device.terminalId").getValue().toString() ;
	    	hql.append(" and d.terminal_id like ?") ;
	    	paramList.add("%"+terminalId+"%") ;
	    }
	    hql.append(" order by d.terminal_id ") ;
	    return dao.pageForSQL(start, limit, hql.toString(), paramList.toArray()) ;
	}

	@Override
	public List<Object> findTerminalForRepeat(long jobId) {
		StringBuffer sbf = new StringBuffer() ;
		List<Object> paramList = new ArrayList<Object>() ;
		sbf.append("select task.deviceId from Task task where task.job.id=? group by task.deviceId having count(task.id)>1") ;
		paramList.add(jobId) ;
		return dao.findByHQL(sbf.toString(), paramList.toArray());
	}


	@Override
	public IDeviceService getDeviceService() {
		return this.deviceService;
	}
	
	@Override
	public ITask make() {
		Task task = new Task();
		task.setTaskService(this);
		return task;
	}
	
	@Override
	public ITask make(IDevice device) {
		return make(device,null);
	}
	
   public ITask make(IDevice device,String versionTypeName) {
        ITask task = make();
        task.setDevice(device);
        IDeviceSoftVersion dsv = deviceSoftVersionService.get(device.getTerminalId(), versionTypeName);
        if(dsv != null){
            task.setVersionBeforeUpdate(versionTypeName + "_" + dsv.getVersionNo());
        }
        return task;
    }
	
    @Override
	public ITask make(long deviceId,String versionTypeName){
	    return make(deviceService.get(deviceId),versionTypeName);
	}

	@Override
	public ITask make(long deviceId) {
		return make(deviceService.get(deviceId));
	}

	@Override
	public List<ITask> findTasksByJobId(long jobId) {
		IFilter filter = new Filter();
		filter.eq("job.jobId", jobId);
		return list(filter);
	}
	
	public List<ChartsInfo> listTaskGroupbyTaskStatus(long jobId){
		StringBuffer sb = new StringBuffer();
		sb.append("select count(task.id),task.status from ").append(Task.class.getSimpleName())
		.append(" task where task.job.id=? group by task.status order by task.status ");
		List<Object> objList = dao.findByHQL(sb.toString(), new Object[]{jobId});
		List<ChartsInfo> chartsList = new ArrayList<ChartsInfo>();
		for(Object objs:objList){
			Object[] obj = (Object[])objs;
			ChartsInfo chartsInfo = new ChartsInfo();
			chartsInfo.setValue(Long.parseLong(obj[0]==null?"0":obj[0].toString()));
			TaskStatus taskStatus = TaskStatus.valueOf(obj[1].toString());
			chartsInfo.setFlag(taskStatus.getId());
			chartsInfo.setTitle(taskStatus.getText());
			chartsList.add(chartsInfo);
		}
		return chartsList;
	}
	
	
	public long getDownloadTimeAvg(long jobId){
		StringBuffer sb = new StringBuffer();
		sb.append("select sum(task.downloadTime),count(task.id) from ").append(Task.class.getSimpleName())
		.append(" task where task.job.id=? and task.downloadTime <> 0 ");
		Object objects = dao.findUniqueByHql(sb.toString(), new Object[]{jobId});
		Object[] obj = (Object[]) objects;
		int taskCounter = Integer.parseInt(obj[1]==null?"0":obj[1].toString());
		long sumTimes = Long.parseLong(obj[0]==null?"0":obj[0].toString());
		if(taskCounter==0){
			return -1;
		}
		else{
			return sumTimes/taskCounter;
		}
	}
	
}
