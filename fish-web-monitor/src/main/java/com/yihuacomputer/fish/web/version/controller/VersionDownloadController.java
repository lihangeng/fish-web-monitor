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
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionDownloadService;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.LinkedDeviceForm;
import com.yihuacomputer.fish.api.version.job.IJob;
import com.yihuacomputer.fish.api.version.job.IJobService;
import com.yihuacomputer.fish.api.version.job.IUpdateDeployDateHistory;
import com.yihuacomputer.fish.api.version.job.IUpdateDeployDateHistoryService;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskManager;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
import com.yihuacomputer.fish.api.version.job.task.TaskType;
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
    private ITaskManager taskManager;

    @Autowired
    private IVersionDownloadService downloadService;

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IJobService jobService;

	@Autowired
	private IDeviceSoftVersionService deviceSoftVersionService;

    @Autowired
    private IUpdateDeployDateHistoryService updateDeployDateHistoryService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request) {
        logger.info(String.format("search job : start = %s ,limit = %s ", start, limit));
        IFilter filter = getFilter(request);
        filter.descOrder("createdTime");
        IPageResult<IJob> pageResult = null;/*jobService.page(start, limit, filter);*/
        ModelMap result = new ModelMap();
        result.addAttribute(FishConstant.SUCCESS, true);
       /* result.addAttribute("total", pageResult.getTotal());
        result.addAttribute("data", toForm(pageResult.list()));*/
        return result;
    }

    /**
     * 创建批量任务
     * @param form
     * @param request
     * @return
     */
    // 增加
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ModelMap add(@RequestBody JobForm form,HttpServletRequest request) {
        logger.info(" add job...");
        ModelMap result = new ModelMap();
        IVersion version = versionService.getById(form.getVersionId());
        String versionTypeName = version.getVersionType().getTypeName();
        List<ITask> tasks = new ArrayList<ITask>();
        String deviceIds = form.getDeviceIds();
        if (StringUtils.isNotEmpty(deviceIds)) {
        	Date createdTime = new Date();
        	IJob job = jobService.make();
        	job.setJobName(form.getJobName());
        	job.setCreatedTime(createdTime);
        	jobService.add(job);
        	List<Object> lists = deviceSoftVersionService.findDeviceSoftVersions(versionTypeName);
            Map<Long,String> maps = convertToMap(lists);
            String[] ids = deviceIds.substring(1).split(",");
            for (String id : ids) {
                ITask task = taskService.make();
                Long deviceId = Long.valueOf(id);
                task.setDeviceId(deviceId);
                String versionNo = maps.get(deviceId);
                if(versionNo != null){
                    task.setVersionBeforeUpdate(versionTypeName + "_" + versionNo);
                }
                task.setVersion(version);
                task.setCreateTime(createdTime);
//                task.setEagerRestart(form.isEagerRestart());
                task.setPlanTime(form.getPlanTime() == null ? new Date() : form.getPlanTime());
                task.setTaskType(TaskType.valueOf(form.getTaskType()));
                task.setJob(job);
                task.setExcuteMachine(request.getLocalAddr());
                tasks.add(task);
            }
        }
//        IJob job = jobService.make();
//        job.setJobName(form.getJobName());
//        job.setVersion(versionService.getById(form.getVersionId()));
//        job.setJobType(form.getJobType());
//        job.setJobPriority(form.getJobPriority());
//        UserSession userSession= (UserSession)request.getSession().getAttribute(FishWebUtils.USER);
//        job.setCreateUserId(userSession.getUserId());
//        if (form.getDeployStartDate() == null || "".equals(form.getDeployStartDate())) {
//            job.setDeployStartDate(new Date());
//        } else {
//            job.setDeployStartDate(DateUtils.getDate(form.getDeployStartDate()));
//        }
//        if (form.getDeployEndDate() != null && !"".equals(form.getDeployEndDate())) {
//            job.setDeployEndDate(DateUtils.getDate(form.getDeployEndDate()));
//        }
//        job.setDesc(form.getDesc());
//        if (form.getJobType().equals(JobType.SCHEDULER)) {
//            job.setPlanTime(form.getPlanTime());
//            job.setJobStatus(JobStatus.SCHEDULER);
//        } else {
//            job.setJobStatus(JobStatus.NEW);
//            job.setPlanTime(new Date());
//        }
//        job.addTasks(tasks);
//        jobManager.createJob(job);

        // 回填值到form中
//        form.setId(job.getJobId());
        taskManager.createTasksByWeb(tasks);

        form.setVersionName(version.getFullName() + " [" + version.getServerPath() + "]");

        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("data", form);
        return result;
    }

    private Map<Long, String> convertToMap(List<Object> lists) {
    	Map<Long,String> maps = new HashMap<Long,String>();
    	for(Object object : lists){
    		Object[] obj = (Object[])object;
    		maps.put(Long.valueOf(obj[0].toString()), obj[1].toString());
    	}
		return maps;
	}

    /**
     * 从2.0开始，不在提供此方法
     * @param id
     * @return
     * @since 2.0
     */
	// 撤销作业
    @Deprecated
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ModelMap delete(@PathVariable long id) {
        logger.info(" delete job: job.id = " + id);
        ModelMap result = new ModelMap();
        try {
           /* jobManager.cancelJob(id);*/
            result.addAttribute(FishConstant.SUCCESS, true);
        }
        catch (Exception ex) {
            result.addAttribute(FishConstant.SUCCESS, false);
            result.addAttribute(FishConstant.ERROR_MSG, ex.getMessage());
        }
        return result;
    }

/*    // 转换数据格式
    private List<JobForm> toForm(List<IJob> jobs) {
        List<JobForm> forms = new ArrayList<JobForm>();
        for (IJob job : jobs) {
            forms.add(new JobForm(job));
        }
        return forms;
    }*/

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
//        IPageResult<IDevice> page = downloadService.pageLinkedDevices(start, limit, job, filter);
        ModelMap result = new ModelMap();
        result.addAttribute(FishConstant.SUCCESS, true);
//        result.addAttribute("total", page.getTotal());
        // result.addAttribute("data", toLinkedDeviceForm(page.list()));
        return result;
    }

    /**
     * 还可以关联到作业的设备列表，
     * 对应增加作业页面可选择的设备列表
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
        logger.info(String.format("search selectable device : start = %s ,limit = %s , versionId = %s ", start, limit,versionId));
        IFilter filter = getDeviceFilter(webRequest);
        IVersion version = versionService.getById(versionId);
        UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
        IPageResult<LinkedDeviceForm> page = downloadService.pageDevices(start, limit, version, filter,userSession.getUserId());
        ModelMap result = new ModelMap();
        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("total", page.getTotal());
        result.addAttribute("data", page.list());
        return result;
    }

    private IFilter getDeviceFilter(WebRequest request) {
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
                filter.eq("device.organization", value);
            }

            if (name.equals("ip")) {
                filter.eq("device.ip", new IP(value));
            }

            if (name.equals("atmTypeId")) {
                filter.eq("device.devType.id", Long.valueOf(value));
            }

            if (name.equals("terminalId")) {
                filter.like("device.terminalId", value);
            }
        }
        return filter;
    }

    // 根据jobId获得下面的任务列表
    @RequestMapping(value = "/task", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap task(@RequestParam int start, @RequestParam int limit, WebRequest request) {
        IFilter filter = getTaskFilter(request);
        IPageResult<ITask> page = taskService.page(start, limit, filter);
        ModelMap result = new ModelMap();
        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("total", page.getTotal());
        result.addAttribute("data", toTaskForm(page.list()));
        return result;
    }

    // 获得任务的过滤条件
    private IFilter getTaskFilter(WebRequest request) {
        IFilter filter = new Filter();
       /* String jobId = request.getParameter("jobId");
        if (StringUtils.isEmpty(jobId)) {
            jobId = "0";
        }
        filter.eq("task.job.jobId", Long.valueOf(jobId));*/

//        String updateResult = request.getParameter("updateResult");
//        if (StringUtils.isNotEmpty(updateResult)) {
//            if (updateResult.equals("1")) {// 成功的升级
//                filter.eq("task.status", TaskStatus.CHECKED);
//            } else {
//                filter.ne("task.status", TaskStatus.CHECKED);
//            }
//        }
//
//        String terminalId = request.getParameter("terminalId");
//        if (StringUtils.isNotEmpty(terminalId)) {
//            filter.like("device.terminalId", terminalId);
//        }
//
//
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

            if (name.equals("updateResult")) {
              if (value.equals("1")) {// 成功的升级
            	  filter.eq("task.status", TaskStatus.CHECKED);
	          } else {
	              filter.ne("task.status", TaskStatus.CHECKED);
	          }
            }else if (name.equals("terminalId")) {
                filter.like("device.terminalId", value);
            }else if(name.equals("jobName")){
            	filter.like("task.jobName", value);
            }else if(name.equals("taskType")){
            	filter.eq("task.taskType", value);
            }else if(name.equals("versionType")){
            	filter.eq("task.version.versionType.id", value);
            }else if(name.equals("versionNo")){
            	filter.eq("task.version.versionNo", value);
            }
        }
        filter.ne("task.status", TaskStatus.REMOVED);
        return filter;
    }

    /**
     *
     * 根据条件生成升级报告
     *
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
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
        filter.ne("status", TaskStatus.REMOVED);
        List<ITask> tasks = taskService.list(filter);

        Excel excel = new Excel();
        String[] headers = new String[]{"终端编号", "设备IP", "所属机构", "分发前版本", "分发版本", "预期版本", "执行时间", "执行结果", "备注"};
        excel.setHeaders(headers);

        // 填充数据
        List<List> data = new ArrayList<List>();
        for (ITask task : tasks) {
            List row = new ArrayList();
            IDevice device = task.getDevice();
            row.add(device.getTerminalId());
            row.add(device.getIp().toString());
            row.add(device.getOrganization().getName());
            row.add(task.getVersionBeforeUpdate() == null ? "" : task.getVersionBeforeUpdate());
            row.add(task.getVersion().getVersionNo());
            row.add(task.getExceptVersion());
            row.add(task.getExcuteTime() == null ? "" : DateUtils.getTimestamp(task.getExcuteTime()));
            row.add(task.getStatus().getText());
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
//            jobManager.cancelTask(jobId, taskId);
            result.addAttribute(FishConstant.SUCCESS, true);
        }
        catch (Exception ex) {
            result.addAttribute(FishConstant.SUCCESS, false);
            result.addAttribute(FishConstant.ERROR_MSG, ex.getMessage() == null ? "" : ex.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/task/deepCancel", method = RequestMethod.POST)
    public @ResponseBody ModelMap deepCancelTask(@RequestParam long jobId,@RequestParam long taskId) {
        logger.info(" deep cancel task: task.id = " + taskId);
        ModelMap result = new ModelMap();
        try {
            taskService.deepCancelApp(taskId);
            result.addAttribute(FishConstant.SUCCESS, true);
        } catch (Exception ex) {
            result.addAttribute(FishConstant.SUCCESS, false);
            result.addAttribute(FishConstant.ERROR_MSG, ex.getMessage() == null ? "" : ex.getMessage());
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
            result.addAttribute(FishConstant.SUCCESS, true);
        }
        catch (Exception ex) {
            result.addAttribute(FishConstant.SUCCESS, false);
            result.addAttribute(FishConstant.ERROR_MSG, ex.getMessage() == null ? "" : ex.getMessage());
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
//            jobManager.suspendJob(id);
            return "{'success':true}";
        }
        catch (Exception ex) {
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

            result.put(FishConstant.SUCCESS, true);
            result.addAttribute("appRet", appRet);
        }
        catch (Exception e) {
            result.put(FishConstant.SUCCESS, false);
            result.addAttribute(FishConstant.ERROR_MSG, e.getMessage());
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
//            jobService.updateDeployDate(jobId, startDate, endDate);
            result.addAttribute(FishConstant.SUCCESS, true);
        }
        catch (Exception ex) {
            result.addAttribute(FishConstant.SUCCESS, false);
            result.addAttribute(FishConstant.ERROR_MSG, ex.getMessage());
        }
        return result;
    }


    @RequestMapping(value = "/queryUpdateDeployDateHist", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap searchUpdateDeployDateHist(@RequestParam int start, @RequestParam int limit, WebRequest request) {
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
    public @ResponseBody
    ModelMap reNoticeApp(@RequestParam long updateDeployDateHistoryId , WebRequest request) {
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
