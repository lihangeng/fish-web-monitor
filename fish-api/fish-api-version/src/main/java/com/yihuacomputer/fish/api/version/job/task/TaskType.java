package com.yihuacomputer.fish.api.version.job.task;

/**
 * 任务类型
 * @since 0.17
 * @author xuxigang
 *
 */
public enum TaskType {
	AUTO_UPDATE(0, "自动升级"), // 自动更新任务
	MANUAL(1, "手动升级"),// 手工任务
	SCHEDULER(2, "计划作业"); // 计划作业

	private int id;
	private String text;

	private TaskType(int id, String text) {
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
