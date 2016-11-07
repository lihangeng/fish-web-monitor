package com.yihuacomputer.fish.web.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * @Title: UserLogFilter.java
 * @Package net.bwda.fish.web.listener
 * @Description: TODO(用于记录用户操作日志)
 * @author shixiaolong
 * @date 2012-5-4
 * @version V1.0
 */
public class UserLogInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private MessageSource messageSource;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		/*
		 * IUser user = (IUser) request.getSession().getAttribute(
		 * FishWebUtils.USER); if (user != null) { IUserLogService logService =
		 * FishWebUtils.getBean( request.getSession(), IUserLogService.class);
		 * Properties log = getProperites(request); String requestURI =
		 * request.getRequestURI(); String url =
		 * requestURI.substring(requestURI.indexOf("/api"),
		 * requestURI.length()); String result = ""; String[] list =
		 * url.split("/"); for (String s : list) { if (s.length() > 20) { s =
		 * "NUM"; } try { Integer.parseInt(s); s = "NUM"; } catch (Exception e)
		 * {
		 *
		 * } finally { if (s.length() != 0) { result = result + "/" + s; } } }
		 *
		 * String str = log.getProperty(result + "[" + request.getMethod() +
		 * "]"); if (str != null && str.length() > 0 && !"".equals(str)) {
		 * String resultName = new String(str.getBytes("ISO-8859-1"), "utf-8");
		 * // System.out.println(resultName); IUserLog operLog =
		 * logService.make(); operLog.setOperTime(new Date());
		 * operLog.setOperContent(resultName);
		 * operLog.setOperCode(user.getCode());
		 * operLog.setOperName(user.getName()); logService.add(operLog); } }
		 */
		return true;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//		String operResult = "";
//		if (ex == null) {
//			operResult =messageSource.getMessage("user.operate.success",null,FishCfg.locale);// "成功";
//		} else {
//			operResult = messageSource.getMessage("user.operate.fail",null,FishCfg.locale);//"失败";
//		}
//		HttpSession session = null;
//		try {
//			session = request.getSession();
//		} catch (Exception e) {
//			return;
//		}
//		UserSession user = (UserSession) session.getAttribute(FishWebUtils.USER);
//		if (user != null) {
//			HandlerMethod handlers = (HandlerMethod)handler;
//			ClassNameDescrible classDesc = handlers.getBean().getClass().getAnnotation(ClassNameDescrible.class);
//			MethodNameDescrible methodDesc = handlers.getMethod().getAnnotation(MethodNameDescrible.class);
//			if(classDesc==null||methodDesc==null){
//				return;
//			}
//			StringBuffer operatorAction = new StringBuffer();
//			operatorAction.append(messageSource.getMessage(classDesc.describle(),null,FishCfg.locale)).append("->").append(messageSource.getMessage(methodDesc.describle(),null,FishCfg.locale));
//			if(methodDesc.hasArgs()){
//				operatorAction.append("->").append(request.getParameter(methodDesc.argsContext()));
//			}
//			if(methodDesc.urlArgs()){
//				String uris[] =  request.getRequestURI().split("\\/");
//				operatorAction.append("->").append(uris[uris.length-1]);
//			}
////			if(methodDesc.hasReqBodyParam()){
////				String className = methodDesc.reqBodyClass();
////				Class classz = Class.forName(className);
////				handlers.g
////				MethodParameter [] methodParams = handlers.getMethodParameters();
////				for(MethodParameter methodParam:methodParams){
////					if(methodParam.getParameterType() instanceof classz){
////						methodParam.get
////					}
////				}
////			}
//			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
//			IUserLogService logService = ctx.getBean(IUserLogService.class);
//			String str = operatorAction.toString();
//			if (str != null && str.length() > 0 && !"".equals(str)) {
////				String resultName = new String(str.getBytes("ISO-8859-1"), "utf-8");
//				IUserLog operLog = logService.make();
//				operLog.setOperTime(new Date());
//				operLog.setOperContent(str);
//				operLog.setOperCode(user.getUserCode());
//				operLog.setOperName(user.getUserName());
//				operLog.setOperResult(operResult);
//				logService.add(operLog);
//			}
//		}
//
//	}

}
