package com.yihuacomputer.fish.web.mock;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job{
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("Hello quzrtz  "+
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new Date()));
		
	}

}
