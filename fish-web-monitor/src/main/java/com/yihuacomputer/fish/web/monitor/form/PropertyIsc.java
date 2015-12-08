package com.yihuacomputer.fish.web.monitor.form;


/**
 * 设备属性isc
 * 
 * @author wangpeiqi
 * 
 */
public class PropertyIsc {
    /**
     * 获取身份证扫描仪类型
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

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getCanEject() {
        return canEject;
    }

    public void setCanEject(String canEject) {
        this.canEject = canEject;
    }

    public String getCanCapture() {
        return canCapture;
    }

    public void setCanCapture(String canCapture) {
        this.canCapture = canCapture;
    }

    public int getBinCapacity() {
        return binCapacity;
    }

    public void setBinCapacity(int binCapacity) {
        this.binCapacity = binCapacity;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getTrack1Read() {
        return track1Read;
    }

    public void setTrack1Read(String track1Read) {
        this.track1Read = track1Read;
    }

    public String getTrack1Write() {
        return track1Write;
    }

    public void setTrack1Write(String track1Write) {
        this.track1Write = track1Write;
    }

    public String getTrack2Read() {
        return track2Read;
    }

    public void setTrack2Read(String track2Read) {
        this.track2Read = track2Read;
    }

    public String getTrack2Write() {
        return track2Write;
    }

    public void setTrack2Write(String track2Write) {
        this.track2Write = track2Write;
    }

    public String getTrack3Read() {
        return track3Read;
    }

    public void setTrack3Read(String track3Read) {
        this.track3Read = track3Read;
    }

    public String getTrack3Write() {
        return track3Write;
    }

    public void setTrack3Write(String track3Write) {
        this.track3Write = track3Write;
    }

    public String getTrackJisiiRead() {
        return trackJisiiRead;
    }

    public void setTrackJisiiRead(String trackJisiiRead) {
        this.trackJisiiRead = trackJisiiRead;
    }

    public String getTrackJisiiWrite() {
        return trackJisiiWrite;
    }

    public void setTrackJisiiWrite(String trackJisiiWrite) {
        this.trackJisiiWrite = trackJisiiWrite;
    }
}
