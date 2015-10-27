package com.yihuacomputer.fish.api.atm;
/**
 *设备型号关联设备模块
 * @author YH
 *
 */
public interface IAtmTypeAtmModuleRelation {
	long getId();
	/**
	 * 获得设备模块Id
	 * @return
	 */
	long getAtmModuleId();
	/**
	 * 获得设备类型Id
	 * @return
	 */
	long getAtmTypeId();
	/**
	 * 设置设备模块Id
	 * @param atmModuleId
	 */
	void setAtmModuleId(long atmModuleId);
	/**
	 * 设置设备类型Id
	 * @param atmTypeId
	 */
	void setAtmTypeId(long atmTypeId);

}
