package com.yihuacomputer.fish.web.atm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.parameter.IParamPublishResult;
import com.yihuacomputer.fish.api.parameter.IParamPublishResultService;
import com.yihuacomputer.fish.api.parameter.IParamPublishService;
import com.yihuacomputer.fish.api.parameter.ParamPublishMsg;
import com.yihuacomputer.fish.api.parameter.ParamPublishRet;

@Controller
@RequestMapping("/msg/paramUpdateResult")
public class ParamUpdateResultController {
	

	private Logger logger = LoggerFactory.getLogger(AutoUpdateController.class);

	@Autowired
	private IParamPublishService paramPublishService;
	@Autowired
	private IDeviceService deviceService;
	
	@Autowired
	private IParamPublishResultService paramPublishResultService;
	
	/**
	 * 接收参数上报更新状态
	 *
	 * @param paramUpdateMsg
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody IParamPublishResult reciveMsg(@RequestBody ParamPublishMsg msg) {
		IParamPublishResult paramPublishResult = paramPublishResultService.get(msg.getTaskId());
		if(null==paramPublishResult){
			logger.error(String.format("param update task %d not exist", msg.getTaskId()));
			return null;
		}
		try {
			paramPublishResult.setRet(ParamPublishRet.getById(msg.getRet()));
			paramPublishResult = paramPublishResultService.update(paramPublishResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paramPublishResult;
	}
}
