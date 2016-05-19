package com.yihuacomputer.fish.parameter.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.parameter.FileFormat;
import com.yihuacomputer.fish.api.parameter.IAppSystem;


@Entity
@Table(name="PARAM_APP_SYSTEM")
public class AppSystem implements IAppSystem,Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6671561112698741806L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="SEQ_PARAM_APP_SYSTEM")
	@SequenceGenerator(name="SEQ_PARAM_APP_SYSTEM",sequenceName="SEQ_PARAM_APP_SYSTEM")
	@Column(name="ID",length=20)
	private long id;
	/**
	 * 应用系统名称
	 */
	@Column(name="NAME",length=40)
	private String name;
	/**
	 * 配置文件名称
	 * @param configName
	 */
	@Column(name="CONFIG_NAME",length=40)
	private String configName;
	/**
	 * 配置文件路径
	 */
	@Column(name="CONFIG_PATH",length=80)
	private String configPath;
	/**
	 * 配置文件格式
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name="CONFIG_FORM",length=10)
	private FileFormat configForm;
	/**
	 * 备注
	 */
	@Column(name="REMARK",length=40)
	private String remark;
	
	
	public AppSystem(){
		
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
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getConfigName() {
		return configName;
	}
	@Override
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	@Override
	public String getConfigPath() {
		return configPath;
	}
	@Override
	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}
	@Override
	public FileFormat getConfigForm() {
		return this.configForm;
	}
	@Override
	public void setConfigForm(FileFormat configForm) {
		this.configForm = configForm;
	}
	@Override
	public String getRemark() {
		return remark;
	}
	@Override
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
