package com.yihuacomputer.fish.system.service.db;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.system.quartz.IJobSynchService;
import com.yihuacomputer.fish.api.system.quartz.IJobSynchronous;
import com.yihuacomputer.fish.system.entity.JobSynchronous;

@Service
@Transactional
public class JobSynchService implements IJobSynchService{
	
	private Logger logger = LoggerFactory.getLogger(JobSynchService.class);

    @Autowired
    private IGenericDao dao;

	@Override
	public boolean getJobRunPriviledge(String jobId) {


		JobSynchronous jobSynch = null;
		try{
			//为了集群时区分不同时间，进行随机等待100秒之内
			int wait = (int)(Math.random()*50+10);
			Thread.sleep(wait);
			jobSynch = dao.get(jobId,JobSynchronous.class);
		}catch(Exception e){
			logger.error(String.format("SM_JOB_SYNCHRON table doesn't exist error![%s] ",e));
		}
		
		//未定义需要同步的定时任务
		if(jobSynch==null){
			logger.debug(String.format("%s job got priviledge to run",jobId));
			return true;
		}
		
		
		Calendar dateTime = Calendar.getInstance();
		if(jobSynch.getDateTime()==null){
			this.updateJobStatus(jobSynch, dateTime.getTime());
			return true;
		}
		long doTime = jobSynch.getDateTime().getTime();

		long subTime = Math.abs(dateTime.getTimeInMillis() -doTime);
		
        long diffMinutes = subTime / (60 * 1000) % 60;
        long diffHours = subTime / (60 * 60 * 1000) % 24;
        long diffDays = subTime / (24 * 60 * 60 * 1000);

        if(diffDays>0||diffHours>0||diffMinutes>2){
        	this.updateJobStatus(jobSynch, dateTime.getTime());
			logger.debug(String.format("%s job got priviledge to run",jobId));
        	return true;
        }		

		return false;
	}
	
	private void updateJobStatus(IJobSynchronous jobSynch,Date dateTime){
        jobSynch.setDateTime(dateTime);
        jobSynch.setServerIp(FishCfg.hostIp);
        
        dao.update(jobSynch);
	}
}
