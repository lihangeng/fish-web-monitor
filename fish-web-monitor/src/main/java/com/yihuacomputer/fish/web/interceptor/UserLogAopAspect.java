package com.yihuacomputer.fish.web.interceptor;


import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;

@Aspect
public class UserLogAopAspect {

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(UserLogAopAspect.class);
    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void controller() {
    }

    @Pointcut("execution(* *(..))")
    public void methodPointcut() {
        logger.info("Invoked: ");
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {
        logger.info("Invoked: ");
    }

    @Before("controller() && methodPointcut() && requestMapping()")
    public void aroundControllerMethod(JoinPoint joinPoint) throws Throwable {
        logger.info("Invoked: " + niceName(joinPoint));
    }

    @AfterReturning(pointcut="controller() && methodPointcut() && requestMapping()",returning="obj")
    public void afterControllerMethod(JoinPoint joinPoint,Object obj) {
    	if(null!=obj)
    	logger.info("----------------"+obj.getClass().getName());
//        logger.info("Finished: " + niceName(joinPoint));
    }
    private String niceName(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass() + "#" + joinPoint.getSignature().getName() + "\n\targs:"
                + Arrays.toString(joinPoint.getArgs());
    }
    @Around("controller() && methodPointcut() && requestMapping()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long time = System.currentTimeMillis();
		Object retVal = pjp.proceed();
		time = System.currentTimeMillis() - time;
		logger.info(pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName() + " process time: " + time + " ms");
		return retVal;
	}
    
	@AfterThrowing(pointcut="controller() && methodPointcut() && requestMapping()",throwing="ex")
	public void doThrowing(JoinPoint jp, Throwable ex) {
		logger.error("method " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + " throw exception");
		logger.error(ex.getMessage());
	}

}
