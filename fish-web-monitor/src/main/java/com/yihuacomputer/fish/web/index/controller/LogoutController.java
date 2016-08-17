package com.yihuacomputer.fish.web.index.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.session.ISessionManage;

@Controller
@RequestMapping("/logout")
public class LogoutController {
	@Autowired
	private ISessionManage sessionManage;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelMap logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		UserSession userSession = (UserSession) session.getAttribute("SESSION_USER");
		sessionManage.logout(userSession.getUserName());
		ModelMap map = new ModelMap();
		map.addAttribute(FishConstant.SUCCESS, true);
		return map;
	}

}
