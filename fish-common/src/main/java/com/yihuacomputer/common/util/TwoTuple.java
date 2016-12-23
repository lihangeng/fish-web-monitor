package com.yihuacomputer.common.util;

import java.io.Serializable;
/**
 * 二元组
 * 
 * @author YiHua
 * @param <A>
 * @param <B>
 */
public class TwoTuple<A,B> implements Serializable{
	
	private static final long serialVersionUID = -1188037780962920202L;
	/**
	 * 第一
	 */
	public final A frist;
	/**
	 * 第二
	 */
	public final B second;
	/**
	 * @param frist
	 * @param second
	 */
	public TwoTuple(A frist,B second)
	{
		this.frist=frist;
		this.second=second;
	}

}
