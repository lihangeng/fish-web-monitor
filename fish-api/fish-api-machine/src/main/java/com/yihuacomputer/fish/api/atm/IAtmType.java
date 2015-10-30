package com.yihuacomputer.fish.api.atm;

import com.yihuacomputer.fish.api.device.CashType;

/**
 * 设备型号
 * 
 * @author xuxiang
 *
 */
public interface IAtmType {
	public long getId();

	public void setId(long id);

	public String getName();

	public void setName(String name);

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

	public void setDevCatalog(IAtmCatalog devCatalog);

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

	public void setRemark(String remark);
}
