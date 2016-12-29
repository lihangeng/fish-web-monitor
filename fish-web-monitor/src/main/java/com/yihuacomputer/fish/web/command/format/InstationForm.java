package com.yihuacomputer.fish.web.command.format;

import java.util.ArrayList;
import java.util.List;
/**
 * 软件安装信息
 * @author YiHua
 *
 */
public class InstationForm {
	/**
	 * 软件名称
	 */
	private String programName;
	/**
	 * 出版商
	 */
	private String publisher;
	/**
	 * 安装日期
	 */
	private String installDate;
	/**
	 * 占用空间
	 */
	private long diskUsed;
	/** 
	 * 软件版本
	 */
	private String version;

	private String url;

	private String cmdKey;

	private String appRet;

	private String httpStatusCode;

	public InstationForm() {

	}

	/**
	 * @param instationForm
	 */
	public InstationForm(InstationForm instationForm) {
		setProgramName(instationForm.getProgramName());
		setPublisher(instationForm.getPublisher());
		setInstallDate(instationForm.getInstallDate());
		setDiskUsed(instationForm.getDiskUsed());
		setVersion(instationForm.getVersion());
		setAppRet(instationForm.getAppRet());
		setUrl(instationForm.getUrl());
		setCmdKey(instationForm.getCmdKey());
		setHttpStatusCode(instationForm.getHttpStatusCode());
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCmdKey() {
		return this.cmdKey;
	}

	public void setCmdKey(String cmdKey) {
		this.cmdKey = cmdKey;
	}

	public String getAppRet() {
		return this.appRet;
	}

	public void setAppRet(String appRet) {
		this.appRet = appRet;
	}

	public String getHttpStatusCode() {
		return this.httpStatusCode;
	}

	public void setHttpStatusCode(String httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getProgramName() {
		return this.programName;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public long getDiskUsed() {
		return this.diskUsed;
	}

	public String getVersion() {
		return this.version;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}

	public void setDiskUsed(long diskUsed) {
		this.diskUsed = diskUsed;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getInstallDate() {
		return this.installDate;
	}

	/**
	 * @param list
	 * @return
	 */
	public static List<InstationForm> convert(List<InstationForm> list) {
		List<InstationForm> result = new ArrayList<InstationForm>();
		for (InstationForm item : list) {
			result.add(new InstationForm(item));
		}
		return result;
	}

}
