/**
 *
 */
package com.yihuacomputer.fish.web.version.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.common.util.TwoTuple;
import com.yihuacomputer.fish.api.charts.ChartsInfo;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionDownloadService;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.IVersionStaticsStautsService;
import com.yihuacomputer.fish.api.version.LinkedDeviceForm;
import com.yihuacomputer.fish.api.version.VersionStaticsStatus;
import com.yihuacomputer.fish.api.version.job.IJob;
import com.yihuacomputer.fish.api.version.job.IJobManager;
import com.yihuacomputer.fish.api.version.job.IJobService;
import com.yihuacomputer.fish.api.version.job.IUpdateDeployDateHistory;
import com.yihuacomputer.fish.api.version.job.IUpdateDeployDateHistoryService;
import com.yihuacomputer.fish.api.version.job.JobStatus;
import com.yihuacomputer.fish.api.version.job.JobType;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
import com.yihuacomputer.fish.web.command.format.CommandLevel;
import com.yihuacomputer.fish.web.command.format.RestartForm;
import com.yihuacomputer.fish.web.command.format.RestartParamForm;
import com.yihuacomputer.fish.web.util.DownFromWebUtils;
import com.yihuacomputer.fish.web.util.FishWebUtils;
import com.yihuacomputer.fish.web.version.form.Excel;
import com.yihuacomputer.fish.web.version.form.JobForm;
import com.yihuacomputer.fish.web.version.form.TaskForm;
import com.yihuacomputer.fish.web.version.form.UpdateDeployDateHistoryForm;

/**
 * @author xuxigang
 *
 */
@Controller
@RequestMapping(value = "/version/download")
@ClassNameDescrible(describle="userlog.VersionDownloadController")
public class VersionDownloadController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(VersionDownloadController.class);

	@Autowired
	private IVersionService versionService;

	@Autowired
	private IJobManager jobManager;

	@Autowired
	private IJobService jobService;

	@Autowired
	private IVersionDownloadService downloadService;

	@Autowired
	private IVersionStaticsStautsService versionStaticsStatusService;

	@Autowired
	private ITaskService taskService;

	@Autowired
	private IUpdateDeployDateHistoryService updateDeployDateHistoryService;

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private IVersionDownloadService vdownService;

	@Autowired
	private IOrganizationService orgService;

	@Autowired
	private IDeviceSoftVersionService deviceSoftVersionService;

	@Autowired
	private MessageSource messageVersionSource;

	@RequestMapping(method = RequestMethod.GET, value = "/searchJobDetailInfo")
	public @ResponseBody ModelMap searchJobDetailInfo(@RequestParam long jobId, HttpServletRequest request, WebRequest wRequest) {
		ModelMap result = new ModelMap();
		IFilter filter = new Filter();
		String nextRecord = request.getParameter("nextRecord");
		long displayJobId = jobId;
		// 前一页
		if ("-1".equals(nextRecord)) {
			filter.lt("id", jobId);
			filter.descOrder("id");
			List<IJob> jobList = jobService.list(filter);
			displayJobId = !jobList.isEmpty() ? jobList.get(0).getJobId() : jobId;
		}
		// 后一页
		else if ("1".equals(nextRecord)) {
			filter.gt("id", jobId);
			filter.order("id");
			List<IJob> jobList = jobService.list(filter);
			displayJobId = !jobList.isEmpty() ? jobList.get(0).getJobId() : jobId;
		}
		UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
		IJob job = jobService.getById(displayJobId);
		IVersion version = job.getVersion();
		// 获取当前版本已下发和可下发的设备数据
		List<ChartsInfo> chartList = versionStaticsStatusService.getVersionSummaryInfo(version.getId(), userSession.getOrgFlag(), 0, 25);
		List<ChartsInfo> statusList = taskService.listTaskGroupbyTaskStatus(displayJobId);
		long downloadTime = taskService.getDownloadTimeAvg(displayJobId);

		List<TwoTuple<String, String>> chartsFormList = new ArrayList<TwoTuple<String, String>>();
		chartsFormList.add(new TwoTuple<String, String>(getI18N("jobDetailInfo.versionName"), version.getVersionType().getTypeName()));
		chartsFormList.add(new TwoTuple<String, String>(getI18N("jobDetailInfo.versionNo"), version.getVersionNo()));
		for (ChartsInfo charsInfo : chartList) {
			if (charsInfo.getTitle().equals(getEnumI18n(VersionStaticsStatus.TOTALDEVICE.getText())) || charsInfo.getTitle().equals(getEnumI18n(VersionStaticsStatus.SUCCESSDEVICE.getText()))) {
				TwoTuple<String, String> twoTuple = new TwoTuple<String, String>(charsInfo.getTitle(), String.valueOf(charsInfo.getValue()));
				chartsFormList.add(twoTuple);
			}
		}
		chartsFormList.add(new TwoTuple<String, String>(getI18N("jobDetailInfo.downLoadSpeed"), String.valueOf(downloadTime)));
		for (ChartsInfo charsInfo : statusList) {
			TwoTuple<String, String> twoTuple = new TwoTuple<String, String>(getEnumI18n(charsInfo.getTitle()), String.valueOf(charsInfo.getValue()));
			chartsFormList.add(twoTuple);
		}
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", "");
		result.addAttribute("data", chartsFormList);
		result.addAttribute("displayJobId", displayJobId);
		return result;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search job : start = %s ,limit = %s ", start, limit));
		IFilter filter = getFilter(request);
		filter.descOrder("id");
		IPageResult<IJob> pageResult = jobService.page(start, limit, filter);
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", pageResult.getTotal());
		result.addAttribute("data", toForm(pageResult.list()));
		return result;
	}

	@RequestMapping(value = "/getJobInfo", method = RequestMethod.POST)
	public @ResponseBody ModelMap getJobInfo(@RequestParam long jobId, HttpServletRequest request) {
		IJob job = jobService.getById(jobId);
		ModelMap result = new ModelMap();
		if (null == job) {
			result.addAttribute(FishConstant.SUCCESS, false);
			return result;
		}
		JobForm form = convert(job);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, form);
		return result;
	}

	// 增加
	@MethodNameDescrible(describle="userlog.VersionDownloadController.add",hasLogKey=true)
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelMap add(@RequestBody JobForm form, HttpServletRequest request) {
		logger.info(" add job...");

		ModelMap result = new ModelMap();
		UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
		IVersion version = versionService.getById(form.getVersionId());
		result.addAttribute(FishConstant.LOG_KEY, version.getVersionType().getTypeName()+"_"+version.getVersionNo());
		List<ITask> tasks = new ArrayList<ITask>();
		long start1 = System.currentTimeMillis();
		IFilter filter = new Filter();
		filter.eq("orgFlag", userSession.getOrgFlag());
		filter.eq("eagerRestart", form.isEagerRestart());
		if (!form.isSelectAll()) {
			String deviceIds = form.getDeviceIds();
			if (StringUtils.isNotEmpty(deviceIds)) {
				String[] ids = deviceIds.substring(1).split(",");
				// List<Object> lists =
				// deviceSoftVersionService.findByTypeName(versionTypeName);
				// Map<Long, Object> maps = convertToMap(lists);
				List<Long> deviceIdList = new ArrayList<Long>();
				for (String id : ids) {
					Long deviceId = Long.valueOf(id);
					IDevice device = deviceService.get(deviceId);
					if (device == null) {
						result.put(FishConstant.SUCCESS, false);
						result.put("errorMsg", messageVersionSource.getMessage("version.device.downloadFailure", null, FishCfg.locale));
						return result;
					}
					deviceIdList.add(deviceId);
				}
				filter.eq("deviceIds", deviceIdList);
			}
		}

		IVersion versions = versionService.getById(form.getVersionId());
		if (versions == null) {
			result.put(FishConstant.SUCCESS, false);
			result.put("errorMsg", messageVersionSource.getMessage("version.versionNo.downloadFailure", null, FishCfg.locale));
			return result;
		}

		int downloadCounter = version.getDownloadCounter() + 1;
		IJob job = jobService.make();
		job.setJobName(versions.getVersionType().getTypeName()+"_"+versions.getVersionNo() + "_" + downloadCounter);
		job.setVersion(versionService.getById(form.getVersionId()));
		job.setJobType(form.getJobType());
		job.setJobPriority(form.getJobPriority());
		job.setCreateUserId(userSession.getUserId());
		job.setDownCounter(downloadCounter);
		job.setDesc(form.getDesc());
		if (form.getDeployStartDate() == null || "".equals(form.getDeployStartDate())) {
			job.setDeployStartDate(new Date());
		} else {
			job.setDeployStartDate(DateUtils.getDate(form.getDeployStartDate()));
		}
		if (form.getDeployEndDate() != null && !"".equals(form.getDeployEndDate())) {
			job.setDeployEndDate(DateUtils.getDate(form.getDeployEndDate()));
		}
		if (form.getJobType().equals(JobType.SCHEDULER)) {
			job.setPlanTime(form.getPlanTime());
			job.setJobStatus(JobStatus.SCHEDULER);
		} else {
			job.setJobStatus(JobStatus.NEW);
			job.setPlanTime(new Date());
		}
		// 设置取消升级和重启部署标志
		job.setCancelPreVer(form.isCancelPreVersion() ? 1 : 0);
		job.setRebootUpdate(form.isRebootUpdate() ? 1 : 0);
		job.addTasks(tasks);
		jobManager.createJob(job, filter);
		version.setDownloadCounter(downloadCounter);
		versionService.update(version);
		logger.info("execute all task times " + (System.currentTimeMillis() - start1));

		// 回填值到form中
		form.setId(job.getJobId());
		form.setVersionName(version.getFullName() + " [" + version.getServerPath() + "]");
		form.setJobName(version.getVersionType().getTypeName() + "_" + version.getVersionNo() + "_" + version.getDownloadCounter());
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("data", form);
		return result;
	}

	// 撤销作业
	@MethodNameDescrible(describle="userlog.VersionDownloadController.delete",hasLogKey=true)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ModelMap delete(@PathVariable long id) {
		logger.info(" delete job: job.id = " + id);
		ModelMap result = new ModelMap();
		IJob job = jobService.getById(id);
		result.addAttribute(FishConstant.LOG_KEY, job.getVersion().getVersionType().getTypeName() + "_" + job.getVersion().getVersionNo());
		try {
			jobManager.cancelJob(id);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			logger.error(String.format("The Failure of Canceling Job：[%s]", ex));
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, getI18N("job.cancel.cancelfailer"));
		}
		return result;
	}

	private String getI18N(String code) {
		return messageVersionSource.getMessage(code, null, FishCfg.locale);
	}

	// 转换数据格式
	private List<JobForm> toForm(List<IJob> jobs) {
		List<JobForm> forms = new ArrayList<JobForm>();
		for (IJob job : jobs) {
			if (JobType.AUTO_UPDATE.equals(job.getJobType())) {
				IFilter filter = new Filter();
				filter.eq("job.jobId", job.getJobId());
				int devVersionCount = taskService.list(filter).size();
				int repeatDevVersionCount = jobService.getRepeatTaskByJob(job.getJobId());
				forms.add(convertWithIntArgs(job, devVersionCount, repeatDevVersionCount));
			} else {
				forms.add(convert(job));
			}

		}
		return forms;
	}

	private boolean canReset(TaskStatus status) {
		if ((status.equals(TaskStatus.DEPLOYED)) || (status.equals(TaskStatus.DOWNLOADED)) || (status.equals(TaskStatus.NEW)) || (status.equals(TaskStatus.NOTICE_APP_OK)) || (status.equals(TaskStatus.NOTICED)) || status.equals(TaskStatus.RUN) || status.equals(TaskStatus.DEPLOYED_WAIT)) {
			return true;
		}
		return false;
	}

	@MethodNameDescrible(describle="userlog.VersionDownloadController.resetTaskStatus",hasLogKey=true)
	@RequestMapping(value = "/resetTaskStatus", method = RequestMethod.POST)
	public @ResponseBody ModelMap resetTaskStatus(@RequestParam long id) {
		logger.info(String.format("reset taskStatus  : taskId = %s  ", id));
		ModelMap result = new ModelMap();
		ITask task = taskService.get(id);
		result.addAttribute(FishConstant.LOG_KEY, task.getVersion().getVersionType().getTypeName()+"_"+task.getVersion().getVersionNo());
		try {
			if (canReset(task.getStatus())) {
				taskService.resetTask(task);
				result.addAttribute(FishConstant.SUCCESS, true);
			} else {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, getI18N("exception.task.cantResetTask"));
			}
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, getI18N("task.reset.failer"));
		}
		return result;
	}

	public JobForm convertWithIntArgs(IJob job, int devVersionCount, int repeatDevVersionCount) {
		JobForm jobForm = convert(job);
		jobForm.setExtraBody("&nbsp;&nbsp; " + getI18N("version.download.jobType") + getEnumI18n(job.getJobType().getText()) + "&nbsp;&nbsp; "
				+ (job.getJobType() == JobType.MANUAL ? getI18N("version.download.jobStatus") + (jobForm.getRunTaskCount() == 0 ? getI18N("version.download.finished") : getI18N("version.download.running")) : getI18N("version.download.autoRefreshStatus") + (job.getVersion().isAutoDown() ? getI18N("version.download.open") : getI18N("version.download.close"))) + "&nbsp;&nbsp; "
				+ getI18N("version.download.deviceRepeatly") + repeatDevVersionCount + "&nbsp;&nbsp;" + getI18N("version.download.currentVersionDevCount") + devVersionCount + "&nbsp;&nbsp;" + getI18N("version.download.taskCount") + jobForm.getAllTaskCount() + "&nbsp;&nbsp; " + getI18N("version.download.taskCountFinished") + jobForm.getFinishTaskCount() + "&nbsp;&nbsp; "
				+ getI18N("version.download.taskCountFailed") + jobForm.getFailTaskCount() + "&nbsp;&nbsp; " + getI18N("version.download.runTaskCount") + jobForm.getRunTaskCount());
		return jobForm;
	}

	public JobForm convert(IJob job) {
		JobForm jobForm = new JobForm();
		jobForm.setId(Long.valueOf(job.getJobId()));
		jobForm.setJobName(job.getVersion().getVersionType().getTypeName() + "_" + job.getVersion().getVersionNo() + "_" + job.getDownCounter());
		jobForm.setPlanTime(job.getPlanTime());
		jobForm.setJobType(job.getJobType());
		jobForm.setJobStatus(job.getJobStatus());
		jobForm.setJobPriority(job.getJobPriority());
		jobForm.setDesc(job.getDesc());
		jobForm.setDownloadCounter(job.getDownCounter());
		jobForm.setCancelPreVersion(job.getCancelPreVer() == 0 ? false : true);
		jobForm.setRebootUpdate(job.getRebootUpdate() == 0 ? false : true);

		if (job.getVersion() != null) {
			jobForm.setVersionId(job.getVersion().getId());
			jobForm.setVersionFile(job.getVersion().getServerPath());
			jobForm.setVersionName(job.getVersion().getFullName());
			jobForm.setVersionCatalog(job.getVersion().getVersionType().getVersionCatalog().name());
		}
		if (job.getDeployStartDate() != null) {
			jobForm.setDeployStartDate(DateUtils.getDate(job.getDeployStartDate()));
		}
		if (job.getDeployEndDate() != null) {
			jobForm.setDeployEndDate(DateUtils.getDate(job.getDeployEndDate()));
		}

		List<ITask> taskList = job.getTasks();
		jobForm.setAllTaskCount(job.getTaskSize());
		for (ITask task : taskList) {
			if (TaskStatus.CHECKED.equals(task.getStatus()) || TaskStatus.FAIL_ROLLBACK.equals(task.getStatus())) {
				jobForm.setFinishTaskCount(jobForm.getFinishTaskCount() + 1);
			} else if (TaskStatus.CANCELED.equals(task.getStatus()) || TaskStatus.CANCEL_UPDATE_OK.equals(task.getStatus()) || TaskStatus.DEPLOYED_FAIL.equals(task.getStatus()) || TaskStatus.NOTICED_FAIL.equals(task.getStatus()) || TaskStatus.OTHER.equals(task.getStatus()) || TaskStatus.REMOVED.equals(task.getStatus()) || TaskStatus.DOWNLOADED_FAIL.equals(task.getStatus())) {
				jobForm.setFailTaskCount(jobForm.getFailTaskCount() + 1);
			}
		}
		jobForm.setRunTaskCount(jobForm.getAllTaskCount() - jobForm.getFinishTaskCount() - jobForm.getFailTaskCount());

		jobForm.setExtraBody("&nbsp;&nbsp; " + getI18N("version.download.jobType") + getEnumI18n(job.getJobType().getText()) + "&nbsp;&nbsp; "
				+ (job.getJobType() == JobType.MANUAL ? getI18N("version.download.jobStatus") + (jobForm.getRunTaskCount() == 0 ? getI18N("version.download.finished") : getI18N("version.download.running")) : getI18N("version.download.autoRefreshStatus") + (job.getVersion().isAutoDown() ? getI18N("version.download.open") : getI18N("version.download.close"))) + "&nbsp;&nbsp;"
				+ getI18N("version.download.taskCount") + jobForm.getAllTaskCount() + "&nbsp;&nbsp;" + getI18N("version.download.taskCountFinished") + jobForm.getFinishTaskCount() + "&nbsp;&nbsp; " + getI18N("version.download.taskCountFailed") + jobForm.getFailTaskCount() + "&nbsp;&nbsp;" + getI18N("version.download.runTaskCount") + jobForm.getRunTaskCount());
		return jobForm;

	}

	// 获得查询条件
	private IFilter getFilter(WebRequest request) {
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (FishWebUtils.isIgnoreRequestName(name)) {
				continue;
			}
			String value = request.getParameter(name);
			if (StringUtils.isEmpty(value)) {
				continue;
			}
			if ("versionTypeId".equals(name)) {
				if (!"0".equals(value)) {
					filter.eq("version.versionType.id", Long.valueOf(value));
				}
			} else if ("versionNo".equals(name)) {
				filter.like("version.versionNo", value);
			} else if (name.equals("jobId")) {
				filter.eq("updateDeployDateHistory.jobId", Long.valueOf(value));
			}
		}
		return filter;
	}

	/**
	 * 已经关联到作业的设备列表
	 *
	 * @param start
	 * @param limit
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/linked", method = RequestMethod.GET)
	public @ResponseBody ModelMap linked(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search job : start = %s ,limit = %s ", start, limit));
		IFilter filter = new Filter();// getFilter(request);
		IJob job = null;
		IPageResult<IDevice> page = downloadService.pageLinkedDevices(start, limit, job, filter);
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", page.getTotal());
		// result.addAttribute("data", toLinkedDeviceForm(page.list()));
		return result;
	}

	/**
	 * 还可以关联到作业的设备列表
	 *
	 * @param start
	 * @param limit
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/selectable", method = RequestMethod.GET)
	public @ResponseBody ModelMap selectable(@RequestParam int start, @RequestParam int limit, @RequestParam int versionId, WebRequest webRequest, HttpServletRequest request) {
		logger.info(String.format("search selectable device : start = %s ,limit = %s , versionId = %s ", start, limit, versionId));
		IFilter filter = getDeviceFilter(webRequest, request);
		String atmGroup = request.getParameter("atmGroup");
		if (atmGroup != null && !"".equals(atmGroup)) {
			filter.eq("atmGroup", Long.parseLong(atmGroup));
		}
		IVersion version = versionService.getById(versionId);
		IPageResult<LinkedDeviceForm> page = null;
		page = downloadService.pageDevices(start, limit, version, filter);

		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", page.getTotal());
		result.addAttribute("data", page.list());
		return result;
	}

	private IFilter getDeviceFilter(WebRequest request, HttpServletRequest rq) {
		UserSession userSession = (UserSession) rq.getSession().getAttribute(FishWebUtils.USER);
		String orgFlag = userSession.getOrgFlag();
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (FishWebUtils.isIgnoreRequestName(name)) {
				continue;
			}

			String value = request.getParameter(name);
			if (StringUtils.isEmpty(value)) {
				continue;
			}

			if ("orgId".equals(name)) {
				orgFlag = orgService.get(value).getOrgFlag();
			}

			if ("ip".equals(name)) {
				filter.eq("ip", new IP(value));
			}

			if ("atmTypeId".equals(name)) {
				filter.eq("atmTypeId", value);
			}

			if ("terminalId".equals(name)) {
				filter.eq("terminalId", value);
			}
		}
		filter.eq("orgFlag", orgFlag);
		return filter;
	}

	// 根据jobId获得下面的任务列表
	@RequestMapping(value = "/task", method = RequestMethod.GET)
	public @ResponseBody ModelMap task(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		IFilter filter = getTaskFilter(request);
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		if ("1".equals(request.getParameter("filterTaskFlag"))) {
			IPageResult<Object> page = taskService.pageForRepeat(start, limit, filter);
			result.addAttribute("total", page.getTotal());
			result.addAttribute("data", toTaskFormForRepeat(page.list()));
		} else {
			IPageResult<ITask> page = taskService.page(start, limit, filter);
			result.addAttribute("total", page.getTotal());
			result.addAttribute("data", toTaskForm(page.list()));
		}
		return result;
	}

	// 获得任务的过滤条件
	private IFilter getTaskFilter(WebRequest request) {
		IFilter filter = new Filter();
		String jobId = request.getParameter("jobId");
		if (StringUtils.isEmpty(jobId)) {
			jobId = "2";
		}
		filter.eq("task.job.jobId", Long.valueOf(jobId));

		String updateResult = request.getParameter("updateResult");
		if (StringUtils.isNotEmpty(updateResult)) {
			if ("NEW".equals(updateResult)) {
				filter.eq("task.status", TaskStatus.NEW);
			} else if ("RUN".equals(updateResult)) {
				filter.eq("task.status", TaskStatus.RUN);
			} else if ("NOTICED".equals(updateResult)) {
				filter.eq("task.status", TaskStatus.NOTICED);
			} else if ("NOTICED_FAIL".equals(updateResult)) {
				filter.eq("task.status", TaskStatus.NOTICED_FAIL);
			} else if ("DOWNLOADED".equals(updateResult)) {
				List<TaskStatus> status = new ArrayList<TaskStatus>();
				status.add(TaskStatus.DOWNLOADED);
				status.add(TaskStatus.DEPLOYED_WAIT);
				filter.in("task.status", status);
			} else if ("DOWNLOADED_FAIL".equals(updateResult)) {
				filter.eq("task.status", TaskStatus.DOWNLOADED_FAIL);
			} else if ("DEPLOYED".equals(updateResult)) {
				filter.eq("task.status", TaskStatus.DEPLOYED);
			} else if ("DEPLOYED_FAIL".equals(updateResult)) {
				filter.eq("task.status", TaskStatus.DEPLOYED_FAIL);
			} else if ("CHECKED".equals(updateResult)) {
				filter.eq("task.status", TaskStatus.CHECKED);
			} else if ("DEPLOYED_WAIT".equals(updateResult)) {
				filter.eq("task.status", TaskStatus.DEPLOYED_WAIT);
			} else if ("DOWNLOADING".equals(updateResult)) {
				filter.eq("task.status", TaskStatus.DOWNLOADING);
			} else if ("CANCEL_UPDATE_OK".equals(updateResult)) {
				filter.eq("task.status", TaskStatus.CANCEL_UPDATE_OK);
			} else if ("FAIL_ROLLBACK".equals(updateResult)) {
				filter.eq("task.status", TaskStatus.FAIL_ROLLBACK);
			} else if ("OTHER".equals(updateResult)) {
				List<TaskStatus> status = new ArrayList<TaskStatus>();
				status.add(TaskStatus.CANCEL_FAIL);
				status.add(TaskStatus.CANCEL_UPDATE_FAIL);
				status.add(TaskStatus.CANCELED);
				status.add(TaskStatus.NOTICE_APP_FAIL);
				status.add(TaskStatus.NOTICE_APP_OK);
				status.add(TaskStatus.OTHER);
				status.add(TaskStatus.REMOVED);
				filter.in("task.status", status);
			} else if ("1".equals(updateResult)) {
				filter.eq("task.status", TaskStatus.CHECKED);
			} else if ("0".equals(updateResult)) {
				List<TaskStatus> status = new ArrayList<TaskStatus>();
				status.add(TaskStatus.NOTICED_FAIL);
				status.add(TaskStatus.DOWNLOADED_FAIL);
				status.add(TaskStatus.DEPLOYED_FAIL);
				status.add(TaskStatus.NOTICE_APP_FAIL);
				status.add(TaskStatus.REMOVED);
				filter.in("task.status", status);
			} else if ("2".equals(updateResult)) {
				List<TaskStatus> status = new ArrayList<TaskStatus>();
				status.add(TaskStatus.DEPLOYED_WAIT);
				status.add(TaskStatus.DOWNLOADED);
				status.add(TaskStatus.DOWNLOADING);
				status.add(TaskStatus.NEW);
				status.add(TaskStatus.NOTICE_APP_OK);
				status.add(TaskStatus.NOTICED);
				status.add(TaskStatus.RUN);
				filter.in("task.status", status);
			}
		}

		String terminalId = request.getParameter("terminalId");
		if (StringUtils.isNotEmpty(terminalId)) {
			filter.like("device.terminalId", terminalId);
		}

		// filter.eq("task.status", TaskStatus.REMOVED);

		return filter;
	}

	/**
	 *
	 * 根据条件生成升级报告
	 *
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "exportToExcel", method = RequestMethod.GET)
	public void export(WebRequest webRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String jobId = request.getParameter("jobId");
		logger.info("jobId is " + jobId);
		if (StringUtils.isEmpty(jobId)) {
			jobId = "0";
		}
		IFilter filter = new Filter();
		filter.eq("job.jobId", Long.valueOf(jobId));
		filter.order("deviceId");
		// filter.addFilterEntry(FilterFactory.ne("status",
		// TaskStatus.REMOVED));
		List<ITask> tasks = taskService.list(filter);

		Excel excel = new Excel();
		String[] headers = new String[] { getI18N("version.export.terminalId"), getI18N("version.export.ip"), getI18N("version.export.orgName"), getI18N("version.export.versionNoBeforeUpdate"), getI18N("version.export.updateVersionNo"), getI18N("version.export.taskStatus"), getI18N("version.export.version.export.executeTime"), getI18N("version.download.versionDownloadStartTime"),
				getI18N("version.download.versionDownloadFinishTime"), getI18N("version.export.remark"), getI18N("version.download.rebootATM") };
		excel.setHeaders(headers);
		// 填充数据
		List<List> data = new ArrayList<List>();
		String filterTaskFlag = request.getParameter("filterTaskFlag");
		List<Long> terminalList = new ArrayList<Long>();
		if ("1".equals(filterTaskFlag)) {
			List<Object> tempDevList = taskService.findTerminalForRepeat(Long.valueOf(jobId));
			if (tempDevList != null && !tempDevList.isEmpty()) {
				for (Object obj : tempDevList) {
					terminalList.add(Long.parseLong(obj.toString()));
				}
			}
		}
		for (ITask task : tasks) {
			IDevice device = task.getDevice();
			if (device == null) {
				continue;
			}
			if ("1".equals(filterTaskFlag)) {
				if (!terminalList.contains(device.getId())) {
					continue;
				}
			}
			List row = new ArrayList();
			row.add(device.getTerminalId());
			row.add(device.getIp().toString());
			row.add(device.getOrganization().getName());
			row.add(task.getVersionBeforeUpdate() == null ? "" : task.getVersionBeforeUpdate());
			row.add(task.getVersion().getVersionNo());
			row.add(getEnumI18n(task.getStatus().getText()));
			// row.add(task.getExceptVersion());
			row.add(task.getExcuteTime() == null ? "" : DateUtils.getTimestamp(task.getExcuteTime()));
			row.add(task.getDownloadStartTime());
			row.add(task.getDownloadFinishTime());
			row.add(task.getReason() == null ? "" : task.getReason());
			data.add(row);
		}
		excel.setData(data);

		String fileName = FishCfg.getTempDir() + File.separator + getI18N("version.download.jobName") + DateUtils.getDate(new Date()) + getI18N("version.download.postfix");
		excel.export(fileName, getI18N("version.export.executeResult"));

		File file = new File(fileName);
		DownFromWebUtils.download(file, request, response);
	}

	/**
	 * 撤销一个任务
	 *
	 * @param taskId
	 * @return
	 */
	@MethodNameDescrible(describle="userlog.VersionDownloadController.cancelTask",hasArgs=true,argsContext="jobId")
	@RequestMapping(value = "/task/cancel", method = RequestMethod.POST)
	public @ResponseBody ModelMap cancelTask(@RequestParam long jobId, @RequestParam long taskId) {
		logger.info(" cancle task: task.id = " + taskId);
		ModelMap result = new ModelMap();
		try {
			jobManager.cancelTask(jobId, taskId);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, getI18N("task.cancel.failer"));
		}
		return result;
	}

	@RequestMapping(value = "/task/deepCancel", method = RequestMethod.POST)
	public @ResponseBody ModelMap deepCancelTask(@RequestParam long jobId, @RequestParam long taskId) {
		logger.info(" deep cancel task: task.id = " + taskId);
		ModelMap result = new ModelMap();
		try {
			taskService.deepCancelApp(taskId);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, getI18N("task.cancel.failer"));
		}
		return result;
	}

	@RequestMapping(value = "/task/reDistribute", method = RequestMethod.POST)
	public @ResponseBody ModelMap reDistribute(@RequestParam long taskId) {
		logger.info(" reDistribute task: task.id = " + taskId);
		ModelMap result = new ModelMap();
		try {
			taskService.reDistribute(taskId);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, getI18N("task.reDistribute.failer"));
		}
		return result;
	}

	/**
	 * 暂停一个作业
	 *
	 * @param id
	 *            作业ID
	 * @return
	 */
	@MethodNameDescrible(describle="userlog.VersionDownloadController.pauseJob",hasArgs=true,argsContext="id")
	@RequestMapping(value = "/pause", method = RequestMethod.POST)
	public @ResponseBody String pauseJob(@RequestParam long id) {
		try {
			jobManager.suspendJob(id);
			return "{'success':true}";
		} catch (Exception ex) {
			return "{'success':false,'errors':'" + getI18N("job.pause.failer") + "'}";
		}
	}

	private List<TaskForm> toTaskForm(List<ITask> tasks) {
		List<TaskForm> forms = new ArrayList<TaskForm>();
		for (ITask task : tasks) {
			forms.add(convertToTaskForm(task));
		}
		return forms;
	}

	private String getEnumI18n(String enumText) {
		if (null == enumText) {
			return "";
		}
		return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
	}

	@Autowired
	private MessageSource messageSourceEnum;

	private TaskForm convertToTaskForm(ITask task) {
		TaskForm form = new TaskForm();
		form.setId(task.getId());
		form.setExcuteTime(task.getExcuteTime() == null ? "" : DateUtils.getTimestamp(task.getExcuteTime()));
		form.setSuccess(task.isSuccess());
		form.setReason(task.getReason());

		if (task.getStatus() != null) {
			form.setTaskStatus(getEnumI18n(task.getStatus().getText()));
			form.setTaskStatusText(getEnumI18n(task.getStatus().getText()));
		}

		form.setJobId(task.getJob().getJobId());
		form.setVersion(task.getVersion().getVersionNo());
		form.setState(task.getState());
		IDevice device = task.getDevice();
		form.setDeviceId(device.getId());
		form.setTerminalId(device.getTerminalId());
		form.setDeviceIp(device.getIp().toString());
		form.setOrgName(device.getOrganization().getName());
		if (task.getVersionBeforeUpdate() != null) {
			int index = task.getVersionBeforeUpdate().indexOf("_");
			form.setVersionBeforeUpdate(task.getVersionBeforeUpdate().substring(index + 1));
		}
		form.setExceptVersion(task.getExceptVersion());
		form.setCurrentVersion(task.getVersionBeforeUpdate());
		form.setProcess(task.getProcess());

		form.setDownloadStartTime(task.getDownloadStartTime());
		form.setDownloadFinishTime(task.getDownloadFinishTime());
		return form;
	}

	public String getState(ITask task) {
		if (task.getStatus().equals(TaskStatus.NEW) || task.getStatus().equals(TaskStatus.RUN)) {
			return getEnumI18n(task.getStatus().getText());
		} else {
			return getEnumI18n(task.getStatus().getText()) + (task.isSuccess() ? getEnumI18n("taskstatus.execute.result.success") : getEnumI18n("taskstatus.execute.result.failer"));
		}
	}

	private List<TaskForm> toTaskFormForRepeat(List<Object> tasks) {
		List<TaskForm> forms = new ArrayList<TaskForm>();
		for (Object task : tasks) {
			Object[] tempObj = (Object[]) task;
			TaskForm taskForm = new TaskForm();
			taskForm.setTerminalId(tempObj[0].toString());
			taskForm.setDeviceIp(tempObj[1].toString());
			taskForm.setOrgName(tempObj[2].toString());
			taskForm.setVersionBeforeUpdate(tempObj[3] == null ? "" : tempObj[3].toString());
			taskForm.setTaskStatus(tempObj[4].toString());
			taskForm.setExcuteTime(tempObj[5] == null ? "" : tempObj[5].toString());
			taskForm.setDownloadStartTime(tempObj[6] == null ? "" : tempObj[6].toString());
			taskForm.setDownloadFinishTime(tempObj[7] == null ? "" : tempObj[7].toString());
			taskForm.setReason(tempObj[8] == null ? "" : tempObj[8].toString());
			taskForm.setId(Long.parseLong(tempObj[9].toString()));
			forms.add(taskForm);
		}
		return forms;
	}

	/**
	 * 重启某作业下的可以重启的全部设备
	 *
	 * @param jobId
	 *            作业ID
	 * @return
	 */
	@RequestMapping(value = "/rebootAll", method = RequestMethod.POST)
	public @ResponseBody ModelMap rebootAll(@RequestParam long jobId) {
		logger.info(" reboot all in job: job.id = " + jobId);
		ModelMap result = new ModelMap();

		return result;
	}

	/**
	 * 重启某任务对应的设备
	 *
	 * @param taskId
	 *            任务ID
	 * @return
	 */
	@MethodNameDescrible(describle="userlog.VersionDownloadController.rebootOne",hasArgs=true,argsContext="taskId")
	@RequestMapping(value = "/rebootOne", method = RequestMethod.POST)
	public @ResponseBody ModelMap rebootOne(@RequestParam long taskId) {
		logger.info(" reboot task: task.id = " + taskId);
		ModelMap result = new ModelMap();
		ITask task = taskService.get(taskId);
		String url = MonitorCfg.getHttpUrl(task.getDevice().getIp().toString()) + "/ctr/normalReboot";

		RestartParamForm restartParamForm = new RestartParamForm();
		restartParamForm.setRestartType(CommandLevel.APP);// 通过应用重启
		try {
			RestartForm restartForm = (RestartForm) HttpProxy.httpPost(url, restartParamForm, RestartForm.class);
			String appRet = restartForm.getAppRet();
			// 修改任务状态
			task.setStatus(TaskStatus.DEPLOYED);
			taskService.updateTask(task);

			result.put(FishConstant.SUCCESS, true);
			result.addAttribute("appRet", appRet);
		} catch (Exception e) {
			result.put(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, getI18N("task.reboot.failer"));
		}
		return result;
	}

	@RequestMapping(value = "/updateDeployDate", method = RequestMethod.POST)
	public @ResponseBody ModelMap update(@RequestParam long jobId, @RequestParam String deployStartDate, @RequestParam String deployEndDate) {
		logger.info(" updateDeployDate  : jobId.id = " + jobId);
		ModelMap result = new ModelMap();
		try {
			Date startDate = DateUtils.getDate(deployStartDate);
			Date endDate = null;
			if (deployEndDate != null && !"".equals(deployEndDate)) {
				endDate = DateUtils.getDate(deployEndDate);
			}
			jobService.updateDeployDate(jobId, startDate, endDate);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, getI18N("job.updateDeployDate.failer"));
		}
		return result;
	}

	@RequestMapping(value = "/queryUpdateDeployDateHist", method = RequestMethod.GET)
	public @ResponseBody ModelMap searchUpdateDeployDateHist(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search job : start = %s ,limit = %s ", start, limit));
		IFilter filter = getFilter(request);
		filter.descOrder("noticeTime");
		IPageResult<IUpdateDeployDateHistory> pageResult = updateDeployDateHistoryService.page(start, limit, filter);
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", pageResult.getTotal());
		result.addAttribute("data", toUpdateDeployDateHistoryForm(pageResult.list(), start));
		return result;
	}

	public List<UpdateDeployDateHistoryForm> toUpdateDeployDateHistoryForm(List<IUpdateDeployDateHistory> list, int start) {
		List<UpdateDeployDateHistoryForm> result = new ArrayList<UpdateDeployDateHistoryForm>();

		int index = 0;
		UpdateDeployDateHistoryForm form = null;
		for (IUpdateDeployDateHistory item : list) {
			form = new UpdateDeployDateHistoryForm(item);
			if (index == 0 && start == 0) {
				form.setGroupName("1");
			} else {
				form.setGroupName("2");
			}
			index++;

			result.add(form);
		}
		return result;
	}

	@RequestMapping(value = "/reNoticeApp", method = RequestMethod.GET)
	public @ResponseBody ModelMap reNoticeApp(@RequestParam long updateDeployDateHistoryId, WebRequest request) {
		logger.info(String.format("reNoticeApp job : updateDeployDateHistoryId = %s  ", updateDeployDateHistoryId));
		ModelMap result = new ModelMap();
		try {
			taskService.reNoticeApp(updateDeployDateHistoryId);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		return result;
	}
}
