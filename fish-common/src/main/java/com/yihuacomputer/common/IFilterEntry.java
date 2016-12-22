package com.yihuacomputer.common;


/**
 * 查询条目接口
 *
 */
public interface IFilterEntry {

	/**获取key
	 * @return
	 */
	public String getKey();
	
	/**获取value
	 * @return
	 */
	public Object getValue();
	
	/**获取操作符
	 * @return
	 */
	public Operator getOperator();
}
