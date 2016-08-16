package com.yihuacomputer.fish.batch.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.stereotype.Service;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.batch.base.IETLjobMonthService;

@Service
public class MonthTransService implements IETLjobMonthService {

	@Autowired
	private IGenericDao dao;
	
	@Autowired
	private Job Month_Trans_job;
	
	@Autowired
	private SimpleJobLauncher launcher;

	@Autowired
	private JobRepositoryFactoryBean jobRepository;
	
	public static String tradeTime;

//	@Override
//	public ITransactionMonths make() {
//		return new TransactionMonths();
//	}
//
//	@Override
//	public void save(ITransactionMonths transactionMonths) {
//		dao.save(transactionMonths);
//
//	}
//
//	@Override
//	public List<ITransactionMonths> list() {
//		return dao.loadAll(ITransactionMonths.class);
//	}

	@Override
	public void extractDate(String date) {
		tradeTime=date;
		try {
			launcher.setJobRepository(jobRepository.getObject());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		launcher.setTaskExecutor(new SyncTaskExecutor());
		try {
			Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
			parameters.put("tradeTime", new JobParameter(date));
			JobExecution je = launcher.run(Month_Trans_job, new JobParameters(parameters));
			System.out.println(je);
			System.out.println(je.getJobInstance());
			System.out.println(je.getStepExecutions());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> dateList(long oldstr, long xinstr) {
		// TODO Auto-generated method stub
		return null;
	}

}
