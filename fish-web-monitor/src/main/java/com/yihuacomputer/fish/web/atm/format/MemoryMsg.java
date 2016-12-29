package com.yihuacomputer.fish.web.atm.format;

import com.yihuacomputer.fish.api.monitor.hardware.IMemory;

/**
 * @author YiHua
 *
 */
public class MemoryMsg
{
    /**
     * 机器的内存大小
     */
    private long memorySize;

    /**
     * 已使用内存大小
     */
    private long used;

    /**
     * 空闲内存大小
     */
    private long free;

    /**
     * 内存使用率
     */
    private double usedPercent;

    public MemoryMsg()
    {
    }

    /**
     * @param memory
     */
    public MemoryMsg(IMemory memory)
    {
        setMemorySize(memory.getMemorySize());
        setUsed(memory.getUsed());
        setFree(memory.getFree());
        setUsedPercent(memory.getUsedPercent());
    }

    /**
     * @param memory
     */
    public void toMemory(IMemory memory)
    {
        memory.setFree(this.free);
        memory.setMemorySize(this.memorySize);
        memory.setUsed(this.used);
        memory.setUsedPercent(this.usedPercent);
    }

    public void setMemorySize(long memorySize)
    {
        this.memorySize = memorySize;
    }

    public void setUsed(long used)
    {
        this.used = used;
    }

    public void setFree(long free)
    {
        this.free = free;
    }

    public void setUsedPercent(double usedPercent)
    {
        this.usedPercent = usedPercent;
    }

    public long getMemorySize()
    {
        return this.memorySize;
    }

    public long getUsed()
    {
        return this.used;
    }

    public long getFree()
    {
        return this.free;
    }

    public double getUsedPercent()
    {
        return this.usedPercent;
    }

}
