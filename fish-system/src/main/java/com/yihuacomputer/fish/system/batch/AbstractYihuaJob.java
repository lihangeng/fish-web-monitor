package com.yihuacomputer.fish.system.batch;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 抽象的Quartz作业
 * @author xuxiang
 * @since 2.1.1.1
 */
public abstract class AbstractYihuaJob extends QuartzJobBean{
	
	private static final String APPLICATION_CONTEXT_KEY = "applicationContextKey";
	
	private Logger logger = LoggerFactory.getLogger(AbstractYihuaJob.class);
	
	private ApplicationContext applicationContext;
	
	public ApplicationContext getApplicationContext(){
		return this.applicationContext;
	}
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			this.applicationContext = (ApplicationContext)context.getScheduler().getContext().get(APPLICATION_CONTEXT_KEY);
		} catch (SchedulerException e) {
			logger.error(String.format("get applicationContext error[%s]",e));
			return ;
		}
		executeYihuaJob(context);
	}
	
	protected abstract void executeYihuaJob(JobExecutionContext context) throws JobExecutionException;
	
}
