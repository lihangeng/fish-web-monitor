package com.yihuacomputer.fish.api.version.job;

/**
 * 作业优先级
 * @author xuxigang
 *
 */
public enum JobPriority {
	GENERAL(0, "JobPriority.GENERAL"), // 普通
	MIDDLE(1, "JobPriority.MIDDLE"), // 中等
	HIGH(2, "JobPriority.HIGH");// 高

	private int id;
	private String text;

	private JobPriority(int id, String text) {
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
