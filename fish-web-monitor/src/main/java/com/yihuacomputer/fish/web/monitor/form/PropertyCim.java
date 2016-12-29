package com.yihuacomputer.fish.web.monitor.form;
/**
 * @author YiHua
 *
 */
public class PropertyCim
{
    /**
     * 是否具有暂存器
     */
    private String canEscrow;

    /**
     * 是否支持控制shutter门
     */
    private String shutterControlSupported;

    /**
     * 获取单笔最大验钞张数
     */
    private int maxAcceptItems;

    /**
     * 是否能探测钞票放入
     */
    private String canDetectCashInserted;

    /**
     * 是否能探测钞票被取走
     */
    private String canDetectCashTaken;

    /**
     * 获取回收位置
     */
    private String[] retractAreas;

    public String getCanEscrow()
    {
        return canEscrow;
    }

    public void setCanEscrow(String canEscrow)
    {
        this.canEscrow = canEscrow;
    }

    public String getShutterControlSupported()
    {
        return shutterControlSupported;
    }

    public void setShutterControlSupported(String shutterControlSupported)
    {
        this.shutterControlSupported = shutterControlSupported;
    }

    public int getMaxAcceptItems()
    {
        return maxAcceptItems;
    }

    public void setMaxAcceptItems(int maxAcceptItems)
    {
        this.maxAcceptItems = maxAcceptItems;
    }

    public String getCanDetectCashInserted()
    {
        return canDetectCashInserted;
    }

    public void setCanDetectCashInserted(String canDetectCashInserted)
    {
        this.canDetectCashInserted = canDetectCashInserted;
    }

    public String getCanDetectCashTaken()
    {
        return canDetectCashTaken;
    }

    public void setCanDetectCashTaken(String canDetectCashTaken)
    {
        this.canDetectCashTaken = canDetectCashTaken;
    }

    public String[] getRetractAreas()
    {
        return retractAreas;
    }

    public void setRetractAreas(String[] retractAreas)
    {
        this.retractAreas = retractAreas;
    }
}
