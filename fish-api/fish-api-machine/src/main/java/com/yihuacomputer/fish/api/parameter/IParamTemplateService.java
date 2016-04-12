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
	
	public void insertNewParam(IParamTemplate template , long timeStamp);

	public void remove(long id);

	public IPageResult<IParamTemplate> page(int offset,int limit,IFilter filter);
	
	public IPageResult<IDevice> pageUnlinkedDevice(int offset,int limit, IParamTemplate template, IFilter filter);
	
	public IPageResult<IDevice> pageLinkedDevice(int offset, int limit, IParamTemplate template, IFilter filter);
	
	public List<IDevice> listDeviceByTemplate(IParamTemplate template);
	
	public void link(IParamTemplate template, IDevice device,long timeStamp);
	
	public void unlink(IParamTemplate template, IDevice device);
	
	public void unlinkAll(long templateId);
	
	public List<IParamElement> listParam(long templateId, long flag) ;
	
	public void unlinkTempParam(IParamTemplate  template, IParamElement emlement);
	
	public void linkTempParam(IParamTemplate template, IParamElement emlement,String paramValue);
	
	public List<IParamTemplateDetail> listTemplateDetail(long id);
	
	public boolean updateTemplateDetail(long templateId , Map<Long,String> newMap);
	
	public void updateTempDev(long timeStamp , long templateId);
	
	public void removeTempDev(long templateId);
	
	public IParamTemplateDetail getDetailByTemplateId(long templateId ,long elementId);
	
}
