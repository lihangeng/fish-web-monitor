package com.yihuacomputer.fish.api.atmMove;

import java.util.Date;

import com.yihuacomputer.fish.api.person.IOrganization;

/**
 * 移机
 * @author xuxiang
 *
 */
public interface IAtmMove {
    /**
     * @param id
     */
    public void setId(long id);

    /**
     * @return
     */
    public long getId();

    /**
     * 移机日期
     *
     * @param date
     */
    public void setDate(Date date);

    /**
     * @return
     */
    public Date getDate();

    /**
     * 设备号
     *
     * @param terminalId
     */
    public void setTerminalId(String terminalId);

    /**
     * @return
     */
    public String getTerminalId();

    /**
     * 源地址
     *
     * @param address
     */
    public void setAddress(String address);

    /**
     * @return
     */
    public String getAddress();

    /**
     * 责任人
     *
     * @param responsibility
     */
    public void setResponsibility(String responsibility);

    /**
     * @return
     */
    public String getResponsibility();

    /**
     * 目标地址
     *
     * @param targetAddress
     */
    public void setTargetAddress(String targetAddress);

    /**
     * @return
     */
    public String getTargetAddress();

    /**
     * 备注
     *
     * @param notice
     */
    public void setNotice(String notice);

    /**
     * @return
     */
    public String getNotice();

    /**
     * 源机构
     *
     * @return
     */
    public IOrganization getOrganization();

    /**
     * @param organization
     */
    public void setOrganization(IOrganization organization);

    /**
     * 目标机构
     *
     * @return
     */
    public IOrganization getTargetOrganization();

    /**
     * @param targetOrganization
     */
    public void setTargetOrganization(IOrganization targetOrganization);

}
