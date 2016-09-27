package com.yihuacomputer.fish.web.atm.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.job.IJob;
import com.yihuacomputer.fish.api.version.job.IJobService;
import com.yihuacomputer.fish.api.version.job.JobStatus;
import com.yihuacomputer.fish.api.version.job.JobType;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
import com.yihuacomputer.fish.api.version.job.task.TaskType;
import com.yihuacomputer.fish.web.atm.format.AutoUpdateMsg;
import com.yihuacomputer.fish.web.atm.format.DeployStatus;
import com.yihuacomputer.fish.web.atm.format.PatchMsg;
import com.yihuacomputer.fish.web.atm.format.SimpleVersion;
import com.yihuacomputer.fish.web.atm.format.TaskMsg;

/**
 * 自动更新请求
 *
 * @author YiHua
 * @since 2012/2/11
 * @version 1.0
 */

@Controller
@RequestMapping("/msg/autoupdate")
public class AutoUpdateController {

    private Logger logger = LoggerFactory.getLogger(AutoUpdateController.class);

    // private static String REFUSE_UPDATE = "refuseUpdate";

    @Autowired
    private IVersionService versionService;

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IJobService jobService;

    /**
     * 接收版本自动更新请求
     *
     * @param msg
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody AutoUpdateMsg reciveMsg(@RequestBody AutoUpdateMsg msg) {
        logger.info(msg.getTermId() + " autoUpdate:" + JsonUtils.toJson(msg));
        try {
            for (SimpleVersion version : msg.getCurrentPatches()) {
                // 登记最新版本
                versionService.collectCurrentVersionInfo(msg.getTermId(), version.getTypeName(), version.getVersionNo());
                // 获取可以更新的版本
                IVersion autoUpdateVersion = versionService.autoUpdate(version.getTypeName(), version.getVersionNo());
                if (autoUpdateVersion != null) {
                	IJob job = createVersionUpdateJob(msg, autoUpdateVersion) ;
                    // 保存到任务队列中
                    ITask task = saveTask(msg, autoUpdateVersion,job);
                    if (task != null) {
                    	msg.addAutoUpdatePatch(new PatchMsg(task));
                    }
                }
            }
            // 修改任务的状态
            for (TaskMsg taskMsg : msg.getTasks()) {
                // 任务ID为0,或者任务状态不是部署确认,则重新计算
                if (taskMsg.getTaskId() == 0 || !DeployStatus.CHECKED.equals(taskMsg.getDeployStatus())) {
                    continue;
                }
                ITask task = taskService.get(taskMsg.getTaskId());
                if (task == null) {
                    // 数据库不存在此任务号,需要把状态设备为commited,客户端就会把此任务删除
                    taskMsg.setDeployStatus(DeployStatus.COMMITED);
                    continue;
                }
                // 更新任务状态
                task.setExceptVersion(taskMsg.getDownTypeName() + "_" + taskMsg.getDownVersionNo());
                task.setStatus(TaskStatus.CHECKED);
                task.setReason(null);
                taskService.updateTask(task);
                taskMsg.setDeployStatus(DeployStatus.COMMITED);
            }
        } catch (Exception e) {
            logger.error(String.format("处理自动升级请求异常![%s],请求内容:[%s]", e, JsonUtils.toJson(msg)));
        }
        return msg;
    }

    private IJob createVersionUpdateJob(AutoUpdateMsg msg, IVersion autoUpdateVersion){
        IDevice device = deviceService.get(msg.getTermId());
        if (device == null) {
            // 设备未注册，忽略
            return null;
        }
        IJob job = jobService.getAutoJobByVersionId(autoUpdateVersion) ;
        if(job==null){
        	job = jobService.make() ;
        	job.setJobType(JobType.AUTO_UPDATE);
        	job.setVersion(autoUpdateVersion);
        	job.setJobStatus(JobStatus.COMPLETE);
        	job.setCreateUserId(1);
        	return jobService.cascadeAdd(job,null) ;
        }
    	return job ;
    }

    private ITask saveTask(AutoUpdateMsg msg, IVersion autoUpdateVersion,IJob job) {

        IDevice device = deviceService.get(msg.getTermId());
        if (device == null) {
            // 设备未注册，忽略
            return null;
        }
        IFilter filter = new Filter();
        filter.eq("taskType", TaskType.AUTO_UPDATE);
        filter.eq("version", autoUpdateVersion);
        filter.eq("deviceId", device.getId());

        List<ITask> listTask = taskService.list(filter);

        for (ITask task : listTask) {

            switch (task.getStatus()) {
            case NOTICED:
            case DOWNLOADED_FAIL:
            case DEPLOYED_FAIL:
                break;

            default:
                logger.info(String.format("设备号[%s]已有相同自动更新版本[%s]任务在执行中", device.getTerminalId(), autoUpdateVersion.getVersionNo()));
                return null;
            }
        }


        ITask task = taskService.make(device, autoUpdateVersion.getVersionType().getTypeName());
        task.setVersion(autoUpdateVersion);
        task.setJob(job);
        task.setTaskType(TaskType.AUTO_UPDATE);
        task.setStatus(TaskStatus.NOTICED);
        //设置任务执行时间
        task.setExcuteTime(new Date());
        return taskService.addTask(task);
    }
}
