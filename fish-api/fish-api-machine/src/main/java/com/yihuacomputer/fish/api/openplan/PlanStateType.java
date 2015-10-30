package com.yihuacomputer.fish.api.openplan;

/**
 * 方案状态
 * 
 * @author YiHua
 * 
 */
public enum PlanStateType {
	Enabled("1", "正常"),
	NotEnabled("2", "未启用"),
	Expired("3", "过期"),
	WExpired("4", "即将过期"),
	Stoped("5", "停用");

	private String id;

	private String text;

	private PlanStateType(String id, String text) {
		this.id = id;
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
