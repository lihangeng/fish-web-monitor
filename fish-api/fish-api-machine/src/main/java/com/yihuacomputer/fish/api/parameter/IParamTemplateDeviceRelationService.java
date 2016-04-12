package com.yihuacomputer.fish.api.parameter;

import java.util.List;

public interface IParamTemplateDeviceRelationService {
	IParamTemplateDeviceRelation getById(long id);
	List<IParamTemplateDeviceRelation> getByDeviceId(long deviceId);
}
