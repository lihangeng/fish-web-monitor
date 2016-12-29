package com.yihuacomputer.fish.web.atm.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsService;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.web.atm.format.StatusKeepAliveMsg;

/**
 * @author GQ
 * 状态心跳包
 */

@Controller
@RequestMapping("/msg/statusAlive")
public class StatusAliveController {

	private Logger logger = LoggerFactory.getLogger(StatusAliveController.class);

	@Autowired
	private ICollectService collectService;

	@Autowired
	private IXfsService xfsService;
	
	/**
	 * @param msg
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelMap acceptStatusAlive(@RequestBody StatusKeepAliveMsg msg){
		
        ModelMap result = new ModelMap();
        result.addAttribute("TermId", msg.getTermId());
        result.addAttribute("ret", "RET_00");

        IXfsStatus xfsStatus = xfsService.loadXfsStatus(msg.getTermId());
        xfsStatus.setDateTime(DateUtils.getTimestamp(new Date()));
        xfsService.updateXfsStatus(xfsStatus);
        if(logger.isDebugEnabled()){
        	logger.debug(String.format("acceptStatusAlive for terminalId is %s ",msg.getTermId()));
        }
        return result;
	}
}
