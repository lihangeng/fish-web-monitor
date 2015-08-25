package com.yihuacomputer.fish.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.web.command.format.CheckInfoForm;

/**
 * ATM体检控制
 *
 * @author huxiaobao
 *
 */
@Controller
@RequestMapping("agent/check")
public class CheckATMController {

	private Logger logger = LoggerFactory.getLogger(CheckATMController.class);

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap searchCheckInfo(WebRequest request) {
		ModelMap result = new ModelMap();
		try {
			String url = MonitorCfg.getHttpUrl(request.getParameter("ip"))
					+ "/ctr/check";
			CheckInfoForm checkInfoForm = (CheckInfoForm) HttpProxy.httpGet(
					url, CheckInfoForm.class);
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("data", checkInfoForm);
		} catch (Exception e) {
			logger.error(String.format("获取ATM体检信息失败.[%s]", e));
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, "获取ATM体检信息失败.");
		}
		return result;
	}
}
