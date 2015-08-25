package com.yihuacomputer.fish.api.atm;

/**
 * 设备模块(xfs中deviceclass)
 * 系统内置数据
 * @author xuxiang
 *
 */
public interface IAtmModule {

	public void setId(long id);

	public long getId();

	/**
	 * 设置模块英文名如：CDM
	 * @param no
	 */
	public void setNo(String no);

	public String getNo();

	/**
	 * 设置模块名称
	 * @param name
	 */
	public void setName(String name);

	public String getName();

	public void setNote(String note);

	public String getNote();

	public boolean isCase();

	public void setCase(boolean isCase);
}
