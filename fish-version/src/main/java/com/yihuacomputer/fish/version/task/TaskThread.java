/**
 *
 */
package com.yihuacomputer.fish.version.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;

/**
 * @author xuxigang
 *
 */
public class TaskThread implements Runnable {
	private Logger logger = LoggerFactory.getLogger(TaskThread.class);

	private ITask task;
	private ITaskService taskService;
	private MessageSource messageSource;

	public TaskThread() {
	}

	/**
	 * @param task
	 */
	public TaskThread(ITask task) {
		this.task = task;
	}

	public void setTaskService(ITaskService taskService) {
		this.taskService = taskService;
	}
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public ITask getTask() {
		return task;
	}

	public void setTask(ITask task) {
		this.task = task;
	}

    /**
     * @param task
     * @return
     */
    public String getState(ITask task) {
        if (task.getStatus().equals(TaskStatus.NEW) || task.getStatus().equals(TaskStatus.RUN)) {
            return getI18n(task.getStatus().getText());
        }
        else {
            return getI18n(task.getStatus().getText()) + (task.isSuccess() ? getI18n("taskstatus.execute.result.success") : getI18n("taskstatus.execute.result.failer"));
        }
    }
    
	private String getI18n(String enumText){
    	if(null==enumText){
    		return "";
    	}
    	return messageSource.getMessage(enumText, null, FishCfg.locale);
    }
	/**
	 * 具体的执行任务
	 * */
	@Override
	public synchronized void run() {
		logger.info("Starting notify atm client：" + task.toString()+task.getState());
		if(!taskService.noticeATM(task)){
			// 尝试等待20秒
	        synchronized (this) {
	            try {
	                this.wait(20l * 1000l);
	            }
	            catch (InterruptedException e) {
	            	logger.error(String.format("InterruptedException is [%s]", e));
	            }
	        }
			logger.info(task.toString() + "notify finish!");
		}else{
			logger.info(task.toString() + "ignore");
		}
	}

}
