package com.yihuacomputer.fish.api.parameter;

import java.util.List;

import com.yihuacomputer.fish.api.device.IDevice;


public interface IParamTemplateDeviceRelationService {
	IParamTemplateDeviceRelation getById(long id);
	List<IParamTemplateDeviceRelation> getByDeviceId(long deviceId);

	
	/**
	 * 通过模板号获取相关联的设备列表
	 * @param templateId
	 * @return
	 */
	List<IDevice> listDeviceByTemplate(long templateId);
}
