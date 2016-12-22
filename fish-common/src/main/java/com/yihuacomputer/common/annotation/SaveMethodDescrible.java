package com.yihuacomputer.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 根据主键判断是否进行保存操作
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SaveMethodDescrible {

	/**
	 * 判断是否进行更新操作，
	 * true则更新，false则不更新也不插入
	 * @return
	 */
	boolean isUpdate() default false;
	
	/**
	 * 实体名称（判重主键）
	 */
	String[] keyName() default "" ;
}
