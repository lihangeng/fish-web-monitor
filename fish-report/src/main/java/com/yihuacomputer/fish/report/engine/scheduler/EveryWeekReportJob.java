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
 * 每周报表数据生成
 * 
 * @author xuxigang
 * @since 2016-8-29
 */
@Service("WeekETLJob")
public class EveryWeekReportJob {
	
	private static Log logger = LogFactory.getLog(EveryWeekReportJob.class);
	
    @Autowired
    private IJobSynchService jobSynchService;
    
	private List<IReportDataETL> reportDataETLList = new ArrayList<IReportDataETL>();
	
	public void execute(){
		try{
			for(IReportDataETL dataEtl:reportDataETLList){
				dataEtl.reportETL(DateUtils.getLastDate());
			}
		}catch(Exception e){
			logger.error(String.format("load data exception once every week[%s]", e));
		}
		logger.debug("week data ETL finished");

	}
	
	public void addDataETL(IReportDataETL reportDataETL){
		reportDataETLList.add(reportDataETL);
	}
}
