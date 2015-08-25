package com.yihuacomputer.fish.api.monitor.business;

public interface ITransType{

	public long getId();

	public String getTransCode();

	public void setTransCode(String transCode);

	public String getCodeDesc();

	public void setCodeDesc(String codeDesc);
	
	public int getTransSeq();

	public void setTransSeq(int transSeq);
}
