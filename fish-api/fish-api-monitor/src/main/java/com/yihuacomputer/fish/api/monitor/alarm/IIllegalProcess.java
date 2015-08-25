package com.yihuacomputer.fish.api.monitor.alarm;

/**
 * 非法进程报警信息
 * 
 * @author YiHua
 * 
 */
public interface IIllegalProcess {
    
    public long getId();
    
    public void setId(long id);

	public void setTerminalId(String terminalId);
	
	public String getTerminalId();

	public String getName();

	public String getUser();

	public double getCpuRate();

	public long getMemoryRate();

	public String getDate();

	public void setName(String name);

	public void setUser(String user);

	public void setCpuRate(double cpuRate);

	public void setMemoryRate(long memoryRate);

	public void setDate(String date);
}
