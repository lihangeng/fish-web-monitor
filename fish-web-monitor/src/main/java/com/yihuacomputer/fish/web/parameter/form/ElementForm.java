package com.yihuacomputer.fish.web.parameter.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yihuacomputer.fish.api.parameter.IElement;

public class ElementForm {
	private long id;
	private String paramName;
	private String paramValue;
	private String paramType;
	private String paramClassify;
	private String versionNo;
	private String paramRights;
	private String paramBelongs;
	private String remark;
	private Date createTime;
	private Date lastModifyTime;

	public ElementForm(){

	}

	public ElementForm(IElement element){
		setId(element.getId());
		setName(element.getName());
		setValue(element.getValue());
		setType(element.getType());
		setClassify(element.getClassify());
		setVersionNo(element.getVersionNo());
		setParamRights(element.getRights());
		setParamBelongs(element.getBelongs());
		setRemark(element.getRemark());
		setCreateTime(element.getCreateTime());
		setLastModifyTime(element.getLastModifyTime());
	}

	public void translate(IElement element){
		element.setId(getId());
		element.setName(getName());
		element.setValue(getValue());
		element.setType(getType());
		element.setClassify(getClassify());
		element.setVersionNo(getVersionNo());
		element.setRights(getParamRights());
		element.setBelongs(getParamBelongs());
		element.setRemark(getRemark());
		element.setCreateTime(getCreateTime());
		element.setLastModifyTime(getLastModifyTime());
	}

	public static List<ElementForm> convert(List<IElement> list) {
		List<ElementForm> result=new ArrayList<ElementForm>();
		for(IElement item:list){
			result.add(new ElementForm(item));
		}
		return result;
	}

	public long getId(){
		return id;
	}

	public void setId(long id){
		this.id=id;
	}

	public String getName(){
		return paramName;
	}

	public void setName(String paramName){
		this.paramName=paramName;
	}

	public String getValue(){
		return paramValue;
	}

	public void setValue(String paramValue){
		this.paramValue=paramValue;
	}

	public String getType(){
		return paramType;
	}

	public void setType(String paramType){
		this.paramType=paramType;
	}

	public String getClassify(){
		return paramClassify;
	}

	public void setClassify(String paramClassify){
		this.paramClassify=paramClassify;
	}

	public String getVersionNo(){
		return versionNo;
	}

	public void setVersionNo(String versionNo){
		this.versionNo=versionNo;
	}

	public String getParamRights(){
		return paramRights;
	}

	public void setParamRights(String paramRights){
		this.paramRights=paramRights;
	}

	public String getParamBelongs(){
		return paramBelongs;
	}

	public void setParamBelongs(String paramBelongs){
		this.paramBelongs=paramBelongs;
	}

	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public Date getCreateTime(){
		return createTime;
	}

	public void setCreateTime(Date createTime){
		this.createTime =createTime;
	}

	public Date getLastModifyTime(){
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime){
		this.lastModifyTime=lastModifyTime;
	}




}
