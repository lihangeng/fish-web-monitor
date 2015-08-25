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
    public long getId();
    
    public void setId(long id);
    
    public String getName();

    public void setName(String name);

    public String getUser();

    public void setUser(String user);

    public double getCpuRate();

    public void setCpuRate(double cpuRate);

    public long getMemoryRate();

    public void setMemoryRate(long memoryRate);

    public String getDescription();

    public void setDescription(String description);
    
    public String getDate();

    public void setDate(String date);
    
}
