package com.yihuacomputer.fish.monitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.business.IHostRet;
import com.yihuacomputer.fish.api.monitor.business.ITransactionView;
import com.yihuacomputer.fish.api.monitor.business.ITransactionViewService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.monitor.entity.business.TransactionView;

@Service
@Transactional
public class TransactionViewService implements ITransactionViewService {

    @Autowired
    private IGenericDao dao;

    @Autowired
    private IOrganizationService orgService;

    @Override
    public ITransactionView make() {
        ITransactionView transaction = new TransactionView();
        return transaction;
    }


    @SuppressWarnings("unchecked")
    @Override
    @Deprecated
    @Transactional(readOnly = true)
    public IPageResult<ITransactionView> page(int offset, int limit, IFilter filter, long orgId) {
        StringBuffer hql = new StringBuffer();
        IOrganization org = orgService.get(String.valueOf(orgId));
        hql.append("select transactionView from TransactionView transactionView,Device device ");
        hql.append("where transactionView.terminalId = device.terminalId and device.organization.orgFlag like ? ");
        hql.append(" order by transactionView.dateTime desc");
        IPageResult<ITransactionView> page = (IPageResult<ITransactionView>) dao.page(offset, limit, filter, hql.toString(),
        		org.getOrgFlag() + "%");
        return page;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
	public IPageResult<ITransactionView> page(int offset, int limit, IFilter filter) {
		StringBuffer hql = new StringBuffer();
		hql.append("from TransactionView transactionView where 1=1 order by transactionView.dateTime desc");
		IPageResult<ITransactionView> page = (IPageResult<ITransactionView>) dao.page(offset, limit, filter, hql.toString());
		return page;
	}


    @SuppressWarnings("unchecked")
    @Override
    @Deprecated
    @Transactional(readOnly = true)
    public IPageResult<ITransactionView> pageBlackList(int offset, int limit, IFilter filter, long orgId) {
        StringBuffer hql = new StringBuffer();
        IOrganization org = orgService.get(String.valueOf(orgId));

        hql.append("select transactionView from TransactionView transactionView , Device device ");
        hql.append("where transactionView.terminalId = device.terminalId and device.organization.orgFlag like ? and  ");
        hql.append(" EXISTS(select cardNo from BlackListCard blackListCard where blackListCard.cardNo=transactionView.debitAccount or blackListCard.cardNo=transactionView.creditAccount) ");
        hql.append(" order by transactionView.dateTime desc");

        IPageResult<ITransactionView> page = (IPageResult<ITransactionView>) dao.page(offset, limit, filter, hql.toString(),
        		org.getOrgFlag() + "%");
        return page;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Deprecated
    @Transactional(readOnly = true)
    public IPageResult<ITransactionView> pageNoBlackList(int offset, int limit, IFilter filter, long orgId) {
        StringBuffer hql = new StringBuffer();
        IOrganization org = orgService.get(String.valueOf(orgId));

        hql.append("select transaction from Transaction transaction , Device device ");
        hql.append("where transaction.terminalId = device.terminalId and device.organization.orgFlag like ? and  ");
        hql.append(" NOT EXISTS(select cardNo from BlackListCard blackListCard where blackListCard.cardNo=transaction.debitAccount or blackListCard.cardNo=transaction.creditAccount) ");
        hql.append(" order by transaction.dateTime desc");

        IPageResult<ITransactionView> page = (IPageResult<ITransactionView>) dao.page(offset, limit, filter, hql.toString(),
        		org.getOrgFlag() + "%");
        return page;
    }

    @Override
    @Transactional(readOnly = true)
    public List<IHostRet> listHostRet() {
        return dao.loadAll(IHostRet.class);
    }

}
