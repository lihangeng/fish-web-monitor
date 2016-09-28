package com.yihuacomputer.fish.web.interceptor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

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
import com.yihuacomputer.fish.api.person.IUserLog;
import com.yihuacomputer.fish.api.person.IUserLogService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.web.util.FishWebUtils;

@Aspect
public class UserLogAopAspect {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private IUserLogService userLogService;

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(UserLogAopAspect.class);

	@Pointcut("@within(org.springframework.stereotype.Controller)")
	public void controller() {
	}

	@Pointcut("execution(* *(..))")
	public void methodPointcut() {
	}

	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void requestMapping() {
	}

	@Before("controller() && methodPointcut() && requestMapping()")
	public void aroundControllerMethod(JoinPoint joinPoint) throws Throwable {
	}

	private Method getPointCutMethod(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		// 获取切入方法
		return methodSignature.getMethod();
	}

	/**
	 * 浏览器IP/服务器IP
	 * 
	 * @param request
	 * @param userlog
	 * @return
	 */
	private IUserLog setIpInfo(HttpServletRequest request, IUserLog userlog) {
		userlog.setClientIP(request.getRemoteAddr());
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
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			operatorAction.append("->").append(reqBodyKey);
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

	@SuppressWarnings("finally")
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
//			if (userSession != null) {
				String str = getOperatorContext(methodDesc, classDesc, methodArgs, request);
				if (str != null && str.length() > 0 && !"".equals(str)) {
					IUserLog operLog = userLogService.make();
					operLog.setOperTime(new Date());
					operLog.setOperContent(str);
					obj = pjp.proceed();
					UserSession userSession = (UserSession) session.getAttribute(FishWebUtils.USER);
					operLog.setOperCode(userSession.getUserCode());
					operLog.setOperName(userSession.getUserName());
					time = System.currentTimeMillis() - time;
					operLog.setTimes(time);
					operLog = setOperatorResult(obj, operLog);
					operLog = setIpInfo(request, operLog);
					userLogService.add(operLog);
				}
//			}
		} catch (Exception e) {
			logger.error(e.getMessage());

		} finally {
			logger.debug(pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName() + " process time: " + time + " ms");
			if(obj==null){
				obj = pjp.proceed();
			}
			return obj;
		}

		// return obj;
	}

	@AfterThrowing(pointcut = "controller() && methodPointcut() && requestMapping()", throwing = "ex")
	public void doThrowing(JoinPoint jp, Throwable ex) {
		logger.error("method " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + " throw exception");
		logger.error(ex.getMessage());
	}

}
