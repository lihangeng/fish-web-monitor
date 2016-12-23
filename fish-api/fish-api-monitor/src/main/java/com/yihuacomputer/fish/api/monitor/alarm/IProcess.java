package com.yihuacomputer.fish.api.monitor.alarm;

/**
 * 设备进程信息
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2011-12-26 下午03:04:29
 * @version 进程信息
 */
public interface IProcess
{
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
    public String getName();

    /**
     * @param name
     */
    public void setName(String name);

    /**
     * @return
     */
    public String getUser();

    /**
     * @param user
     */
    public void setUser(String user);

    /**
     * @return
     */
    public double getCpuRate();

    /**
     * @param cpuRate
     */
    public void setCpuRate(double cpuRate);

    /**
     * @return
     */
    public long getMemoryRate();

    /**
     * @param memoryRate
     */
    public void setMemoryRate(long memoryRate);

    /**
     * @return
     */
    public String getDescription();

    /**
     * @param description
     */
    public void setDescription(String description);
    
    /**
     * @return
     */
    public String getDate();

    /**
     * @param date
     */
    public void setDate(String date);
    
}
