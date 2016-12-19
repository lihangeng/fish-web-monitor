package com.yihuacomputer.fish.web.parameter.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.parameter.IParamTemplate;

public class ParamTemplateForm {
	
	private static Logger logger = LoggerFactory.getLogger(ParamTemplateForm.class);
	
	private long id;
	private String name;
	private String remark;
	private String applyFlag;
	private String resources;
	private String paramValue;
	private String paramName;
	private String elementId;

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public ParamTemplateForm() {

	}

	public ParamTemplateForm(IParamTemplate template) {
		setId(template.getId());
		setName(template.getName());
		setRemark(template.getRemark());
		setApplyFlag(template.getApplyFlag());
	}

	public static List<ParamTemplateForm> convert(List<IParamTemplate> list) {
		List<ParamTemplateForm> result = new ArrayList<ParamTemplateForm>();
		for (IParamTemplate item : list) {
			result.add(new ParamTemplateForm(item));
		}
		return result;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getApplyFlag() {
		return applyFlag;
	}

	public void setApplyFlag(String applyFlag) {
		this.applyFlag = applyFlag;
	}

	public String getResources() {
		return resources;
	}

	public void setResource(String resources) {
		this.resources = resources;
	}

	public List<ParamTemplateForm> getParamTemplateForm() {
		List<ParamTemplateForm> tempForm = new ArrayList<ParamTemplateForm>();
		if (StringUtils.isNotEmpty(this.resources)) {
			try {
				JsonUtils.om.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,
						true);
				tempForm = JsonUtils.om.readValue(this.resources,
						new TypeReference<List<ParamTemplateForm>>() {
						});
			} catch (Exception e) {
				logger.error(String.format("[%s]", e));
			}
		}
		return tempForm;
	}

}
