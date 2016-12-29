package com.yihuacomputer.fish.web.monitor.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.monitor.alarm.IProcess;
/**
 * 进程信息
 * @author YiHua
 *
 */
public class ProcessForm
{
    private long id;
    
    private String name;

    private String user;

    private double cpuRate;

    private long memoryRate;

    private String description;

    private String date;
    
    public ProcessForm() {
        
    }
    
    /**
     * @param process
     */
    public ProcessForm(IProcess process) {
        setId(process.getId());
        setName(process.getName());
        setUser(process.getUser());
        setCpuRate(process.getCpuRate());
        setMemoryRate(process.getMemoryRate());
        setDescription(process.getDescription());
        setDate(process.getDate());
    }
    
    /**
     * @param process
     */
    public void translate(IProcess process) {
       process.setCpuRate(getCpuRate());
       process.setDescription(getDescription());
       process.setMemoryRate(getMemoryRate());
       process.setName(getName());
       process.setUser(getUser());
       process.setDate(getDate());
    }

    /**
     * @param list
     * @return
     */
    public static List<ProcessForm> convert(List<IProcess> list) {
        List<ProcessForm> result = new ArrayList<ProcessForm>();
        for(IProcess item : list) {
            result.add(new ProcessForm(item));
        }
        return result;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public double getCpuRate()
    {
        return cpuRate;
    }

    public void setCpuRate(double cpuRate)
    {
        this.cpuRate = cpuRate;
    }

    public long getMemoryRate()
    {
        return memoryRate;
    }

    public void setMemoryRate(long memoryRate)
    {
        this.memoryRate = memoryRate;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }
    
}
