package com.yihuacomputer.fish.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.person.IPersonJob;

/**
 * 人员岗位
 *
 * @author pengwenchao
 *
 */
@Entity
@Table(name = "SM_PERSON_JOB")
public class PersonJob implements IPersonJob,Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7384753444582361614L;

	/**
     * ID,自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SM_PERSON_JOB")
    @SequenceGenerator(name = "SEQ_SM_PERSON_JOB", sequenceName = "SEQ_SM_PERSON_JOB")
    @Column(name = "ID")
    private long id;

    /**
     * 岗位编号(唯一)
     */
    @Column(name = "CODE", unique = true, length = 20)
    private String code;

    /**
     * 岗位名称
     */
    @Column(name = "NAME", length = 20)
    private String name;

    /**
     * 备注
     */
    @Column(name = "REMARK", nullable = true, length = 400)
    private String remark;

    @Override
    public long getId() {
        return id;
    }

    @Override
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

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
