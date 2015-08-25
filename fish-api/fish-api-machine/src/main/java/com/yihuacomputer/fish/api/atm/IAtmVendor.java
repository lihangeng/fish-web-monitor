package com.yihuacomputer.fish.api.atm;

/**
 * 设备品牌
 * @author xuxiang
 *
 */
public interface IAtmVendor
{

    public long getId();

    public void setId(long id);

    public String getName();

    public void setName(String name);

    public String getCountry();

    public void setCountry(String country);

    public String getAddress();

    public void setAddress(String address);

    public String getHotline1();

    public void setHotline1(String hotline1);

    public String getHotline2();

    public void setHotline2(String hotline2);

    public VendorStatus getStatus();

    public void setStatus(VendorStatus status);
}
