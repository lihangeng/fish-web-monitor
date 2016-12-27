package com.yihuacomputer.fish.api.version;

import java.util.List;

import com.yihuacomputer.fish.api.atm.IAtmType;

/**
 * 版本分类和设备型号关系服务
 * @author xuxiang
 *
 */
public interface IVersionTypeAtmTypeRelationService {
	
	/**
	 * 
	 * @param atmTypeId
	 * @param versionTypeId
	 */
	public void link(long versionTypeId,long atmTypeId);
	/**
	 * 
	 * @param atmTypeId
	 * @param versionTypeId
	 */
	public void unlink(long versionTypeId,long atmTypeId);
	
	/**
	 * 获取版本分类适用的设备型号
	 * @param versionTypeId
	 * @return
	 */
	public List<IAtmType> findAtmTypes(long versionTypeId);
	
	/**
	 * @param versionTypeId
	 * @return
	 */
	public List<Long> findAtmTypeIds(long versionTypeId);
	
	/**
	 * 
	 * @param versionTypeId
	 * @param atmTypeIds
	 */
	public void link(long versionTypeId,List<Long> atmTypeIds);
	
	/**
	 * 根据版本ID获取机型信息
	 * @param versionId
	 * @return
	 */
	public List<Long> getAtmTypeIdsByVersionId(long versionId);
	
}
