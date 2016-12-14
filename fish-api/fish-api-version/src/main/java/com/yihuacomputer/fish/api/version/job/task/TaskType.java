package com.yihuacomputer.fish.api.version.job.task;

/**
 * 任务类型
 * @since 0.17
 * @author xuxigang
 *
 */
public enum TaskType {
	AUTO_UPDATE(0, "TaskType.AUTO_UPDATE"), // 自动更新任务
	MANUAL(1, "TaskType.MANUAL"),// 手工任务
	SCHEDULER(2, "TaskType.SCHEDULER");

	private int id;
	private String text;

	private TaskType(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public static TaskType getById(int id){
        for (TaskType each : TaskType.values()){
            if (each.getId() == id){
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }

}
