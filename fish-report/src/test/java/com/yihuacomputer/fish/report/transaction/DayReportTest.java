package com.yihuacomputer.fish.report.transaction;
/*
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.fish.report.MysqlTestConfig;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MysqlTestConfig.class)
public class DayReportTest {

	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JobLocator jobLocator;
	
	@Test
	public void daysLoad(){
		JobParametersBuilder builder = new JobParametersBuilder();
		try {
			JobExecution  jobExecution= jobLauncher.run(jobLocator.getJob("batchDayDeportJob"), builder.toJobParameters());
			System.out.println(jobExecution.getExitStatus().getExitDescription());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
*/