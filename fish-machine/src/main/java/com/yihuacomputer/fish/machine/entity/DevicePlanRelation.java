package com.yihuacomputer.fish.machine.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.openplan.IDevicePlanRelation;


@Entity
@Table(name = "DEV_OPENPLAN_RELATION")
public class DevicePlanRelation implements IDevicePlanRelation,Serializable{

    /** serialVersionUID **/
    private static final long serialVersionUID = 1304047025603006338L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_OPENPLAN_RELATION")
    @SequenceGenerator(name = "SEQ_DEV_OPENPLAN_RELATION", sequenceName = "SEQ_DEV_OPENPLAN_RELATION")
    @Column(name = "ID")
	private long id;
	
    @Column(name = "DEV_ID")
	private long deviceId;
    
    
    @Column(name = "PLAN_ID")
	private long openPlanId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public long getOpenPlanId() {
        return openPlanId;
    }

    public void setOpenPlanId(long openPlanId) {
        this.openPlanId = openPlanId;
    }
    
    /**
     * 根据指定的开机方案和设备创建关系实体
     * 
     * @param master
     * @param role
     * @return
     */
    public static DevicePlanRelation make(long openPlanId, long deviceId) {
        DevicePlanRelation obj = new DevicePlanRelation();
        obj.openPlanId = openPlanId;
        obj.deviceId = deviceId;
        return obj;
    }

}
