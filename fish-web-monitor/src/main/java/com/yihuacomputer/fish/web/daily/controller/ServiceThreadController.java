package com.yihuacomputer.fish.web.daily.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.fish.api.atmlog.IBackupManagerStatus;
import com.yihuacomputer.fish.api.version.job.IJobManangerStatus;
import com.yihuacomputer.fish.web.daily.form.ServiceThreadForm;


@Controller
@RequestMapping("/service/thread")
public class ServiceThreadController {
    
    private final Logger logger = org.slf4j.LoggerFactory
            .getLogger(ServiceThreadController.class);

    @Autowired
    private IBackupManagerStatus backupManager;
    
    @Autowired
    private IJobManangerStatus JobManager;

    /**
     * 
     * 根据条件得到线程状态列表
     * 
     * @param form
     * @return ModelMap<String, Object>
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ModelMap lookServiceThread(WebRequest request) {
        logger.info("search thread");
        ModelMap result = new ModelMap();
        ServiceThreadForm form = new ServiceThreadForm();
        form.setActiveRuners(backupManager.getActiveRuners());
        form.setDaybackThreadState(backupManager.getDaybackThreadState());
        form.setRedoBackTreadState(backupManager.getRedoBackTreadState());
        form.setActiveTaskCount(JobManager.getActiveTaskCount());
        form.setJobManagerState(JobManager.getJobManagerState());
        form.setJobQueueCount(JobManager.getJobQueueCount());
        form.setMaxJobCount(JobManager.getMaxJobCount());
        form.setTaskMangerState(JobManager.getTaskMangerState());
        result.addAttribute("success", true);
        result.addAttribute("data", form);
        return result;
    }

}
