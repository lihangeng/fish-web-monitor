package com.yihuacomputer.fish.web.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.system.quartz.IQuartzService;
import com.yihuacomputer.fish.web.system.form.QuartzConfForm;

/**
 * @author panxin
 * @version
 * @date 2016-7-18
 */
@Controller
@RequestMapping("/system/quartz")
public class QuartzConfController {
	private Logger logger = LoggerFactory.getLogger(QuartzConfController.class);

	@Autowired
	private IQuartzService quartzService;
	
	@Autowired(required = false)
	SchedulerFactoryBean startQuartz;



	/**
	 *
	 * 根据条件得到JOB列表
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		
		logger.info(String.format("search group : start = %s ,limt = %s ", start, limit));

		IFilter filter = new Filter();

		String parentId = request.getParameter("parentId");
		if (null != parentId) {
			filter.eq("organizationCode", parentId);
			logger.info("parentId : " + parentId);
		}

		String name = request.getParameter("name");
		if (null != name) {
			filter.like("name", name);
			logger.info("name : " + name);
		}
		List<QuartzConfForm>  data = convert(quartzService.listJobs());

		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, data.size());
		result.addAttribute(FishConstant.DATA, data);
		return result;
	}
	
	
	
	/**
	 *
	 * 暂停job
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/pauseJob", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap pauseJob(String jobName,String jobGroup,WebRequest request) {
		
		
		ModelMap result = new ModelMap();
		try {
			Scheduler scheduler = startQuartz.getScheduler();
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			scheduler.pauseJob(jobKey);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (SchedulerException e) {
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		return result;
	}
	
	
	/**
	 *
	 * 恢复job
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/resumeJob", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap resumeJob(String jobName,String jobGroup,WebRequest request) {
		
		ModelMap result = new ModelMap();
		try {
			Scheduler scheduler = startQuartz.getScheduler();
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			scheduler.resumeJob(jobKey);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (SchedulerException e) {
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		return result;
	}
	
	
	
	/**
	 *
	 * 删除job
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/deleteJob", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap deleteJob(String jobName,String jobGroup,WebRequest request) {
		
		ModelMap result = new ModelMap();
		try {
			Scheduler scheduler = startQuartz.getScheduler();
			
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			
			scheduler.deleteJob(jobKey);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (SchedulerException e) {
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		return result;
	}
	
	
	
	
	/**
	 *
	 * 立即执行job
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/executeJob", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap executeJob(String jobName,String jobGroup,WebRequest request) {
		
		ModelMap result = new ModelMap();
		
		try {
			Scheduler scheduler = startQuartz.getScheduler();
			
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			
			scheduler.triggerJob(jobKey);
			
			result.addAttribute(FishConstant.SUCCESS, true);
			
		} catch (SchedulerException e) {
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		
		return result;
	}
	
	
	
	
	/**
	 *
	 * 修改job的Cron表达式
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody ModelMap update(@PathVariable long id, @RequestBody QuartzConfForm request) {
		logger.info("update quartz: job.id = " + id);
		ModelMap result = new ModelMap();
		try {
			Scheduler scheduler = startQuartz.getScheduler();
			
			String jobName = request.getJobName();
			String jobGroup = request.getJobGroup();
			String cronExpression = request.getCronExpression();
			
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName,jobGroup);
			
			////获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			
			//表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
			 
			//按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
			
			 
			//按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey , trigger);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (SchedulerException e) {
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		return result;
	}


	
	private List<QuartzConfForm> convert(List<Object[]> jobs){
		List<QuartzConfForm> qfs = new ArrayList<QuartzConfForm>();
		for(Object qf[] : jobs ){
			QuartzConfForm qff = new QuartzConfForm();
			qff.setTriggerName((String)qf[0]);
			qff.setTriggerGroup((String)qf[1]);
			qff.setJobName((String)qf[2]);
			qff.setJobDescription((String)qf[4]);
			qff.setJobClassName((String)qf[5]);
			qff.setCronExpression((String)qf[6]);
			qff.setTriggerState((String)qf[7]);
			
			Date time = new Date(Long.parseLong(qf[8].toString()));
			qff.setStartTime(DateUtils.getTimestamp(time));
			time = new Date(Long.parseLong(qf[9].toString()));
			qff.setEndTime(DateUtils.getTimestamp(time));
			time = new Date(Long.parseLong(qf[10].toString()));
			qff.setNextFireTime(DateUtils.getTimestamp(time));
			time = new Date(Long.parseLong(qf[11].toString()));
			qff.setPrevFireTime(DateUtils.getTimestamp(time));
			qfs.add(qff);
			
		}
		return qfs;
		
	}

}
