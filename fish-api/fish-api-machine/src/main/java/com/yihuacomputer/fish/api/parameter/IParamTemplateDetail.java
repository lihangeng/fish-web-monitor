package com.yihuacomputer.fish.api.parameter;

public interface IParamTemplateDetail {
	public long getTemplateId();

	public void setTemplateId(long templateId);

	public long getParamBelongs();

	public void setParamBelongs(long paramBelongs);

	public void setParamName(String paramName);

	public String getParamName();

	public String getParamValue();

	public void setParamValue(String paramValue);

	public long getVersionNo();

	public void setVersionNo(long versionNo);

	public long getId();

	public void setId(long id);
}
