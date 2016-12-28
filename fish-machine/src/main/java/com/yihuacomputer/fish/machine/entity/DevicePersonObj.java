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
@Table(name = "DEV_PERSON")
public class DevicePersonObj implements Serializable {

    /** serialVersionUID **/
    private static final long serialVersionUID = 1304047025603006338L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_PERSON")
    @SequenceGenerator(name = "SEQ_DEV_PERSON", sequenceName = "SEQ_DEV_PERSON")
    @Column(name = "DEV_PERSON_ID")
    private Long id;

    @Column(name = "PERSON_ID")
    private Long personId;

    @Column(name = "DEV_ID")
    private Long deviceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public Long getPersonId()
    {
        return personId;
    }

    public void setPersonId(Long personId)
    {
        this.personId = personId;
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
     * 根据指定的人员和设备创建关系实体
     * 
     * @param personId
     * @param deviceId
     * @return
     */
    public static DevicePersonObj make(Long personId, Long deviceId) {
        DevicePersonObj obj = new DevicePersonObj();
        obj.personId = personId;
        obj.deviceId = deviceId;
        return obj;
    }
}