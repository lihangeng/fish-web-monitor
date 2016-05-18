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
@Service("EveryMonthFault")
public class EveryMonthFault {

	private static Log logger = LogFactory.getLog(EveryMonthFault.class);

	private final String jobId = "ExtractJob";

	public void extract(){
	
	}
}
