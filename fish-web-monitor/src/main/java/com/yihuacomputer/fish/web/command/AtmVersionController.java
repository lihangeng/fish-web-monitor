package com.yihuacomputer.fish.web.command;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.monitor.business.IDeviceRegister;
import com.yihuacomputer.fish.api.monitor.business.IRegistService;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.web.command.format.AtmVersionForm;

@Controller
@RequestMapping("agent/atmVersion")
public class AtmVersionController {

	private Logger logger = org.slf4j.LoggerFactory
			.getLogger(AtmVersionController.class);

	@Autowired
	private IRegistService registService;

	@RequestMapping(value = "/versioninfo", method = RequestMethod.POST)
	public @ResponseBody
	ModelMap searchInstation(@RequestParam String terminalId,
			@RequestParam String ip) {
		logger.info("get application version : " + ip);
		ModelMap result = new ModelMap();
		try {
			String url = MonitorCfg.getHttpUrl(ip) + "/ctr/versioninfo";
			AtmVersionForm atmVersionForm = (AtmVersionForm) HttpProxy.httpGet(
					url, AtmVersionForm.class);

			// 当实时获取的ATM版本与数据库ATM版本不同时，以实时获取的为准，并修改数据库ATM版本
			IDeviceRegister deviceRegister = registService.load(terminalId);

			if (atmVersionForm.getAtmcVersion() != null
					&& !atmVersionForm.getAtmcVersion().equals(
							deviceRegister.getAtmcVersion())) {
				deviceRegister.setAtmcVersion(atmVersionForm.getAtmcVersion());
				registService.update(deviceRegister);
			}

			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("data", atmVersionForm);
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		logger.info(JsonUtils.toJson(result));
		return result;
	}

}
