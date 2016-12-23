package com.yihuacomputer.domain.interceptor;

/**
 *
 * @author xuxigang
 * @version
 * @since  
 * @date 2010-8-22
 */
public interface IEntityInjector {
	/**
	 * @param entity
	 */
	public void injectDependencies(Object entity);
}
