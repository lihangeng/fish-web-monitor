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

import com.yihuacomputer.fish.api.system.quartz.IJobSynchronous;

/**
 * @author YiHua
 *
 */
@Entity
@Table(name = "SM_JOB_SYNCHRON")
public class JobSynchronous implements IJobSynchronous, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6587926842321410439L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SM_JOB_SYNCHRON")
    @SequenceGenerator(name = "SEQ_SM_JOB_SYNCHRON", sequenceName = "SEQ_SM_JOB_SYNCHRON")
    @Column(name = "ID")
	private String id;
	
    @Column(name = "JOB_NAME")
	private String jobName;
	
    @Column(name = "JOB_TRIGER")
	private String jobTriger;
	
    @Column(name = "SERVER_IP")
	private String serverIp;
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "JOB_DATE")
	private Date jobDate;
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getJobName() {
		return jobName;
	}

	@Override
	public String getJobTriger() {
		return jobTriger;
	}

	@Override
	public Date getDateTime() {
		return jobDate;
	}

	@Override
	public String getServerIp() {
		return serverIp;
	}

	@Override
	public void setDateTime(Date dateTime) {
		this.jobDate = dateTime;
	}

	@Override
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

}
