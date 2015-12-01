package com.yihuacomputer.fish.web.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yihuacomputer.fish.web.util.FishWebUtils;

/**
 * 用于检测用户是否登陆，如果未登录或超时，则重定向到指的登录页面
 * 
 * @author xuxigang
 * 
 */
public class TimeoutInterceptor extends HandlerInterceptorAdapter {
	// 用于保存忽略的URL列表
	private List<String> ignoreUrls = new ArrayList<String>();

	/**
	 * 返回false表示用户未登陆或者超时
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = httpServletRequest.getSession(false);
		// 用户超时或没有登陆时跳转到登陆页面
		String uri = httpServletRequest.getRequestURI();
		if (isIgnoreUrl(uri)) {//在忽略列表中
		    return true;
		}
		
		if (session != null && session.getAttribute(FishWebUtils.USER) != null) {//存在会话
		    return true;
		}
		
		if (httpServletRequest.getHeader("x-requested-with") != null &&
				httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
			// ajax超时处理
			httpServletResponse.addHeader("sessionStatus", "timeout");
		} else {// http超时处理
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
		}
        return false;
	}

	public void init(){
		ignoreUrls.add("/api/data/");
		ignoreUrls.add("/api/msg/");
		ignoreUrls.add(".jpg");
		ignoreUrls.add(".gif");
		ignoreUrls.add(".png");
		ignoreUrls.add(".ico");
		ignoreUrls.add(".GIF");
		ignoreUrls.add("/api/login");
		ignoreUrls.add("/api/system/register");
		ignoreUrls.add("/api/register/isRegister");
		ignoreUrls.add("login.jsp");
	}

	private boolean isIgnoreUrl(String uri) {
		for (String url : ignoreUrls) {
			if(uri.contains(url)){
				return true;
			}
		}
		return false;	
	}
}
