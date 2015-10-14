package com.yihuacomputer.fish.machine.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.yihuacomputer.fish.api.atmMove.IAtmMove;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.machine.service.api.IDomainAtmMoveService;

@Entity
@Table(name = "DEV_MOVE")
public class AtmMove implements IAtmMove {
    @Transient
    private IDomainAtmMoveService service;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_MOVE")
    @SequenceGenerator(name = "SEQ_DEV_MOVE", sequenceName = "SEQ_DEV_MOVE")
    @Column(name = "ID")
    private long id;

    /**
     * 日期
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MOVE_DATE")
    private Date date;

    /**
     * 设备号
     */
    @Column(name = "DEV_NO", length = 20)
    private String terminalId;

    /**
     * 源地址
     */
    @Column(name = "SRC_ADDR", length = 50)
    private String address;

    // /**
    // * 源机构
    // */
    // @Column(name = "SRC_ORG" ,length = 20)
    // private String organization;

    /**
     * 源机构
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = com.yihuacomputer.fish.system.entity.Organization.class)
    @JoinColumn(name = "SRC_ORG_ID")
    private IOrganization organization;

    /**
     * 责任人
     */
    @Column(name = "RES_PERSON", length = 10)
    private String responsibility;

    /**
     * 目标地址
     */
    @Column(name = "TARGET_ADDR", length = 50)
    private String targetAddress;

    // /**
    // * 目标机构
    // */
    // @Column(name = "TARGET_ORG" ,length = 20)
    // private String targetOrganization;
    /**
     * 源机构
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = com.yihuacomputer.fish.system.entity.Organization.class)
    @JoinColumn(name = "TARGET_ORG_ID")
    private IOrganization targetOrganization;

//    /**
//     * 设备号
//     */
//    @Column(name = "TARGET_DEV_NO", length = 60)
//    private String targetTerminalId;

    /**
     * 备注
     */
    @Column(name = "NOTE", length = 50)
    private String notice;

//    /**
//     * 原IP
//     */
//    @Column(name = "IP", length = 20)
//    private String ip;
//
//    /**
//     * 目的ip
//     */
//    @Column(name = "TARGET_IP", length = 20)
//    private String targetIp;

    public AtmMove(IDomainAtmMoveService service) {
        this.service = service;
    }

    public AtmMove() {
    }

    public IOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(IOrganization organization) {
        this.organization = organization;
    }

    public IOrganization getTargetOrganization() {
        return targetOrganization;
    }

    public void setTargetOrganization(IOrganization targetOrganization) {
        this.targetOrganization = targetOrganization;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    @Override
    public String getTerminalId() {
        return this.terminalId;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    @Override
    public String getResponsibility() {
        return this.responsibility;
    }

    @Override
    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }

    @Override
    public String getTargetAddress() {
        return this.targetAddress;
    }


    @Override
    public void setNotice(String notice) {
        this.notice = notice;
    }

    @Override
    public String getNotice() {
        return this.notice;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;

    }

    @Override
    public Date getDate() {
        return date;
    }

//    @Override
//    public void setTargetIp(String targetIp) {
//        this.targetIp = targetIp;
//    }
//
//    @Override
//    public String getTargetIp() {
//        return this.targetIp;
//    }
//
//    @Override
//    public void setIp(String ip) {
//        this.ip = ip;
//    }
//
//    @Override
//    public String getIp() {
//        return this.ip;
//    }

//    @Override
//    public void setTargetTerminalId(String targetTerminalId) {
//        this.targetTerminalId = targetTerminalId;
//    }
//
//    @Override
//    public String getTargetTerminalId() {
//        return this.targetTerminalId;
//    }

    public void update(IAtmMove atmMove) {
        setAddress(atmMove.getAddress());
        setOrganization(atmMove.getOrganization());
        setDate(atmMove.getDate());
        setNotice(atmMove.getNotice());
        setTerminalId(atmMove.getTerminalId());
        setResponsibility(atmMove.getResponsibility());
        setTargetAddress(atmMove.getTargetAddress());
        setTargetOrganization(atmMove.getTargetOrganization());
//        setTargetTerminalId(atmMove.getTargetTerminalId());
//        setIp(atmMove.getIp());
//        setTargetIp(atmMove.getTargetIp());
    }

}
