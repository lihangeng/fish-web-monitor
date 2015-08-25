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
	public long getId();

	public void setId(long id);

	public String getNo();

	public void setNo(String no);

	public String getName();

	public void setName(String name);

	public String getNote();

	public void setNote(String note);
}
