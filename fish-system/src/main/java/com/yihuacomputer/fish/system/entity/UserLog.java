package com.yihuacomputer.fish.system.entity;

import java.io.Serializable;
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

import com.yihuacomputer.fish.api.person.IUserLog;

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
public class UserLog implements IUserLog,Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -924643267240921084L;

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
    

    /**
     * 浏览器IP
     */
    @Column(name = "CLIENT_IP", nullable = false, length = 20)
    private String clientIP;
    
    /**
     * 服务器IP
     */
    @Column(name = "SERVER_IP", nullable = false, length = 20)
    private String serverIp;

    /**
     * 执行时间
     */
    @Column(name = "TIMES", nullable = false, length = 20)
    private long times;

    public UserLog() {
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

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public long getTimes() {
		return times;
	}

	public void setTimes(long times) {
		this.times = times;
	}
}