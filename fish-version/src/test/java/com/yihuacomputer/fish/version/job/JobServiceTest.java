package com.yihuacomputer.fish.version.job;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.version.job.IJob;
import com.yihuacomputer.fish.api.version.job.IJobService;
import com.yihuacomputer.fish.version.H2TestConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {H2TestConfig.class})
public class JobServiceTest  extends BindSessionInTest2{

	@Autowired
	private IJobService jobService;


	@Test
	public void test(){
		IJob job = jobService.make();
		job.setJobName("ATMC需求第一批次上线");
		jobService.add(job);
		assertTrue(job.getId() > 0 );
		assertEquals(job.getJobName(),"ATMC需求第一批次上线");
	}

}
