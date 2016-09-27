package com.yihuacomputer.fish.web.atm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.fish.api.monitor.alarm.IProcess;
import com.yihuacomputer.fish.api.monitor.alarm.IProcessService;
import com.yihuacomputer.fish.monitor.entity.alarm.SysProcess;
import com.yihuacomputer.fish.web.atm.format.SchindlerMSg;

/**
 * 提供白名单进程列表下载服务接口
 * 
 * @author YiHua
 * @since 2011/12/22
 * */

@Controller
@RequestMapping("/msg/schindlerlist")
public class SchindlerController {

	@Autowired
	private IProcessService processService;

	/**
	 * 接收白名单进程下载请求信息
	 * @param runInfo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	SchindlerMSg reciveMsg(@RequestBody SchindlerMSg schindler) {

		List<SysProcess> processList = new ArrayList<SysProcess>();
		for (IProcess pro : processService.getSchindlerList()) {
			processList.add((SysProcess) pro);
		}
		schindler.setProcList(processList);

		return schindler;
	}
}
