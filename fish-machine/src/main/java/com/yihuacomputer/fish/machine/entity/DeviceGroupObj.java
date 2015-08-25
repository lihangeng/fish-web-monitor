package com.yihuacomputer.fish.machine.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * 人员设备关联关系实体（映射到数据库表DEV_PERSON）
 * @author huxiaobao
 *
 */
@Entity
@Table(name = "DEV_GROUP_RELATION")
public class DeviceGroupObj implements Serializable{
	
	/** serialVersionUID **/
    private static final long serialVersionUID = 1304047025603006338L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_GROUP_RELATION")
    @SequenceGenerator(name = "SEQ_DEV_GROUP_RELATION", sequenceName = "SEQ_DEV_GROUP_RELATION")
    @Column(name = "DEV_GROUP_RELATION_ID")
    private Long id;

    @Column(name = "GROUP_ID")
    private Long groupId;

    @Column(name = "DEV_ID")
    private Long deviceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public Long getGroupId()
    {
        return groupId;
    }

    public void setGroupId(Long groupId)
    {
        this.groupId = groupId;
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
     * 根据指定的分组和设备创建关系实体
     * 
     * @param master
     * @param role
     * @return
     */
    public static DeviceGroupObj make(Long groupId, Long deviceId) {
    	DeviceGroupObj obj = new DeviceGroupObj();
        obj.groupId = groupId;
        obj.deviceId = deviceId;
        return obj;
    }
}
