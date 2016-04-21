package com.yihuacomputer.fish.parameter.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.parameter.IParamPublish;
import com.yihuacomputer.fish.api.parameter.IParamPublishResult;
import com.yihuacomputer.fish.api.version.job.JobType;

@Entity
@Table(name="PARAM_PUBLISH")
public class ParamPublish implements IParamPublish {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_PARAM_PUBLISH")
	@SequenceGenerator(name = "SEQ_PARAM_PUBLISH", sequenceName = "SEQ_PARAM_PUBLISH")
	@Column(name = "ID")
	private long id;

	@Column(name = "PUBLISH_DATE")
	private String date;

	@Column(name = "PUBLISHER_ID")
	private long publisher;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "PUBLISHER_TYPE", length = 20)
    private JobType jobType;
	
    @OneToMany(mappedBy = "paramPublish", targetEntity = ParamPublishResult.class)
    private List<IParamPublishResult> paramPublishs = new ArrayList<IParamPublishResult>();

	@Column(name = "PUBLISH_RET")
	private String ret;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public long getPublisher() {
		return publisher;
	}
	public void setPublisher(long publisher) {
		this.publisher = publisher;
	}
	public String getRet() {
		return ret;
	}
	public void setRet(String ret) {
		this.ret = ret;
	}
	public List<IParamPublishResult> getParamPublishs() {
		return paramPublishs;
	}
	public void setParamPublishs(List<IParamPublishResult> paramPublishs) {
		this.paramPublishs = paramPublishs;
	}
	public JobType getJobType() {
		return jobType;
	}
	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}
	
}
