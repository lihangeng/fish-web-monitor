package com.yihuacomputer.fish.api.atmMove;

import java.util.Date;

import com.yihuacomputer.fish.api.person.IOrganization;

/**
 * 移机
 * @author xuxiang
 *
 */
public interface IAtmMove {
    public void setId(long id);

    public long getId();

    /**
     * 移机日期
     *
     * @param date
     */
    public void setDate(Date date);

    public Date getDate();

    /**
     * 设备号
     *
     * @param number
     */
    public void setTerminalId(String terminalId);

    public String getTerminalId();

    /**
     * 源地址
     *
     * @param address
     */
    public void setAddress(String address);

    public String getAddress();

    /**
     * 责任人
     *
     * @param responsibility
     */
    public void setResponsibility(String responsibility);

    public String getResponsibility();

    /**
     * 目标地址
     *
     * @param targetAddress
     */
    public void setTargetAddress(String targetAddress);

    public String getTargetAddress();

    /**
     * 备注
     *
     * @param notice
     */
    public void setNotice(String notice);

    public String getNotice();

    /**
     * 源机构
     *
     * @return
     */
    public IOrganization getOrganization();

    public void setOrganization(IOrganization organization);

    /**
     * 目标机构
     *
     * @return
     */
    public IOrganization getTargetOrganization();

    public void setTargetOrganization(IOrganization targetOrganization);

}
