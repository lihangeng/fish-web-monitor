package com.yihuacomputer.fish.api.parameter;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IParamTemplateDetailService {
	IParamTemplateDetail get(long id);
	IPageResult<IParamTemplateDetail> page(int start,int limit,IFilter filter);
	IPageResult<IParamTemplateDetail> getByDeviceId(int start,int limit,IFilter filter,long deviceId);
	
}
