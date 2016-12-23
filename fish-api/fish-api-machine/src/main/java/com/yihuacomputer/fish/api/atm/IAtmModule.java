package com.yihuacomputer.fish.api.atm;

/**
 * 设备模块(xfs中deviceclass)
 * 系统内置数据
 * @author xuxiang
 *
 */
public interface IAtmModule {

	/**
	 * @param id
	 */
	public void setId(long id);

	/**
	 * @return
	 */
	public long getId();

	/**
	 * 设置模块英文名如：CDM
	 * @param no
	 */
	public void setNo(String no);

	/**
	 * @return
	 */
	public String getNo();

	/**
	 * 设置模块名称
	 * @param name
	 */
	public void setName(String name);

	/**
	 * @return
	 */
	public String getName();

	/**
	 * @param note
	 */
	public void setNote(String note);

	/**
	 * @return
	 */
	public String getNote();

	/**
	 * @return
	 */
	public boolean isCase();

	/**
	 * @param isCase
	 */
	public void setCase(boolean isCase);
}
