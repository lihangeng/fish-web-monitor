package com.yihuacomputer.fish.web.atm;

import java.util.ArrayList;
import java.util.Date;
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

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.alarm.IIllegalProcess;
import com.yihuacomputer.fish.api.monitor.alarm.IProcessService;
import com.yihuacomputer.fish.monitor.entity.alarm.IllegalProcess;
import com.yihuacomputer.fish.monitor.entity.alarm.SysProcess;
import com.yihuacomputer.fish.web.atm.format.SchindlerMSg;

/**
 * 提供白名单进程报警服务接口
 * @author YiHua
 * */

@Controller
@RequestMapping("/msg/schindleralarm")
public class SchindlerAlarmController {

	private Logger logger = LoggerFactory.getLogger(SchindlerAlarmController.class);

	@Autowired
	private ICollectService collectService;
	
	@Autowired
	private IProcessService processService;

	/**
	 * 接收白名单进程报警信息
	 * @param schindler
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ModelMap reciveMsg(@RequestBody SchindlerMSg schindler) {
		ModelMap result = new ModelMap();
		result.put("ret", "RET_00");
		try{
			IIllegalProcess illegalProc = processService.makeIlegProc();
			List<IIllegalProcess> processList = new ArrayList<IIllegalProcess>();
			for (SysProcess process : schindler.getProcList()) {	
				illegalProc = new IllegalProcess();
				illegalProc.setName(process.getName());
				illegalProc.setCpuRate(process.getCpuRate());
				illegalProc.setMemoryRate(process.getMemoryRate());
				illegalProc.setUser(process.getUser());	
				illegalProc.setDate(DateUtils.getTimestamp(new Date()));
				processList.add(illegalProc);
			}

			collectService.collectSchindlerAlarm(schindler.getTermId(), processList);
		}catch(Exception e){
            logger.error(String.format("处理黑名单信息异常![%s],信息内容:[%s]",e,JsonUtils.toJson(schindler)));
            logger.error(String.format("collection illegalProc info exception![%s],illegalProc info is :[%s]",e,JsonUtils.toJson(schindler)));
		}
		return result;
	}
}
