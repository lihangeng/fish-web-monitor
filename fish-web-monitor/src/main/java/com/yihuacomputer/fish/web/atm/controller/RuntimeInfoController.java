package com.yihuacomputer.fish.web.atm.controller;

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
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.report.IRuntimeInfo;
import com.yihuacomputer.fish.monitor.entity.report.RumtimeInfo;
import com.yihuacomputer.fish.web.atm.format.RuntimeInfoMsg;

/**
 * 提供设备状态报文处理的服务接口
 * @author YiHua
 * */

@Controller
@RequestMapping("/msg/runtimeinfo")
public class RuntimeInfoController {
	
	private Logger logger = LoggerFactory.getLogger(RuntimeInfoController.class);

	
	@Autowired
	private ICollectService collectService;
		
	/**
	 * 接收设备定时状态报告
	 * @param msg
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelMap acceptStatus(@RequestBody RuntimeInfoMsg msg){		
        ModelMap result = new ModelMap();
        result.addAttribute("TermId", msg.getTermId());
        result.addAttribute("ret", "RET_00");	
        logger.info(JsonUtils.toJson(msg));        
        
        IRuntimeInfo runtimeInfo = new RumtimeInfo();
        runtimeInfo.setTermId(msg.getTermId());
        runtimeInfo.setTotalNote(msg.getTotalNoteCount());
        runtimeInfo.setAdminRate(msg.getAdminRate());
        runtimeInfo.setRefusedNote(msg.getRefusedNoteCount());
        runtimeInfo.setCashNote(msg.getCashNoteCount());
        runtimeInfo.setCashTrans(msg.getCashTransCount());
        runtimeInfo.setCashError(msg.getCashTransError());
        runtimeInfo.setOtherTrans(msg.getOtherTransCount());
        runtimeInfo.setCustomRate(msg.getCustomRate());
        runtimeInfo.setAdminRate(msg.getAdminRate());
        runtimeInfo.setStopRate(msg.getStopRate());
        collectService.collectRumtimeInfo( msg.getTermId(), runtimeInfo);
        return result;
	}
}
