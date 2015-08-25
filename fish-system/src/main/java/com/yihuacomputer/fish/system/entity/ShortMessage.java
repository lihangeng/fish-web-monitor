package com.yihuacomputer.fish.system.entity;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.system.sms.IShortMessage;
import com.yihuacomputer.fish.system.service.api.IDomainShortMessageService;

public class ShortMessage implements IShortMessage {

	private List<String> mobiles;

	private String content;
	
	private IDomainShortMessageService service;

	public ShortMessage(){
		mobiles = new ArrayList<String>();
	}
	
	public ShortMessage(IDomainShortMessageService service) {
		this.service = service;
		mobiles = new ArrayList<String>();
	}

	@Override
	public List<String> listMobile() {
		return mobiles;
	}

	@Override
	public void addMobile(String mobile) {
		mobiles.add(mobile);
	}

	@Override
	public void addMobile(List<String> mobiles) {
		mobiles.addAll(mobiles);
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public void setContent(String content) {
		this.content = content;
	}

	public IDomainShortMessageService getService() {
		return service;
	}

	public void setService(IDomainShortMessageService service) {
		this.service = service;
	}
	
	@Override
	public String getSendCode() {
		// TODO Auto-generated method stub
		return null;
	}
}
