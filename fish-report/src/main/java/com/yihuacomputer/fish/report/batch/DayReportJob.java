package com.yihuacomputer.fish.report.batch;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.yihuacomputer.fish.report.engine.scheduler.EveryDayReportJob;
import com.yihuacomputer.fish.system.batch.AbstractYihuaJob;

/**
 * 每日定时任务执行（调用原来内存版本quartz执行的启动类）
 * @author xuxiang
 *
 */
public class DayReportJob extends AbstractYihuaJob{

	@Override
	protected void executeYihuaJob(JobExecutionContext context) throws JobExecutionException {
		EveryDayReportJob everyDayReportJob  = super.getApplicationContext().getBean(EveryDayReportJob.class);
		everyDayReportJob.execute();
	}

}
