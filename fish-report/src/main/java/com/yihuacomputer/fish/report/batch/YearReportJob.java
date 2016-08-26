package com.yihuacomputer.fish.report.batch;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.yihuacomputer.fish.report.engine.scheduler.EveryYearReportJob;
import com.yihuacomputer.fish.system.batch.AbstractYihuaJob;

/**
 * 每年定时任务执行（调用原来内存版本quartz执行的启动类）
 * @author xuxiang
 *
 */
public class YearReportJob extends AbstractYihuaJob{

	@Override
	protected void executeYihuaJob(JobExecutionContext context) throws JobExecutionException {
		EveryYearReportJob everyYearReportJob  = super.getApplicationContext().getBean(EveryYearReportJob.class);
		everyYearReportJob.execute();
	}

}
