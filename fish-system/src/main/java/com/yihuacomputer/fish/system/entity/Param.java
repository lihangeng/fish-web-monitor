package com.yihuacomputer.fish.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.system.config.IParam;

/**
 * @author YiHua
 *
 */
@Entity
@Table(name = "SM_PARAM")
public class Param implements IParam,Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5074691289583960484L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SM_PARAM")
    @SequenceGenerator(name = "SEQ_SM_PARAM", sequenceName = "SEQ_SM_PARAM")
    @Column(name = "ID")
	private long id;
    /**
     * 参数key
     */
    @Column(name = "PARAM_KEY",length = 20)
	private String paramKey;
    /**
     * 参数值
     */
    @Column(name = "PARAM_VALUE",length = 128)
	private String paramValue;
    /**
     * 参数展示值
     */
    @Column(name = "PARAM_DIS_VALUE",length = 128)
	private String paramDisplayValue;
    /**
     * 参数类型:0-不可修改，1-可以修改
     */ 
    @Column(name = "PARAM_CLASSIFY")
    private int classify;
    /**
     * 参数信息描述
     */
    @Column(name = "DESCIPTION",length = 60)
	private String description;
    
    /**
     * 参数类型
     */ 
    @Column(name = "PARAM_TYPE",length = 128)
	private String paramType;
	
    @Override
	public String getParamType() {
		return paramType;
	}

    @Override
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public Param() {
	}
	
	@Override
	public int getClassify() {
		return classify;
	}

	@Override
	public void setClassify(int classify) {
		this.classify = classify;
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
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	@Override
	public String getParamKey() {
		return paramKey;
	}

	@Override
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	@Override
	public String getParamValue() {
		return paramValue;
	}

	@Override
	public String getParamDisplayValue() {
		return this.paramDisplayValue;
	}

	@Override
	public void setParamDisplayValue(String paramDisplayValue) {
		this.paramDisplayValue = paramDisplayValue;
	}

}
