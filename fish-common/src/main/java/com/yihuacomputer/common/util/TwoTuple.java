package com.yihuacomputer.common.util;

import java.io.Serializable;
/**
 * 二元组
 * 
 * @author YiHua
 *  
 */
public class TwoTuple<A,B> implements Serializable{
	
	private static final long serialVersionUID = -1188037780962920202L;
	public final A frist;
	public final B second;
	public TwoTuple(A frist,B second)
	{
		this.frist=frist;
		this.second=second;
	}

}
