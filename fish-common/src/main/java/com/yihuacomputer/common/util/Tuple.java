package com.yihuacomputer.common.util;

/**
 * 元组工厂 
 *@author YiHua
 *
 */
public class Tuple {
	//方法参数推断，使得创建新的元组对象不必再显示声明泛型参数类型
	public static <A,B> TwoTuple<A,B> tuple(A a,B b){
		return new TwoTuple<A,B>(a,b);
	}
	public static <A,B,C> ThreeTuple<A,B,C> tuple(A a,B b,C c){
		return new ThreeTuple<A,B,C>(a,b,c);
	} 
}
