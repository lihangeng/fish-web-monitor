package com.yihuacomputer.fish.web.monitor.form;
public class PropertyRpr
{
    /**
     * 是否具有退能力
     */
    private String canEject;

    /**
     * 是否具有回收能力
     */
    private String canCapture;

    /**
     * 是有具有暂存能力
     */
    private String canStack;

    /**
     * 获取最大回收张数
     */
    private int maxRetract;

    public String getCanEject()
    {
        return canEject;
    }

    public void setCanEject(String canEject)
    {
        this.canEject = canEject;
    }

    public String getCanCapture()
    {
        return canCapture;
    }

    public void setCanCapture(String canCapture)
    {
        this.canCapture = canCapture;
    }

    public String getCanStack()
    {
        return canStack;
    }

    public void setCanStack(String canStack)
    {
        this.canStack = canStack;
    }

    public int getMaxRetract()
    {
        return maxRetract;
    }

    public void setMaxRetract(int maxRetract)
    {
        this.maxRetract = maxRetract;
    }
}
