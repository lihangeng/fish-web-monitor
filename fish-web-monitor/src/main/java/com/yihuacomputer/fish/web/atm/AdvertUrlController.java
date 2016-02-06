package com.yihuacomputer.fish.web.atm;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.jackson.JsonUtils;

@Controller
@RequestMapping("/msg/advertUrl")
public class AdvertUrlController {
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	String reciveMsg(HttpServletRequest request) {
		ModelMap result = new ModelMap();
		String callbackName = (String)request.getParameter("jsoncallback");
		String terminalId = request.getParameter("terminalId");
		if("1".equals(terminalId))
			result.put("ret", "10");
		else{
			result.put("ret", "aaa");
		}
		String renderStr = callbackName+"("+JsonUtils.toJson(result)+")";
		return renderStr;
	}
}
