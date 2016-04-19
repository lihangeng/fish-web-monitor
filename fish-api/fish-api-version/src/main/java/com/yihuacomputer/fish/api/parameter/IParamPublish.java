package com.yihuacomputer.fish.api.parameter;

import java.util.List;


public interface IParamPublish {
	public long getId() ;
	public void setId(long id);
	
	/**
	 * 发布参数时间
	 * @return
	 */
	public String getDate();
	/**
	 * 发布参数时间
	 * @param date
	 */
	public void setDate(String date);
	
	/**
	 * 参数发布者(PERSONID)
	 * @return
	 */
	public long getPublisher();
	/**
	 * 参数发布者(PERSONID)
	 * @param publisher
	 */
	public void setPublisher(long publisher);
	
	public String getRet();
	public void setRet(String ret);
	public List<IParamPublishResult> getParamPublishs();
	public void setParamPublishs(List<IParamPublishResult> paramPublishs);
}
