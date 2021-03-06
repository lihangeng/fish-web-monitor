package com.yihuacomputer.fish.monitor.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.monitor.business.IHostRet;


/**
 * @author YiHua
 *
 */
@Entity
@Table(name = "ATMC_TRANS_HOSTRET")
public class HostRet implements IHostRet{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ATMC_TRANS_HOSTRET")
    @SequenceGenerator(name = "SEQ_ATMC_TRANS_HOSTRET", sequenceName = "SEQ_ATMC_TRANS_HOSTRET")
    @Column(name = "ID")
    private long id;
    
    @Column(name = "HOSTRET_CODE", nullable=false,unique = true,length = 20)
    private String code;
    
    @Column(name = "HOSTRET_NAME", nullable=false, length = 30)
    private String name;
    
    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
