package com.yihuacomputer.fish.api.openplan;

/**
 * 方案状态
 * 
 * @author YiHua
 * 
 */
public enum PlanStateType {
	Enabled(1, "PlanStateType.Enabled"),
	NotEnabled(2, "PlanStateType.NotEnabled"),
	Expired(3, "PlanStateType.Expired"),
	WExpired(4, "PlanStateType.WExpired"),
	Stoped(5, "PlanStateType.Stoped");

	private int id;

	private String text;

	private PlanStateType(int id, String text) {
		this.id = id;
		this.text = text;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
