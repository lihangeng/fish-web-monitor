package com.yihuacomputer.fish.monitor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.util.ThreeTuple;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.business.IHostRet;
import com.yihuacomputer.fish.api.monitor.business.ITransaction;
import com.yihuacomputer.fish.api.monitor.business.ITransactionService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.monitor.entity.business.Transaction;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class TransactionService implements ITransactionService {

    @Autowired
    private IGenericDao dao;

    @Autowired
    private IOrganizationService orgService;

    @Override
    public ITransaction make() {
        Transaction transaction = new Transaction();
        return transaction;
    }

    @Override
    public void save(ITransaction transaction) {
        this.dao.save(transaction);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Deprecated
    @Transactional(readOnly = true)
    public IPageResult<ITransaction> page(int offset, int limit, IFilter filter, long orgId) {
        StringBuffer hql = new StringBuffer();
        IOrganization org = orgService.get(String.valueOf(orgId));
        hql.append("select transaction from Transaction transaction,Device device ");
        hql.append("where transaction.terminalId = device.terminalId and device.organization.orgFlag like ? ");
        hql.append(" order by transaction.dateTime desc");
        IPageResult<ITransaction> page = (IPageResult<ITransaction>) dao.page(offset, limit, filter, hql.toString(),
                org.getOrgFlag() + "%");
        return page;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
	public IPageResult<ITransaction> page(int offset, int limit, IFilter filter) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Transaction transaction where 1=1 order by transaction.dateTime desc");
		IPageResult<ITransaction> page = (IPageResult<ITransaction>) dao.page(offset, limit, filter, hql.toString());
		return page;
	}


    @SuppressWarnings("unchecked")
    @Override
    @Deprecated
    @Transactional(readOnly = true)
    public IPageResult<ITransaction> pageBlackList(int offset, int limit, IFilter filter, long orgId) {
        StringBuffer hql = new StringBuffer();
        IOrganization org = orgService.get(String.valueOf(orgId));

        hql.append("select transaction from Transaction transaction , Device device ");
        hql.append("where transaction.terminalId = device.terminalId and device.organization.orgFlag like ? and  ");
        hql.append(" EXISTS(select cardNo from BlackListCard blackListCard where blackListCard.cardNo=transaction.debitAccount or blackListCard.cardNo=transaction.creditAccount) ");
        hql.append(" order by transaction.dateTime desc");

        IPageResult<ITransaction> page = (IPageResult<ITransaction>) dao.page(offset, limit, filter, hql.toString(),
        		org.getOrgFlag() + "%");
        return page;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Deprecated
    @Transactional(readOnly = true)
    public IPageResult<ITransaction> pageNoBlackList(int offset, int limit, IFilter filter, long orgId) {
        StringBuffer hql = new StringBuffer();
        IOrganization org = orgService.get(String.valueOf(orgId));

        hql.append("select transaction from Transaction transaction , Device device ");
        hql.append("where transaction.terminalId = device.terminalId and device.organization.orgFlag like ? and  ");
        hql.append(" NOT EXISTS(select cardNo from BlackListCard blackListCard where blackListCard.cardNo=transaction.debitAccount or blackListCard.cardNo=transaction.creditAccount) ");
        hql.append(" order by transaction.dateTime desc");

        IPageResult<ITransaction> page = (IPageResult<ITransaction>) dao.page(offset, limit, filter, hql.toString(),
        		org.getOrgFlag() + "%");
        return page;
    }

    @Override
    @Transactional(readOnly = true)
    public List<IHostRet> listHostRet() {
        return dao.loadAll(IHostRet.class);
    }

	@Override
	public List<Object> statisticsTransTrend(IFilter filter) {
		IFilterEntry startDate = filter.getFilterEntry("startDate") ;
		IFilterEntry endDate = filter.getFilterEntry("endDate") ;
		IFilterEntry orgFlag = filter.getFilterEntry("orgFlag") ;
		List<Object> paramList = new ArrayList<Object>() ;
		paramList.add(Integer.parseInt(startDate.getValue().toString())) ;
		paramList.add(Integer.parseInt(endDate.getValue().toString())) ;
		StringBuffer hql = new StringBuffer();
		hql.append("select transaction.transDate,count(transaction.id) as transCount from TransactionView transaction");
		if(orgFlag!=null){
			hql.append(",Organization org,Device device ") ;
		}
		hql.append(" where transaction.transDate >= ? and transaction.transDate <= ?");
		if(orgFlag!=null){
			hql.append(" and transaction.terminalId=device.terminalId and device.organization.id=org.id and org.orgFlag like ?") ;
			paramList.add(orgFlag.getValue().toString()+"%") ;
		}
		hql.append(" group by transaction.transDate");
		hql.append(" order by transaction.transDate");
		return dao.findByHQL(hql.toString(), paramList.toArray());
	}

	@Override
	public List<Object> statisticsTransHourTrend(IFilter filter) {
		IFilterEntry startDate = filter.getFilterEntry("startDate") ;
		IFilterEntry endDate = filter.getFilterEntry("endDate") ;
		IFilterEntry orgFlag = filter.getFilterEntry("orgFlag") ;
		List<Object> paramList = new ArrayList<Object>() ;
		paramList.add(Integer.parseInt(startDate.getValue().toString())) ;
		paramList.add(Integer.parseInt(endDate.getValue().toString())) ;
		StringBuffer hql = new StringBuffer();
		hql.append("select date_format(transaction.dateTime,'%H'),count(transaction.id) as transCount from TransactionView transaction");
		if(orgFlag!=null){
			hql.append(",Organization org,Device device ") ;
		}
		hql.append(" where transaction.transDate >= ? and transaction.transDate <= ?");
		if(orgFlag!=null){
			hql.append(" and transaction.terminalId=device.terminalId and device.organization.id=org.id and org.orgFlag like ?") ;
			paramList.add(orgFlag.getValue().toString()+"%") ;
		}
		hql.append(" group by date_format(transaction.dateTime,'%H')");
		hql.append(" order by date_format(transaction.dateTime,'%H')");
		return dao.findByHQL(hql.toString(), paramList.toArray());
	}

	@Override
	public List<ThreeTuple<String, Integer,Double>> statisticsTransCountForDevice(IFilter filter) {
		IFilterEntry startDate = filter.getFilterEntry("startDate") ;
		IFilterEntry endDate = filter.getFilterEntry("endDate") ;
		IFilterEntry terminalId = filter.getFilterEntry("terminalId") ;
		List<Object> paramList = new ArrayList<Object>() ;
		StringBuffer hql = new StringBuffer();
		hql.append("select transaction.transCode,count(transaction.id) as transCount,sum(transaction.amt) from TransactionView transaction");

		hql.append(" where transaction.transDate >= ? and transaction.transDate < ?");
		paramList.add(Integer.parseInt(startDate.getValue().toString())) ;
		paramList.add(Integer.parseInt(endDate.getValue().toString())) ;
		hql.append(" and transaction.terminalId= ?") ;
		paramList.add(terminalId.getValue().toString()) ;
		hql.append(" group by transaction.transCode");
		List<Object> list = dao.findByHQL(hql.toString(), paramList.toArray());
		List<ThreeTuple<String, Integer,Double>> result = new ArrayList<ThreeTuple<String, Integer,Double>>();
		for(Object obj:list){
			Object[] objs = (Object[])obj;
			ThreeTuple<String, Integer,Double> threeTuple = new ThreeTuple<String, Integer,Double>(String.valueOf(objs[0]),Integer.parseInt(String.valueOf(objs[1])),Double.parseDouble(String.valueOf(objs[2])));
			result.add(threeTuple);
		}
		return result;
	}
	
}
