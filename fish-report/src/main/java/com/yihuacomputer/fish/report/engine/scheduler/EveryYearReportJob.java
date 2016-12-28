package com.yihuacomputer.fish.report.engine.scheduler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * 每年报表数据生成(从月报表数据进行统计)
 *
 * @author YiHua
 * 
 * @since 2010-9-8
 */
@Service("YearETLJob")
public class EveryYearReportJob {
	
	private static Log logger = LogFactory.getLog(EveryYearReportJob.class);

	/**
	 * 执行
	 */
	public void execute(){
		
		logger.debug("year data ETL finished");
	}	

}
