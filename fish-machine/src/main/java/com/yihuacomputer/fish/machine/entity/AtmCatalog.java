package com.yihuacomputer.fish.machine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.atm.IAtmCatalog;

/**
 * 设备分类
 *
 * @author wangchao
 *
 */
@Entity
@Table(name = "DEV_CATALOG")
public class AtmCatalog implements IAtmCatalog
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_CATALOG")
    @SequenceGenerator(name = "SEQ_DEV_CATALOG", sequenceName = "SEQ_DEV_CATALOG")
    @Column(name = "ID")
    private long id;

    /**
     * 编号
     */
    @Column(name = "CATALOG_NO", length = 5)
    private String no;

    /**
     * 分类名称
     */
    @Column(name = "NAME", length = 30)
    private String name;

    /**
     * 描述
     */
    @Column(name = "NOTE", length = 30)
    private String note;

    public AtmCatalog()
    {
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
    public String getNo()
    {
        return no;
    }

    @Override
    public void setNo(String no)
    {
        this.no = no;

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
    public String getNote()
    {
        return note;
    }

    @Override
    public void setNote(String note)
    {
        this.note = note;
    }

    public void update(IAtmCatalog catalog)
    {
        setName(catalog.getName());
        setNo(catalog.getNo());
        setNote(catalog.getNote());
    }

}
