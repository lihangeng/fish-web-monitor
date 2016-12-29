package com.yihuacomputer.fish.web.atm.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author YiHua
 *
 */
@Controller
@RequestMapping("/msg/testQuartz")
public class quartzTestController {

	/**
	 * @param request
	 * @return
	 */
	@Autowired

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String reciveMsg(HttpServletRequest request) {
		return "";
	}
}
