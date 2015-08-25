package com.yihuacomputer.common.util;
/**
 * 三元组
 * @author YiHua
 *  
 */
public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {

	private static final long serialVersionUID = -1107961965283014808L;
	public final C third;

	public ThreeTuple(A frist, B second, C third) {
		super(frist, second);
		this.third = third;
	}
}
