/**
 *
 */
package com.yihuacomputer.fish.version.service.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.ITypeIP;
import com.yihuacomputer.common.exception.AppException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.VersionStatus;
import com.yihuacomputer.fish.api.version.job.IUpdateDeployDateHistory;
import com.yihuacomputer.fish.api.version.job.IUpdateDeployDateHistoryService;
import com.yihuacomputer.fish.api.version.job.NoticeStatus;
import com.yihuacomputer.fish.api.version.job.task.AutoUpdateTaskForm;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
import com.yihuacomputer.fish.api.version.job.task.TaskType;
import com.yihuacomputer.fish.version.entity.Task;
import com.yihuacomputer.fish.version.notice.NoticeForm;
import com.yihuacomputer.fish.version.service.api.IDomainTaskService;

/**
 * @author xuxigang
 *
 */
@Service
@Transactional
public class TaskService implements IDomainTaskService {

	private final String path = "/ctr/patchpush";

	@Autowired
	private IGenericDao dao;

	@Autowired
	private IVersionService versionService;

	@Autowired
	private IUpdateDeployDateHistoryService updateDeployDateService;

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private IDeviceSoftVersionService deviceSoftVersionService;
	
	@Autowired
	private MessageSource messageSourceVersion;

	@Override
	public IDeviceService getDeviceService() {
		return this.deviceService;
	}

	@Override
	public ITask make(Date firstCreateDate) {
		Task task = new Task(firstCreateDate);
		task.setTaskService(this);
		return task;
	}

	@Override
	public List<ITask> findTasksByJobId(long jobId) {
		IFilter filter = new Filter();
		filter.eq("job.jobId", jobId);
		return list(filter);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ITask> findTasks(long deviceId) {
		IFilter filter = new Filter();
		filter.eq("deviceId", deviceId);
		return this.list(filter);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ITask> findTask(long deviceId, String typeName, String versionNo) {
		String hql = "from Task task where task.job.version.versionType.typeName = ? and task.job.version.versionNo = ? and task.deviceId = ?";
		return dao.findByHQL(hql, typeName, versionNo, deviceId);
	}

	@Override
	public ITask addTask(ITask task) {
		Task entity = this.interface2Entity(task, false);
		dao.save(entity);
		// 修改版本状态,NEW--> WAITING
		IVersion version = entity.getVersion();
		if (version.getVersionStatus().equals(VersionStatus.NEW)) {
			version.setVersionStatus(VersionStatus.WAITING);
			versionService.update(version);
		}
		// 修改或者保存设备版本表
		//@since 2.0 删除
		return entity;
	}

	@Override
	public void updateTask(ITask task) {
		if (task.getStatus().equals(TaskStatus.REMOVED)) {
			cancelTask(task);
		} else {
			Task entity = this.interface2Entity(task, true);
			dao.update(entity);

			IVersion version = task.getVersion();
			// 修改版本状态,WAITING--> DOWNLOADED
			if (version != null && version.getVersionStatus().equals(VersionStatus.WAITING)) {
				version.setVersionStatus(VersionStatus.DOWNLOADED);
				dao.update(version);
			}

			// 修改
			//@since 2.0 删除
		}
	}

	public void onlyUpdateTask(ITask task) {
		dao.update(task);
	}

	/**
	 * 取消任务
	 */
	public void cancelTask(ITask task) {
		task.setStatus(TaskStatus.REMOVED);
		dao.update(task);

		List<ITask> tasks = new ArrayList<ITask>();
		tasks.add(task);
		cancelTasks(tasks);
	}

	/**
	 * 取消前，任务的状态已经是removed 取消一批任务
	 */
	public void cancelTasks(List<ITask> tasks) {
		/**/int size = tasks.size();
		if (size > 0) {
			for (ITask task : tasks) {// 修改设备版本表的任务状态为“删除”
				task.setStatus(TaskStatus.CANCELED);
			}
		}
	}

	@Override
	public void removeTask(ITask task) {
		dao.delete(task.getId(), Task.class);
	}

	@Override
	public List<ITask> list(IFilter filter) {
		return dao.findByFilter(filter, ITask.class);
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public IPageResult<ITask> export( IFilter filter) {
		StringBuffer hql = new StringBuffer();
		hql.append("select task from Task task,Device device where task.deviceId = device.id ");
		
		return (IPageResult<ITask>) dao.page(0, -1, filter, hql.toString());
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public IPageResult<ITask> page(int start, int limit, IFilter filter) {
		StringBuffer hql = new StringBuffer();
		hql.append("select task from Task task,Device device where task.deviceId = device.id ");
		return (IPageResult<ITask>) dao.page(start, limit, filter, hql.toString());
	}

	private Task interface2Entity(ITask job, boolean load) {
		if (job instanceof Task) {
			return (Task) job;
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public ITask findTask(long deviceId, long versionId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Task t where t.deviceId = ? and t.version.id = ? and t.status <> ?");
		return dao.findUniqueByHql(hql.toString(), deviceId, versionId, TaskStatus.REMOVED);
	}

	/*@Override
	public void removeTasks(IJob job) {
		for (ITask task : job.getTasks()) {// 修改设备版本表的任务状态为“删除”
			dvService.updateDeviceVersionStatus(task.getDevice().getId(), task.getVersion().getId(), TaskStatus.REMOVED);
		}
		dao.batchUpdate("delete Task task where task.job.jobId = ?", job.getJobId());
	}*/

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
		if (outerFilter == null) {
			filter = new Filter();
		} else {
			filter = outerFilter;
		}
		filter.eq("taskType", TaskType.AUTO_UPDATE);
		return dao.page(start, limit, filter, ITask.class);
	}

	@Override
	public void reDistribute(long taskId) {
		ITask task = get(taskId);
		if (!task.isSuccess()) {
			noticeATM(task);
		} else {
			String exceptionMsg = messageSourceVersion.getMessage("exception.task.dontNeedReDO", null, FishCfg.locale);
			throw new AppException(exceptionMsg);
		}
	}

	public boolean noticeATM(ITask task) {
		boolean ignore = true;
		if (TaskStatus.canRun(task.getStatus()) || TaskStatus.RUN.equals(task.getStatus())) {
			// 通知
			try {
				IDevice device = task.getDevice();
				NoticeForm notice = new NoticeForm(task);
				notice = (NoticeForm) HttpProxy.httpPost(geNoticetUrl(device.getIp()), notice, NoticeForm.class);

				if (notice.getRet().equals("RET0100")) {
					task.setStatus(TaskStatus.NOTICED_FAIL);
					task.setReason(messageSourceVersion.getMessage("exception.task.sameTaskRuningForAgentRefuse", null, FishCfg.locale));
					task.setSuccess(false);
				} else {
					task.setStatus(TaskStatus.NOTICED);
					task.setReason("");
					task.setSuccess(true);
				}
			} catch (Exception ex) {
				task.setStatus(TaskStatus.NOTICED_FAIL);
				task.setSuccess(false);
				task.setReason(messageSourceVersion.getMessage("exception.task.connectAgentFail", null, FishCfg.locale));
			}
			// 更新任务状态
			task.setExcuteTime(new Date());
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
		if (!task.isDeploySuccess() || task.getStatus().equals(TaskStatus.CHECKED)) {
			try {
				IDevice device = task.getDevice();
				NoticeForm notice = new NoticeForm(task);
				HttpProxy.httpPost(getCancelUrl(device.getIp()), notice, NoticeForm.class);
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new AppException(messageSourceVersion.getMessage("exception.task.cancelFail", new Object[]{ex.getMessage()}, FishCfg.locale) );
			}
		} else {
			throw new AppException(messageSourceVersion.getMessage("exception.task.cantCancelForComplete", null, FishCfg.locale));
		}
	}

	private String getCancelUrl(ITypeIP ip) {
		return MonitorCfg.getHttpUrl(ip.toString()) + "/ctr/patch/cancel";
	}

	@Override
	public void reNoticeApp(long updateDeployDateHisotryId) {
		IUpdateDeployDateHistory updateDeployDateHistory = updateDeployDateService.getById(updateDeployDateHisotryId);
		if (updateDeployDateHistory.getNoticeStatus().equals(NoticeStatus.FAIL) || updateDeployDateHistory.getNoticeStatus().equals(NoticeStatus.UNKNOW)) {
			ITask task = this.get(updateDeployDateHistory.getTaskId());
			try {
				IDevice device = task.getDevice();
				NoticeForm notice = new NoticeForm(task);
				HttpProxy.httpPost(MonitorCfg.getHttpUrl(device.getIp().toString()) + "/ctr/patch/updateDeployDate", notice, NoticeForm.class);
				updateDeployDateHistory.setNoticeStatus(NoticeStatus.SUCCESS);
			} catch (Exception ex) {
				ex.printStackTrace();
				updateDeployDateHistory.setNoticeStatus(NoticeStatus.FAIL);
				updateDeployDateHistory.setReason(messageSourceVersion.getMessage("exception.task.noticeFail", null, FishCfg.locale));
			}
			updateDeployDateService.update(updateDeployDateHistory);

		} else {
			throw new AppException(messageSourceVersion.getMessage("exception.task.dontReNoticeForNoticeSuccess", null, FishCfg.locale));
		}
	}

	@Override
	public List<ITask> findTaskByDeviceIdAndVersionId(long deviceId, long versionId) {
		return dao.findByHQL("from Task t where t.deviceId = ? and t.version.id = ? order by t.excuteTime desc", deviceId, versionId);
	}

	@Override
	public List<ITask> findTasks(Date planTime) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Task task where task.planTime = ?");
		return dao.findByHQL(hql.toString(), planTime);
	}
	
	public IPageResult<AutoUpdateTaskForm> pageAutoUpdateTask(int start,int limit,IFilter filter){
		StringBuffer hql = new StringBuffer();
		hql.append("select task,device from Task task,Device device ").
		append("where task.deviceId=device.id and task.taskType= ? ");
		List<Object> args = new ArrayList<Object>();
		args.add(TaskType.AUTO_UPDATE);
		Object versionType = filter.getValue("versionType");
		Object versionNo = filter.getValue("versionNo");
		Object taskStatus = filter.getValue("taskStatus");
		Object orgFlag = filter.getValue("orgFlag");
		Object atmTypeId = filter.getValue("atmTypeId");
		Object terminalId = filter.getValue("terminalId");
		Object deviceIp = filter.getValue("deviceIp");
		if(null!=versionType){
			hql.append(" and task.version.versionType.id=? ");
			args.add(versionType);
		}if(null!=versionNo){
			hql.append(" and task.version.versionNo=? ");
			args.add(versionNo);
		}if(null!=taskStatus){
			hql.append(" and task.status=? ");
			args.add(taskStatus);
		}if(null!=orgFlag){
			hql.append(" and device.organization.orgFlag like ? ");
			args.add(orgFlag);
		}if(null!=atmTypeId){
			hql.append(" and device.devType.id = ? ");
			args.add(atmTypeId);
		}if(null!=terminalId){
			hql.append(" and device.terminalId like ? ");
			args.add(terminalId);
		}if(null!=deviceIp){
			hql.append(" and device.ip = ? ");
			args.add(deviceIp);
		}
		@SuppressWarnings("unchecked")
		IPageResult<Object> result = (IPageResult<Object>) dao.page(start, limit, hql.toString(), args.toArray());
		List<AutoUpdateTaskForm> list = new ArrayList<AutoUpdateTaskForm>();
		for(Object queryResult :result.list()){
			Object[]entity = (Object[])queryResult;
			ITask task = (ITask)entity[0];
			IDevice device = (IDevice)entity[1];
			AutoUpdateTaskForm autoUpdateTaskForm = new AutoUpdateTaskForm();
			autoUpdateTaskForm.setId(task.getId());
			autoUpdateTaskForm.setAtmType(device.getDevType().getName());
			autoUpdateTaskForm.setDeviceIp(device.getIp().toString());
			autoUpdateTaskForm.setDownSource(task.getDownSource());
			autoUpdateTaskForm.setExcuteMachine(task.getExcuteMachine());
			if(null!=task.getExcuteTime()){
				autoUpdateTaskForm.setExcuteTime(DateUtils.getTimestamp(task.getExcuteTime()));
			}
			autoUpdateTaskForm.setOrgName(device.getOrganization().getName());
			autoUpdateTaskForm.setTaskStatus(task.getState());
			autoUpdateTaskForm.setTerminalId(device.getTerminalId());
			autoUpdateTaskForm.setVersion(task.getExceptVersion());
			autoUpdateTaskForm.setVersionBeforeUpdate(task.getVersionBeforeUpdate());
			autoUpdateTaskForm.setVersionType(task.getVersion().getVersionType().getDesc());
			list.add(autoUpdateTaskForm);
		}
		return new PageResult<AutoUpdateTaskForm>(result.getTotal(), list);
	}

}
