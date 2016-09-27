package com.yihuacomputer.fish.web.atm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.business.IRunInfo;
import com.yihuacomputer.fish.api.monitor.business.IRunInfoService;
import com.yihuacomputer.fish.web.atm.format.AppRunInfoMsg;

/**
 * 提供ATM运行状态处理服务接口
 * 
 * @author YiHua
 * @since 2011/12/22
 * 
 */
@Controller
@RequestMapping("/msg/runstatus")
public class AppRunInfoController {
	
	private Logger logger = LoggerFactory.getLogger(AppRunInfoController.class);

	@Autowired
	private ICollectService collectService;
	
	@Autowired
	private IRunInfoService runInfoService;
	
	@Autowired
	protected MessageSource messageSource;
	
	/**
	 * 采集设备应用运行状态信息
	 * @param runInfo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ModelMap reciveMsg(@RequestBody AppRunInfoMsg msg) {		
		ModelMap result = new ModelMap();
		result.put("ret", "RET_00");
		IRunInfo runInfo = runInfoService.make();
		runInfo.setTerminalId(msg.getTermId());
		runInfo.setRunStatus(msg.getRunStatus());
		try{
			collectService.collectATMCRunInfo(msg.getTermId(), runInfo);
		}catch(Exception e){
            logger.error(String.format(messageSource.getMessage("appRunInfo.processError", null, FishCfg.locale),e,JsonUtils.toJson(msg)));
		}
		return result;
	}
}
