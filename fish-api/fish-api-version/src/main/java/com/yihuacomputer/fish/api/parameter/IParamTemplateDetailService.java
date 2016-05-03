package com.yihuacomputer.fish.api.parameter;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IParamTemplateDetailService {
	IParamTemplateDetail get(long id);
	IPageResult<IParamTemplateDetail> page(int start,int limit,IFilter filter);
	IPageResult<IParamTemplateDetail> getByDeviceId(int start,int limit,IFilter filter,long deviceId);
	List<IParamTemplateDetail> list(long deviceId);
	List<IParamTemplateDetail> listByTempateId(long templateId);

}
