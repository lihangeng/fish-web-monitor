package com.yihuacomputer.fish.atmlog.service.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.atmlog.IAtmLogInfo;
import com.yihuacomputer.fish.api.atmlog.IAtmLogInfoService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.atmlog.entity.AtmLogInfo;

@Service
@Transactional
public class AtmLogInfoService implements IAtmLogInfoService {

    @Autowired
    private IOrganizationService orgService;
    @Autowired
    private IGenericDao dao;

	@SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
	public Map<String,IAtmLogInfo> getBackUpInfo(String backupStartDay , String backupEndDay) {
	    StringBuffer sql = new StringBuffer();
	    sql.append("select appLogs.DATE_TIME backupDate, ");
	    sql.append(" SUM(case appLogs.BACKUP_RESULT ");
	    sql.append(" when 'SUCCESS' then 1 ");
	    sql.append(" else 0 ");
	    sql.append(" end) backupSuccessNumber, ");
	    sql.append(" SUM(case appLogs.BACKUP_RESULT ");
	    sql.append(" when 'ERROR_CONNECT' then 1 ");
	    sql.append(" when 'ERROR_NOLOG' then 1 ");
	    sql.append(" when 'UNDO' then 1 ");
	    sql.append(" when 'ERROR' then 1 ");
	    sql.append(" else 0 ");
	    sql.append(" end) backupErrorNumber ");
	    sql.append(" from ATMC_APP_LOGS appLogs ");
	    sql.append(" where appLogs.DATE_TIME >= ").append("'"+backupStartDay+"'");
	    sql.append(" and appLogs.DATE_TIME <= ").append("'"+backupEndDay+"'");
        sql.append(" group by appLogs.DATE_TIME order by appLogs.DATE_TIME desc");
	    SQLQuery query = dao.getSQLQuery(sql.toString());
	    query.addScalar("backupDate",StandardBasicTypes.STRING);
	    query.addScalar("backupSuccessNumber",StandardBasicTypes.INTEGER);
	    query.addScalar("backupErrorNumber",StandardBasicTypes.INTEGER);
        List<Object> infos = query.list();
        Map<String,IAtmLogInfo> atmLogMap = new HashMap<String,IAtmLogInfo>();
        for(Object item : infos){
            IAtmLogInfo info = new AtmLogInfo();
            Object [] objs = (Object[])item;           
            info.setBackupSuccessNumber(Integer.parseInt(objs[1].toString()));
            info.setBackupErrorNumber(Integer.parseInt(objs[2].toString()));
            atmLogMap.put(objs[0].toString(), info);
        }
        return atmLogMap;		
	}

	private String getFilter(IFilter filter) {
	    StringBuffer f = new StringBuffer();
        for (IFilterEntry c : filter.entrySet()) {
            if(c.getKey().equals("appLogs.backupDate")){
                f.append(" and appLogs.DATE_TIME = '").append(c.getValue().toString()).append("'");
                continue;
            }
            if(c.getKey().equals("device.organization.orgFlag")){
            	f.append(" and org.ORG_FLAG like '").append(c.getValue()).append("%'");
                continue;
            }
        }
        return f.toString();
    }

	@SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    private int getTotal(String sqlStr) {
	    String sqlNoOrderBy = "";
	    int orderByIndex = sqlStr.indexOf("order by");
	    if(orderByIndex > 0){
	        sqlNoOrderBy = sqlStr.substring(0,orderByIndex);
	    }else{
	        sqlNoOrderBy = sqlStr;
	    }

        StringBuffer sql = new StringBuffer();
        sql = sql.append("select COUNT(a.id) as total from (").append(sqlNoOrderBy).append(") a");
        SQLQuery query = dao.getSQLQuery(sql.toString());
        query.addScalar("total", StandardBasicTypes.INTEGER);
        List<Integer> lists = query.list();
        return lists.get(0);
    }

    @Override
    @Transactional(readOnly=true)
	public List<IAtmLogInfo> list(long orgId) {
	    IOrganization org = orgService.get(String.valueOf(orgId));
	    StringBuffer sql = new StringBuffer();
	    sql.append("select org.ID id,org.NAME orgName,appLogs.DATE_TIME backupDate, ");
	    sql.append("SUM(case appLogs.BACKUP_RESULT ");
	    sql.append("when 'SUCCESS' then 1 ");
	    sql.append("else 0 ");
	    sql.append("end) backupSuccessNumber, ");
	    sql.append("SUM(case appLogs.BACKUP_RESULT ");
	    sql.append(" when 'ERROR_CONNECT' then 1 ");
	    sql.append(" when 'ERROR_NOLOG' then 1 ");
	    sql.append(" when 'UNDO' then 1 ");
	    sql.append(" when 'ERROR' then 1 ");
	    sql.append("else 0 ");
	    sql.append("end) backupErrorNumber  ");
	    sql.append("from ATMC_APP_LOGS appLogs,DEV_INFO device,SM_ORG org ");
	    sql.append("where appLogs.TERMINAL_ID = device.TERMINAL_ID and device.ORG_ID = org.ID ");
	    sql.append(" and org.ORG_FLAG like '%").append(org.getOrgFlag()).append("' ");
	    sql.append(" group by org.ID,org.NAME,appLogs.DATE_TIME");

	    String realSql = sql.toString();
	    SQLQuery query = dao.getSQLQuery(realSql);
	    query.addScalar("id",StandardBasicTypes.INTEGER);
	    query.addScalar("orgName",StandardBasicTypes.STRING);
	    query.addScalar("backupDate",StandardBasicTypes.STRING);
	    query.addScalar("backupSuccessNumber",StandardBasicTypes.INTEGER);
	    query.addScalar("backupErrorNumber",StandardBasicTypes.INTEGER);
        List<?> infos = query.list();
        List<IAtmLogInfo> lists = new ArrayList<IAtmLogInfo>();
        for(Object item : infos){
            IAtmLogInfo info = new AtmLogInfo();
            Object [] objs = (Object[])item;
            info.setId(Long.parseLong(objs[0].toString()));
            info.setOrgName(objs[1].toString());
            info.setBackupDate(DateUtils.getDate(objs[2].toString()));
            info.setBackupSuccessNumber(Integer.parseInt(objs[3].toString()));
            info.setBackupErrorNumber(Integer.parseInt(objs[4].toString()));
            info.setTotalBackupNumber(info.getBackupErrorNumber() + info.getBackupSuccessNumber());
            lists.add(info);
        }
		return lists;
	}

	@Override
	public List<IAtmLogInfo> listByFilter(long orgId, IFilter filter) {
	    IOrganization org = orgService.get(String.valueOf(orgId));
	    StringBuffer sql = new StringBuffer();
	    sql.append("select org.ID id,org.NAME orgName,appLogs.DATE_TIME backupDate, ");
	    sql.append("SUM(case appLogs.BACKUP_RESULT ");
	    sql.append("when 'SUCCESS' then 1 ");
	    sql.append("else 0 ");
	    sql.append("end) backupSuccessNumber, ");
	    sql.append("SUM(case appLogs.BACKUP_RESULT ");
	    sql.append(" when 'ERROR_CONNECT' then 1 ");
	    sql.append(" when 'ERROR_NOLOG' then 1 ");
	    sql.append(" when 'UNDO' then 1 ");
	    sql.append(" when 'ERROR' then 1 ");
	    sql.append("else 0 ");
	    sql.append("end) backupErrorNumber  ");
	    sql.append("from ATMC_APP_LOGS appLogs,DEV_INFO device,SM_ORG org ");
	    sql.append("where appLogs.TERMINAL_ID = device.TERMINAL_ID and device.ORG_ID = org.ID ");
	    sql.append(" and org.ORG_FLAG like '%").append(org.getOrgFlag()).append("' ").append(" ${filters}");
	    sql.append(" group by org.ID,org.NAME,appLogs.DATE_TIME");

	    String filterStr = getFilter(filter);
	    String realSql = sql.toString().replace("${filters}",filterStr);

//	    String realSql = sql.toString();
	    SQLQuery query = dao.getSQLQuery(realSql);
	    query.addScalar("id",StandardBasicTypes.INTEGER);
	    query.addScalar("orgName",StandardBasicTypes.STRING);
	    query.addScalar("backupDate",StandardBasicTypes.STRING);
	    query.addScalar("backupSuccessNumber",StandardBasicTypes.INTEGER);
	    query.addScalar("backupErrorNumber",StandardBasicTypes.INTEGER);
        List<?> infos = query.list();
        List<IAtmLogInfo> lists = new ArrayList<IAtmLogInfo>();
        for(Object item : infos){
            IAtmLogInfo info = new AtmLogInfo();
            Object [] objs = (Object[])item;
            info.setId(Long.parseLong(objs[0].toString()));
            info.setOrgName(objs[1].toString());
            info.setBackupDate(DateUtils.getDate(objs[2].toString()));
            info.setBackupSuccessNumber(Integer.parseInt(objs[3].toString()));
            info.setBackupErrorNumber(Integer.parseInt(objs[4].toString()));
            info.setTotalBackupNumber(info.getBackupErrorNumber() + info.getBackupSuccessNumber());
            lists.add(info);
        }
		return lists;
	}

}
