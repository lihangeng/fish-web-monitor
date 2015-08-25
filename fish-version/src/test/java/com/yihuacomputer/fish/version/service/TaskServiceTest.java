package com.yihuacomputer.fish.version.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yihuacomputer.domain.test.BindSessionInTest2;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.IVersionType;
import com.yihuacomputer.fish.api.version.IVersionTypeService;
import com.yihuacomputer.fish.api.version.job.IJob;
import com.yihuacomputer.fish.api.version.job.IJobService;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.version.H2TestConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {H2TestConfig.class})
public class TaskServiceTest  extends BindSessionInTest2{

	@Autowired
	private ITaskService taskService;

	@Autowired
	private IVersionTypeService versionTypeService;

	@Autowired
	private IVersionService versionService;

	@Autowired
	private IJobService jobService;

	@Test
	public void test(){
		IJob job = jobService.make();
		job.setJobName("ATMC需求第二批次上线");
		jobService.add(job);

		IVersionType versionType = versionTypeService.make("JCOLS");
		versionTypeService.add(versionType);

		IVersion version = versionService.make();
		version.setCreatedTime(new Date());
		version.setVersionType(versionType);
		version.setVersionNo("1.0");
		version.setVersionPath("D:");
		version.setServerPath("aa.file");
		versionService.add(version);

		Date planTime = new Date();
		ITask task = taskService.make();
		task.setJob(job);
		task.setPlanTime(planTime);
		task.setVersion(version);
		taskService.addTask(task);
		List<ITask> tasks = taskService.findTasks(planTime);
		assertEquals(tasks.size(),1);

		ITask taskInDB = tasks.get(0);
		assertEquals(taskInDB.getJobName(),"ATMC需求第二批次上线");
	}

}
