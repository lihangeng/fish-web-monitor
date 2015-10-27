package com.yihuacomputer.fish.atmlog.service.db;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.Operator;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.atmlog.IAtmLogGlobalStatistics;
import com.yihuacomputer.fish.api.atmlog.IAtmLogGlobalStatisticsService;
import com.yihuacomputer.fish.api.atmlog.IErrorLogsDeviceInfo;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.atmlog.entity.AtmLogGlobalStatistics;
import com.yihuacomputer.fish.atmlog.entity.ErrorLogsDeviceInfo;

@Service
public class AtmLogGlobalStatisticsService implements IAtmLogGlobalStatisticsService {

	@Autowired
	private IOrganizationService orgService;
	@Autowired
	private IGenericDao dao;

	@Override
    @Transactional(readOnly=true)
	public IPageResult<IAtmLogGlobalStatistics> pageList(int start, int limit, IFilter filter, long orgId) {
		IOrganization org = orgService.get(String.valueOf(orgId));
		StringBuffer sql = new StringBuffer();
		sql.append("select org.id as id,org.CODE as orgCode,org.NAME as orgName,vendor.NAME as deviceVendor, ");
		sql.append("SUM(case appLogs.BACKUP_RESULT ");
		sql.append("when 'SUCCESS' then 1 ");
		sql.append("else 0 ");
		sql.append("end) as backupSuccessNumber, ");
		sql.append("SUM(case appLogs.BACKUP_RESULT ");
		sql.append(" when 'ERROR_CONNECT' then 1 ");
		sql.append(" when 'ERROR_NOLOG' then 1 ");
		sql.append(" when 'UNDO' then 1 ");
		sql.append(" when 'ERROR' then 1 ");
		sql.append("else 0 ");
		sql.append("end) as backupErrorNumber  ");
		sql.append("from atmc_app_logs appLogs,dev_info device,sm_org org,dev_type type,dev_vendor vendor ");
		sql.append("where appLogs.TERMINAL_ID = device.TERMINAL_ID and device.DEV_TYPE_ID = type.ID and type.DEV_VENDOR_ID = vendor.ID ");
		sql.append(" and device.ORG_ID = org.ID ");
		sql.append(" and org.org_flag like '%").append(org.getOrgFlag()).append("' ").append(" ${filters}");
		sql.append(" group by org.ID, vendor.ID");
		String filterStr = getFilter(filter);
		String realSql = sql.toString().replace("${filters}", filterStr);
		int total = getTotal(realSql);
		SQLQuery query = dao.getSQLQuery(realSql.toString());
		query.addScalar("id", StandardBasicTypes.INTEGER);
		query.addScalar("orgCode", StandardBasicTypes.STRING);
		query.addScalar("orgName", StandardBasicTypes.STRING);
		query.addScalar("deviceVendor", StandardBasicTypes.STRING);
		query.addScalar("backupSuccessNumber", StandardBasicTypes.INTEGER);
		query.addScalar("backupErrorNumber", StandardBasicTypes.INTEGER);
		query.setFirstResult(start);
		query.setMaxResults(limit);
		List<?> infos = query.list();
		List<IAtmLogGlobalStatistics> lists = new ArrayList<IAtmLogGlobalStatistics>();
		for (Object item : infos) {
			IAtmLogGlobalStatistics info = new AtmLogGlobalStatistics();
			Object[] objs = (Object[]) item;
			info.setId(Long.parseLong(objs[0].toString()));
			info.setOrgCode(objs[1].toString());
			info.setOrgName(objs[2].toString());
			// info.setBackupDate(DateUtils.getDate(objs[2].toString()));
			info.setDeviceVendor(objs[3].toString());
			info.setBackupSuccessNumber(Integer.parseInt(objs[4].toString()));
			info.setBackupErrorNumber(Integer.parseInt(objs[5].toString()));
			lists.add(info);
		}
		PageResult<IAtmLogGlobalStatistics> page = new PageResult<IAtmLogGlobalStatistics>();
		page.setData(lists);
		page.setTotal(total);
		return page;
	}

	private String getFilter(IFilter filter) {
		StringBuffer f = new StringBuffer();
		for (IFilterEntry c : filter.entrySet()) {
			if (c.getKey().equals("appLogs.backupDate")) {
				if (c.getOperator().equals(Operator.LE)) {
					f.append(" and appLogs.DATE_TIME <= '").append(c.getValue().toString()).append("'");
				} else {
					f.append(" and appLogs.DATE_TIME >= '").append(c.getValue().toString()).append("'");
				}
			}
			if (c.getKey().equals("device.organization.orgFlag")) {
				f.append(" and org.org_flag like '%").append(c.getValue()).append("'");
				continue;
			}
		}
		return f.toString();
	}

	@SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
	private int getTotal(String sqlStr) {
		StringBuffer sql = new StringBuffer();
		sql = sql.append("select COUNT(a.id) as total from (").append(sqlStr).append(") a");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.addScalar("total", StandardBasicTypes.INTEGER);
		List<Integer> lists = query.list();
		return lists.get(0);
	}

	@Override
    @Transactional(readOnly=true)
	public List<IAtmLogGlobalStatistics> list(IFilter filter, long orgId) {
		IOrganization org = orgService.get(String.valueOf(orgId));
		StringBuffer sql = new StringBuffer();
		sql.append("select org.id as id,org.NAME as orgName,org.CODE as orgCode, vendor.NAME as deviceVendor, ");
		sql.append("SUM(case appLogs.BACKUP_RESULT ");
		sql.append("when 'SUCCESS' then 1 ");
		sql.append("else 0 ");
		sql.append("end) as backupSuccessNumber, ");
		sql.append("SUM(case appLogs.BACKUP_RESULT ");
		sql.append(" when 'ERROR_CONNECT' then 1 ");
		sql.append(" when 'ERROR_NOLOG' then 1 ");
		sql.append(" when 'UNDO' then 1 ");
		sql.append(" when 'ERROR' then 1 ");
		sql.append("else 0 ");
		sql.append("end) as backupErrorNumber  ");
		sql.append("from atmc_app_logs appLogs,dev_info device,sm_org org,dev_type type,dev_vendor vendor ");
		sql.append("where appLogs.TERMINAL_ID = device.TERMINAL_ID and device.DEV_TYPE_ID = type.ID and type.DEV_VENDOR_ID = vendor.ID ");
		sql.append(" and device.ORG_ID = org.ID");
		sql.append(" and org.org_flag like '%").append(org.getOrgFlag()).append("' ").append(" ${filters}");
		sql.append(" group by org.ID, vendor.ID");

		String filterStr = getFilter(filter);
		String realSql = sql.toString().replace("${filters}", filterStr);
		SQLQuery query = dao.getSQLQuery(realSql.toString());
		query.addScalar("id", StandardBasicTypes.INTEGER);
		query.addScalar("orgName", StandardBasicTypes.STRING);
		// query.addScalar("backupDate",StandardBasicTypes.STRING);
		query.addScalar("orgCode", StandardBasicTypes.STRING);
		query.addScalar("deviceVendor", StandardBasicTypes.STRING);
		query.addScalar("backupSuccessNumber", StandardBasicTypes.INTEGER);
		query.addScalar("backupErrorNumber", StandardBasicTypes.INTEGER);
		List<?> infos = query.list();
		List<IAtmLogGlobalStatistics> lists = new ArrayList<IAtmLogGlobalStatistics>();
		for (Object item : infos) {
			IAtmLogGlobalStatistics info = new AtmLogGlobalStatistics();
			Object[] objs = (Object[]) item;
			info.setId(Long.parseLong(objs[0].toString()));
			info.setOrgName(objs[1].toString());
			// info.setBackupDate(DateUtils.getDate(objs[2].toString()));
			info.setOrgCode(objs[2].toString());
			info.setDeviceVendor(objs[3].toString());
			info.setBackupSuccessNumber(Integer.parseInt(objs[4].toString()));
			info.setBackupErrorNumber(Integer.parseInt(objs[5].toString()));
			lists.add(info);
		}
		return lists;
	}

	@Override
    @Transactional(readOnly=true)
	public List<IErrorLogsDeviceInfo> loadDayErrorLogs(IFilter filter, long orgId) {

		StringBuffer sql = new StringBuffer();
		IOrganization org = orgService.get(String.valueOf(orgId));
		sql.append("SELECT devInfo.TERMINAL_ID as terminalId, org.ID as orgId, org.NAME as orgName, ");
		sql.append("devInfo.ADDRESS as address, appLogs.BACKUP_RESULT as backupResult, appLogs.DATE_TIME as dateTime,devExtend.INSTALL_DATE as installDate, ");
		sql.append("devInfo.IP as ip ");
		sql.append("from dev_info devInfo, atmc_app_logs appLogs, sm_org org ");
		sql.append("WHERE appLogs.TERMINAL_ID = devInfo.TERMINAL_ID ");
		sql.append("and devInfo.ORG_ID = org.ID ");
		sql.append("and appLogs.BACKUP_RESULT <> 'SUCCESS' ");
		sql.append("and org.org_flag like '%").append(org.getOrgFlag()).append("' ");
		sql.append(" ${filters}");
		sql.append(" group by devInfo.TERMINAL_ID, appLogs.DATE_TIME order by appLogs.DATE_TIME desc");

		String filterStr = getFilter(filter);
		String realSql = sql.toString().replace("${filters}", filterStr);

		SQLQuery query = dao.getSQLQuery(realSql.toString());
		query.addScalar("terminalId", StandardBasicTypes.STRING);
		query.addScalar("orgId", StandardBasicTypes.INTEGER);
		query.addScalar("orgName", StandardBasicTypes.STRING);
		query.addScalar("address", StandardBasicTypes.STRING);
		query.addScalar("backupResult", StandardBasicTypes.STRING);
		query.addScalar("dateTime", StandardBasicTypes.STRING);
		query.addScalar("ip", StandardBasicTypes.STRING);

		List<?> infos = query.list();
		List<IErrorLogsDeviceInfo> lists = new ArrayList<IErrorLogsDeviceInfo>();
		for (Object item : infos) {
			IErrorLogsDeviceInfo info = new ErrorLogsDeviceInfo();
			Object[] objs = (Object[]) item;
			info.setTerminalId(objs[0].toString());
			info.setOrgId(String.valueOf(objs[1].toString()));
			info.setOrgName(objs[2].toString());
			info.setAddress(objs[3] == null ? null : objs[3].toString());
			info.setBackupResult(objs[4].toString());
			info.setDateTime(objs[5].toString());
			info.setInstallDate(objs[6] == null ? null : objs[6].toString());
			info.setIP(objs[7].toString());
			lists.add(info);
		}
		return lists;
	}

}
