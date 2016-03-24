package com.yihuacomputer.fish.api.parameter;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.device.IDevice;


public interface ITemplateService {
	public ITemplate make();

	public ITemplate get(long id);

	public ITemplate get(String name);

	public ITemplate add(ITemplate template);

	public void remove(long id);

	public void update(ITemplate template);

//	public Iterable<IElement> list();
//
	public IPageResult<ITemplate> page(int offset,int limit,IFilter filter);
//
//	public Iterable<IElement> list(IFilter filter);
	
	public IPageResult<IDevice> pageUnlinkedDevice(int offset,int limit, ITemplate template, IFilter filter);
	
	public IPageResult<IDevice> pageLinkedDevice(int offset, int limit, ITemplate template, IFilter filter);
	
	public List<IDevice> listDeviceByTemplate(ITemplate template);
	
	
	public void link(ITemplate template, IDevice device);
	
	
	public void unlink(ITemplate template, IDevice device);
	
			
			 
	
	

}
