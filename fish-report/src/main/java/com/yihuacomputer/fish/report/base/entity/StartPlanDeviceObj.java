package com.yihuacomputer.fish.report.base.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * 开机方案设备关联关系实体
 * @author huxiaobao
 *
 */
@Entity
@Table(name = "DEV_PLAN_RELATION")
public class StartPlanDeviceObj implements Serializable{
	
	/** serialVersionUID **/
    private static final long serialVersionUID = 1304047025603006338L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_PLAN_RELATION")
    @SequenceGenerator(name = "SEQ_DEV_PLAN_RELATION", sequenceName = "SEQ_DEV_PLAN_RELATION")
    @Column(name = "DEV_PLAN_RELATION_ID")
    private Long id;

    @Column(name = "PLAN_ID")
    private Long planId;

    @Column(name = "DEV_ID")
    private Long deviceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public Long getPlanId()
    {
        return planId;
    }

    public void setPlanId(Long planId)
    {
        this.planId = planId;
    }

    public Long getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(Long deviceId)
    {
        this.deviceId = deviceId;
    }

    /**
     * 根据指定的开机方案和设备创建关系实体
     * 
     * @param master
     * @param role
     * @return
     */
    public static StartPlanDeviceObj make(Long planId, Long deviceId) {
    	StartPlanDeviceObj obj = new StartPlanDeviceObj();
        obj.planId = planId;
        obj.deviceId = deviceId;
        return obj;
    }
}
