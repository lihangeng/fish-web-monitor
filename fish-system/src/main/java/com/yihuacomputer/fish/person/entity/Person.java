package com.yihuacomputer.fish.person.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.person.Gender;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.IPersonJob;
import com.yihuacomputer.fish.api.person.PersonState;
import com.yihuacomputer.fish.api.person.PersonType;
import com.yihuacomputer.fish.person.service.api.IDomainPersonService;

/**
 * 人员信息：（信息实体对应数据库表PERSON）
 *
 * @author huxiaobao
 *
 */
@Entity
@Table(name = "SM_PERSON")
public class Person implements IPerson {

    @Transient
    private IDomainPersonService service;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SM_PERSON")
    @SequenceGenerator(name = "SEQ_SM_PERSON", sequenceName = "SEQ_SM_PERSON")
    @Column(name = "ID")
    private long id;

    /**
     * 编号
     */
    @Column(name = "CODE", nullable = true, length = 40)
    private String code;

    /**
     * 工号
     */
    @Column(name = "JOB_NUM", nullable = true, length = 40)
    private String jobNum;

    /**
     * 名称
     */
    @Column(name = "NAME", nullable = false, length = 40)
    private String name;

    /**
     * 生日
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY", nullable = true)
    private Date birthday;

    /**
     * 电子邮件
     */
    @Column(name = "EMAIL", nullable = true, length = 50)
    private String email;

    /**
     * 性别
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "GENDER", nullable = true, length = 10)
    private Gender gender;

    /**
     * 手机号
     */
    @Column(name = "MOBILE", nullable = true, length = 20)
    private String mobile;

    /**
     * 固定电话
     */
    @Column(name = "PHONE", nullable = true, length = 20)
    private String phone;

    /**
     * 人员类型
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "PERSON_TYPE", nullable = true, length = 10)
    private PersonType type;

    /**
     * 所属机构
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = com.yihuacomputer.fish.person.entity.Organization.class)
    @JoinColumn(name = "ORGANIZATION_ID")
    private IOrganization organization;

    /**
     * 状态
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "STATE", nullable = true, length = 10)
    private PersonState state;

    @Column(name = "REMARK", nullable = true, length = 400)
    private String remark;

    /**
     * 人员工作岗位
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = com.yihuacomputer.fish.person.entity.PersonJob.class)
    @JoinColumn(name = "PERSONJOB")
    private IPersonJob personJob;

    /**
     * 预留字段
     */
    @Column(name = "RESERVE1", nullable = true, length = 20)
    private String reserve1;

    /**
     * 预留字段
     */
    @Column(name = "RESERVE2", nullable = true, length = 20)
    private String reserve2;

    /**
     * 预留字段
     */
    @Column(name = "RESERVE3", nullable = true, length = 20)
    private String reserve3;

    public Person() {
    }

    public Person(IDomainPersonService service, String name) {
        this.service = service;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getGuid() {
        return String.valueOf(id);
    }

    @Override
    public void setGuid(String guid) {
        id = Long.valueOf(guid);
    }

    @Override
    public Date getBirthday() {
        return this.birthday;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public Gender getGender() {
        return this.gender;
    }

    @Override
    public String getMobile() {
        return this.mobile;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public IOrganization getOrganization() {
        return this.organization;
    }

    @Override
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setOrganization(IOrganization organization) {
        this.organization = organization;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getPhone() {
        return this.phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public IDomainPersonService getService() {
        return service;
    }

    public void setService(IDomainPersonService service) {
        this.service = service;
    }

    @Override
    public PersonType getType() {
        return this.type;
    }

    @Override
    public void setType(PersonType type) {
        this.type = type;
    }

    @Override
    public PersonState getState() {
        return this.state;
    }

    @Override
    public void setState(PersonState state) {
        this.state = state;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public IPersonJob getPersonJob() {
        return personJob;
    }

    @Override
    public void setPersonJob(IPersonJob personJob) {
        this.personJob = personJob;
    }

    @Override
    public String getReserve1() {
        return reserve1;
    }

    @Override
    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    @Override
    public String getReserve2() {
        return reserve2;
    }

    @Override
    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    @Override
    public String getReserve3() {
        return reserve3;
    }

    @Override
    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }
}