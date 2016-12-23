package com.yihuacomputer.fish.api.atm;

import com.yihuacomputer.fish.api.device.CashType;

/**
 * 设备型号
 * 
 * @author xuxiang
 *
 */
public interface IAtmType {
	/**
	 * @return
	 */
	public long getId();

	/**
	 * @param id
	 */
	public void setId(long id);

	/**
	 * @return
	 */
	public String getName();

	/**
	 * @param name
	 */
	public void setName(String name);

	/**
	 * @return
	 */
	public IAtmVendor getDevVendor();

	/**
	 * 设置所属的设备品牌
	 * 
	 * @param devVendor
	 */
	public void setDevVendor(IAtmVendor devVendor);

	/**
	 * 设置所属的设备类型
	 * 
	 * @return
	 */
	public IAtmCatalog getDevCatalog();

	/**
	 * @param devCatalog
	 */
	public void setDevCatalog(IAtmCatalog devCatalog);

	/**
	 * @return
	 */
	public CashType getCashtype();

	/**
	 * 设置现金类型标识
	 * 
	 * @param cashtype
	 */
	public void setCashtype(CashType cashtype);

	/**
	 * 备注
	 * 
	 * @return
	 */
	public String getRemark();

	/**
	 * @param remark
	 */
	public void setRemark(String remark);
}
