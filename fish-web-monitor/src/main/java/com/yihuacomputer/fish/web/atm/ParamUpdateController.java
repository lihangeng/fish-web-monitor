package com.yihuacomputer.fish.web.atm;

import java.io.File;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.parameter.IParamPublish;
import com.yihuacomputer.fish.api.parameter.IParamPublishResult;
import com.yihuacomputer.fish.api.parameter.IParamPublishResultService;
import com.yihuacomputer.fish.api.parameter.IParamPublishService;
import com.yihuacomputer.fish.api.parameter.ParamPublishMsg;
import com.yihuacomputer.fish.api.parameter.ParamPublishRet;
import com.yihuacomputer.fish.api.version.VersionCfg;
import com.yihuacomputer.fish.api.version.job.JobType;
import com.yihuacomputer.fish.parameter.service.ParamPublishService;

/**
 * 参数下发
 * 
 * @author zhengnan
 *
 */

@Controller
@RequestMapping("/msg/paramUpdate")
public class ParamUpdateController {

	private Logger logger = LoggerFactory.getLogger(AutoUpdateController.class);

	@Autowired
	private IParamPublishService paramPublishService;
	@Autowired
	private IDeviceService deviceService;
	
	@Autowired
	private IParamPublishResultService paramPublishResultService;

	/**
	 * 接收参数自动更新请求
	 *
	 * @param paramUpdateMsg
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ParamPublishMsg reciveMsg(@RequestBody ParamPublishMsg msg) {
		IDevice device = deviceService.get(msg.getTerminalId());
		if(null==device){
			logger.error(String.format("device %s not exist", msg.getTerminalId()));
			return msg;
		}
		try {
			Map<String,Long> deviceMap = paramPublishService.getMaxVersionNoInfoByDeviceId(device.getId());
			long versionNo = deviceMap.get(ParamPublishService.MAX_VERSION_TIMESTAMP);
			IParamPublish paramPublish = paramPublishService.make();
			paramPublish.setJobType(JobType.AUTO_UPDATE);
			paramPublish.setDate(DateUtils.getTimestamp(new Date()));
			paramPublish.setPublisher(0);
			paramPublishService.save(paramPublish);
			logger.debug(String.format("paramPublish job create success,id is %d",paramPublish.getId()));
			IParamPublishResult paramPublishResult = paramPublishResultService.make();
			paramPublishResult.setDevice(device);
			paramPublishResult.setDeviceId(device.getId());
			paramPublishResult.setParamPublish(paramPublish);
			paramPublishResult.setRet(ParamPublishRet.NEW);
			paramPublishResult.setVersionNo(versionNo);
			paramPublishResultService.save(paramPublishResult);
			logger.debug(String.format("paramPublishResult task create success,id is %d",paramPublishResult.getId()));
			msg.setTaskId(paramPublishResult.getId());
			msg.setVersionNo(versionNo);
			msg.setServerPath(VersionCfg.getAtmParamDir()+File.separator+versionNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
   
}
