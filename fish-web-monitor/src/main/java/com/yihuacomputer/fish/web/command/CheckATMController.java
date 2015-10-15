package com.yihuacomputer.fish.web.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
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
	
	@Autowired
	private MessageSource messageSource;

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
			logger.error(String.format(messageSource.getMessage("checkATM.fail", null, FishCfg.locale)+"[%s]", e));
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("checkATM.fail", null, FishCfg.locale));
		}
		return result;
	}
}
