package com.yihuacomputer.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodNameDescrible{
	/**
	 * 获取方法的描述信息
	 * @return
	 */
	String describle() default "";
	/**
	 * 当前传参数是否为数字(主键)
	 * @return
	 */
	boolean hasArgs() default true;
	/**
	 * 参数主标识key;能够标识当前操作对象的 key
	 * 列如:/api/person?name=abc&orgId=1(此处主标识key应该为name)
	 * @return
	 */
	String argsContext() default "";
}
