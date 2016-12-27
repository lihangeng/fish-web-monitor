package com.yihuacomputer.fish.api.system.im;

import java.util.Date;

/**
 * @author YiHua
 *
 */
public interface IMessage {

	/**
	 * @return
	 */
	public long getId();
	
	/**
	 * @param id
	 */
	public void setId(long id);
	
	/**
	 * @return
	 */
	public Date getCreateTime();
	
	/**
	 * @param createTime
	 */
	public void setCreateTime(Date createTime);
	
	/**
	 * @return
	 */
	public String getUser();
	
	/**
	 * @param user
	 */
	public void setUser(String user);
	
	/**
	 * @return
	 */
	public String getContent();
	
	/**
	 * @param content
	 */
	public void setContent(String content);
	
}
