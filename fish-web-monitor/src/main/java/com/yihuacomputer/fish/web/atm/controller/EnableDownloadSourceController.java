package com.yihuacomputer.fish.web.atm.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
import com.yihuacomputer.fish.web.atm.format.PatchMsg;


@Controller
@RequestMapping("/msg/getdownloadsource")
public class EnableDownloadSourceController {

	@Autowired 
	private ITaskService taskService;
	@Autowired 
	private IDeviceService deviceService;
	
	private Logger logger = LoggerFactory.getLogger(EnableDownloadSourceController.class);
	/**
	 * 返回可下载数据的IP列表
	 * @param msg
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelMap getDownloadSource(@RequestBody PatchMsg msg) {
		logger.debug(JsonUtils.toJson(msg));
		ModelMap result = new ModelMap();
		long taskId = msg.getTaskId();
		ITask task = taskService.get(taskId);
		List<String> list = new ArrayList<String>();
		//获取本次任务的设备
		if(null==task){
			return result;
		}
		IDevice device = task.getDevice();
		if(null==device){
			return result;
		}
		IOrganization org = device.getOrganization();
		//找到本次任务设备同网点的设备
		IFilter filter = new Filter();
		filter.eq("organization.id", org.getId());
		List<IDevice> deviceList = deviceService.list(filter);
		List<Long> deviceId = new ArrayList<Long>();
		for(IDevice orgDeivce :deviceList){
			deviceId.add(orgDeivce.getId());
		}
		IFilter canPushFilter = new Filter();
		canPushFilter.eq("status", TaskStatus.CHECKED);
		canPushFilter.eq("version.id",task.getVersion().getId());
		canPushFilter.in("deviceId", deviceId);
		List<ITask> taskList = taskService.list(canPushFilter);
		if(null!=taskList && !taskList.isEmpty()){
			for(ITask successTask:taskList){
				IDevice successDevice = successTask.getDevice();
				if(null!=successDevice){
					list.add(successDevice.getIp().toString());
				}
			}
		}
		result.addAttribute("ipList", list);
		return result;
	}
	
}
