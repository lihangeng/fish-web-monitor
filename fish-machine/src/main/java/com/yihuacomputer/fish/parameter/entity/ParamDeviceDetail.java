package com.yihuacomputer.fish.parameter.entity;

import java.io.Serializable;

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

import com.yihuacomputer.fish.api.parameter.IAppSystem;
import com.yihuacomputer.fish.api.parameter.IParamDeviceDetail;

/**
 * 参数元数据
 *
 * @author YH
 *
 */
@Entity
@Table(name = "PARAM_DEVICE_DETAIL")
public class ParamDeviceDetail implements IParamDeviceDetail, Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_PARAM_DEVICE_DETAIL")
	@SequenceGenerator(name = "SEQ_PARAM_DEVICE_DETAIL", sequenceName = "SEQ_PARAM_DEVICE_DETAIL")
	@Column(name = "ID")
	private long id;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = AppSystem.class)
	@JoinColumn(name = "DEVICE_ID")
	private IAppSystem deviceId;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = AppSystem.class)
	@JoinColumn(name = "ELEMENT_ID")
	private IAppSystem elementId;

	public ParamDeviceDetail() {

	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public IAppSystem getDeviceId() {
		return deviceId;
	}

	@Override
	public void setDeviceId(IAppSystem deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public IAppSystem getElementId() {
		return elementId;
	}

	@Override
	public void setElementId(IAppSystem elementId) {
		this.elementId = elementId;
	}

}
