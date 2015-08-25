package com.yihuacomputer.fish.web.command.format;

import java.util.ArrayList;
import java.util.List;

/**
 * 远程网络连接检查.
 * 
 * @author wangchao
 * 
 */
public class NetWorkForm {
	/**
	 * 网络连接速度
	 */
	private long conenctRate;
	/**
	 * 实际传输速度
	 */
	private long linkedRate;
	/**
	 * 已经接收字节
	 */
	private long receivedByte;
	/**
	 * 已经发送字节
	 */
	private long sendByte;
	/**
	 * 持续时间
	 */
	private long continued;

	private String url;

	private String cmdKey;

	private String appRet;

	private String httpStatusCode;

	public NetWorkForm() {

	}

	public NetWorkForm(NetWorkForm netWorkForm) {
		setConenctRate(netWorkForm.getConenctRate());
		setLinkedRate(netWorkForm.getLinkedRate());
		setReceivedByte(netWorkForm.getReceivedByte());
		setSendByte(netWorkForm.getSendByte());
		setContinued(netWorkForm.getContinued());
		setAppRet(netWorkForm.getAppRet());
		setUrl(netWorkForm.getUrl());
		setCmdKey(netWorkForm.getCmdKey());
		setHttpStatusCode(netWorkForm.getHttpStatusCode());
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCmdKey() {
		return cmdKey;
	}

	public void setCmdKey(String cmdKey) {
		this.cmdKey = cmdKey;
	}

	public String getAppRet() {
		return appRet;
	}

	public void setAppRet(String appRet) {
		this.appRet = appRet;
	}

	public String getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(String httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public long getConenctRate() {
		return conenctRate;
	}

	public void setConenctRate(long conenctRate) {
		this.conenctRate = conenctRate;
	}

	public long getLinkedRate() {
		return linkedRate;
	}

	public void setLinkedRate(long linkedRate) {
		this.linkedRate = linkedRate;
	}

	public long getReceivedByte() {
		return receivedByte;
	}

	public void setReceivedByte(long receivedByte) {
		this.receivedByte = receivedByte;
	}

	public long getSendByte() {
		return sendByte;
	}

	public void setSendByte(long sendByte) {
		this.sendByte = sendByte;
	}

	public long getContinued() {
		return continued;
	}

	public void setContinued(long continued) {
		this.continued = continued;
	}

	public static List<NetWorkForm> convert(List<NetWorkForm> list) {
		List<NetWorkForm> result = new ArrayList<NetWorkForm>();
		for (NetWorkForm item : list) {
			result.add(new NetWorkForm(item));
		}
		return result;
	}
}
