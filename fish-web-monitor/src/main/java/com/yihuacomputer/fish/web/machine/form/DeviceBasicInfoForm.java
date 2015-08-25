package com.yihuacomputer.fish.web.machine.form;

/**
 * 实时监控－－设备状态
 * 
 * 设备基本信息Form
 * 
 * @author pengwenchao
 * @date 2011-10-21
 * 
 */
public class DeviceBasicInfoForm
{
    /**
     * id
     */
    private long id;

    /**
     * 设备号
     */
    private String number;

    /**
     * 所属机构
     */
    private String affiliation;

    /**
     * 设备地址
     */
    private String address;

    /**
     * 设备型号
     */
    private String model;

    /**
     * 经营方式
     */
    private String operation;

    /**
     * 在行标志
     */
    private String lineLogo;

    /**
     * IP地址
     */
    private String ipAddress;

    /**
     * 设备维护商
     */
    private String maintainer;

    /**
     * 吞卡张数
     */
    private long swallowCard;

    /**
     * 钱箱报警金额(人发币)
     */
    private String alarmRateRMB;

    /**
     * 钱箱报警金额(港币)
     */
    private String alarmRateHKD;

    /**
     * 管理员(手机号)
     */
    private String adminPhone;

    /**
     * 维护员(手机号)
     */
    private String maintainPhone;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getAffiliation()
    {
        return affiliation;
    }

    public void setAffiliation(String affiliation)
    {
        this.affiliation = affiliation;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getOperation()
    {
        return operation;
    }

    public void setOperation(String operation)
    {
        this.operation = operation;
    }

    public String getLineLogo()
    {
        return lineLogo;
    }

    public void setLineLogo(String lineLogo)
    {
        this.lineLogo = lineLogo;
    }

    public String getIpAddress()
    {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    public String getMaintainer()
    {
        return maintainer;
    }

    public void setMaintainer(String maintainer)
    {
        this.maintainer = maintainer;
    }

    public long getSwallowCard()
    {
        return swallowCard;
    }

    public void setSwallowCard(long swallowCard)
    {
        this.swallowCard = swallowCard;
    }

    public String getAlarmRateRMB()
    {
        return alarmRateRMB;
    }

    public void setAlarmRateRMB(String alarmRateRMB)
    {
        this.alarmRateRMB = alarmRateRMB;
    }

    public String getAlarmRateHKD()
    {
        return alarmRateHKD;
    }

    public void setAlarmRateHKD(String alarmRateHKD)
    {
        this.alarmRateHKD = alarmRateHKD;
    }

    public String getAdminPhone()
    {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone)
    {
        this.adminPhone = adminPhone;
    }

    public String getMaintainPhone()
    {
        return maintainPhone;
    }

    public void setMaintainPhone(String maintainPhone)
    {
        this.maintainPhone = maintainPhone;
    }

    @Override
    public String toString()
    {
        return "DeviceBasicInfoForm [id=" + id + ", number=" + number
                + ", affiliation=" + affiliation + ", address=" + address
                + ", model=" + model + ", operation=" + operation
                + ", lineLogo=" + lineLogo + ", ipAddress=" + ipAddress
                + ", maintainer=" + maintainer + ", swallowCard=" + swallowCard
                + ", alarmRateRMB=" + alarmRateRMB + ", alarmRateHKD="
                + alarmRateHKD + ", adminPhone=" + adminPhone
                + ", maintainPhone=" + maintainPhone + "]";
    }
}
