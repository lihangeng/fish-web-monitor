package com.yihuacomputer.fish.person.entity;

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
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.person.IUserLog;
import com.yihuacomputer.fish.person.service.api.IDomainUserLogService;

/**
 * 用户日志信息:（信息实体对应数据库表SM_USER_LOG）
 * 
 * @author shixiaolong
 * @version
 * @since
 * @date 2010-5-3
 */
@Entity
@Table(name = "SM_USER_LOG")
public class UserLog implements IUserLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SM_USER_LOG")
    @SequenceGenerator(name = "SEQ_SM_USER_LOG", sequenceName = "SEQ_SM_USER_LOG")
    @Column(name = "ID")
    private long id;

    /**
     * 操作内容
     */
    @Column(name = "OPER_CONTENT", nullable = false, length = 100)
    private String operContent;

    /**
     * 操作人编号
     */
    @Column(name = "OPER_CODE", nullable = false, length = 40)
    private String operCode;

    /**
     * 操作人姓名
     */
    @Column(name = "OPER_NAME", nullable = false, length = 40)
    private String operName;

    /**
     * 操作时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "OPER_TIME", nullable = true)
    private Date operTime;
    
    /**
     * 操作结果
     */
    @Column(name = "OPER_RESULT", nullable = false, length = 10)
    private String operResult;

    @Transient
    private IDomainUserLogService service;

    public UserLog() {

    }

    public UserLog(IDomainUserLogService service) {
        this.service = service;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getOperCode() {
        return this.operCode;
    }

    @Override
    public void setOperCode(String operCode) {
        this.operCode = operCode;
    }

    @Override
    public Date getOperTime() {
        return this.operTime;
    }

    @Override
    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    @Override
    public String getOperContent() {
        return this.operContent;
    }

    @Override
    public void setOperContent(String operContent) {
        this.operContent = operContent;
    }

    @Override
    public String getOperName() {
        return this.operName;
    }

    @Override
    public void setOperName(String operName) {
        this.operName = operName;
    }
    
    @Override
    public String getOperResult() {
        return this.operResult;
    }

    @Override
    public void setOperResult(String operResult) {
        this.operResult = operResult;
    }

    public IDomainUserLogService getService() {
        return this.service;
    }

    public void setService(IDomainUserLogService service) {
        this.service = service;
    }

}