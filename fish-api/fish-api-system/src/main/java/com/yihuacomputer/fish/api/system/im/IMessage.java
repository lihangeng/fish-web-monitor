package com.yihuacomputer.fish.api.system.im;

import java.util.Date;

public interface IMessage {

	public long getId();
	
	public void setId(long id);
	
	public Date getCreateTime();
	
	public void setCreateTime(Date createTime);
	
	public String getUser();
	
	public void setUser(String user);
	
	public String getContent();
	
	public void setContent(String content);
	
}
