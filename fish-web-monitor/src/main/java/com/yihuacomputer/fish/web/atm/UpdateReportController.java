package com.yihuacomputer.fish.web.atm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.TaskCanceledException;
import com.yihuacomputer.fish.web.atm.format.UpdateReportMsg;

/**
 * 提供版本自动更新报告请求响应服务接口
 *
 * @author YiHua
 * */
@Controller
@RequestMapping("/msg/updateversion")
public class UpdateReportController {

	private Logger logger = LoggerFactory.getLogger(UpdateReportController.class);

	@Autowired
	private IVersionService versionServic;

	/**
	 * 接收版本下发过程中的报告信息
	 *
	 * @param msg
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody UpdateReportMsg reciveMsg(@RequestBody UpdateReportMsg msg) {
		if(msg.getTaskId() > 0){
			try{
				versionServic.collectUpdateReport(msg.getTaskId(),msg.getRet(),msg.getIp());
			}catch(TaskCanceledException cancelException){
				msg.setRet("CANCEL");
				logger.error(String.format("collection UpdateReport is cancel :[%s]",JsonUtils.toJson(msg)));
			}
			catch(Exception e){
	            logger.error(String.format("collection UpdateReport exception![%s],UpdateReport is [%s]",e,JsonUtils.toJson(msg)));
			}
		}
		return msg;
	}
}
