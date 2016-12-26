package com.yihuacomputer.fish.api.parameter;

import java.util.List;

import com.yihuacomputer.fish.api.version.job.JobType;


/**
 * @author YiHua
 *
 */
public interface IParamPublish {
	/**
	 * @return
	 */
	public long getId();
	/**
	 * @param id
	 */
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
	
	/**
	 * @return
	 */
	public String getRet();
	/**
	 * @param ret
	 */
	public void setRet(String ret);
	/**
	 * @return
	 */
	public List<IParamPublishResult> getParamPublishs();
	/**
	 * @param paramPublishs
	 */
	public void setParamPublishs(List<IParamPublishResult> paramPublishs);
	/**
	 * @return
	 */
	public JobType getJobType();
	/**
	 * @param jobType
	 */
	public void setJobType(JobType jobType);
	/**
	 * @param tempateId
	 */
	public void setTemplateId(long tempateId);
	/**
	 * @return
	 */
	public long getTemplateId();
}
