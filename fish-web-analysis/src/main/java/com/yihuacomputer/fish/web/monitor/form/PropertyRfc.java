package com.yihuacomputer.fish.web.monitor.form;


/**
 * 设备属性idc
 * 
 * @author pengwenchao
 * 
 */
public class PropertyRfc
{
    /**
     * 获取读卡器类型
     */
    private String variant;

    /**
     * 是否具有安全支持？？
     */
    private String security;

    /**
     * 是否具有读一磁道数据能力
     */
    private String track1Read;

    /**
     * 是否具有写一磁道数据能力
     */
    private String track1Write;

    /**
     * 是否具有读二磁道数据能力
     */
    private String track2Read;

    /**
     * 是否具有写二磁道数据能力
     */
    private String track2Write;

    /**
     * 是否具有读三磁道数据能力
     */
    private String track3Read;

    /**
     * 是否具有写三磁道数据能力
     */
    private String track3Write;

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

   

    public String isSecurity()
    {
        return security;
    }

    public void setSecurity(String security)
    {
        this.security = security;
    }

    public String isTrack1Read()
    {
        return track1Read;
    }

    public void setTrack1Read(String track1Read)
    {
        this.track1Read = track1Read;
    }

    public String isTrack1Write()
    {
        return track1Write;
    }

    public void setTrack1Write(String track1Write)
    {
        this.track1Write = track1Write;
    }

    public String isTrack2Read()
    {
        return track2Read;
    }

    public void setTrack2Read(String track2Read)
    {
        this.track2Read = track2Read;
    }

    public String isTrack2Write()
    {
        return track2Write;
    }

    public void setTrack2Write(String track2Write)
    {
        this.track2Write = track2Write;
    }

    public String isTrack3Read()
    {
        return track3Read;
    }

    public void setTrack3Read(String track3Read)
    {
        this.track3Read = track3Read;
    }

    public String isTrack3Write()
    {
        return track3Write;
    }

    public void setTrack3Write(String track3Write)
    {
        this.track3Write = track3Write;
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
        return "PropertyRFC [variant=" + variant + ", security=" + security + ", track1Read=" + track1Read
                + ", track1Write=" + track1Write + ", track2Read=" + track2Read
                + ", track2Write=" + track2Write + ", track3Read=" + track3Read
                + ", track3Write=" + track3Write + ", trackJisiiRead="
                + trackJisiiRead + ", trackJisiiWrite=" + trackJisiiWrite + "]";
    }
}
