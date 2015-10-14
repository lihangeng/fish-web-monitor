package com.yihuacomputer.fish.machine.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.atm.IAtmGroup;
import com.yihuacomputer.fish.api.atm.IAtmGroupService;



/**
 * 设备分组
 * 
 * @author huxiaobao
 * 
 */
@Entity
@Table(name = "DEV_GROUP")
public class AtmGroup implements IAtmGroup,Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 381297126968571251L;

	@Transient
    private IAtmGroupService service;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_GROUP")
    @SequenceGenerator(name = "SEQ_DEV_GROUP", sequenceName = "SEQ_DEV_GROUP")
    @Column(name = "ID")
    private long id;

    /**
     * 分组名称
     */
    @Column(name = "NAME", length = 30)
    private String name;

    /**
     * 描述
     */
    @Column(name = "NOTE", length = 30)
    private String note;
    
    public AtmGroup()
    {
    }

    public AtmGroup(IAtmGroupService service)
    {
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
    public String getNote()
    {
        return note;
    }

    @Override
    public void setNote(String note)
    {
        this.note = note;
    }

    public void update(IAtmGroup atmGroup)
    {
        setName(atmGroup.getName());
        setNote(atmGroup.getNote());
    }

}
