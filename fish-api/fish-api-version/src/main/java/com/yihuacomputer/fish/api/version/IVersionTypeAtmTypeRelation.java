package com.yihuacomputer.fish.api.version;

/**
 * 版本分类和设备型号关系关系表 版本分类实用的设备型号
 * 
 * @author xuxiang
 * @since 2.0.0.0
 *
 */
public interface IVersionTypeAtmTypeRelation {

	/**
	 * @return
	 */
	long getId();

	/**
	 * 获得设备型号ID
	 * @return
	 */
	long getAtmTypeId();
	
	/**
	 * 获得版本分类ID
	 * @return
	 */
	long getVersionTypeId();

	/**
	 * @param atmTypeId
	 */
	void setAtmTypeId(long atmTypeId);

	/**
	 * @param versionTypeId
	 */
	void setVersionTypeId(long versionTypeId);

}
