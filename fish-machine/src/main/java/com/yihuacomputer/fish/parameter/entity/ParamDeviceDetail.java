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

import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.parameter.IParamDeviceDetail;
import com.yihuacomputer.fish.api.parameter.IParamElement;
import com.yihuacomputer.fish.machine.entity.Device;

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

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Device.class)
	@JoinColumn(name = "DEVICE_ID")
	private IDevice device;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = ParamElement.class)
	@JoinColumn(name = "ELEMENT_ID")
	private IParamElement element;

	@Column(name = "PARAM_VALUE", length = 40)
	private String paramValue;

	@Column(name = "VERSION_TIMESTAMP")
	private long versionTimeStamp;

	public ParamDeviceDetail() {

	}

	@Override
	public IDevice getDevice() {
		return device;
	}

	@Override
	public void setDevice(IDevice device) {
		this.device = device;
	}

	@Override
	public IParamElement getElement() {
		return element;
	}

	@Override
	public void setElement(IParamElement element) {
		this.element = element;
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
	public String getParamValue() {
		return paramValue;
	}

	@Override
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public long getVersionTimeStamp() {
		return versionTimeStamp;
	}

	public void setVersionTimeStamp(long versionTimeStamp) {
		this.versionTimeStamp = versionTimeStamp;
	}

}
