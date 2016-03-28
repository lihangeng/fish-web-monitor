package com.yihuacomputer.fish.parameter.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.yihuacomputer.fish.api.parameter.IParamClassify;
import com.yihuacomputer.fish.api.parameter.IParamElement;

/**
 * 参数元数据
 *
 * @author YH
 *
 */
@Entity
@Table(name="PARAM_ELEMENT")
public class ParamElement implements IParamElement, Serializable {


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

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = ParamClassify.class)
	@JoinColumn(name="PARAM_CLASSIFY")
	private IParamClassify paramClassify;

	@Column(name=" VERSION_NO",length=60)
	private String versionNo;

	@Column(name="PARAM_RIGHTS",length=10)
	private String paramRights;

	@Column(name="PARAM_BELONGS",length=40)
	private String paramBelongs;

	@Column(name="REMARK",length=60)
	private String remark;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	private Date createTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_MODIFY_TIME")
	private Date lastModifyTime;

	public ParamElement(){

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
	public String getParamName()
	{
		return paramName;
	}

	@Override
	public void setParamName(String paramName)
	{
		this.paramName=paramName;
	}

	@Override
	public String getParamValue()
	{
		return paramValue;
	}

	@Override
	public void setParamValue(String paramValue)
	{
       this.paramValue=paramValue;
	}

	@Override
	public String getParamType()
	{
		return paramType;
	}

	@Override
	public void setParamType(String paramType)
	{
        this.paramType=paramType;
	}

	@Override
	public IParamClassify getParamClassify()
	{
		return paramClassify;
	}

	@Override
	public void setParamClassify(IParamClassify paramClassify)
	{
        this.paramClassify=paramClassify;
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
	public String getParamRights()
	{
		return paramRights;
	}

	@Override
	public void setParamRights(String paramRights)
	{
       this.paramRights=paramRights;
	}

	@Override
	public String getParamBelongs()
	{
		return paramBelongs;
	}

	@Override
	public void setParamBelongs(String paramBelongs)
	{
        this.paramBelongs=paramBelongs;
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
