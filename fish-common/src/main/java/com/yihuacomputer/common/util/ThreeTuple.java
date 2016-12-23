package com.yihuacomputer.common.util;
/**三元组
 * @author YiHua
 *
 * @param <A>
 * @param <B>
 * @param <C>
 */
public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {

	private static final long serialVersionUID = -1107961965283014808L;
	/**
	 * 第三个元素
	 */
	public final C third;

	/**
	 * @param frist
	 * @param second
	 * @param third
	 */
	public ThreeTuple(A frist, B second, C third) {
		super(frist, second);
		this.third = third;
	}
}
