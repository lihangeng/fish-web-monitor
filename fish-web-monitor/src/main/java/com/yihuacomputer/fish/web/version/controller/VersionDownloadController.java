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
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionDownloadService;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.IVersionStaticsStautsService;
import com.yihuacomputer.fish.api.version.LinkedDeviceForm;
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
    private IVersionStaticsStautsService versionStaticsStatusService;

    @Autowired
    private ITaskService taskService;
    
    @Autowired
    private IOrganizationService organizationService;


	@Autowired
	private IDeviceSoftVersionService deviceSoftVersionService;

    @Autowired
    private IUpdateDeployDateHistoryService updateDeployDateHistoryService;

    @Autowired
    private MessageSource messageSourceVersion;
    


    /**
     * 创建批量任务
     * @param form
     * @param request
     * @return
     * 可以全批量的下发所有设备 {@link JobForm.allDevice}
     */
    // 增加
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ModelMap add(@RequestBody JobForm form,HttpServletRequest request) {
        logger.info(" add job...");
        ModelMap result = new ModelMap();
        IVersion version = versionService.getById(form.getVersionId());
        String deviceIds = form.getDeviceIds();
        UserSession session = (UserSession)request.getSession().getAttribute(FishWebUtils.USER);
        Long[] ids = null;
    	List<Long> list = new ArrayList<Long>();
        if(form.isAllDevice()){
        	IFilter filter = new Filter();
        	filter.eq("orgFlag", session.getOrgFlag());
        	IPageResult<Object> pageResult = downloadService.getCanPushDevicePagesInfo(0, Integer.MAX_VALUE, version, filter);
        	for(Object obj:pageResult.list()){
        		Object [] objects = (Object []) obj;
        		IDevice device = (IDevice)objects[0];
        		list.add(device.getId());
        	}
        	
        }
        else{
        	String deviceIdArray[] = deviceIds.substring(1).split(",");
        	for(String deviceId:deviceIdArray){
        		list.add(Long.parseLong(deviceId));
        	}
        }

        ids = list.toArray(new Long[0]);
        createThreadExecuteTask(ids,request.getLocalAddr(),form,version);
        form.setVersionName(version.getFullName() + " [" + version.getServerPath() + "]");
        form.setDownLoadCounter(version.getDownloadCounter());
        versionService.updateDownLoadCounter(version);
        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("data", form);
        return result;
    }
    private static Map<String,ExecuteTask> threadMap = new HashMap<String,ExecuteTask>();
    private void createThreadExecuteTask(Long[] deviceId,String executeIp,JobForm form,IVersion version){
		ExecuteTask execute = new ExecuteTask();
		String batchName = messageSourceVersion.getMessage("version.download.batchNumber", new Object[]{form.getJobName(),version.getDownloadCounter()}, FishCfg.locale);
		execute.setDeviceId(deviceId);
		execute.setExecuteMachine(executeIp);
		execute.setForm(form);
		execute.setBatchName(batchName);
		execute.setVersion(version);
		Thread thread = new Thread(execute);
		thread.setName(batchName);
		thread.start();
		threadMap.put(batchName, execute);
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
        	String cantCancel = messageSourceVersion.getMessage("version.task.cancel.cantcancel", null, FishCfg.locale);
        	String tasknotExist = messageSourceVersion.getMessage("version.task.notexsit", null, FishCfg.locale);
        	ITask task = taskService.get(id);
        	if(null==task){
        		return "{'success':false,'errors':'"+tasknotExist+"'}";
        	}
        	if(TaskType.AUTO_UPDATE==task.getTaskType()){
        		return "{'success':false,'errors':'"+cantCancel+"'}";
        	}
        	String batch = task.getTaskBatchName();
        	ExecuteTask thread = threadMap.get(batch);
        	if(thread!=null){
            	//终止线程新建任务
            	thread.flag=false;
        	}
        	//修改new任务为cancel
        	
        	boolean executeResult = taskService.cancelTasks(batch,task.getVersion().getId());
            return executeResult?"{'success':true}":"{'success':false,'errors':'"+cantCancel+"'}";
        }
        catch (Exception ex) {
            return "{'success':false,'errors':'" + ex.getMessage() + "'}";
        }
    }
    
	class ExecuteTask implements Runnable{
		private Long[] deviceId;
		
		private boolean flag = true;
		
		private JobForm form;

		private String executeMachine;
		
		private String batchName;
		
		private IVersion version;

    	Date createdTime = new Date();
		public Long[] getDeviceId() {
			return deviceId;
		}

		public void setDeviceId(Long[] deviceId) {
			this.deviceId = deviceId;
		}

		public JobForm getForm() {
			return form;
		}

		public void setForm(JobForm form) {
			this.form = form;
		}

		public String getExecuteMachine() {
			return executeMachine;
		}

		public void setExecuteMachine(String executeMachine) {
			this.executeMachine = executeMachine;
		}

		public IVersion getVersion() {
			return version;
		}

		public void setVersion(IVersion version) {
			this.version = version;
		}

		public String getBatchName() {
			return batchName;
		}

		public void setBatchName(String batchName) {
			this.batchName = batchName;
		}

		@Override
		public void run() {
			try{
		        String versionTypeName = this.getVersion().getVersionType().getTypeName();
	
		    	List<Object> lists = deviceSoftVersionService.findDeviceSoftVersions(versionTypeName);
		    	List<ITask> newTaskList = new ArrayList<ITask>();
		        Map<Long,String> maps = convertToMap(lists);
				IFilter taskFilter = new Filter();
		        taskFilter.eq("version.id", form.getVersionId());
		        List<ITask> taskList = taskService.list(taskFilter);
		        Map<Long,ITask> taskMap = convertTaskListToMap(taskList);
		        Date planTime = form.getPlanTime() == null ? new Date() : form.getPlanTime();
		        TaskType taskType = TaskType.valueOf(form.getTaskType());
		        String executeMachine = this.getExecuteMachine();
		        for (Long deviceId : deviceId) {
		        	if(!flag){
		        		break;
		        	}
		            ITask task = taskMap.get(deviceId);
		            //如果存在相关的任务则要判断当前是否可以下发
		            if(null != task){
		            	 if(!TaskStatus.canCreate(task.getStatus())){
		            		 continue;
		            	 }
		            	 else{
		            		 task.setStatus(TaskStatus.NEW);
		            		 task.setTaskType(TaskType.MANUAL);
		            		 task.setCreateTime(createdTime);
		            		 task.setTaskCount(task.getTaskCount()+1);
		            	 }
		            }
		            else{
		            	task = taskService.make(createdTime);
		            }
		            task.setTaskBatchName(batchName);
		            task.setDeviceId(deviceId);
		            String versionNo = maps.get(deviceId);
		            if(versionNo != null){
		                task.setVersionBeforeUpdate(versionTypeName+ "_" + versionNo);
		            }
		            task.setVersion(version);
		            task.setCreateTime(createdTime);
		            task.setPlanTime(planTime);
		            task.setTaskType(taskType);
		            task.setExcuteMachine(executeMachine);
		    		taskService.addTask(task);
		    		newTaskList.add(task);
		        }
	            taskManager.createTasksByWeb(newTaskList);
			}catch(Exception e){
				logger.error(e.getMessage());
			}finally{
				threadMap.remove(this.getBatchName());
			}
		}
		
	}
    
    private Map<Long, String> convertToMap(List<Object> lists) {
    	Map<Long,String> maps = new HashMap<Long,String>();
    	for(Object object : lists){
    		Object[] obj = (Object[])object;
    		maps.put(Long.valueOf(obj[0].toString()), obj[1].toString());
    	}
		return maps;
	}
    private Map<Long, ITask> convertTaskListToMap(List<ITask> lists) {
    	Map<Long,ITask> maps = new HashMap<Long,ITask>();
    	for(ITask task : lists){
    		maps.put(task.getDeviceId(), task);
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
        UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
        if(null==filter.getValue("orgFlag")){
        	filter.eq("orgFlag", userSession.getOrgFlag());
        }
        IVersion version = versionService.getById(versionId);
        IPageResult<LinkedDeviceForm> page = downloadService.pageDownLoadDevices(start, limit, version, filter);
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
            	IOrganization org = organizationService.get(value);
                filter.eq("orgFlag", org.getOrgFlag());
            }

            if (name.equals("ip")) {
                filter.eq(name, value);
            }

            if (name.equals("atmTypeId")) {
                filter.eq(name, Long.valueOf(value));
            }

            if (name.equals("terminalId")) {
                filter.like(name, value);
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
            	filter.like("task.taskBatchName", value);
            }else if(name.equals("taskType")){
            	filter.eq("task.taskType", TaskType.getById(Integer.parseInt(value)));
            }else if(name.equals("versionTypeId")){
            	filter.eq("task.version.versionType.id", Long.parseLong(value));
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

        IFilter filter =	getTaskFilter(webRequest);
        List<ITask> tasks = taskService.export(filter).list();

        Excel excel = new Excel();

        String[] headers = new String[]{messageSourceVersion.getMessage("version.export.jobBatchName", null, FishCfg.locale),
        		messageSourceVersion.getMessage("version.export.devId", null, FishCfg.locale),
        		messageSourceVersion.getMessage("version.export.updateVersionNo", null, FishCfg.locale),
        		messageSourceVersion.getMessage("version.export.taskStatus", null, FishCfg.locale),
        		messageSourceVersion.getMessage("version.export.ipAddress", null, FishCfg.locale),
        		messageSourceVersion.getMessage("version.export.org", null, FishCfg.locale),
        		messageSourceVersion.getMessage("version.export.versionNoBeforeUpdate", null, FishCfg.locale),
        		messageSourceVersion.getMessage("version.export.exceptVersionNo", null, FishCfg.locale),
        		messageSourceVersion.getMessage("version.export.executeTime", null, FishCfg.locale),
        		messageSourceVersion.getMessage("version.export.downSource", null, FishCfg.locale),
        		messageSourceVersion.getMessage("version.export.planTime", null, FishCfg.locale),
        		messageSourceVersion.getMessage("version.export.excuteMachine", null, FishCfg.locale),
        		messageSourceVersion.getMessage("version.export.remark", null, FishCfg.locale),
        		};
        excel.setHeaders(headers);

        // 填充数据
        List<List> data = new ArrayList<List>();
        for (ITask task : tasks) {
            List row = new ArrayList();
            IDevice device = task.getDevice();
            row.add(task.getTaskBatchName());
            row.add(device.getTerminalId());
            row.add(task.getVersion().getVersionNo());
            row.add(getEnumI18n(task.getStatus().getText()));
            row.add(device.getIp().toString());
            row.add(device.getOrganization().getName());
            row.add(task.getVersionBeforeUpdate() == null ? "" : task.getVersionBeforeUpdate());
            row.add(task.getExceptVersion());
            row.add(task.getExcuteTime() == null ? "" : DateUtils.getTimestamp(task.getExcuteTime()));
            row.add(task.getDownSource());
            row.add(task.getPlanTime().toString());
            row.add(task.getExcuteMachine());
            row.add(task.getReason() == null ? "" : task.getReason());
            data.add(row);
        }
        excel.setData(data);
        String fileName = messageSourceVersion.getMessage("version.export.fileName", new Object[]{FishCfg.getTempDir() + File.separator,DateUtils.getDate(new Date())}, FishCfg.locale);
        String result = messageSourceVersion.getMessage("version.export.result", null, FishCfg.locale);
        excel.export(fileName, result);

        File file = new File(fileName);
        DownFromWebUtils.download(file, request, response);
    }

	@Autowired
	private MessageSource messageSourceEnum;
    private String getEnumI18n(String enumText){
    	if(null==enumText){
    		return "";
    	}
    	return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
    }
    /**
     * 撤销一个任务
     *
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/task/cancel", method = RequestMethod.POST)
    public @ResponseBody
    ModelMap cancelTask( @RequestParam long taskId) {
        logger.info(" cancle task: task.id = " + taskId);
        ModelMap result = new ModelMap();
        try {
        	ITask task = taskService.get(taskId);
        	if(task.getStatus()!=TaskStatus.NEW){
        		result.addAttribute(FishConstant.SUCCESS, false);
                result.addAttribute(FishConstant.ERROR_MSG,messageSourceVersion.getMessage("version.task.cancel.cantcancel", null, FishCfg.locale) );
        	}
        	taskService.cancelTask(task);
            result.addAttribute(FishConstant.SUCCESS, true);
        }
        catch (Exception ex) {
            result.addAttribute(FishConstant.SUCCESS, false);
            result.addAttribute(FishConstant.ERROR_MSG, ex.getMessage() == null ? "" : ex.getMessage());
        }
        return result;
    }

   

    private List<TaskForm> toTaskForm(List<ITask> tasks) {
        List<TaskForm> forms = new ArrayList<TaskForm>();
        for (ITask task : tasks) {
            forms.add(convertTaskToTaskForm(task));
        }
        return forms;
    }
    private TaskForm convertTaskToTaskForm(ITask task) {
    	TaskForm taskForm = new TaskForm();
		taskForm.setId(task.getId());
		taskForm.setExcuteTime(task.getExcuteTime() == null ? "" : DateUtils.getTimestamp(task.getExcuteTime()));
		taskForm.setSuccess(task.isSuccess());
		taskForm.setReason(task.getReason());
		taskForm.setTaskStatus(task.getStatus() == null ? "" : getEnumI18n(task.getStatus().getText()));
		taskForm.setVersion(task.getVersion().getVersionNo());
		taskForm.setState(task.getState());
		IDevice device = task.getDevice();
		taskForm.setDeviceId(device.getId());
		taskForm.setTerminalId(device.getTerminalId());
		taskForm.setDeviceIp(device.getIp().toString());
		
		taskForm.setOrgName(device.getOrganization().getName());
		if (task.getVersionBeforeUpdate() != null) {
			int index = task.getVersionBeforeUpdate().indexOf("_");
			taskForm.setVersionBeforeUpdate(task.getVersionBeforeUpdate().substring(index + 1));
		}
		taskForm.setExceptVersion(task.getExceptVersion());
		taskForm.setCurrentVersion("");
		taskForm.setJobName(task.getTaskBatchName());
		taskForm.setPlanTime(DateUtils.getTimestamp(task.getPlanTime()));
		taskForm.setDownSource(task.getDownSource());
		taskForm.setExcuteMachine(task.getExcuteMachine());
		
		taskForm.setProcess(task.getProcess());
		
		return taskForm;
	}
    
    /**
     * 重启某作业下的可以重启的全部设备
     *
     * @param jobId
     *            作业ID
     * @return
     */
//    @RequestMapping(value = "/rebootAll", method = RequestMethod.POST)
//    public @ResponseBody
//    ModelMap rebootAll(@RequestParam long jobId) {
//        logger.info(" reboot all in job: job.id = " + jobId);
//        ModelMap result = new ModelMap();
//
//        return result;
//    }

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
            logger.error(e.getMessage());
            result.addAttribute(FishConstant.ERROR_MSG, messageSourceVersion.getMessage("task.restart.fail", null, FishCfg.locale));
        }
        return result;
    }

    @RequestMapping(value = "/updateDeployDate", method = RequestMethod.POST)
    public @ResponseBody
    ModelMap update(@RequestParam long jobId, @RequestParam String deployStartDate, @RequestParam String deployEndDate) {
        logger.info(" updateDeployDate  : jobId.id = " + jobId);
        ModelMap result = new ModelMap();
        try {
//            Date startDate = DateUtils.getDate(deployStartDate);
//            Date endDate = null;
            if (deployEndDate != null && !"".equals(deployEndDate)) {
//                endDate = DateUtils.getDate(deployEndDate);
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


//    @RequestMapping(value = "/queryUpdateDeployDateHist", method = RequestMethod.GET)
//    public @ResponseBody
//    ModelMap searchUpdateDeployDateHist(@RequestParam int start, @RequestParam int limit, WebRequest request) {
//        logger.info(String.format("search job : start = %s ,limit = %s ", start, limit));
//        IFilter filter = getFilter(request);
//        filter.descOrder("noticeTime");
//        IPageResult<IUpdateDeployDateHistory> pageResult = updateDeployDateHistoryService.page(start, limit, filter);
//        ModelMap result = new ModelMap();
//        result.addAttribute(FishConstant.SUCCESS, true);
//        result.addAttribute("total", pageResult.getTotal());
//        result.addAttribute("data", toUpdateDeployDateHistoryForm(pageResult.list(), start));
//        return result;
//    }

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

//    @RequestMapping(value = "/reNoticeApp", method = RequestMethod.GET)
//    public @ResponseBody
//    ModelMap reNoticeApp(@RequestParam long updateDeployDateHistoryId , WebRequest request) {
//        logger.info(String.format("reNoticeApp job : updateDeployDateHistoryId = %s  ", updateDeployDateHistoryId));
//        ModelMap result = new ModelMap();
//        try {
//            taskService.reNoticeApp(updateDeployDateHistoryId);
//            result.addAttribute(FishConstant.SUCCESS, true);
//        } catch (Exception e) {
//            result.addAttribute(FishConstant.SUCCESS, false);
//        }
//        return result;
//    }
    
    private boolean canReset(TaskStatus status){
	    if((status.equals(TaskStatus.DEPLOYED))
                || (status.equals(TaskStatus.DOWNLOADED))
                || (status.equals(TaskStatus.NEW))
                || (status.equals(TaskStatus.NOTICE_APP_OK))
                || (status.equals(TaskStatus.NOTICED))
                || status.equals(TaskStatus.RUN)
                || status.equals(TaskStatus.DEPLOYED_WAIT)){
	        return true;
	    }
	    return false;
	}
    
    @RequestMapping(value = "/resetTaskStatus", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap resetTaskStatus(@RequestParam long id , WebRequest request) {
        logger.info(String.format("reset taskStatus  : taskId = %s  ", id));
        ModelMap result = new ModelMap();
        try {
        	ITask task = taskService.get(id);
        	if(canReset(task.getStatus())){
	            taskService.webResetTaskStatus(id);
	            result.addAttribute(FishConstant.SUCCESS, true);
        	}
        	else{
        		result.addAttribute(FishConstant.SUCCESS, false);
        		result.addAttribute(FishConstant.ERROR_MSG,messageSourceVersion.getMessage("exception.task.cantResetTask",null,FishCfg.locale));
        	}
        } catch (Exception e) {
            result.addAttribute(FishConstant.SUCCESS, false);
        }
        return result;
    }
    
}
