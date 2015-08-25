package com.yihuacomputer.fish.version.entity;

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

import com.yihuacomputer.fish.api.version.job.IJob;
/**
 * 作业的实体实现类
 * @since 2.0.0.0开始简化了模型
 * @author xuxiang
 *
 */
@Entity
@Table(name = "VER_JOB")
public class Job implements IJob, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_VER_JOB")
    @SequenceGenerator(name = "SEQ_VER_JOB", sequenceName = "SEQ_VER_JOB")
    @Column(name = "ID")
    private long id;

	@Column(name = "JOB_NAME", nullable = true, length = 128)
	private String jobName;

    // 作业创建时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME")
    private Date createdTime;

    public Job() {
        this.createdTime = new Date();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

}


