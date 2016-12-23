package com.yihuacomputer.fish.api.atm;

/**
 * 设备类型
 * 一般有：
 * ATM 取款机
 * CDS 存取款循环机
 * ASM 查询机等
 * @author xuxiang
 *
 */
public interface IAtmCatalog {
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
	public String getNo();

	/**
	 * @param no
	 */
	public void setNo(String no);

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
	public String getNote();

	/**
	 * @param note
	 */
	public void setNote(String note);
}
