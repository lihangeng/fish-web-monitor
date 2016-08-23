package com.yihuacomputer.fish.batch.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.stereotype.Service;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.batch.base.IETLjobDaysService;

@Service
// @Transactional 标识事务，如果处理失败则自动回滚
public class DayTransBatchService implements IETLjobDaysService {

	@Autowired
	private IGenericDao dao;
	
	@Autowired
	private Job Day_Trans_job;
	
	@Autowired
	private SimpleJobLauncher launcher;

	@Autowired
	private JobRepositoryFactoryBean jobRepository;
	
	public static String tradeTime;

//	@Override
//	public void save(ITransactionDays transactionDay) {
//		dao.save(transactionDay);
//	}
//
//	@Override
//	public List<ITransaction> list2() {
//		return null;
//	}
//
//	@Override
//	public List<ITransactionDays> list() {
//		return dao.loadAll(ITransactionDays.class);
//	}

	@Override
	public String extractDate(String date) {
		String str =null;
		tradeTime=date;

		try {
			launcher.setJobRepository(jobRepository.getObject());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		launcher.setTaskExecutor(new SyncTaskExecutor());
		try {
			Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
			parameters.put("tradeTime", new JobParameter(tradeTime));
			JobExecution je = launcher.run(Day_Trans_job, new JobParameters(parameters));
			
			if(!je.getStatus().toString().equals("COMPLETED")){
				
				str = (((StepExecution) (je.getStepExecutions().toArray())[0]).getFailureExceptions()).get(0).toString();
				return str;
			}
//			System.out.println(je);
//			System.out.println(je.getJobInstance());
//			System.out.println(je.getStepExecutions());
			return je.getStatus().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return str;
		}

	}

//	@Override
//	public ITransactionDays make() {
//		return null;
//	}

	@Override
	public List<String> dateList(long oldstr,long xinstr) {
		StringBuffer sql = new StringBuffer();

		sql.append("select t.TRANS_DATE tradeTime from atmc_transaction_days t "
				+ "where t.TRANS_DATE>="
				+ oldstr
				+ " and t.TRANS_DATE<="
				+ xinstr);
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.addScalar("tradeTime", StandardBasicTypes.STRING);
//		query.setFirstResult(offset);
//		query.setMaxResults(limit);
		List<String> strs = query.list();
//		List<Object> infos = query.list();
//		PageResult<Object> page = new PageResult<Object>();
//		page.setData(infos);
//		page.setTotal(infos.size());

		return strs;
	}
	
}