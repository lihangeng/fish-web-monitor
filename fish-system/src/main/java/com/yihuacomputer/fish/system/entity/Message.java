package com.yihuacomputer.fish.system.entity;

import java.util.Date;

import com.yihuacomputer.fish.api.system.im.IMessage;
import com.yihuacomputer.fish.system.service.api.IDomainMessageService;

public class Message implements IMessage {

	private IDomainMessageService service;
	
	private long id;
	private Date createTime;
	private String user;
	private String content;
	
	
	public Message(IDomainMessageService service){
		
		this.service = service;		
	}
	
	public IDomainMessageService getService() {
		return service;
	}

	public void setService(IDomainMessageService service) {
		this.service = service;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public long getId() {
		return this.id;
	}

	@Override
	public Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String getUser() {
		return this.user;
	}

	@Override
	public String getContent() {
		return this.content;
	}

}
