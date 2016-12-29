package com.yihuacomputer.fish.web.monitor.form;
/**
 * @author YiHua
 *
 */
public class PropertyJpr
{
    /**
     * 是否具有退纸能力
     */
    private String canEject;

    /**
     * 是否具有回收能力
     */
    private String canCapture;

    /**
     * 是否具有暂存能力
     */
    private String canStack;

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
}
