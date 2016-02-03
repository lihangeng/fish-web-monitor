package com.yihuacomputer.fish.web.bsadvert.form;

import java.util.List;

/**
 * @author GQ
 *加载更改页面的广告资源时候使用
 */
public class BsAdvertScreenForm {
	private String id;
	private String type;
	private List<BsUploadResourceForm> resources;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<BsUploadResourceForm> getResources() {
		return resources;
	}
	public void setResources(List<BsUploadResourceForm> resources) {
		this.resources = resources;
	}
	
}
