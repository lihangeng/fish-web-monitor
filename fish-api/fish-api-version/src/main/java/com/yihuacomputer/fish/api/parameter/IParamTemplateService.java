package com.yihuacomputer.fish.api.parameter;

import java.util.List;
import java.util.Map;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.device.IDevice;


/**
 * @author panxin
 *
 */
public interface IParamTemplateService {
	public IParamTemplate make();

	public IParamTemplate get(long id);

	public IParamTemplate get(String name);

	public IParamTemplate add(IParamTemplate template);

	public void update(IParamTemplate template);

	public void remove(long id);

	public IPageResult<IParamTemplate> page(int offset,int limit,IFilter filter);

	/**
	 * 获取未绑定设备
	 * @param offset
	 * @param limit
	 * @param template
	 * @param filter
	 * @return
	 */
	public IPageResult<IDevice> pageUnlinkedDevice(int offset,int limit, IParamTemplate template,long orgId, IFilter filter);


	/**
	 * 获取已绑定设备
	 * @param offset
	 * @param limit
	 * @param template
	 * @param filter
	 * @return
	 */
	public IPageResult<IDevice> pageLinkedDevice(int offset, int limit, IParamTemplate template,long orgId, IFilter filter);

	/**
	 * 获取所有模板关联的设备
	 * @param template
	 * @return
	 */
	public List<IDevice> listDeviceByTemplate(IParamTemplate template);

	/**
	 * 关联设备和模板
	 * @param template
	 * @param device
	 * @param timeStamp
	 */
	public void link(IParamTemplate template, IDevice device,long timeStamp);

	/**
	 * 解绑设备和模板
	 * @param template
	 * @param device
	 */
	public void unlink(IParamTemplate template, IDevice device);

	/**
	 * 解绑所有模板元数据关联,并删除模板详情
	 * @param templateId
	 */
	public void unlinkAll(long templateId);

	/**
	 * 获取模板所有关联元数据
	 * @param templateId
	 * @param flag 1:新增时查询  0:更改时查询
	 * @return
	 */
	public List<IParamElement> listParam(long templateId, long flag,long appSystem) ;

    /**
     *
     * @param templateId
     * @param flag
     * @return
     */
	public List<IParamElement> listParam2(long templateId, long flag) ;

	/**
	 * 解绑元数据与模板关联
	 * @param template
	 * @param emlement
	 */
	public void unlinkTempParam(IParamTemplate  template, IParamElement emlement);

	/**
	 * 关联模板元数据
	 * @param template
	 * @param emlement
	 * @param paramValue
	 */
	public void linkTempParam(IParamTemplate template, IParamElement emlement,String paramValue);

	/**
	 * 根据模板id获取模板详情
	 * @param id
	 * @return
	 */
	public List<IParamTemplateDetail> listTemplateDetail(long id);

	/**
	 * 更新模板
	 * @param templateId
	 * @param newMap 从页面获取到的模板详情
	 * @return
	 */
	public boolean updateTemplateDetail(long templateId , Map<Long,String> newMap);


	/**
	 * 根据模板id、元数据id获取模板详情
	 * @param templateId
	 * @param elementId
	 * @return
	 */
	public IParamTemplateDetail getDetailByTemplateId(long templateId ,long elementId);

	/**
	 * 模板名称重复判断
	 * @param templateName
	 * @return
	 */
	public boolean duplicateTemplateName(String templateName);


	/**
	 * 删除模板之前删除所有模板关联关系
	 * @param templateId
	 */
	public void unlinkAllBeforeDelete(long templateId);


	public void issueTemplate(IParamTemplate template ,long timeStamp);

	/**
	 * 根据设备号获取关联的模板中的元数据
	 * @param deviceId
	 * @return
	 */
	List<IParamTemplateDetail> getParamTemplateDetailListByDeviceId(long deviceId);

}
