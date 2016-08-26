package com.yihuacomputer.fish.system.batch;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;

import com.yihuacomputer.common.util.DateUtils;

/**
 * Quartz定时任务启动一个spring Batch作业
 * 
 * @author xuxiang
 * @since 2.1.1.1
 */
public class SpringBatchJob extends AbstractYihuaJob {

	private static final String BATCH_JOB_NAME_KEY = "batchJobName";

	private Logger logger = LoggerFactory.getLogger(SpringBatchJob.class);

	@Override
	protected void executeYihuaJob(JobExecutionContext context) throws JobExecutionException {
		JobLauncher jobLauncher = this.getApplicationContext().getBean(JobLauncher.class);
		JobLocator jobLocator = this.getApplicationContext().getBean(JobLocator.class);
		Map<String, Object> jobDataMap = context.getMergedJobDataMap();
		String jobName = (String) jobDataMap.get(BATCH_JOB_NAME_KEY);
		logger.info(String.format("prepare to exexute job [%s]" ,jobName));
		JobParameters jobParameters = getJobParametersFromJobMap(jobDataMap);
		try {
			JobExecution jobExecution = jobLauncher.run(jobLocator.getJob(jobName), jobParameters);
			logger.info(String.format("job [%s] finished [%s] ",jobName,jobExecution.getExitStatus().getExitCode()));
		} catch (Exception e) {
			logger.error("Could not execute batch_job.", e);
		}
	}

	/*
	 * Copy parameters that are of the correct type over to {@link
	 * JobParameters}, ignoring jobName.
	 * 
	 * @return a {@link JobParameters} instance
	 */
	private JobParameters getJobParametersFromJobMap(Map<String, Object> jobDataMap) {

		JobParametersBuilder builder = new JobParametersBuilder();

		for (Entry<String, Object> entry : jobDataMap.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if(key.contains("date.")){
				builderDate(builder,key,(String) value);
			}else if (value instanceof String && !key.equals(BATCH_JOB_NAME_KEY)) {
				builder.addString(key, (String) value);
			} else if (value instanceof Float || value instanceof Double) {
				builder.addDouble(key, ((Number) value).doubleValue());
			} else if (value instanceof Integer || value instanceof Long) {
				builder.addLong(key, ((Number) value).longValue());
			} else if (value instanceof Date) {
				builder.addDate(key, (Date) value);
			} else {
				logger.debug("JobDataMap contains values which are not job parameters (ignoring).");
			}
		}

		return builder.toJobParameters();

	}

	private void builderDate(JobParametersBuilder builder,String key, String format) {
		/*builder.addString(key, DateUtils.getLastDate());*/
	}

}
