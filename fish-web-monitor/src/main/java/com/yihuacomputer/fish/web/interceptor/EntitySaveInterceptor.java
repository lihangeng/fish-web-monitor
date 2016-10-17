package com.yihuacomputer.fish.web.interceptor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.annotation.EntityKeyDescrible;
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

	@Before("service()")
	public void aroundControllerMethod(JoinPoint joinPoint) throws Throwable {
		Method aopMethod = getPointCutMethod(joinPoint);
		SaveMethodDescrible saveMethod = aopMethod.getAnnotation(SaveMethodDescrible.class);
		if(saveMethod !=null && saveMethod.isUpdate()==false){
			return;
		}
		if(saveMethod !=null){
			Object entity = joinPoint.getArgs()[0];
			EntityKeyDescrible entityKeyDescrible = entity.getClass().getAnnotation(EntityKeyDescrible.class);
			if(entityKeyDescrible != null){
				String[] keyNameArgs = entityKeyDescrible.keyName();
				Map<String,String> values = getValue(entity,keyNameArgs);
				IFilter filter = new Filter();
				for(Map.Entry<String,String> m:values.entrySet()){
					filter.eq(m.getKey(), m.getValue());
				}
				Object entityDB = dao.findUniqueByFilter(filter, entity.getClass());
				if(entityDB != null){
					 String[] idCode = {"id"}; 
					 Map<String,String> ID = this.getValue(entityDB,idCode);
					 Object obj = this.setId(entity,ID.get("id"));
					 dao.delete(Long.valueOf(ID.get("id")), entityDB.getClass());
					 dao.save(obj);
				}
			}
		}
		
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
	
	//根据已有实体和ID号对更改的实体赋值
	private Object setId(Object entity,String id) throws Exception, Throwable{
		Class<? extends Object> clazz = entity.getClass();
		Method method = clazz.getDeclaredMethod("setId",long.class);
		method.invoke(entity, Long.valueOf(id));
        return entity;
	}
	

}
