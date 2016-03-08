/**
 *
 */
package com.yihuacomputer.fish.web.version.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersion;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionDownloadService;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.LinkedDeviceForm;
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
import com.yihuacomputer.fish.web.version.form.JobForm;
import com.yihuacomputer.fish.web.version.form.TaskForm;
import com.yihuacomputer.fish.web.version.form.UpdateDeployDateHistoryForm;

/**
 * @author xuxigang
 *
 */
@Controller
@RequestMapping(value = "/version/download")
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

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search job : start = %s ,limit = %s ", start, limit));
		IFilter filter = getFilter(request);
		filter.descOrder("createdTime");
		IPageResult<IJob> pageResult = jobService.page(start, limit, filter);
		ModelMap result = new ModelMap();
		result.addAttribute("success", true);
		result.addAttribute("total", pageResult.getTotal());
		result.addAttribute("data", toForm(pageResult.list()));
		return result;
	}

	// 增加
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ModelMap add(@RequestBody JobForm form, HttpServletRequest request) {
		logger.info(" add job...");

		ModelMap result = new ModelMap();
		UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
		IVersion version = versionService.getById(form.getVersionId());
		String versionTypeName = version.getVersionType().getTypeName();
		List<ITask> tasks = new ArrayList<ITask>();
		long start1 = System.currentTimeMillis();
		if (form.isSelectAll()) {
			IFilter filter = new Filter();
			filter.eq("orgFlag", userSession.getOrgFlag());
			IPageResult<Object> results = vdownService.getCanPushDevicePagesInfo(0, Integer.MAX_VALUE, version, filter);
			logger.info("getCanPushDevicePagesInfo times " + (System.currentTimeMillis() - start1));
			List<Object> devices =results.list();
			if (devices.size() == 0) {
				result.addAttribute("success", false);
				result.addAttribute(FishConstant.ERROR_MSG, "下发失败,当前无可下发的设备!");
				return result;
			}

			for (Object obj : devices) {
				long start2 = System.currentTimeMillis();
				Object[] objs = (Object[]) obj;
				IDevice device = (IDevice)objs[0];
				IDeviceSoftVersion deviceSoftVersion = (IDeviceSoftVersion)objs[1];
				Long deviceId = device.getId();
				ITask task = taskService.make();
				logger.info("execute one task times " + (System.currentTimeMillis() - start2));
				task.setDeviceId(deviceId);
				String dsv = deviceSoftVersion.getTypeName() + "_" + deviceSoftVersion.getVersionNo();
				if (dsv != null) {
					task.setVersionBeforeUpdate(dsv);
				}
				task.setVersion(version);
				task.setEagerRestart(form.isEagerRestart());
				tasks.add(task);
			}
			logger.info("execute all task times " + (System.currentTimeMillis() - start1));
		} else {
			String deviceIds = form.getDeviceIds();
			if (StringUtils.isNotEmpty(deviceIds)) {
				String[] ids = deviceIds.substring(1).split(",");
				List<Object> lists = deviceSoftVersionService.findByTypeName(versionTypeName);
				Map<Long, Object> maps = convertToMap(lists);
				
				for (String id : ids) {
					Long deviceId = Long.valueOf(id);
					ITask task = taskService.make();
					task.setDeviceId(deviceId);
					String dsv = findDeviceSoftVersion(maps, deviceId);
					if (dsv != null) {
						task.setVersionBeforeUpdate(dsv);
					}
					task.setVersion(version);
					task.setEagerRestart(form.isEagerRestart());
					tasks.add(task);
				}
				logger.info("execute all task times " + (System.currentTimeMillis() - start1));
			}
		}
		int downloadCounter = version.getDownloadCounter()+1;
		IJob job = jobService.make();
		job.setJobName(form.getJobName()+"_"+downloadCounter);
		job.setVersion(versionService.getById(form.getVersionId()));
		job.setJobType(form.getJobType());
		job.setJobPriority(form.getJobPriority());
		job.setCreateUserId(userSession.getUserId());
		if (form.getDeployStartDate() == null || "".equals(form.getDeployStartDate())) {
			job.setDeployStartDate(new Date());
		} else {
			job.setDeployStartDate(DateUtils.getDate(form.getDeployStartDate()));
		}
		if (form.getDeployEndDate() != null && !"".equals(form.getDeployEndDate())) {
			job.setDeployEndDate(DateUtils.getDate(form.getDeployEndDate()));
		}
		job.setDesc(form.getDesc());
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
		jobManager.createJob(job);
		version.setDownloadCounter(downloadCounter);
		versionService.update(version);
		logger.info("execute all task times " + (System.currentTimeMillis() - start1));

		// 回填值到form中
		form.setId(job.getJobId());
		form.setVersionName(version.getFullName() + " [" + version.getServerPath() + "]");

		result.addAttribute("success", true);
		result.addAttribute("data", form);
		return result;
	}
	

	private Map<Long, Object> convertToMap(List<Object> lists) {
		Map<Long, Object> maps = new HashMap<Long, Object>();
		for (Object object : lists) {
			Object[] dsv = (Object[]) object;
			maps.put(Long.valueOf(dsv[0].toString()), object);
		}
		return maps;
	}

	private String findDeviceSoftVersion(Map<Long, Object> maps, Long deviceId) {
		Object object = maps.get(deviceId);
		if (object != null) {
			Object[] dsv = (Object[]) object;
			return dsv[1].toString() + "_" + dsv[2].toString();
		}
		return null;
	}

	// 撤销作业
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	ModelMap delete(@PathVariable long id) {
		logger.info(" delete job: job.id = " + id);
		ModelMap result = new ModelMap();
		try {
			jobManager.cancelJob(id);
			result.addAttribute("success", true);
		} catch (Exception ex) {
			logger.error("撤销作业失败：" + ex.getMessage());
			result.addAttribute("success", false);
			result.addAttribute("errors", ex.getMessage());
		}
		return result;
	}

	// 转换数据格式
	private List<JobForm> toForm(List<IJob> jobs) {
		List<JobForm> forms = new ArrayList<JobForm>();
		for (IJob job : jobs) {
			if (JobType.AUTO_UPDATE.equals(job.getJobType())) {
				IFilter filter = new Filter();
				filter.eq("deviceExtend.version", job.getVersion().getVersionNo());
				int devVersionCount = deviceService.list(filter).size();
				int repeatDevVersionCount = jobService.getRepeatTaskByJob(job.getJobId());
				forms.add(new JobForm(job, devVersionCount, repeatDevVersionCount));
			} else {
				forms.add(new JobForm(job));
			}

		}
		return forms;
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
			if (name.equals("versionTypeId")) {
				if (!value.equals("0")) {
					filter.eq("version.versionType.id", Long.valueOf(value));
				}
			} else if (name.equals("versionNo")) {
				filter.like("version.versionNo", value);
			} else if ("jobId".equals(name)) {
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
	public @ResponseBody
	ModelMap linked(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search job : start = %s ,limit = %s ", start, limit));
		IFilter filter = new Filter();// getFilter(request);
		IJob job = null;
		IPageResult<IDevice> page = downloadService.pageLinkedDevices(start, limit, job, filter);
		ModelMap result = new ModelMap();
		result.addAttribute("success", true);
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
	public @ResponseBody
	ModelMap selectable(@RequestParam int start, @RequestParam int limit, @RequestParam int versionId,
			WebRequest webRequest, HttpServletRequest request) {
		logger.info(String.format("search selectable device : start = %s ,limit = %s , versionId = %s ", start, limit,
				versionId));
		IFilter filter = getDeviceFilter(webRequest, request);
		String atmGroup = request.getParameter("atmGroup");
		if (atmGroup != null && !"".equals(atmGroup)) {
			filter.eq("atmGroup", Long.parseLong(atmGroup));
		}
		IVersion version = versionService.getById(versionId);
		IPageResult<LinkedDeviceForm> page = null;
		page = downloadService.pageDevices(start, limit, version,filter);

		ModelMap result = new ModelMap();
		result.addAttribute("success", true);
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

			if (name.equals("orgId")) {
				orgFlag = orgService.get(value).getOrgFlag();
			}

			if (name.equals("ip")) {
				filter.eq("ip", new IP(value));
			}

			if (name.equals("atmTypeId")) {
				filter.eq("atmTypeId", value);
			}

			if (name.equals("terminalId")) {
				filter.eq("terminalId", value);
			}
		}
		filter.eq("orgFlag", orgFlag);
		return filter;
	}

	// 根据jobId获得下面的任务列表
	@RequestMapping(value = "/task", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap task(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		IFilter filter = getTaskFilter(request);
		ModelMap result = new ModelMap();
		result.addAttribute("success", true);
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
			if (updateResult.equals("NEW")) {
				filter.eq("task.status", TaskStatus.NEW);
			} else if (updateResult.equals("RUN")) {
				filter.eq("task.status", TaskStatus.RUN);
			} else if (updateResult.equals("NOTICED")) {
				filter.eq("task.status", TaskStatus.NOTICED);
			} else if (updateResult.equals("NOTICED_FAIL")) {
				filter.eq("task.status", TaskStatus.NOTICED_FAIL);
			} else if (updateResult.equals("DOWNLOADED")) {
				List<TaskStatus> status = new ArrayList<TaskStatus>();
				status.add(TaskStatus.DOWNLOADED);
				status.add(TaskStatus.DEPLOYED_WAIT);
				filter.in("task.status", status);
			} else if (updateResult.equals("DOWNLOADED_FAIL")) {
				filter.eq("task.status", TaskStatus.DOWNLOADED_FAIL);
			} else if (updateResult.equals("DEPLOYED")) {
				filter.eq("task.status", TaskStatus.DEPLOYED);
			} else if (updateResult.equals("DEPLOYED_FAIL")) {
				filter.eq("task.status", TaskStatus.DEPLOYED_FAIL);
			} else if (updateResult.equals("CHECKED")) {
				filter.eq("task.status", TaskStatus.CHECKED);
			} else if (updateResult.equals("DEPLOYED_WAIT")) {
				filter.eq("task.status", TaskStatus.DEPLOYED_WAIT);
			} else if (updateResult.equals("DOWNLOADING")) {
				filter.eq("task.status", TaskStatus.DOWNLOADING);
			} else if (updateResult.equals("CANCEL_UPDATE_OK")) {
				filter.eq("task.status", TaskStatus.CANCEL_UPDATE_OK);
			} else if (updateResult.equals("FAIL_ROLLBACK")) {
				filter.eq("task.status", TaskStatus.FAIL_ROLLBACK);
			}  else if (updateResult.equals("OTHER")) {
				List<TaskStatus> status = new ArrayList<TaskStatus>();
				status.add(TaskStatus.CANCEL_FAIL);
				status.add(TaskStatus.CANCEL_UPDATE_FAIL);
				status.add(TaskStatus.CANCELED);
				status.add(TaskStatus.NOTICE_APP_FAIL);
				status.add(TaskStatus.NOTICE_APP_OK);
				status.add(TaskStatus.OTHER);
				status.add(TaskStatus.REMOVED);
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
	public void export(WebRequest webRequest, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

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
		String[] headers = new String[] { "终端号", "设备IP", "所属机构", "分发前版本", "分发版本", "任务状态", "执行时间", "版本下载开始时间",
				"版本下载完成时间", "备注", "重启ATM" };
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
			if(device==null){
				continue ;
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
			row.add(task.getStatus().getText());
			// row.add(task.getExceptVersion());
			row.add(task.getExcuteTime() == null ? "" : DateUtils.getTimestamp(task.getExcuteTime()));
			row.add(task.getDownloadStartTime());
			row.add(task.getDownloadFinishTime());
			row.add(task.getReason() == null ? "" : task.getReason());
			data.add(row);
		}
		excel.setData(data);

		String fileName = FishCfg.getTempDir() + File.separator + "作业_" + DateUtils.getDate(new Date()) + ".xls";
		excel.export(fileName, "执行结果");

		File file = new File(fileName);
		DownFromWebUtils.download(file, request, response);
	}

	/**
	 * 撤销一个任务
	 *
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/task/cancel", method = RequestMethod.POST)
	public @ResponseBody
	ModelMap cancelTask(@RequestParam long jobId, @RequestParam long taskId) {
		logger.info(" cancle task: task.id = " + taskId);
		ModelMap result = new ModelMap();
		try {
			jobManager.cancelTask(jobId, taskId);
			result.addAttribute("success", true);
		} catch (Exception ex) {
			result.addAttribute("success", false);
			result.addAttribute("errors", ex.getMessage() == null ? "" : ex.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/task/deepCancel", method = RequestMethod.POST)
	public @ResponseBody
	ModelMap deepCancelTask(@RequestParam long jobId, @RequestParam long taskId) {
		logger.info(" deep cancel task: task.id = " + taskId);
		ModelMap result = new ModelMap();
		try {
			taskService.deepCancelApp(taskId);
			result.addAttribute("success", true);
		} catch (Exception ex) {
			result.addAttribute("success", false);
			result.addAttribute("errors", ex.getMessage() == null ? "" : ex.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/task/reDistribute", method = RequestMethod.POST)
	public @ResponseBody
	ModelMap reDistribute(@RequestParam long taskId) {
		logger.info(" reDistribute task: task.id = " + taskId);
		ModelMap result = new ModelMap();
		try {
			taskService.reDistribute(taskId);
			result.addAttribute("success", true);
		} catch (Exception ex) {
			result.addAttribute("success", false);
			result.addAttribute("errors", ex.getMessage() == null ? "" : ex.getMessage());
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
	@RequestMapping(value = "/pause", method = RequestMethod.POST)
	public @ResponseBody
	String pauseJob(@RequestParam long id) {
		try {
			jobManager.suspendJob(id);
			return "{'success':true}";
		} catch (Exception ex) {
			return "{'success':false,'errors':'" + ex.getMessage() + "'}";
		}
	}

	private List<TaskForm> toTaskForm(List<ITask> tasks) {
		List<TaskForm> forms = new ArrayList<TaskForm>();
		for (ITask task : tasks) {
			forms.add(new TaskForm(task));
		}
		return forms;
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
	public @ResponseBody
	ModelMap rebootAll(@RequestParam long jobId) {
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
	@RequestMapping(value = "/rebootOne", method = RequestMethod.POST)
	public @ResponseBody
	ModelMap rebootOne(@RequestParam long taskId) {
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

			result.put("success", true);
			result.addAttribute("appRet", appRet);
		} catch (Exception e) {
			result.put("success", false);
			result.addAttribute("errors", e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/updateDeployDate", method = RequestMethod.POST)
	public @ResponseBody
	ModelMap update(@RequestParam long jobId, @RequestParam String deployStartDate, @RequestParam String deployEndDate) {
		logger.info(" updateDeployDate  : jobId.id = " + jobId);
		ModelMap result = new ModelMap();
		try {
			Date startDate = DateUtils.getDate(deployStartDate);
			Date endDate = null;
			if (deployEndDate != null && !"".equals(deployEndDate)) {
				endDate = DateUtils.getDate(deployEndDate);
			}
			jobService.updateDeployDate(jobId, startDate, endDate);
			result.addAttribute("success", true);
		} catch (Exception ex) {
			result.addAttribute("success", false);
			result.addAttribute("errors", ex.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/queryUpdateDeployDateHist", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap searchUpdateDeployDateHist(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search job : start = %s ,limit = %s ", start, limit));
		// IFilter filter = new Filter();
		IFilter filter = getFilter(request);
		// filter.addOrder(new OrderBy("createdTime", OrderBy.DESC));
		// filter.addOrder(new OrderBy("updateDeployDateHistory.noticeTime",
		// OrderBy.DESC));
		filter.descOrder("noticeTime");
		IPageResult<IUpdateDeployDateHistory> pageResult = updateDeployDateHistoryService.page(start, limit, filter);
		ModelMap result = new ModelMap();
		result.addAttribute("success", true);
		result.addAttribute("total", pageResult.getTotal());
		result.addAttribute("data", toUpdateDeployDateHistoryForm(pageResult.list(), start));
		return result;
	}

	public List<UpdateDeployDateHistoryForm> toUpdateDeployDateHistoryForm(List<IUpdateDeployDateHistory> list,
			int start) {
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
	public @ResponseBody
	ModelMap reNoticeApp(@RequestParam long updateDeployDateHistoryId, WebRequest request) {
		logger.info(String.format("reNoticeApp job : updateDeployDateHistoryId = %s  ", updateDeployDateHistoryId));
		ModelMap result = new ModelMap();
		try {
			taskService.reNoticeApp(updateDeployDateHistoryId);
			result.addAttribute("success", true);
		} catch (Exception e) {
			result.addAttribute("success", false);
		}
		return result;
	}
}
