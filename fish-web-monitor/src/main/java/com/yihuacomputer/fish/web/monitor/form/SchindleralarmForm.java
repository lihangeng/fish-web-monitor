package com.yihuacomputer.fish.web.monitor.form;

import java.util.ArrayList;
import java.util.List;
import com.yihuacomputer.fish.api.monitor.alarm.IIllegalProcess;

/**
 * 数据库中映射的黑名单进程实体
 * @author huxiaobao
 *
 */
public class SchindleralarmForm
{
    /**
     * 记录Id
     */
    private long id;
    /**
     * 设备号
     */
    private String terminalId;
    /**
     * 程序名称
     */
    private String name;
    /**
     * 用户名
     */
    private String user;
    /**
     * cpu使用
     */
    private double cpuRate;
    /**
     * 内存使用
     */
    private long memoryRate;
    /**
     * 存入数据库的时间
     */
    private String date;
    
    public SchindleralarmForm(){
        
    }
    
    public SchindleralarmForm(IIllegalProcess illegalProcess) {
        setId(illegalProcess.getId());
        setTerminalId(illegalProcess.getTerminalId());
        setName(illegalProcess.getName());
        setUser(illegalProcess.getUser());
        setCpuRate(illegalProcess.getCpuRate());
        setMemoryRate(illegalProcess.getMemoryRate());
        setDate(illegalProcess.getDate());
    }
    
    public void translate(IIllegalProcess illegalProcess) {
       illegalProcess.setCpuRate(getCpuRate());
       illegalProcess.setId(getId());
       illegalProcess.setTerminalId(getTerminalId());
       illegalProcess.setMemoryRate(getMemoryRate());
       illegalProcess.setName(getName());
       illegalProcess.setUser(getUser());
       illegalProcess.setDate(getDate());
    }

    public static List<SchindleralarmForm> convert(List<IIllegalProcess> list) {
        List<SchindleralarmForm> result = new ArrayList<SchindleralarmForm>();
        for(IIllegalProcess item : list) {
            result.add(new SchindleralarmForm(item));
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

    public String getTerminalId()
    {
        return terminalId;
    }

    public void setTerminalId(String terminalId)
    {
        this.terminalId = terminalId;
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

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }
    
    
}
