package com.yihuacomputer.fish.advert.bs.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupDeviceRelation;

@Entity
@Table(name = "ADV_GROUP_DEVICE_RELATION")
public class AdvertGroupDeviceRelation implements IAdvertGroupDeviceRelation,
		Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ADV_GROUP")
	@SequenceGenerator(name = "SEQ_ADV_GROUP", sequenceName = "SEQ_ADV_GROUP")
	@Column(name = "ID")
	private long id;

	/**
	 * 组ID
	 */
	@Column(name = "GROUP_ID")
	private long groupId;

	@Column(name = "DEVICE_ID")
	private long deviceId;
	

	@Column(name = "ADVERT_ID")
	private long advertId;

	@Column(name = "ADVERT_ID_USING")
	private long advertIdUsing;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}

	public long getAdvertId() {
		return advertId;
	}

	public void setAdvertId(long advertId) {
		this.advertId = advertId;
	}

	public long getAdvertIdUsing() {
		return advertIdUsing;
	}

	public void setAdvertIdUsing(long advertIdUsing) {
		this.advertIdUsing = advertIdUsing;
	}
	
}
