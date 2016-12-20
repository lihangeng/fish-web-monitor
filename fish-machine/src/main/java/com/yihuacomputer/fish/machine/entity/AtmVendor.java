package com.yihuacomputer.fish.machine.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.atm.VendorStatus;
import com.yihuacomputer.fish.api.atm.IAtmVendor;
import com.yihuacomputer.fish.api.atm.IAtmBrandService;

/**
 * 设备品牌
 * 
 * @author wangchao
 * 
 */
@Entity
@Table(name = "DEV_VENDOR")
public class AtmVendor implements IAtmVendor,Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7696388152736249153L;

	@Transient
    private IAtmBrandService service;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_VENDOR")
    @SequenceGenerator(name = "SEQ_DEV_VENDOR", sequenceName = "SEQ_DEV_VENDOR")
    @Column(name = "ID")
    private long id;

    /**
     * 编号
     */
/*    @Column(name = "NO", length = 20)
    private String no;*/

    /**
     * 品牌名称
     */
    @Column(name = "NAME", length = 50)
    private String name;

    /**
     * 生产商国家或地区
     */
    @Column(name = "COUNTRY", length = 30)
    private String country;

    /**
     * 生产商地址
     */
    @Column(name = "ADDRESS", length = 60)
    private String address;

    /**
     * 生产商热线1
     */
    @Column(name = "HOTLINE1", length = 20)
    private String hotline1;

    /**
     * 生产商热线2
     */
    @Column(name = "HOTLINE2", length = 20)
    private String hotline2;

    /**
     * 生产商状态
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "STATUS")
    private VendorStatus status;

    public AtmVendor()
    {
    	this.status = VendorStatus.SUPPLY;
    }

    public AtmVendor(IAtmBrandService service)
    {
    	this();
    	this.service = service;
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
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String getCountry()
    {
        return country;
    }

    @Override
    public void setCountry(String country)
    {
        this.country = country;
    }

    @Override
    public String getAddress()
    {
        return address;
    }

    @Override
    public void setAddress(String address)
    {
        this.address = address;
    }

    @Override
    public String getHotline1()
    {
        return hotline1;
    }

    @Override
    public void setHotline1(String hotline1)
    {
        this.hotline1 = hotline1;
    }

    @Override
    public String getHotline2()
    {
        return hotline2;
    }

    @Override
    public void setHotline2(String hotline2)
    {
        this.hotline2 = hotline2;
    }

    @Override
    public VendorStatus getStatus()
    {
        return status;
    }

    @Override
    public void setStatus(VendorStatus status)
    {
        this.status = status;
    }

    public void update(IAtmVendor brand)
    {
        setAddress(brand.getAddress());
        setCountry(brand.getCountry());
        setHotline1(brand.getHotline1());
        setHotline2(brand.getHotline2());
        setName(brand.getName());
        setStatus(brand.getStatus());
    }
}
