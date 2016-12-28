package com.yihuacomputer.fish.system.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.system.sms.IShortMessage;
import com.yihuacomputer.fish.api.system.sms.IShortMessageService;

/**
 * @author YiHua
 *
 */
public class ShortMessage implements IShortMessage,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8997032317930250485L;

	private List<String> mobiles = new ArrayList<String>();

	private String content;
	
	private IShortMessageService service;

	/**
	 * 初始化
	 */
	public ShortMessage(){
		mobiles = new ArrayList<String>();
	}
	
	/**
	 * @param service
	 */
	public ShortMessage(IShortMessageService service) {
		this.service = service;
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
		this.mobiles.addAll(mobiles);
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public void setContent(String content) {
		this.content = content;
	}

	public IShortMessageService getService() {
		return service;
	}

	public void setService(IShortMessageService service) {
		this.service = service;
	}
	
	@Override
	public String getSendCode() {
		// TODO Auto-generated method stub
		return null;
	}
}
