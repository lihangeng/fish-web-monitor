package com.yihuacomputer.fish.api.parameter;

/**
 * @author YiHua
 *
 */
public interface IParamTemplateDetail {
	/**
	 * @return
	 */
	public IParamTemplate getTemplate();

	/**
	 * @param template
	 */
	public void setTemplate(IParamTemplate template);

	/**
	 * @return
	 */
	public String getParamValue();

	/**
	 * @param paramValue
	 */
	public void setParamValue(String paramValue);

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
	public IParamElement getParamElement();

	/**
	 * @param paramElement
	 */
	public void setParamElement(IParamElement paramElement);

}
