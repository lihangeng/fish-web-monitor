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
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.mq.IMqProducer;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.monitor.entity.login.LoginMessage;

@Controller
@RequestMapping("/logout")
public class LogoutController {
	@Autowired(required = false)
	private IMqProducer mqProducer;
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody  ModelMap logout(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		UserSession userSession = (UserSession) session.getAttribute("SESSION_USER");
		if(session != null){
		    session.invalidate();  
		}
		ModelMap map = new ModelMap();
		map.addAttribute(FishConstant.SUCCESS, true);
		
		LoginMessage loginMessage = new LoginMessage("LOGIN_OUT",userSession.getUserName(),session.getId());
		if(mqProducer!=null){
			mqProducer.put(JsonUtils.toJson(loginMessage));
//			Map<String,HttpSession> sessionMap = FishConstant.APPLICATION_MAP.get(userSession.getUserName());
			FishConstant.APPLICATION_MAP.remove(userSession.getUserName());
		}else{
			FishConstant.APPLICATION_MAP.remove(userSession.getUserName());
//			session.getServletContext().removeAttribute(userSession.getUserName());
		}
		return map;
	}

}
