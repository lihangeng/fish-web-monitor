package com.yihuacomputer.fish.api.parameter;

public interface IAppSystem {
	void setId(long id);
	long getId();
	/**
	 * 应用系统名
	 * @param name
	 */
	void setName(String name);
	String getName();
	
	void setConfigName(String configName);
	String getConfigName();
	/**
	 * 配置文件路径
	 * @param configPath
	 */
	void setConfigPath(String configPath);
	String getConfigPath();
	/**
	 * 配置文件格式
	 * @param configForm
	 */
	void setConfigForm(FileForm configForm);
	FileForm getConfigForm();
	/**
	 * 备注
	 * @param remark
	 */
	void setRemark(String remark);
	String getRemark();
}
