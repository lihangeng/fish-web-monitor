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
 * 每月报表数据生成
 * 
 * @author YiHua
 * 
 * @since 2010-9-8
 */
@Service("MonthETLJob")
public class EveryMonthReportJob {
	
	private static Log logger = LogFactory.getLog(EveryMonthReportJob.class);
	
	private final String jobId = "monthreporttask";

	@Autowired
	private IJobSynchService jobSynchService;
	    
	private List<IReportDataETL> reportDataETLList = new ArrayList<IReportDataETL>();
	
	public void execute(){
		
		logger.debug("month data ETL ....");
    	if(!jobSynchService.getJobRunPriviledge(jobId)){
    		logger.debug("day data ETL job is running");
    		return;
    	}
		try{			
			for(IReportDataETL dataEtl:reportDataETLList){
				dataEtl.reportETL(DateUtils.getLastDate());
			}
		}catch(Exception e){
			logger.error(String.format("load data exception once every month[%s]", e));
		}
		logger.debug("month data ETL finished");

	}	
	
	public void addDataETL(IReportDataETL reportDataETL){
		reportDataETLList.add(reportDataETL);
	}
}
