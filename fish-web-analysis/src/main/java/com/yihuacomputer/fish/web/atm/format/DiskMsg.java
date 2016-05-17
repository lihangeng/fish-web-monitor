package com.yihuacomputer.fish.web.atm.format;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.monitor.hardware.IDisk;

public class DiskMsg
{

    /**
     * 磁盘卷标名称
     */
    private String label;

    /**
     * 磁盘分区名称
     */
    private String name;

    /**
     * 磁盘总大小
     */
    private long totalSize;

    /**
     * 磁盘可用空间大小
     */
    private long freeSize;

    /**
     * 磁盘文件系统
     */
    private String fileSys;

    /**
     * 备注
     */
    private String memo;

    /**
     * 磁盘卷标名称和磁盘分区名称
     */
    private String labelAndname;

    public DiskMsg()
    {

    }

    public DiskMsg(IDisk disk)
    {
        setLabel(disk.getLabel());
        setName(disk.getName());
        setTotalSize(disk.getTotalSize());
        setFreeSize(disk.getFreeSize());
        setFileSys(disk.getFileSys());
        setMemo(disk.getMemo());
        setLabelAndname(disk.getLabelAndname());
    }

    public static List<DiskMsg> convert(List<IDisk> list)
    {
        List<DiskMsg> result = new ArrayList<DiskMsg>();
        for (IDisk item : list)
        {
            result.add(new DiskMsg(item));
        }
        return result;
    }
    
    public void toDisk(IDisk disk)
    {
        disk.setFileSys(this.fileSys);
        disk.setFreeSize(this.freeSize);
        disk.setLabel(this.label);
        disk.setLabelAndname(this.labelAndname);
        disk.setMemo(this.memo);
        disk.setName(this.name);
        disk.setTotalSize(this.totalSize);
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setTotalSize(long totalSize)
    {
        this.totalSize = totalSize;
    }

    public void setFreeSize(long freeSize)
    {
        this.freeSize = freeSize;
    }

    public void setFileSys(String fileSys)
    {
        this.fileSys = fileSys;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public void setLabelAndname(String labelAndname)
    {
        this.labelAndname = labelAndname;
    }

    public String getLabel()
    {
        return this.label;
    }

    public String getName()
    {
        return this.name;
    }

    public long getTotalSize()
    {
        return this.totalSize;
    }

    public long getFreeSize()
    {
        return this.freeSize;
    }

    public String getFileSys()
    {
        return this.fileSys;
    }

    public String getMemo()
    {
        return this.memo;
    }

    public String getLabelAndname()
    {
        return this.labelAndname;
    }

}
