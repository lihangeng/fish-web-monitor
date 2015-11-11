package com.yihuacomputer.fish.web.command;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.web.command.format.DeviceBoxMsg;

@Controller
@RequestMapping("agent/boxdetail")
public class DeviceBoxController {

	/**
	 * @param id terminalId终端编号
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deviceBox", method = RequestMethod.GET)
	public @ResponseBody ModelMap getDeviceBox(@RequestParam String id,WebRequest request) {
        ModelMap result = new ModelMap();

		String url = MonitorCfg.getHttpUrl(request.getParameter("id"))+"/ctr/boxdetail";

        DeviceBoxMsg msg = (DeviceBoxMsg)HttpProxy.httpGet(url,DeviceBoxMsg.class, 5000);
        if(msg != null){
        	result.addAttribute(FishConstant.SUCCESS, true);
        	result.addAttribute("data", msg);
        }else{
        	result.addAttribute(FishConstant.SUCCESS, false);
        }
        return result;
	}
}
