package com.yihuacomputer.fish.machine.entity.device;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.device.DeviceBrand;
import com.yihuacomputer.fish.api.device.IDeviceMeta;

/**
 * 设备元数据
 * 
 * @author pengwenchao
 * 
 */
@Embeddable
public class DeviceMeta implements IDeviceMeta
{
    @Transient
    private long id;

    @Column(name = "BRAND", length = 5)
    private DeviceBrand brand;

    @Column(name = "TYPE", length = 5)
    private String type;

    public DeviceMeta(DeviceBrand brand, String type)
    {
        this.brand = brand;
        this.type = type;
    }

    @Override
    public long getId()
    {
        return id;
    }

    @Override
    public void setId(long id)
    {
        this.id = id;
    }

    @Override
    public DeviceBrand getBrand()
    {
        return brand;
    }

    @Override
    public String getType()
    {
        return type;
    }
}
