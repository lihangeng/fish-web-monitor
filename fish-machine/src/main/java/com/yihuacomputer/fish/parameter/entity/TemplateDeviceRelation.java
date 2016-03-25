package com.yihuacomputer.fish.parameter.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.parameter.ITemplateDeviceRelation;


@Entity
@Table(name="PARAM_TEMPLATE_DEVICE_RELATION")
public class TemplateDeviceRelation implements ITemplateDeviceRelation,Serializable {


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="SEQ_PAR_TEMPLATE_DEVICE_RELATION")
	@SequenceGenerator(name="SEQ_PAR_TEMPLATE_DEVICE_RELATION",sequenceName="SEQ_PAR_TEMPLATE_DEVICE_RELATION")
	
	@Column(name="ID",length=20)
	private long id;
	/**
	 * 模板ID
	 */
	@Column(name="TEMPLATE_ID",length=20)
	private long templateId;
	/**
	 * 设备ID
	 * @param configName
	 */
	@Column(name="DEVICE_ID",length=20)
	private long deviceId;
	
	
	public TemplateDeviceRelation(){
		
	}
	
	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}

	@Override
	public long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}
	
	
	  /**
     * 根据指定的模板和设备创建关系实体
     * 
     * @param master
     * @param role
     * @return
     */
    public static TemplateDeviceRelation make(Long templateId, Long deviceId) {
    	TemplateDeviceRelation obj = new TemplateDeviceRelation();
        obj.templateId = templateId;
        obj.deviceId = deviceId;
        return obj;
    }
	
}
