package com.yihuacomputer.fish.web.index.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.FishConstant;

@Controller
@RequestMapping("/logout")
public class LogoutController {
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody  ModelMap logout(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if(session != null){
		    session.invalidate();  
		}
		ModelMap map = new ModelMap();
		map.addAttribute(FishConstant.SUCCESS, true);
		return map;
	}

}
