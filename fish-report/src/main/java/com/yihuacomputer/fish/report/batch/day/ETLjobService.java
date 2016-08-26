package com.yihuacomputer.fish.report.batch.day;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.batch.IETLjobService;

@Service
@Transactional
public class ETLjobService implements IETLjobService {

	@Autowired
	private IGenericDao dao;

	private int getTotal(String sqlStr) {
		StringBuffer sql = new StringBuffer();
		sql = sql.append("select COUNT(a.id) as total from (").append(sqlStr).append(") a");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.addScalar("total", StandardBasicTypes.INTEGER);
		@SuppressWarnings("unchecked")
		List<Integer> lists = query.list();
		return lists.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IPageResult<Object> listData(int offset, int limit, IFilter filter) {
		StringBuffer sql = new StringBuffer();
		IFilterEntry startTime = filter.getFilterEntry("startTime");
		IFilterEntry endTime = filter.getFilterEntry("endTime");
		sql.append("select * from (select i.JOB_INSTANCE_ID id,i.JOB_NAME jobName,e.START_TIME startTime,e.END_TIME endTime,p.string_val tradeTime,e.EXIT_CODE operaResult ");
		sql.append("from batch_job_execution e,batch_job_instance i,BATCH_JOB_EXECUTION_PARAMS p ");
		sql.append("where i.JOB_INSTANCE_ID=e.JOB_INSTANCE_ID and e.JOB_EXECUTION_ID=p.JOB_EXECUTION_ID and p.key_name='tradeTime' ");
//		sql.append("select e.JOB_EXECUTION_ID id,i.JOB_NAME jobName,e.START_TIME startTime,e.END_TIME endTime,p.string_val tradeTime,e.EXIT_CODE operaResult ");
//		sql.append("from batch_job_execution e,batch_job_instance i,BATCH_JOB_EXECUTION_PARAMS p ");
//		sql.append("where i.JOB_INSTANCE_ID=e.JOB_INSTANCE_ID and e.JOB_INSTANCE_ID=p.JOB_EXECUTION_ID and p.key_name='tradeTime' ");
		
		if (startTime != null) {
			sql.append(" and e.START_TIME >= '");
			sql.append(startTime.getValue()+"'");
		}

		if (endTime != null) {
			sql.append("  and e.END_TIME <= '");
			sql.append(endTime.getValue()+"'");
		}
		sql.append("order by startTime desc) a GROUP BY a.id order by a.tradeTime");
		int total = getTotal(sql.toString());
//		sql.append(" order by tradeTime");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.addScalar("id", StandardBasicTypes.BIG_INTEGER);
		query.addScalar("jobName", StandardBasicTypes.STRING);
		query.addScalar("startTime", StandardBasicTypes.TIMESTAMP);
		query.addScalar("endTime", StandardBasicTypes.TIMESTAMP);
		query.addScalar("tradeTime", StandardBasicTypes.STRING);
		query.addScalar("operaResult", StandardBasicTypes.STRING);
		query.setFirstResult(offset);
		query.setMaxResults(limit);

		List<Object> infos = query.list();
		PageResult<Object> page = new PageResult<Object>();
		page.setData(infos);
		page.setTotal(total);

		return page;
	}

	@Override
	public void deteleDayOpera(String tradeTime) {
		dao.getSQLQuery("delete from ATMC_TRANSACTION_DAYS where trans_date="+Integer.parseInt(tradeTime)).executeUpdate();
	}

	@Override
	public void deteleMonthOpera(String tradeTime) {
		dao.getSQLQuery("delete from ATMC_TRANSACTION_MONTHS where trans_date="+Integer.parseInt(tradeTime)).executeUpdate();
		
	}

	@Override
	public String getErrorMsg(long id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t.EXIT_MESSAGE from batch_step_execution t where t.JOB_EXECUTION_ID="+id);
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.addScalar("EXIT_MESSAGE", StandardBasicTypes.STRING);
		@SuppressWarnings("unchecked")
		List<String> infos = query.list();
		return infos.get(0);
	}

	@Override
	public void reStartMonthOpera(String tradeTime) {
		StringBuffer sql = new StringBuffer();
		sql.append("select p.JOB_EXECUTION_ID from batch_job_execution_params p where p.STRING_VAL='"+tradeTime+"'");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.addScalar("JOB_EXECUTION_ID", StandardBasicTypes.BIG_INTEGER);
		List<Object> infos = query.list();
		if(!infos.isEmpty()){
			dao.getSQLQuery("delete from BATCH_STEP_EXECUTION_CONTEXT where step_execution_id="+infos.get(0)).executeUpdate();
			dao.getSQLQuery("delete from BATCH_JOB_EXECUTION_CONTEXT where job_execution_id="+infos.get(0)).executeUpdate();
			dao.getSQLQuery("delete from BATCH_STEP_EXECUTION where step_execution_id="+infos.get(0)).executeUpdate();
			dao.getSQLQuery("delete from BATCH_JOB_EXECUTION_PARAMS where job_execution_id="+infos.get(0)).executeUpdate();
			dao.getSQLQuery("delete from BATCH_JOB_EXECUTION where job_execution_id="+infos.get(0)).executeUpdate();
			dao.getSQLQuery("delete from BATCH_JOB_INSTANCE where job_instance_id="+infos.get(0)).executeUpdate();
		}
	}

}
