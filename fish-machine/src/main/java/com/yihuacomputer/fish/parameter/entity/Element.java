package com.yihuacomputer.fish.parameter.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.yihuacomputer.fish.api.parameter.IElement;

/**
 * 参数元数据
 *
 * @author YH
 *
 */
@Entity
@Table(name="PARAM_ELEMENT")
public class Element implements IElement, Serializable {


	public static final long serialVersionUID=1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_PARAM_ELEMENT")
    @SequenceGenerator(name = "SEQ_PARAM_ELEMENT", sequenceName = "SEQ_PARAM_ELEMENT")
    @Column(name = "ID")
    private long id;

	@Column(name="PARAM_NAME",length=40)
	private String paramName;

	@Column(name="PARAM_VALUE",length=40)
	private String paramValue;

	@Column(name="PARAM_TYPE",length=10)
	private String paramType;

	@Column(name="PARAM_CLASSIFY",length=10)
	private String paramClassify;

	@Column(name=" VERSION_NO",length=60)
	private String versionNo;

	@Column(name="PARAM_RIGHTS",length=10)
	private String paramRights;

	@Column(name="PARAM_BELONGS",length=40)
	private String paramBelongs;

	@Column(name="REMARK",length=60)
	private String remark;

	@Temporal(TemporalType.TIME)
	@Column(name="CREATE_TIME")
	private Date createTime;

	@Temporal(TemporalType.TIME)
	@Column(name="LAST_MODIFY_TIME")
	private Date lastModifyTime;

	public Element(){

	}

	@Override
	public long getId()
	{
		return id;
	}

	@Override
	public void setId(long id)
	{
		this.id=id;
	}

	@Override
	public String getName()
	{
		return paramName;
	}

	@Override
	public void setName(String name)
	{
		this.paramName=name;
	}

	@Override
	public String getValue()
	{
		return paramValue;
	}

	@Override
	public void setValue(String value)
	{
       this.paramValue=value;
	}

	@Override
	public String getType()
	{
		return paramType;
	}

	@Override
	public void setType(String type)
	{
        this.paramType=type;
	}

	@Override
	public String getClassify()
	{
		return paramClassify;
	}

	@Override
	public void setClassify(String classify)
	{
        this.paramClassify=classify;
	}

	@Override
	public String getVersionNo()
	{
		return versionNo;
	}

	@Override
	public void setVersionNo(String versionNo)
	{
       this.versionNo=versionNo;
	}

	@Override
	public String getRights()
	{
		return paramRights;
	}

	@Override
	public void setRights(String rights)
	{
       this.paramRights=rights;
	}

	@Override
	public String getBelongs()
	{
		return paramBelongs;
	}

	@Override
	public void setBelongs(String belongs)
	{
        this.paramBelongs=belongs;
	}

	@Override
	public Date getCreateTime()
	{
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime)
    {
        this.createTime=createTime;
	}

	@Override
	public Date getLastModifyTime()
	{
		return lastModifyTime;
	}

	@Override
	public void setLastModifyTime(Date lastModifyTime)
	{
        this.lastModifyTime=lastModifyTime;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
        this.remark=remark;
	}

}
