package com.yihuacomputer.fish.api.version.job;

/**
 * 作业状态
 * NEW---------|
 *             |--READY_RUN--RUN--COMPLETE--FINISH
 * SCHEDULER---|             |
 *                          STOP
 * @author xuxigang
 *
 */
public enum JobStatus {
	NEW(0, "新建"), // 新建
	SCHEDULER(1, "计划中"), // 计划中
	READY_RUN(2,"准备运行"),//
	RUN(3, "运行中"), // 运行
	PAUSE(4, "暂停"), // 暂停
	COMPLETE(5, "完成"),// job线程执行完毕，作业中所有的任务通知一遍完成（通知完成）
	FINISH(6,"完成");//作业中的所有任务都被执行完毕（取消，删除，部署已确认）

	private int id;
	private String text;

	private JobStatus(int id, String text) {
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

	//只有新建和计划中的作业在重启系统的时候自动加载到作业队列中
	public static boolean needLoaded(JobStatus status){
		if(status.getId() == 0 && status.getId() == 1){
			return true;
		}
		return false;
	}

}
