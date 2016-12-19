package com.yihuacomputer.domain.spring;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceInterceptor {

	private final Logger logger = LoggerFactory.getLogger(DataSourceInterceptor.class);
	public void intercept(JoinPoint point) {
		Class<?> target = point.getTarget().getClass();
		MethodSignature signature = (MethodSignature) point.getSignature();
		// 默认使用目标类型的注解，如果没有则使用其实现接口的注解
		resolveDataSource(target, signature.getMethod());
	}

	private void resolveDataSource(Class<?> clazz, Method method) {
		try {
			Class<?>[] types = method.getParameterTypes();
			
			// 方法注解可以覆盖类型注解
			Method m = clazz.getMethod(method.getName(), types);
			// 默认使用类型注解
			if (clazz.isAnnotationPresent(DataSource.class)||(m != null && m.isAnnotationPresent(DataSource.class))) {
				DataSource source = clazz.getAnnotation(DataSource.class);
				if(source!=null){
					DatabaseContextHolder.setDataSource(source.value().getText());
				}
				if (m != null && m.isAnnotationPresent(DataSource.class)) {
					source = m.getAnnotation(DataSource.class);
					DatabaseContextHolder.setDataSource(source.value().getText());
				}
			}
			else{
				DatabaseContextHolder.setDataSource(DataSources.Monitor.getText());
			}
			
		} catch (Exception e) {
			logger.error(String.format("The class :[%s]:exception is :[%s]",clazz.toString(), e));
		}
	}
}