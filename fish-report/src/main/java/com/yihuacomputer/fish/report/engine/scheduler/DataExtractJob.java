package com.yihuacomputer.fish.report.engine.scheduler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
/**
 *
 * 启动数据抽取工作
 *
 * @author YH
 *
 */
@Service("DataExtractJob")
public class DataExtractJob {

	private static Log logger = LogFactory.getLog(DataExtractJob.class);

	private final String jobId = "ExtractJob";

	public void extracttask(){
		System.out.println("hello");
	}
}
