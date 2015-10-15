package com.yihuacomputer.fish.web.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.web.command.format.ListProcessForm;
import com.yihuacomputer.fish.web.command.format.ProcessForm;

/**
 * 远程获取系统进程信息
 *
 * @author wangchao
 *
 */
@Controller
@RequestMapping("agent/process")
public class SysProcessController {
	
	@Autowired
	private MessageSource messageSource;
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap searchInstation(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		ModelMap result = new ModelMap();
		try {
			String url = MonitorCfg.getHttpUrl(request.getParameter("ip")) + "/ctr/listprocess";
			ListProcessForm listProcessForm = (ListProcessForm) HttpProxy.httpGet(url, ListProcessForm.class);
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("data", ProcessForm.convert(listProcessForm.getProcessList()));
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("sysProcess.getFail", null, FishCfg.locale));
		}
		return result;
	}
}
