package com.yihuacomputer.fish.web.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.Transient;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.annotation.SaveMethodDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;

@Aspect
public class EntitySaveInterceptor {
	
	@Autowired
    private IGenericDao dao;
	
	@Pointcut("@within(org.springframework.stereotype.Service)")
	public void service() {
	}
	
	@Pointcut("@annotation(com.yihuacomputer.common.annotation.SaveMethodDescrible)")
	public void save() {
	}

	@Around("service() && save()")
	public Object aroundControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		Method aopMethod = getPointCutMethod(joinPoint);
		SaveMethodDescrible saveMethod = aopMethod.getAnnotation(SaveMethodDescrible.class);
		Object obj = null;
		if(saveMethod !=null && saveMethod.isUpdate()==false){
			return obj;
		}
		if(saveMethod !=null){
			Object entity = joinPoint.getArgs()[0];
			String[] keyNameArgs = saveMethod.keyName();
			Map<String,String> values = getValue(entity,keyNameArgs);
			IFilter filter = new Filter();
			for(Map.Entry<String,String> m:values.entrySet()){
				filter.eq(m.getKey(), m.getValue());
			}
			Object entityDB = dao.findUniqueByFilter(filter, entity.getClass());
			if(entityDB != null){
				//获取数据库实体的属性集合
				 Field[] dbFields = entityDB.getClass().getDeclaredFields();
				 for(Field dbField:dbFields){
					 //属性注解
					 Annotation[] annotations= dbField.getDeclaredAnnotations();
					 //无注解不做处理
					 if(annotations.length==0){
						 continue;
					 }
					 boolean isTransient = false;
					 //临时注解不做处理
					 for(Annotation annotation:annotations){
						 if(annotation.annotationType().equals(Transient.class)||annotation.annotationType().equals(Id.class)){
							 isTransient = true;
							 break;
						 }
					 }
					 if(isTransient){
						 continue;
					 }
					 //数据库实体属性可访问
					 dbField.setAccessible(true);
					 
					 //获取更新数据的相应属性 
					 Field field = entity.getClass().getDeclaredField(dbField.getName());
					 field.setAccessible(true);
					 //将更新实体属性的数据赋予数据库实体属性
					 dbField.set(entityDB, field.get(entity));;
				 }
				 dao.update(entityDB);
			}
				return obj;
		}
		return joinPoint.proceed();
	}

	private Method getPointCutMethod(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		// 获取切入方法
		
		return methodSignature.getMethod();
	}
	
	//根据实体和字段获取字段值
	private Map<String,String> getValue(Object entity,String[] name) throws Exception, IllegalAccessException{
		String value = null;
		Map<String,String> map = new HashMap<String,String>();
		Class<? extends Object> entityClass = (Class<? extends Object>)entity.getClass();
		Field[] fs =entityClass.getDeclaredFields();
		for(int i=0;i<fs.length;i++){
			Field f = fs[i];
			for(int j=0;j<name.length;j++){
				if(f.getName().equals(name[j])){
					f.setAccessible(true);
					value = String.valueOf(f.get(entity));
					map.put(name[j], value);
				}
			}
			
		}
		return map;
	}
	
}
