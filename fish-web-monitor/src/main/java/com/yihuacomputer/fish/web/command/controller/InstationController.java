package com.yihuacomputer.fish.web.command.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	@Autowired
	private MessageSource messageSource;
	private Logger logger = LoggerFactory.getLogger(InstationController.class);

	/**
	 * @param start
	 * @param limit
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap searchInstation(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		ModelMap result = new ModelMap();
		try {
			String url = MonitorCfg.getHttpUrl(request.getParameter("ip")) + "/ctr/listinstation";
			ListInstationForm listInstationForm = (ListInstationForm) HttpProxy.httpGet(url, ListInstationForm.class, 5000);
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("data", InstationForm.convert(listInstationForm.getInstationList()));
		} catch (Exception e) {
			logger.error(String.format("get installed applications failed![%s]",e));
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG,messageSource.getMessage("command.instation.listFail", null, FishCfg.locale) );
		}
		return result;
	}

}
