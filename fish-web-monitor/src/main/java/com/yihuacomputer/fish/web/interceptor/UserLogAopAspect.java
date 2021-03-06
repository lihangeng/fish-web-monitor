package com.yihuacomputer.fish.web.interceptor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.person.IUserLog;
import com.yihuacomputer.fish.api.person.IUserLogService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.web.util.FishWebUtils;

/**
 * @author YiHua
 *
 */
@Aspect
public class UserLogAopAspect {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private IUserLogService userLogService;

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(UserLogAopAspect.class);

	/**
	 * 切点
	 */
	@Pointcut("@within(com.yihuacomputer.common.annotation.ClassNameDescrible)")
	public void controller() {
	}

	/**
	 * 切点
	 */
	@Pointcut("execution(* *(..))")
	public void methodPointcut() {
	}

	/**
	 * 切点
	 */
	@Pointcut("@annotation(com.yihuacomputer.common.annotation.MethodNameDescrible)")
	public void requestMapping() {
	}

	/**
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before("controller() && methodPointcut() && requestMapping()")
	public void aroundControllerMethod(JoinPoint joinPoint) throws Throwable {
	}

	private Method getPointCutMethod(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		// 获取切入方法
		return methodSignature.getMethod();
	}

	
	/**
	 * 获取客户端的IP
	 * @param request
	 * @return
	 */
	private String getRemoteHost(HttpServletRequest request){
		 	String ip = request.getHeader("X-Forwarded-For");  
	        if (logger.isDebugEnabled()) {  
	            logger.debug("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);  
	        }  
	 
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	                ip = request.getHeader("Proxy-Client-IP");  
	                if (logger.isDebugEnabled()) {  
	                    logger.debug("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);  
	                }  
	            }  
	            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	                ip = request.getHeader("WL-Proxy-Client-IP");  
	                if (logger.isDebugEnabled()) {  
	                    logger.debug("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);  
	                }  
	            }  
	            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	                ip = request.getHeader("HTTP_CLIENT_IP");  
	                if (logger.isDebugEnabled()) {  
	                    logger.debug("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);  
	                }  
	            }  
	            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	                ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	                if (logger.isDebugEnabled()) {  
	                    logger.debug("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);  
	                }  
	            }  
	            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	                ip = request.getRemoteAddr();  
	                if (logger.isDebugEnabled()) {  
	                    logger.debug("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);  
	                }  
	            }  
	        } else if (ip.length() > 15) {  
	            String[] ips = ip.split(",");  
	            for (int index = 0; index < ips.length; index++) {  
	                String strIp = (String) ips[index];  
	                if (!("unknown".equalsIgnoreCase(strIp))) {  
	                    ip = strIp;  
	                    break;  
	                }  
	            }  
	        }  
	        return ip;  
	}
	/**
	 * 浏览器IP/服务器IP
	 * 
	 * @param request
	 * @param userlog
	 * @return
	 */
	private IUserLog setIpInfo(HttpServletRequest request, IUserLog userlog) {
		userlog.setClientIP(getRemoteHost(request));
		userlog.setServerIp(request.getLocalAddr());
		return userlog;
	}

	private IUserLog setOperatorResult(Object executResult, IUserLog userLog) {
		// 如果没有返回结果默认执行成果
		boolean operResult = true;
		// 提示语默认为成功提示语
		String operResultStr = getI18N("user.operate.success");
		// 获取执行结果
		if (null != executResult) {
			if (executResult instanceof ModelMap) {
				ModelMap result = (ModelMap) executResult;
				operResult = (null == result.get(FishConstant.SUCCESS)) ? operResult : (Boolean) result.get(FishConstant.SUCCESS);
			}else if(executResult instanceof String){
				String obj = JsonUtils.jsonValue(executResult.toString(), FishConstant.SUCCESS);
				operResult = Boolean.parseBoolean(obj);
			}
			if (!operResult) {
				operResultStr = getI18N("user.operate.fail");// "失败";
			}
		}
		userLog.setOperResult(operResultStr);
		return userLog;
	}

	private String getOperatorContext(MethodNameDescrible methodDesc, ClassNameDescrible classDesc, Object[] methodArgs, HttpServletRequest request) {
		if (classDesc == null) {
			logger.warn("ClassNameDescrible is Null");
			return "";
		}
		StringBuilder operatorAction = new StringBuilder();
		operatorAction.append(getI18N(classDesc.describle())).append("->").append(getI18N(methodDesc.describle()));
		// url后缀中有?参数
		if (methodDesc.hasArgs()) {
			operatorAction.append("->").append(request.getParameter(methodDesc.argsContext()));
		}
		// 直接URL中截取参数
		if (methodDesc.urlArgs()) {
			String uris[] = request.getRequestURI().split("\\/");
			operatorAction.append("->").append(uris[uris.length - 1]);
		}
		// 要记录的关键字在请求体中
		if (methodDesc.hasReqBodyParam()) {
			@SuppressWarnings("unchecked")
			Class<? extends Object> requestBodyclazz = methodDesc.reqBodyClass();
			Field field = null;
			String reqBodyKey = "";
			try {
				field = requestBodyclazz.getDeclaredField(methodDesc.bodyProperties());
				field.setAccessible(true);
				for (Object arg : methodArgs) {
					if (arg.getClass().equals(requestBodyclazz)) {
						reqBodyKey = String.valueOf(field.get(arg));
					}
				}
			} catch (SecurityException e) {
				logger.error(String.format("SecurityException is [%s]", e));
			} catch (NoSuchFieldException e) {
				logger.error(String.format("NoSuchFieldException is [%s]", e));
			} catch (IllegalArgumentException e) {
				logger.error(String.format("IllegalArgumentException is [%s]", e));
			} catch (IllegalAccessException e) {
				logger.error(String.format("IllegalAccessException is [%s]", e));
			}
			operatorAction.append("->").append(reqBodyKey);
		}
		//要记录的关键字从前台传过来
		if(methodDesc.hasFaceParam()){
			Class<? extends Object> requestBodyclazz = methodArgs[1].getClass();
			Field[] field = null;
			field = requestBodyclazz.getDeclaredFields();
			field[1].setAccessible(true);
			Object obj;
			try {
				obj = field[1].get(methodArgs[1]);
				HashMap<?, ?> map = (HashMap<?, ?>)obj;
				String[] value = (String[])map.get(methodDesc.faceParam());
				operatorAction.append("->").append(value[0]);
			} catch (IllegalArgumentException e) {
				logger.error(String.format("IllegalArgumentException is [%s]", e));
			} catch (IllegalAccessException e) {
				logger.error(String.format("IllegalAccessException is [%s]", e));
			}
		}
		return operatorAction.toString();
	}

	private String getI18N(String code) {
		if (null == code) {
			return "";
		}
		return messageSource.getMessage(code, null, FishCfg.locale);
	}

	/**
	 * 获取request
	 * 
	 * @return
	 */
	private HttpServletRequest getRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		return sra.getRequest();
	}

	/**
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("controller() && methodPointcut() && requestMapping()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long time = System.currentTimeMillis();
		Method aopMethod = getPointCutMethod(pjp);
		MethodNameDescrible methodDesc = aopMethod.getAnnotation(MethodNameDescrible.class);
		// 如果没有方法描述，则不作日志记录
		if (methodDesc == null) {
			return pjp.proceed();
		}
		HttpServletRequest request = getRequest();
		HttpSession session = null;
		Object obj = null;
		try {
			session = request.getSession();

			// 获取切入类
			Class<? extends Object> clazz = pjp.getTarget().getClass();
			// 获取切入方法参数
			Object[] methodArgs = pjp.getArgs();
			ClassNameDescrible classDesc = clazz.getAnnotation(ClassNameDescrible.class);
			String str = getOperatorContext(methodDesc, classDesc, methodArgs, request);
			if (str != null && str.length() > 0 && !"".equals(str)) {
				IUserLog operLog = userLogService.make();
				operLog.setOperTime(new Date());
				obj = pjp.proceed();
				if(methodDesc.hasLogKey()){
					if(obj instanceof ModelMap){
						ModelMap data = (ModelMap)obj;
						str=str+"->"+data.get(FishConstant.LOG_KEY);
					}else if(obj instanceof String){
						str = str +"->"+ JsonUtils.jsonValue((String)obj, FishConstant.LOG_KEY);
					}
				}
				if(str.indexOf("null")==-1){
					operLog.setOperContent(str);
					UserSession userSession = (UserSession) session.getAttribute(FishWebUtils.USER);
					operLog.setOperCode(userSession.getUserCode());
					operLog.setOperName(userSession.getUserName());
					time = System.currentTimeMillis() - time;
					operLog.setTimes(time);
					operLog = setOperatorResult(obj, operLog);
					operLog = setIpInfo(request, operLog);
					userLogService.add(operLog);
				}
			}
		} catch (Exception e) {
			logger.error(String.format("[%s]", e));

		} finally {
			logger.debug(pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName() + " process time: " + time + " ms");
			if(obj==null){
				obj = pjp.proceed();
			}
		}
		 return obj;
	}

	/**
	 * 记录拦截异常
	 * @param jp
	 * @param ex
	 */
	@AfterThrowing(pointcut = "controller() && methodPointcut() && requestMapping()", throwing = "ex")
	public void doThrowing(JoinPoint jp, Throwable ex) {
		logger.error("method " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + " throw exception");
		logger.error(String.format("[%s]", ex));
	}

}
