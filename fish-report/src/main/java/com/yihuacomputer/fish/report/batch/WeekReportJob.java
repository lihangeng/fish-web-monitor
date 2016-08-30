package com.yihuacomputer.fish.report.batch;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.yihuacomputer.fish.report.engine.scheduler.EveryWeekReportJob;
import com.yihuacomputer.fish.system.batch.AbstractYihuaJob;

/**
 * 每周定时任务执行（调用原来内存版本quartz执行的启动类）
 * @author xuxiang
 *
 */
public class WeekReportJob extends AbstractYihuaJob{

	@Override
	protected void executeYihuaJob(JobExecutionContext context) throws JobExecutionException {
		EveryWeekReportJob everyWeekReportJob  = super.getApplicationContext().getBean(EveryWeekReportJob.class);
		everyWeekReportJob.execute();
	}

}
