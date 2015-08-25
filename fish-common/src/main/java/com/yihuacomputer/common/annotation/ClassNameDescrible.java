package com.yihuacomputer.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassNameDescrible {
	/**
	 * 获取类的描述信息
	 * @return
	 */
	String describle() default "";
	
}
