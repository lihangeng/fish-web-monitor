package com.yihuacomputer.fish.web.util;

import javax.servlet.http.HttpServletRequest;

import com.yihuacomputer.fish.api.person.UserSession;



/**
 *
 * @author xuxigang
 * @version
 * @since
 * @date 2011-4-26
 */
public class FishWebUtils {

	public static final String USER = "SESSION_USER";

	public static boolean isIgnoreRequestName(String name) {
		return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name);
	}
	/**
	 * 获得web上下文tmp的实际目录
	 * @param request
	 * @return
	 */
	public static String getRealPathByTmp(HttpServletRequest request){
	    return request.getSession().getServletContext().getRealPath("/tmp");
	}

	public static UserSession getUserSession(HttpServletRequest request){
		return (UserSession)request.getSession().getAttribute(FishWebUtils.USER);
	}

	public static long getSessionUserId(HttpServletRequest request){
		return FishWebUtils.getUserSession(request).getUserId();
	}
}
