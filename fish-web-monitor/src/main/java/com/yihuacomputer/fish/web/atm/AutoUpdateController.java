package com.yihuacomputer.fish.web.atm;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersion;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionService;
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

	@Autowired
	private IVersionService versionService;

	@Autowired
	private ITaskService taskService;

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private IDeviceSoftVersionService deviceSoftVersionService;

	/**
	 * 接收版本自动更新请求
	 *
	 * @param msg
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody AutoUpdateMsg reciveMsg(@RequestBody AutoUpdateMsg msg) {
	    logger.info(msg.getTermId() + " autoUpdate:"  + JsonUtils.toJson(msg));
		try{
			for (SimpleVersion version : msg.getCurrentPatches()) {
				//登记最新版本
				versionService.collectCurrentVersionInfo(msg.getTermId(), version.getTypeName(), version.getVersionNo());
				//获取可以更新的版本
				IVersion autoUpdateVersion = versionService.autoUpdate(version.getTypeName(), version.getVersionNo());
				if(autoUpdateVersion != null){
				    //保存到任务队列中
				    ITask task = saveTask(msg,autoUpdateVersion);
				    if(task != null){
				        msg.addAutoUpdatePatch(new PatchMsg(task));
				    }
				}
			}
	        //修改任务的状态
			for(TaskMsg taskMsg : msg.getTasks()){
			    if(taskMsg.getTaskId() == 0){
			        continue;
			    }

			    if(taskMsg.getDeployStatus().equals(DeployStatus.CHECKED)){
			        ITask task = taskService.get(taskMsg.getTaskId());
			        task.setExceptVersion(taskMsg.getDownTypeName()+"_" + taskMsg.getDownVersionNo());
			        task.setStatus(TaskStatus.CHECKED);
			        //任务通过检查时候;将最新的版本号存入设备软件信息中
			        IDeviceSoftVersion dsv = deviceSoftVersionService.get(task.getDeviceId(), task.getVersion().getVersionType().getTypeName());
			        dsv.setVersionStr(task.getVersion().getVersionStr());
			        deviceSoftVersionService.update(dsv);
			        task.setReason(null);
			        task.setDownSource(taskMsg.getDownUrl());
			        taskService.updateTask(task);
			        taskMsg.setDeployStatus(DeployStatus.COMMITED);
			    }
			}
		}catch(Exception e){
			logger.error(String.format("autoupdate exception![%s],request context is:[%s]",e,JsonUtils.toJson(msg)));
//			logger.error(String.format("处理自动升级请求异常![%s],请求内容:[%s]",e,JsonUtils.toJson(msg)));
		}
		return msg;
	}

	private ITask saveTask(AutoUpdateMsg msg,IVersion autoUpdateVersion){
	    IDevice device = deviceService.get(msg.getTermId());
        if(device == null){
            return null;
        }
        Date date = new Date();
        ITask task = taskService.findTask(device.getId(), autoUpdateVersion.getId());
        //如果存在相同的任务了，则不要进行新建
        if(null==task){
        	task = taskService.make(date);
        }
        task.setDevice(device);
        task.setExcuteTime(date);
        task.setPlanTime(date);
        String typeName = autoUpdateVersion.getVersionType().getTypeName();
        IDeviceSoftVersion dsv = deviceSoftVersionService.get(device.getId(), typeName);
    	if(dsv != null){
    		task.setVersionBeforeUpdate(typeName + "_" + dsv.getVersionNo());
    	}
        task.setVersion(autoUpdateVersion);
        task.setTaskType(TaskType.AUTO_UPDATE);
        return taskService.addTask(task);
	}
}
