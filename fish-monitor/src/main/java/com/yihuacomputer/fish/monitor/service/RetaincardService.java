package com.yihuacomputer.fish.monitor.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.Operator;
import com.yihuacomputer.common.OrderBy;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.domain.util.DBType;
import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.monitor.business.CardStatus;
import com.yihuacomputer.fish.api.monitor.business.IRetaincard;
import com.yihuacomputer.fish.api.monitor.business.IRetaincardService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.monitor.entity.business.Retaincard;

@Service
@Transactional
public class RetaincardService implements IRetaincardService{

    @Autowired
    private IGenericDao dao;

    @Autowired
    private IOrganizationService orgService;

    @Autowired
    private LocalSessionFactoryBean sf;

	@Override
	public void remove(long id) {
		dao.delete(id, Retaincard.class);
	}

	@Override
	public IRetaincard get(long id) {
		return dao.get(id, Retaincard.class);
	}

	@Override
	public void update(IRetaincard retaincard) {
		dao.update(interface2Entity(retaincard, true));
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<IRetaincard> list() {
		return dao.loadAll(IRetaincard.class);
	}

	@Override
	@Transactional(readOnly=true)
	public Iterable<IRetaincard> list(IFilter filter) {
		return dao.findByFilter(filter, IRetaincard.class);
	}

	@Override
	public IRetaincard add(IRetaincard retaincard) {
		return dao.save(this.interface2Entity(retaincard, true));
	}

	private Retaincard interface2Entity(IRetaincard card, boolean load) {
		if (card instanceof Retaincard) {
			return (Retaincard) card;
		}
		return null;
	}

	@Override
	public List<IRetaincard> getCardByTerminalId(String terminalId) {
		List<IRetaincard> result = new ArrayList<IRetaincard>();
		Iterable<IRetaincard> list = list();
		for(IRetaincard item : list){
			if(item.getTerminalId().equals(terminalId)){
				result.add(item);
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
    @Override
	public List<IRetaincard> listCardByOrgId(long orgId, IFilter filter) {

		IOrganization org = orgService.get(String.valueOf(orgId));

		StringBuffer sql = new StringBuffer();
		sql.append("select DISTINCT ${columns} from (select retaincard.* from ATMC_RETAIN_CARD retaincard,DEV_INFO device,SM_ORG org,DEV_TYPE devType,DEV_VENDOR vendor ");
		sql.append("where retaincard.TERMINAL_ID = device.TERMINAL_ID and device.ORG_ID = org.ID and device.DEV_TYPE_ID = devType.ID and devType.DEV_VENDOR_ID = vendor.ID ");
		sql.append("and org.ORG_FLAG like ? ${filter} ");
		sql.append("UNION ");
		sql.append("select retaincard.* from ATMC_RETAIN_CARD retaincard,DEV_INFO device,SM_ORG org,DEV_TYPE devType,DEV_VENDOR vendor ");
		sql.append("where retaincard.TERMINAL_ID = device.TERMINAL_ID and retaincard.HANDOVER_ORG_ID = org.ID and device.DEV_TYPE_ID = devType.ID and devType.DEV_VENDOR_ID = vendor.ID and org.ORG_FLAG like ? ${filter1} ) retaincard ");

		String filterStr = getFilter(filter);
		String orgFlag = org.getOrgFlag();

		String sql1 = sql.toString();
		sql1 = sql1.replace("${columns}", "retaincard.*");
		sql1 = sql1.replace("${filter}", filterStr);
		sql1 = sql1.replace("${filter1}", filterStr);
		SQLQuery query = dao.getSQLQuery(sql1);
		query.addEntity(Retaincard.class);
		query.setString(0, orgFlag + "%");
		query.setString(1,  orgFlag + "%");
		return query.list();
	}


	/**
	 * 通过设备列表找出所有的吞卡信息
	 * @param deviceList
	 * @return
	 */
	public List<IRetaincard> getCardByDevice(List<IDevice> deviceList){
		List<IRetaincard> cardList = new ArrayList<IRetaincard>();
		for(IDevice device : deviceList){
			List<IRetaincard> list = getCardByTerminalId(device.getTerminalId());
			cardList.addAll(list);
		}
		return cardList;
	}

	@SuppressWarnings("unchecked")
    public IPageResult<IRetaincard> page(int offset, int limit, IFilter filter,long orgId) {
		StringBuffer sql = new StringBuffer();
		IOrganization org = orgService.get(String.valueOf(orgId));
		sql.append("select DISTINCT ${columns} from (select retaincard.* from ATMC_RETAIN_CARD retaincard,DEV_INFO device,SM_ORG org,DEV_TYPE devType,DEV_VENDOR vendor ");
		sql.append("where retaincard.TERMINAL_ID = device.TERMINAL_ID and device.ORG_ID = org.ID and device.DEV_TYPE_ID = devType.ID and devType.DEV_VENDOR_ID = vendor.ID ");
		sql.append("and org.ORG_FLAG like ? ${filter} ");
		sql.append("UNION ");
		sql.append("select retaincard.* from ATMC_RETAIN_CARD retaincard,DEV_INFO device,SM_ORG org,DEV_TYPE devType,DEV_VENDOR vendor ");
		sql.append("where retaincard.TERMINAL_ID = device.TERMINAL_ID and retaincard.HANDOVER_ORG_ID = org.ID and device.DEV_TYPE_ID = devType.ID and devType.DEV_VENDOR_ID = vendor.ID and org.ORG_FLAG like ?  ${filter1} ) retaincard ");



		String filterStr = getFilter(filter);
		String orgFlag = org.getOrgFlag();
		int total = getTotal(sql.toString(),filterStr,orgFlag);

		Set<OrderBy> orderBySet = filter.getOrders();
		if (orderBySet != null && orderBySet.size() > 0) {
			sql.append(" order by ");
		}
		int index = 0;
		for (OrderBy orderBy : orderBySet) {
			if (index > 0) {
				sql.append(", ");
			}
			sql.append(" retaincard.").append(orderBy.getPropertyName());
			sql.append(" ").append(orderBy.isAscending() ? "asc" : "desc");
			index++;
		}

		String sql1 = sql.toString();
		sql1 = sql1.replace("${columns}", "retaincard.*");
		sql1 = sql1.replace("${filter}", filterStr);
		sql1 = sql1.replace("${filter1}", filterStr);

		SQLQuery query = dao.getSQLQuery(sql1);
		query.addEntity(Retaincard.class);
		query.setString(0,  orgFlag + "%");
		query.setString(1,  orgFlag + "%");
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List<IRetaincard> lists = query.list();
		PageResult<IRetaincard> page = new PageResult<IRetaincard>();
		page.setData(lists);
		page.setTotal(total);
		return page;
	}

	private String getFilter(IFilter filter) {
		StringBuffer f = new StringBuffer();
		DBType dbType = new DBType(sf.getHibernateProperties());
		for (IFilterEntry c : filter.entrySet()) {
			if(c.getKey().equals("retaincard.cardRetainTime")){
			    if(c.getOperator().equals(Operator.LE)){
			        if(dbType.isOracle()){
			            f.append(" and retaincard.CARD_RETAIN_TIME <= ").append("to_date('").append(DateUtils.getTimestamp((Date)c.getValue())).append("','yyyy-mm-dd hh24:mi:ss')");
			        }else{
			            f.append(" and retaincard.CARD_RETAIN_TIME <= '").append(DateUtils.getTimestamp((Date)c.getValue())).append("'");
			        }
                }else{
                    if(dbType.isOracle()){
                        f.append(" and retaincard.CARD_RETAIN_TIME >= ").append("to_date('").append(DateUtils.getTimestamp((Date)c.getValue())).append("','yyyy-mm-dd hh24:mi:ss')");
                    }else{
                        f.append(" and retaincard.CARD_RETAIN_TIME >= '").append(DateUtils.getTimestamp((Date)c.getValue())).append("'");
                    }
                }
				continue;
			}
			if(c.getKey().equals("device.devType.devVendor.id")){
				f.append(" and vendor.ID = ").append(c.getValue());
				continue;
			}
			if(c.getKey().equals("device.devType.id")){
				f.append(" and devType.ID = ").append(c.getValue());
				continue;
			}
			if(c.getKey().equals("device.organization.orgFlag")){
				f.append(" and org.ORG_FLAG like '").append(c.getValue()).append("%'");
				continue;
			}
			if(c.getKey().equals("retaincard.status")){
				f.append(" and retaincard.STATUS like '%").append(c.getValue()).append("%'");
				continue;
			}
			if(c.getKey().equals("retaincard.accountNo")){
				f.append(" and retaincard.ACCOUNT_NO like '%").append(c.getValue()).append("%'");
				continue;
			}
			if(c.getKey().equals("device.awayFlag")){
				f.append(" and device.AWAY_FLAG = '").append(((AwayFlag)c.getValue()).name()).append("'");
				continue;
			}
			if(c.getKey().equals("retaincard.terminalId")){
				f.append(" and retaincard.TERMINAL_ID like '%").append(c.getValue()).append("%' ");
				continue;
			}
		}
		return f.toString();
	}

	@SuppressWarnings("unchecked")
    private int getTotal(String sqlStr,String filter,String orgFlag) {
		String sql = sqlStr;
		sql = sql.replace("${columns}", "count(*) as total");
		sql = sql.replace("${filter}", filter);
		sql = sql.replace("${filter1}", filter);
		SQLQuery query = dao.getSQLQuery(sql);
		query.addScalar("total",  StandardBasicTypes.INTEGER);
		query.setString(0,orgFlag +  "%");
		query.setString(1,orgFlag +  "%");
		List<Integer> lists = query.list();
		return lists.get(0);
	}

	@SuppressWarnings("unchecked")
    private int getTotal2(String sqlStr,String filter,String orgFlag,CardStatus status1, CardStatus status2) {
		String sql = sqlStr;
		sql = sql.replace("${columns}", "count(*) as total");
		sql = sql.replace("${filter}", filter);
		sql = sql.replace("${filter1}", filter);
		SQLQuery query = dao.getSQLQuery(sql);
		query.addScalar("total", StandardBasicTypes.INTEGER);
		query.setString(0, orgFlag +  "%");
		query.setString(1, status1.name());
		query.setString(2, status2.name());
		query.setString(3, orgFlag +  "%");
		query.setString(4, status1.name());
		query.setString(5, status2.name());
		List<Integer> lists = query.list();
		return lists.get(0);
	}

	@SuppressWarnings("unchecked")
    @Override
	public IPageResult<IRetaincard> page(int offset, int limit, IFilter filter,long orgId, CardStatus status1, CardStatus status2) {
		StringBuffer sql = new StringBuffer();
		IOrganization org = orgService.get(String.valueOf(orgId));

		sql.append("select DISTINCT ${columns} from (select retaincard.* from ATMC_RETAIN_CARD retaincard,DEV_INFO device,SM_ORG org,DEV_TYPE devType,DEV_VENDOR vendor ");
		sql.append("where retaincard.TERMINAL_ID = device.TERMINAL_ID and device.ORG_ID = org.ID and device.DEV_TYPE_ID = devType.ID and devType.DEV_VENDOR_ID = vendor.ID ");
		sql.append("and org.ORG_FLAG like ?  and retaincard.STATUS in (?,?) ${filter} ");
		sql.append("UNION ");
		sql.append("select retaincard.* from ATMC_RETAIN_CARD retaincard,DEV_INFO device,SM_ORG org,DEV_TYPE devType,DEV_VENDOR vendor ");
		sql.append("where retaincard.TERMINAL_ID = device.TERMINAL_ID and retaincard.HANDOVER_ORG_ID = org.ID and device.DEV_TYPE_ID = devType.ID and devType.DEV_VENDOR_ID = vendor.ID and org.ORG_FLAG like ? and retaincard.STATUS in (?,?) ${filter1} ) retaincard ");


		String filterStr = getFilter(filter);
		String orgFlag = org.getOrgFlag();
		int total = getTotal2(sql.toString(),filterStr,orgFlag,status1,status2);

		String sql1 = sql.toString();
		sql1 = sql1.replace("${columns}", "retaincard.*");
		sql1 = sql1.replace("${filter}", filterStr);
		sql1 = sql1.replace("${filter1}", filterStr);
		SQLQuery query = dao.getSQLQuery(sql1);
		query.addEntity(Retaincard.class);
		query.setString(0, orgFlag +  "%");
		query.setString(1, status1.name());
		query.setString(2, status2.name());
		query.setString(3, orgFlag +  "%");
		query.setString(4, status1.name());
		query.setString(5, status2.name());
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List<IRetaincard> lists = query.list();
		PageResult<IRetaincard> page = new PageResult<IRetaincard>();
		page.setData(lists);
		page.setTotal(total);
		return page;
	}

	@Override
	public IRetaincard make() {
		return new Retaincard();
	}

	@Override
	public List<Object> statisticsReatainCardTrend(int days) {
		StringBuffer hql = new StringBuffer();
		String timeStr = "CONCAT(CONCAT(CONCAT(YEAR(retaincard.cardRetainTime),'-'),CONCAT(MONTH(retaincard.cardRetainTime),'-')),DAY(retaincard.cardRetainTime))";
		hql.append("select ").append(timeStr).append(" as retainTime,count(*) as cardCount from ").append(Retaincard.class.getName());
		hql.append(" retaincard where retaincard.cardRetainTime > ?");
		hql.append(" group by ").append(timeStr);
		hql.append(" order by ").append(timeStr);
		return dao.findByHQL(hql.toString(),DateUtils.getDate(days));
	}


}
