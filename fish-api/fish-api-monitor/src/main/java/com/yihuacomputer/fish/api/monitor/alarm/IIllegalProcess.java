package com.yihuacomputer.fish.api.monitor.alarm;

/**
 * 非法进程报警信息
 * 
 * @author YiHua
 * 
 */
public interface IIllegalProcess {
    
    /**
     * @return
     */
    public long getId();
    
    /**
     * @param id
     */
    public void setId(long id);

	/**
	 * @param terminalId
	 */
	public void setTerminalId(String terminalId);
	
	/**
	 * @return
	 */
	public String getTerminalId();

	/**
	 * @return
	 */
	public String getName();

	/**
	 * @return
	 */
	public String getUser();

	/**
	 * @return
	 */
	public double getCpuRate();

	/**
	 * @return
	 */
	public long getMemoryRate();

	/**
	 * @return
	 */
	public String getDate();

	/**
	 * @param name
	 */
	public void setName(String name);

	/**
	 * @param user
	 */
	public void setUser(String user);

	/**
	 * @param cpuRate
	 */
	public void setCpuRate(double cpuRate);

	/**
	 * @param memoryRate
	 */
	public void setMemoryRate(long memoryRate);

	/**
	 * @param date
	 */
	public void setDate(String date);
}
