package com.yihuacomputer.fish.report.batch;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.yihuacomputer.fish.report.engine.scheduler.EveryMonthReportJob;
import com.yihuacomputer.fish.system.batch.AbstractYihuaJob;

/**
 * 每月定时任务执行（调用原来内存版本quartz执行的启动类）
 * @author xuxiang
 *
 */
public class MonthReportJob extends AbstractYihuaJob{

	@Override
	protected void executeYihuaJob(JobExecutionContext context) throws JobExecutionException {
		EveryMonthReportJob everyMonthReportJob  = super.getApplicationContext().getBean(EveryMonthReportJob.class);
		everyMonthReportJob.execute();
	}

}
