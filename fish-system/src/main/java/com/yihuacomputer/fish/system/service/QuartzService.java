package com.yihuacomputer.fish.system.service;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.system.dbQuartz.IQuartzService;

@Service
@Transactional
public class QuartzService implements IQuartzService{
	
	//private Logger logger = LoggerFactory.getLogger(QuartzService.class);
	
	private final static String TASK_LIST_SQL = 
			"SELECT TRIGGERS.TRIGGER_NAME,\n" +
			"       TRIGGERS.TRIGGER_GROUP,\n" + 
			"       TRIGGERS.JOB_NAME,\n" + 
			"       TRIGGERS.JOB_GROUP,\n" + 
			"       JOB_D.DESCRIPTION,\n" + 
			"       JOB_D.JOB_CLASS_NAME,\n" + 
			"       CRON_TRIGGERS.CRON_EXPRESSION,\n" + 
			"		TRIGGERS.TRIGGER_STATE,"+
			"       TRIGGERS.START_TIME,\n" + 
			"       TRIGGERS.END_TIME,\n" + 
			"       TRIGGERS.NEXT_FIRE_TIME,\n" + 
			"       TRIGGERS.PREV_FIRE_TIME\n" + 
			"  FROM QRTZ_TRIGGERS TRIGGERS\n" + 
			" INNER JOIN QRTZ_CRON_TRIGGERS CRON_TRIGGERS\n" + 
			"    ON (CRON_TRIGGERS.TRIGGER_NAME = TRIGGERS.TRIGGER_NAME AND\n" + 
			"       CRON_TRIGGERS.TRIGGER_GROUP = TRIGGERS.TRIGGER_GROUP)\n" + 
			" INNER JOIN QRTZ_JOB_DETAILS JOB_D\n" + 
			"    ON (JOB_D.JOB_NAME = TRIGGERS.JOB_NAME AND\n" + 
			"       JOB_D.JOB_GROUP = TRIGGERS.JOB_GROUP)";

    @Autowired
    private IGenericDao dao;

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listJobs() {
		
	        SQLQuery query = dao.getSQLQuery(TASK_LIST_SQL);
	        return query.list();
	}


}
