/**
 * 
 */
package com.yihuacomputer.fish.web.version.form;

/**
 * 
 * 主要用于下拉列表的数据封装格式
 * 
 * @author xuxigang
 * 
 */
public class BaseForm {
	private long id;
	private String name;

	public BaseForm(){}
	
	public BaseForm(long id, String name){
		this.id = id;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
