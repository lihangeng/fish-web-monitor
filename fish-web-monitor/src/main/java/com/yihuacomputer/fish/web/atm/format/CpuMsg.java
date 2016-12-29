package com.yihuacomputer.fish.web.atm.format;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.monitor.hardware.ICpu;

/**
 * @author YiHua
 *
 */
public class CpuMsg
{
    /**
     * CPU的总量MHz
     */
    private int frequency;

    /**
     * CPU的厂商，如：Intel
     */
    private String vendor;

    /**
     * CPU的类别，如：Celeron
     */
    private String model;

    /**
     * 缓冲存储器数量
     */
    private long cacheSize;

    /**
     * CPU核数
     */
    private int totalCores;

    /**
     * 用户使用率
     */
    private String user;

    /**
     * 系统使用率
     */
    private String sys;

    /**
     * 当前空闲率
     */
    private String idle;

    /**
     * 总的使用率
     */
    private String combined;

    public CpuMsg()
    {
    }

    /**
     * @param cpu
     */
    public CpuMsg(ICpu cpu)
    {
        setFrequency(cpu.getFrequency());
        setVendor(cpu.getVendor());
        setModel(cpu.getModel());
        setCacheSize(cpu.getCacheSize());
        setTotalCores(cpu.getTotalCores());
        setUser(cpu.getUser());
        setSys(cpu.getSys());
        setIdle(cpu.getIdle());
        setCombined(cpu.getCombined());
    }
    /**
     * @param list
     * @return
     */
    public static List<CpuMsg> convert(List<ICpu> list)
    {
        List<CpuMsg> result = new ArrayList<CpuMsg>();
        for (ICpu item : list)
        {
            result.add(new CpuMsg(item));
        }
        return result;
    }
    
    public int getFrequency()
    {
        return this.frequency;
    }

    public void setFrequency(int frequency)
    {
        this.frequency = frequency;
    }

    public String getVendor()
    {
        return vendor;
    }

    public void setVendor(String vendor)
    {
        this.vendor = vendor;
    }

    public String getModel()
    {
        return this.model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public long getCacheSize()
    {
        return this.cacheSize;
    }

    public void setCacheSize(long cacheSize)
    {
        this.cacheSize = cacheSize;
    }

    public int getTotalCores()
    {
        return this.totalCores;
    }

    public void setTotalCores(int totalCores)
    {
        this.totalCores = totalCores;
    }

    public String getUser()
    {
        return this.user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getSys()
    {
        return this.sys;
    }

    public void setSys(String sys)
    {
        this.sys = sys;
    }

    public String getIdle()
    {
        return this.idle;
    }

    public void setIdle(String idle)
    {
        this.idle = idle;
    }

    public String getCombined()
    {
        return this.combined;
    }

    public void setCombined(String combined)
    {
        this.combined = combined;
    }

    /**
     * @param cpu
     */
    public void toCpu(ICpu cpu)
    {
        cpu.setCacheSize(this.cacheSize);
        cpu.setCombined(this.combined);
        cpu.setFrequency(this.frequency);
        cpu.setIdle(this.idle);
        cpu.setModel(this.model);
        cpu.setSys(this.sys);
        cpu.setTotalCores(this.totalCores);
        cpu.setUser(this.user);
        cpu.setVendor(this.vendor);
    }

}
