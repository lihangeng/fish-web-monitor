package com.yihuacomputer.fish.api.parameter;

import java.util.List;
import java.util.Map;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.device.IDevice;


public interface IParamTemplateService {
	public IParamTemplate make();

	public IParamTemplate get(long id);

	public IParamTemplate get(String name);

	public IParamTemplate add(IParamTemplate template);

	public void remove(long id);

	public void update(IParamTemplate template);

	public IPageResult<IParamTemplate> page(int offset,int limit,IFilter filter);
	
	public IPageResult<IDevice> pageUnlinkedDevice(int offset,int limit, IParamTemplate template, IFilter filter);
	
	public IPageResult<IDevice> pageLinkedDevice(int offset, int limit, IParamTemplate template, IFilter filter);
	
	public List<IDevice> listDeviceByTemplate(IParamTemplate template);
	
	
	public void link(IParamTemplate template, IDevice device);
	
	
	public void unlink(IParamTemplate template, IDevice device);
	

	public List<IParamElement> listParamByTemplate(long templateId) ;
	
	public void unlinkTempParam(IParamTemplate  template, IParamElement emlement);
	
	public void linkTempParam(IParamTemplate  template, IParamElement emlement);
	
	public List<IParamTemplateDetail> listTemplateDetail(long id);
	
	public boolean updateTemplateDetail(long templateId , Map<Long,String> newMap);
	
	public boolean coverDeviceParam(long templateId);
	

}
