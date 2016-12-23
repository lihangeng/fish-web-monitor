package com.yihuacomputer.fish.api.atm;

/**
 * 设备品牌
 * @author xuxiang
 *
 */
public interface IAtmVendor
{

    /**
     * @return
     */
    public long getId();

    /**
     * @param id
     */
    public void setId(long id);

    /**
     * @return
     */
    public String getName();

    /**
     * @param name
     */
    public void setName(String name);

    /**
     * @return
     */
    public String getCountry();

    /**
     * @param country
     */
    public void setCountry(String country);

    /**
     * @return
     */
    public String getAddress();

    /**
     * @param address
     */
    public void setAddress(String address);

    /**
     * @return
     */
    public String getHotline1();

    /**
     * @param hotline1
     */
    public void setHotline1(String hotline1);

    /**
     * @return
     */
    public String getHotline2();

    /**
     * @param hotline2
     */
    public void setHotline2(String hotline2);

    /**
     * @return
     */
    public VendorStatus getStatus();

    /**
     * @param status
     */
    public void setStatus(VendorStatus status);
}
