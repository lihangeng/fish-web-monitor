package com.yihuacomputer.fish.api.version;

/**
 * 版本分类和设备型号关系关系表 版本分类实用的设备型号
 * 
 * @author xuxiang
 * @since 2.0.0.0
 *
 */
public interface IVersionTypeAtmTypeRelation {

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

	void setAtmTypeId(long atmTypeId);

	void setVersionTypeId(long versionTypeId);

}
