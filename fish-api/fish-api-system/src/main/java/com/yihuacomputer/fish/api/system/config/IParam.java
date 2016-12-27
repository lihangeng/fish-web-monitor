package com.yihuacomputer.fish.api.system.config;

/**
 * @author YiHua
 *
 */
public interface IParam {
	
	/**
	 * 设置参数key
	 * @param paramKey
	 */
	public void setParamKey(String paramKey);
	
	/**
	 * 取参数key
	 * @return
	 */
	public String getParamKey();
	
	/**
	 * 设置参数值
	 * @param paramValue
	 */
	public void setParamValue(String paramValue);
	
	/**
	 * 获取参数展示值
	 * @return
	 */
	public String getParamDisplayValue();
	
	/**
	 * 设置参数参数展示值
	 * @param paramDisplayValue
	 */
	public void setParamDisplayValue(String paramDisplayValue);
	
	/**
	 * 获取参数值
	 * @return
	 */
	public String getParamValue();
	
	/**
	 * 设置参数信息描述
	 * @param description
	 */
	public void setDescription(String description);
	
	/**
	 * 获取参数信息描述
	 * @return
	 */
	public String getDescription();
	
	/**
	 * @return
	 */
	public int getClassify();

	/**
	 * 参数类型:0-不可修改，1-可以修改
	 * @param classify
	 */
	public void setClassify(int classify);
	
	/**
	 * @param id
	 */
	public void setId(long id);
	
	/**
	 * @return
	 */
	public long getId();
	
	
	/**
     * 参数类别
     * @return
     */
	public String getParamType();

	/**
	 * @param paramType
	 */
	public void setParamType(String paramType) ;
}
