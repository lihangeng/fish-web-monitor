package com.yihuacomputer.fish.api.atm;

/**
 * @author YiHua
 *
 */
public interface IAtmGroup {
	/**
	 * @return
	 */
	public long getId();

	/**
	 * @param id
	 */
	public void setId(long id);

	/**获取组名
	 * @return
	 */
	public String getName();

	/**设置组名
	 * @param name
	 */
	public void setName(String name);

	/**获取备注
	 * @return
	 */
	public String getNote();

	/**设置备注
	 * @param note
	 */
	public void setNote(String note);
}
