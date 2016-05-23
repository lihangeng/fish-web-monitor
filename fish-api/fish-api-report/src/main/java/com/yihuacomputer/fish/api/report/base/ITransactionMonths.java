package com.yihuacomputer.fish.api.report.base;

public interface ITransactionMonths {

	public long getId();

	public void setId(long id);

	public String getTerminalId();

	public void setTerminalId(String terminalId);

	public long getTransDate();

	public void setTransDate(long transDate);

	public long getTransCount();

	public void setTransCount(long transCount);

}
