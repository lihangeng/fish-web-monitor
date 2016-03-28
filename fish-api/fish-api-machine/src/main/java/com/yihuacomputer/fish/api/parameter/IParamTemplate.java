package com.yihuacomputer.fish.api.parameter;

public interface IParamTemplate {
	
	public String getName();

	public long getId();
	
	public String getRemark();
	
	public void setName(String name);

	public void setId(long id);
	
	public void setRemark(String remark);
	
	public String getParamKey();
	
	public void setParamKey(String paramKey);
	
	public String getParamValue();
	
	public void setParamValue(String paramValue);
	
	public long getVersionNo();
	
	public void setVersionNo(long versionNo);

}
