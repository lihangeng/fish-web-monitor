package com.yihuacomputer.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SaveMethodDescrible {

	/**
	 * 判断是否进行更新操作，
	 * true则更新，false则不更新也不插入
	 * @return
	 */
	boolean isUpdate() default false;
}
