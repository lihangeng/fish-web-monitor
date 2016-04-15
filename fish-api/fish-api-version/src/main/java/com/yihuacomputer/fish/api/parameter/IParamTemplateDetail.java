package com.yihuacomputer.fish.api.parameter;

public interface IParamTemplateDetail {
	public IParamTemplate getTemplate();

	public void setTemplate(IParamTemplate template);

	public String getParamValue();

	public void setParamValue(String paramValue);

	public long getId();

	public void setId(long id);

	public IParamElement getParamElement();

	public void setParamElement(IParamElement paramElement);

}
