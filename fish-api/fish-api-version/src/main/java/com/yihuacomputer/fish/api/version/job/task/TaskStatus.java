package com.yihuacomputer.fish.api.version.job.task;

/**
 * 任务状态
 * NEW--RUN--NOTICED--DOWNLOADED--DEPLOYED-CHECKED
 * @author xuxigang
 *
 */
public enum TaskStatus {
	NEW(0, "TaskStatus.NEW"), // 新建
	RUN(1, "TaskStatus.RUN"), // 运行中,已进入任务队列
	REMOVED(2,"TaskStatus.REMOVED"),//删除的标记，假删除
	CANCELED(3,"TaskStatus.CANCELED"),//已取消
	CANCEL_FAIL(4,"TaskStatus.CANCEL_FAIL"),//取消失败，因为取消的动作通知应用失败

	NOTICED(30, "TaskStatus.NOTICED"),
	NOTICED_FAIL(31,"TaskStatus.NOTICED_FAIL"),

	DOWNLOADING(42, "TaskStatus.DOWNLOADING"), // 版本文件正在下载中
	DOWNLOADED(40, "TaskStatus.DOWNLOADED"), //下发到客户端的临时目录
	DOWNLOADED_FAIL(41, "TaskStatus.DOWNLOADED_FAIL"),
	DOWN_BEFORE_WAIT(43, "正在排队等待下载"), 

	DEPLOYED(50,"TaskStatus.DEPLOYED"),//根据不同的软件类型，对于部署有不同的含义，如简单的拷贝到指定目录，调用第三方的部署服务，重启系统等
	DEPLOYED_WAIT(51,"TaskStatus.DEPLOYED_WAIT"),//等待部署的任务需要从页面手动触发一次“重启ATM”的动作，完成最终的流程，执行此动作后修改任务状态为DEPLOYED
	DEPLOYED_FAIL(52,"TaskStatus.DEPLOYED_FAIL"),//任务的最终状态“失败”
	CHECKED(53,"TaskStatus.CHECKED"),//任务的最终状态“成功”
	NOTICE_APP_OK(54,"TaskStatus.NOTICE_APP_OK"),
	NOTICE_APP_FAIL(55,"TaskStatus.NOTICE_APP_FAIL"),

	OTHER(99,"TaskStatus.OTHER"),

	CANCEL_UPDATE_OK(60,"TaskStatus.CANCEL_UPDATE_OK"),//取消升级
	CANCEL_UPDATE_FAIL(61,"TaskStatus.CANCEL_UPDATE_FAIL"),//客户端取消升级失败
	
	
	
	ROLLBACK_OR_PATCHED(101, "TaskStatus.ROLLBACK_OR_PATCHED"),//版本被回滚或者被人为安装成低版本
	
	REPEAT_TASK(102, "TaskStatus.REPEAT_TASK"),//相同的软件分类正在升级,已终止
	
	FAIL_ROLLBACK(103, "TaskStatus.FAIL_ROLLBACK");//部署失败，自动回滚至上一版本
	
	private int id;
	private String text;

	private TaskStatus(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	/**
	 * @param status
	 * @return
	 */
	public static boolean isNoticed(TaskStatus status){
		if(status.getId() == 2 || status.getId() == 3){
			return true;
		}
		return false;
	}

	/**
	 * 没有下载成功,没有通知成功，新建的任务放入任务队列
	 * @param status
	 * @return
	 */
	public static boolean canRun(TaskStatus status){
	    if((status.equals(TaskStatus.DOWNLOADED_FAIL))
	    		|| (status.equals(TaskStatus.RUN))
                || (status.equals(TaskStatus.NOTICED_FAIL))
                || (status.equals(TaskStatus.DEPLOYED_FAIL))
                || (status.equals(TaskStatus.NOTICE_APP_FAIL))
                || (status.equals(TaskStatus.OTHER))
                || status.equals(TaskStatus.NEW)){
	        return true;
	    }
	    return false;
	}

	/**
	 * @param taskStatus
	 * @return
	 */
	public static boolean isCancel(TaskStatus taskStatus){
		 if((taskStatus.equals(TaskStatus.REMOVED))
	                || (taskStatus.equals(TaskStatus.CANCELED))
	                || taskStatus.equals(TaskStatus.CANCEL_FAIL)){
	        return true;
	    }
	    return false;
	}

 }
