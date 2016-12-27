package com.yihuacomputer.fish.atmlog.service.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.atmlog.IAtmLog;
import com.yihuacomputer.fish.api.atmlog.IAtmLogService;
import com.yihuacomputer.fish.api.atmlog.IBackupRule;
import com.yihuacomputer.fish.atmlog.entity.AtmLog;
import com.yihuacomputer.fish.atmlog.rule.BackupRule;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class AtmLogService implements IAtmLogService{

	private final String LOAD_LOGS_HQL = "select a.terminalId,a.dateTime,d.ip from AtmLog a,Device d " +
			"where a.backupResult <>'SUCCESS' and a.backupResult <>'ERROR_NOLOG' and a.doTimes<100 and a.lastDoDate<> ? and " +
			" a.terminalId = d.terminalId";

	private final String LOAD_ERROR_HQL = "select d,a.ip from Device a,AtmLog d " +
			"where a.status=0 and d.dateTime=? and d.backupResult<>'SUCCESS' and d.terminalId = a.terminalId";

	private final String LOAD_CROWN_HQL = "from AtmLog a where a.backupResult='SUCCESS' and a.isCrownImport='0' and a.lastDoDate>=?";

	@Autowired
	private IGenericDao dao;

	@Override
	public IAtmLog make() {
		return new AtmLog();
	}

	@Override
	public void saveAtmLog(IAtmLog atmLog) {
		this.dao.save(atmLog);
	}

	@Override
	public void updateAtmLog(IAtmLog atmLog) {
		this.dao.update(atmLog);
	}

	@Override
    @Transactional(readOnly=true)
	public IAtmLog getAtmLog(String terminalId, String date) {
		IFilter filter = new Filter();
		filter.eq("terminalId", terminalId);
		filter.eq("dateTime", date);
		return dao.findUniqueByFilter(filter, AtmLog.class);
	}

	@Override
    @Transactional(readOnly=true)
	public List<IBackupRule> loadErrorLogs() {
		List<Object> results = this.dao.findByHQL(LOAD_LOGS_HQL, DateUtils.getDate(new Date()));

		List<IBackupRule> backRules = new ArrayList<IBackupRule>();
		for(Object log:results){
			Object [] logs = (Object [])log;
			IBackupRule rule = new BackupRule();
			rule.setTerminalId(String.valueOf(logs[0]));
			rule.setBackupDate(String.valueOf(logs[1]));
			rule.setTerminalIp(String.valueOf(logs[2]));
			backRules.add(rule);
		}
		return backRules;
	}

	@Override
    @Transactional(readOnly=true)
	public IPageResult<IAtmLog> pageList(int start, int limit, IFilter filter) {
		IPageResult<IAtmLog> result = this.dao.page(start, limit, filter, AtmLog.class);
		return result;
	}

    /**
     * @param start
     * @param limit
     * @param filter
     * @return
     */
    @Transactional(readOnly=true)
	public IPageResult<?> page(int start, int limit, IFilter filter) {
		StringBuffer hql = new StringBuffer();
		hql.append("select a.terminalId,a.dateTime,d.ip from AtmLog a,Device d ");
		hql.append("where a.backupResult <>'SUCCESS' and a.backupResult <>'ERROR_NOLOG' and a.doTimes<100 ");
		hql.append("and a.terminalId = d.terminalId");
		//根据filter增加条件
		return dao.page(start, limit, hql.toString());
	}

    @Override
    @Transactional(readOnly=true)
	public List<Object> loadDayErrorLogs(String date){

		List<Object> results = this.dao.findByHQL(LOAD_ERROR_HQL, date);

		return results;
	}


	@SuppressWarnings("unchecked")
	@Override
    @Transactional(readOnly=true)
	public IPageResult<IAtmLog> pageErrorList(int start, int limit, String date, long orgId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select a from AtmLog a ,Device d ");
		hql.append("where a.backupResult <>'SUCCESS' and a.terminalId = d.terminalId and a.dateTime = ? ");
		hql.append("and d.organization.id = ? ");
		IPageResult<IAtmLog> result = (IPageResult<IAtmLog>) dao.page(start, limit, hql.toString(), date, orgId);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
    @Transactional(readOnly=true)
	public IPageResult<IAtmLog> pageSuccessList(int start, int limit, String date, long orgId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select a from AtmLog a ,Device d ");
		hql.append("where a.backupResult = 'SUCCESS' and a.terminalId = d.terminalId and a.dateTime = ? ");
		hql.append("and d.organization.id = ? ");
		IPageResult<IAtmLog> result = (IPageResult<IAtmLog>) dao.page(start, limit, hql.toString(), date, orgId);
		return result;
	}

	@Override
    @Transactional(readOnly=true)
	public List<IAtmLog> loadDaySuccessLogs(String date, long orgId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select a from AtmLog a, Device d ");
		hql.append("where a.backupResult = 'SUCCESS' and a.terminalId = d.terminalId and a.dateTime = ? ");
		hql.append("and d.organization.id = ? ");

		List<IAtmLog> list = dao.findByHQL(hql.toString(), date, orgId);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
    @Transactional(readOnly=true)
	public List<IAtmLog> loadDayErrorLogs(String date, long orgId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select a from AtmLog a, Device d ");
		hql.append("where a.backupResult <>'SUCCESS' and a.terminalId = d.terminalId and a.dateTime = ? ");
		hql.append("and d.organization.id = ? ");
		IPageResult<IAtmLog> result = (IPageResult<IAtmLog>) dao.page(0, 2000, hql.toString(), date, orgId);
		return result.list();
	}

	@SuppressWarnings("unchecked")
	@Override
    @Transactional(readOnly=true)
	public List<IAtmLog> pageImportDevice(int offset, int limit) {
		String lastDay = DateUtils.getLastDate()+" 00:00:00";
		List<IAtmLog> list = (List<IAtmLog>) dao.page(offset,limit,LOAD_CROWN_HQL, lastDay).list();
		return list;
	}
}
