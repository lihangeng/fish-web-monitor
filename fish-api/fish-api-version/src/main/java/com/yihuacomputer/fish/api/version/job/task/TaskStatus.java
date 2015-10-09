package com.yihuacomputer.fish.api.version.job.task;

/**
 * 任务状态
 * NEW--RUN--NOTICED--DOWNLOADED--DEPLOYED-CHECKED
 * @author xuxigang
 *
 */
public enum TaskStatus {
	NEW(0, "新建"), // 新建
	RUN(1, "运行中"), // 运行中,已进入任务队列
	REMOVED(2,"已删除"),//删除的标记，假删除
	CANCELED(3,"已取消"),//已取消
	CANCEL_FAIL(4,"取消失败"),//取消失败，因为取消的动作通知应用失败

	NOTICED(30, "已通知监控客户端"),
	NOTICED_FAIL(31,"通知监控客户端失败"),

	DOWNLOADED(40, "已下发"), //下发到客户端的临时目录
	DOWNLOADED_FAIL(41, "下发失败"),

	DEPLOYED(50,"正在部署"),//根据不同的软件类型，对于部署有不同的含义，如简单的拷贝到指定目录，调用第三方的部署服务，重启系统等
	DEPLOYED_WAIT(51,"等待部署"),//等待部署的任务需要从页面手动触发一次“重启ATM”的动作，完成最终的流程，执行此动作后修改任务状态为DEPLOYED
	DEPLOYED_FAIL(52,"部署失败"),//任务的最终状态“失败”
	CHECKED(53,"部署已确认"),//任务的最终状态“成功”
	NOTICE_APP_OK(54,"已通知应用"),
	NOTICE_APP_FAIL(55,"通知应用失败"),

	OTHER(99,"其它");

	private int id;
	private String text;

	private TaskStatus(int id, String text) {
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

	public static boolean isNoticed(TaskStatus status){
		if(status.getId() == 2 || status.getId() == 3){
			return true;
		}
		return false;
	}

	//没有下载成功,没有通知成功或者取消任务的，新建的任务放入任务队列
	public static boolean canRun(TaskStatus status){
	    if((status.equals(TaskStatus.DOWNLOADED_FAIL))
                || (status.equals(TaskStatus.NOTICED_FAIL))
                || (status.equals(TaskStatus.DEPLOYED_FAIL))
                || (status.equals(TaskStatus.NOTICE_APP_FAIL))
                || (status.equals(TaskStatus.OTHER))
                || status.equals(TaskStatus.NEW)
                || status.equals(TaskStatus.CANCELED)){
	        return true;
	    }
	    return false;
	}

	public static boolean isCancel(TaskStatus taskStatus){
		 if((taskStatus.equals(TaskStatus.REMOVED))
	                || (taskStatus.equals(TaskStatus.CANCELED))
	                || taskStatus.equals(TaskStatus.CANCEL_FAIL)){
	        return true;
	    }
	    return false;
	}

}
