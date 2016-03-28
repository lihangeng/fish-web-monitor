package com.yihuacomputer.fish.api.parameter;

import java.util.List;

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

//	public Iterable<IElement> list();
//
	public IPageResult<IParamTemplate> page(int offset,int limit,IFilter filter);
//
//	public Iterable<IElement> list(IFilter filter);
	
	public IPageResult<IDevice> pageUnlinkedDevice(int offset,int limit, IParamTemplate template, IFilter filter);
	
	public IPageResult<IDevice> pageLinkedDevice(int offset, int limit, IParamTemplate template, IFilter filter);
	
	public List<IDevice> listDeviceByTemplate(IParamTemplate template);
	
	
	public void link(IParamTemplate template, IDevice device);
	
	
	public void unlink(IParamTemplate template, IDevice device);
	
			
			 
	
	

}
