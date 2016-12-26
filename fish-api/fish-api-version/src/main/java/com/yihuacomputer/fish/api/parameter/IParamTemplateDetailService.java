package com.yihuacomputer.fish.api.parameter;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IParamTemplateDetailService {
	/**
	 * @param id
	 * @return
	 */
	IParamTemplateDetail get(long id);
	/**
	 * @param start
	 * @param limit
	 * @param filter
	 * @return
	 */
	IPageResult<IParamTemplateDetail> page(int start,int limit,IFilter filter);
	/**
	 * @param start
	 * @param limit
	 * @param filter
	 * @param deviceId
	 * @return
	 */
	IPageResult<IParamTemplateDetail> getByDeviceId(int start,int limit,IFilter filter,long deviceId);
	/**
	 * @param deviceId
	 * @return
	 */
	List<IParamTemplateDetail> list(long deviceId);
	/**
	 * @param templateId
	 * @return
	 */
	List<IParamTemplateDetail> listByTempateId(long templateId);
	/**
	 * @param templateId
	 * @return
	 */
	List<IParamTemplate> listParamByTemplate(long templateId);
}
