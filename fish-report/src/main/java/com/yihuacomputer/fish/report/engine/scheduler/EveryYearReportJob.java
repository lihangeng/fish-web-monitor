package com.yihuacomputer.fish.report.engine.scheduler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.report.engine.IReportDataETL;
import com.yihuacomputer.fish.api.system.quartz.IJobSynchService;

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
	
	private final String jobId = "yearreporttask";

	@Autowired
	private IJobSynchService jobSynchService;
	
	private List<IReportDataETL> reportDataETLList = new ArrayList<IReportDataETL>();
	
	public void execute(){
		
		logger.debug("year data ETL ....");
    	if(!jobSynchService.getJobRunPriviledge(jobId)){
    		logger.debug("day data ETL job is running");
    		return;
    	}
		try{			
			for(IReportDataETL dataEtl:reportDataETLList){
				dataEtl.reportETL(DateUtils.getLastDate());
			}
		}catch(Exception e){
			logger.error(String.format("load data exception once every year[%s]", e));
		}
		
		logger.debug("year data ETL finished");
	}	
	public void addDataETL(IReportDataETL reportDataETL){
		reportDataETLList.add(reportDataETL);
	}
}
