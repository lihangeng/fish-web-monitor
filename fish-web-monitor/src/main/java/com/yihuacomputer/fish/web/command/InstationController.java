package com.yihuacomputer.fish.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.yihuacomputer.fish.web.command.format.InstationForm;
import com.yihuacomputer.fish.web.command.format.ListInstationForm;

/**
 * 软件安装列表控制
 *
 * @author wangchao
 *
 */
@Controller
@RequestMapping("agent/instation")
public class InstationController {

	private Logger logger = LoggerFactory.getLogger(InstationController.class);

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap searchInstation(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		ModelMap result = new ModelMap();
		try {
			String url = MonitorCfg.getHttpUrl(request.getParameter("ip")) + "/ctr/listinstation";
			ListInstationForm listInstationForm = (ListInstationForm) HttpProxy.httpGet(url, ListInstationForm.class);
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("data", InstationForm.convert(listInstationForm.getInstationList()));
		} catch (Exception e) {
			logger.error(String.format("获取软件安装列表失败！[%s]",e));
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, "获取软件安装列表失败.");
		}
		return result;
	}

}
