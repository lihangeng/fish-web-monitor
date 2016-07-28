package com.yihuacomputer.fish.web.interceptor;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.yihuacomputer.common.annotation.MethodNameDescrible;

@Component
@Aspect
public class UserLogAopAspect {
	
	public UserLogAopAspect(){
		System.out.println("***********************************************************UserLogAopAspect UserLogAopAspect UserLogAopAspect");
	}
	public void pointCut(){
		
	}
	
    public void recordLog(JoinPoint point)  throws Exception{
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();
		MethodNameDescrible methodDesc = method.getAnnotation(MethodNameDescrible.class);
    	if(methodDesc!=null){

        	Class<?> target = point.getTarget().getClass();

			Object[] object= point.getArgs();
			System.out.println("aaa");
    	}
    }
}
