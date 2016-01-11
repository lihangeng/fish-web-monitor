package com.yihuacomputer.fish.version.task;

import java.lang.Thread.State;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.version.job.ITaskManagerStatus;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskCollection;
import com.yihuacomputer.fish.api.version.job.task.ITaskManager;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;

public class TaskManager implements ITaskManager ,ITaskManagerStatus {

	private Logger logger = LoggerFactory.getLogger(TaskManager.class);

	@Autowired
	private ITaskService taskService;

	private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

	@Autowired
	private ITaskCollection taskCollection;
	
	private TaskTaker taskTaker;

	private Thread taskTaskerThread;

	private TaskThreadPool taskThreadPool2;
	
	

	/***
	 * 一、创建阶段 1.保存到数据库 2.放入定时器 二、执行阶段－放入队列 1.从数据中根据计划时间查找任务 ２.放入队列
	 */
	public void createTasksByWeb(List<ITask> tasks){
		if (tasks.size() == 0) {
			return;
		}
		// 1.保存到数据库
		long start = System.currentTimeMillis();
//		for (ITask task : tasks) {
//			taskService.addTask(task);
//		}
		logger.info(String.format("tasks insert to database spend [%s]s .", (System.currentTimeMillis() - start) / 1000));

		// 2.放入定时器
		ITask task = tasks.get(0);
		final Date planDate = task.getPlanTime();
		long delay = planDate.getTime() - System.currentTimeMillis();
		scheduledExecutorService.schedule(new Runnable() {
			// 定时时间到，执行任务分发
			@Override
			public void run() {
				logger.info(String.format("start [%s] task:push to queue", DateUtils.getTimestamp(planDate)));
				List<ITask> tasks = taskService.findTasks(planDate);
				for (ITask task : tasks) {
					logger.info(String.format("the task will push to queue is [%s]",task.toString()));
					// 没有下载成功,没有通知成功，新建的任务放入任务队列
					if (TaskStatus.canRun(task.getStatus())) {
						taskCollection.put(task);
					} else {
						logger.info(String.format("ignore a task [%s]", task.toString()));
					}
				}
				logger.info(String.format("complete[%s] task:push to queue", DateUtils.getTimestamp(planDate)));
			}

		}, delay <= 0 ? 0 : delay, TimeUnit.MILLISECONDS);
	
	}

	@Override
	public void createTasksByWeb(ITask task) {
		// 1.保存到数据库
		long start = System.currentTimeMillis();
		taskService.addTask(task);
		logger.info(String.format("tasks insert to database spend [%s]s .", (System.currentTimeMillis() - start) / 1000));

		// 2.放入定时器
//		ITask task = tasks.get(0);
		final Date planDate = task.getPlanTime();
		long delay = planDate.getTime() - System.currentTimeMillis();
		scheduledExecutorService.schedule(new Runnable() {
			// 定时时间到，执行任务分发
			@Override
			public void run() {
				logger.info(String.format("start [%s] task:push to queue", DateUtils.getTimestamp(planDate)));
				List<ITask> tasks = taskService.findTasks(planDate);
				for (ITask task : tasks) {
					logger.info(String.format("the task will push to queue is [%s]",task.toString()));
					// 没有下载成功,没有通知成功，新建的任务放入任务队列
					if (TaskStatus.canRun(task.getStatus())) {
						taskCollection.put(task);
					} else {
						logger.info(String.format("ignore a task [%s]", task.toString()));
					}
				}
				logger.info(String.format("complete[%s] task:push to queue", DateUtils.getTimestamp(planDate)));
			}

		}, delay <= 0 ? 0 : delay, TimeUnit.MILLISECONDS);
	}

	@Override
	public void createTasksBySystem(List<ITask> tasks) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelTask(long taskId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadUnFinishedTasks() {
		//TODO 

	}
	
	/**
	 * 构造函数执行后，spring自动调用该方法
	 */
	@PostConstruct
	public void init() {
		this.taskThreadPool2 = new TaskThreadPool();
		this.initTaskTaker();
		loadUnFinishedTasks();
	}

	@PreDestroy
	public void stop(){
		scheduledExecutorService.shutdown();
		logger.info("scheduledExecutorService was closed.");
		this.taskTaskerThread.interrupt();
		logger.info("taskTaskerThread was closed.");
		this.taskThreadPool2.close();
	}
	
	/**
	 * 初始化任务调度管理器
	 * */
	private void initTaskTaker() {
		taskTaker = new TaskTaker(taskService,taskCollection,taskThreadPool2);
		taskTaskerThread = new Thread(taskTaker);
		taskTaskerThread.setName("Task_Taker");
		taskTaskerThread.start();
		logger.info(String.format("started thread taskTaker = %s" ,taskTaskerThread.getName()));
	}

	@Override
	public void cancelTasks(List<ITask> list) {
		for(ITask task:list){
			//如果移除成功，则直接将任务状态置为已取消
			if(taskCollection.cancelTask(task)){
				task.setStatus(TaskStatus.CANCELED);
				taskService.updateTask(task);
			}
		}
	}

	@Override
	public State getJobManagerState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State getTaskMangerState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxJobCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getJobQueueCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getActiveTaskCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
