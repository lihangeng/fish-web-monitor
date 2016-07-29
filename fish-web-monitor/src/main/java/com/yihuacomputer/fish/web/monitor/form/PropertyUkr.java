package com.yihuacomputer.fish.web.monitor.form;

/**
 * 设备属性idc
 * 
 * @author pengwenchao
 * 
 */
public class PropertyUkr
{
    /**
     * 获取读卡器类型
     */
    private String variant;

    /**
     * 是否具有退卡能力
     */
    private String canEject;

    /**
     * 是否具有吞卡能力
     */
    private String canCapture;

    /**
     * 获取最大吞卡张数
     */
    private int binCapacity;

    /**
     * 是否具有安全支持？？
     */
    private String security;

    /**
     * 是否具有TrackJisii读能力？？
     */
    private String trackJisiiRead;

    /**
     * 是否具有TrackJisii写能力？？
     */
    private String trackJisiiWrite;

    public String getVariant()
    {
        return variant;
    }

    public void setVariant(String variant)
    {
        this.variant = variant;
    }

    public String isCanEject()
    {
        return canEject;
    }

    public void setCanEject(String canEject)
    {
        this.canEject = canEject;
    }

    public String isCanCapture()
    {
        return canCapture;
    }

    public void setCanCapture(String canCapture)
    {
        this.canCapture = canCapture;
    }

    public int getBinCapacity()
    {
        return binCapacity;
    }

    public void setBinCapacity(int binCapacity)
    {
        this.binCapacity = binCapacity;
    }

    public String isSecurity()
    {
        return security;
    }

    public void setSecurity(String security)
    {
        this.security = security;
    }

    public String isTrackJisiiRead()
    {
        return trackJisiiRead;
    }

    public void setTrackJisiiRead(String trackJisiiRead)
    {
        this.trackJisiiRead = trackJisiiRead;
    }

    public String isTrackJisiiWrite()
    {
        return trackJisiiWrite;
    }

    public void setTrackJisiiWrite(String trackJisiiWrite)
    {
        this.trackJisiiWrite = trackJisiiWrite;
    }

    @Override
    public String toString()
    {
        return "PropertyIdc [variant=" + variant + ", canEject=" + canEject
                + ", canCapture=" + canCapture + ", binCapacity=" + binCapacity
                + ", security=" + security + trackJisiiRead + ", trackJisiiWrite=" + trackJisiiWrite + "]";
    }
}
