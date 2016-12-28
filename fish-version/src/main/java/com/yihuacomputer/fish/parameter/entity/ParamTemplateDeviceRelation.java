package com.yihuacomputer.fish.parameter.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.parameter.IParamTemplateDeviceRelation;


/**
 * @author YiHua
 *
 */
@Entity
@Table(name="PARAM_TEMPLATE_DEVICE_RELATION")
public class ParamTemplateDeviceRelation implements IParamTemplateDeviceRelation,Serializable {


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
	
	
	public ParamTemplateDeviceRelation(){
		
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
     * @param templateId
     * @param deviceId
     * @return
     */
    public static ParamTemplateDeviceRelation make(Long templateId, Long deviceId) {
    	ParamTemplateDeviceRelation obj = new ParamTemplateDeviceRelation();
        obj.templateId = templateId;
        obj.deviceId = deviceId;
        return obj;
    }
	
}
