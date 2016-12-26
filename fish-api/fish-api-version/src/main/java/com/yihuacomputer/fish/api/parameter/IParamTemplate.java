package com.yihuacomputer.fish.api.parameter;

/**
 * @author YiHua
 *
 */
public interface IParamTemplate {

	/**
	 * @return
	 */
	public String getName();

	/**
	 * @return
	 */
	public long getId();

	/**
	 * @return
	 */
	public String getRemark();

	/**
	 * @param name
	 */
	public void setName(String name);

	/**
	 * @param id
	 */
	public void setId(long id);

	/**
	 * @param remark
	 */
	public void setRemark(String remark);

	/**
	 * @param applyFlag
	 */
	public void setApplyFlag(String applyFlag);

	/**
	 * @return
	 */
	public String getApplyFlag();

}
