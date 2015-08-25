package com.yihuacomputer.fish.web.command.format;

/**
 * 远程浏览文件系统传参的实体类：
 * 
 * @author huxiaobao
 * 
 */
public class ExplorerParamForm {

	private String path;
	private int offset;
	private int limit;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
