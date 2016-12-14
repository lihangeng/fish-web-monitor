package com.yihuacomputer.fish.api.version.job;

/**
 * 作业类型
 * @author xuxigang
 *
 */
public enum JobType {
	SCHEDULER(0, "JobType.SCHEDULER"), // 计划作业
	AUTO_UPDATE(1, "JobType.AUTO_UPDATE"), // 自动更新作业
	MANUAL(2, "JobType.MANUAL");// 手工作业

	private int id;
	private String text;

	private JobType(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

}
