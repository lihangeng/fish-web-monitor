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
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ADV_GROUP_DEVICE_RELATION")
	@SequenceGenerator(name = "SEQ_ADV_GROUP_DEVICE_RELATION", sequenceName = "SEQ_ADV_GROUP_DEVICE_RELATION")
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
	
	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getGroupId() {
		return groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	@Override
	public long getDeviceId() {
		return deviceId;
	}

	@Override
	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public long getAdvertId() {
		return advertId;
	}

	@Override
	public void setAdvertId(long advertId) {
		this.advertId = advertId;
	}

	@Override
	public long getAdvertIdUsing() {
		return advertIdUsing;
	}

	@Override
	public void setAdvertIdUsing(long advertIdUsing) {
		this.advertIdUsing = advertIdUsing;
	}
	
	
    /**
     * 根据指定的广告组和设备创建关系实体
     * 
     * @param master
     * @param role
     * @return
     */
    public static AdvertGroupDeviceRelation make(Long groupId, Long deviceId) {
    	AdvertGroupDeviceRelation obj = new AdvertGroupDeviceRelation();
        obj.groupId = groupId;
        obj.deviceId = deviceId;
        return obj;
    }
	
}
