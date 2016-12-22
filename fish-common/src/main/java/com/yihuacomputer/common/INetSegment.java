package com.yihuacomputer.common;

/**
 * 网段
 * @author xuxigang
 * @version
 * @since  
 * @date 2010-9-3
 */
public interface INetSegment {
	/**
	 * @return
	 */
	public String getName();
	/**
	 * @return
	 */
	public String getNetworkNum();
	/**
	 * @return
	 */
	public String getHostNum();
	/**
	 * @return
	 */
	public ITypeIP getStartIP();
	/**
	 * @return
	 */
	public ITypeIP getEndIP();
	/**
	 * @return
	 */
	public int getCount();
	/**
	 * @return
	 */
	public int getSubNet();
	/**
	 * @return
	 */
	public String getType();
	/**
	 * @param b
	 * @return
	 */
	public boolean contains(INetSegment b);
	/**
	 * @param name
	 * @return
	 */
	public boolean contains(String name);
	/**
	 * @param b
	 * @return
	 */
	public boolean isIn(INetSegment b);
	/**
	 * @param ip
	 * @return
	 */
	public boolean hasIP(ITypeIP ip);
	/**
	 * @return
	 */
	public ITypeIP example();
}
