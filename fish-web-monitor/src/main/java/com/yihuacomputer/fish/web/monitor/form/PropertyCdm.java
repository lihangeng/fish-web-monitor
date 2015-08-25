package com.yihuacomputer.fish.web.monitor.form;
public class PropertyCdm
{
    /**
     * 是否具有暂存器
     */
    private String hasStack;

    /**
     * 是否具有shutter门
     */
    private String hasShutter;

    /**
     * 是否具有回收能力
     */
    private String canRetract;

    /**
     * 是否探测钞币被拿走
     */
    private String canDetectCashTaken;

    /**
     * 是否能测试物理单元
     */
    private String canTestPhysicalUnits;

    /**
     * 获取单笔最大挖钞张数
     */
    private int maxDispensBills;

    /**
     * 获取逻辑钞箱个数
     */
    private int logicalUnits;

    /**
     * 获取物理钞箱个数
     */
    private int physicalUnits;

    /**
     * 获取支持的币种类别总个数
     */
    private int currency;

    /**
     * 获取支持的币种类别
     */
    private String[] currencies;

    /**
     * 获取指数？？
     */
    private int[] exponents;

    public String getHasStack()
    {
        return hasStack;
    }

    public void setHasStack(String hasStack)
    {
        this.hasStack = hasStack;
    }

    public String getHasShutter()
    {
        return hasShutter;
    }

    public void setHasShutter(String hasShutter)
    {
        this.hasShutter = hasShutter;
    }

    public String getCanRetract()
    {
        return canRetract;
    }

    public void setCanRetract(String canRetract)
    {
        this.canRetract = canRetract;
    }

    public String getCanDetectCashTaken()
    {
        return canDetectCashTaken;
    }

    public void setCanDetectCashTaken(String canDetectCashTaken)
    {
        this.canDetectCashTaken = canDetectCashTaken;
    }

    public String getCanTestPhysicalUnits()
    {
        return canTestPhysicalUnits;
    }

    public void setCanTestPhysicalUnits(String canTestPhysicalUnits)
    {
        this.canTestPhysicalUnits = canTestPhysicalUnits;
    }

    public int getMaxDispensBills()
    {
        return maxDispensBills;
    }

    public void setMaxDispensBills(int maxDispensBills)
    {
        this.maxDispensBills = maxDispensBills;
    }

    public int getLogicalUnits()
    {
        return logicalUnits;
    }

    public void setLogicalUnits(int logicalUnits)
    {
        this.logicalUnits = logicalUnits;
    }

    public int getPhysicalUnits()
    {
        return physicalUnits;
    }

    public void setPhysicalUnits(int physicalUnits)
    {
        this.physicalUnits = physicalUnits;
    }

    public int getCurrency()
    {
        return currency;
    }

    public void setCurrency(int currency)
    {
        this.currency = currency;
    }

    public String[] getCurrencies()
    {
        return currencies;
    }

    public void setCurrencies(String[] currencies)
    {
        this.currencies = currencies;
    }

    public int[] getExponents()
    {
        return exponents;
    }

    public void setExponents(int[] exponents)
    {
        this.exponents = exponents;
    }
}
