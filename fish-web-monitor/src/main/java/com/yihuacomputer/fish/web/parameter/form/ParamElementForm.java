package com.yihuacomputer.fish.web.parameter.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.parameter.IParamElement;

/**
 * @author YiHua
 *
 */
public class ParamElementForm {
	private long id;
	private String paramName;
	private String paramValue;
	private String paramType;
	private long   classifyId;
	private String paramClassify;
	private String paramRights;
	private long paramBelongsId;
	private String paramBelongsName;
	private String remark;
	private String createTime;
	private String lastModifyTime;
	private long  versionNo;

	public ParamElementForm(){

	}

	/**
	 * @param element
	 */
	public ParamElementForm(IParamElement element){
		setId(element.getId());
		setParamName(element.getParamName());
		setParamValue(element.getParamValue());
		setParamType(element.getParamType());
		if(element.getParamClassify()!=null){
		setClassifyId(element.getParamClassify().getId());
		setParamClassify(element.getParamClassify().getName());
		}
		setParamRights(element.getParamRights());
		if(element.getParamBelongs()!=null){
		setParamBelongsId(element.getParamBelongs().getId());
		setParamBelongsName(element.getParamBelongs().getName());
		}
		setRemark(element.getRemark());
		setCreateTime(nullString(element.getCreateTime()));
		setLastModifyTime(nullString(element.getLastModifyTime()));
		setVersionNo(element.getParamTimestamp());

	}


	/**
	 * @param list
	 * @return
	 */
	public static List<ParamElementForm> convert(List<IParamElement> list) {
		List<ParamElementForm> result=new ArrayList<ParamElementForm>();
		for(IParamElement item:list){
			result.add(new ParamElementForm(item));
		}
		return result;
	}

	/**
	 * @param string
	 * @return
	 */
	public Date nullDate(String string) {
		if (string == null || "".equals(string)) {
			return null;
		}
		String str="2014-00-00 00:00:00";
		if(string.contains("T")){
			str=string.replace("T", " ");
		}
		return DateUtils.getTimestamp(str);
	}

	private String nullString(Date date) {
		if (date == null) {
			return null;
		}
		return DateUtils.getTimestamp(date);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public long getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(long classifyId) {
		this.classifyId = classifyId;
	}


	public String getParamClassify() {
		return paramClassify;
	}

	public void setParamClassify(String paramClassify) {
		this.paramClassify = paramClassify;
	}

	public String getParamRights() {
		return paramRights;
	}

	public void setParamRights(String paramRights) {
		this.paramRights = paramRights;
	}

	public long getParamBelongsId() {
		return paramBelongsId;
	}

	public void setParamBelongsId(long paramBelongsId) {
		this.paramBelongsId = paramBelongsId;
	}

	public String getParamBelongsName(){
		return paramBelongsName;
	}

	public void setParamBelongsName(String paramBelongsName){
		this.paramBelongsName = paramBelongsName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(long versionNo) {
		this.versionNo = versionNo;
	}





}
