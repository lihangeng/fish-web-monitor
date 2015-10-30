package com.yihuacomputer.common.util;

import javax.servlet.http.HttpServletRequest;



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
}
