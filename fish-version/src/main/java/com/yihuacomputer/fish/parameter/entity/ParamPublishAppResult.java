package com.yihuacomputer.fish.parameter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.parameter.IAppSystem;
import com.yihuacomputer.fish.api.parameter.IParamPublishAppResult;
import com.yihuacomputer.fish.api.parameter.IParamPublishResult;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;

@Entity
@Table(name = "PARAM_PUBLISH_APP_RESULT")
public class ParamPublishAppResult implements IParamPublishAppResult {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_PARAM_PUBLISH_APP_RESULT")
	@SequenceGenerator(name = "SEQ_PARAM_PUBLISH_APP_RESULT", sequenceName = "SEQ_PARAM_PUBLISH_APP_RESULT")
	@Column(name = "ID")
	private long id;

	@ManyToOne(targetEntity = AppSystem.class)
	@JoinColumn(name = "APP_SYSTEM_ID")
	private IAppSystem appSystem;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS", nullable = true, length = 20)
	private TaskStatus status;
	
	@Column(name = "REASON", nullable = true, length = 30)
	private String reason;

	@ManyToOne(targetEntity = ParamPublishResult.class)
	@JoinColumn(name = "PARAM_PUBLISH_RESULT_ID")
	private IParamPublishResult paramPublishResult;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public IAppSystem getAppSystem() {
		return appSystem;
	}

	@Override
	public void setAppSystem(IAppSystem appSystem) {
		this.appSystem = appSystem;
	}

	@Override
	public TaskStatus getStatus() {
		return status;
	}

	@Override
	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	@Override
	public IParamPublishResult getParamPublishResult() {
		return paramPublishResult;
	}

	@Override
	public void setParamPublishResult(IParamPublishResult paramPublishResult) {
		this.paramPublishResult = paramPublishResult;
	}

	@Override
	public String getReason() {
		return reason;
	}

	@Override
	public void setReason(String reason) {
		this.reason = reason;
	}

}
