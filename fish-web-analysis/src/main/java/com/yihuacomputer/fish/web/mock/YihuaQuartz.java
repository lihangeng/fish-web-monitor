package com.yihuacomputer.fish.web.mock;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class YihuaQuartz {

	//@Autowired
	private Scheduler scheduler;

	public void init() {
		try {
			JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
			// 任务执行类
					.withIdentity("job1_1", "jGroup1")
					// 任务名，任务组
					.build();
			// 2、创建Trigger
			SimpleScheduleBuilder builder = SimpleScheduleBuilder.
					simpleSchedule().
					repeatSecondlyForTotalCount(10);
			Trigger trigger = TriggerBuilder.newTrigger().
					withIdentity("trigger1_1", "tGroup1").
					startNow().
					withSchedule(builder).
					build();
			// 3、创建Scheduler

			scheduler.start();

			// 4、调度执行
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
