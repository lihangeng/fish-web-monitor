package com.yihuacomputer.fish.monitor.entity.business;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.yihuacomputer.fish.api.monitor.business.IBlackListCard;

/**
 * 黑名单卡
 * 
 * @author shixiaolong
 * 
 */
@Entity
@Table(name = "ATMC_BLACK_CARDS")
public class BlackListCard implements IBlackListCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ATMC_BLACK_CARDS")
    @SequenceGenerator(name = "SEQ_ATMC_BLACK_CARDS", sequenceName = "SEQ_ATMC_BLACK_CARDS")
    @Column(name = "ID")
    private long id;

    @Column(name = "CARD_NO", nullable=false, unique = true, length = 20)
    private String cardNo;

    @Column(name = "USER_NAME", length = 20)
    private String userName;
    
    @Column(name = "CARD_ORGANIZATION", length = 60)
    private String organization;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ADD_DATE", nullable = true)
    private Date addDate;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public BlackListCard() {
    }

    @Override
    public String getCardNo() {
        return this.cardNo;
    }

    @Override
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public Date getAddDate() {
        return this.addDate;
    }

    @Override
    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    @Override
    public String getOrganization() {
        return this.organization;
    }

    @Override
    public void setOrganization(String organization) {
        this.organization = organization;
    }

}
