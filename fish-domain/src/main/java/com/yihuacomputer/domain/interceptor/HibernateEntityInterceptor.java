package com.yihuacomputer.domain.interceptor;

import java.io.Serializable;
import java.util.Map;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author xuxigang
 *
 */
@SuppressWarnings("serial")
public class HibernateEntityInterceptor extends EmptyInterceptor implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
    
    @Override
	public boolean onLoad(Object entity, Serializable id, Object[] state,String[] propertyNames, Type[] types) {
        Map<String,IEntityInjector> injectors = this.applicationContext.getBeansOfType(IEntityInjector.class);
        for(String key :injectors.keySet()){
        	injectors.get(key).injectDependencies(entity);
    	}
        return super.onLoad(entity, id, state, propertyNames, types);
    }
}
