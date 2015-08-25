package com.yihuacomputer.common;

/**
 * 网段
 * @author xuxigang
 * @version
 * @since  
 * @date 2010-9-3
 */
public interface INetSegment {
	public String getName();
	public String getNetworkNum();
	public String getHostNum();
	public ITypeIP getStartIP();
	public ITypeIP getEndIP();
	public int getCount();
	public int getSubNet();
	public String getType();
	public boolean contains(INetSegment b);
	public boolean contains(String name);
	public boolean isIn(INetSegment b);
	public boolean hasIP(ITypeIP ip);
	public ITypeIP example();
}
