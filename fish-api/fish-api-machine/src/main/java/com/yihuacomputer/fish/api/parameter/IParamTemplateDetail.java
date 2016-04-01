package com.yihuacomputer.fish.api.parameter;

public interface IParamTemplateDetail {
	public long getTemplateId();

	public void setTemplateId(long templateId);

	public String getParamValue();

	public void setParamValue(String paramValue);

	public long getId();

	public void setId(long id);

	public IParamElement getParamElement();

	public void setParamElement(IParamElement paramElement);

	public long getVersionNo();

	public void setVersionNo(long versionNo);

}
