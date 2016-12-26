package com.yihuacomputer.fish.api.parameter;

/**
 * @author YiHua
 *
 */
public interface IAppSystem {
	
	/**
	 * @param id
	 */
	void setId(long id);
	
	/**
	 * @return
	 */
	long getId();
	
	/**
	 * 应用系统名
	 * @param name
	 */
	void setName(String name);
	
	/**
	 * @return
	 */
	String getName();
	
	/**
	 * @param configName
	 */
	void setConfigName(String configName);
	
	/**
	 * @return
	 */
	String getConfigName();
	
	/**
	 * 配置文件路径
	 * @param configPath
	 */
	void setConfigPath(String configPath);
	
	/**
	 * @return
	 */
	String getConfigPath();
	
	/**
	 * 配置文件格式
	 * @param configForm
	 */
	void setConfigForm(FileFormat configForm);
	
	/**
	 * @return
	 */
	FileFormat getConfigForm();
	
	/**
	 * 备注
	 * @param remark
	 */
	void setRemark(String remark);
	
	/**
	 * @return
	 */
	String getRemark();
}
