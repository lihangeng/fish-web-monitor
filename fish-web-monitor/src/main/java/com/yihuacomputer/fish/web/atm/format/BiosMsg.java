package com.yihuacomputer.fish.web.atm.format;

import com.yihuacomputer.fish.api.monitor.hardware.IBios;

public class BiosMsg
{
    /**
     * Bios版本
     */
    private String biosVersion;

    /**
     * Bios厂商
     */
    private String biosVendor;

    /**
     * Bios发布日期
     */
    private String biosReleaseDate;

    public BiosMsg()
    {
    }

    public BiosMsg(IBios bios)
    {
        setBiosReleaseDate(bios.getBiosReleaseDate());
        setBiosVendor(bios.getBiosVendor());
        setBiosVersion(bios.getBiosVersion());
    }

    /**
     * 将对象的信息复制到IBios接口
     * 
     * @param bios
     *            接口
     */
    public void toBios(IBios bios)
    {
        bios.setBiosReleaseDate(this.biosReleaseDate);
        bios.setBiosVendor(this.biosVendor);
        bios.setBiosVersion(this.biosVersion);
    }

    public void setBiosVersion(String biosVersion)
    {
        this.biosVersion = biosVersion;
    }

    public void setBiosVendor(String biosVendor)
    {
        this.biosVendor = biosVendor;
    }

    public void setBiosReleaseDate(String biosReleaseDate)
    {
        this.biosReleaseDate = biosReleaseDate;
    }

    public String getBiosVendor()
    {
        return this.biosVendor;
    }

    public String getBiosVersion()
    {
        return this.biosVersion;
    }

    public String getBiosReleaseDate()
    {
        return this.biosReleaseDate;
    }
}
